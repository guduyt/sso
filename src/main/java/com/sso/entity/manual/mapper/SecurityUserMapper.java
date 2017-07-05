package com.sso.entity.manual.mapper;

import com.sso.entity.manual.model.SecurityUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * SecurityUserMapper
 *
 * @author yitao
 * @version 1.0.0
 * @date 2017/7/5 23:22
 */
public interface SecurityUserMapper {

    List<SecurityUser> queryUserAndRoles(@Param("username") String username);
    
}
