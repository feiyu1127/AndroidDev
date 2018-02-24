package com.nwei.utils;


import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;


public class ParseJson {
    public static  <T> T parseJson(String jsonString, Class<T> targetClass) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonString);
        Gson gson = new Gson();

        T t = gson.fromJson(jsonString, targetClass);
        return t;
    }

    public static String toJson(Object obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
}
