package com.github.sadaharusong.wolfkillassistant.util;

import java.util.regex.Pattern;

/**
 * Created by victor on 2017/1/24.
 * 字符串处理工具类
 */

public class TextUtils {

    public static boolean isStartFromZero(String str){

        Pattern pattern = Pattern.compile("^0\\d*$");
        return pattern.matcher(str).matches();
    }

    public static boolean isEmpty(String str){
        if (android.text.TextUtils.isEmpty(str) ||
                "null".equals(str))
            return true;
        return false;
    }
}
