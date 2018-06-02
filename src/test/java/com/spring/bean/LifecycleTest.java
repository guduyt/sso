package com.spring.bean;

import com.sso.entity.manual.model.Person;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * lifecycleTest
 *
 * @author yitao
 * @version 1.0.0
 * @date 2018/5/6 10:29
 */

public class LifecycleTest {

    private static String xmlPath="classpath:config/applicationContext-lifecycle.xml";

    @Test
    public void beanLifecycleTest(){
        Resource res = new ClassPathResource(xmlPath);
        AbstractApplicationContext applicationContext= new ClassPathXmlApplicationContext(xmlPath);

        Person person=applicationContext.getBean(Person.class);
        person.doThing();

        applicationContext.close();
    }
}
