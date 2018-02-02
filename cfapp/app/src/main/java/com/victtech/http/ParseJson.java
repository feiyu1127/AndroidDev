package com.victtech.http;


import com.google.gson.Gson;
import com.victtech.model.ErrorModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Richard on 2018/1/3.
 */

public class ParseJson {
    public static  <T> T parseJson(String jsonString,Class<T> targetClass) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonString);
        int code = jsonObject.getInt("code");
        Gson gson = new Gson();
        if(code != 0){
            T t = (T)gson.fromJson(jsonString, ErrorModel.class);
            return t;
        }else{
            T t = gson.fromJson(jsonString, targetClass);
            return t;
        }
    }

    public static String toJson(Object obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
}
