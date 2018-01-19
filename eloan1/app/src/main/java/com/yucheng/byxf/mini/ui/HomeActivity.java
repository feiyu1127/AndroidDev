package com.yucheng.byxf.mini.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.slidingmenu.lib.SlidingMenu;
import com.wujay.fund.ui.LockMenuActivoty;
import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.apfx.utl.NetworkUtil;
import com.yucheng.byxf.mini.easyloan.ui.RelaxedLoanAdActivity;
import com.yucheng.byxf.mini.map.ActActivity;
import com.yucheng.byxf.mini.map.MapListActivity;
import com.yucheng.byxf.mini.message.BannerResponse;
import com.yucheng.byxf.mini.message.BannerResult;
import com.yucheng.byxf.mini.message.MiniIsOldUserResponse;
import com.yucheng.byxf.mini.message.PersonalApkResponse;
import com.yucheng.byxf.mini.message.Response;
import com.yucheng.byxf.mini.miniLoan.MiniLoanActivity;
import com.yucheng.byxf.mini.utils.ApkUpdate;
import com.yucheng.byxf.mini.utils.ChooseDialog;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.DownLoaderBitmap;
import com.yucheng.byxf.mini.utils.OnImageLoadDownListener;
import com.yucheng.byxf.mini.utils.ViewTools;
import com.yucheng.byxf.mini.xiaojinying.XiaoJinYingAdActivity;

