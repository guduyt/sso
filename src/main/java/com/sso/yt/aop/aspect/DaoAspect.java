package com.sso.yt.aop.aspect;

import com.yt.mybatis.model.BaseModel;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by yt on 2017-1-24.
 * 统一给insert和update操作添加创建人和修改人，并且设置创建时间和修改时间为null，使用数据库自动生成的时间 Created by yt on 2017-1-22.
 */
@Component
@Aspect
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DaoAspect {

    private static ThreadLocal<String> loginName = new ThreadLocal<>();
    private static final String CREATOR = "creator";
    private static final String EDITOR = "editor";

    private static final String CREATE_TIME = "createTime";
    private static final String LAST_TIME = "lastTime";

    private static final String DEFAULT = "admin";

    /**
     * 设置aop程序需要使用的登陆用户名
     * @param name
     */
    public static void setLoginName(String name) {
        loginName.set(name);
    }

    /**
     * 此方法禁止开放
     * @return
     */
    private static String getLoginName() {
        if (StringUtils.isNotEmpty(loginName.get()))
            return loginName.get();
        else
            return DEFAULT;
    }


    @Pointcut("execution(* com.yt.mybatis.model.BaseMapper.insert*(..))")
    public void insertPointcut() {
        //声明插入操作拦截点
    }


    @Pointcut("execution(* com.yt.mybatis.model.BaseMapper.update*(..)) || execution(* com.yt.mybatis.model.BasePKMapper.update*(..))")
    public void updatePointcut() {
        //声明更新操作拦截点
    }


    @Before("updatePointcut()")
    public void beforeUpdate(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0)
            return;

        if (args[0] instanceof BaseModel) {
            BeanWrapper beanWrapper = new BeanWrapperImpl(args[0]);
            if (beanWrapper.isWritableProperty(EDITOR)) {
                beanWrapper.setPropertyValue(EDITOR, getLoginName());
            }
            if (beanWrapper.isWritableProperty(LAST_TIME)) {
                beanWrapper.setPropertyValue(LAST_TIME, null);
            }
        } else if (args[0] instanceof Map) {
            Map map = (Map) args[0];
            if (map.containsKey(EDITOR)) {
                map.put(EDITOR, getLoginName());
            }

            if (map.containsKey(LAST_TIME)) {
                map.put(LAST_TIME, null);
            }
        }
    }

    @Before("insertPointcut()")
    public void beforeInsert(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0)
            return;

        if (args[0] instanceof BaseModel) {
            BeanWrapper beanWrapper = new BeanWrapperImpl(args[0]);
            if (beanWrapper.isWritableProperty(CREATOR)) {
                beanWrapper.setPropertyValue(CREATOR, getLoginName());
            }
            if (beanWrapper.isWritableProperty(CREATE_TIME)) {
                beanWrapper.setPropertyValue(CREATE_TIME, null);
            }
            if (beanWrapper.isWritableProperty(EDITOR)) {
                beanWrapper.setPropertyValue(EDITOR, getLoginName());
            }
            if (beanWrapper.isWritableProperty(LAST_TIME)) {
                beanWrapper.setPropertyValue(LAST_TIME, null);
            }
        } else if (args[0] instanceof Map) {
            Map map = (Map) args[0];
            if (map.containsKey(CREATOR)) {
                map.put(CREATOR, getLoginName());
            }
            if (map.containsKey(CREATE_TIME)) {
                map.put(CREATE_TIME, null);
            }
            if (map.containsKey(EDITOR)) {
                map.put(EDITOR, getLoginName());
            }
            if (map.containsKey(LAST_TIME)) {
                map.put(LAST_TIME, null);
            }
        }
    }
}
