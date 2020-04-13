package com.wtxy.familyeducation.task;

import com.wtxy.familyeducation.httpresult.LoadSubjectListResult;
import com.wtxy.familyeducation.net.FamilyEduHttpRequest;
import com.zhy.http.okhttp.requestBase.TaskListener;

import java.util.HashMap;

/**
 * @Author: maxiaohu
 * @Date: 2020/4/12
 * @Describe:
 */
public class LoadSubjectListTask extends FamilyEduHttpRequest<LoadSubjectListResult> {


    public LoadSubjectListTask(TaskListener<LoadSubjectListResult> taskListener, Class<LoadSubjectListResult> mResultClassType) {
        super(taskListener, mResultClassType);
    }

    @Override
    protected String getPath() {
        return "/subjectList";
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
