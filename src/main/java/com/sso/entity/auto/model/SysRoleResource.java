package com.sso.entity.auto.model;

import com.yt.mybatis.model.BaseModel;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "sys_role_resource")
public class SysRoleResource extends BaseModel implements Serializable {
    /**
    * 主键
    */
    @Column(name="id")
    @Id
    private Integer id;

    /**
    * 角色id
    */
    @Column(name="role_id")
    private Integer roleId;

    /**
    * 资源id
    */
    @Column(name="resources_id")
    private Integer resourcesId;

    /**
    * 1.细粒度资源，2资源组,3应用菜单
    */
    @Column(name="type")
    private Integer type;

    /**
    * 创建时间
    */
    @Column(name="create_time")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(Integer resourcesId) {
        this.resourcesId = resourcesId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}