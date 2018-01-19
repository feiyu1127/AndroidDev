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
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.map.DemoApplication;
import com.yucheng.byxf.mini.message.Response;
import com.yucheng.byxf.mini.message.VerifyCodeResponse;
import com.yucheng.byxf.mini.miniLoan.fragment.NameDialogFragment;
import com.yucheng.byxf.mini.ui.BaseActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DESUtils;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.NumberUtil;
import com.yucheng.byxf.mini.utils.RegexUtils2;
import com.yucheng.byxf.mini.utils.RegexUtils2.Result;
import com.yucheng.byxf.mini.utils.StringUtils;

/**
 * 类名: XiaoJinYingCancelAccountActivity</br> 描述:销户 </br> 开发人员： weiyb</br> 创建时间：
 * 2015-7-10
 */

public class XiaoJinYingCancelAccountActivity extends BaseActivity {

	private String TAG = this.getClass().getSimpleName();

	// 账号
	private TextView et_zhangdan;

	// 手机号
	private TextView et_shoujihaoma;

	// 已用额度
	private TextView et_yiyongedu;

	// 可用额度
	private TextView et_keyongedu;

	// 账单信息
	private ImageButton ib_zhangdanxinxi;

	// 溢缴款
	private TextView et_yijiaokuan;

	// 身份证号
	private EditText et_shenfenzhenghao;

	// 查询密码
	private EditText et_chaxunmima;

	// 验证码
	private EditText et_duanxinyanzhengma;

	// 获取验证码
	private TextView huoquyanzhengma;

	// 返回
	private ImageView backxiaohu;

	// 提交
	private Button xiaohutijiao;

	private boolean isFlag = true;

	private int time;

	private static final Integer RET_CODE = 0;

	private String loginErrorMessage;

	private Result result;

