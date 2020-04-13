package com.wtxy.familyeducation.httpresult;

import com.wtxy.familyeducation.bean.SubjectInfo;
import com.zhy.http.okhttp.requestBase.HttpResult;

import java.util.List;

/**
 * @Author: maxiaohu
 * @Date: 2020/4/12
 * @Describe:
 */
public class LoadSubjectListResult extends HttpResult {
    private List<SubjectInfo> result;

    public List<SubjectInfo> getResult() {
        return result;
    }

    public void setResult(List<SubjectInfo> result) {
        this.result = result;
    }
}
