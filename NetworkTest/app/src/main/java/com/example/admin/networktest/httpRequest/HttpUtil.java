package com.example.admin.networktest.httpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by admin on 2018/1/12.
 */

public class HttpUtil {
    public static void sendHttpRequest(final String address,final HttpCallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    StringBuffer response = new StringBuffer();
                    String line;
                    while ((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    if(listener != null){
                        //回调 onFinish() 方法
                        listener.onFinish(response.toString());
                    }

                }catch (Exception e) {
                   if(listener != null){
                       // 回调 onError 方法
                       listener.onError(e);
                   }
                }finally {
                    if(connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }


    public static void sendOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }

}
