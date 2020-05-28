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
    private ListView listView;//申明属性
    private ManageListPresenter manageListPresenter;
    private ManageListBiz manageListBiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_choice_class);//布局
        super.onCreate(savedInstanceState);//调用父类的方法，父类也要进行oncreate，也要调用这个方法

        manageListBiz = new ManageListBiz();
        listView = findViewById(R.id.manage_content);//将布局文件中的ID为manage_content的控件与当前Activity中的ListView属性绑定
        classList = new ArrayList<>();
        manageListPresenter = new ManageListPresenter(this);//负责请求（班级列表）
        mAdapter = new CommonListAdapter(this, classList, R.layout.education_manage_item);//创建Adapter的时候将数据源作为参数传递进去，用于刷新数据
        listView.setAdapter(mAdapter);//添加适配器
        getTestClass();//获取测试班级（已注释）
        showTitle("班级选择");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ClassInfo classInfo = classList.get(position);//取出点击的位置所带的对象信息
                Intent intent = new Intent();//intent负责跳转
                intent.putExtra("class_name", classInfo.getClass_name());
                intent.putExtra("class_id",classInfo.getClass_id());
                setResult(1000, intent);
                finish();//当前页面销毁，退出当前页面
            }
        });
    }


    private void getTestClass() {
        this.manageListPresenter.loadData(EducationManageInfo.MANAGE_TYPE_MANAGER_CLASS);//负责请求（班级列表）
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
        classList.clear();//清除之前刷新前的缓存
        classList.addAll(classInfos);
        mAdapter.notifyDataSetChanged();//数据源改变，让ListView去刷新
    }//请求成功后的回调（班级列表）

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
