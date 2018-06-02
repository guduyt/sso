package com.sso.business.security;

import com.sso.entity.manual.model.SecurityRole;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

import static com.sso.yt.commons.constants.CommonConstant.ADMIN;

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
        if(Objects.nonNull(authentication) && CollectionUtils.isNotEmpty(authentication.getAuthorities())){
             //如果是管理员，就放弃权限检查
            for (GrantedAuthority securityRole: authentication.getAuthorities()) {
                if(securityRole instanceof SecurityRole){
                    if(ADMIN.equals(((SecurityRole)securityRole).getRoleName())){
                        return;
                    }
                }
            }
        }
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
        throw new AccessDeniedException("没有权限操作！");
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
