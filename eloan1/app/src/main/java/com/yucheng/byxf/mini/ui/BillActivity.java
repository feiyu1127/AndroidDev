package com.yucheng.byxf.mini.ui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.message.BillResponse;
import com.yucheng.byxf.mini.message.CardResponse;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class BillActivity extends BaseActivity implements OnClickListener {
	private Button check_bill;
	private static final Integer RET_CODE = 0;
	private static final Integer NOT_CODE = 20201;
	private String cardNo = "";
	private String billInd = "Y";
	private Button bt_left_a, bt_right_a;
	private EditText psDueDt, curAllPsAmt, curMinPay, curPayedAmt,
			curAppendAmt, curAdjAmt, cycleInt, curNormInt, curCashAmt,
			curFeeAmt, curAllOdAmt, cashNum;
	private BillResponse response_Y;
	private BillResponse response_N;
	private TextView lastMonth, nextMonth, nowMonth;

	private ScrollView scrollView;
	private ImageView back;

	private HashMap<String, BillResponse> response_Y_Hash = new HashMap<String, BillResponse>();
	private HashMap<String, BillResponse> response_N_Hash = new HashMap<String, BillResponse>();
	private String date;
	private int year = 2013;
	private int month = 4;

	LinearLayout imageView;
	Calendar calendar = Calendar.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_billpage);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
						| WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		Intent intent = getIntent();
		cardNo = intent.getExtras().getString("cardNo");
		check_bill = (Button) findViewById(R.id.check_bill);
		check_bill.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent inent = new Intent(BillActivity.this,
						BillActivityDetails.class);
				if (billInd.equals("Y")) {
					if (!"".equals(response_Y) && response_Y != null) {
						inent.putExtra("response", response_Y.getResult());
					}

				} else {
					if (!"".equals(response_N) && response_N != null) {
						inent.putExtra("response", response_N.getResult());
					}
				}
				inent.putExtra("month", month + "");
				startActivity(inent);
			}
		});

		initView();
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH) + 1;
		if (month > 10) {
			date = "" + year + "-" + month;
		} else {
			date = "" + year + "-0" + month;
		}
		nowMonth.setText(date);

		new GetBillAsyncTask().execute(cardNo, date, billInd);
	}

	private void initView() {
		// TODO Auto-generated method stub
		back = (ImageView) findViewById(R.id.back);
		bt_left_a = (Button) findViewById(R.id.bt_left_aa);
		bt_right_a = (Button) findViewById(R.id.bt_right_aa);

		lastMonth = (TextView) findViewById(R.id.lastMonth);
		nextMonth = (TextView) findViewById(R.id.nextMonth);
		nowMonth = (TextView) findViewById(R.id.nowMonth);

		psDueDt = (EditText) findViewById(R.id.psDueDt);
		curAllPsAmt = (EditText) findViewById(R.id.curAllPsAmt);
		curMinPay = (EditText) findViewById(R.id.curMinPay);
		curPayedAmt = (EditText) findViewById(R.id.curPayedAmt);
		curAppendAmt = (EditText) findViewById(R.id.curAppendAmt);
		curAdjAmt = (EditText) findViewById(R.id.curAdjAmt);
		cycleInt = (EditText) findViewById(R.id.cycleInt);
		curNormInt = (EditText) findViewById(R.id.curNormInt);
		curCashAmt = (EditText) findViewById(R.id.curCashAmt);
		curFeeAmt = (EditText) findViewById(R.id.curFeeAmt);
		curAllOdAmt = (EditText) findViewById(R.id.curAllOdAmt);
		cashNum = (EditText) findViewById(R.id.cashNum);

		scrollView = (ScrollView) findViewById(R.id.scrollview);
		imageView = (LinearLayout) findViewById(R.id.imageview);

		bt_left_a.setOnClickListener(this);
		bt_right_a.setOnClickListener(this);
		lastMonth.setOnClickListener(this);
		nextMonth.setOnClickListener(this);
		nowMonth.setOnClickListener(this);
		back.setOnClickListener(this);
	}

	private void setEidtDate(BillResponse response) {

		if (!response.getResult().getPsDueDt().equals("")
				&& null != response.getResult().getPsDueDt()) {
			if (response.getResult().getPsDueDt().trim().startsWith(".")) {
				psDueDt.setText("0" + response.getResult().getPsDueDt() + "");
			} else {
				psDueDt.setText(response.getResult().getPsDueDt() + "");
			}
		}

		if (!response.getResult().getCurAllPsAmt().equals("")
				&& null != response.getResult().getCurAllPsAmt()) {
			if (response.getResult().getCurAllPsAmt().trim().startsWith(".")) {
				curAllPsAmt.setText("0" + response.getResult().getCurAllPsAmt()
						+ "");
			} else {
				curAllPsAmt.setText(response.getResult().getCurAllPsAmt() + "");
			}
		}

		if (!response.getResult().getCurMinPay().equals("")
				&& null != response.getResult().getCurMinPay()) {
			if (response.getResult().getCurMinPay().trim().startsWith(".")) {
				curMinPay.setText("0" + response.getResult().getCurMinPay()
						+ "");
			} else {
				curMinPay.setText(response.getResult().getCurMinPay() + "");
			}
		}

		if (!response.getResult().getCurPayedAmt().equals("")
				&& null != response.getResult().getCurPayedAmt()) {
			if (response.getResult().getCurPayedAmt().trim().startsWith(".")) {
				curPayedAmt.setText("0" + response.getResult().getCurPayedAmt()
						+ "");
			} else {
				curPayedAmt.setText(response.getResult().getCurPayedAmt() + "");
			}
		}

		if (!response.getResult().getCurAppendAmt().equals("")
				&& null != response.getResult().getCurPayedAmt()) {
			if (response.getResult().getCurAppendAmt().trim().startsWith(".")) {
				curAppendAmt.setText("0"
						+ response.getResult().getCurAppendAmt() + "");
			} else {
				curAppendAmt.setText(response.getResult().getCurAppendAmt()
						+ "");
			}
		}

		if (!response.getResult().getCurAdjAmt().equals("")
				&& null != response.getResult().getCurAdjAmt()) {
			if (response.getResult().getCurAdjAmt().trim().startsWith(".")) {
				curAdjAmt.setText("0" + response.getResult().getCurAdjAmt()
						+ "");
			} else {
				curAdjAmt.setText(response.getResult().getCurAdjAmt() + "");
			}
		}

		if (!response.getResult().getCycleInt().equals("")
				&& null != response.getResult().getCycleInt()) {
			if (response.getResult().getCycleInt().trim().startsWith(".")) {
				cycleInt.setText("0" + response.getResult().getCycleInt() + "");
			} else {
				cycleInt.setText(response.getResult().getCycleInt() + "");
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

		if (!response.getResult().getCurCashAmt().equals("")
				&& null != response.getResult().getCurCashAmt()) {
			if (response.getResult().getCurCashAmt().trim().startsWith(".")) {
				curCashAmt.setText("0" + response.getResult().getCurCashAmt()
						+ "");
			} else {
				curCashAmt.setText(response.getResult().getCurCashAmt() + "");
			}
		}

		if (!response.getResult().getCurFeeAmt().equals("")
				&& null != response.getResult().getCurFeeAmt()) {
			if (response.getResult().getCurFeeAmt().trim().startsWith(".")) {
				curFeeAmt.setText("0" + response.getResult().getCurFeeAmt()
						+ "");
			} else {
				curFeeAmt.setText(response.getResult().getCurFeeAmt() + "");
			}
		}

		if (!response.getResult().getCurAllOdAmt().equals("")
				&& null != response.getResult().getCurAllOdAmt()) {
			if (response.getResult().getCurAllOdAmt().trim().startsWith(".")) {
				curAllOdAmt.setText("0" + response.getResult().getCurAllOdAmt()
						+ "");
			} else {
				curAllOdAmt.setText(response.getResult().getCurAllOdAmt() + "");
			}
		}

		if (!response.getResult().getCashNum().equals("")
				&& null != response.getResult().getCashNum()) {
			if (response.getResult().getCashNum().trim().startsWith(".")) {
				cashNum.setText("0" + response.getResult().getCashNum() + "");
			} else {
				cashNum.setText(response.getResult().getCashNum() + "");
			}
		}
	}

	class GetBillCard extends AsyncTask<String, Object, Object> {
    @Override
    protected Object doInBackground(String... params) {
      HttpHelper httpHelper = HttpHelper.getInstance(BillActivity.this);
      List<NameValuePair> arg = new ArrayList<NameValuePair>();
      arg.add(new BasicNameValuePair("idNo", params[0]));
      return httpHelper.post(ContantsAddress.GET_CARDNO, arg, CardResponse.class);
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
			GetCardResponse(result);
		}
	}

	private void GetCardResponse(Object result) {
		CardResponse response = (CardResponse) result;
		if (result == null) {
			Toast.makeText(BillActivity.this, response.getMsg(),
					Toast.LENGTH_SHORT).show();
			return;
		} else {
			if (RET_CODE.equals(response.getCode())) {
				cardNo = response.getResult().getCardNo();
				new GetBillAsyncTask().execute(cardNo, date, billInd);
			} else {
				Toast.makeText(BillActivity.this, "服务器请求异常", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	class GetBillAsyncTask extends AsyncTask<String, Object, Object> {
    @Override
    protected Object doInBackground(String... params) {
      HttpHelper httpHelper = HttpHelper.getInstance(BillActivity.this);
      List<NameValuePair> arg = new ArrayList<NameValuePair>();
      arg.add(new BasicNameValuePair("cardNo", params[0]));
      arg.add(new BasicNameValuePair("billDt", params[1]));
      arg.add(new BasicNameValuePair("billInd", params[2]));
      return httpHelper.post(ContantsAddress.MY_BILL, arg, BillResponse.class);
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
			getResponse(result);
		}
	}

	private void getResponse(Object result) {
		BillResponse response = (BillResponse) result;
		if (result == null) {
			Toast.makeText(BillActivity.this, response.getMsg(),
					Toast.LENGTH_SHORT).show();
			scrollView.setVisibility(View.GONE);
			check_bill.setVisibility(View.GONE);
			imageView.setVisibility(View.VISIBLE);
			return;
		} else {
			if (RET_CODE.equals(response.getCode())) {
				scrollView.setVisibility(View.VISIBLE);
				check_bill.setVisibility(View.VISIBLE);
				imageView.setVisibility(View.GONE);
				if ("Y".equals(billInd)) {
					response_Y = response;
					response_Y_Hash.put(date, response_Y);
					setEidtDate(response_Y);
				} else if ("N".equals(billInd)) {
					response_N = response;
					response_N_Hash.put(date, response_N);
					setEidtDate(response_N);
				}

			} else if (NOT_CODE.equals(response.getCode())) {
				scrollView.setVisibility(View.GONE);
				check_bill.setVisibility(View.GONE);
				imageView.setVisibility(View.VISIBLE);
			}

			else {
				Toast.makeText(BillActivity.this, "服务器请求异常", Toast.LENGTH_SHORT)
						.show();
				scrollView.setVisibility(View.GONE);
				check_bill.setVisibility(View.GONE);
				imageView.setVisibility(View.VISIBLE);
			}
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_left_aa:
			billInd = "Y";
			response_Y = response_Y_Hash.get(date);
			if (response_Y == null) {
				new GetBillAsyncTask().execute(cardNo, date, billInd);
			} else {
				setEidtDate(response_Y);
				scrollView.setVisibility(View.VISIBLE);
				check_bill.setVisibility(View.VISIBLE);
				imageView.setVisibility(View.GONE);
			}
			bt_left_a.setTextColor(Color.WHITE);
			bt_right_a.setTextColor(getResources().getColor(R.color.headcolor));
			bt_left_a.setBackgroundResource(R.drawable.bill_click);
			bt_right_a.setBackgroundResource(R.drawable.unbill_nomal);
			break;
		case R.id.bt_right_aa:
			billInd = "N";
			response_N = response_N_Hash.get(date);
			if (response_N == null) {
				new GetBillAsyncTask().execute(cardNo, date, billInd);
			} else {
				setEidtDate(response_N);
				scrollView.setVisibility(View.VISIBLE);
				check_bill.setVisibility(View.VISIBLE);
				imageView.setVisibility(View.GONE);
			}
			bt_left_a.setTextColor(getResources().getColor(R.color.headcolor));
			bt_right_a.setTextColor(Color.WHITE);
			bt_left_a.setBackgroundResource(R.drawable.bill_nomal);
			bt_right_a.setBackgroundResource(R.drawable.unbill_click);
			break;
		case R.id.nextMonth:
			if (month == 12) {
				month = 1;
				year += 1;
			} else {
				month += 1;
			}
			if (month >= 10) {
				date = "" + year + "-" + month;
			} else {
				date = "" + year + "-0" + month;
			}
			if (billInd.equals("Y")) {
				response_Y = response_Y_Hash.get(date);
				if (response_Y == null) {
					new GetBillAsyncTask().execute(cardNo, date, billInd);
				} else {
					setEidtDate(response_Y);
					scrollView.setVisibility(View.VISIBLE);
					check_bill.setVisibility(View.VISIBLE);
					imageView.setVisibility(View.GONE);
				}
			} else {
				response_N = response_N_Hash.get(date);
				if (response_N == null) {
					new GetBillAsyncTask().execute(cardNo, date, billInd);
				} else {
					setEidtDate(response_N);
					scrollView.setVisibility(View.VISIBLE);
					check_bill.setVisibility(View.VISIBLE);
					imageView.setVisibility(View.GONE);
				}
			}
			nowMonth.setText(date);
			break;
		case R.id.lastMonth:
			if (month == 1) {
				month = 12;
				year -= 1;
			} else {
				month -= 1;
			}
			if (month >= 10) {
				date = "" + year + "-" + month;
			} else {
				date = "" + year + "-0" + month;
			}
			if (billInd.equals("Y")) {
				response_Y = response_Y_Hash.get(date);
				if (response_Y == null) {
					new GetBillAsyncTask().execute(cardNo, date, billInd);
				} else {
					setEidtDate(response_Y);
					scrollView.setVisibility(View.VISIBLE);
					check_bill.setVisibility(View.VISIBLE);
					imageView.setVisibility(View.GONE);
				}
			} else {
				response_N = response_N_Hash.get(date);
				if (response_N == null) {
					new GetBillAsyncTask().execute(cardNo, date, billInd);
				} else {
					setEidtDate(response_N);
					scrollView.setVisibility(View.VISIBLE);
					check_bill.setVisibility(View.VISIBLE);
					imageView.setVisibility(View.GONE);
				}
			}
			nowMonth.setText(date);
			break;
		case R.id.nowMonth:
			break;
		case R.id.back:
			finish();
			break;
		default:
			break;
		}
	}

}
