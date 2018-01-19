package com.yucheng.byxf.mini.easyloan.ui;

import com.yucheng.byxf.comm.ScreenManager;
import com.yucheng.byxf.mini.ui.R;

import android.app.Activity;
import android.os.Bundle;

public class ShowPicture extends Activity {
	private ScreenManager screenManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		screenManager = ScreenManager.getScreenManager();
		screenManager.pushActivity(this);
		setContentView(R.layout.showpicture);
	}
}
