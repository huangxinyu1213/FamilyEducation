package com.wtxy.familyeducation.biz;

import com.wtxy.familyeducation.httpresult.LoginHttpResult;
import com.wtxy.familyeducation.ibiz.ILoginBiz;
import com.wtxy.familyeducation.task.LoginTask;
import com.zhy.http.okhttp.requestBase.TaskListener;

/**
 * @Author: maxiaohu
 * @Date: 2020/2/23
 * @Describe:
 */
public class LoginBiz implements ILoginBiz {

    @Override
    public void login(int loginType, String loginName, String pwd, TaskListener<LoginHttpResult> taskListener) {
        LoginTask loginTask = new LoginTask(taskListener,LoginHttpResult.class);
        loginTask.setParam(loginName,pwd,loginType);
        loginTask.execute();
    }

    @Override
    public void regist(int registType, String loginName, String pwd,TaskListener<LoginHttpResult> taskListener) {

    }
}
