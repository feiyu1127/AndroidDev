package com.nwei.shen.activities.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nwei.R;
import com.nwei.http.HttpCallBackLisioner;
import com.nwei.http.HttpUtil;
import com.nwei.http.ParseJson;
import com.nwei.model.entity.UserEntity;
import com.nwei.shen.activities.BaseActivity;
import com.nwei.shen.activities.main.MainActivity;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends BaseActivity implements View.OnClickListener{

    Context mContext = this;
    EditText loginRequestPhone;
    EditText loginRequestPassword;
    Button loginBtn;
    TextView loginToHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView register = findViewById(R.id.register);
        loginRequestPhone = findViewById(R.id.login_request_phone);
        loginRequestPassword = findViewById(R.id.login_request_password);
        loginBtn = findViewById(R.id.login_btn);

        loginToHome = findViewById(R.id.login_to_home);
        loginToHome.setOnClickListener(this);


        //登录
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginRequestPhoneStr = loginRequestPhone.getText().toString();
                String loginRequestPasswordStr = loginRequestPassword.getText().toString();
                if(loginRequestPhoneStr == null || loginRequestPhoneStr.length() <= 0 || loginRequestPasswordStr == null || loginRequestPasswordStr.length() <= 0){
                    customUIToast(mContext,"手机号或密码不能为空");
                }

               //发起请求
                Map<String,String> params = new HashMap<>();
                params.put("phone",loginRequestPhoneStr);
                params.put("password",loginRequestPhoneStr);

                HttpUtil.postRequest("login", new HttpCallBackLisioner() {
                    @Override
                    public void onFinish(String requestString) {
                        try {
                            UserEntity userEntity = ParseJson.parseJson(requestString,UserEntity.class);
                            if("0".equals(userEntity.getCode())){
                                customUIToast(mContext,userEntity.getMessage());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                },null,params);
            }
        });

        //设置光标的位置
        loginRequestPhone.setSelection(loginRequestPhone.getText().toString().length());
        loginRequestPassword.setSelection(loginRequestPassword.getText().toString().length());

        register.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                Intent registerIntent = new Intent(mContext,RegisterActivity.class);
                mContext.startActivity(registerIntent);
                break;
            case R.id.login_to_home:
                Intent loginToHome = new Intent(mContext, MainActivity.class);
                mContext.startActivity(loginToHome);
            default:
                break;
        }
    }



}
