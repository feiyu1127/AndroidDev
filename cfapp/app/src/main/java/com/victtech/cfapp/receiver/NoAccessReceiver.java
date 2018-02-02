package com.victtech.cfapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.victtech.cfapp.CFApplication;
import com.victtech.cfapp.LoginActivity;
import com.victtech.tools.ActivityContent;
import com.victtech.tools.LogUtil;

/**
 * Created by Richard on 2018/1/25.
 */

public class NoAccessReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtil.d("receiver---------",context.toString());
        ActivityContent.finishAll();
        Intent intent1 = new Intent(context,LoginActivity.class);
        context.startActivity(intent1);
    }
}