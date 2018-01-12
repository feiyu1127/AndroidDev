package com.example.admin.networktest.httpRequest;

/**
 * Created by admin on 2018/1/12.
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
