package com.sso.entity.manual.dao;

import com.sso.entity.manual.model.SecurityUser;

import java.util.List;

/**
 * SecurityUserDao
 *
 * @author yitao
 * @version 1.0.0
 * @date 2017/7/8 0:19
 */
public interface SecurityUserDao {

    List<SecurityUser> queryUserAndRoleByUserName(String userName);

    List<SecurityUser> queryUserAndRoleByMobile(String mobile);

    List<SecurityUser> queryUserAndRoleByEmail(String email);

}
