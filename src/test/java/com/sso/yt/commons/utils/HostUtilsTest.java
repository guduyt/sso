package com.sso.yt.commons.utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Formatter;
import java.util.Locale;

import org.junit.Test;

/**
 * Created by yt on 2017-7-25.
 */
public class HostUtilsTest {

	@Test
	public void hostUtilsTest() throws Exception{
		LogUtils.LOGGER.info("HostUtils.isWindows():"+HostUtils.isWindows());
		LogUtils.LOGGER.info("HostUtils.isLinux():"+HostUtils.isLinux());
		LogUtils.LOGGER.info("HostUtils.getUserName():"+HostUtils.getUserName());
		LogUtils.LOGGER.info("HostUtils.getUserDomain():"+HostUtils.getUserDomain());
		LogUtils.LOGGER.info("HostUtils.getComputerName():"+HostUtils.getComputerName());
		LogUtils.LOGGER.info("HostUtils.getOSName():"+HostUtils.getOSName());
		LogUtils.LOGGER.info("HostUtils.getHostName():"+HostUtils.getHostName());
		LogUtils.LOGGER.info("HostUtils.getUserName():"+HostUtils.getIp());
		LogUtils.LOGGER.info("HostUtils.getFileSeparator():"+HostUtils.getFileSeparator());
		LogUtils.LOGGER.info("HostUtils.getLineSeparator():"+HostUtils.getLineSeparator());
		LogUtils.LOGGER.info("HostUtils.getPathSeparator():"+HostUtils.getPathSeparator());
	}


	@Test
	public void testHost() throws Exception{
		LogUtils.LOGGER.info("username:"+System.getenv().get("USERNAME"));
		LogUtils.LOGGER.info("computername:"+System.getenv("COMPUTERNAME"));
		LogUtils.LOGGER.info("userdomain:"+System.getenv("USERDOMAIN"));
		LogUtils.LOGGER.info("user.country"+System.getProperty("user.country"));

		LogUtils.LOGGER.info("os.name:"+System.getProperty("os.name"));
		LogUtils.LOGGER.info("os.version:"+System.getProperty("os.version"));
		LogUtils.LOGGER.info("os.arch:"+System.getProperty("os.arch"));
		LogUtils.LOGGER.info("file.separator:"+System.getProperty("file.separator"));
		LogUtils.LOGGER.info("path.separator:"+System.getProperty("path.separator"));
		LogUtils.LOGGER.info("line.separator:"+System.getProperty("line.separator"));
		LogUtils.LOGGER.info("user.name:"+System.getProperty("user.name"));

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
							LogUtils.LOGGER.info("外网IP:"+ip.getHostAddress());
							break;
						}

						if(!ip.isLoopbackAddress() && !ip.isLinkLocalAddress() && ip.isSiteLocalAddress()){ // 内网IP
							LogUtils.LOGGER.info("内网IP:"+ip.getHostAddress());
						}
						byte[] mac = netInterface.getHardwareAddress();
						String sMAC = "";
						Formatter formatter = new Formatter();
						for (int i = 0; i < mac.length; i++) {
							sMAC = formatter.format(Locale.getDefault(), "%02X%s", mac[i],
									(i < mac.length - 1) ? "-" : "").toString();

						}
						LogUtils.LOGGER.info("sMAC:"+sMAC);
					}
				}
			}

			LogUtils.LOGGER.info("ip.getHostAddress()"+InetAddress.getByName(InetAddress.getLocalHost().getHostName()).getHostAddress());

		} catch (Exception e) {
			LogUtils.LOGGER.error("IP地址获取失败", e);
		}
	}
}