package com.yucheng.byxf.mini.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.adapter.BillHomeConsumeAdapter;
import com.yucheng.byxf.mini.adapter.BillHomeMiniAdapter;
import com.yucheng.byxf.mini.message.CardNoId;
import com.yucheng.byxf.mini.message.CardNoMessage;
import com.yucheng.byxf.mini.message.CardNoResponse;
import com.yucheng.byxf.mini.message.LoginResponse;
import com.yucheng.byxf.mini.message.RelaxBillResponse;
import com.yucheng.byxf.mini.message.RelaxBillResult;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.views.ListViewNew;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class BillHomeActivity extends BaseActivity implements OnClickListener {

	BillHomeMiniAdapter miniAdapter;
	BillHomeConsumeAdapter consumeAdapter;

	List<CardNoId> miniList = new ArrayList<CardNoId>();
	List<RelaxBillResponse> relaxList = new ArrayList<RelaxBillResponse>();

	ListView miniListView;
	ListView relaxListView;

	private boolean isMini = false;// 判断选择的是mini贷还是轻松贷
	private boolean isMiniFlag = true;// 是否是第一次进入mini贷的标志
	private boolean isCommonFlag = true;// 是否是第一次进入轻松贷的标志 true标识第一次进入

	private Button miniButton;
	private Button commonButton;
	ImageView application_schedule_null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bill_home_activity);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub

		miniListView = (ListView) findViewById(R.id.listview);
