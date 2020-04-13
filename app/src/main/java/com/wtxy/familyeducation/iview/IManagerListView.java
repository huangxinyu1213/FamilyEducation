package com.wtxy.familyeducation.iview;

import android.widget.ListView;

import com.wtxy.familyeducation.bean.ClassInfo;
import com.wtxy.familyeducation.bean.SubjectInfo;
import com.wtxy.familyeducation.user.TeachInfo;

import java.util.List;

/**
 * @Author: maxiaohu
 * @Date: 2020/4/12
 * @Describe:
 */
public interface IManagerListView extends IView {
    public void refreshTeacherList(List<TeachInfo> teachInfos);
    public void refreshClassList(List<ClassInfo> classInfos);
    public void refreshSubjectList(List<SubjectInfo> subjectInfos);
}
