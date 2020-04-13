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
import com.wtxy.familyeducation.adapter.CommonListAdapter;
import com.wtxy.familyeducation.bean.BaseItemBean;
import com.wtxy.familyeducation.bean.ClassInfo;
import com.wtxy.familyeducation.bean.EducationManageInfo;
import com.wtxy.familyeducation.bean.SubjectInfo;
import com.wtxy.familyeducation.constant.Const;
import com.wtxy.familyeducation.iview.IManagerListView;
import com.wtxy.familyeducation.presenter.ManageListPresenter;
import com.wtxy.familyeducation.user.TeachInfo;

import java.util.ArrayList;
import java.util.List;

/**
 *  管理员教务管理列表页
 */
public class ManagerListManageActivity extends BaseActivity implements IManagerListView {
   private ListView listView;
   private CommonListAdapter mAdapter;
   private List<BaseItemBean> mData = new ArrayList<>();
   private ManageListPresenter manageListPresenter;
   private int manageType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_manager_list_manage);
        super.onCreate(savedInstanceState);
        listView = findViewById(R.id.manage_content);
        mAdapter = new CommonListAdapter(this,mData,R.layout.education_manage_item);
        listView.setAdapter(mAdapter);
        EducationManageInfo educationManageInfo = (EducationManageInfo) getIntent().getSerializableExtra(Const.KEY_MANAGE_INFO);
        manageListPresenter = new ManageListPresenter(this);
        if (educationManageInfo != null ) {
            manageType = educationManageInfo.getManageType();
            manageListPresenter.loadData(educationManageInfo.getManageType());
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
        if (itemBean instanceof TeachInfo){
            intent = new Intent(this,TeacherInfoActivity.class);
            intent.putExtra(Const.KEY_TEACHER_INFO,itemBean);
        }else if (itemBean instanceof ClassInfo){
            intent = new Intent(this,ClassInfoActivity.class);
        }else if (itemBean instanceof SubjectInfo){
            intent = new Intent(this,SubjectListActivity.class);
        }
        startActivityForResult(intent,1);
    }

    @Override
    public void refreshTeacherList(List<TeachInfo> teachInfos) {
      showRightBtn("新增");
      mData.clear();
      mData.addAll(teachInfos);
      mAdapter.notifyDataSetChanged();
    }

    @Override
    public void refreshClassList(List<ClassInfo> classInfos) {
        mData.clear();
        mData.addAll(classInfos);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void refreshSubjectList(List<SubjectInfo> subjectInfos) {
        mData.clear();
        mData.addAll(subjectInfos);
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

    @Override
    public void onRightBtnClick() {
        super.onRightBtnClick();
        if (manageType == EducationManageInfo.MANAGE_TYPE_MANAGER_TEAHCER){
           Intent intent = new Intent(this,TeacherInfoActivity.class);
           startActivityForResult(intent,1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1){
            manageListPresenter.loadData(manageType);
        }
    }
}
