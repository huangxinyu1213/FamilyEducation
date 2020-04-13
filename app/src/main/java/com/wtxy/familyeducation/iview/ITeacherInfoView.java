package com.wtxy.familyeducation.iview;

import com.wtxy.familyeducation.user.TeachInfo;

/**
 * @Author: maxiaohu
 * @Date: 2020/4/12
 * @Describe:
 */
public interface ITeacherInfoView extends IView{
    public void modifySuccess();
    public TeachInfo getTeacherInfo();
}
