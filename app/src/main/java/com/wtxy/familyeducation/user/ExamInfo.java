package com.wtxy.familyeducation.user;

import com.wtxy.familyeducation.bean.BaseItemBean;

public class ExamInfo extends BaseItemBean {
    public int subject_id;
    public String subject_name;
    public int class_id;
    public String class_name;
    public int exam_id;
    public String exam_name;

    @Override
    public String getShowTitle() {
        return subject_name;
    }
}
