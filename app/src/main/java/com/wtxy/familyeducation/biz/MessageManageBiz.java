package com.wtxy.familyeducation.biz;

import com.wtxy.familyeducation.httpresult.LoadNewsHttpResult;
import com.wtxy.familyeducation.iview.IMessageManageView;
import com.wtxy.familyeducation.task.LoadNewsTask;
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
}
