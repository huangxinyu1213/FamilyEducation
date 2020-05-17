package com.wtxy.familyeducation.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wtxy.familyeducation.R;
import com.wtxy.familyeducation.adapter.MessageManagePageAdapter;
import com.wtxy.familyeducation.adapter.NewsAdapter;
import com.wtxy.familyeducation.adapter.NoticeAdapter;
import com.wtxy.familyeducation.bean.News;
import com.wtxy.familyeducation.bean.Notices;
import com.wtxy.familyeducation.constant.Const;
import com.wtxy.familyeducation.home.PublishActivity;
import com.wtxy.familyeducation.iview.IMessageManageView;
import com.wtxy.familyeducation.presenter.MessageManagePresenter;
import com.wtxy.familyeducation.util.ToastUtil;
import com.wtxy.familyeducation.view.BottomDialog;
import com.wtxy.familyeducation.web.WebActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yiwenhui
 * @Date: 2020/2/25
 * @Describe: 信息管理frgment
 */
public class MessageManageFragment extends BaseFragment implements IMessageManageView,View.OnClickListener{
    private static MessageManageFragment mInstance;
    private final int PAGE_NEWS = 1;
    private final int PAGE_NOTICE = 2;
    private TextView btnNews;
    private TextView btnNotices;
    private ViewPager vp;
    private View messageView;
    private View noticeView;
    private ListView messageListView;
    private ListView noticeListView;
    private NewsAdapter newsAdapter;
    private NoticeAdapter noticeAdapter;
    private List<News> newsData;
    private List<Notices> noticeData;
    private MessageManagePresenter mPresenter;
    public static MessageManageFragment getInstance(){
        if (mInstance == null){
            mInstance = new MessageManageFragment();
        }
        return mInstance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message_manage,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initPager(view);
    }

    /**
     * 初始化pager信息
     */
    private void initPager(View view) {
        messageView = LayoutInflater.from(getContext()).inflate(R.layout.layout_news_list,null);
        noticeView = LayoutInflater.from(getContext()).inflate(R.layout.layout_notice_list,null);
        List<View> list = new ArrayList<>();
        list.add(messageView);
        list.add(noticeView);
        vp = view.findViewById(R.id.vp);
        MessageManagePageAdapter pageAdapter = new MessageManagePageAdapter(list);
        vp.setAdapter(pageAdapter);
        vp.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {

            }
        });
        messageListView = messageView.findViewById(R.id.news_content);
        noticeListView = noticeView.findViewById(R.id.notice_content);
        newsData = new ArrayList<>();
        noticeData = new ArrayList<>();
        newsAdapter = new NewsAdapter(getContext(),newsData,R.layout.item_news);
        noticeAdapter = new NoticeAdapter(getContext(),noticeData,R.layout.item_notice);
        messageListView.setAdapter(newsAdapter);
        noticeListView.setAdapter(noticeAdapter);
        mPresenter = new MessageManagePresenter(this);
        mPresenter.loadNews();
        mPresenter.loadNotices();
        messageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                goToWebActivity(newsData.get(i).news_url,"新闻");
            }
        });
        noticeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                goToWebActivity(noticeData.get(i).notice_detail,"公告");
            }
        });
    }

    private void initView(View view) {
        btnNews = view.findViewById(R.id.btn_news);
        btnNotices = view.findViewById(R.id.btn_notice);
        btnNews.setSelected(true);
        btnNews.setOnClickListener(this);
        btnNotices.setOnClickListener(this);
        ImageView btnAdd = view.findViewById(R.id.add);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      switch (view.getId()){
          case R.id.btn_news:
              switchPage(PAGE_NEWS);
              break;
          case R.id.btn_notice:
              switchPage(PAGE_NOTICE);
              break;
          case R.id.add:
              showDialog();
              break;
      }
    }

    private void switchPage(int page) {
        if (page == PAGE_NEWS){
            btnNews.setSelected(true);
            btnNotices.setSelected(false);
            btnNews.setTextColor(getResources().getColor(R.color.common_white));
            btnNotices.setTextColor(getResources().getColor(R.color.commen_blue));
            vp.setCurrentItem(0);
        }else {
            btnNotices.setSelected(true);
            btnNews.setSelected(false);
            btnNotices.setTextColor(getResources().getColor(R.color.common_white));
            btnNews.setTextColor(getResources().getColor(R.color.commen_blue));
            vp.setCurrentItem(1);
        }
    }

    @Override
    public void refreshNewsView(List<News> news) {
       if (null == news || news.isEmpty()){
           return;
       }
       newsData.clear();
       newsData.addAll(news);
       newsAdapter.notifyDataSetChanged();
    }

    @Override
    public void refreshNoticeView(List<Notices> notices) {
       if (null == notices || notices.isEmpty()){
           return;
       }
       noticeData.clear();
       noticeData.addAll(notices);
       noticeAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {
        ToastUtil.showShortToast(getContext(),msg);
    }

    private void showDialog(){
        BottomDialog bottomDialog = new BottomDialog(getContext(),R.layout.layout_bottom_dialog,new int[]{R.id.btn_pub_news,
                R.id.btn_pub_notice,R.id.btn_cancel});
        bottomDialog.setOnBottomItemClickListener(new BottomDialog.OnBottomItemClickListener() {
            @Override
            public void onBottomItemClick(BottomDialog dialog, View view) {
              dialog.dismiss();
              Intent intent = new Intent(getActivity(), PublishActivity.class);
              int requestCode = 0;
              switch (view.getId()){
                  case R.id.btn_pub_news:
                      requestCode = Const.REQUEST_PUB_NEWS;
                      intent.putExtra(Const.KEY_ISNEWS,true);
                      break;
                  case R.id.btn_pub_notice:
                      requestCode =  Const.REQUEST_PUB_NOTICE;
                      intent.putExtra(Const.KEY_ISNEWS,false);
                      break;
                 }
                getActivity().startActivityForResult(intent,requestCode);
            }
        });
        bottomDialog.show();
    }


    private void goToWebActivity(String url,String title) {
        Intent intent = new Intent(getActivity(), WebActivity.class);
        intent.putExtra(WebActivity.TITLE, title);
        intent.putExtra(WebActivity.WEB_URL, url);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Const.REQUEST_PUB_NEWS){
            mPresenter.loadNews();
            return;
        }
        if (requestCode == Const.REQUEST_PUB_NOTICE){
            mPresenter.loadNotices();
            return;
        }
    }
}
