package com.example.admin.coolweatherapp.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 2018/1/25.
 */

public class Weather {
    public String status; //成功返回 ok, 失败返回具体原因

    public Basic basic;

    public AQI aqi;

    public Now now;

    public Suggestion suggestion;

    //由于 daily_forecast 包含的是一个数组,用 List 来引用 Forecast 类.
    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;

}
