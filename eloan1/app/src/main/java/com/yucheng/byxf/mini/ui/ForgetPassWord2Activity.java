package com.yucheng.byxf.mini.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.apfx.utl.RegexResult;
import com.yucheng.byxf.mini.message.Response;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.DESUtils;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.RegexCust;
import com.yucheng.byxf.util.MD5;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgetPassWord2Activity extends BaseActivity {

	private EditText et_password;
	private EditText et_password_2;
	private Button bt_next;

	private String username;

	// 验证结果
	private RegexResult result;

	// 身份信息有误
	private final int INFO_ERROR = 4;

	private String loginuser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rapidly_forget_key3);
		Intent intent = getIntent();
		username = intent.getStringExtra("username");
		loginuser = intent.getStringExtra("loginuser");
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		et_password = (EditText) findViewById(R.id.et_password);
		et_password_2 = (EditText) findViewById(R.id.et_password_2);
		bt_next = (Button) findViewById(R.id.bt_next);

		bt_next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String st_password = et_password.getText().toString();
				String st_password1 = et_password_2.getText().toString();

				RegexResult temp_password = RegexCust.required("密码", st_password);
				if (temp_password.match) {
					if (RegexCust.userPass(st_password) == false) {
						result = new RegexResult(false,
								"密码为不能小于8位且不能大于20位，数字和字母的组合");
						showDialog(INFO_ERROR);
						et_password.requestFocus();
						return;
					}
				} else {
					result = temp_password;
					showDialog(INFO_ERROR);
					et_password.requestFocus();
					return;
				}

				if (!hasDigit(st_password)) {
					result = new RegexResult(false, "密码需为数字和字母的组合");
					showDialog(INFO_ERROR);
					et_password.requestFocus();
					return;
				}

				if (!st_password.equals(st_password1)) {
					result = new RegexResult(false, "两次输入密码不一致");
					showDialog(INFO_ERROR);
					et_password.requestFocus();
					return;
				}

				new ModifyAsyncTask().execute(loginuser,
						MD5.getMD5ofStr(loginuser + st_password));
			}
		});
	}

	class ModifyAsyncTask extends AsyncTask<String, Object, Object> {
    @Override
    protected Object doInBackground(String... params) {
      HttpHelper httpHelper = HttpHelper.getInstance(ForgetPassWord2Activity.this);
      List<NameValuePair> arg = new ArrayList<NameValuePair>();
      arg.add(new BasicNameValuePair("cipCsino", params[0]));
      arg.add(new BasicNameValuePair("cipPassword", params[1]));
      return httpHelper.post(ContantsAddress.MODIFY_PWD, arg, Response.class);
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
			registerResponse(result);
		}
	}

	private void registerResponse(Object result) {
		Response response = (Response) result;
		if (response == null) {
			DialogUtil.showDialogOneButton2(ForgetPassWord2Activity.this,
					"服务器请求异常!");
			return;
		} else {
			if (0 == response.getCode()) {
				DialogUtil.showDialogOneButton(ForgetPassWord2Activity.this,
						"恭喜您，已经重置密码,请使用新密码登录", new OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								finish();
							}
						});
			} else {
				DialogUtil.showDialogOneButton2(ForgetPassWord2Activity.this,
						response.getMsg());
			}
		}
	}

	@Override
	protected Dialog onCreateDialog(int id, Bundle args) {
		Dialog dialog = null;
		StringBuffer sb = new StringBuffer();

		switch (id) {
		case INFO_ERROR:
			sb.append(result.msg);
			dialog = DialogUtil.showDialogOneButton2(
					ForgetPassWord2Activity.this, sb.toString());
			break;
		default:
			break;
		}
		return super.onCreateDialog(id, args);
	}

	private boolean hasDigit(String contant) {
		boolean flag = false;
		Pattern pattern = Pattern.compile(".*\\d*");
		Matcher matcher = pattern.matcher(contant);
		if (matcher.matches()) {
			flag = true;
		}
		return flag;
	}
}
