package com.yucheng.byxf.mini.ui;

import com.yucheng.byxf.comm.ScreenManager;
import com.yucheng.byxf.mini.soc.SocShare;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ShareActivity extends BaseActivity{
	private ImageView bt_share; 
	private Button bt_next;
	private String type="";
	private TextView tv_register;
	private TextView tv_content;
	private TextView tv_pic;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.share_app_activity);
		tv_pic=(TextView) findViewById(R.id.tx_pic);
		tv_content=(TextView) findViewById(R.id.tx_content);
		bt_share=(ImageView) findViewById(R.id.bt_share);
		Intent intent = getIntent();
		type=intent.getStringExtra("type_ch");
		tv_register=(TextView) findViewById(R.id.tv_register);
		if(type.equals("0")){
			tv_register.setText("轻松贷");
		}else if(type.equals("1")){
			tv_register.setText("极速贷");
			tv_content.setVisibility(View.GONE);
			tv_pic.setVisibility(View.VISIBLE);
			
		}else{
			tv_register.setText("轻松e贷");
		}
//		bt_share.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//		           Intent intent=new Intent(Intent.ACTION_SEND);   
//		            //    intent.setType("image/*");   
//		                intent.setType("text/plain");
//		                intent.putExtra(Intent.EXTRA_SUBJECT, "轻松e贷-分享");   
//		                intent.putExtra(Intent.EXTRA_TEXT, "极速贷贷款一经发放即须按规定支付手续费，手续费一经收取概不退还。手续费以执行费用为准。 www.baidu.com (分享自 北银消费 轻松e贷客户端)");   
//		      有了北银消费轻松e贷，就是这么任性。
//		                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);   
//		                startActivity(Intent.createChooser(intent, getTitle()));  
//				
//			}
//		});
		
		bt_share.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
		      SocShare soc = new SocShare(ShareActivity.this);
		      soc.initShareImage();
		      soc.initShareText();
		      soc.showShare(true, null, null);
			}});
		
		
		bt_next=(Button) findViewById(R.id.button_next);
		bt_next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				//wz0113
				ScreenManager screenManager = ScreenManager
						.getScreenManager();
				screenManager.popAllActivityExceptOne();
				Intent it =new Intent(ShareActivity.this,HomeActivity.class);
				startActivity(it);
				finish();
			}
		});
		
		
	}
	public boolean onKeyDown(int keyCode,KeyEvent event) {
		//这里重写返回键
		 if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
		
		return true;
		}
		 return false;
		}
}
