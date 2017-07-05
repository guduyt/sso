package com.sso.entity.auto.model;

import com.yt.mybatis.model.BaseModel;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "sys_resource_group_relation")
public class SysResourceGroupRelation extends BaseModel implements Serializable {
    /**
    * 主键
    */
    @Column(name="id")
    @Id
    private Integer id;

    /**
    * 资源组id
    */
    @Column(name="resource_group_id")
    private Integer resourceGroupId;

    /**
    * 资源id
    */
    @Column(name="resources_id")
    private Integer resourcesId;

    /**
    * 启用标志
    */
    @Column(name="enable")
    private Boolean enable;

    /**
    * 创建人
    */
    @Column(name="creator")
    private String creator;

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

    public Integer getResourceGroupId() {
        return resourceGroupId;
    }

    public void setResourceGroupId(Integer resourceGroupId) {
        this.resourceGroupId = resourceGroupId;
    }

    public Integer getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(Integer resourcesId) {
        this.resourcesId = resourcesId;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}