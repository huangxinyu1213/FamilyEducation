package com.wtxy.familyeducation.biz;

import com.wtxy.familyeducation.httpresult.LoadNewsHttpResult;
import com.zhy.http.okhttp.requestBase.TaskListener;

/**
 * @Author: maxiaohu
 * @Date: 2020/2/26
 * @Describe:
 */
public interface IMessageManageBiz {
    void loadNews(TaskListener<LoadNewsHttpResult> taskListener);
}
