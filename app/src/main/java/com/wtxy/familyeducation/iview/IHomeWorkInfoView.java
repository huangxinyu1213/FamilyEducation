package com.wtxy.familyeducation.iview;

import com.wtxy.familyeducation.bean.HomeWorkInfo;

public interface IHomeWorkInfoView extends IView {
    public void modifySuccess();
    public HomeWorkInfo getHomeWorkInfo();
}
