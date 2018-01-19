package com.yucheng.byxf.mini.xiaojinying;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.message.QingkuanResponse;
import com.yucheng.byxf.mini.message.VerifyCodeResponse;
import com.yucheng.byxf.mini.miniLoan.fragment.NameDialogFragment;
import com.yucheng.byxf.mini.ui.BaseActivity;
import com.yucheng.byxf.mini.ui.MyLoginActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.LogUtil;
import com.yucheng.byxf.mini.utils.NumberUtil;
import com.yucheng.byxf.mini.utils.RegexUtils2;
import com.yucheng.byxf.mini.utils.RegexUtils2.Result;
import com.yucheng.byxf.mini.utils.StringUtils;
import com.yucheng.byxf.util.LogManager;

public class XiaoJinYingQingKuanApplyActivity extends BaseActivity {
	/**
	 * 请款申请
	 * 
	 */

	// 请款金额
	private String temQkje = "";
	private String tempString;

	private TextView tx_fangkuanNum;// 放款账号
	private TextView tx_moblieNum;// 手机号
	private TextView tx_available_edu;// 可用额度
	private EditText tx_qingkuanjine2;// 请款金额
	private EditText tx_yanzhegnma2;// 验证码
	private ImageView backqingkuan;// 返回按钮
	private TextView message_button;// 获取验证码
	private Button tijiao_button;// 提交

	// ----------默认为false
	private boolean isFlag = true;

	private int time;
	private static final Integer RET_CODE = 0;
	private String loginErrorMessage;

	private Result result;
	// 信息有误
	private final int INFO_ERROR = 0;

