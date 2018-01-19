package com.yucheng.byxf.mini.ui;

import com.yucheng.byxf.mini.message.AdvanceRepaymentResponse;
import com.yucheng.byxf.mini.message.AdvanceRepaymentResultResponse;
import com.yucheng.byxf.mini.message.ApplicationScheduleQueryResponse;
import com.yucheng.byxf.mini.rapidly.RapidlyLoanInfo1Activity;
import com.yucheng.byxf.mini.rapidly.RapidlyLoanInfoActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AdvanceRepaymentResult2 extends BaseActivity {
	private String login_type;//0--1
	private LinearLayout include;
	private ImageView back;
	private Button b_next;//下一步
	private TextView  shenqingbianhao;//申请编号
	private TextView  zhengjianhao;//证件号
	private TextView  kehuxingming;//客户姓名
	private TextView  shenqingriqi;//申请日期
	private TextView  shenqingjine;//申请金额
	private TextView  shenqingzhuangtai;//申请状态
	private TextView  huankuanzhuangtai;//还款状态
	
	private LinearLayout js_shenqingzhuangtai;
	private LinearLayout js_huankuanzhuangtai;
	private LinearLayout qs_shenqingqixian;
	private LinearLayout qs_huankuanshenqingqixian;
	private LinearLayout qs_huankuanshenqingzhuangtai;
	private TextView shenqingqixian;
	private TextView huankuanshenqingqixian;
	private TextView huankuanshenqingzhuangtai;

	AdvanceRepaymentResultResponse response;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.advance_repayment_result_activity1_x);

		Bundle bundle = getIntent().getExtras();
		response =(AdvanceRepaymentResultResponse) bundle.getSerializable("list_Result");
		init() ;
		initList();
	}

	private void init() {
		login_type=response.getApplyType();//0极速贷，1轻松贷
		System.out.println("===>"+login_type);
		shenqingbianhao=(TextView) findViewById(R.id.shenqingbianhao);
		zhengjianhao=(TextView) findViewById(R.id.zhengjianhaoma);
		kehuxingming=(TextView) findViewById(R.id.kehuxingming);
		shenqingriqi=(TextView) findViewById(R.id.shenqingriqi);
		shenqingjine=(TextView) findViewById(R.id.shenqingjine);
		shenqingzhuangtai=(TextView) findViewById(R.id.shenqingzhuangtai);
		huankuanzhuangtai=(TextView) findViewById(R.id.huankuanzhuangtai);
		//qs
		shenqingqixian=(TextView) findViewById(R.id.shenqingqixian);
		huankuanshenqingqixian=(TextView) findViewById(R.id.huankuanshenqingriqi);
	    huankuanshenqingzhuangtai=(TextView) findViewById(R.id.huankuanshenqingzhuantai);
		
		js_shenqingzhuangtai=(LinearLayout) findViewById(R.id.js_shenqingzhuangtai);
		js_huankuanzhuangtai=(LinearLayout) findViewById(R.id.js_huankuanzhuangtai);
		qs_shenqingqixian=(LinearLayout) findViewById(R.id.qs_shenqingqixian);
		qs_huankuanshenqingqixian=(LinearLayout) findViewById(R.id.qs_huankuanshenqingriqi);
		qs_huankuanshenqingzhuangtai=(LinearLayout) findViewById(R.id.qs_huankuanshenqingzhuangtai);
		
		 b_next = (Button) findViewById(R.id.button_next);
		 //根据状态隐藏  下一步按钮
		 if(response.getPayAppState().equals("1")){
			 b_next.setVisibility(View.VISIBLE); 
		 }else{
			 b_next.setVisibility(View.GONE); 
		 }
		 
		 // 下一步
		b_next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent it = new Intent(AdvanceRepaymentResult2.this,
						AdvanceRepaymentResult3.class);
				
				it.putExtra("Result2", response);
				startActivity(it);
			}
		});

		//返回back
		
		back = (ImageView) findViewById(R.id.tuikuan_result_back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Intent it = new Intent(AdvanceRepaymentResult2.this,
//						AdvanceRepayment.class);
//				startActivity(it);
				finish();

			}
		});
		
		//js  隐藏申请状态   还款状态 
		//qs 加 qs_shenqingqixian  qs_huankuanshenqingqixian qs_huankuanshenqingzhuangtai
		if(login_type.equals("1")){
		  js_shenqingzhuangtai.setVisibility(View.GONE);
		  js_huankuanzhuangtai.setVisibility(View.GONE);
	   	 qs_shenqingqixian.setVisibility(View.VISIBLE);
		  qs_huankuanshenqingqixian.setVisibility(View.VISIBLE);
		  qs_huankuanshenqingzhuangtai.setVisibility(View.VISIBLE);
	//--
	
		}
	}
	
	private void initList(){
		shenqingbianhao.setText(response.getApplyCde());
		zhengjianhao.setText(response.getIdNo());
		kehuxingming.setText(response.getCustName());
		shenqingriqi.setText(response.getPayAppDt());
		shenqingjine.setText(response.getOrigPrcp()+"元");
		//加判断！shenqingzhuangtai.setText(response.getPayAppState());
		//还款申请状态 0处理中 1成功 2失败
				if(response.getPayAppState().equals("0")){
					shenqingzhuangtai.setText("处理中");
				}else if(response.getPayAppState().equals("1")){
					shenqingzhuangtai.setText("成功");
				}else if(response.getPayAppState().equals("2")){
					shenqingzhuangtai.setText("失败");
				}else{
					shenqingzhuangtai.setText(response.getPayAppState());
				}
		
		//判断  0未扣款 1全部还清 2部分还清
		if(response.getPayState().equals("0")){
			huankuanzhuangtai.setText("未扣款");
		}else if(response.getPayState().equals("1")){
			huankuanzhuangtai.setText("全部还清");
		}else if(response.getPayState().equals("2")){
			huankuanzhuangtai.setText("部分还清");
		}else{
			huankuanzhuangtai.setText(response.getPayState());
		}
		
		//qs
		 shenqingqixian.setText(response.getApplyTnr()+"个月");
		huankuanshenqingqixian.setText(response.getPayAppDt());
		//还款申请状态 0处理中 1成功 2失败
		if(response.getPayAppState().equals("0")){
			huankuanshenqingzhuangtai.setText("处理中");
		}else if(response.getPayAppState().equals("1")){
			huankuanshenqingzhuangtai.setText("成功");
		}else if(response.getPayAppState().equals("2")){
			huankuanshenqingzhuangtai.setText("失败");
		}else{
			huankuanshenqingzhuangtai.setText(response.getPayAppState());
		}
	}
	public boolean onKeyDown(int keyCode,KeyEvent event) {
		//这里重写返回键
		 if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
				Intent it = new Intent(AdvanceRepaymentResult2.this,
						AdvanceRepayment.class);
				startActivity(it);
		return true;
		}
		 return false;
		}
}