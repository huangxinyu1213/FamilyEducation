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
import com.wtxy.familyeducation.user.HomeworkInfo;

public class HomeworkInfoActivity extends BaseActivity {
    private EditText edtTitle;
    private EditText edtSubTitle;
    private TextView tvClass;
    private RelativeLayout rl_check_class;

    private HomeworkInfo mHomeworkInfo;

    public static Intent newIntent(Context context, HomeworkInfo homeworkInfo) {
        Intent intent = new Intent(context, HomeworkInfoActivity.class);
        intent.putExtra("HomeworkInfo", homeworkInfo);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_homework_info);
        super.onCreate(savedInstanceState);
        mHomeworkInfo = (HomeworkInfo) getIntent().getSerializableExtra("HomeworkInfo");
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
            edtTitle.setText(mHomeworkInfo.homeword_name);
            edtSubTitle.setText(mHomeworkInfo.homeword_desc);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == 1000) {
            String className = data.getStringExtra("class_name");
            tvClass.setText(className);
        }
    }

    @Override
    public void onRightBtnClick() {
        super.onRightBtnClick();
        if (mHomeworkInfo == null) {
            mHomeworkInfo = new HomeworkInfo();
        }
        mHomeworkInfo.homeword_name = edtTitle.getText().toString().trim();
        mHomeworkInfo.homeword_desc = edtSubTitle.getText().toString().trim();
        Intent intent = new Intent();
        intent.putExtra("HomeworkInfo", mHomeworkInfo);
        setResult(300, intent);
        finish();
    }

}
