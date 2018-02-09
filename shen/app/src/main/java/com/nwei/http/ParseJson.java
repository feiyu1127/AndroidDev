package com.nwei.http;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by admin on 2018/2/5.
 */

public class ParseJson {
    public static <T> T parseJson(String jsonString,Class<T> targetClass) throws JSONException{
        JSONObject jsonObject = new JSONObject(jsonString);
        Gson gson = new Gson();
        T t = (T)gson.fromJson(jsonString,targetClass);
        return t;
    }


    public static String toJson(Object obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

}
