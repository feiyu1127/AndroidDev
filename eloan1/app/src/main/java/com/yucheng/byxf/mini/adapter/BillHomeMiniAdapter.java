package com.yucheng.byxf.mini.adapter;

import java.util.List;

import com.yucheng.byxf.mini.message.CardNoId;
import com.yucheng.byxf.mini.ui.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BillHomeMiniAdapter extends BaseAdapter {

	Context context;
	List<CardNoId> list;

	public BillHomeMiniAdapter(Context context, List<CardNoId> list) {
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
					R.layout.bill_home_item, null);
		}

		TextView tv = (TextView) convertView.findViewById(R.id.cardNum);

		tv.setText(list.get(position).getCardNo());

		return convertView;
	}

}
