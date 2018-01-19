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

public class SuggestionActivity2 extends BaseActivity {

	private Button mBt_next;
	private ImageView back;
	private static final int MAX_COUNT = 200;  
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.suggestion_activity2);
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
		mBt_next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				finish();
			}
		});
		
	}
	
	
   
   


}
