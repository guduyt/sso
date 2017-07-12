package com.sso.entity.auto.dao.impl;

import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sso.entity.auto.model.SysUserExample;
import com.sso.entity.manual.dao.SecurityUserDao;
import com.sso.entity.manual.model.SecurityUser;

/**
 * Created by yt on 2017/7/8.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring.xml" })
public class SecurityUserDaoImplTest {
     @Autowired
     private SecurityUserDao securityUserDao;

    
    @Test
    public void queryUserAndRoleByUserName() throws Exception {
        SecurityUser list=securityUserDao.queryUserAndRoleByUserName("易涛");
        Properties properties=System.getProperties();
        String printSql=properties.getProperty("printSql");
        list=securityUserDao.queryUserAndRoleByUserName("yt");
        SysUserExample userExample=new SysUserExample();
        userExample.createCriteria().andUserNameEqualTo("易涛").andEnableEqualTo(true);
        
        
    }

}