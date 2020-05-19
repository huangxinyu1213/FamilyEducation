package com.wtxy.familyeducation.httpresult;

import com.wtxy.familyeducation.user.ExamInfo;
import com.zhy.http.okhttp.requestBase.HttpResult;

import java.util.List;

public class LoadExamListResult extends HttpResult {

    private List<ExamInfo> result;

    public List<ExamInfo> getResult() {
        return result;
    }

    public void setResult(List<ExamInfo> result) {
        this.result = result;
    }
}
