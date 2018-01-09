package com.example.richard.entity;

/**
 * Created by Richard on 2018/1/3.
 */

public class LoginModel extends BaseModel {
    private LoginEntity data;
    public LoginEntity getData() {
        return data;
    }


    public void setData(LoginEntity data) {
        this.data = data;
    }
}
