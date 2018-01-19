package com.yucheng.byxf.mini.ui;

import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.MyInOutAnimation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ConcernOfLoveActivity extends BaseActivity implements
		OnClickListener {

	boolean isFlag = true;
	private LinearLayout layout;

	private Button miniButton;
	private Button generalButton;
	private Button button;
	private RelativeLayout backLayout;
	
	private ImageView back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.concern_of_love_activity);
		back = (ImageView) findViewById(R.id.back);
		miniButton = (Button) findViewById(R.id.mini_button);
		generalButton = (Button) findViewById(R.id.common_button);

		layout = (LinearLayout) findViewById(R.id.layout2);
		button = (Button) findViewById(R.id.button);

		backLayout = (RelativeLayout) findViewById(R.id.backLayout);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (isFlag) {
					MyInOutAnimation.startAnimationsIn(layout);
					backLayout.setVisibility(View.VISIBLE);
				} else {
					MyInOutAnimation.startAnimationsOut(layout);
					backLayout.setVisibility(View.GONE);
				}
				isFlag = !isFlag;
			}
		});

		backLayout.setOnClickListener(this);
		miniButton.setOnClickListener(this);
		generalButton.setOnClickListener(this);
		back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.backLayout:
			MyInOutAnimation.startAnimationsOut(layout);
			backLayout.setVisibility(View.GONE);
			isFlag = !isFlag;
			break;
		case R.id.mini_button:
			// if (Contents.IS_LOGIN) {
			// intent.setClass(ProductIntroduceELoanActivity.this,
			// MiniRiskPromptLoanActivity.class);
			// startActivity(intent);
			// } else {
			// Toast.makeText(ProductIntroduceELoanActivity.this,
			// "此功能需要登录才能访问！", Toast.LENGTH_SHORT).show();
			// intent.setClass(ProductIntroduceELoanActivity.this,
			// MyLoginActivity.class);
			// intent.putExtra("type", "xunhuan");
			// startActivity(intent);
			// }
			// finish();
			Toast.makeText(this, "此功能正在开发中，敬请期待", Toast.LENGTH_LONG).show();
			MyInOutAnimation.startAnimationsOut(layout);
			backLayout.setVisibility(View.GONE);
			isFlag = !isFlag;
			break;
		case R.id.common_button:
			if (Contents.IS_LOGIN) {
				intent.setClass(ConcernOfLoveActivity.this,
						GeneralConsumeLoanActivity.class);
				startActivity(intent);
			} else {
				Toast.makeText(ConcernOfLoveActivity.this, "此功能需要登录才能访问！",
						Toast.LENGTH_SHORT).show();
				intent.setClass(ConcernOfLoveActivity.this,
						MyLoginActivity.class);
				intent.putExtra("type", "common");
				startActivity(intent);
			}
			finish();
			break;
		case R.id.back:
			finish();
			break;
		default:
			break;
		}
	}
}
