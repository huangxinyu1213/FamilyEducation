package com.wtxy.familyeducation.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * @Author: maxiaohu
 * @Date: 2020/2/23
 * @Describe:
 */
public class ToastUtil {
    /**
     *  显示toats'提示
     * @param context
     * @param content
     */
    public static void showShortToast(Context context,String content){
        Toast toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
