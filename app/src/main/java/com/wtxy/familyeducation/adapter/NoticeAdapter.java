package com.wtxy.familyeducation.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wtxy.familyeducation.R;
import com.wtxy.familyeducation.bean.News;
import com.wtxy.familyeducation.bean.Notices;

import java.util.List;

/**
 * @Author: yiwenhui
 * @Date: 2020/2/26
 * @Describe:
 */
public class NoticeAdapter extends CommonAdapter<Notices>{
    public NoticeAdapter(Context context, List<Notices> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, Notices item, int postion) {
        TextView tvTitle = helper.getView(R.id.tv_title);
        TextView tvContent = helper.getView(R.id.tv_content);
        tvTitle.setText(item.notice_title);
        tvContent.setText(item.notice_author);
    }
}