	private String IMEI = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xiaojinyin_check_activity);
		tx_fangkuanNum = (TextView) findViewById(R.id.tx_fangkuanNum);
		tx_moblieNum = (TextView) findViewById(R.id.tx_moblieNum_check);
		tx_available_edu = (TextView) findViewById(R.id.tx_available_edu);
		tx_qingkuanjine2 = (EditText) findViewById(R.id.tx_qingkuanjine2);

		tx_yanzhegnma2 = (EditText) findViewById(R.id.tx_yanzhegnma2);
		backqingkuan = (ImageView) findViewById(R.id.backqingkuan);
		message_button = (TextView) findViewById(R.id.message_button_check);
		tijiao_button = (Button) findViewById(R.id.tijiao_button_check);
		init();
		backqingkuan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(XiaoJinYingQingKuanApplyActivity.this,
						XiaoJinYingMenuActivity.class);
				startActivity(intent);

			}
		});
		message_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String qingkuan_temp = tx_qingkuanjine2.getText().toString();

				if (tx_qingkuanjine2.getText().toString().equals("")) {
					DialogUtil.showDialogOneButton2(
							XiaoJinYingQingKuanApplyActivity.this, "请输入金额！");
					return;

				}

				if (qingkuan_temp != null) {

					if (Contents.xiaoChaXunResult2 != null) {

						String temp = String.valueOf(String.valueOf(NumberUtil
								.decimalFormat(Double.valueOf(qingkuan_temp))));
						// TODO
						LogUtil.i("TypeUtil", temp);

						if (!temp.equals(qingkuan_temp)) {

							tx_qingkuanjine2.setText(temp);
							// TODO
							LogUtil.i("TypeUtil", qingkuan_temp + "__" + temp);

							DialogUtil.showDialogOneButton2(
									XiaoJinYingQingKuanApplyActivity.this,
									"\t\t在获取验证码前，请再次确定您输入的请款金额！");

							return;
						}
					}

				}

				if (Double.parseDouble(qingkuan_temp) > Double
						.parseDouble(Contents.xiaoChaXunResult2.getAvailAmt())) {
					DialogUtil.showDialogOneButton2(
							XiaoJinYingQingKuanApplyActivity.this,
							"请款金额不能大于可用额度！");
					return;
				}

				Result temp = null;
				String moneyMonth = tx_qingkuanjine2.getText().toString()
						.trim();
				temp = RegexUtils2.doubled("请款金额", moneyMonth);
				if (temp.match == false) {
					result = temp;
					// showEditDialog(v, result.msg);

					DialogUtil.showDialogOneButton2(
							XiaoJinYingQingKuanApplyActivity.this, "请输入请款金额！");
					tx_qingkuanjine2.requestFocus();
					return;
				}

				new shenqingAsyncTask().execute(Contents.response.getResult()
						.getCipMobile());
				timer();
			}
		});
		tijiao_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Result temp = null;
				String moneyMonth = tx_qingkuanjine2.getText().toString()
						.trim();
				temp = RegexUtils2.doubled("请款金额", moneyMonth);
				if (temp.match == false) {
					result = temp;
					// showEditDialog(v, result.msg);

					DialogUtil.showDialogOneButton2(
							XiaoJinYingQingKuanApplyActivity.this, "请输入请款金额！");

					tx_qingkuanjine2.requestFocus();
					return;
				}
				String yanzhegnma = tx_yanzhegnma2.getText().toString().trim();
				temp = RegexUtils2.required("验证码", yanzhegnma);
				if (temp.match == false) {
					result = temp;
					showEditDialog(v, result.msg);
					tx_yanzhegnma2.requestFocus();
					return;
				}

				new yanzhengAsyncTask().execute(tx_moblieNum.getText()
						.toString());
			}
		});
	}

	class yanzhengAsyncTask extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(XiaoJinYingQingKuanApplyActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("phoneNum", params[0]));
			arg.add(new BasicNameValuePair("verifyCode", tx_yanzhegnma2
					.getText().toString()));
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
			System.out
					.println("--------------------------------------------------服务器异常");
			return;
		} else {
			if (RET_CODE.equals(response.getCode())) {
				String s = response.getMsg();

				new qingkuanAsyncTask().execute();
			} else {

				String ss = response.getMsg();
				Toast.makeText(XiaoJinYingQingKuanApplyActivity.this,
						"验证码错误" + response.getMsg(), Toast.LENGTH_LONG).show();
				System.out
						.println("-----------------------------------------------------验证错误");
			}
		}
	}

	public void init() {
		if (Contents.xiaoChaXunResult2 != null) {
			// 绑定放款账号
			tx_fangkuanNum.setText(Contents.xiaoChaXunResult2.getAcctNo());
			// tx_fangkuanNum.setText(Contents.xiaoChaXunResult2.getCardNo());
			tx_moblieNum.setText(Contents.response.getResult().getCipMobile());
			tx_available_edu.setText(String.valueOf(NumberUtil
					.decimalFormat(Double
							.parseDouble(Contents.xiaoChaXunResult2
									.getAvailAmt()))));
		}
	}

	class shenqingAsyncTask extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(XiaoJinYingQingKuanApplyActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("phoneNum", params[0]));
			arg.add(new BasicNameValuePair("msgTemplate", "XJY_USE_APPLY"));
			String s = Contents.response.getResult().getIdNo();
			s = StringUtils.replacesFour(s);

			arg.add(new BasicNameValuePair("param", "****" + s + ","
					+ tx_qingkuanjine2.getText().toString().trim()));
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
				message_button.setText(time + "秒后可获取");
				message_button.setClickable(false);
			} else if (msg.what == 1) {
				isFlag = !isFlag;
				message_button.setText("获取验证码");
				message_button.setClickable(true);
			}
		};
	};

	private void loginResponse(Object result) {
		VerifyCodeResponse response = (VerifyCodeResponse) result;
		if (response == null) {
			loginErrorMessage = "获取验证服务器请求异常!";
			DialogUtil.createDialog(this, 1, loginErrorMessage);
			message_button.setText("重新获取");
			return;
		} else {
			if (RET_CODE.equals(response.getCode())) {
				String s = response.getMsg();

				Toast.makeText(XiaoJinYingQingKuanApplyActivity.this,
						"获取验证码成功" + response.getMsg(), Toast.LENGTH_LONG)
						.show();
				time = 120;// response.getResult();
				timer();
			} else {
				loginErrorMessage = response.getMsg();
				DialogUtil.createDialog(this, 4, loginErrorMessage);
				message_button.setText("重新获取");
			}
		}
	}

	public void showEditDialog(View view, String str) {
		DialogFragment myFragment = NameDialogFragment.newInstance(str);
		myFragment.show(getFragmentManager(), "EditNameDialog");
	}

	// 请款申请提交
	class qingkuanAsyncTask extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(XiaoJinYingQingKuanApplyActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			// 交易日期 txDt yyyyMMdd
			// arg.add(new BasicNameValuePair("txDt", "70"));
			// 交易时间 txTime kkmmss
			// arg.add(new BasicNameValuePair("txTime", "70"));
			// 渠道 txChannel 80 客服70 手机
			arg.add(new BasicNameValuePair("txChannel", "70"));
			// 交易类型 txTyp 默认CASHOUT 用款申请
			// arg.add(new BasicNameValuePair("txTyp", "CASHOUT"));
			// 卡号 cardNo 必填
			arg.add(new BasicNameValuePair("cardNo", Contents.xiaoChaXunResult2
					.getCardNo()));
			// 交易币种 txCcyCde 默认156人民币
			// arg.add(new BasicNameValuePair("txCcyCde", "156"));
			// 交易金额 必填
			arg.add(new BasicNameValuePair("txAmt", tx_qingkuanjine2.getText()
					.toString()));
			// 证件号 idNo 必填
			arg.add(new BasicNameValuePair("idNo", Contents.xiaoChaXunResult2
					.getIdNo()));
			// 设备ID deviceId
			arg.add(new BasicNameValuePair("deviceId", IMEI));
			// 设备型号 SCH-I959
			arg.add(new BasicNameValuePair("deviceType", android.os.Build.MODEL));
			// 设备名称 deviceName
			arg.add(new BasicNameValuePair("deviceName",
					android.os.Build.PRODUCT));
			// 设备制造商 mfrs sansung
			arg.add(new BasicNameValuePair("mfrs",
					android.os.Build.MANUFACTURER));
			// 操作系统名称 osName iphone/android/miui
			arg.add(new BasicNameValuePair("osName",
					android.os.Build.VERSION.INCREMENTAL));
			// 操作系统版本 osVersion 4.2.2
			arg.add(new BasicNameValuePair("osVersion",
					android.os.Build.VERSION.RELEASE));
			// 应用类型 appName iphone/android
			arg.add(new BasicNameValuePair("appName", "android"));
			// 应用版本 appVersion 1.01
			arg.add(new BasicNameValuePair("appVersion",
					getVerName(XiaoJinYingQingKuanApplyActivity.this)));
			return httpHelper.post(ContantsAddress.qingkuan, arg,
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
				String s = response.getMsg();
				Toast.makeText(XiaoJinYingQingKuanApplyActivity.this,
						"提交成功" + response.getMsg(), Toast.LENGTH_LONG).show();
				Intent intent = new Intent();
				intent.setClass(XiaoJinYingQingKuanApplyActivity.this,
						XiaoJinYingMenuActivity.class);
				startActivity(intent);
				finish();
			} else {
				String ss = response.getMsg();
				Toast.makeText(XiaoJinYingQingKuanApplyActivity.this,
						"提交失败" + response.getMsg(), Toast.LENGTH_LONG).show();
			}
		}
	}

	private void getProInfo() {
		TelephonyManager telephonyManager = (TelephonyManager) this
				.getSystemService(Context.TELEPHONY_SERVICE);
		IMEI = telephonyManager.getDeviceId();
		PackageManager packageManager = getPackageManager();
		PackageInfo packInfo;
	}

	// 获取版本名
	public String getVerName(Context context) {
		String verName = "";
		try {
			verName = context.getPackageManager().getPackageInfo(
					"com.yucheng.byxf.mini.ui", 0).versionName;
		} catch (NameNotFoundException e) {
			LogManager.i(MyLoginActivity.class, e.getMessage());
		}
		return verName;
	}

}
