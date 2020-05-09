package com.wtxy.familyeducation.presenter;


import android.text.TextUtils;

import com.wtxy.familyeducation.biz.PubBiz;
import com.wtxy.familyeducation.ibiz.IPubBiz;
import com.wtxy.familyeducation.iview.IPublishView;
import com.wtxy.familyeducation.iview.IView;
import com.zhy.http.okhttp.requestBase.HttpResult;
import com.zhy.http.okhttp.requestBase.TaskListener;

/**
 * @Author: yiwenhui
 * @Date: 2020/2/29
 * @Describe:
 */
public class PublishPresenter {
    private IView view;
    private IPubBiz pubBiz;
    public PublishPresenter(IView iView){
        this.view = iView;
        pubBiz = new PubBiz();
    }

    /**
     *  发布新闻
     */
    public void pubNews(){
        IPublishView publishView = (IPublishView) view;
        if (TextUtils.isEmpty(publishView.getPubTitle())){
            publishView.showToast("请输入新闻标题");
            return;
        }
        if (TextUtils.isEmpty(publishView.getOtherTitle())){
            publishView.showToast("请输入新闻副标题");
            return;
        }
        if (TextUtils.isEmpty(publishView.getLink())){
            publishView.showToast("请输入新闻链接");
            return;
        }
        pubBiz.pushLishNews(publishView.getPubTitle(),publishView.getOtherTitle(),publishView.getLink(),taskListener);
    }

    private TaskListener<HttpResult> taskListener = new TaskListener<HttpResult>() {
        @Override
        public void onTaskStart(TaskListener<HttpResult> listener) {
            view.showLoading();
        }

        @Override
        public void onTaskComplete(TaskListener<HttpResult> listener, HttpResult result, Exception e) {
            view.hideLoading();
            if (result != null && result.isSuccess()){
                view.showToast("发布成功");
                return;
            }else {
                view.showToast("发布失败");
            }
        }
    };
}
