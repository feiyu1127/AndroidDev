package com.yucheng.byxf.mini.map;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.adapter.MapListAdapter;
import com.yucheng.byxf.mini.ui.BaseActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;

public class MapListActivity extends BaseActivity {

	private List<BranchLocation> list = new ArrayList<BranchLocation>();
	private List<String> list_pro = new ArrayList<String>();
	private List<String> list_city = new ArrayList<String>();
		
	private Button bt_inquire;//查
	private LinearLayout lin_search;
	private MapListAdapter adapter;
	private ListView listView;
	private ImageView back;
	private Spinner sp_1;
	private Spinner sp_2;
	private List<String> data_list_city;
	private List<String> data_list;
	private List<String> list_null=new ArrayList<String>();
	private ArrayAdapter<String> arr_adapter;
	private ArrayAdapter<String> arr_city_adapter;
	private ImageView bt_search;
	private boolean isSearch =false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_list_activity);

		initView();
		new Thread(){
			public void run() {
				Looper.prepare();
				myHandler.sendEmptyMessage(0);
				//myHandler.sendEmptyMessage(3); wz暂时 评比这个功能
				Looper.loop();
			};
		}.start();
	}
	
	Handler myHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0) {
				//Contents.province="123";
				if(Contents.province!=null&&!"".equals(Contents.province)){
					new NetLocationAsyncTask().execute(Contents.province,Contents.city);
				}else{
					new NetLocationAsyncTask().execute("","");
				}
				new GetProvinceAsyncTask().execute();
			}
			if(msg.what==1){
				if(sp_1.getSelectedItem().toString().equals("全部")){
					
			        arr_city_adapter= new ArrayAdapter<String>(MapListActivity.this, android.R.layout.simple_spinner_item, list_null);
			        //设置样式
			        arr_city_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			        //加载适配器
			        sp_2.setAdapter(arr_city_adapter);
					return;
				};
				new GetCityAsyncTask().execute(sp_1.getSelectedItem().toString());
			}
			if(msg.what==2){
				Toast.makeText(MapListActivity.this, "请选择省份", Toast.LENGTH_LONG).show();
			}
			if(msg.what==3){
				new NetLocationAsyncTask().execute("","");
			}
		};
	};

	private void initView() {
		lin_search=(LinearLayout) findViewById(R.id.lin_search);
		bt_search=(ImageView) findViewById(R.id.bt_search);
		bt_search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(!isSearch){
					lin_search.setVisibility(View.VISIBLE);
					isSearch=true;
				}else{
					lin_search.setVisibility(View.GONE);
					isSearch=false;
				}
				// TODO 自动生成的方法存根
			}
		});
	//	bt_search.setVisibility(View.GONE);
		sp_1 = (Spinner) findViewById(R.id.spinner_sheng);		
		sp_2 = (Spinner) findViewById(R.id.spinner_shi);		
		listView = (ListView) findViewById(R.id.listview);
		back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MapListActivity.this, OverlayMapActivity.class);
				intent.putExtra("list", list.get(position));
				startActivity(intent);
			}
		});

