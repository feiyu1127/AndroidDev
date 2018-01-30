package com.example.admin.chufang.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.admin.chufang.gson.Data;
import com.example.admin.chufang.gson.InnerData;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    public static void getRequest(final String address, final HttpCallBackLisioner callBackLisioner){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url(getAddrss(address)).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    callBackLisioner.onFinish(response.body().string());
                } catch (IOException e) {
                    callBackLisioner.onError(e);
                }
            }
        }).start();
    }

    public static void postRequest(final String address,final HttpCallBackLisioner callBackLisioner, @Nullable final Map<String,String> params){
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestBody requestBody = new FormBody.Builder().build();
                if(params != null){
                    FormBody.Builder bodyBuilder = new FormBody.Builder();
                    for (Map.Entry<String,String> entry:params.entrySet()) {
                        bodyBuilder.add(entry.getKey(),entry.getValue());
                    }
                    requestBody = bodyBuilder.build();
                }
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url(getAddrss(address)).post(requestBody).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    callBackLisioner.onFinish(response.body().string());
                } catch (IOException e) {
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


    /**
     * 解析返回的 JSON 数据
     */

    @Nullable
    public static Data JsonToData(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("ChuFang");
            String chufangContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(chufangContent,Data.class);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}
