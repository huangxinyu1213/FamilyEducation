package com.zhy.http.okhttp.requestBase;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

/**
 * Created by xhma on 16-8-3.
 */
public class HttpResult implements AbType,Parcelable {
	public static String RESULT_OK = "200";
	@SerializedName("code")
	public String mState = "";
	@SerializedName("msg")
	private String errMessage;
	private String errtime;
	private String action ="";
	private String message ="";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



	public String getAction() {
		if (TextUtils.isEmpty(action)){
			return "";
		}
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}



	protected HttpResult(Parcel in) {
		mState = in.readString();
		errMessage = in.readString();
		errtime = in.readString();
		action = in.readString();
	}

	public HttpResult(){

	}

	public static final Creator<HttpResult> CREATOR = new Creator<HttpResult>() {
		@Override
		public HttpResult createFromParcel(Parcel in) {
			return new HttpResult(in);
		}

		@Override
		public HttpResult[] newArray(int size) {
			return new HttpResult[size];
		}
	};

	public boolean isSuccess() {
		return mState.equals("10000");
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	public String getErrtime() {
		return errtime;
	}

	public void setErrtime(String errtime) {
		this.errtime = errtime;
	}

	public String getmState() {
		return mState;
	}

	public void setmState(String mState) {
		this.mState = mState;
	}

	public static String getResultOk() {
		return RESULT_OK;
	}

	public static void setResultOk(String resultOk) {
		RESULT_OK = resultOk;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public boolean hasAction(){
		return (!TextUtils.isEmpty(action) && !"0".equals(action));
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mState);
		dest.writeString(errMessage);
		dest.writeString(errtime);
		dest.writeString(action);
	}
}
