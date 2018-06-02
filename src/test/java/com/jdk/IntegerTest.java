package com.jdk;

import com.sso.yt.commons.utils.LogUtils;
import org.junit.Test;

/**
 * Created by yt on 2017-6-29.
 */
public class IntegerTest {
	//1. equals与==的区别
	//2. 在-128到127的Integer，Long数据会默认被缓存，使用 =、Integer.valueOf()、new Integer()的区别

	@Test
	public void IntegerCacheTest(){
		Integer integer=127;
		Integer integerValue=Integer.valueOf(127);
		Integer integerNew=new Integer(127);

		//在-128到127的Integer，Long数据会默认被缓存
		LogUtils.LOGGER.info("integer==integerValue:{}",integer==integerValue);//是同一个对象
		LogUtils.LOGGER.info("integer==integer1:{}",integer==integerNew); //不是同一个对象
		LogUtils.LOGGER.info("integer.equals(integer1):{}",integer.equals(integerNew)); //不是同一个对象，值相等

		Integer integer128=128;
		Integer integerValue128=Integer.valueOf(128);
		Integer integerNew128=new Integer(128);;
		LogUtils.LOGGER.info("integer128==integerValue128:{}",integer128==integerValue128);
		LogUtils.LOGGER.info("integer128==integerValue128:{}",integer128==integerNew128);
		LogUtils.LOGGER.info("integer128.equals(integerValue128):{}",integer128.equals(integerValue128));

		Long aLong=127L;
		Long aLongValue127=Long.valueOf(127) ;
		Long aLongValue=Long.valueOf(128) ;

		LogUtils.LOGGER.info("aLong==aLongValue127:{}",aLong==aLongValue127);
		LogUtils.LOGGER.info("aLong1.equals(integer2):{}",aLongValue.equals(integer128));
	}

	@Test
	public void typeTest(){

	}

}
