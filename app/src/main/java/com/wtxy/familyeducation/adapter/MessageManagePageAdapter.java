package com.wtxy.familyeducation.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @Author: yiwenhui
 * @Date: 2020/2/26
 * @Describe:
 */
public class MessageManagePageAdapter extends PagerAdapter {
    private List<View> mLists;
    public MessageManagePageAdapter(List<View> lists){
      this.mLists = lists;
    }
    @Override
    public int getCount() {
        return mLists.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(mLists.get(position),0);

        return mLists.get(position);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
        container.removeView(mLists.get(position));
    }
}
