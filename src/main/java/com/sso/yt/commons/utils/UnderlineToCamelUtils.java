package com.sso.yt.commons.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * UnderlineToCamelUtils
 *
 * @author yitao
 * @version 1.0.0
 * @date 2017/7/8 10:57
 */
public class UnderlineToCamelUtils {
     private static  char underline= '_';

     private static  String camelPattern="([A-Za-z\\d]+)(_)?";

    private static  String underlinePattern="[A-Z]([a-z\\d]+)?";
    
    private UnderlineToCamelUtils(){
        
    }

    /**
     * 下划线转驼峰法
     * @param line 源字符串
     * @param smallCamel 大小驼峰,是否为小驼峰
     * @return 转换后的字符串
     */
    public static String underlineToCamel(String line,boolean smallCamel){
        if(line==null||"".equals(line)){
            return "";
        }
        StringBuilder sb=new StringBuilder();
        Pattern pattern= Pattern.compile(camelPattern);
        Matcher matcher=pattern.matcher(line);
        while(matcher.find()){
            String word=matcher.group();
            sb.append(smallCamel&&matcher.start()==0?Character.toLowerCase(word.charAt(0)):Character.toUpperCase(word.charAt(0)));
            int index=word.lastIndexOf(underline);
            if(index>0){
                sb.append(word.substring(1, index).toLowerCase());
            }else{
                sb.append(word.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }

    
    /**
     * 驼峰法转下划线
     * @param line 源字符串
     * @return 转换后的字符串
     */
    public static String camelToUnderline(String line){
        if(line==null||"".equals(line)){
            return "";
        }
        line=String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1));
        StringBuilder sb=new StringBuilder();
        Pattern pattern=Pattern.compile(underlinePattern);
        Matcher matcher=pattern.matcher(line);
        while(matcher.find()){
            String word=matcher.group();
            sb.append(word.toUpperCase());
            sb.append(matcher.end()==line.length()?"":underline);
        }
        return sb.toString();
    }
}
