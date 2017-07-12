package com.sso.entity.manual.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sso.entity.manual.model.SecurityUser;

/**
 * SecurityUserMapper
 *
 * @author yitao
 * @version 1.0.0
 * @date 2017/7/5 23:22
 */
public interface SecurityUserMapper {

    SecurityUser queryUserAndRoles(Map map);

    SecurityUser queryUserAndRolesById(@Param("id") Long userId);

    List<SecurityUser> queryAllUserAndRoles(Map map);

}
