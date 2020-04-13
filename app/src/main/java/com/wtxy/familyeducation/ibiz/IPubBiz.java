package com.wtxy.familyeducation.ibiz;

import com.zhy.http.okhttp.requestBase.HttpResult;
import com.zhy.http.okhttp.requestBase.TaskListener;

/**
 * @Author: maxiaohu
 * @Date: 2020/2/29
 * @Describe:
 */
public interface IPubBiz {
    void pushLishNews(String title, String otherTitle, String link, TaskListener<HttpResult> taskListener);
    void pushLishNotices(String title, String otherTitle, String link, TaskListener<HttpResult> taskListener);
}
