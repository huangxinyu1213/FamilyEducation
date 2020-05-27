package com.wtxy.familyeducation.bean;

public class HomeWorkInfo extends BaseItemBean {
    private int hw_id;
    private String hw_title;
    private String hw_detail;
    private String hw_date;
    private int class_id;
    private int subject_id;
    private String class_name;

    public int getHw_id() {
        return hw_id;
    }

    public void setHw_id(int hw_id) {
        this.hw_id = hw_id;
    }

    public String getHw_title() {
        return hw_title;
    }

    public void setHw_title(String hw_title) {
        this.hw_title = hw_title;
    }

    public String getHw_detail() {
        return hw_detail;
    }

    public void setHw_detail(String hw_detail) {
        this.hw_detail = hw_detail;
    }

    public String getHw_date() {
        return hw_date;
    }

    public void setHw_date(String hw_date) {
        this.hw_date = hw_date;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    @Override
    public String getShowTitle() {
        return this.getHw_title();
    }
}
