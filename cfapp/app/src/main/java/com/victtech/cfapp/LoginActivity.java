package com.victtech.cfapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.victtech.component.ImageDialog;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //初始化背景图片
        ImageView backImg = findViewById(R.id.login_back);
        Glide.with(this)
                .load("https://static.victtech.com/chufang/dist/bc2eff90c256c0045f04c340e3525aba.jpg")
                .into(backImg);

        findViewById(R.id.login_btn).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn:
                Intent intent = new Intent(CFApplication.getContext(),AdminActivity.class);
                startActivity(intent);
                break;
        }
    }
}
