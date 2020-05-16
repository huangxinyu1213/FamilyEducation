package com.wtxy.familyeducation.httpresult;

import com.wtxy.familyeducation.bean.ScoreInfo;
import com.zhy.http.okhttp.requestBase.HttpResult;

import java.util.List;

/**
 * @Author: maxiaohu
 * @Date: 2020/4/16
 * @Describe:
 */
public class LoadScoreListHttpResult extends HttpResult {
   private  List<ScoreInfo> result;

    public List<ScoreInfo> getResult() {
        return result;
    }

    public void setResult(List<ScoreInfo> result) {
        this.result = result;
    }
}
