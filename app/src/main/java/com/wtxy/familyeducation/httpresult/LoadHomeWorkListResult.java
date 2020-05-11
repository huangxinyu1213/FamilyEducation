package com.wtxy.familyeducation.httpresult;

import com.wtxy.familyeducation.bean.HomeWorkInfo;
import com.zhy.http.okhttp.requestBase.HttpResult;

import java.util.List;

public class LoadHomeWorkListResult extends HttpResult {
    private List<HomeWorkInfo> result;

    public List<HomeWorkInfo> getResult() {
        return result;
    }

    public void setResult(List<HomeWorkInfo> result) {
        this.result = result;
    }
}
