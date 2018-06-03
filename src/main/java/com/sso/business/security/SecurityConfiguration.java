package com.sso.business.security;

import com.sso.business.security.filter.SecurityFilter;
import com.sso.business.security.filter.UsernamePasswordCodeAuthenticationFilter;
import com.sso.business.security.handler.SecurityAccessDeniedHandler;
import com.sso.business.security.handler.SecurityAuthenticationFailureHandler;
import com.sso.business.security.handler.SecurityAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.BasePasswordEncoder;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yt on 2017-11-18 10:03.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final static String DEFAULT_HOMe="/index.html";
	private final static String DEFAULT_LOGIN="/login.html";

	private static List<String> ignoreUrls=new ArrayList<>();
	

	@Autowired
	private DataSource dataSource;


	@Autowired
	@Qualifier("securityAccessDecisionManagerImpl")
	private AccessDecisionManager accessDecisionManager;

	@Autowired
	@Qualifier("securityMetadataSourceImpl")
	private SecurityMetadataSource securityMetadataSource;


	@Autowired
	@Qualifier("securityUserDetailsServiceImpl")
	private UserDetailsService userDetailsService;

	private void init(){
		ignoreUrls.add("/**/*.css");
		ignoreUrls.add("/**/*.css.map");
		ignoreUrls.add("/**/*.jpg");
		ignoreUrls.add("/**/*.jpeg");
		ignoreUrls.add("/**/*.gif");
		ignoreUrls.add("/**/*.png");
		ignoreUrls.add("/**/*.js");
		ignoreUrls.add("/**/*.ico");
		ignoreUrls.add("/login");
		ignoreUrls.add("/login/imageCode");
		ignoreUrls.add("/login.html");
		ignoreUrls.add("/index.html");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
				.antMatchers(
						"/**/*.js",
						"/**/*.css",
						"/**/*.css.map",
						"/**/*.gif",
						"/**/*.jpeg",
						"/**/*.ico",
						"/**/*.jpg",
						"/**/*.png",
						"/index.html",
						"/login.html",
						"/loginImageCode.html",
						"/login/imageCode");
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
				.authorizeRequests()
				.antMatchers("/login").permitAll()
				.anyRequest().authenticated()
				.and().apply(getCodeSecurityConfig())
				.and().addFilterBefore(getSecurityInterceptor(),FilterSecurityInterceptor.class)
				.formLogin()
				.usernameParameter("username").passwordParameter("password")
				.loginPage("/login.html").loginProcessingUrl("/login")
				.successHandler(getLoginSuccessHandler()).failureHandler(getLoginFailureHandler()) .permitAll()
				.and()
				.logout()
				.logoutUrl("/logout").permitAll()
				.logoutSuccessUrl("/login.html")
				.invalidateHttpSession(true).deleteCookies("JSESSIONID")
				.and()
				.rememberMe()
				.rememberMeParameter("rememberMe").tokenRepository(getTokenRepository()).userDetailsService(userDetailsService())
				.and()
				.sessionManagement().maximumSessions(1).expiredUrl("/login.html")
				.and().invalidSessionUrl("/login.html").sessionFixation().migrateSession()
				.and().csrf().disable()
				.anonymous().disable();
		  http
				.exceptionHandling()
				.accessDeniedHandler(getSecurityAccessDeniedHandler());

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.authenticationProvider(daoAuthenticationProvider())
				.authenticationEventPublisher(authenticationEventPublisher())
				.eraseCredentials(false);
	}

	@Bean
	public AuthenticationEventPublisher authenticationEventPublisher() {
		return new DefaultAuthenticationEventPublisher();
	}


	@Bean
	public BasePasswordEncoder getPasswordEncoder(){
		   return   new Md5PasswordEncoder();
	}

	@Bean
	public SaltSource getSaltSource(){
		    ReflectionSaltSource saltSource=new ReflectionSaltSource();
		    saltSource.setUserPropertyToUse("username");
		    return saltSource;
	}


	@Override
	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		ProviderManager authenticationManager = new ProviderManager(Arrays.asList(daoAuthenticationProvider()));
		authenticationManager.setAuthenticationEventPublisher(authenticationEventPublisher());
		authenticationManager.setEraseCredentialsAfterAuthentication(false);
		return authenticationManager;
	}


	private DaoAuthenticationProvider daoAuthenticationProvider(){
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
		/*daoAuthenticationProvider.setSaltSource(getSaltSource());
		daoAuthenticationProvider.setHideUserNotFoundExceptions(false);*/
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		return daoAuthenticationProvider;
	}


	@Override
	protected UserDetailsService userDetailsService() {
		return userDetailsService;
	}

	/*public OAuth2ClientAuthenticationProcessingFilter getOauth(){
		OAuth2ClientAuthenticationProcessingFilter oAuth2ClientAuthenticationProcessingFilter=new OAuth2ClientAuthenticationProcessingFilter("/login");
		return oAuth2ClientAuthenticationProcessingFilter;
	}*/

	@Bean
	public SecurityFilter getSecurityInterceptor(){
		SecurityFilter securityFilter=new SecurityFilter();
		securityFilter.setSecurityMetadataSource(securityMetadataSource);
		securityFilter.setAccessDecisionManager(accessDecisionManager);
		return securityFilter;
	}

	private AuthenticationSuccessHandler getLoginSuccessHandler(){
		return new SecurityAuthenticationSuccessHandler(DEFAULT_HOMe);
	}

	private AuthenticationFailureHandler getLoginFailureHandler(){
		return new SecurityAuthenticationFailureHandler(DEFAULT_LOGIN+"?error");
	}

	private AccessDeniedHandler getSecurityAccessDeniedHandler(){
		return new SecurityAccessDeniedHandler();
	}


	private PersistentTokenRepository getTokenRepository(){
		JdbcTokenRepositoryImpl tokenRepository= new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		return tokenRepository;
	}

	@Bean
	public CodeSecurityConfig getCodeSecurityConfig(){
		return new  CodeSecurityConfig();
	}
	class CodeSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
		@Override
		public void configure(HttpSecurity http) throws Exception {
			UsernamePasswordCodeAuthenticationFilter codeAuthenticationFilter=new UsernamePasswordCodeAuthenticationFilter();
			codeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
			codeAuthenticationFilter.setAuthenticationSuccessHandler(getLoginSuccessHandler());
			codeAuthenticationFilter.setAuthenticationFailureHandler(getLoginFailureHandler());
			codeAuthenticationFilter.setRememberMeServices(http.getSharedObject(RememberMeServices.class));

			http.authenticationProvider(daoAuthenticationProvider())
					.addFilterAfter(codeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		}
	}

}
