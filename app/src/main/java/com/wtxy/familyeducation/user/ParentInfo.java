package com.wtxy.familyeducation.user;

import com.wtxy.familyeducation.bean.BaseItemBean;

/**
 * @Author: yiwenhui
 * @Date: 2020/4/11
 * @Describe:
 */
public class ParentInfo extends BaseItemBean {
    public int parent_id;
    public String  parent_name;
    public String parent_gender;
    public String parent_phone;
    public String student_id;
    public String relationship;
    public String parent_account;

    @Override
    public String toString() {
        return "ParentInfo{" +
                "parent_id=" + parent_id +
                ", parent_name='" + parent_name + '\'' +
                ", parent_gender='" + parent_gender + '\'' +
                ", parent_phone='" + parent_phone + '\'' +
                ", student_id='" + student_id + '\'' +
                ", relationship='" + relationship + '\'' +
                ", parent_account='" + parent_account + '\'' +
                '}';
    }

    @Override
    public String getShowTitle() {
        return parent_name;
    }
}
