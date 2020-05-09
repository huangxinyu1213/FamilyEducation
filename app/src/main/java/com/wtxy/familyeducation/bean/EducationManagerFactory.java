package com.wtxy.familyeducation.bean;
import com.wtxy.familyeducation.user.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yiwenhui
 * @Date: 2020/4/11
 * @Describe: 教务管理类
 */
public class EducationManagerFactory {

    public static List<EducationManageInfo> generateEducationInfo(int userType){
        switch (userType){
            case UserInfo.ACCOUNT_TYPE_MANAGER:
               return createManagerManageInfo(userType);
            case UserInfo.ACCOUNT_TYPE_TEACHER:
                return createTeacherManageInfo(userType);
            case UserInfo.ACCOUNT_TYPE_STUDENT:
            case UserInfo.ACCOUNT_TYPE_PARENT:
                return createStudentManageInfo(userType);
            default:
                return createManagerManageInfo(userType);
        }
    }

    /**
     *  创建管理员教务管理信息
     */
    private static List<EducationManageInfo> createManagerManageInfo(int userType) {
        List<EducationManageInfo> list = new ArrayList<>();
        list.add(new EducationManageInfo(userType,EducationManageInfo.MANAGE_TYPE_MANAGER_TEAHCER,"教师管理"));
        list.add(new EducationManageInfo(userType,EducationManageInfo.MANAGE_TYPE_MANAGER_CLASS,"班级管理"));
        list.add(new EducationManageInfo(userType,EducationManageInfo.MANAGE_TYPE_MANAGER_SUBJECT,"科目管理"));
        return list;
    }

    /**
     *  创建教师教务管理信息
     */
    private static List<EducationManageInfo> createTeacherManageInfo(int userType) {
        List<EducationManageInfo> list = new ArrayList<>();
        list.add(new EducationManageInfo(userType,EducationManageInfo.MANAGE_TYPE_TEAHCER_GRADE,"成绩管理"));
        list.add(new EducationManageInfo(userType,EducationManageInfo.MANAGE_TYPE_MANAGER_HOMEWORK,"作业管理"));
        return list;
    }

    /**
     *  常见学生教务管理信息
     */
    private static List<EducationManageInfo> createStudentManageInfo(int userType) {
        List<EducationManageInfo> list = new ArrayList<>();
        list.add(new EducationManageInfo(userType,EducationManageInfo.MANAGE_TYPE_STUDENT_QUERY_GRADE,"查看成绩"));
        list.add(new EducationManageInfo(userType,EducationManageInfo.MANAGE_TYPE_STUDENT_QUERY_WORK,"查看作业"));
        list.add(new EducationManageInfo(userType,EducationManageInfo.MANAGE_TYPE_STUDENT_QUERY_COURSE,"查询课表"));
        return list;
    }
}
