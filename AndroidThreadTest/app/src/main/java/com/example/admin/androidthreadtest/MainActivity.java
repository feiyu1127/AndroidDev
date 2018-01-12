package com.example.admin.androidthreadtest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int UPDATE_TEXT = 1;
    private TextView text;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_TEXT:
                    //进行 UI 操作
                    text.setText("nice to meet you~!");
                    break;
                default:
                    text.setText("lalalalalalalal~~~~~~");
                    break;
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text);
        Button changeText = findViewById(R.id.change_text);

        changeText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change_text:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = UPDATE_TEXT;
                        handler.sendMessage(message); // 将 Message 对象发送出去
                    }
                }).start();
                break;
            default:
                break;
        }
    }
}
