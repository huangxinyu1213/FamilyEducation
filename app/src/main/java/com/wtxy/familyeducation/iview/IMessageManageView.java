package com.wtxy.familyeducation.iview;

import com.wtxy.familyeducation.bean.News;
import com.wtxy.familyeducation.bean.Notices;

import java.util.List;

/**
 * @Author: maxiaohu
 * @Date: 2020/2/26
 * @Describe:
 */
public interface IMessageManageView extends IView{
     void refreshNewsView(List<News> news);
     void refreshNoticeView(List<Notices> notices);
}
