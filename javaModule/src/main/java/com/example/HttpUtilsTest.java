package com.example;

import com.example.utils.HttpUtils;

/**
 * Desc
 * Created by Michael on 2018/5/4.
 */

public class HttpUtilsTest {

    public static void main(String args[]) {
        String doGet = HttpUtils.doGet("https://www.baidu.com/");
        System.out.println(doGet);
    }
}
