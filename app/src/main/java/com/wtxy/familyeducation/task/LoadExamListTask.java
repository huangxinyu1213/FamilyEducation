package com.wtxy.familyeducation.task;

import com.wtxy.familyeducation.httpresult.LoadExamListResult;
import com.wtxy.familyeducation.net.FamilyEduHttpRequest;
import com.zhy.http.okhttp.requestBase.TaskListener;

import java.util.HashMap;

public class LoadExamListTask extends FamilyEduHttpRequest<LoadExamListResult> {
    public LoadExamListTask(TaskListener<LoadExamListResult> taskListener, Class<LoadExamListResult> mResultClassType) {
        super(taskListener, mResultClassType);
    }

    @Override
    protected String getPath() {
        return "/examList";
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
