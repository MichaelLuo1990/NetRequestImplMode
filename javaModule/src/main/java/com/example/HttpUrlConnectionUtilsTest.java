package com.example;

import com.example.utils.HttpCallbackListener;
import com.example.utils.HttpUrlConnectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Desc jdk-httpUrlConnection工具类封装测试
 * Created by Michael on 2018/4/11.
 */

public class HttpUrlConnectionUtilsTest {

    public static void main (String[] args) {
        HttpUrlConnectionUtils.get("http://www.weather.com.cn/data/cityinfo/101190408.html", String.class, new HttpCallbackListener() {
            @Override
            public void onSuccess(Object response) {
                System.out.println(response.toString());
            }

            @Override
            public void onError(Exception e) {
                System.out.println(e.toString());
            }
        });

        Map<String,Object> getParams = new HashMap<>();
        getParams.put("key", "free");
        getParams.put("appid", "0");
        getParams.put("msg", "天气");
        HttpUrlConnectionUtils.post("http://api.qingyunke.com/api.php", getParams, String.class, new HttpCallbackListener() {
            @Override
            public void onSuccess(Object response) {
                System.out.println(response.toString());
            }

            @Override
            public void onError(Exception e) {
                System.out.println(e.toString());
            }
        });
    }

}
