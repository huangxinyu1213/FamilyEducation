package com.wtxy.familyeducation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wtxy.familyeducation.BaseActivity;
import com.wtxy.familyeducation.R;
import com.wtxy.familyeducation.adapter.GradeListAdapter;
import com.wtxy.familyeducation.bean.BaseItemBean;
import com.wtxy.familyeducation.bean.EducationManageInfo;
import com.wtxy.familyeducation.bean.HomeWorkInfo;
import com.wtxy.familyeducation.bean.SubjectInfo;
import com.wtxy.familyeducation.constant.Const;
import com.wtxy.familyeducation.iview.ITeacherListView;
import com.wtxy.familyeducation.presenter.TeacherListPresenter;
import com.wtxy.familyeducation.user.ExamInfo;
import com.wtxy.familyeducation.user.HomeworkInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 教师教务管理列表
 * 成绩管理列表/作业管理列表
 */
public class TeacherManageListActivity extends BaseActivity implements ITeacherListView {

    private ListView listView;
    private GradeListAdapter mAdapter;
    private List<BaseItemBean> mData = new ArrayList<>();
    private TeacherListPresenter teacherListPresenter;
    private int manageType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_teacher_manage);
        super.onCreate(savedInstanceState);
        listView = findViewById(R.id.manage_content);
        mAdapter = new GradeListAdapter(this, mData, R.layout.grade_manage_item);
        listView.setAdapter(mAdapter);
        EducationManageInfo educationManageInfo = (EducationManageInfo) getIntent().getSerializableExtra(Const.KEY_MANAGE_INFO);
        teacherListPresenter = new TeacherListPresenter(this);
        if (educationManageInfo != null) {
            manageType = educationManageInfo.getManageType();
            teacherListPresenter.loadData(educationManageInfo.getManageType());
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
        Intent intent = null;
        if (itemBean instanceof ExamInfo) {
            intent = ExamInfoActivity.newIntent(this, (ExamInfo) itemBean);
        } else if (itemBean instanceof HomeWorkInfo) {
            intent = HomeworkInfoActivity.newIntent(this, (HomeWorkInfo) itemBean);
        }
        startActivityForResult(intent, 1);
    }

    @Override
    public void refreshGrandList(List<ExamInfo> gradeInfos) {
        showRightBtn("新增");
        mData.clear();
        mData.addAll(gradeInfos);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void refreshHomeworkList(List<HomeworkInfo> homeworkInfos) {
        showRightBtn("新增");
        mData.clear();
        mData.addAll(homeworkInfos);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (manageType == EducationManageInfo.MANAGE_TYPE_TEAHCER_GRADE) {
            if (resultCode == 300) {
                ExamInfo gradeInfo = (ExamInfo) data.getSerializableExtra("GradeInfo");
                if (gradeInfo != null) {
                    if (!checkGradeInfoData(gradeInfo)) {
                        mData.add(gradeInfo);
                    }
                    mAdapter.notifyDataSetChanged();
                }
            }
        } else if (manageType == EducationManageInfo.MANAGE_TYPE_MANAGER_HOMEWORK) {
            if (resultCode == 300) {
                this.teacherListPresenter.loadData(manageType);
//                HomeWorkInfo homeworkInfo = (HomeWorkInfo) data.getSerializableExtra("HomeworkInfo");
//                if (homeworkInfo != null) {
//                    if (!checkHomeworkInfoData(homeworkInfo)) {
//                        mData.add(homeworkInfo);
//                    }
//                    mAdapter.notifyDataSetChanged();
//                }
            }
        }
    }

    private boolean checkGradeInfoData(ExamInfo gradeInfo) {
        for (BaseItemBean bean : mData) {
            if (((ExamInfo) bean).exam_id == gradeInfo.exam_id) {
                ((ExamInfo) bean).exam_name = gradeInfo.exam_name;
                ((ExamInfo) bean).subject_name = gradeInfo.subject_name;
                ((ExamInfo) bean).class_name = gradeInfo.class_name;
                return true;
            }
        }
        return false;
    }

    private boolean checkHomeworkInfoData(HomeWorkInfo homeworkInfo) {
        for (BaseItemBean bean : mData) {
            if (((HomeWorkInfo) bean).getHw_id() == homeworkInfo.getHw_id()) {
                ((HomeWorkInfo) bean).setHw_title(homeworkInfo.getHw_title());
                ((HomeWorkInfo) bean).setHw_detail(homeworkInfo.getHw_detail());
                return true;
            }
        }
        return false;
    }


    @Override
    public void onRightBtnClick() {
        super.onRightBtnClick();
        if (manageType == EducationManageInfo.MANAGE_TYPE_TEAHCER_GRADE) {
            Intent intent = ExamInfoActivity.newIntent(this, null);
            startActivityForResult(intent, 1);
        } else if (manageType == EducationManageInfo.MANAGE_TYPE_MANAGER_HOMEWORK) {
            Intent intent = HomeworkInfoActivity.newIntent(this, null);
            startActivityForResult(intent, 1);
        }
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
