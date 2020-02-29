package com.wtxy.familyeducation.constant;

import android.content.Context;

import com.wtxy.familyeducation.util.SPUtils;

/**
 * @Author: maxiaohu
 * @Date: 2020/2/24
 * @Describe:
 */
public class LoginStateUtil {
    /**
     *  未登录离线状态
     */
    public static final int LOGIN_OFFLINE = 0;
    /**
     *  登录成功状态
     */
    public static final int LOGIN_SUCCESS = 1;
    /**
     *  登录失败
     */
    public static final int LOGIN_FAILD = 2;

    /**
     *  是否是登录状态
     * @param context
     * @return
     */
    public static boolean isLoginSuccess(Context context){
        int loginState = (int) SPUtils.get(context,Const.KEY_LOGIN_STATE,LOGIN_OFFLINE);
        return loginState == LOGIN_SUCCESS;
    }
}
