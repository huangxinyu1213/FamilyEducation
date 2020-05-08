package com.wtxy.familyeducation.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.wtxy.familyeducation.BaseActivity;
import com.wtxy.familyeducation.R;
import com.wtxy.familyeducation.adapter.CommonListAdapter;
import com.wtxy.familyeducation.bean.ClassInfo;
import com.wtxy.familyeducation.constant.Const;
import com.wtxy.familyeducation.httpresult.LoadStudentListHttpResult;
import com.wtxy.familyeducation.httpresult.LoadSubjectListResult;
import com.wtxy.familyeducation.task.LoadStudentListTask;
import com.wtxy.familyeducation.user.StudentInfo;
import com.zhy.http.okhttp.requestBase.TaskListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ClassInfoActivity extends BaseActivity {
    private TextView tvClassName;
    private ListView listview;
    private ClassInfo classInfo;
    private CommonListAdapter commonListAdapter;
    private List<StudentInfo> studentInfos = new ArrayList<>();
    private View classTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_class_info);
        super.onCreate(savedInstanceState);
        tvClassName = findViewById(R.id.tv_content);
        listview = findViewById(R.id.listview);
        classInfo = (ClassInfo) getIntent().getSerializableExtra(Const.KEY_CLASS_INFO);
        if (classInfo == null){
            return;
        }
        tvClassName.setText(classInfo.getClass_name());
        classTable = findViewById(R.id.class_table);
        classTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClassInfoActivity.this,ClassTableActivity.class);
                intent.putExtra(Const.KEY_CLASS_ID,classInfo.getClass_id());
                intent.putExtra(Const.KEY_CLASS_NAME,classInfo.getClass_name());
                startActivity(intent);
            }
        });
        showTitle(classInfo.getShowTitle());
        loadStudent();
    }

    private void loadStudent() {
        LoadStudentListTask loadStudentListTask = new LoadStudentListTask(new TaskListener<LoadStudentListHttpResult>() {
            @Override
            public void onTaskStart(TaskListener<LoadStudentListHttpResult> listener) {

            }

            @Override
            public void onTaskComplete(TaskListener<LoadStudentListHttpResult> listener, LoadStudentListHttpResult result, Exception e) {
                 if (result != null && result.isSuccess()){
                     if (result.getResult() != null && !result.getResult().isEmpty()){
                         studentInfos.clear();
                         studentInfos.addAll(result.getResult());
                     //studentInfos = getTestInfo();
                     if (commonListAdapter == null) {
                             commonListAdapter = new CommonListAdapter(ClassInfoActivity.this, studentInfos, R.layout.common_list_item);
                             listview.setAdapter(commonListAdapter);
                         }else {
                             commonListAdapter.notifyDataSetChanged();
                         }
                     }
                 }
            }
        }, LoadStudentListHttpResult.class);
        loadStudentListTask.setClassId(classInfo.getClass_id());
        loadStudentListTask.execute();
    }

    private List<StudentInfo> getTestInfo(){
        List<StudentInfo> studentInfos = new ArrayList<>();
        StudentInfo studentInfo1 = new StudentInfo();
        studentInfo1.student_name = "张三";
        studentInfos.add(studentInfo1);
        StudentInfo studentInfo2 = new StudentInfo();
        studentInfo1.student_name = "李四";
        studentInfos.add(studentInfo2);
        StudentInfo studentInfo3 = new StudentInfo();
        studentInfo1.student_name = "王五";
        studentInfos.add(studentInfo3);
        return studentInfos;
    }
}
