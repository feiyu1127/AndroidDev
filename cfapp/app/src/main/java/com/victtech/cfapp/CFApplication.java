package com.victtech.cfapp;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;

import com.victtech.cfapp.receiver.NoAccessReceiver;
import com.victtech.http.ParseJson;
import com.victtech.model.LoginModel;
import com.victtech.model.entity.Login;
import com.victtech.tools.LogUtil;

import org.json.JSONException;

/**
 * Created by Richard on 2018/1/17.
 */

public class CFApplication extends Application {
    private static Context mContext;
    private static LocalBroadcastManager mBroadcastManager;
    private static SharedPreferences mPreferences;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mBroadcastManager = LocalBroadcastManager.getInstance(mContext);
        mPreferences = mContext.getSharedPreferences("user",MODE_PRIVATE);
        regist4010Broadcast();
    }

    public static Context getContext(){
        return mContext;
    }

    public static void ClearTmpUser(){
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.clear();
        editor.apply();
    }

    private void regist4010Broadcast(){
        IntentFilter filter = new IntentFilter();
        filter.addAction("401");
        NoAccessReceiver receiver = new NoAccessReceiver();
        mBroadcastManager.registerReceiver(receiver,filter);
    }

    public static void send401Broadcast(){
        Intent intent = new Intent("401");
        mBroadcastManager.sendBroadcast(intent);
    }

    public static String getToken() throws JSONException {
        String userInfo = mPreferences.getString("info","");
        if(userInfo!=null && !userInfo.equals("")){
            LoginModel loginModel = ParseJson.parseJson(userInfo,LoginModel.class);
            return loginModel.getData().getLast_token();
        }else{
            return "";
        }
    }

    public static void saveUserInfo(String userInfoJson){
        SharedPreferences.Editor userInfo = mPreferences.edit();
        userInfo.putString("info",userInfoJson);
        userInfo.apply();
    }

    public static Login getTmpUser() throws JSONException {
        String info = mPreferences.getString("info","");
        if(!info.equals("")){
            LogUtil.d("临时存储信息+++++++++++",info);
            LoginModel login = ParseJson.parseJson(info,LoginModel.class);
            return login.getData();
        }
        return null;
    }
}
