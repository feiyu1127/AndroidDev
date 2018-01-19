package com.yucheng.byxf.mini.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.easyloan.ui.RelaxedLoanOneContractBook;
import com.yucheng.byxf.mini.message.ForgetPasswordResult;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.DESUtils;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.DownLoaderBitmap;
import com.yucheng.byxf.mini.utils.OnImageLoadDownListener;
import com.yucheng.byxf.mini.utils.StringUtils;
import com.yucheng.byxf.util.IdcardValidator;
import com.yucheng.byxf.util.RegexUtils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ForgetPassWordActivity extends BaseActivity{
	
	private EditText user_et_id;
	private EditText et_codekey;
	private ImageView updata_view;
	private ImageView code_view;
	private Button bt_next;
	
	private	DownLoaderBitmap download;
	
	private boolean isFlag;
	private String cardNumDES;
	
	String st_userString ;
	String st_code;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rapidly_forget_key);
		initView();
	}
	
	private void initView(){
		
		user_et_id = (EditText) findViewById(R.id.user_et_id);
		et_codekey = (EditText) findViewById(R.id.et_codekey);
		updata_view = (ImageView) findViewById(R.id.updata_view);
		code_view = (ImageView) findViewById(R.id.code_view);
		bt_next = (Button) findViewById(R.id.bt_next);
		isFlag = true;
		download = new DownLoaderBitmap();
		download.downloadStart(this,ContantsAddress.VERIFY_CODE ,0,null);
		download.setOnIamgeLoadDownListener(new OnImageLoadDownListener() {
			
			@Override
			public void OnFinished(Bitmap retBmp, int index) {
				// TODO Auto-generated method stub
				isFlag = true;
				if (null != retBmp) {
					code_view.setImageBitmap(retBmp);
				}else {
					Toast.makeText(ForgetPassWordActivity.this, "网络连接不稳定" , Toast.LENGTH_LONG).show();
				}
			}
		});
		
		setViewListener();
	}
	
	private void setViewListener(){
		updata_view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (isFlag) {
					isFlag = false;
					download.downloadStart(ForgetPassWordActivity.this,ContantsAddress.VERIFY_CODE ,0,null);
					download.setOnIamgeLoadDownListener(new OnImageLoadDownListener() {
						
						@Override
						public void OnFinished(Bitmap retBmp, int index) {
							// TODO Auto-generated method stub
							isFlag = true;
							if (null != retBmp) {
								code_view.setImageBitmap(retBmp);
							}else {
								Toast.makeText(ForgetPassWordActivity.this, "网络连接不稳定" , Toast.LENGTH_LONG).show();
							}
						}
					});
				}
			}
		});
		
		bt_next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				st_userString = user_et_id.getText().toString();
				st_code = et_codekey.getText().toString();
				
				if (StringUtils.isBlank(st_userString)) {
					DialogUtil.showDialogOneButton2(ForgetPassWordActivity.this, "请输入登录账号");
					return;
				}
				if (StringUtils.isBlank(st_code)) {
					DialogUtil.showDialogOneButton2(ForgetPassWordActivity.this, "请输入验证码");
					return;
				}
				if (4!=st_code.length()) {
					DialogUtil.showDialogOneButton2(ForgetPassWordActivity.this, "请输入正确的验证码");
					return;
				}
				IdcardValidator idCardValidator = new IdcardValidator();
				if (!idCardValidator.isValidatedAllIdcard(st_userString)) {
					new GetPhoneAsyncTask().execute(st_userString,st_code);
					System.out.println("st_code===>"+st_code);
				}else {
					try {
						cardNumDES = DESUtils.encryptMode(st_userString,
								ForgetPassWordActivity.this);
						new GetPhoneAsyncTask().execute(cardNumDES,st_code);
						System.out.println("st_code===>"+st_code);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	
	class GetPhoneAsyncTask extends AsyncTask<String, Object, Object> {

    @Override
    protected Object doInBackground(String... params) {
      HttpHelper httpHelper = HttpHelper.getInstance(ForgetPassWordActivity.this);
      List<NameValuePair> arg = new ArrayList<NameValuePair>();
      arg.add(new BasicNameValuePair("cipCsino", params[0]));
      arg.add(new BasicNameValuePair("j_verify_code", params[1]));
      return httpHelper.post(ContantsAddress.GET_PHONE, arg, ForgetPasswordResult.class);
    }

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog();
		}

		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
		}

		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);
			dismissProgressDialog();
			System.out.println(result);
			if (null != result) {
				ForgetPasswordResult response = (ForgetPasswordResult) result;
				if (response.getCode() == 0) {
					if(response.getResult().getCipCstno()==null||response.getResult().getCipCstno().equals("")){
						DialogUtil.showDialogOneButton(ForgetPassWordActivity.this, "您尚未注册此应用，请先注册！", new OnClickListener(){

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								Intent it1 = new Intent(ForgetPassWordActivity.this, RegisterActivity.class);
								startActivity(it1);
								finish();
							}
							
						});
					}else{
					Intent intent = new Intent();
					intent.setClass(ForgetPassWordActivity.this, ForgetPassWord1Activity.class);
					intent.putExtra("phone", response.getResult().getCipMobile());
					if (null == cardNumDES || "".equals(cardNumDES)) {
						intent.putExtra("username", st_userString);
					}else {
						intent.putExtra("username", cardNumDES);
					}
					intent.putExtra("loginuser", response.getResult().getCipCstno());
					startActivity(intent);
					finish();
					}
				}else {
					DialogUtil.showDialogOneButton2(ForgetPassWordActivity.this, response.getMsg());
				}
			}else {
				DialogUtil.showDialogOneButton2(ForgetPassWordActivity.this, "服务器请求异常!");
			}
		}
	}
}
