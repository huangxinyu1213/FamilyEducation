package com.wtxy.familyeducation.task;

import com.wtxy.familyeducation.httpresult.LoadStudentScoreListHttpResult;
import com.wtxy.familyeducation.net.FamilyEduHttpRequest;
import com.zhy.http.okhttp.requestBase.TaskListener;

import java.util.HashMap;

public class LoadStudentScoreListTask extends FamilyEduHttpRequest<LoadStudentScoreListHttpResult> {
    private int mStudentId;

    public LoadStudentScoreListTask(TaskListener<LoadStudentScoreListHttpResult> taskListener, Class<LoadStudentScoreListHttpResult> mResultClassType) {
        super(taskListener, mResultClassType);
    }

    @Override
    protected String getPath() {
        return "/studentScoreList";
    }

    public void setStudentId(int studentId) {
        this.mStudentId = studentId;
    }

    @Override
    protected void addParam(HashMap<String, String> params) {
        params.put("student_id", mStudentId + "");
    }

    @Override
    protected void setHeader(HashMap<String, String> headers) {

    }

    @Override
    protected String getMethod() {
        return "POST";
    }
}
