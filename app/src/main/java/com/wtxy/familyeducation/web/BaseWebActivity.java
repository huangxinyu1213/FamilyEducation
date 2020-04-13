package com.wtxy.familyeducation.web;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
import android.webkit.GeolocationPermissions;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wtxy.familyeducation.BaseActivity;
import com.wtxy.familyeducation.R;
import com.wtxy.familyeducation.util.ToastUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * web页面基类
 */
public abstract class BaseWebActivity extends BaseActivity {
    public static final String H5_REABCK_APP_URI = "aibang://reback_app";

    public static final String TAG = "AbActiveWebActivity";
    public static final String TITLE = "title";
    public static final String WEB_URL = "web_url";
    /**
     * 错误页面
     */
    protected View mErrorViewContainer;

    /**
     * 正常页面
     */
    protected View mWebViewContainer;
    protected WebView mWebView;

    /**
     * 进度
     */
    protected View mProgressView;


    protected String mUrl;
    protected String title;
    private boolean mOnError = false;  //加载本次链接的是时候是否发生错误
    protected Map<String, String> header = new HashMap<>();
    private boolean isFirst = true;

    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(getContentLayoutId());
        super.onCreate(savedInstanceState);
        findViews();
        showTitle(getTitleStr());
        initData();
        setWebViewAttri();
    }

    private void findViews() {
        mWebView = (WebView) findViewById(R.id.web_view);
        mErrorViewContainer = findViewById(R.id.load_active_error);
        findViewById(R.id.error_net_request).setVisibility(View.VISIBLE);
        mWebViewContainer = findViewById(R.id.load_active_panel);
        mProgressView = findViewById(R.id.progress_bar);
    }

    private void initData() {
        mUrl = getIntent().getStringExtra(WEB_URL);
    }

    protected abstract int getContentLayoutId();

    protected abstract String getTitleStr();

    protected abstract boolean shouldSetCookie();

    /**
     * 这里负责拦截服务器的url
     */
    protected boolean shouldOverrideUrlLoading1(WebView view, String url) {
        return false;
    }


    /**
     * 处理View的显示工作 如进度条
     *
     * @param view
     * @param url
     * @param favicon
     */
    protected void onPageStarted(WebView view, String url, Bitmap favicon) {
    }

    protected void onPageFinished(WebView view, String url) {
    }

    protected void onReceivedError(WebView view, int errorCode,
                                   String description, String failingUrl) {

    }

    protected void onProgressChanged(int progress) {
    }

    protected void setWebViewAttri() {
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setBlockNetworkImage(false);
        webSettings.setBlockNetworkLoads(false);
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setGeolocationDatabasePath(getFilesDir().getPath());
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        if (shouldSetCookie()) {
            synCookies(this, mUrl);
        }
        mWebView.setWebChromeClient(new MyWebChromeClient());
        mWebView.setWebViewClient(new InterruptClient());
        mWebView.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        mWebView.loadUrl(mUrl);
    }

    private void hideBarProgress() {
        mProgressView.setVisibility(View.GONE);
    }

    /**
     * 同步一下cookie
     */
    public void synCookies(Context context, String url) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();//移除
       // cookieManager.setCookie(url, "laravel_token=" + SettingsManager.getInstance().getToken());
        CookieSyncManager.getInstance().sync();
    }


    private void showBarProgress() {
        mProgressView.setVisibility(View.VISIBLE);
    }

    public void refreshView(View v) {
        Log.d(TAG, "refreshView");
        mWebView.reload();
    }

    /**
     * 设置是显示webView 还是 ErrorView
     *
     * @param isOK true 显示webView false 显示 ErrorView
     */
    private void setWebViewAndLoadErrorPanelVisible(boolean isOK) {
        if (isOK) {
            mWebViewContainer.setVisibility(View.VISIBLE);
            mErrorViewContainer.setVisibility(View.GONE);
        } else {
            mWebViewContainer.setVisibility(View.GONE);
            mErrorViewContainer.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 自定义WebChromeClient， 用来进行自定对话框的显示
     *
     * @author htyuan
     */
    private class MyWebChromeClient extends WebChromeClient {
        public boolean onJsAlert(WebView view, String url, String message,
                                 JsResult result) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(
                    view.getContext());

            builder.setMessage(message).setPositiveButton("确定", null);

            // 不需要绑定按键事件
            // 屏蔽keycode等于84之类的按键
            builder.setOnKeyListener(new OnKeyListener() {
                public boolean onKey(DialogInterface dialog, int keyCode,
                                     KeyEvent event) {
                    Log.v("onJsAlert", "keyCode==" + keyCode + "event=" + event);
                    return true;
                }
            });
            // 禁止响应按back键的事件
            builder.setCancelable(false);
            AlertDialog dialog = builder.create();
            dialog.show();
            result.confirm();// 因为没有绑定事件，需要强行confirm,否则页面会变黑显示不了内容。
            return true;

        }

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            super.onShowCustomView(view, callback);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            BaseWebActivity.this.onProgressChanged(newProgress);
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onGeolocationPermissionsHidePrompt() {
            super.onGeolocationPermissionsHidePrompt();
            log("onGeolocationPermissionsHidePrompt");
        }

        @Override
        public void onGeolocationPermissionsShowPrompt(final String origin,
                                                       final GeolocationPermissions.Callback callback) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(BaseWebActivity.this);
//            builder.setMessage("是否允许获取当前位置信息?");
//            DialogInterface.OnClickListener dialogButtonOnClickListener = new DialogInterface.OnClickListener() {
//
//                @Override
//                public void onClick(DialogInterface dialog, int clickedButton) {
//                    if (DialogInterface.BUTTON_POSITIVE == clickedButton) {
//                        callback.invoke(origin, true, true);
//                    } else if (DialogInterface.BUTTON_NEGATIVE == clickedButton) {
//                        callback.invoke(origin, false, false);
//                    }
//                }
//            };
//            builder.setPositiveButton("允许", dialogButtonOnClickListener);
//            builder.setNegativeButton("禁止", dialogButtonOnClickListener);
//            builder.show();
            super.onGeolocationPermissionsShowPrompt(origin, callback);

            log("onGeolocationPermissionsShowPrompt");
        }
    }

    private void dial(String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        } catch (Exception e) {
            ToastUtil.showShortToast(this,"拨号出了一点小问题");
            //UIUtils.showShortToastInCenter(this, R.string.dial_question);
        }
    }

    private void log(String string) {
        Log.d("BaseWebActivity", string);
    }

    private class InterruptClient extends WebViewClient {

        public InterruptClient() {
            super();
        }


        /**
         * 在这里拦截是否跳转客户端
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (H5_REABCK_APP_URI.equals(url)) {
                finish();
                return true;
            }
            if (url.startsWith("tel:")) {
                dial(url);
                return true;
            }
            // if 子类没有消耗，交给BaseWebActivity
            if (shouldOverrideUrlLoading1(view, url)) {
                return true;
            }

            return false;
        }


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            mOnError = false;
            showBarProgress();
            Log.d(TAG, "onPageStarted");
            if (!mOnError) {
                BaseWebActivity.this.onPageStarted(view, url, favicon);
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            hideBarProgress();
            if (!mOnError) {
                setWebViewAndLoadErrorPanelVisible(true);
                BaseWebActivity.this.onPageFinished(view, url);
            }
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            mOnError = true;
            setWebViewAndLoadErrorPanelVisible(false);
            BaseWebActivity.this.onReceivedError(view, errorCode, description,
                    failingUrl);
        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
