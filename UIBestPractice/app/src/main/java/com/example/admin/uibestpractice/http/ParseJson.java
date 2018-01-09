package com.example.admin.uibestpractice.http;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Richard on 2018/1/3.
 */

public class ParseJson {
    public static  <T> T parseJson(String jsonString,Class<T> targetClass) throws JSONException {
        Gson gson = new Gson();
        T t = gson.fromJson(jsonString, targetClass);
        return t;
    }
}
