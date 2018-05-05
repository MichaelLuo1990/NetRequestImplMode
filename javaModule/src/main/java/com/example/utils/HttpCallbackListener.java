package com.example.utils;

/**
 * Desc http回调监听接口
 * Created by Michael on 2018/4/11.
 */

public interface HttpCallbackListener<T> {
    // 网络请求成功
    void onSuccess(T response);

    // 网络请求失败
    void onError(Exception e);
}
