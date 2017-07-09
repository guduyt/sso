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
        Assert.assertTrue("匹配",antPathMatcher.match("/demo/{id}","/demo/2"));

        Assert.assertTrue("不匹配",antPathMatcher.match("/demo/{id}","/demo/d2"));
    }
}
