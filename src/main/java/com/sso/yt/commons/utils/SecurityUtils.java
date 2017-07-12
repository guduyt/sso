package com.sso.yt.commons.utils;

/**
 * Created by yt on 2017-7-12.
 */
public class SecurityUtils {

	/**
	 *  根据资源url和请求方式转换为ResourcesPath
	 * @param requestPath  资源url
	 * @param requestType  请求方式
	 * @return
	 */
	public static String getResourcesPath(String requestPath,String requestType){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(requestType).append("[").append(urlConversion(requestPath)).append("]");
		return  stringBuilder.toString();
	}

	/**
	 *  把资源url包含大写字符转换为小写，
	 * @param str 资源url
	 * @return
	 */
	public static String urlConversion(String str) {

		str = str.replace("_", "@");
		return UnderlineToCamelUtils.parseToUnderline(str);

	}

	public static String securityRoleConversionAuthority(Integer appId,String roleName) {
		return appId+"@"+roleName;
	}

}
