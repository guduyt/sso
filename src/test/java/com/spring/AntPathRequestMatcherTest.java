package com.spring;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.AntPathMatcher;

/**
 * AntPathRequestMatcherTest
 *
 * @author yitao
 * @version 1.0.0
 * @date 2017/7/8 1:16
 */
public class AntPathRequestMatcherTest {
    @Test
    public void matcherTest(){

        AntPathMatcher antPathMatcher=new AntPathMatcher();
        Assert.assertTrue("/demo/{id}不匹配/demo/2",antPathMatcher.match("/demo/{id}","/demo/2"));

        Assert.assertTrue("/demo/{id}不匹配/demo/id",antPathMatcher.match("/demo/{id}","/demo/id"));
        Assert.assertTrue("/demo/{id}不匹配/demo/query",antPathMatcher.match("/demo/{id}","/demo/query"));
        Assert.assertFalse("/demo/query/{id}不匹配/demo/query",antPathMatcher.match("/demo/query/{id}","/demo/query"));
        Assert.assertFalse("/demo/{id}不匹配/demo/",antPathMatcher.match("/demo/{id}","/demo/"));
    }
}
