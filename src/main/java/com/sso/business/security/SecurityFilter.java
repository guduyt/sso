package com.sso.business.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * SecurityFilter
 *
 * @author yitao
 * @version 1.0.0
 * @date 2017/7/11 23:51
 */
@Service("securityFilter")
public class SecurityFilter extends AbstractSecurityInterceptor implements Filter {

	@Autowired
	@Qualifier("securityMetadataSourceImpl")
	private SecurityMetadataSource securityMetadataSource;

	@Autowired
	@Qualifier("securityAccessDecisionManagerImpl")
	private AccessDecisionManager accessDecisionManager;

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager ;

	@PostConstruct
	public void init(){
		super.setAccessDecisionManager(accessDecisionManager);
		super.setAuthenticationManager(authenticationManager);
	}


	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}

	@Override
	public Class<?> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		FilterInvocation fi = new FilterInvocation(servletRequest, servletResponse, filterChain);

		
		InterceptorStatusToken token = super.beforeInvocation(fi);

		try {
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			super.afterInvocation(token,null);
		}


	}

	@Override
	public void destroy() {

	}
}
