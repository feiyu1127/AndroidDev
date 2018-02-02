package com.victtech.cfapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.victtech.http.ParseJson;
import com.victtech.model.LoginModel;
import com.victtech.model.entity.Login;
import com.victtech.tools.ActivityContent;
import com.victtech.tools.LogUtil;

import org.json.JSONException;

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
        if(keyCode == KeyEvent.KEYCODE_BACK ){
            if(this instanceof LoginActivity){
                ActivityContent.finishAll();
                return true;
            }else {
                Login login = null;
                try {
                    login = CFApplication.getTmpUser();
                } catch (JSONException e) {
                    e.printStackTrace();
                    UIToast("用户信息获取失败");
                }
                if(login.getRole() == 1 && this instanceof AdminActivity){
                   return showExitMsg();
                }else if(login.getRole() ==2 && this instanceof CFPostActivity){
                    return showExitMsg();
                }else{
                    return super.onKeyDown(keyCode, event);
                }
            }
        }else{
            return super.onKeyDown(keyCode, event);
        }
    }

    private boolean showExitMsg(){
        if(flag == 0){
            Toast.makeText(BaseActivity.this,"再按一次将退出系统",Toast.LENGTH_SHORT).show();
            flag++;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        flag = 0;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            return false;
        }else{
            ActivityContent.finishAll();
            return true;
        }

    }

    protected void UIToast(final String msg){
        final String tmpMsg = msg;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(CFApplication.getContext(),msg,Toast.LENGTH_SHORT).show();
            }
        });
    }




}
