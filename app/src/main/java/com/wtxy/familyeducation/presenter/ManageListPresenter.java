package com.wtxy.familyeducation.presenter;

import com.wtxy.familyeducation.bean.EducationManageInfo;
import com.wtxy.familyeducation.biz.ManageListBiz;
import com.wtxy.familyeducation.httpresult.LoadClassListResult;
import com.wtxy.familyeducation.httpresult.LoadSubjectListResult;
import com.wtxy.familyeducation.httpresult.LoadTeacherListResult;
import com.wtxy.familyeducation.ibiz.IManageListBiz;
import com.wtxy.familyeducation.iview.IManagerListView;
import com.zhy.http.okhttp.requestBase.TaskListener;

/**
 * @Author: yiwenhui
 * @Date: 2020/4/11
 * @Describe:
 */
public class ManageListPresenter {

    private IManagerListView managerListView;
    private IManageListBiz manageListBiz;
    public ManageListPresenter(IManagerListView managerListView){
        this.manageListBiz = new ManageListBiz();
        this.managerListView = managerListView;
    }

    public void loadData(int manageType){
       switch (manageType){
           case EducationManageInfo.MANAGE_TYPE_MANAGER_TEAHCER:
               manageListBiz.loadTeacherList(loadTeacherListResultTaskListener);
               break;
           case EducationManageInfo.MANAGE_TYPE_MANAGER_CLASS:
               manageListBiz.loadClassList(loadClassListResultTaskListener);
               break;
           case EducationManageInfo.MANAGE_TYPE_MANAGER_SUBJECT:
               manageListBiz.loadSubjectList(loadSubjectListResultTaskListener);
               break;
       }
    }

    private TaskListener<LoadTeacherListResult> loadTeacherListResultTaskListener = new TaskListener<LoadTeacherListResult>() {
        @Override
        public void onTaskStart(TaskListener<LoadTeacherListResult> listener) {

        }

        @Override
        public void onTaskComplete(TaskListener<LoadTeacherListResult> listener, LoadTeacherListResult result, Exception e) {
            if (result != null && result.isSuccess()) {
                managerListView.refreshTeacherList(result.getResult());
            }
        }
    };

    private TaskListener<LoadClassListResult> loadClassListResultTaskListener = new TaskListener<LoadClassListResult>() {

        @Override
        public void onTaskStart(TaskListener<LoadClassListResult> listener) {

        }

        @Override
        public void onTaskComplete(TaskListener<LoadClassListResult> listener, LoadClassListResult result, Exception e) {
            if (result.isSuccess() && result != null){
                managerListView.refreshClassList(result.getResult());
            }
        }
    };

    private TaskListener<LoadSubjectListResult> loadSubjectListResultTaskListener = new TaskListener<LoadSubjectListResult>() {

        @Override
        public void onTaskStart(TaskListener<LoadSubjectListResult> listener) {

        }

        @Override
        public void onTaskComplete(TaskListener<LoadSubjectListResult> listener, LoadSubjectListResult result, Exception e) {
            if (result.isSuccess() && result != null){
                managerListView.refreshSubjectList(result.getResult());
            }
        }
    };


}
