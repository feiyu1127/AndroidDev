package com.yucheng.byxf.mini.ui;

import java.util.ArrayList;
import java.util.List;

import com.yucheng.byxf.mini.message.BillResponse;
import com.yucheng.byxf.mini.message.CardBillInfo;
import com.yucheng.byxf.mini.message.MyBillResponse;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class BillActivityDetails extends BaseAllActivity implements OnClickListener {

	private ListView listView;
	private MyBillResponse response;
	private List<CardBillInfo> list = new ArrayList<CardBillInfo>();

	private MyBillBaseAdapter adapter;
	private String month;
	private TextView tv_month;

//	private String[] english = new String[] { "CASHOUT", "CONSUME", "STAGES",
//			"REPAYMENT", "REVSE", "YEAR_FEE", "TAKE_FEE", "LATE_FEE", "AFFIRM" ,"LND3"};
//	private String[] chinese = new String[] { "取现", "消费", "分期", "还款", "冲正",
//			"年费", "提现费", "滞纳金", "存款确认" ,""};
	private ImageView back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_billpage_details);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
						| WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		Intent intent = getIntent();
		if (intent != null) {
			response = (MyBillResponse) intent.getSerializableExtra("response");
			if (response != null && !"".equals(response)) {
				list = response.getCardBillList();
			}

			month = getIntent().getStringExtra("month");
		}

		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		back = (ImageView) findViewById(R.id.back);
		listView = (ListView) findViewById(R.id.listview);
		adapter = new MyBillBaseAdapter(list);
		listView.setAdapter(adapter);

		tv_month = (TextView) findViewById(R.id.month);
		tv_month.setText(month + "月账单详情");
		back.setOnClickListener(this);
	}

	class MyBillBaseAdapter extends BaseAdapter {

		private List<CardBillInfo> list;
//		private String[] english;
//		private String[] chinese;

		public MyBillBaseAdapter(List<CardBillInfo> list
//				, String[] english,
//				String[] chinese) {
				) {
			super();
			this.list = list;
//			this.english = english;
//			this.chinese = chinese;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			if (list != null) {
				return list.size();
			}
			return 0;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(BillActivityDetails.this)
						.inflate(R.layout.billpage_details_item, null);
				holder = new ViewHolder();

				holder.hostTxDt = (EditText) convertView
						.findViewById(R.id.hostTxDt);
				holder.seqNo = (EditText) convertView.findViewById(R.id.seqNo);
//				holder.txTyp = (EditText) convertView.findViewById(R.id.txTyp);
				holder.txValDt = (EditText) convertView
						.findViewById(R.id.txValDt);
				holder.lastNo = (EditText) convertView
						.findViewById(R.id.lastNo);
				holder.txAmt = (EditText) convertView.findViewById(R.id.txAmt);
				holder.reMark = (EditText) convertView
						.findViewById(R.id.reMark);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.hostTxDt.setText(list.get(position).getHostTxDt());
			holder.seqNo.setText(list.get(position).getSeqNo());

//			if (null != list.get(position).getTxTyp()
//					&& !"".equals(list.get(position).getTxTyp())) {
//				for (int i = 0; i < english.length; i++) {
//					if (list.get(position).getTxTyp().equals(english[i])) {
//						holder.txTyp.setText(chinese[i]);
//						i = english.length + 1;
//					} else {
//						holder.txTyp.setText(list.get(position).getTxTyp());
//					}
//				}
//			}
			holder.txValDt.setText(list.get(position).getTxValDt());
			holder.lastNo.setText(list.get(position).getLastNo());
			if (!list.get(position).getTxAmt().equals("")
					&& null != list.get(position).getTxAmt()) {
				if (list.get(position).getTxAmt().startsWith(".")) {
					holder.txAmt.setText("0" + list.get(position).getTxAmt());
				} else {
					holder.txAmt.setText(list.get(position).getTxAmt());
				}
			}
			holder.reMark.setText(list.get(position).getReMark());

			return convertView;
		}

		class ViewHolder {
			private EditText seqNo;
			private EditText txTyp;
			private EditText hostTxDt;
			private EditText txValDt;
			private EditText lastNo;
			private EditText txAmt;
			private EditText reMark;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		default:
			break;
		}
	}
}
