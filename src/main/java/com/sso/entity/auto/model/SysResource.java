package com.sso.entity.auto.model;

import com.yt.mybatis.model.BaseModel;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "sys_resource")
public class SysResource extends BaseModel implements Serializable {
    /**
    * 资源主键
    */
    @Column(name="id")
    @Id
    private Integer id;

    /**
    * 资源名
    */
    @Column(name="resources_name")
    private String resourcesName;

    /**
    * 资源url
    */
    @Column(name="resource_path")
    private String resourcePath;

    /**
    * 请求url
    */
    @Column(name="request_path")
    private String requestPath;

    /**
    * 请求方式
    */
    @Column(name="request_type")
    private String requestType;

    /**
    * java类名
    */
    @Column(name="java_class")
    private String javaClass;

    /**
    * 方法名称
    */
    @Column(name="short_method")
    private String shortMethod;

    /**
    * 包名、类名、参数等信息的完整方法名
    */
    @Column(name="method")
    private String method;

    /**
    * 描述
    */
    @Column(name="description")
    private String description;

    /**
    * 启用标志
    */
    @Column(name="enable")
    private Boolean enable;

    /**
    * 资源类型
    */
    @Column(name="resources_type")
    private Byte resourcesType;

    /**
    * 父级资源
    */
    @Column(name="parent_id")
    private Integer parentId;

    /**
    * 备注
    */
    @Column(name="remark")
    private String remark;

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

    /**
    * 修改人
    */
    @Column(name="editor")
    private String editor;

    /**
    * 修改时间
    */
    @Column(name="edit_time")
    private Date editTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResourcesName() {
        return resourcesName;
    }

    public void setResourcesName(String resourcesName) {
        this.resourcesName = resourcesName == null ? null : resourcesName.trim();
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath == null ? null : resourcePath.trim();
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath == null ? null : requestPath.trim();
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType == null ? null : requestType.trim();
    }

    public String getJavaClass() {
        return javaClass;
    }

    public void setJavaClass(String javaClass) {
        this.javaClass = javaClass == null ? null : javaClass.trim();
    }

    public String getShortMethod() {
        return shortMethod;
    }

    public void setShortMethod(String shortMethod) {
        this.shortMethod = shortMethod == null ? null : shortMethod.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Byte getResourcesType() {
        return resourcesType;
    }

    public void setResourcesType(Byte resourcesType) {
        this.resourcesType = resourcesType;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor == null ? null : editor.trim();
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }
}