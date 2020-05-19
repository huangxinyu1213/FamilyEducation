package com.wtxy.familyeducation.net;

import com.wtxy.familyeducation.constant.HostUtil;
import com.zhy.http.okhttp.requestBase.HttpRequesterBase;
import com.zhy.http.okhttp.requestBase.HttpResult;
import com.zhy.http.okhttp.requestBase.TaskListener;

/**
 * @Author: yiwenhui
 * @Date: 2020/2/23
 * @Describe:
 */
public abstract class FamilyEduHttpRequest <T extends HttpResult> extends HttpRequesterBase<T> {

    public FamilyEduHttpRequest(TaskListener<T> taskListener, Class<T> mResultClassType) {
        super(taskListener, mResultClassType);
    }

    @Override
    protected String getHost() {
        return HostUtil.HOST;
    }

}
