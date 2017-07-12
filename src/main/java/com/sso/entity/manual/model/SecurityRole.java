package com.sso.entity.manual.model;

import java.io.Serializable;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.GrantedAuthority;

/**
 * SecurityRole
 *
 * @author yitao
 * @version 1.0.0
 * @date 2017/7/5 21:49
 */
public class SecurityRole implements GrantedAuthority,ConfigAttribute,Serializable {
    private Integer id;

    private Integer appId;

    private String roleName;
    
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    @Override
    public String getAuthority() {
        return appId+"@"+roleName;
    }

    @Override
    public String getAttribute() {
        return appId+"@"+roleName;
    }
}
