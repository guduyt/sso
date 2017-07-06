package com.sso.business.base;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.google.common.collect.Sets;

/**
 * Created by yt on 2017-7-6.
 */
@Configuration
@Controller
public class WebConfigBeans {

	@Autowired
	private RequestMappingHandlerAdapter handlerAdapter;

	/**
	 * 增加字符串转日期的功能
	 */

	@PostConstruct
	public void initEditableValidation() {


		ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) handlerAdapter.getWebBindingInitializer();
		if (initializer.getConversionService() != null) {
			GenericConversionService genericConversionService = (GenericConversionService) initializer.getConversionService();
			genericConversionService.addConverter(new StringToDateConverter());
		}
	}
}
