package com.wtxy.familyeducation.adapter;

import android.content.Context;
import android.widget.TextView;

import com.wtxy.familyeducation.R;
import com.wtxy.familyeducation.bean.CourseInfo;

import java.util.List;

/**
 * @Author: yiwenhui
 * @Date: 2020/5/9
 * @Describe:
 */
public class CourseAdapter extends CommonAdapter<CourseInfo>{

    public CourseAdapter(Context context, List<CourseInfo> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, CourseInfo item, int postion) {
       CourseInfo courseInfo = mDatas.get(postion);
       TextView courseName = helper.getView(R.id.course_name);
       courseName.setText(courseInfo.course_name);
       TextView courseTime = helper.getView(R.id.course_time);
       courseTime.setText(courseInfo.course_time);
       TextView courseAddress = helper.getView(R.id.course_address);
       courseAddress.setText(courseInfo.course_address);
    }
}
