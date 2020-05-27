package com.wtxy.familyeducation.task;

import com.wtxy.familyeducation.user.HomeworkInfo;
import com.wtxy.familyeducation.httpresult.AddOrUpdateHomeWorkInfoResult;
import com.wtxy.familyeducation.net.FamilyEduHttpRequest;
import com.zhy.http.okhttp.requestBase.TaskListener;

import java.util.HashMap;

public class AddOrUpdateHomeWorkInfoTask extends FamilyEduHttpRequest<AddOrUpdateHomeWorkInfoResult> {
    private HomeworkInfo homeWorkInfo;
    public AddOrUpdateHomeWorkInfoTask(TaskListener<AddOrUpdateHomeWorkInfoResult> taskListener, Class<AddOrUpdateHomeWorkInfoResult> mResultClassType) {
        super(taskListener, mResultClassType);
    }

    @Override
    protected String getPath() {
        return "/add/homework";
    }

    @Override
    protected void addParam(HashMap<String, String> params) {
        params.put("hw_id",this.homeWorkInfo.hw_id+"");
        params.put("hw_title",this.homeWorkInfo.hw_title+"");
        params.put("hw_detail",this.homeWorkInfo.hw_detail+"");
        params.put("hw_date",this.homeWorkInfo.hw_time);
        params.put("class_id",this.homeWorkInfo.class_id+"");
        params.put("class_name",this.homeWorkInfo.class_name);
        params.put("subject_id",this.homeWorkInfo.subject_id+"");
    }

    @Override
    protected void setHeader(HashMap<String, String> headers) {

    }

    @Override
    protected String getMethod() {
        return "POST";
    }

    public HomeworkInfo getHomeWorkInfo() {
        return homeWorkInfo;
    }

    public void setHomeWorkInfo(HomeworkInfo homeWorkInfo) {
        this.homeWorkInfo = homeWorkInfo;
    }
}
