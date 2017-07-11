package com.sso.business.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;

/**
 * AccessDecisionManagerImpl
 *
 * @author yitao
 * @version 1.0.0
 * @date 2017/7/11 23:50
 */

@Service("securityAccessDecisionManagerImpl")
public class SecurityAccessDecisionManagerImpl implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) {
        if (null == configAttributes) {
            return;
        }

        Iterator<ConfigAttribute> cons = configAttributes.iterator();

        while (cons.hasNext()) {
            ConfigAttribute ca = cons.next();
            String needRole = ca.getAttribute();
            //gra 为用户所被赋予的权限，needRole为访问相应的资源应具有的权限
            for (GrantedAuthority gra : authentication.getAuthorities()) {
                if (needRole.trim().equals(gra.getAuthority().trim())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("Access Denied");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
