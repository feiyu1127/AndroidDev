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
import android.widget.ImageView;
import android.widget.TextView;

public class AboutUsActivity extends BaseAllActivity implements OnClickListener {

	private ImageView imageView;
	private ImageView back;
	private TextView version;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aboutus_activity);

		initView();
	}

	// 获取版本名
	private String getVerName(Context context) {
		String verName = "";
		try {
			verName = context.getPackageManager().getPackageInfo(
					"com.yucheng.byxf.mini.ui", 0).versionName;
		} catch (NameNotFoundException e) {
			LogManager.i(MyLoginActivity.class, e.getMessage());
		}
		return verName;
	}

	private void initView() {
		// TODO Auto-generated method stub
		imageView = (ImageView) findViewById(R.id.call);
		back = (ImageView) findViewById(R.id.back);
		version = (TextView) findViewById(R.id.version);

		String st_version = getVerName(this);
		version.setText("Android版  V" + st_version);
		
		imageView.setOnClickListener(this);
		back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.call) {
			Intent tn = new Intent(Intent.ACTION_CALL,
					Uri.parse("tel:" + "4006695526"));
			startActivity(tn);
		} else if (v.getId() == R.id.back) {
			finish();
		}
	}
}
