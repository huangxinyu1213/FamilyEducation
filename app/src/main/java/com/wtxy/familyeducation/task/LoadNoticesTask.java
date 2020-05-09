package com.wtxy.familyeducation.task;

import com.wtxy.familyeducation.httpresult.LoadNewsHttpResult;
import com.wtxy.familyeducation.httpresult.LoadNoticeHttpResult;
import com.wtxy.familyeducation.net.FamilyEduHttpRequest;
import com.zhy.http.okhttp.requestBase.TaskListener;

import java.util.HashMap;

/**
 * @Author: yiwenhui
 * @Date: 2020/2/26
 * @Describe:
 */
public class LoadNoticesTask extends FamilyEduHttpRequest<LoadNoticeHttpResult> {
    public LoadNoticesTask(TaskListener<LoadNoticeHttpResult> taskListener, Class<LoadNoticeHttpResult> mResultClassType) {
        super(taskListener, mResultClassType);
    }

    @Override
    protected String getPath() {
        return "//announcements";
    }

    @Override
    protected void addParam(HashMap<String, String> params) {

    }

    @Override
    protected void setHeader(HashMap<String, String> headers) {

    }

    @Override
    protected String getMethod() {
        return "POST";
    }
}
