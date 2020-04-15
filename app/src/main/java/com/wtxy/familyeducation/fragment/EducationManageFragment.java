package com.wtxy.familyeducation.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wtxy.familyeducation.R;
import com.wtxy.familyeducation.activity.ManagerListManageActivity;
import com.wtxy.familyeducation.activity.StudentHomeworkListActivity;
import com.wtxy.familyeducation.activity.StudentScoreListActivity;
import com.wtxy.familyeducation.activity.SubjectListActivity;
import com.wtxy.familyeducation.activity.TeacherManageListActivity;
import com.wtxy.familyeducation.adapter.CommonListAdapter;
import com.wtxy.familyeducation.bean.EducationManageInfo;
import com.wtxy.familyeducation.bean.EducationManagerFactory;
import com.wtxy.familyeducation.constant.Const;
import com.wtxy.familyeducation.user.UserInfo;
import com.wtxy.familyeducation.user.UserInfoManager;

import java.util.List;

/**
 * @Author: maxiaohu
 * @Date: 2020/2/25
 * @Describe: 教务管理
 */
public class EducationManageFragment extends BaseFragment {
    private static EducationManageFragment mInstance;
    private ListView mListView;
    private CommonListAdapter manageAdapter;

    public static EducationManageFragment getInstance() {
        if (mInstance == null) {
            mInstance = new EducationManageFragment();
        }
        return mInstance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_education_manage, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showTitle("教务管理");
        mListView = view.findViewById(R.id.manage_content);
        final int userInfoType = UserInfoManager.getInstance().getCurrentUserInfo().getCurrentUserType();
        final List<EducationManageInfo> data = EducationManagerFactory.generateEducationInfo(userInfoType);
        manageAdapter = new CommonListAdapter(getActivity(), data, R.layout.education_manage_item);
        mListView.setAdapter(manageAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                jumpToManageListActivity(userInfoType, data.get(position));
            }
        });
    }

    /**
     * @param userInfoType
     */
    private void jumpToManageListActivity(int userInfoType, EducationManageInfo educationManageInfo) {
        Intent intent = null;
        switch (userInfoType) {
            case UserInfo.ACCOUNT_TYPE_MANAGER:
                if (educationManageInfo.getManageType() == EducationManageInfo.MANAGE_TYPE_MANAGER_SUBJECT) {
                    intent = new Intent(getActivity(), SubjectListActivity.class);
                    intent.putExtra(Const.KEY_IS_SELECT, false);
                } else {
                    intent = new Intent(getActivity(), ManagerListManageActivity.class);
                    intent.putExtra(Const.KEY_MANAGE_INFO, educationManageInfo);
                }
                break;
            case UserInfo.ACCOUNT_TYPE_TEACHER:
                intent = new Intent(getActivity(), TeacherManageListActivity.class);
                intent.putExtra(Const.KEY_MANAGE_INFO, educationManageInfo);
                break;
            case UserInfo.ACCOUNT_TYPE_STUDENT:
            case UserInfo.ACCOUNT_TYPE_PARENT:
                if (educationManageInfo.getManageType() == EducationManageInfo.MANAGE_TYPE_STUDENT_QUERY_GRADE) {
                    intent = new Intent(getActivity(), StudentScoreListActivity.class);
                    intent.putExtra(Const.KEY_MANAGE_INFO, educationManageInfo);
                } else if (educationManageInfo.getManageType() == EducationManageInfo.MANAGE_TYPE_STUDENT_QUERY_WORK) {
                    intent = new Intent(getActivity(), StudentHomeworkListActivity.class);
                    intent.putExtra(Const.KEY_MANAGE_INFO, educationManageInfo);
                } else if (educationManageInfo.getManageType() == EducationManageInfo.MANAGE_TYPE_STUDENT_QUERY_COURSE) {

                }
                break;
            default:
                intent = new Intent(getActivity(), ManagerListManageActivity.class);
                intent.putExtra(Const.KEY_MANAGE_INFO, educationManageInfo);
                break;
        }
        if (intent == null) {
            return;
        }
        startActivity(intent);
    }
}
