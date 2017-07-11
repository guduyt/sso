package com.sso.entity.manual.mapper;

import com.sso.entity.manual.model.SecurityResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * ResourceSecurityMapper
 *
 * @author yitao
 * @version 1.0.0
 * @date 2017/7/8 0:46
 */
public interface ResourceSecurityMapper {
	SecurityResource queryResourcesAndRoles(Map map);

	SecurityResource queryResourcesAndRolesById(@Param("id") Integer resourcesId);

	SecurityResource queryResourcesAndRolesForGroup(Map map);

	List<SecurityResource> queryAllResourcesAndRoles(Map map);
}
