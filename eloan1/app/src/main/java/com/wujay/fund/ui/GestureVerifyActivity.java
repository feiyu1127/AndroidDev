package com.wujay.fund.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.util.EncodingUtils;

 








import com.wujay.fund.widget.FileUtil;
import com.wujay.fund.widget.GestureContentView;
import com.wujay.fund.widget.GestureDrawline.GestureCallBack;
import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.message.Response;
import com.yucheng.byxf.mini.ui.ForgetPassWordActivity;
import com.yucheng.byxf.mini.ui.HomeActivity;
import com.yucheng.byxf.mini.ui.MyLoginActivity;
import com.yucheng.byxf.mini.ui.R;
 
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * 手势绘制/校验界面
 *  
 */
public class GestureVerifyActivity extends Activity implements android.view.View.OnClickListener {
	/** 手机号码*/
	public static final String PARAM_PHONE_NUMBER = "PARAM_PHONE_NUMBER";
	/** 意图 */
	public static final String PARAM_INTENT_CODE = "PARAM_INTENT_CODE";
	private RelativeLayout mTopLayout;
	private TextView mTextTitle;
	private static final Integer RET_CODE = 0;
	private TextView mTextCancel;
	private ImageView mImgUserLogo;
	private TextView mTextPhoneNumber;
	private TextView mTextTip;
	private FrameLayout mGestureContainer;
	private GestureContentView mGestureContentView;
	private TextView mTextForget;
	private TextView mTextOther;
	private String mParamPhoneNumber;
	private long mExitTime = 0;
	private int i =0;
	private int mParamIntentCode;
	private int type =-1;
	private SharedPreferences sp0 = null;// 声明一个SharedPreferences
	private String FILE = "saveGesturePwd";// 用于保存SharedPreferences的文件
	GestureVerifyActivity activity =null;
	private String filename=getSDPath()+File.separator+"zz.txt";
	private String Key="";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gesture_verify);
		i=0;
System.out.println("DD**GestureVerifyActivity==onCreatr");
		//接收intent
		Intent intent = getIntent();
		type = intent.getIntExtra("type",-1);
