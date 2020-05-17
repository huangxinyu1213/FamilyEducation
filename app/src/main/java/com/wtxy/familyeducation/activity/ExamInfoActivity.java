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
    private EditText edtTitle;
    private RelativeLayout rl_check_subject;
    private TextView tv_subject;
    private TextView tvClass;
    private LinearLayout llGrade;
    private RelativeLayout rl_check_class;
    private ExamInfo mGradeInfo;
    private List<StudentInfo> studentInfoList;
    private List<ScoreInfo> scoreList;

    public static Intent newIntent(Context context, ExamInfo gradeInfo) {
        Intent intent = new Intent(context, ExamInfoActivity.class);
        intent.putExtra("GradeInfo", gradeInfo);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_grand_info);
        super.onCreate(savedInstanceState);
        mGradeInfo = (ExamInfo) getIntent().getSerializableExtra("GradeInfo");
        showTitle("考试详情");
        showRightBtn("保存");
        scoreList = new ArrayList<>();
        studentInfoList = new ArrayList<>();
//        getTestScore();
        edtTitle = findViewById(R.id.edtTitle);
        rl_check_subject = findViewById(R.id.rl_check_subject);
        tv_subject = findViewById(R.id.tv_subject);
        tvClass = findViewById(R.id.tvClass);
        llGrade = findViewById(R.id.llGrade);
        rl_check_class = findViewById(R.id.rl_check_class);
        //表示新增，标题副标题部分允许编辑
        if (mGradeInfo == null) {
            edtTitle.setEnabled(true);
            rl_check_subject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ExamInfoActivity.this, SubjectListActivity.class);
                    intent.putExtra(Const.KEY_IS_SELECT, true);
                    startActivityForResult(intent, 1000);
                }
            });
            rl_check_class.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ExamInfoActivity.this, ChoiceClassActivity.class);
                    startActivityForResult(intent, 2000);
                }
            });
            llGrade.setVisibility(View.GONE);
            mGradeInfo = new ExamInfo();
        } else {
            //标题副标题部分不允许编辑
            edtTitle.setEnabled(false);
            edtTitle.setText(mGradeInfo.exam_name);
            tv_subject.setText(mGradeInfo.subject_name);
            tvClass.setText(mGradeInfo.class_name);
            //请求接口
            loadStudentList();
        }
    }

    private void loadStudentList() {
        LoadStudentListTask loadStudentListTask = new LoadStudentListTask(new TaskListener<LoadStudentListHttpResult>() {
            @Override
            public void onTaskStart(TaskListener<LoadStudentListHttpResult> listener) {

            }

            @Override
            public void onTaskComplete(TaskListener<LoadStudentListHttpResult> listener, LoadStudentListHttpResult result, Exception e) {
                if (result != null && result.isSuccess()) {
                    studentInfoList.addAll(result.getResult());
                }
                loadScoreList();
            }
        }, LoadStudentListHttpResult.class);
        loadStudentListTask.setClassId(mGradeInfo.class_id);
        loadStudentListTask.execute();
    }

    private void loadScoreList() {
        LoadScoreListTask loadScoreListTask = new LoadScoreListTask(new TaskListener<LoadScoreListHttpResult>() {
            @Override
            public void onTaskStart(TaskListener<LoadScoreListHttpResult> listener) {

            }

            @Override
            public void onTaskComplete(TaskListener<LoadScoreListHttpResult> listener, LoadScoreListHttpResult result, Exception e) {
                if (result != null && result.isSuccess()) {
                    scoreList.addAll(result.getResult());
                }
                showData();
            }
        }, LoadScoreListHttpResult.class);
        loadScoreListTask.setExamId(mGradeInfo.exam_id);
        loadScoreListTask.execute();
    }

    private void showData() {
        if (studentInfoList.size() == 0) {
            return;
        }
        for (StudentInfo studentInfo : studentInfoList) {
            setScoreInfo(studentInfo);
        }
        addScoreView();
    }

    @Override
    public void onRightBtnClick() {
        super.onRightBtnClick();
        String examName = edtTitle.getText().toString().trim();
        String subjectName = tv_subject.getText().toString().trim();
        String className = tvClass.getText().toString().trim();
        mGradeInfo.exam_name = examName;
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2000 && resultCode == 2000) {
            ClassInfo classInfo = (ClassInfo) data.getSerializableExtra("classInfo");
            if (classInfo != null && mGradeInfo != null) {
                mGradeInfo.class_id = classInfo.getClass_id();
                mGradeInfo.class_name = classInfo.getClass_name();
                tvClass.setText(mGradeInfo.class_name);
            }
        } else if (requestCode == 1000 && resultCode == 1) {
            SubjectInfo subjectInfo = (SubjectInfo) data.getSerializableExtra(Const.KEY_SUBJECT_INFO);
            if (subjectInfo != null && mGradeInfo != null) {
                mGradeInfo.subject_id = subjectInfo.getSubject_id();
                mGradeInfo.subject_name = subjectInfo.getSubject_name();
                tv_subject.setText(mGradeInfo.subject_name);
            }
        }
    }

    private void getTestScore() {
        NameFactory nameFactory = new NameFactory();
        for (int i = 0; i < 10; i++) {
            ScoreInfo score = new ScoreInfo();
            score.student_name = nameFactory.getName();
            int scoreData = new Random().nextInt(100);
            score.score_num = scoreData;
            scoreList.add(score);
        }
    }

    private void setScoreInfo(StudentInfo studentInfo) {
        if (scoreList.size() > 0) {
            for (ScoreInfo scoreInfo : scoreList) {
                if (scoreInfo.student_id == studentInfo.student_id) {
                    studentInfo.scoreInfo = scoreInfo;
                    return;
                }
            }
        }
    }

    private void addScoreView() {
        LayoutInflater inflate = LayoutInflater.from(this);
        llGrade.removeAllViews();
        for (final StudentInfo studentInfo : studentInfoList) {
            View view = inflate.inflate(R.layout.layout_score_item, null);
            TextView tvName = view.findViewById(R.id.tv_name);
            TextView tvScore = view.findViewById(R.id.tv_score);
            tvName.setText(studentInfo.student_name);
            if (studentInfo.scoreInfo != null) {
                if ((studentInfo.scoreInfo.score_num + "").endsWith(".0")) {
                    tvScore.setText((int) studentInfo.scoreInfo.score_num + "");
                } else {
                    tvScore.setText(studentInfo.scoreInfo.score_num + "");
                }
            } else {
                tvScore.setVisibility(View.GONE);
            }
            llGrade.addView(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAddDialog(studentInfo);
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
        tv_title.setText(studentInfo.student_name + "同学");
        btn_cancel_high_opion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        if (studentInfo.scoreInfo != null) {
            editText.setText(studentInfo.scoreInfo.score_num + "");
        }
        btn_agree_high_opion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editText.getText())) {
                    Toast.makeText(ExamInfoActivity.this, "请输入分数", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    float newScore = Float.parseFloat(editText.getText().toString().trim());
                    if (studentInfo.scoreInfo != null) {
                        studentInfo.scoreInfo.score_num = newScore;
                    } else {
                        ScoreInfo scoreInfo = new ScoreInfo();
                        scoreInfo.score_num = newScore;
                        scoreInfo.student_id = studentInfo.student_id;
                        scoreInfo.student_name = studentInfo.student_name;
                        scoreInfo.exam_id = mGradeInfo.exam_id;
                        studentInfo.scoreInfo = scoreInfo;
                    }
                    addScore(studentInfo.scoreInfo);
                } catch (Exception e) {
                }
                dialog.dismiss();
            }
        });
        dialog.show();
        //此处设置位置窗体大小，我这里设置为了手机屏幕宽度的3/4  注意一定要在show方法调用后再写设置窗口大小的代码，否则不起效果会
        dialog.getWindow().setLayout((ScreenUtils.getScreenWidth(this) / 4 * 3), LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    private void addScore(ScoreInfo scoreInfo) {
        AddScoreTask addScoreTask = new AddScoreTask(new TaskListener<HttpResult>() {
            @Override
            public void onTaskStart(TaskListener<HttpResult> listener) {

            }

            @Override
            public void onTaskComplete(TaskListener<HttpResult> listener, HttpResult result, Exception e) {
                addScoreView();
            }
        }, HttpResult.class);
        addScoreTask.setParam(scoreInfo);
        addScoreTask.execute();
    }
}
