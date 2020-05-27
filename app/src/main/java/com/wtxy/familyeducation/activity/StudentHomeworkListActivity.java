package com.wtxy.familyeducation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wtxy.familyeducation.BaseActivity;
import com.wtxy.familyeducation.R;
import com.wtxy.familyeducation.adapter.GradeListAdapter;
import com.wtxy.familyeducation.adapter.StudentGradeListAdapter;
import com.wtxy.familyeducation.bean.BaseItemBean;
import com.wtxy.familyeducation.bean.EducationManageInfo;
import com.wtxy.familyeducation.bean.ScoreInfo;
import com.wtxy.familyeducation.constant.Const;
import com.wtxy.familyeducation.iview.IStudentListView;
import com.wtxy.familyeducation.presenter.StudentListPresenter;
import com.wtxy.familyeducation.user.GradeInfo;
import com.wtxy.familyeducation.user.HomeworkInfo;
import com.wtxy.familyeducation.bean.HomeWorkInfo;
import com.wtxy.familyeducation.user.UserInfoManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生教务管理
 * 学生作业列表
 */
public class StudentHomeworkListActivity extends BaseActivity implements IStudentListView {

    private ListView listView;
    private StudentGradeListAdapter mAdapter;
    private List<BaseItemBean> mData = new ArrayList<>();
    private StudentListPresenter studentListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_student_manage);
        super.onCreate(savedInstanceState);
        listView = findViewById(R.id.manage_content);
        mAdapter = new StudentGradeListAdapter(this, mData, R.layout.student_grade_manage_item);
        listView.setAdapter(mAdapter);
        EducationManageInfo educationManageInfo = (EducationManageInfo) getIntent().getSerializableExtra(Const.KEY_MANAGE_INFO);
        studentListPresenter = new StudentListPresenter(this);
        if (educationManageInfo != null) {
            Integer classId = Integer.parseInt(UserInfoManager.getInstance().getCurrentUserInfo().getStudentInfo().class_id);
            studentListPresenter.loadStudentHomewordData(classId);
            showTitle(educationManageInfo.getTitle());
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BaseItemBean itemBean = mData.get(position);
                goToItemActivity(itemBean);
            }
        });
    }

    private void goToItemActivity(BaseItemBean itemBean) {
        if (itemBean instanceof HomeworkInfo) {
            Intent intent = StudentHomeworkDetailActivity.newIntent(this, (HomeworkInfo) itemBean);
            startActivity(intent);
        }
    }

    @Override
    public void refreshGrandList(List<ScoreInfo> gradeInfos) {
    }

    @Override
    public void refreshHomeworkList(List<HomeworkInfo> homeworkInfos) {
        mData.clear();
        mData.addAll(homeworkInfos);
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
}
