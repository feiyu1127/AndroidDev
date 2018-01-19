package com.yucheng.byxf.mini.adapter;

import java.util.List;
import com.yucheng.byxf.mini.message.PaymentDetail;
import com.yucheng.byxf.mini.ui.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RelaxLoanBillParticularAdapter extends BaseAdapter {

	private List<PaymentDetail> mList;
	private Context mContext;
	private boolean mIsFlag;
	public RelaxLoanBillParticularAdapter(List<PaymentDetail> list,Context context, boolean isFlag) {
		super();
		this.mList = list;
		this.mContext = context;
		this.mIsFlag = isFlag;
	}
	
	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			if (mIsFlag){
				convertView = LayoutInflater.from(mContext).inflate(R.layout.relaxloanbill_particular_item, null);
				holder.psPrcpAmt = (TextView) convertView.findViewById(R.id.particular_loan_money);
				holder.psNormIntAmt = (TextView) convertView.findViewById(R.id.particular_accrual);
				holder.psFeeAmt = (TextView) convertView.findViewById(R.id.particular_overhead_expenses);
				holder.psPrcpAmt.setText(mList.get(position).getPsPrcpAmt().toString().trim());
				holder.psNormIntAmt.setText(mList.get(position).getPsNormIntAmt().toString().trim());
				holder.psFeeAmt.setText(mList.get(position).getPsFeeAmt().toString().trim());
			} else {
				convertView = LayoutInflater.from(mContext).inflate(R.layout.relaxloanbill_particular_title_item, null);
				holder.psInstmAmt = (TextView) convertView.findViewById(R.id.particular_date_gong);
				holder.psInstmAmt.setText(mList.get(position).getPsInstmAmt().toString().trim());
			}
			holder.psDueDt = (TextView) convertView.findViewById(R.id.particular_date);
			holder.psRemPrcp = (TextView) convertView.findViewById(R.id.particular_money);
			holder.psDueDt.setText(mList.get(position).getPsDueDt().toString().trim());
			holder.psRemPrcp.setText(mList.get(position).getPsRemPrcp().toString().trim());
			if (position % 2 == 0){
			   convertView.setBackgroundResource(R.drawable.list_item_sim_select);
			} else {
			   convertView.setBackgroundResource(R.drawable.list_item_sim);
			}
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		return convertView;
	}

	class ViewHolder {
		TextView psDueDt;     //放款日期
		TextView psPrcpAmt;   //本金
		TextView psNormIntAmt;//利息
		TextView psInstmAmt;  //期供
		TextView psFeeAmt;    //管理费
		TextView psRemPrcp;   //本金金额 
	}
}