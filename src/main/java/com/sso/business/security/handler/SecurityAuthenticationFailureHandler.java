package com.sso.business.security.handler;

import com.alibaba.fastjson.JSON;
import com.sso.business.base.JsonResult;
import com.sso.yt.commons.utils.HostUtils;
import com.sso.yt.commons.utils.RequestUtils;
import com.sso.yt.commons.utils.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * SecurityAuthenticationFailureHandler
 *
 * @author yitao
 * @version 1.0.0
 * @date 2018/6/2 15:22
 */
public class SecurityAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private String failureUrl;
    
    public SecurityAuthenticationFailureHandler(String defaultFailureUrl){
        super(defaultFailureUrl);
        this.failureUrl=defaultFailureUrl;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {

        logger.info("登录失败,ip={},用户名={}", HostUtils.getIpAddress(request),request.getParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY));
        if(RequestUtils.isAjaxRequest(request)){
            JsonResult result=new JsonResult();
            result.setCode(-1);
            result.setMessage(exception.getMessage());
            ResponseUtils.renderJson(response, JSON.toJSONString(result));
        }
        else {
            super.setDefaultFailureUrl(this.failureUrl+"="+URLEncoder.encode(exception.getMessage()));
            super.onAuthenticationFailure(request,response,exception);
        }


    }
    
}
