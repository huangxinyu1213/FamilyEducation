package com.wtxy.familyeducation.task;

import com.wtxy.familyeducation.net.FamilyEduHttpRequest;
import com.wtxy.familyeducation.user.StudentInfo;
import com.zhy.http.okhttp.requestBase.HttpResult;
import com.zhy.http.okhttp.requestBase.TaskListener;

import java.util.HashMap;

/**
 * @Author: maxiaohu
 * @Date: 2020/5/9
 * @Describe:
 */
public class ModifyStudentInfoTask extends FamilyEduHttpRequest<HttpResult> {
    private StudentInfo studentInfo;
    public ModifyStudentInfoTask(TaskListener<HttpResult> taskListener, Class<HttpResult> mResultClassType) {
        super(taskListener, mResultClassType);
    }

    public  void setStudentInfo(StudentInfo studentInfo){
        this.studentInfo = studentInfo;
    }

    @Override
    protected String getPath() {
        return "/add/student";
    }

    @Override
    protected void addParam(HashMap<String, String> params) {
       params.put("student_name",studentInfo.student_name);
       params.put("student_gender",studentInfo.student_gender);
       params.put("class_id",studentInfo.class_id);
       params.put("student_account",studentInfo.student_account);
       params.put("parent_name",studentInfo.parent_name);
       params.put("parent_account",studentInfo.parent_account);
       params.put("parent_relation",studentInfo.parent_relation);
    }

    @Override
    protected void setHeader(HashMap<String, String> headers) {

    }

    @Override
    protected String getMethod() {
        return "POST";
    }
}
