package com.yucheng.byxf.mini.miniLoan;

import java.io.Serializable;

import com.yucheng.byxf.mini.miniLoan.fragment.MiniApplyInfo;
import com.yucheng.byxf.mini.miniLoan.fragment.MiniRiskPromptFragment;
import com.yucheng.byxf.mini.ui.R;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Window;

public class MiniLoanActivity extends Activity {
	MiniApplyInfo applyinfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mini_new_add);
		Intent intent = getIntent();
		Serializable serialize = intent.getSerializableExtra("MiniApplyInfo");
		MiniRiskPromptFragment fOne = new MiniRiskPromptFragment();
		FragmentManager fm = this.getFragmentManager();
		FragmentTransaction tx = fm.beginTransaction();
		tx.add(R.id.id_content, fOne, "CONTRACTS");
		tx.addToBackStack(null);
		tx.commit();

		// ImageView back = (ImageView) findViewById(R.id.back);
		// back.setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// finish();
		// }
		// });
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	public MiniApplyInfo getCurrentUser() {
		return applyinfo;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

}
