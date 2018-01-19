package com.yucheng.byxf.mini.ui;

import com.yucheng.byxf.mini.soc.SocShare;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MenuShareActivity extends BaseActivity{
	private ImageView back;
	private Button bt_Shanre;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.share_activity);
		back=(ImageView) findViewById(R.id.back);
		bt_Shanre=(Button) findViewById(R.id.btshare);
		bt_Shanre.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		      SocShare soc = new SocShare(MenuShareActivity.this);
		      soc.initShareImage();
		      soc.initShareText();
		      soc.showShare(true, null, null);
			}
		});
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
	}

}