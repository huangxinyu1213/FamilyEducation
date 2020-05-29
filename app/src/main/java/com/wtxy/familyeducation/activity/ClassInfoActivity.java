package com.wtxy.familyeducation.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
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
    private CommonListAdapter commonListAdapter;//适配器，控制显示什么数据，数据改变之后通知刷新
    private List<StudentInfo> studentInfos = new ArrayList<>();
    private View classTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_class_info);
        super.onCreate(savedInstanceState);
        tvClassName = findViewById(R.id.tv_content);//将控件与属性绑定
        listview = findViewById(R.id.listview);//将控件与属性绑定
        classInfo = (ClassInfo) getIntent().getSerializableExtra(Const.KEY_CLASS_INFO);//查找上一次切换，取出刚刚存的信息
        if (classInfo == null){
            return;
        }
        tvClassName.setText(classInfo.getClass_name());//赋值（赋值什么）
        classTable = findViewById(R.id.class_table);//绑定
        classTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClassInfoActivity.this,ClassTableActivity.class);//跳到ClassTableActivity
                intent.putExtra(Const.KEY_CLASS_ID,classInfo.getClass_id());//KEY_CLASS_ID静态常量
                intent.putExtra(Const.KEY_CLASS_NAME,classInfo.getClass_name());
                startActivity(intent);
            }
        });
        showTitle(classInfo.getShowTitle());//classInfo中的班级名称
        showRightBtn("新增学生");
        loadStudent();//加载学生
    }

    @Override
    public void onRightBtnClick() {
        super.onRightBtnClick();
        Intent intent = new Intent(this,StudentInfoActivity.class);
        intent.putExtra(Const.KEY_CLASS_ID,classInfo.getClass_id());
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
                         studentInfos.clear();//清空缓存
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
        }, LoadStudentListHttpResult.class);//加该参数是为了请求成功以后把返回的result中的Json数组转成该类的对象数组
        loadStudentListTask.setClassId(classInfo.getClass_id());//请求+参数，给请求复制，给这个请求带上这个参数
        loadStudentListTask.execute();//开始请求
    }

    private List<StudentInfo> getTestInfo(){ //测试数据
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