//		s1.getSelectedItem()).toString()
		sp_1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO 自动生成的方法存根
				myHandler.sendEmptyMessage(1);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO 自动生成的方法存根
				
			}
		});
		bt_inquire=(Button) findViewById(R.id.bt_inquire);
		bt_inquire.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				String str_sheng="";
				String str_city="";
				str_sheng=sp_1.getSelectedItem().toString();
				str_city=sp_2.getSelectedItem().toString();
					
				
				if(str_sheng==null||"".equals(str_sheng)){
					myHandler.sendEmptyMessage(2);
					return;
				}
				
				if(sp_1.getSelectedItem().toString().equals("全部")){
					
					new NetLocationAsyncTask().execute("","");
				}else if(!sp_1.getSelectedItem().toString().equals("全部")&&sp_2.getSelectedItem().toString().equals("全部")){
					new NetLocationAsyncTask().execute(str_sheng,"");
				}else{
				new NetLocationAsyncTask().execute(str_sheng,str_city);
				}
				lin_search.setVisibility(View.GONE);
			}
		});
		
		list_null.add(0,"全部");
	}
	//默认请求的列表
	class NetLocationAsyncTask extends AsyncTask<String, Object, Object> {
    @Override
    protected Object doInBackground(String... params) {
      HttpHelper httpHelper = HttpHelper.getInstance(MapListActivity.this);
      List<NameValuePair> arg = new ArrayList<NameValuePair>();
      arg.add(new BasicNameValuePair("province", params[0]));
      arg.add(new BasicNameValuePair("city", params[1]));
      return httpHelper.post(ContantsAddress.GET_NetInfo, arg, BranchLocationResponse.class);
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
				BranchLocationResponse response = (BranchLocationResponse) result;
				if (response.getCode() == 0) {
					list = response.getResult();
					System.out.println("网点--"+list);
					adapter = new MapListAdapter(MapListActivity.this, list);
					listView.setAdapter(adapter);
				} else if(response.getCode() == 40101){
					new NetLocationAsyncTask().execute("","");
				}else {
					Toast.makeText(MapListActivity.this, response.getMsg(), Toast.LENGTH_LONG)
							.show();
				}
			} else {
				Toast.makeText(MapListActivity.this, getResources().getString(R.string.no_network), Toast.LENGTH_LONG).show();
			}
		}
		
	}
	
	//请求省份的
	class GetProvinceAsyncTask extends AsyncTask<String, Object, Object> {
	    @Override
	    protected Object doInBackground(String... params) {
	      HttpHelper httpHelper = HttpHelper.getInstance(MapListActivity.this);
	      List<NameValuePair> arg = new ArrayList<NameValuePair>();
	      return httpHelper.post(ContantsAddress.GET_Net_Province, arg, LocationProvinceResponse.class);
	    }

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
			//	showProgressDialog();
			}

			@Override
			protected void onPostExecute(Object result) {
			//	dismissProgressDialog();
				super.onPostExecute(result);
				if (result != null) {
					LocationProvinceResponse response = (LocationProvinceResponse) result;
					if (response.getCode() == 0) {
						list_pro = response.getResult();
						list_pro.add(0, "全部");
						System.out.println("网点--"+list_pro);
						   //适配器
				        arr_adapter= new ArrayAdapter<String>(MapListActivity.this, android.R.layout.simple_spinner_item, list_pro);
				        //设置样式
				        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				        //加载适配器
				        sp_1.setAdapter(arr_adapter);
				        
				  
					} else {
						Toast.makeText(MapListActivity.this, response.getMsg(), Toast.LENGTH_LONG)
								.show();
					}
				} else {
					Toast.makeText(MapListActivity.this, getResources().getString(R.string.no_network), Toast.LENGTH_LONG).show();
				}
			}
			
		}
	
	//请求city的
	class GetCityAsyncTask extends AsyncTask<String, Object, Object> {
	    @Override
	    protected Object doInBackground(String... params) {
	      HttpHelper httpHelper = HttpHelper.getInstance(MapListActivity.this);
	      List<NameValuePair> arg = new ArrayList<NameValuePair>();
	     
	      arg.add(new BasicNameValuePair("province", params[0]));
	      return httpHelper.post(ContantsAddress.GET_Net_City, arg, LocationCityResponse.class);
	    }

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
			//	showProgressDialog();
			}

			@Override
			protected void onPostExecute(Object result) {
			//	dismissProgressDialog();
				super.onPostExecute(result);
				if (result != null) {
					LocationCityResponse response = (LocationCityResponse) result;
					if (response.getCode() == 0) {
						list_city = response.getResult();
						list_city.add(0, "全部");
						System.out.println("网点--"+list_city);
						   //适配器
				        arr_city_adapter= new ArrayAdapter<String>(MapListActivity.this, android.R.layout.simple_spinner_item, list_city);
				        //设置样式
				        arr_city_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				        //加载适配器
				        sp_2.setAdapter(arr_city_adapter);

					} else {
						Toast.makeText(MapListActivity.this, response.getMsg(), Toast.LENGTH_LONG)
								.show();
					}
				} else {
					Toast.makeText(MapListActivity.this, getResources().getString(R.string.no_network), Toast.LENGTH_LONG).show();
				}
			}
			
		}
	
}
