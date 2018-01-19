package com.yucheng.byxf.mini.ui;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.yucheng.byxf.mini.message.MiniCardActivateList;
import com.yucheng.byxf.mini.utils.Contents;

public class ApplicationScheduleQueryActivity extends BaseActivity implements
		OnClickListener {
	/**
	 * mini申请查询第二页
	 */
	private TextView app_schedule_query_name;
	private TextView app_schedule_query_zhengjian_num;
	private TextView app_schedule_query_shenqing_money;
	private TextView app_schedule_query_jihuo_state;
	private TextView app_schedule_query_shenpi_state;
	private TextView app_schedule_query_jihuo_data;
	private ImageView app_schedule_query_menu;
	private MiniCardActivateList response;
	private String[] stateCode = new String[] { "001", "111", "990", "991",
			"992", "997", "998" };
	private String[] stateChina = new String[] { "待发起", "审批中", "取消", "追回",
			"打回", "通过", "否决(不同意)" };
	private String[] jihuoStateCode = new String[] { "A", "I", "C", "H" };
	private String[] jihuoStateChina = new String[] { "有效", "无效", "销卡", "挂失" };
	private TextView app_schedule_query_shengqingbianhao;
	private TextView app_schedule_query_shenqingshijian;
	private TextView app_schedule_query_shenheriqi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.application_schedule_query);
		Bundle bundle = getIntent().getExtras();
		response = (MiniCardActivateList) bundle.getSerializable("list");
		initView();
	}

	private void initView() {
		  app_schedule_query_shengqingbianhao=(TextView) findViewById(R.id.app_schedule_query_shenqingbianhao);
		 app_schedule_query_shenqingshijian=(TextView) findViewById(R.id.app_schedule_query_shenqingshijian);
		 app_schedule_query_shenheriqi=(TextView) findViewById(R.id.app_schedule_query_shenheshiqi);
		app_schedule_query_name = (TextView) findViewById(R.id.app_schedule_query_name);
		app_schedule_query_zhengjian_num = (TextView) findViewById(R.id.app_schedule_query_zhengjian_num);
		
		app_schedule_query_shenpi_state = (TextView) findViewById(R.id.app_schedule_query_shenpi_state);
		app_schedule_query_menu = (ImageView) findViewById(R.id.app_schedule_query_menu);

		app_schedule_query_menu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
			finish();	
			}
		});
 
		setTextData();
	}

	private void setTextData() {
		if(Contents.response != null && Contents.response.getResult() != null) {
			// 客户姓名
			app_schedule_query_name.setText(Contents.response.getResult()
					.getCipNamecn());
			// 证件号码
			app_schedule_query_zhengjian_num.setText(Contents.response.getResult()
					.getIdNo());
		}
		// 审批状态
		app_schedule_query_shenpi_state.setText(response.getApplSts());
		//申请编号
		 app_schedule_query_shengqingbianhao.setText(response.getApplSeq());
		//申请时间
		  app_schedule_query_shenqingshijian.setText(response.getCrtDt());
		//审核日期
		 app_schedule_query_shenheriqi.setText(response.getApplDt());
	}

 

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}