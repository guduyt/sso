package com.jdk;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.sso.yt.commons.utils.LogUtils;

/**
 * Created by yt on 2017-7-7.
 */

public class StringUtilsTest {

	@Test
	public void  StringUtils_substring(){
		  String msg="100040044:价格必须在区间{min}-{max}" ;
		if (msg.indexOf(":") != -1) {

			LogUtils.LOGGER.info("StringUtils.substring={}",StringUtils.substring(msg,msg.indexOf(":")));
			LogUtils.LOGGER.info("StringUtils.substring={}",StringUtils.substring(msg,0,msg.indexOf(":")));
			LogUtils.LOGGER.info("StringUtils.substringBefore={}",StringUtils.substringBefore(msg,":"));
			LogUtils.LOGGER.info("StringUtils.substringAfter={}",StringUtils.substringAfter(msg,":"));
		}
	}
}
