package com.wtxy.familyeducation.task;

import com.wtxy.familyeducation.httpresult.LoginHttpResult;
import com.wtxy.familyeducation.net.FamilyEduHttpRequest;
import com.wtxy.familyeducation.util.MD5Util;
import com.zhy.http.okhttp.requestBase.TaskListener;

import java.util.HashMap;

/**
 * @Author: yiwenhui
 * @Date: 2020/2/23
 * @Describe:
 */
public class LoginTask extends FamilyEduHttpRequest<LoginHttpResult> {
    private String loginName;
    private String loginPwd;
    private int loginType;

    public LoginTask(TaskListener<LoginHttpResult> taskListener, Class<LoginHttpResult> mResultClassType) {
        super(taskListener, mResultClassType);
    }

    public void setParam(String loginName,String loginPwd,int loginType){
        this.loginName = loginName;
        this.loginPwd = loginPwd;
        this.loginType = loginType;
    }

    @Override
    protected String getPath() {
        return "/login";
    }

    @Override
    protected void addParam(HashMap<String, String> params) {
       params.put("account_number",loginName);
       params.put("account_password", MD5Util.createMD5String(loginPwd));
       params.put("account_type",String.valueOf(loginType));
    }

    @Override
    protected void setHeader(HashMap<String, String> headers) {

    }

    @Override
    protected String getMethod() {
        return "POST";
    }
}
