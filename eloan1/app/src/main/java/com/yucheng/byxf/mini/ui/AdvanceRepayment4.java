package com.yucheng.byxf.mini.ui;



import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.message.AdvanceRepaymentApplyResponse;
import com.yucheng.byxf.mini.message.AdvanceRepaymentApplyResult;
import com.yucheng.byxf.mini.message.AdvanceRepaymentApplyResultSubmitYe;
import com.yucheng.byxf.mini.ui.AdvanceRepayment3.GetAdvanceApply;
import com.yucheng.byxf.mini.ui.R.id;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.DialogUtil;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AdvanceRepayment4 extends BaseActivity{
private AdvanceRepaymentApplyResponse response4;
private String login_type;
private TextView hetongbianhao;
private TextView huankuanfangshi;
private TextView tiqianhuankuanbenjin;
private TextView shouxufei;
private TextView yuqizhinajin;
private TextView tiqianhuankuankjinegongji;
private TextView huankuanzhanghao;
private TextView jiekuanqixian;
//轻松
private TextView tiqianhuankuanzhanghao;
private TextView dangqilixi;
private TextView tiqianhuankuanweiyuejin;
private TextView yuqifaxi;
private TextView yuqibenjin;
private TextView yuqizhanghuguanlifei;
private TextView yuqilixi;
//----LinearLayout-
private LinearLayout qs_tiqianhuankuanzhanghao;
private LinearLayout qs_dangqilixi;
private LinearLayout qs_tiqianhuankuanweiyuejin;
private LinearLayout qs_yuqifaxi;
private LinearLayout qs_yuqibenjin;
private LinearLayout qs_yuqizhanghuguanlifei;
private LinearLayout qs_yuqilixi;
//js
private LinearLayout js_shouxufei;
private LinearLayout js_huankuanzhanghao;
private LinearLayout js_jiekuanqixian;


private static String su_type="no";
public String tx_jiejuhaho="";
public String tx_kehuxingming="";
public String tx_zhengjianhao="";
public String tx_huankuanzhanghao="";
public String tx_huankuanmoshi="";
public String tx_shifuodaozhang="";
public String tx_yuqifaxi="";
public String tx_znj="";
public String tx_shenqingbianhao;
public String tx_shenqingleixing;
public String tx_qixian;
public String tx_pinzhong;

public String tx_zhongshenriqi;
public String tx_lianloubumen;
private ImageView back;
private Button bt_sub;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.advance_repayment_submit_activity1);
		
		Bundle bundle = getIntent().getExtras();
		response4 = (AdvanceRepaymentApplyResponse) bundle.getSerializable("Appl");
		login_type = bundle.getString("login_type");
		 tx_shenqingbianhao=response4.getApplyCde();
		 tx_shenqingleixing=response4.getApplyType();
		 System.out.println("===>打印"+tx_shenqingbianhao+"===>"+tx_shenqingleixing);
		System.out.println("登录类型为0表示极速贷，1表示轻松贷===》"+login_type);
	init();
	setText();
	}
	
	@Override
	protected void onResume() {
		// TODO 自动生成的方法存根
		su_type="no";
		super.onResume();
	}

	private void init(){
		hetongbianhao=(TextView) findViewById(R.id.hetongbianhao);
		huankuanfangshi=(TextView) findViewById(R.id.huankuanfangshi);
		tiqianhuankuanbenjin=(TextView) findViewById(R.id.tiqianhuankuanbenjin);
		shouxufei=(TextView) findViewById(R.id.shouxufei);
		yuqizhinajin=(TextView) findViewById(R.id.yuqizhinajin);
		tiqianhuankuankjinegongji=(TextView) findViewById(R.id.tiqianhuankuanjinezongji);
		huankuanzhanghao=(TextView) findViewById(R.id.huankuanzhanghao);
		jiekuanqixian=(TextView) findViewById(R.id.jiekuanqixian);
		//轻松
		tiqianhuankuanzhanghao=(TextView) findViewById(R.id.tiqianhuankuanzhanghao);
		dangqilixi=(TextView) findViewById(R.id.dangqilixi);
		tiqianhuankuanweiyuejin=(TextView) findViewById(R.id.tiqianhuakuanweiyuejin);
		 yuqifaxi=(TextView) findViewById(R.id.yuqifaxi);
		yuqibenjin=(TextView) findViewById(R.id.yuqibenjin);
		yuqizhanghuguanlifei=(TextView) findViewById(R.id.yuqizhanghuguanlifei);
		yuqilixi=(TextView) findViewById(R.id.yuqilixi);
		
		js_shouxufei=(LinearLayout) findViewById(R.id.js_shouxufei);
		 js_huankuanzhanghao=(LinearLayout) findViewById(R.id.js_huankuanzhanghao);
		 js_jiekuanqixian=(LinearLayout) findViewById(R.id.js_jiekuanqixian);
		
		  qs_tiqianhuankuanzhanghao=(LinearLayout) findViewById(R.id.qs_tiqianhuankuanzhanghao);
		  qs_dangqilixi=(LinearLayout) findViewById(R.id.qs_dangqilixi);
		  qs_tiqianhuankuanweiyuejin=(LinearLayout) findViewById(R.id.qs_tiqianhuankuanweiyuejin);
		  qs_yuqifaxi=(LinearLayout) findViewById(R.id.qs_yuqifaxi);
		  qs_yuqibenjin=(LinearLayout) findViewById(R.id.qs_yuqibenjin);
		  qs_yuqizhanghuguanlifei=(LinearLayout) findViewById(R.id.qs_yuqizhanghuguanlifei);
	   	 qs_yuqilixi=(LinearLayout) findViewById(R.id.qs_yuqilixi);
		//返回back
		
				back = (ImageView) findViewById(R.id.back);
				back.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent it = new Intent(AdvanceRepayment4.this,
								AdvanceRepayment.class);
						startActivity(it);

					}
				});
		// 提交时("借据号",，”进件来源08“，"证件号110225197102012412"，"还款模式FS","是否已到账N","逾期罚息","逾期滞纳金"，“申请编号”，“申请类型”);
		 tx_jiejuhaho=response4.getLoanNo();
		// tx_kehuxingming=response4.getCustName();
		 tx_zhengjianhao=response4.getIdNo();
		 tx_huankuanzhanghao=response4.getAcctNo();
		 tx_huankuanmoshi=response4.getSetlMode();
		 tx_shifuodaozhang=response4.getPaymInd();
		 tx_yuqifaxi=response4.getFx();
		 tx_znj=response4.getZnj();
		 tx_shenqingbianhao=response4.getApplyCde();
		 tx_shenqingleixing=response4.getApplyType();
		//期限
		//品种
		 tx_qixian=response4.getLoanTnr();
		 tx_pinzhong=response4.getLoanTyp();

		tx_zhongshenriqi=response4.getAppvDt();
		tx_lianloubumen=response4.getPomsDepart();

		 
		 System.out.println(tx_jiejuhaho+"--->"+"->"+tx_kehuxingming+"--->"+tx_zhengjianhao+"--->"+tx_huankuanzhanghao+
				 "--->"+tx_huankuanmoshi+"--->"+tx_shifuodaozhang+"--->"+tx_yuqifaxi+"--->"+tx_znj+"");
	
		 
		 bt_sub=(Button) findViewById(R.id.button_next);
		 bt_sub.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				 new GetAdvanceSubmit().execute
				 (tx_jiejuhaho,"08",tx_zhengjianhao,"FS","N",tx_yuqifaxi,tx_znj,tx_shenqingbianhao,tx_shenqingleixing,tx_qixian,tx_pinzhong,tx_zhongshenriqi,tx_lianloubumen);
 
				 System.out.println("===>"+tx_jiejuhaho+"--->"+"08->"+"--->"+tx_zhengjianhao+"--FS N->"+tx_yuqifaxi+
						 "--->"+tx_znj+"--->"+tx_shenqingbianhao+"--->"+tx_shenqingleixing+"--->\\");
			
			}
		});
		 
	}
	private void setText(){
	//	0表示极速贷，1表示轻松贷
		if(login_type.equals("0")){
			
		}
		
		if(login_type.equals("1")){
			qs_tiqianhuankuanzhanghao.setVisibility(View.VISIBLE);
			qs_dangqilixi.setVisibility(View.VISIBLE);
			qs_tiqianhuankuanweiyuejin.setVisibility(View.VISIBLE);
			qs_yuqifaxi.setVisibility(View.VISIBLE);
			qs_yuqibenjin.setVisibility(View.VISIBLE);
			qs_yuqizhanghuguanlifei.setVisibility(View.VISIBLE);
			qs_yuqilixi.setVisibility(View.VISIBLE);
			
			 js_shouxufei.setVisibility(View.GONE);
			  js_huankuanzhanghao.setVisibility(View.GONE);
			  js_jiekuanqixian.setVisibility(View.GONE);
			//填充数据
			tiqianhuankuanzhanghao.setText(response4.getAcctNo());
			dangqilixi.setText(response4.getSetlPsIncTaken()+"元");
			tiqianhuankuanweiyuejin.setText(response4.getFalseAmt()+"元");
			yuqifaxi.setText(response4.getFx()+"元");
			yuqibenjin.setText(response4.getAllOdPrcpAmt()+"元");
			yuqizhanghuguanlifei.setText(response4.getAllPsFeeAmt()+"元");
			yuqilixi.setText(response4.getAllOdNormIntAmt()+"元");
		}
		hetongbianhao.setText(response4.getLoanContNo());
		huankuanfangshi.setText("全部还款");
		tiqianhuankuanbenjin.setText(response4.getSetlRemPrcpPaym()+"元");
		String s=response4.getOrigPrcp();
		double i=0;
		i=Double.parseDouble(s);
		System.out.println("s>>"+s);
		System.out.println("i>>"+i);
		//需要 动态修改手续费
		shouxufei.setText(response4.getOutlayAmt()+"元");	
//		if(i<501){
//		//	shouxufei.setText("10元");	
//			shouxufei.setText(response4.getOutlayAmt()+"元");	
//		}else
//		if(i>501&&i<1001){
//			shouxufei.setText(response4.getOutlayAmt()+"元");	
//		}else{
//			shouxufei.setText(response4.getOutlayAmt()+"元");	
//		}
		yuqizhinajin.setText(response4.getAllLateFee()+"元");
		tiqianhuankuankjinegongji.setText(response4.getAmt()+"元");
		huankuanzhanghao.setText(response4.getAcctNo());
		jiekuanqixian.setText("1个月");
	
	}
	
