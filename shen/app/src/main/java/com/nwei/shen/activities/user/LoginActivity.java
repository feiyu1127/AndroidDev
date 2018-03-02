package com.nwei.shen.activities.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;
import com.mobsandgeeks.saripaar.annotation.Pattern;
import com.nwei.R;
import com.nwei.http.HttpCallBackLisioner;
import com.nwei.http.HttpUtil;
import com.nwei.http.ParseJson;
import com.nwei.model.UserModel;
import com.nwei.model.entity.User;
import com.nwei.shen.activities.BaseActivity;
import com.nwei.shen.activities.MyApplication;
import com.nwei.shen.activities.main.MainActivity;

import org.json.JSONException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LoginActivity extends BaseActivity implements View.OnClickListener, Validator.ValidationListener {

    private static final String TAG = "LoginActivity";
    Context mContext = this;
    @NotEmpty(message = "用户名不能为空")
    @Pattern(regex = "1[3578]\\d{9}", message = "手机号格式不正确")
    @Order(1)
    EditText loginRequestPhone;
    @NotEmpty(message = "密码不能为空")
    @Order(2)
    EditText loginRequestPassword;

    Button loginBtn;
    TextView loginToHome;
    Validator validator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView register = findViewById(R.id.register);
        loginRequestPhone = findViewById(R.id.login_request_phone);
        loginRequestPassword = findViewById(R.id.login_request_password);

        validator = new Validator(this);
        validator.setValidationListener(this);

        loginBtn = findViewById(R.id.login_btn);

//        loginToHome = findViewById(R.id.login_to_home);
//        loginToHome.setOnClickListener(this);

        //设置光标的位置
//        loginRequestPhone.setSelection(loginRequestPhone.getText().toString().length());
//        loginRequestPassword.setSelection(loginRequestPassword.getText().toString().length());

        loginBtn.setEnabled(false);
        loginBtn.setOnClickListener(this);

        loginRequestPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!"".equals(loginRequestPhone.getText().toString().trim()) && !"".equals(loginRequestPassword.getText().toString().trim())) {
                    loginBtn.setEnabled(true);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            loginBtn.setBackground(getResources().getDrawable(R.drawable.button_red_bg));
                        }
                    });

                    Log.d(TAG, "按钮可以点击");
                } else {
                    loginBtn.setEnabled(false);
                    loginBtn.setBackground(getResources().getDrawable(R.drawable.button_shape));
                    Log.d(TAG, "按钮不可点击 ");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        loginRequestPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!"".equals(loginRequestPhone.getText().toString().trim()) && !"".equals(loginRequestPassword.getText().toString().trim())) {
                    loginBtn.setEnabled(true);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            loginBtn.setBackground(getResources().getDrawable(R.drawable.button_red_bg));
                        }
                    });

                    Log.d(TAG, "按钮可以点击");
                } else {
                    loginBtn.setEnabled(false);
                    loginBtn.setBackground(getResources().getDrawable(R.drawable.button_shape));
                    Log.d(TAG, "按钮不可点击 ");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        register.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                validator.validate(); //表单验证
                break;
            case R.id.register:
                Intent registerIntent = new Intent(MyApplication.getContext(), RegisterActivity.class);
                MyApplication.getContext().startActivity(registerIntent);
                break;
//            case R.id.login_to_home:
//                Intent loginToHome = new Intent(mContext, MainActivity.class);
//                mContext.startActivity(loginToHome);
            default:
                break;
        }
    }

    @Override
    public void onValidationSucceeded() {
        //发起请求
        Map<String,String> params = new HashMap<>();
        params.put("phone",loginRequestPhone.getText().toString());
        params.put("password",loginRequestPassword.getText().toString());

        HttpUtil.postRequest("login", new HttpCallBackLisioner() {
            @Override
            public void onFinish(String requestString) {
                try {
                    UserModel userModel = ParseJson.parseJson(requestString,UserModel.class);
                    if("0".equals(userModel.getCode())){
                        User user = userModel.getUserData();
                        Log.d("userString", ParseJson.toJson(user));

                        //数据持久化
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext()).edit();
                        editor.putString("userInfo", ParseJson.toJson(user));
                        editor.apply();

                        Intent intent = new Intent(mContext, MainActivity.class);
                        mContext.startActivity(intent);
                    }else{
                        customUIToast(userModel.getMessage());
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

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(MyApplication.getContext());
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                customUIToast(message);
            }
//                customUIToast(message);
            Log.d(TAG, message);
        }
    }


}
