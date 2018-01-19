package com.yucheng.byxf.mini.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.domain.MiniPersonInfo;
import com.yucheng.byxf.mini.easyloan.ui.RelaxedLoanAdActivity;
import com.yucheng.byxf.mini.eloan.domain.EloanEasyCheckConfirmResult;
import com.yucheng.byxf.mini.eloan.domain.EloanEasyCheckResult;
import com.yucheng.byxf.mini.eloan.domain.EloanListCheckEntity;
import com.yucheng.byxf.mini.message.BillNewResponse;
import com.yucheng.byxf.mini.message.MiniCardActivateResult;
import com.yucheng.byxf.mini.message.Response;
import com.yucheng.byxf.mini.message.VerifyCodeResponse;
import com.yucheng.byxf.mini.rapidly.RepidlyLoanInfoContractBook;
import com.yucheng.byxf.mini.utils.ChooseDialog;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.MoneyUtil;
import com.yucheng.byxf.mini.utils.ChooseDialog.OnDialogClickListener;

import android.R.integer;
import android.R.layout;
import android.app.Activity;
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

public class ConfirmContractBook extends BaseActivity {
	/**
	 * 核准确认书页面
	 */
	private List<BillNewResponse> list = new ArrayList<BillNewResponse>();
	private TextView tv_loan_contract_num;
	private TextView tv_loan_name;
	private TextView tv_identify_type;
	private TextView tv_identify_num;
	private TextView tv_standed_loan_up;
	private TextView tv_standed_loan_lower;
	private TextView tv_loan_limit_time;
	private TextView tv_repay_method;
	private TextView tv_loan_month_interest;
	private TextView tv_repay_num;
	private TextView tv_repay_name;
	private TextView tv_loan_num;
	private TextView tv_loan_popurse;
	private static String applCde;
	private static String confirmStatus;
	private EloanListCheckEntity eloanlistcheckentity;

	private Button bt_loan_confirm;

	private ImageView app_schedule_query_menu;

	private List<String> tick_loans;
	private List<String> tick_loan_codes;

	private int time = 180;
	private ChooseDialog chooseDialog;
	// private TextView submit;
	//
	// private EditText yanzhengma;

	private boolean isFlag = true;
	private View layout7;
	private View layout8;
					
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.application_schedule_contractbook_eloan);
		tv_loan_contract_num = (TextView) findViewById(R.id.tv_loan_contract_num);
		tv_loan_name = (TextView) findViewById(R.id.tv_loan_name);
		tv_identify_type = (TextView) findViewById(R.id.tv_identify_type);
		tv_identify_num = (TextView) findViewById(R.id.tv_identify_num);
		tv_standed_loan_up = (TextView) findViewById(R.id.tv_standed_loan_up);
		tv_standed_loan_lower = (TextView) findViewById(R.id.tv_standed_loan_lower);
		tv_loan_limit_time = (TextView) findViewById(R.id.tv_loan_limit_time);
		tv_repay_method = (TextView) findViewById(R.id.tv_repay_method);
		tv_loan_month_interest = (TextView) findViewById(R.id.tv_loan_month_interest);
		tv_repay_num = (TextView) findViewById(R.id.tv_repay_num);
		tv_repay_name = (TextView) findViewById(R.id.tv_repay_name);
		tv_loan_num = (TextView) findViewById(R.id.tv_loan_num);
		tv_loan_popurse = (TextView) findViewById(R.id.tv_loan_popurse);
		bt_loan_confirm = (Button) findViewById(R.id.bt_loan_confirm);
		app_schedule_query_menu = (ImageView) findViewById(R.id.app_schedule_query_menu);
		layout7 = findViewById(R.id.app_schedule_re7);
		layout8 = findViewById(R.id.app_schedule_re8);
		// submit = (TextView) findViewById(R.id.submit);
		// yanzhengma = (EditText) findViewById(R.id.yanzhengma);
		tick_loans = new ArrayList<String>();
		tick_loans.add("装修");
		tick_loans.add("教育");
		tick_loans.add("旅游");
		tick_loans.add("婚庆");
		tick_loans.add("其它");
		tick_loan_codes = new ArrayList<String>();
		tick_loan_codes.add("DEC");
		tick_loan_codes.add("EDU");
		tick_loan_codes.add("TRA");
		tick_loan_codes.add("MAR");
		tick_loan_codes.add("OTH");

		bt_loan_confirm.setClickable(false);
		bt_loan_confirm.setBackgroundResource(R.drawable.confirm_unclick);

		bt_loan_confirm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// String st_yanzhengma = yanzhengma.getText().toString();
				// if ("".equals(st_yanzhengma)) {
				// DialogUtil.showDialogOneButton2(ConfirmContractBook.this,
				// "验证码不能为空");
				// return;
				// }
				bt_loan_confirm.setClickable(false);
				bt_loan_confirm
						.setBackgroundResource(R.drawable.confirm_unclick);
				new GetConfirmLoanBook().execute(applCde);
				// ,
				// Contents.response.getResult().getCipMobile()
			}
		});
		app_schedule_query_menu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		// submit.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View arg0) {
		// // TODO Auto-generated method stub
		// if (isFlag) {
		// new VertifyCodeAsyncTask().execute(Contents.response
		// .getResult().getCipMobile());
		// }
		// }
		// });
 
	}
