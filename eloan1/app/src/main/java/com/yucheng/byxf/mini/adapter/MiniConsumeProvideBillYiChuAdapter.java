package com.yucheng.byxf.mini.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yucheng.byxf.mini.message.XiaoJinYingZhangDanXiangQing2;
import com.yucheng.byxf.mini.message.weichuzhangdan.Details;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.ui.MiniConsumeProvideBillActivity1;
import com.yucheng.byxf.mini.utils.TypeUtil;
import com.yucheng.byxf.mini.utils.TypeUtil4ZhanagDan;
import com.yucheng.byxf.mini.xiaojinying.XiaoJinYingZhangDanActivity;
import com.yucheng.byxf.mini.xiaojinying.XiaoJinYingZhangDanBodyList;
import com.yucheng.byxf.mini.xiaojinying.XiaoJinYingZhangDanMingXiList_Weichu_Adapter.ListItemView;

public class MiniConsumeProvideBillYiChuAdapter extends BaseAdapter{

	@SuppressWarnings("unused")
    private Context context;
    
    private List<XiaoJinYingZhangDanXiangQing2> list2;
    
    private LayoutInflater linfalater;
    
    public final class ListItemView
    {
        // 明细名称
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
        
        // 交易金额——
        private TextView jiaoyijinenum;
        
        // 尹
        private RelativeLayout item_two;
    }

	public MiniConsumeProvideBillYiChuAdapter(
			List<XiaoJinYingZhangDanXiangQing2> listItems, Context context) {
		this.context = context;
		linfalater = LayoutInflater.from(context);
		this.list2 = listItems;
	}

	@Override
	public int getCount() {
		return list2.size();
	}

	@Override
	public Object getItem(int position) {
		return list2.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ListItemView listItemView = null;
        if (convertView == null)
        {
            listItemView = new ListItemView();
            convertView = linfalater.inflate(R.layout.xiaojinyin_zhangdanmingxi_item, null);
            listItemView.mingxiname = (TextView)convertView.findViewById(R.id.mingxiname);
            listItemView.xiangqing_name = (TextView)convertView.findViewById(R.id.xiangqing_name);
            listItemView.shijian = (TextView)convertView.findViewById(R.id.shijian);
            listItemView.renminbinum = (TextView)convertView.findViewById(R.id.renminbinum);
            listItemView.xiangqing = (TextView)convertView.findViewById(R.id.xiangqing);
            listItemView.jiaoyiriqidata = (TextView)convertView.findViewById(R.id.jiaoyiriqidata);
            listItemView.jiaoyileixingtype = (TextView)convertView.findViewById(R.id.jiaoyileixingtype);
            listItemView.jizhangriqidata = (TextView)convertView.findViewById(R.id.jizhangriqidata);
            listItemView.jiaoyijinenum = (TextView)convertView.findViewById(R.id.jiaoyijinenum);
            listItemView.item_two = (RelativeLayout)convertView.findViewById(R.id.item_two);
            convertView.setTag(listItemView);
            
        }
        else
        {
            listItemView = (ListItemView)convertView.getTag();
        }
        
        // 交易描述 2015年7月16日改为 交易类型
        // listItemView.mingxiname.setText(list2.get(position).getTxDesc());
        // 交易日期
        if (list2.get(position).getHostTxDt().toString() != null)
        {
            listItemView.shijian.setText((String)list2.get(position).getHostTxDt().toString());
        }
        
        // 金额
        if (list2.get(position).getTxAmt().toString() != null)
        {
            listItemView.renminbinum.setText("¥" + (String)list2.get(position).getTxAmt().toString());
        }
        
        //
        if (list2.get(position).getTxDesc() != null)
        {
            listItemView.xiangqing_name.setText(list2.get(position).getTxDesc());
        }
        
        // 交易日期
        if (list2.get(position).getHostTxDt().toString() != null)
        {
            listItemView.jiaoyiriqidata.setText((String)list2.get(position).getHostTxDt().toString());
        }
        
        // 交易类型
        if (list2.get(position).getTxTyp() != null)
        {
            String type = (String)list2.get(position).getTxTyp().toString();
            /*
             * LND4 日终还款
             * REPAYMENT 存款
             * CASHOUT 取现
             * LRX1 提领费
             * LRX4 年费
             * LND3 利息计提
             * LND5 滞纳金
             * LRD7 滞纳金
             * STAGES 分期
             * AFFIRM 还款
             * LRX5 提现费用
             * LCC5 挂失费用
             * LCC2 换卡费用
             * CASHOUTREVSE 取现冲正
             * TRANSFER 转账
             * PAYCANCEL 存款撤销
             * RETURN 退货
             * REPAYM 还款
             */
            TypeUtil4ZhanagDan mTypeUtil = new TypeUtil4ZhanagDan();
            
            if (mTypeUtil.getType(type) != null)
            {
                type = mTypeUtil.getType(type);
            }
            
            // 交易描述>2015年7月16日改为 交易类型
            listItemView.mingxiname.setText(type);
            // 交易类型
            listItemView.jiaoyileixingtype.setText(type);
        }
        
        // 记账日期
        if (list2.get(position).getAcctDt().toString() != null)
        {
            listItemView.jizhangriqidata.setText((String)list2.get(position).getAcctDt().toString());
        }
        
        // 交易金额
        if (list2.get(position).getTxAmt().toString() != null)
        {
            listItemView.jiaoyijinenum.setText((String)list2.get(position).getTxAmt().toString());
        }
        
        if (position == MiniConsumeProvideBillActivity1.currentPosition)
        {
            listItemView.item_two.setVisibility(View.VISIBLE);
            listItemView.xiangqing.setBackgroundResource(R.drawable.arrow_up);
            MiniConsumeProvideBillActivity1.currentPositionTF = 1;
        }
        else
        {
            listItemView.item_two.setVisibility(View.GONE);
            listItemView.xiangqing.setBackgroundResource(R.drawable.arrow_down);
            
        }
        return convertView;
    }
}
