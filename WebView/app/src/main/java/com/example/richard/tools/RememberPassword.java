package com.example.richard.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.ArrayMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Richard on 2018/1/5.
 */

public class RememberPassword {
    private  Context context;
    public RememberPassword(Context context){
        this.context = context;
    }
    public void remember(String name, String password){
        SharedPreferences.Editor editor =  context.getSharedPreferences("password",Context.MODE_PRIVATE).edit();
        editor.putString("name",name);
        editor.putString("psd",password);
        editor.apply();
    }

    public Map<String,String> load(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("password",Context.MODE_PRIVATE);
        Map<String,String> login_info = new HashMap<String,String>();
        login_info.put("name",sharedPreferences.getString("name",""));
        login_info.put("psd",sharedPreferences.getString("psd",""));
        return login_info;
    }

    public void forgot(){
        SharedPreferences.Editor editor =  context.getSharedPreferences("password",Context.MODE_PRIVATE).edit();
        editor.putString("name","");
        editor.putString("psd","");
        editor.apply();
    }
}
