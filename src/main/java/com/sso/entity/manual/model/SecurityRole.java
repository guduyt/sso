package com.sso.entity.manual.model;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

/**
 * SecurityRole
 *
 * @author yitao
 * @version 1.0.0
 * @date 2017/7/5 21:49
 */
public class SecurityRole implements GrantedAuthority ,Serializable {
    private Integer id;

    private String roleName;
    
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    @Override
    public String getAuthority() {
        return roleName;
    }
}
