package com.wtxy.familyeducation.task;

import com.wtxy.familyeducation.bean.ScoreInfo;
import com.wtxy.familyeducation.net.FamilyEduHttpRequest;
import com.wtxy.familyeducation.user.ExamInfo;
import com.zhy.http.okhttp.requestBase.HttpResult;
import com.zhy.http.okhttp.requestBase.TaskListener;

import java.util.HashMap;

public class AddScoreTask extends FamilyEduHttpRequest<HttpResult> {
    private ScoreInfo mScoreInfo;

    public AddScoreTask(TaskListener<HttpResult> taskListener, Class<HttpResult> mResultClassType) {
        super(taskListener, mResultClassType);
    }

    @Override
    protected String getPath() {
        return "/add/score";
    }

    public void setParam(ScoreInfo scoreInfo) {
        this.mScoreInfo = scoreInfo;
    }

    @Override
    protected void addParam(HashMap<String, String> params) {
        if (mScoreInfo != null) {
            params.put("exam_id", mScoreInfo.exam_id + "");
            params.put("student_id", mScoreInfo.student_id + "");
            params.put("student_name", mScoreInfo.student_name);
            if (mScoreInfo.score_id != 0) {
                params.put("score_id", mScoreInfo.score_id + "");
            }
            params.put("score_num", mScoreInfo.score_num + "");
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
