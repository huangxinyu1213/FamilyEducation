package com.zhy.http.okhttp.requestBase;

/**
 * Created by xhma on 16-8-3.
 */
public interface TaskListener <T>{
	void onTaskStart(TaskListener<T> listener);
	void onTaskComplete(TaskListener<T> listener, T result, Exception e);
}
