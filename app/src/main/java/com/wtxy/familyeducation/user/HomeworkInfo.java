package com.wtxy.familyeducation.user;


import com.wtxy.familyeducation.bean.BaseItemBean;

/**
 * @Author: yiwenhui
 * @Date: 2020/4/11
 * @Describe:
 */
public class HomeworkInfo extends BaseItemBean {
    public int hw_id;
    public String hw_title;
    public String hw_detail;
    public String hw_time;
    public String subject_name;
    public String class_name;

    @Override
    public String getShowTitle() {
        return hw_title;
    }
}
