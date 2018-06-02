package com.jdk;

import com.google.common.base.Charsets;
import com.sso.yt.commons.utils.LogUtils;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * Created by yt on 2017-6-29.
 */
public class BasicTypeTest {

	 //基本类型（原始数据类型） byte short int long float double char boolean
	static byte aByte;
	static short aShort;
	static int anInt;
	static long aLong;
	static float aFloat;
	static double aDouble;
	static char aChar;
	static boolean aBoolean;

	static {
		LogUtils.LOGGER.info("byte的大小："+Byte.SIZE+" 默认值："+aByte+" -数据范围："+Byte.MIN_VALUE+"~"+Byte.MAX_VALUE);
		LogUtils.LOGGER.info("short的大小："+Short.SIZE+" 默认值："+aShort+" -数据范围："+Short.MIN_VALUE+"~"+Short.MAX_VALUE);
		LogUtils.LOGGER.info("int的大小："+Integer.SIZE+" 默认值："+anInt+" -数据范围："+Integer.MIN_VALUE+"~"+Integer.MAX_VALUE);
		LogUtils.LOGGER.info("long的大小："+Long.SIZE+" 默认值："+aLong+" -数据范围："+Long.MIN_VALUE+"~"+Long.MAX_VALUE);
		LogUtils.LOGGER.info("float的大小："+Float.SIZE+" 默认值："+aFloat+" -数据范围："+Float.MIN_VALUE+"~"+Float.MAX_VALUE);
		LogUtils.LOGGER.info("double的大小："+Double.SIZE+" 默认值："+aDouble+" -数据范围："+Double.MIN_VALUE+"~"+Double.MAX_VALUE);
		LogUtils.LOGGER.info("char的大小："+Character.SIZE+" 默认值："+ Charsets.UTF_8.encode("")+Integer.parseInt(Integer.toHexString(aChar))+" -数据范围："+Character.MIN_VALUE+"~"+Character.MAX_VALUE);
		LogUtils.LOGGER.info("boolean的大小："+Byte.SIZE+" 默认值："+aBoolean+" -数据范围："+Byte.MIN_VALUE+"~"+Byte.MAX_VALUE);
	}


	@Test
	public void typeTest(){
		ByteBuffer buffer = Charsets.UTF_8.encode("hello");
		while (buffer.hasRemaining()) {
			LogUtils.LOGGER.info(""+(char) buffer.get());
		}
		buffer.flip() ;
		CharBuffer charBuffer = Charsets.UTF_8.decode(buffer);
		LogUtils.LOGGER.info(charBuffer.toString());

	}
}
