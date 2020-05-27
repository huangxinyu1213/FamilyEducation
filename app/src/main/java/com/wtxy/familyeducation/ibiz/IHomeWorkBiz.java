package com.wtxy.familyeducation.ibiz;

import com.wtxy.familyeducation.user.HomeworkInfo;
import com.wtxy.familyeducation.httpresult.AddOrUpdateHomeWorkInfoResult;
import com.wtxy.familyeducation.httpresult.LoadHomeWorkListResult;
import com.zhy.http.okhttp.requestBase.TaskListener;

public interface IHomeWorkBiz {
    public void loadHomeWorkList(TaskListener<LoadHomeWorkListResult> taskListener,int classId);

    public void addOrUpdateHomeWork(TaskListener<AddOrUpdateHomeWorkInfoResult> taskListener, HomeworkInfo homeWorkInfo);
}
