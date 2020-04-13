package com.wtxy.familyeducation.iview;

import com.wtxy.familyeducation.bean.SubjectInfo;

import java.util.List;

/**
 * @Author: maxiaohu
 * @Date: 2020/4/12
 * @Describe:
 */
public interface ISubjectListView extends IView{
    public void refrshSubJectList(List<SubjectInfo> list);
}
