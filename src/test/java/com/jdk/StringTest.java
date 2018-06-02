package com.jdk;

import org.junit.Assert;
import org.junit.Test;

/**
 * StringTest
 *
 * @author yitao
 * @version 1.0.0
 * @date 2018/5/8 22:23
 */
public class StringTest {


    @Test
    /** 在jdk1.7之前，字符串常量存储在方法区的永久代(PermGen Space)。在jdk1.7之后，字符串常量重新被移到了堆
     * 常量池位置的不同影响到了String的intern()方法的表现，对intern的作用以及在JDK1.6和1.7中的实现原理进行测试
     */
    public void stringInternTest(){

        String s1=new String("1");      //生成了常量池中的"1"对象 和堆空间中的字符串对象
        s1.intern();                             //常量池中寻找后发现"1"对象已经存在于常量池中,返回常量池的字符串引用
        String s2="1";                           //引用指向常量池中的“1”对象
        
        Assert.assertEquals(true,s1.intern()==s2);  //"1"对象已经存在于常量池中,返回常量池的字符串引用
        Assert.assertEquals(false,s1==s2);          //常量池对象 与字符串对象比较 引用地址不相同  false;


        
        String s3=new String("y")+new String("t");  //生成了常量池中的"1"对象 和堆空间中的字符串对象"1"和"11"

        //jdk1.7 常量池中寻找后发现"11"对象不存在于常量池中,直接使用堆上创建的"11"对象的引用，返回堆上的"11"对象的引用
        //JDK1.6 常量池中寻找后发现"11"对象不存在于常量池中,直接在常量池中生成一个 "11" 的对象,返回常量池的字符串引用
        s3.intern();
        String s4 = "yt"; //准备在常量池中创建"11"对象，发现已经有这个对象，直接引用常量池 指向堆上的"11"对象的引用

        Assert.assertEquals(true,s3==s3.intern());
        Assert.assertEquals(true,s3==s4);

        String s5=new String("ja")+ new String("va");
        s5.intern();
        String s6="java";
        Assert.assertEquals(false,s5==s5.intern());
        Assert.assertEquals(false,s5==s6); // 字符串"java"默认已经在常量池中

        
    }

    
}
