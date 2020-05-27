package com.wtxy.familyeducation.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.wtxy.familyeducation.BaseActivity;
import com.wtxy.familyeducation.R;

public class NoticeDetailActivity extends BaseActivity {

    private TextView titleView;
    private TextView contentView;
    private TextView authorView;
    private TextView dateView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_notice_detail);
        super.onCreate(savedInstanceState);

        showTitle("公告");

        titleView = findViewById(R.id.titleView);
        String title = getIntent().getStringExtra("title");
        titleView.setText(title);

        contentView = findViewById(R.id.contentView);
        String detail = getIntent().getStringExtra("detail");
        contentView.setText(detail);

        authorView = findViewById(R.id.authorView);
        String author = getIntent().getStringExtra("author");
        authorView.setText(author);

        dateView = findViewById(R.id.dateView);
        String date = getIntent().getStringExtra("date");
        dateView.setText(date);
    }

    @Override
    public void showTitle(String title) {
        super.showTitle(title);
    }
}
