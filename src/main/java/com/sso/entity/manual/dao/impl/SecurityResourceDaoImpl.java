package com.sso.entity.manual.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sso.entity.manual.dao.SecurityResourceDao;
import com.sso.entity.manual.mapper.SecurityResourceMapper;
import com.sso.entity.manual.model.SecurityResource;
import com.sso.yt.commons.constants.error.code.DaoErrorCode;
import com.sso.yt.commons.utils.Assert;

/**
 * ResourceSecurityDaoImpl
 *
 * @author yitao
 * @version 1.0.0
 * @date 2017/7/11 21:18
 */

@Repository
public class SecurityResourceDaoImpl implements SecurityResourceDao {
	private final String QUERY_TYPE = "queryType";
	private final String QUERY_KEY = "queryKey";
	private final String APP_ID = "appId";

	@Autowired
	private SecurityResourceMapper securityResourceMapper;

	@Override
	public SecurityResource queryResourcesAndRolesById(Integer id){
		return securityResourceMapper.queryResourcesAndRolesById(id);
	}

	@Override
	public SecurityResource queryResourcesAndRolesByMethod(String method){
		Map<String, Object> map = new HashedMap();
		map.put(QUERY_TYPE,"method");
		map.put(QUERY_KEY,method);

		List<SecurityResource>  list=securityResourceMapper.queryAllResourcesAndRoles(map);
		Assert.isTrue(list.size() < 2, DaoErrorCode.CODE_1002002, "根据唯一资源方法名称查询存在多条数据！");
		if (list.size() == 0)
			return null;
		return list.get(0);
	}

	@Override
	public SecurityResource queryResourcesAndRolesByResourcePath(String resourcePath){
		Map<String, Object> map = new HashedMap();
		map.put(QUERY_TYPE,"resourcePath");
		map.put(QUERY_KEY,resourcePath);
		List<SecurityResource>  list=securityResourceMapper.queryAllResourcesAndRoles(map);
		Assert.isTrue(list.size() < 2, DaoErrorCode.CODE_1002003, "根据唯一资源Url查询存在多条数据！");
		if (list.size() == 0)
			return null;
		return list.get(0);
	}

	@Override
	public List<SecurityResource> queryAllResourcesAndRolesByAppID(Integer appId){
		Map<String, Object> map = new HashedMap();
		map.put(APP_ID,appId);
		return securityResourceMapper.queryAllResourcesAndRoles(map);
	}

	@Override
	public List<SecurityResource> queryAllResourcesAndRoles(){
		Map<String, Object> map = new HashedMap();
		return securityResourceMapper.queryAllResourcesAndRoles(map);
	}

}
