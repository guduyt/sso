package com.sso.business.security.listener;


import com.sso.yt.commons.utils.LogUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

/**
 * Created by yt on 2016-11-03.
 */
@Component
public class FailureEventListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent authenticationFailureBadCredentialsEvent) {

        WebAuthenticationDetails auth = (WebAuthenticationDetails) authenticationFailureBadCredentialsEvent.getAuthentication().getDetails() ;
        LogUtils.LOGGER.info("登录失败"+auth!=null?auth.toString():"",authenticationFailureBadCredentialsEvent.getException());
    }
}