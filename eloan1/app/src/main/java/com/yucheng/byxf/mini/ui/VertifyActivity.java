package com.yucheng.byxf.mini.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.comm.CommonContants;
import com.yucheng.byxf.mini.message.Response;
import com.yucheng.byxf.mini.message.VerifyCodeResponse;
import com.yucheng.byxf.mini.ui.MyLoginActivity.LoginAsyncTask;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.util.MD5;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class VertifyActivity extends BaseActivity implements OnClickListener {
	private Button bt_commitregister;
	private TextView reget;
	private String loginErrorMessage;
	private static final Integer RET_CODE = 0;
	private EditText et_vertify_code;
	private String phone;
	private String user_name;
	private String user_password;
	
	private LinearLayout retget_layout;
	
	private int time;
	
	private boolean isFlag = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auth_confirm);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
						| WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		Intent intent = getIntent();
		phone = intent.getStringExtra("user_mobile");
		user_name = intent.getStringExtra("user_name");
		user_password = intent.getStringExtra("pass_word");
		init();
		// 获取接口；
		new VertifyCodeAsyncTask().execute(phone);
	}

	private void init() {
		et_vertify_code = (EditText) findViewById(R.id.et_vertify_code);
		bt_commitregister = (Button) findViewById(R.id.bt_commitregister);
		reget = (TextView) findViewById(R.id.bt_retget);
		bt_commitregister.setOnClickListener(this);
		reget.setOnClickListener(this);
		retget_layout = (LinearLayout) findViewById(R.id.retget_layout);
	}
	
	private void timer() {
		new Thread() {
			public void run() {
				while (time > 0) {
					time--;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					handler.sendEmptyMessage(0);

				}
				handler.sendEmptyMessage(1);
				isFlag=true;
			};
		}.start();
	}
	
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0) {
				reget.setText(time + "秒后可获取");
				
			} else if (msg.what == 1) {
				isFlag = true;
				retget_layout.setBackgroundResource(R.drawable.timer);
				reget.setText("获取验证码");
			}
		};
	};

	class VertifyCodeAsyncTask extends AsyncTask<String, Object, Object> {
    @Override
    protected Object doInBackground(String... params) {
      HttpHelper httpHelper = HttpHelper.getInstance(VertifyActivity.this);
      List<NameValuePair> arg = new ArrayList<NameValuePair>();
      arg.add(new BasicNameValuePair("phoneNum", params[0]));
      return httpHelper.post(ContantsAddress.SEND_VERIFY_CODE, arg, VerifyCodeResponse.class);
    }

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog();
		}

		@Override
		protected void onPostExecute(Object result) {
			dismissProgressDialog();
			super.onPostExecute(result);
			loginResponse(result);
		}
	}

	private void loginResponse(Object result) {
		VerifyCodeResponse response = (VerifyCodeResponse) result;
		if (response == null) {
			loginErrorMessage = "服务器请求异常!";
			DialogUtil.createDialog(this, 1, loginErrorMessage);
			isFlag = true;
			retget_layout.setBackgroundResource(R.drawable.timer);
			reget.setText("重新获取");
			return;
		} else {
			if (RET_CODE.equals(response.getCode())) {

				Toast.makeText(VertifyActivity.this,
						"获取验证码成功" + response.getMsg(), Toast.LENGTH_LONG).show();
				time = response.getResult();
				isFlag = false;
				retget_layout.setBackgroundResource(R.drawable.timer_dao);
				timer();
			} else {
				loginErrorMessage = response.getMsg();
				DialogUtil.createDialog(this, 4, loginErrorMessage);
				isFlag = true;
				retget_layout.setBackgroundResource(R.drawable.timer);
				reget.setText("重新获取");
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_commitregister://确认注册
			String vertify_code = et_vertify_code.getText().toString().trim();
			// 获取接口；
			new CommitRegisterAsyncTask().execute(phone, vertify_code);
			break;
		case R.id.bt_retget:
			if (isFlag) {
				isFlag = !isFlag;
				new VertifyCodeAsyncTask().execute(phone);
			}
		default:
			break;
		}
	}

//	确认注册
	class CommitRegisterAsyncTask extends AsyncTask<String, Object, Object> {
    @Override
    protected Object doInBackground(String... params) {
      HttpHelper httpHelper = HttpHelper.getInstance(VertifyActivity.this);
      List<NameValuePair> arg = new ArrayList<NameValuePair>();
      arg.add(new BasicNameValuePair("phoneNum", params[0]));
      arg.add(new BasicNameValuePair("verifyCode", params[1]));
      return httpHelper.post(ContantsAddress.CONFIRM_REG_INFO, arg, Response.class);
    }

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog();
		}

		@Override
		protected void onPostExecute(Object result) {
			dismissProgressDialog();
			super.onPostExecute(result);
			CommitResponse(result);
		}
	}

	private void CommitResponse(Object result) {
		Response response = (Response) result;
		if (response == null) {
			loginErrorMessage = "服务器请求异常!";
			DialogUtil.createDialog(this, 1, loginErrorMessage);
			return;
		} else {
			if (RET_CODE.equals(response.getCode())) {
				Toast.makeText(VertifyActivity.this, "恭喜您注册成功", Toast.LENGTH_LONG).show();
				Intent mIntent = new Intent("automatic_login");
				mIntent.putExtra("user_name", user_name);
				mIntent.putExtra("user_password", user_password);
				mIntent.putExtra("isRegisterFlag", true);
				getApplicationContext().sendBroadcast(mIntent);
				VertifyActivity.this.finish();
//				Builder builder = new Builder(VertifyActivity.this);
//				builder.setTitle("提示");
//				builder.setMessage("注册成功");
//				builder.setPositiveButton("确定",
//						new DialogInterface.OnClickListener() {
//							@Override
//							public void onClick(DialogInterface dialog,
//									int which) {
////								Intent intent = new Intent(VertifyActivity.this, MyLoginActivity.class);
////								intent.putExtra("user_name", user_name);
////								startActivity(intent);
//								
//							}
//						});
//				builder.show();
			} else {
				loginErrorMessage = response.getMsg();
				DialogUtil.createDialog(this, 4, loginErrorMessage);
			}
		}
	}
}
