package com.example.admin.coolweatherapp.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 2018/1/25.
 */

public class Basic {

    @SerializedName("name")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update{
        @SerializedName("loc")
        public String updateTime;
    }

}
