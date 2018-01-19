package com.yucheng.byxf.mini.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.message.Response;
import com.yucheng.byxf.mini.message.VerifyCodeResponse;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.DialogUtil;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgetPassWord1Activity extends BaseActivity {

	private EditText et_phone_num;
	private EditText et_codekey;
	private Button bt_next;

	private TextView submit;

	private boolean isFlag = true;

	private String phone;
	private String username;
	private int time = 180;
	private String loginuser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rapidly_forget_key2);
		Intent intent = getIntent();
		phone = intent.getStringExtra("phone");
		username = intent.getStringExtra("username");
		loginuser = intent.getStringExtra("loginuser");
		initView();

	}

	private void initView() {
		// TODO Auto-generated method stub
		et_phone_num = (EditText) findViewById(R.id.et_phone_num);
		et_codekey = (EditText) findViewById(R.id.et_codekey);
		bt_next = (Button) findViewById(R.id.bt_next);
		submit = (TextView) findViewById(R.id.submit);

		et_phone_num.setText(phone);
		
		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (isFlag) {
					new VertifyCodeAsyncTask().execute(phone);
				}
			}
		});

		bt_next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String st_code = et_codekey.getText().toString();
				if ("".equals(st_code)) {
					DialogUtil.showDialogOneButton2(
							ForgetPassWord1Activity.this, "请输入验证码");
					return;
				}
				new CommitRegisterAsyncTask().execute(phone, st_code);
			}
		});
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
			};
		}.start();
	}

	class VertifyCodeAsyncTask extends AsyncTask<String, Object, Object> {
    @Override
    protected Object doInBackground(String... params) {
      HttpHelper httpHelper = HttpHelper.getInstance(ForgetPassWord1Activity.this);
      List<NameValuePair> arg = new ArrayList<NameValuePair>();
      arg.add(new BasicNameValuePair("phoneNum", params[0]));
      return httpHelper.post(ContantsAddress.SEND_MODIFY_PWD_CODE, arg, VerifyCodeResponse.class);
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
			verifyResponse(result);
		}
	}

	private void verifyResponse(Object result) {
		VerifyCodeResponse response = (VerifyCodeResponse) result;
		if (response == null) {
			DialogUtil.createDialog(this, 1, "服务器请求异常!");
			return;
		} else {
			if (response.getCode() == 0) {
				time = response.getResult();
				Toast.makeText(ForgetPassWord1Activity.this,
						"获取验证码成功" + response.getMsg(), Toast.LENGTH_LONG).show();
				timer();
				submit.setBackgroundResource(R.drawable.timer_dao);
				isFlag = !isFlag;
			} else {
				DialogUtil.createDialog(this, 4, response.getMsg());
			}
		}
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0) {
				submit.setText(time + "秒后可获取");
			} else if (msg.what == 1) {
				isFlag = !isFlag;
				submit.setBackgroundResource(R.drawable.timer);
				submit.setText("获取验证码");
			}
		};
	};

	class CommitRegisterAsyncTask extends AsyncTask<String, Object, Object> {
    @Override
    protected Object doInBackground(String... params) {
      HttpHelper httpHelper = HttpHelper.getInstance(ForgetPassWord1Activity.this);
      List<NameValuePair> arg = new ArrayList<NameValuePair>();
      arg.add(new BasicNameValuePair("phoneNum", params[0]));
      arg.add(new BasicNameValuePair("verifyCode", params[1]));
      return httpHelper.post(ContantsAddress.VALID_PHONE_VERIFY_CODE, arg, Response.class);
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
			DialogUtil.showDialogOneButton2(ForgetPassWord1Activity.this,
					"服务器请求异常!");
			return;
		} else {
			if (response.getCode() == 0) {
				Intent intent = new Intent(ForgetPassWord1Activity.this,
						ForgetPassWord2Activity.class);
				intent.putExtra("username", username);
				intent.putExtra("loginuser", loginuser);
				
				startActivity(intent);
				finish();
			} else {
				DialogUtil.showDialogOneButton2(ForgetPassWord1Activity.this,
						response.getMsg());
			}
		}
	}
}
