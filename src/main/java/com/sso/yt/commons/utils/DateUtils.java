package com.sso.yt.commons.utils;

import com.sso.yt.commons.DateFormat;
import com.sso.yt.commons.constants.ErrorCode;
import com.sso.yt.commons.exceptions.BusinessException;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Utils
 *
 * @author yitao
 * @version 1.0.0
 * @date 2016/5/27 16:44
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils implements DateFormat {
    /**
     * 判断字符串是否是空字符串
     * @param s
     * @return
     */
    private static Boolean isNotEmpty(String s){
        return StringUtils.isNotEmpty(s);
    }

    /**
     *判断字符串是否是数字
     * @param s
     * @return
     */
    private static Boolean isNumber(String s){
        if(isNotEmpty(s)) {
            Pattern p = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");
            return p.matcher(s).matches();
        }
        return false;
    }

    /**
     * 获取当前时间的默认时间格式 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String defaultDateTimeNow(){
        return dateFormat(new Date(),DateFormat_DEFAULT);
    }

    /**
     *  获取当前日期的默认日期格式，yyyy-MM-dd
     * @return
     */
    public static String defaultDateNow(){
        return dateFormat(new Date(),DateFormat_DATE_DEFAULT);
    }

    /**
     *  获取当前时间的默认日期格式，HH:mm:ss
     * @return
     */
    public static String defaultTimeNow(){
        return dateFormat(new Date(),DateFormat_TIME);
    }


    /**
     * 获取当前时间的默认时间格式 yyyy-MM-dd HH:mm:ss
     * @param date  日期
     * @return
     */
    public static String defaultDateTime( Date date) {
        return dateFormat(date,DateFormat_DEFAULT);
    }

    /**
     * 获取当前时间的默认时间格式 yyyy-MM-dd
     * @param date  日期
     * @return
     */
    public static String defaultDate( Date date) {
        return dateFormat(date,DateFormat_DATE_DEFAULT);
    }

    /**
     * 获取当前时间的默认时间格式 HH:mm:ss
     * @param date  日期
     * @return
     */
    public static String defaultTime( Date date) {
        return  dateFormat(date,DateFormat_TIME);
    }

    /**
     * 日期转换为对应格式字符串
     * @param date  日期
     * @param dateFormat 日期应格
     * @return
     */
    public static String dateFormat( Date date,String dateFormat) {
        java.text.DateFormat sdf = DateFormatPool.getDateFormat(dateFormat);
        return sdf.format(date);
    }

    /**
     * 字符串转换为对应格式日期
     * @param str 日期字符串
     * @param dateFormat  日期应格
     * @return
     */
    public static Date toDateForFormat(String str, String dateFormat){
        Date date=null;
        try {
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat(dateFormat);
            if(isNotEmpty(str)){
                date=simpleDateFormat.parse(str);
            }
        } catch (Exception ex){
            throw new BusinessException(ErrorCode.CODE_1000011,"转化日期错误[输入：" + str + "],格式["+dateFormat+"]",ex);
        }

        return date;
    }

    /**
     *  枚举基本日期格式，将字符串转化日期或者时间
     * @param str  日期字符串
     * @return
     */
    public static Date toDateForFormat(String str) {
        try {
            String[] parsePatterns = { DateFormat_DEFAULT, DateFormat_ONE,DateFormat_TWO,DateFormat_THREE,DateFormat_DATE_DEFAULT,DateFormat__DATE_ONE,DateFormat_DATE_TWO,DateFormat_DATE_THREE,DateFormat_TIME };
            return parseDate(str, parsePatterns);
        } catch (ParseException ex) {
            throw new BusinessException(ErrorCode.CODE_1000012,"转化日期错误[输入：" + str + "]",ex);
        }
    }

    /**
     * 将字符串按照默认格式转换为时间类型，默认格式"yyyy-MM-dd HH:mm:ss"
     * @param str 日期字符串
     * @return
     */
    public static Date toDateForDefault(String str){
        Date date=null;
        try {
            String  format=DateFormat_DEFAULT;
            SimpleDateFormat dateFormat=new SimpleDateFormat(format);
            if(isNotEmpty(str)){
                dateFormat.setLenient(false);
                date=dateFormat.parse(str);
            }
        } catch (Exception ex){
            throw new BusinessException(ErrorCode.CODE_1000013,"转换默认格式的时间类型错误[输入：" + str + "]",ex);
        }

        return date;
    }

    /**
     * 根据字符串符合日期格式，自适应转换为对应格式时间类型
     * @param str 日期字符串
     * @return
     */
    public static Date toDateAutoFormat(String str){
        Date date=null;
        try {
            String  format=getDateFormat(str)  ;
            SimpleDateFormat dateFormat=new SimpleDateFormat(format);
            if(isNotEmpty(str)){
                dateFormat.setLenient(false);
                date=dateFormat.parse(str);
            }
        } catch (Exception ex){
            throw new BusinessException(ErrorCode.CODE_1000014,"自适应转换时间类型错误[输入：" + str + "]",ex);
        }

        return date;
    }

    /**
     *获得字符串符合的日期格式
     * @param str  日期字符串
     * @return
     */
    public static String getDateFormat(String str){
        String  format= DateUtils.DateFormat_DEFAULT;
        Pattern p=Pattern.compile("^\\d{4}(\\-)\\d{1,2}\\1\\d{1,2}(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        if(p.matcher(str).matches()) {
            p=Pattern.compile("^\\d{4}(\\-)\\d{1,2}\\1\\d{1,2}$");
            if(p.matcher(str).matches()){
               return DateUtils.DateFormat_DATE_DEFAULT;
            } else {
                return DateUtils.DateFormat_DEFAULT;
            }
        }
        p=Pattern.compile("^\\d{4}(\\/)\\d{1,2}\\1\\d{1,2}(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        if(p.matcher(str).matches()){
            p=Pattern.compile("^\\d{4}(\\/)\\d{1,2}\\1\\d{1,2}$");
            if(p.matcher(str).matches()){
                return DateUtils.DateFormat__DATE_ONE;
            } else {
                return DateUtils.DateFormat_ONE;
            }

        }
        p=Pattern.compile("^\\d{4}(\\.)\\d{1,2}\\1\\d{1,2}(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        if(p.matcher(str).matches()){
            p=Pattern.compile("^\\d{4}(\\.)\\d{1,2}\\1\\d{1,2}$");
            if(p.matcher(str).matches()){
                return DateUtils.DateFormat_DATE_TWO;
            } else {
                return DateUtils.DateFormat_TWO;
            }

        }
        p=Pattern.compile("^\\d{4}\\d{1,2}\\d{1,2}((((0?[0-9])|([1-2][0-3]))([0-5]?[0-9])((\\s)|(([0-5]?[0-9])))))?$");
        if(p.matcher(str).matches()){
            p=Pattern.compile("^\\d{4}\\d{1,2}\\d{1,2}$");
            if(p.matcher(str).matches()){
                return DateUtils.DateFormat_DATE_THREE;
            } else {
                return DateUtils.DateFormat_THREE ;
            }

        }
        return format;
    }

    /**
     *  获取指定date所在月份的第一天，默认是0点0份0秒0毫秒
     * @param date  日期
     * @return
     */
    public static Date getFirstDateTimeForMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取指定date所在月份的最后一天，默认是23点59份59秒999毫秒
     * @param date  日期
     * @return
     */
    public static Date getLastDateTimeForMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * 输入2017-03-03 11:22:33 会得到2017-03-03 00:00:00.000
     * @param date  日期
     * @return
     */
    public static Date getDateForZero(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 输入2017-03-03 11:22:33 会得到2017-03-03 23:59:59.999
     * @param date  日期
     * @return
     */
    public static Date getDateFor59(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

}
