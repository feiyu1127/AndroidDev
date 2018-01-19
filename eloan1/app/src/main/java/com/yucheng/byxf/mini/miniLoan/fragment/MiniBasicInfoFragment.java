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

import android.R.integer;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;

import com.yucheng.apfx.utl.RegexResult;
import com.yucheng.byxf.mini.miniLoan.MiniLoanActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.RegexUtils2;
import com.yucheng.byxf.mini.utils.SharedPreferencesUtils;
import com.yucheng.byxf.mini.utils.RegexUtils2.Result;

/**
 * 
 * 基本资料Fragment
 */
public class MiniBasicInfoFragment extends Fragment implements OnClickListener,
		OnWheelChangedListener {
	private Result result;
	// 婚姻状况
	private RadioGroup rg_marriage_info;
	// 供养人数
	private EditText et_house_hold;
	// 同户籍地址按钮
	private Button bt_getcommon_address;
	// 现居住地址
	private EditText live_address;
	//现居地址详细
	private EditText et_live_address;
	// 邮编
	private EditText et_postcode;
	// 住宅性质
	private Spinner sp_house_type;
	// 文化性质
	private Spinner sp_cultrue;
	// 住宅电话区号
	private EditText et_area_code;
	// 住宅电话
	private EditText et_tel_code;
	// 第二手机号
	private EditText et_electron_mail;
	// 第二玩手机号按钮 tag=0
	private Button diershoujihao;
	// 第二手机号码
	private EditText diershoujihaoma;
	// 租金
	private LinearLayout zujinnum_layout;
	// 手机号码
	private EditText et_phone_code;
	// 下一页按钮
	private Button mini_loan_basicinfo_next_button;
	// 返回
	private ImageView back3;
	// 租金金额
	private EditText et_house_zujin;
	private String[] sp_indivLive = { "请选择", "自购无贷款", "自购有贷款", "单位宿舍", "与亲属同住",
			"租住", "其他" };
	private String[] sp_indivDegree = { "请选择", "初中及以下", "高中或中专", "大专", "本科",
			"硕士", "博士" };

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
		View view = inflater.inflate(R.layout.minifragment3, container, false);
		ImageView backcustom = (ImageView) view.findViewById(R.id.back3);
		backcustom.setOnClickListener(new OnClickListener() {
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
		et_live_address=(EditText) view.findViewById(R.id.et_live_address1);
		rg_marriage_info = (RadioGroup) view
				.findViewById(R.id.rg_marriage_info);
		et_house_hold = (EditText) view.findViewById(R.id.et_house_hold);
		bt_getcommon_address = (Button) view
				.findViewById(R.id.bt_getcommon_address);
		live_address = (EditText) view.findViewById(R.id.live_address);
		et_postcode = (EditText) view.findViewById(R.id.et_postcode);
		sp_house_type = (Spinner) view.findViewById(R.id.sp_house_type);
		sp_cultrue = (Spinner) view.findViewById(R.id.sp_cultrue);
		et_area_code = (EditText) view.findViewById(R.id.et_area_code);
		et_tel_code = (EditText) view.findViewById(R.id.et_tel_code);
		et_electron_mail = (EditText) view.findViewById(R.id.et_postcode);
		et_phone_code = (EditText) view.findViewById(R.id.et_phone_code);
		back3 = (ImageView) view.findViewById(R.id.back3);
		diershoujihao = (Button) view.findViewById(R.id.diershoujihao);
		diershoujihaoma = (EditText) view.findViewById(R.id.diershoujihaoma);
		zujinnum_layout = (LinearLayout) view
				.findViewById(R.id.zujinnum_layout);
		et_house_zujin = (EditText) view.findViewById(R.id.et_house_zujin);
		mini_loan_basicinfo_next_button = (Button) view
				.findViewById(R.id.mini_loan_basicinfo_next_button);
		setSpinner(getActivity(), sp_indivLive, sp_house_type);
		mini_loan_basicinfo_next_button.setOnClickListener(this);
		diershoujihao.setOnClickListener(this);
		live_address.setOnClickListener(this);
		bt_getcommon_address.setOnClickListener(this);
		sp_house_type.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Spinner spin = (Spinner) parent;
				String house = (String) spin.getItemAtPosition(position);
				if (position != 0) {
					spin.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
				} else {
					spin.setBackgroundResource(R.drawable.comm_spinner_selecter);
				}
				if (house == "租住") {
					zujinnum_layout.setVisibility(view.VISIBLE);
				} else if (house == "自购有贷款") {
					zujinnum_layout.setVisibility(view.VISIBLE);
				} else {
					zujinnum_layout.setVisibility(view.GONE);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

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

		setOnItemSelected(sp_cultrue);
		setSpinner(getActivity(), sp_indivDegree, sp_cultrue);
		initinfo();

		return view;
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

	private void setSpinner(Context mContext, String[] array, Spinner sp) {
		// TODO Auto-generated method stub

		ArrayAdapter<String> commSpinnerAdapter = null;
		commSpinnerAdapter = new ArrayAdapter<String>(mContext,
				R.layout.simple_spinner_item, array);
		commSpinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_item);
		sp.setAdapter(commSpinnerAdapter);

	}

	private void initinfo() {
		// TODO Auto-generated method stub
		SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
				getActivity(), "MiniTwo");
		MiniApplyInfo applyinfo = preferencesUtils.readObj();
		if (applyinfo != null) {
			if (applyinfo.getIdCard().equals(
					Contents.response.getResult().getIdNo())) {
				System.out.println("----------------------"
						+ applyinfo.getIdCard().equals(
								Contents.response.getResult().getIdNo()));
				// 婚姻状况
				int hunyin = applyinfo.getIndivMarital();
				if (hunyin == 10) {
					// 未婚
					rg_marriage_info.check(R.id.marital_status_dis_no);
				} else if (hunyin == 20) {
					// 已婚
					rg_marriage_info.check(R.id.marital_status_mar_no);
				} else if (hunyin == 90) {
					// 其他
					rg_marriage_info.check(R.id.marital_status_other_no);
				}
				String house_hold = applyinfo.getDependentPersons() + "";
				et_house_hold.setText(house_hold);
				live_address.setText(applyinfo.getIndivLiveAddr());
				et_postcode.setText(applyinfo.getIndivLiveZip() + "");
				short zhuzhai = applyinfo.getIndivLive();
				if (zhuzhai == 1) {
					sp_house_type.setSelection(1);
				} else if (zhuzhai == 2) {
					sp_house_type.setSelection(2);
				} else if (zhuzhai == 3) {
					sp_house_type.setSelection(3);
				} else if (zhuzhai == 4) {
					sp_house_type.setSelection(4);
				} else if (zhuzhai == 5) {
					sp_house_type.setSelection(5);
				} else if (zhuzhai == 7) {
					sp_house_type.setSelection(7);
				} else {
					sp_house_type.setSelection(0);
				}
				if (et_house_zujin.isEnabled()) {
					et_house_zujin.setText(applyinfo.getIndivRentAmt());
				}
				short wenhua = applyinfo.getIndivDegree();
				if (wenhua == 1) {
					sp_cultrue.setSelection(1);
				} else if (wenhua == 2) {
					sp_cultrue.setSelection(2);
				} else if (wenhua == 3) {
					sp_cultrue.setSelection(3);
				} else if (wenhua == 4) {
					sp_cultrue.setSelection(4);
				} else if (wenhua == 5) {
					sp_cultrue.setSelection(5);
				} else if (wenhua == 6) {
					sp_cultrue.setSelection(6);
				} else {
					sp_cultrue.setSelection(0);
				}
				live_address.setText(applyinfo.getIndivLiveAddr());
				et_live_address.setText(applyinfo.getHujiXiangQing());
				et_area_code.setText(applyinfo.getIndivFmyZone());
				et_tel_code.setText(applyinfo.getIndivFmyTel());
				et_phone_code.setText(applyinfo.getIndivMobile());
				diershoujihaoma.setText(applyinfo.getIndivMobileAno());
			}
		} else {
			// 身份证号不匹配 删除原有的数据
			preferencesUtils.clearData();
		}
	}

	private void saveInfo() {

		MiniApplyInfo saveapplyinfo = new MiniApplyInfo();
		// 婚姻状况
		saveapplyinfo.setIdCard(Contents.response.getResult().getIdNo());
		switch (rg_marriage_info.getCheckedRadioButtonId()) {
		case R.id.marital_status_dis_no:
			saveapplyinfo.setIndivMarital(10);
			break;
		case R.id.marital_status_mar_no:
			saveapplyinfo.setIndivMarital(20);
			break;
		case R.id.marital_status_other_no:
			saveapplyinfo.setIndivMarital(90);
			break;
		default:
			break;
		}
		// 供养人数
		int gongyangCount = Integer
				.parseInt(et_house_hold.getText().toString());
		saveapplyinfo.setDependentPersons(gongyangCount);
		saveapplyinfo
				.setIndivLiveAddr(live_address.getText().toString().trim());
		saveapplyinfo.setHujiXiangQing(et_live_address.getText().toString());
		// 邮编
		int livezip = Integer.parseInt(et_postcode.getText().toString());
		saveapplyinfo.setIndivLiveZip(livezip);
		switch ((int) sp_house_type.getSelectedItemId()) {
		case 0:
			saveapplyinfo.setIndivLive((short) 0);
			break;
		case 1:
			saveapplyinfo.setIndivLive((short) 1);
			break;
		case 2:
			saveapplyinfo.setIndivLive((short) 2);
			break;
		case 3:
			saveapplyinfo.setIndivLive((short) 3);
			break;
		case 4:
			saveapplyinfo.setIndivLive((short) 4);
			break;
		case 5:
			saveapplyinfo.setIndivLive((short) 5);
			break;
		default:
			saveapplyinfo.setIndivLive((short) 7);
			break;
		}
		// 月供租金
		if (et_house_zujin.isEnabled()) {
			saveapplyinfo.setIndivRentAmt(et_house_zujin.getText().toString());
		} else {
			saveapplyinfo.setIndivRentAmt("");
		}
		// 文化程度
		switch ((int) sp_cultrue.getSelectedItemId()) {
		case 0:
			saveapplyinfo.setIndivDegree((short) -1);
			break;
		case 1:

			saveapplyinfo.setIndivDegree((short) 1);
			break;
		case 2:

			saveapplyinfo.setIndivDegree((short) 2);
			break;
		case 3:

			saveapplyinfo.setIndivDegree((short) 3);
			break;
		case 4:

			saveapplyinfo.setIndivDegree((short) 4);
			break;
		case 5:

			saveapplyinfo.setIndivDegree((short) 5);
			break;
		default:
			saveapplyinfo.setIndivDegree((short) 6);
			break;
		}
		// 住宅电话
		saveapplyinfo.setIndivFmyZone(et_area_code.getText().toString());
		saveapplyinfo.setIndivFmyTel(et_tel_code.getText().toString());
		// 手机号码
		saveapplyinfo.setIndivMobile(et_phone_code.getText().toString());
		// 第二手机号码
		if (diershoujihaoma.isEnabled()) {
			saveapplyinfo.setIndivMobileAno(diershoujihaoma.getText()
					.toString());
		} else {
			saveapplyinfo.setIndivMobileAno(diershoujihaoma.getText()
					.toString());
		}
		SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
				getActivity(), "MiniTwo");
		preferencesUtils.saveObj(saveapplyinfo);
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
		case R.id.mini_loan_basicinfo_next_button:
			Result temp = null;
			if (rg_marriage_info.getCheckedRadioButtonId() == -1) {
				result = new Result(false, "请选择婚姻状况!");
				showEditDialog(v, result.msg);
				return;

			}
			// 供养人数
			String house_hold = et_house_hold.getText().toString().trim();
			temp = RegexUtils2.number("供养人数", house_hold);
			if (house_hold.equals("")) {
				showEditDialog(v, "供养人数不得为空");
				return;
			}
			if (temp.match == false) {
				result = temp;
				showEditDialog(v, result.msg);
				et_house_hold.requestFocus();
				return;
			}
			// 现居地址
			String house_now = live_address.getText().toString().trim();
			if (house_now.equals("")) {
				showEditDialog(v, "请选择现居地址省市县信息!");
				return;
			}
			if(et_live_address.getText().toString().equals("")){
				showEditDialog(v, "请输入详细地址！");
				return;
			}
			// 月供金额
			if (zujinnum_layout.getVisibility() == View.VISIBLE) {
				String zujin = et_house_zujin.getText().toString();
				if (zujin.equals("")) {
					showEditDialog(v, "月供金额不能为空！");
					et_house_zujin.requestFocus();
					return;
				}
			}
			// 邮编
			String electron_mail = et_electron_mail.getText().toString();
			if (electron_mail.equals("")) {
				showEditDialog(v, "邮编不能为空");
				return;
			}
			temp = RegexUtils2.zip("邮编", electron_mail);
			if (!temp.match) {
				result = temp;
				showEditDialog(v, result.msg);
				return;
			}
			String house_type = sp_house_type.getSelectedItem().toString();
			if (house_type.equals("请选择")) {
				showEditDialog(v, "请选择住宅性质");
				sp_house_type.requestFocus();
				return;
			}
			String cultrue = sp_cultrue.getSelectedItem().toString();
			if (cultrue.equals("请选择")) {
				showEditDialog(v, "请选择文化程度");
				sp_cultrue.requestFocus();
				return;
			}

			// 手机号
			String phone_code = et_phone_code.getText().toString();
			temp = RegexUtils2.mobile("手机号", phone_code);
			if (!temp.match) {
				result = temp;
				showEditDialog(v, result.msg);
				return;
			}
			// 第二手机号
			if (diershoujihaoma.isEnabled()) {
				String shoujihao = diershoujihaoma.getText().toString();
				if (shoujihao.equals("")) {
					showEditDialog(v, "第二手机号不能为空！");
					diershoujihaoma.requestFocus();
					return;
				}
			}
			//住宅电话区号
			String area_code = et_area_code.getText().toString();
			if (!TextUtils.isEmpty(area_code) && area_code.length()<3)
            {
                showEditDialog(v, "住宅电话区号长度只能是3到4位!");
                et_area_code.requestFocus();
                return;
            }
			saveInfo();
			MiniCareerInfoFragment fOur = new MiniCareerInfoFragment();
			FragmentManager fm = getFragmentManager();
			FragmentTransaction tx = fm.beginTransaction();
			tx.replace(R.id.id_content, fOur, "Four");
			// tx.remove(fm.findFragmentByTag("Three"));
			tx.addToBackStack("Three");
			tx.commit();

			System.out.println("--------------------------------------------"
					+ rg_marriage_info.getCheckedRadioButtonId());
			break;

		case R.id.bt_getcommon_address:
			SharedPreferencesUtils pUtils = new SharedPreferencesUtils(
					getActivity(), "MiniOne");
			final MiniApplyInfo info = pUtils.readObj();
			if (info != null) {
				live_address.setText(info.getIndivRegAddr());
				et_live_address.setText(info.getHujiXiangQing());
			}
			break;
		case R.id.diershoujihao:
			if (diershoujihaoma.isEnabled()) {
				diershoujihao.setBackgroundResource(R.drawable.twomobileoff);
				diershoujihaoma.setEnabled(false);

			} else {
				diershoujihao.setBackgroundResource(R.drawable.twomobileon);
				diershoujihaoma.setEnabled(true);
			}
			break;
		case R.id.live_address:
			// 选择省市
			city_layout.setVisibility(View.VISIBLE);
			break;
		case R.id.choose_button:
			// 选择省市
			city_layout.setVisibility(View.GONE);
			live_address.setText(mCurrentProviceName + mCurrentCityName
					+ mCurrentAreaName);
			break;
		}
	}

	public void showEditDialog(View view, String str) {
		DialogFragment myFragment = NameDialogFragment.newInstance(str);
		myFragment.show(getFragmentManager(), "EditNameDialog");
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
