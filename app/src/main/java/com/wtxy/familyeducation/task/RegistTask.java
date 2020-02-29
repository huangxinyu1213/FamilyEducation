package com.wtxy.familyeducation.task;

import com.wtxy.familyeducation.httpresult.LoginHttpResult;
import com.wtxy.familyeducation.net.FamilyEduHttpRequest;
import com.zhy.http.okhttp.requestBase.TaskListener;

import java.util.HashMap;

/**
 * @Author: maxiaohu
 * @Date: 2020/2/23
 * @Describe:
 */
public class RegistTask extends FamilyEduHttpRequest<LoginHttpResult> {
    private String loginName;
    private String loginPwd;
    private int loginType;

    public RegistTask(TaskListener<LoginHttpResult> taskListener, Class<LoginHttpResult> mResultClassType) {
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
       params.put("account_password",loginPwd);
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
