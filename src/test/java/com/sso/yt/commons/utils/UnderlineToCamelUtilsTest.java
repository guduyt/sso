package com.sso.yt.commons.utils;

import org.junit.Test;

/**
 * Created by yt on 2017-7-10.
 */
public class UnderlineToCamelUtilsTest {
	@Test
	public void underlineToCamel() throws Exception {
		String s="user_id";
		LogUtils.LOGGER.info(UnderlineToCamelUtils.underlineToCamel(s,true));
		LogUtils.LOGGER.info(UnderlineToCamelUtils.parseToCamel(s));
		 s="user_id@name";
		LogUtils.LOGGER.info(UnderlineToCamelUtils.underlineToCamel(s,true));
		LogUtils.LOGGER.info(UnderlineToCamelUtils.parseToCamel(s));
		s="user_id@name_TAO_bao";
		LogUtils.LOGGER.info(UnderlineToCamelUtils.underlineToCamel(s,true));
		LogUtils.LOGGER.info(UnderlineToCamelUtils.parseToCamel(s));
	}

	@Test
	public void camelToUnderline() throws Exception {
		String s="userIdName";
		LogUtils.LOGGER.info(UnderlineToCamelUtils.camelToUnderline(s,true));
		LogUtils.LOGGER.info(UnderlineToCamelUtils.parseToUnderline(s));
		s="user_IdName";
		LogUtils.LOGGER.info(urlConversion(s));
		LogUtils.LOGGER.info(UnderlineToCamelUtils.camelToUnderline(s,true));
		LogUtils.LOGGER.info(UnderlineToCamelUtils.parseToUnderline(s));
		s="user_Id@Name";
		LogUtils.LOGGER.info(urlConversion(s));
		LogUtils.LOGGER.info(UnderlineToCamelUtils.camelToUnderline(s,true));
		LogUtils.LOGGER.info(UnderlineToCamelUtils.parseToUnderline(s));
	}

	private String urlConversion(String str) {

		str = str.replace("_", "@");
		return UnderlineToCamelUtils.parseToUnderline(str);

	}

}