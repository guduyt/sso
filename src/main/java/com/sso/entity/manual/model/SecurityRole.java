package com.sso.entity.manual.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * SecurityRole
 *
 * @author yitao
 * @version 1.0.0
 * @date 2017/7/5 21:49
 */
public class SecurityRole implements GrantedAuthority {
    private Integer id;

    private String roleName;

    private Boolean enable;

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

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Override
    public String getAuthority() {
        return roleName;
    }
}
