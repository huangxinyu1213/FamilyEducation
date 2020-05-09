package com.wtxy.familyeducation.iview;

import com.wtxy.familyeducation.user.GradeInfo;
import com.wtxy.familyeducation.user.HomeworkInfo;

import java.util.List;

/**
 * @Author: yiwenhui
 * @Date: 2020/4/12
 * @Describe:
 */
public interface IStudentListView extends IView {
    public void refreshGrandList(List<GradeInfo> gradeInfos);

    public void refreshHomeworkList(List<HomeworkInfo> homeworkInfos);
}