public class HomeActivity extends BaseActivity implements OnClickListener,
		OnTouchListener {
	int width;

	SlidingMenu menu;

	float density;

	Context context = HomeActivity.this;

	ViewPager viewPager;

	ArrayList<ImageView> images = new ArrayList<ImageView>();

	private ArrayList<Bitmap> listBitmap = new ArrayList<Bitmap>();

	private ArrayList<View> dots;

	// private int[] imageId;
	private int currentIndex = -1;

	private int oldPosition = 0;

	View view;

	ScheduledExecutorService scheduledExecutor;

	ViewPagerBaseAdapter imageAdapter;

	int bitmapWidth;

	int height;

	private ImageView menuButton;

	RelativeLayout miniRiskPromptLoanLayout;

	ImageView miniRiskPromptLoanImageView;

	RelativeLayout generalConsumeLoanLayout;

	ImageView generalConsumeLoanImageView;

	RelativeLayout applicationScheduleQueryLayout;

	ImageView applicationScheduleQueryImageView;

	RelativeLayout billLayout;

	ImageView billImageView;

	RelativeLayout productIntroduceLayout;

	ImageView productIntroduceImageView;

	RelativeLayout companyIntroduceLayout;

	ImageView companyIntroduceImageView;

	private RelativeLayout mainLayout, messageLayout, updateLayout, miniLayout,
			aboutusLayout, mapLayout, youhui, actLayout, shareLayout, lock;

	private TextView quit;

	String loginErrorMessage;

	private static final Integer RET_CODE = 0;

	Dialog dialog;

	private TextView username;

	private int flag = 0;

	private TextView tv_update;

	private ImageView update_image;

	ApkUpdate mApkUpdate;

	private PersonalApkResponse apk;

	private ImageView logoImageView;

	private TextView dianTextView;

	private Button consumeLoanButton;

	private Dialog dialog1;

	private HomeActivity activity = null;

	private SDKReceiver mReceiver;

	public static List<BannerResponse> listPicture = new ArrayList<BannerResponse>();

	private boolean isPicture = true;

	private RelativeLayout picture_layout;

	private ImageView picture_imageview;

	private boolean isoldcast = false;

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				viewPager.setCurrentItem(currentIndex);
				break;
			case 2:
				update_image.setVisibility(View.VISIBLE);
				break;
			case 3:
				update_image.setVisibility(View.GONE);
				break;
			case 5:
				new GetAPKAsyncTask().execute();
				break;
			case 6:
				showProgressDialog();
				break;
			case 7:
				dismissProgressDialog();
				break;
			case 10:
				new PictureAsyncTask().execute();
				break;
			case 11:
				docView();
				break;
			}
		}
	};

	private class PagerTask implements Runnable {
		public void run() {
			synchronized (viewPager) {
				currentIndex = (currentIndex + 1) % images.size(); // 当只有两张图片时
				// currentIndex++;// 多张图片时使用
				myHandler.obtainMessage().sendToTarget(); // 通过Handler切换图片
			}
		}
	}

	// 切换当前显示的图片
	private Handler myHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			viewPager.setCurrentItem(currentIndex);// 切换当前显示的图片
		};
	};

	private int logoWidth;

	private int logoHeight;

	private ChooseDialog fengxianDialog;

	public int isFlag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_layout);

		activity = new HomeActivity();
		fengxianDialog = new ChooseDialog();
		fengxianDialog.createFengxianDialog(this);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		width = displayMetrics.widthPixels;
		height = displayMetrics.heightPixels;
		Contents.width = width;
		Contents.height = height;
		density = this.getResources().getDisplayMetrics().density;
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.brand1);
		height = (width / bitmap.getWidth()) * bitmap.getHeight();
		if (height == 0) {
			height = (bitmap.getWidth() / width) * bitmap.getHeight();
		}
		bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.logo_mini);
		if (width <= 480) {
			logoWidth = (int) (bitmap.getWidth() * 0.8);
			logoHeight = (int) (bitmap.getHeight() * 0.8);
		}
		bitmap.recycle();
		mApkUpdate = new ApkUpdate(HomeActivity.this);
		initView();
		addListener();
		initMenu();
		new Thread() {
			public void run() {
				Looper.prepare();
				// if (!NetworkUtil.check(HomeActivity.this)) {
				// // Toast.makeText(HomeActivity.this, "网络未连接，请重试",
				// // Toast.LENGTH_SHORT).show();
				// } else {
				ApkUpdate mApkUpdate = new ApkUpdate(HomeActivity.this);
				Message msg = handler.obtainMessage();
				// 检测版本
				isFlag = mApkUpdate.checkNewVersionAndTap(HomeActivity.this);
				if (isFlag == 1 || isFlag == 2) {
					msg.what = 2;
				} else {
					msg.what = 3;
				}
				handler.sendEmptyMessage(10);
				handler.sendMessage(msg);
				// }
				Looper.loop();
			};
		}.start();
		// 注册 SDK 广播监听者
		IntentFilter iFilter = new IntentFilter();
		iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
		iFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
		mReceiver = new SDKReceiver();
		registerReceiver(mReceiver, iFilter);

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		activity = null;
		// 取消监听 SDK 广播
		unregisterReceiver(mReceiver);
	}

	private void initMenu() {
		// TODO Auto-generated method stub
		menu = new SlidingMenu(context);
		// 设置Menu属性
		menu.setMode(SlidingMenu.LEFT);// 设置菜单位置 LEFT_RIGHT为左右格式，LEFT为左侧格式
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);// 设置菜单滑动方式
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.yinying);// 菜单滑动是阴影部分
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.setBehindWidth(width / 3 * 2);// 菜单宽度
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		menu.setMenu(R.layout.home_menu_layout);// 添加菜单
		menu.addIgnoredView(viewPager);
		// 设置右侧菜单
		// menu.setSecondaryMenu(R.layout.home_menu_layout);
		// menu.setSecondaryShadowDrawable(R.drawable.shadow);
		// menu.setShadowDrawable(R.drawable.shadow);
		actLayout = (RelativeLayout) findViewById(R.id.actLayout);// 活动
		mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);
		messageLayout = (RelativeLayout) findViewById(R.id.messageLayout);
		updateLayout = (RelativeLayout) findViewById(R.id.updateLayout);
		miniLayout = (RelativeLayout) findViewById(R.id.miniLayout);
		aboutusLayout = (RelativeLayout) findViewById(R.id.aboutusLayout);
		youhui = (RelativeLayout) findViewById(R.id.youhui);
		shareLayout = (RelativeLayout) findViewById(R.id.share);
		lock = (RelativeLayout) findViewById(R.id.lock);
		mapLayout = (RelativeLayout) findViewById(R.id.mapLayout);
		quit = (TextView) findViewById(R.id.quit);
		tv_update = (TextView) findViewById(R.id.tv_update);
		tv_update.setText("版本更新");
		update_image = (ImageView) findViewById(R.id.update_image);
		mainLayout.setOnClickListener(this);
		messageLayout.setOnClickListener(this);
		updateLayout.setOnClickListener(this);
		miniLayout.setOnClickListener(this);
		aboutusLayout.setOnClickListener(this);
		youhui.setOnClickListener(this);
		shareLayout.setOnClickListener(this);
		quit.setOnClickListener(this);
		mapLayout.setOnClickListener(this);
		lock.setOnClickListener(this);
		actLayout.setOnClickListener(this);
	}

	@SuppressWarnings("deprecation")
	private void initView() {
		consumeLoanButton = (Button) findViewById(R.id.consumeloanButton);
		logoImageView = (ImageView) findViewById(R.id.logoImage);
		picture_imageview = (ImageView) findViewById(R.id.picture_imageview);
		picture_layout = (RelativeLayout) findViewById(R.id.picture_layout);
		// picture_layout.setVisibility(view.GONE);
		// picture_imageview.setVisibility(view.VISIBLE);
		miniRiskPromptLoanLayout = (RelativeLayout) findViewById(R.id.xunhuan);
		generalConsumeLoanLayout = (RelativeLayout) findViewById(R.id.common);
		// 申请进度---》改为mini循环贷
		applicationScheduleQueryLayout = (RelativeLayout) findViewById(R.id.search);
		// 账单介绍---》改为循环时贷
		billLayout = (RelativeLayout) findViewById(R.id.bill);
		// 产品介绍--》改为我的贷款
		productIntroduceLayout = (RelativeLayout) findViewById(R.id.chanpin);
		// 公司介绍---》改为产品介绍
		companyIntroduceLayout = (RelativeLayout) findViewById(R.id.company);
		username = (TextView) findViewById(R.id.username);
		dianTextView = (TextView) findViewById(R.id.dian);
		miniRiskPromptLoanImageView = (ImageView) findViewById(R.id.xunhuan_log);
		generalConsumeLoanImageView = (ImageView) findViewById(R.id.common_log);
		applicationScheduleQueryImageView = (ImageView) findViewById(R.id.search_log);
		billImageView = (ImageView) findViewById(R.id.bill_log);
		productIntroduceImageView = (ImageView) findViewById(R.id.chanpin_log);
		companyIntroduceImageView = (ImageView) findViewById(R.id.company_log);
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.FILL_PARENT, height);
		viewPager.setLayoutParams(params1);
		LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT, height);
		picture_imageview.setLayoutParams(params3);
		// ==================================暂时使用的滚动图
		// ================================
		/*
		 * picture_imageview.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO 自动生成的方法存根 Intent
		 * intent = new Intent(HomeActivity.this,
		 * ProductIntroduceELoanActivity.class); startActivity(intent); } });
		 */
		if (width <= 480) {
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
					logoWidth, logoHeight);
			params.addRule(RelativeLayout.CENTER_IN_PARENT);
			logoImageView.setLayoutParams(params);
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.mini_log_down);
			RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(
					(int) (bitmap.getWidth() * 0.8),
					(int) (bitmap.getHeight() * 0.8));
			params2.addRule(RelativeLayout.CENTER_IN_PARENT);
			miniRiskPromptLoanImageView.setLayoutParams(params2);
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.common_log_down);
			params2 = new RelativeLayout.LayoutParams(
					(int) (bitmap.getWidth() * 0.8),
					(int) (bitmap.getHeight() * 0.8));
			params2.addRule(RelativeLayout.CENTER_IN_PARENT);
			generalConsumeLoanImageView.setLayoutParams(params2);
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.mini_log_down);
			params2 = new RelativeLayout.LayoutParams(
					(int) (bitmap.getWidth() * 0.8),
					(int) (bitmap.getHeight() * 0.8));
			params2.addRule(RelativeLayout.CENTER_IN_PARENT);
			applicationScheduleQueryImageView.setLayoutParams(params2);
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.company_log_down);
			params2 = new RelativeLayout.LayoutParams(
					(int) (bitmap.getWidth() * 0.8),
					(int) (bitmap.getHeight() * 0.8));
			params2.addRule(RelativeLayout.CENTER_IN_PARENT);
			billImageView.setLayoutParams(params2);
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.myeloan2);
			params2 = new RelativeLayout.LayoutParams(
					(int) (bitmap.getWidth() * 0.8),
					(int) (bitmap.getHeight() * 0.8));
			params2.addRule(RelativeLayout.CENTER_IN_PARENT);
			productIntroduceImageView.setLayoutParams(params2);
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.chanpin_log_down);
			params2 = new RelativeLayout.LayoutParams(
					(int) (bitmap.getWidth() * 0.8),
					(int) (bitmap.getHeight() * 0.8));
			params2.addRule(RelativeLayout.CENTER_IN_PARENT);
			companyIntroduceImageView.setLayoutParams(params2);
			bitmap.recycle();
		}
		// for (int i = 0; i < imageId.length; i++) {
		// ImageView imageView = new ImageView(this);
		// LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT,
		// height);
		// imageView.setLayoutParams(params);
		// imageView.setImageResource(imageId[i]);
		// imageView.setScaleType(ScaleType.FIT_XY);
		// images.add(imageView);
		// }
		menuButton = (ImageView) findViewById(R.id.menuButton);
		// docView();
	}

	private void addListener() {
		menuButton.setOnClickListener(this);
		miniRiskPromptLoanLayout.setOnTouchListener(this);
		generalConsumeLoanLayout.setOnTouchListener(this);
		applicationScheduleQueryLayout.setOnTouchListener(this);
		billLayout.setOnTouchListener(this);
		productIntroduceLayout.setOnTouchListener(this);
		companyIntroduceLayout.setOnTouchListener(this);
		consumeLoanButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.menuButton:
			menu.showMenu();
			break;
		case R.id.mainLayout:
			menu.showContent();
			break;
		case R.id.messageLayout:
			if (Contents.IS_LOGIN) {
				intent.setClass(HomeActivity.this, MessageActivity.class);
				startActivity(intent);
			} else {
				Toast.makeText(HomeActivity.this, "此功能需要登录才能访问！",
						Toast.LENGTH_SHORT).show();
				intent.setClass(HomeActivity.this, MyLoginActivity.class);
				intent.putExtra("type", "message");
				startActivity(intent);
				overridePendingTransition(R.anim.lefttoright,
						R.anim.righttoleft);
			}
			break;
		case R.id.updateLayout:
			handler.sendEmptyMessage(6);
			new Thread() {
				public void run() {
					Looper.prepare();
					// if (!NetworkUtil.check(HomeActivity.this)) {
					// if (activity != null) {
					// handler.sendEmptyMessage(7);
					// Toast.makeText(HomeActivity.this, "网络未连接，请重试",
					// Toast.LENGTH_SHORT).show();
					// }
					// } else {
					handler.sendEmptyMessage(5);
					// }
					Looper.loop();
				};
			}.start();
			break;
		// 贷款小常识
		case R.id.miniLayout:
			intent.setClass(HomeActivity.this, CommonActivity.class);
			startActivity(intent);
			break;
		case R.id.aboutusLayout:
			// 关于我们
			intent.setClass(HomeActivity.this, AboutUsActivity.class);
			startActivity(intent);
			System.out.println("====>" + menu.getMenu().getVisibility());
			break;
		case R.id.youhui:
			if (Contents.IS_LOGIN) {
				// add意见反馈模块
				intent.setClass(HomeActivity.this, SuggestionActivity.class);
				startActivity(intent);
			} else {
				Toast.makeText(HomeActivity.this, "此功能需要登录才能访问！",
						Toast.LENGTH_SHORT).show();
				intent.setClass(HomeActivity.this, MyLoginActivity.class);
				intent.putExtra("type", "menu");
				startActivity(intent);
				overridePendingTransition(R.anim.lefttoright,
						R.anim.righttoleft);
			}
			// 分享
			// wz临时跳转-----》
			// intent.setClass(HomeActivity.this, MenuShareActivity.class);
			// startActivity(intent);

			// SocShare soc = new SocShare(HomeActivity.this);
			// soc.initShareImage();
			// soc.initShareText();
			// soc.showShare(true, null, null);

			break;
		case R.id.share:
			intent.setClass(HomeActivity.this, ShareActivity2.class);
			intent.putExtra("type_ch", "1");
			startActivity(intent);
			break;
		case R.id.lock:
			if (Contents.IS_LOGIN) {
				// // lock
				intent.setClass(HomeActivity.this, LockMenuActivoty.class);
				startActivity(intent);
			} else {
				Toast.makeText(HomeActivity.this, "此功能需要登录才能访问！",
						Toast.LENGTH_SHORT).show();
				intent.setClass(HomeActivity.this, MyLoginActivity.class);
				intent.putExtra("type", "menu");
				startActivity(intent);
				overridePendingTransition(R.anim.lefttoright,
						R.anim.righttoleft);
			}

			break;
		case R.id.quit:
			if (!Contents.IS_LOGIN) {
				Intent intent1 = new Intent();
				intent1.setClass(HomeActivity.this, MyLoginActivity.class);
				intent1.putExtra("type", "menu");
				startActivity(intent1);
				overridePendingTransition(R.anim.lefttoright,
						R.anim.righttoleft);
				finish();
			} else {
				quit();
			}
			break;
		case R.id.mapLayout:
			Intent overLayIntent = new Intent();
			overLayIntent.setClass(HomeActivity.this, MapListActivity.class);
			startActivity(overLayIntent);
			break;
		case R.id.consumeloanButton:
			if (Contents.IS_LOGIN) {
				Intent intent3 = new Intent();
				intent3.setClass(HomeActivity.this,
						GeneralConsumeLoanActivity.class);
				startActivity(intent3);
				overridePendingTransition(R.anim.lefttoright,
						R.anim.righttoleft);
			} else {
				Toast.makeText(HomeActivity.this, "此功能需要登录才能访问！",
						Toast.LENGTH_SHORT).show();
				intent.setClass(HomeActivity.this, MyLoginActivity.class);
				intent.putExtra("type", "consumeloan");
				startActivity(intent);
				overridePendingTransition(R.anim.lefttoright,
						R.anim.righttoleft);
			}
			break;
		// 活动公告
		case R.id.actLayout:
			intent.setClass(HomeActivity.this, ActActivity.class);
			startActivity(intent);
			break;
		}
	}

	class GetAPKAsyncTask extends AsyncTask<Object, Object, Object> {
		@Override
		protected Object doInBackground(Object... params) {
			apk = mApkUpdate.getVersionOnServer();
			return apk;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Object result) {
			dismissProgressDialog();
			super.onPostExecute(result);
			if (activity != null) {
				getResponse(result);
			}
		}
	}

	public void getResponse(Object result) {
		PersonalApkResponse apk = (PersonalApkResponse) result;
		if (apk == null) {
			Toast.makeText(HomeActivity.this,
					getResources().getString(R.string.no_network),
					Toast.LENGTH_SHORT).show();
		} else {
			if (0 == apk.getCode()) {
				mApkUpdate.checkNewVersion(HomeActivity.this, apk);
			} else {
				Toast.makeText(HomeActivity.this, apk.getMsg(),
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private void quit() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("是否要注销登录？");
		OnClickListener posListener = new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				flag = 0;
				new LoginAsyncTask().execute(null, null);
			}
		};
		dialog = DialogUtil.showDialog(HomeActivity.this, sb.toString(),
				posListener);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (menu.isMenuShowing()) {
				menu.showContent();
			} else {
				StringBuffer sb = new StringBuffer();
				sb.append("是否要退出程序？");
				OnClickListener posListener = new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						finish();
						Contents.username = "";
						Contents.IS_LOGIN = false;
						Contents.response = null;
					}
				};
				dialog1 = DialogUtil.showDialog(HomeActivity.this,
						sb.toString(), posListener);
			}
		}
		return true;
	}

	@Override
	public void onResume() {
		Contents.isChoice = false;
		scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutor.scheduleAtFixedRate(new PagerTask(), 1, 5,
				TimeUnit.SECONDS);
		username.setText(Contents.username);
		if (Contents.username.length() > 4) {
			dianTextView.setVisibility(View.VISIBLE);
		} else {
			dianTextView.setVisibility(View.INVISIBLE);
		}
		if (!Contents.IS_LOGIN) {
			quit.setText("登录");
		} else {
			quit.setText("退出登录");
		}
		if (!isPicture) {
			handler.sendEmptyMessage(10);
		}
		super.onResume();
	}

	public void onPause() {
		scheduledExecutor.shutdown();
		super.onPause();
	}

	@SuppressWarnings("deprecation")
	private void docView() {
		// TODO Auto-generated method stub
		imageAdapter = new ViewPagerBaseAdapter();
		viewPager.setAdapter(imageAdapter);
		viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
		dots = new ArrayList<View>();
		LinearLayout layout = (LinearLayout) findViewById(R.id.pagedot);
		int layoutHeight;
		int docheight;
		if (width <= 480) {
			layoutHeight = 15;
			docheight = 8;
		} else if (width <= 540) {
			layoutHeight = 20;
			docheight = 10;
		} else {
			layoutHeight = 35;
			docheight = 16;
		}
		LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT, layoutHeight);
		layout.setLayoutParams(params2);
		LinearLayout.LayoutParams viewParams = new LinearLayout.LayoutParams(
				docheight, docheight);
		viewParams.setMargins(5, 4, 5, 4);
		for (int i = 0; i < images.size(); i++) {
			view = new View(this);
			if (i == 0)
				view.setBackgroundResource(R.drawable.slide_adv_selected);
			else
				view.setBackgroundResource(R.drawable.slide_adv_normal);
			dots.add(view);
			layout.addView(view, viewParams);
		}
	}

	class ViewPagerBaseAdapter extends PagerAdapter {
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return images.size();
			// return 1;
			// return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup viewpager, final int position) {
			viewpager.removeView(images.get(position % images.size()));
			viewpager.addView(images.get(position % images.size()));
			images.get(position % images.size()).setOnClickListener(
					new OnClickListener() {
						@Override
						public void onClick(View v) {
							// if (position % images.size() == 2) {
							// Intent intent = new Intent(HomeActivity.this,
							// ConcernOfLoveActivity.class);
							// startActivity(intent);
							// } else {
							if (!NetworkUtil.isNetConnected(HomeActivity.this)) {
								System.out.println("沒網");
							} else {
								System.out.println("--->点击第" + position + "张");
								Intent intent = new Intent(HomeActivity.this,
										AdEloanActivity.class);
								intent.putExtra("picNum", position);
								startActivity(intent);
								System.out.println("===》》有网");
							}
						}
					});
			return images.get(position % (images.size()));
		}

		@Override
		public void destroyItem(ViewGroup viewpager, int position, Object object) {
			// TODO Auto-generated method stub
			viewpager.removeView(images.get(position % images.size()));
		}
	}

	private class MyOnPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onPageSelected(final int position) {
			// TODO Auto-generated method stub
			currentIndex = position;
			dots.get(position % images.size()).setBackgroundResource(
					R.drawable.slide_adv_selected);
			dots.get(oldPosition % images.size()).setBackgroundResource(
					R.drawable.slide_adv_normal);
			oldPosition = position;
		}
	}

	class LoginAsyncTask extends AsyncTask<String, Object, Object> {
		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper.getInstance(HomeActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			return httpHelper.post(ContantsAddress.LOGOUT, arg, Response.class);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog();
		}

		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);
			if (activity != null) {
				dismissProgressDialog();
				quitResponse(result);
			}
		}
	}

	private void quitResponse(Object result) {
		Response response = (Response) result;
		if (response == null) {
			loginErrorMessage = "服务器请求异常!";
			// DialogUtil.createDialog(this, 1, loginErrorMessage);
			Toast.makeText(HomeActivity.this,
					getResources().getString(R.string.no_network),
					Toast.LENGTH_SHORT).show();
			return;
		} else {
			if (RET_CODE.equals(response.getCode())) {
				Toast.makeText(HomeActivity.this, "退出成功", Toast.LENGTH_SHORT)
						.show();
				if (flag == 0) {
					Intent intent = new Intent();
					intent.setClass(HomeActivity.this, MyLoginActivity.class);
					intent.putExtra("type", "menu");
					intent.putExtra("user_name", Contents.username);
					startActivity(intent);
					dialog.cancel();
					overridePendingTransition(R.anim.lefttoright,
							R.anim.righttoleft);
					finish();
				} else if (flag == 1) {
					finish();
				}
				Contents.username = "";
				Contents.IS_LOGIN = false;
				Contents.response = null;
			} else {
				loginErrorMessage = response.getMsg();
				DialogUtil.createDialog(this, 4, loginErrorMessage);
			}
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		if (v == miniRiskPromptLoanLayout) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				miniRiskPromptLoanImageView
						.setBackgroundResource(R.drawable.jisu_loan_down);
				miniRiskPromptLoanLayout
						.setBackgroundResource(R.drawable.mini_down);
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				miniRiskPromptLoanImageView
						.setBackgroundResource(R.drawable.jisu_loan_normal);
				miniRiskPromptLoanLayout
						.setBackgroundResource(R.drawable.mini_normal);
				if (Contents.IS_LOGIN) {
					// intent = new Intent(HomeActivity.this,
					// RepidlyLoanInfoContractBook.class);
					// intent.putExtra("choiceType", "choicenull");
					// startActivity(intent);
					// if(1==2){
					if (Contents.response != null
							&& Contents.response.getResult() != null
							&& Contents.response.getResult()
									.isExistFavourableActv()) {
						intent.setClass(HomeActivity.this,
								RedPacketActivity.class);
						intent.putExtra("choiceType", "choicenull");
						startActivity(intent);
					} else {
						intent.setClass(HomeActivity.this,
								RedPacketActivity2.class);
						intent.putExtra("choiceType", "choicenull");
						startActivity(intent);
					}

				} else {
					Toast.makeText(HomeActivity.this, "此功能需要登录才能访问！",
							Toast.LENGTH_SHORT).show();
					intent.setClass(HomeActivity.this, MyLoginActivity.class);
					// intent.setClass(HomeActivity.this,
					// TranscribeVideoActivity.class);
					// intent.putExtra("type", "rapidly");
					intent.putExtra("type", "menu");
					startActivity(intent);
					overridePendingTransition(R.anim.lefttoright,
							R.anim.righttoleft);
				}
				// Toast.makeText(this, "此功能正在开发中，敬请期待",
				// Toast.LENGTH_LONG).show();
			} else if (event.getAction() == MotionEvent.ACTION_CANCEL) {
				miniRiskPromptLoanImageView
						.setBackgroundResource(R.drawable.jisu_loan_normal);
				miniRiskPromptLoanLayout
						.setBackgroundResource(R.drawable.mini_normal);
			}
		} else if (v == generalConsumeLoanLayout) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				generalConsumeLoanImageView
						.setBackgroundResource(R.drawable.common_log_down);
				generalConsumeLoanLayout
						.setBackgroundResource(R.drawable.common_down);
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				generalConsumeLoanImageView
						.setBackgroundResource(R.drawable.common_log_normal);
				generalConsumeLoanLayout
						.setBackgroundResource(R.drawable.common_normal);
				if (Contents.IS_LOGIN) {
					// intent.setClass(HomeActivity.this,
					// RelaxedLoanOneContractBook.class);
					// 加轻松贷内页
					intent.setClass(HomeActivity.this,
							RelaxedLoanAdActivity.class);
					intent.putExtra("choiceType", "choicenull");
					startActivity(intent);
				} else {
					Toast.makeText(HomeActivity.this, "此功能需要登录才能访问！",
							Toast.LENGTH_SHORT).show();
					intent.setClass(HomeActivity.this, MyLoginActivity.class);
					intent.putExtra("type", "common");
					startActivity(intent);
					overridePendingTransition(R.anim.lefttoright,
							R.anim.righttoleft);
				}
			} else if (event.getAction() == MotionEvent.ACTION_CANCEL) {
				generalConsumeLoanImageView
						.setBackgroundResource(R.drawable.common_log_normal);
				generalConsumeLoanLayout
						.setBackgroundResource(R.drawable.common_normal);
			}
		} else if (v == applicationScheduleQueryLayout) {
			// 、---------------迷你
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				applicationScheduleQueryImageView
						.setBackgroundResource(R.drawable.mini_log_home_down);
				applicationScheduleQueryLayout
						.setBackgroundResource(R.drawable.search_company_down);
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				applicationScheduleQueryImageView
						.setBackgroundResource(R.drawable.mini_log_home);
				applicationScheduleQueryLayout
						.setBackgroundResource(R.drawable.search_company_normal);
				if (Contents.IS_LOGIN) {
					// 判断是否为老用户
//					new isOldUser().execute();
					intent.setClass(HomeActivity.this,MiniNewAdActivity.class);
					startActivity(intent);

				} else {
					Toast.makeText(HomeActivity.this, "此功能需要登录才能访问！",
					Toast.LENGTH_SHORT).show();
					intent.setClass(HomeActivity.this, MyLoginActivity.class);
//					 intent.setClass(HomeActivity.this, MiniNewAdActivity.class);
					intent.putExtra("type", "menu");
					startActivity(intent);
					overridePendingTransition(R.anim.lefttoright,
							R.anim.righttoleft);
				}
			} else if (event.getAction() == MotionEvent.ACTION_CANCEL) {
				applicationScheduleQueryImageView
						.setBackgroundResource(R.drawable.mini_log_home);
				applicationScheduleQueryLayout
						.setBackgroundResource(R.drawable.search_company_normal);
			}
		} else if (v == billLayout) {
			// 公司介绍
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				billImageView
						.setBackgroundResource(R.drawable.xiaojinyin_log_down);
				billLayout.setBackgroundResource(R.drawable.bill_chanpin_down);
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				billImageView.setBackgroundResource(R.drawable.xiaojinyin_log);
				billLayout
						.setBackgroundResource(R.drawable.bill_chanpin_normal);
				if (Contents.IS_LOGIN) {
					intent.setClass(HomeActivity.this,
							XiaoJinYingAdActivity.class);
					intent.putExtra("flag", "bill");
					startActivity(intent);
				} else {
					Toast.makeText(HomeActivity.this, "此功能需要登录才能访问！",
							Toast.LENGTH_SHORT).show();
					intent.setClass(HomeActivity.this, MyLoginActivity.class);
					intent.putExtra("type", "menu");
					startActivity(intent);
					overridePendingTransition(R.anim.lefttoright,
							R.anim.righttoleft);
				}
			} else if (event.getAction() == MotionEvent.ACTION_CANCEL) {
				billImageView.setBackgroundResource(R.drawable.xiaojinyin_log);
				billLayout
						.setBackgroundResource(R.drawable.bill_chanpin_normal);
			}
		} else if (v == productIntroduceLayout) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				productIntroduceImageView
						.setBackgroundResource(R.drawable.myeloan2);
				productIntroduceLayout
						.setBackgroundResource(R.drawable.bill_chanpin_down);
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				productIntroduceImageView
						.setBackgroundResource(R.drawable.myeloan1);
				productIntroduceLayout
						.setBackgroundResource(R.drawable.bill_chanpin_normal);
				intent.setClass(HomeActivity.this, MyEloanActivity.class);
				startActivity(intent);
			} else if (event.getAction() == MotionEvent.ACTION_CANCEL) {
				productIntroduceImageView
						.setBackgroundResource(R.drawable.myeloan1);
				productIntroduceLayout
						.setBackgroundResource(R.drawable.bill_chanpin_normal);
			}
		} else if (v == companyIntroduceLayout) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				companyIntroduceImageView
						.setBackgroundResource(R.drawable.chanpin_log_down);
				companyIntroduceLayout
						.setBackgroundResource(R.drawable.search_company_down);
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				companyIntroduceImageView
						.setBackgroundResource(R.drawable.chanpin_log_normal2);
				companyIntroduceLayout
						.setBackgroundResource(R.drawable.search_company_normal);
				intent.setClass(HomeActivity.this,
						ProductIntroduceActivity.class);
				startActivity(intent);
			} else if (event.getAction() == MotionEvent.ACTION_CANCEL) {
				companyIntroduceImageView
						.setBackgroundResource(R.drawable.chanpin_log_normal2);
				companyIntroduceLayout
						.setBackgroundResource(R.drawable.search_company_normal);
			}
		}
		return true;
	}

	/**
	 * 构造广播监听类，监听 SDK key 验证以及网络异常广播
	 */
	public class SDKReceiver extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {
			String s = intent.getAction();
			if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
				// Toast.makeText(context,
				// "key 验证出错! 请在 AndroidManifest.xml 文件中检查 key 设置", 0)
				// .show();
			} else if (s
					.equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {
				Toast.makeText(context, "网络出错", Toast.LENGTH_LONG).show();
			}
		}
	}

	class PictureAsyncTask extends AsyncTask<String, Object, Object> {
		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper.getInstance(HomeActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			return httpHelper.post(ContantsAddress.BANNER, arg,
					BannerResult.class);
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (result != null) {
				isPicture = true;
				BannerResult response = (BannerResult) result;
				// listPicture = new ArrayList<BannerResponse>();
				listPicture = response.getResult();
				if (null != listPicture && 0 < listPicture.size()) {
					picture_imageview.setVisibility(View.GONE);
					picture_layout.setVisibility(View.VISIBLE);
					for (int i = 0; i < listPicture.size(); i++) {
						ImageView imageView = new ImageView(HomeActivity.this);
						LayoutParams params = new LayoutParams(
								LayoutParams.FILL_PARENT, height);
						imageView.setLayoutParams(params);
						imageView.setImageResource(R.drawable.brand1);
						imageView.setScaleType(ScaleType.FIT_XY);
						DownLoaderBitmap download = new DownLoaderBitmap();
						String fileName = listPicture.get(i).getDt()
								.replaceAll("[//d]", "")
								+ i + ".jpg";
						if (checkCache(HomeActivity.this, fileName)) {
							images.add(imageView);
							Bitmap bm = ViewTools.getBitMap("/data/data/"
									+ context.getPackageName() + "/file/"
									+ fileName, 0);
							images.get(i).setImageBitmap(bm);
							listBitmap.add(bm);
							System.out.println("==================");
						} else {
							String path = listPicture
									.get(i)
									.getPath()
									.replaceAll(
											"/washome/webapps/eloan-services.ear/eloan-services.war/",
											"");
							download.downloadStart1(HomeActivity.this,
							// "http://10.160.7.220:9080/eloan-services/"
									ContantsAddress.ip + path, i, fileName);
							download.setOnIamgeLoadDownListener(new OnImageLoadDownListener() {
								@Override
								public void OnFinished(Bitmap retBmp, int index) {
									if (null != retBmp) {
										images.get(index)
												.setImageBitmap(retBmp);
										listBitmap.add(retBmp);
										System.out.println(index);
									}
								}
							});
							images.add(imageView);
						}
					}
					handler.sendEmptyMessage(11);
				} else {
					picture_imageview.setVisibility(View.VISIBLE);
					picture_layout.setVisibility(View.GONE);
					isPicture = false;
				}
			} else {
				picture_imageview.setVisibility(View.VISIBLE);
				picture_layout.setVisibility(View.GONE);
				isPicture = false;
			}
		}
	}

	private Boolean checkCache(Context context, String name) {
		String dirs = "/data/data/" + context.getPackageName() + "/file/";
		String fileName = dirs + name;
		// return DataUtils.fileExits(context, fileName);
		return new File(fileName).exists();
	}

	// 判断是否是老用户
	class isOldUser extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {
			// TODO Auto-generated method stub
			HttpHelper httpHelper = HttpHelper.getInstance(HomeActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("idNo", Contents.response
					.getResult().getIdNo()));
			return httpHelper.post(ContantsAddress.MiniOldCust, arg,
					MiniIsOldUserResponse.class);
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
			if (result != null) {
				MiniIsOldUserResponse loduser = (MiniIsOldUserResponse) result;
				if ("true".equals(loduser.getFlag())) {
					Intent intent = new Intent();
//					intent.setClass(HomeActivity.this,
//							ApplicationScheduleQueryHomeActivity.class);
					intent.setClass(HomeActivity.this, MiniLoanActivity.class);
					startActivity(intent);
				} else {
					Toast.makeText(HomeActivity.this,
							"现申请一笔极速贷或者一般贷款后，方可申请迷你循环消费贷！", Toast.LENGTH_SHORT)
							.show();
				}
			} else {
				System.out.println("Mini----···服务器连接失败");
				Toast.makeText(HomeActivity.this, "服务器连接失败", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}
}
