package com.wtxy.familyeducation.login;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.wtxy.familyeducation.R;
import com.wtxy.familyeducation.constant.Const;
import com.wtxy.familyeducation.constant.Tutor;
import com.wtxy.familyeducation.util.ToastUtil;
import com.wtxy.familyeducation.iview.IRegistView;

public class RegistActivity extends AppCompatActivity implements IRegistView {
    private EditText edtCount;
    private EditText edtPwd;
    private EditText edtConfirmPwd;
    private int registType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        edtCount = findViewById(R.id.edtName);
        edtPwd = findViewById(R.id.edtPwd);
        edtConfirmPwd = findViewById(R.id.edtConfirmPwd);
        registType = getIntent().getIntExtra(Const.KEY_LOGIN_TYPE, Tutor.TYPE_TEACHER);
    }

    @Override
    public String getCount() {
        return edtCount.getText().toString();
    }

    @Override
    public int getLoginType() {
        return registType;
    }

    @Override
    public String getPwd() {
        return edtPwd.getText().toString();
    }

    @Override
    public String getConfimRegistPwd() {
        return edtConfirmPwd.getText().toString();
    }

    @Override
    public void gotoHomeActivity() {

    }

    @Override
    public void clearCount() {

    }

    @Override
    public void clearPwd() {

    }

    @Override
    public Context getContext() {
        return this;
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
}
