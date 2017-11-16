package com.sso.business.security;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * SecurityConfig
 * 使用java代码配置替换web.xml的Security配置 ，并且配置CharacterEncodingFilter，解决请求参数中文乱码问题。
 * @author yitao
 * @version 1.0.0
 * @date 2017/7/28 22:20
 */
@Configuration
public class SecurityFilterChainConfig extends AbstractSecurityWebApplicationInitializer {
    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext)
    {
        FilterRegistration.Dynamic characterEncodingFilter = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
        characterEncodingFilter.setInitParameter("encoding", "UTF-8");
        characterEncodingFilter.setInitParameter("forceEncoding", "true");
        characterEncodingFilter.addMappingForUrlPatterns(null, false, "/*");
    }
}
