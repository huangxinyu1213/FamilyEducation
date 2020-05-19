package com.wtxy.familyeducation.iview;

import com.wtxy.familyeducation.bean.ScoreInfo;
import com.wtxy.familyeducation.user.HomeworkInfo;

import java.util.List;

/**
 * @Author: yiwenhui
 * @Date: 2020/4/12
 * @Describe:
 */
public interface IStudentListView extends IView {
    public void refreshGrandList(List<ScoreInfo> gradeInfos);

    public void refreshHomeworkList(List<HomeworkInfo> homeworkInfos);
}
