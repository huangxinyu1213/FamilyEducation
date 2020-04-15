package com.wtxy.familyeducation.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.wtxy.familyeducation.BaseActivity;
import com.wtxy.familyeducation.home.HomeActivity;
import com.wtxy.familyeducation.iview.ILoginView;
import com.wtxy.familyeducation.R;
import com.wtxy.familyeducation.constant.Tutor;
import com.wtxy.familyeducation.presenter.LoginPresenter;

public class LoginActivity extends BaseActivity implements ILoginView {
    private int mLoginType;
    private EditText edtName;
    private EditText edtPwd;
    private View btnLogin;
    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);
        mLoginType = getIntent().getIntExtra("loginType", Tutor.TYPE_TEACHER);//默认跳转教师
        refreshTitleView();
        edtName = findViewById(R.id.edtName);
        edtPwd = findViewById(R.id.edtPwd);
        btnLogin = findViewById(R.id.btn_login);
        mPresenter = new LoginPresenter(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                mPresenter.login(mLoginType);
                Intent intent1 = new Intent(this, HomeActivity.class);
                startActivity(intent1);
                //todo 跳转后暂时先直接finish
                finish();
                break;
            case R.id.regist:
                Intent intent = new Intent(this, RegistActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;

        }
    }

    /**
     * 根据登录类别显示标题
     */
    private void refreshTitleView() {
        switch (mLoginType) {
            case Tutor.TYPE_TEACHER:
                showTitle("教师登录");
                break;
            case Tutor.TYPE_STUDENT:
                showTitle("学生登录");
                break;
            case Tutor.TYPE_PARENT:
                showTitle("家长登录");
                break;
            case Tutor.TYPE_MANAGER:
                showTitle("管理员登录");
                break;
            default:
                break;
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public String getCount() {
        return edtName.getText().toString();
    }

    @Override
    public int getLoginType() {
        return mLoginType;
    }

    @Override
    public String getPwd() {
        return edtPwd.getText().toString();
    }

    @Override
    public void gotoHomeActivity() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void clearCount() {
        edtName.setText("");
    }

    @Override
    public void clearPwd() {
        edtPwd.setText("");
    }

    @Override
    public void showToast(String msg) {

    }
}
