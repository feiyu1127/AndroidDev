package com.yucheng.byxf.mini.xiaojinying;

import java.util.List;
import java.util.Map;

import com.yucheng.byxf.mini.message.ApplicationScheduleQueryResponse;
import com.yucheng.byxf.mini.message.DetailsCardInfo;
import com.yucheng.byxf.mini.message.XiaoJinYingMingXiresult2;
import com.yucheng.byxf.mini.message.XiaojinYingRelationInfoResultBodyList;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.xiaojinying.XiaoJinYingZhangDanMingXiListAdapter.ListItemView;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class XiaoJinYingZhangDanH_ListAdapter extends BaseAdapter {

	private List<XiaoJinYingMingXiresult2> list;
	private Context context;

	public XiaoJinYingZhangDanH_ListAdapter(Context context,
			List<XiaoJinYingMingXiresult2> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.xiaojinyin_zhangdan_item, null);
			// R.layout.mini_card_bill_item, null);
		}
		TextView tv = (TextView) convertView.findViewById(R.id.zhangdanjinenum);
		TextView tv2 = (TextView) convertView.findViewById(R.id.zhangdanbishu);
		TextView tv3 = (TextView) convertView.findViewById(R.id.zhangdanriqi);

		tv3.setText(list.get(position).getPsBillDt() + "账单");
		tv.setText(list.get(position).getCurBillAmt());
		tv2.setText(list.get(position).getCashNum());

		return convertView;
	}
}
