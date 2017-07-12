package com.sso.entity.manual.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sso.entity.manual.model.SecurityResource;

/**
 * ResourceSecurityMapper
 *
 * @author yitao
 * @version 1.0.0
 * @date 2017/7/8 0:46
 */
public interface SecurityResourceMapper {

	SecurityResource queryResourcesAndRolesById(@Param("id") Integer resourcesId);

	List<SecurityResource> queryAllResourcesAndRoles(Map map);
}
