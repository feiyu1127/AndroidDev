package com.yucheng.byxf.mini.xiaojinying;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.Text;
import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.message.Response;
import com.yucheng.byxf.mini.message.VerifyCodeResponse;
import com.yucheng.byxf.mini.ui.BaseActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.StringUtils;
import com.yucheng.byxf.mini.utils.RegexUtils2.Result;
import com.yucheng.byxf.mini.xiaojinying.XiaoJinYingQingKuanApplyActivity.qingkuanAsyncTask;

public class XiaoJinYingJIHuoActivity extends BaseActivity {
	/**
	 * 激活
	 * 
	 */
	private TextView et_shenfenzhenghao;
	private EditText et_shoujihaoma;
	private TextView huoquyanzhengma;
	private EditText et_duanxinyanzhengma;
	private Button jihuo;
	private String _shenfenzhenghao = "";
	private String _shoujihao = "";
	private String _yanzhengma = "";
	private int time;
	private static final Integer RET_CODE = 0;
	private String loginErrorMessage;
	private ImageView back_jihuo;

	private boolean isFlag = true;
	private Result result;
	// 信息有误
	private final int INFO_ERROR = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xiaojinyin_jihuo_activity);
		et_shenfenzhenghao = (TextView) findViewById(R.id.et_shenfenzhenghao);
		et_shoujihaoma = (EditText) findViewById(R.id.et_shoujihaoma);
		et_duanxinyanzhengma = (EditText) findViewById(R.id.et_duanxinyanzhengma);
		huoquyanzhengma = (TextView) findViewById(R.id.huoquyanzhengma);
		et_shenfenzhenghao.setText(Contents.response.getResult().getIdNo());
		et_shoujihaoma.setText(Contents.response.getResult().getCipMobile());
		back_jihuo = (ImageView) findViewById(R.id.back_jihuo);
		back_jihuo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(XiaoJinYingJIHuoActivity.this,
						XiaoJinYingMenuActivity.class);
				startActivity(intent);
			}
		});
		huoquyanzhengma.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				_shoujihao = et_shoujihaoma.getText().toString();
				if (_shoujihao == null || _shoujihao.equals("")) {
					DialogUtil.showDialogOneButton2(
							XiaoJinYingJIHuoActivity.this, "请输入手机号");
					return;
				}

				new jihuoshenqingAsyncTask().execute(_shoujihao);
			}
		});
		jihuo = (Button) findViewById(R.id.jihuo);
		jihuo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				_shenfenzhenghao = et_shenfenzhenghao.getText().toString();
				_shoujihao = et_shoujihaoma.getText().toString();
				_yanzhengma = et_duanxinyanzhengma.getText().toString();
				if (_shenfenzhenghao == null || _shenfenzhenghao.equals("")) {
					DialogUtil.showDialogOneButton2(
							XiaoJinYingJIHuoActivity.this, "请输入身份证号");
					return;
				}
				if (_shoujihao == null || _shoujihao.equals("")) {
					DialogUtil.showDialogOneButton2(
							XiaoJinYingJIHuoActivity.this, "请输入手机号");
					return;
				}
				if (_yanzhengma == null || _yanzhengma.equals("")) {
					DialogUtil.showDialogOneButton2(
							XiaoJinYingJIHuoActivity.this, "请输入验证码");
					return;
				}
				new jihuoyanzhengAsyncTask().execute(_shoujihao);
			}
		});
	}

	// 验证验证码
	class jihuoyanzhengAsyncTask extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(XiaoJinYingJIHuoActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("phoneNum", params[0]));
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
			jihuoyanzhengmaResponse(result);
		}
	}

	private void jihuoyanzhengmaResponse(Object result) {
		VerifyCodeResponse response = (VerifyCodeResponse) result;
		if (response == null) {
			loginErrorMessage = "验证服务器请求异常!";
			DialogUtil.createDialog(this, 1, loginErrorMessage);
			return;
		} else {
			if (RET_CODE.equals(response.getCode())) {
				String s = response.getMsg();
			 
				new jihuoyanzhengtijiaoAsyncTask().execute();
			} else {

				String ss = response.getMsg();
				Toast.makeText(XiaoJinYingJIHuoActivity.this,
						"验证码错误" + response.getMsg(), Toast.LENGTH_LONG).show();
			}
		}
	}

	// 获取验证码

	class jihuoshenqingAsyncTask extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(XiaoJinYingJIHuoActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("phoneNum", params[0]));
			arg.add(new BasicNameValuePair("msgTemplate", "XJY_ACTIVITY"));
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
			if (RET_CODE==response.getCode()) {
				String s = response.getMsg();

				Toast.makeText(XiaoJinYingJIHuoActivity.this,
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

	// 提交激活申请
	class jihuoyanzhengtijiaoAsyncTask extends
			AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(XiaoJinYingJIHuoActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			String s = Contents.xiaoChaXunCardNo.toString();
			arg.add(new BasicNameValuePair("cardNum", s));

			arg.add(new BasicNameValuePair("IDNum", Contents.response
					.getResult().getIdNo()));

			return httpHelper.post(ContantsAddress.jihuo, arg,
					Response.class);
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
			jihuotijiaoResponse(result);
		}
	}

	private void jihuotijiaoResponse(Object result) {
		Response response = (Response) result;
		if (response == null) {
			loginErrorMessage = "服务器请求异常!";
			DialogUtil.createDialog(this, 1, loginErrorMessage);
			return;
		} else {
			if (RET_CODE==response.getCode()) {
				DialogUtil.showDialogOneButton(XiaoJinYingJIHuoActivity.this,
						"提交成功！", new OnClickListener() {
							@Override
							public void onClick(View v) {
							    Intent mIntent =new Intent(XiaoJinYingJIHuoActivity.this, XiaoJinYingMenuActivity.class);
							    startActivity(mIntent);
								finish();
							}
						});
			} else {
				String ss = response.getMsg();
				DialogUtil.showDialogOneButton(XiaoJinYingJIHuoActivity.this,
						"提交失败！" + ss, new OnClickListener() {
							@Override
							public void onClick(View v) {
								finish();
							}
						});
			}
		}
	}
}
