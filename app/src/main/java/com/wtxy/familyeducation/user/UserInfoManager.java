package com.wtxy.familyeducation.user;

import android.content.Context;
import android.text.TextUtils;

import com.wtxy.familyeducation.constant.Const;
import com.wtxy.familyeducation.constant.LoginStateUtil;
import com.wtxy.familyeducation.constant.Tutor;
import com.wtxy.familyeducation.util.SPUtils;

/**
 * @Author: maxiaohu
 * @Date: 2020/4/11
 * @Describe:
 */
public class UserInfoManager {
    private static UserInfo userInfo;
    private static UserInfoManager mInstance;
    private UserInfoManager(){
        if (userInfo == null) {
            userInfo = new UserInfo();
        }
    }
    public static  UserInfoManager getInstance(){
        if (mInstance == null){
            mInstance = new UserInfoManager();
        }
        return mInstance;
    }

    public UserInfo getCurrentUserInfo(){
        return userInfo;
    }

    /**
     *  自动登录
     */
    public void autoLogin(Context context){
        if (LoginStateUtil.isLoginSuccess(context)){
            String loginType = (String) SPUtils.get(context, Const.KEY_LOGIN_TYPE,"");
            String loginName = (String) SPUtils.get(context,Const.KEY_LOGIN_NAME,"");
            if (TextUtils.isEmpty(loginType) || TextUtils.isEmpty(loginName)){
                loginOut(context);
            }else {
                saveUserInfo(Integer.parseInt((loginType)), loginName);
            }
        }
    }

    public void loginOut(Context context){
        //登录成功
        SPUtils.put(context, Const.KEY_LOGIN_ID, "");
        SPUtils.put(context, Const.KEY_LOGIN_NAME, "");
        SPUtils.put(context, Const.KEY_LOGIN_TYPE, "");
        SPUtils.put(context, Const.KEY_LOGIN_STATE, LoginStateUtil.LOGIN_OFFLINE);
    }


    public static void saveUserInfo(int loginType,String name){
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
