package com.sso.yt.commons.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * RequestUtils
 *
 * @author yitao
 * @version 1.0.0
 * @date 2018/6/2 10:56
 */
public final class RequestUtils {
     private RequestUtils(){
         
     }

    public static boolean isAjaxRequest(HttpServletRequest request, HandlerMethod handlerMethod) {
        if (null != handlerMethod) {
            if (null != handlerMethod.getBean().getClass().getDeclaredAnnotation(RestController.class))
                return true;
            if (null != handlerMethod.getMethodAnnotation(ResponseBody.class)) {
                return true;
            }
        }
        return isAjaxRequest(request);
    }

    public static boolean isAjaxRequest(HttpServletRequest request){
        if (request.getHeader("accept").contains("application/json"))
            return true;
        return StringUtils.isNotBlank(request.getHeader("X-Requested-With"));
    }
}
