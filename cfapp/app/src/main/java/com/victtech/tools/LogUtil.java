package com.victtech.tools;

import android.util.Log;

/**
 * Created by Richard on 2018/1/24.
 */

public class LogUtil {
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;
    public static int lever = VERBOSE;
    public static void v(String tag, String msg){
        if(lever <= VERBOSE){
            Log.v(tag,msg);
        }
    }
    public static void d(String tag, String msg){
        if(lever <= DEBUG){
            Log.d(tag,msg);
        }
    }
    public static void i(String tag, String msg){
        if(lever <= INFO){
            Log.i(tag,msg);
        }
    }
    public static void w(String tag, String msg){
        if(lever <= WARN){
            Log.w(tag,msg);
        }
    }
    public static void e(String tag, String msg){
        if(lever <= ERROR){
            Log.e(tag,msg);
        }
    }

}
