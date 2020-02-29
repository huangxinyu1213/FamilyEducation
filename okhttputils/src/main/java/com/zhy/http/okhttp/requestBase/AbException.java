package com.zhy.http.okhttp.requestBase;

/**
 * Created by xhma on 16-8-19.
 */
public class AbException extends Exception{
	private static final long serialVersionUID = 1L;

	public String mExtra;
	public int mStatus;

	public AbException(String message) {
		super(message);
	}

	public AbException(int status, String message) {
		super(message);

		mStatus = status;
	}

	public AbException(String message, String extra) {
		super(message);
		mExtra = extra;
	}
}
