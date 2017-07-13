package com.sso.business.base;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by yt on 2017-7-6.
 */
@Configuration
public class WebConfigBeans extends WebMvcConfigurerAdapter {


	/**
	 * 增加字符串转日期的功能
	 */
	@Override
	public void addFormatters(FormatterRegistry registry) {
		super.addFormatters(registry);
		registry.addConverter(new StringToDateConverter());
	}
}
