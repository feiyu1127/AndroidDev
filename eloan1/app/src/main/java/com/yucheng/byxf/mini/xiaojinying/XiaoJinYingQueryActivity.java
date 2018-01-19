package com.yucheng.byxf.mini.xiaojinying;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yucheng.byxf.mini.ui.BaseActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.NumberUtil;

public class XiaoJinYingQueryActivity extends BaseActivity {
	private Button bt_wodezhangdan;
	private Button bt_mingxichaxun;

	private TextView tv_renminbi;
	private TextView tv_meiyuan;
	private TextView bt_xiangqing;
	private ImageView back;
	/**
	 * 查询页面
	 * 
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xiaojinyin_chaxun_activity);
		bt_wodezhangdan = (Button) findViewById(R.id.btn_zhangdan);
		bt_mingxichaxun = (Button) findViewById(R.id.btn_mingxi);
		back=(ImageView) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 finish();
				
			}
		});
		tv_renminbi = (TextView) findViewById(R.id.tv_renminbi);
		bt_xiangqing = (TextView) findViewById(R.id.bt_xiangqing);
		bt_xiangqing.setOnClickListener(this);

		bt_wodezhangdan.setOnClickListener(this);
		bt_mingxichaxun.setOnClickListener(this);
		if(Contents.xiaoChaXunResult2.getAvailAmt()!=null){
			tv_renminbi.setText("¥ "+String.valueOf(NumberUtil.decimalFormat(Double.valueOf(Contents.xiaoChaXunResult2.getAvailAmt()))));
		}
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_zhangdan:
			Intent it = new Intent();
			it.setClass(XiaoJinYingQueryActivity.this,
					XiaoJinYingZhangDanActivity.class);
			it.putExtra("zhuangtai", "0");
			startActivity(it);
			break;
		case R.id.btn_mingxi:
			Intent it2 = new Intent();
			it2.setClass(XiaoJinYingQueryActivity.this,
					XiaoJinYingQuery2Activity.class);
			startActivity(it2);
			break;

		case R.id.bt_xiangqing:
			Intent it3 = new Intent();
			it3.setClass(XiaoJinYingQueryActivity.this,
					XiaoJinYingQuery3Activity.class);
			startActivity(it3);
			break;
		default:
			break;
		}

	}

}
