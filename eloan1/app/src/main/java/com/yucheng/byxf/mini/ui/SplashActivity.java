package com.yucheng.byxf.mini.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import cn.jpush.android.api.JPushInterface;

import com.tencent.stat.StatConfig;
import com.tencent.stat.StatService;
import com.yucheng.byxf.mini.utils.ExampleUtil;

public class SplashActivity extends BaseActivity {
	//推送
    public static boolean isForeground = false;
	private static final int REQUEST_START_LOGIN = 1000;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case REQUEST_START_LOGIN:
				 startActivity(new Intent(SplashActivity.this,
				 GuideViewActivity.class));
				SplashActivity.this.finish();
				overridePendingTransition(R.anim.fading_in, R.anim.fading_out);
				break;
			default:
				break; 
			}
		}
	};
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		handler.sendEmptyMessageDelayed(REQUEST_START_LOGIN, 1000);
		
		//android.os.Debug.startMethodTracing("MTA");
		// 打开debug开关，可查看mta上报日志或错误
		// 发布时，请务必要删除本行或设为false
		StatConfig.setDebugEnable(false);
		StatService.trackCustomEvent(this, "onCreate", "");
		// 获取MTA MID等信息
		// 用户自定义UserId
		// StatConfig.setCustomUserId(this, "1234");
		//java.util.UUID.randomUUID();
		registerMessageReceiver();
	}
	
	@Override
	protected void onResume() {
		//推送
    	isForeground = true;
    	JPushInterface.onResume(this);
		super.onResume();
	}

	@Override
	protected void onPause() {
		//推送
    	isForeground = false;
    	JPushInterface.onPause(this);
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mMessageReceiver);
	}
	
	/**
     * 推送TEST
     */
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
	public static final String KEY_MESSAGE = "message";
	public static final String KEY_EXTRAS = "extras";
    
    public void registerMessageReceiver() {
		mMessageReceiver = new MessageReceiver();
		IntentFilter filter = new IntentFilter();
		filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
		filter.addAction(MESSAGE_RECEIVED_ACTION);
		registerReceiver(mMessageReceiver, filter);
	}
    
    public class MessageReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
              String messge = intent.getStringExtra(KEY_MESSAGE);
              String extras = intent.getStringExtra(KEY_EXTRAS);
              StringBuilder showMsg = new StringBuilder();
              showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
              if (!ExampleUtil.isEmpty(extras)) {
            	  showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
              }
			}
		}
	}
}
