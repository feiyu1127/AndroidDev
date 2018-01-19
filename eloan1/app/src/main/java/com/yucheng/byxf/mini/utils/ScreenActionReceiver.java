package com.yucheng.byxf.mini.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class ScreenActionReceiver extends BroadcastReceiver {

	 
	private boolean isRegisterReceiver = false;
 

	public void registerScreenActionReceiver(Context mContext) {
		if (!isRegisterReceiver) {
			isRegisterReceiver = true;

			IntentFilter filter = new IntentFilter();
			filter.addAction(Intent.ACTION_SCREEN_OFF);
			filter.addAction(Intent.ACTION_SCREEN_ON);
			System.out.print("注册屏幕解锁、加锁广播接收者...");
			mContext.registerReceiver(ScreenActionReceiver.this, filter);
		}
	}

	public void unRegisterScreenActionReceiver(Context mContext) {
		if (isRegisterReceiver) {
			isRegisterReceiver = false;
			System.out.print("注销屏幕解锁、加锁广播接收者...");
			mContext.unregisterReceiver(ScreenActionReceiver.this);
		}
	}

	@Override
	public void onReceive(Context mContext, Intent intent) {
	 
		String action = intent.getAction();
		if (action.equals(Intent.ACTION_SCREEN_ON)) {
			System.out.print( "屏幕解锁广播...");
		} else if (action.equals(Intent.ACTION_SCREEN_OFF)) {
			System.out.print( "屏幕加锁广播...");
		}
	}

}