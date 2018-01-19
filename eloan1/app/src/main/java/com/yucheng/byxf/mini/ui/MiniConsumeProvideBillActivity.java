package com.yucheng.byxf.mini.ui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.message.BillResponse;
import com.yucheng.byxf.mini.message.MiniCardBillDetail;
import com.yucheng.byxf.mini.message.MiniCardBillResponse;
import com.yucheng.byxf.mini.message.MiniNoBillResponse;
import com.yucheng.byxf.mini.ui.BillActivity.GetBillAsyncTask;
import com.yucheng.byxf.mini.utils.ContantsAddress;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Mini循环消费贷已出账单
 */
public class MiniConsumeProvideBillActivity extends BaseActivity implements
		OnClickListener {

	private Button check_bill;
	private String date;
	private String end_date;
	private int year = 2015;
	private int month = 3;
	private int end_month=4;
	private int end_year=2015;
	private ImageView back;
	private Button bt_left_a;
	private Button bt_right_a;
	private TextView lastMonth;
	private TextView nextMonth;
	private TextView nowMonth;
	private EditText psDueDt;
	private EditText curBillAmt;
	private EditText minAmt;
	private EditText setlAmt;
	private EditText curAddAmt;
	private EditText allOdInt;
	private EditText curNormInt;
	private EditText curCashAmt;
	private EditText curFeeAmt;
	private EditText odAmt;
	private EditText cashNum;
	private LinearLayout imageView;
	private ScrollView scrollView_Y;
	private ScrollView scrollView_N;
	private static final Integer RET_CODE = 0;
	private static final Integer NOT_CODE = 20201;
	private String cardNo = "";
	// 账单状态
	// private String billInd = "Y";
	// 已出还是未出
	private boolean isBill = true;
	private MiniCardBillResponse response_Y;
	private MiniNoBillResponse response_N;
	private HashMap<String, MiniCardBillResponse> response_Y_Hash = new HashMap<String, MiniCardBillResponse>();
	private HashMap<String, MiniNoBillResponse> response_N_Hash = new HashMap<String, MiniNoBillResponse>();
	Calendar calendar = Calendar.getInstance();
	private EditText hostTxDt;
	private EditText acctDt;
	private EditText txAmt;
	private EditText totalInt;
	private EditText txTyp;
	private EditText txDesc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mini_consume_provide_bill);
		Intent intent = getIntent();
		cardNo = intent.getExtras().getString("cardNo");

		initView();

		check_bill.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 已出账单 查询账单详情 ---跳转
				Intent intent = new Intent(MiniConsumeProvideBillActivity.this,
						BillDetailsActivity.class);
				intent.putExtra("month", month + "");
				intent.putExtra("cardNo", cardNo);
				startActivity(intent);
			}
		});
	//日期操作
//		year = calendar.get(Calendar.YEAR);
//		month = calendar.get(Calendar.MONTH) + 1;
	//测试环境日期	
		year=2013;
		month=4;
		
		end_date=endDate(month,year);
		if (month > 10) {
			date = "" + year + "-" + month;
		} else {
			date = "" + year + "-0" + month;
		}
		nowMonth.setText(date);

		new GetBillAsyncTask().execute("9999019000000061", date,
				end_date);

	}

	private void initView() {
		check_bill = (Button) findViewById(R.id.check_bill);
		back = (ImageView) findViewById(R.id.back);
		bt_left_a = (Button) findViewById(R.id.bt_left_aa);
		bt_right_a = (Button) findViewById(R.id.bt_right_aa);

		lastMonth = (TextView) findViewById(R.id.lastMonth);
		nextMonth = (TextView) findViewById(R.id.nextMonth);
		nowMonth = (TextView) findViewById(R.id.nowMonth);

		psDueDt = (EditText) findViewById(R.id.psDueDt);
		curBillAmt = (EditText) findViewById(R.id.curBillAmt);
		minAmt = (EditText) findViewById(R.id.minAmt);
		setlAmt = (EditText) findViewById(R.id.setlAmt);
		curAddAmt = (EditText) findViewById(R.id.curAddAmt);
		allOdInt = (EditText) findViewById(R.id.allOdInt);
		curNormInt = (EditText) findViewById(R.id.curNormInt);
		curCashAmt = (EditText) findViewById(R.id.curCashAmt);
		curFeeAmt = (EditText) findViewById(R.id.curFeeAmt);
		odAmt = (EditText) findViewById(R.id.odAmt);
		cashNum = (EditText) findViewById(R.id.cashNum);

		hostTxDt = (EditText) findViewById(R.id.hostTxDt);
		acctDt = (EditText) findViewById(R.id.acctDt);
		txAmt = (EditText) findViewById(R.id.txAmt);
		totalInt = (EditText) findViewById(R.id.totalInt);
		txTyp = (EditText) findViewById(R.id.txTyp);
		txDesc = (EditText) findViewById(R.id.txDesc);

		scrollView_Y = (ScrollView) findViewById(R.id.scrollview1);
		scrollView_N = (ScrollView) findViewById(R.id.scrollview2);
		imageView = (LinearLayout) findViewById(R.id.imageview);

		bt_left_a.setOnClickListener(this);
		bt_right_a.setOnClickListener(this);
		lastMonth.setOnClickListener(this);
		nextMonth.setOnClickListener(this);
		nowMonth.setOnClickListener(this);
		back.setOnClickListener(this);
	}
