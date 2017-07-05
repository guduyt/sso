package com.sso.entity.auto.model;

import com.yt.mybatis.model.BaseModel;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "test")
public class Test extends BaseModel implements Serializable {
    /**
    */
    @Column(name="id")
    @Id
    private Integer id;

    /**
    */
    @Column(name="name")
    private String name;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}