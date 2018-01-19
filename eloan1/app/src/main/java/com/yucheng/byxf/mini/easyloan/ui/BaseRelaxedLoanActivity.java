package com.yucheng.byxf.mini.easyloan.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yucheng.byxf.comm.ScreenManager;
import com.yucheng.byxf.mini.map.DemoApplication;
import com.yucheng.byxf.mini.message.CustInfoResponseResult;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.views.ProgressView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BaseRelaxedLoanActivity extends Activity implements
		OnClickListener, OnItemSelectedListener {
	private ScreenManager screenManager;
	public TextView easy_loan_head;
	public Button bt_back_common;
	private static final Integer RET_CODE = 0;
	private static final int PROGRESS_DIALOG_ID = 0;
	private CustInfoResponseResult custInfoResponseResult;
	private Gson gson;
	private GsonBuilder gsonBuilder;
	protected DemoApplication application = null;

	private static View view;

	private int time = 10;
	
	private Context context;
	protected SharedPreferencesUtils preferencesUtils;
	
	private Dialog baseDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = this;
		init();
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		screenManager = ScreenManager.getScreenManager();
		screenManager.pushActivity(this);
		
		downLoadData();
	}

	protected void downLoadData() {
		// TODO Auto-generated method stub
	}

	protected void init() {
		// TODO Auto-generated method stub
		preferencesUtils = new SharedPreferencesUtils(this);
		easy_loan_head = (TextView) findViewById(R.id.easy_loan_head);
		bt_back_common = (Button) findViewById(R.id.bt_back_common);
		bt_back_common.setOnClickListener(this);
		gsonBuilder = new GsonBuilder();
		gson = gsonBuilder.create();
		application = ((DemoApplication) getApplicationContext());
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:// 表示返回
//			closeView();
//			if(view!=null){
//				view.setVisibility(View.GONE);
//				view = null;
//				return false;
//			if(view!=null){
//				return true;
//			}else {
				destroyActivity();
				
//			}
			break;
		}
		return true;
	}
	
	public boolean viewIsClose(){
		if (view == null) {
			return true;
		}
		return false;
	}
	
	public void destroyActivity() {
		removeDialog(PROGRESS_DIALOG_ID);
		screenManager.popActivity(this);
		System.gc();
	}
	
	private void timer() {
		new Thread() {
			public void run() {
				while (time > 0) {
					time--;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				handler.sendEmptyMessage(1);
			};
		}.start();
	}
	
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 1) {
				if (view != null) {
					Toast.makeText(context, "网络请求超时", Toast.LENGTH_LONG).show();
					dismissProgressDialog();
				}
				
			}
		};
	};

	
	private boolean createView() {
		boolean flag = false;
		WindowManager wm = (WindowManager) getApplicationContext()
				.getSystemService(Context.WINDOW_SERVICE);
		WindowManager.LayoutParams wmParams = new LayoutParams();
		view = LayoutInflater.from(this).inflate(
				R.layout.common_progress_layout, null);
		ImageView imageView = (ImageView) view
				.findViewById(R.id.comm_progress_iv_anim);// 加载布局
		// // 加载动画
		AnimationDrawable animation = (AnimationDrawable) imageView
				.getBackground();
		// 使用ImageView显示动画
		animation.start();
		/**
		 * 设置WindowManager.LayoutParams的相关属性，具体的用途参考SDK文档
		 */
		wmParams.type = 2002;
		wmParams.format = 1;
		wmParams.flags = 40;
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int wight = metrics.widthPixels;
		int height = metrics.heightPixels;
		float density = metrics.density;
		// 屏幕的分辨率等于（360,640）与density的乘积
		wmParams.width = (int) (wight + 0.5f);
		wmParams.height = (int) (height + 0.5f);
		wm.addView(view, wmParams);
		flag = true;
		time = 10;
//		timer();
		return flag;
	}

	private boolean closeView() {
		if (view != null) {
			view.setVisibility(View.GONE);
			view = null;
		}
		return true;
	}


	protected boolean showProgressDialog() {
		boolean isSuccess = false;
//		isSuccess = createView();
		baseDialog = ProgressView.createLoadingDialog(this);
		baseDialog.show();
		baseDialog.setCanceledOnTouchOutside(false);
		baseDialog.setCancelable(false);
		isSuccess = true;
		return isSuccess;
	}

	protected void dismissProgressDialog() {
//		closeView();
		baseDialog.dismiss();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
//		if(view!=null){
//			view.setVisibility(View.GONE);
//			view = null;
//		}else{
			destroyActivity();
//		}
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.easy_loan_btn_agree_button:
//			if (application.getClientInfo() != null) {
				Intent agree_button = getIntent();
				agree_button.setClass(BaseRelaxedLoanActivity.this,
						RelaxedLoanTwoIdentityInfo.class);
				startActivity(agree_button);
//			} else {
//				downLoanOldClientData();
//			}

			break;
		case R.id.easy_loan_identity_next_button:
			Intent identity_info = new Intent(BaseRelaxedLoanActivity.this,
					RelaxedLoanThreeBasicInfo.class);
			startActivity(identity_info);
			break;
		case R.id.easy_loan_basicinfo_next_button:
			Intent basic_info = new Intent(BaseRelaxedLoanActivity.this,
					RelaxedLoanFourProfessionInfo.class);
			startActivity(basic_info);
			break;

		case R.id.easy_loan_profession_info_next_button:
			Intent profession_info = new Intent(BaseRelaxedLoanActivity.this,
					RelaxedLoanFiveAssertDebt.class);
			startActivity(profession_info);
			break;
		case R.id.assert_dept_next_button:
			Intent assert_dept = new Intent(BaseRelaxedLoanActivity.this,
					RelaxedLoanSixApplyLoanInfo.class);
			startActivity(assert_dept);
			break;
		case R.id.apply_info_next_button:
			Intent apply_info = new Intent(BaseRelaxedLoanActivity.this,
					RelaxedLoanSevenContactInfo.class);
			startActivity(apply_info);
			break;
		case R.id.photogragh_info_next_button:
			Intent contact_info = new Intent(BaseRelaxedLoanActivity.this,
					RelaxedLoanEightCertificateInfo.class);
			startActivity(contact_info);
			break;
		case R.id.commit_Info_next_button:
//			Intent commit_Info = new Intent(BaseRelaxedLoanActivity.this,
//					RelaxedLoanNineCommitInfo.class);
//			startActivity(commit_Info);
			break;
		case R.id.easy_loan_btn_disagree_button:
			finish();
			break;
		case R.id.bt_back_common:
			finish();
			break;
		default:
			break;
		}
	}

	protected void downLoanOldClientData() {
		DownJsonData jsonData = new DownJsonData();
		String url = ContantsAddress.CUST_INFO;
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		param.add(new BasicNameValuePair("idType", "20"));
		// 用登录人的身份证去查询老客户信息；
		//对所需信息进行非空判断，
		if(application != null && application.getResponse()!= null
				&& application.getResponse().getResult() != null
				&& application.getResponse().getResult().getIdNo() != null) {
			param.add(new BasicNameValuePair("idNo", application.getResponse()
					.getResult().getIdNo()));
		}//TODO else 应该如何处理空值的情况
		jsonData.jsonDataDownStart(BaseRelaxedLoanActivity.this, url, param);
		jsonData.setDataListener(new OnLoadJsonDataListener() {
			@Override
			public void onFinished(String ret) {
				if (ret != null) {
					custInfoResponseResult = gson.fromJson(ret,
							CustInfoResponseResult.class);

					if (custInfoResponseResult == null) {
						DialogUtil.createDialog(BaseRelaxedLoanActivity.this,
								1, "Json解析数据失败");
						return;
					} else {
						if (RET_CODE.equals(custInfoResponseResult.getCode())) {
							application.setClientInfo(custInfoResponseResult);
//							Toast.makeText(BaseRelaxedLoanActivity.this,
//									"欢迎您回来使用轻松易贷", Toast.LENGTH_LONG).show();
						} else {
							Toast.makeText(BaseRelaxedLoanActivity.this,
									custInfoResponseResult.getMsg(),
									Toast.LENGTH_LONG).show();
						}
						Intent agree_button = getIntent();
						agree_button.setClass(BaseRelaxedLoanActivity.this,
								RelaxedLoanTwoIdentityInfo.class);
						startActivity(agree_button);
					}
				}
			}
		});
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}
}