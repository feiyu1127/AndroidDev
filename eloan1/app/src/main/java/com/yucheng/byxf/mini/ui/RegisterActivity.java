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
import com.yucheng.byxf.mini.utils.IdcardInfoExtractor;
import com.yucheng.byxf.mini.utils.RegexCust;
import com.yucheng.byxf.util.IdcardValidator;
import com.yucheng.byxf.util.MD5;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.text.InputType;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends BaseActivity implements OnClickListener {
	private Button bt_commit;
	private EditText name;// 账号
	private EditText password;// 密码
	private EditText confirmPassword;// 密码

	private EditText et_identify;// 身份证
	private EditText phone;// 电话
	private EditText username;// 用户名

	private String id_num;// 用户名

	private boolean isfocus = false;
	private String loginErrorMessage;
	private static final Integer RET_CODE = 0;
	// 验证结果
	private RegexResult result;

	private boolean isOdd = false;

	// 身份信息有误
	private final int INFO_ERROR = 4;

	private String sex;
	private IdcardInfoExtractor extractor;
	private String mobile;
	private String cardNumDES;
	private String cardNum;
	private String localPassword;
	
	private RegisterActivity activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
						| WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		init();
		activity = new RegisterActivity();
	}

	private void init() {
		// TODO Auto-generated method stub
		bt_commit = (Button) findViewById(R.id.bt_commit);
		bt_commit.setOnClickListener(this);
		name = (EditText) findViewById(R.id.login_name);
		password = (EditText) findViewById(R.id.tv_Password);
		confirmPassword = (EditText) findViewById(R.id.tv_confirmPassword);

		phone = (EditText) findViewById(R.id.et_phone);
		et_identify = (EditText) findViewById(R.id.et_identify);
		username = (EditText) findViewById(R.id.et_username);
		id_num = name.getText().toString();
	}
	
	class VertifyIdNameAsyncTask extends AsyncTask<String, Object, Object> {
    @Override
    protected Object doInBackground(String... params) {
      HttpHelper httpHelper = HttpHelper.getInstance(RegisterActivity.this);
      List<NameValuePair> arg = new ArrayList<NameValuePair>();
      arg.add(new BasicNameValuePair("userId", params[0]));
      return httpHelper.post(ContantsAddress.CHECK_USERID, arg, Response.class);
    }

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog();
		}

		@Override
		protected void onPostExecute(Object result) {
			
			super.onPostExecute(result);
			if (activity != null) {
				dismissProgressDialog();
				checkIDNameResponse(result);
			}
			
		}
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		activity = null;
	}

	private void checkIDNameResponse(Object result) {
		Response response = (Response) result;
		if (response == null) {
			loginErrorMessage = "服务器请求异常!";
			DialogUtil.createDialog(this, 1, loginErrorMessage);
			return;
		} else {
			if (RET_CODE.equals(response.getCode())) {
				Toast.makeText(RegisterActivity.this, "验证用户名合法", Toast.LENGTH_LONG).show();
			} else {
				loginErrorMessage = response.getMsg();
				DialogUtil.createDialog(this, 4, loginErrorMessage);
			}
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_commit:
			vertifyPersonalInfo();
			break;
		default:
			break;
		}
	}

	private void vertifyPersonalInfo() {
		// 用户名
		id_num = name.getText().toString().trim();
		// 是否匹配mobile为空值；
		RegexResult temp_user = RegexCust.required("用户名", id_num);
		if (temp_user.match) {
			if (id_num.length() < 4 || id_num.length() > 20) {
				result = new RegexResult(false, "用户名应该以字母开头，并且要在4到20位之间");
				showDialog(INFO_ERROR);
				name.requestFocus();
				return;
			}
			if (RegexCust.userName(id_num) == false) {
				result = new RegexResult(false, "用户名为非法字符,用户名要以字母开头，且只能包含数字和大小写字母");
				showDialog(INFO_ERROR);
				name.requestFocus();
				return;
			}
		} else {
			result = temp_user;
			showDialog(INFO_ERROR);
			name.requestFocus();
			return;
		}
		if (hasDigit(id_num)) {
			result = new RegexResult(false, "用户名需为数字和字母的组合");
			showDialog(INFO_ERROR);
		 
			return;
		}
		
		
		// 证件号码 ,获取性别
		cardNum = et_identify.getText().toString().trim();
		cardNum = cardNum.toUpperCase();
		try {
			cardNumDES = DESUtils.encryptMode(cardNum, RegisterActivity.this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
		extractor = new IdcardInfoExtractor(cardNum);
		sex = extractor.getGender();
		if ("男".equals(sex))
			sex = "1";
		else
			sex = "2";
		IdcardValidator idCardValidator = new IdcardValidator();
		if (cardNum.length() == 0) {
			result = new RegexResult(false, "证件号码不能为空!");
			showDialog(INFO_ERROR);
			return;
		} else if (!idCardValidator.isValidatedAllIdcard(cardNum)) {
			result = new RegexResult(false, "证件号码有误!");
			showDialog(INFO_ERROR);
			return;
		}

		// 联系人手机
		mobile = phone.getText().toString().trim();
		// 是否匹配mobile为空值；
		RegexResult temp = RegexCust.required("手机号码", mobile);
		if (temp.match) {
			if (RegexCust.phoneMob(mobile) == false) {
				result = new RegexResult(false, "手机号码格式有误！");
				showDialog(INFO_ERROR);
				phone.requestFocus();
				return;
			}
		} else {
			result = temp;
			showDialog(INFO_ERROR);
			phone.requestFocus();
			return;
		}
		// 姓名
		RegexResult temp_uname = null;
		String localMobile = username.getText().toString().trim();
		temp_uname = RegexCust.length("姓名", localMobile, 1, 8);
		if (temp_uname.match) {
			temp_uname = RegexCust.chinese("姓名", localMobile);
			if (!temp_uname.match) {
				result = new RegexResult(false, "姓名应为汉字!");
				showDialog(INFO_ERROR);
				return;
			}
		} else {
			if (localMobile.length() == 0) {
				result = new RegexResult(false, "姓名不能为空!");
			} else {
				result = new RegexResult(false, "姓名字符长度不对!");
			}
			showDialog(INFO_ERROR);
			return;
		}
		// 密码；
		localPassword = password.getText().toString().trim();
		String localConfirmpassword = confirmPassword.getText().toString()
				.trim();
		// 是否匹配username为空值；
		RegexResult temp_password = RegexCust.required("密码", localPassword);
		if (temp_password.match) {
			if (RegexCust.userPass(localPassword) == false) {
				result = new RegexResult(false, "密码为不能小于8位且不能大于20位，数字和字母的组合");
				showDialog(INFO_ERROR);
				password.requestFocus();
				return;
			}
		} else {
			result = temp_password;
			showDialog(INFO_ERROR);
			password.requestFocus();
			return;
		}
		
		if (hasDigit(localPassword)) {
			result = new RegexResult(false, "密码需为数字和字母的组合");
			showDialog(INFO_ERROR);
			password.requestFocus();
			return;
		}
		
		if (!localPassword.equals(localConfirmpassword)) {
			DialogUtil.createDialog(this, 4, "两次输入的密码不一致");
			return;
		}
		// 调用接口
		new CommitAsyncTask().execute(id_num, cardNumDES, mobile, localMobile,
				MD5.getMD5ofStr(id_num + localPassword));
	}

	class CommitAsyncTask extends AsyncTask<String, Object, Object> {
    @Override
    protected Object doInBackground(String... params) {
      HttpHelper httpHelper = HttpHelper.getInstance(RegisterActivity.this);
      List<NameValuePair> arg = new ArrayList<NameValuePair>();
      arg.add(new BasicNameValuePair("cipAlias", params[0]));
      arg.add(new BasicNameValuePair("cipCtfno", params[1]));
      arg.add(new BasicNameValuePair("cipMobile", params[2]));
      arg.add(new BasicNameValuePair("cipNamecn", params[3]));
      arg.add(new BasicNameValuePair("cipPassword", params[4]));
      arg.add(new BasicNameValuePair("cipSex", sex));
      arg.add(new BasicNameValuePair("cipCtftype", "20"));
      return httpHelper.post(ContantsAddress.CUST_REG, arg, Response.class);
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
			loginErrorMessage = "服务器请求异常!";
			DialogUtil.createDialog(this, 1, loginErrorMessage);
			return;
		} else {
			if (RET_CODE.equals(response.getCode())) {
				Toast.makeText(RegisterActivity.this, "注册提交", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(RegisterActivity.this,
						VertifyActivity.class);
				intent.putExtra("user_mobile", mobile);
				intent.putExtra("user_name", id_num);
				intent.putExtra("pass_word", localPassword);

				startActivity(intent);
				RegisterActivity.this.finish();
			} else if (response.getCode() == 90017) {
				loginErrorMessage = response.getMsg();
				DialogUtil.showDialogOneButton(RegisterActivity.this, loginErrorMessage, new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						finish();
					}
				});
			}
			
			else {
				loginErrorMessage = response.getMsg();
				DialogUtil.createDialog(this, 4, loginErrorMessage);
			}
		}
	}
	
	private boolean hasDigit(String contant){
		boolean flag = false;
		Pattern pattern = Pattern.compile("[a-zA-Z]+");
		Matcher matcher = pattern.matcher(contant);
		if (matcher.matches()) {
			flag = true;
		}
		return flag;
	}

	@Override
	protected Dialog onCreateDialog(int id, Bundle args) {
		Dialog dialog = null;
		StringBuffer sb = new StringBuffer();

		switch (id) {
		case INFO_ERROR:
			sb.append(result.msg);
			dialog = DialogUtil.showDialogOneButton2(RegisterActivity.this,
					sb.toString());
			break;
		default:
			break;
		}
		return super.onCreateDialog(id, args);
	}
}
