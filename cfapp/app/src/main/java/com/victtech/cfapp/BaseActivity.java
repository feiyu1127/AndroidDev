package com.victtech.cfapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.victtech.tools.ActivityContent;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Richard on 2018/1/17.
 */

public class BaseActivity extends AppCompatActivity {
    protected final String TAG = "";
    private int flag = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityContent.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityContent.removeActivity(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && this instanceof LoginActivity){
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
}
