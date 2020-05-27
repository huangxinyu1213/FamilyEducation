package com.wtxy.familyeducation.home;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wtxy.familyeducation.MainActivity;
import com.wtxy.familyeducation.R;
import com.wtxy.familyeducation.constant.LoginStateUtil;
import com.wtxy.familyeducation.user.UserInfoManager;

public class SplashActivity extends AppCompatActivity {
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        UserInfoManager.getInstance().autoLogin(this);

        if (LoginStateUtil.isLoginSuccess(this)){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            },3000);
        }else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            },3000);
        }

    }
}
