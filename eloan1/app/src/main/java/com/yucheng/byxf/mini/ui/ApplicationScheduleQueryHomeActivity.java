package com.yucheng.byxf.mini.ui;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.adapter.ApplicationScheduleQueryNAdaper;
import com.yucheng.byxf.mini.message.ApplicationScheduleQueryResponse;
import com.yucheng.byxf.mini.message.ApplicationScheduleQueryResult;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.GetSystemDate;
import com.yucheng.byxf.mini.views.XListView;
import com.yucheng.byxf.mini.views.XListView.IXListViewListener;

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
import android.widget.ListView;
import android.widget.Toast;

public class ApplicationScheduleQueryHomeActivity extends BaseActivity
		implements OnClickListener, IXListViewListener {
	private List<ApplicationScheduleQueryResponse> ACTVList = new ArrayList<ApplicationScheduleQueryResponse>();
	private List<ApplicationScheduleQueryResponse> ACTVListMore = new ArrayList<ApplicationScheduleQueryResponse>();
	private List<ApplicationScheduleQueryResponse> NACTVList = new ArrayList<ApplicationScheduleQueryResponse>();
	private List<ApplicationScheduleQueryResponse> NACTVListMore = new ArrayList<ApplicationScheduleQueryResponse>();
	private boolean isLoan = true;// 判断选择的是mini贷还是轻松贷

	private Button miniButton;
	private Button commonButton;
	// private ApplicationScheduleQueryEloanAdaper adaperEloan;// 轻松贷进度查询适配器
	private XListView listView;
	private XListView loan_listView;
	private ImageView application_schedule_null;

	private int listPosition;

	private ApplicationScheduleQueryNAdaper nAdaper;
	private ApplicationScheduleQueryNAdaper nAdaper1;

	private String type = "NACTV";

	private ApplicationScheduleQueryHomeActivity activity;

	private boolean isMoreACTV = true;
	private boolean isMoreNACTV = true;

	private int pageNoACTV = 1;
	private int totalPagesACTV;
	private int currentPageACTV = 1;

	private int pageNoNACTV = 1;
	private int totalPagesNACTV;
	private int currentPageNACTV = 1;

	Handler mHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.application_schedule_query_home_activity);
		activity = new ApplicationScheduleQueryHomeActivity();
		ImageView back = (ImageView) findViewById(R.id.back);

		mHandler = new Handler();
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		initview();
	}

	private void onLoad() {
		if ("ACTV".equals(type)) {
			listView.stopRefresh();
			listView.stopLoadMore();
			GetSystemDate date = new GetSystemDate();
			listView.setRefreshTime(date.getSystemYear() + "-"
					+ date.getSystemMonth() + "-" + date.getSystemDay() + "  "
					+ date.getSystemHour() + ":" + date.getSystemMinute() + ":"
					+ date.getSystemSecond());
		} else {
			loan_listView.stopRefresh();
			loan_listView.stopLoadMore();
			GetSystemDate date = new GetSystemDate();
			loan_listView.setRefreshTime(date.getSystemYear() + "-"
					+ date.getSystemMonth() + "-" + date.getSystemDay() + "  "
					+ date.getSystemHour() + ":" + date.getSystemMinute() + ":"
					+ date.getSystemSecond());
		}

	}

	private void initview() {
		miniButton = (Button) findViewById(R.id.mini);
		commonButton = (Button) findViewById(R.id.common);
		listView = (XListView) findViewById(R.id.listview);
		loan_listView = (XListView) findViewById(R.id.loan_listview);
		application_schedule_null = (ImageView) findViewById(R.id.application_schedule_null);
		miniButton.setOnClickListener(this);
		commonButton.setOnClickListener(this);

		nAdaper = new ApplicationScheduleQueryNAdaper(ACTVListMore,
				ApplicationScheduleQueryHomeActivity.this);

		nAdaper1 = new ApplicationScheduleQueryNAdaper(NACTVList,
				ApplicationScheduleQueryHomeActivity.this);

		listView.setAdapter(nAdaper);
		loan_listView.setAdapter(nAdaper1);

		listView.setPullLoadEnable(true);
		listView.setPullRefreshEnable(false);
		listView.setXListViewListener(this);
		loan_listView.setPullLoadEnable(true);
		loan_listView.setPullRefreshEnable(false);
		loan_listView.setXListViewListener(this);

		String idNo = "";
		if (Contents.response != null && Contents.response.getResult() != null)
			idNo = Contents.response.getResult().getIdNo();
		new GetACTVApplicationSchedule().execute("20", idNo, (pageNoNACTV - 1)
				* 10 + 1 + "", "10", "NACTV", "00");
		loan_listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (position == 0) {

				} else {
					Intent intent = new Intent();
					if (NACTVList.get(position - 1).getInputSrc().equals("08")) {
						intent.setClass(
								ApplicationScheduleQueryHomeActivity.this,
								ApplicationScheduleQueryEloanActivity.class);
					} else {
						intent.setClass(
								ApplicationScheduleQueryHomeActivity.this,
								ApplicationScheduleQueryNoPhoneActivity.class);
					}
					intent.putExtra("list", NACTVList.get(position - 1));
					listPosition = position - 1;
					startActivityForResult(intent, 101);
				}
			}
		});

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				if (position == 0) {

				} else {
					Intent intent = new Intent();
					intent.setClass(ApplicationScheduleQueryHomeActivity.this,
							ApplicationScheduleQueryACTVActivity.class);
					intent.putExtra("list", ACTVList.get(position - 1));
					startActivity(intent);
				}

			}
		});
	}

	class GetACTVApplicationSchedule extends AsyncTask<String, Object, Object> {
		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(ApplicationScheduleQueryHomeActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("idType", params[0]));
			arg.add(new BasicNameValuePair("idNo", params[1]));
			arg.add(new BasicNameValuePair("beginPos", params[2]));
			arg.add(new BasicNameValuePair("number", params[3]));
			arg.add(new BasicNameValuePair("queryFlag", params[4]));
			arg.add(new BasicNameValuePair("txType", params[5]));
			return httpHelper.post(ContantsAddress.EASY_LOAN_PROGRESS, arg,
					ApplicationScheduleQueryResult.class);
		}

		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);
			if (activity != null) {
				dismissProgressDialog();
				ApplicationScheduleQueryResult response = (ApplicationScheduleQueryResult) result;
				if ("ACTV".equals(type)) {
					if (response != null) {
						if (response.getCode() == 0) {
							ACTVList = response.getResult().getResult();

							if (ACTVList.size() == 0) {
								application_schedule_null
										.setVisibility(View.VISIBLE);
								listView.setVisibility(View.GONE);
								loan_listView.setVisibility(View.GONE);
								application_schedule_null
										.setBackgroundResource(R.drawable.schedule_n);
							} else {

								totalPagesACTV = Integer.parseInt(response
										.getResult().getTalCount()) / 10;
								if (Integer.parseInt(response.getResult()
										.getTalCount()) % 10 > 0) {
									totalPagesACTV++;
								}
								System.out.println(totalPagesACTV);

								application_schedule_null
										.setVisibility(View.GONE);
								loan_listView.setVisibility(View.GONE);
								listView.setVisibility(View.VISIBLE);

								if (isMoreACTV) {
									ACTVListMore.addAll(ACTVList);
								} else {
									ACTVListMore.clear();
									ACTVListMore.addAll(ACTVList);
								}

								nAdaper = new ApplicationScheduleQueryNAdaper(
										ACTVListMore,
										ApplicationScheduleQueryHomeActivity.this);
								listView.setAdapter(nAdaper);
							}
						} else {
							Toast.makeText(
									ApplicationScheduleQueryHomeActivity.this,
									response.getMsg(), Toast.LENGTH_LONG)
									.show();
						}
					} else {
						application_schedule_null.setVisibility(View.GONE);
						// application_schedule_null
						// .setBackgroundResource(R.drawable.application_actv);
						listView.setVisibility(View.GONE);
						loan_listView.setVisibility(View.GONE);
						Toast.makeText(
								ApplicationScheduleQueryHomeActivity.this,
								getResources().getString(R.string.no_network),
								Toast.LENGTH_LONG).show();
					}
				} else {
					// N---
					if (response != null) {
						if (response.getCode() == 0) {
							NACTVList = response.getResult().getResult();
							if (NACTVList.size() > 0) {

								totalPagesNACTV = Integer.parseInt(response
										.getResult().getTalCount()) / 10;
								if (Integer.parseInt(response.getResult()
										.getTalCount()) % 10 > 0) {
									totalPagesNACTV++;
								}

								application_schedule_null
										.setVisibility(View.GONE);
								listView.setVisibility(View.GONE);
								loan_listView.setVisibility(View.VISIBLE);

								if (isMoreNACTV) {
									NACTVListMore.addAll(NACTVList);
								} else {
									NACTVListMore.clear();
									NACTVListMore.addAll(NACTVList);
								}

								nAdaper1 = new ApplicationScheduleQueryNAdaper(
										NACTVList,
										ApplicationScheduleQueryHomeActivity.this);
								loan_listView.setAdapter(nAdaper1);
							}
							// if (null == response.getFlag()
							// || "".equals(response.getFlag())) {
							// application_schedule_null.setVisibility(View.GONE);
							// listView.setVisibility(View.GONE);
							// loan_listView.setVisibility(View.VISIBLE);
							// nAdaper1 = new ApplicationScheduleQueryNAdaper(
							// NACTVList,
							// ApplicationScheduleQueryHomeActivity.this);
							// loan_listView.setAdapter(nAdaper1);
							// }else
							else {
								if ("0".equals(response.getFlag())) {
									// flag=0 5分鐘
									application_schedule_null
											.setVisibility(View.VISIBLE);
									application_schedule_null
											.setBackgroundResource(R.drawable.schedule_5min);
									listView.setVisibility(View.GONE);
									loan_listView.setVisibility(View.GONE);
								} else if ("1".equals(response.getFlag())) {
									// flag=1 圖示
									application_schedule_null
											.setVisibility(View.VISIBLE);
									application_schedule_null
											.setBackgroundResource(R.drawable.schedule_flag1);
									listView.setVisibility(View.GONE);
									loan_listView.setVisibility(View.GONE);
								} else if ("2".equals(response.getFlag())) {
									// flag=2 放款
									application_schedule_null
											.setVisibility(View.VISIBLE);
									application_schedule_null
											.setBackgroundResource(R.drawable.schedule_flag2);
									listView.setVisibility(View.GONE);
									loan_listView.setVisibility(View.GONE);
								}
							}

						}
					} else {
						application_schedule_null.setVisibility(View.GONE);
						// application_schedule_null
						// .setBackgroundResource(R.drawable.application_nactv);
						listView.setVisibility(View.GONE);
						loan_listView.setVisibility(View.GONE);
						Toast.makeText(
								ApplicationScheduleQueryHomeActivity.this,
								getResources().getString(R.string.no_network),
								Toast.LENGTH_LONG).show();
					}
				}
			}
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			if ("ACTV".equals(type)) {
				if (ACTVListMore.size() > 0) {
				} else {
					showProgressDialog();
				}
			} else {
				if (NACTVListMore.size() > 0) {
				} else {
					showProgressDialog();
				}
			}

		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		System.out.println("code===>" + resultCode);
		if (21 == resultCode) {
			String status = data.getExtras().getString("confirmStatus");
			System.out.println(status + "=======>");
			if ("Y".equals(status)) {
				NACTVList.get(listPosition).setPsSignInd(status);
				nAdaper1.notifyDataSetChanged();
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onClick(View v) {
		String str = String.valueOf(isLoan);
		Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
		if (v.getId() == R.id.mini) {
			if (isLoan) {
				application_schedule_null.setVisibility(View.GONE);
				miniButton
						.setBackgroundResource(R.drawable.title_right_selector);
				miniButton.setTextColor(Color.parseColor("#ffffff"));
				commonButton
						.setBackgroundResource(R.drawable.title_left_normal);
				commonButton.setTextColor(Color.parseColor("#595959"));
				isLoan = !isLoan;
				loan_listView.setVisibility(View.GONE);
				type = "ACTV";
				if (ACTVList.size() == 0) {
					listView.setVisibility(View.GONE);
					// new GetACTVApplicationSchedule().execute("20",
					// "110108197411271838",
					// (pageNoACTV - 1) * 5 + 1 + "", "5", "ACTV", "00");
					String idNo = "";
					if (Contents.response != null
							&& Contents.response.getResult() != null)
						idNo = Contents.response.getResult().getIdNo();
					new GetACTVApplicationSchedule().execute("20", idNo,
							(pageNoACTV - 1) * 10 + 1 + "", "10", "ACTV", "00");
				} else {
					application_schedule_null.setVisibility(View.GONE);
					listView.setVisibility(View.VISIBLE);
					nAdaper1 = new ApplicationScheduleQueryNAdaper(
							ACTVListMore,
							ApplicationScheduleQueryHomeActivity.this);
					listView.setAdapter(nAdaper1);
				}

			}
		} else if (v.getId() == R.id.common) {

			if (!isLoan) {
				miniButton.setBackgroundResource(R.drawable.title_right_normal);
				commonButton
						.setBackgroundResource(R.drawable.title_left_selector);
				commonButton.setTextColor(Color.parseColor("#ffffff"));
				miniButton.setTextColor(Color.parseColor("#595959"));
				application_schedule_null.setVisibility(View.GONE);
				listView.setVisibility(View.GONE);
				loan_listView.setVisibility(View.VISIBLE);
				isLoan = !isLoan;
				type = "NACTV";
				if (NACTVList.size() == 0) {
					String idNo = "";
					if (Contents.response != null
							&& Contents.response.getResult() != null)
						idNo = Contents.response.getResult().getIdNo();
					new GetACTVApplicationSchedule().execute("20", idNo,
							(pageNoNACTV - 1) * 10 + 1 + "", "10", "NACTV",
							"00");
				} else {
					nAdaper = new ApplicationScheduleQueryNAdaper(
							NACTVListMore,
							ApplicationScheduleQueryHomeActivity.this);
					loan_listView.setAdapter(nAdaper);
				}
			}
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		activity = null;
	}

	@Override
	public void onRefresh() {

//		mHandler.postDelayed(new Runnable() {
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//
//				if ("ACTV".equals(type)) {
//					pageNoACTV = 1;
//					currentPageACTV = 1;
//					isMoreACTV = false;
//					ACTVList.clear();
//					String idNo = "";
//					if (Contents.response != null
//							&& Contents.response.getResult() != null)
//						idNo = Contents.response.getResult().getIdNo();
//					new GetACTVApplicationSchedule().execute("20", idNo,
//							(pageNoACTV - 1) * 10 + 1 + "", "10", "ACTV", "00");
//					nAdaper1.notifyDataSetChanged();
//
//				} else {
//					pageNoNACTV = 1;
//					currentPageNACTV = 1;
//					isMoreNACTV = false;
//					NACTVList.clear();
//					String idNo = "";
//					if (Contents.response != null
//							&& Contents.response.getResult() != null)
//						idNo = Contents.response.getResult().getIdNo();
//					new GetACTVApplicationSchedule().execute("20", idNo,
//							(pageNoNACTV - 1) * 10 + 1 + "", "10", "NACTV",
//							"00");
//					nAdaper.notifyDataSetChanged();
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
				// TODO Auto-generated method stub
				if ("ACTV".equals(type)) {
					if (currentPageACTV < totalPagesACTV) {
						pageNoACTV = currentPageACTV + 1;
						isMoreACTV = true;
						currentPageACTV++;
						// new
						// GetACTVApplicationSchedule().execute("20","110108197411271838",
						// (pageNoACTV - 1) * 5 + 1 + "", "5", "ACTV",
						// "00");
						String idNo = "";
						if (Contents.response != null
								&& Contents.response.getResult() != null)
							idNo = Contents.response.getResult().getIdNo();
						new GetACTVApplicationSchedule().execute("20", idNo,
								(pageNoACTV - 1) * 10 + 1 + "", "10", "ACTV",
								"00");
						nAdaper1.notifyDataSetChanged();
					}
					onLoad();
				} else {
					if (currentPageNACTV < totalPagesNACTV) {
						pageNoNACTV = currentPageNACTV + 1;
						isMoreNACTV = true;
						currentPageACTV++;
						String idNo = "";
						if (Contents.response != null
								&& Contents.response.getResult() != null)
							idNo = Contents.response.getResult().getIdNo();
						new GetACTVApplicationSchedule().execute("20", idNo,
								(pageNoNACTV - 1) * 10 + 1 + "", "10", "NACTV",
								"00");
						nAdaper.notifyDataSetChanged();
					}
					onLoad();
				}

			}
		}, 2000);
	}

}