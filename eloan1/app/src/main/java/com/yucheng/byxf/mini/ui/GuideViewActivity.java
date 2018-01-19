package com.yucheng.byxf.mini.ui;

import android.app.Activity;

import android.os.Bundle;
import java.util.ArrayList;

import com.yucheng.byxf.mini.easyloan.ui.RelaxedLoanEightCertificateInfo;
import com.yucheng.byxf.mini.easyloan.ui.SharedPreferencesUtils;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class GuideViewActivity extends BaseActivity {
	private ViewPager viewPager;
	private ArrayList<View> pageViews;
	private ViewGroup main, group;
	private ImageView imageView;
	private ImageView[] imageViews;
	private Button bt_one;
	protected SharedPreferencesUtils preferencesUtils;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		LayoutInflater inflater = getLayoutInflater();
		pageViews = new ArrayList<View>();
		SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
				this);
		if (preferencesUtils.hasData("login_sign")) {
			// 第2次
			pageViews.add(inflater.inflate(R.layout.splash_one, null));
			pageViews.add(inflater.inflate(R.layout.splash_six, null));
			pageViews.add(inflater.inflate(R.layout.splash_seven, null));
		} else {
			pageViews.add(inflater.inflate(R.layout.splash_one, null));
			pageViews.add(inflater.inflate(R.layout.splash_six, null));
			pageViews.add(inflater.inflate(R.layout.splash_seven, null));
			// pageViews.add(inflater.inflate(R.layout.splash_two, null));
			// pageViews.add(inflater.inflate(R.layout.splash_three, null));
			// pageViews.add(inflater.inflate(R.layout.splash_four, null));
			// pageViews.add(inflater.inflate(R.layout.splash_five, null));

		}
		// pageViews.add(inflater.inflate(R.layout.splash_two, null));
		// pageViews.add(inflater.inflate(R.layout.splash_one, null));
		// pageViews.add(inflater.inflate(R.layout.splash_two, null));
		imageViews = new ImageView[pageViews.size()];
		main = (ViewGroup) inflater.inflate(R.layout.splash_main, null);

		// group = (ViewGroup) main.findViewById(R.id.viewGroup);
		LinearLayout layout = (LinearLayout) main.findViewById(R.id.viewGroup);
		viewPager = (ViewPager) main.findViewById(R.id.guidePages);
		bt_one = (Button) main.findViewById(R.id.bt_one);
		bt_one.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(GuideViewActivity.this,
						HomeActivity.class));
				finish();
			}
		});

		for (int i = 0; i < pageViews.size(); i++) {
			imageView = new ImageView(GuideViewActivity.this);

			LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.FILL_PARENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			layout.setLayoutParams(params2);
			LinearLayout.LayoutParams viewParams = new LinearLayout.LayoutParams(
					15, 15);
			viewParams.setMargins(5, 4, 5, 4);
			// imageView.setLayoutParams(new LayoutParams(150, 150));
			// imageView.setPadding(200, 0, 200, 0);
			imageViews[i] = imageView;
			if (i == 0) {
				imageViews[i].setBackgroundResource(R.drawable.click_pot);
			} else {
				imageViews[i].setBackgroundResource(R.drawable.nomal_port);

			}
			layout.addView(imageViews[i], viewParams);
		}
		// ------------存登陆标志信息
		preferencesUtils.setData("login_sign", "login_sign");
		setContentView(main);
		viewPager.setAdapter(new GuidePageAdapter());
		viewPager.setOnPageChangeListener(new GuidePageChangeListener());
		if (imageViews.length == 1) {
			// 一张图直接显示 按钮
			bt_one.setVisibility(View.VISIBLE);
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
		super.onResume();
	}

	/**
	 * 类似于BaseAdapter�?指引页面Adapter
	 */
	class GuidePageAdapter extends PagerAdapter {
		@Override
		public int getCount() {
			return pageViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public int getItemPosition(Object object) {
			// TODO Auto-generated method stub
			return super.getItemPosition(object);
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			// TODO Auto-generated method stub
			((ViewPager) arg0).removeView(pageViews.get(arg1));
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			// TODO Auto-generated method stub
			((ViewPager) arg0).addView(pageViews.get(arg1));
			return pageViews.get(arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public Parcelable saveState() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void finishUpdate(View arg0) {
			// TODO Auto-generated method stub
		}
	}

	class GuidePageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onPageSelected(int arg0) {
			for (int i = 0; i < imageViews.length; i++) {
				imageViews[arg0].setBackgroundResource(R.drawable.click_pot);
				if (arg0 != i) {
					imageViews[i].setBackgroundResource(R.drawable.nomal_port);
				}
				if ((arg0 + 1) == imageViews.length) {
					System.out.println("最后页-->");
					bt_one.setVisibility(View.VISIBLE);
				}
				System.out.println("arg0==>" + arg0);
				System.out.println("length++>" + imageViews.length);
			}
		}
	}
}