package com.nwei.model;

import com.google.gson.annotations.SerializedName;
import com.nwei.model.entity.User;

/**
 * Created by admin on 2018/2/5.
 */

public class UserModel extends BaseModel{
    @SerializedName("data")
    private User userData;

    public User getUserData() {
        return userData;
    }

    public void setUserData(User userData) {
        this.userData = userData;
    }
}
