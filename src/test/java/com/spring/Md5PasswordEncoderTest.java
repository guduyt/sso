package com.spring;

import org.junit.Test;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.sso.yt.commons.utils.LogUtils;

/**
 * Created by yt on 2017-7-12.
 */
public class Md5PasswordEncoderTest {


	@Test
	public void md5PasswordEncoderTest(){
		Md5PasswordEncoder md5PasswordEncoder=new Md5PasswordEncoder();
		LogUtils.LOGGER.info("密码转换："+md5PasswordEncoder.encodePassword("123456",null));
	}
}
