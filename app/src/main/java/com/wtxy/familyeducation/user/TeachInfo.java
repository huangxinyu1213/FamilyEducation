package com.wtxy.familyeducation.user;

import android.text.TextUtils;

import com.wtxy.familyeducation.bean.BaseItemBean;

/**
 * @Author: yiwenhui
 * @Date: 2020/4/11
 * @Describe:
 */
public class TeachInfo extends BaseItemBean {
    public int teacher_id;
    public String  teacher_name;
    public String teacher_gender;
    public String teacher_phone;
    public String subject_id;
    public String subject_name;
    public String teacher_account;

    public boolean isWoMen(){
        return !TextUtils.isEmpty(teacher_gender) && teacher_gender.trim().equals("女");
    }

    @Override
    public String toString() {
        return "TeachInfo{" +
                "teacher_id=" + teacher_id +
                ", teacher_name='" + teacher_name + '\'' +
                ", teacher_gender='" + teacher_gender + '\'' +
                ", teacher_phone='" + teacher_phone + '\'' +
                ", subject_id='" + subject_id + '\'' +
                ", subject_name='" + subject_name + '\'' +
                ", teacher_account='" + teacher_account + '\'' +
                '}';
    }

    @Override
    public String getShowTitle() {
        return teacher_name;
    }
}
