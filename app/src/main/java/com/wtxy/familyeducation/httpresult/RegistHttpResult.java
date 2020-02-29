package com.wtxy.familyeducation.httpresult;

import com.wtxy.familyeducation.bean.LoginResultInfo;
import com.zhy.http.okhttp.requestBase.HttpResult;

/**
 * @Author: maxiaohu
 * @Date: 2020/2/23
 * @Describe:
 */
public class RegistHttpResult extends HttpResult {

    public LoginResultInfo getResult() {
        return result;
    }

    public void setResult(LoginResultInfo result) {
        this.result = result;
    }

    private LoginResultInfo result;

}
