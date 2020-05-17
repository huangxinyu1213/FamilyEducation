package com.wtxy.familyeducation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wtxy.familyeducation.BaseActivity;
import com.wtxy.familyeducation.R;
import com.wtxy.familyeducation.adapter.CommonListAdapter;
import com.wtxy.familyeducation.bean.ClassInfo;
import com.wtxy.familyeducation.biz.ManageListBiz;
import com.wtxy.familyeducation.httpresult.LoadClassListResult;
import com.wtxy.familyeducation.ibiz.IManageListBiz;
import com.zhy.http.okhttp.requestBase.TaskListener;

import java.util.ArrayList;
import java.util.List;

public class ChoiceClassActivity extends BaseActivity {

    private List<ClassInfo> classList;
    private CommonListAdapter mAdapter;
    private ListView listView;

    private IManageListBiz manageListBiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_choice_class);
        super.onCreate(savedInstanceState);
        manageListBiz = new ManageListBiz();
        listView = findViewById(R.id.manage_content);
        classList = new ArrayList<>();
//        getTestClass();
        mAdapter = new CommonListAdapter(this, classList, R.layout.education_manage_item);
        listView.setAdapter(mAdapter);
        showTitle("班级选择");
        manageListBiz.loadClassList(new TaskListener<LoadClassListResult>() {
            @Override
            public void onTaskStart(TaskListener<LoadClassListResult> listener) {

            }

            @Override
            public void onTaskComplete(TaskListener<LoadClassListResult> listener, LoadClassListResult result, Exception e) {
                if (result.isSuccess() && result != null) {
                    classList.addAll(result.getResult());
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ClassInfo classInfo = classList.get(position);
                Intent intent = new Intent();
                intent.putExtra("classInfo", classInfo);
                setResult(2000, intent);
                finish();
            }
        });
    }

    private void getTestClass() {
        ClassInfo classInfo1 = new ClassInfo();
        classInfo1.setClass_id(111);
        classInfo1.setClass_name("计算机1班");
        classList.add(classInfo1);

        ClassInfo classInfo2 = new ClassInfo();
        classInfo2.setClass_id(222);
        classInfo2.setClass_name("计算机2班");
        classList.add(classInfo2);

        ClassInfo classInfo3 = new ClassInfo();
        classInfo3.setClass_id(333);
        classInfo3.setClass_name("经管1班");
        classList.add(classInfo3);
        ClassInfo classInfo4 = new ClassInfo();
        classInfo4.setClass_id(444);
        classInfo4.setClass_name("经管2班");
        classList.add(classInfo4);
    }
}
