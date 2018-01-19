package com.wujay.fund.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yucheng.byxf.mini.ui.BaseActivity;
import com.yucheng.byxf.mini.ui.ForgetPassWord1Activity;
import com.yucheng.byxf.mini.ui.ForgetPassWordActivity;
import com.yucheng.byxf.mini.ui.HomeActivity;
import com.yucheng.byxf.mini.ui.MessageActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.Contents;

public class LockMenuActivoty extends BaseActivity {

	private RelativeLayout amendKey;

	private CheckBox turnOnGesturePassword;

	private TextView tv;

	private RelativeLayout amendGesturePassword;

	private RelativeLayout forgetGesturePassword;

	private SharedPreferences sp0 = null;// 声明一个SharedPreferences

	private String FILE = "saveGesturePwd";// 用于保存SharedPreferences的文件

	private ImageView back0;

	private View xian;

	private View xian2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.lock_menu_activity);

		xian = findViewById(R.id.xian);

		xian2 = findViewById(R.id.xian2);

		tv = (TextView) findViewById(R.id.t22);

		sp0 = getSharedPreferences(FILE, MODE_PRIVATE);

		back0 = (ImageView) findViewById(R.id.back);

		amendKey = (RelativeLayout) findViewById(R.id.xiugaimima);

		amendKey.setOnClickListener(this);

		turnOnGesturePassword = (CheckBox) findViewById(R.id.kaiqishoushi);

		turnOnGesturePassword.setOnClickListener(this);

		amendGesturePassword = (RelativeLayout) findViewById(R.id.xiugaishoushimima);

		amendGesturePassword.setOnClickListener(this);

		forgetGesturePassword = (RelativeLayout) findViewById(R.id.wangjishoushimima);

		forgetGesturePassword.setOnClickListener(this);

		// 给CheckBox设置事件监听

		if (sp0.getString("isTurnOn", "").equals("YES")) {

			tv.setText("手势密码 已开启");

			turnOnGesturePassword.setChecked(true);

			xian.setVisibility(View.VISIBLE);

			xian2.setVisibility(View.VISIBLE);

			amendGesturePassword.setVisibility(View.VISIBLE);

			forgetGesturePassword.setVisibility(View.VISIBLE);

		} else {

			tv.setText("手势密码 已关闭");

			turnOnGesturePassword.setChecked(false);

			xian.setVisibility(View.GONE);

			xian2.setVisibility(View.GONE);

			amendGesturePassword.setVisibility(View.GONE);

			forgetGesturePassword.setVisibility(View.GONE);

		}

		back0.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

			Intent i=new Intent();
			i.setClass(LockMenuActivoty.this, HomeActivity.class);
			startActivity(i);
			finish();
			}

		});

		turnOnGesturePassword

				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,

					boolean isChecked) {

						// TODO Auto-generated method stub

						if (isChecked) {

							
							Contents.IS_LocStart=0;
						 
							tv.setText("手势密码 已开启");

							String Key = "";

							sp0 = getSharedPreferences(FILE, MODE_PRIVATE);

							Key = sp0.getString("Pwd", "");

							if (Key == null || "".equals(Key)) {

								Intent it = new Intent();

								it.setClass(LockMenuActivoty.this,

								GestureEditActivity.class);

								startActivity(it);

							}

							Editor edit = sp0.edit();

							edit.putString("isTurnOn", "YES");

							edit.commit();

							xian.setVisibility(View.VISIBLE);

							xian2.setVisibility(View.VISIBLE);

							amendGesturePassword.setVisibility(View.VISIBLE);

							forgetGesturePassword.setVisibility(View.VISIBLE);

						} else {
							Contents.IS_LocStart=0;

							Editor edit = sp0.edit();

							edit.putString("isTurnOn", "NO");

							edit.commit();

							tv.setText("手势密码 已关闭");

							amendGesturePassword.setVisibility(View.GONE);

							forgetGesturePassword.setVisibility(View.GONE);

							xian.setVisibility(View.GONE);

							xian2.setVisibility(View.GONE);

						}

					}

				});

	}

	@Override
	public void onClick(View v) {

		super.onClick(v);

		switch (v.getId()) {

		case R.id.xiugaimima:

			// 修改密码Verify====》原先密码修改

			Intent it2 = new Intent();

			it2.setClass(LockMenuActivoty.this, ForgetPassWordActivity.class);

			startActivity(it2);

			break;

		case R.id.xiugaishoushimima:

			// 修改手势密码

			// 去密码 若对了--》设定密码

			Intent it = new Intent();

			it.putExtra("type", 0);

			it.setClass(LockMenuActivoty.this, GestureVerifyActivity.class);

			startActivity(it);

			break;

		case R.id.wangjishoushimima:

			// 去登陆页面登陆 ==》成功清空手势密码

			// 忘记手势密码 登陆

			Intent it3 = new Intent();

			it3.setClass(LockMenuActivoty.this, PassLoginActivoty.class);

			startActivity(it3);

			break;

		default:

			break;

		}

	}

	@Override
	protected void onResume() {

		// TODO Auto-generated method stub

		super.onResume();

		if (sp0.getString("isTurnOn", "").equals("YES")) {

			turnOnGesturePassword.setChecked(true);

			amendGesturePassword.setVisibility(View.VISIBLE);

			forgetGesturePassword.setVisibility(View.VISIBLE);

		} else {

			turnOnGesturePassword.setChecked(false);

			amendGesturePassword.setVisibility(View.GONE);

			forgetGesturePassword.setVisibility(View.GONE);

		}

	}

}
