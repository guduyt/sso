package com.sso.business.base;

import java.io.Serializable;

/**
 * Created by yt on 2017-7-6.
 */
public class JsonResult <T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private int code = 0;
	private String message;
	private T data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}