package com.wtxy.familyeducation.iview;

/**
 * @Author: maxiaohu
 * @Date: 2020/2/25
 * @Describe:
 */
public interface IRegistView extends IView{
    public String getCount();

    public int getLoginType();

    public String getPwd();

    public String getConfimRegistPwd();

    public void gotoHomeActivity();

    public void clearCount();

    public void clearPwd();
}
