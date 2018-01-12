package com.example.admin.servicebestpractice;

/**
 * Created by admin on 2018/1/12.
 */

public interface DownLoadListener {
    void onProgress(int progress); //通知当前的下载进度
    void onSuccess(); //下载成功
    void onFailed(); //下载失败
    void onPaused(); //下载暂停
    void onCanceled(); //下载取消
}
