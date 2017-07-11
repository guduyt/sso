package com.sso.entity.manual.model;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.List;

/**
 * SecurityResource
 *
 * @author yitao
 * @version 1.0.0
 * @date 2017/7/5 21:50
 */
public class SecurityResource implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer appId;

    private String resourcePath;

    private String requestPath;

    private String requestType;

    private String javaClass;

    private String shortMethod;

    private String method;

    private List<SecurityRole> securityRoles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getJavaClass() {
        return javaClass;
    }

    public void setJavaClass(String javaClass) {
        this.javaClass = javaClass;
    }

    public String getShortMethod() {
        return shortMethod;
    }

    public void setShortMethod(String shortMethod) {
        this.shortMethod = shortMethod;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<SecurityRole> getSecurityRoles() {
        return securityRoles;
    }

    public void setSecurityRoles(List<SecurityRole> securityRoles) {
        this.securityRoles = securityRoles;
    }


    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(": ");
        sb.append("id: ").append(this.id).append("; ");
        sb.append("appId: ").append(this.appId).append("; ");
        sb.append("resourcePath: ").append(this.resourcePath).append("; ");
        sb.append("requestPath: ").append(this.requestPath).append("; ");
        sb.append("requestType: ").append(this.requestType).append("; ");
        sb.append("javaClass: ").append(this.javaClass).append("; ");
        sb.append("shortMethod: ").append(this.shortMethod).append("; ");
        sb.append("method: ").append(this.method).append("; ");

        if (securityRoles!=null && !securityRoles.isEmpty()) {
            sb.append("资源授予的角色权限: ");

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
            sb.append("资源没有没有授予角色权限！");
        }

        return sb.toString();
    }
}
