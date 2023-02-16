package com.zjl.springsecurity.util;

public class MyStrUtil {
    public static String[] addPrefixIfNot(String[] arr, String prefix) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = prefix + arr[i];
        }
        return arr;
    }
}
