package com.yucheng.byxf.mini.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.R.color;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.Time;
import android.text.method.DialerKeyListener;
import android.text.style.LineHeightSpan.WithDensity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.adapter.AdanceRepaymentAdapter;
import com.yucheng.byxf.mini.adapter.AdanceRepaymentAdapter2;
import com.yucheng.byxf.mini.adapter.ApplicationScheduleQueryNAdaper;
import com.yucheng.byxf.mini.message.AdvanceRepaymentResponse;
import com.yucheng.byxf.mini.message.AdvanceRepaymentResult;
import com.yucheng.byxf.mini.message.AdvanceRepaymentResultAllResult;
import com.yucheng.byxf.mini.message.AdvanceRepaymentResultResponse;
import com.yucheng.byxf.mini.message.ApplicationScheduleQueryResponse;
import com.yucheng.byxf.mini.message.ApplicationScheduleQueryResult;
import com.yucheng.byxf.mini.rapidly.RapidlyLoanInfo1Activity;
import com.yucheng.byxf.mini.rapidly.TranscribeVideoActivity;
import com.yucheng.byxf.mini.ui.ApplicationScheduleQueryHomeActivity.GetACTVApplicationSchedule;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.views.XListView;
import com.yucheng.byxf.mini.views.XListView.IXListViewListener;

