package com.sso.yt.interceptor.exception;

import com.alibaba.fastjson.JSON;
import com.sso.business.base.JsonResult;
import com.sso.yt.commons.exceptions.BaseException;
import com.sso.yt.commons.exceptions.ValidateException;
import com.sso.yt.commons.utils.RequestUtils;
import com.sso.yt.commons.utils.ResponseUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerMethodExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ExceptionInterceptor
 *
 * @author yitao
 * @version 1.0.0
 * @date 2016/8/26 9:29
 */
@ControllerAdvice
public class ExceptionInterceptor extends AbstractHandlerMethodExceptionResolver implements Ordered {
    private int code = -1;
    private String message;

    @Override
    protected ModelAndView doResolveHandlerMethodException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HandlerMethod handlerMethod, Exception ex) {
        httpServletResponse.setStatus(200);
        Class exClass = ex.getClass();

        if (ClassUtils.isAssignable(exClass, BaseException.class)) { //自定义异常
            code = ((BaseException) ex).getCode();
            message = ex.getMessage();
            if(ex instanceof ValidateException){
                logger.error(ex.getMessage());
            } else {
                logger.error(ex.getMessage(), ex);
            }
        } else if(ClassUtils.isAssignable(exClass, MethodArgumentNotValidException.class) || ClassUtils.isAssignable(exClass, BindException.class)){ //参数校验错误
            validExceptionHandle(ex);
        }else {
            otherExceptionHandle(ex);
        }

        return this.handleResponse(httpServletRequest, httpServletResponse, handlerMethod, code, message);
    }

    private void validExceptionHandle(Exception ex){
        message="参数校验错误！";
        BindingResult bindingResult;
        if (ex instanceof MethodArgumentNotValidException) {
            bindingResult= ((MethodArgumentNotValidException)ex).getBindingResult();
        }  else {
            bindingResult= ((BindException)ex).getBindingResult();
        }
        String msg = getMessageFromBindingResult(bindingResult);
        if (StringUtils.isNotEmpty(msg)) {
            if (msg.indexOf(':') != -1) {
                String intCode = StringUtils.substringBefore(msg, ":");
                message = StringUtils.substringAfter(msg, ":");
                if (StringUtils.isNumeric(intCode)) {
                    code = Integer.valueOf(intCode);}
            } else if(StringUtils.isNumeric(msg)){
                code = Integer.valueOf(msg);
            }else {
                logger.error(ex.getMessage(), ex);
            }
        }
    }

    private void otherExceptionHandle(Exception ex){
        Class exClass = ex.getClass();
        logger.error(ex.getMessage(), ex);

        if (ClassUtils.isAssignable(exClass, IllegalArgumentException.class)) {//方法的参数错误
            message = ex.getMessage();

        } else if (ClassUtils.isAssignable(exClass, NullPointerException.class) || ClassUtils.isAssignable(exClass, ArrayIndexOutOfBoundsException.class)) { //未经初始化的对象
            message = "访问数据失败！";

        } else if (ClassUtils.isAssignable(exClass, DataAccessException.class)) { //数据库操作
            message = "操作数据库失败！";

        } else if (ClassUtils.isAssignable(exClass, ArithmeticException.class)) {//数学运算异常
            message = "数据运算失败！";

        } else if (ClassUtils.isAssignable(exClass, ClassCastException.class)) {//类型强制转换错误
            message = "数据转换失败！";

        } else {
            message = "应用程序内部未知错误,请联系开发工程师！";
        }
    }

    private ModelAndView handleResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HandlerMethod handlerMethod, int code, String message) {
        if (RequestUtils.isAjaxRequest(httpServletRequest, handlerMethod)) {
            JsonResult result=new JsonResult();
            result.setCode(code);
            result.setMessage(message);
            
            ResponseUtils.renderJson(httpServletResponse, JSON.toJSONString(result));
        } else {
            ModelAndView modelAndView = new ModelAndView("redirect:/www/error/500.html");
            modelAndView.getView();
            modelAndView.addObject("code", code);
            modelAndView.addObject("message", message);
            return modelAndView;
        }
        return null;
    }

    private String getMessageFromBindingResult(BindingResult bindingResult){
        for (ObjectError error :  bindingResult.getAllErrors()) {
            return error.getDefaultMessage();
        }
        return "";
    }
    @Override
    public int getOrder() {
        return 1;
    }
}