//		FileUtil fu=new FileUtil();
//		try {
//			Key=fu.readSDFile(filename);
//		} catch (IOException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//			System.out.println("出错了");
//		}
		
		sp0 = getSharedPreferences(FILE, MODE_PRIVATE);
		Key = sp0.getString("Pwd", "");
	
		
		System.out.println("Key="+Key);
		ObtainExtraData();
		setUpViews();
		setUpListeners();

	}
	
	private void ObtainExtraData() {
		mParamPhoneNumber = getIntent().getStringExtra(PARAM_PHONE_NUMBER);
		mParamIntentCode = getIntent().getIntExtra(PARAM_INTENT_CODE, 0);
	}
	
	private void setUpViews() {
		mTopLayout = (RelativeLayout) findViewById(R.id.top_layout);
		mTextTitle = (TextView) findViewById(R.id.text_title);
		mTextCancel = (TextView) findViewById(R.id.text_cancel);
		mImgUserLogo = (ImageView) findViewById(R.id.user_logo);
		mTextPhoneNumber = (TextView) findViewById(R.id.text_phone_number);
		mTextTip = (TextView) findViewById(R.id.text_tip);
		mGestureContainer = (FrameLayout) findViewById(R.id.gesture_container);
		mTextForget = (TextView) findViewById(R.id.text_forget_gesture);
		mTextForget.setOnClickListener(this);
		mTextOther = (TextView) findViewById(R.id.text_other_account);
		
		
		// 初始化一个显示各个点的viewGroup
		mGestureContentView = new GestureContentView(this, true, Key,
				new GestureCallBack() {

					@Override
					public void onGestureCodeInput(String inputCode) {

					}

					@Override
					public void checkedSuccess() {
						mGestureContentView.clearDrawlineState(0L);
						Toast.makeText(GestureVerifyActivity.this, "密码正确", 1000).show();
				 	Contents.IS_LocStart=0;
						if(type==0){
							// 修改密码Verify
							Intent it=new Intent();
			            	it.setClass(GestureVerifyActivity.this, GestureEditActivity.class);
							startActivity(it);
						}
						GestureVerifyActivity.this.finish();
					}

					@Override
					public void checkedFail() {
						i++;
						mGestureContentView.clearDrawlineState(1300L);
						mTextTip.setVisibility(View.VISIBLE);
						mTextTip.setText(Html
								.fromHtml("<font color='#c70c1e'>密码"+i+"次错误</font>"));
						// 左右移动动画
						Animation shakeAnimation = AnimationUtils.loadAnimation(GestureVerifyActivity.this, R.anim.shake);
						mTextTip.startAnimation(shakeAnimation);
					
						if(i>4){
					 
						new LoginAsyncTask().execute(null, null);
							
						}
					}
				});
		// 设置手势解锁显示到哪个布局里面
		mGestureContentView.setParentView(mGestureContainer);
	}
	
	private void setUpListeners() {
		mTextCancel.setOnClickListener(this);
		mTextForget.setOnClickListener(this);
		mTextOther.setOnClickListener(this);
	}
	
	private String getProtectedMobile(String phoneNumber) {
		if (TextUtils.isEmpty(phoneNumber) || phoneNumber.length() < 11) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		builder.append(phoneNumber.subSequence(0,3));
		builder.append("****");
		builder.append(phoneNumber.subSequence(7,11));
		return builder.toString();
	}
	
	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.text_cancel:
			this.finish();
			break;
		case R.id.text_forget_gesture:
			Intent it3 = new Intent();
			it3.setClass(GestureVerifyActivity.this, PassLoginActivoty.class);
			it3.putExtra("type_2","1");
			startActivity(it3);
			break;
				
		default:
			break;
		}
	}
	
	//读sd卡中的 密码
	
	//路径
    public String getSDPath() {  
        File sdDir = null;  
        boolean sdCardExist = Environment.getExternalStorageState().equals(  
                android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在  
        if (sdCardExist) {  
            sdDir = Environment.getExternalStorageDirectory();// 获取跟目录  
        }  
        return sdDir.toString();  
    }  
    public boolean onKeyDown(int keyCode, KeyEvent event) {  
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {  
           
            return true;  
        } else  
            return super.onKeyDown(keyCode, event);  
    }  
    @Override
    protected void onResume() {
    	mTextTip.setVisibility(View.GONE);
    	System.out.println("DD**GestureVerifyActivity==onResume");
    	sp0 = getSharedPreferences(FILE, MODE_PRIVATE);
		Key = sp0.getString("Pwd", "");
		if(Key==null||"".equals(Key)){
			finish();
		}
		
    	if(Contents.IS_LocStart!=1){
    		if(type!=0){
    			i=0;
    		}
    	
		}
    	sp0 = getSharedPreferences(FILE, MODE_PRIVATE);
		Key = sp0.getString("Pwd", "");
		if(Key==null||"".equals(Key)){
			finish();
		}
    	super.onResume();
    }
    
    class LoginAsyncTask extends AsyncTask<String, Object, Object> {
		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper.getInstance(GestureVerifyActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			return httpHelper.post(ContantsAddress.LOGOUT, arg, Response.class);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			 
		}

		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);
			 
				quitResponse(result);
		 
		}
	}

	private void quitResponse(Object result) {
		Response response = (Response) result;
		if (response == null) {
		 
			 
			return;
		} else {
			if (RET_CODE.equals(response.getCode())) {
				Toast.makeText(GestureVerifyActivity.this, "密码输入失败，请重新登录", Toast.LENGTH_LONG)
						.show();
				 
					Intent intent = new Intent();
					intent.setClass(GestureVerifyActivity.this, PassLoginActivoty.class);
					intent.putExtra("type_2","0");
					startActivity(intent);
			} else {
//				loginErrorMessage = response.getMsg();
//				DialogUtil.createDialog(this, 4, loginErrorMessage)、;
			}
		}
	}
}
