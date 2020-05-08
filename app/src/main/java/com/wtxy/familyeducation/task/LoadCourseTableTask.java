package com.wtxy.familyeducation.task;

import com.wtxy.familyeducation.httpresult.LoadCourceTableResult;
import com.wtxy.familyeducation.net.FamilyEduHttpRequest;
import com.zhy.http.okhttp.requestBase.TaskListener;

import java.util.HashMap;

/**
 * @Author: maxiaohu
 * @Date: 2020/5/8
 * @Describe:
 */
public class LoadCourseTableTask extends FamilyEduHttpRequest<LoadCourceTableResult> {
    private String classId;
    public LoadCourseTableTask(TaskListener<LoadCourceTableResult> taskListener, Class<LoadCourceTableResult> mResultClassType) {
        super(taskListener, mResultClassType);
    }

    @Override
    protected String getPath() {
        return "/courseList";
    }

    public void setParam(String classId){
        this.classId = classId;
    }

    @Override
    protected void addParam(HashMap<String, String> params) {
        params.put("class_id",classId);
    }

    @Override
    protected void setHeader(HashMap<String, String> headers) {

    }

    @Override
    protected String getMethod() {
        return "POST";
    }
}
