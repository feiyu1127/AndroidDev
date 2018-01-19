package com.yucheng.byxf.mini.xiaojinying;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.message.HuankuanResponse;
import com.yucheng.byxf.mini.message.VerifyCodeResponse;
import com.yucheng.byxf.mini.ui.BaseActivity;
import com.yucheng.byxf.mini.ui.MyLoginActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.NameDialogFragment;
import com.yucheng.byxf.mini.utils.NumberUtil;
import com.yucheng.byxf.mini.utils.RegexUtils2;
import com.yucheng.byxf.mini.utils.RegexUtils2.Result;
import com.yucheng.byxf.mini.utils.StringUtils;
import com.yucheng.byxf.util.LogManager;

public class XiaoJinYingHuanKuanApplyActivity extends BaseActivity {
	/**
	 * 还款申请
	 */
	private TextView tx_huankuanNum;// 还款账号

	private TextView tx_moblieNum;// 手机号

	private TextView et_huankuandate;// 还款日期

	private EditText et_huankuanjine2;// 还款金额

	private EditText et_yanzhegnma2;// 验证码

	private TextView message_button2;// 获取验证码

	private Button tijiao_button;// 提交

	private ImageView backhuankuan;// 返回

	private Result result;

	// 信息有误
	private final int INFO_ERROR = 0;

	private int time;

	private static final Integer RET_CODE = 0;

	private String loginErrorMessage;

	private boolean isFlag = true;

