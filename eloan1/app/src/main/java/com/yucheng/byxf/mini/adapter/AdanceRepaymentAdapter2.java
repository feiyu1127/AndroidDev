package com.yucheng.byxf.mini.adapter;

import java.util.List;

import com.yucheng.byxf.mini.adapter.ApplicationScheduleQueryNAdaper.ViewHolder;
import com.yucheng.byxf.mini.message.AdvanceRepaymentResponse;
import com.yucheng.byxf.mini.message.AdvanceRepaymentResultResponse;
import com.yucheng.byxf.mini.message.ApplicationScheduleQueryResponse;
import com.yucheng.byxf.mini.ui.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdanceRepaymentAdapter2 extends BaseAdapter {

	private List<AdvanceRepaymentResultResponse> list;
	private Context context;
	public AdanceRepaymentAdapter2(
			List<AdvanceRepaymentResultResponse> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		
	}
	
	@Override
	public int getCount() {
		
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO 自动生成的方法存根
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO 自动生成的方法存根
		return position;
	}

	class ViewHolder {
		TextView money;
		TextView date;
		
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.advance_repayment_home_item, null);

			holder.money = (TextView) convertView.findViewById(R.id.money);
			holder.date = (TextView) convertView.findViewById(R.id.date);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
//
		holder.money.setText(list.get(position).getOrigPrcp()+"元");
		holder.date.setText(list.get(position).getIntStartDt());
		return convertView;
	}
	

}
