package com.spring.bean;

import com.sso.entity.manual.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * LifecycleWithScanTest
 *
 * @author yitao
 * @version 1.0.0
 * @date 2018/5/6 10:39
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/applicationContext-lifecycle.xml" })
public class LifecycleWithScanTest {
    
    @Autowired
    private Person person;

    @Test
    public void beanLifecycleTest(){
        person.doThing();
    }
}
