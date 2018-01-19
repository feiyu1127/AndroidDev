package com.yucheng.byxf.mini.xiaojinying;

import java.util.List;
import java.util.Map;

import com.yucheng.byxf.mini.message.ApplicationScheduleQueryResponse;
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

public class XiaoJinYingZhangDanMingXiListViewPagerAdapter extends PagerAdapter {

	private List<XiaoJinYingMingXiresult2> list;
	private Context context;
	private LayoutInflater linfalater;

	public XiaoJinYingZhangDanMingXiListViewPagerAdapter(
			List<XiaoJinYingMingXiresult2> list, Context context) {
		this.context = context;

		this.list = list;
	}

	@Override
	public int getCount() {

	 
			return  list.size() ;
		 
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		ListItemView listItemView = null;
	
			
			container = (ViewGroup) linfalater.inflate(
					R.layout.xiaojinyin_zhangdanmingxi_item, null);
		
	 
		
		return container;
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

}
