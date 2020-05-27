package com.wtxy.familyeducation.activity;

import android.os.Bundle;

import com.wtxy.familyeducation.BaseActivity;
import com.wtxy.familyeducation.R;

public class AboutUsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_about_us);
        super.onCreate(savedInstanceState);
        showTitle("关于我们");
    }
}