	// 信息有误

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(TAG + "__onCreate", "oncreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xiaojinyin_cancel_account_activity);
		et_zhangdan = (TextView) findViewById(R.id.et_zhangdan);
		et_shoujihaoma = (TextView) findViewById(R.id.et_shoujihaoma);
		et_yiyongedu = (TextView) findViewById(R.id.et_yiyongedu);
		et_keyongedu = (TextView) findViewById(R.id.et_keyongedu);
		ib_zhangdanxinxi = (ImageButton) findViewById(R.id.ib_zhangdanxinxi);
		et_yijiaokuan = (TextView) findViewById(R.id.et_yijiaokuan);
		et_shenfenzhenghao = (EditText) findViewById(R.id.et_shenfenzhenghao);
		et_chaxunmima = (EditText) findViewById(R.id.et_chaxunmima);
		et_duanxinyanzhengma = (EditText) findViewById(R.id.et_duanxinyanzhengma);
		huoquyanzhengma = (TextView) findViewById(R.id.huoquyanzhengma);
		backxiaohu = (ImageView) findViewById(R.id.backxiaohu);
		xiaohutijiao = (Button) findViewById(R.id.xiaohutijiao);
		init();
		// 返回
		backxiaohu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(XiaoJinYingCancelAccountActivity.this,
						XiaoJinYingMenuActivity.class);
				startActivity(intent);
			}
		});
		// 跳转我的账单
		ib_zhangdanxinxi.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				// intent.setClass(XiaoJinYingCancelAccountActivity.this,
				// XiaoJinYingQueryActivity.class);

				intent.setClass(XiaoJinYingCancelAccountActivity.this,
						XiaoJinYingZhangDanActivity.class);
				intent.putExtra("zhuangtai", "0");
				startActivity(intent);
			}
		});
		// 获取验证码
		huoquyanzhengma.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new xiaohuyanzhengmaAsyncTask().execute(Contents.response
						.getResult().getCipMobile());
				timer();
			}
		});
		xiaohutijiao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Result temp = null;
				String moneyMonth = et_shenfenzhenghao.getText().toString()
						.trim();
				temp = RegexUtils2.idcard("身份证号", moneyMonth);
				if (temp.match == false) {
					result = temp;
					showEditDialog(v, result.msg);
					et_shenfenzhenghao.requestFocus();
					return;
				}
				String moneyMonth1 = et_chaxunmima.getText().toString().trim();
				temp = RegexUtils2.required("查询密码", moneyMonth);
				if (temp.match == false) {
					result = temp;
					showEditDialog(v, result.msg);
					et_chaxunmima.requestFocus();
					return;
				}

				String yanzhegnma = et_duanxinyanzhengma.getText().toString()
						.trim();
				temp = RegexUtils2.required("验证码", yanzhegnma);
				if (temp.match == false) {
					result = temp;
					showEditDialog(v, result.msg);
					et_duanxinyanzhengma.requestFocus();
					return;
				}
				new yanzhengAsyncTask().execute(Contents.response.getResult()
						.getCipMobile());
			}
		});

	}

	public void init() {
		// 账号
		et_zhangdan.setText(Contents.xiaoChaXunResult2.getCardNo());
		// 手机号
		et_shoujihaoma.setText(Contents.response.getResult().getCipMobile());
		// 已用额度
		// String yiyongedu = Contents.xiaoChaXunResult2.getCrAmt()sad
		// + Contents.xiaoChaXunResult2.getOsAmt()
		// + Contents.xiaoChaXunResult2.getAvailAmt();
		// 已用额度=信用额度+溢缴款-可用额度
		// =crAmt+osAmt-availAmt
		Double crAmt = Double.valueOf(Contents.xiaoChaXunResult2.getCrAmt());
		Double osAmt = Double.valueOf(Contents.xiaoChaXunResult2.getOsAmt());
		Double availAmt = Double.valueOf(Contents.xiaoChaXunResult2
				.getAvailAmt());
		// Double s = crAmt + osAmt - availAmt;

		Double s = NumberUtil.sub(NumberUtil.add(crAmt, osAmt), availAmt);

		et_yiyongedu.setText(s.toString());
		// 可用额度
		et_keyongedu.setText(Contents.xiaoChaXunResult2.getAvailAmt());
		// 溢缴款
		et_yijiaokuan.setText(Contents.xiaoChaXunResult2.getOsAmt());
	}

	// 获取验证码接口
	class xiaohuyanzhengmaAsyncTask extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(XiaoJinYingCancelAccountActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("phoneNum", Contents.response
					.getResult().getCipMobile()));
			arg.add(new BasicNameValuePair("msgTemplate", "XJY_DESTORY_USER"));
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
			xiaohuResponse(result);
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
				huoquyanzhengma.setText(time + "秒后可获取");
				huoquyanzhengma.setClickable(false);
			} else if (msg.what == 1) {
				isFlag = !isFlag;
				huoquyanzhengma.setText("获取验证码");
				huoquyanzhengma.setClickable(true);
			}
		};
	};

	private void xiaohuResponse(Object result) {
		VerifyCodeResponse response = (VerifyCodeResponse) result;
		if (response == null) {
			loginErrorMessage = "获取验证服务器请求异常!";
			DialogUtil.createDialog(this, 1, loginErrorMessage);
			huoquyanzhengma.setText("重新获取");
			return;
		} else {
			if (RET_CODE.equals(response.getCode())) {
				String s = response.getMsg();

				Toast.makeText(XiaoJinYingCancelAccountActivity.this,
						"获取验证码成功" + response.getMsg(), Toast.LENGTH_LONG)
						.show();
				time = 120;// response.getResult();
				timer();
			} else {
				loginErrorMessage = response.getMsg();
				DialogUtil.createDialog(this, 4, loginErrorMessage);
				huoquyanzhengma.setText("重新获取");
			}
		}
	}

	// 验证验证码接口
	class yanzhengAsyncTask extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(XiaoJinYingCancelAccountActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("phoneNum", Contents.response
					.getResult().getCipMobile()));
			arg.add(new BasicNameValuePair("verifyCode", et_duanxinyanzhengma
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
			Log.i(TAG + "__验证码", result.toString() + "ddd");
			xiaohuyanzhengResponse(result);
		}
	}

	private void xiaohuyanzhengResponse(Object result) {
		VerifyCodeResponse response = (VerifyCodeResponse) result;
		if (response == null) {
			loginErrorMessage = "验证服务器请求异常!";
			DialogUtil.createDialog(this, 1, loginErrorMessage);
			return;
		} else {
			if (RET_CODE.equals(response.getCode())) {
				String s = response.getMsg();

				new xiaohutijiaoAsyncTask().execute();
			} else {

				String ss = response.getMsg();
				Toast.makeText(XiaoJinYingCancelAccountActivity.this,
						"验证码错误" + response.getMsg(), Toast.LENGTH_LONG).show();
			}
		}
	}

	// 销户接口
	class xiaohutijiaoAsyncTask extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(XiaoJinYingCancelAccountActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("idNo", et_shenfenzhenghao.getText()
					.toString()));
			// arg.add(new BasicNameValuePair("password",
			// et_chaxunmima.getText().toString()));

			try {
				arg.add(new BasicNameValuePair("password", DESUtils
						.encryptMode(et_chaxunmima.getText().toString(),
								DemoApplication.getApplication())));
			} catch (Exception e) {

				e.printStackTrace();
			}

			return httpHelper.post(ContantsAddress.xiaohu, arg, Response.class);
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

			Log.i(TAG + "__销户接口", result.toString() + "dd");
			xiaohResponse(result);
		}
	}

	private void xiaohResponse(Object result) {
		Response response = (Response) result;
		if (response == null) {
			loginErrorMessage = "服务器请求异常!";
			DialogUtil.createDialog(this, 1, loginErrorMessage);
			return;
		} else {
			if (RET_CODE == response.getCode()) {
				String s = response.getMsg();
				DialogUtil.showDialogOneButton(
						XiaoJinYingCancelAccountActivity.this, "提交成功",
						new OnClickListener() {
							@Override
							public void onClick(View v) {

								Intent mIntent = new Intent(
										XiaoJinYingCancelAccountActivity.this,
										XiaoJinYingMenuActivity.class);

								startActivity(mIntent);
								finish();
							}
						});
			} else {
				String ss = response.getMsg();
				DialogUtil.showDialogOneButton(
						XiaoJinYingCancelAccountActivity.this, "提交失败" + ss,
						new OnClickListener() {
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
