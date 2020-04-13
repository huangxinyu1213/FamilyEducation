package com.wtxy.familyeducation.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.wtxy.familyeducation.BaseActivity;
import com.wtxy.familyeducation.R;
import com.wtxy.familyeducation.adapter.CommonListAdapter;
import com.wtxy.familyeducation.bean.BaseItemBean;
import com.wtxy.familyeducation.bean.SubjectInfo;
import com.wtxy.familyeducation.constant.Const;
import com.wtxy.familyeducation.iview.ISubjectListView;
import com.wtxy.familyeducation.presenter.SubjectPresenter;
import com.wtxy.familyeducation.util.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class SubjectListActivity extends BaseActivity implements ISubjectListView {

    private boolean isSelect;
    private CommonListAdapter<BaseItemBean> mAdapter;
    private List<BaseItemBean> mData = new ArrayList<>();
    private ListView mListView;
    private SubjectPresenter subjectPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_subject_list);
        super.onCreate(savedInstanceState);
        isSelect = getIntent().getBooleanExtra(Const.KEY_IS_SELECT,false);
        if (isSelect){
            showTitle("科目选择");
        }else {
            showTitle("科目管理");
            showRightBtn("新增");
        }
        mAdapter = new CommonListAdapter<>(this,mData,R.layout.common_list_item);
        mListView = findViewById(R.id.manage_content);
        mListView.setAdapter(mAdapter);
        if (isSelect){
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent();
                    intent.putExtra(Const.KEY_SUBJECT_INFO,mData.get(position));
                    setResult(1,intent);
                    finish();
                }
            });
        }
        subjectPresenter = new SubjectPresenter(this);
        subjectPresenter.loadSubjectList();
    }

    @Override
    public void onRightBtnClick() {
        super.onRightBtnClick();
        showAddDialog();
    }

    @Override
    public void refrshSubJectList(List<SubjectInfo> list) {
         mData.clear();
         mData.addAll(list);
         mAdapter.notifyDataSetChanged();
    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {

    }

    private void showAddDialog(){
        View view = LayoutInflater.from(this).inflate(R.layout.layout_subject_add,null,false);
        final AlertDialog dialog = new AlertDialog.Builder(this).setView(view).create();

        TextView btn_cancel_high_opion = view.findViewById(R.id.btn_cancel);
        TextView btn_agree_high_opion = view.findViewById(R.id.btn_confirm);

        btn_cancel_high_opion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        btn_agree_high_opion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
        //此处设置位置窗体大小，我这里设置为了手机屏幕宽度的3/4  注意一定要在show方法调用后再写设置窗口大小的代码，否则不起效果会
        dialog.getWindow().setLayout((ScreenUtils.getScreenWidth(this)/4*3), LinearLayout.LayoutParams.WRAP_CONTENT);
    }
}
