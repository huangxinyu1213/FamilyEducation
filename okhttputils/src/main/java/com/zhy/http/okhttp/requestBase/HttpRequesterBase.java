package com.zhy.http.okhttp.requestBase;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.utils.Exceptions;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by xhma on 16-8-3.
 */
public abstract class HttpRequesterBase <T extends HttpResult>{
	protected TaskListener<T> mTaskListener;
	private final Class<T> mResultClassType;
	private String mUrl;
	private HashMap<String,String> mParams = new HashMap<>();
	private HashMap<String,String> mHeader;
	private long mRequestTag;

	public HttpRequesterBase(TaskListener<T> taskListener,Class<T> mResultClassType) {
		this.mTaskListener = taskListener;
		this.mResultClassType = mResultClassType;
	}

	public void execute(){
		initUrl();
		initParam();
		initHeader();

		mRequestTag = System.currentTimeMillis();
		if (null != mTaskListener){
			mTaskListener.onTaskStart(mTaskListener);
		}
		String method = getMethod();
		if (null == method || "".equals(method)){
			throw new IllegalArgumentException("method can not be null");
		}
		Log.i("info",mUrl);
		Log.i("info",mHeader.toString());
		switch (method){
			case "GET":
				doGet();
				break;
			case "POST":
				doPost();
				break;
			case "DELETE":
				doDelete();
				break;
			case "PUT":
				doPut();
				break;
		}
	}

	public void executePostFile(String name,String fileName,File file){
		initUrl();
		initParam();
		initHeader();
		if (null != mTaskListener){
			mTaskListener.onTaskStart(mTaskListener);
		}
		postFile(name, fileName, file);
	}

	public void executePostFile(String name,String fileName,byte[] file){
		initUrl();
		initParam();
		initHeader();
		if (null != mTaskListener){
			mTaskListener.onTaskStart(mTaskListener);
		}
		postFile(name, fileName, file);
	}


	private void initUrl() {
		mUrl = getHost() + getPath();
	}

	protected abstract String getHost();

	protected abstract String getPath();

	private void initParam() {
		addParam(mParams);
	}

	protected abstract void addParam(HashMap<String,String> params);

	private void initHeader(){
		mHeader = new HashMap<String,String>();
		setHeader(mHeader);
	}

	protected abstract void setHeader(HashMap<String,String> headers);

	protected abstract String getMethod();

	private Callback<T> callBack = new Callback<T>() {
		@Override
		public T parseNetworkResponse(Response response, int i) throws Exception {
			String str = response.body().string();
			Log.i("info","body:"+str);
			T t = new Gson().fromJson(str,mResultClassType);
			/*if (response.isSuccessful()) {
				if (null != t && TextUtils.isEmpty(t.getmState())) {
					t.setmState(response.code() + "");
				}
			}*/
			Log.i("info",response.headers().toString());
			Log.i("info","code:"+response.code());
			return t;
		}

		@Override
		public void onError(Call call, Exception e, int i) {
			//网络异常或者取消请求
			if (null == mTaskListener){
				return;
			}
			mTaskListener.onTaskComplete(mTaskListener, null, e);
		}

		@Override
		public void onResponse(T response, int i) {
			//Log.i("info","responseCode:"+response.getmState());
			if (mTaskListener != null) {
				if (null == response){
					mTaskListener.onTaskComplete(mTaskListener,null,new IOException(""));
					return;
				}

				AbException exception = null;
				// 当前业务类的异常
				if (!response.isSuccess()) {
					int status = 0;
					try {
						status = Integer.parseInt(response.getmState());
					}catch (Exception e){
						status = 100;
					}
					exception = new AbException(status, response.getErrMessage());
				}
				mTaskListener.onTaskComplete(mTaskListener, response, exception);
			}
		}
	};

	public void cancel() {
		mTaskListener = null;
		OkHttpUtils.getInstance().cancelTag(mRequestTag);
	}

	private void doGet(){
		OkHttpUtils.get()
				.headers(mHeader)
				.url(mUrl)
				.params(mParams)
				.tag(mRequestTag)
				.build()
				.execute(callBack);
	}


	private void doPost(){
		OkHttpUtils.post()
				.headers(mHeader)
				.url(mUrl)
				.params(mParams)
				.tag(mRequestTag)
				.build()
				.execute(callBack);
	}

	private void doPut(){
		OkHttpUtils.put()
				.headers(mHeader)
				.url(mUrl)
				.requestBody(generateRequestBody())
				.tag(mRequestTag)
				.build()
				.execute(callBack);
	}

	private void doPatch(){
		OkHttpUtils.patch()
				.headers(mHeader)
				.url(mUrl)
				.requestBody(generateRequestBody())
				.tag(mRequestTag)
				.build()
				.execute(callBack);
	}

	private void doDelete(){
		OkHttpUtils.delete()
				.headers(mHeader)
				.url(mUrl)
				.requestBody(generateRequestBody())
				.tag(mRequestTag)
				.build()
				.execute(callBack);
	}

	//基于POST的文件上传（类似web上的表单）

	private void postFile(String name,String fileName,File file){
		OkHttpUtils.post()
				.addFile(name,fileName,file)
				.url(mUrl)
				.params(mParams)
				.headers(mHeader)
				.tag(mRequestTag)
				.build()
				.execute(callBack);
	}

	private void postFile(String name,String fileName,byte[] file){
		OkHttpUtils.postByte()
				.addFile(name,fileName,file)
				.url(mUrl)
				.params(mParams)
				.headers(mHeader)
				.tag(mRequestTag)
				.build()
				.connTimeOut(7000)
				.writeTimeOut(7000)
				.readTimeOut(7000)
				.execute(callBack);
	}

	private RequestBody generateRequestBody(){
		FormBody.Builder builder = new FormBody.Builder();
		Iterator iterator = mParams.keySet().iterator();
		while (iterator.hasNext()){
			String key = (String)iterator.next();
			String value = mParams.get(key);
			Log.i("value",value+"");
			builder.add(key,mParams.get(key));
		}
		return builder.build();
	}

	public String initUrl(String url, List<AbNameValuePair> params) {
		return params == null ? url : (url + "?" + getParams(params));
	}

	private String getParams(List<AbNameValuePair> nameValuePairs) {
		List<AbNameValuePair> list = stripNulls(nameValuePairs);
		return format(list, "UTF-8");
	}

	private List<AbNameValuePair> stripNulls(List<AbNameValuePair> nameValuePairs) {
		List<AbNameValuePair> params = null;
		if (nameValuePairs != null) {
			params = new ArrayList<AbNameValuePair>();
			for (AbNameValuePair param : nameValuePairs) {
				if (param.getValue() != null) {
					params.add(param);
				}
			}
		}
		return params;
	}

	private  String format(final List<? extends AbNameValuePair> parameters,
								 final String encoding) {
		String s = "";
		if (parameters != null && encoding != null) {
			StringBuilder sb = new StringBuilder();
			for (AbNameValuePair valuePair : parameters){
				sb.append(URLEncoder.encode(valuePair.getName())+"=")
						.append(URLEncoder.encode(valuePair.getValue())+"&");
			}
			sb.deleteCharAt(sb.lastIndexOf("&"));
			s = sb.toString();
		}
		return s.replace("+", "%20");
	}


}
