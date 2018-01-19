package com.yucheng.byxf.mini.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.adapter.BillNewAdapter;
import com.yucheng.byxf.mini.message.BillNewResponse;
import com.yucheng.byxf.mini.message.BillNewResult;
import com.yucheng.byxf.mini.message.RelaxBillResponse;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class BillNewActivity extends BaseActivity {

	private ListView listView;

	private List<BillNewResponse> list = new ArrayList<BillNewResponse>();

	private BillNewAdapter adapter;

	private ImageView application_schedule_null;

	private ImageView back;

	private BillNewActivity activity;

	// List<RelaxBillResponse> relaxList = new ArrayList<RelaxBillResponse>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bill_home_activity);
		activity = new BillNewActivity();
		initView();
		String idNo = "";
		if (Contents.response != null && Contents.response.getResult() != null)
			idNo = Contents.response.getResult().getIdNo();
		new GetBillAsynTask().execute(idNo, "100", "1", "20", "00");
	}

	private void initView() {
		// TODO Auto-generated method stub
		back = (ImageView) findViewById(R.id.back);
		application_schedule_null = (ImageView) findViewById(R.id.application_schedule_null);
		listView = (ListView) findViewById(R.id.listview);

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(BillNewActivity.this,
						RelaxLoanBillActivity.class);
				intent.putExtra("loanNo", list.get(position).getLoanNo());
				intent.putExtra("tnr", list.get(position).getTnr());
				System.out.println("tnr---Extra" + list.get(position).getTnr());
				startActivity(intent);
			}
		});
	}

	class GetBillAsynTask extends AsyncTask<String, Object, Object> {
		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(BillNewActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("idNo", params[0]));
			arg.add(new BasicNameValuePair("number", params[1]));
			arg.add(new BasicNameValuePair("beginPos", params[2]));
			arg.add(new BasicNameValuePair("idType", params[3]));
			arg.add(new BasicNameValuePair("txType", params[4]));
			return httpHelper.post(ContantsAddress.PAYMENT_LIST, arg,
					BillNewResult.class);
		}

		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);
			if (activity != null) {
				dismissProgressDialog();
				BillNewResult response = (BillNewResult) result;
				if (response != null) {
					if (response.getCode() == 0) {
						list = response.getResult();
						if (list.size() == 0) {
							application_schedule_null
									.setVisibility(View.VISIBLE);
							listView.setVisibility(View.GONE);
						} else {
							application_schedule_null.setVisibility(View.GONE);
							listView.setVisibility(View.VISIBLE);
							adapter = new BillNewAdapter(list,
									BillNewActivity.this);
							listView.setAdapter(adapter);
						}
					} else {
						application_schedule_null.setVisibility(View.VISIBLE);
						listView.setVisibility(View.GONE);
						Toast.makeText(BillNewActivity.this, response.getMsg(),
								Toast.LENGTH_LONG).show();
					}
				} else {
					application_schedule_null.setVisibility(View.VISIBLE);
					listView.setVisibility(View.GONE);
					Toast.makeText(BillNewActivity.this,
							getResources().getString(R.string.no_network),
							Toast.LENGTH_LONG).show();
				}
			}

		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog();
		}

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		activity = null;
	}
}
