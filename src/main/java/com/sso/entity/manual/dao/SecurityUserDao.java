package com.sso.entity.manual.dao;

import java.util.List;

import com.sso.entity.manual.model.SecurityUser;

/**
 * SecurityUserDao
 *
 * @author yitao
 * @version 1.0.0
 * @date 2017/7/8 0:19
 */
public interface SecurityUserDao {

    /**
     * 根据用户名称查询用户所有角色
     * @param userName  用户名
     * @return
     */
    SecurityUser queryUserAndRoleByUserName(String userName);

    /**
     *  根据用户名称和应用id查询用户角色
     * @param userName 用户名
     * @param appId 应用id
     * @return
     */
    SecurityUser queryUserAndRoleByUserName(String userName,Integer appId);

    /**
     *  根据用户手机查询用户角色
     * @param mobile 手机
     * @return
     */
    SecurityUser queryUserAndRoleByMobile(String mobile);

    /**
     *  根据用户手机和应用id查询用户角色
     * @param mobile 手机
     * @param appId 应用id
     * @return
     */
    SecurityUser queryUserAndRoleByMobile(String mobile,Integer appId);

    /**
     *  根据用户邮箱和应用id查询用户角色
     * @param email 邮箱
     * @return
     */
    SecurityUser queryUserAndRoleByEmail(String email);

    /**
     *  根据用户邮箱和应用id查询用户角色
     * @param email 邮箱
     * @param appId 应用id
     * @return
     */
    SecurityUser queryUserAndRoleByEmail(String email,Integer appId);

    /**
     *  根据用户id和应用id查询用户角色
     * @param id 用户id
     * @return
     */
    SecurityUser queryUserAndRoleById(Long id);

    /**
     *  根据用户id和应用id查询用户角色
     * @param id 用户id
     * @param appId 应用id
     * @return
     */
    SecurityUser queryUserAndRoleById(Long id,Integer appId);

    /**
     * 分页查询用户角色
     * @param limit 开始行
     * @param offset 结束行
     * @return
     */
    List<SecurityUser> queryAllUserAndRoles(int limit, int offset);

}
