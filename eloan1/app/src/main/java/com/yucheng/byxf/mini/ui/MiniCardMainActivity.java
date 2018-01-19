package com.yucheng.byxf.mini.ui;

import com.yucheng.byxf.util.LogManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MiniCardMainActivity extends BaseAllActivity implements
		OnClickListener {

	private ImageView imageView;
	private ImageView back;
	private TextView version;
	private RelativeLayout bt_apply;
	private RelativeLayout bt_bill;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mini_card_main_activity);

		initView();
	}

	// 获取版本名

	private void initView() {
		// TODO Auto-generated method stub
		back = (ImageView) findViewById(R.id.back);
		bt_apply = (RelativeLayout) findViewById(R.id.shenqing);
		bt_bill = (RelativeLayout) findViewById(R.id.zhangdan);

		bt_apply.setOnClickListener(this);
		bt_bill.setOnClickListener(this);
		back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.shenqing:
			Intent it = new Intent(MiniCardMainActivity.this,
					MiniCardApplyActivity.class);
			startActivity(it);
			break;
		case R.id.zhangdan:
			Intent it2 = new Intent(MiniCardMainActivity.this,
					MiniCardBillActivity.class);
			startActivity(it2);

			break;
		default:
			break;
		}
	}
}
