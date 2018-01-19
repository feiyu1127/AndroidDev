package com.yucheng.byxf.mini.xiaojinying;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.map.DemoApplication;
import com.yucheng.byxf.mini.message.QingkuanResponse;
import com.yucheng.byxf.mini.message.VerifyCodeResponse;
import com.yucheng.byxf.mini.miniLoan.fragment.NameDialogFragment;
import com.yucheng.byxf.mini.ui.BaseActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DESUtils;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.RegexUtils2;
import com.yucheng.byxf.mini.utils.RegexUtils2.Result;
import com.yucheng.byxf.mini.utils.StringUtils;

/** 
 * 类名: XiaoJinYingChangeBankCardActivity</br> 
 * 描述: 变更绑定银行卡 </br>
 * 开发人员： weiyb</br>
 * 创建时间： 2015-7-24 
 */ 

public class XiaoJinYingChangeBankCardActivity extends BaseActivity {

	// 原始开户行
	private EditText et_oldBankValue;
	// 原始卡号
	private EditText et_oldCardNum;
	// 新开户行名称
	private EditText et_newBankValue;
	private EditText et_newBankValue2;
	// 新卡号
	private EditText et_newCardNum;
	// 确认新卡号
	private EditText et_newCardNum2;
	// 查询密码
	private EditText et_selectPassword;
	// 短信验证码
	private EditText et_message;
	// 获取验证码
	private TextView message_button_change;
	// 提交按钮
	private Button tijiao_button;
	// 返回按钮
	private ImageView changeback;
	private boolean isFlag = true;
	private int time;
	private String loginErrorMessage;
	private static final Integer RET_CODE = 0;
	private Result result;

