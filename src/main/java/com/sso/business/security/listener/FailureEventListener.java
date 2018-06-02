package com.sso.business.security.listener;


import com.sso.yt.commons.utils.LogUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

/**
 * Created by yt on 2016-11-03.
 */
@Component
public class FailureEventListener implements ApplicationListener<AbstractAuthenticationFailureEvent> {
    @Override
    public void onApplicationEvent(AbstractAuthenticationFailureEvent authenticationFailureEvent) {

        WebAuthenticationDetails auth = (WebAuthenticationDetails) authenticationFailureEvent.getAuthentication().getDetails() ;
        authenticationFailureEvent.getSource();
        LogUtils.LOGGER.info("登录失败,{},{}",auth!=null?auth.toString():"",authenticationFailureEvent.getException());
    }
}