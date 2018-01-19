package com.yucheng.byxf.mini.adapter;

import java.util.List;
import com.yucheng.byxf.mini.message.MiniCardActivateList;
import com.yucheng.byxf.mini.ui.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ApplicationScheduleQueryAdaper extends BaseAdapter {
/**
 * mini 申请adapter
 */
	private List<MiniCardActivateList> list;
	private Context context;
	class ViewHolder {
		TextView cadestr;
		TextView date;
	}
	public ApplicationScheduleQueryAdaper(List<MiniCardActivateList> list,
			Context context) {
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
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.mini_apply_list_item, null);

			holder.cadestr = (TextView) convertView.findViewById(R.id.str_code);
			holder.date = (TextView) convertView.findViewById(R.id.str_date);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.cadestr.setText(list.get(position).getApplSeq());
		holder.date.setText(list.get(position).getCrtDt());

		return convertView;
	}


}
