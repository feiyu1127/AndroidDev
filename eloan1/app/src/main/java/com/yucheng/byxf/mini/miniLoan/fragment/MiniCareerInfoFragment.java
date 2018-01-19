package com.yucheng.byxf.mini.miniLoan.fragment;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.sharesdk.framework.p;

import com.yucheng.apfx.utl.RegexResult;
import com.yucheng.byxf.mini.miniLoan.MiniLoanActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.RegexCust;
import com.yucheng.byxf.mini.utils.RegexUtils2;
import com.yucheng.byxf.mini.utils.SharedPreferencesUtils;
import com.yucheng.byxf.mini.utils.RegexUtils2.Result;
import com.yucheng.byxf.sqlite.DBManager;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * 职业信息Fragment
 * 
 */
public class MiniCareerInfoFragment extends Fragment implements
		OnClickListener, OnWheelChangedListener {

	// 下一页
	private Button mini_loan_profession_info_next_button;
	// 验证结果
	private RegexResult result;
	// 信息有误
	private final int INFO_ERROR = 0;
	// MiniApplyInfo applyinfo;
	private ImageView back4;
	// 现单位全称
	private EditText et_com_name;
	// 任职部门
	private EditText et_dpment_name;
	// 本单位工作年限
	private EditText et_worktimes;
	// 总工作年限
	private EditText et_total_worktimes;
	// 单位地址
	private EditText et_com_address;
	// 单位地址详情
	private EditText et_live_address;
	// 邮编
	private EditText et_mail_code;
	// 单位电话区号
	private EditText et_com_zone_code;
	// 单位电话号码
	private EditText et_com_tel_code;
	// 分机号
	private EditText et_com_extension_code;
	// 邮寄地址
	private RadioGroup email_address_radio;
	// 现居住地址
	private RadioButton email_address_dwell;
	// 单位地址
	private RadioButton email_address_unit;
	// 公司规模
	private Spinner et_com_scale;
	// 单位性质
	private Spinner et_com_properties;
	// 行业性质
	private Spinner et_trade_properties;
	// 所在职位
	private Spinner et_com_position;
	// 申请人月收入
	private EditText et_salary;
	// 公司规模
	private String[] scale = { "请选择", "99人及以下", "99-500人", "500-2000人",
			"2000人以上" };
	// 单位性质
	private String[] properties = { "请选择", "国家机关/事业单位", "国有企业", "集体企业",
			"中外合资/中外合作/外商独资", "股份制企业", "私营企业", "其他" };
	// 行业性质
	private String[] trade_properties = { "请选择", "个体工商户", "批发/零售", "制造业",
			"房地产", "建筑业", "酒店/旅游/餐饮", "交通运输/仓储/邮政业", "传媒/体育/娱乐", "专业事务所",
			"信息传输/计算机服务/软件业", "生物/医药", "金融", "公共事业", "石油/石化", "教育", "医疗卫生",
			"科研院所", "水利/环境/公共设施管理业", "烟草", "军事机构", "社会团体", "政府机构/公检法", "自由职业",
			"其他" };
	// 所在职位
	private String[] position = { "请选择", "高级管理人员", "一般管理人员", "一般正式员工", "非正式员工",
			"企业负责人", "中层管理人员", "其他", "厅局级以上", "处级", "科级", "一般干部", "退休", " 失业" };

	/**
	 * 把全国的省市区的信息以json的格式保存，解析完成后赋值为null
	 */
	private Button choose_button;// 省市选择的确定按钮
	private LinearLayout city_layout;// 省市选择的布局
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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.minifragment4, container, false);

		
		back4 = (ImageView) view.findViewById(R.id.back4);
		back4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentManager fm = getFragmentManager();
				fm.popBackStack();
			}
		});

		// 省市区数据的初始化
		initJsonData();

		// 省市区控件的初始化
		city_layout = (LinearLayout) view.findViewById(R.id.city_layout);
		choose_button = (Button) view.findViewById(R.id.choose_button);
		mProvince = (WheelView) view.findViewById(R.id.id_province);
		mCity = (WheelView) view.findViewById(R.id.id_city);
		mArea = (WheelView) view.findViewById(R.id.id_area);
		choose_button.setOnClickListener(this);
		et_com_name = (EditText) view.findViewById(R.id.et_com_name);
		et_live_address = (EditText) view.findViewById(R.id.et_com_address_more);
		et_dpment_name = (EditText) view.findViewById(R.id.et_dpment_name);
		et_worktimes = (EditText) view.findViewById(R.id.et_worktimes);
		et_total_worktimes = (EditText) view
				.findViewById(R.id.et_total_worktimes);
		et_com_address = (EditText) view.findViewById(R.id.et_com_address);
		et_mail_code = (EditText) view.findViewById(R.id.et_mail_code);
		et_com_zone_code = (EditText) view.findViewById(R.id.et_com_zone_code);
		et_com_tel_code = (EditText) view.findViewById(R.id.et_com_tel_code);
		email_address_radio = (RadioGroup) view
				.findViewById(R.id.email_address_radio);
		email_address_dwell = (RadioButton) view
				.findViewById(R.id.email_address_dwell);
		email_address_unit = (RadioButton) view
				.findViewById(R.id.email_address_unit);
		et_com_extension_code = (EditText) view
				.findViewById(R.id.et_com_extension_code);
		et_com_scale = (Spinner) view.findViewById(R.id.et_com_scale);
		et_com_properties = (Spinner) view.findViewById(R.id.et_com_properties);
		et_trade_properties = (Spinner) view
				.findViewById(R.id.et_trade_properties);
		et_com_position = (Spinner) view.findViewById(R.id.et_com_position);
		et_salary = (EditText) view.findViewById(R.id.et_salary);
		mini_loan_profession_info_next_button = (Button) view
				.findViewById(R.id.mini_loan_profession_info_next_button);
		mini_loan_profession_info_next_button.setOnClickListener(this);
		et_com_address.setOnClickListener(this);
		setOnItemSelected(et_com_scale);
		setOnItemSelected(et_com_properties);
		setOnItemSelected(et_trade_properties);
		setOnItemSelected(et_com_position);
		setSpinner(getActivity(), scale, et_com_scale);
		setSpinner(getActivity(), properties, et_com_properties);
		setSpinner(getActivity(), trade_properties, et_trade_properties);
		setSpinner(getActivity(), position, et_com_position);

		// 省市区数据的初始化
		initDatas();
		mProvince.setViewAdapter(new ArrayWheelAdapter<String>(getActivity(),
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

		initinfo();
		return view;

	}

	private void initinfo() {
		SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
				getActivity(), "MiniThree");
		MiniApplyInfo applyinfo = preferencesUtils.readObj();
		if (applyinfo != null) {
			if (applyinfo.getIdCard().equals(
					Contents.response.getResult().getIdNo())) {
				et_com_name.setText(applyinfo.getIndivEmp());
				et_dpment_name.setText(applyinfo.getIndivBranch());
				et_worktimes.setText(applyinfo.getIndivEmpYrs() + "");
				et_total_worktimes.setText(applyinfo.getIndivWorkYrs() + "");
				et_com_address.setText(applyinfo.getIndivEmpAddr());
				et_live_address.setText(applyinfo.getHujiXiangQing());
				et_mail_code.setText(applyinfo.getIndivEmpZip());
				et_com_zone_code.setText(applyinfo.getIndivEmpZone());
				et_com_tel_code.setText(applyinfo.getIndivEmpTelNo());
				et_com_extension_code.setText(applyinfo.getIndivEmpTelSub());
				int checkedId = applyinfo.getSendTyp();
				switch (checkedId) {
				// ：01单位 00住宅
				case 0:
					email_address_dwell.setChecked(true);
					break;

				case 1:
					email_address_unit.setChecked(true);
					break;
				}
				et_com_scale.setSelection(Integer.parseInt(applyinfo
						.getGsguimo()));
				et_com_properties.setSelection(Integer.parseInt(applyinfo
						.getDwxingzhi()));
				et_trade_properties.setSelection(Integer.parseInt(applyinfo
						.getHyxingzhi()));
				et_com_position.setSelection(Integer.parseInt(applyinfo
						.getSzzhiwei()));
				et_com_scale.setSelection(Integer.parseInt(applyinfo
						.getGsguimo()));
				et_com_properties.setSelection(Integer.parseInt(applyinfo
						.getDwxingzhi()));
				et_trade_properties.setSelection(Integer.parseInt(applyinfo
						.getHyxingzhi()));
				et_com_position.setSelection(Integer.parseInt(applyinfo
						.getSzzhiwei()));
				et_salary.setText(applyinfo.getIndivMthInc());
			}
		} else {
			preferencesUtils.clearData();
		}
	}

	private void setSpinner(Context mContext, String[] array, Spinner sp) {
		// TODO Auto-generated method stub

		ArrayAdapter<String> commSpinnerAdapter = null;
		commSpinnerAdapter = new ArrayAdapter<String>(mContext,
				R.layout.simple_spinner_item, array);
		commSpinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_item);
		sp.setAdapter(commSpinnerAdapter);

	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.mini_loan_profession_info_next_button:
			RegexResult temp = null;
			// 现单位全称
			String nowDpName = et_com_name.getText().toString().trim();
			if (nowDpName.equals("")) {
				showEditDialog(v, "现单位全称不能为空！");
				et_com_name.requestFocus();
				return;
			}

			// 任职部门
			String rzDp = et_dpment_name.getText().toString().trim();
			if (rzDp.equals("")) {
				showEditDialog(v, "任职部门不能为空！");
				et_com_name.requestFocus();
				return;
			}
			// 本单位工作年限
			String year = et_worktimes.getText().toString().trim();
			temp = RegexCust.required("本单位工作年限", year);
			if (temp.match) {
				if (!RegexCust.lengthMax(year, 10)) {
					result = new RegexResult(false, "本单位工作年限字符长度过长!");
					showEditDialog(v, result.msg);
					et_worktimes.requestFocus();
					return;
				}
			} else {
				result = temp;
				showEditDialog(v, result.msg);
				et_worktimes.requestFocus();
				return;
			}
			// 总工作年限
			String totalYear = et_total_worktimes.getText().toString().trim();
			if (totalYear.equals("")) {
				showEditDialog(v, "总工作年限不能为空！");
				et_total_worktimes.requestFocus();
				return;
			}
			int year1 = Integer.parseInt(year);
			int year2 = Integer.parseInt(totalYear);
			if (year2 < year1) {
				showEditDialog(v, "总工作年限不能小于当前单位工作年限！");
				et_total_worktimes.requestFocus();
				return;
			}
			// 单位地址
			String dpPlace = et_com_address.getText().toString().trim();
			if (dpPlace.equals("")) {
				showEditDialog(v, "请选择单位地址省市县信息！");
				et_com_address.requestFocus();
				return;
			}
			String xiangxi=et_live_address.getText().toString().trim();
			if (xiangxi.equals("")) {
				showEditDialog(v, "详细单位地址信息不能为空！");
				return;
			}
			// 邮编
			String et_mail_codeString = et_mail_code.getText().toString()
					.trim();
			if (et_mail_codeString.equals("")) {
				showEditDialog(v, "邮编不能为空");
				return;
			}
			// 单位电话区号
			String areaNum = et_com_zone_code.getText().toString().trim();
			temp = RegexCust.required("单位电话区号", areaNum);
			if (temp.match) {
				if (!RegexCust.lengthMax(areaNum, 5)) {
					result = new RegexResult(false, "单位电话区号格式有误!");
					showEditDialog(v, result.msg);
					et_com_zone_code.requestFocus();
					return;
				}
				//住宅电话区号
				if (!TextUtils.isEmpty(areaNum) && areaNum.length()<3)
                {
                    result = new RegexResult(false, "单位电话区号长度只能是3到4位!");
                    showEditDialog(v, result.msg);
                    et_com_zone_code.requestFocus();
                    return;
                }
			} else {

				result = temp;
				showEditDialog(v, result.msg);
				et_com_zone_code.requestFocus();
				return;
			}
			// 单位电话号码
			String telNum = et_com_tel_code.getText().toString().trim();
			temp = RegexCust.required("单位电话号码", telNum);
			if (temp.match) {
				if (!RegexCust.lengthMax(telNum, 20)) {
					result = new RegexResult(false, "单位电话号码格式有误!");
					showEditDialog(v, result.msg);
					et_com_tel_code.requestFocus();
					return;
				}
			} else {
				result = temp;
				showEditDialog(v, result.msg);
				et_com_tel_code.requestFocus();
				return;
			}
			// 单位电话号码区号
			String com_extension_code = et_com_extension_code.getText().toString();
			if(!TextUtils.isEmpty(com_extension_code) && com_extension_code.length()<3){
				result = new RegexResult(false, "单位电话分机号长度只能是3到4位!");
                showEditDialog(v, result.msg);
                et_com_extension_code.requestFocus();
                return;
			}
			// 邮寄地址
			if (email_address_radio.getCheckedRadioButtonId() == -1) {
				showEditDialog(v, "请选择邮寄地址！");
				return;
			}
			// 单位规模
			if (!et_com_scale.isClickable()) {
				showEditDialog(v, "请选择单位规模！");
			}
			// 单位性质
			if ("请选择".equals(et_com_properties.getSelectedItem().toString())) {
				result = new RegexResult(false, "请选择单位性质!");
				showEditDialog(v, result.msg);
				et_com_properties.requestFocus();
				return;
			}
			// 行业性质
			if ("请选择".equals(et_trade_properties.getSelectedItem().toString())) {
				result = new RegexResult(false, "请选择行业性质!");
				showEditDialog(v, result.msg);
				et_trade_properties.requestFocus();
				return;
			}
			// 现任职位
			if ("请选择".equals(et_com_position.getSelectedItem().toString())) {
				result = new RegexResult(false, "请选择所在职位!");
				showEditDialog(v, result.msg);
				et_com_position.requestFocus();
				return;
			}
			// 月工资收入

			String moneyYear = et_salary.getText().toString().trim();
			temp = RegexCust.required("月工资收入", moneyYear);
			if (temp.match) {
				if (!RegexCust.lengthMax(moneyYear, 16)) {
					result = new RegexResult(false, "月工资收入格式有误!");
					showEditDialog(v, result.msg);
					et_salary.requestFocus();
					return;
				}
			} else {
				result = temp;
				showEditDialog(v, result.msg);
				et_salary.requestFocus();
				return;
			}

			MiniApplyInfo applyInfo = new MiniApplyInfo();

			String indivEmp = et_com_name.getText().toString();
			String indivBranch = et_dpment_name.getText().toString();
			short indivEmpYrs = (short) Integer.parseInt(et_worktimes.getText()
					.toString());
			short indivWorkYrs = (short) Integer.parseInt(et_total_worktimes
					.getText().toString());
			String indivEmpAddr = et_com_address.getText().toString();
			String indivEmpZip = et_mail_code.getText().toString();
			String indivEmpZone = et_com_zone_code.getText().toString();
			String indivEmpTelNo = et_com_tel_code.getText().toString();
			String indivEmpTelSub = et_com_extension_code.getText().toString();
			if (email_address_dwell.isChecked() == true) {
				Log.d("xxx", "邮寄地址+1");
				applyInfo.setSendTyp((short) 00);
			} else if (email_address_unit.isChecked() == true) {
				Log.d("xxx", "邮寄地址+2");
				applyInfo.setSendTyp((short) 01);
			}
			String gsguimo = et_com_scale.getSelectedItemPosition() + "";
			String dwxingzhi = et_com_properties.getSelectedItemPosition() + "";
			String hyxingzhi = et_trade_properties.getSelectedItemPosition()
					+ "";
			String szzhiwei = et_com_position.getSelectedItemPosition() + "";
			applyInfo.setIdCard(Contents.response.getResult().getIdNo());
			applyInfo.setIndivEmp(indivEmp);
			applyInfo.setIndivBranch(indivBranch);
			applyInfo.setIndivEmpYrs(indivEmpYrs);
			applyInfo.setIndivWorkYrs(indivWorkYrs);
			applyInfo.setIndivEmpAddr(indivEmpAddr);
			applyInfo.setHujiXiangQing(et_live_address.getText().toString());
			applyInfo.setIndivEmpZip(indivEmpZip);
			applyInfo.setIndivEmpZone(indivEmpZone);
			applyInfo.setIndivEmpTelNo(indivEmpTelNo);
			applyInfo.setIndivEmpTelSub(indivEmpTelSub);
			applyInfo.setGsguimo(gsguimo);
			applyInfo.setDwxingzhi(dwxingzhi);
			applyInfo.setHyxingzhi(hyxingzhi);
			applyInfo.setSzzhiwei(szzhiwei);
			applyInfo.setIndivMthInc(et_salary.getText().toString());
			SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
					getActivity(), "MiniThree");
			preferencesUtils.saveObj(applyInfo);

			MiniContactsInfoFragment fFiv = new MiniContactsInfoFragment();
			FragmentManager fm = getFragmentManager();
			FragmentTransaction tx = fm.beginTransaction();
			tx.replace(R.id.id_content, fFiv, "Five");
			// tx.remove(fm.findFragmentByTag("Four"));
			tx.addToBackStack("Four");
			tx.commit();
			break;
		case R.id.et_com_address:
			// 选择省市
			city_layout.setVisibility(View.VISIBLE);
			break;
		case R.id.choose_button:
			// 选择省市
			city_layout.setVisibility(View.GONE);
			et_com_address.setText(mCurrentProviceName + mCurrentCityName
					+ mCurrentAreaName);
			break;
		}

	}

	public void showEditDialog(View view, String str) {
		DialogFragment myFragment = NameDialogFragment.newInstance(str);
		myFragment.show(getFragmentManager(), "EditNameDialog");
	}

	private void setOnItemSelected(Spinner sp) {
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Spinner spin = (Spinner) parent;
				if (position != 0) {
					spin.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
				} else {
					spin.setBackgroundResource(R.drawable.comm_spinner_selecter);
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
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
		mArea.setViewAdapter(new ArrayWheelAdapter<String>(getActivity(), areas));
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
		mCity.setViewAdapter(new ArrayWheelAdapter<String>(getActivity(),
				cities));
		mCity.setCurrentItem(0);
		updateAreas();
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
			InputStream is = getActivity().getAssets().open("city.json");
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

	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		// TODO Auto-generated method stub
		if (wheel == mProvince) {
			updateCities();
		} else if (wheel == mCity) {
			updateAreas();
		} else if (wheel == mArea) {
			mCurrentAreaName = "";
			mCurrentAreaName = mAreaDatasMap.get(mCurrentCityName)[newValue];
		}
	}

}
