package com.wtxy.familyeducation.ibiz;

import com.wtxy.familyeducation.httpresult.LoadClassListResult;
import com.wtxy.familyeducation.httpresult.LoadSubjectListResult;
import com.wtxy.familyeducation.httpresult.LoadTeacherListResult;
import com.zhy.http.okhttp.requestBase.TaskListener;

/**
 * @Author: yiwenhui
 * @Date: 2020/4/11
 * @Describe:
 */
public interface IManageListBiz {
    public void loadTeacherList(TaskListener<LoadTeacherListResult> taskListener);
    public void loadClassList(TaskListener<LoadClassListResult> taskListener);
    public void loadSubjectList(TaskListener<LoadSubjectListResult> taskListener);
}
