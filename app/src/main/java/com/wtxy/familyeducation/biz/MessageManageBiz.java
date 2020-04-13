package com.wtxy.familyeducation.biz;

import com.wtxy.familyeducation.httpresult.LoadNewsHttpResult;
import com.wtxy.familyeducation.httpresult.LoadNoticeHttpResult;
import com.wtxy.familyeducation.ibiz.IMessageManageBiz;
import com.wtxy.familyeducation.task.LoadNewsTask;
import com.wtxy.familyeducation.task.LoadNoticesTask;
import com.zhy.http.okhttp.requestBase.TaskListener;

/**
 * @Author: maxiaohu
 * @Date: 2020/2/26
 * @Describe:
 */
public class MessageManageBiz implements IMessageManageBiz {

    @Override
    public void loadNews(TaskListener<LoadNewsHttpResult> taskListener) {
        LoadNewsTask loadNewsTask = new LoadNewsTask(taskListener,LoadNewsHttpResult.class);
        loadNewsTask.execute();
    }

    @Override
    public void loadNotices(TaskListener<LoadNoticeHttpResult> taskListener) {
        LoadNoticesTask loadNoticesTask = new LoadNoticesTask(taskListener, LoadNoticeHttpResult.class);
        loadNoticesTask.execute();
    }
}
