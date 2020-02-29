package com.wtxy.familyeducation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class BaseActivity extends AppCompatActivity {
    private ImageView ivBack;
    private TextView tvTitle;
    private TextView tvRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTitleView();
    }

    private void initTitleView() {
        ivBack = findViewById(R.id.btn_back);
        tvTitle = findViewById(R.id.tv_title);
        tvRight = findViewById(R.id.btn_right);
        if (ivBack != null){
            ivBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
        if (tvRight != null){
            tvRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onRightBtnClick();
                }
            });
        }
    }

    public void showTitle(String title){
        if (tvTitle == null){
            return;
        }
       if (!TextUtils.isEmpty(title)){
           tvTitle.setText(title);
       }
    }

    public void showRightBtn(String rightTitle){
        if (tvRight != null) {
            tvRight.setText(rightTitle);
            tvRight.setVisibility(View.VISIBLE);
        }
    }

    public void hideRightBtn(){
        if (tvRight != null) {
            tvRight.setVisibility(View.GONE);
        }
    }

    public  void onRightBtnClick(){

    }
}