/**
 *  Mini已出账单 
 *
 */
	class GetBillAsyncTask extends AsyncTask<String, Object, Object> {
		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(MiniConsumeProvideBillActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("cardNo", params[0]));
			arg.add(new BasicNameValuePair("startBillDt", params[1]));
			arg.add(new BasicNameValuePair("endBillDt", params[2]));
			return httpHelper.post(ContantsAddress.MINI_IN, arg,
					MiniCardBillResponse.class);
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
			isBill = true;
			getResponse(result);
		}
	}
/**
 * 
 * 未出账 账单查询
 *
 */
	class GetNoBillAsyncTask extends AsyncTask<String, Object, Object> {
		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(MiniConsumeProvideBillActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("cardNo", params[0]));
			return httpHelper.post(ContantsAddress.MINI_OUT, arg,
					MiniNoBillResponse.class);
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
			isBill = false;
			getResponse(result);
		}
	}

	private void getResponse(Object result) {
		if (isBill) {
			/**
			 * 已出账单
			 */
			
			MiniCardBillResponse response = (MiniCardBillResponse) result;
			if (result == null) {
				Toast.makeText(MiniConsumeProvideBillActivity.this,
						response.getMsg(), Toast.LENGTH_SHORT).show();
				scrollView_Y.setVisibility(View.GONE);
				scrollView_N.setVisibility(View.GONE);
				check_bill.setVisibility(View.GONE);
				imageView.setVisibility(View.VISIBLE);
				return;
			} else if (RET_CODE.equals(response.getCode())) {
				scrollView_Y.setVisibility(View.VISIBLE);
				scrollView_N.setVisibility(View.GONE);
				check_bill.setVisibility(View.VISIBLE);
				imageView.setVisibility(View.GONE);
				response_Y = response;
				response_Y_Hash.put(date, response_Y);
				setEidtDate(response_Y);
			} else if (NOT_CODE.equals(response.getCode())) {
				scrollView_Y.setVisibility(View.GONE);
				scrollView_N.setVisibility(View.GONE);
				check_bill.setVisibility(View.GONE);
				imageView.setVisibility(View.VISIBLE);
			} else {
				Toast.makeText(MiniConsumeProvideBillActivity.this, "服务器请求异常",
						Toast.LENGTH_SHORT).show();
				scrollView_Y.setVisibility(View.GONE);
				scrollView_N.setVisibility(View.GONE);
				check_bill.setVisibility(View.GONE);
				imageView.setVisibility(View.VISIBLE);
			}
		} else {
			/**
			 * 未出账单
			 */
			
			MiniNoBillResponse response2 = (MiniNoBillResponse) result;
			if (result == null) {
//				Toast.makeText(MiniConsumeProvideBillActivity.this,
//						response2.getMsg(), Toast.LENGTH_SHORT).show();
//				scrollView_Y.setVisibility(View.GONE);
//				scrollView_N.setVisibility(View.GONE);
//				check_bill.setVisibility(View.GONE);
//				imageView.setVisibility(View.VISIBLE);
				return;
			} else if (RET_CODE.equals(response2.getCode())) {
				scrollView_Y.setVisibility(View.GONE);
				scrollView_N.setVisibility(View.VISIBLE);
				check_bill.setVisibility(View.GONE);
				imageView.setVisibility(View.GONE);
				response_N = response2;
				response_N_Hash.put(date, response_N);
			//	setEidtDate(response_N);
			} else if (NOT_CODE.equals(response2.getCode())) {
				scrollView_Y.setVisibility(View.GONE);
				scrollView_N.setVisibility(View.GONE);
				check_bill.setVisibility(View.GONE);
				imageView.setVisibility(View.VISIBLE);
			} else {
				Toast.makeText(MiniConsumeProvideBillActivity.this, "服务器请求异常",
						Toast.LENGTH_SHORT).show();
				scrollView_Y.setVisibility(View.GONE);
				scrollView_N.setVisibility(View.GONE);
				check_bill.setVisibility(View.GONE);
				imageView.setVisibility(View.VISIBLE);
			}
		}
	}

	private void setEidtDate(MiniCardBillResponse response) {
		if (!response.getResult().get(0).getPsDueDt().equals("")
				&& null != response.getResult().get(0).getPsDueDt()) {
			if (response.getResult().get(0).getPsDueDt().trim().startsWith(".")) {
				psDueDt.setText("0" + response.getResult().get(0).getPsDueDt()
						+ "");
			} else {
				psDueDt.setText(response.getResult().get(0).getPsDueDt() + "");
			}
		}

		if (!response.getResult().get(0).getCurBillAmt().equals("")
				&& null != response.getResult().get(0).getCurBillAmt()) {
			if (response.getResult().get(0).getCurBillAmt().trim()
					.startsWith(".")) {
				curBillAmt.setText("0"
						+ response.getResult().get(0).getCurBillAmt() + "");
			} else {
				curBillAmt.setText(response.getResult().get(0).getCurBillAmt()
						+ "");
			}
		}

		if (!response.getResult().get(0).getMinAmt().equals("")
				&& null != response.getResult().get(0).getMinAmt()) {
			if (response.getResult().get(0).getMinAmt().trim().startsWith(".")) {
				minAmt.setText("0" + response.getResult().get(0).getMinAmt()
						+ "");
			} else {
				minAmt.setText(response.getResult().get(0).getMinAmt() + "");
			}
		}

		if (!response.getResult().get(0).getSetlAmt().equals("")
				&& null != response.getResult().get(0).getSetlAmt()) {
			if (response.getResult().get(0).getSetlAmt().trim().startsWith(".")) {
				setlAmt.setText("0" + response.getResult().get(0).getSetlAmt()
						+ "");
			} else {
				setlAmt.setText(response.getResult().get(0).getSetlAmt() + "");
			}
		}

		if (!response.getResult().get(0).getCurAddAmt().equals("")
				&& null != response.getResult().get(0).getCurAddAmt()) {
			if (response.getResult().get(0).getCurAddAmt().trim()
					.startsWith(".")) {
				curAddAmt.setText("0"
						+ response.getResult().get(0).getCurAddAmt() + "");
			} else {
				curAddAmt.setText(response.getResult().get(0).getCurAddAmt()
						+ "");
			}
		}

		if (!response.getResult().get(0).getAllOdInt().equals("")
				&& null != response.getResult().get(0).getAllOdInt()) {
			if (response.getResult().get(0).getAllOdInt().trim()
					.startsWith(".")) {
				allOdInt.setText("0"
						+ response.getResult().get(0).getAllOdInt() + "");
			} else {
				allOdInt.setText(response.getResult().get(0).getAllOdInt() + "");
			}
		}

		if (!response.getResult().get(0).getCurNormInt().equals("")
				&& null != response.getResult().get(0).getCurNormInt()) {
			if (response.getResult().get(0).getCurNormInt().trim()
					.startsWith(".")) {
				curNormInt.setText("0"
						+ response.getResult().get(0).getCurNormInt() + "");
			} else {
				curNormInt.setText(response.getResult().get(0).getCurNormInt()
						+ "");
			}
		}

		if (!response.getResult().get(0).getCurCashAmt().equals("")
				&& null != response.getResult().get(0).getCurCashAmt()) {
			if (response.getResult().get(0).getCurCashAmt().trim()
					.startsWith(".")) {
				curCashAmt.setText("0"
						+ response.getResult().get(0).getCurCashAmt() + "");
			} else {
				curNormInt.setText(response.getResult().get(0).getCurCashAmt()
						+ "");
			}
		}

		if (!response.getResult().get(0).getCurFeeAmt().equals("")
				&& null != response.getResult().get(0).getCurFeeAmt()) {
			if (response.getResult().get(0).getCurFeeAmt().trim()
					.startsWith(".")) {
				curFeeAmt.setText("0"
						+ response.getResult().get(0).getCurFeeAmt() + "");
			} else {
				curFeeAmt.setText(response.getResult().get(0).getCurFeeAmt()
						+ "");
			}
		}
		if (!response.getResult().get(0).getCashNum().equals("")
				&& null != response.getResult().get(0).getCashNum()) {
			if (response.getResult().get(0).getCashNum().trim().startsWith(".")) {
				cashNum.setText("0" + response.getResult().get(0).getCashNum()
						+ "");
			} else {
				cashNum.setText(response.getResult().get(0).getCashNum() + "");
			}
		}
	}

  private void setEidtDate(MiniNoBillResponse response) {
		if (!response.getResult().getDetails().get(0).getHostTxDt().equals("")
				&& null != response.getResult().getDetails().get(0).getHostTxDt()) {
			if (response.getResult().getDetails().get(0).getHostTxDt().trim().startsWith(".")) {
				hostTxDt.setText("0" + response.getResult().getDetails().get(0).getHostTxDt() + "");
			} else {
				hostTxDt.setText(response.getResult().getDetails().get(0).getHostTxDt() + "");
			}
		}

		if (!response.getResult().getDetails().get(0).getAcctDt().equals("")
				&& null != response.getResult().getDetails().get(0).getAcctDt()) {
			if (response.getResult().getDetails().get(0).getAcctDt().trim().startsWith(".")) {
				acctDt.setText("0" + response.getResult().getDetails().get(0).getAcctDt() + "");
			} else {
				acctDt.setText(response.getResult().getDetails().get(0).getAcctDt() + "");
			}
		}

		if (!response.getResult().getDetails().get(0).getTxAmt().equals("")
				&& null != response.getResult().getDetails().get(0).getTxAmt()) {
			if (response.getResult().getDetails().get(0).getTxAmt().trim().startsWith(".")) {
				txAmt.setText("0" + response.getResult().getDetails().get(0).getTxAmt() + "");
			} else {
				txAmt.setText(response.getResult().getDetails().get(0).getTxAmt() + "");
			}
		}
		if (!response.getResult().getTotalInt().equals("")
				&& null != response.getResult().getTotalInt()) {
			if (response.getResult().getTotalInt().trim().startsWith(".")) {
				totalInt.setText("0" + response.getResult().getTotalInt() + "");
			} else {
				totalInt.setText(response.getResult().getTotalInt() + "");
			}
		}
		if (!response.getResult().getCurNormInt().equals("")
				&& null != response.getResult().getCurNormInt()) {
			if (response.getResult().getCurNormInt().trim().startsWith(".")) {
				curNormInt.setText("0" + response.getResult().getCurNormInt()
						+ "");
			} else {
				curNormInt.setText(response.getResult().getCurNormInt() + "");
			}
		}

		if (!response.getResult().getDetails().get(0).getTxTyp().equals("")
				&& null != response.getResult().getDetails().get(0).getTxTyp()) {
			if (response.getResult().getDetails().get(0).getTxTyp().trim().startsWith(".")) {
				txTyp.setText("0" + response.getResult().getDetails().get(0).getTxTyp() + "");
			} else {
				txTyp.setText(response.getResult().getDetails().get(0).getTxTyp() + "");
			}
		}

		if (!response.getResult().getDetails().get(0).getTxDesc().equals("")
				&& null != response.getResult().getDetails().get(0).getTxDesc()) {
			if (response.getResult().getDetails().get(0).getTxDesc().trim().startsWith(".")) {
				txDesc.setText("0" + response.getResult().getDetails().get(0).getTxDesc() + "");
			} else {
				txDesc.setText(response.getResult().getDetails().get(0).getTxDesc() + "");
			}
		}
	}
 
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
			/**
			 * 已出账单
			 */
		case R.id.bt_left_aa:
		response_Y = response_Y_Hash.get(date);
			if (response_Y == null) {
			//	new GetBillAsyncTask().execute(cardNo, date, billInd);
				new GetBillAsyncTask().execute("9999019000000061", date,
						end_date);
			} else {
				new GetBillAsyncTask().execute("9999019000000061", date,
						end_date);
//				setEidtDate(response_Y);
//				scrollView_Y.setVisibility(View.VISIBLE);
//				scrollView_N.setVisibility(View.GONE);
//				check_bill.setVisibility(View.VISIBLE);
//				imageView.setVisibility(View.GONE);
			}
			bt_left_a.setTextColor(Color.WHITE);
			bt_right_a.setTextColor(getResources().getColor(R.color.headcolor));
			bt_left_a.setBackgroundResource(R.drawable.bill_click);
			bt_right_a.setBackgroundResource(R.drawable.unbill_nomal);
			break;
			/**
			 * 未出账单
			 */
		case R.id.bt_right_aa:
			response_N = response_N_Hash.get(date);
			if (response_N == null) {
			//	new GetNoBillAsyncTask().execute(cardNo, date, billInd);
				new GetNoBillAsyncTask().execute("9999019000000061");
			} else {
				new GetNoBillAsyncTask().execute("9999019000000061");
			//setEidtDate(response_N);
//				scrollView_Y.setVisibility(View.GONE);
//				scrollView_N.setVisibility(View.VISIBLE);
//				check_bill.setVisibility(View.GONE);
//				imageView.setVisibility(View.GONE);
			}
			bt_left_a.setTextColor(getResources().getColor(R.color.headcolor));
			bt_right_a.setTextColor(Color.WHITE);
			bt_left_a.setBackgroundResource(R.drawable.bill_nomal);
			bt_right_a.setBackgroundResource(R.drawable.unbill_click);
			break;
		case R.id.lastMonth:
			if (month == 1) {
				month = 12;
				year -= 1;
			} else {
				month -= 1;
			}
			end_date=endDate( month,year);
			if (month >= 10) {
				date = "" + year + "-" + month;
			} else {
				date = "" + year + "-0" + month;
			}
			System.out.println("选择的日期是："+date);
			System.out.println("选择的日期+1是："+end_date);
			if (isBill) {
			//	response_Y = response_Y_Hash.get(date);
			//	if (response_Y == null) {
				//	new GetBillAsyncTask().execute(cardNo, date, billInd);
				new GetBillAsyncTask().execute("9999019000000061", date,
						end_date);
//				} else {
//					setEidtDate(response_Y);
//					scrollView_Y.setVisibility(View.VISIBLE);
//					scrollView_N.setVisibility(View.GONE);
//					check_bill.setVisibility(View.VISIBLE);
//					imageView.setVisibility(View.GONE);
//				}
			} else {
			//	response_N = response_N_Hash.get(date);
			//	if (response_N == null) {
				new GetNoBillAsyncTask().execute("9999019000000061");
//					new GetNoBillAsyncTask()
//				} else {
//					//setEidtDate(response_N);
//					scrollView_Y.setVisibility(View.GONE);
//					scrollView_N.setVisibility(View.VISIBLE);
//					check_bill.setVisibility(View.GONE);
//					imageView.setVisibility(View.GONE);
//				}
			}
			nowMonth.setText(date);
			break;
		case R.id.nextMonth:
			if (month == 12) {
				month = 1;
				year += 1;
			} else {
				month += 1;
			}
			end_date=endDate(month, year);
			if (month >= 10) {
				date = "" + year + "-" + month;
			} else {
				date = "" + year + "-0" + month;
			}
			System.out.println("选择的日期是："+date);
			System.out.println("选择的日期+1是："+end_date);
			if (isBill) {
				//response_Y = response_Y_Hash.get(date);
			//	if (response_Y == null) {
				//	new GetBillAsyncTask().execute(cardNo, date, billInd);
				new GetBillAsyncTask().execute("9999019000000061", date,
						end_date);
//				} else {
//					setEidtDate(response_Y);
//					scrollView_Y.setVisibility(View.VISIBLE);
//					scrollView_N.setVisibility(View.VISIBLE);
//					check_bill.setVisibility(View.VISIBLE);
//					imageView.setVisibility(View.GONE);
//				}
			} else {
			//	response_N = response_N_Hash.get(date);
			//	if (response_N == null) {
				//	new GetBillAsyncTask().execute(cardNo, date, billInd);
				new GetNoBillAsyncTask().execute("9999019000000061");
//				} else {
//				//	setEidtDate(response_N);
//					scrollView_Y.setVisibility(View.GONE);
//					scrollView_N.setVisibility(View.VISIBLE);
//					check_bill.setVisibility(View.VISIBLE);
//					imageView.setVisibility(View.GONE);
//				}
			}
			nowMonth.setText(date);
			break;
		case R.id.nowMonth:
			break;

		default:
			break;
		}
	}
	//月份加+1
	private String endDate(int m,int y){
		end_month=m+1;
		end_year=y;
		String end;
		//加入下一个月
		if(end_month==13){
			end_year=end_year+1;
			end = "" + end_year + "-01" ; 
		}else if(end_month>9){
			end = "" + end_year + "-" + end_month; 
		}else {
			end = "" + end_year + "-0" + end_month;
		}
		
		return end;
	}

}
