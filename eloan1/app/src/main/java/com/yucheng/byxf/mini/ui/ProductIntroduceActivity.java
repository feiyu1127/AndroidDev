package com.yucheng.byxf.mini.ui;

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

import com.yucheng.byxf.mini.easyloan.ui.RelaxedLoanAdActivity;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.MyInOutAnimation;

public class ProductIntroduceActivity extends BaseActivity implements
		OnClickListener {

	private ImageView product_introduce_menu;
	private WebView product_introduce_webview;
	private Button button;
	
	private RelativeLayout backLayout;
	boolean isFlag = true;
	private LinearLayout layout;
	private LinearLayout layout_new;
	private Button miniButton;
	private Button generalButton;
	private Button mini_newButton;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_introduce);
		initView();
		initData();
		initListener();
	}

	private void initView() {
		product_introduce_menu = (ImageView) findViewById(R.id.product_introduce_menu);
		product_introduce_webview = (WebView) findViewById(R.id.product_introduce_webview);
		button = (Button) findViewById(R.id.button);
		layout_new = (LinearLayout) findViewById(R.id.layout2_new);
		layout = (LinearLayout) findViewById(R.id.layout2);
		backLayout = (RelativeLayout) findViewById(R.id.backLayout);
		miniButton = (Button) findViewById(R.id.mini_button);
		mini_newButton = (Button) findViewById(R.id.mini_new_button);
		generalButton = (Button) findViewById(R.id.common_button);
	}

	private void initData() {
		String url = "file:///android_asset/html/productintroduce.html";
		product_introduce_webview.getSettings().setJavaScriptEnabled(true);
		product_introduce_webview.getSettings().setDefaultTextEncodingName(
				"GBK");
		product_introduce_webview.loadUrl(url);
	}

	private void initListener() {
		product_introduce_menu.setOnClickListener(this);
		button.setOnClickListener(this);
		
		backLayout.setOnClickListener(this);
		miniButton.setOnClickListener(this);
		mini_newButton.setOnClickListener(this);
		generalButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.product_introduce_menu:
			finish();
			break;
		case R.id.button:
			if (isFlag) {
				MyInOutAnimation.startAnimationsIn(layout);
				MyInOutAnimation.startAnimationsIn(layout_new);
				backLayout.setVisibility(View.VISIBLE);
			}else {
				MyInOutAnimation.startAnimationsOut(layout);
				MyInOutAnimation.startAnimationsOut(layout_new);
				backLayout.setVisibility(View.GONE);
			}
			isFlag = !isFlag;
			break;
		case R.id.backLayout:
			MyInOutAnimation.startAnimationsOut(layout);
			MyInOutAnimation.startAnimationsOut(layout_new);
			backLayout.setVisibility(View.GONE);
			isFlag = !isFlag;
			break;
		case R.id.mini_new_button:
			//迷你球
			if (Contents.IS_LOGIN) {
				intent.setClass(ProductIntroduceActivity.this,
						MiniNewAdActivity.class);
//				intent.putExtra("choiceType", "choicenull");
				startActivity(intent);
			} else {
				Toast.makeText(ProductIntroduceActivity.this,
						"此功能需要登录才能访问！", Toast.LENGTH_SHORT).show();
				intent.setClass(ProductIntroduceActivity.this,
						MyLoginActivity.class);
				intent.putExtra("type", "menu");
				startActivity(intent);
			}
			finish();
			break;
		case R.id.mini_button:
			//添加红包
			if (Contents.IS_LOGIN) {
				//1==2
				if(Contents.response != null && Contents.response.getResult() != null
						&& Contents.response.getResult().isExistFavourableActv()){
					intent.setClass(ProductIntroduceActivity.this,
							RedPacketActivity.class);
					intent.putExtra("choiceType", "choicenull");
					startActivity(intent);
				}else{
					intent.setClass(ProductIntroduceActivity.this,
							RedPacketActivity2.class);
					intent.putExtra("choiceType", "choicenull");
					startActivity(intent);
				}
				
				
			} else {
				Toast.makeText(ProductIntroduceActivity.this,
						"此功能需要登录才能访问！", Toast.LENGTH_SHORT).show();
				intent.setClass(ProductIntroduceActivity.this,
						MyLoginActivity.class);
				intent.putExtra("type", "menu");
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
//				intent.setClass(ProductIntroduceActivity.this,
//						RelaxedLoanOneContractBook.class);
				//加轻松贷内页
				intent.setClass(ProductIntroduceActivity.this,
						RelaxedLoanAdActivity.class);
				intent.putExtra("choiceType", "choicenull");
				startActivity(intent);
			} else {
				Toast.makeText(ProductIntroduceActivity.this,
						"此功能需要登录才能访问！", Toast.LENGTH_SHORT).show();
				intent.setClass(ProductIntroduceActivity.this,
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
				MyInOutAnimation.startAnimationsOut(layout_new);
				backLayout.setVisibility(View.GONE);
				isFlag = !isFlag;
			}else {
				finish();
			}
		}
		return false;
	}
}
