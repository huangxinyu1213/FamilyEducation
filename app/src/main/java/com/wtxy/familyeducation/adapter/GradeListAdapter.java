package com.wtxy.familyeducation.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.wtxy.familyeducation.R;
import com.wtxy.familyeducation.bean.BaseItemBean;
import com.wtxy.familyeducation.bean.HomeWorkInfo;
import com.wtxy.familyeducation.user.GradeInfo;
import com.wtxy.familyeducation.user.ExamInfo;
import com.wtxy.familyeducation.user.HomeworkInfo;

import java.util.List;

/**
 * @Author: yiwenhui
 * @Date: 2020/4/11
 * @Describe:
 */
public class GradeListAdapter<T extends BaseItemBean> extends CommonAdapter<BaseItemBean> {


    public GradeListAdapter(Context context, List<BaseItemBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, BaseItemBean item, int postion) {
        if (item instanceof ExamInfo) {
            ExamInfo gradeInfo = (ExamInfo) item;
            ((TextView) helper.getView(R.id.tv_title)).setText(gradeInfo.exam_name);
            ((TextView) helper.getView(R.id.tv_college)).setText(gradeInfo.class_name + " " + gradeInfo.subject_name);
            helper.getView(R.id.tv_time).setVisibility(View.GONE);
        } else if (item instanceof HomeworkInfo) {
            HomeworkInfo homeworkInfo = (HomeworkInfo) item;
            ((TextView) helper.getView(R.id.tv_title)).setText(homeworkInfo.hw_title);
            ((TextView) helper.getView(R.id.tv_college)).setText(homeworkInfo.hw_detail);
            helper.getView(R.id.tv_time).setVisibility(View.VISIBLE);
            ((TextView) helper.getView(R.id.tv_time)).setText(homeworkInfo.hw_time);
        }

    }
}
