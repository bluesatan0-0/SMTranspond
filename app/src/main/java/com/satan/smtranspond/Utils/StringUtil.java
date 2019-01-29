package com.satan.smtranspond.Utils;

import java.util.regex.Pattern;

/**
 * 类名: StringUtil <p>
 * 创建人: YanJ <p>
 * 创建时间: 2018/8/16 10:32 <p>
 * 描述:
 * <p>
 * 更新人: <p>
 * 更新时间: <p>
 * 更新描述: <p>
 */
public class StringUtil {
    /**
     * 手机号正则表达式
     */
    public static final String PHONE_NUMBER_REG = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$";

    public static boolean isNotNull(String str) {
        if (str == null) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isEmpty(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean checkMobile(String mobile) {
        if (isEmpty(mobile)) {
            return false;
        }
        return Pattern.matches(PHONE_NUMBER_REG, mobile);
    }

    public static boolean equals(String str1, String str2) {
        if (isEmpty(str1) || isEmpty(str2)) {
            return false;
        }
        if (str1.equals(str2)) {
            return true;
        } else {
            return false;
        }
    }
}
