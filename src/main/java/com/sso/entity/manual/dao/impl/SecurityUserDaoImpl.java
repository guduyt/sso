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
    private final String queryType = "queryType";
    private final String queryKey = "queryKey";
    @Autowired
    private SecurityUserMapper securityUserMapper;

    @Override
    public List<SecurityUser> queryUserAndRoleByUserName(String userName) {
        Map<String, Object> map = new HashedMap();
        map.put(queryType, "username");
        map.put(queryKey, userName);

        return securityUserMapper.queryUserAndRoles(map);
    }

    @Override
    public List<SecurityUser> queryUserAndRoleByMobile(String mobile) {
        Map<String, Object> map = new HashedMap();
        map.put(queryType, "mobile");
        map.put(queryKey, mobile);

        return securityUserMapper.queryUserAndRoles(map);
    }

    @Override
    public List<SecurityUser> queryUserAndRoleByEmail(String email) {
        Map<String, Object> map = new HashedMap();
        map.put(queryType, "mobile");
        map.put(queryKey, email);

        return securityUserMapper.queryUserAndRoles(map);
    }

    public  List<SecurityUser> queryAllUserAndRoles(int limit, int offset){
        Map<String, Object> map = new HashedMap();
        map.put("limit", limit);
        map.put("offset", offset);
        return securityUserMapper.queryAllUserAndRoles(map);
    }
}
