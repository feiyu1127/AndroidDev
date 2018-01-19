package com.yucheng.byxf.mini.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.adapter.BillHomeMiniAdapter;
import com.yucheng.byxf.mini.adapter.MiniCardBillAdapter;
import com.yucheng.byxf.mini.message.ApplicationScheduleQueryResult;
import com.yucheng.byxf.mini.message.CardInfo;
import com.yucheng.byxf.mini.message.CardNoId;
import com.yucheng.byxf.mini.message.CardNoResponse;
import com.yucheng.byxf.mini.message.DetailsCardInfo;
import com.yucheng.byxf.mini.message.GetCardNo;
import com.yucheng.byxf.mini.ui.ApplicationScheduleQueryHomeActivity.GetACTVApplicationSchedule;
import com.yucheng.byxf.mini.ui.BillHomeActivity.GetMiniBillListAsyncTask;
import com.yucheng.byxf.mini.ui.BillHomeActivity.GetRelaxBillListAsyncTask;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.views.XListView;
import com.yucheng.byxf.mini.views.XListView.IXListViewListener;
import com.yucheng.byxf.mini.xiaojinying.XiaoJinYingZhangDanActivity;
import com.yucheng.byxf.util.LogManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.Loader.OnLoadCanceledListener;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
/**
 * 我的账单
 */
public class MiniCardBillActivity extends BaseActivity implements
		OnClickListener {

	private ImageView back;
	private List<DetailsCardInfo> cardList = new ArrayList<DetailsCardInfo>();
	private Button miniButton;
	private ListView miniListView;
	private ImageView application_schedule_null;
	private MiniCardBillActivity activity;
	private boolean isMini = false;// 判断选择的是mini贷还是轻松贷
	private Handler mhandler;
	private MiniCardBillAdapter adapter;
	private boolean isMoreCard = true;

	private int pageNoCard = 1;
	private int totalPagesCard;
	private int currentPageCard = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mini_card_bill_activity);
		activity = new MiniCardBillActivity();
		handler = new Handler();
		back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		initView();
		
	}

	private void initView() {
		miniListView = (ListView) findViewById(R.id.listview);
		application_schedule_null = (ImageView) findViewById(R.id.application_schedule_null);
		adapter = new MiniCardBillAdapter(MiniCardBillActivity.this,cardList);
		miniListView.setAdapter(adapter);
		miniListView.setVisibility(View.VISIBLE);
		String idNo = Contents.response.getResult().getIdNo();
//		new GetMiniBillListAsyncTask().execute("cust","","20","13102219890528033X");
		new GetMiniBillListAsyncTask().execute("cust","","20",idNo);
		
		miniListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				intent.setClass(MiniCardBillActivity.this,
						MiniConsumeProvideBillActivity1.class);
				intent.putExtra("cardNo", cardList.get(position).getCardNo());
				startActivity(intent);
			}
		});

	}


	class GetMiniBillListAsyncTask extends AsyncTask<String, Object, Object> {
		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(MiniCardBillActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			 arg.add(new BasicNameValuePair("txTyp", params[0]));
			 arg.add(new BasicNameValuePair("cardNo", params[1]));
			 arg.add(new BasicNameValuePair("idTyp", params[2]));
			arg.add(new BasicNameValuePair("idNo", params[3]));
			return httpHelper.post(ContantsAddress.GET_CARDNO, arg,
					GetCardNo.class);
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
			if (result != null) {
				GetCardNo response = (GetCardNo) result;

				if (response.getCode() == 0) {
					cardList = response.getResult().getDetails();
					if (response.getMsg().equals("没有查询到账单")) {
						application_schedule_null.setVisibility(View.VISIBLE);
						miniListView.setVisibility(View.GONE);
					} else if (response.getMsg().equals("处理成功")) {
						application_schedule_null.setVisibility(View.GONE);
						miniListView.setVisibility(View.VISIBLE);
						adapter = new MiniCardBillAdapter(
								MiniCardBillActivity.this, cardList);
						miniListView.setAdapter(adapter);
					}
				} else {
					application_schedule_null.setVisibility(View.VISIBLE);
					miniListView.setVisibility(View.GONE);
				}
			}
		}
	}

	@Override
	public void onClick(View v) {
 	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		activity = null;
	}

 
}
