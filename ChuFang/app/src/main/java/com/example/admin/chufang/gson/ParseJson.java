package com.example.admin.chufang.gson;

import com.example.admin.chufang.entity.ErrorModel;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by admin on 2018/1/17.
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
}
