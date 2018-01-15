package com.example.admin.mywebview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by admin on 2018/1/15.
 */

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";

    private static final String DIALACTIVITY = "DialActivity"; //打电话活动


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.title);

        Log.d(TAG, getClass().getSimpleName());
        switch (getClass().getSimpleName()){ //获取当前是哪一个活动
            case DIALACTIVITY:
                Log.d(TAG, "修改文字");
                TextView titleText = findViewById(R.id.title_text);
                titleText.setText("电话亭");
                break;
            default:
                break;
        }
    }
}
