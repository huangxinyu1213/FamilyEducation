package com.wtxy.familyeducation.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wtxy.familyeducation.R;
import com.wtxy.familyeducation.bean.News;

import java.util.List;

/**
 * @Author: maxiaohu
 * @Date: 2020/2/26
 * @Describe:
 */
public class NewsAdapter extends CommonAdapter<News>{
    public NewsAdapter(Context context, List<News> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, News item, int postion) {
        ImageView imageView = helper.getView(R.id.image);
        TextView tvTitle = helper.getView(R.id.tv_title);
        TextView tvContent = helper.getView(R.id.tv_content);
        Picasso.get().load(item.news_url).into(imageView);
        tvTitle.setText(item.new_title);
        tvContent.setText(item.news_subtitle);
    }
}
