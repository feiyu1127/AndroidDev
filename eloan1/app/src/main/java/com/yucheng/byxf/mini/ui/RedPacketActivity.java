package com.yucheng.byxf.mini.ui;

import com.yucheng.byxf.mini.soc.SocShare;
import com.yucheng.byxf.mini.soc.SocShare2;
import com.yucheng.byxf.mini.ui.GuaGuaKa.OnFinishView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class RedPacketActivity extends BaseActivity {
	private String stateType=""; 
	private Button bt_next;
	private Button bt_share;
	private GuaGuaKa view;
	private RelativeLayout kaijiang;
	  private static Context ctx;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.redpacket_activity);
		//接收 是不是打回操作的标志；
		Intent it=getIntent();
		stateType=it.getStringExtra("choiceType");
		kaijiang=(RelativeLayout) findViewById(R.id.ly_kaijiang);
		//自定义view
		view=(GuaGuaKa) findViewById(R.id.guaugaview);
		
		view.setmOnFinishView(new GuaGuaKa.OnFinishView() {
			
			@Override
			public void onActionFinish() {
				// TODO 自动生成的方法存根
			//	  Toast.makeText(RedPacketActivity.this, "123321", Toast.LENGTH_SHORT).show();
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					// 更新主线程ＵＩ
					RedPacketActivity.this.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							view.setVisibility(View.GONE);
							kaijiang.setVisibility(View.VISIBLE);
						}
					});

				}
			}).start();
			
			}
		});
			
		
		bt_next=(Button) findViewById(R.id.bt_next);
		bt_share=(Button) findViewById(R.id.bt_share);
		
		bt_next.setOnClickListener(new OnClickListener() {
			//--->下一步
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(RedPacketActivity.this,
						RedPacketActivity2.class);
				intent.putExtra("choiceType", stateType);
				startActivity(intent);
			}
		});
		
		bt_share.setOnClickListener(new OnClickListener() {
			//----->分享
			@Override
			public void onClick(View v) {
			      SocShare2 soc1 = new SocShare2(RedPacketActivity.this);
			      soc1.initShareImage();
			      soc1.initShareText();
			      soc1.showShare(true, null, null);
				
			}
		});
		
		
	}

	
	
	
//	Handler handler=new Handler(){
//
//		@Override
//		public void handleMessage(Message msg) {
//			super.handleMessage(msg);
//			if(msg.what==210){
//			//	getReward.setClickable(true);
//				   Toast.makeText(RedPacketActivity.this, "123321", Toast.LENGTH_SHORT).show();
//			}else{
//				
//			}
//			
//		}
//		
//	};
}
