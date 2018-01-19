package com.yucheng.byxf.mini.adapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yucheng.byxf.mini.message.SuggestionMsgEntity;
import com.yucheng.byxf.mini.ui.R;

public class SuggestionMsgAdapter extends BaseAdapter {

	private final int TYPE_KH=0;
	private final int TYPE_BY=1;
	private List<SuggestionMsgEntity> list;
	private Context context;
	private String cipSex;
	private Date parse;
	private String createTime;
	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm:ss");
	
	public SuggestionMsgAdapter(List<SuggestionMsgEntity> list, Context context, String cipSex) {
		super();
		this.list = list;
		this.context = context;
		this.cipSex = cipSex;
	}

	@Override
	public int getCount() {
		return list!=null?list.size():0;
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}
	
	@Override
	public int getItemViewType(int position) {
		SuggestionMsgEntity entity = list.get(position);
		if("0".equals(entity.getType())){
			return TYPE_KH;
		}else{
			return TYPE_BY;
		}
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		SuggestionMsgEntity entity = list.get(position);
		int type = getItemViewType(position);
		switch (type) {
		case TYPE_BY://北銀
			ByHolder holderBy=null;
			if(convertView==null){
				convertView=View.inflate(context, R.layout.beiyin_left_item, null);
				holderBy=new ByHolder();
				holderBy.ivby_userhead=(ImageView) convertView.findViewById(R.id.iv_userhead);
				holderBy.tvby_chatcontent=(TextView) convertView.findViewById(R.id.tv_chatcontent);
				holderBy.tvby_sendtime=(TextView) convertView.findViewById(R.id.tv_sendtime);
				convertView.setTag(holderBy);
			}else{
				holderBy=(ByHolder) convertView.getTag();
			}
			holderBy.ivby_userhead.setImageResource(R.drawable.beiyin);
			holderBy.tvby_chatcontent.setText(entity.getContent());
			if(position == 0){
				holderBy.tvby_sendtime.setVisibility(View.GONE);
			}else{
				holderBy.tvby_sendtime.setVisibility(View.VISIBLE);
				try {
					parse = format1.parse(entity.getCreateTime());
				    createTime = sdf.format(parse);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				holderBy.tvby_sendtime.setText(createTime);
			}
			break;
		case TYPE_KH://客户
			KhHolder holderKh=null;
			if(convertView==null){
				holderKh=new KhHolder();
				convertView=View.inflate(context, R.layout.kehu_right_item, null);
				holderKh.ivkh_userhead=(ImageView) convertView.findViewById(R.id.ivkh_userhead);
				holderKh.tvkh_chatcontent=(TextView) convertView.findViewById(R.id.tvkh_chatcontent);
				holderKh.tvkh_sendtime=(TextView) convertView.findViewById(R.id.tvkh_sendtime);
				convertView.setTag(holderKh);
			}else{
				holderKh=(KhHolder) convertView.getTag();
			}
			if("1".equals(cipSex)){
				holderKh.ivkh_userhead.setImageResource(R.drawable.six_boy);
			}else{
				holderKh.ivkh_userhead.setImageResource(R.drawable.six_gril);
			}
			holderKh.tvkh_chatcontent.setText(entity.getContent());
			try {
				parse = format1.parse(entity.getCreateTime());
			    createTime = sdf.format(parse);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			holderKh.tvkh_sendtime.setText(createTime);
			break;

		default:
			break;
		}
		return convertView;
	}

	class KhHolder{
		TextView tvkh_chatcontent,tvkh_sendtime;
		ImageView ivkh_userhead;
	}
	
	class ByHolder{
		ImageView ivby_userhead;
		TextView tvby_chatcontent,tvby_sendtime;
	}
}
