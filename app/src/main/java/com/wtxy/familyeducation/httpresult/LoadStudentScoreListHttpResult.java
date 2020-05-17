package com.wtxy.familyeducation.httpresult;

import com.wtxy.familyeducation.bean.ScoreInfo;
import com.wtxy.familyeducation.user.StudentInfo;
import com.zhy.http.okhttp.requestBase.HttpResult;

import java.util.List;

/**
 * @Author: yiwenhui
 * @Date: 2020/4/16
 * @Describe:
 */
public class LoadStudentScoreListHttpResult extends HttpResult {
   private  List<ScoreInfo> result;

    public List<ScoreInfo> getResult() {
        return result;
    }

    public void setResult(List<ScoreInfo> result) {
        this.result = result;
    }
}
