package com.wtxy.familyeducation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wtxy.familyeducation.BaseActivity;
import com.wtxy.familyeducation.R;
import com.wtxy.familyeducation.bean.HomeWorkInfo;
import com.wtxy.familyeducation.iview.IHomeWorkInfoView;
import com.wtxy.familyeducation.presenter.AddOrUpdateHomeWorkInfoPresenter;
import com.wtxy.familyeducation.user.HomeworkInfo;
import com.wtxy.familyeducation.util.DateUtils;

import java.util.Date;

public class HomeworkInfoActivity extends BaseActivity implements IHomeWorkInfoView {
    private EditText edtTitle;
    private EditText edtSubTitle;
    private TextView tvClass;
    private RelativeLayout rl_check_class;

    private HomeworkInfo mHomeworkInfo;
    private AddOrUpdateHomeWorkInfoPresenter presenter;
    private boolean isAdd;
    private int classId;
    private String className;

    public static Intent newIntent(Context context, HomeworkInfo homeworkInfo) {
        Intent intent = new Intent(context, HomeworkInfoActivity.class);
        intent.putExtra("HomeworkInfo", homeworkInfo);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_homework_info);
        super.onCreate(savedInstanceState);
        presenter = new AddOrUpdateHomeWorkInfoPresenter(this);
        mHomeworkInfo = (HomeworkInfo) getIntent().getSerializableExtra("HomeworkInfo");
        isAdd = mHomeworkInfo == null;
        if (mHomeworkInfo != null) {
            classId = mHomeworkInfo.class_id;
            className = mHomeworkInfo.class_name;
        }
        showTitle("作业详情");
        showRightBtn("保存");
        edtTitle = findViewById(R.id.edtTitle);
        edtSubTitle = findViewById(R.id.edtSubTitle);
        tvClass = findViewById(R.id.tvClass);
        rl_check_class = findViewById(R.id.rl_check_class);
        rl_check_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeworkInfoActivity.this, ChoiceClassActivity.class);
                startActivityForResult(intent, 1000);
            }
        });
        if (mHomeworkInfo != null) {
            edtTitle.setText(mHomeworkInfo.hw_title);
            edtSubTitle.setText(mHomeworkInfo.hw_detail);
            tvClass.setText(className);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == 1000) {
            className = data.getStringExtra("class_name");
            classId = data.getIntExtra("class_id",0);
            tvClass.setText(className);
        }
    }

    @Override
    public void onRightBtnClick() {
        super.onRightBtnClick();
        if (mHomeworkInfo == null) {
            mHomeworkInfo = new HomeworkInfo();
        }
        mHomeworkInfo.hw_title = edtTitle.getText().toString().trim();
        mHomeworkInfo.hw_detail = edtSubTitle.getText().toString().trim();
        mHomeworkInfo.hw_time = (DateUtils.getCurrentTime());
        mHomeworkInfo.class_id = classId;
        mHomeworkInfo.class_name = className;
//        Intent intent = new Intent();
//        intent.putExtra("HomeworkInfo", mHomeworkInfo);
//        setResult(300, intent);
//        finish();
        presenter.addOrUpdateHomeWorkInfo(isAdd);
    }

    @Override
    public void modifySuccess() {
        this.setResult(300);
        this.finish();
    }

    @Override
    public HomeworkInfo getHomeWorkInfo() {
        return this.mHomeworkInfo;
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
}
