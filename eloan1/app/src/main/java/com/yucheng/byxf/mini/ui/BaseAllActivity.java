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
import com.wujay.fund.ui.GestureVerifyActivity;
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
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
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

public class BaseAllActivity extends Activity implements OnClickListener {
	private SharedPreferences sp0 = null;// 声明一个SharedPreferences
	private String FILE = "saveGesturePwd";// 用于保存SharedPreferences的文件
	protected DemoApplication application = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		application = (DemoApplication) getApplicationContext();
	}

	@Override
	public void onClick(View arg0) {

	}

	@Override
	protected void onRestart() {
		super.onRestart();
		System.out.println("DD***onRestart" + Contents.IS_Loc);

		if (Contents.IS_Loc == 2) {

			Intent it = new Intent();
			it.putExtra("type", 9);
			it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			it.setClass(application, GestureVerifyActivity.class);
			System.out.println("DD***开启锁屏");
			startActivity(it);
			Contents.IS_Loc = -1;
			Contents.IS_LocStart = 1;

		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		System.out.println("DD***onPause");
	}

	@Override
	protected void onStop() {
		System.out.println("DD***stop");
		super.onStop();
	}

}