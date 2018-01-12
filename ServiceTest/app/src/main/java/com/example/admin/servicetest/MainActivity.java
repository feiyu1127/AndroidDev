package com.example.admin.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity";
    private MyService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startService = findViewById(R.id.start_service);
        Button stopService = findViewById(R.id.stop_service);
        Button bindService = findViewById(R.id.bind_service);
        Button unbindService = findViewById(R.id.unbind_service);
        Button startIntentService = findViewById(R.id.start_intent_service);

        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);
        startIntentService.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
          switch (v.getId()){
              case R.id.start_service:
                  Intent startIntent = new Intent(this,MyService.class);
                  startService(startIntent); //开启服务
                  break;
              case R.id.stop_service:
                  Intent stopIntent = new Intent(this,MyService.class);
                  stopService(stopIntent); //停止服务
                  break;
              case R.id.bind_service:
                  Intent bindIntent = new Intent(this,MyService.class);
                  bindService(bindIntent,connection,BIND_AUTO_CREATE); //绑定服务
                  break;
              case R.id.unbind_service:
                  unbindService(connection); //解除绑定
                  break;
              case R.id.start_intent_service:
                  //打印主线程 ID
                  Log.d(TAG, "主线程ID "+ Thread.currentThread().getId());
                  Intent intentService = new Intent(this,MyIntentService.class);
                  startService(intentService);
                  break;
              default:
                  break;
          }
    }
}
