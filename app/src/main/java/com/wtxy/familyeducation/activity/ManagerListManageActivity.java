package com.wtxy.familyeducation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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
import com.wtxy.familyeducation.util.ScreenUtils;
import com.wtxy.familyeducation.util.ToastUtil;

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
            intent.putExtra(Const.KEY_CLASS_INFO,itemBean);
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
        showRightBtn("新增班级");
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
        ToastUtil.showShortToast(this,msg);
    }

    @Override
    public void onRightBtnClick() {
        super.onRightBtnClick();
        if (manageType == EducationManageInfo.MANAGE_TYPE_MANAGER_TEAHCER){
           Intent intent = new Intent(this,TeacherInfoActivity.class);
           startActivityForResult(intent,1);
           return;
        }
        if (manageType == EducationManageInfo.MANAGE_TYPE_MANAGER_CLASS){
            showAddDialog();
            return;
        }

    }

    private void showAddDialog(){
        View view = LayoutInflater.from(this).inflate(R.layout.layout_class_add,null,false);
        final AlertDialog dialog = new AlertDialog.Builder(this).setView(view).create();
        final EditText editText = view.findViewById(R.id.edt_subject);
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
                if (TextUtils.isEmpty(editText.getText())){
                    showToast("请输入班级名称");
                    return;
                }
                manageListPresenter.addClass(editText.getText().toString().trim());
                dialog.dismiss();
            }
        });

        dialog.show();
        //此处设置位置窗体大小，我这里设置为了手机屏幕宽度的3/4  注意一定要在show方法调用后再写设置窗口大小的代码，否则不起效果会
        dialog.getWindow().setLayout((ScreenUtils.getScreenWidth(this)/4*3), LinearLayout.LayoutParams.WRAP_CONTENT);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1){
            manageListPresenter.loadData(manageType);
        }
    }
}
