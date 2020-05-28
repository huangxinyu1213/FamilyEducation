package com.wtxy.familyeducation.task;

import com.wtxy.familyeducation.net.FamilyEduHttpRequest;
import com.zhy.http.okhttp.requestBase.HttpResult;
import com.zhy.http.okhttp.requestBase.TaskListener;

import java.util.HashMap;

/**
 * @Author: yiwenhui
 * @Date: 2020/4/12
 * @Describe:
 */
public class AddClassTask extends FamilyEduHttpRequest<HttpResult> {
    private String className;
    public AddClassTask(TaskListener<HttpResult> taskListener, Class<HttpResult> mResultClassType) {
        super(taskListener, mResultClassType);
    }

    public void setParam(String className){
        this.className = className;
    }


    @Override
    protected String getPath() {
        return "/add/class";
    }

    @Override
    protected void addParam(HashMap<String, String> params) {
        params.put("class_name",className);
    }

    @Override
    protected void setHeader(HashMap<String, String> headers) {

    }

    @Override
    protected String getMethod() {
        return "POST";
    }
}
