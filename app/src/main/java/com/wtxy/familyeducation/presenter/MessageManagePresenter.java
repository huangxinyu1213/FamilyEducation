package com.wtxy.familyeducation.presenter;

import com.wtxy.familyeducation.adapter.MessageManagePageAdapter;
import com.wtxy.familyeducation.biz.IMessageManageBiz;
import com.wtxy.familyeducation.biz.MessageManageBiz;
import com.wtxy.familyeducation.httpresult.LoadNewsHttpResult;
import com.wtxy.familyeducation.iview.IMessageManageView;
import com.wtxy.familyeducation.iview.IView;
import com.zhy.http.okhttp.requestBase.TaskListener;

/**
 * @Author: maxiaohu
 * @Date: 2020/2/26
 * @Describe:
 */
public class MessageManagePresenter {
    private IView messageManageView;
    private IMessageManageBiz messageManageBiz;
    public MessageManagePresenter(IView view){
        this.messageManageView = view;
        messageManageBiz = new MessageManageBiz();
    }

    public void loadNews(){

    }

    private TaskListener<LoadNewsHttpResult> loadNewsHttpResultTaskListener = new TaskListener<LoadNewsHttpResult>() {
        @Override
        public void onTaskStart(TaskListener<LoadNewsHttpResult> listener) {
            messageManageView.showLoading();
        }

        @Override
        public void onTaskComplete(TaskListener<LoadNewsHttpResult> listener, LoadNewsHttpResult result, Exception e) {
           messageManageView.hideLoading();
           if (result != null && result.isSuccess()){
               ((IMessageManageView)messageManageView).refreshNewsView(result.getResult());
           }
        }
    };

}
