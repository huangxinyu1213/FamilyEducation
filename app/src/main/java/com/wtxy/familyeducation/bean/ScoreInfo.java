package com.wtxy.familyeducation.bean;

public class ScoreInfo extends BaseItemBean {
    public String student_name;
    public int student_id;
    public int score_id;
    public float score_num;
    public int exam_id;
    public String subject_name;
    public String exam_name;

    @Override
    public String getShowTitle() {
        return exam_name;
    }
}
