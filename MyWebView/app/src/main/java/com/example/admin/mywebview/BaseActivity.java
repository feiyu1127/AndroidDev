package com.example.admin.mywebview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by admin on 2018/1/15.
 */

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("当前活动是", getClass().getSimpleName());

        //将系统自带的标题栏隐藏
//        ActionBar actionBar = getSupportActionBar();
//        if(actionBar != null){
//            actionBar.hide();
//        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //将活动添加到活动管理器
        ActivityCollector.addActivity(this);

    }


    protected void setTitleText(String title){
        View rootView = ((ViewGroup)getWindow().getDecorView().findViewById(android.R.id.content)).getChildAt(0);
        ViewGroup viewGroup = ((ViewGroup)rootView);
        for(int i=0;i<viewGroup.getChildCount();i++){
            View view = viewGroup.getChildAt(i);
            if(view instanceof TitleLayout){
                TitleLayout header = (TitleLayout) view;
                header.setTitleText(title);
            }
        }
    }


}