	private String IMEI = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.xiaojinyin_huankuan_activity);
		tx_huankuanNum = (TextView) findViewById(R.id.tx_huankuanNum);
		tx_moblieNum = (TextView) findViewById(R.id.tx_moblieNum);
		et_huankuandate = (TextView) findViewById(R.id.et_huankuandate);
		et_huankuanjine2 = (EditText) findViewById(R.id.et_huankuanjine2);
		et_yanzhegnma2 = (EditText) findViewById(R.id.et_yanzhegnma2);
		message_button2 = (TextView) findViewById(R.id.message_button_huankuan);
		tijiao_button = (Button) findViewById(R.id.tijiao_button_huankuan);
		backhuankuan = (ImageView) findViewById(R.id.backhuankuan);
		init();
		backhuankuan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(XiaoJinYingHuanKuanApplyActivity.this,
						XiaoJinYingMenuActivity.class);
				startActivity(intent);
			}
		});

		et_huankuandate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 显示时间选择器

				showDatePickerFragemnt();

			}
		});
		message_button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (et_huankuanjine2.getText().toString().trim().equals("")) {
					DialogUtil.showDialogOneButton2(
							XiaoJinYingHuanKuanApplyActivity.this, "请输入金额！");
					return;
				}

				String huankuan_temp = et_huankuanjine2.getText().toString();

				if (huankuan_temp != null) {

					if (Contents.xiaoChaXunResult2 != null) {

						String temps = String.valueOf(String.valueOf(NumberUtil
								.decimalFormat(Double.valueOf(huankuan_temp))));

						if (!temps.equals(huankuan_temp)) {

							et_huankuanjine2.setText(temps);

							DialogUtil.showDialogOneButton2(
									XiaoJinYingHuanKuanApplyActivity.this,
									"\t\t在获取验证码前，请再次确定您输入的还款金额！");

							return;
						}
					}

				}

				Result temp = null;
				String qingkuanmoney = et_huankuanjine2.getText().toString()
						.trim();
				temp = RegexUtils2.doubled(getString(R.string.xiaojinying_hkje), qingkuanmoney);
				if (temp.match == false) {
					result = temp;
					// showEditDialog(v, result.msg);

					DialogUtil.showDialogOneButton2(
							XiaoJinYingHuanKuanApplyActivity.this,
							"\t\t请检查您输入的还款金额的格式！");
					return;
				}

				new huankuanAsyncTask().execute(Contents.response.getResult()
						.getCipMobile());
				timer();
			}
		});

		tijiao_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Result temp = null;

				String moneydate = et_huankuandate.getText().toString().trim();
				temp = RegexUtils2.required("还款日期", moneydate);
				if (temp.match == false) {
					result = temp;
					showEditDialog(v, result.msg);
					return;
				}
				Date datenow = new Date();
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String timenow = format.format(datenow);
				try {
					Date dt1 = format.parse(timenow);
					Date dt2 = format.parse(moneydate);
					if (dt2.getTime() < dt1.getTime()) {
						showEditDialog(v, "还款日期不能大于当前日期");
						return;
					}

				} catch (ParseException e) {
					e.printStackTrace();
					return;
				}

				String qingkuanmoney = et_huankuanjine2.getText().toString()
						.trim();
				temp = RegexUtils2.doubled(getString(R.string.xiaojinying_hkje), qingkuanmoney);
				if (temp.match == false) {
					result = temp;
					// showEditDialog(v, result.msg);

					DialogUtil.showDialogOneButton2(
							XiaoJinYingHuanKuanApplyActivity.this, getString(R.string.xiaojinying_qsrhkje));
					return;
				}

				String yanzhegnma = et_yanzhegnma2.getText().toString().trim();
				temp = RegexUtils2.required(getString(R.string.xiaojinyin_yzm), yanzhegnma);
				if (temp.match == false) {
					result = temp;
					showEditDialog(v, result.msg);
					return;
				}
				new yanzhengAsyncTask().execute(tx_moblieNum.getText()
						.toString());
			}
		});

	}

	public void init() {
		if (Contents.xiaoChaXunResult2 != null) {
			// 绑定放款账号
			tx_huankuanNum.setText(Contents.xiaoChaXunResult2.getAcctNo());
			// tx_huankuanNum.setText(Contents.xiaoChaXunResult2.getCardNo());
			tx_moblieNum.setText(Contents.response.getResult().getCipMobile());
		}
	}

	public void showEditDialog(View view, String str) {
		DialogFragment myFragment = NameDialogFragment.newInstance(str);
		myFragment.show(getFragmentManager(), "EditNameDialog");
	}

	// -------------------------------判断还款日期和账单日期---------------------------------
	public static int compare_date(String DATE1, String DATE2) {

		Date datenow = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String timenow = format.format(datenow);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			Date dtnow = df.parse(timenow);

			if (dt1.getTime() > dt2.getTime()) {
				System.out.println("dt1大");
				return 1;
				// 开始日期大
			} else if (dt1.getTime() < dt2.getTime()) {
				System.out.println("dt2大");
				// 结束日期大
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	public class DatePickerFragment extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {

		int _year = 1970;

		int _month = 0;

		int _day = 0;

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);

			Date datenow = new Date();

			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			// 当前时间
			String timenow = format.format(datenow);

			String[] array = timenow.split("-");

			year = Integer.parseInt(array[0]);
			month = Integer.parseInt(array[1]) - 1;
			day = Integer.parseInt(array[2]);

			return new DatePickerDialog(getActivity(), this, year, month, day);
			// return new DatePickerDialog(getActivity(), this, year, month, 0);
		}

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// 日期选择完成事件
			_year = year;
			_month = monthOfYear + 1;
			_day = dayOfMonth;
			et_huankuandate.setText(getValue());
		}

		private String getValue() {
			String month;
			String day;
			if (_month < 10) {
				month = "0" + String.valueOf(_month);
			} else {
				month = String.valueOf(_month);
			}
			if (_day < 10) {
				day = "0" + String.valueOf(_day);
			} else {
				day = String.valueOf(_day);
			}

			return "" + _year + "-" + month + "-" + day;
			// return "" + _year + "-" + _month + "-" + _day;
		}

	}

	private void showDatePickerFragemnt() {
		DialogFragment fragment = new DatePickerFragment();
		fragment.show(getFragmentManager(), "datePicker");

	}

	class huankuanAsyncTask extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(XiaoJinYingHuanKuanApplyActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("phoneNum", params[0]));
			arg.add(new BasicNameValuePair("msgTemplate", "XJY_ACTIVE_PAYMENT"));
			String s = Contents.response.getResult().getIdNo();
			s = StringUtils.replacesFour(s);

			arg.add(new BasicNameValuePair("param", "****" + s + ","
					+ et_huankuanjine2.getText().toString().trim()));
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
				message_button2.setText(time + "秒后可获取");
				message_button2.setClickable(false);
			} else if (msg.what == 1) {
				isFlag = !isFlag;
				message_button2.setText("获取验证码");
				message_button2.setClickable(true);
			}
		};
	};

	private void loginResponse(Object result) {
		VerifyCodeResponse response = (VerifyCodeResponse) result;
		if (response == null) {
			loginErrorMessage = "服务器请求异常!";
			DialogUtil.createDialog(this, 1, loginErrorMessage);
			isFlag = true;
			message_button2.setText("重新获取");
			return;
		} else {
			if (RET_CODE.equals(response.getCode())) {
				String s = response.getMsg();

				Toast.makeText(XiaoJinYingHuanKuanApplyActivity.this,
						"获取验证码成功" + response.getMsg(), Toast.LENGTH_LONG)
						.show();
				time = 120;// response.getResult();
				isFlag = false;
				timer();
			} else {
				loginErrorMessage = response.getMsg();
				DialogUtil.createDialog(this, 4, loginErrorMessage);
				isFlag = true;
				message_button2.setText("重新获取");
			}
		}
	}

	// 还款申请提交
	class huankuantijiaoAsyncTask extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(XiaoJinYingHuanKuanApplyActivity.this);
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
			// 还款金额 必填
			arg.add(new BasicNameValuePair("details[0].reAmt", et_huankuanjine2
					.getText().toString()));
			// 还款日期
			arg.add(new BasicNameValuePair("details[0].reDt", et_huankuandate
					.getText().toString()));
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
					getVerName(XiaoJinYingHuanKuanApplyActivity.this)));
			return httpHelper.post(ContantsAddress.Huankuan_xiaojinying, arg,
					HuankuanResponse.class);
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
			huankuantijiaoResponse(result);
		}
	}

	private void huankuantijiaoResponse(Object result) {
		HuankuanResponse response = (HuankuanResponse) result;
		if (response == null) {
			loginErrorMessage = "服务器请求异常!";
			DialogUtil.createDialog(this, 1, loginErrorMessage);
			isFlag = false;
			return;
		} else {
			if (RET_CODE == response.getCode()) {
				String s = response.getMsg();

				isFlag = true;
				DialogUtil.showDialogOneButton(
						XiaoJinYingHuanKuanApplyActivity.this, s,
						new OnClickListener() {

							@Override
							public void onClick(View v) {
								finish();
							}
						});
			} else {

				String ss = response.getMsg();
				isFlag = false;
				DialogUtil.showDialogOneButton(
						XiaoJinYingHuanKuanApplyActivity.this, "提交失败：" + ss,
						new OnClickListener() {

							@Override
							public void onClick(View v) {
								finish();
							}
						});
			}
		}
	}

	class yanzhengAsyncTask extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {

			HttpHelper httpHelper = HttpHelper
					.getInstance(XiaoJinYingHuanKuanApplyActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("phoneNum", params[0]));
			arg.add(new BasicNameValuePair("verifyCode", et_yanzhegnma2
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
			return;
		} else {
			if (RET_CODE == response.getCode()) {
				String s = response.getMsg();

				new huankuantijiaoAsyncTask().execute();
			} else {

				String ss = response.getMsg();
				Toast.makeText(XiaoJinYingHuanKuanApplyActivity.this,
						"验证码错误" + response.getMsg(), Toast.LENGTH_LONG).show();
			}
		}
	}

	private void getProInfo() {
		TelephonyManager telephonyManager = (TelephonyManager) this
				.getSystemService(Context.TELEPHONY_SERVICE);
		IMEI = telephonyManager.getDeviceId();
		// BobLog.print("IMEI ------------->" + IMEI);
		// BobLog.print("操作系统名称 ------------->" +
		// android.os.Build.VERSION.INCREMENTAL);
		// BobLog.print("操作系统版本 ------------->" +
		// android.os.Build.VERSION.RELEASE);
		PackageManager packageManager = getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo;
		// try {
		// packInfo = packageManager.getPackageInfo(getPackageName(),0);
		// appVersion = packInfo.versionName;
		// // BobLog.print("应用版本 ------------->" + appVersion);
		// } catch (NameNotFoundException e) {
		// e.printStackTrace();
		// }
		// BobLog.print("设备制造商 ------------->" + android.os.Build.MANUFACTURER);
		// BobLog.print("设备型号 ------------->" + android.os.Build.MODEL);
		// BobLog.print("设备名称 ------------->" + android.os.Build.PRODUCT);
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
