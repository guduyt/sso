package com.sso.yt.commons.utils;

import com.sso.yt.commons.exceptions.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Formatter;
import java.util.Locale;

import static java.net.InetAddress.getLocalHost;

/**
 * Created by yt on 2017-7-25.
 */
public class HostUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(HostUtils.class);
	public enum EPlatform {
		ANY("any"),
		WINDOWS("Windows"),
		LINUX("Linux"),
		MAC_OS("Mac OS"),
		MAC_OS_X("Mac OS X"),
		OTHERS("Others");

		private EPlatform(String desc){
			this.description = desc;
		}

		private String description;
		@Override
		public String toString(){
			return description;
		}
	}

	private static String ip;
	private static String hostName;
	private static String macAddress;

	private static String OSName;
	private static String computerName;
	private static String userDomain;
	private static String userName;

	private static String fileSeparator;
	private static String pathSeparator;
	private static String lineSeparator;

	public static String getIp() {
		if(null==ip)
			ip= getInetAddress().getHostAddress();
		return ip;
	}

	public static String getHostName() {
		if(null==hostName)
			setHostName();
		return hostName;
	}

	public static String getMacAddress() {
		if(null==macAddress)
			setMacAddress();
		return macAddress;
	}

	public static String getOSName() {
		if (OSName==null){
			OSName=System.getProperty("os.name");
		}
		return OSName;
	}

	public static String getComputerName() {
		if(null==computerName)
			computerName=System.getenv("COMPUTERNAME");
		return computerName;
	}

	public static String getUserDomain() {
		if(null==userDomain)
			userDomain=System.getenv("USERDOMAIN");
		return userDomain;
	}

	public static String getUserName() {
		if (null==userName)
			userName=System.getProperty("user.name");
		return userName;
	}

	public static String getFileSeparator() {
		if (null==fileSeparator)
			fileSeparator=System.getProperty("file.separator");
		return fileSeparator;
	}

	public static String getPathSeparator() {
		if (null==pathSeparator)
			pathSeparator=System.getProperty("path.separator");
		return pathSeparator;
	}

	public static String getLineSeparator() {
		if (null==lineSeparator)
			lineSeparator=System.getProperty("line.separator");
		return lineSeparator;
	}


	public static boolean isLinux(){
		return getOSName().indexOf(EPlatform.LINUX.toString())>=0;
	}

	public static boolean isWindows(){
		return getOSName().indexOf(EPlatform.WINDOWS.toString())>=0;
	}

	private static synchronized void setHostName() {
		try {
			hostName = getLocalHost().getHostName();
		} catch (Exception ex) {
			String host = ex.getMessage(); // host = "hostname: hostname"
			if (host != null) {
				int colon = host.indexOf(':');
				hostName = host.substring(0, host.indexOf(':'));
			}
			if (null != getComputerName() && null == hostName) {
				hostName = getComputerName();
			}
			LOGGER.error("获取计算机名称异常，尝试使用System.getenv().get()获取计算机名称:{}！", hostName, ex);
		}
	}

	private static synchronized void setMacAddress(){
		try (Formatter formatter = new Formatter()){
			NetworkInterface networkInterface=NetworkInterface.getByInetAddress(getInetAddress());

			byte[] mac = networkInterface.getHardwareAddress();
			String sMAC = "";
			for (int i = 0; i < mac.length; i++) {
				sMAC = formatter.format(Locale.getDefault(), "%02X%s", mac[i],
						(i < mac.length - 1) ? "-" : "").toString();
			}
			macAddress=sMAC;
		} catch (Exception e) {
			LogUtils.LOGGER.error("mac地址获取失败", e);
		}
	}

	private static synchronized InetAddress getInetAddress(){
		try {
			InetAddress inetAddress;
			if(isWindows()){
				inetAddress= InetAddress.getLocalHost();
			} else if(isLinux()){
				inetAddress=InetAddress.getByName(getHostName());
			} else {
				inetAddress=InetAddress.getLocalHost();
			}
			return inetAddress;
		} catch (Exception ex){
			return finalGetInetAddress();
		}
	}

	private static InetAddress finalGetInetAddress(){
		try {
			Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
				if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
					continue;
				}
				Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
				while (addresses.hasMoreElements()) {
					ip = addresses.nextElement();
					if (ip != null && ip instanceof Inet4Address) {
						if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
							LogUtils.LOGGER.debug("外网IP:"+ip.getHostAddress());
							return ip;
						}
						if(!ip.isLoopbackAddress() && !ip.isLinkLocalAddress() && ip.isSiteLocalAddress()){ // 内网IP
							LogUtils.LOGGER.debug("内网IP:"+ip.getHostAddress());
							return ip;
						}
					}
				}
			}
			return InetAddress.getLocalHost();
		} catch (Exception ex) {
			throw  new BusinessException("获取计算机InetAddress异常！",ex);
		}
	}


	/**
	 * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
	 *
	 * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
	 * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
	 *
	 * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
	 * 192.168.1.100
	 * 用户真实IP为： 192.168.1.110
	 *
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}


