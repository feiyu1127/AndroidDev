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

import com.yucheng.byxf.mini.message.weichuzhangdan.Details;
import com.yucheng.byxf.mini.ui.MiniConsumeProvideBillActivity1;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.TypeUtil4ZhanagDan;
import com.yucheng.byxf.mini.xiaojinying.XiaoJinYingZhangDanActivity;

public class MiniConsumeProvideBillWeiChuAdapter extends BaseAdapter{

	@SuppressWarnings("unused")
    private Context context;
    
    private List<Details> list2;
    
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
    
    public MiniConsumeProvideBillWeiChuAdapter(List<Details> listItems, Context context)
    {
        this.context = context;
        linfalater = LayoutInflater.from(context);
        this.list2 = listItems;
    }
    
    @Override
    public int getCount()
    {
        return list2.size();
    }
    
    @Override
    public Object getItem(int position)
    {
        return list2.get(position);
    }
    
    @Override
    public long getItemId(int position)
    {
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
        
        // 交易描述
        listItemView.mingxiname.setText(list2.get(position).getTxDesc());
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
            // CASHOUT 取现CONSUME 消费STAGES 分期REPAYMENT 还款REVSE 冲正NORM_INT 正常利息
            // OD_INT 罚息YEAR_FEE 年费TAKE_FEE 提现费LATE_FEE滞纳金AFFIRM 存款确认
            
            TypeUtil4ZhanagDan mTypeUtil = new TypeUtil4ZhanagDan();
            
            if (mTypeUtil.getType(type) != null)
            {
                type = mTypeUtil.getType(type);
            }
            
            listItemView.jiaoyileixingtype.setText(type);
            listItemView.mingxiname.setText(type);
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
            XiaoJinYingZhangDanActivity.currentPositionTF = 1;
        }
        else
        {
            listItemView.item_two.setVisibility(View.GONE);
            listItemView.xiangqing.setBackgroundResource(R.drawable.arrow_down);
            
        }
        
        return convertView;
    }
}
