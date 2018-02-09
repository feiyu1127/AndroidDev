package com.nwei.http;

/**
 * Created by admin on 2018/2/5.
 */

public interface HttpCallBackLisioner {
    public void onFinish(String requestString);
    public void onError(Exception e);
}
