package com.sso.yt.commons.utils;


import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.sso.yt.commons.Regular;
import com.sso.yt.commons.exceptions.ValidateException;

/**
 * Created by yt on 2016-10-19.
 * JAVA 正则验证数据
 */
public class ValidatorUtils implements Regular {

    private ValidatorUtils() {

    }

    /**
     * 判断是否数字
     *
     * @param str 输入字符串
     * @return Boolean
     */
    public static boolean isNumber(String str) {
        if (isEmpty(str))
            return false;
        return str.matches(DIGITAL);
    }

    /**
     * 判断是否整型
     *
     * @param str 输入字符串
     * @return Boolean
     */
    public static boolean isInteger(String str) {
        if (isEmpty(str))
            return false;
        return str.matches(INTEGER);
    }

    /**
     * 判断是否日期格式
     *
     * @param str 输入字符串
     * @return Boolean
     */
    public static boolean isDate(String str) {
        if (isEmpty(str))
            return false;
        return str.matches(DATE);
    }

    /**
     * 严格判断是否日期格式，包括匹配闰年，月份大小 ,天数大小
     *
     * @param str 输入字符串
     * @return Boolean
     */
    public static boolean isStrictDate(String str) {
        if (isEmpty(str))
            return false;
        return str.matches(STRICT_DATE);
    }

    /**
     * 判断是否IPV4
     *
     * @param str 输入字符串
     * @return Boolean
     */
    public static boolean isIpv4(String str) {
        if (isEmpty(str))
            return false;
        return str.matches(IPV4);
    }

    /**
     * 判断是否为纯数字0-9
     *
     * @param str 输入字符串
     * @return Boolean
     */
    public static boolean isOnlyNumberSp(String str) {
        if (isEmpty(str))
            return false;
        return str.matches(NUMBER);
    }

    /**
     * 判断是否纯字母 a-Z
     *
     * @param str 输入字符串
     * @return Boolean
     */
    public static boolean isOnlyLetterSp(String str) {
        if (isEmpty(str))
            return false;
        return str.matches(LETTER);
    }

    /**
     * 判断是否纯字母数字组合a-Z0-9
     *
     * @param str 输入字符串
     * @return Boolean
     */
    public static boolean isOnlyLetterNumber(String str) {
        if (isEmpty(str))
            return false;
        return str.matches(LETTER_NUMBER);
    }

    /**
     * 判断是否邮箱
     *
     * @param str 输入字符串
     * @return Boolean
     */
    public static boolean isEmail(String str) {
        if (isEmpty(str))
            return false;
        return str.matches(EMAIL);
    }

    /**
     * 判断是否电话号码
     *
     * @param str 输入字符串
     * @return Boolean
     */
    public static boolean isPhone(String str) {
        if (isEmpty(str))
            return false;
        return str.matches(PHONE);
    }


    /**
     * 判断是否URL
     *
     * @param str 输入字符串
     * @return Boolean
     */
    public static boolean isUrl(String str) {
        if (isEmpty(str))
            return false;
        return str.matches(URL);
    }

    /**
     * 判断是否中文
     *
     * @param str 输入字符串
     * @return Boolean
     */
    public static boolean isChinese(String str) {
        if (isEmpty(str))
            return false;
        return str.matches(CHINESE);
    }
    
    /**
     * 判断用户名是否合法(开头只能为字母或下划线,长度3-13)
     *
     * @param str 输入字符串
     * @return Boolean
     */
    public static boolean isUsername(String str) {
        if (isEmpty(str))
            return false;
        return str.matches(USERNAME);
    }

    /**
     * 判断是否QQ号码
     *
     * @param str 输入字符串
     * @return Boolean
     */
    public static boolean isQQ(String str) {
        if (isEmpty(str))
            return false;
        return str.matches(QQ);
    }

    /**
     * 判断是否合法身份证号码
     *
     * @param str 输入字符串
     * @return Boolean
     */
    public static boolean isIDCard(String str) {
        if (isEmpty(str))
            return false;
        return str.matches(IDCARD);
    }

    /**
     * 判断是不是邮政编码
     *
     * @param str 输入字符串
     * @return Boolean
     */
    public static boolean isPostCode(String str) {
        if (isEmpty(str))
            return false;
        return str.matches(POSTCODE);
    }

    /**
     * 判断有没有空格
     *
     * @param str 输入字符串
     * @return Boolean
     */
    public static boolean isSpace(String str) {
        if (isEmpty(str))
            return false;
        return !str.matches(SPACE);
    }


    /**
     * 去除所有空格
     *
     * @param str 输入字符串
     * @return String
     */
    public static String removeSpace(String str) {
        if (isEmpty(str))
            return str;
        return str.replaceAll("[\\s/ ]*", "");
    }

    /**
     * 判断前后是否有空格
     *
     * @param str 输入字符串
     * @return Boolean
     */
    public static boolean isQHSpace(String str) {
        if (isEmpty(str))
            return false;
        return str.matches(QHSPACE);
    }

    /**
     * 去除前后空格
     *
     * @param str 输入字符串
     * @return String
     */
    public static String removeQHOSpace(String str) {
        if (isEmpty(str))
            return str;
        return str.trim();
    }

    /**
     * 判断是否存在特殊字符
     *
     * @param str 输入字符串
     * @return Boolean
     */
    public static boolean isSpLetter(String str) {
        if (isEmpty(str))
            return false;
        return !str.matches("^[\\w\u4e00-\u9fa5]+$");
    }

    /**
     * 自定义正则校验
     *
     * @param str   输入字符串
     * @param regex 输入校验
     * @return Boolean
     */
    public static boolean matches(String str, String regex) {
        if (isEmpty(str))
            return false;
        return str.matches(regex);
    }

    /**
     * 针对实体校验
     * @param o 校验的实体
     */
    public static boolean validate(Object o) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(o);
        constraintViolations.forEach(constraintViolation -> {
            throw new ValidateException(Integer.parseInt(constraintViolation.getMessage()));
        });
        return true;
    }

    /**
     * 针对实体校验
     * @param o 校验的实体
     * @param groups 校验的分组
     */
    public static boolean validate(Object o, Class<?>... groups) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(o, groups);
        constraintViolations.forEach(constraintViolation -> {
            throw new ValidateException(Integer.parseInt(constraintViolation.getMessage()));
        });
        return true;
    }

    public static boolean isMonth(String month) {
        if (isEmpty(month))
            return false;
        return month.matches("(19[7-9][0-9]|20[0-9][0-9])-(0[1-9]|1[012])");
    }

    private static boolean isEmpty(String str){
         if(null==str || str.length()==0)
             return true;
        return false;
    }

}