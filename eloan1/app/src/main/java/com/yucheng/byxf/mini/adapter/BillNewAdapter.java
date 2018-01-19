package com.yucheng.byxf.mini.adapter;

import com.yucheng.byxf.mini.message.BillNewResponse;
import com.yucheng.byxf.mini.ui.R;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BillNewAdapter extends BaseAdapter {

	private List<BillNewResponse> list;
	private Context context;

	public BillNewAdapter(List<BillNewResponse> list, Context context) {
		super();
		this.list = list;
		this.context = context;
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
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.bill_new_listitem, null);
			holder = new ViewHolder();
			holder.money = (TextView) convertView.findViewById(R.id.money);
			holder.date = (TextView) convertView.findViewById(R.id.date);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.money.setText(list.get(position).getOrigPrcp());
		holder.date.setText(list.get(position).getLoanActvDt());
		
		return convertView;
	}

	class ViewHolder {
		TextView money;
		TextView date;
	}

}
