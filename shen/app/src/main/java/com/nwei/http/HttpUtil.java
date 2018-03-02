package com.nwei.http;

import android.support.annotation.Nullable;

import com.nwei.config.Config;

import java.io.IOException;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by admin on 2018/2/5.
 */

public class HttpUtil {

    private static String HOST = new Config().getHOST();

    /**
     * get 请求
     * @param address
     * @param httpCallBackLisioner
     * @param token
     */
    public static void getRequest(final String address,final HttpCallBackLisioner httpCallBackLisioner, @Nullable final String token){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder().url(getAddress(address));
                if(token != null){ //头信息中加上 token 信息
                    builder.addHeader("token",token);
                }

                Request request = builder.build();

                try {
                    Response response = okHttpClient.newCall(request).execute();
                    String responseStr = response.body().string();
                    httpCallBackLisioner.onFinish(responseStr);
                } catch (IOException e) {
                    httpCallBackLisioner.onError(e);
                }
            }
        }).start();
    }


    public static void postRequest(final String address, final HttpCallBackLisioner callBackLisioner, @Nullable final String token, @Nullable final Map<String,String> params){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request.Builder builder = new Request.Builder().url(getAddress(address));
                if(token != null){ // 添加头信息
                    builder.addHeader("device","mobile");
                    builder.addHeader("token",token);
                }

                RequestBody requestBody = new FormBody.Builder().build();
                if(params != null){ //遍历参数
                    FormBody.Builder bodyBuilder = new FormBody.Builder();
                    for(Map.Entry<String,String> entry:params.entrySet()){ //循环 Map
                        bodyBuilder.add(entry.getKey(),entry.getValue());
                    }
                    requestBody = bodyBuilder.build();
                }

                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = builder.post(requestBody).build();

                try {
                    Response response = okHttpClient.newCall(request).execute();
                    String responseStr = response.body().string();
                    callBackLisioner.onFinish(responseStr);
                } catch (IOException e) {
                    callBackLisioner.onError(e);
                }

            }
        }).start();
    }


    private static String getAddress(String address){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(HOST);
        stringBuilder.append(address);
        return stringBuilder.toString();
    }


}
