package com.wtxy.familyeducation.adapter;

import android.content.Context;
import android.widget.TextView;

import com.wtxy.familyeducation.R;
import com.wtxy.familyeducation.bean.BaseItemBean;

import java.util.List;

/**
 * @Author: maxiaohu
 * @Date: 2020/4/11
 * @Describe:
 */
public class CommonListAdapter<T extends BaseItemBean> extends CommonAdapter<BaseItemBean> {


    public CommonListAdapter(Context context, List<BaseItemBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, BaseItemBean item, int postion) {
        ((TextView) helper.getView(R.id.tv_title)).setText(item.getShowTitle());
    }
}
