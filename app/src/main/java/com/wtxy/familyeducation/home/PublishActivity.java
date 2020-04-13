package com.wtxy.familyeducation.home;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;

import com.wtxy.familyeducation.BaseActivity;
import com.wtxy.familyeducation.R;
import com.wtxy.familyeducation.iview.IPublishView;
import com.wtxy.familyeducation.presenter.PublishPresenter;
import com.wtxy.familyeducation.util.ToastUtil;

public class PublishActivity extends BaseActivity implements IPublishView {
   private EditText edtTitle,edtSubTitle,edtLink;
   private PublishPresenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_publish);
        super.onCreate(savedInstanceState);
        mPresenter = new PublishPresenter(this);
        showTitle("发布新闻");
        showRightBtn("发布");
        initView();
    }

    private void initView() {
        edtTitle = findViewById(R.id.edt_title);
        edtSubTitle = findViewById(R.id.edt_other_title);
        edtLink = findViewById(R.id.edt_link);
    }

    @Override
    public void onRightBtnClick() {
        super.onRightBtnClick();
        mPresenter.pubNews();
    }

    @Override
    public String getPubTitle() {
        return edtTitle.getText().toString().trim();
    }

    @Override
    public String getOtherTitle() {
        return edtSubTitle.getText().toString().trim();
    }

    @Override
    public String getLink() {
        return edtLink.getText().toString().trim();
    }

    @Override
    public void onPublishSuccess() {

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
