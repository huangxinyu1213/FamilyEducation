package com.wtxy.familyeducation.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wtxy.familyeducation.BaseActivity;
import com.wtxy.familyeducation.R;
import com.wtxy.familyeducation.adapter.StudentGradeListAdapter;
import com.wtxy.familyeducation.bean.BaseItemBean;
import com.wtxy.familyeducation.bean.EducationManageInfo;
import com.wtxy.familyeducation.constant.Const;
import com.wtxy.familyeducation.iview.IStudentListView;
import com.wtxy.familyeducation.presenter.StudentListPresenter;
import com.wtxy.familyeducation.user.GradeInfo;
import com.wtxy.familyeducation.user.HomeworkInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生教务管理
 * 学生成绩列表
 */
public class StudentScoreListActivity extends BaseActivity implements IStudentListView {

    private ListView listView;
    private StudentGradeListAdapter mAdapter;
    private List<BaseItemBean> mData = new ArrayList<>();
    private StudentListPresenter studentListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_student_manage);
        super.onCreate(savedInstanceState);
        listView = findViewById(R.id.manage_content);
        mAdapter = new StudentGradeListAdapter<>(this, mData, R.layout.student_grade_manage_item);
        listView.setAdapter(mAdapter);
        EducationManageInfo educationManageInfo = (EducationManageInfo) getIntent().getSerializableExtra(Const.KEY_MANAGE_INFO);
        studentListPresenter = new StudentListPresenter(this);
        if (educationManageInfo != null) {
            studentListPresenter.loadStudentGradeData();
            showTitle(educationManageInfo.getTitle());
        }
    }

    @Override
    public void refreshGrandList(List<GradeInfo> gradeInfos) {
        mData.clear();
        mData.addAll(gradeInfos);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void refreshHomeworkList(List<HomeworkInfo> homeworkInfos) {
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
