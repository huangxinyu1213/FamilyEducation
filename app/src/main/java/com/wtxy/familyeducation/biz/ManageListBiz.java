package com.wtxy.familyeducation.biz;

import com.wtxy.familyeducation.httpresult.LoadClassListResult;
import com.wtxy.familyeducation.httpresult.LoadNoticeHttpResult;
import com.wtxy.familyeducation.httpresult.LoadSubjectListResult;
import com.wtxy.familyeducation.httpresult.LoadTeacherListResult;
import com.wtxy.familyeducation.ibiz.IManageListBiz;
import com.wtxy.familyeducation.task.LoadClassListTask;
import com.wtxy.familyeducation.task.LoadSubjectListTask;
import com.wtxy.familyeducation.task.LoadTeacherListTask;
import com.zhy.http.okhttp.requestBase.TaskListener;

/**
 * @Author: maxiaohu
 * @Date: 2020/4/12
 * @Describe:
 */
public class ManageListBiz implements IManageListBiz {
    @Override
    public void loadTeacherList(TaskListener<LoadTeacherListResult> taskListener) {
        LoadTeacherListTask loadTeacherListTask = new LoadTeacherListTask(taskListener, LoadTeacherListResult.class);
        loadTeacherListTask.execute();
    }

    @Override
    public void loadClassList(TaskListener<LoadClassListResult> taskListener) {
        LoadClassListTask loadClassListTask = new LoadClassListTask(taskListener, LoadClassListResult.class);
        loadClassListTask.execute();
    }

    @Override
    public void loadSubjectList(TaskListener<LoadSubjectListResult> taskListener) {
        LoadSubjectListTask loadSubjectListTask = new LoadSubjectListTask(taskListener, LoadSubjectListResult.class);
        loadSubjectListTask.execute();
    }
}
