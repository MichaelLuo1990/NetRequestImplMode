package com.michael.netrequestimplmode.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Desc httpUrlConnection请求工具类（JDK自带-java.net包）
 * Created by Michael on 2018/4/10.
 */

public class HttpUrlConnectionUtils {

    static ExecutorService threadPool = Executors.newCachedThreadPool();

    /**
     * @param urlStr   请求地址
     * @param cls      返回对象
     * @param listener 回调监听接口
     * @param <T>      泛形参数
     */
    public static <T> void get(final String urlStr, final Class<T> cls, final HttpCallbackListener listener) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                URL url;
                HttpURLConnection httpURLConnection = null;
                try {
                    url = new URL(urlStr); // 根据URL地址创建URL对象
                    httpURLConnection = (HttpURLConnection) url.openConnection();// 获取HttpURLConnection对象
                    httpURLConnection.setRequestMethod("GET");// 设置请求方式，默认为GET
                    httpURLConnection.setConnectTimeout(5000);// 设置连接超时
                    httpURLConnection.setReadTimeout(8000);// 设置读取超时
                    // 响应码为200表示成功，否则失败。
                    if (httpURLConnection.getResponseCode() == 200) {
                        InputStream is = httpURLConnection.getInputStream(); // 获取网络的输入流
                        BufferedReader bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                        StringBuffer buffer = new StringBuffer();
                        String line = "";
                        while ((line = bf.readLine()) != null) {
                            buffer.append(line);
                        }
                        bf.close();
                        is.close();
                        listener.onSuccess(cls);
                    } else {
                        if (listener != null) {
                            listener.onError(new Exception("response err code:" +
                                    httpURLConnection.getResponseCode()));
                        }
                    }
                } catch (MalformedURLException e) {
                    if (listener != null) {
                        listener.onError(new Exception(e));
                    }
                } catch (IOException e) {
                    if (listener != null) {
                        listener.onError(new Exception(e));
                    }
                } finally {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();// 释放资源
                    }
                }
            }
        });
    }

    /**
     * GET方法 返回数据会解析成cls对象
     *
     * @param urlStr   请求的路径
     * @param params   参数列表
     * @param cls      对象
     * @param listener 回调监听
     * @param <T>      监听泛型
     */
    public static <T> void post(final String urlStr, final Map<String, Object> params, final Class<T> cls, final HttpCallbackListener listener) {
        final StringBuffer paramsStr = new StringBuffer();
        Iterator it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry element = (Map.Entry) it.next();
            paramsStr.append(element.getKey());
            paramsStr.append("=");
            paramsStr.append(element.getValue());
            paramsStr.append("&");
        }
        if (paramsStr.length() > 0) {
            paramsStr.deleteCharAt(paramsStr.length() - 1);
        }
        // 因为网络请求是耗时操作，所以需要另外开启一个线程来执行该任务。
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                URL url;
                HttpURLConnection httpURLConnection = null;
                try {
                    url = new URL(urlStr);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setReadTimeout(8000);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
                    printWriter.write(paramsStr.toString());
                    printWriter.flush();
                    printWriter.close();
                    if (httpURLConnection.getResponseCode() == 200) {
                        // 获取网络的输入流
                        InputStream is = httpURLConnection.getInputStream();
                        BufferedReader bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                        //最好在将字节流转换为字符流的时候 进行转码
                        StringBuffer buffer = new StringBuffer();
                        String line = "";
                        while ((line = bf.readLine()) != null) {
                            buffer.append(line);
                        }
                        bf.close();
                        is.close();
                        listener.onSuccess(buffer.toString());
                    } else {
                        listener.onError(new Exception("response err code:" +
                                httpURLConnection.getResponseCode()));
                    }
                } catch (MalformedURLException e) {
                    if (listener != null) {
                        listener.onError(e);
                    }
                } catch (IOException e) {
                    if (listener != null) {
                        listener.onError(e);
                    }
                } finally {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }
            }
        });
    }

}
