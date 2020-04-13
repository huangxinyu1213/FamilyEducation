package com.wtxy.familyeducation.httpresult;

import com.wtxy.familyeducation.bean.News;
import com.wtxy.familyeducation.bean.Notices;
import com.zhy.http.okhttp.requestBase.HttpResult;

import java.util.List;

/**
 * @Author: maxiaohu
 * @Date: 2020/2/26
 * @Describe:
 */
public class LoadNoticeHttpResult extends HttpResult {
    private List<Notices>  result;

    public List<Notices> getResult() {
        return result;
    }

    public void setResult(List<Notices> result) {
        this.result = result;
    }
}
