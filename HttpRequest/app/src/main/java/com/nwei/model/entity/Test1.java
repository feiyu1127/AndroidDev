package com.nwei.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 2018/2/24.
 */

public class Test1 {
    private int id;
    private String code;
    private String name;
    private String manu_name;

    private ApiVal api_val;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManu_name() {
        return manu_name;
    }

    public void setManu_name(String manu_name) {
        this.manu_name = manu_name;
    }

    public ApiVal getApi_val() {
        return api_val;
    }

    public void setApi_val(ApiVal api_val) {
        this.api_val = api_val;
    }
}
