package com.yucheng.byxf.mini.xiaojinying;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yucheng.byxf.mini.message.rijian.Details;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.TypeUtil;
import com.yucheng.byxf.mini.utils.Utils4JiaoYiJieGuo;

/**
 * 类名: XiaoJinYingZhangDanRiJianJiaoYiAdapter</br> 描述: 日间交易明细</br> 开发人员：
 * weiyb</br> 创建时间： 2015-7-13
 */

public class XiaoJinYingZhangDanRiJianJiaoYiAdapter extends BaseAdapter {
	@SuppressWarnings("unused")
	private Context context;

	private List<Details> list;

	private LayoutInflater linfalater;

	public final class ListItemView {
		// 交易明细
		private TextView mingxiname;

		// 交易时间
		private TextView shijian;

		// 明细金额
		private TextView renminbinum;

		// // 详情按钮
		private TextView xiangqing;

		// 明细名称——
		private TextView xiangqing_name;

		// 交易时间——
		private TextView jiaoyiriqidata;

		// 交易类型
		private TextView jiaoyileixingtype;

		// 记账日期
		private TextView jizhangriqidata;

		// 交易金额—
		private TextView jiaoyijinenum;
		// —》实扣金额
		private TextView sikoujine_numb;

		// 交易结果

		private TextView jiaoyijieguodata;

		//
		private RelativeLayout item_two;

		// 底部线
		private View foot_line;

		// 交易描述

		private TextView jiaoyimiaoshu;

	}

