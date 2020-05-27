package com.wtxy.familyeducation.task;

import com.wtxy.familyeducation.httpresult.LoadStudentScoreListHttpResult;
import com.wtxy.familyeducation.httpresult.LoadHomeWorkListResult;
import com.wtxy.familyeducation.net.FamilyEduHttpRequest;
import com.zhy.http.okhttp.requestBase.TaskListener;

import java.util.HashMap;

public class LoadStudentHomeworkListTask extends FamilyEduHttpRequest<LoadHomeWorkListResult> {
    private int mClassId;

    public LoadStudentHomeworkListTask(TaskListener<LoadHomeWorkListResult> taskListener, Class<LoadHomeWorkListResult> mResultClassType) {
        super(taskListener, mResultClassType);
    }

    @Override
    protected String getPath() {
        return "/homeworkList";
    }

    public void setmClassId(int mClassId) {
        this.mClassId = mClassId;
    }

    @Override
    protected void addParam(HashMap<String, String> params) {
        params.put("class_id", mClassId + "");
    }

    @Override
    protected void setHeader(HashMap<String, String> headers) {

    }

    @Override
    protected String getMethod() {
        return "POST";
    }
}
