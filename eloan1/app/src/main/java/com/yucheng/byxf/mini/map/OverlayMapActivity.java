package com.yucheng.byxf.mini.map;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.InfoWindow.OnInfoWindowClickListener;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.yucheng.byxf.mini.ui.BaseActivity;
import com.yucheng.byxf.mini.ui.MyLoginActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.ui.RegisterActivity;
import com.yucheng.byxf.mini.utils.Contents;

@SuppressLint("DalvikOverride")
public class OverlayMapActivity extends BaseActivity implements
		OnGetGeoCoderResultListener {

	private MapView mMapView;
	private BaiduMap mBaiduMap;
	// 初始化全局 bitmap 信息，不用时及时 recycle
	BitmapDescriptor bdA = BitmapDescriptorFactory
			.fromResource(R.drawable.icon_marka);
	private List<BranchLocation> list = new ArrayList<BranchLocation>();
	private BranchLocation branchLocation;

	private Marker mMarker;

	private InfoWindow mInfoWindow;
	private String qidian = "";
	// 定位相关
	LocationClient mLocClient;
	public MyLocationListenner myListener = new MyLocationListenner();
	BitmapDescriptor mCurrentMarker;
	protected boolean isFirstLoc = true;// 是否首次定位
	GeoCoder mSearch = null; // 搜索模块，也可去掉地图模块独立使用

	double latitude; // 维度
	double longitude; // 精度

	// pop
	private TextView popupTextTitle = null;
	private TextView popupTextAddress = null;
	private TextView textPhone = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_overlay);

		Intent intent = getIntent();
		branchLocation = (BranchLocation) intent.getExtras().get("list");
		list.add(branchLocation);

		mMapView = (MapView) findViewById(R.id.bmapView);

		mBaiduMap = mMapView.getMap();
		// 设置缩放级别
		MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(11);
		mBaiduMap.setMapStatus(msu);

		initLocationInfo();
		initOverlay();

		//wz
		System.out.println("w精度---"+list.get(0).getLongitude());
		System.out.println("w维度---"+list.get(0).getLatitude());
		System.out.println("waddr---"+list.get(0).getAddr());
		System.out.println("wName---"+list.get(0).getName());
		

//		
//		popupTextTitle.setText(list.get(0).getName());
//		popupTextAddress.setText(list.get(0).getAddr());
//		textPhone.setText(list.get(0).getPhone());
		
		mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {

			@Override
			public boolean onMarkerClick(final Marker marker) {
				// TODO Auto-generated method stub
				View viewCache = getLayoutInflater().inflate(
						R.layout.custom_text_view, null);
			popupTextTitle = (TextView) viewCache.findViewById(R.id.text_title);
			popupTextAddress=(TextView) viewCache.findViewById(R.id.textaddress);
			textPhone=(TextView) viewCache.findViewById(R.id.textPhone);
				
			
			popupTextTitle.setText(list.get(0).getName());
			popupTextAddress.setText(list.get(0).getAddr());
			textPhone.setText(list.get(0).getPhone());	
						
				final LatLng ll = marker.getPosition();
				Point p = mBaiduMap.getProjection().toScreenLocation(ll);
				p.y -= 47;
				LatLng llInfo = mBaiduMap.getProjection().fromScreenLocation(p);
				OnInfoWindowClickListener listener = null;
				if (marker == mMarker) {
					listener = new OnInfoWindowClickListener() {
						public void onInfoWindowClick() {
							// LatLng llNew = new LatLng(ll.latitude + 0.005,
							// ll.longitude + 0.005);
							// marker.setPosition(llNew);
							mBaiduMap.hideInfoWindow();
//							Toast.makeText(OverlayMapActivity.this,
//									"gotoButton", Toast.LENGTH_LONG).show();
						//gotoButton 跳转w
						Intent intent = new Intent(OverlayMapActivity.this, MapRouteResultNewActivity.class);
						intent.putExtra("latitude",latitude+"");
						intent.putExtra("longitude",longitude+"");
//	目标支行
						intent.putExtra("endLatitude",
								list.get(0).getLatitude()+"");
						intent.putExtra("endLongitude",
								list.get(0).getLongitude()+"");
//						intent.putExtra("city", city);
						intent.putExtra("shi", list.get(0).getName());
						intent.putExtra("qi", "我的位置");
						startActivity(intent);
						}
					};
				}else {
					listener = new OnInfoWindowClickListener() {
						public void onInfoWindowClick() {
							// LatLng llNew = new LatLng(ll.latitude + 0.005,
							// ll.longitude + 0.005);
							// marker.setPosition(llNew);
							mBaiduMap.hideInfoWindow();
						}
					};
				}
				mInfoWindow = new InfoWindow(viewCache, llInfo, listener);
				mBaiduMap.showInfoWindow(mInfoWindow);
				return true;
			}
		});
	}

	private void initLocationInfo() {
		// 开启定位图层
		mBaiduMap.setMyLocationEnabled(true);
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
					Toast.makeText(OverlayMapActivity.this, "抱歉，未能找到结果",
							Toast.LENGTH_LONG).show();
					return;
				}
				String strInfo = String.format("纬度：%f 经度：%f",
						result.getLocation().latitude,
						result.getLocation().longitude);
				System.out.println(result.getAddress() + "<===========");
			}

			@Override
			public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
				// TODO Auto-generated method stub
				if (result == null
						|| result.error != SearchResult.ERRORNO.NO_ERROR) {
					Toast.makeText(OverlayMapActivity.this, "抱歉，未能找到结果",
							Toast.LENGTH_LONG).show();
					return;
				}

				System.out.println(result.getAddress() + "<===========");
				Contents.locationInfo = result.getAddress();
				Contents.city = result.getAddressDetail().city;
				Contents.area = result.getAddressDetail().district;
				Contents.province = result.getAddressDetail().province;
			}
		});

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
			mBaiduMap.setMyLocationData(locData);
			if (isFirstLoc) {
				isFirstLoc = false;
				LatLng ll = new LatLng(location.getLatitude(),
						location.getLongitude());
				System.out.println("我的坐标" + ll);
				MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
				latitude = location.getLatitude();
				longitude = location.getLongitude();
				Contents.latitude = location.getLatitude() + "";
				Contents.longitude = location.getLongitude() + "";
				System.out.println("u===>" + u);
				mBaiduMap.animateMapStatus(u);
				SearchButtonProcess();
			}
		}

		public void onReceivePoi(BDLocation poiLocation) {
		}
	}

	public void initOverlay() {
		LatLng llA = new LatLng(Float.valueOf(list.get(0).getLatitude()),
				Float.valueOf(list.get(0).getLongitude()));
		OverlayOptions ooA = new MarkerOptions().position(llA).icon(bdA)
				.zIndex(11).draggable(true);
		mMarker = (Marker) (mBaiduMap.addOverlay(ooA));

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mMapView.onDestroy();
		// 回收 bitmap 资源
		bdA.recycle();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// MapView的生命周期与Activity同步，当activity恢复时需调用MapView.onResume()
		mMapView.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		// MapView的生命周期与Activity同步，当activity挂起时需调用MapView.onPause()
		mMapView.onPause();
	}

	@Override
	public void onGetGeoCodeResult(GeoCodeResult arg0) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
		// TODO 自动生成的方法存根
		result.getAddress();
		System.out.println("result.getAddress()--->" + result.getAddress());
	}
}
