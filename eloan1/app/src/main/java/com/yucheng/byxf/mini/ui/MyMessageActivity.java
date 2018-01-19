package com.yucheng.byxf.mini.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.adapter.SystemMsgAdapter;
import com.yucheng.byxf.mini.map.ActWebviewActivity;
import com.yucheng.byxf.mini.message.OtherMessageResult;
import com.yucheng.byxf.mini.message.SystemNotice;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.GetSystemDate;
import com.yucheng.byxf.mini.views.XListView;
import com.yucheng.byxf.mini.views.XListView.IXListViewListener;

public class MyMessageActivity extends BaseActivity implements
		IXListViewListener,OnClickListener {

	private SystemMsgAdapter adapter;
	private SystemMsgAdapter otherAdapter;
	private List<SystemNotice> list = new ArrayList<SystemNotice>();
	private List<SystemNotice> listMore = new ArrayList<SystemNotice>();
	private List<SystemNotice> otherList = new ArrayList<SystemNotice>();
	private List<SystemNotice> otherListMore = new ArrayList<SystemNotice>();
	private Button mycommon,mymini;
	private XListView sysMsgListView;
	private XListView other_sysMsgListView;
	private ImageView sysMsg_schedule_null;
	private boolean isLoan = true;
	private boolean isJPush;
	private boolean isMore = true;
	private boolean isMoreOther = true;
	private int countPage = 1;
	private final int size = 3; 
	Handler mHandler;
	private String type = "common";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.system_messange);

		Intent intent = getIntent();
		isJPush = intent.getBooleanExtra("isJPush", false);
		mycommon = (Button) findViewById(R.id.mycommon);
		mymini = (Button) findViewById(R.id.mymini);
		sysMsgListView = (XListView) findViewById(R.id.sysMsgList);
		other_sysMsgListView = (XListView) findViewById(R.id.other_sysMsgList);
		sysMsg_schedule_null = (ImageView) findViewById(R.id.sysMsg_schedule_null);
		mycommon.setOnClickListener(this);
		mymini.setOnClickListener(this);
		mHandler = new Handler();
		ImageView back = (ImageView) findViewById(R.id.mybackhuodonggonggao);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		init();
	}

	private void init() {
		adapter = new SystemMsgAdapter(list, getApplicationContext());
		otherAdapter = new SystemMsgAdapter(otherList, getApplicationContext());
		sysMsgListView.setAdapter(adapter);
		other_sysMsgListView.setAdapter(otherAdapter);
		sysMsgListView.setPullLoadEnable(true);
		sysMsgListView.setPullRefreshEnable(false);
		sysMsgListView.setXListViewListener(this);
		other_sysMsgListView.setPullLoadEnable(true);
		other_sysMsgListView.setPullRefreshEnable(false);
		other_sysMsgListView.setXListViewListener(this);
		
		sysMsgListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				intent.setClass(MyMessageActivity.this,
						ActWebviewActivity.class);
				intent.putExtra("message", list.get(position - 1));
				startActivity(intent);
			}
		});
		other_sysMsgListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				intent.setClass(MyMessageActivity.this,
						ActWebviewActivity.class);
				intent.putExtra("message", otherList.get(position - 1));
				startActivity(intent);
			}
		});
		sysMsg_schedule_null.setVisibility(View.VISIBLE);
		sysMsg_schedule_null
		.setBackgroundResource(R.drawable.common_msg);
		sysMsgListView.setVisibility(View.GONE);
		other_sysMsgListView.setVisibility(View.GONE);
		if(isJPush){
			mymini.setBackgroundResource(R.drawable.title_right_selector);
			mymini.setTextColor(Color.parseColor("#ffffff"));
			mycommon.setBackgroundResource(R.drawable.title_left_normal);
			mycommon.setTextColor(Color.parseColor("#595959"));
			isLoan = !isLoan;
			type = "other";
			sysMsgListView.setVisibility(View.GONE);
			other_sysMsgListView.setVisibility(View.VISIBLE);
			if (otherList.size() == 0) {
				new GetUserOtherMsg().execute(countPage+"", size+"");
			} else {
				otherAdapter = new SystemMsgAdapter(
						otherList,
						MyMessageActivity.this);
				other_sysMsgListView.setAdapter(otherAdapter);
			}
		}
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.mymini:
			if (isLoan) {
				mymini.setBackgroundResource(R.drawable.title_right_selector);
				mymini.setTextColor(Color.parseColor("#ffffff"));
				mycommon.setBackgroundResource(R.drawable.title_left_normal);
				mycommon.setTextColor(Color.parseColor("#595959"));
				isLoan = !isLoan;
				type = "other";
				sysMsgListView.setVisibility(View.GONE);
				other_sysMsgListView.setVisibility(View.VISIBLE);
				if (otherList.size() == 0) {
					new GetUserOtherMsg().execute(countPage+"", size+"");
				} else {
					otherAdapter = new SystemMsgAdapter(
							otherList,
							MyMessageActivity.this);
					other_sysMsgListView.setAdapter(otherAdapter);
				}
			}
			break;
        case R.id.mycommon:
        	if (!isLoan) {
        		mymini.setBackgroundResource(R.drawable.title_right_normal);
        		mymini.setTextColor(Color.parseColor("#595959"));
        		mycommon.setBackgroundResource(R.drawable.title_left_selector);
        		mycommon.setTextColor(Color.parseColor("#ffffff"));
				sysMsg_schedule_null.setVisibility(View.VISIBLE);
				sysMsgListView.setVisibility(View.GONE);
				other_sysMsgListView.setVisibility(View.GONE);
				sysMsg_schedule_null
				.setBackgroundResource(R.drawable.common_msg);
				isLoan = !isLoan;
				type = "common";
			}
			break;
		default:
			break;
		}
	}
	
	// --==========================查询用户其他消息===========================================
	class GetUserOtherMsg extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(MyMessageActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("countPage", params[0]));
			arg.add(new BasicNameValuePair("size", params[1]));
			return httpHelper.post(ContantsAddress.USER_OTHER_MSG, arg,
					OtherMessageResult.class);
		}
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog();
		}
		
		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);
			dismissProgressDialog();
			OtherMessageResult response = (OtherMessageResult) result;
			if (response != null) {
				if (response.getCode() == 0) {
					otherList = response.getResult().getUserother();

					if (otherList.size() == 0) {
						sysMsg_schedule_null
								.setVisibility(View.VISIBLE);
						sysMsgListView.setVisibility(View.GONE);
						other_sysMsgListView.setVisibility(View.GONE);
						sysMsg_schedule_null
								.setBackgroundResource(R.drawable.other_msg);
					} else {

//						if(otherList.size() >= countPage*size + size){
//							countPage ++;
//						}
						if (isMoreOther) {
							otherListMore.addAll(otherList);
						} else {
							otherListMore.clear();
							otherListMore.addAll(otherList);
						}
						otherAdapter = new SystemMsgAdapter(
								otherListMore,
								MyMessageActivity.this);
						other_sysMsgListView.setAdapter(otherAdapter);
					}
				} else {
					sysMsg_schedule_null
					.setBackgroundResource(R.drawable.other_msg);
					Toast.makeText(
							MyMessageActivity.this,
							response.getMsg(), Toast.LENGTH_LONG)
							.show();
				}
			} else {
				sysMsg_schedule_null.setVisibility(View.GONE);
				sysMsgListView.setVisibility(View.GONE);
				other_sysMsgListView.setVisibility(View.GONE);
				sysMsg_schedule_null
				.setBackgroundResource(R.drawable.other_msg);
				Toast.makeText(
						MyMessageActivity.this,
						getResources().getString(R.string.no_network),
						Toast.LENGTH_LONG).show();
			}
		}

	}
		
	@Override
	public void onRefresh() {

//		mHandler.postDelayed(new Runnable() {
//
//			@Override
//			public void run() {
//
//				new GetUserOtherMsg().execute(countPage+"", size+"");
//				if ("common".equals(type)) {
//					isMore = false;
//					otherListMore.clear();
//					adapter.notifyDataSetChanged();
//
//				} else {
//					isMoreOther = false;
//					listMore.clear();
//					otherAdapter.notifyDataSetChanged();
//				}
//				onLoad();
//			}
//		}, 2000);
	}

	@Override
	public void onLoadMore() {
		mHandler.postDelayed(new Runnable() {

			@Override
			public void run() {
				if ("common".equals(type)) {
					if (list.size() >= countPage*size + size) {
						countPage++;
						isMore = true;
						sysMsg_schedule_null
						.setBackgroundResource(R.drawable.common_msg);
//						new GetUserOtherMsg().execute(countPage+"", size+"");
						adapter.notifyDataSetChanged();
					}
					onLoad();
				} else {
					if (otherList.size() >= countPage*size + size) {
						countPage++;
						isMoreOther = true;
						new GetUserOtherMsg().execute(countPage+"", size+"");
						otherAdapter.notifyDataSetChanged();
					}
					onLoad();
				}

			}
		}, 2000);
	}
	
	private void onLoad() {
		if ("common".equals(type)) {
			sysMsgListView.stopRefresh();
			sysMsgListView.stopLoadMore();
			GetSystemDate date = new GetSystemDate();
			sysMsgListView.setRefreshTime(date.getSystemYear() + "-"
					+ date.getSystemMonth() + "-" + date.getSystemDay() + "  "
					+ date.getSystemHour() + ":" + date.getSystemMinute() + ":"
					+ date.getSystemSecond());
		} else {
			other_sysMsgListView.stopRefresh();
			other_sysMsgListView.stopLoadMore();
			GetSystemDate date = new GetSystemDate();
			other_sysMsgListView.setRefreshTime(date.getSystemYear() + "-"
					+ date.getSystemMonth() + "-" + date.getSystemDay() + "  "
					+ date.getSystemHour() + ":" + date.getSystemMinute() + ":"
					+ date.getSystemSecond());
		}

	}
}
