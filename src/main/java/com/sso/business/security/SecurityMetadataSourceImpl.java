package com.sso.business.security;

import com.sso.entity.manual.dao.SecurityResourceDao;
import com.sso.entity.manual.model.SecurityResource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * SecurityMetadataSourceImpl
 *
 * @author yitao
 * @version 1.0.0
 * @date 2017/7/11 23:49
 */
@Service("securityMetadataSourceImpl")
public class SecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {

	@Autowired
	private SecurityResourceDao securityResourceDao;

	private static List<SecurityResource> list=new ArrayList<>();
	private RequestMatcher requestMatcher;
	private SessionRegistry sessionRegistry;


	@Override
	public Collection<ConfigAttribute> getAttributes(Object object){
		if (CollectionUtils.isEmpty(list))
			 loadResource();
		Collection<ConfigAttribute> collection = new ArrayList<>();
		HttpServletRequest request = ((FilterInvocation) object).getRequest();
		Iterator<SecurityResource> iterator=list.iterator();
		while (iterator.hasNext()) {
			RequestMatcher requestMatcherRole;
			SecurityResource securityResource=iterator.next();
			if("ALL".equals(securityResource.getRequestType())){
				 requestMatcherRole = new AntPathRequestMatcher(securityResource.getRequestPath());
			}else{
				 requestMatcherRole = new AntPathRequestMatcher(securityResource.getRequestPath(),securityResource.getRequestType());
			}
			if (requestMatcherRole.matches(request)  ) {
				//获得该uri所需要的角色列表
				collection.addAll(securityResource.getSecurityRoles());
			}

		}
		/*if (collection.size() == 0) {
			collection.add(new ConfigAttribute() {
				@Override
				public String getAttribute() {
					return "ROLE_NO_USER";
				}
			});
		}*/
		return collection;
	}


	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	public void loadResource() {
		list= securityResourceDao.queryAllResourcesAndRoles();
	}



	public void refresh() {
		list.clear();
		loadResource();
	}

}
