package com.sso.business.base;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Set;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sso.entity.auto.dao.SysResourceDao;
import com.sso.entity.auto.model.SysResource;
import com.sso.yt.commons.constants.CommonConstant;
import com.sso.yt.commons.utils.UnderlineToCamelUtils;

/**
 * Created by yt on 2017-7-6.
 */
@Component
public class ResourceScanUtils implements InitializingBean {

	private static Logger logger = LoggerFactory.getLogger(ResourceScanUtils.class);
	private static final String BACK_AGE = "com.sso.business";
	private final String defaultMethod="ALL";

	@Autowired
	private SysResourceDao sysResourceDao;

	@Override
	public void afterPropertiesSet() throws Exception {
		if("dev".equals(System.getProperty(CommonConstant.ACTIVE_PROFILES))){
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					scanAllController();
				}

			});
			thread.setDaemon(true);
			thread.run();
		}
	}

	private void scanAllController() {
		Reflections reflections = new Reflections(BACK_AGE);
		Set<Class<? extends BaseSpringController>> set = reflections.getSubTypesOf(BaseSpringController.class);
		for (Class<?> controller : set) {
			execute(controller);
		}
	}

	private void execute(Class<?> controller) {
		Method[] methods = controller.getDeclaredMethods();
		for (Method method : methods) {
			try {
				methodHandle(method);
			} catch (Exception e) {
				logger.error(method.toString(), e);
			}
		}
	}

	private void methodHandle(Method method) {
		RequestMapping controllerRequestMapping = method.getDeclaringClass().getAnnotation(RequestMapping.class);
		RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);

		SysResource sysResource = new SysResource();
		getResource(controllerRequestMapping, method, sysResource);
		SysResource queryData = sysResourceDao.queryByResourcePath(sysResource.getResourcePath());
		if (Objects.isNull(queryData)) {
			sysResourceDao.insertSelective(sysResource);
		} else {
			sysResource.setId(queryData.getId());
			sysResourceDao.updateByPrimaryKeySelective(sysResource);
		}
	}


	private void getResource(RequestMapping controllerRequestMapping,Method method,SysResource sysResource) {
		String methodUrl="/";
		String requestMethod=defaultMethod;
		String controllerUrl=getRequestMappingUrl(controllerRequestMapping);

		RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
		if (Objects.nonNull(requestMapping)) {

			RequestMethod[] methods = requestMapping.method();
			if (methods.length > 0) {
				requestMethod=methods[0].toString();
			}
			methodUrl=getRequestMappingUrl(requestMapping);
		}
		{
			GetMapping mapping = method.getAnnotation(GetMapping.class);
			if (Objects.nonNull(mapping)) {
				requestMethod = RequestMethod.GET.toString();

				String[] values = mapping.value();
				methodUrl = values.length > 0 ? values[0] : "/";
			}
		}
		{
			PostMapping mapping = method.getAnnotation(PostMapping.class);
			if (Objects.nonNull(mapping)) {
				requestMethod = RequestMethod.POST.toString();

				String[] values = mapping.value();
				methodUrl = values.length > 0 ? values[0] : "/";
			}
		}
		{
			PutMapping mapping = method.getAnnotation(PutMapping.class);
			if (Objects.nonNull(mapping)) {
				requestMethod = RequestMethod.PUT.toString();

				String[] values = mapping.value();
				methodUrl = values.length > 0 ? values[0] : "/";
			}
		}
		{
			PatchMapping mapping = method.getAnnotation(PatchMapping.class);
			if (Objects.nonNull(mapping)) {
				requestMethod = RequestMethod.PATCH.toString();

				String[] values = mapping.value();
				methodUrl = values.length > 0 ? values[0] : "/";
			}
		}
		{
			DeleteMapping mapping = method.getAnnotation(DeleteMapping.class);
			if (Objects.nonNull(mapping)) {
				requestMethod = RequestMethod.DELETE.toString();

				String[] values = mapping.value();
				methodUrl = values.length > 0 ? values[0] : "/";
			}
		}
		sysResource.setRequestPath(getUrl(controllerUrl,methodUrl));
		sysResource.setRequestType(requestMethod);
		sysResource.setJavaClass(String.valueOf(method.getDeclaringClass()));
		sysResource.setShortMethod(method.getName());
		sysResource.setMethod(method.toString());
		sysResource.setResourcesName(sysResource.getRequestPath());
		sysResource.setResourcePath(getResourcesPath(sysResource.getRequestPath(),sysResource.getRequestType()));
	}

	private String getRequestMappingUrl(RequestMapping requestMapping){
		String[] values = requestMapping.value();
		if (values.length > 0 &&  values[0].length()!=0) {
			return values[0];
		}
		return "/";
	}

	private String getUrl(String controllerUrl,String methodUrl) {
		StringBuilder stringBuilder = new StringBuilder();

		   if(!controllerUrl.startsWith("/")){
			   stringBuilder.append("/");
		   }
		stringBuilder.append(controllerUrl);
		if (!controllerUrl.endsWith("/") && !methodUrl.startsWith("/"))
			stringBuilder.append("/");
		if (controllerUrl.endsWith("/") && methodUrl.startsWith("/")) {
			stringBuilder.append(methodUrl.substring(1));
		}else {
			stringBuilder.append(methodUrl);
		}
		return stringBuilder.toString();
	}

	private String getResourcesPath(String requestPath,String requestType){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(requestType).append("[").append(urlConversion(requestPath)).append("]");
		return  stringBuilder.toString();
	}
	private String urlConversion(String str) {

		str = str.replace("_", "@");
		return UnderlineToCamelUtils.parseToUnderline(str);

	}
}
