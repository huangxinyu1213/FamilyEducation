package com.wtxy.familyeducation.web;

import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.wtxy.familyeducation.R;
import com.wtxy.familyeducation.util.LogUtils;


public class WebActivity extends BaseWebActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentLayoutId() {
        return  R.layout.activity_web;
    }

    @Override
    protected String getTitleStr() {
        String title = getIntent().getStringExtra(BaseWebActivity.TITLE);
        return title;
    }

    @Override
    protected boolean shouldSetCookie() {
        return true;
    }

    @Override
    protected void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        LogUtils.info("errorcode",errorCode+"  "+description+"");

    }

    @Override
    protected void setWebViewAttri() {
        mWebView.addJavascriptInterface(this,"EbusClient");
        super.setWebViewAttri();
    }

    @JavascriptInterface
    public void dealPermissionDenied(){
       // CommenExceptionTools.dealException(this,"40101",null);
    }

}
