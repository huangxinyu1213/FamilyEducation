package com.wtxy.familyeducation.httpresult;

import com.wtxy.familyeducation.bean.ClassInfo;
import com.wtxy.familyeducation.user.TeachInfo;
import com.zhy.http.okhttp.requestBase.HttpResult;

import java.util.List;

/**
 * @Author: yiwenhui
 * @Date: 2020/4/12
 * @Describe:
 */
public class LoadClassListResult extends HttpResult {
    private List<ClassInfo> result;

    public List<ClassInfo> getResult() {
        return result;
    }

    public void setResult(List<ClassInfo> result) {
        this.result = result;
    }
}
