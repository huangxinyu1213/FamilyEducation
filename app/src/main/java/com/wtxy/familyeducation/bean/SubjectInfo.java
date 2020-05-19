package com.wtxy.familyeducation.bean;

/**
 * @Author: yiwenhui
 * @Date: 2020/4/12
 * @Describe:
 */
public class SubjectInfo extends BaseItemBean{
    private int subject_id;
    private String subject_name;

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    @Override
    public String getShowTitle() {
        return subject_name;
    }
}
