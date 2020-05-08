package com.wtxy.familyeducation.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.wtxy.familyeducation.BaseActivity;
import com.wtxy.familyeducation.R;
import com.wtxy.familyeducation.adapter.CommonAdapter;
import com.wtxy.familyeducation.adapter.CourseAdapter;
import com.wtxy.familyeducation.adapter.ViewHolder;
import com.wtxy.familyeducation.bean.CourseInfo;
import com.wtxy.familyeducation.constant.Const;
import com.wtxy.familyeducation.httpresult.LoadCourceTableResult;
import com.wtxy.familyeducation.task.LoadCourseTableTask;
import com.zhy.http.okhttp.requestBase.TaskListener;

import java.util.ArrayList;
import java.util.List;

public class ClassTableActivity extends BaseActivity {
    private ListView weekList;
    private ListView tableList;
    private List<String> weeks = new ArrayList<>();
    private int selectedDay = 0;//默认选中周一
    private int courseId;
    private String className;
    private CourseAdapter courseAdapter;
    private List<CourseInfo> selectCourseInfo;
    private List<List<CourseInfo>> mCourseList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_class_table);
        super.onCreate(savedInstanceState);
        courseId = getIntent().getIntExtra(Const.KEY_CLASS_ID,0);
        className = getIntent().getStringExtra(Const.KEY_CLASS_NAME);
        showTitle(className+"-课表");
        initWeekList();
        weekList = findViewById(R.id.week_list);
        weekList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedDay = position;
                weekAdapter.notifyDataSetChanged();
                changeClassTable();
            }
        });
        tableList = findViewById(R.id.table_list);
        weekAdapter = new CommonAdapter<String>(this,weeks,R.layout.layout_week_item) {
            @Override
            public void convert(ViewHolder helper, String item, int postion) {
                TextView textView =  helper.getView(R.id.week_day);
                textView.setText(weeks.get(postion));
                textView.setSelected(selectedDay == postion);
            }
        };
        weekList.setAdapter(weekAdapter);
        loadCourseData();
    }

    private void loadCourseData() {
        LoadCourseTableTask loadCourseTableTask = new LoadCourseTableTask(taskListener,LoadCourceTableResult.class);
        loadCourseTableTask.setParam(courseId+"");
        loadCourseTableTask.execute();
    }

    private TaskListener<LoadCourceTableResult> taskListener = new TaskListener<LoadCourceTableResult>() {
        @Override
        public void onTaskStart(TaskListener<LoadCourceTableResult> listener) {

        }

        @Override
        public void onTaskComplete(TaskListener<LoadCourceTableResult> listener, LoadCourceTableResult result, Exception e) {
             if (result != null && result.isSuccess()){
                 mCourseList = result.result;
                if (courseAdapter != null){
                    selectCourseInfo.clear();
                    try {
                        selectCourseInfo.addAll(result.result.get(selectedDay));
                    }catch (Exception e1){

                    }
                    courseAdapter.notifyDataSetChanged();
                }else {
                    selectCourseInfo = result.result.get(selectedDay);
                    courseAdapter = new CourseAdapter(ClassTableActivity.this,selectCourseInfo,R.layout.layout_course_item);
                    tableList.setAdapter(courseAdapter);
                }
             }
        }
    };

    /**
     *  切换课表
     */
    private void changeClassTable() {
         try {
             selectCourseInfo = mCourseList.get(selectedDay);
         }catch (Exception e){
              selectCourseInfo = new ArrayList<>();
         }
         courseAdapter.notifyDataSetChanged();
    }

    private void initWeekList() {
        weeks.add("周一");
        weeks.add("周二");
        weeks.add("周三");
        weeks.add("周四");
        weeks.add("周五");
        weeks.add("周六");
        weeks.add("周日");
    }


    private CommonAdapter<String> weekAdapter;
}
