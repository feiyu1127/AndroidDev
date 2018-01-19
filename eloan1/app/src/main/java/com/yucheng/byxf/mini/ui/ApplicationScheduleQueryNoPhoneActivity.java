package com.yucheng.byxf.mini.ui;

import com.yucheng.byxf.mini.message.ApplicationScheduleQueryResponse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class ApplicationScheduleQueryNoPhoneActivity extends BaseActivity{
	/** 申请编号 **/
	private TextView approve_progress_shenqing_num;
	/** 证件号 **/
	private TextView app_schedule_query_zhengjian_num;
	/** 客户姓名 **/
	private TextView app_schedule_query_name;
	/** 申请日期 **/
	private TextView app_schedule_query_shenpi_time;
	/** 申请金额 **/
	private TextView app_schedule_query_shenpi_money;
	/** 申请期限 **/
	private TextView app_schedule_query_shenqing_qixian;
	/** 申请状态 **/
	private TextView app_schedule_query_shenqing_state;
	
	private ImageView back;
	
	private ApplicationScheduleQueryResponse response;
	
	private ImageView iv_line;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.application_schedule_query_no_phone_activity);
		
		Intent intent = getIntent();
		response = (ApplicationScheduleQueryResponse) intent.getExtras().getSerializable("list");
		
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		back = (ImageView) findViewById(R.id.back);
		
		iv_line = (ImageView) findViewById(R.id.iv_line);
		approve_progress_shenqing_num = (TextView) findViewById(R.id.approve_progress_shenqing_num);
		app_schedule_query_zhengjian_num = (TextView) findViewById(R.id.app_schedule_query_zhengjian_num);
		app_schedule_query_name = (TextView) findViewById(R.id.app_schedule_query_name);
		app_schedule_query_shenpi_time = (TextView) findViewById(R.id.app_schedule_query_shenpi_time);
		app_schedule_query_shenpi_money = (TextView) findViewById(R.id.app_schedule_query_shenpi_money);
		app_schedule_query_shenqing_qixian = (TextView) findViewById(R.id.app_schedule_query_shenqing_qixian);
		app_schedule_query_shenqing_state = (TextView) findViewById(R.id.app_schedule_query_shenqing_state);
		
		approve_progress_shenqing_num.setText(response.getApplCde());
		app_schedule_query_zhengjian_num.setText(response.getIdNo());
		app_schedule_query_name.setText(response.getCustName());
		app_schedule_query_shenpi_time.setText(response.getApplyDt());
		app_schedule_query_shenpi_money.setText(response.getApplyAmt());
		app_schedule_query_shenqing_qixian.setText(response.getApplyTnr()+"个月");
		app_schedule_query_shenqing_state.setText(response.getStateShow());
		
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		String status = response.getContStatus();
		if (status.equals("200")) {
			iv_line.setBackgroundResource(R.drawable.actv_01);
		} 
		if (response.getDnWfFlag().equals("Y")) {
			iv_line.setBackgroundResource(R.drawable.actv_02);
		}
		if (response.getActvStr().equals("Y")) {
			iv_line.setBackgroundResource(R.drawable.actv_03);
		}
	}
}
