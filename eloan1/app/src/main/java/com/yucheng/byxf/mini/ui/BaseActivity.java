package com.yucheng.byxf.mini.ui;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.yucheng.byxf.comm.ScreenManager;
import com.yucheng.byxf.mini.domain.MiniPersonInfo;
import com.yucheng.byxf.mini.easyloan.ui.SharedPreferencesUtils;
import com.yucheng.byxf.mini.map.DemoApplication;
import com.yucheng.byxf.mini.rapidly.RapidlyLoanInfoActivity;

import com.yucheng.byxf.mini.utils.ChooseDialog;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.views.ProgressView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class BaseActivity extends BaseAllActivity implements OnClickListener {
	protected final static int PROGRESS_DIALOG_ID = 10000000;
	private ScreenManager screenManager;
	private static View view;
	private Context context;

	private int time = 30; // 设置请求超时时间 30s

	protected DemoApplication application = null;
	protected MiniPersonInfo miniPersonInfo=null;

	private String strInfo;
	private ChooseDialog chooseDialog;
	private String st_city;
	private String st_province;
	private String st_area;

	protected SharedPreferencesUtils preferencesUtils;

	// 定位相关
	LocationClient mLocClient;
	public MyLocationListenner myListener = new MyLocationListenner();
	private LocationMode mCurrentMode;
	BitmapDescriptor mCurrentMarker;
	protected boolean isFirstLoc = true;// 是否首次定位
	GeoCoder mSearch = null; // 搜索模块，也可去掉地图模块独立使用

	double latitude;
	double longitude;

	private Dialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// System.out.println(Runtime.getRuntime().totalMemory());
		screenManager = ScreenManager.getScreenManager();
		screenManager.pushActivity(this);
		context = this;
		preferencesUtils = new SharedPreferencesUtils(this);
		application = (DemoApplication) getApplicationContext();
	}

	protected void getLocationInfo() {

		// 定位初始化
		mLocClient = new LocationClient(this);
		mLocClient.registerLocationListener(myListener);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(1000);
		mLocClient.setLocOption(option);
		mLocClient.start();

		// 初始化搜索模块，注册事件监听
		mSearch = GeoCoder.newInstance();
		mSearch.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {

			@Override
			public void onGetGeoCodeResult(GeoCodeResult result) {
				// TODO Auto-generated method stub
				if (result == null
						|| result.error != SearchResult.ERRORNO.NO_ERROR) {
					Toast.makeText(context, "抱歉，未能找到结果", Toast.LENGTH_LONG)
							.show();
					return;
				}
				String strInfo = String.format("纬度：%f 经度：%f",
						result.getLocation().latitude,
						result.getLocation().longitude);
				Toast.makeText(context, strInfo, Toast.LENGTH_LONG).show();
				System.out.println(result.getAddress() + "<===========");
				Contents.locationInfo = result.getAddress();
			}

			@Override
			public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
				// TODO Auto-generated method stub
				if (result == null
						|| result.error != SearchResult.ERRORNO.NO_ERROR) {
					Toast.makeText(context, "抱歉，未能找到结果", Toast.LENGTH_LONG)
							.show();
					return;
				}
//				Toast.makeText(context, result.getAddress(), Toast.LENGTH_LONG)
//						.show();

				System.out.println(result.getAddress() + "<===========");
				Contents.locationInfo = result.getAddress();
				Contents.city = result.getAddressDetail().city;
				Contents.area = result.getAddressDetail().district;
				Contents.province = result.getAddressDetail().province;
			}
		});

	}

	protected void locationAgain() {
		mLocClient.requestLocation();
		System.out
				.println("-locationAgain|strInfo:" + strInfo + "---city:"
						+ st_city + "---area：" + st_area + "---province:"
						+ st_province);

	}

	public void cancleAsyncTask() {

	}

	/**
	 * 发起搜索
	 * 
	 * @param v
	 */
	void SearchButtonProcess() {
		LatLng ptCenter = new LatLng(Float.valueOf(latitude + ""),
				Float.valueOf(longitude + ""));
		// 反Geo搜索
		mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(ptCenter));
		System.out.println("============>");
	}

	/**
	 * 定位SDK监听函数
	 */
	public class MyLocationListenner implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// map view 销毁后不在处理新接收的位置
			if (location == null)
				return;
			MyLocationData locData = new MyLocationData.Builder()
					.accuracy(location.getRadius())
					// 此处设置开发者获取到的方向信息，顺时针0-360
					.direction(100).latitude(location.getLatitude())
					.longitude(location.getLongitude()).build();
			// mBaiduMap.setMyLocationData(locData);
			latitude = location.getLatitude();
			longitude = location.getLongitude();
			Contents.longitude = longitude + "";
			Contents.latitude = latitude + "";
			System.out.println(latitude + "," + longitude);
			if (isFirstLoc) {
				isFirstLoc = false;
				LatLng ll = new LatLng(latitude, longitude);
				// MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
				// mBaiduMap.animateMapStatus(u);

				SearchButtonProcess();
			}
		}

		public void onReceivePoi(BDLocation poiLocation) {
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:// 表示返回
			// closeView();
			if (view != null) {
				// view.setVisibility(View.GONE);
				// view = null;
				return false;
			} else {
				destroyActivity();
			}
			break;
		}
		return false;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	public static void setDialogDismiss() {
		if (view != null) {
			view.setVisibility(View.GONE);
			view = null;
		}
	}

	private void timer() {
		new Thread() {
			public void run() {
				while (time > 0) {
					time--;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				handler.sendEmptyMessage(1);
			};
		}.start();
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 1) {
				if (view != null) {
					Toast.makeText(context, "网络请求超时！", Toast.LENGTH_LONG).show();
					dismissProgressDialog();
				}
			}
		};
	};

	public void destroyActivity() {
		removeDialog(PROGRESS_DIALOG_ID);
		destroy();
		screenManager.popActivity(this);
		System.gc();
	}

	protected void destroy() {
	}

	private boolean createView() {
		boolean flag = false;
		WindowManager wm = (WindowManager) getApplicationContext()
				.getSystemService("window");
		WindowManager.LayoutParams wmParams = new LayoutParams();
		view = LayoutInflater.from(this).inflate(
				R.layout.common_progress_layout, null);
		ImageView imageView = (ImageView) view
				.findViewById(R.id.comm_progress_iv_anim);// 加载布局
		// // 加载动画
		AnimationDrawable animation = (AnimationDrawable) imageView
				.getBackground();
		// 使用ImageView显示动画
		animation.start();
		/**
		 * 设置WindowManager.LayoutParams的相关属性，具体的用途参考SDK文档
		 */
		wmParams.type = 2002;
		wmParams.format = 1;
		wmParams.flags = 40;
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int wight = metrics.widthPixels;
		int height = metrics.heightPixels;
		float density = metrics.density;
		// 屏幕的分辨率等于（360,640）与density的乘积
		wmParams.width = (int) (wight + 0.5f);
		wmParams.height = (int) (height + 0.5f);
		wm.addView(view, wmParams);
		flag = true;
		time = 30;
		// timer();
		return flag;
	}

	private boolean closeView() {
		if (view != null) {
			cancleAsyncTask();
			view.setVisibility(View.GONE);
			view = null;
			time = -1;
		}
		return true;
	}

	protected boolean showProgressDialog() {
		boolean isSuccess = false;
		// isSuccess = createView();
		dialog = ProgressView.createLoadingDialog(context);
		dialog.show();
		dialog.setCanceledOnTouchOutside(false);
		dialog.setCancelable(false);
		isSuccess = true;
		return isSuccess;
	}

	protected void dismissProgressDialog() {
		// closeView();
		if (null != dialog  && dialog.isShowing()) {
			dialog.dismiss();
		}
	}

	protected void onCancelDialog() {
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// if (view != null) {
		// view.setVisibility(View.GONE);
		// view = null;
		// } else {
		destroyActivity();

		// }
		// 退出时销毁定位
		if (mLocClient != null) {
			mLocClient.stop();
		}
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}
	
	
}