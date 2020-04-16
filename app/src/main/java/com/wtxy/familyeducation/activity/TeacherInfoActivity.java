package com.wtxy.familyeducation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.wtxy.familyeducation.BaseActivity;
import com.wtxy.familyeducation.R;
import com.wtxy.familyeducation.bean.SubjectInfo;
import com.wtxy.familyeducation.constant.Const;
import com.wtxy.familyeducation.iview.ITeacherInfoView;
import com.wtxy.familyeducation.presenter.TeacherInfoPresenter;
import com.wtxy.familyeducation.user.TeachInfo;
import com.wtxy.familyeducation.util.ToastUtil;

public class TeacherInfoActivity extends BaseActivity implements ITeacherInfoView {
    private TeachInfo teachInfo;
    private EditText edtName;
    private RadioGroup rp_sex;
    private EditText edtPhone;
    private TextView tvSubject;
    private EditText edtAcount;
    private RadioButton rbMan;
    private RadioButton rbWoMen;
    private TeacherInfoPresenter teacherInfoPresenter;
    private View subjectViewl;
    private boolean isAdd;//是否是新增操作
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_teacher_info);
        super.onCreate(savedInstanceState);
        teachInfo = (TeachInfo) getIntent().getSerializableExtra(Const.KEY_TEACHER_INFO);
        isAdd = teachInfo == null;
        teacherInfoPresenter = new TeacherInfoPresenter(this);
        initView();
        showTitle("教师信息");
        showRightBtn("确认");
    }

    private void initView() {
         edtName = findViewById(R.id.edtName);
         rp_sex = findViewById(R.id.rg_sex);
         edtPhone = findViewById(R.id.edtPhone);
         tvSubject = findViewById(R.id.tv_subject);
         edtAcount = findViewById(R.id.edtAcount);
         rbMan = findViewById(R.id.rb_man);
         rbWoMen = findViewById(R.id.rb_woman);
         subjectViewl = findViewById(R.id.subect);
         subjectViewl.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(TeacherInfoActivity.this, SubjectListActivity.class);
                 intent.putExtra(Const.KEY_IS_SELECT,true);
                 startActivityForResult(intent,1);
             }
         });
         refreshTeachInfo();
    }

    private void refreshTeachInfo() {
        if (teachInfo != null){
            edtName.setText(!TextUtils.isEmpty(teachInfo.teacher_name) ? teachInfo.teacher_name : "");
            edtPhone.setText(!TextUtils.isEmpty(teachInfo.teacher_phone) ? teachInfo.teacher_phone : "");
            tvSubject.setText(!TextUtils.isEmpty(teachInfo.subject_name) ? teachInfo.subject_name : "");
            edtAcount.setText(!TextUtils.isEmpty(teachInfo.teacher_account) ? teachInfo.teacher_account : "");
            if (teachInfo.isWoMen()){
                rbWoMen.setChecked(true);
            }else {
                rbMan.setChecked(true);
            }
        }
    }

    @Override
    public void modifySuccess() {
        this.setResult(1);
       this.finish();
    }

    @Override
    public TeachInfo getTeacherInfo() {
        if (teachInfo == null){
            teachInfo = new TeachInfo();
        }
        teachInfo.teacher_name = edtName.getText().toString();
        teachInfo.teacher_gender = rp_sex.getCheckedRadioButtonId() == R.id.rb_man ? "男" : "女";
        teachInfo.teacher_phone = edtPhone.getText().toString();
        teachInfo.subject_name = tvSubject.getText().toString();
        teachInfo.teacher_account = edtAcount.getText().toString();
        return teachInfo;
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
        teacherInfoPresenter.modifyTeacherInfo(isAdd);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            SubjectInfo subjectInfo = (SubjectInfo) data.getSerializableExtra(Const.KEY_SUBJECT_INFO);
            if (subjectInfo != null) {
                if (teachInfo == null){
                    teachInfo = new TeachInfo();
                }
                teachInfo.subject_name = subjectInfo.getSubject_name();
                teachInfo.subject_id = subjectInfo.getSubject_id() + "";
            }
            refreshTeachInfo();
        }
    }
}