public class AdvanceRepayment extends BaseActivity implements OnClickListener
// ,IXListViewListener
{

	private List<AdvanceRepaymentResponse> ApplyList = new ArrayList<AdvanceRepaymentResponse>();
	// private List<AdvanceRepaymentResponse> ApplyListtMore = new
	// ArrayList<AdvanceRepaymentResponse>();
	private List<AdvanceRepaymentResultResponse> ResultList = new ArrayList<AdvanceRepaymentResultResponse>();
	// private List<AdvanceRepaymentResponse> ResultListMore = new
	// ArrayList<AdvanceRepaymentResponse>();

	private Button ApplyButton; // 申请页面
	private Button ResultButton; // 结果页面
	private ListView listView_apply;
	private ListView listView_result;
	private ImageView pic_null;
	private AdanceRepaymentAdapter adapter;
	private AdanceRepaymentAdapter2 adapter2;
	private ImageView back;
	private int listPosition;
	private AdvanceRepayment activity;

	private String type="Tp_Apple";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.advance_repayment_home_activity);
		activity = new AdvanceRepayment();
		initview();
		//initDate();//8:00-16:00提示
	}

	private void initview() {
		ApplyButton = (Button) findViewById(R.id.rep_apply_for);
		ResultButton = (Button) findViewById(R.id.rep_result);
		listView_apply = (ListView) findViewById(R.id.listview1);
		listView_result = (ListView) findViewById(R.id.listview2);
		ApplyButton.setOnClickListener(this);
		ResultButton.setOnClickListener(this);
		adapter = new AdanceRepaymentAdapter(ApplyList, AdvanceRepayment.this);
		adapter2 = new AdanceRepaymentAdapter2(ResultList,
				AdvanceRepayment.this);
		listView_apply.setAdapter(adapter);
		listView_result.setAdapter(adapter2);
		//返回back
		back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		 pic_null=(ImageView) findViewById(R.id.pic_null);
		// pic_null.setBackgroundResource(R.drawable.advance_null);
		//
		// listView_apply.setPullLoadEnable(true);
		// listView_apply.setXListViewListener(this);
		// listView_result.setPullLoadEnable(true);
		// listView_result.setXListViewListener(this);

		//测试  new GetAdvanceHome().execute("130821198207207078", "0");
		//生产

		String idNo = "";
		if(Contents.response != null && Contents.response.getResult() != null)
			idNo = Contents.response.getResult().getIdNo();
		 new GetAdvanceHome().execute(idNo, "0");
//		
		// 130821198207207078 110225196809305011
		
		/*
		 * ========================== 
		 * listView_apply申请页面的list
		 * listView_result结果页面的list setOnItemClickListener 事件
		 * =========================
		 */

		listView_apply.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				intent.setClass(AdvanceRepayment.this, AdvanceRepayment3.class);
				// intent.putExtra("list", ApplyList.get(position - 1));
				intent.putExtra("list", ApplyList.get(position));
				startActivity(intent);
			}
		});

		listView_result.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				intent.setClass(AdvanceRepayment.this,
						AdvanceRepaymentResult2.class);
				intent.putExtra("list_Result", ResultList.get(position));
				startActivity(intent);
			}
		});

	}
	private void initDate(){
		Time t=new Time(); 
		t.setToNow(); // 取得系统时间。   
		int hour = t.hour; // 0-23   
		System.out.println("现在的时间是："+t);
		System.out.println("时间hour==>"+hour);
		//8:00-16:00正常时间
		if(hour<8||hour>15){
			DialogUtil.showDialogOneButton2(AdvanceRepayment.this, "极速贷的还款业务受理时间为法定工作日的8：00-16:00，请在该时间提交申请，超出该时间段提交会影响您的审批结果。");
			}
		}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		if (v.getId() == R.id.rep_result) {
			 type="Tp_Result";
			// 获取Result页数据=======================================
			pic_null.setVisibility(View.GONE);
			ApplyButton.setTextColor(Color.parseColor("#595959"));
			ResultButton.setTextColor(Color.parseColor("#ffffff"));
			listView_apply.setVisibility(View.GONE);
			listView_result.setVisibility(View.VISIBLE);
			// pic_null.setVisibility(View.GONE);
			ResultButton.setBackgroundResource(R.drawable.title_right_selector);
			ApplyButton.setBackgroundResource(R.drawable.title_left_normal);
			//测试	
		//	new GetAdvanceResult().execute("230503198905150210", "1");
			String idNo = "";
			if(Contents.response != null && Contents.response.getResult() != null)
				idNo = Contents.response.getResult().getIdNo();
			new GetAdvanceResult().execute(idNo, "1");
			System.out.println("结果页面");
			if (ResultList.size() > 0) {
				listView_apply.setVisibility(View.GONE);
				listView_result.setVisibility(View.VISIBLE);
				 pic_null.setVisibility(View.GONE);
				adapter2 = new AdanceRepaymentAdapter2(ResultList,
						AdvanceRepayment.this);
				listView_result.setAdapter(adapter2);
			}
			else if(ResultList.size()==0) {
				// pic_null.setVisibility(View.VISIBLE);
				 listView_apply.setVisibility(View.GONE);
				 listView_result.setVisibility(View.GONE);
				 pic_null.setBackgroundResource(R.drawable.advance_null);
				 
			 }

		}
		if (v.getId() == R.id.rep_apply_for) {
			 type="Tp_Apple";
			// 获取申请页数据=======================================
			ApplyButton.setTextColor(Color.parseColor("#ffffff"));
			ResultButton.setTextColor(Color.parseColor("#595959"));
			listView_apply.setVisibility(View.VISIBLE);
			listView_result.setVisibility(View.GONE);
			 pic_null.setVisibility(View.GONE);
			ApplyButton.setBackgroundResource(R.drawable.title_left_selector);
			ResultButton.setBackgroundResource(R.drawable.title_right_normal);
 		//生产
			String idNo = "";
			if(Contents.response != null && Contents.response.getResult() != null)
				idNo = Contents.response.getResult().getIdNo();
			new GetAdvanceHome().execute(idNo, "0");
	//测试  new GetAdvanceHome().execute("130821198207207078", "0");
			System.out.println("申请页面");
			if (ApplyList.size() > 0) {
				listView_apply.setVisibility(View.VISIBLE);
				listView_result.setVisibility(View.GONE);
				pic_null.setVisibility(View.GONE); 
				adapter = new AdanceRepaymentAdapter(ApplyList,
						AdvanceRepayment.this);
				listView_apply.setAdapter(adapter);
			}else if(ApplyList.size()==0) {
				// pic_null.setVisibility(View.VISIBLE);
				 listView_apply.setVisibility(View.GONE);
				 listView_result.setVisibility(View.GONE);
				 pic_null.setBackgroundResource(R.drawable.advance_null);
				 
			 }
		}
	}

	// =========================Result页网咯数据交互=========================================
	class GetAdvanceResult extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {
			// TODO 自动生成的方法存根
			HttpHelper mBobcfcHttpClient = HttpHelper
					.getInstance(AdvanceRepayment.this);
			String url = ContantsAddress.ADVANCEREPAYMENT;
			List<NameValuePair> param = new ArrayList<NameValuePair>();
			// 请求参数
			param.add(new BasicNameValuePair("idNo", params[0]));
			param.add(new BasicNameValuePair("status", params[1]));
			Class<AdvanceRepaymentResultAllResult> clazz = AdvanceRepaymentResultAllResult.class;
			AdvanceRepaymentResultAllResult response = mBobcfcHttpClient.post(
					url, param, clazz);
			return response;

		}

		@Override
		protected void onPostExecute(Object result) {
			// TODO 自动生成的方法存根
			super.onPostExecute(result);
			dismissProgressDialog();
			AdvanceRepaymentResultAllResult response = (AdvanceRepaymentResultAllResult) result;
			if("Tp_Result".equals(type)){
			if (response != null) {
				System.out.println("结果页++》》" + response.getCode());
				if (response.getCode() == 0) {
					 System.out.println("code==>" + response.getCode());
					 ResultList = response.getResult();
					 if (ResultList.size() > 0) {
						 listView_apply.setVisibility(View.GONE);
						 listView_result.setVisibility(View.VISIBLE);
						 pic_null.setVisibility(View.GONE);
						 adapter2 = new AdanceRepaymentAdapter2(ResultList,
								 AdvanceRepayment.this);
						 listView_result.setAdapter(adapter2);
					 }else if(ResultList.size()==0) {
						 pic_null.setVisibility(View.VISIBLE);
						 listView_apply.setVisibility(View.GONE);
						 listView_result.setVisibility(View.GONE);
						 pic_null.setBackgroundResource(R.drawable.advance_null);
						 
					 }
//					 listView_apply.setVisibility(View.VISIBLE);
//					 listView_apply.setVisibility(View.GONE);
//					 listView_result.setVisibility(View.VISIBLE);

					// pic_null.setVisibility(View.VISIBLE);

				} else {
					Toast.makeText(AdvanceRepayment.this, response.getMsg(), Toast.LENGTH_LONG)
							.show();
				}
			}else{

				listView_apply.setVisibility(View.GONE);
				pic_null.setVisibility(View.GONE);
				listView_result.setVisibility(View.GONE);
				Toast.makeText(
						AdvanceRepayment.this,
						getResources().getString(R.string.no_network),
						Toast.LENGTH_LONG).show();
			
			}
			}
		}
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			if ("Tp_Result".equals(type)) {
				System.out.println("ResultList.size()+++>"+ResultList.size());
				if (ResultList.size() > 0) {
				} else {
					showProgressDialog();
				}
			} 


		}

	}

	// --==========================Home网咯交互数据===========================================
	class GetAdvanceHome extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {
			HttpHelper mBobcfcHttpClient = HttpHelper
					.getInstance(AdvanceRepayment.this);
			String url = ContantsAddress.ADVANCEREPAYMENT;
			List<NameValuePair> param = new ArrayList<NameValuePair>();
			param.add(new BasicNameValuePair("idNo", params[0]));
			param.add(new BasicNameValuePair("status", params[1]));
			// param.add(new BasicNameValuePair("beginPos", params[2]));
			// param.add(new BasicNameValuePair("number", params[3]));
			// param.add(new BasicNameValuePair("queryFlag", params[4]));
			// param.add(new BasicNameValuePair("txType", params[5]));
			Class<AdvanceRepaymentResult> clazz = AdvanceRepaymentResult.class;
			AdvanceRepaymentResult response = mBobcfcHttpClient.post(url,
					param, clazz);
			return response;

		}

		@Override
		protected void onPostExecute(Object result) {
			// TODO 自动生成的方法存根
			super.onPostExecute(result);
			dismissProgressDialog();
			AdvanceRepaymentResult response = (AdvanceRepaymentResult) result;
		if("Tp_Apple".equals(type)){
			if (response != null) {
				System.out.println("申请页面++》》" + response.getCode());
				if (response.getCode() == 0) {
					System.out.println("code==>" + response.getCode());
					ApplyList = response.getResult();
					if (ApplyList.size() > 0) {
						listView_apply.setVisibility(View.VISIBLE);
						listView_result.setVisibility(View.GONE);
						 pic_null.setVisibility(View.GONE);
						adapter = new AdanceRepaymentAdapter(ApplyList,
								AdvanceRepayment.this);
						listView_apply.setAdapter(adapter);
					}
					else if(ApplyList.size()==0) {
						 pic_null.setVisibility(View.VISIBLE);
						 listView_apply.setVisibility(View.GONE);
						 listView_result.setVisibility(View.GONE);
						 pic_null.setBackgroundResource(R.drawable.advance_null);
						 
					 }
				} else {
					Toast.makeText(AdvanceRepayment.this, response.getMsg(), Toast.LENGTH_LONG)
							.show();
				}
			}else{

				pic_null.setVisibility(View.GONE);
				listView_apply.setVisibility(View.GONE);
				listView_result.setVisibility(View.GONE);
				Toast.makeText(
						AdvanceRepayment.this,
						getResources().getString(R.string.no_network),
						Toast.LENGTH_LONG).show();
			
			}
		}
		}
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			if ("Tp_Apple".equals(type)) {
				if (ApplyList.size() > 0) {
				} else {
					showProgressDialog();
				}
			} 
	}
	}
	/*
	 * @Override public void onRefresh() { // TODO 自动生成的方法存根
	 * 
	 * }
	 * 
	 * @Override public void onLoadMore() { // TODO 自动生成的方法存根
	 * 
	 * }
	 */
	public boolean onKeyDown(int keyCode,KeyEvent event) {
		//这里重写返回键
		 if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
				Intent it = new Intent(AdvanceRepayment.this,
						HomeActivity.class);
				startActivity(it);
		return true;
		}
		 return false;
		}
}
