package com.victtech.http;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import com.bumptech.glide.RequestBuilder;
import com.victtech.cfapp.BaseActivity;
import com.victtech.cfapp.CFApplication;
import com.victtech.cfapp.receiver.NoAccessReceiver;
import com.victtech.model.BaseModel;

import org.json.JSONException;

import java.io.IOException;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Richard on 2018/1/3.
 */

public class HttpUtil {
    private static String HOST = "https://chufang-api.victtech.com/api/v1/";
    public static void getRequest(final String address, final HttpCallBackLisioner callBackLisioner, @Nullable final String token){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder().url(getAddrss(address));
                if(token!=null){
                    builder.addHeader("token",token);
                }
                Request request = builder.build();
                try {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Response response = okHttpClient.newCall(request).execute();
                    String responseStr = response.body().string();
                    accessDenied(responseStr);
                    callBackLisioner.onFinish(responseStr);
                } catch (IOException e) {
                    callBackLisioner.onError(e);
                } catch (JSONException e){
                    callBackLisioner.onError(e);
                }
            }
        }).start();
    }

    public static void postRequest(final String address,final HttpCallBackLisioner callBackLisioner, @Nullable final Map<String,String> params, @Nullable final String token){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Request.Builder builder = new Request.Builder().url(getAddrss(address));
                if(token != null)
                {
                    builder.addHeader("token",token);
                }
                RequestBody requestBody = new FormBody.Builder().build();
                if(params != null){
                    FormBody.Builder bodyBuilder = new FormBody.Builder();
                    for (Map.Entry<String,String> entry:params.entrySet()) {
                        bodyBuilder.add(entry.getKey(),entry.getValue());
                    }
                    requestBody = bodyBuilder.build();
                }
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = builder.post(requestBody).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    String responseStr = response.body().string();
                    accessDenied(responseStr);
                    callBackLisioner.onFinish(responseStr);
                } catch (IOException e) {
                    callBackLisioner.onError(e);
                } catch (JSONException e){
                    callBackLisioner.onError(e);
                }
            }
        }).start();
    }

    private static String getAddrss(String address){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(HOST);
        stringBuilder.append(address);
        return stringBuilder.toString();
    }

    private static void accessDenied(String bodyString) throws JSONException {
        BaseModel base = ParseJson.parseJson(bodyString, BaseModel.class);
        if(base.getCode()==401){
            CFApplication.ClearTmpUser();
            CFApplication.send401Broadcast();
        }
    }
}
