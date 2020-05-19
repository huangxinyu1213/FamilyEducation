package com.wtxy.familyeducation.iview;

import android.content.Context;

/**
 * @Author: yiwenhui
 * @Date: 2020/2/23
 * @Describe:
 */
public interface IView {
    public Context getContext();
    public void showLoading();
    public void hideLoading();
    public void showToast(String msg);
}
