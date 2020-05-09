package com.wtxy.familyeducation.presenter;

import android.app.Dialog;
import android.content.Context;

import com.wtxy.familyeducation.httpresult.LoadSubjectListResult;
import com.wtxy.familyeducation.iview.ISubjectListView;
import com.wtxy.familyeducation.task.AddSubjectInfoTask;
import com.wtxy.familyeducation.task.LoadSubjectListTask;
import com.wtxy.familyeducation.util.UIUtils;
import com.zhy.http.okhttp.requestBase.HttpResult;
import com.zhy.http.okhttp.requestBase.TaskListener;

/**
 * @Author: yiwenhui
 * @Date: 2020/4/12
 * @Describe:
 */
public class SubjectPresenter {
    private ISubjectListView subjectListView;
    public SubjectPresenter(ISubjectListView subjectListView){
        this.subjectListView = subjectListView;
    }

    public void loadSubjectList(){
        LoadSubjectListTask loadSubjectListTask = new LoadSubjectListTask(new TaskListener<LoadSubjectListResult>() {
            private Dialog dialog;
            @Override
            public void onTaskStart(TaskListener<LoadSubjectListResult> listener) {
                dialog = UIUtils.showDialog((Context) subjectListView);
            }

            @Override
            public void onTaskComplete(TaskListener<LoadSubjectListResult> listener, LoadSubjectListResult result, Exception e) {
                UIUtils.dismissDialog(dialog);
                if (result != null && result.isSuccess()){
                   subjectListView.refrshSubJectList(result.getResult());
               }
            }
        },LoadSubjectListResult.class);
        loadSubjectListTask.execute();
    }

    public void addSubject(String subjectName){
        AddSubjectInfoTask addSubjectInfoTask = new AddSubjectInfoTask(new TaskListener<HttpResult>() {
            private Dialog dialog;
            @Override
            public void onTaskStart(TaskListener<HttpResult> listener) {
                dialog = UIUtils.showDialog((Context) subjectListView);
            }

            @Override
            public void onTaskComplete(TaskListener<HttpResult> listener, HttpResult result, Exception e) {
                UIUtils.dismissDialog(dialog);
                if (result != null && result.isSuccess()){
                    subjectListView.showToast("添加成功");
                    loadSubjectList();
                }else {
                    subjectListView.showToast("添加失败");
                }
            }
        },HttpResult.class);
        addSubjectInfoTask.setParam(subjectName);
        addSubjectInfoTask.execute();
    }
}
