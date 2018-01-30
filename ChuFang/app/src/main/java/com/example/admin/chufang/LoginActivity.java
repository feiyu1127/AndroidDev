package com.example.admin.chufang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.chufang.entity.ErrorModel;
import com.example.admin.chufang.entity.LoginEntity;
import com.example.admin.chufang.entity.LoginModel;
import com.example.admin.chufang.gson.ParseJson;
import com.example.admin.chufang.utils.HttpCallBackLisioner;
import com.example.admin.chufang.utils.HttpUtil;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "LoginActivity";
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.login_toolbar);
        if (toolbar != null) {
            toolbar.setTitle("");
        }
        setSupportActionBar(toolbar);

        Button btnLogin = findViewById(R.id.login_btn);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn:
                String usernameText = username.getText().toString();
                String passwordText = password.getText().toString();

                if(usernameText == null || passwordText == null || usernameText.isEmpty() || passwordText.isEmpty()){
                    Toast.makeText(this,"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
                    break;
                }

                Map<String,String> loginData = new HashMap<>();
                loginData.put("telephone",usernameText);
                loginData.put("password",passwordText);

                HttpUtil.postRequest("login", new HttpCallBackLisioner() {
                    @Override
                    public void onFinish(String requestString) {
                        showJsonResponse(requestString);
                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(LoginActivity.this,"网络错误",Toast.LENGTH_SHORT).show();
                    }
                },loginData);
                break;
            default:
                break;
        }
    }


    private void showJsonResponse(final String jsonResponse){
        try {
            Object baseModel = ParseJson.parseJson(jsonResponse, LoginModel.class);

            if(baseModel instanceof ErrorModel){ //判断左边的对象是不是右边类的实体
                ErrorModel errorModel = (ErrorModel) baseModel;
                showToastInThread(errorModel.getMessage());

            }else{
                LoginModel loginModel = (LoginModel) baseModel;
                final LoginEntity loginEntity = loginModel.getData();

                //登录成功调转
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                }).start();

                Log.d(TAG, "登录成功" + loginEntity.getName());
            }
        } catch (JSONException e) {
            showToastInThread("返回解析json错误");
        }

    }


    private void showToastInThread(final String msgToast){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this,msgToast,Toast.LENGTH_SHORT).show();
            }
        });
    }


}
