package com.example.richard.entity;

/**
 * Created by Richard on 2018/1/3.
 */

public class ErrorModel extends BaseModel {
    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    private String[] data;
}
