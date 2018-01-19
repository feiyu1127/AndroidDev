package com.victtech.cfapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by Richard on 2018/1/17.
 */

public class CFApplication extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext(){
        return mContext;
    }
}