//		relaxListView = (ListView) findViewById(R.id.relaxlistview);
		miniButton = (Button) findViewById(R.id.mini);
		commonButton = (Button) findViewById(R.id.common);
		miniButton.setOnClickListener(this);
		commonButton.setOnClickListener(this);
		application_schedule_null = (ImageView) findViewById(R.id.application_schedule_null);
		miniListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				// Toast.makeText(BillHomeActivity.this, "position"+position,
				// Toast.LENGTH_LONG).show();
				Intent intent = new Intent();
				intent.setClass(BillHomeActivity.this, BillActivity.class);
				intent.putExtra("cardNo", miniList.get(position) + "");
				startActivity(intent);
			}
		});

		relaxListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(BillHomeActivity.this,
						RelaxLoanBillActivity.class);
				intent.putExtra("loanNo", relaxList.get(position).getLoanNo());
				
				startActivity(intent);
			}
		});

		ImageView back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		// new GetMiniBillListAsyncTask().execute(Contents.response.getResult()
		// .getIdNo());
		new GetRelaxBillListAsyncTask().execute();
	}

	class GetMiniBillListAsyncTask extends AsyncTask<String, Object, Object> {
    @Override
    protected Object doInBackground(String... params) {
      HttpHelper httpHelper = HttpHelper.getInstance(BillHomeActivity.this);
      List<NameValuePair> arg = new ArrayList<NameValuePair>();
      arg.add(new BasicNameValuePair("idNo", params[0]));
      return httpHelper.post(ContantsAddress.GET_CARDNO, arg, CardNoResponse.class);
    }

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog();
		}

		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);
			dismissProgressDialog();
			if (result != null) {
				CardNoResponse response = (CardNoResponse) result;

				if (response.getCode() == 0) {
					isMiniFlag = false;
					miniList = response.getResult().getCardList();
					if (response.getMsg().equals("没有查询到账单")) {
						application_schedule_null.setVisibility(View.VISIBLE);
						miniListView.setVisibility(View.GONE);
					} else if (response.getMsg().equals("处理成功")) {
						application_schedule_null.setVisibility(View.GONE);
						miniListView.setVisibility(View.VISIBLE);
						miniAdapter = new BillHomeMiniAdapter(
								BillHomeActivity.this, miniList);
						miniListView.setAdapter(miniAdapter);
					}
				} else {
					isMiniFlag = false;
					application_schedule_null.setVisibility(View.VISIBLE);
					miniListView.setVisibility(View.GONE);
				}
			}
		}
	}

	class GetRelaxBillListAsyncTask extends AsyncTask<String, Object, Object> {
    @Override
    protected Object doInBackground(String... params) {
      HttpHelper httpHelper = HttpHelper.getInstance(BillHomeActivity.this);
      List<NameValuePair> arg = new ArrayList<NameValuePair>();
      // "511423198403110014"
		String idNo = "";
		if(Contents.response != null && Contents.response.getResult() != null)
			idNo = Contents.response.getResult().getIdNo();
      arg.add(new BasicNameValuePair("idNo", idNo));
      arg.add(new BasicNameValuePair("number", "20"));
      arg.add(new BasicNameValuePair("beginPos", "1"));
      arg.add(new BasicNameValuePair("loanType", "C002"));
      return httpHelper.post(ContantsAddress.RELAX_LOAN_BILL_LIST, arg, RelaxBillResult.class);
    }

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog();
		}

		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);
			dismissProgressDialog();
			if (result != null) {
				RelaxBillResult response = (RelaxBillResult) result;
				if (response.getCode() == 0) {
					isCommonFlag = false;
					System.out.println(response.getResult());
					relaxList = response.getResult();
					System.out.println(relaxList.size());
					// isMini = !isMini;
					miniListView.setVisibility(View.GONE);
					relaxListView.setVisibility(View.VISIBLE);

					consumeAdapter = new BillHomeConsumeAdapter(
							BillHomeActivity.this, relaxList);
					relaxListView.setAdapter(consumeAdapter);
					if (relaxList.size() == 0) {
						application_schedule_null.setVisibility(View.VISIBLE);
					} else {
						application_schedule_null.setVisibility(View.GONE);
					}
				} else {
					application_schedule_null.setVisibility(View.VISIBLE);
					isCommonFlag = false;
				}
			} else {
				application_schedule_null.setVisibility(View.VISIBLE);
			}
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.mini) {
			if (!isMini) {
				miniButton
						.setBackgroundResource(R.drawable.title_right_selector);
				miniButton.setTextColor(Color.parseColor("#ffffff"));
				commonButton
						.setBackgroundResource(R.drawable.title_left_normal);
				commonButton.setTextColor(Color.parseColor("#595959"));
				isMini = !isMini;
				miniListView.setVisibility(View.VISIBLE);
				relaxListView.setVisibility(View.GONE);
				if (!isMiniFlag) {
					if (miniList.size() == 0) {
						miniListView.setVisibility(View.GONE);
						application_schedule_null.setVisibility(View.VISIBLE);
					} else {
						miniListView.setVisibility(View.VISIBLE);
						application_schedule_null.setVisibility(View.GONE);
					}
				} else {
					String idNo = "";
					if(Contents.response != null && Contents.response.getResult() != null)
						idNo = Contents.response.getResult().getIdNo();
					new GetMiniBillListAsyncTask().execute(idNo);
				}
			}
		} else if (v.getId() == R.id.common) {
			if (isMini) {
				miniButton.setBackgroundResource(R.drawable.title_right_normal);
				miniButton.setTextColor(Color.parseColor("#595959"));
				commonButton
						.setBackgroundResource(R.drawable.title_left_selector);
				commonButton.setTextColor(Color.parseColor("#ffffff"));
				isMini = !isMini;
				miniListView.setVisibility(View.GONE);
				relaxListView.setVisibility(View.VISIBLE);
				application_schedule_null.setVisibility(View.GONE);
				if (!isCommonFlag) {
					if (relaxList.size() == 0) {
						application_schedule_null.setVisibility(View.VISIBLE);
					}
				} else {
					new GetRelaxBillListAsyncTask().execute();
				}

			}
			// Toast.makeText(BillHomeActivity.this, "此功能正在开发中，敬请期待。。。",
			// Toast.LENGTH_LONG).show();
			// DialogUtil.showDialogOneButton2(BillHomeActivity.this,
			// "此功能正在开发中，敬请期待").show();
		}
	}

}
