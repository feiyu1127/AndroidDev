package com.victtech.model;

import com.victtech.model.entity.Login;

/**
 * Created by Richard on 2018/1/24.
 */

public class LoginModel extends BaseModel {
    private Login data;

    public Login getData() {
        return data;
    }

    public void setData(Login data) {
        this.data = data;
    }
}
