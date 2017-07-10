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
     * 下划线转驼峰法 ，会抛弃特殊字符
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
     * 驼峰法转下划线，会抛弃特殊字符
     * @param line 源字符串
     * @param small 是否为小驼峰
     * @return 转换后的字符串
     */
    public static String camelToUnderline(String line,boolean small){
        if(line==null||"".equals(line)){
            return "";
        }
        line=String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1));
        StringBuilder sb=new StringBuilder();
        Pattern pattern=Pattern.compile(underlinePattern);
        Matcher matcher=pattern.matcher(line);
        while(matcher.find()){
            String word=matcher.group();
            sb.append(small?word.toLowerCase():word.toUpperCase());
            sb.append(matcher.end()==line.length()?"":underline);
        }
        return sb.toString();
    }

    /**
     * 驼峰法转换为下划线命名法规则
     * 结果"user@Name" =>"USER_@NAME";
     * @param str 源字符串
     * @return
     */
    public static String parseToUnderline(String str) {
        char[] chars = str.toCharArray();
        StringBuilder s = new StringBuilder();
        boolean isFirst = true;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 65 && chars[i] <= 90) {
                if (!isFirst)
                    s.append('_');
                s.append(chars[i]);
            } else if (chars[i] >= 97 && chars[i] <= 122) {
                s.append((char) (chars[i] - 32));
            } else {
                s.append(chars[i]);
            }
            isFirst = false;
        }
        return s.toString();
    }

    /**
     *  下划线转驼峰法
     *  结果 "user_id@name"=>userId@name
     * @param source 源字符串
     * @return
     */
    public static String parseToCamel(String source) {
        source = source.toLowerCase();
        Pattern p = Pattern.compile("_[a-z]");
        Matcher m = p.matcher(source);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String firstChar = m.group().substring(1, 2);
            m.appendReplacement(sb, firstChar.toUpperCase());
        }
        m.appendTail(sb);
        return sb.toString();
    }

}
