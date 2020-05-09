package com.wtxy.familyeducation.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wtxy.familyeducation.R;
import com.wtxy.familyeducation.bean.News;

import java.util.List;

/**
 * @Author: yiwenhui
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
        String testUrl = "https://baike.baidu.com/pic/%E7%85%A7%E7%89%87/1465692/1/37d3d539b6003af3" +
                "56370ff93f2ac65c1138b69e?fr=lemma&ct=single#aid=1&pic=37d3d539b6003af356370ff93f2ac65c1138b69e";
        Picasso.get().load(testUrl).error(R.mipmap.ic_launcher).into(imageView);
        tvTitle.setText(item.news_title);
        tvContent.setText(item.news_subtitle);
    }
}
