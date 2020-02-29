package com.wtxy.familyeducation.httpresult;

import com.wtxy.familyeducation.bean.News;
import com.zhy.http.okhttp.requestBase.HttpResult;

import java.util.List;

/**
 * @Author: maxiaohu
 * @Date: 2020/2/26
 * @Describe:
 */
public class LoadNewsHttpResult extends HttpResult {
    private List<News>  result;

    public List<News> getResult() {
        return result;
    }

    public void setResult(List<News> result) {
        this.result = result;
    }
}
