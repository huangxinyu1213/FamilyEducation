package com.wtxy.familyeducation.httpresult;

import com.wtxy.familyeducation.bean.HomeWorkInfo;
import com.zhy.http.okhttp.requestBase.HttpResult;

public class AddOrUpdateHomeWorkInfoResult extends HttpResult {
    private HomeWorkInfo homeWorkInfo;

    public HomeWorkInfo getHomeWorkInfo() {
        return homeWorkInfo;
    }

    public void setHomeWorkInfo(HomeWorkInfo homeWorkInfo) {
        this.homeWorkInfo = homeWorkInfo;
    }
}
