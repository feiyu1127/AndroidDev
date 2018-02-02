package com.victtech.cfapp;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.victtech.component.CustomEditText;
import com.victtech.http.HttpCallBackLisioner;
import com.victtech.http.HttpUtil;
import com.victtech.http.ParseJson;
import com.victtech.model.BaseModel;
import com.victtech.model.ErrorModel;
import com.victtech.model.LoginModel;
import com.victtech.model.entity.Login;
import com.victtech.tools.LogUtil;
import com.victtech.tools.ValidateTools;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private CustomEditText userPhone;
    private CustomEditText userPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int role = autoLogin();
        if(role==1){
            Intent intent = new Intent(CFApplication.getContext(),AdminActivity.class);
            startActivity(intent);
        }else if(role==-1){
            setContentView(R.layout.activity_login);

            //初始化背景图片
            ImageView backImg = findViewById(R.id.login_back);
            Glide.with(this)
                    .load("https://static.victtech.com/chufang/dist/bc2eff90c256c0045f04c340e3525aba.jpg")
                    .into(backImg);

            findViewById(R.id.login_btn).setOnClickListener(this);
            userPhone = findViewById(R.id.user_phone);
            userPassword = findViewById(R.id.user_password);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn:
                login();
                break;
        }
    }

    private void login(){
        ValidateTools.Rule rule = ValidateTools.Rule.REQUIRED;
        rule.setErrMsg("手机号码不能为空");
        ErrorModel err = ValidateTools.validate(rule,userPhone.getText().toString());
        if(err!=null){
            Toast.makeText(this,err.getMessage(),Toast.LENGTH_SHORT).show();
        }
        rule.setErrMsg("密码不能为空");
        err = ValidateTools.validate(rule,userPassword.getText().toString());
        if(err!=null){
            Toast.makeText(this,err.getMessage(),Toast.LENGTH_SHORT).show();
        }
        //请求api
        Map<String,String> params = new HashMap<String, String>() ;
        params.put("telephone",userPhone.getText().toString());
        params.put("password",userPassword.getText().toString());
        HttpUtil.postRequest("login", new HttpCallBackLisioner() {
            @Override
            public void onFinish(String requestString) {
                try {
                    BaseModel login = ParseJson.parseJson(requestString, LoginModel.class);
                    if(login instanceof ErrorModel){
                        UIToast(login.getMessage());
                    }else{
                        LoginModel loginModel = (LoginModel)login;
                        CFApplication.saveUserInfo(ParseJson.toJson(loginModel));//保存用户信息
                        if(loginModel.getData().getRole() ==1){
                            Intent intent = new Intent(CFApplication.getContext(),AdminActivity.class);
                            startActivity(intent);
                        }else{
                            UIToast("普通用户登陆");
                        }
                    }
                } catch (JSONException e) {
                    UIToast("解析数据出错");
                    LogUtil.e("登陆解析：",e.getMessage());
                }
            }

            @Override
            public void onError(Exception e) {
                UIToast("网络访问错误，请稍后再试");
            }
        },params,null);
    }

    private int autoLogin() {
        Login login = null;
        try {
            login = CFApplication.getTmpUser();
            if(login == null){
                return -1;
            }else{
                return login.getRole();
            }
        } catch (JSONException e) {
            UIToast("用户信息获取失败");
            return -1;
        }
    }
}
