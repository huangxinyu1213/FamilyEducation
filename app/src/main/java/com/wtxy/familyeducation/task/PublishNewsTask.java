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
public class PublishNewsTask extends FamilyEduHttpRequest<HttpResult> {
    private String title,otherTitle,link;
    public PublishNewsTask(TaskListener<HttpResult> taskListener, Class<HttpResult> mResultClassType) {
        super(taskListener, mResultClassType);
    }

    public void setParam(String title,String otherTitle,String link){
        this.title = title;
        this.otherTitle = otherTitle;
        this.link = link;
    }

    @Override
    protected String getPath() {
        return "/post/news";
    }

    @Override
    protected void addParam(HashMap<String, String> params) {
          params.put("news_title",title);
          params.put("news_subtitle",otherTitle);
          params.put("news_date", DateUtils.getCurrentDate());
          params.put("news_url",link);
    }

    @Override
    protected void setHeader(HashMap<String, String> headers) {

    }

    @Override
    protected String getMethod() {
        return "POST";
    }
}
