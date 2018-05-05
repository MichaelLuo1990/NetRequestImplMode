package com.example;

import com.example.utils.HttpClientUtils;

import java.util.Hashtable;

/**
 * java类实现&测试
 */
public class HttpClientUtilsTest {

    /**
     * 相关测试url
     * http://www.weather.com.cn/data/cityinfo/101190408.html   ref 中国天气网
     * http://api.qingyunke.com/api.php?key=free&appid=0&msg=鹅鹅鹅   （智能语音）ref sojson开放平台： https://www.sojson.com
     * https://www.sojson.com/open/api/weather/json.shtml?city=%E7%A6%8F%E5%B7%9E       (天气查询）ref sojson开放平台： https://www.sojson.com
     *
     */
    private static String httpUrl = "http://api.qingyunke.com/api.php";
    private static String httpParam = "key=free&appid=0&msg=你好啊";
    private static String httpsUrl = "https://www.sojson.com/open/api/weather/json.shtml";
    private static String httpsParam = "city=福州";

    public static void main(String[] args) {
        System.out.println("=============================get 请求===========================");
        /**http get请求*/
        //url拼接形式
        String getUrlSplitStr = HttpClientUtils.get(httpUrl +"?"+ httpParam);
        System.out.println(getUrlSplitStr);
        //键值对带参形式
        Hashtable<String,String> getParams = new Hashtable<>();
        getParams.put("key", "free");
        getParams.put("appid", "0");
        getParams.put("msg", "床前明月光");
        String getParamModeStr = HttpClientUtils.get(httpUrl, getParams);
        System.out.println(getParamModeStr);
        //ssl请求-url拼接
        String sslJsonStr = HttpClientUtils.getSSL(httpsUrl + "?" + httpsParam, null);
        System.out.println(sslJsonStr);
        //ssl请求-键值对带参形式
        Hashtable<String,String> getSSLParam = new Hashtable<>();
        getSSLParam.put("city", "福州");
        String sslParamJsonStr = HttpClientUtils.getSSL(httpsUrl, getSSLParam);
        System.out.println(sslParamJsonStr);

        System.out.println("=============================post 请求===========================");
        /**http post请求*/
        //键值对带参形式
        Hashtable<String,String> postParams = new Hashtable<>();
        postParams.put("key", "free");
        postParams.put("msg", "哈哈哈");
        String postParamModeStr = HttpClientUtils.post(httpUrl, postParams);
        System.out.println(postParamModeStr);
        //json带参格式
        String jsonParamsStr = "{\"key\": \"free\",\"appid\": \"0\",\"msg\": \"13430108888\"}";
        try {
            String postJsonModeStr = HttpClientUtils.post(httpUrl, jsonParamsStr);
            System.out.println(postJsonModeStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Hashtable<String,String> postSSLParam = new Hashtable<>();
        postSSLParam.put("city", "厦门");
        String postJsonSSLStr = HttpClientUtils.postSSL(httpsUrl , postSSLParam);
        System.out.println(postJsonSSLStr);

    }

}
