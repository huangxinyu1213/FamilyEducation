package com.wtxy.familyeducation.presenter;

import com.wtxy.familyeducation.httpresult.LoadSubjectListResult;
import com.wtxy.familyeducation.iview.ISubjectListView;
import com.wtxy.familyeducation.task.LoadSubjectListTask;
import com.zhy.http.okhttp.requestBase.TaskListener;

/**
 * @Author: maxiaohu
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
            @Override
            public void onTaskStart(TaskListener<LoadSubjectListResult> listener) {

            }

            @Override
            public void onTaskComplete(TaskListener<LoadSubjectListResult> listener, LoadSubjectListResult result, Exception e) {
               if (result != null && result.isSuccess()){
                   subjectListView.refrshSubJectList(result.getResult());
               }
            }
        },LoadSubjectListResult.class);
        loadSubjectListTask.execute();
    }

    public void addSubject(String subjectName){

    }
}
