package com.sso.business.controller;

import com.sso.business.base.BaseSpringController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * LoginController
 *
 * @author yitao
 * @version 1.0.0
 * @date 2017/7/28 23:04
 */
@RestController
@RequestMapping("/login")
public class LoginController extends BaseSpringController{
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request){
        request.getSession().invalidate();
        ModelAndView modelAndView=new ModelAndView("login");
        return modelAndView;
    }
}
