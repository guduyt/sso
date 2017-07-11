package com.sso.entity.manual.dao.impl;

import com.sso.entity.manual.dao.SecurityUserDao;
import com.sso.entity.manual.mapper.SecurityUserMapper;
import com.sso.entity.manual.model.SecurityUser;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * SecurityUserDaoImpl
 *
 * @author yitao
 * @version 1.0.0
 * @date 2017/7/8 0:19
 */

@Repository
public class SecurityUserDaoImpl implements SecurityUserDao {
    private final String QUERY_TYPE = "queryType";
    private final String QUERY_KEY = "queryKey";
    private final String APP_ID = "appId";
    @Autowired
    private SecurityUserMapper securityUserMapper;

    @Override
    public SecurityUser queryUserAndRoleByUserName(String userName) {
        Map<String, Object> map = new HashedMap();
        map.put(QUERY_TYPE, "username");
        map.put(QUERY_KEY, userName);

        return securityUserMapper.queryUserAndRoles(map);
    }

    @Override
    public SecurityUser queryUserAndRoleByUserName(String userName,Integer appId) {
        Map<String, Object> map = new HashedMap();
        map.put(QUERY_TYPE, "username");
        map.put(QUERY_KEY, userName);
        map.put(APP_ID,appId);
        return securityUserMapper.queryUserAndRoles(map);
    }

    @Override
    public SecurityUser queryUserAndRoleByMobile(String mobile) {
        Map<String, Object> map = new HashedMap();
        map.put(QUERY_TYPE, "mobile");
        map.put(QUERY_KEY, mobile);

        return securityUserMapper.queryUserAndRoles(map);
    }

    @Override
    public SecurityUser queryUserAndRoleByMobile(String mobile,Integer appId) {
        Map<String, Object> map = new HashedMap();
        map.put(QUERY_TYPE, "mobile");
        map.put(QUERY_KEY, mobile);
        map.put(APP_ID,appId);
        return securityUserMapper.queryUserAndRoles(map);
    }

    @Override
    public SecurityUser queryUserAndRoleByEmail(String email) {
        Map<String, Object> map = new HashedMap();
        map.put(QUERY_TYPE, "mobile");
        map.put(QUERY_KEY, email);

        return securityUserMapper.queryUserAndRoles(map);
    }

    @Override
    public SecurityUser queryUserAndRoleByEmail(String email,Integer appId) {
        Map<String, Object> map = new HashedMap();
        map.put(QUERY_TYPE, "mobile");
        map.put(QUERY_KEY, email);
        map.put(APP_ID,appId);
        return securityUserMapper.queryUserAndRoles(map);
    }

    public SecurityUser queryUserAndRoleById(Long id) {
        return securityUserMapper.queryUserAndRolesById(id);
    }

    public SecurityUser queryUserAndRoleById(Long id,Integer appId) {
        Map<String, Object> map = new HashedMap();
        map.put(QUERY_TYPE, "id");
        map.put(QUERY_KEY, id);
        map.put(APP_ID,appId);
        return securityUserMapper.queryUserAndRoles(map);
    }

    public  List<SecurityUser> queryAllUserAndRoles(int limit, int offset){
        Map<String, Object> map = new HashedMap();
        map.put("limit", limit);
        map.put("offset", offset);
        return securityUserMapper.queryAllUserAndRoles(map);
    }
}
