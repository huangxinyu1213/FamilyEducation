package com.wtxy.familyeducation.task;

import com.wtxy.familyeducation.httpresult.LoadHomeWorkListResult;
import com.wtxy.familyeducation.net.FamilyEduHttpRequest;
import com.zhy.http.okhttp.requestBase.TaskListener;

import java.util.HashMap;

public class LoadHomeWorkListTask extends FamilyEduHttpRequest<LoadHomeWorkListResult> {

    private int classId;

    public LoadHomeWorkListTask(TaskListener<LoadHomeWorkListResult> taskListener, Class<LoadHomeWorkListResult> mResultClassType) {
        super(taskListener, mResultClassType);
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    @Override
    protected String getPath() {
        return "/homeworkList";
    }

    @Override
    protected void addParam(HashMap<String, String> params) {
        if(classId != 0){
            params.put("class_id",classId+"");
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
