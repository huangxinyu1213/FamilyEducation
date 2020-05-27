package com.wtxy.familyeducation.iview;

import com.wtxy.familyeducation.user.ExamInfo;
import com.wtxy.familyeducation.bean.HomeWorkInfo;
import com.wtxy.familyeducation.user.GradeInfo;
import com.wtxy.familyeducation.user.HomeworkInfo;


import java.util.List;

/**
 * @Author: yiwenhui
 * @Date: 2020/4/12
 * @Describe:
 */
public interface ITeacherListView extends IView {
    public void refreshGrandList(List<ExamInfo> gradeInfos);

    public void refreshHomeworkList(List<HomeworkInfo> homeworkInfos);
}
