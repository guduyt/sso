package com.sso.entity.manual.model;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * SecurityUser
 *
 * @author yitao
 * @version 1.0.0
 * @date 2017/7/5 21:49
 */
public class SecurityUser implements UserDetails,CredentialsContainer {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private Long id;

    private String userName;

    private String password;

    private String mobile;

    private String email;

    private Boolean enable;

    private Boolean isLock;

    private Date expire;

    private List<SecurityRole> securityRoles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Boolean getLock() {
        return isLock;
    }

    public void setLock(Boolean lock) {
        isLock = lock;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    public List<SecurityRole> getSecurityRoles() {
        return securityRoles;
    }

    public void setSecurityRoles(List<SecurityRole> securityRoles) {
        this.securityRoles = securityRoles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return securityRoles;
    }

    
    @Override
    public boolean isAccountNonExpired() {
        if(expire==null)
            return true;
        return expire.compareTo(new Date())>0?true:false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isLock;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        if(expire==null)
            return true;
        return expire.compareTo(new Date())>0?true:false;
    }

    @Override
    public boolean isEnabled() {
        return this.enable;
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof SecurityUser) {
            return userName.equals(((SecurityUser) rhs).userName);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return userName.hashCode();
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(": ");
        sb.append("UserName: ").append(this.userName).append("; ");
        sb.append("Password: [PROTECTED]; ");
        sb.append("Mobile: ").append(this.mobile).append("; ");
        sb.append("Email: ").append(this.email).append("; ");
        sb.append("Enable: ").append(this.enable).append("; ");
        sb.append("IsLock: ").append(this.isLock).append("; ");
        sb.append("Expire: ").append(this.expire).append("; ");

        if (securityRoles!=null && !securityRoles.isEmpty()) {
            sb.append("用户授予的权限: ");

            boolean first = true;
            for (GrantedAuthority auth : securityRoles) {
                if (!first) {
                    sb.append(",");
                }
                first = false;
                sb.append(auth);
            }
        }
        else {
            sb.append("用户没有授予任何权限！");
        }

        return sb.toString();
    }
}
