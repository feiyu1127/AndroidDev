package com.nwei.entity;

import com.nwei.model.User;

/**
 * Created by admin on 2018/2/5.
 */

public class UserEntity extends BaseEntity {
    private User data;

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}
