package com.example.admin.chufang.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 2018/1/29.
 */

public class Response {
    public String code; //成功返回"0"
    public String message;


    @SerializedName("data")
    public List<InnerData> mData;

}
