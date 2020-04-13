package com.wtxy.familyeducation.task;


import com.wtxy.familyeducation.net.FamilyEduHttpRequest;
import com.wtxy.familyeducation.user.TeachInfo;
import com.wtxy.familyeducation.util.LogUtils;
import com.zhy.http.okhttp.requestBase.HttpResult;
import com.zhy.http.okhttp.requestBase.TaskListener;

import java.util.HashMap;

/**
 * @Author: maxiaohu
 * @Date: 2020/4/12
 * @Describe:
 */
public class ModifyTeacherInfoTask extends FamilyEduHttpRequest<HttpResult> {
    private TeachInfo teachInfo;
    public ModifyTeacherInfoTask(TaskListener<HttpResult> taskListener, Class<HttpResult> mResultClassType) {
        super(taskListener, mResultClassType);
    }

    public void setTeachInfo(TeachInfo teachInfo){
        this.teachInfo = teachInfo;
    }

    @Override
    protected String getPath() {
        return "/add/teacher";
    }

    @Override
    protected void addParam(HashMap<String, String> params) {
        params.put("teacher_id",teachInfo.teacher_id+"");
        params.put("teacher_name",teachInfo.teacher_name+"");
        params.put("teacher_gender",teachInfo.teacher_gender+"");
        params.put("teacher_phone",teachInfo.teacher_phone+"");
        params.put("subject_id",teachInfo.subject_id+"");
        params.put("subject_name",teachInfo.subject_name+"");
        params.put("teacher_account",teachInfo.teacher_account+"");
        LogUtils.info("param","teacherInfo:"+teachInfo);
    }

    @Override
    protected void setHeader(HashMap<String, String> headers) {

    }

    @Override
    protected String getMethod() {
        return "POST";
    }
}
