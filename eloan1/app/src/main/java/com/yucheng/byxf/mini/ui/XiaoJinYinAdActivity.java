package com.yucheng.byxf.mini.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class XiaoJinYinAdActivity extends BaseActivity{
private ImageView back;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.xiaojinyin_ad_activity);
		super.onCreate(savedInstanceState);
		back=(ImageView) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent it=new Intent(XiaoJinYinAdActivity.this,HomeActivity.class);
				startActivity(it);
			};
		});
				
	}

}