//	@Override
//		public void onClick(View v) {
//		switch (v.getId()) {
//		case R.id.button_next:
//			
//// 提交时("借据号",，”进件来源08“，"证件号110225197102012412"，"还款模式FS","是否已到账N","逾期罚息","逾期滞纳金"，“申请编号”，“申请类型”);
// new GetAdvanceSubmit().execute
// (tx_jiejuhaho,"08",tx_zhengjianhao,"FS","N",tx_yuqifaxi,tx_znj,tx_shenqingbianhao,tx_shenqingleixing);
//
//			break;
//		default:
//			break;
//		}
//		}
	
	//联网---》》》》》
	class GetAdvanceSubmit extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {

			HttpHelper mBobcfcHttpClient = HttpHelper
					.getInstance(AdvanceRepayment4.this);
			String url = ContantsAddress.ADVANCEREPAYMENTSUBMIT;
			List<NameValuePair> param = new ArrayList<NameValuePair>();
			// 提交时("借据号",，”进件来源08“，"证件号110225197102012412"，"还款模式FS","是否已到账N","逾期罚息","逾期滞纳金"，“申请编号”，“申请类型”);
			param.add(new BasicNameValuePair("loanNo", params[0]));
			param.add(new BasicNameValuePair("inputSrc", params[1]));
			param.add(new BasicNameValuePair("idNo", params[2]));
			param.add(new BasicNameValuePair("setlMode", params[3]));
			param.add(new BasicNameValuePair("paymInd", params[4]));
			param.add(new BasicNameValuePair("fx", params[5]));
			param.add(new BasicNameValuePair("znj", params[6]));
			param.add(new BasicNameValuePair("applyCde", params[7]));
			param.add(new BasicNameValuePair("applyType", params[8]));
			param.add(new BasicNameValuePair("loanTnr", params[9]));
			param.add(new BasicNameValuePair("loanTyp", params[10]));
			param.add(new BasicNameValuePair("appvDt", params[11]));
			param.add(new BasicNameValuePair("pomsDepart", params[12]));
			Class<AdvanceRepaymentApplyResultSubmitYe> clazz = AdvanceRepaymentApplyResultSubmitYe.class;
			AdvanceRepaymentApplyResultSubmitYe response = mBobcfcHttpClient.post(url,
					param, clazz);
			return response;

		}

		@Override
		protected void onPostExecute(Object result) {
			// TODO 自动生成的方法存根
			super.onPostExecute(result);
			dismissProgressDialog();
			AdvanceRepaymentApplyResultSubmitYe response = (AdvanceRepaymentApplyResultSubmitYe) result;
			su_type="yes";
			if (response != null) {
				System.out.println("不为空");
				if (response.getCode() == 0) {
					System.out.println("code=>" + response.getCode());
					DialogUtil.showDialogOneButton(AdvanceRepayment4.this, "提前还款提交成功！",new OnClickListener() {
						@Override
						public void onClick(View v) {
							// TODO 自动生成的方法存根
							Intent it =new Intent(AdvanceRepayment4.this,HomeActivity.class);
							startActivity(it);
						}
					});
					//提交成功！！！！！！！！！！！！！！！！！！！！
			}else{
				DialogUtil.showDialogOneButton(AdvanceRepayment4.this,  response.getMsg() ,new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO 自动生成的方法存根
						Intent it =new Intent(AdvanceRepayment4.this,HomeActivity.class);
						startActivity(it);
					}
				});
			}
			} else {
				Toast.makeText(
						AdvanceRepayment4.this,
						getResources().getString(R.string.no_network),
						Toast.LENGTH_LONG).show();
			
			}

		}
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
				if ((su_type.equals("yes"))) {
					
				} else {
					showProgressDialog();
				}
	}
	}
	
}
