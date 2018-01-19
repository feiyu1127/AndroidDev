package com.yucheng.byxf.mini.adapter;

import java.util.List;
import java.util.Map;

import com.yucheng.byxf.mini.ui.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MapRoutePlanAdapter extends BaseAdapter {

	private Context context;
	private List<Map<String, Object>> list;
	private int searchType;

	public MapRoutePlanAdapter(Context context, List<Map<String, Object>> list,
			int searchType) {
		super();
		this.context = context;
		this.list = list;
		this.searchType = searchType;
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
					R.layout.map_result_item, null);
			holder = new ViewHolder();
			holder.remark = (ImageView) convertView.findViewById(R.id.market);
			holder.content = (TextView) convertView.findViewById(R.id.content);
			holder.imageView = (ImageView) convertView.findViewById(R.id.image);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if (searchType == 0) {
			holder.remark.setImageResource(R.drawable.list_driving);
			holder.content.setText(list.get(position).get("node").toString());
		} else if (searchType == 1) {
			holder.content.setText(list.get(position).get("node").toString());
			String node = list.get(position).get("node").toString();
			if (node.contains("乘坐") && !node.contains("地铁")) {
				holder.remark.setImageResource(R.drawable.list_transit);
			}
			if (node.contains("步行")) {
				holder.remark.setImageResource(R.drawable.list_walk);
			}
			if (node.contains("地铁")) {
				holder.remark.setImageResource(R.drawable.ditie);
			}
		} else if (searchType == 2) {
			holder.remark.setImageResource(R.drawable.list_walk);
			holder.content.setText(list.get(position).get("node").toString());
		}

		if (position != list.size() - 1) {
			System.out.println("======>"+position+","+(list.size() - 1));
			holder.imageView.setVisibility(View.VISIBLE);
		}

		return convertView;
	}

	class ViewHolder {
		ImageView remark;
		TextView content;
		ImageView imageView;
	}

}