	// 信息有误

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.xiaojinyin_changebankcard_activity);
		et_oldBankValue = (EditText) findViewById(R.id.et_oldBankValue);
		et_oldCardNum = (EditText) findViewById(R.id.et_oldCardNum);
		et_newBankValue = (EditText) findViewById(R.id.et_newBankValue);
		et_newBankValue2 = (EditText) findViewById(R.id.et_newBankValue2);
		et_newCardNum = (EditText) findViewById(R.id.et_newCardNum);
		et_newCardNum2 = (EditText) findViewById(R.id.et_newCardNum2);
		et_selectPassword = (EditText) findViewById(R.id.et_selectPassword);
		et_message = (EditText) findViewById(R.id.et_message);
		message_button_change = (TextView) findViewById(R.id.message_button_change);
		tijiao_button = (Button) findViewById(R.id.tijiao_button);
		changeback = (ImageView) findViewById(R.id.changeback);

		// 返回按钮点击事件
		changeback.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(XiaoJinYingChangeBankCardActivity.this,
						XiaoJinYingMenuActivity.class);
				startActivity(intent);
			}
		});
		// 获取验证码 点击事件
		message_button_change.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new biangengAsyncTask().execute(Contents.response.getResult()
						.getCipMobile());
				timer();
			}
		});
		// 提交
		tijiao_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Result temp = null;
				String moneyMonth = et_oldCardNum.getText().toString().trim();
				temp = RegexUtils2.required("借记卡号", moneyMonth);
				if (temp.match == false) {
					result = temp;
					showEditDialog(v, result.msg);
					et_oldCardNum.requestFocus();
					return;
				}
				// 验证是否是正确卡号
				if (Contents.xiaoChaXunResult2.getAcctNo() != null) {

					if (!moneyMonth.equals(Contents.xiaoChaXunResult2
							.getAcctNo())) {

						showEditDialog(v, "请输入正确的卡号");
						et_oldCardNum.requestFocus();
						return;
					}

				}

				String moneyMonth2 = et_newCardNum.getText().toString().trim();

				// 新卡号不能与旧卡号相同
				if (moneyMonth.equals(moneyMonth2)) {
					showEditDialog(v, "新卡号不能与旧卡号相同");
					et_newCardNum.requestFocus();
					return;
				}

				temp = RegexUtils2.required("新卡号", moneyMonth2);
				if (temp.match == false) {
					result = temp;
					showEditDialog(v, result.msg);
					et_newCardNum.requestFocus();
					return;
				}

				String moneyMonth3 = et_newCardNum2.getText().toString().trim();
				temp = RegexUtils2.required("确认新卡号", moneyMonth3);
				if (temp.match == false) {
					result = temp;
					showEditDialog(v, result.msg);
					et_newCardNum2.requestFocus();
					return;
				}
				if (!moneyMonth3.equals(moneyMonth2)) {
					showEditDialog(v, "请输入相同新卡号");
					et_newCardNum2.requestFocus();
					return;
				}
				// int one=Integer.parseInt(moneyMonth3);
				// int two=Integer.parseInt(moneyMonth2);
				// if(one!=two){
				// showEditDialog(v, "两次卡号输入不一致，请重新输入");
				// et_newCardNum2.requestFocus();
				// return;
				// }
				String moneyMonth4 = et_selectPassword.getText().toString()
						.trim();
				temp = RegexUtils2.required("查询密码", moneyMonth4);
				if (temp.match == false) {
					result = temp;
					showEditDialog(v, result.msg);
					et_selectPassword.requestFocus();
					return;
				}
				String yanzhegnma = et_message.getText().toString().trim();
				temp = RegexUtils2.required("验证码", yanzhegnma);
				if (temp.match == false) {
					result = temp;
					showEditDialog(v, result.msg);
					et_message.requestFocus();
					return;
				}
				// wz
				String yuanshikaihuhang = et_oldBankValue.getText().toString()
						.trim();
				temp = RegexUtils2.required("原始开户行", yuanshikaihuhang);
				if (temp.match == false) {
					result = temp;
					showEditDialog(v, result.msg);
					et_oldBankValue.requestFocus();
					return;
				}
				// wz
				String xinkaihuhang = et_newBankValue.getText().toString()
						.trim();
				temp = RegexUtils2.required("新开户行", xinkaihuhang);
				if (temp.match == false) {
					result = temp;
					showEditDialog(v, result.msg);
					et_newBankValue.requestFocus();
					return;
				}
				// wz
				String querenxinkaihuhang = et_newBankValue2.getText()
						.toString().trim();
				temp = RegexUtils2.required("确认新开户行", querenxinkaihuhang);
				if (temp.match == false) {
					result = temp;
					showEditDialog(v, result.msg);
					et_newBankValue2.requestFocus();
					return;
				}
				if (!xinkaihuhang.equals(querenxinkaihuhang)) {
					showEditDialog(v, "确认新开户行应与新开户行相同");
					return;
				}
				new yanzhengchangeAsyncTask().execute(Contents.response
						.getResult().getCipMobile());
			}
		});

	}

	// 验证码对错
	class yanzhengchangeAsyncTask extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(XiaoJinYingChangeBankCardActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("phoneNum", params[0]));
			arg.add(new BasicNameValuePair("verifyCode", et_message.getText()
					.toString()));
			return httpHelper.post(ContantsAddress.messageYanzheng, arg,
					VerifyCodeResponse.class);
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
			yanzhengResponse(result);
		}
	}

	private void yanzhengResponse(Object result) {
		VerifyCodeResponse response = (VerifyCodeResponse) result;
		if (response == null) {
			loginErrorMessage = "验证服务器请求异常!";
			DialogUtil.createDialog(this, 1, loginErrorMessage);
			return;
		} else {
			if (RET_CODE.equals(response.getCode())) {
				String s = response.getMsg();
				Toast.makeText(XiaoJinYingChangeBankCardActivity.this,
						"验证码验证成功" + response.getMsg(), Toast.LENGTH_LONG)
						.show();
				new changebankAsyncTask().execute();
			} else {

				String ss = response.getMsg();
				Toast.makeText(XiaoJinYingChangeBankCardActivity.this,
						"验证码错误" + response.getMsg(), Toast.LENGTH_LONG).show();
			}
		}
	}

	// 获取验证码
	class biangengAsyncTask extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {

			HttpHelper httpHelper = HttpHelper
					.getInstance(XiaoJinYingChangeBankCardActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("phoneNum", params[0]));
			arg.add(new BasicNameValuePair("msgTemplate",
					"XJY_MODIFY_BANK_CARD"));
			String s = Contents.response.getResult().getIdNo();
			s = StringUtils.replacesFour(s);
			arg.add(new BasicNameValuePair("param", "****" + s));
			return httpHelper.post(ContantsAddress.Qingkuan_xiaojinying, arg,
					VerifyCodeResponse.class);
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
			loginErrorMessage = "获取验证服务器请求异常!";
			DialogUtil.createDialog(this, 1, loginErrorMessage);
			message_button_change.setText("重新获取");
			return;
		} else {
			if (RET_CODE.equals(response.getCode())) {
				String s = response.getMsg();

				Toast.makeText(XiaoJinYingChangeBankCardActivity.this,
						"获取验证码成功" + response.getMsg(), Toast.LENGTH_LONG)
						.show();
				time = 120;// response.getResult();
				timer();
			} else {
				loginErrorMessage = response.getMsg();
				DialogUtil.createDialog(this, 4, loginErrorMessage);
				message_button_change.setText("重新获取");
			}
		}
	}

	private void timer() {
		new Thread() {
			public void run() {

				while (time > 0) {
					time--;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					handler.sendEmptyMessage(0);

				}
				handler.sendEmptyMessage(1);
			};
		}.start();
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0) {
				message_button_change.setText(time + "秒后可获取");
				message_button_change.setClickable(false);
			} else if (msg.what == 1) {
				isFlag = !isFlag;
				message_button_change.setText("获取验证码");
				message_button_change.setClickable(true);
			}
		};
	};

	// 提交变更
	class changebankAsyncTask extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(XiaoJinYingChangeBankCardActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();

			// 卡号 cardNo
			arg.add(new BasicNameValuePair("cardNo", Contents.xiaoChaXunResult2
					.getCardNo()));
			// 证件类型
			arg.add(new BasicNameValuePair("applRpymIdTyp", "20"));
			// 证件号
			arg.add(new BasicNameValuePair("applRpymIdNo", Contents.response
					.getResult().getIdNo()));
			// 户名
			arg.add(new BasicNameValuePair("applRpymAcNam",
					Contents.xiaoChaXunResult2.getCustNam()));
			// 开户银行
			arg.add(new BasicNameValuePair("applRpymAcBank", "BOB"));
			// 借记卡号
			arg.add(new BasicNameValuePair("applRpymAcNo", et_newCardNum2
					.getText().toString()));
			// 查询密码
			/*
			 * arg.add(new BasicNameValuePair("newPsw", et_selectPassword
			 * .getText().toString()));
			 */

			try {
				arg.add(new BasicNameValuePair("newPsw", DESUtils.encryptMode(
						et_selectPassword.getText().toString(),
						DemoApplication.getApplication())));
			} catch (Exception e) {

				e.printStackTrace();
			}

			return httpHelper.post(ContantsAddress.changebank, arg,
					QingkuanResponse.class);
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
			qingkuanResponse(result);
		}
	}

	private void qingkuanResponse(Object result) {
		QingkuanResponse response = (QingkuanResponse) result;
		if (response == null) {
			loginErrorMessage = "请款服务器请求异常!";
			DialogUtil.createDialog(this, 1, loginErrorMessage);
			return;
		} else {
			if (RET_CODE.equals(response.getCode())) {

				// 刷新卡号
				Contents.xiaoChaXunResult2.setAcctNo(et_newCardNum2.getText()
						.toString().trim());
				String s = response.getMsg();
				DialogUtil.showDialogOneButton(
						XiaoJinYingChangeBankCardActivity.this,

						"提交成功" + s, new OnClickListener() {

							@Override
							public void onClick(View v) {
								finish();
							}
						});

				// Intent intent = new Intent();
				// intent.setClass(XiaoJinYingChangeBankCardActivity.this,
				// XiaoJinYingMenuActivity.class);
				// startActivity(intent);
				// finish();
			} else {
				DialogUtil.showDialogOneButton(
						XiaoJinYingChangeBankCardActivity.this,

						"提交失败" + response.getMsg(), new OnClickListener() {

							@Override
							public void onClick(View v) {

								finish();

							}

						});

			}
		}
	}

	public void showEditDialog(View view, String str) {
		DialogFragment myFragment = NameDialogFragment.newInstance(str);
		myFragment.show(getFragmentManager(), "EditNameDialog");
	}
}
