package com.yucheng.byxf.mini.map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.yucheng.byxf.mini.message.SystemNotice;
import com.yucheng.byxf.mini.ui.BaseActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.ContantsAddress;

public class ActWebviewActivity extends BaseActivity {
	private ImageView ima;
	private TextView title;
	private TextView createTime;
	private TextView detial;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.huodong_html);
		title = (TextView) findViewById(R.id.titleM);
		createTime = (TextView) findViewById(R.id.createTimeM);
		detial = (TextView) findViewById(R.id.sysMsgDetialM);
		Intent intent = getIntent();
		SystemNotice systemNotice = (SystemNotice) intent.getSerializableExtra("message");
		ima = (ImageView) findViewById(R.id.backhuodong);
		ima.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		if(systemNotice != null){
			title.setText(IsNull(systemNotice.getTitle()));
			createTime.setText(IsNull(systemNotice.getCreateTime()));
			detial.setText(IsNull(systemNotice.getDetail()));
		}

	}
	
	/**
	 * 判断数据是否为null，为null转换成"",否则正常
	 * @param str
	 * @return
	 */
	private String IsNull(String str){
		return str != null?str : "";
	}
}
