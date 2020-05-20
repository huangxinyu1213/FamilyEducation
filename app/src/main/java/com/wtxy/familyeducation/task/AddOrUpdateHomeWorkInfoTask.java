package com.wtxy.familyeducation.task;

import com.wtxy.familyeducation.bean.HomeWorkInfo;
import com.wtxy.familyeducation.httpresult.AddOrUpdateHomeWorkInfoResult;
import com.wtxy.familyeducation.net.FamilyEduHttpRequest;
import com.zhy.http.okhttp.requestBase.TaskListener;

import java.util.HashMap;

public class AddOrUpdateHomeWorkInfoTask extends FamilyEduHttpRequest<AddOrUpdateHomeWorkInfoResult> {
    private HomeWorkInfo homeWorkInfo;
    public AddOrUpdateHomeWorkInfoTask(TaskListener<AddOrUpdateHomeWorkInfoResult> taskListener, Class<AddOrUpdateHomeWorkInfoResult> mResultClassType) {
        super(taskListener, mResultClassType);
    }

    @Override
    protected String getPath() {
        return "/add/homework";
    }

    @Override
    protected void addParam(HashMap<String, String> params) {
        params.put("hw_id",this.homeWorkInfo.getHw_id()+"");
        params.put("hw_title",this.homeWorkInfo.getHw_title()+"");
        params.put("hw_detail",this.homeWorkInfo.getHw_detail()+"");
        params.put("hw_date",this.homeWorkInfo.getHw_date());
        params.put("class_id",this.homeWorkInfo.getClass_id()+"");
        params.put("class_name",this.homeWorkInfo.getClass_name());
        params.put("subject_id",this.homeWorkInfo.getSubject_id()+"");
    }

    @Override
    protected void setHeader(HashMap<String, String> headers) {

    }

    @Override
    protected String getMethod() {
        return "POST";
    }

    public HomeWorkInfo getHomeWorkInfo() {
        return homeWorkInfo;
    }

    public void setHomeWorkInfo(HomeWorkInfo homeWorkInfo) {
        this.homeWorkInfo = homeWorkInfo;
    }
}
