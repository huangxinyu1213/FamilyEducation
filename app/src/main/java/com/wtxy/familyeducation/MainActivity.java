package com.wtxy.familyeducation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wtxy.familyeducation.constant.Tutor;
import com.wtxy.familyeducation.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClick(View view){
        int loginType = Tutor.TYPE_TEACHER;
        switch (view.getId()){
            case R.id.btn_teacher:
                loginType = Tutor.TYPE_TEACHER;
                break;
            case R.id.btn_student:
                loginType = Tutor.TYPE_STUDENT;
                break;
            case R.id.btn_parent:
                loginType = Tutor.TYPE_PARENT;
                break;
            case R.id.btn_manager:
                loginType = Tutor.TYPE_MANAGER;
                break;
            default:
                break;
        }
        gotoLoginActivity(loginType);
    }

    /**
     *  跳转登录
     * @param loginType 登录人员类别
     */
    private void gotoLoginActivity(int loginType) {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("loginType",loginType);
        startActivity(intent);
        finish();
    }
}
