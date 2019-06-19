package com.example.demo.apec.utils;

import java.util.List;

/**
 * @author bogao
 * @version 2.0
 * @date 2019/6/19 10:22
 */
public enum  StringUtil {

    ;

    public static  String getListString(List<String> list){
        StringBuilder stringBuilder = new StringBuilder();
        for (String s:list) {
            stringBuilder.append(s).append(" ");
        }
        return stringBuilder.toString();
    }
}
