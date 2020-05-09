package com.wtxy.familyeducation.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.RadioButton;

import com.wtxy.familyeducation.BaseActivity;
import com.wtxy.familyeducation.R;
import com.wtxy.familyeducation.constant.Const;
import com.wtxy.familyeducation.task.ModifyStudentInfoTask;
import com.wtxy.familyeducation.user.StudentInfo;
import com.wtxy.familyeducation.util.ToastUtil;
import com.zhy.http.okhttp.requestBase.HttpResult;
import com.zhy.http.okhttp.requestBase.TaskListener;

public class StudentInfoActivity extends BaseActivity {
    private EditText edtName,edtAccount,edtParentName,edtParentAccount,edtRelation;
    private int classId;
    private RadioButton rdMan,rdWoman;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_student_info);
        super.onCreate(savedInstanceState);
        showTitle("学生信息");
        showRightBtn("确认");
        edtName = findViewById(R.id.edtName);
        edtAccount = findViewById(R.id.edtAcount);
        edtParentName = findViewById(R.id.edtParent);
        edtRelation = findViewById(R.id.edt_relation_ship);
        edtParentAccount = findViewById(R.id.edtParentAcount);
        classId = getIntent().getIntExtra(Const.KEY_CLASS_ID,0);
        rdMan = findViewById(R.id.rb_man);
        rdWoman = findViewById(R.id.rb_woman);
    }

    @Override
    public void onRightBtnClick() {
        super.onRightBtnClick();
        if (TextUtils.isEmpty(edtName.getText())){
            showToast("请输入学生姓名");
            return;
        }
        if (TextUtils.isEmpty(edtAccount.getText())){
            showToast("请输入学生账号");
            return;
        }
        if (TextUtils.isEmpty(edtParentName.getText())){
            showToast("请输入家长姓名");
            return;
        }
        if (TextUtils.isEmpty(edtParentAccount.getText())){
            showToast("请输入家长账号");
            return;
        }
        if (TextUtils.isEmpty(edtRelation.getText())){
            showToast("请输入与家长关系");
            return;
        }
        addStudent();
    }

    private void addStudent() {
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.student_name = edtName.getText().toString();
        studentInfo.student_account = edtAccount.getText().toString();
        studentInfo.student_gender = rdMan.isChecked() ? "男" : "女";
        studentInfo.class_id = classId+"";
        studentInfo.parent_name = edtParentName.getText().toString();
        studentInfo.parent_relation = edtRelation.getText().toString();
        studentInfo.parent_account = edtParentAccount.getText().toString();
        ModifyStudentInfoTask modifyStudentInfoTask = new ModifyStudentInfoTask(new TaskListener<HttpResult>() {
            @Override
            public void onTaskStart(TaskListener<HttpResult> listener) {

            }

            @Override
            public void onTaskComplete(TaskListener<HttpResult> listener, HttpResult result, Exception e) {
               if (result != null && result.isSuccess()){
                   showToast("学生新增成功");
                   setResult(1);
                   finish();
               }else {
                   showToast("新增学生失败");
               }
            }
        },HttpResult.class);
        modifyStudentInfoTask.setStudentInfo(studentInfo);
        modifyStudentInfoTask.execute();
    }

    private void showToast(String content){
        if (!TextUtils.isEmpty(content)){
            ToastUtil.showShortToast(this,content);
        }
    }
}