	public XiaoJinYingZhangDanRiJianJiaoYiAdapter(List<Details> listItems,
			Context context) {
		this.context = context;
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

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ListItemView listItemView = null;

		if (convertView == null) {
			listItemView = new ListItemView();
			convertView = linfalater.inflate(
					R.layout.xiaojinyin_zhangdan_rijianjiaoyi_item, null);
			listItemView.mingxiname = (TextView) convertView
					.findViewById(R.id.mingxiname);
			listItemView.xiangqing_name = (TextView) convertView
					.findViewById(R.id.xiangqing_name);
			listItemView.shijian = (TextView) convertView
					.findViewById(R.id.shijian);
			listItemView.renminbinum = (TextView) convertView
					.findViewById(R.id.renminbinum);
			listItemView.xiangqing = (TextView) convertView
					.findViewById(R.id.xiangqing);
			listItemView.jiaoyiriqidata = (TextView) convertView
					.findViewById(R.id.jiaoyiriqidata);
			listItemView.jiaoyileixingtype = (TextView) convertView
					.findViewById(R.id.jiaoyileixingtype);
			listItemView.jizhangriqidata = (TextView) convertView
					.findViewById(R.id.jizhangriqidata);
			// 交易金额
			listItemView.jiaoyijinenum = (TextView) convertView
					.findViewById(R.id.jiaoyijinerdata);
			listItemView.item_two = (RelativeLayout) convertView
					.findViewById(R.id.item_two);

			listItemView.jiaoyijieguodata = (TextView) convertView
					.findViewById(R.id.jiaoyijieguodata);
			// 交易描述
			listItemView.jiaoyimiaoshu = (TextView) convertView
					.findViewById(R.id.jiaoyimiaoshu);

			listItemView.foot_line = convertView.findViewById(R.id.foot_line);
			listItemView.sikoujine_numb = (TextView) convertView
					.findViewById(R.id.sikoujine_numb);

			convertView.setTag(listItemView);

		} else {
			listItemView = (ListItemView) convertView.getTag();
		}

		// 交易日期
		if (list.get(position).getTxDt().toString() != null) {
			listItemView.shijian.setText((String) list.get(position).getTxDt()
					.toString());
		}

		// 金额
		if (list.get(position).getTxAmt().toString() != null) {

			if (!list.get(position).getTxAmt().toString().equals("")) {
				listItemView.renminbinum.setText("¥"
						+ (String) list.get(position).getTxAmt().toString());
			} else {
				listItemView.renminbinum.setText("");
			}

		} else {
			listItemView.renminbinum.setText("");
		}

		// 实扣金额
		if (list.get(position).getActualAmt() != null) {

			if (!list.get(position).getActualAmt().equals("")) {
				listItemView.sikoujine_numb.setText("¥"
						+ list.get(position).getActualAmt() + "");
			} else {
				listItemView.sikoujine_numb.setText("");
			}

		} else {
			listItemView.sikoujine_numb.setText("");
		}

		// 交易明细
		// if (list.get(position).getTxDesc() != null) {
		// listItemView.xiangqing_name
		// .setText(list.get(position).getTxDesc());
		// }

		// 交易日期
		if (list.get(position).getTxDt().toString() != null) {
			listItemView.jiaoyiriqidata.setText((String) list.get(position)
					.getTxDt().toString());
		} else {
			listItemView.jiaoyiriqidata.setText("");
		}

		// 交易描述>2015年7月16日改为 交易类型
		// 交易类型
		if (list.get(position).getTxTyp() != null) {
			String type = (String) list.get(position).getTxTyp().toString();
			// CASHOUT 取现CONSUME 消费STAGES 分期REPAYMENT 还款REVSE 冲正NORM_INT 正常利息
			// OD_INT 罚息YEAR_FEE 年费TAKE_FEE 提现费LATE_FEE滞纳金AFFIRM 存款确认

			// 根据预设键值对转换类型字符串
			TypeUtil mTypeUtil = new TypeUtil();

			if (mTypeUtil.getType(type) != null) {
				type = mTypeUtil.getType(type);
			}

			// 交易描述>2015年7月16日改为 交易类型
			listItemView.mingxiname.setText(type);
			// 交易类型
			listItemView.jiaoyileixingtype.setText(type);

		} else {
			// 交易描述>2015年7月16日改为 交易类型
			listItemView.mingxiname.setText("");
			// 交易类型
			listItemView.jiaoyileixingtype.setText("");
		}

		// 记账日期
		if (list.get(position).getRtPrcsDt().toString() != null) {
			listItemView.jizhangriqidata.setText((String) list.get(position)
					.getRtPrcsDt().toString());
		} else {
			listItemView.jizhangriqidata.setText("");
		}

		// 交易金额
		if (list.get(position).getTxAmt().toString() != null) {

			if (!list.get(position).getTxAmt().toString().equals("")) {

				listItemView.jiaoyijinenum.setText("¥"
						+ (String) list.get(position).getTxAmt().toString());
			} else {
				listItemView.jiaoyijinenum.setText("");
			}

		} else {
			listItemView.jiaoyijinenum.setText("");
		}

		// 交易结果
		if (list.get(position).getPrcsSts() != null) {
			Utils4JiaoYiJieGuo mUtils4JiaoYiJieGuo = new Utils4JiaoYiJieGuo();

			String mtype = "";

			mtype = list.get(position).getPrcsSts();

			listItemView.jiaoyijieguodata.setText(mUtils4JiaoYiJieGuo
					.getType(mtype));

		} else {
			listItemView.jiaoyijieguodata.setText("");
		}

		// 交易描述

		if (list.get(position).getRemark() != null) {
			listItemView.jiaoyimiaoshu.setText(list.get(position).getRemark());
		} else {
			listItemView.jiaoyimiaoshu.setText("");
		}

		if (position == XiaoJinYingZhangDanActivity2.currentPosition) {
			listItemView.item_two.setVisibility(View.VISIBLE);
			listItemView.xiangqing.setBackgroundResource(R.drawable.arrow_up);
			XiaoJinYingZhangDanActivity2.currentPositionTF = 1;
		} else {
			listItemView.item_two.setVisibility(View.GONE);
			listItemView.xiangqing.setBackgroundResource(R.drawable.arrow_down);

		}

		if (position == list.size() - 1) {
			listItemView.foot_line.setVisibility(View.VISIBLE);
		} else {
			listItemView.foot_line.setVisibility(View.GONE);
		}
		// LogUtil.i("Position", position + "****" + list.size());

		return convertView;
	}

	public List<Details> getList() {
		return list;
	}

}
