package com.yucheng.byxf.mini.map;

import java.lang.reflect.Field;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.R.integer;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.DrivingRouteOverlay;
import com.baidu.mapapi.overlayutil.OverlayManager;
import com.baidu.mapapi.overlayutil.TransitRouteOverlay;
import com.baidu.mapapi.overlayutil.WalkingRouteOverlay;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteLine;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.yucheng.byxf.mini.adapter.MapRoutePlanAdapter;
import com.yucheng.byxf.mini.ui.BaseActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.Contents;

@SuppressLint("NewApi")
public class RoutePlanNewActivity extends BaseActivity  implements
OnGetRoutePlanResultListener, OnMapClickListener {
	
	int searchType = -1; //区分 类型 驾车。步行。公交
	
	/**
	 * MapView 是地图主控件， 一个显示地图的视图，当被焦点选中时，它能捕获按键事件和触摸手势去平移和缩放地图。
	 */
	private MapView mMapView = null;
	DemoApplication app;
	// 搜索相关
	RoutePlanSearch mSearch = null; // 搜索模块，也可去掉地图模块独立使用
	
	private String latitude;
	private String longitude;
	private String endLatitude;
	private String endLongitude;
	private String city;
	private int position;
	private String qiString;
	private String shiString;

	int nodeIndex = -1;// 节点索引,供浏览节点时使用
    RouteLine route = null;
    OverlayManager routeOverlay = null;

	private int numSteps;
	 BaiduMap mBaidumap = null;
	private String st_node;
	
	//List==============
	private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

	private ListView listView;
	private MapRoutePlanAdapter adapter;
	

	private ImageView animImageView;
	private RelativeLayout layout;

	private int status = 0;

	int width, height;
	float density;

	private int layoutHeight;

	private RelativeLayout titleLayout;

	private int titleHeight;

	private int heightS = 0;

	private int zhuang = 0;

	private int marginHeight = 0;
	 private TextView popupText = null;//泡泡view
	
	private ImageView back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.routeplan_activity);
	
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		width = displayMetrics.widthPixels;
		height = displayMetrics.heightPixels;
		density = this.getResources().getDisplayMetrics().density;
		System.out.println("w--"+width+"h---"+height);
		System.out.println("--------------------------");
		
		//传递的数据
		Intent intent = getIntent();
		latitude = intent.getStringExtra("latitude");
		longitude = intent.getStringExtra("longitude");
		endLatitude = intent.getStringExtra("endLatitude");
		endLongitude = intent.getStringExtra("endLongitude");
		city = intent.getStringExtra("city");
		position = intent.getIntExtra("position",-1);
		qiString = intent.getStringExtra("qiString");
		shiString = intent.getStringExtra("shiString");
		searchType = intent.getIntExtra("searchType", -1);
		app = (DemoApplication) this.getApplication();
	
