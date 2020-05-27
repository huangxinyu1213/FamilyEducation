package com.wtxy.familyeducation.httpresult;

import com.wtxy.familyeducation.user.HomeworkInfo;
import com.zhy.http.okhttp.requestBase.HttpResult;

import java.util.List;

public class LoadHomeWorkListResult extends HttpResult {
    private List<HomeworkInfo> result;

    public List<HomeworkInfo> getResult() {
        return result;
    }

    public void setResult(List<HomeworkInfo> result) {
        this.result = result;
    }
}
