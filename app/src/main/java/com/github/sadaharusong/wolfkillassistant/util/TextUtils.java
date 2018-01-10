package com.github.sadaharusong.wolfkillassistant.util;

import java.util.regex.Pattern;

/**
 * 字符串处理工具类
 * @author sadaharusong
 * @date 2017/9/17 0017.
 * Github : https://github.com/sadaharusong
 * Email : jacksomangel@163.com
 */

public class TextUtils {

    public static boolean isStartFromZero(String str){
        Pattern pattern = Pattern.compile("^0\\d*$");
        return pattern.matcher(str).matches();
    }

    public static boolean isEmpty(String str){
        if (android.text.TextUtils.isEmpty(str) || "null".equals(str)){
            return true;
        }
        return false;
    }
}
