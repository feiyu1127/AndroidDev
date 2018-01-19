package com.yucheng.byxf.mini.ui;

import java.util.ArrayList;
import java.util.List;

import com.yucheng.byxf.mini.easyloan.ui.RelaxedLoanAdActivity;
import com.yucheng.byxf.mini.easyloan.ui.RelaxedLoanOneContractBook;
import com.yucheng.byxf.mini.message.BannerResponse;
import com.yucheng.byxf.mini.message.BannerResult;
import com.yucheng.byxf.mini.rapidly.RepidlyLoanInfoContractBook;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.MyInOutAnimation;
import com.yucheng.byxf.mini.utils.StringUtils;


import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AdEloanActivity extends BaseActivity implements OnClickListener {
	private WebView webview;
	private Button bt_back;
	private TextView textview;
	public List<BannerResponse> listPicture;
	
	private Button button;
	private RelativeLayout backLayout;
	boolean isFlag = true;
	private LinearLayout layout;

	private Button miniButton;
	private Button generalButton;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ad_eloan);
		
		init();
		initListener();
	}
	
	private void init(){
		Intent it=getIntent();
		int picNum=it.getIntExtra("picNum", -1);

		String picpath=HomeActivity.listPicture.get(picNum).getHtmlName();
		String title = HomeActivity.listPicture.get(picNum).getTitle();
		bt_back=(Button) findViewById(R.id.ad_menu_e);
		bt_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AdEloanActivity.this,
						HomeActivity.class);
				startActivity(intent);	
			}
		});
		
		textview = (TextView) findViewById(R.id.textview);
		if (StringUtils.isNotBlank(title)) {
			textview.setText(title);
		}
		
		webview=(WebView) findViewById(R.id.ad_e_loan_webview);
		String url=ContantsAddress.ip+picpath;
		AdEloanActivity.this.webview.getSettings().setJavaScriptEnabled(
				true);
//		AdEloanActivity.this.webview.getSettings()
//		.setDefaultTextEncodingName("UTF-8");
		AdEloanActivity.this.webview.loadUrl(url);
		
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
	private void initListener() {
		//product_introduce_menu_e.setOnClickListener(this);
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
//			intent.setClass(AdEloanActivity.this,
//					RepidlyLoanInfoContractBook.class);
//			intent.putExtra("choiceType", "choicenull");
//			startActivity(intent);
			//添加红包
			if (Contents.IS_LOGIN) {
//				if(1==2){
				if(Contents.response != null && Contents.response.getResult() != null
						&&Contents.response.getResult().isExistFavourableActv()){
					intent.setClass(AdEloanActivity.this,
							RedPacketActivity.class);
					intent.putExtra("choiceType", "choicenull");
					startActivity(intent);
				}else{
					intent.setClass(AdEloanActivity.this,
							RedPacketActivity2.class);
					intent.putExtra("choiceType", "choicenull");
					startActivity(intent);
				}
			}
		} else {
			Toast.makeText(AdEloanActivity.this,
					"此功能需要登录才能访问！", Toast.LENGTH_SHORT).show();
			intent.setClass(AdEloanActivity.this,
					MyLoginActivity.class);
			intent.putExtra("type", "menu");
			startActivity(intent);
		}
		finish();
//		Toast.makeText(this, "此功能正在开发中，敬请期待", Toast.LENGTH_LONG).show();
//		MyInOutAnimation.startAnimationsOut(layout);
//		backLayout.setVisibility(View.GONE);
//		isFlag = !isFlag;
		break;
	case R.id.common_button:
		if (Contents.IS_LOGIN) {
//			intent.setClass(AdEloanActivity.this,
//					RelaxedLoanOneContractBook.class);
			//加轻松贷内页 
			intent.setClass(AdEloanActivity.this,
				RelaxedLoanAdActivity.class);
			intent.putExtra("choiceType", "choicenull");
			startActivity(intent);
		} else {
			Toast.makeText(AdEloanActivity.this,
					"此功能需要登录才能访问！", Toast.LENGTH_SHORT).show();
			intent.setClass(AdEloanActivity.this,
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