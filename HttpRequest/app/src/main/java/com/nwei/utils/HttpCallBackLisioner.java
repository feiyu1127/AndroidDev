package com.nwei.utils;



public interface HttpCallBackLisioner {
    public void onFinish(String requestString);
    public void onError(Exception e);
}
