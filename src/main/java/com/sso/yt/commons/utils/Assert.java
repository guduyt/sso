package com.sso.yt.commons.utils;

import com.sso.yt.commons.constants.error.code.ErrorCode;
import com.sso.yt.commons.exceptions.ValidateException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yt on 2017-7-7.
 */
public class Assert {
	private static final String DEFAULT_MESSAGE = "target can't be null";
	public static final Map<Integer, String> map = new HashMap<>();

	private Assert() {
	}

	private static void error(int code, String error) {
		throw new ValidateException(code == 0 ? ErrorCode.DEFAULT : code, error == null ? DEFAULT_MESSAGE : error);
	}

	public static void notEmpty(Object obj) {
		notEmpty(obj, ErrorCode.DEFAULT, DEFAULT_MESSAGE);
	}

	public static void notEmpty(Object obj, String error) {
		notEmpty(obj, ErrorCode.DEFAULT, error);
	}

	public static void notEmpty(Object obj, int code) {
		notEmpty(obj, code, DEFAULT_MESSAGE);
	}

	@SuppressWarnings("rawtypes")
	public static void notEmpty(Object obj, int code, String error) {
		if (obj == null)
			error(code, error);
		if (obj instanceof List && ((List) obj).isEmpty()) {
				error(code, error);
		}
		if (obj instanceof String && ((String) obj).length() == 0) {
			error(code, error);
		}
	}

	@SuppressWarnings("rawtypes")
	public static void isEmpty(Object obj, int code, String error) {
		if (obj != null) {
			if (obj instanceof List) {
				if (!((List) obj).isEmpty())
					error(code, error);
			} else if (!(obj instanceof String && ((String) obj).length() == 0)) {
				error(code, error);
			}
		}
	}

	public static void notNull(Object obj) {
		notNull(obj, ErrorCode.DEFAULT, DEFAULT_MESSAGE);
	}

	public static void notNull(Object obj, String error) {
		notNull(obj, ErrorCode.DEFAULT, error);
	}

	public static void notNull(Object obj, int code) {
		notNull(obj, code, DEFAULT_MESSAGE);
	}

	public static void isNull(Object obj, int code, String error) {
		if (obj != null)
			error(code, error);
	}

	public static void notNull(Object obj, int code, String error) {
		if (obj == null)
			error(code, error);
	}

	public static void isTrue(boolean flag, String error) {
		isTrue(flag, ErrorCode.DEFAULT, error == null ? DEFAULT_MESSAGE : error);
	}

	public static void isTrue(boolean flag, int code, String error) {
		if (!flag)
			error(code, error == null ? DEFAULT_MESSAGE : error);
	}

	public static void isFalse(boolean flag, int code, String error) {
		if (flag)
			error(code, error == null ? DEFAULT_MESSAGE : error);
	}
}
