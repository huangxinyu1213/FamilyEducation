package com.wtxy.familyeducation.task;

import com.wtxy.familyeducation.httpresult.LoadScoreListHttpResult;
import com.wtxy.familyeducation.net.FamilyEduHttpRequest;
import com.zhy.http.okhttp.requestBase.TaskListener;

import java.util.HashMap;

public class LoadScoreListTask extends FamilyEduHttpRequest<LoadScoreListHttpResult> {
    private int examId;

    public LoadScoreListTask(TaskListener<LoadScoreListHttpResult> taskListener, Class<LoadScoreListHttpResult> mResultClassType) {
        super(taskListener, mResultClassType);
    }

    @Override
    protected String getPath() {
        return "/scoreList";
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    @Override
    protected void addParam(HashMap<String, String> params) {
        params.put("exam_id", examId + "");
    }

    @Override
    protected void setHeader(HashMap<String, String> headers) {

    }

    @Override
    protected String getMethod() {
        return "POST";
    }
}
