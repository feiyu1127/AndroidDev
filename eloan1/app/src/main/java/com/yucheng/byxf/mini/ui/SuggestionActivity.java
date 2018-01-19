package com.yucheng.byxf.mini.ui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.adapter.AdanceRepaymentAdapter;
import com.yucheng.byxf.mini.adapter.SuggestionAdapter;
import com.yucheng.byxf.mini.message.AdvanceRepaymentResult;
import com.yucheng.byxf.mini.message.SuggestionMsgEntity;
import com.yucheng.byxf.mini.message.SuggestionMsgResult;
import com.yucheng.byxf.mini.ui.AdvanceRepayment.GetAdvanceHome;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SuggestionActivity extends BaseActivity {

	private Button mBt_next;
	private EditText mEt_Msg;
	private TextView mTx_num;
	private ImageView back;
	private static final int MAX_COUNT = 200;  
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.suggestion_activity);
		initview();
}
	
	private void initview(){
		//返回按钮
		back=(ImageView) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
			finish();
			}
		});
		mBt_next=(Button) findViewById(R.id.bt_next);
		mBt_next.setOnClickListener(this);
		mEt_Msg=(EditText) findViewById(R.id.help_feedback);
		mEt_Msg.setOnClickListener(this);
		mTx_num=(TextView) findViewById(R.id.testnum);
		
		mEt_Msg.addTextChangedListener(mWatcher);
		mEt_Msg.setSelection(mEt_Msg.length());
		
	}
	
	private TextWatcher mWatcher =new TextWatcher() {
		    private int editStart;  
	        private int editEnd;
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			
		}
		
		@Override
		public void afterTextChanged(Editable s) {
		   	    editStart = mEt_Msg.getSelectionStart();  
	            editEnd = mEt_Msg.getSelectionEnd();  
	            mEt_Msg.removeTextChangedListener(mWatcher);  
	            while (calculateLength(s.toString()) > MAX_COUNT) { // 当输入字符个数超过限制的大小时，进行截断操作  
	                s.delete(editStart - 1, editEnd);  
	                editStart--;  
	                editEnd--;  
	            }  
	            mEt_Msg.setSelection(editStart);  
	            mEt_Msg.addTextChangedListener(mWatcher);  
	            setLeftCount();  
		}
	};
	
	  private long calculateLength(CharSequence c) {  
	        double len = 0;  
	        for (int i = 0; i < c.length(); i++) {  
	            int tmp = (int) c.charAt(i);  
	            if (tmp > 0 && tmp < 127) {  
	                len += 0.5;  
	            } else {  
	                len++;  
	            }  
	        }  
	        return Math.round(len);  
	    }  
   private void setLeftCount() {  
	   mTx_num.setText("还可以再输入"+String.valueOf((MAX_COUNT - getInputCount()))+"字");  
   }  
   private long getInputCount() {  
       return calculateLength(mEt_Msg.getText().toString());  
   }  
   
   
	public void onClick(View view) {
		switch(view.getId()) {
	//发送按钮
		
		case R.id.bt_next:
			int i=mEt_Msg.length();
			if(i<1){
				Toast.makeText(
						SuggestionActivity.this,
						"请输入反馈信息",
						Toast.LENGTH_LONG).show();
			}else{
				String idNo = "", cipMob = "";
				if(Contents.response != null && Contents.response.getResult() != null) {
					idNo = Contents.response.getResult().getIdNo();
					cipMob = Contents.response.getResult().getCipMobile();
				}
			new SendGetSuggestionMsg().execute(idNo,cipMob,mEt_Msg.getText().toString());
		}
			break;
			}
	}

	// ============GetSuggestionMsg网咯交互数据=================
	class SendGetSuggestionMsg extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {
			HttpHelper mBobcfcHttpClient = HttpHelper
					.getInstance(SuggestionActivity.this);
			String url = ContantsAddress.MSGSUGGESTIONINFO;
			List<NameValuePair> param = new ArrayList<NameValuePair>();
			param.add(new BasicNameValuePair("launchName", params[0]));
			param.add(new BasicNameValuePair("phone", params[1]));
			param.add(new BasicNameValuePair("content", params[2]));
			Class<SuggestionMsgResult> clazz = SuggestionMsgResult.class;
			SuggestionMsgResult response = mBobcfcHttpClient.post(url,
					param, clazz);
			return response;

		}

		@Override
		protected void onPostExecute(Object result) {
			// TODO 自动生成的方法存根
			super.onPostExecute(result);
			dismissProgressDialog();
			SuggestionMsgResult response = (SuggestionMsgResult) result;
			if (response != null) {
				if (response.getCode() == 0) {
				Intent in=new Intent(SuggestionActivity.this, SuggestionActivity2.class);
				startActivity(in);
				overridePendingTransition(R.anim.lefttoright, R.anim.righttoleft);
				finish();
				}else{
					Toast.makeText(
							SuggestionActivity.this,
							response.getMsg(),
							Toast.LENGTH_LONG).show();
				}
				
			}else{
				Toast.makeText(
						SuggestionActivity.this,
						getResources().getString(R.string.no_network),
						Toast.LENGTH_LONG).show();
		
			}	
				}
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
					showProgressDialog();
	  }
	}

}
