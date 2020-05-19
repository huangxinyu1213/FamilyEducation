package com.wtxy.familyeducation.task;

import com.wtxy.familyeducation.net.FamilyEduHttpRequest;
import com.wtxy.familyeducation.util.DateUtils;
import com.zhy.http.okhttp.requestBase.HttpResult;
import com.zhy.http.okhttp.requestBase.TaskListener;

import java.util.HashMap;

/**
 * @Author: yiwenhui
 * @Date: 2020/2/29
 * @Describe:
 */
public class PublishNoticeTask extends FamilyEduHttpRequest<HttpResult> {
    private String title,otherTitle;
    public PublishNoticeTask(TaskListener<HttpResult> taskListener, Class<HttpResult> mResultClassType) {
        super(taskListener, mResultClassType);
    }

    public void setParam(String title,String otherTitle){
        this.title = title;
        this.otherTitle = otherTitle;
    }

    @Override
    protected String getPath() {
        return "/post/announcements";
    }

    @Override
    protected void addParam(HashMap<String, String> params) {
          params.put("notice_title",title);
          params.put("notice_time", DateUtils.getCurrentDate());
          params.put("notice_detail",otherTitle);
          params.put("notice_author","学校");
    }

    @Override
    protected void setHeader(HashMap<String, String> headers) {

    }

    @Override
    protected String getMethod() {
        return "POST";
    }
}
