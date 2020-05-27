package com.wtxy.familyeducation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wtxy.familyeducation.BaseActivity;
import com.wtxy.familyeducation.R;
import com.wtxy.familyeducation.adapter.CommonListAdapter;
import com.wtxy.familyeducation.bean.ClassInfo;
import com.wtxy.familyeducation.bean.EducationManageInfo;
import com.wtxy.familyeducation.bean.SubjectInfo;
import com.wtxy.familyeducation.httpresult.LoadClassListResult;
import com.wtxy.familyeducation.iview.IManagerListView;
import com.wtxy.familyeducation.presenter.ManageListPresenter;
import com.wtxy.familyeducation.user.TeachInfo;
import com.zhy.http.okhttp.requestBase.TaskListener;
import com.wtxy.familyeducation.biz.ManageListBiz;

import java.util.ArrayList;
import java.util.List;

public class ChoiceClassActivity extends BaseActivity implements IManagerListView {

    private List<ClassInfo> classList;
    private CommonListAdapter mAdapter;//listView的监听适配器
    private ListView listView;
    private ManageListPresenter manageListPresenter;
    private ManageListBiz manageListBiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_choice_class);
        super.onCreate(savedInstanceState);

        manageListBiz = new ManageListBiz();
        listView = findViewById(R.id.manage_content);
        classList = new ArrayList<>();
        manageListPresenter = new ManageListPresenter(this);
        mAdapter = new CommonListAdapter(this, classList, R.layout.education_manage_item);
        listView.setAdapter(mAdapter);//
        getTestClass();
        showTitle("班级选择");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ClassInfo classInfo = classList.get(position);
                Intent intent = new Intent();
                intent.putExtra("class_name", classInfo.getClass_name());
                intent.putExtra("class_id",classInfo.getClass_id());
                setResult(1000, intent);
                finish();
            }
        });
    }


    private void getTestClass() {
        this.manageListPresenter.loadData(EducationManageInfo.MANAGE_TYPE_MANAGER_CLASS);
//        ClassInfo classInfo1 = new ClassInfo();
//        classInfo1.setClass_id(111);
//        classInfo1.setClass_name("计算机1班");
//        classList.add(classInfo1);
//
//        ClassInfo classInfo2 = new ClassInfo();
//        classInfo2.setClass_id(222);
//        classInfo2.setClass_name("计算机2班");
//        classList.add(classInfo2);
//
//        ClassInfo classInfo3 = new ClassInfo();
//        classInfo3.setClass_id(333);
//        classInfo3.setClass_name("经管1班");
//        classList.add(classInfo3);
//        ClassInfo classInfo4 = new ClassInfo();
//        classInfo4.setClass_id(444);
//        classInfo4.setClass_name("经管2班");
//        classList.add(classInfo4);
    }

    @Override
    public void refreshTeacherList(List<TeachInfo> teachInfos) {

    }

    @Override
    public void refreshClassList(List<ClassInfo> classInfos) {
        classList.clear();
        classList.addAll(classInfos);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void refreshSubjectList(List<SubjectInfo> subjectInfos) {

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
