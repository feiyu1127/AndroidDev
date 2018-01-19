package com.yucheng.byxf.mini.easyloan.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.message.OldCustResult;
import com.yucheng.byxf.mini.ui.BaseActivity;
import com.yucheng.byxf.mini.ui.GeneralConsumeLoanActivity;
import com.yucheng.byxf.mini.ui.HomeActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;

public class RelaxedLoanAdActivity extends BaseActivity {
	
	private String stateType=""; 
	private Button bt_next;
	private ImageView back;
	private boolean isOldCustomer=false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.relaxedloan_ad_activity);
	
		Intent it=getIntent();
		stateType=it.getStringExtra("choiceType");
		
		back=(ImageView) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				
				Intent intent =new Intent();
				intent.setClass(RelaxedLoanAdActivity.this,
						HomeActivity.class);
				startActivity(intent);
			finish();	
			}
		});
		bt_next=(Button) findViewById(R.id.bt_next);
		bt_next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				if (isOldCustomer) {
					//老客戶
					Intent intent = new Intent();
					intent.setClass(RelaxedLoanAdActivity.this,
							RelaxedLoanOneContractBook.class);
					intent.putExtra("choiceType", stateType);
					startActivity(intent);
					System.out.println("choiceType=提交頁面=>>>>" + stateType);
				} else {
					//新客戶
					Intent intent = new Intent();
					intent.setClass(RelaxedLoanAdActivity.this,
							GeneralConsumeLoanActivity.class);
					startActivity(intent);
				}
			}
		});
		if(Contents.response != null && Contents.response.getResult()!= null
					&& Contents.response.getResult().getIdNo()!= null) {
			new OldCosAsyncTask().execute(Contents.response
					.getResult().getIdNo());
		}//TODO else 值为空时该如何处理？
	
	}
	public boolean onKeyDown(int keyCode,KeyEvent event) {
		//这里重写返回键
		 if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
				Intent intent =new Intent();
				intent.setClass(RelaxedLoanAdActivity.this,
						HomeActivity.class);
				startActivity(intent);
			finish();
		return true;
		}
		 return false;
		}
	//!!!!!!!!!!!!!!!!!!!!老客户
	class OldCosAsyncTask extends AsyncTask<Object, Object, Object> {

		@Override
		protected Object doInBackground(Object... params_obj) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(RelaxedLoanAdActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("idNo", (String) params_obj[0]));
			return httpHelper.post(ContantsAddress.OldCust,
					arg, OldCustResult.class);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog();
		}

		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);
			dismissProgressDialog();
			OldCustResult moOldCustResult=(OldCustResult) result;
			if (moOldCustResult != null) {
				if(moOldCustResult.getCode()==0){
					if(moOldCustResult.getFlag()==null||"".equals(moOldCustResult.getFlag())){
						isOldCustomer=false;
					}else {
						if(moOldCustResult.getFlag().equals("true")){
							isOldCustomer=true;
						}else{
							isOldCustomer=false;
						}
					}
				}

		}

	}
	}
}