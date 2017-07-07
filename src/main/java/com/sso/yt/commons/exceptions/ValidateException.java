package com.sso.yt.commons.exceptions;

/**
 * Created by yt on 2017-7-7.
 */
public class ValidateException extends BaseException{
	private static final long serialVersionUID = -1L;

	public ValidateException(int code) {
		super(code, null, null);
	}

	public ValidateException(String message) {
		super(-1, message, null);
	}

	public ValidateException(int code, String message) {
		super(code, message, null);
	}

	@Override
	public Throwable fillInStackTrace() {
		return this;
	}

}
