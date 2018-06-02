package com.sso.business.security.handler;

import com.alibaba.fastjson.JSON;
import com.sso.business.base.JsonResult;
import com.sso.yt.commons.utils.RequestUtils;
import com.sso.yt.commons.utils.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * SecurityAccessDeniedHandler
 *
 * @author yitao
 * @version 1.0.0
 * @date 2018/6/2 11:11
 */
public class SecurityAccessDeniedHandler implements AccessDeniedHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private String errorPage;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
      if(RequestUtils.isAjaxRequest(request)){
          JsonResult result=new JsonResult();
          result.setCode(-1);
          result.setMessage(accessDeniedException.getMessage());
          ResponseUtils.renderJson(response, JSON.toJSONString(result));
      }else if (!response.isCommitted()) {
          if (errorPage != null) {
              // Put exception into request scope (perhaps of use to a view)
              request.setAttribute(WebAttributes.ACCESS_DENIED_403,
                      accessDeniedException);

              // Set the 403 status code.
              response.setStatus(HttpServletResponse.SC_FORBIDDEN);

              // forward to error page.
              RequestDispatcher dispatcher = request.getRequestDispatcher(errorPage);
              dispatcher.forward(request, response);
          }
          else {
              response.sendError(HttpServletResponse.SC_FORBIDDEN,
                      accessDeniedException.getMessage());
          }
      }

    }

    public void setErrorPage(String errorPage) {
        if ((errorPage != null) && !errorPage.startsWith("/")) {
            throw new IllegalArgumentException("errorPage must begin with '/'");
        }

        this.errorPage = errorPage;
    }
}
