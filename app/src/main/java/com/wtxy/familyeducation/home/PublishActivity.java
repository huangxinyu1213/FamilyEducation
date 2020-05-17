package com.wtxy.familyeducation.home;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.wtxy.familyeducation.BaseActivity;
import com.wtxy.familyeducation.R;
import com.wtxy.familyeducation.constant.Const;
import com.wtxy.familyeducation.iview.IPublishView;
import com.wtxy.familyeducation.presenter.PublishPresenter;
import com.wtxy.familyeducation.util.ToastUtil;

public class PublishActivity extends BaseActivity implements IPublishView {
   private EditText edtTitle,edtSubTitle,edtLink,editContent;
   private PublishPresenter mPresenter;
   private boolean isPubNews;
   private View newsContent,noticeContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_publish);
        super.onCreate(savedInstanceState);
        mPresenter = new PublishPresenter(this);
        isPubNews = getIntent().getBooleanExtra(Const.KEY_ISNEWS,false);
        if (isPubNews) {
            showTitle("发布新闻");
        }else {
            showTitle("发布公告");
        }
        showRightBtn("发布");
        initView();
    }

    private void initView() {
        edtTitle = findViewById(R.id.edt_title);
        edtSubTitle = findViewById(R.id.edt_other_title);
        edtLink = findViewById(R.id.edt_link);
        editContent = findViewById(R.id.edt_notice_content);
        newsContent = findViewById(R.id.news_content);
        noticeContent = findViewById(R.id.notice_content);
        if (isPubNews){
            newsContent.setVisibility(View.VISIBLE);
            noticeContent.setVisibility(View.GONE);
        }else {
            newsContent.setVisibility(View.GONE);
            noticeContent.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRightBtnClick() {
        super.onRightBtnClick();
        if (isPubNews) {
            mPresenter.pubNews();
        }else {
            mPresenter.puNotices();
        }
    }

    @Override
    public String getPubTitle() {
        return edtTitle.getText().toString();
    }

    @Override
    public String getOtherTitle() {
        return edtSubTitle.getText().toString();
    }

    @Override
    public String getLink() {
        return edtLink.getText().toString();
    }

    @Override
    public String getNoticeContent() {
        return editContent.getText().toString();
    }

    @Override
    public void onPublishSuccess() {
        setResult(1);
        finish();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {
        ToastUtil.showShortToast(this,msg);
    }
}
