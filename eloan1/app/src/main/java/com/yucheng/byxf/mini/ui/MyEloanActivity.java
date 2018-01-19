package com.yucheng.byxf.mini.ui;

import com.yucheng.byxf.mini.utils.Contents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * 类名: MyEloanActivity</br> 描述: 申请进度</br> 开发人员： weiyb</br> 创建时间： 2015-7-17
 */

public class MyEloanActivity extends BaseActivity {
	private RelativeLayout my_jindu;
	private RelativeLayout my_huankuan;
	private RelativeLayout my_zhangdan;
	private RelativeLayout my_minizhangdan;
	private RelativeLayout my_messange;
	private ImageView back;
	Intent intent = new Intent();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.my_eloan_activity);
		init();
	}

	private void init() {
		my_jindu = (RelativeLayout) findViewById(R.id.my_jindu);
		my_huankuan = (RelativeLayout) findViewById(R.id.my_huankuan);
		my_zhangdan = (RelativeLayout) findViewById(R.id.my_zhangdan);
		my_minizhangdan = (RelativeLayout) findViewById(R.id.my_minizhangdan);
		my_messange = (RelativeLayout) findViewById(R.id.my_messange);
		back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(this);
		my_jindu.setOnClickListener(this);
		my_huankuan.setOnClickListener(this);
		my_minizhangdan.setOnClickListener(this);
		my_zhangdan.setOnClickListener(this);
		my_messange.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		super.onClick(v);
		switch (v.getId()) {
		case R.id.my_jindu:
			if (Contents.IS_LOGIN) {
				intent.setClass(MyEloanActivity.this,
						ApplicationScheduleQueryHomeActivity.class);
				startActivity(intent);
			} else {
				Toast.makeText(MyEloanActivity.this, "此功能需要登录才能访问！",
						Toast.LENGTH_SHORT).show();
				intent.setClass(MyEloanActivity.this, MyLoginActivity.class);
				intent.putExtra("type", "search");
				startActivity(intent);
				overridePendingTransition(R.anim.lefttoright,
						R.anim.righttoleft);
			}

			break;
		case R.id.my_huankuan:

			if (Contents.IS_LOGIN) {
				intent.setClass(MyEloanActivity.this, AdvanceRepayment.class);
				startActivity(intent);
			} else {
				Toast.makeText(MyEloanActivity.this, "此功能需要登录才能访问！",
						Toast.LENGTH_SHORT).show();
				intent.setClass(MyEloanActivity.this, MyLoginActivity.class);
				intent.putExtra("type", "myeloan");
				startActivity(intent);
				overridePendingTransition(R.anim.lefttoright,
						R.anim.righttoleft);
			}

			break;
		case R.id.my_minizhangdan:
			if (Contents.IS_LOGIN) {
				intent.setClass(MyEloanActivity.this,
						MiniCardMainActivity.class);
				// intent.putExtra("flag", "bill");
				startActivity(intent);
			} else {
				Toast.makeText(MyEloanActivity.this, "此功能需要登录才能访问！",
						Toast.LENGTH_SHORT).show();
				intent.setClass(MyEloanActivity.this, MyLoginActivity.class);
				intent.putExtra("type", "menu");
				startActivity(intent);
				overridePendingTransition(R.anim.lefttoright,
						R.anim.righttoleft);
			}
			break;
		case R.id.my_zhangdan:
			if (Contents.IS_LOGIN) {
				intent.setClass(MyEloanActivity.this, BillNewActivity.class);
				intent.putExtra("flag", "bill");
				startActivity(intent);
			} else {
				Toast.makeText(MyEloanActivity.this, "此功能需要登录才能访问！",
						Toast.LENGTH_SHORT).show();
				intent.setClass(MyEloanActivity.this, MyLoginActivity.class);
				intent.putExtra("type", "bill");
				startActivity(intent);
				overridePendingTransition(R.anim.lefttoright,
						R.anim.righttoleft);
			}
			break;
		case R.id.my_messange://我的消息
			if (Contents.IS_LOGIN) {
				intent.setClass(MyEloanActivity.this, MyMessageActivity.class);
				intent.putExtra("isJPush", false);
//				intent.putExtra("flag", "bill");
				startActivity(intent);
			} else {
				Toast.makeText(MyEloanActivity.this, "此功能需要登录才能访问！",
						Toast.LENGTH_SHORT).show();
				intent.setClass(MyEloanActivity.this, MyLoginActivity.class);
				intent.putExtra("type", "mymessage");
				startActivity(intent);
				overridePendingTransition(R.anim.lefttoright,
						R.anim.righttoleft);
			}
			break;

		case R.id.back:
			intent.setClass(MyEloanActivity.this, HomeActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.lefttoright, R.anim.righttoleft);
			break;
		default:
			break;
		}
	}
}
