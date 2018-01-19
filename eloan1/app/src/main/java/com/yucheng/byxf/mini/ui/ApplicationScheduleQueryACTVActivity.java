package com.yucheng.byxf.mini.ui;

import com.yucheng.byxf.mini.message.ApplicationScheduleQueryResponse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class ApplicationScheduleQueryACTVActivity extends BaseActivity {
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
	/** 放款状态 **/
	private TextView app_schedule_query_fangkuan_state;
	/** 还款状态 **/
	private TextView app_schedule_query_huankuan_state;

	private ImageView back;

	private ApplicationScheduleQueryResponse response;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.application_schedule_query_actv_activity);

		Intent intent = getIntent();
		response = (ApplicationScheduleQueryResponse) intent.getExtras()
				.getSerializable("list");

		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		back = (ImageView) findViewById(R.id.back);

		approve_progress_shenqing_num = (TextView) findViewById(R.id.approve_progress_shenqing_num);
		app_schedule_query_zhengjian_num = (TextView) findViewById(R.id.app_schedule_query_zhengjian_num);
		app_schedule_query_name = (TextView) findViewById(R.id.app_schedule_query_name);
		app_schedule_query_shenpi_time = (TextView) findViewById(R.id.app_schedule_query_shenpi_time);
		app_schedule_query_shenpi_money = (TextView) findViewById(R.id.app_schedule_query_shenpi_money);
		app_schedule_query_shenqing_qixian = (TextView) findViewById(R.id.app_schedule_query_shenqing_qixian);
		app_schedule_query_shenqing_state = (TextView) findViewById(R.id.app_schedule_query_shenqing_state);
		app_schedule_query_fangkuan_state = (TextView) findViewById(R.id.app_schedule_query_fangkuan_state);
		app_schedule_query_huankuan_state = (TextView) findViewById(R.id.app_schedule_query_huankuan_state);

		approve_progress_shenqing_num.setText(response.getApplCde());
		app_schedule_query_zhengjian_num.setText(response.getIdNo());
		app_schedule_query_name.setText(response.getCustName());
		app_schedule_query_shenpi_time.setText(response.getApplyDt());
		app_schedule_query_shenpi_money.setText(response.getApplyAmt()+"元");
		app_schedule_query_shenqing_qixian.setText(response.getApplyTnr()
				+ "个月");
		app_schedule_query_shenqing_state.setText(response.getStateShow());
		String fangkuanStatus = response.getActvStr();
		System.out.println(fangkuanStatus+"<==fangkuanStatus==");
		if (fangkuanStatus.equals("Y")) {
			app_schedule_query_fangkuan_state.setText("放款成功");
		} else if (fangkuanStatus.equals("N")) {
			app_schedule_query_fangkuan_state.setText("放款失败");
		} else if (fangkuanStatus.equals("W")) {
			app_schedule_query_fangkuan_state.setText("放款异常");
		}
		String huankuanStatus = response.getLoanDebtSts();
		System.out.println(huankuanStatus+"<===huankuan==");
		if (huankuanStatus.equals("OVER")) {
			app_schedule_query_huankuan_state.setText("逾期");
		} else if (huankuanStatus.equals("ACTV")) {
			app_schedule_query_huankuan_state.setText("正常");
		} else if (huankuanStatus.equals("EMPTY")) {
			app_schedule_query_huankuan_state.setText("空");
		} else if (huankuanStatus.equals("SETL")) {
			app_schedule_query_huankuan_state.setText("结清");
		}

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
}
