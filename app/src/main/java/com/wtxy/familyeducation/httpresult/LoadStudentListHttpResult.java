package com.wtxy.familyeducation.httpresult;

import com.wtxy.familyeducation.user.StudentInfo;
import com.zhy.http.okhttp.requestBase.HttpResult;

import java.util.List;

/**
 * @Author: maxiaohu
 * @Date: 2020/4/16
 * @Describe:
 */
public class LoadStudentListHttpResult extends HttpResult {
   private  List<StudentInfo> result;

    public List<StudentInfo> getResult() {
        return result;
    }

    public void setResult(List<StudentInfo> result) {
        this.result = result;
    }
}
