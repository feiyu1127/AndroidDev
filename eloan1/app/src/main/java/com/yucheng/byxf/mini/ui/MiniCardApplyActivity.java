package com.yucheng.byxf.mini.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.adapter.ApplicationScheduleQueryAdaper;
import com.yucheng.byxf.mini.adapter.ApplicationScheduleQueryNAdaper;
import com.yucheng.byxf.mini.message.MiniCardActivateList;
import com.yucheng.byxf.mini.message.MiniCardActivateMessage;
import com.yucheng.byxf.mini.rapidly.RepidlyLoanInfoContractBook;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.views.XListView;
import com.yucheng.byxf.mini.views.XListView.IXListViewListener;
import com.yucheng.byxf.util.LogManager;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MiniCardApplyActivity extends BaseActivity implements
		OnClickListener, IXListViewListener {
	private List<MiniCardActivateList> ListMiniApply = new ArrayList<MiniCardActivateList>();
	private ListView listViewMini;
	private int listPosition;
	private MiniCardApplyActivity activity;
	private ApplicationScheduleQueryAdaper nAdapter;
	private ImageView imgView;
	Dialog dialog;
	private ImageView back;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mini_card_apply_activity);
		activity = new MiniCardApplyActivity();
		initview();
	}

	private void initview() {
		back=(ImageView) findViewById(R.id.back);
		imgView=(ImageView) findViewById(R.id.imgView);
		listViewMini = (ListView) findViewById(R.id.loan_mini_listview);
		nAdapter = new ApplicationScheduleQueryAdaper(ListMiniApply,
				MiniCardApplyActivity.this);
		listViewMini.setAdapter(nAdapter);
		new GetMiniApplyAsyncTask().execute("110105198802101153");

		listViewMini.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				intent.setClass(MiniCardApplyActivity.this,
						ApplicationScheduleQueryActivity.class);
				intent.putExtra("list", ListMiniApply.get(position));
				listPosition = position;
				startActivity(intent);
			}
		});
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
			finish();
			}
		});
	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public void onRefresh() {
		// TODO 自动生成的方法存根

	}

	@Override
	public void onLoadMore() {
		// TODO 自动生成的方法存根

	}

	class GetMiniApplyAsyncTask extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {
			// TODO 自动生成的方法存根
			HttpHelper httpHelper = HttpHelper
					.getInstance(MiniCardApplyActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("idNo", params[0]));
			return httpHelper.post(ContantsAddress.MINIAPPLY, arg,
					MiniCardActivateMessage.class);
		}

		@Override
		protected void onPostExecute(Object result) {
			// TODO 自动生成的方法存根
			super.onPostExecute(result);
			dismissProgressDialog();
			MiniCardActivateMessage response = (MiniCardActivateMessage) result;
			if (response != null) {
				if (response.getCode() == 0) {
					ListMiniApply = (List<MiniCardActivateList>) response
							.getResult();

					nAdapter = new ApplicationScheduleQueryAdaper(
							ListMiniApply, MiniCardApplyActivity.this);

					listViewMini.setAdapter(nAdapter);

				}else if(response.getCode()==2){
					listViewMini.setVisibility(View.GONE);
					imgView.setVisibility(View.VISIBLE);
				}
				else
				{
					
				}
			} else {
				//
				dialog = DialogUtil.showDialogOneButton(
						MiniCardApplyActivity.this, getResources().getString(R.string.no_network),
						new OnClickListener() {

							@Override
							public void onClick(View v) {
								Intent it1 = new Intent(
										MiniCardApplyActivity.this,
										HomeActivity.class);
								startActivity(it1);
								finish();
							}
						});
			}

		}
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog();
		}

	}
}
