package com.yucheng.byxf.mini.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.adapter.MiniCardBillAdapter;
import com.yucheng.byxf.mini.message.CardBillInfo;
import com.yucheng.byxf.mini.message.GetCardNo;
import com.yucheng.byxf.mini.message.MiniBillDetails;
import com.yucheng.byxf.mini.message.MiniCardBillDetail;
import com.yucheng.byxf.mini.message.MyBillResponse;
import com.yucheng.byxf.mini.ui.BillActivityDetails.MyBillBaseAdapter;
import com.yucheng.byxf.mini.ui.BillActivityDetails.MyBillBaseAdapter.ViewHolder;
import com.yucheng.byxf.mini.utils.ContantsAddress;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Mini循环消费贷账单详情
 */
public class BillDetailsActivity extends BaseActivity implements
		OnClickListener {

	private String month;
	private MiniBillDetails response;
	private List<MiniBillDetails> list = new ArrayList<MiniBillDetails>();
	private MyBillDetailAdapter adapter;
	private String cardNo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bill_datails_check);
		Intent intent = getIntent();
		month = getIntent().getStringExtra("month");
		cardNo = getIntent().getStringExtra("cardNo");
		new GetMyBillDetail().execute(cardNo,month);
		initView();
	}

	private void initView() {
		ImageView back = (ImageView) findViewById(R.id.back);
		ListView listView = (ListView) findViewById(R.id.listview);
		 adapter = new MyBillDetailAdapter(list);
		listView.setAdapter(adapter);

		TextView tv_month = (TextView) findViewById(R.id.month);
		tv_month.setText(month + "月账单详情");

		back.setOnClickListener(this);
	}

	//请求获取编号
	class GetMyBillDetail extends AsyncTask<String, Object, Object> {
		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(BillDetailsActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("cardNo", params[0]));
			arg.add(new BasicNameValuePair("billDt", params[1]));
			return httpHelper.post(ContantsAddress.MINI_BILL, arg,
					MiniBillDetails.class);
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
				GetCardNo response = (GetCardNo) result;

				if (response.getCode() == 0) {

					if (response.getMsg().equals("没有查询到账单")) {
						Toast.makeText(BillDetailsActivity.this, "没有查询到账单！", 2000).show();
					} else if (response.getMsg().equals("处理成功")) {

					}
				} else {

				}
			}
		}
	}

	class MyBillDetailAdapter extends BaseAdapter {

		private List<MiniBillDetails> list;

		public MyBillDetailAdapter(List<MiniBillDetails> list) {
			this.list = list;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(BillDetailsActivity.this)
						.inflate(R.layout.bill_detail_item, null);
				holder = new ViewHolder();

				holder.hostTxDt = (EditText) convertView
						.findViewById(R.id.hostTxDt);
				holder.acctDt = (EditText) convertView
						.findViewById(R.id.acctDt);

				holder.cardNo = (EditText) convertView
						.findViewById(R.id.cardNo);
				holder.txDesc = (EditText) convertView
						.findViewById(R.id.txDesc);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.hostTxDt.setText(list.get(position).getHostTxDt());

//			 if (null != list.get(position).getTxTyp()
//			 && !"".equals(list.get(position).getTxTyp())) {
//			 for (int i = 0; i < english.length; i++) {
//			 if (list.get(position).getTxTyp().equals(english[i])) {
//			 holder.txTyp.setText(chinese[i]);
//			 i = english.length + 1;
//			 } else {
//			 holder.txTyp.setText(list.get(position).getTxTyp());
//			 }
//			 }
//			 }
			holder.acctDt.setText(list.get(position).getAcctDt());
			 holder.cardNo.setText(list.get(position).getCode());
			holder.txDesc.setText(list.get(position).getTxDesc());
//			 if (!list.get(position).getTxAmt().equals("")
//			 && null != list.get(position).getTxAmt()) {
//			 if (list.get(position).getTxAmt().startsWith(".")) {
//			 holder.txAmt.setText("0" + list.get(position).getTxAmt());
//			 } else {
//			 holder.txAmt.setText(list.get(position).getTxAmt());
//			 }
//			 }

			return convertView;
		}

		class ViewHolder {
			private EditText acctDt;
			private EditText cardNo;
			private EditText hostTxDt;
			private EditText txDesc;
		}
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		}
	}

}
