package com.sso.yt.commons.utils;


import java.util.ArrayList;
import java.util.List;

import com.sso.yt.commons.exceptions.BusinessException;

/**
 * Created by yt on 2017-1-23.
 */
public class BeanCopyUtils extends org.springframework.beans.BeanUtils {
    private BeanCopyUtils() {

    }

    /**
     * 对象间复制数据
     * @param source 原始对象
     * @param target 目标对象
     */
    public static void copyProperties(Object source, Object target) {
       /* Assert.notNull(source, 110001007, "源对象不能为空");
        Assert.notNull(source, 110001008, "目标对象不能为空");*/
        try {
            org.springframework.beans.BeanUtils.copyProperties(source, target);
        } catch (Exception ex) {
            throw new BusinessException(110001005, "数据对象属性类型转换错误", ex);
        }
    }

    /**
     *  对象数据复制数据
     * @param source 原始数组
     * @param targetClass 目标类
     * @param <S> 原始类型
     * @param <T> 目标类型
     * @return
     */
    public static <S, T> List<T> copyArrayList(List<S> source, Class<T> targetClass) {
       /* Assert.notNull(targetClass, 110001009, "目标对象类型不能为空");*/
        List<T> list = new ArrayList<T>();
        if (source == null || source.isEmpty())
            return list;
        try {
            for (S s : source) {
                if (s == null) {
                    list.add(null);
                } else {
                    T t = targetClass.newInstance();
                    org.springframework.beans.BeanUtils.copyProperties(s, t);
                    list.add(t);
                }
            }
            return list;
        } catch (Exception ex) {
            throw new BusinessException(110001005, "数据对象属性类型转换错误", ex);
        }
    }
}
