package com.yucheng.byxf.mini.adapter;

import java.util.List;

import com.yucheng.byxf.mini.message.RelaxBillResponse;
import com.yucheng.byxf.mini.ui.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BillHomeConsumeAdapter extends BaseAdapter {

	Context context;
	List<RelaxBillResponse> list;
	

	public BillHomeConsumeAdapter(Context context, List<RelaxBillResponse> list) {
		super();
		this.context = context;
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

		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.application_schedule_query_home_item, null);
		}

		TextView tv1 = (TextView) convertView.findViewById(R.id.money);
		TextView tv2 = (TextView) convertView.findViewById(R.id.date);
		TextView tv11 = (TextView) convertView.findViewById(R.id.tv_money);
		TextView tv22 = (TextView) convertView.findViewById(R.id.tv_date);

		tv1.setText( list.get(position).getOrigPrcp());
		tv2.setText(list.get(position).getLoanActvDt());
		
		tv11.setText("放款金额：");
		tv22.setText("放款日期：");
		return convertView;
	}

}
