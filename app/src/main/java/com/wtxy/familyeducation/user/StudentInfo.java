package com.wtxy.familyeducation.user;

import com.wtxy.familyeducation.bean.BaseItemBean;

/**
 * @Author: yiwenhui
 * @Date: 2020/4/11
 * @Describe:
 */
public class StudentInfo extends BaseItemBean {
    public int student_id;
    public String  student_name;
    public String student_gender;
    public String class_id;
    public String student_account;
    public String parent_name;
    public String parent_account;
    public String parent_relation;

    @Override
    public String toString() {
        return "StudentInfo{" +
                "student_id=" + student_id +
                ", student_name='" + student_name + '\'' +
                ", student_gender='" + student_gender + '\'' +
                ", class_id='" + class_id + '\'' +
                ", student_account='" + student_account + '\'' +
                '}';
    }


    @Override
    public String getShowTitle() {
        return student_name;
    }
}
