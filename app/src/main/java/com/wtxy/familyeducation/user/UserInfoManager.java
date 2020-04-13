package com.wtxy.familyeducation.user;

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
}
