package com.nwei.shen.activities.user;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nwei.R;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView register = findViewById(R.id.register);
        register.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                Intent registerIntent = new Intent(mContext,RegisterActivity.class);
                mContext.startActivity(registerIntent);
                break;
            default:
                break;
        }
    }


    private void mToast(Context mContext,String msg){
        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
    }

}
