package com.yucheng.byxf.mini.map;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.DrivingRouteOverlay;
import com.baidu.mapapi.overlayutil.OverlayManager;
import com.baidu.mapapi.overlayutil.TransitRouteOverlay;
import com.baidu.mapapi.overlayutil.WalkingRouteOverlay;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRouteLine.DrivingStep;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.mapapi.search.route.TransitRouteLine.TransitStep;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteLine;
import com.baidu.mapapi.search.route.WalkingRouteLine.WalkingStep;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.yucheng.byxf.mini.adapter.MapRouteResultNewAdapter;
import com.yucheng.byxf.mini.ui.BaseActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.Contents;

public class MapRouteResultNewActivity extends BaseActivity implements
		OnGetRoutePlanResultListener, OnMapClickListener {
	public static List<DrivingRouteLine> listDrivingRouteResult = new ArrayList<DrivingRouteLine>();
	public static List<TransitRouteLine> listTransitRouteResult = new ArrayList<TransitRouteLine>();
	public static List<WalkingRouteLine> listWalkingRouteResult = new ArrayList<WalkingRouteLine>();

	private MapRouteResultNewAdapter adapter;
	// UI
	ImageView mBtnDrive = null; // 驾车搜索
	ImageView mBtnTransit = null; // 公交搜索
	ImageView mBtnWalk = null; // 步行搜索

	int nodeIndex = -2;// 节点索引,供浏览节点时使用
	RouteLine route = null;
	OverlayManager routeOverlay = null;
	boolean useDefaultIcon = false;
	int searchType = 0;// 记录搜索的类型，区分驾车/步行和公交

	// 搜索相关
	RoutePlanSearch mSearch = null; // 搜索模块，也可去掉地图模块独立使用
	// 当前定位的城市
	private String city = "北京";
	DemoApplication app;

	private ImageView back;

	private String latitude;
	private String longitude;
	private String endLatitude;
	private String endLongitude;
	private ListView listView;

	// private MapRouteResultAdapter adapter;
	private String qiString; // 起点
	private String shiString; // 终点

	private TextView qiTextView;// 起点
	private TextView shiTextView;// 终点

	BaiduMap mBaidumap = null;

	private int type;// 导航类型的标识

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maprouteresult_activity);
		listDrivingRouteResult.clear();
		listTransitRouteResult.clear();
		listWalkingRouteResult.clear();
		
		Intent intent = getIntent();
		latitude = intent.getStringExtra("latitude");
		longitude = intent.getStringExtra("longitude");
		endLatitude = intent.getStringExtra("endLatitude");
		endLongitude = intent.getStringExtra("endLongitude");
		// city = intent.getStringExtra("city");
		qiString = intent.getStringExtra("qi");
		shiString = intent.getStringExtra("shi");

		mBtnDrive = (ImageView) findViewById(R.id.driving_button);
		mBtnTransit = (ImageView) findViewById(R.id.transit_button);
		mBtnWalk = (ImageView) findViewById(R.id.walk_button);
		listView = (ListView) findViewById(R.id.listview);

		qiTextView = (TextView) findViewById(R.id.qi_text);
		shiTextView = (TextView) findViewById(R.id.shi_text);

		qiTextView.setText("我的位置");
		shiTextView.setText(shiString);

		adapter = new MapRouteResultNewAdapter(this, searchType,
				listDrivingRouteResult, listTransitRouteResult,
				listWalkingRouteResult, qiString, shiString);
		listView.setAdapter(adapter);
		mSearch = RoutePlanSearch.newInstance();
		mSearch.setOnGetRoutePlanResultListener(this);
		// list
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				System.out.println("============================");
				Intent intent = new Intent(MapRouteResultNewActivity.this, RoutePlanNewActivity.class);
				intent.putExtra("searchType", searchType);
				intent.putExtra("position", position);
				intent.putExtra("latitude", latitude+"");
				intent.putExtra("longitude", longitude+"");
				intent.putExtra("endLatitude", endLatitude+"");
				intent.putExtra("endLongitude", endLongitude+"");
				intent.putExtra("city", city);
				startActivity(intent);
				System.out.println("===plan2>"+"latitude"+latitude);
				System.out.println("===plan2>"+"longitude"+longitude);
				System.out.println("===plan2>"+"endLatitude"+endLatitude);
				System.out.println("===plan2>"+"endLongitude"+endLongitude);
				System.out.println("===plan2>"+"city"+city);
				System.out.println("===plan2>"+"position"+position);
				System.out.println("===plan2"+"searchType"+searchType);
			}
		});
		
		
		
		// 按键点击事件
		OnClickListener clickListener = new OnClickListener() {
			public void onClick(View v) {
				// 发起搜索
				SearchButtonProcess(v);
			}
		};
		mBtnDrive.setOnClickListener(clickListener);
		mBtnTransit.setOnClickListener(clickListener);
		mBtnWalk.setOnClickListener(clickListener);
		
		back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		LatLng st = new LatLng(Double.valueOf(latitude),
				Double.valueOf(longitude));
		PlanNode stNode = PlanNode.withLocation(st);
		LatLng end = new LatLng(Double.valueOf(endLatitude),
				Double.valueOf(endLongitude));
		PlanNode endNode = PlanNode.withLocation(end);
		searchType = 0;// 标识
		mSearch.drivingSearch((new DrivingRoutePlanOption()).from(stNode).to(
				endNode));
		
		System.out.println(latitude+","+longitude+","+endLatitude+","+endLongitude);
	}

	
	public void SearchButtonProcess(View v) {
		route = null; // 节点
		routeOverlay = null;
	
		LatLng st = new LatLng(Double.valueOf(latitude),
				Double.valueOf(longitude));
		PlanNode stNode = PlanNode.withLocation(st);
		LatLng end = new LatLng(Double.valueOf(endLatitude),
				Double.valueOf(endLongitude));
		PlanNode endNode = PlanNode.withLocation(end);
		
		System.out.println(latitude+","+longitude+","+endLatitude+","+endLongitude);
		// ======
		if (mBtnDrive.equals(v)) {

			searchType = 0;// 标识
			if (listDrivingRouteResult.size() == 0) {
				mSearch.drivingSearch((new DrivingRoutePlanOption()).from(
						stNode).to(endNode));
			} else {
				adapter = new MapRouteResultNewAdapter(this, searchType,
						listDrivingRouteResult, listTransitRouteResult,
						listWalkingRouteResult, qiString, shiString);
				listView.setAdapter(adapter);
			}
			mBtnDrive.setImageResource(R.drawable.driving_down);
			mBtnTransit.setImageResource(R.drawable.transit_normal);
			mBtnWalk.setImageResource(R.drawable.walk_normal);

		} else if (mBtnTransit.equals(v)) {
			searchType = 1;// 标识
			if (listTransitRouteResult.size() == 0) {
				mSearch.transitSearch((new TransitRoutePlanOption())
						.from(stNode).city(Contents.city).to(endNode));
			} else {
				adapter = new MapRouteResultNewAdapter(this, searchType,
						listDrivingRouteResult, listTransitRouteResult,
						listWalkingRouteResult, qiString, shiString);
				listView.setAdapter(adapter);
			}
			mBtnDrive.setImageResource(R.drawable.driving_normal);
			mBtnTransit.setImageResource(R.drawable.transit_down);
			mBtnWalk.setImageResource(R.drawable.walk_normal);
			// }
		} else if (mBtnWalk.equals(v)) {
			searchType = 2;// 标识
			if (listWalkingRouteResult.size() == 0) {
				mSearch.walkingSearch((new WalkingRoutePlanOption()).from(
						stNode).to(endNode));
			} else {
				adapter = new MapRouteResultNewAdapter(this, searchType,
						listDrivingRouteResult, listTransitRouteResult,
						listWalkingRouteResult, qiString, shiString);
				listView.setAdapter(adapter);
			}
			mBtnDrive.setImageResource(R.drawable.driving_normal);
			mBtnTransit.setImageResource(R.drawable.transit_normal);
			mBtnWalk.setImageResource(R.drawable.walk_down);
		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onGetWalkingRouteResult(WalkingRouteResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(MapRouteResultNewActivity.this, "抱歉，未找到结果",
					Toast.LENGTH_SHORT).show();
		}
		if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
			// 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
			return;
		}
		if (result.error == SearchResult.ERRORNO.NO_ERROR) {
			nodeIndex = -1;
			searchType = 2;
			listWalkingRouteResult = result.getRouteLines();
			adapter = new MapRouteResultNewAdapter(this, searchType,
					listDrivingRouteResult, listTransitRouteResult,
					listWalkingRouteResult, qiString, shiString);
			listView.setAdapter(adapter);
			System.out.println(listWalkingRouteResult.size());
		}

	}

	@Override
	public void onGetTransitRouteResult(TransitRouteResult result) {

		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(MapRouteResultNewActivity.this, "抱歉，未找到结果",
					Toast.LENGTH_SHORT).show();
		}
		if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
			return;
		}
		if (result.error == SearchResult.ERRORNO.NO_ERROR) {
			nodeIndex = -1;
			searchType = 1;
			listTransitRouteResult = result.getRouteLines();
			adapter = new MapRouteResultNewAdapter(this, searchType,
					listDrivingRouteResult, listTransitRouteResult,
					listWalkingRouteResult, qiString, shiString);
			listView.setAdapter(adapter);
			System.out.println(listTransitRouteResult.size());
		}
	}

	@Override
	public void onGetDrivingRouteResult(DrivingRouteResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(MapRouteResultNewActivity.this, "抱歉，未找到结果",
					Toast.LENGTH_SHORT).show();
		}
		if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
			return;
		}
		if (result.error == SearchResult.ERRORNO.NO_ERROR) {
			nodeIndex = -1;
			searchType = 0;
			listDrivingRouteResult = result.getRouteLines();
			adapter = new MapRouteResultNewAdapter(this, searchType,
					listDrivingRouteResult, listTransitRouteResult,
					listWalkingRouteResult, qiString, shiString);
			listView.setAdapter(adapter);
			System.out.println(listDrivingRouteResult.size());
		}

	}

	// 定制RouteOverly
	private class MyDrivingRouteOverlay extends DrivingRouteOverlay {
		public MyDrivingRouteOverlay(BaiduMap baiduMap) {
			super(baiduMap);
		}
		@Override
		public BitmapDescriptor getStartMarker() {
			if (useDefaultIcon) {
				return BitmapDescriptorFactory
						.fromResource(R.drawable.ic_launcher);
			}
			return null;
		}
		@Override
		public BitmapDescriptor getTerminalMarker() {
			if (useDefaultIcon) {
				return BitmapDescriptorFactory
						.fromResource(R.drawable.ic_launcher);
			}
			return null;
		}
	}
	public class MyWalkingRouteOverlay extends WalkingRouteOverlay {

		public MyWalkingRouteOverlay(BaiduMap baiduMap) {
			super(baiduMap);
		}
		@Override
		public BitmapDescriptor getStartMarker() {
			if (useDefaultIcon) {
				return BitmapDescriptorFactory
						.fromResource(R.drawable.ic_launcher);
			}
			return null;
		}
		@Override
		public BitmapDescriptor getTerminalMarker() {
			if (useDefaultIcon) {
				return BitmapDescriptorFactory
						.fromResource(R.drawable.ic_launcher);
			}
			return null;
		}
	}
	private class MyTransitRouteOverlay extends TransitRouteOverlay {

		public MyTransitRouteOverlay(BaiduMap baiduMap) {
			super(baiduMap);
		}
		@Override
		public BitmapDescriptor getStartMarker() {
			if (useDefaultIcon) {
				return BitmapDescriptorFactory
						.fromResource(R.drawable.ic_launcher);
			}
			return null;
		}
		@Override
		public BitmapDescriptor getTerminalMarker() {
			if (useDefaultIcon) {
				return BitmapDescriptorFactory
						.fromResource(R.drawable.ic_launcher);
			}
			return null;
		}
	}
	@Override
	public void onMapClick(LatLng arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public boolean onMapPoiClick(MapPoi arg0) {
		// TODO Auto-generated method stub
		return false;
	}
}
