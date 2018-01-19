package com.wujay.fund.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.apfx.utl.RegexResult;
import com.yucheng.byxf.mini.domain.MiniPersonInfo;
import com.yucheng.byxf.mini.easyloan.ui.RelaxedLoanAdActivity;
import com.yucheng.byxf.mini.easyloan.ui.SharedPreferencesUtils;
import com.yucheng.byxf.mini.message.LoginResponse;
import com.yucheng.byxf.mini.rapidly.RepidlyLoanInfoContractBook;
import com.yucheng.byxf.mini.ui.ApplicationScheduleQueryHomeActivity;
import com.yucheng.byxf.mini.ui.BaseActivity;
import com.yucheng.byxf.mini.ui.BillNewActivity;
import com.yucheng.byxf.mini.ui.ForgetPassWord1Activity;
import com.yucheng.byxf.mini.ui.ForgetPassWordActivity;
import com.yucheng.byxf.mini.ui.GeneralConsumeLoanActivity;
import com.yucheng.byxf.mini.ui.HomeActivity;
import com.yucheng.byxf.mini.ui.MessageActivity;
import com.yucheng.byxf.mini.ui.MyEloanActivity;
import com.yucheng.byxf.mini.ui.MyLoginActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.ChooseDialog;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DESUtils;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.RegexCust;
import com.yucheng.byxf.mini.utils.ChooseDialog.OnDialogClickListener;
import com.yucheng.byxf.util.IdcardValidator;

public class PassLoginActivoty extends BaseActivity {

	private Button sub;
	private EditText id;
	private EditText pass;
	LoginAsyncTask loginAsyncTask;
	private String cardNumDES;
	private String loginErrorMessage;
	private SharedPreferences sp0 = null;// 声明一个SharedPreferences
	private String FILE = "saveGesturePwd";// 用于保存SharedPreferences的文件
	private String type="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	 
		Intent intent = getIntent();
		type = intent.getStringExtra("type_2");
		
		setContentView(R.layout.pass_login_activity);
		sub = (Button) findViewById(R.id.bt_login);
		sub.setOnClickListener(this);
		id = (EditText) findViewById(R.id.bg_username);
		pass = (EditText) findViewById(R.id.bg_password);
		if(Contents.response != null && Contents.response.getResult() != null) {
			id.setText(Contents.response
					.getResult().getCipAlias());
		}
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.bt_login:
			//提交
			String loginName = id.getText().toString();
			String loginPassword = pass.getText().toString();
			
			if (null == loginName || "".equals(loginName)) {
				sub.setClickable(true);
				DialogUtil.showDialogOneButton2(PassLoginActivoty.this, "请输入用户名");

				return;
			}
			if (null == loginName || "".equals(loginPassword)) {
				sub.setClickable(true);
				DialogUtil.showDialogOneButton2(PassLoginActivoty.this, "请输入密码");
				return;
			}
			
			// 是否匹配username为空值；
			RegexResult temp_password = RegexCust.required("密码",
					loginPassword);
			if (temp_password.match) {
				if (RegexCust.userPass(loginPassword) == false) {
					DialogUtil.showDialogOneButton2(PassLoginActivoty.this,
							"密码格式错误");
					dismissProgressDialog();
					return;
				}
			} else {
				DialogUtil.showDialogOneButton2(PassLoginActivoty.this,
						"密码格式错误");
				dismissProgressDialog();
				return;
			}

			if (hasDigit(loginPassword)) {
				DialogUtil.showDialogOneButton2(PassLoginActivoty.this,
						"密码格式错误");
				dismissProgressDialog();
				return;
			}
			String macAddress = "";
			macAddress = getLocalMacAddress(PassLoginActivoty.this);

			IdcardValidator idCardValidator = new IdcardValidator();

			try {
				loginPassword = DESUtils.encryptMode(loginPassword,
						PassLoginActivoty.this);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (!idCardValidator.isValidatedAllIdcard(loginName)) {
				// handler.sendEmptyMessage(0);
				loginAsyncTask = new LoginAsyncTask();
				loginAsyncTask
						.execute(loginName, loginPassword, macAddress);
			} else {
				try {
					cardNumDES = DESUtils.encryptMode(loginName,
							PassLoginActivoty.this);
					loginAsyncTask = new LoginAsyncTask();
					loginAsyncTask.execute(cardNumDES, loginPassword,
							macAddress);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
			
			
			break;

		default:
			break;
		}

	}
	class LoginAsyncTask extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(PassLoginActivoty.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("username", params[0]));
			arg.add(new BasicNameValuePair("password", params[1]));
			arg.add(new BasicNameValuePair("longitude", Contents.longitude + ""));
			arg.add(new BasicNameValuePair("latitude", Contents.latitude + ""));
			arg.add(new BasicNameValuePair("location", Contents.locationInfo));	
			TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
			String imei = telephonyManager.getDeviceId();
			Contents.iMei = imei;
			arg.add(new BasicNameValuePair("udid", imei));
			arg.add(new BasicNameValuePair("city", Contents.city));
			arg.add(new BasicNameValuePair("area", Contents.area));
			arg.add(new BasicNameValuePair("province", Contents.province));
			return httpHelper.post(ContantsAddress.LOGIN, arg,
					LoginResponse.class);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onCancelled() {
			super.onCancelled();
		}

		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);
			dismissProgressDialog();
			 loginResponse2(result);
		}
	}

	private boolean hasDigit(String contant) {
		boolean flag = false;
		Pattern pattern = Pattern.compile("[a-zA-Z]+");
		Matcher matcher = pattern.matcher(contant);
		if (matcher.matches()) {
			flag = true;
		}
		return flag;
	}

	private String getLocalMacAddress(Context context) {
		WifiManager wifi = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		return info.getMacAddress();
	}

	private void loginResponse2(Object result) {
		LoginResponse response = (LoginResponse) result;
		if (response == null) {
			loginErrorMessage = getResources().getString(R.string.no_network);
			DialogUtil.showDialogOneButton2(this, loginErrorMessage);
			try {
			} catch (NullPointerException e) {			
				System.out.println(e);
			}

			return;
		} else {
			if (response.getCode()==0) {
				//登陆成功！ 解除密码  重新设置密码
				
				sp0 = getSharedPreferences(FILE, MODE_PRIVATE);
				Editor edit = sp0.edit();
				 
				edit.putString("isTurnOn", "No");
				edit.putString("Pwd","");
				edit.commit();
				finish();
				Intent it3 = new Intent();
				it3.setClass(PassLoginActivoty.this, LockMenuActivoty.class);
				startActivity(it3);
			 
			
			} else if ("1004".equals(response.getCode())) {
				loginErrorMessage = "用户不存在";
				DialogUtil.showDialogOneButton2(this, loginErrorMessage);
				 
			} else if (response.getCode() == 90019) {
				DialogUtil.showDialogOneButton(this, response.getMsg(),
						new OnClickListener() {
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								finish();
							}
						});
			} else {
				loginErrorMessage = response.getMsg();
				DialogUtil.showDialogOneButton2(this, loginErrorMessage);
	 
			}

		}
	}
    public boolean onKeyDown(int keyCode, KeyEvent event) {  
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {  
           if("1".equals(type)){
        		Intent it3 = new Intent();
    			it3.setClass(PassLoginActivoty.this, GestureVerifyActivity.class);
    			 
    			startActivity(it3);
    			finish();
           }else if("0".equals(type)){
        	   
           }
           else{
        	   finish();
           }
            return true;  
        } else  
            return super.onKeyDown(keyCode, event);  
    }  
}
