package com.yucheng.byxf.mini.ui;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.message.RelaxBillDetailResult;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class RelaxLoanBillActivity extends BaseActivity {

	private String loginErrorMessage;
	private String loanNo = null;
	private String tnr = null;
	private GsonBuilder gsonBuilder;
	private Gson gson;
	private RelaxBillDetailResult response;
	EditText client_name;
	EditText contract_num;
	EditText loan_deadline;
	EditText extenddate;
	EditText duedate;
	EditText repay_way;
	EditText loan_money;
	EditText withhold_num;
	EditText month_interest_rate;
	EditText shouxufei_rate;
	Button check_bill;
	TextView register;
	View nianlilv;
	View shouxufei;

	private ImageView back;

	private RelaxLoanBillActivity activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.relaxloanbill_activity);
		activity = new RelaxLoanBillActivity();
		Intent intent = getIntent();
		loanNo = intent.getExtras().getString("loanNo");
		tnr = intent.getExtras().getString("tnr");
		System.out.println("tnr==>" + tnr);
		initView();
		if ("1".equals(tnr)) {
//			check_bill.setVisibility(View.GONE);
			register.setText("极速贷");
//			nianlilv.setVisibility(View.GONE);
		}

	}

	private void initView() {
		// TODO Auto-generated method stub
		client_name = (EditText) findViewById(R.id.client_name);
		contract_num = (EditText) findViewById(R.id.contract_num);
		loan_deadline = (EditText) findViewById(R.id.loan_deadline);
		extenddate = (EditText) findViewById(R.id.extenddate);
		duedate = (EditText) findViewById(R.id.duedate);
		repay_way = (EditText) findViewById(R.id.repay_way);
		loan_money = (EditText) findViewById(R.id.loan_money);
		withhold_num = (EditText) findViewById(R.id.withhold_num);
		month_interest_rate = (EditText) findViewById(R.id.month_interest_rate);
		shouxufei_rate = (EditText) findViewById(R.id.shouxufei_rate);
		shouxufei = findViewById(R.id.shouxufei);
		check_bill = (Button) findViewById(R.id.check_bill_relax);
		register = (TextView) findViewById(R.id.tv_register);
		nianlilv = findViewById(R.id.nianlilv);
		back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		new RelaxLoanAsyncTask().execute(loanNo);

		gsonBuilder = new GsonBuilder();
		gson = gsonBuilder.create();
		check_bill.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RelaxLoanBillActivity.this,
						RelaxLoanBillParticularActivity.class);
				intent.putExtra("response", gson.toJson(response));
				startActivity(intent);
				RelaxLoanBillActivity.this.finish();
			}
		});
	}

	class RelaxLoanAsyncTask extends AsyncTask<String, Object, Object> {

    @Override
    protected Object doInBackground(String... params) {
      HttpHelper httpHelper = HttpHelper.getInstance(RelaxLoanBillActivity.this);
      List<NameValuePair> arg = new ArrayList<NameValuePair>();
      String idNo = "";
	  if(Contents.response != null && Contents.response.getResult() != null)
          idNo = Contents.response.getResult().getIdNo();
      arg.add(new BasicNameValuePair("idNo", idNo));
      arg.add(new BasicNameValuePair("loanNo", loanNo));
      arg.add(new BasicNameValuePair("inputSrc", "08"));
      return httpHelper.post(ContantsAddress.PAYMENT_PLAN, arg, RelaxBillDetailResult.class);
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
				if (result != null) {
					response = (RelaxBillDetailResult) result;
					if (response.getCode() == 0) {
						client_name.setText(IsNull(response.getResult().getCustName())
								.toString().trim());
						contract_num.setText(IsNull(response.getResult().getLoanContNo())
								.toString().trim());
						if(!"".equals(IsNull(response.getResult().getLoanTnr()))){
							loan_deadline.setText(IsNull(response.getResult().getLoanTnr())
									.toString().trim() + "个月");
						}
						extenddate.setText(IsNull(response.getResult().getIntStartDt())
								.toString().trim());
						duedate.setText(IsNull(response.getResult().getLastDueDt())
								.toString().trim());
						if ("1".equals(tnr)) {
							repay_way.setText("一次还本");
							if(response.getResult().getList()!=null && 
									response.getResult().getList().size()>=2){
								//手续费
								shouxufei_rate.setText(response.getResult().getList().get(1).getPsFeeAmt());
							}
							shouxufei.setVisibility(View.VISIBLE);
						} else {
							repay_way.setText(IsNull(response.getResult().getMtdDesc())
									.toString().trim());
							shouxufei.setVisibility(View.GONE);
						}

						loan_money.setText(IsNull(response.getResult().getOrigPrcp())
								.toString().trim());
						withhold_num.setText(IsNull(response.getResult().getAcctNo())
								.toString().trim());
//						if (IsNull(response.getResult().getLoanIntRate())
//								.toString().trim().charAt(0) == 46) {
//							String loanIntRate = "0"
//									+ response.getResult().getLoanIntRate()
//											.toString().trim();
//							month_interest_rate.setText(loanIntRate + "%");
//						} else {
//							
//						}
						if(!"".equals(IsNull(response.getResult().getLoanIntRate()))){
							month_interest_rate.setText(IsNull(response.getResult().getLoanIntRate())
									.toString().trim() + "%");
						}
					}
				} else {
					loginErrorMessage = "服务器请求异常!";
					DialogUtil.createDialog(RelaxLoanBillActivity.this, 1,
							loginErrorMessage);
					return;
				}
			}

		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		activity = null;
	}
	
	/**
	 * 判断数据是否为null，为null转换成"",否则正常
	 * @param str
	 * @return
	 */
	private String IsNull(String str){
		return str != null?str : "";
	}
}