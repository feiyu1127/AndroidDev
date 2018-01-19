package com.yucheng.byxf.mini.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.apfx.utl.RegexResult;
import com.yucheng.byxf.comm.ScreenManager;
import com.yucheng.byxf.mini.message.LoginResponse;
import com.yucheng.byxf.mini.message.Response;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DES3Utils;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.PINUtils;
import com.yucheng.byxf.mini.utils.RegexCust;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MiniCardActivateActivity extends BaseActivity implements
		OnClickListener {
	private TextView mini_card_certificate_style;
	private TextView mini_card_certificate_number;
	private EditText mini_card_number;
	private EditText mini_card_trade_psw;
	private EditText mini_card_confirm_psw;
	private TextView mini_card_atten_jihuo;
	private EditText mini_card_cvv;

	// 信息有误
	private final int INFO_ERROR = 0;
	// 验证结果
	private RegexResult result;

	private String cvvNum;
	private String newPsw;
	private String cardNum;
	private String idNum;
	private ImageView back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mini_card_jihuo);
		initView();
	}

	private void initView() {
		mini_card_certificate_style = (TextView) findViewById(R.id.mini_card_certificate_style);
		mini_card_certificate_number = (TextView) findViewById(R.id.mini_card_certificate_number);
		mini_card_number = (EditText) findViewById(R.id.mini_card_number);
		mini_card_trade_psw = (EditText) findViewById(R.id.mini_card_trade_psw);
		mini_card_confirm_psw = (EditText) findViewById(R.id.mini_card_confirm_psw);
		mini_card_atten_jihuo = (TextView) findViewById(R.id.mini_card_atten_jihuo);
		mini_card_cvv = (EditText) findViewById(R.id.mini_card_cvv);
		back = (ImageView) findViewById(R.id.mini_card_activate_menu);
		initData();
	}

	private void initData() {
		mini_card_certificate_style.setText("20");
		String idNo = "";
		if(Contents.response != null && Contents.response.getResult() != null)
			idNo = Contents.response.getResult().getIdNo();
		mini_card_certificate_number.setText(idNo);
		idNum = idNo;
		initListener();
	}

	private void initListener() {
		mini_card_atten_jihuo.setOnClickListener(this);
		back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mini_card_atten_jihuo:
			String st_card_number = mini_card_number.getText().toString();
			cardNum = st_card_number;
			String str_trade_psw = mini_card_trade_psw.getText().toString();
			newPsw = str_trade_psw;
			String str_confirm_psw = mini_card_confirm_psw.getText().toString();
			String str_card_cvv = mini_card_cvv.getText().toString();
			cvvNum = str_card_cvv;
			RegexResult temp = null;
			temp = RegexCust.required("卡号", st_card_number);
			if (temp.match) {
				if (st_card_number.length() != 16
						&& st_card_number.length() != 19) {
					System.out.println(st_card_number.length());
					result = new RegexResult(false, "卡号为16位或19位数字");
					showDialog(INFO_ERROR);
					mini_card_number.requestFocus();
					return;
				}
			} else {
				result = temp;
				showDialog(INFO_ERROR);
				mini_card_number.requestFocus();
				return;
			}

			temp = RegexCust.required("交易密码", str_trade_psw);
			if (temp.match) {
				if (str_trade_psw.length() < 6) {
					result = new RegexResult(false, "交易密码为6位数字!");
					showDialog(INFO_ERROR);
					mini_card_trade_psw.requestFocus();
					return;
				}
			} else {
				result = temp;
				showDialog(INFO_ERROR);
				mini_card_trade_psw.requestFocus();
				return;
			}

			temp = RegexCust.required("确认交易密码", str_confirm_psw);
			if (temp.match) {
				if (!str_trade_psw.equalsIgnoreCase(str_confirm_psw)) {
					result = new RegexResult(false, "两次输入密码不同！");
					showDialog(INFO_ERROR);
					mini_card_confirm_psw.requestFocus();
					return;
				}
			} else {
				result = temp;
				showDialog(INFO_ERROR);
				mini_card_confirm_psw.requestFocus();
				return;
			}

			temp = RegexCust.required("CVV码", str_card_cvv);
			if (temp.match) {
				if (str_card_cvv.length() != 3) {
					result = new RegexResult(false, "CVV码为三位数字！");
					showDialog(INFO_ERROR);
					mini_card_cvv.requestFocus();
					return;
				}
			} else {
				result = temp;
				showDialog(INFO_ERROR);
				mini_card_cvv.requestFocus();
				return;
			}
			String st = PINUtils.computePinBlock(cardNum, newPsw);
			System.out.println("st==>"+st);
			newPsw = DES3Utils.encryptBy3DES(st,
					ContantsAddress.CARD_ACTIVATE_KEY);
			 new ActivateAsyncTask().execute(idNum,cardNum,newPsw,cvvNum);
			break;
		case R.id.mini_card_activate_menu:
			finish();
			break;
		}

	}

	class ActivateAsyncTask extends AsyncTask<String, Object, Object> {

    @Override
    protected Object doInBackground(String... params) {
      HttpHelper httpHelper = HttpHelper.getInstance(MiniCardActivateActivity.this);
      List<NameValuePair> arg = new ArrayList<NameValuePair>();
      arg.add(new BasicNameValuePair("fpjrnlNum", ""));
      arg.add(new BasicNameValuePair("staffId", ""));
      arg.add(new BasicNameValuePair("idType", "20"));
      arg.add(new BasicNameValuePair("idNum", idNum));
      arg.add(new BasicNameValuePair("cardNum", cardNum));
      arg.add(new BasicNameValuePair("newPsw", newPsw));
      arg.add(new BasicNameValuePair("cvvNum", cvvNum));
      return httpHelper.post(ContantsAddress.MINI_CARD_ACTIVATE, arg, Response.class);
    }

		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			dismissProgressDialog();
			Response response = (Response) result;
			Dialog dialog;
			if (response != null) {
				if (response.getCode() == 0) {
					dialog = DialogUtil.showDialogOneButton(
							MiniCardActivateActivity.this, response.getMsg(),
							new OnClickListener() {

								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									ScreenManager screenManager = ScreenManager
											.getScreenManager();
									screenManager.popAllActivityExceptOne();
									Intent intent = new Intent(
											MiniCardActivateActivity.this,
											HomeActivity.class);
									startActivity(intent);
								}
							});
				} else {
					dialog = DialogUtil.showDialogOneButton2(
							MiniCardActivateActivity.this, response.getMsg());
				}
			} else {

			}
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			showProgressDialog();
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
					MiniCardActivateActivity.this, sb.toString());
			break;

		default:
			break;
		}
		return super.onCreateDialog(id, args);
	}

}
