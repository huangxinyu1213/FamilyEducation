package com.wtxy.familyeducation.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wtxy.familyeducation.R;

/**
 * @Author: maxiaohu
 * @Date: 2020/2/25
 * @Describe: 教务管理
 */
public class EducationManageFragment extends BaseFragment{
    private static EducationManageFragment mInstance;

    public static EducationManageFragment getInstance(){
        if (mInstance == null){
            mInstance = new EducationManageFragment();
        }
        return mInstance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_education_manage,null);
    }
}
