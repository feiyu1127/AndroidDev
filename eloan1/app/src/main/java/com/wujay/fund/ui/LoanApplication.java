package com.wujay.fund.ui;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class LoanApplication extends Application  {



 

	private void initBroadcastReceiver() {
		System.out.println("******** Self Loan is started *********");
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		filter.addAction(Intent.ACTION_SCREEN_ON);
		registerReceiver(receiver, filter);
	}

	private final BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			System.out.println(intent.getAction());
			if ("android.intent.action.SCREEN_OFF".equals(intent.getAction()))
				System.out.println("********** The Screen is locked");
			if ("android.intent.action.SCREEN_ON".equals(intent.getAction()))
				System.out.println("********** The screen is unlocked");
		}
	};

	public void abortBroadcastReceive() {
		unregisterReceiver(receiver);
	}
}
