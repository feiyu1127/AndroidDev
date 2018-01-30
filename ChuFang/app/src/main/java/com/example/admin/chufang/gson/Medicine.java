package com.example.admin.chufang.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 2018/1/30.
 */

public class Medicine {
    public int medicine_id;
    public int recipe_id;
    public int count;
    public String code;
    public String name;
    public String manu_name;
    public String manu_code;
    public String trademark;

    @SerializedName("api_val")
    public ApiVal apiVal;

    public class ApiVal{
        public String code;
        public String manuName;
        public String goodsName;
        public String goodsType;
        public String trademark;
    }
}
