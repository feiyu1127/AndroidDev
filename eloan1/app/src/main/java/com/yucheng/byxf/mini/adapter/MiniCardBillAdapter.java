package com.yucheng.byxf.mini.adapter;

import java.util.List;

import com.yucheng.byxf.mini.adapter.ApplicationScheduleQueryNAdaper.ViewHolder;
import com.yucheng.byxf.mini.message.CardNoId;
import com.yucheng.byxf.mini.message.DetailsCardInfo;
import com.yucheng.byxf.mini.ui.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MiniCardBillAdapter extends BaseAdapter {

	private List<DetailsCardInfo> list;
	private Context context;

	public MiniCardBillAdapter(Context context,List<DetailsCardInfo> list) {
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
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.mini_card_bill_item, null);
		}
		TextView tv = (TextView) convertView.findViewById(R.id.cardNum);
		tv.setText(list.get(position).getCardNo());
		return convertView;
	}
}
