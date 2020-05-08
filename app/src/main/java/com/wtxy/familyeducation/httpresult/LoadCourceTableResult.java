package com.wtxy.familyeducation.httpresult;

import com.wtxy.familyeducation.bean.CourseInfo;
import com.zhy.http.okhttp.requestBase.HttpResult;

import java.util.List;

/**
 * @Author: maxiaohu
 * @Date: 2020/5/8
 * @Describe:
 */
public class LoadCourceTableResult extends HttpResult {
    public List<List<CourseInfo>> result;
}
