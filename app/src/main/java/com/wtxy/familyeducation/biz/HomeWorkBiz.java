package com.wtxy.familyeducation.biz;

import com.wtxy.familyeducation.bean.HomeWorkInfo;
import com.wtxy.familyeducation.httpresult.AddOrUpdateHomeWorkInfoResult;
import com.wtxy.familyeducation.httpresult.LoadHomeWorkListResult;
import com.wtxy.familyeducation.ibiz.IHomeWorkBiz;
import com.wtxy.familyeducation.task.AddOrUpdateHomeWorkInfoTask;
import com.wtxy.familyeducation.task.LoadHomeWorkListTask;
import com.zhy.http.okhttp.requestBase.TaskListener;

public class HomeWorkBiz implements IHomeWorkBiz {
    @Override
    public void loadHomeWorkList(TaskListener<LoadHomeWorkListResult> taskListener,int classId) {
        LoadHomeWorkListTask task = new LoadHomeWorkListTask(taskListener,LoadHomeWorkListResult.class);
        task.setClassId(classId);
        task.execute();
    }


    @Override
    public void addOrUpdateHomeWork(TaskListener<AddOrUpdateHomeWorkInfoResult> taskListener, HomeWorkInfo homeWorkInfo) {
        AddOrUpdateHomeWorkInfoTask task = new AddOrUpdateHomeWorkInfoTask(taskListener, AddOrUpdateHomeWorkInfoResult.class);
        task.setHomeWorkInfo(homeWorkInfo);
        task.execute();
    }
}
