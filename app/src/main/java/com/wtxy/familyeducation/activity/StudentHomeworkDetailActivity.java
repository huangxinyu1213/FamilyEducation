package com.wtxy.familyeducation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.wtxy.familyeducation.BaseActivity;
import com.wtxy.familyeducation.R;
import com.wtxy.familyeducation.user.HomeworkInfo;

public class StudentHomeworkDetailActivity extends BaseActivity {
    private TextView tv_name, tv_course, tv_desc;

    private HomeworkInfo mHomeworkInfo;

    public static Intent newIntent(Context context, HomeworkInfo homeworkInfo) {
        Intent intent = new Intent(context, StudentHomeworkDetailActivity.class);
        intent.putExtra("HomeworkInfo", homeworkInfo);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_student_homework_detail);
        super.onCreate(savedInstanceState);
        mHomeworkInfo = (HomeworkInfo) getIntent().getSerializableExtra("HomeworkInfo");
        showTitle("作业详情");
        tv_name = findViewById(R.id.tv_name);
        tv_course = findViewById(R.id.tv_course);
        tv_desc = findViewById(R.id.tv_desc);
        if (mHomeworkInfo != null) {
            tv_name.setText(mHomeworkInfo.homeword_name);
            tv_course.setText(mHomeworkInfo.homeword_course);
            tv_desc.setText(mHomeworkInfo.homeword_desc);
        }
    }


}