//		System.out.println("===plan>"+"latitude"+latitude);
//		System.out.println("===plan>"+"longitude"+longitude);
//		System.out.println("===plan>"+"endLatitude"+endLatitude);
//		System.out.println("===plan>"+"endLongitude"+endLongitude);
//		System.out.println("===plan>"+"city"+city);
//		System.out.println("===plan>"+"position"+position);
//		System.out.println("===plan>"+"searchType"+searchType);
//		System.out.println("===plan>"+"qiString"+qiString);
//		System.out.println("===plan>"+"shiString"+shiString);
		
		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaidumap = mMapView.getMap();
		// tv_content = (TextView) findViewById(R.id.content);
		listView = (ListView) findViewById(R.id.listview);

		titleLayout = (RelativeLayout) findViewById(R.id.title);

		int w = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		titleLayout.measure(w, h);
		titleHeight = titleLayout.getMeasuredHeight();
		mMapView.measure(w, h);
	
		
		
		// 初始化搜索模块，注册事件监听
	    mSearch = RoutePlanSearch.newInstance();
        mSearch.setOnGetRoutePlanResultListener(this);
     
        init();
        
        
        //donghua 
       // handler.sendEmptyMessage(1);
    	animImageView = (ImageView) findViewById(R.id.animImageView);
		layout = (RelativeLayout) findViewById(R.id.layout);

		animImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (status == 0) {
					// move();
					heightS = 0;
					setAnimation0();
					status = 1;
				} else if (status == 1) {
					setAnimation1();
					heightS = 0;
					status = 2;
				} else if (status == 2) {
					setAnimation2();
					heightS = 0;
					status = 0;
				}

			}
		});

		layoutHeight = (height - titleHeight - getHeight()) / 2 - 20;
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.FILL_PARENT, layoutHeight);
		layoutParams.setMargins(0, height - layoutHeight, 0, 0);
		layout.setLayoutParams(layoutParams);
		zhuang = getHeight();

		marginHeight = height - layoutHeight - zhuang - titleHeight - 20;

		System.out.println("手機型號：" + android.os.Build.MODEL);
		
		back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			finish();
			}
		});
	}
        
	private int getHeight() {
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, sbar = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			sbar = getResources().getDimensionPixelSize(x);
			return sbar;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sbar;
	}

	private void setAnimation0() {
		new Thread() {
			public void run() {
				// if (status == 0) {
				while (heightS < marginHeight) {
					layoutHeight += marginHeight;
					heightS += marginHeight;
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					handler.sendEmptyMessage(2);
				}
				// }

			};
		}.start();
	}

	private void setAnimation1() {
		new Thread() {
			public void run() {
				// if (status == 0) {

				while (heightS < (height - zhuang - titleHeight - 20 - 60)) {
					layoutHeight = 60;
					heightS += height - zhuang - titleHeight - 20 - 60;
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					handler.sendEmptyMessage(2);
				}
				// }

			};
		}.start();
	}

	private void setAnimation2() {
		new Thread() {
			public void run() {
				// if (status == 0) {

				while (heightS < ((height - titleHeight - zhuang) / 2 - 20)) {
					layoutHeight = (height - titleHeight - zhuang) / 2 - 20;
					heightS += (height - titleHeight - zhuang) / 2 - 20;
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					handler.sendEmptyMessage(2);
				}
				// }

			};
		}.start();
	}

	
	
    public void init() {
        //重置浏览节点的路线数据
        route = null;
        mBaidumap.clear();
    
        //设置起终点信息，对于tranist search 来说，城市名无意义
//        PlanNode stNode = PlanNode.withCityNameAndPlaceName("北京", "西单");
//        PlanNode enNode = PlanNode.withCityNameAndPlaceName("北京", "西直门");
      
        LatLng st = new LatLng(Double.valueOf(latitude),
				Double.valueOf(longitude));
		PlanNode stNode = PlanNode.withLocation(st);
		LatLng end = new LatLng(Double.valueOf(endLatitude),
				Double.valueOf(endLongitude));
		PlanNode enNode = PlanNode.withLocation(end);
		
        System.out.println("stNode"+stNode);
        System.out.println("enNode"+enNode);
        
        
        System.out.println(position+"<---position");
        System.out.println(searchType+"<---searchType");
        // 实际使用中请对起点终点城市进行正确的设定
        if (searchType==0) {
            mSearch.drivingSearch((new DrivingRoutePlanOption())
                    .from(stNode)
                    .to(enNode));
        } else if (searchType==1) {
            mSearch.transitSearch((new TransitRoutePlanOption())
                    .from(stNode)
                    .city("北京")
                    .to(enNode));
        } else if (searchType==2) {
        	System.out.println("点击-》walk");
            mSearch.walkingSearch((new WalkingRoutePlanOption())
                    .from(stNode)
                    .to(enNode));
        }
    }
	
	
    public void nodeClick(View v) {
        if (route == null ||
                route.getAllStep() == null) {
            return;
        }
//        }
        //获取节结果信息
        LatLng nodeLocation = null;
        String nodeTitle = null;
        Object step = route.getAllStep().get(nodeIndex);
        if (step instanceof DrivingRouteLine.DrivingStep) {
            nodeLocation = ((DrivingRouteLine.DrivingStep) step).getEntrace().getLocation();
            nodeTitle = ((DrivingRouteLine.DrivingStep) step).getInstructions();
        } else if (step instanceof WalkingRouteLine.WalkingStep) {
            nodeLocation = ((WalkingRouteLine.WalkingStep) step).getEntrace().getLocation();
            nodeTitle = ((WalkingRouteLine.WalkingStep) step).getInstructions();
        } else if (step instanceof TransitRouteLine.TransitStep) {
            nodeLocation = ((TransitRouteLine.TransitStep) step).getEntrace().getLocation();
            nodeTitle = ((TransitRouteLine.TransitStep) step).getInstructions();
        }

        if (nodeLocation == null || nodeTitle == null) {
            return;
        }
        //移动节点至中心
        mBaidumap.setMapStatus(MapStatusUpdateFactory.newLatLng(nodeLocation));
        // show popup
      //  popupText = new TextView(RoutePlanDemo.this);
        popupText.setBackgroundResource(R.drawable.popup);
        popupText.setTextColor(0xFF000000);
        popupText.setText(nodeTitle);
        mBaidumap.showInfoWindow(new InfoWindow(popupText, nodeLocation, null));
    }
	
	
	@Override
		public void onGetWalkingRouteResult(WalkingRouteResult result) {
			if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
				Toast.makeText(RoutePlanNewActivity.this, "抱歉，未找到结果",
						Toast.LENGTH_SHORT).show();
			}
			if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
				// 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
				// result.getSuggestAddrInfo()
				return;
			}
			if (result.error == SearchResult.ERRORNO.NO_ERROR) {
				nodeIndex = -1;
				searchType = 2;
				route = result.getRouteLines().get(position);
				WalkingRouteOverlay overlay = new MyWalkingRouteOverlay(mBaidumap);
				mBaidumap.setOnMarkerClickListener(overlay);
				routeOverlay = overlay;
				  result.getTaxiInfo().getDuration();
		            result.getRouteLines().get(position).getDuration();
		            result.getRouteLines().get(position).getAllStep();
		   
		            System.out.println("1--->"+  result.getTaxiInfo().getDuration());
		            //公里
		            System.out.println("2--->"+result.getRouteLines().get(position).getDuration());
		            System.out.println("3--->"+result.getRouteLines().get(position).getAllStep());
				
				// list
				System.out.println("List--->work");
				result.getTaxiInfo().getDuration();
	            result.getRouteLines().get(position).getDuration();
	            overlay.setData(result.getRouteLines().get(position));
	            result.getRouteLines().get(position).getAllStep();
				
	            try {
					overlay.addToMap();
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				overlay.zoomToSpan();
				 for(int j=0;j<result.getRouteLines().get(0).getAllStep().size();j++){
		         	   System.out.println("走路===>"+result.getRouteLines().get(0).getAllStep().get(j).getInstructions());
		         Map<String, Object> map=new HashMap<String, Object>();
		         map.put("node",result.getRouteLines().get(0).getAllStep().get(j).getInstructions()) ;
		         list.add(map);
				 }
				adapter = new MapRoutePlanAdapter(RoutePlanNewActivity.this, list,
						searchType);
				listView.setAdapter(adapter);
			}

		}

		@Override
		public void onGetTransitRouteResult(TransitRouteResult result) {

			if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
				Toast.makeText(RoutePlanNewActivity.this, "抱歉，未找到结果",
						Toast.LENGTH_SHORT).show();
			}
			if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
				// 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
				// result.getSuggestAddrInfo()
				return;
			}
			if (result.error == SearchResult.ERRORNO.NO_ERROR) {
				nodeIndex = -1;
				searchType = 1;
				System.out.println("pos-->"+position);
				route = result.getRouteLines().get(position);
				TransitRouteOverlay overlay = new MyTransitRouteOverlay(mBaidumap);
				mBaidumap.setOnMarkerClickListener(overlay);
				routeOverlay = overlay;
			
				
	            result.getTaxiInfo().getDuration();
	            result.getRouteLines().get(position).getDuration();
	            overlay.setData(result.getRouteLines().get(position));
	            result.getRouteLines().get(position).getAllStep();
	            System.out.println("lsit====>"+result.getRouteLines().get(position).getAllStep());
				
				overlay.addToMap();
				overlay.zoomToSpan();
				nodeIndex = 0;
				
				 for(int j=0;j<result.getRouteLines().get(position).getAllStep().size();j++){
		         	   System.out.println("公交===>"+result.getRouteLines().get(position).getAllStep().get(j).getInstructions());
		         Map<String, Object> map=new HashMap<String, Object>();
		         map.put("node",result.getRouteLines().get(position).getAllStep().get(j).getInstructions()) ;
		         list.add(map);
				 }
				adapter = new MapRoutePlanAdapter(RoutePlanNewActivity.this, list,
						searchType);
				listView.setAdapter(adapter);
			}
		}

		@Override
		public void onGetDrivingRouteResult(DrivingRouteResult result) {
			if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
				Toast.makeText(RoutePlanNewActivity.this, "抱歉，未找到结果",
						Toast.LENGTH_SHORT).show();
			}
			if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
				// 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
				// result.getSuggestAddrInfo()
				return;
			}
			if (result.error == SearchResult.ERRORNO.NO_ERROR) {
				nodeIndex = -1;
				searchType = 0;
				route = result.getRouteLines().get(position);
				DrivingRouteOverlay overlay = new MyDrivingRouteOverlay(mBaidumap);
				routeOverlay = overlay;
				result.getTaxiInfo().getDuration();
	            result.getRouteLines().get(position).getDuration();
	            overlay.setData(result.getRouteLines().get(position));
	            result.getRouteLines().get(position).getAllStep();
				mBaidumap.setOnMarkerClickListener(overlay);
				overlay.setData(result.getRouteLines().get(position));
				overlay.addToMap();
				overlay.zoomToSpan();
				// 重置路线节点索引，节点浏览时使用
				nodeIndex = -1;
				 for(int j=0;j<result.getRouteLines().get(0).getAllStep().size();j++){
		         	   System.out.println("开车===>"+result.getRouteLines().get(0).getAllStep().get(j).getInstructions());
		         Map<String, Object> map=new HashMap<String, Object>();
		         map.put("node",result.getRouteLines().get(0).getAllStep().get(j).getInstructions()) ;
		         list.add(map);
				 }
				adapter = new MapRoutePlanAdapter(RoutePlanNewActivity.this, list,
						searchType);
				listView.setAdapter(adapter);
			}
		}

	    /**
	     * 切换路线图标，刷新地图使其生效
	     * 注意： 起终点图标使用中心对齐.
	     */
	    public void changeRouteIcon(View v) {
	        if (routeOverlay == null) {
	            return;
	        }
	 
	        routeOverlay.removeFromMap();
	        routeOverlay.addToMap();
	    }

	  //定制RouteOverly
	    private class MyDrivingRouteOverlay extends DrivingRouteOverlay {

	        public MyDrivingRouteOverlay(BaiduMap baiduMap) {
	            super(baiduMap);
	        }

	        @Override
	        public BitmapDescriptor getStartMarker() {
	                return BitmapDescriptorFactory.fromResource(R.drawable.qidian);
	        }

	        @Override
	        public BitmapDescriptor getTerminalMarker() {
	                return BitmapDescriptorFactory.fromResource(R.drawable.zhongdian);
	        }
	    }

	    private class MyWalkingRouteOverlay extends WalkingRouteOverlay {

	        public MyWalkingRouteOverlay(BaiduMap baiduMap) {
	            super(baiduMap);
	        }

	        @Override
	        public BitmapDescriptor getStartMarker() {
	                return BitmapDescriptorFactory.fromResource(R.drawable.qidian);
	        }

	        @Override
	        public BitmapDescriptor getTerminalMarker() {
	                return BitmapDescriptorFactory.fromResource(R.drawable.zhongdian);
	        }
	    }

	    private class MyTransitRouteOverlay extends TransitRouteOverlay {

	        public MyTransitRouteOverlay(BaiduMap baiduMap) {
	            super(baiduMap);
	        }

	        @Override
	        public BitmapDescriptor getStartMarker() {
	                return BitmapDescriptorFactory.fromResource(R.drawable.qidian);
	        }

	        @Override
	        public BitmapDescriptor getTerminalMarker() {
	                return BitmapDescriptorFactory.fromResource(R.drawable.zhongdian);
	        }
	    }

	    @Override
	    public void onMapClick(LatLng point) {
	        mBaidumap.hideInfoWindow();
	    }

	    @Override
	    public boolean onMapPoiClick(MapPoi poi) {
	    	return false;
	    }

	    @Override
	    protected void onPause() {
	        mMapView.onPause();
	        super.onPause();
	    }

	    @Override
	    protected void onResume() {
	        mMapView.onResume();
	        super.onResume();
	    }

	    @Override
	    protected void onDestroy() {
	        mSearch.destroy();
	        mMapView.onDestroy();
	        super.onDestroy();
	    }

	    Handler handler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				if  (msg.what == 2) {
					RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
							RelativeLayout.LayoutParams.FILL_PARENT, layoutHeight);
					if (status == 2) {
						layoutParams.setMargins(0,
								titleHeight + mMapView.getMeasuredHeight()
										- layoutHeight, 0, 0);
					}else {
						layoutParams.setMargins(0, height - layoutHeight - zhuang,
								0, 0);
					}

					layout.setLayoutParams(layoutParams);
				}
			};
		};


	   
		public void move() {
			Animation mTranslateAnimation = new TranslateAnimation(0, 0, 0, -300);// 移动
			final int startY = (int) layout.getY();
			final int endY = startY - 300;
			mTranslateAnimation.setDuration(2000);
			mTranslateAnimation
					.setAnimationListener(new Animation.AnimationListener() {
						public void onAnimationStart(Animation animation) {
							// if (isNoMenu == false) {
							// curent.setVisibility(View.GONE);
							// }
						}

						public void onAnimationEnd(Animation animation) {
							RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
									RelativeLayout.LayoutParams.FILL_PARENT,
									RelativeLayout.LayoutParams.FILL_PARENT);
							params.setMargins(0, 300, 0, 0);
							layout.setLayoutParams(params);

						}

						public void onAnimationRepeat(Animation animation) {

						}
					});
			layout.startAnimation(mTranslateAnimation);

		}
}

