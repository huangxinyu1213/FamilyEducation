package com.wtxy.familyeducation.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @Author: maxiaohu
 * @Date: 2020/2/25
 * @Describe: 我的
 */
public class MineFragment extends BaseFragment{
    private static MineFragment mInstance;
    public static MineFragment getInstance(){
        if (mInstance == null){
            mInstance = new MineFragment();
        }
        return mInstance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
