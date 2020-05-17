package com.wtxy.familyeducation.task;

import com.wtxy.familyeducation.net.FamilyEduHttpRequest;
import com.wtxy.familyeducation.user.ExamInfo;
import com.zhy.http.okhttp.requestBase.HttpResult;
import com.zhy.http.okhttp.requestBase.TaskListener;

import java.util.HashMap;

public class AddExamTask extends FamilyEduHttpRequest<HttpResult> {
    private ExamInfo mExamInfo;

    public AddExamTask(TaskListener<HttpResult> taskListener, Class<HttpResult> mResultClassType) {
        super(taskListener, mResultClassType);
    }

    @Override
    protected String getPath() {
        return "/add/exam";
    }

    public void setParam(ExamInfo examInfo) {
        this.mExamInfo = examInfo;
    }

    @Override
    protected void addParam(HashMap<String, String> params) {
        if (mExamInfo != null) {
            params.put("exam_name", mExamInfo.exam_name);
            params.put("subject_id", mExamInfo.subject_id + "");
            params.put("subject_name", mExamInfo.subject_name);
            params.put("class_id", mExamInfo.class_id + "");
            params.put("class_name", mExamInfo.class_name);
        }
    }

    @Override
    protected void setHeader(HashMap<String, String> headers) {

    }

    @Override
    protected String getMethod() {
        return "POST";
    }
}
