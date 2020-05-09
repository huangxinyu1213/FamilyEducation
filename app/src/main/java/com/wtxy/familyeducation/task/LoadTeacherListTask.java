package com.wtxy.familyeducation.task;

import com.wtxy.familyeducation.httpresult.LoadTeacherListResult;
import com.wtxy.familyeducation.net.FamilyEduHttpRequest;
import com.zhy.http.okhttp.requestBase.TaskListener;

import java.util.HashMap;

/**
 * @Author: yiwenhui
 * @Date: 2020/4/12
 * @Describe:
 */
public class LoadTeacherListTask extends FamilyEduHttpRequest<LoadTeacherListResult> {
    public LoadTeacherListTask(TaskListener<LoadTeacherListResult> taskListener, Class<LoadTeacherListResult> mResultClassType) {
        super(taskListener, mResultClassType);
    }

    @Override
    protected String getPath() {
        return "/teacherList";
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
