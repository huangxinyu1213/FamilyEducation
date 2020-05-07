package com.wtxy.familyeducation.presenter;

import android.os.Parcelable;
import android.text.TextUtils;

import com.wtxy.familyeducation.constant.Const;
import com.wtxy.familyeducation.constant.LoginStateUtil;
import com.wtxy.familyeducation.constant.Tutor;
import com.wtxy.familyeducation.httpresult.LoginHttpResult;
import com.wtxy.familyeducation.user.ParentInfo;
import com.wtxy.familyeducation.user.StudentInfo;
import com.wtxy.familyeducation.user.TeachInfo;
import com.wtxy.familyeducation.user.UserInfo;
import com.wtxy.familyeducation.user.UserInfoManager;
import com.wtxy.familyeducation.util.SPUtils;
import com.wtxy.familyeducation.iview.ILoginView;
import com.wtxy.familyeducation.ibiz.ILoginBiz;
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
    private ILoginView view;

    public LoginPresenter(IView view) {
        this.loginBiz = new LoginBiz();
        this.view = (ILoginView) view;
    }

    public void login(int loginType,String name) {

        if (view instanceof ILoginView){
           ILoginView loginView = (ILoginView) view;
            if (TextUtils.isEmpty(loginView.getCount())){
                loginView.showToast("账户不能为空");
                return;
            }
            if (TextUtils.isEmpty(loginView.getPwd())){
                loginView.showToast("密码不能为空");
                return;
            }
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
            if (result != null && result.isSuccess()) {
                //登录成功
                SPUtils.put(view.getContext(), Const.KEY_LOGIN_ID, result.getResult().account_id);
                SPUtils.put(view.getContext(), Const.KEY_LOGIN_STATE, LoginStateUtil.LOGIN_SUCCESS);
                if (view != null){
                    view.gotoHomeActivity();
                    view.showToast("登录成功");
                }
                saveUserInfo(Integer.parseInt(result.getResult().account_type),result.getResult().account_number);
            } else {
                //登录失败
                if (view != null){
                    view.showToast("登录失败");
                }
                SPUtils.put(view.getContext(), Const.KEY_LOGIN_STATE, LoginStateUtil.LOGIN_FAILD);
            }
        }
    };

    private void saveUserInfo(int loginType,String name){
                switch (loginType) {
            case Tutor.TYPE_TEACHER:
                TeachInfo teachInfo = new TeachInfo();
                teachInfo.teacher_name = name;
                UserInfoManager.getInstance().getCurrentUserInfo().setTeachInfo(teachInfo);
                UserInfoManager.getInstance().getCurrentUserInfo().setCurrentUserType(UserInfo.ACCOUNT_TYPE_TEACHER);
                break;
            case Tutor.TYPE_MANAGER:
                UserInfoManager.getInstance().getCurrentUserInfo().setCurrentUserType(UserInfo.ACCOUNT_TYPE_MANAGER);
                break;
            case Tutor.TYPE_PARENT:
                ParentInfo parentInfo = new ParentInfo();
                parentInfo.parent_name = name;
                UserInfoManager.getInstance().getCurrentUserInfo().setParentInfo(parentInfo);
                UserInfoManager.getInstance().getCurrentUserInfo().setCurrentUserType(UserInfo.ACCOUNT_TYPE_PARENT);
                break;
            case Tutor.TYPE_STUDENT:
                StudentInfo studentInfo = new StudentInfo();
                studentInfo.student_name = name;
                UserInfoManager.getInstance().getCurrentUserInfo().setStudentInfo(studentInfo);
                UserInfoManager.getInstance().getCurrentUserInfo().setCurrentUserType(UserInfo.ACCOUNT_TYPE_STUDENT);
                break;
            default:
                UserInfoManager.getInstance().getCurrentUserInfo().setCurrentUserType(UserInfo.ACCOUNT_TYPE_TEACHER);
        }
    }
}
