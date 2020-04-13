package com.wtxy.familyeducation.presenter;

import com.wtxy.familyeducation.iview.ITeacherInfoView;
import com.wtxy.familyeducation.task.ModifyTeacherInfoTask;
import com.wtxy.familyeducation.user.TeachInfo;
import com.zhy.http.okhttp.requestBase.HttpResult;
import com.zhy.http.okhttp.requestBase.TaskListener;

/**
 * @Author: maxiaohu
 * @Date: 2020/4/12
 * @Describe:
 */
public class TeacherInfoPresenter {
    private ITeacherInfoView teacherInfoView;
    public TeacherInfoPresenter(ITeacherInfoView teacherInfoView){
        this.teacherInfoView = teacherInfoView;
    }

    public void modifyTeacherInfo(final boolean isAdd){
        final TeachInfo teachInfo = teacherInfoView.getTeacherInfo();
        ModifyTeacherInfoTask modifyTeacherInfoTask = new ModifyTeacherInfoTask(new TaskListener<HttpResult>() {
            @Override
            public void onTaskStart(TaskListener<HttpResult> listener) {

            }

            @Override
            public void onTaskComplete(TaskListener<HttpResult> listener, HttpResult result, Exception e) {
                if (result.isSuccess()){
                    teacherInfoView.showToast(isAdd ? "新增教师信息成功" : "教师信息修改成功");
                    teacherInfoView.modifySuccess();
                }else {
                    teacherInfoView.showToast(isAdd ? "新增教师信息失败" : "教师信息修改失败");
                }
            }
        },HttpResult.class);
        modifyTeacherInfoTask.setTeachInfo(teachInfo);
        modifyTeacherInfoTask.execute();
    }
}
