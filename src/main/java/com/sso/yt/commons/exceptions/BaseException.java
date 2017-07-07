package com.sso.yt.commons.exceptions;

import com.sso.yt.commons.utils.MessageUtils;

/**
 * BaseException
 * 自定义异常基类
 *
 * @author yitao
 * @version 1.0.0
 * @date 2016/8/26 10:44
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID =-1;
    private static final String defaultMessage="应用异常，请联系相关工程师";
    /*异常的错误码*/
    private int code;


    public BaseException() {
        super(defaultMessage);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(int code) {
        super(defaultMessage);
        this.code = code;
    }

    public BaseException(int code, String message) {
        super(MessageUtils.generate(code, message));
        this.code = code;
    }


    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(int code, String message, Throwable cause) {
        super(MessageUtils.generate(code, message), cause);
        this.code = code;
    }


    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(int code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    @Override
    public String getMessage() {
        String message = getLocalizedMessage();
        StringBuilder s = new StringBuilder();
        s.append(getClass().getName());
        s.append(":");
        s.append(getCode());
        if (message != null) {
            s.append(", ");
            s.append(message);
        }
        if (super.getCause() != null && super.getCause().getMessage() != null) {
            s.append(";").append(super.getCause().getMessage());
        }
        return s.toString();
    }

    @Override
    public String getLocalizedMessage() {
        return super.getMessage();
    }

}
