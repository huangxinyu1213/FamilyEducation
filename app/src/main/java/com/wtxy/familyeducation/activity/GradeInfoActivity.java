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
import com.wtxy.familyeducation.bean.Score;
import com.wtxy.familyeducation.user.GradeInfo;
import com.wtxy.familyeducation.util.NameFactory;
import com.wtxy.familyeducation.util.ScreenUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GradeInfoActivity extends BaseActivity {
    private EditText edtTitle;
    private EditText edtSubTitle;
    private TextView tvClass;
    private LinearLayout llGrade;
    private RelativeLayout rl_check_class;
    private GradeInfo mGradeInfo;
    private List<Score> scoreList;

    public static Intent newIntent(Context context, GradeInfo gradeInfo) {
        Intent intent = new Intent(context, GradeInfoActivity.class);
        intent.putExtra("GradeInfo", gradeInfo);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_grand_info);
        super.onCreate(savedInstanceState);
        mGradeInfo = (GradeInfo) getIntent().getSerializableExtra("GradeInfo");
        showTitle("成绩详情");
        showRightBtn("保存");
        scoreList = new ArrayList<>();
        getTestScore();
        edtTitle = findViewById(R.id.edtTitle);
        edtSubTitle = findViewById(R.id.edtSubTitle);
        tvClass = findViewById(R.id.tvClass);
        llGrade = findViewById(R.id.llGrade);
        rl_check_class = findViewById(R.id.rl_check_class);
        rl_check_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GradeInfoActivity.this, ChoiceClassActivity.class);
                startActivityForResult(intent, 1000);
            }
        });
        if (mGradeInfo != null) {
            edtTitle.setText(mGradeInfo.grade_name);
            edtSubTitle.setText(mGradeInfo.grade_college);
        }
        addScoreView();
    }

    @Override
    public void onRightBtnClick() {
        super.onRightBtnClick();
        if (mGradeInfo == null) {
            mGradeInfo = new GradeInfo();
        }
        mGradeInfo.grade_name = edtTitle.getText().toString().trim();
        mGradeInfo.grade_college = edtSubTitle.getText().toString().trim();
        Intent intent = new Intent();
        intent.putExtra("GradeInfo", mGradeInfo);
        setResult(300, intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == 1000) {
            String className = data.getStringExtra("class_name");
            tvClass.setText(className);
        }
    }

    private void getTestScore() {
        NameFactory nameFactory = new NameFactory();
        for (int i = 0; i < 10; i++) {
            Score score = new Score();
            score.name = nameFactory.getName();
            int scoreData = new Random().nextInt(100);
            score.score = scoreData;
            scoreList.add(score);
        }
    }

    private void addScoreView() {
        LayoutInflater inflate = LayoutInflater.from(this);
        llGrade.removeAllViews();
        for (final Score score : scoreList) {
            View view = inflate.inflate(R.layout.layout_score_item, null);
            TextView tvName = view.findViewById(R.id.tv_name);
            TextView tvScore = view.findViewById(R.id.tv_score);
            tvName.setText(score.name);
            tvScore.setText(score.score + "");
            llGrade.addView(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAddDialog(score);
                }
            });
        }
    }

    private void showAddDialog(final Score score) {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_score_set, null, false);
        final AlertDialog dialog = new AlertDialog.Builder(this).setView(view).create();
        final EditText editText = view.findViewById(R.id.edt_subject);
        TextView btn_cancel_high_opion = view.findViewById(R.id.btn_cancel);
        TextView btn_agree_high_opion = view.findViewById(R.id.btn_confirm);
        TextView tv_title = view.findViewById(R.id.tv_title);
        tv_title.setText(score.name + "同学");
        btn_cancel_high_opion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn_agree_high_opion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editText.getText())) {
                    Toast.makeText(GradeInfoActivity.this, "请输入分数", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    int scored = Integer.parseInt(editText.getText().toString().trim());
                    score.score = scored;
                    addScoreView();
                } catch (Exception e) {
                }
                dialog.dismiss();
            }
        });

        dialog.show();
        //此处设置位置窗体大小，我这里设置为了手机屏幕宽度的3/4  注意一定要在show方法调用后再写设置窗口大小的代码，否则不起效果会
        dialog.getWindow().setLayout((ScreenUtils.getScreenWidth(this) / 4 * 3), LinearLayout.LayoutParams.WRAP_CONTENT);
    }
}
