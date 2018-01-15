package com.example.admin.mywebview;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DialSelectActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dial_select_layout);

        //将系统自带的标题栏隐藏
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        Button addressListBtn = findViewById(R.id.address_list_btn);
        Button directBtn = findViewById(R.id.direct_btn);

        addressListBtn.setOnClickListener(this);
        directBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.address_list_btn: //通讯录拨号

                break;
            case R.id.direct_btn: // 直接拨号

                break;
            default:
                break;
        }
    }
}
