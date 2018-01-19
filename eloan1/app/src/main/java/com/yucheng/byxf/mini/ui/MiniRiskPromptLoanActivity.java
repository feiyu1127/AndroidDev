package com.yucheng.byxf.mini.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.comm.MyApplication;
import com.yucheng.byxf.comm.ScreenManager;
import com.yucheng.byxf.mini.message.CustInfoResponseResult;
import com.yucheng.byxf.mini.message.LoginResponse;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DESUtils;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.ViewTools;

public class MiniRiskPromptLoanActivity extends BaseActivity {
	/**
	 * MINI合约书!
	 * 
	 */
	
	
	private static final String _Tag = "MiniRiskPromptLoanActivity";
	private WebView webview;//

	private Button agreeButton;
	private Button disagreeButton;
	private ImageView iv_menu_common;

	@Override
	protected void onStart() {
		super.onStart();
		if (MyApplication.isExit()) {
			finish();
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.risk_prompt_mini);
		ScreenManager.getScreenManager().pushActivity(this);
		iv_menu_common = (ImageView) findViewById(R.id.iv_menu_common);
		iv_menu_common.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		agreeButton = (Button) this
				.findViewById(R.id.risk_prompt_loan_btn_agree_button);
		agreeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (Contents.custInfoResponseResult != null) {
					Intent intent = getIntent();
					intent.setClass(MiniRiskPromptLoanActivity.this,
							MiniCardScanningActivity.class);
					startActivity(intent);
				}else {
//					String card = null;
					try {
//						card = DESUtils.decryptMode(Contents.response.getResult().getIdNo(),MiniRiskPromptLoanActivity.this);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					System.out.println("card==>"+Contents.response.getResult().getIdNo());
					String idNo = "";
					if(Contents.response != null && Contents.response.getResult() != null)
						idNo = Contents.response.getResult().getIdNo();
					new getContactInfo().execute("20",idNo);
				}
			}
		});
		disagreeButton = (Button) this
				.findViewById(R.id.risk_prompt_loan_btn_disagree_button);
		disagreeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		// String url = "file:///android_asset/easyloan.html";
		String url = "file:///android_asset/html/easyloan.html";
		webview = (WebView) findViewById(R.id.risk_prompt_loan_webview);
		webview.getSettings().setJavaScriptEnabled(true);
		webview.getSettings().setDefaultTextEncodingName("GBK");
		webview.loadUrl(url);

	}

	class getContactInfo extends AsyncTask<String, Object, Object> {

    @Override
    protected Object doInBackground(String... params) {
      HttpHelper httpHelper = HttpHelper.getInstance(MiniRiskPromptLoanActivity.this);
      List<NameValuePair> arg = new ArrayList<NameValuePair>();
      arg.add(new BasicNameValuePair("idType", params[0]));
      arg.add(new BasicNameValuePair("idNo", params[1]));
      return httpHelper.post(ContantsAddress.CUST_INFO, arg, CustInfoResponseResult.class);
    }
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			showProgressDialog();
		}

		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			dismissProgressDialog();
			CustInfoResponseResult response = (CustInfoResponseResult) result;
			if (response == null) {
				Toast.makeText(MiniRiskPromptLoanActivity.this, getResources().getString(R.string.no_network), Toast.LENGTH_LONG).show();
			}else {
				if (response.getCode() == 0) {
					Contents.custInfoResponseResult = response;
				}else {
					Toast.makeText(MiniRiskPromptLoanActivity.this, response.getMsg(), Toast.LENGTH_LONG).show();
				}
				Intent intent = getIntent();
				intent.setClass(MiniRiskPromptLoanActivity.this,
						MiniCardScanningActivity.class);
				startActivity(intent);
			}
		}
	}

}
