package com.jdk;

import com.sso.yt.commons.utils.LogUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yt on 2017-7-7.
 */

public class StringUtilsTest {

	@Test
	public void  stringUtilsSubstring(){
		  String msg="100040044:价格必须在区间{min}-{max}" ;
		if (msg.indexOf(":") != -1) {

			LogUtils.LOGGER.info("StringUtils.substring={}",StringUtils.substring(msg,msg.indexOf(":")));
			LogUtils.LOGGER.info("StringUtils.substring={}",StringUtils.substring(msg,0,msg.indexOf(":")));
			LogUtils.LOGGER.info("StringUtils.substringBefore={}",StringUtils.substringBefore(msg,":"));
			LogUtils.LOGGER.info("StringUtils.substringAfter={}",StringUtils.substringAfter(msg,":"));
		}
	}


	@Test
	public void arrayListToString(){
		List<String> ignoreUrls=new ArrayList<>();
		ignoreUrls.add("/**/*.css");
		ignoreUrls.add("/**/*.css.map");
		ignoreUrls.add("/**/*.jpg");
		ignoreUrls.add("/**/*.jpeg");
		ignoreUrls.add("/**/*.gif");
		ignoreUrls.add("/**/*.png");
		ignoreUrls.add("/**/*.js");
		ignoreUrls.add("/**/*.ico");

		LogUtils.LOGGER.info("ArrayList to string: {}",ignoreUrls.stream().collect(Collectors.joining(",")));
	}
}
