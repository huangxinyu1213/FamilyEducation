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
    private List<CourseInfo> selectCourseInfo;//选中当天的所有课程表
    private List<List<CourseInfo>> mCourseList;//所有的课程表
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_class_table);
        super.onCreate(savedInstanceState);
        courseId = getIntent().getIntExtra(Const.KEY_CLASS_ID,0);//取参数
        className = getIntent().getStringExtra(Const.KEY_CLASS_NAME);//取参数班级
        showTitle(className+"-课表");
        initWeekList();
        weekList = findViewById(R.id.week_list);//绑定
        weekList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedDay = position;//记录选中的哪一天，用来标识，与未选中的区分
                weekAdapter.notifyDataSetChanged();//通知刷新
                changeClassTable();//创建课表
            }
        });
        tableList = findViewById(R.id.table_list);//绑定
        weekAdapter = new CommonAdapter<String>(this,weeks,R.layout.layout_week_item) {//创建适配器
            @Override
            public void convert(ViewHolder helper, String item, int postion) {//设置weekListView每一行的显示样式
                TextView textView =  helper.getView(R.id.week_day);
                textView.setText(weeks.get(postion));//设置文字
                textView.setSelected(selectedDay == postion);//设置是否选中
            }
        };
        weekList.setAdapter(weekAdapter);//赋值适配器
        loadCourseData();//请求课程表
    }

    private void loadCourseData() {
        LoadCourseTableTask loadCourseTableTask = new LoadCourseTableTask(taskListener,LoadCourceTableResult.class);//创建课程表的网络请求
        loadCourseTableTask.setParam(courseId+"");//给请求加参数
        loadCourseTableTask.execute();
    }

    private TaskListener<LoadCourceTableResult> taskListener = new TaskListener<LoadCourceTableResult>() {
        @Override
        public void onTaskStart(TaskListener<LoadCourceTableResult> listener) {

        }

        @Override
        public void onTaskComplete(TaskListener<LoadCourceTableResult> listener, LoadCourceTableResult result, Exception e) {
             if (result != null && result.isSuccess()){
                 mCourseList = result.result;//请求的结果付给所有的课程表
                if (courseAdapter != null){
                    selectCourseInfo.clear();//清除缓存
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
             selectCourseInfo = mCourseList.get(selectedDay);//从所有课表的集合中取出选中当天的课程列表
         }catch (Exception e){
              selectCourseInfo = new ArrayList<>();
         }
         courseAdapter.notifyDataSetChanged();//通知更新
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
