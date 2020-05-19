package com.wtxy.familyeducation.task;

import com.wtxy.familyeducation.httpresult.LoadStudentListHttpResult;
import com.wtxy.familyeducation.net.FamilyEduHttpRequest;
import com.zhy.http.okhttp.requestBase.TaskListener;

import java.util.HashMap;

/**
 * @Author: yiwenhui
 * @Date: 2020/4/16
 * @Describe:
 */
public class LoadStudentListTask extends FamilyEduHttpRequest<LoadStudentListHttpResult> {
    private int classId;
    public LoadStudentListTask(TaskListener<LoadStudentListHttpResult> taskListener, Class<LoadStudentListHttpResult> mResultClassType) {
        super(taskListener, mResultClassType);
    }

    @Override
    protected String getPath() {
        return "/studentList";
    }

    public void setClassId(int classId){
        this.classId = classId;
    }

    @Override
    protected void addParam(HashMap<String, String> params) {
        params.put("class_id",classId+"");
    }

    @Override
    protected void setHeader(HashMap<String, String> headers) {

    }

    @Override
    protected String getMethod() {
        return "POST";
    }
}
