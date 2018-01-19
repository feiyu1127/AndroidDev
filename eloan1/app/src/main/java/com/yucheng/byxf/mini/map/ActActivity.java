package com.yucheng.byxf.mini.map;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.adapter.HuoDongAdapter;
import com.yucheng.byxf.mini.message.ActivityNotice;
import com.yucheng.byxf.mini.message.ActivityNoticeResponse;
import com.yucheng.byxf.mini.ui.BaseAllActivity;
import com.yucheng.byxf.mini.ui.HomeActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.GetSystemDate;
import com.yucheng.byxf.mini.views.XListView;
import com.yucheng.byxf.mini.views.XListView.IXListViewListener;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class ActActivity extends BaseAllActivity implements IXListViewListener {

	private XListView actList;
	private List<ActivityNotice> list = new ArrayList<ActivityNotice>();
	private Dialog dialog;
	private ImageView backhuodonggonggao;
	private ImageView act_schedule_null;

	private HuoDongAdapter adapter;
	private int countPage = 1;
	private int size = 4;// 首次显示的条目
	private int count = 0;

	private static final Integer RET_CODE = 0;

	Handler mHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.huodong_activity);
		backhuodonggonggao = (ImageView) findViewById(R.id.backhuodonggonggao);
		act_schedule_null = (ImageView) findViewById(R.id.act_schedule_null);
		mHandler = new Handler();
		backhuodonggonggao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent inten = new Intent();
				inten.setClass(ActActivity.this, HomeActivity.class);
				startActivity(inten);
			}
		});
		actList = (XListView) findViewById(R.id.actList);
		actList.setPullLoadEnable(true);
		actList.setPullRefreshEnable(false);
		actList.setXListViewListener(this);

		new getActivityInfo().execute();
		actList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(ActActivity.this, ActWebviewActivity.class);
				intent.putExtra("url", list.get(position - 1).getHtmlName());
				startActivity(intent);
			}

		});
	}

	class getActivityInfo extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {
			// TODO Auto-generated method stub
			HttpHelper httpHelper = HttpHelper
					.getInstance(getApplicationContext());
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("countPage", countPage + ""));
			arg.add(new BasicNameValuePair("size", size + ""));
			return httpHelper.post(ContantsAddress.ACTIVITY_MENU, arg,
					ActivityNoticeResponse.class);
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			showProgressDialog();
		}

		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			ActivityNoticeResponse response = (ActivityNoticeResponse) result;
			if (response != null) {
				if (RET_CODE == response.getCode()) {
					list = response.getResult().getActivitives();
					if (0 != list.size()) {
						if (list.size() != count) {
							adapter = new HuoDongAdapter(list, ActActivity.this);
							actList.setDividerHeight(0);
							actList.setVerticalScrollbarPosition(list.size());
							actList.setAdapter(adapter);
						} else {
							Toast.makeText(getApplicationContext(),
									"没有更多数据加载！", 0).show();
						}
						act_schedule_null.setVisibility(View.GONE);
						actList.setVisibility(View.VISIBLE);
						count = list.size();
					} else {
						act_schedule_null.setVisibility(View.VISIBLE);
						actList.setVisibility(View.GONE);
					}

				} else {
					System.out.println(response.getMsg());
					Toast.makeText(getApplicationContext(),
							"获取数据失败！" + response.getMsg(), Toast.LENGTH_LONG)
							.show();
				}
			} else {
				Toast.makeText(getApplicationContext(),
						getResources().getString(R.string.no_network),
						Toast.LENGTH_LONG).show();
				act_schedule_null.setVisibility(View.VISIBLE);
				actList.setVisibility(View.GONE);
			}
		}

	}

	public void showProgressDialog() {
		// TODO Auto-generated method stub
		if (null != dialog && dialog.isShowing()) {
			dialog.dismiss();
		}
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		mHandler.postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				size = size + 2;
				new getActivityInfo().execute();
				adapter.notifyDataSetChanged();
				onLoad();
			}
		}, 2000);

	}

	private void onLoad() {
		actList.stopRefresh();
		actList.stopLoadMore();
		GetSystemDate date = new GetSystemDate();
		actList.setRefreshTime(date.getSystemYear() + "-"
				+ date.getSystemMonth() + "-" + date.getSystemDay() + "  "
				+ date.getSystemHour() + ":" + date.getSystemMinute() + ":"
				+ date.getSystemSecond());

	}

}