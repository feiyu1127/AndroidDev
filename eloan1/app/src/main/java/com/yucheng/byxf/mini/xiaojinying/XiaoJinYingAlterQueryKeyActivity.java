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
 * 类名: XiaoJinYingAlterQueryKeyActivity</br> 描述:修改查询密码 </br> 开发人员： weiyb</br>
 * 创建时间： 2015-7-24
 */

public class XiaoJinYingAlterQueryKeyActivity extends BaseActivity {
	// 账号
	private TextView et_zhanghao;
	// 手机号码
	private TextView et_shoujihaoma;
	// 元查询密码
	private EditText et_yuanmima;
	// 新查询密码
	private EditText et_xinmima;
	// 确认密码
	private EditText et_querenmima;
	// 短信验证码
	private EditText et_yanzhengma;
	// 获取验证码
	private TextView huoquyanzhengma;
	// 返回按钮
	private ImageView back_changekeey;
	// 提交按钮
	private Button tijiao_changekey;
	private boolean isFlag = true;
	private int time;

	private static final Integer RET_CODE = 0;

	private String loginErrorMessage;

	private Result result;

	// 信息有误
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xiaojinyin_change_key_activity);
		et_zhanghao = (TextView) findViewById(R.id.et_zhanghao);
		et_shoujihaoma = (TextView) findViewById(R.id.et_shouji);
		et_yuanmima = (EditText) findViewById(R.id.et_yuanmima);
		et_xinmima = (EditText) findViewById(R.id.et_xinmima);
		et_querenmima = (EditText) findViewById(R.id.et_querenmima);
		et_yanzhengma = (EditText) findViewById(R.id.et_yanzhengma);
		huoquyanzhengma = (TextView) findViewById(R.id.huoquyanzhengma);
		back_changekeey = (ImageView) findViewById(R.id.back_changekeey);
		tijiao_changekey = (Button) findViewById(R.id.tijiao_changekey);
		init();
		// 返回事件
		back_changekeey.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(XiaoJinYingAlterQueryKeyActivity.this,
						XiaoJinYingMenuActivity.class);
				startActivity(intent);
			}
		});
		// 获取验证码
		huoquyanzhengma.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new xiugaimimaAsyncTask().execute(Contents.response.getResult()
						.getCipMobile());
				timer();
			}
		});
		// 提交按钮
		tijiao_changekey.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Result temp = null;
				String moneyMonth = et_yuanmima.getText().toString().trim();
				temp = RegexUtils2.positiveInteger("原查询密码", moneyMonth);
				if (temp.match == false) {
					result = temp;
					showEditDialog(v, result.msg);
					et_yuanmima.requestFocus();
					return;
				}
				String moneyMonth2 = et_xinmima.getText().toString().trim();
				temp = RegexUtils2.required("新查询密码", moneyMonth2);
				if (temp.match == false) {
					result = temp;
					showEditDialog(v, result.msg);
					et_xinmima.requestFocus();
					return;
				}
				String moneyMonth3 = et_querenmima.getText().toString().trim();
				temp = RegexUtils2.required("确认密码", moneyMonth3);
				if (temp.match == false) {
					result = temp;
					showEditDialog(v, result.msg);
					et_querenmima.requestFocus();
					return;
				}
				int one = Integer.parseInt(moneyMonth3);
				int two = Integer.parseInt(moneyMonth2);
				if (one != two) {
					showEditDialog(v, "两次密码输入不一致，请重新输入");
					et_querenmima.requestFocus();
					return;
				}
				String yanzhegnma = et_yanzhengma.getText().toString().trim();
				temp = RegexUtils2.required("验证码", yanzhegnma);
				if (temp.match == false) {
					result = temp;
					showEditDialog(v, result.msg);
					et_yanzhengma.requestFocus();
					return;
				}
				new yanzhengalterAsyncTask().execute(Contents.response
						.getResult().getCipMobile());
			}
		});

	}

	public void init() {
		et_zhanghao.setText(Contents.xiaoChaXunResult2.getCardNo());
		et_shoujihaoma.setText((Contents.response.getResult().getCipMobile()));
	}

	// 获取验证码接口
	class xiugaimimaAsyncTask extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(XiaoJinYingAlterQueryKeyActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("phoneNum", params[0]));
			arg.add(new BasicNameValuePair("msgTemplate", "XJY_MODIFY_PWD"));
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

	private void loginResponse(Object result) {
		VerifyCodeResponse response = (VerifyCodeResponse) result;
		if (response == null) {
			loginErrorMessage = "获取验证服务器请求异常!";
			DialogUtil.createDialog(this, 1, loginErrorMessage);
			huoquyanzhengma.setText("重新获取");
			return;
		} else {
			if (RET_CODE.equals(response.getCode())) {
				String s = response.getMsg();

				Toast.makeText(XiaoJinYingAlterQueryKeyActivity.this,
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

	// 验证码验证接口
	class yanzhengalterAsyncTask extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(XiaoJinYingAlterQueryKeyActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("phoneNum", params[0]));
			arg.add(new BasicNameValuePair("verifyCode", et_yanzhengma
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
			yanzhengalterResponse(result);
		}
	}

	private void yanzhengalterResponse(Object result) {
		VerifyCodeResponse response = (VerifyCodeResponse) result;
		if (response == null) {
			loginErrorMessage = "验证服务器请求异常!";
			DialogUtil.createDialog(this, 1, loginErrorMessage);
			return;
		} else {
			if (RET_CODE.equals(response.getCode())) {
				String s = response.getMsg();

				new xiugaimimaalterAsyncTask().execute();
			} else {

				String ss = response.getMsg();
				Toast.makeText(XiaoJinYingAlterQueryKeyActivity.this,
						"验证码错误" + response.getMsg(), Toast.LENGTH_LONG).show();
			}
		}
	}

	// 修改密码提交
	class xiugaimimaalterAsyncTask extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {

			HttpHelper httpHelper = HttpHelper
					.getInstance(XiaoJinYingAlterQueryKeyActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("cardNum",
					Contents.xiaoChaXunResult2.getCardNo()));
			arg.add(new BasicNameValuePair("IDNum", Contents.xiaoChaXunResult2
					.getIdNo()));

			arg.add(new BasicNameValuePair("txn_Flag", "1"));
			/*
			 * arg.add(new BasicNameValuePair("newPsw", et_querenmima.getText()
			 * .toString())); arg.add(new BasicNameValuePair("oldPsw",
			 * et_yuanmima.getText() .toString()));
			 */

			try {
				arg.add(new BasicNameValuePair("newPsw", DESUtils.encryptMode(
						et_querenmima.getText().toString(),
						DemoApplication.getApplication())));
				arg.add(new BasicNameValuePair("oldPsw", DESUtils.encryptMode(
						et_yuanmima.getText().toString(),
						DemoApplication.getApplication())));
			} catch (Exception e) {

				e.printStackTrace();
			}

			return httpHelper.post(ContantsAddress.xiugaimima, arg,
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
			xiugaimimaalterResponse(result);
		}
	}

	private void xiugaimimaalterResponse(Object result) {
		QingkuanResponse response = (QingkuanResponse) result;
		if (response == null) {
			loginErrorMessage = "服务器请求异常!";
			DialogUtil.createDialog(this, 1, loginErrorMessage);
			return;
		} else {
			if (RET_CODE.equals(response.getCode())) {
				String s = response.getMsg();
				DialogUtil.showDialogOneButton(
						XiaoJinYingAlterQueryKeyActivity.this,

						"提交成功！", new OnClickListener() {

							@Override
							public void onClick(View v) {
								Intent it = new Intent();
								it.setClass(
										XiaoJinYingAlterQueryKeyActivity.this,
										XiaoJinYingMenuActivity.class);
								startActivity(it);
								finish();

							}

						});
			} else {

				String ss = response.getMsg();
				DialogUtil.showDialogOneButton(
						XiaoJinYingAlterQueryKeyActivity.this,

						"提交失败！" + ss, new OnClickListener() {

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
