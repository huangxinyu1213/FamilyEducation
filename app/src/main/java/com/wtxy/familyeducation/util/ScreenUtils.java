package com.wtxy.familyeducation.util;

import android.content.Context;

/**
 * @Author: yiwenhui
 * @Date: 2020/4/12
 * @Describe:
 */
public class ScreenUtils {
    /**
     * 获取屏幕高度(px)
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
    /**
     * 获取屏幕宽度(px)
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}
