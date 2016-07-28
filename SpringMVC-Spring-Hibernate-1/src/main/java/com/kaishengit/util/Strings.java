package com.kaishengit.util;


import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2016/7/6.
 */
public class Strings {

    public static String toUTF8(String str){
        if(StringUtils.isNotEmpty(str)){
            try {
                return new String(str.getBytes("ISO8859-1"),"utf-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("字符串转码异常！");
            }
        }
        return "";
    }
}
