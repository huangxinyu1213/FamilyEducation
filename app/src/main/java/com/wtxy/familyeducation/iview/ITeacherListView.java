package com.wtxy.familyeducation.iview;

import com.wtxy.familyeducation.user.GradeInfo;
import com.wtxy.familyeducation.user.HomeworkInfo;


import java.util.List;

/**
 * @Author: maxiaohu
 * @Date: 2020/4/12
 * @Describe:
 */
public interface ITeacherListView extends IView {
    public void refreshGrandList(List<GradeInfo> gradeInfos);

    public void refreshHomeworkList(List<HomeworkInfo> homeworkInfos);
}
