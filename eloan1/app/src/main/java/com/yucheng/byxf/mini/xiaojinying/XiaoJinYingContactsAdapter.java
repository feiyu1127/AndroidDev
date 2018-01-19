package com.yucheng.byxf.mini.xiaojinying;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yucheng.byxf.mini.message.XiaojinYingRelationInfoResultBodyList;
import com.yucheng.byxf.mini.ui.R;

public class XiaoJinYingContactsAdapter extends BaseAdapter {

	private List<XiaojinYingRelationInfoResultBodyList> list;
	private LayoutInflater linfalater;

	public final class ListItemView {
		public TextView tname;
		public TextView tmobile;
		public TextView ttype;
		public TextView tphone;
		public TextView tcontacts_type;
		public TextView tquhao;
	}

	public XiaoJinYingContactsAdapter(
			List<XiaojinYingRelationInfoResultBodyList> listItems,
			Context context) {

		linfalater = LayoutInflater.from(context);
		this.list = listItems;
	}

	@Override
	public int getCount() {
		return list.size();
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
	public View getView(int position, View convertView, ViewGroup parent) {
		ListItemView listItemView = null;
		if (convertView == null) {
			listItemView = new ListItemView();
			convertView = linfalater.inflate(
					R.layout.xiaojinyin_contactsinfo_item, null);
			listItemView.tname = (TextView) convertView
					.findViewById(R.id.tx_name);
			listItemView.tmobile = (TextView) convertView
					.findViewById(R.id.tx_mobile);
			listItemView.ttype = (TextView) convertView
					.findViewById(R.id.tx_type);
			listItemView.tphone = (TextView) convertView
					.findViewById(R.id.tx_phone);
			listItemView.tcontacts_type = (TextView) convertView
					.findViewById(R.id.tx_contacts_type);
			listItemView.tquhao = (TextView) convertView
					.findViewById(R.id.tx_quhao);
			convertView.setTag(listItemView);

		} else {
			listItemView = (ListItemView) convertView.getTag();
		}

		listItemView.tname.setText((String) list.get(position).getContName());
		listItemView.tmobile
				.setText((String) list.get(position).getContPhone());
		// listItemView.ttype.setText((String) list.get(position).getContRel());
		listItemView.tphone.setText((String) list.get(position).getContTel());
		listItemView.tquhao.setText((String) list.get(position).getContZone());
		// listItemView.tcontacts_type.setText((String)
		// list.get(position).getContTyp());
		String ContRel = list.get(position).getContRel();

		// 01 配偶.02 父母.03 子女.04 其他血亲.05 其他姻亲.06 同事.
		// 07 合伙人.08 其他关系.09 兄弟姐妹.10 同学.11 朋友
		if (ContRel.equals("01")) {
			listItemView.ttype.setText("配偶");
		}
		if (ContRel.equals("02")) {
			listItemView.ttype.setText("父母");
		}
		if (ContRel.equals("03")) {
			listItemView.ttype.setText("子女");
		}
		if (ContRel.equals("04")) {
			listItemView.ttype.setText("其他血亲");
		}
		if (ContRel.equals("05")) {
			listItemView.ttype.setText("其他姻亲");
		}

		if (ContRel.equals("06")) {
			listItemView.ttype.setText("同事");
		}
		if (ContRel.equals("07")) {
			listItemView.ttype.setText("合伙人");
		}

		if (ContRel.equals("08")) {
			listItemView.ttype.setText("其他关系");
		}

		if (ContRel.equals("09")) {
			listItemView.ttype.setText("兄弟姐妹");
		}

		if (ContRel.equals("10")) {
			listItemView.ttype.setText("同学");
		}
		if (ContRel.equals("11")) {
			listItemView.ttype.setText("朋友");
		}
		// -================================================
		String ContTyp = list.get(position).getContTyp();
		// 01 直系联系人02 其他联系人03 第三联系人04 第四联系人
		if (ContTyp.equals("01")) {
			listItemView.tcontacts_type.setText("直系联系人");
		}
		if (ContTyp.equals("02")) {
			listItemView.tcontacts_type.setText("其他联系人");
		}
		if (ContTyp.equals("03")) {
			listItemView.tcontacts_type.setText("第三联系人");
		}
		if (ContTyp.equals("04")) {
			listItemView.tcontacts_type.setText("第四联系人");
		}
		return convertView;
	}
}
