package com.sso.yt.commons.utils;

import com.sso.yt.commons.constants.error.code.ErrorCode;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;

/**
 * MessageUtils
 * 处理异常时多个变量传入消息的格式替换
 *
 * @author yitao
 * @version 1.0.0
 * @date 2016/8/29 14:07
 */
public class MessageUtils {

    private MessageUtils() {
    }

    public static String generate(String messageTemplate, Object... args) {

        if (StringUtils.isNotEmpty(messageTemplate)) {
            return MessageFormat.format(messageTemplate, args);
        } else {
            return null;
        }
    }


    public static String generate(int messageCode, String message) {
        return "【消息码：" + messageCode + "】：" + message;
    }

    public static int generateMessageCode(int module,int errorCode){
        return  (ErrorCode.MODULE+module)*ErrorCode.CODE_NUMBER+errorCode;
    }

    public static int generateModule(int module){
        return  (ErrorCode.MODULE+module);
    }

}
