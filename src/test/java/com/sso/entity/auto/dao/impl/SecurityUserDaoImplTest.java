package com.sso.entity.auto.dao.impl;

import com.sso.entity.auto.model.SysUserExample;
import com.sso.entity.manual.dao.SecurityUserDao;
import com.sso.entity.manual.model.SecurityUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Properties;

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
        List<SecurityUser> list=securityUserDao.queryUserAndRoleByUserName("易涛");
        Properties properties=System.getProperties();
        String printSql=properties.getProperty("printSql");

        SysUserExample userExample=new SysUserExample();
        userExample.createCriteria().andUserNameEqualTo("易涛").andEnableEqualTo(true);
        list.size();
        
    }

}