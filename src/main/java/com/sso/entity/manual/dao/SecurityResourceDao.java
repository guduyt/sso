package com.sso.entity.manual.dao;

import java.util.List;

import com.sso.entity.manual.model.SecurityResource;

/**
 * ResourceSecurity
 *
 * @author yitao
 * @version 1.0.0
 * @date 2017/7/11 21:18
 */
public interface SecurityResourceDao {

	/**
	 *  根据资源id查询资源与对应角色
	 * @param id 资源id
	 * @return
	 */
	SecurityResource queryResourcesAndRolesById(Integer id);

	/**
	 *  根据资源方法查询资源与对应角色
	 * @param method 资源方法
	 * @return
	 */
	SecurityResource queryResourcesAndRolesByMethod(String method);

	/**
	 *  根据资源查询资源与对应角色
	 * @param resourcePath 资源
	 * @return
	 */
	SecurityResource queryResourcesAndRolesByResourcePath(String resourcePath);

	/**
	 *  根据应用id查询全部资源与对应角色
	 * @param appId 应用id
	 * @return
	 */
	List<SecurityResource> queryAllResourcesAndRolesByAppID(Integer appId);

	/**
	 * 查询全部资源与对应角色
	 * @return
	 */
	List<SecurityResource> queryAllResourcesAndRoles();
}
