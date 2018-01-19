package com.yucheng.byxf.mini.ui;

import com.yucheng.byxf.mini.domain.MiniPersonInfo;
import com.yucheng.byxf.mini.easyloan.ui.RelaxedLoanAdActivity;
import com.yucheng.byxf.mini.easyloan.ui.RelaxedLoanOneContractBook;
import com.yucheng.byxf.mini.rapidly.RepidlyLoanInfoContractBook;
import com.yucheng.byxf.mini.ui.HomeActivity.LoginAsyncTask;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;
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

public class ProductIntroduceELoanActivity extends BaseActivity implements
		OnClickListener {

	private ImageView product_introduce_menu_e;
	private WebView product_introduce_e_loan_webview;

	private Button button;

	private RelativeLayout backLayout;
	boolean isFlag = true;
	private LinearLayout layout;

	private Button miniButton;
	private Button generalButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_introduce_e_loan);
		initView();
		initData();
		initListener();
	}

	private void initView() {
		product_introduce_menu_e = (ImageView) findViewById(R.id.product_introduce_menu_e);
		product_introduce_e_loan_webview = (WebView) findViewById(R.id.product_introduce_e_loan_webview);

		miniButton = (Button) findViewById(R.id.mini_button);
		generalButton = (Button) findViewById(R.id.common_button);

		layout = (LinearLayout) findViewById(R.id.layout2);
		backLayout = (RelativeLayout) findViewById(R.id.backLayout);

		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (isFlag) {
					MyInOutAnimation.startAnimationsIn(layout);
					backLayout.setVisibility(View.VISIBLE);
				} else {
					MyInOutAnimation.startAnimationsOut(layout);
					backLayout.setVisibility(View.GONE);
				}
				isFlag = !isFlag;
				/*
				 * final Intent intent = new Intent(); OnClickListener
				 * posListener = new OnClickListener() {
				 * 
				 * @Override public void onClick(View arg0) { if
				 * (Contents.IS_LOGIN) {
				 * intent.setClass(ProductIntroduceELoanActivity.this,
				 * MiniRiskPromptLoanActivity.class); startActivity(intent); }
				 * else { Toast.makeText(ProductIntroduceELoanActivity.this,
				 * "此功能需要登录才能访问！", Toast.LENGTH_SHORT).show();
				 * intent.setClass(ProductIntroduceELoanActivity.this,
				 * MyLoginActivity.class); intent.putExtra("type", "xunhuan");
				 * startActivity(intent); } finish(); } };
				 * 
				 * OnClickListener negListener = new OnClickListener() {
				 * 
				 * @Override public void onClick(View arg0) { if
				 * (Contents.IS_LOGIN) {
				 * intent.setClass(ProductIntroduceELoanActivity.this,
				 * GeneralConsumeLoanActivity.class); startActivity(intent);
				 * }else { Toast.makeText(ProductIntroduceELoanActivity.this,
				 * "此功能需要登录才能访问！", Toast.LENGTH_SHORT).show();
				 * intent.setClass(ProductIntroduceELoanActivity.this,
				 * MyLoginActivity.class); intent.putExtra("type", "common");
				 * startActivity(intent); } finish(); } }; DialogUtil
				 * .showDialogTwoButton(ProductIntroduceELoanActivity.this,
				 * "请选择贷款类型", posListener,negListener);
				 */

			}
		});
	}

	private void initData() {
		String url = "file:///android_asset/html/productintroduceeloan.html";
		product_introduce_e_loan_webview.getSettings().setJavaScriptEnabled(
				true);
		product_introduce_e_loan_webview.getSettings()
				.setDefaultTextEncodingName("GBK");
		product_introduce_e_loan_webview.loadUrl(url);
	}

	private void initListener() {
		product_introduce_menu_e.setOnClickListener(this);
		backLayout.setOnClickListener(this);
		miniButton.setOnClickListener(this);
		generalButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.product_introduce_menu_e:
			finish();
			break;
		case R.id.backLayout:
			MyInOutAnimation.startAnimationsOut(layout);
			backLayout.setVisibility(View.GONE);
			isFlag = !isFlag;
			break;
		case R.id.mini_button:
			if (Contents.IS_LOGIN) {
				intent.setClass(ProductIntroduceELoanActivity.this,
						RepidlyLoanInfoContractBook.class);
				intent.putExtra("choiceType", "choicenull");
				startActivity(intent);
			} else {
				Toast.makeText(ProductIntroduceELoanActivity.this,
						"此功能需要登录才能访问！", Toast.LENGTH_SHORT).show();
				intent.setClass(ProductIntroduceELoanActivity.this,
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
//				intent.setClass(ProductIntroduceELoanActivity.this,
//						RelaxedLoanOneContractBook.class);
				intent.setClass(ProductIntroduceELoanActivity.this,
						RelaxedLoanAdActivity.class);
				intent.putExtra("choiceType", "choicenull");
				startActivity(intent);
			} else {
				Toast.makeText(ProductIntroduceELoanActivity.this,
						"此功能需要登录才能访问！", Toast.LENGTH_SHORT).show();
				intent.setClass(ProductIntroduceELoanActivity.this,
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
			} else {
				finish();
			}

		}
		return false;
	}
}
