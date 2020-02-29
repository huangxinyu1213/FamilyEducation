package com.wtxy.familyeducation.fragment;

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
}
