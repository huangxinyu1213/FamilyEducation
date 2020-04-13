package com.wtxy.familyeducation.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.wtxy.familyeducation.R;

/**
 * @Author: maxiaohu
 * @Date: 2020/2/25
 * @Describe:
 */
public class BaseFragment extends Fragment {
    private TextView titleView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titleView = view.findViewById(R.id.tv_title);
    }

    protected void showTitle(String content){
        titleView.setText(content);
    }
}
