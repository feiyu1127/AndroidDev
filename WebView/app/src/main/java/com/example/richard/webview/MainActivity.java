package com.example.richard.webview;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import java.io.File;
import java.util.Random;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    Button web_view_btn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setHeaderContent("综合页面");

        web_view_btn = findViewById(R.id.web_view_btn);
        web_view_btn.setOnClickListener(this);

        Button http_btn = findViewById(R.id.http_connect_btn);
        http_btn.setOnClickListener(this);

        Button boradcast_btn = findViewById(R.id.boradcast_btn);
        boradcast_btn.setOnClickListener(this);

        Button saveDataBtn = findViewById(R.id.save_data_btn);
        saveDataBtn.setOnClickListener(this);

        Button mediaBtn = findViewById(R.id.media_btn);
        mediaBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.web_view_btn:
                Intent webViewIntent = new Intent(MainActivity.this,WebViewActivity.class);
                startActivity(webViewIntent);
                break;
            case R.id.http_connect_btn:
                Intent httpConnection = new Intent(MainActivity.this,NetworkConnect.class);
                startActivity(httpConnection);
                break;
            case R.id.boradcast_btn:
                Intent boradCast = new Intent(MainActivity.this,BroadcastActivity.class);
                startActivity(boradCast);
                break;
            case R.id.save_data_btn:
                Intent saveData = new Intent(MainActivity.this,SaveDataActivity.class);
                startActivity(saveData);
                break;
            case R.id.media_btn:
                Random random = new Random();
                int flag = random.nextInt();
                Intent media = new Intent(MainActivity.this,MediaActivity.class);
                media.putExtra("flag",flag);
                PendingIntent pi = PendingIntent.getActivity(this,0,media,0);
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notification = new NotificationCompat.Builder(this)
                        .setContentTitle("通知测试")
                        .setContentText("这个是个通知测试信息")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                        .setContentIntent(pi)
                        .setAutoCancel(true)
//                        .setSound(Uri.fromFile(new File("/system/media/audio/Game.ogg")))//设置通知的声音
//                        .setVibrate(new long[]{0,500,500,500}) //设置通知震动
//                        .setLights(Color.BLUE,1000,1000) //设置颜色
                        .setDefaults(NotificationCompat.DEFAULT_ALL) //设置所有的为手机设置的值。
                        .build();
                manager.notify(flag,notification);
//                startActivity(media);
                break;
            default:
        }
    }
}
