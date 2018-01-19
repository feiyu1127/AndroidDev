package com.yucheng.byxf.mini.ui;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.apfx.utl.RegexResult;
import com.yucheng.byxf.mini.easyloan.ui.RelaxedLoanAdActivity;
import com.yucheng.byxf.mini.message.LoanIntent;
import com.yucheng.byxf.mini.message.LoanIntentResponse;
import com.yucheng.byxf.mini.message.Response;
import com.yucheng.byxf.mini.utils.CommonUtil;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.RegexCust;
import com.yucheng.byxf.msg.sale.SaleStatResponse;
import com.yucheng.byxf.service.LoanPayInfo;

public class GeneralConsumeLoanActivity extends BaseActivity implements
		OnItemSelectedListener, OnClickListener, OnWheelChangedListener {
	/**
	 * 把全国的省市区的信息以json的格式保存，解析完成后赋值为null
	 */
	private JSONObject mJsonObj;
	private WheelView mProvince;
	private WheelView mCity;
	private WheelView mArea;
	private String[] mProvinceDatas;
	private Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
	private Map<String, String[]> mAreaDatasMap = new HashMap<String, String[]>();
	private String mCurrentProviceName;
	private String mCurrentCityName;
	private String mCurrentAreaName = "";
	private Button choose_button;
	private LinearLayout city_layout;
	private ImageView general_consume_loan_menu;// menu
	private EditText general_consume_loan_name;// 姓名
	private EditText general_consume_loan_contact_tel;// 联系电话
	private EditText general_consume_loan_contact_tel2;// 联系电话2
	private EditText general_consume_loan_card_num;// 身份证号码
	private EditText general_consume_loan_loan_money_count;// 贷款金额
	private EditText general_consume_loan_address;// 居住地址
	private EditText general_consume_loan_work_unit;// 工作单位
	private TextView commit_apply_intention;// 提交申请意向
	// 验证结果
	private RegexResult result;
	// 基本信息有误
	private final int INFO_DIALOG = 4;
	private String[] tick_loans = { "请选择", "装修", "教育", "旅游", "婚庆", "其它" };
	private String save_tick_name;
	private String save_stand_degree;
	private String[] stand_degrees = { "请选择", "博士", "硕士", "本科", "大专", "高中或中专",
			"初中及以下" };
	private String marital_info;
	private String house_property_status;
	private String[] house_property_statuss = { "请选择", "自购无贷款", "自购有贷款",
			"单位宿舍", "与亲属同住", "租住", "其它" };
	private String save_dp_nature;
	private String[] dp_natures = { "请选择", "国家机关/事业单位", "国有企业", "集体企业",
			"中外合资/中外合作/外商独资", "股份制企业", "私营企业", "其它" };
	private Dialog dialog1;
	private Dialog dialog2;
	private boolean isCommit = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.general_consume_loan);
		initView();
		initJsonData();
		initListener();
	}

	private void initView() {
		general_consume_loan_menu = (ImageView) findViewById(R.id.general_consume_loan_menu);
		general_consume_loan_name = (EditText) findViewById(R.id.general_consume_loan_name);
		general_consume_loan_contact_tel = (EditText) findViewById(R.id.general_consume_loan_contact_tel);
		general_consume_loan_contact_tel2 = (EditText) findViewById(R.id.general_consume_loan_contact_tel2);
		general_consume_loan_loan_money_count = (EditText) findViewById(R.id.general_consume_loan_loan_money_count);
		general_consume_loan_address = (EditText) findViewById(R.id.general_consume_loan_address);
		general_consume_loan_work_unit = (EditText) findViewById(R.id.general_consume_loan_work_unit);
		commit_apply_intention = (TextView) findViewById(R.id.commit_apply_intention);
		general_consume_loan_card_num = (EditText) findViewById(R.id.general_consume_loan_card_num);
		// new ObtainGCLAsyncTask().execute("150205199408011317");
		if (Contents.response != null && Contents.response.getResult() != null) {
			new ObtainGCLAsyncTask().execute(Contents.response.getResult()
					.getCipCtfno());
			general_consume_loan_card_num.setText(Contents.response.getResult()
					.getIdNo());
			general_consume_loan_name.setText(Contents.response.getResult()
					.getCipNamecn());
			general_consume_loan_contact_tel.setText(Contents.response
					.getResult().getCipMobile());

			// new ObtainGCLAsyncTask().execute("44088219940519612x");

		}
	}

	 private void initData(LoanIntent mLoanIntent) {
	 if (mLoanIntent.getIndivMobile2() != null) {
		 general_consume_loan_contact_tel2.setText(mLoanIntent.getIndivMobile2());
	 }
	 if (mLoanIntent.getApplyAmt() != null) {
		 general_consume_loan_loan_money_count.setText(mLoanIntent
	 .getApplyAmt());
	 }
	 if (mLoanIntent.getIndivLiveAddr() != null) {
		 general_consume_loan_address.setText(mLoanIntent
	 .getIndivLiveAddr());
	 }
	 if (mLoanIntent.getIndivEmp() != null) {
		 general_consume_loan_work_unit.setText(mLoanIntent
				 .getIndivEmp());
	 }
	
	
	 }

	private void initListener() {
		general_consume_loan_menu.setOnClickListener(this);
		commit_apply_intention.setOnClickListener(this);
		general_consume_loan_address.setOnClickListener(this);
		// 滚动
		choose_button = (Button) findViewById(R.id.choose_button);
		city_layout = (LinearLayout) findViewById(R.id.city_layout);
		mProvince = (WheelView) findViewById(R.id.id_province);
		mCity = (WheelView) findViewById(R.id.id_city);
		mArea = (WheelView) findViewById(R.id.id_area);
		choose_button.setOnClickListener(this);
		initDatas();
		mProvince.setViewAdapter(new ArrayWheelAdapter<String>(this,
				mProvinceDatas));
		// 添加change事件
		mProvince.addChangingListener(this);
		// 添加change事件
		mCity.addChangingListener(this);
		// 添加change事件
		mArea.addChangingListener(this);
		mProvince.setVisibleItems(5);
		mCity.setVisibleItems(5);
		mArea.setVisibleItems(5);
		updateCities();
		updateAreas();
	}

	String name = "";
	String phoneNo = "";
	String phoneNo2 = "";
	String amountValue = "";

	String address = "";
	String nowDpName = "";


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.general_consume_loan_menu:
			Intent intent =new Intent();
			intent.setClass(GeneralConsumeLoanActivity.this,
					HomeActivity.class);
			startActivity(intent);
			 
			finish();
			overridePendingTransition(R.anim.transaction_item_righttoleftss,
					R.anim.transaction_item_lefttorights);
			break;
		case R.id.commit_apply_intention:
			RegexResult temp = null;

			// 姓名
			name = general_consume_loan_name.getText().toString().trim();
			temp = RegexCust.length("姓名", name, 2, 30);
			if (temp.match) {
				temp = RegexCust.chinese("姓名", name);
				if (!temp.match) {
					result = temp;
					DialogUtil.showDialogOneButton2(this, result.msg);
					general_consume_loan_name.requestFocus();
					return;
				}
			} else {
				result = temp;
				DialogUtil.showDialogOneButton2(this, result.msg);
				general_consume_loan_name.requestFocus();
				return;
			}
			// 贷款申请金额
			amountValue = general_consume_loan_loan_money_count.getText()
					.toString().trim();
			temp = RegexCust.required("申请贷款金额", amountValue);
			if (temp.match) {
				if (!RegexCust.lengthMax(amountValue, 9)) {
					result = new RegexResult(false, "请重新输入贷款金额(600-20万)");
					DialogUtil.showDialogOneButton2(this, result.msg);
					general_consume_loan_loan_money_count.requestFocus();
					return;
				}
			} else {
				result = temp;
				DialogUtil.showDialogOneButton2(this, result.msg);
				general_consume_loan_loan_money_count.requestFocus();
				return;
			}
			if (Long.parseLong(amountValue) > 50000
					|| Long.parseLong(amountValue) < 600) {
				result = new RegexResult(false, "请重新输入贷款金额(600-5万)");
				DialogUtil.showDialogOneButton2(this, result.msg);
				general_consume_loan_loan_money_count.requestFocus();
				return;
			}
			// 联系人住宅电话
			phoneNo = general_consume_loan_contact_tel.getText().toString()
					.trim();
			if (phoneNo != null && !phoneNo.equals("")) {
				temp = RegexCust.phone("联系电话", phoneNo);
				if (temp.match == false) {
					result = new RegexResult(false, "联系电话格式不对!");
					DialogUtil.showDialogOneButton2(this, result.msg);
					general_consume_loan_contact_tel.requestFocus();
					return;
				}
			} else {
				result = new RegexResult(false, "联系电话不能为空");
				DialogUtil.showDialogOneButton2(this, result.msg);
				general_consume_loan_contact_tel.requestFocus();
				return;
			}
			// 联系人住宅电话2
			phoneNo2 = general_consume_loan_contact_tel2.getText().toString()
					.trim();
			if (phoneNo2 != null && !phoneNo2.equals("")) {
				temp = RegexCust.phone("联系电话", phoneNo2);
				if (temp.match == false) {
					result = new RegexResult(false, "联系电话2格式不对!");
					DialogUtil.showDialogOneButton2(this, result.msg);
					general_consume_loan_contact_tel2.requestFocus();
					return;
				}
			}

			// 现居住地址
			address = general_consume_loan_address.getText().toString().trim();
			temp = RegexCust.required("现居住地址", address);
			if (temp.match) {
				boolean flag = RegexCust.lengthMax(address, 200);
				if (flag == false) {
					result = new RegexResult(false, "居住地址字符长度过长!");
					DialogUtil.showDialogOneButton2(this, result.msg);
					general_consume_loan_address.requestFocus();
					return;
				}
			} else {
				result = temp;
				DialogUtil.showDialogOneButton2(this, result.msg);
				general_consume_loan_address.requestFocus();
				return;
			}
			// 工作单位
			nowDpName = general_consume_loan_work_unit.getText().toString()
					.trim();
			temp = RegexCust.required("工作单位", nowDpName);
			if (temp.match) {
				if (!RegexCust.lengthMax(nowDpName, 60)) {
					result = new RegexResult(false, "工作单位字符长度过长!");
					DialogUtil.showDialogOneButton2(this, result.msg);
					general_consume_loan_work_unit.requestFocus();
					return;
				}
			} else {
				result = temp;
				DialogUtil.showDialogOneButton2(this, result.msg);
				general_consume_loan_work_unit.requestFocus();
				return;
			}
			String cipNo = "";
			if(Contents.response != null && Contents.response.getResult() != null)
				cipNo = Contents.response.getResult().getCipCtfno();
			new GeneralConsumeLoanAsyncTask().execute(cipNo,
					name, 
					phoneNo, 
					phoneNo2,
					amountValue,
					address, 
					nowDpName);
			break;
		case R.id.general_consume_loan_address:
			city_layout.setVisibility(View.VISIBLE);

			break;
		case R.id.choose_button:
			city_layout.setVisibility(View.GONE);
			// 不要区
			general_consume_loan_address.setText(mCurrentProviceName
					+ mCurrentCityName);
			// general_consume_loan_address.setText(mCurrentProviceName
			// + mCurrentCityName + mCurrentAreaName);
		default:
			break;
		}

	}

	class ObtainGCLAsyncTask extends AsyncTask<Object, Object, Object> {

		@Override
		protected Object doInBackground(Object... params_obj) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(GeneralConsumeLoanActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("idNo", (String) params_obj[0]));
			return httpHelper.post(ContantsAddress.GENERAL_CUSTOME_LOAN_QUERY,
					arg, LoanIntentResponse.class);
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
				LoanIntentResponse mIntentResponse = (LoanIntentResponse) result;
				if (mIntentResponse.getCode() == 0) {
					if (mIntentResponse.getResult() != null) {
						isCommit = true;
						initData(mIntentResponse.getResult());
					} else {
						if(Contents.response != null && Contents.response.getResult() != null) {
							general_consume_loan_name.setText(Contents.response
									.getResult().getCipNamecn());
							general_consume_loan_contact_tel
									.setText(Contents.response.getResult()
											.getCipMobile());
						}
					}
		
				} else if (mIntentResponse.getCode() == 403) {
					dialog2 = DialogUtil.showDialogOneButton(
							GeneralConsumeLoanActivity.this,
							mIntentResponse.getMsg(), new OnClickListener() {

								@Override
								public void onClick(View v) {
									Intent intent = new Intent(
											GeneralConsumeLoanActivity.this,
											MyLoginActivity.class);
									startActivity(intent);
									dialog2.cancel();
									Intent intent2 =new Intent();
									intent2.setClass(GeneralConsumeLoanActivity.this,
											HomeActivity.class);
									startActivity(intent2);
									finish();
									overridePendingTransition(R.anim.transaction_item_righttoleftss,
											R.anim.transaction_item_lefttorights);
								}
							});
				} else {
					if(Contents.response != null && Contents.response.getResult() != null) {
						general_consume_loan_name.setText(Contents.response
								.getResult().getCipNamecn());
						general_consume_loan_contact_tel.setText(Contents.response
								.getResult().getCipMobile());
					}
				}
			} else {
				if(Contents.response != null && Contents.response.getResult() != null) {
					general_consume_loan_name.setText(Contents.response.getResult()
							.getCipNamecn());
					general_consume_loan_contact_tel.setText(Contents.response
							.getResult().getCipMobile());
				}
			}

		}

	}

	class GeneralConsumeLoanAsyncTask extends AsyncTask<Object, Object, Object> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog();
		}

		@Override
		protected Object doInBackground(Object... params_obj) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(GeneralConsumeLoanActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			
		
			if (Contents.response != null
					&& Contents.response.getResult() != null) {
				arg.add(new BasicNameValuePair("idNo",(String) params_obj[0]));
			}
			arg.add(new BasicNameValuePair("custName", (String) params_obj[1]));
			arg.add(new BasicNameValuePair("indivMobile", (String) params_obj[2]));
			arg.add(new BasicNameValuePair("indivMobile2", (String) params_obj[3]));
			arg.add(new BasicNameValuePair("applyAmt", (String) params_obj[4]));
			arg.add(new BasicNameValuePair("indivLiveAddr",
					(String) params_obj[5]));
			arg.add(new BasicNameValuePair("indivEmp", (String) params_obj[6]));

			return httpHelper.post(ContantsAddress.GENERAL_CUSTOME_LOAN, arg,
					Response.class);
		}

		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);
			dismissProgressDialog();
			if (result != null) {
				Response mResponse = (Response) result;
				if (mResponse.getCode() == 0) {
					dialog2 = DialogUtil.showDialogOneButton(
							GeneralConsumeLoanActivity.this,
							mResponse.getMsg(), new OnClickListener() {

								@Override
								public void onClick(View v) {
									dialog2.cancel();
								
									Intent intent =new Intent();
									intent.setClass(GeneralConsumeLoanActivity.this,
											HomeActivity.class);
									startActivity(intent);
									GeneralConsumeLoanActivity.this.finish();
									overridePendingTransition(R.anim.transaction_item_righttoleftss,
											R.anim.transaction_item_lefttorights);

								}
							});
				} else if (mResponse.getCode() == 403) {
					dialog2 = DialogUtil.showDialogOneButton(
							GeneralConsumeLoanActivity.this,
							mResponse.getMsg(), new OnClickListener() {

								@Override
								public void onClick(View v) {
									Intent intent = new Intent(
											GeneralConsumeLoanActivity.this,
											MyLoginActivity.class);
									startActivity(intent);
									dialog2.cancel();
									// // finish();
									// GeneralConsumeLoanActivity.this.finish();
								}
							});
				} else {
					Toast.makeText(GeneralConsumeLoanActivity.this,
							mResponse.getMsg(), Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(GeneralConsumeLoanActivity.this,
						getResources().getString(R.string.no_network),
						Toast.LENGTH_SHORT).show();
			}

		}
	}

	private void createDialog(String telPhone) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("提示");
		builder.setMessage(telPhone);
		builder.setPositiveButton("确定", new AlertDialog.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				// finish();
				GeneralConsumeLoanActivity.this.finish();
			}
		});
		builder.create();
		builder.show();
	}

	// @Override
	// protected Dialog onCreateDialog(int id, Bundle args) {
	// AlertDialog.Builder builder = new AlertDialog.Builder(
	// new ContextThemeWrapper(this, R.style.AlertDialogCustom));
	// builder.setTitle("提示信息");
	// switch (id) {
	// case INFO_ERROR:
	// builder.setMessage(result.msg);
	// builder.setPositiveButton("确定",
	// new DialogInterface.OnClickListener() {
	// @Override
	// public void onClick(DialogInterface dialog, int which) {
	// }
	// });
	// break;
	// }
	// builder.create();
	// builder.show();
	// return super.onCreateDialog(id, args);
	// }

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent =new Intent();
			intent.setClass(GeneralConsumeLoanActivity.this,
					HomeActivity.class);
			startActivity(intent);
			finish();
			overridePendingTransition(R.anim.transaction_item_righttoleftss,
					R.anim.transaction_item_lefttorights);
		}
		return false;
	}

	/**
	 * 解析整个Json对象，完成后释放Json对象的内存
	 */
	private void initDatas() {
		try {
			JSONArray jsonArray = mJsonObj.getJSONArray("citylist");
			mProvinceDatas = new String[jsonArray.length()];
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonP = jsonArray.getJSONObject(i);// 每个省的json对象
				String province = jsonP.getString("p");// 省名字

				mProvinceDatas[i] = province;

				JSONArray jsonCs = null;
				try {
					/**
					 * Throws JSONException if the mapping doesn't exist or is
					 * not a JSONArray.
					 */
					jsonCs = jsonP.getJSONArray("c");
				} catch (Exception e1) {
					continue;
				}
				String[] mCitiesDatas = new String[jsonCs.length()];
				for (int j = 0; j < jsonCs.length(); j++) {
					JSONObject jsonCity = jsonCs.getJSONObject(j);
					String city = jsonCity.getString("n");// 市名字
					mCitiesDatas[j] = city;
					JSONArray jsonAreas = null;
					try {
						/**
						 * Throws JSONException if the mapping doesn't exist or
						 * is not a JSONArray.
						 */
						jsonAreas = jsonCity.getJSONArray("a");
					} catch (Exception e) {
						continue;
					}

					String[] mAreasDatas = new String[jsonAreas.length()];// 当前市的所有区
					for (int k = 0; k < jsonAreas.length(); k++) {
						String area = jsonAreas.getJSONObject(k).getString("s");// 区域的名称
						mAreasDatas[k] = area;
					}
					mAreaDatasMap.put(city, mAreasDatas);
				}
				mCitisDatasMap.put(province, mCitiesDatas);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		mJsonObj = null;
	}

	/**
	 * 从assert文件夹中读取省市区的json文件，然后转化为json对象
	 */
	private void initJsonData() {
		try {
			StringBuffer sb = new StringBuffer();
			InputStream is = getAssets().open("city.json");
			int len = -1;
			byte[] buf = new byte[1024 * 2];
			while ((len = is.read(buf)) != -1) {
				sb.append(new String(buf, 0, len, "GB2312"));
			}
			is.close();
			mJsonObj = new JSONObject(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * change事件的处理
	 */
	// wz10.11
	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue)
			throws NullPointerException {
		if (wheel == mProvince) {
			updateCities();
		} else if (wheel == mCity) {
			updateAreas();
		} else if (wheel == mArea) {
			mCurrentAreaName = "";
			mCurrentAreaName = mAreaDatasMap.get(mCurrentCityName)[newValue];
		}
	}

	public void showChoose(View view) {
		Toast.makeText(this,
				mCurrentProviceName + mCurrentCityName + mCurrentAreaName,
				Toast.LENGTH_LONG).show();
	}

	/**
	 * 根据当前的市，更新区WheelView的信息
	 */
	private void updateAreas() {
		int pCurrent = mCity.getCurrentItem();
		mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
		String[] areas = mAreaDatasMap.get(mCurrentCityName);

		if (areas == null) {
			areas = new String[] { "" };
			mCurrentAreaName = "";
		} else {
			mCurrentAreaName = mAreaDatasMap.get(mCurrentCityName)[0];
		}
		mArea.setViewAdapter(new ArrayWheelAdapter<String>(this, areas));
		mArea.setCurrentItem(0);
	}

	/**
	 * 根据当前的省，更新市WheelView的信息
	 */
	private void updateCities() {
		int pCurrent = mProvince.getCurrentItem();
		mCurrentProviceName = mProvinceDatas[pCurrent];
		String[] cities = mCitisDatasMap.get(mCurrentProviceName);
		if (cities == null) {
			cities = new String[] { "" };
		}
		mCity.setViewAdapter(new ArrayWheelAdapter<String>(this, cities));
		mCity.setCurrentItem(0);
		updateAreas();
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO 自动生成的方法存根

	}
}
