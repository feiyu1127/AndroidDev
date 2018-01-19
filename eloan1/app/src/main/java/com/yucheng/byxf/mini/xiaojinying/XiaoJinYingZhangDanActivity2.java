package com.yucheng.byxf.mini.xiaojinying;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.message.rijian.Details;
import com.yucheng.byxf.mini.message.rijian.XiaoJinYingRiJianJiaoYi;
import com.yucheng.byxf.mini.ui.BaseActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.LogUtil;
import com.yucheng.byxf.mini.views.XListView;
import com.yucheng.byxf.mini.views.XListView.IXListViewListener;

/**
 * 类名: XiaoJinYingZhangDanActivity</br> 描述: 帐单查询</br> 开发人员： weiyb</br> 创建时间：
 * 2015-7-8
 */

public class XiaoJinYingZhangDanActivity2 extends BaseActivity implements
		IXListViewListener {

	// 返回
	private ImageView backzhangdan;

	// 返回的列表
	private List<Details> mDetails = new LinkedList<Details>();

	// 分页页码
	private Integer pageNum = 1;

	// 分页步长

	private Integer pageCount = 10;

	// 是否还有一下面

	private Boolean hasNextPage = true;

	/**********************************/

	/**
	 * 帐单明细
	 */
	private XListView listView;

	private static final Integer RET_CODE = 0;

	// 0--全部 1--指定日期
	private String zhangdanleixing = "0";

	public static int currentPosition = -1;

	public static int currentPositionTF = -1;

	private String loginErrorMessage;

	private XiaoJinYingZhangDanRiJianJiaoYiAdapter mAdapter;

	/**
	 * 开始日期
	 */
	private String kaishi;

	/**
	 * 结束日期
	 */
	private String jieshu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xiaojinyin_zhangdan_activity2);
		Intent intent = getIntent();
		kaishi = intent.getStringExtra("kaishiriqi");
		jieshu = intent.getStringExtra("jieshuriqi");
		zhangdanleixing = intent.getStringExtra("zhuangtai");

		initview();
	}

	private void initview() {

		backzhangdan = (ImageView) findViewById(R.id.backzhangdan);

		listView = (XListView) findViewById(R.id.zhangdanmingxi);

		// 激活加载更多
		listView.setPullLoadEnable(true);
		// 禁止下拉刷新
		listView.setPullRefreshEnable(false);

		listView.setXListViewListener(this);

		if (mAdapter == null) {
			mAdapter = new XiaoJinYingZhangDanRiJianJiaoYiAdapter(mDetails,
					XiaoJinYingZhangDanActivity2.this);
		}
		;

		listView.setAdapter(mAdapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// 选中项
				if (currentPosition == position - 1) { // 如果重复点击 关闭
					currentPosition = -1;
				} else {
					currentPosition = position - 1;
				}

				if (mAdapter != null) { // 已出帐单适配器
					mAdapter.notifyDataSetChanged();
				}

			}
		});

		backzhangdan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 回退

				finish();
			}
		});

		handler.sendEmptyMessage(1);
	}

	Handler handler = new Handler() {

		public void handleMessage(android.os.Message msg) {

			if (msg.what == 1) {

				if (zhangdanleixing.equals("1")) {// 已选时间区间
													// 已出帐单查询
					new riqichaxunzhangdanAsyncTask().execute(

					Contents.xiaoChaXunResult2.getCardNo(),
					// "9999019000183883",
							kaishi, jieshu);
					// "9999010000096118", kaishi, jieshu);

				} else { // 如时没有选择时间区间

					// 获取当前时间
					Date datenow = new Date();

					DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					// 当前时间
					String timenow = format.format(datenow);

					String[] array = timenow.split("-");
					// 根据当前时间计算出起始时间
					int day = Integer.parseInt(array[0]);
					day = day - 1;
					String olddate = "";
					// 起始时间
					olddate = day + "-" + array[1] + "-" + array[2];

					// 请求已出帐单信息

					new riqichaxunzhangdanAsyncTask().execute(
							Contents.xiaoChaXunResult2.getCardNo(),
							// "9999010000096118",
							olddate, timenow);

				}

			}
		}
	};

	// 已出帐单查询接口
	class riqichaxunzhangdanAsyncTask extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {
			// 请求已出帐单数据
			HttpHelper httpHelper = HttpHelper
					.getInstance(XiaoJinYingZhangDanActivity2.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();

			arg.add(new BasicNameValuePair("cardNo", params[0]));
			arg.add(new BasicNameValuePair("startTime", params[1]));
			arg.add(new BasicNameValuePair("endTime", params[2]));
			arg.add(new BasicNameValuePair("pageNum", String.valueOf(pageNum)));
			arg.add(new BasicNameValuePair("pageCount", String
					.valueOf(pageCount)));
			arg.add(new BasicNameValuePair("cardGrp", "G02"));

			return httpHelper.post(
					ContantsAddress.shijianzhangdan_rijianjiaoyi, arg,
					XiaoJinYingRiJianJiaoYi.class);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			if (mDetails == null) {
				showProgressDialog();

			} else {
				if (mDetails.size() < 1) {
					showProgressDialog();
				}
			}

		}

		@Override
		protected void onPostExecute(Object result) {
			dismissProgressDialog();
			super.onPostExecute(result);

			riqizhangdanchaxunResponse(result);

			// 停止加载更多

			listView.stopLoadMore();

		}
	}

	/**
	 * 描述:处理已出帐单返回结果 </br> 开发人员：weiyb</br> 创建时间：2015-7-7</br>
	 * 
	 * @param result
	 */
	private void riqizhangdanchaxunResponse(Object result) {
		XiaoJinYingRiJianJiaoYi response = (XiaoJinYingRiJianJiaoYi) result;

		if (response == null) {
			loginErrorMessage = "服务器请求异常!";
			DialogUtil.createDialog(this, 1, loginErrorMessage);

			listView.setVisibility(View.GONE);
			return;
		} else {
			if (RET_CODE == response.getCode()) { // 加载成功后，页数自动加1

				pageNum = pageNum + 1;

				if (response.getResult() != null) {
					mDetails = response.getResult().getMsgBody().getDetails();

					if (mDetails != null)

					{
						LogUtil.i("Details", mDetails.toString());
						
						if (mDetails.size() < pageCount) {
							hasNextPage = false;

						}

						// 如果当前页是1 先清空列表
						if (pageNum <= 1) {
							mAdapter.getList().clear();
							mAdapter.getList().addAll(mDetails);
						} else {
							// 否则追加到列表后面

							mAdapter.getList().addAll(mDetails);
						}

						mAdapter.notifyDataSetChanged();
					} else {
						mAdapter = new XiaoJinYingZhangDanRiJianJiaoYiAdapter(
								mDetails, XiaoJinYingZhangDanActivity2.this);
						listView.setAdapter(mAdapter);

					}

					listView.setVisibility(View.VISIBLE);

				}

			} else {

				Toast.makeText(XiaoJinYingZhangDanActivity2.this,
						response.getMsg(), Toast.LENGTH_LONG).show();
				// Toast.makeText(XiaoJinYingZhangDanActivity.this, "暂无已出帐单",
				// Toast.LENGTH_LONG).show();
				// 如果返回码不等于0
				listView.setVisibility(View.GONE);
			}
		}
	}

	@Override
	public void onRefresh() {

	}

	@Override
	public void onLoadMore() {
		// 加载更多时

		if (hasNextPage) {
			listView.setPullLoadEnable(true);
			new riqichaxunzhangdanAsyncTask().execute(

			Contents.xiaoChaXunResult2.getCardNo(),
			// "9999019000183883",
					kaishi, jieshu);
		} else {
			listView.stopLoadMore();
			listView.setPullLoadEnable(false);
			Toast.makeText(XiaoJinYingZhangDanActivity2.this, "已无更多数据！",
					Toast.LENGTH_LONG).show();
		}

	}

}