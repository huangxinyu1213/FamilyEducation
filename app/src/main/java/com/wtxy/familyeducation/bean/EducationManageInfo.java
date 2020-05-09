package com.wtxy.familyeducation.bean;

/**
 * @Author: maxiaohu
 * @Date: 2020/4/11
 * @Describe:教务管理信息
 */
public class EducationManageInfo extends BaseItemBean{
    //管理员教务管理类型
    public static final int MANAGE_TYPE_MANAGER_TEAHCER = 10;//教师管理
    public static final int MANAGE_TYPE_MANAGER_CLASS = 11;//班级管理
    public static final int MANAGE_TYPE_MANAGER_SUBJECT= 12;//科目管理
    //教师教务管理类型
    public static final int MANAGE_TYPE_TEAHCER_GRADE = 20;//成绩管理
    public static final int MANAGE_TYPE_MANAGER_HOMEWORK = 21;//作业管理
    //学生/家长教务管理类型
    public static final int MANAGE_TYPE_STUDENT_QUERY_GRADE= 30;//查询成绩
    public static final int MANAGE_TYPE_STUDENT_QUERY_WORK = 31;//查询作业
    public static final int MANAGE_TYPE_STUDENT_QUERY_COURSE = 32;//查询课表

    public EducationManageInfo(int userType,int manageType,String title){
        this.userType = userType;
        this.manageType = manageType;
        this.title = title;
    }

    private String title;
    private int userType;
    private int manageType;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getManageType() {
        return manageType;
    }

    public void setManageType(int manageType) {
        this.manageType = manageType;
    }

    @Override
    public String getShowTitle() {
        return title;
    }
}
