package com.sso.entity.manual.model;

import com.sso.yt.commons.utils.LogUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Person
 *
 * @author yitao
 * @version 1.0.0
 * @date 2018/5/6 10:00
 */

public class Person {
    private String name;
    private String address;
    private int    phone;
    private String beanName;

    public Person() {
        LogUtils.LOGGER.info("Person 执行构造器");
    }

    public void doThing(){
        LogUtils.LOGGER.info("Person 执行 doThing");
    }

    @PostConstruct
    public void postConstruct(){
        LogUtils.LOGGER.info("Person 执行 postConstruct");
    }

    public void initMethod(){
        LogUtils.LOGGER.info("Person 执行 initMethod");
    }

    public void destroyMethod(){
        LogUtils.LOGGER.info("Person 执行 destroyMethod");
    }

    @PreDestroy
    public void preDestroyMethod(){
        LogUtils.LOGGER.info("Person 执行 preDestroyMethod");
    }

    

}
