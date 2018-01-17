package com.example.admin.chufang.entity;

/**
 * Created by admin on 2018/1/17.
 */

public class LoginModel extends BaseModel {
        private LoginEntity data;

        public LoginEntity getData(){
            return data;
        }

        public void setData(LoginEntity data){
            this.data = data;
        }
}
