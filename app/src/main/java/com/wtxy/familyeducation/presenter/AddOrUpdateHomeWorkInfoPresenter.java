package com.wtxy.familyeducation.presenter;

import com.wtxy.familyeducation.biz.HomeWorkBiz;
import com.wtxy.familyeducation.httpresult.AddOrUpdateHomeWorkInfoResult;
import com.wtxy.familyeducation.ibiz.IHomeWorkBiz;
import com.wtxy.familyeducation.iview.IHomeWorkInfoView;
import com.zhy.http.okhttp.requestBase.TaskListener;

public class AddOrUpdateHomeWorkInfoPresenter {
    private IHomeWorkBiz homeWorkBiz;
    private IHomeWorkInfoView homeWorkInfoView;

    public AddOrUpdateHomeWorkInfoPresenter(IHomeWorkInfoView homeWorkInfoView) {
        this.homeWorkInfoView = homeWorkInfoView;
        this.homeWorkBiz = new HomeWorkBiz();
    }

    public void addOrUpdateHomeWorkInfo(final boolean isAdd){
        TaskListener<AddOrUpdateHomeWorkInfoResult> taskListener = new TaskListener<AddOrUpdateHomeWorkInfoResult>() {
            @Override
            public void onTaskStart(TaskListener<AddOrUpdateHomeWorkInfoResult> listener) {
                homeWorkInfoView.showLoading();
            }

            @Override
            public void onTaskComplete(TaskListener<AddOrUpdateHomeWorkInfoResult> listener, AddOrUpdateHomeWorkInfoResult result, Exception e) {
                homeWorkInfoView.hideLoading();
                if (result.isSuccess()){
                    homeWorkInfoView.showToast(isAdd? "作业发布成功" : "作业修改成功");
                    homeWorkInfoView.modifySuccess();
                }else {
                    homeWorkInfoView.showToast(isAdd ? "作业发布失败" : "作业修改失败");
                }
            }
        };
        this.homeWorkBiz.addOrUpdateHomeWork(taskListener,homeWorkInfoView.getHomeWorkInfo());
    }

}
