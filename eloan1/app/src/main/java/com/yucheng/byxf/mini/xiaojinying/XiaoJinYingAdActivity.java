package com.yucheng.byxf.mini.xiaojinying;

import java.util.ArrayList;
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
import com.yucheng.byxf.mini.message.Response3;
import com.yucheng.byxf.mini.ui.BaseActivity;
import com.yucheng.byxf.mini.ui.HomeActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;

public class XiaoJinYingAdActivity extends BaseActivity {
	private Button btn_next;
	private ImageView im_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.xiaojinyin_ad_activity);

		btn_next = (Button) findViewById(R.id.btn_next);
		im_back = (ImageView) findViewById(R.id.backone);
		btn_next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new XiaoChaxunAsyncTask().execute(Contents.response.getResult()
						.getIdNo());
			}
		});
		im_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent();
				intent.setClass(XiaoJinYingAdActivity.this, HomeActivity.class);
				startActivity(intent);
				finish();
			}
		});
		//
	/*	btn_next.setOnClickListener(new OnClickListener() {
			//
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(XiaoJinYingAdActivity.this,
						XiaoJinYingMenuActivity.class);
				startActivity(intent);
			}

		});*/

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			Intent intent = new Intent();
			intent.setClass(XiaoJinYingAdActivity.this, HomeActivity.class);
			startActivity(intent);
			finish();
			return true;
		}
		return false;
	}

	class XiaoChaxunAsyncTask extends AsyncTask<String, Object, Object> {
		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(XiaoJinYingAdActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("idNo", params[0]));
			return httpHelper.post(ContantsAddress.XianjinyinghuoquKaHao, arg,
					Response3.class);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog();
		}

		@Override
		protected void onPostExecute(Object result) {
			dismissProgressDialog();
			super.onPostExecute(result);
			registerResponse(result);
		}
	}

	private void registerResponse(Object result) {
		Response3 response = (Response3) result;
		if (response == null) {

			DialogUtil.showDialogOneButton2(XiaoJinYingAdActivity.this,
					"服务器请求异常!");
			return;
		} else {
			if (0 == response.getCode()) {
				if (response.getFlag().toString().equals("true")) {
				    //将返回的手机号保存到返回值中
				    String phoneNumber =response.getResult();
				    // 设置  手机号
				    Contents.response.getResult().setCipMobile(phoneNumber);
					Intent intent = new Intent();
					intent.setClass(XiaoJinYingAdActivity.this,
							XiaoJinYingMenuActivity.class);
					startActivity(intent);
					finish();

				} else {
					DialogUtil.showDialogOneButton2(XiaoJinYingAdActivity.this,
							"不存在虚拟卡！");
				}

			} else {
				DialogUtil.showDialogOneButton2(XiaoJinYingAdActivity.this,
						response.getMsg());
			}
		}
	}

}
