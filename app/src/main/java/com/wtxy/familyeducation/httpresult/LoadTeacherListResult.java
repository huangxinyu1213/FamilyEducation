package com.wtxy.familyeducation.httpresult;

import com.wtxy.familyeducation.user.TeachInfo;
import com.zhy.http.okhttp.requestBase.HttpResult;

import java.util.List;

/**
 * @Author: maxiaohu
 * @Date: 2020/4/12
 * @Describe:
 */
public class LoadTeacherListResult extends HttpResult {

    private List<TeachInfo> result;

    public List<TeachInfo> getResult() {
        return result;
    }

    public void setResult(List<TeachInfo> result) {
        this.result = result;
    }
}
