package com.sso.entity.manual.model;

import java.io.Serializable;

/**
 * SecurityModule
 *
 * @author yitao
 * @version 1.0.0
 * @date 2017/7/5 22:30
 */
public class SecurityModule implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer id;


    private Integer appId;


    private String moduleName;


    private Byte moduleType;


    private String description;


    private String modulePath;

    
    private Integer parentId;
}
