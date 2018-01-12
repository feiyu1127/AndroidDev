package com.example.admin.servicetest;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by admin on 2018/1/12.
 */

public class MyIntentService extends IntentService {

    private static final String TAG = "MyIntentService";

    public MyIntentService(){
        super("MyIntentService"); //调用父类的有参构造方法
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "当前线程的id是" + Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: executed");
    }
}
