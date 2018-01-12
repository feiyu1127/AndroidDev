package com.example.admin.mywebview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DialSelectActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dial_select_layout);

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
