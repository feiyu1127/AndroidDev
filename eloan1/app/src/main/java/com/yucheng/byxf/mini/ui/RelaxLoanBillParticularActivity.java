package com.yucheng.byxf.mini.ui;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yucheng.byxf.mini.adapter.RelaxLoanBillParticularAdapter;
import com.yucheng.byxf.mini.message.PaymentDetail;
import com.yucheng.byxf.mini.message.RelaxBillDetailResult;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class RelaxLoanBillParticularActivity extends BaseActivity {
	private ListView listView;
	private RelaxLoanBillParticularAdapter adapter;
	private LinearLayout layout1;
	private RelativeLayout layout2;
	private boolean isFlag = false;
	private RelaxBillDetailResult result;
	private List<PaymentDetail> list_Details;
	private GsonBuilder gsonBuilder;
	private Gson gson;
	ImageView back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.relaxloanbill_particular_activity);
		initView();
	}
	private void initView() {
		
		back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		Intent intent = getIntent();
		gsonBuilder = new GsonBuilder();
		gson = gsonBuilder.create();
		result = gson.fromJson(intent.getStringExtra("response"),RelaxBillDetailResult.class);
		if (result != null){
			list_Details  = new ArrayList<PaymentDetail>();
			for (int i = 0; i < result.getResult().getList().size(); i++){
				PaymentDetail detail = new PaymentDetail();
				detail.setPsDueDt(result.getResult().getList().get(i).getPsDueDt().trim());
				detail.setPsPrcpAmt(result.getResult().getList().get(i).getPsPrcpAmt().trim());
				detail.setPsNormIntAmt(result.getResult().getList().get(i).getPsNormIntAmt().trim());
				detail.setPsInstmAmt(result.getResult().getList().get(i).getPsInstmAmt().trim());
				detail.setPsFeeAmt(result.getResult().getList().get(i).getPsFeeAmt().trim());
				detail.setPsRemPrcp(result.getResult().getList().get(i).getPsRemPrcp().trim());
				list_Details.add(detail);
			}
		}
		layout1 = (LinearLayout) findViewById(R.id.layout1);
		layout2 = (RelativeLayout) findViewById(R.id.layout2);
//		layout1.setVisibility(View.VISIBLE);
//		layout2.setVisibility(View.GONE);
		layout1.setVisibility(View.GONE);
		layout2.setVisibility(View.VISIBLE);

		listView  = (ListView) findViewById(R.id.relaxloanbill_particular_listView);
		adapter = new RelaxLoanBillParticularAdapter(list_Details, RelaxLoanBillParticularActivity.this,isFlag);
		listView.setAdapter(adapter);
		listView.setClickable(false);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				if (!isFlag){
					isFlag = true;
					layout1.setVisibility(View.VISIBLE);
					layout2.setVisibility(View.GONE);
				} else {
					isFlag = false;
					layout1.setVisibility(View.GONE);
					layout2.setVisibility(View.VISIBLE);			
				}
				adapter.notifyDataSetChanged();
				adapter = new RelaxLoanBillParticularAdapter(list_Details, RelaxLoanBillParticularActivity.this,isFlag);
				listView.setAdapter(adapter);
			}
		});
	}	
}