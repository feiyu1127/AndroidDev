package com.example.admin.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"接收我的广播",Toast.LENGTH_SHORT).show();
        abortBroadcast(); //阻断广播
    }
}
