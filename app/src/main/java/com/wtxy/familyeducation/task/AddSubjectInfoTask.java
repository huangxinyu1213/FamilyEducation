package com.wtxy.familyeducation.task;

import com.wtxy.familyeducation.net.FamilyEduHttpRequest;
import com.wtxy.familyeducation.user.TeachInfo;
import com.zhy.http.okhttp.requestBase.HttpResult;
import com.zhy.http.okhttp.requestBase.TaskListener;

import java.util.HashMap;

/**
 * @Author: maxiaohu
 * @Date: 2020/4/12
 * @Describe:
 */
public class AddSubjectInfoTask extends FamilyEduHttpRequest<HttpResult> {
    private String subjectName;
    public AddSubjectInfoTask(TaskListener<HttpResult> taskListener, Class<HttpResult> mResultClassType) {
        super(taskListener, mResultClassType);
    }

    public void setParam(String subjectName){
        this.subjectName = subjectName;
    }


    @Override
    protected String getPath() {
        return "/add/subject";
    }

    @Override
    protected void addParam(HashMap<String, String> params) {
        params.put("subject_name",subjectName);
    }

    @Override
    protected void setHeader(HashMap<String, String> headers) {

    }

    @Override
    protected String getMethod() {
        return "POST";
    }
}
