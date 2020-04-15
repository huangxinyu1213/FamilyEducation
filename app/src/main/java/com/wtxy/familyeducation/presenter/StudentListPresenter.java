package com.wtxy.familyeducation.presenter;

import com.wtxy.familyeducation.bean.EducationManageInfo;
import com.wtxy.familyeducation.iview.IStudentListView;
import com.wtxy.familyeducation.user.GradeInfo;
import com.wtxy.familyeducation.user.HomeworkInfo;

import java.util.ArrayList;
import java.util.List;

public class StudentListPresenter {

    private IStudentListView studentListView;

    public StudentListPresenter(IStudentListView studentListView) {
        this.studentListView = studentListView;
    }


    public void loadStudentGradeData() {
        getTestGradeData();
    }

    public void loadStudentHomewordData() {
        getTestHomeworkDate();
    }

    private void getTestGradeData() {
        List<GradeInfo> list = new ArrayList<>();
        GradeInfo gradeInfo1 = new GradeInfo();
        gradeInfo1.grade_id = 100001;
        gradeInfo1.grade_college = "计算机院";
        gradeInfo1.grade_name = "2019年上学期计算机考试成绩";
        gradeInfo1.grade_score = 89.5f;
        gradeInfo1.grade_time = "2019-5-10";
        list.add(gradeInfo1);
        GradeInfo gradeInfo2 = new GradeInfo();
        gradeInfo2.grade_id = 100002;
        gradeInfo2.grade_college = "计算机院";
        gradeInfo2.grade_name = "2019年上学期高等数学考试成绩";
        gradeInfo2.grade_score = 70f;
        gradeInfo2.grade_time = "2019-5-18";
        list.add(gradeInfo2);
        if (studentListView != null) {
            studentListView.refreshGrandList(list);
        }
    }

    private void getTestHomeworkDate() {
        List<HomeworkInfo> list = new ArrayList<>();
        HomeworkInfo homeworkInfo1 = new HomeworkInfo();
        homeworkInfo1.homeword_id = 1001;
        homeworkInfo1.homeword_name = "2019年第一周作业";
        homeworkInfo1.homeword_desc = "做一个小型商店管理系统，能够实现对商店货物的增删改查操作";
        homeworkInfo1.homeword_time = "2019-09-21";
        homeworkInfo1.homeword_course = "数据结构";
        list.add(homeworkInfo1);
        HomeworkInfo homeworkInfo2 = new HomeworkInfo();
        homeworkInfo2.homeword_id = 1002;
        homeworkInfo2.homeword_name = "2019年第二周作业";
        homeworkInfo2.homeword_desc = "做一个小型游戏界面，能够实现游戏任务的走路前进和后退";
        homeworkInfo2.homeword_time = "2019-09-28";
        homeworkInfo2.homeword_course = "Java";
        list.add(homeworkInfo2);
        if (studentListView != null) {
            studentListView.refreshHomeworkList(list);
        }
    }

}
