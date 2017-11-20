package com.sso.business.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

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
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

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
						"/login.html");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/login").anonymous()
				.anyRequest().authenticated()
				.and().addFilterBefore(getSecurityInterceptor(),FilterSecurityInterceptor.class)
				.formLogin()
				.usernameParameter("username").passwordParameter("password")
				.loginPage("/login.html").loginProcessingUrl("/login")
				.successHandler(getLoginSuccessHandler()).failureHandler(getLoginfailureHandler()) .permitAll()
				.and()
				.logout()
				.logoutUrl("/logout").permitAll()
				.logoutSuccessUrl("/login.html")
				.invalidateHttpSession(true).deleteCookies("JSESSIONID")
				.and()
				.rememberMe()
				.rememberMeParameter("rememberMe").tokenRepository(getTokenRepository())
				.and()
				.sessionManagement().maximumSessions(1).expiredUrl("/login.html")
				.and().invalidSessionUrl("/login.html").sessionFixation().migrateSession()
				.and().csrf().disable();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.authenticationEventPublisher(authenticationEventPublisher())
				.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
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
	protected AuthenticationManager authenticationManager() throws Exception {
		ProviderManager authenticationManager = new ProviderManager(Arrays.asList(daoAuthenticationProvider()));
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


	@Bean
	public SecurityFilter getSecurityInterceptor(){
		SecurityFilter securityFilter=new SecurityFilter();
		securityFilter.setSecurityMetadataSource(securityMetadataSource);
		securityFilter.setAccessDecisionManager(accessDecisionManager);
		return securityFilter;
	}

	private AuthenticationSuccessHandler getLoginSuccessHandler(){
		return new SimpleUrlAuthenticationSuccessHandler(DEFAULT_HOMe);
	}

	private AuthenticationFailureHandler getLoginfailureHandler(){
		return new SimpleUrlAuthenticationFailureHandler(DEFAULT_LOGIN+"?error");
	}


	private PersistentTokenRepository getTokenRepository(){
		JdbcTokenRepositoryImpl tokenRepository= new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		return tokenRepository;
	}

}
