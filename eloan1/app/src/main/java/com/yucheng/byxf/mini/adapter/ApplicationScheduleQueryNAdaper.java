package com.yucheng.byxf.mini.adapter;

import java.util.List;

import com.yucheng.byxf.mini.message.ApplicationScheduleQueryResponse;
import com.yucheng.byxf.mini.ui.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

public class ApplicationScheduleQueryNAdaper extends BaseAdapter {

	private List<ApplicationScheduleQueryResponse> list;
	private Context context;

	private String[] code;
	private String[] inputSrc;

	public ApplicationScheduleQueryNAdaper(
			List<ApplicationScheduleQueryResponse> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		code = new String[] { "01", "02", "03", "04", "06", "07", "08", "09",
				"10" };
		inputSrc = new String[] { "商户", "pad", "业务受理", "电信业务", "轻松e贷", "自助贷款机",
				"手机App", "亨元", "直销银行" };
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
					R.layout.application_schedule_query_home_item, null);

			holder.money = (TextView) convertView.findViewById(R.id.money);
			holder.date = (TextView) convertView.findViewById(R.id.date);
			holder.laiyuan = (TextView) convertView.findViewById(R.id.laiyuan);
			holder.state=(TextView) convertView.findViewById(R.id.state);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.money.setText(list.get(position).getApplyAmt()+"元");
		holder.date.setText(list.get(position).getApplyDt());
		for (int i = 0; i < code.length; i++) {
			if (code[i].equals(list.get(position).getInputSrc())) {
				holder.laiyuan.setText(inputSrc[i]);
				break;
			} 
		}
		holder.state.setText(list.get(position).getStateShow());
		return convertView;
	}

	class ViewHolder {
		TextView money;
		TextView date;
		TextView laiyuan;
		TextView state;
	}
}
