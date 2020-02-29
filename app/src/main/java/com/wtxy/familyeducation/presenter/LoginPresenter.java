package com.wtxy.familyeducation.presenter;

import com.wtxy.familyeducation.constant.Const;
import com.wtxy.familyeducation.constant.LoginStateUtil;
import com.wtxy.familyeducation.httpresult.LoginHttpResult;
import com.wtxy.familyeducation.util.SPUtils;
import com.wtxy.familyeducation.iview.ILoginView;
import com.wtxy.familyeducation.biz.ILoginBiz;
import com.wtxy.familyeducation.biz.LoginBiz;
import com.wtxy.familyeducation.iview.IView;
import com.zhy.http.okhttp.requestBase.TaskListener;

/**
 * @Author: maxiaohu
 * @Date: 2020/2/23
 * @Describe:
 */
public class LoginPresenter {
    private ILoginBiz loginBiz;
    private IView view;
    public LoginPresenter(IView view){
       this.loginBiz = new LoginBiz();
       this.view = view;
    }

    public void login() {
        if (view instanceof ILoginView){
           ILoginView loginView = (ILoginView) view;
            loginBiz.login(loginView.getLoginType(),loginView.getCount(),loginView.getPwd(),taskListener);
        }
    }

    private TaskListener<LoginHttpResult> taskListener = new TaskListener<LoginHttpResult>() {
        @Override
        public void onTaskStart(TaskListener<LoginHttpResult> listener) {
            view.showLoading();
        }

        @Override
        public void onTaskComplete(TaskListener<LoginHttpResult> listener, LoginHttpResult result, Exception e) {
            view.hideLoading();
            if (result != null && result.isSuccess()){
                //登录成功
                SPUtils.put(view.getContext(),Const.KEY_LOGIN_ID,result.getResult().account_id);
                SPUtils.put(view.getContext(),Const.KEY_LOGIN_STATE, LoginStateUtil.LOGIN_SUCCESS);

            }else {
                //登录失败
                SPUtils.put(view.getContext(),Const.KEY_LOGIN_STATE, LoginStateUtil.LOGIN_FAILD);
            }
        }
    };
}
