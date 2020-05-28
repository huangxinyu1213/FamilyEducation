package com.wtxy.familyeducation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wtxy.familyeducation.BaseActivity;
import com.wtxy.familyeducation.R;
import com.wtxy.familyeducation.bean.ClassInfo;
import com.wtxy.familyeducation.bean.ScoreInfo;
import com.wtxy.familyeducation.bean.SubjectInfo;
import com.wtxy.familyeducation.constant.Const;
import com.wtxy.familyeducation.httpresult.LoadScoreListHttpResult;
import com.wtxy.familyeducation.httpresult.LoadStudentListHttpResult;
import com.wtxy.familyeducation.task.AddExamTask;
import com.wtxy.familyeducation.task.AddScoreTask;
import com.wtxy.familyeducation.task.LoadScoreListTask;
import com.wtxy.familyeducation.task.LoadStudentListTask;
import com.wtxy.familyeducation.user.ExamInfo;
import com.wtxy.familyeducation.user.StudentInfo;
import com.wtxy.familyeducation.util.NameFactory;
import com.wtxy.familyeducation.util.ScreenUtils;
import com.zhy.http.okhttp.requestBase.HttpResult;
import com.zhy.http.okhttp.requestBase.TaskListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExamInfoActivity extends BaseActivity {
    private EditText edtTitle;//考试名称输入框
    private RelativeLayout rl_check_subject;//选择科目一行的布局，用于点击
    private TextView tv_subject;//科目名称
    private TextView tvClass;//班级名称
    private LinearLayout llGrade;//分数布局，新建考试信息的时候不展示
    private RelativeLayout rl_check_class;//选择班级一行的布局，用于点击
    private ExamInfo mGradeInfo;//考试信息，null表示新建，否则是查看详情
    private List<StudentInfo> studentInfoList;//当前班级学生list
    private List<ScoreInfo> scoreList;//当前班级当前考试的分数list（包含学生信息）

    public static Intent newIntent(Context context, ExamInfo gradeInfo) {
        Intent intent = new Intent(context, ExamInfoActivity.class);
        intent.putExtra("GradeInfo", gradeInfo);//先存
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_grand_info);
        super.onCreate(savedInstanceState);
        mGradeInfo = (ExamInfo) getIntent().getSerializableExtra("GradeInfo");//先取参数，不一定能取到
        showTitle("考试详情");
        showRightBtn("保存");
        scoreList = new ArrayList<>();//初始化数组
        studentInfoList = new ArrayList<>();//初始化数组
//        getTestScore();
        edtTitle = findViewById(R.id.edtTitle);
        rl_check_subject = findViewById(R.id.rl_check_subject);
        tv_subject = findViewById(R.id.tv_subject);
        tvClass = findViewById(R.id.tvClass);
        llGrade = findViewById(R.id.llGrade);
        rl_check_class = findViewById(R.id.rl_check_class);
        //null表示新增，标题、班级、科目部分允许编辑，并设置跳转
        if (mGradeInfo == null) {
            edtTitle.setEnabled(true);//设置可编辑
            rl_check_subject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转选科目，标识设为1000
                    Intent intent = new Intent(ExamInfoActivity.this, SubjectListActivity.class);
                    intent.putExtra(Const.KEY_IS_SELECT, true);
                    startActivityForResult(intent, 1000);
                }
            });
            rl_check_class.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转选班级，标识设为2000
                    Intent intent = new Intent(ExamInfoActivity.this, ChoiceClassActivity.class);
                    startActivityForResult(intent, 2000);
                }
            });
            llGrade.setVisibility(View.GONE);//新增不显示学生列表
            mGradeInfo = new ExamInfo();//创建空对象，用于后面保存属性，新建考试信息
        } else {//当考试信息不为空，表示查看考试信息，并可以编辑学生分数
            //标题、科目、班级部分不允许编辑，没有设置跳转，填充考试信息
            edtTitle.setEnabled(false);
            edtTitle.setText(mGradeInfo.exam_name);
            tv_subject.setText(mGradeInfo.subject_name);
            tvClass.setText(mGradeInfo.class_name);
            //请求学生列表接口
            loadStudentList();
        }
    }

    private void loadStudentList() {
        //创建学生列表请求接口，并设置回调
        LoadStudentListTask loadStudentListTask = new LoadStudentListTask(new TaskListener<LoadStudentListHttpResult>() {
            @Override
            public void onTaskStart(TaskListener<LoadStudentListHttpResult> listener) {

            }

            @Override
            public void onTaskComplete(TaskListener<LoadStudentListHttpResult> listener, LoadStudentListHttpResult result, Exception e) {
                if (result != null && result.isSuccess()) {
                    studentInfoList.addAll(result.getResult());//保存学生信息
                }
                loadScoreList();//同时请求考试信息
            }
        }, LoadStudentListHttpResult.class);//设置响应对象类，请求结果直接转为该类对象
        loadStudentListTask.setClassId(mGradeInfo.class_id);//设置请求参数班级id
        loadStudentListTask.execute();//请求开始执行，发起
    }

    private void loadScoreList() {//请求分数列表
        LoadScoreListTask loadScoreListTask = new LoadScoreListTask(new TaskListener<LoadScoreListHttpResult>() {
            @Override
            public void onTaskStart(TaskListener<LoadScoreListHttpResult> listener) {

            }

            @Override
            public void onTaskComplete(TaskListener<LoadScoreListHttpResult> listener, LoadScoreListHttpResult result, Exception e) {
                if (result != null && result.isSuccess()) {
                    scoreList.addAll(result.getResult());//保存分数list
                }
                showData();//展示数据
            }
        }, LoadScoreListHttpResult.class);
        loadScoreListTask.setExamId(mGradeInfo.exam_id);//设置请求参数考试id（一个考试对应一个班级，所以不用再传班级id）
        loadScoreListTask.execute();
    }

    private void showData() {
        if (studentInfoList.size() == 0) {//学生对象数组为空的话，就不展示
            return;
        }
        for (StudentInfo studentInfo : studentInfoList) {
            setScoreInfo(studentInfo);//对于当前班级的每一个学生，都去处理其分数信息（有就显示，没有就不显示）
        }
        addScoreView();//处理完成后，展示
    }

    @Override
    public void onRightBtnClick() {//右上按钮的点击事件，保存考试信息
        super.onRightBtnClick();
        String examName = edtTitle.getText().toString().trim();//取考试名称
        String subjectName = tv_subject.getText().toString().trim();//取考试科目
        String className = tvClass.getText().toString().trim();//取班级名称
        mGradeInfo.exam_name = examName;//赋值给考试对象，用于提交(其它信息都在onActivityResult方法中赋值过了，具体看onActivityResult方法)
        //如果哪一项为空，toast提示一下
        if (TextUtils.isEmpty(examName) || TextUtils.isEmpty(subjectName) || TextUtils.isEmpty(className)) {
            Toast.makeText(this, "请输入或选择对应的信息", Toast.LENGTH_SHORT).show();
            return;
        }
        AddExamTask addExamTask = new AddExamTask(new TaskListener<HttpResult>() {
            @Override
            public void onTaskStart(TaskListener<HttpResult> listener) {

            }

            @Override
            public void onTaskComplete(TaskListener<HttpResult> listener, HttpResult result, Exception e) {
                Intent intent = new Intent();
                intent.putExtra("GradeInfo", mGradeInfo);
                setResult(300, intent);
                finish();
            }
        }, HttpResult.class);
        addExamTask.setParam(mGradeInfo);
        addExamTask.execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {//从别的页面返回触发这个方法
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2000 && resultCode == 2000) {//从选择班级页面回来，取一下班级信息
            ClassInfo classInfo = (ClassInfo) data.getSerializableExtra("classInfo");
            if (classInfo != null && mGradeInfo != null) {
                mGradeInfo.class_id = classInfo.getClass_id();//取班级id和名称，赋值给考试对象，用于保存上传
                mGradeInfo.class_name = classInfo.getClass_name();
                tvClass.setText(mGradeInfo.class_name);//当前页面上的班级信息也显示上
            }
        } else if (requestCode == 1000 && resultCode == 1) {//从选择科目页面回来，取一下科目信息
            SubjectInfo subjectInfo = (SubjectInfo) data.getSerializableExtra(Const.KEY_SUBJECT_INFO);
            if (subjectInfo != null && mGradeInfo != null) {
                mGradeInfo.subject_id = subjectInfo.getSubject_id();//取科目id和名称，赋值给考试对象，用于保存上传
                mGradeInfo.subject_name = subjectInfo.getSubject_name();
                tv_subject.setText(mGradeInfo.subject_name);//当前页面上的科目信息也显示上
            }
        }
    }

    private void getTestScore() {//接口没好时候的测试数据
        NameFactory nameFactory = new NameFactory();
        for (int i = 0; i < 10; i++) {
            ScoreInfo score = new ScoreInfo();
            score.student_name = nameFactory.getName();
            int scoreData = new Random().nextInt(100);
            score.score_num = scoreData;
            scoreList.add(score);
        }
    }

    private void setScoreInfo(StudentInfo studentInfo) {//给班级学生列表赋值考试信息，有就显示分数，没有就不显示
        if (scoreList.size() > 0) {
            for (ScoreInfo scoreInfo : scoreList) {//for循环分数列表的每一项
                if (scoreInfo.student_id == studentInfo.student_id) {//如果某个分数信息中的学生id和参数传进来的学生信息的id相同，则取学生的分数
                    studentInfo.scoreInfo = scoreInfo;//讲分数对象赋值给当前学生
                    return;
                }
            }
        }
    }

    private void addScoreView() {//添加学生列表页，只有不是新建考试信息的时候才有
        LayoutInflater inflate = LayoutInflater.from(this);
        llGrade.removeAllViews();//清空学生列表页上其它多余view，重新添加
        for (final StudentInfo studentInfo : studentInfoList) {//for循环每个学生，都创建一行数据
            View view = inflate.inflate(R.layout.layout_score_item, null);//新建一行
            TextView tvName = view.findViewById(R.id.tv_name);//绑定这一行上的学生姓名控件
            TextView tvScore = view.findViewById(R.id.tv_score);//绑定这一行上的学生分数控件
            tvName.setText(studentInfo.student_name);//先把学生姓名显示上
            if (studentInfo.scoreInfo != null) {//如果学生的分数信息不为空，则显示分数
                if ((studentInfo.scoreInfo.score_num + "").endsWith(".0")) {
                    tvScore.setText((int) studentInfo.scoreInfo.score_num + "");
                } else {
                    tvScore.setText(studentInfo.scoreInfo.score_num + "");
                }
            } else {//否则如果学生的分数信息为空的话，分数控件隐藏
                tvScore.setVisibility(View.GONE);
            }
            llGrade.addView(view);//把这一行加到学生列表上去
            view.setOnClickListener(new View.OnClickListener() {//设置这一行的点击事件，用于输入和更新学生分数
                @Override
                public void onClick(View v) {
                    showAddDialog(studentInfo);//弹框，把这个学生传进去
                }
            });
        }
    }

    private void showAddDialog(final StudentInfo studentInfo) {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_score_set, null, false);
        final AlertDialog dialog = new AlertDialog.Builder(this).setView(view).create();
        final EditText editText = view.findViewById(R.id.edt_subject);
        TextView btn_cancel_high_opion = view.findViewById(R.id.btn_cancel);
        TextView btn_agree_high_opion = view.findViewById(R.id.btn_confirm);
        TextView tv_title = view.findViewById(R.id.tv_title);
        tv_title.setText(studentInfo.student_name + "同学");//弹框上的标题表示显示 学生名字+"同学"
        btn_cancel_high_opion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        if (studentInfo.scoreInfo != null) {//如果这个学生有分数信息，就把分数显示上，即改成绩
            editText.setText(studentInfo.scoreInfo.score_num + "");
        }
        btn_agree_high_opion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editText.getText())) {//没写分数就提示一下
                    Toast.makeText(ExamInfoActivity.this, "请输入分数", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    float newScore = Float.parseFloat(editText.getText().toString().trim());
                    if (studentInfo.scoreInfo != null) {
                        studentInfo.scoreInfo.score_num = newScore;//把分数赋给这个学生
                    } else {//如果这个学生没有分数信息，就当下创建一个空的，然后把必要信息赋上，包括分数
                        ScoreInfo scoreInfo = new ScoreInfo();
                        scoreInfo.score_num = newScore;
                        scoreInfo.student_id = studentInfo.student_id;
                        scoreInfo.student_name = studentInfo.student_name;
                        scoreInfo.exam_id = mGradeInfo.exam_id;
                        studentInfo.scoreInfo = scoreInfo;
                    }
                    addScore(studentInfo.scoreInfo);//调接口，给这个学生添加分数
                } catch (Exception e) {
                }
                dialog.dismiss();//点击"取消"或"确定"后弹框消失
            }
        });
        dialog.show();//此处弹框刚创建好，显示出来，上面是回调
        //此处设置位置窗体大小，我这里设置为了手机屏幕宽度的3/4  注意一定要在show方法调用后再写设置窗口大小的代码，否则不起效果会
        dialog.getWindow().setLayout((ScreenUtils.getScreenWidth(this) / 4 * 3), LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    private void addScore(ScoreInfo scoreInfo) {
        // 创建网络请求，添加学生分数
        AddScoreTask addScoreTask = new AddScoreTask(new TaskListener<HttpResult>() {
            @Override
            public void onTaskStart(TaskListener<HttpResult> listener) {

            }

            @Override
            public void onTaskComplete(TaskListener<HttpResult> listener, HttpResult result, Exception e) {
                addScoreView();//请求成功后，重新显示学生和分数的列表
            }
        }, HttpResult.class);
        addScoreTask.setParam(scoreInfo);//参数是这个分数信息
        addScoreTask.execute();
    }
}
