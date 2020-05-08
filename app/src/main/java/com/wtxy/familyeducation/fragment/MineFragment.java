package com.wtxy.familyeducation.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wtxy.familyeducation.MainActivity;
import com.wtxy.familyeducation.R;
import com.wtxy.familyeducation.user.UserInfo;
import com.wtxy.familyeducation.user.UserInfoManager;

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
        return inflater.inflate(R.layout.fragment_mine, null);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showTitle("个人");
        view.findViewById(R.id.btn_back).setVisibility(View.GONE);
        view.findViewById(R.id.divider).setVisibility(View.GONE);
        TextView tvName =  view.findViewById(R.id.tv_name);
        TextView tvLable = view.findViewById(R.id.tv_lable);
        UserInfo userInfo = UserInfoManager.getInstance().getCurrentUserInfo();
        if (userInfo != null) {
            tvName.setText(userInfo.getUserName());
            tvLable.setText(userInfo.getUserTypeName());
        }
        TextView tvRightButton = view.findViewById(R.id.btn_right);
        tvRightButton.setVisibility(View.VISIBLE);
        tvRightButton.setText("退出登录");
        tvRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                UserInfoManager.getInstance().loginOut(getActivity());
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });
    }
}
