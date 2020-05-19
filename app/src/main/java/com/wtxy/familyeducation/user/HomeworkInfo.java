package com.wtxy.familyeducation.user;


import com.wtxy.familyeducation.bean.BaseItemBean;

/**
 * @Author: yiwenhui
 * @Date: 2020/4/11
 * @Describe:
 */
public class HomeworkInfo extends BaseItemBean {
    public int homeword_id;
    public String homeword_name;
    public String homeword_desc;
    public String homeword_time;
    public String homeword_course;


    @Override
    public String getShowTitle() {
        return homeword_name;
    }
}
