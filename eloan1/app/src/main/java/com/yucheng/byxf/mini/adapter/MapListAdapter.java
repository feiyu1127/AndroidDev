package com.yucheng.byxf.mini.adapter;

import java.util.List;

import com.yucheng.byxf.mini.map.BranchLocation;
import com.yucheng.byxf.mini.ui.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MapListAdapter extends BaseAdapter {

	private Context context;
	private List<BranchLocation> list;

	public MapListAdapter(Context context, List<BranchLocation> list) {
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
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.map_list_item, null);
			holder = new ViewHolder();
			holder.number = (TextView) convertView.findViewById(R.id.number);
			holder.name = (TextView) convertView.findViewById(R.id.name);
			holder.address = (TextView) convertView.findViewById(R.id.address);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (position < 9) {
			holder.number.setText("0" + (position + 1));
		} else {
			holder.number.setText((position + 1) + "");
		}
		holder.name.setText(list.get(position).getName());
		holder.address.setText(list.get(position).getAddr());
		return convertView;
	}

	class ViewHolder {
		TextView number;
		TextView name;
		TextView address;
	}

}
