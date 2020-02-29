package com.wtxy.familyeducation.iview;

/**
 * @Author: maxiaohu
 * @Date: 2020/2/23
 * @Describe:
 */
public interface ILoginView extends IView{
    public String getCount();

    public int getLoginType();

    public String getPwd();

    public void gotoHomeActivity();

    public void clearCount();

    public void clearPwd();
}
