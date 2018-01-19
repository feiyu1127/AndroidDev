package com.yucheng.byxf.mini.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.adapter.AdanceRepaymentAdapter;
import com.yucheng.byxf.mini.message.AdvanceRepaymentApplyResponse;
import com.yucheng.byxf.mini.message.AdvanceRepaymentApplyResult;
import com.yucheng.byxf.mini.message.AdvanceRepaymentResponse;
import com.yucheng.byxf.mini.message.AdvanceRepaymentResult;
import com.yucheng.byxf.mini.message.ApplicationScheduleQueryResponse;
import com.yucheng.byxf.mini.ui.AdvanceRepayment.GetAdvanceHome;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.DialogUtil;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AdvanceRepayment3 extends BaseActivity {
	private AdvanceRepaymentResponse response2;
	private TextView tv_applyCde;// 申请编号
	private TextView tv_idNo;// 证件号
	private TextView tv_custName;// 客户姓名
	private TextView tv_applyDt;// 申请日期
	private TextView tv_applyAmt;// 申请金额
	private Button bt_next; // 下一步
	private static String re_type="no";
	private ImageView back;
	private String login_type="";
	private static ArrayList<AdvanceRepaymentApplyResponse> List3 = new
	 ArrayList<AdvanceRepaymentApplyResponse>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.advance_repayment_apply_activity);

		Bundle bundle = getIntent().getExtras();
		response2 = (AdvanceRepaymentResponse) bundle.getSerializable("list");
		init();

		setText();
	}

	private void init() {
		tv_applyDt = (TextView) findViewById(R.id.shenqingriqi);
		tv_idNo = (TextView) findViewById(R.id.zhengjianhaoma);
		tv_custName = (TextView) findViewById(R.id.kehuxingming);
		tv_applyAmt = (TextView) findViewById(R.id.shenqingjine);
		tv_applyCde = (TextView) findViewById(R.id.shenqingbianhao);
		bt_next = (Button) findViewById(R.id.button_next);
		bt_next.setOnClickListener(this);
		//返回back
		
				back = (ImageView) findViewById(R.id.tuikuan_apply_back);
				back.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
//						Intent it = new Intent(AdvanceRepayment3.this,
//								AdvanceRepayment.class);
//						startActivity(it);
						finish();

					}
				});
	}

	private void setText() {
		login_type=response2.getApplyType();//判断登录 标识！！！！！0表示极速贷，1表示轻松贷
		tv_applyDt.setText(response2.getApplyDt());
		tv_idNo.setText(response2.getIdNo());
		tv_custName.setText(response2.getCustName());
		tv_applyAmt.setText(response2.getApplyAmt()+"元");
		tv_applyCde.setText(response2.getApplyCde());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_next:
			// 709
//			public String applyCde;//申请编号
//			public String applyType;//0-1类型
//			public String loanTnr;//期限
//			public String loanTyp;//品种
			 new GetAdvanceApply().execute(response2.getIdNo(),response2.getLoanNo(),"FS","N","08",response2.getApplyCde(),response2.getApplyType()
					 ,response2.getLoanTnr(),response2.getLoanTyp(),response2.getAppvDt(),response2.getPomsDepart());

			 
//		测试	new GetAdvanceApply().execute("130133198409010019",
//					"HT0050010000025803001", "FS", "N", "08");
			break;
		default:
			break;
		}
	}

	@Override
	protected void onResume() {
		// TODO 自动生成的方法存根
		super.onResume();
		re_type="no";
	}

	class GetAdvanceApply extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {

			HttpHelper mBobcfcHttpClient = HttpHelper
					.getInstance(AdvanceRepayment3.this);
			String url = ContantsAddress.ADVANCEREPAYMENTAPPLY;
			List<NameValuePair> param = new ArrayList<NameValuePair>();
			param.add(new BasicNameValuePair("idNo", params[0]));
			param.add(new BasicNameValuePair("loanNo", params[1]));
			param.add(new BasicNameValuePair("setlMode", params[2]));
			param.add(new BasicNameValuePair("paymInd", params[3]));
			param.add(new BasicNameValuePair("inputSrc", params[4]));
			param.add(new BasicNameValuePair("applyCde", params[5]));
			param.add(new BasicNameValuePair("applyType", params[6]));
			param.add(new BasicNameValuePair("loanTnr", params[7]));//期限
			param.add(new BasicNameValuePair("loanTyp", params[8]));//品种
			param.add(new BasicNameValuePair("appvDt", params[9]));//终极日期
			param.add(new BasicNameValuePair("pomsDepart", params[10]));//bumen
			Class<AdvanceRepaymentApplyResult> clazz = AdvanceRepaymentApplyResult.class;
			AdvanceRepaymentApplyResult response = mBobcfcHttpClient.post(url,
					param, clazz);
			return response;

		}

		@Override
		protected void onPostExecute(Object result) {
			// TODO 自动生成的方法存根
			super.onPostExecute(result);
			dismissProgressDialog();
			AdvanceRepaymentApplyResult response = (AdvanceRepaymentApplyResult) result;
			re_type="yes";
			if (response != null) {
				System.out.println("不为空");
				if (response.getCode() == 0) {
					response.getResult();
					System.out.println("code=申请页面=>" + response.getCode());
					
					Intent it =new Intent(AdvanceRepayment3.this,AdvanceRepayment4.class);
					it.putExtra("Appl",response.getResult());
					it.putExtra("login_type",login_type);
					startActivity(it);
			}
				else if(response.getCode() == 7070901){
					DialogUtil.showDialogOneButton(AdvanceRepayment3.this,"很抱歉，该笔贷款暂时无法申请提前还款，如有疑问请拨打客服热线40066-95526",new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO 自动生成的方法存根
							Intent it = new Intent(AdvanceRepayment3.this,
									HomeActivity.class);
							startActivity(it);
						}
					});
				}else if(response.getCode() == 7070902){
					DialogUtil.showDialogOneButton(AdvanceRepayment3.this,"互联网提前还款业务受理时间为法定工作日的8:00-16:00，请您在该时间内提交申请",new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO 自动生成的方法存根
							Intent it = new Intent(AdvanceRepayment3.this,
									HomeActivity.class);
							startActivity(it);
						}
					});
				}
				else{
					 Toast.makeText(
							 AdvanceRepayment3.this,
							 response.getMsg(), Toast.LENGTH_LONG).show();
				}
			} else {
				Toast.makeText(
						AdvanceRepayment3.this,
						getResources().getString(R.string.no_network),
						Toast.LENGTH_LONG).show();
			
			}

		}
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
				if ((re_type.equals("yes"))) {
					
				} else {
					showProgressDialog();
				}
	}
	}

}
