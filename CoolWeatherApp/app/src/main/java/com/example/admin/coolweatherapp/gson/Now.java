package com.example.admin.coolweatherapp.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 2018/1/25.
 */

public class Now {
    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public More more;

    public class More{
        @SerializedName("txt")
        public String info;
    }
}
