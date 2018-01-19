package com.yucheng.byxf.mini.easyloan.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.message.LoginMessage;
import com.yucheng.byxf.mini.message.MsgKeyCodeResponse;
import com.yucheng.byxf.mini.message.Response;
import com.yucheng.byxf.mini.ui.HomeActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;

public class RelaxedLoanOneContractBook extends BaseRelaxedLoanActivity {
	private static final String _Tag = "RiskPromptLoanActivity";
	private static final Integer RET_CODE = 0;
	private WebView webview;
	private Button disagree_button, agreeButton;

	Dialog myDialog;
	public Button bt_back_common;
	
	private RelaxedLoanOneContractBook activity;
	
	private String dahuiType;
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		setContentView(R.layout.easy_loan_one_contractbook);
		super.init();
		Intent it=getIntent();
		dahuiType=it.getStringExtra("choiceType");
		activity = new RelaxedLoanOneContractBook();
		easy_loan_head.setText("贷款条款");
		agreeButton = (Button) this
				.findViewById(R.id.easy_loan_btn_agree_button);
		disagree_button = (Button) findViewById(R.id.easy_loan_btn_disagree_button);
		agreeButton.setOnClickListener(this);
		disagree_button.setOnClickListener(this);
		bt_back_common = (Button) findViewById(R.id.bt_back_common);
		bt_back_common.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it1 = new Intent(RelaxedLoanOneContractBook.this,
						HomeActivity.class);
				startActivity(it1);
				finish();
			}
		});
		initWebView();
		//二次验证
		LoginMessage result = null;
		if(Contents.response != null)
			result = Contents.response.getResult();
		Contents.response.getResult();
		if(Contents.isChoice){
			//dahui
			new ValidatorAsyncTask().execute("20", ((result == null)? "" : result
					.getIdNo()),getProInfo(),"02");
		}else{
			new ValidatorAsyncTask().execute("20", ((result == null)? "" : result
					.getIdNo()),getProInfo(),"01");
		}
	//是否需要 验证
		new IsChoiseCodeKeyAsync2Task().execute("quickApplyMsgValid");
	}

	private void initWebView() {
		//String url = "file:///android_asset/html/easyloan.html";
		//后台控制url
		String url=ContantsAddress.EASYBOOK;
		webview = (WebView) findViewById(R.id.risk_prompt_loan_webview);
		webview.getSettings().setJavaScriptEnabled(true);
		webview.getSettings().setDefaultTextEncodingName("GBK");
		webview.loadUrl(url);
	}

	class ValidatorAsyncTask extends AsyncTask<String, Object, Object> {
    @Override
    protected Object doInBackground(String... params) {
      HttpHelper httpHelper = HttpHelper.getInstance(RelaxedLoanOneContractBook.this);
      List<NameValuePair> arg = new ArrayList<NameValuePair>();
      arg.add(new BasicNameValuePair("idType", params[0]));
      arg.add(new BasicNameValuePair("idNo", params[1]));
      return httpHelper.post(ContantsAddress.RELAX_APPLY_VALIDATOR, arg, Response.class);
    }

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog();
		}

		@Override
		protected void onPostExecute(Object result) {
			
			super.onPostExecute(result);
			if (activity != null) {
				dismissProgressDialog();
				Response response = (Response) result;
				if (response != null) {
//					if (response.getCode() == 0||((response.getCode()==30306)&&(dahuiType.equals("choiceType")))) {
						if (response.getCode() == 0) {

					} else if((response.getCode()==30306)&&dahuiType.equals("choiceType")){
						
					}
						else {
						myDialog = DialogUtil.showDialogOneButton(
								RelaxedLoanOneContractBook.this, response.getMsg(),
								new OnClickListener() {

									@Override
									public void onClick(View v) {
										// TODO Auto-generated method stub
										Intent it1 = new Intent(RelaxedLoanOneContractBook.this,
												HomeActivity.class);
										startActivity(it1);
										finish();
									}
								});
					}
				} else {
					myDialog = DialogUtil.showDialogOneButton(
							RelaxedLoanOneContractBook.this, getResources().getString(R.string.no_network),
							new OnClickListener() {

								@Override
								public void onClick(View v) {
									Intent it1 = new Intent(RelaxedLoanOneContractBook.this,
											HomeActivity.class);
									startActivity(it1);
									finish();
								}
							});
				}
			}
			
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (viewIsClose()) {
				Intent it1 = new Intent(RelaxedLoanOneContractBook.this,
						HomeActivity.class);
				startActivity(it1);
				finish();
			}
		}
		return true;
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		activity = null;
	}
	
	private String getProInfo(){
		TelephonyManager telephonyManager= (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		PackageManager packageManager = getPackageManager();
        PackageInfo packInfo;
        return telephonyManager.getDeviceId();
	}
	
	   //获取是否可以------
   	class IsChoiseCodeKeyAsync2Task extends AsyncTask<String, Object, Object> {
       @Override
       protected Object doInBackground(String... params) {
         HttpHelper httpHelper = HttpHelper.getInstance(RelaxedLoanOneContractBook.this);
         List<NameValuePair> arg = new ArrayList<NameValuePair>();
         arg.add(new BasicNameValuePair("paramName",params[0]));
         return httpHelper.post(ContantsAddress.SEND_KEY_MSG, arg, MsgKeyCodeResponse.class);
       }

   		@Override
   		protected void onPreExecute() {
   			super.onPreExecute();
   		}

   		@Override
   		protected void onPostExecute(Object result) {
   			super.onPostExecute(result);
   			KeyResponse(result);
   		}
   	}
  	private void KeyResponse(Object result) {
  		MsgKeyCodeResponse response = (MsgKeyCodeResponse) result;
  		if (response == null) {
  		} else {
  			if (response.getCode() == 0) {
  				String strkey="";
  				strkey=	response.getResult().getSysConfigValue().toString();
  				if(strkey.equals("true")){
  				//Contents.isChoiceMsgKey=false;
  		          Contents.isChoiceMsgKey=true;
  				}else if(strkey.equals("false")){
  					Contents.isChoiceMsgKey=false;
  				}
  			}
  		}
  	}
}