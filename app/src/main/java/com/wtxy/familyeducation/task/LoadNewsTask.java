package com.wtxy.familyeducation.task;

import com.wtxy.familyeducation.httpresult.LoadNewsHttpResult;
import com.wtxy.familyeducation.net.FamilyEduHttpRequest;
import com.zhy.http.okhttp.requestBase.TaskListener;

import java.util.HashMap;

/**
 * @Author: maxiaohu
 * @Date: 2020/2/26
 * @Describe:
 */
public class LoadNewsTask extends FamilyEduHttpRequest<LoadNewsHttpResult> {
    public LoadNewsTask(TaskListener<LoadNewsHttpResult> taskListener, Class<LoadNewsHttpResult> mResultClassType) {
        super(taskListener, mResultClassType);
    }

    @Override
    protected String getPath() {
        return "/newslist";
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
