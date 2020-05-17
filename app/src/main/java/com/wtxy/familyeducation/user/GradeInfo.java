package com.wtxy.familyeducation.user;


import com.wtxy.familyeducation.bean.BaseItemBean;

/**
 * @Author: yiwenhui
 * @Date: 2020/4/11
 * @Describe:
 */
public class GradeInfo extends BaseItemBean {
    public int grade_id;
    public String grade_name;
    public String grade_college;
    public float grade_score;
    public String grade_time;


    @Override
    public String getShowTitle() {
        return grade_name;
    }
}
