package com.sso.business.security;

import com.sso.entity.manual.dao.SecurityUserDao;
import com.sso.entity.manual.model.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * SecurityUserDetailsService
 *
 * @author yitao
 * @version 1.0.0
 * @date 2017/7/11 23:47
 */
public class SecurityUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SecurityUserDao securityUserDao;

    @Override
    public UserDetails loadUserByUsername(String username) {
        SecurityUser securityUser = securityUserDao.queryUserAndRoleByUserName(username);
        if (null==securityUser)
            throw new UsernameNotFoundException("用户" + username + "不存在");
        return securityUser;
    }
}
