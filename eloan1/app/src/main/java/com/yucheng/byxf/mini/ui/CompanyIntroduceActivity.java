package com.yucheng.byxf.mini.ui;

import com.yucheng.byxf.mini.easyloan.ui.RelaxedLoanAdActivity;
import com.yucheng.byxf.mini.easyloan.ui.RelaxedLoanOneContractBook;
import com.yucheng.byxf.mini.rapidly.RepidlyLoanInfoContractBook;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.MyInOutAnimation;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class CompanyIntroduceActivity extends BaseActivity implements
		OnClickListener {
	private ImageView company_introduce_menu;
	private WebView company_introduce_webview;

	private Button button;

	private RelativeLayout backLayout;
	boolean isFlag = true;
	private LinearLayout layout;
	private Button miniButton;
	private Button generalButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.company_introduce);
		initView();
		initData();
		initListener();
	}

	private void initView() {
		company_introduce_menu = (ImageView) findViewById(R.id.company_introduce_menu);
		company_introduce_webview = (WebView) findViewById(R.id.company_introduce_webview);
		button = (Button) findViewById(R.id.button);
		layout = (LinearLayout) findViewById(R.id.layout2);
		backLayout = (RelativeLayout) findViewById(R.id.backLayout);
		miniButton = (Button) findViewById(R.id.mini_button);
		generalButton = (Button) findViewById(R.id.common_button);
	}

	private void initData() {
		String url = "file:///android_asset/html/companyintroduce.html";
		company_introduce_webview.getSettings().setJavaScriptEnabled(true);
		company_introduce_webview.getSettings().setDefaultTextEncodingName(
				"GBK");
		company_introduce_webview.loadUrl(url);
	}

	private void initListener() {
		company_introduce_menu.setOnClickListener(this);
		button.setOnClickListener(this);
		backLayout.setOnClickListener(this);
		miniButton.setOnClickListener(this);
		generalButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.company_introduce_menu:
			finish();
			break;
		case R.id.button:
			if (isFlag) {
				MyInOutAnimation.startAnimationsIn(layout);
				backLayout.setVisibility(View.VISIBLE);
			}else {
				MyInOutAnimation.startAnimationsOut(layout);
				backLayout.setVisibility(View.GONE);
			}
			isFlag = !isFlag;
			break;
		case R.id.backLayout:
			MyInOutAnimation.startAnimationsOut(layout);
			backLayout.setVisibility(View.GONE);
			isFlag = !isFlag;
			break;
		case R.id.mini_button:
			if (Contents.IS_LOGIN) {
				intent.setClass(CompanyIntroduceActivity.this,
						RepidlyLoanInfoContractBook.class);
				intent.putExtra("choiceType", "choicebull");
				startActivity(intent);
			} else {
				Toast.makeText(CompanyIntroduceActivity.this,
						"此功能需要登录才能访问！", Toast.LENGTH_SHORT).show();
				intent.setClass(CompanyIntroduceActivity.this,
						MyLoginActivity.class);
				intent.putExtra("type", "rapidly");
				startActivity(intent);
			}
			finish();
//			Toast.makeText(this, "此功能正在开发中，敬请期待", Toast.LENGTH_LONG).show();
//			MyInOutAnimation.startAnimationsOut(layout);
//			backLayout.setVisibility(View.GONE);
//			isFlag = !isFlag;
			break;
		case R.id.common_button:
			if (Contents.IS_LOGIN) {
//				intent.setClass(CompanyIntroduceActivity.this,
//						RelaxedLoanOneContractBook.class);
				//加轻松贷内页
				intent.setClass(CompanyIntroduceActivity.this,
				RelaxedLoanAdActivity.class);
				intent.putExtra("choiceType", "choicenull");
				startActivity(intent);
			} else {
				Toast.makeText(CompanyIntroduceActivity.this,
						"此功能需要登录才能访问！", Toast.LENGTH_SHORT).show();
				intent.setClass(CompanyIntroduceActivity.this,
						MyLoginActivity.class);
				intent.putExtra("type", "common");
				startActivity(intent);
			}
			finish();
			break;
		default:
			break;
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (!isFlag) {
				MyInOutAnimation.startAnimationsOut(layout);
				backLayout.setVisibility(View.GONE);
				isFlag = !isFlag;
			}else {
				finish();
			}
			
		}
		return false;
	}
}
