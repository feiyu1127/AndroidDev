package com.example.richard.webview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.richard.tools.ActivityContent;

import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.Inflater;

/**
 * Created by Richard on 2018/1/2.
 */

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    private int flag = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        ActivityContent.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityContent.removeActivity(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && this instanceof MainActivity){
            if(flag ==0 ){
                Toast.makeText(BaseActivity.this,"再按一次将退出系统",Toast.LENGTH_SHORT).show();
                flag++;
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        flag = 0;
                    }
                },1000);
                return false;
            }else{
                ActivityContent.finishAll();
                return true;
            }
        }else{
            return super.onKeyDown(keyCode, event);
        }

    }

    protected void setHeaderContent(String title){
        View rootView = ((ViewGroup)getWindow().getDecorView().findViewById(android.R.id.content)).getChildAt(0);
        ViewGroup viewGroup = ((ViewGroup)rootView);
        for(int i=0;i<viewGroup.getChildCount();i++){
            View view = viewGroup.getChildAt(i);
            if(view instanceof Header){
                Header header = (Header)view;
                header.setheaderTxt(title);
            }
        }

    }
}
