package com.yucheng.byxf.mini.ui;

import com.yucheng.byxf.mini.message.AdvanceRepaymentResponse;
import com.yucheng.byxf.mini.message.AdvanceRepaymentResultResponse;
import com.yucheng.byxf.mini.message.ApplicationScheduleQueryResponse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AdvanceRepaymentResult3 extends BaseActivity{
	private String login_type;
	private Button bt_next;//下一步
	private TextView jikuanrenxingming;//借款人姓名
	private TextView shenfenzhenghao;//身份证号
	private TextView shenqingriqi;//申请日期
	private TextView hetongbianhao;//合同编号
	private TextView daikuanjine;//贷款金额
	private TextView tiqianhuankuanbenjin;//提前还款本金
	private TextView shouxufen;//手续费
	private TextView yuqizhinajin;//预期滞纳金
	private TextView qiqianhuankuanjinegongji;//提前还款本金
	private TextView tiqianhuankuanshenpizhuantai;//提前还款审批状态
	private TextView shikoujine;//实扣金额
	private TextView koukuanzhuangai;//扣款状态
	private TextView qingchangriqi;//清偿日期
	private TextView jiekuanqixian;//借款期限
	private TextView tiqianhuankuanjinezongji;
	private ImageView back;
	private LinearLayout js_shouxufei;
	//qs--------

	private TextView yongtu;
	private TextView dangqilixi;
	private TextView tiqianhuankuanweiyuejin;
	private TextView yuqifaxi;
	private TextView yuqibenjin;
	private TextView yuqizhanghuguanlifei;
	private TextView yuqilixi;
	
	private LinearLayout qs_yongtu;
	private LinearLayout qs_dangqilixi;
	private LinearLayout qs_tiqianhuankuanweiyuejin;
	private LinearLayout qs_yuqifaxi;
	private LinearLayout qs_yuqibenjin;
	private LinearLayout qs_yuqizhanghuguanlifei;
	private LinearLayout qs_yuqilixi;
	AdvanceRepaymentResultResponse response;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.advance_repayment_result_activity2);
		
		Bundle bundle = getIntent().getExtras();
		response =(AdvanceRepaymentResultResponse) bundle.getSerializable("Result2");
		init();
		initList();
	}
	
	private void init(){
		jikuanrenxingming=(TextView) findViewById(R.id.jiekuanrenxingming);
		shenfenzhenghao=(TextView) findViewById(R.id.shenfenzhenghao);
		shenqingriqi=(TextView) findViewById(R.id.shenqingriqi);
		hetongbianhao=(TextView) findViewById(R.id.hetongbianhao);
		daikuanjine=(TextView) findViewById(R.id.daikuanjine);
		tiqianhuankuanbenjin=(TextView) findViewById(R.id.tiqianhuankuanbenjin);
		shouxufen=(TextView) findViewById(R.id.shouxufei);
		yuqizhinajin=(TextView) findViewById(R.id.yuqizhinajin);
		qiqianhuankuanjinegongji=(TextView) findViewById(R.id.tiqianhuankuanjinezongji);
		tiqianhuankuanshenpizhuantai=(TextView) findViewById(R.id.tiqianhuankuanshenpizhuangtai);
		shikoujine=(TextView) findViewById(R.id.shikoujine);
		koukuanzhuangai=(TextView) findViewById(R.id.koukuanzhuangtai);
		qingchangriqi=(TextView) findViewById(R.id.qichangriqi);
		jiekuanqixian=(TextView) findViewById(R.id.jiekuanqixian);
		tiqianhuankuanjinezongji=(TextView) findViewById(R.id.tiqianhuankuanjinezongji);
		
		js_shouxufei=(LinearLayout) findViewById(R.id.js_shouxufei);
		 qs_yongtu=(LinearLayout) findViewById(R.id.qs_yongtu);
		 qs_dangqilixi=(LinearLayout) findViewById(R.id.qs_dangqilixi);
		 qs_tiqianhuankuanweiyuejin=(LinearLayout) findViewById(R.id.qs_tiqianhuankuanweiyuejin);
		 qs_yuqifaxi=(LinearLayout) findViewById(R.id.qs_yuqifaxi);
		 qs_yuqibenjin=(LinearLayout) findViewById(R.id.qs_yuqibenjin);
		 qs_yuqizhanghuguanlifei=(LinearLayout) findViewById(R.id.qs_yuqizhanghuguanlifei);
		 qs_yuqilixi=(LinearLayout) findViewById(R.id.qs_yuqilixi);
	
		  yongtu=(TextView) findViewById(R.id.yongtu);
		 dangqilixi=(TextView) findViewById(R.id.dangqilixi);
		 tiqianhuankuanweiyuejin=(TextView) findViewById(R.id.tiqianhuankuanweiyuejin);
		  yuqifaxi=(TextView) findViewById(R.id.yuqifaxi);
		  yuqibenjin=(TextView) findViewById(R.id.yuqibenjin);
		  yuqizhanghuguanlifei=(TextView) findViewById(R.id.yuqizhanghuguanlifei);
		  yuqilixi=(TextView) findViewById(R.id.yuqilixi);

		  //nextButton下一步
		bt_next=(Button) findViewById(R.id.button_next);
		bt_next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent it=new Intent();
				it.setClass(AdvanceRepaymentResult3.this, HomeActivity.class);
				startActivity(it);
			}
		});
		
		//返回back
		
		back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(AdvanceRepaymentResult3.this,
						AdvanceRepayment.class);
				startActivity(it);
			}
		});
	}
	private void initList(){
		login_type=response.getApplyType();//0极速贷，1轻松贷
		if(login_type.equals("1")){
			
			//填充數據
			if(response.getPurpose().equals("DEC")){
				yongtu.setText("装修");
			}else if(response.getPurpose().equals("EDU")){
				yongtu.setText("教育");
			}else if(response.getPurpose().equals("MAR")){
				yongtu.setText("婚庆");
			}else if(response.getPurpose().equals("MED")){
				yongtu.setText("医疗健康");
			}else if(response.getPurpose().equals("OTH")){
				yongtu.setText("其他");
			}else if(response.getPurpose().equals("TRA")){
				yongtu.setText("旅游");
			}else{
				yongtu.setText(response.getPurpose());
			}
			 
			 dangqilixi.setText(response.getSetlPsIncTaken()+"元");
			 tiqianhuankuanweiyuejin.setText(response.getFalseAmt()+"元");
			 yuqifaxi.setText(response.getFx()+"元");
			 yuqibenjin.setText(response.getSetlOdPrcpAmt()+"元");
			yuqizhanghuguanlifei.setText(response.getAllPsFeeAmt()+"元");
			 yuqilixi.setText(response.getAllOdNormIntAmt()+"元");
		
			js_shouxufei.setVisibility(View.GONE);
			 qs_yongtu.setVisibility(View.VISIBLE);
			 qs_dangqilixi.setVisibility(View.VISIBLE);
			 qs_tiqianhuankuanweiyuejin.setVisibility(View.VISIBLE);
			qs_yuqifaxi.setVisibility(View.VISIBLE);
			 qs_yuqibenjin.setVisibility(View.VISIBLE);
			 qs_yuqizhanghuguanlifei.setVisibility(View.VISIBLE);
			 qs_yuqilixi.setVisibility(View.VISIBLE);
		}
		jikuanrenxingming.setText(response.getCustName());
		shenfenzhenghao.setText(response.getIdNo());
		shenqingriqi.setText(response.getPayAppDt());//还款申请日期
		hetongbianhao.setText(response.getLoanContNo());
		daikuanjine.setText(response.getOrigPrcp()+"元");
		tiqianhuankuanbenjin.setText(response.getSetlRemPrcpPaym()+"元");
		String s=response.getOrigPrcp();
		double i=0;
		i=Double.parseDouble(s);
		System.out.println("s>>"+s);
		System.out.println("i>>"+i);
		//需要 动态修改手续费
		if(i<501){
			shouxufen.setText(response.getOutlayAmt()+"元");	
	//		shouxufen.setText("10元");	
		}else
		if(i>501&&i<1001){
			shouxufen.setText(response.getOutlayAmt()+"元");	
		}else{
			shouxufen.setText(response.getOutlayAmt()+"元");
		}
		yuqizhinajin.setText(response.getZnj()+"元");
		qiqianhuankuanjinegongji.setText(response.getAmt()+"元");
		//加判断 提前还款审批状态     000-待发起 ...111-审批中... 990-取消 ..991-追回.. 992-打回 ..997-通过 ..998-否决(不同意)
		if(response.getState().equals("000")){
			tiqianhuankuanshenpizhuantai.setText("待发起");
		}else if(response.getState().equals("111")){
			tiqianhuankuanshenpizhuantai.setText("审批中");
		}else if(response.getState().equals("990")){
			tiqianhuankuanshenpizhuantai.setText("取消");
		}else if(response.getState().equals("991")){
			tiqianhuankuanshenpizhuantai.setText("追回 ");
		}else if(response.getState().equals("992")){
			tiqianhuankuanshenpizhuantai.setText("打回");
		}else if(response.getState().equals("997")){
			tiqianhuankuanshenpizhuantai.setText("通过 ");
		}else if(response.getState().equals("998")){
			tiqianhuankuanshenpizhuantai.setText("否决(不同意)");
		}else{
			tiqianhuankuanshenpizhuantai.setText(response.getState());
		}
	
		shikoujine.setText(response.getActTransAmt()+"元");
		//加判断SU：数据初始化 FZ：扣款成功 CP：帐务处理成 FA：扣款失败 SP：现金卡扣款失败

		if(response.getDdaSts().equals("SU")){
			koukuanzhuangai.setText("数据初始化");
		}else if(response.getDdaSts().equals("FZ")){
			koukuanzhuangai.setText("扣款成功 ");
		}else if(response.getDdaSts().equals("CP")){
			koukuanzhuangai.setText("帐务处理中");
		}else if(response.getDdaSts().equals("FA")){
			koukuanzhuangai.setText("扣款失败");
		}else if(response.getDdaSts().equals("SP")){
			koukuanzhuangai.setText("现金卡扣款失败");
		}else {
			koukuanzhuangai.setText(response.getDdaSts());
		}
		qingchangriqi.setText(response.getOlDdaDt());
		jiekuanqixian.setText(response.getApplyTnr()+"个月");
		tiqianhuankuanjinezongji.setText(response.getAmt()+"元");
	}
	
}
