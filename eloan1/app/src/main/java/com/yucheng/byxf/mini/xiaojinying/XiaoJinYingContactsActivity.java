package com.yucheng.byxf.mini.xiaojinying;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.message.XiaojinYingRelationInfoResult;
import com.yucheng.byxf.mini.message.XiaojinYingRelationInfoResultBodyList;
import com.yucheng.byxf.mini.ui.BaseActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;

/**
 * 类名: XiaoJinYingContactsActivity</br> 描述:联系人 </br> 开发人员： weiyb</br> 创建时间：
 * 2015-7-24
 */

public class XiaoJinYingContactsActivity extends BaseActivity implements
		Serializable {

	private static final long serialVersionUID = 1L;
	private ImageView add;
	private ListView listView;
	/*
	 * private XiaoJinYingContactsAdapter list; private List<Map<String,
	 * Object>> listItems; private String[] name = { "张晓", "阿斯芬", "阿斯蒂" };
	 * private String[] mobile = { "15115111515", "15115111515", "15115111515"
	 * }; private String[] type = { "父母", "朋友", "子女" }; private String[] phone =
	 * { "010-23221222", "050-55555555", "000-22222222" }; private String[]
	 * tcontacts_type = { "直系联系人", "其他联系人", "第三联系人" };
	 */

	private XiaoJinYingContactsAdapter nadapter;
	private ImageView back;

	private List<XiaojinYingRelationInfoResultBodyList> RealitionInfo = new ArrayList<XiaojinYingRelationInfoResultBodyList>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.xiaojinyin_contactsinfo_activity);
		back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});

		add = (ImageView) findViewById(R.id.back_contacts);
		add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent it = new Intent();
				it.setClass(XiaoJinYingContactsActivity.this,
						XiaoJinYingNewContactsActivity.class);
				startActivity(it);

			}
		});
		listView = (ListView) findViewById(R.id.lv_contacts);
		nadapter = new XiaoJinYingContactsAdapter(RealitionInfo,
				XiaoJinYingContactsActivity.this);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				intent.setClass(XiaoJinYingContactsActivity.this,
						XiaoJinYingNewContactsActivity.class);

				intent.putExtra("list", RealitionInfo.get(position));
				intent.putExtra("listAll", (Serializable) RealitionInfo);
				intent.putExtra("pos", position);
				startActivity(intent);

			}
		});
	}

	// private List<Map<String, Object>> getListItems() {
	// List<Map<String, Object>> listItems = new ArrayList<Map<String,
	// Object>>();
	// for (int i = 0; i < name.length; i++) {
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("name", name[i]);
	// map.put("mobile", mobile[i]);
	// map.put("type", type[i]);
	// map.put("phone", phone[i]);
	// map.put("tcontacts_type", tcontacts_type[i]);
	// listItems.add(map);
	// }
	// return listItems;
	// }

	/**
	 * 获取联系人信息
	 */
	class ContenInfoAsyncTask extends AsyncTask<String, Object, Object> {
		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(XiaoJinYingContactsActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("cardNo", params[0]));

			return httpHelper.post(ContantsAddress.XiaoLianXiRen, arg,
					XiaojinYingRelationInfoResult.class);
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
			registerResponse3(result);
		}
	}

	private void registerResponse3(Object result) {
		XiaojinYingRelationInfoResult response3 = (XiaojinYingRelationInfoResult) result;
		if (response3 == null) {
			DialogUtil.showDialogOneButton2(XiaoJinYingContactsActivity.this,
					"服务器请求异常!");
			return;
		} else {
			if (0 == response3.getCode()) {
				RealitionInfo = response3.getResult().getBody().getDetails();
				nadapter = new XiaoJinYingContactsAdapter(RealitionInfo,
						XiaoJinYingContactsActivity.this);
				listView.setAdapter(nadapter);
			} else {
				DialogUtil.showDialogOneButton2(
						XiaoJinYingContactsActivity.this, response3.getMsg());
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		new ContenInfoAsyncTask().execute(Contents.xiaoChaXunResult2
				.getCardNo());
	}
}
