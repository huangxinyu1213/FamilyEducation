package com.wtxy.familyeducation.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.wtxy.familyeducation.R;
import com.wtxy.familyeducation.constant.Const;
import com.wtxy.familyeducation.fragment.BaseFragment;
import com.wtxy.familyeducation.fragment.EducationManageFragment;
import com.wtxy.familyeducation.fragment.MessageManageFragment;
import com.wtxy.familyeducation.fragment.MineFragment;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    switchFragment(Const.PAGE_MESSAGE_MANAGE);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    switchFragment(Const.PAGE_EDUCATION_MANAGE);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    switchFragment(Const.PAGE_MINE);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initFragment();
    }

    /**
     *  首次进入默认加载信息管理页
     */
    private void initFragment() {
        switchFragment(Const.PAGE_MESSAGE_MANAGE);
    }

    /**
     *  当前显示的fragment
     */
    private BaseFragment currentFragment;
    private BaseFragment messageManageFragment;

    /**
     *  显示对应页面的fragment
     * @param pageId
     */
    private void switchFragment(int pageId){
        BaseFragment fragment;
        switch (pageId){
            case Const.PAGE_MESSAGE_MANAGE:
                fragment = MessageManageFragment.getInstance();
                messageManageFragment = fragment;
                break;
             case Const.PAGE_EDUCATION_MANAGE:
                 fragment = EducationManageFragment.getInstance();
                 break;
             case Const.PAGE_MINE:
                 fragment = MineFragment.getInstance();
                 break;
             default:
                 fragment = MessageManageFragment.getInstance();
                 break;
        }
        // 页面转场动画
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (!fragment.isAdded()){
            if (currentFragment != null) {
                fragmentTransaction.hide(currentFragment);
            }
                    fragmentTransaction.add(R.id.frag_container,fragment)
                    .commit();
        }else {
            fragmentTransaction.hide(currentFragment)
                    .show(fragment)
                    .commit();
        }
        currentFragment = fragment;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Const.REQUEST_PUB_NOTICE || requestCode == Const.REQUEST_PUB_NEWS){
            if (messageManageFragment != null){
                messageManageFragment.onActivityResult(requestCode,resultCode,data);
            }
        }
    }
}
