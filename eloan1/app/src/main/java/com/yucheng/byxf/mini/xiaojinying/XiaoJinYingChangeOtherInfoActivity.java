package com.yucheng.byxf.mini.xiaojinying;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yucheng.byxf.mini.ui.BaseActivity;
import com.yucheng.byxf.mini.ui.R;

/**
 * 类名: XiaoJinYingChangeOtherInfoActivity</br> 描述: 变更其他信息 </br> 开发人员： weiyb</br>
 * 创建时间： 2015-7-24
 */

public class XiaoJinYingChangeOtherInfoActivity extends BaseActivity {

	private RelativeLayout re_1;
	private RelativeLayout re_2;
	private RelativeLayout re_3;
	private ImageView back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.xiaojinyin_changother_activity);

		re_1 = (RelativeLayout) findViewById(R.id.jibenxinxi);
		re_2 = (RelativeLayout) findViewById(R.id.dizhixinxi);
		re_3 = (RelativeLayout) findViewById(R.id.lianxirenxinxi);
		re_1.setOnClickListener(this);
		re_2.setOnClickListener(this);
		re_3.setOnClickListener(this);
		back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.jibenxinxi:
			Intent i = new Intent();
			i.setClass(XiaoJinYingChangeOtherInfoActivity.this,
					XiaoJinYingChangeOtherInfoActivity_1.class);
			startActivity(i);

			break;

		case R.id.dizhixinxi:
			Intent i2 = new Intent();
			i2.setClass(XiaoJinYingChangeOtherInfoActivity.this,
					XiaoJinYingChangeOtherInfoActivity_2.class);
			startActivity(i2);
			break;
		case R.id.lianxirenxinxi:
			Intent i3 = new Intent();
			i3.setClass(XiaoJinYingChangeOtherInfoActivity.this,
					XiaoJinYingContactsActivity.class);
			startActivity(i3);
			break;
		case R.id.back:
			Intent i4 = new Intent();
			i4.setClass(XiaoJinYingChangeOtherInfoActivity.this,
					XiaoJinYingMenuActivity.class);
			startActivity(i4);
			finish();
			break;
		default:
			break;
		}

	}

}