private void init(){
	chooseDialog = new ChooseDialog();
	chooseDialog.createTipsDialog(ConfirmContractBook.this);
	chooseDialog.showDialog();
	chooseDialog
			.setOnDialogClickListener(new OnDialogClickListener() {

				@Override
				public void onPos() {
					// TODO Auto-generated method stub
					chooseDialog.dismissDialog();
					chuandiValue();
				}

				@Override
				public void onNeg() {

				}
			});
}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		applCde = getIntent().getStringExtra("applCde");
		confirmStatus = getIntent().getStringExtra("confirmStatus");
		if (confirmStatus.equals("Y")) {
			bt_loan_confirm.setClickable(false);
			// yanzhengma.setFocusable(false);
			// submit.setClickable(false);
			// submit.setBackgroundResource(R.drawable.timer_dao);
			bt_loan_confirm.setBackgroundResource(R.drawable.confirm_unclick);
		} else {
			bt_loan_confirm.setClickable(true);
			bt_loan_confirm.setBackgroundResource(R.drawable.confirm);
		}
		new GetLoanCardApplicationSchedule().execute(applCde);
	}

	class GetLoanCardApplicationSchedule extends
			AsyncTask<String, Object, Object> {
    @Override
    protected Object doInBackground(String... params) {
      HttpHelper httpHelper = HttpHelper.getInstance(ConfirmContractBook.this);
      List<NameValuePair> arg = new ArrayList<NameValuePair>();
      arg.add(new BasicNameValuePair("applCode", params[0]));
      arg.add(new BasicNameValuePair("idType", "20"));
		String idNo = "";
		if(Contents.response != null && Contents.response.getResult() != null)
			idNo = Contents.response.getResult().getIdNo();
      arg.add(new BasicNameValuePair("idNo", idNo));
      return httpHelper.post(ContantsAddress.ELOAN_CONFIRMATION, arg, EloanEasyCheckResult.class);
    }

		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			dismissProgressDialog();
			// isMiniFlag = true;
			EloanEasyCheckResult response = (EloanEasyCheckResult) result;
			if (response != null) {
				if (response.getCode() == 0) {
					eloanlistcheckentity = response.getResult();
					tv_loan_contract_num.setText(eloanlistcheckentity
							.getContNo());
					tv_loan_name.setText(eloanlistcheckentity.getCustName());
					// tv_identify_type.setText(eloanlistcheckentity.getIdType());
					tv_identify_type.setText("身份证");
					tv_identify_num.setText(eloanlistcheckentity.getIdNo());

					if (null != eloanlistcheckentity.getApplyAmt()
							&& !"".equals(eloanlistcheckentity.getApplyAmt())) {
						String money = MoneyUtil
								.amountToChinese(Double
										.parseDouble(eloanlistcheckentity
												.getApplyAmt()));
						tv_standed_loan_up.setText(money);
					}
					tv_standed_loan_lower.setText(eloanlistcheckentity
							.getApplyAmt());
					tv_loan_limit_time.setText(eloanlistcheckentity.getTnr()
							+ "个月");
					if (eloanlistcheckentity.getTnr().equals("1")) {
						layout7.setVisibility(View.GONE);
						layout8.setVisibility(View.GONE);
						tv_repay_method.setText("一次还本");
					}else {
						tv_repay_method.setText("等额本息");
					}
					// tv_repay_method.setText(eloanlistcheckentity.getLoanMtd());
					tv_loan_month_interest.setText(eloanlistcheckentity
							.getMinthRat() + "%");
					tv_repay_num.setText(eloanlistcheckentity.getInstalAmt());
					tv_repay_name.setText(eloanlistcheckentity
							.getApplDisbAcNam());
					tv_loan_num.setText(eloanlistcheckentity.getApplDisbAcNo());
					for (int i = 0; i < tick_loans.size(); i++) {
						if (tick_loan_codes.get(i).contains(
								eloanlistcheckentity.getPurpose())) {
							tv_loan_popurse.setText(tick_loans.get(i));
						}
					}
					if ("N".equals(response.getResult().getPsSignInd())
							&& "200".equals(response.getResult()
									.getContStatus())) {
						bt_loan_confirm.setClickable(true);
						bt_loan_confirm
								.setBackgroundResource(R.drawable.confirm);
					} else {
						bt_loan_confirm.setClickable(false);
						// yanzhengma.setFocusable(false);
						// submit.setClickable(false);
						// submit.setBackgroundResource(R.drawable.timer_dao);
						bt_loan_confirm
								.setBackgroundResource(R.drawable.confirm_unclick);
						if ("N".equals(response.getResult().getPsSignInd())
								&& "300".equals(response.getResult()
										.getContStatus())) {
							DialogUtil.showDialogOneButton2(
									ConfirmContractBook.this,
									"你的核准确认书已经超过了签订期限");
						}
					}
				}
			}
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			showProgressDialog();
		}
	}

	// private void timer() {
	// new Thread() {
	// public void run() {
	// while (time > 0) {
	// time--;
	// try {
	// Thread.sleep(1000);
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// handler.sendEmptyMessage(0);
	//
	// }
	// handler.sendEmptyMessage(1);
	// };
	// }.start();
	// }

	class VertifyCodeAsyncTask extends AsyncTask<String, Object, Object> {
    @Override
    protected Object doInBackground(String... params) {
      HttpHelper httpHelper = HttpHelper.getInstance(ConfirmContractBook.this);
      List<NameValuePair> arg = new ArrayList<NameValuePair>();
      arg.add(new BasicNameValuePair("phoneNum", params[0]));
      return httpHelper.post(ContantsAddress.SEND_VERIFY_CODE, arg, VerifyCodeResponse.class);
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
			DialogUtil.createDialog(this, 1, "服务器请求异常!");
			return;
		} else {
			if (response.getCode() == 0) {
				time = response.getResult();
				Toast.makeText(ConfirmContractBook.this,
						"获取验证码成功" + response.getMsg(), Toast.LENGTH_LONG).show();
				// timer();
				// submit.setBackgroundResource(R.drawable.timer_dao);
				isFlag = !isFlag;
			} else {
				DialogUtil.createDialog(this, 4, response.getMsg());
			}
		}
	}

	// Handler handler = new Handler() {
	// public void handleMessage(android.os.Message msg) {
	// if (msg.what == 0) {
	// submit.setText(time + "秒后可获取");
	// } else if (msg.what == 1) {
	// isFlag = !isFlag;
	// submit.setBackgroundResource(R.drawable.timer);
	// }
	// };
	// };

	class GetConfirmLoanBook extends AsyncTask<String, Object, Object> {
    @Override
    protected Object doInBackground(String... params) {
      HttpHelper httpHelper = HttpHelper.getInstance(ConfirmContractBook.this);
      List<NameValuePair> arg = new ArrayList<NameValuePair>();
      arg.add(new BasicNameValuePair("applSeq", params[0]));
      // arg.add(new BasicNameValuePair("msgCode", params[1]));
      // arg.add(new BasicNameValuePair("phoneNum", params[2]));
      arg.add(new BasicNameValuePair("idType", "20"));
		String idNo = "";
		if(Contents.response != null && Contents.response.getResult() != null)
			idNo = Contents.response.getResult().getIdNo();
      arg.add(new BasicNameValuePair("idNo", idNo));
      return httpHelper.post(ContantsAddress.ELOAN_CONFIRM_CONFIRMATION, arg, EloanEasyCheckConfirmResult.class);
    }

		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			dismissProgressDialog();
			EloanEasyCheckConfirmResult response = (EloanEasyCheckConfirmResult) result;
			if (response != null) {
				if (response.getCode() == 0) {
					//chuandiValue();
			init();
				} else {
					bt_loan_confirm.setClickable(true);
					bt_loan_confirm.setBackgroundResource(R.drawable.confirm);
					Toast.makeText(ConfirmContractBook.this,
							response.getMsg().toString(), Toast.LENGTH_LONG).show();
				}
			} else {
				bt_loan_confirm.setClickable(true);
				bt_loan_confirm.setBackgroundResource(R.drawable.confirm);
				Toast.makeText(ConfirmContractBook.this, getResources().getString(R.string.no_network), Toast.LENGTH_LONG).show();
			}
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			showProgressDialog();
		}
	}

	private void chuandiValue() {
		Toast.makeText(this, "确认成功", Toast.LENGTH_LONG).show();
		Intent data = new Intent();
		data.putExtra("confirmStatus", "Y");
		setResult(20, data);
		finish();
	}

}
