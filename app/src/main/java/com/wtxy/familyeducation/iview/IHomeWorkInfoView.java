package com.wtxy.familyeducation.iview;

import com.wtxy.familyeducation.user.HomeworkInfo;

public interface IHomeWorkInfoView extends IView {
    public void modifySuccess();
    public HomeworkInfo getHomeWorkInfo();
}
