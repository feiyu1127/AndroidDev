package com.yucheng.byxf.mini.miniLoan.fragment;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import android.R.integer;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.Text;
import com.yucheng.byxf.mini.domain.MiniPersonInfo;
import com.yucheng.byxf.mini.miniLoan.MiniLoanActivity;
import com.yucheng.byxf.mini.ui.HomeActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.IdcardValidator;
import com.yucheng.byxf.mini.utils.RegexUtils2;
import com.yucheng.byxf.mini.utils.RegexUtils2.Result;
import com.yucheng.byxf.mini.utils.SharedPreferencesUtils;

/**
 * 
 * 身份信息采集fragment
 * 
 */
public class MiniCustomInfoFragment extends Fragment implements
		OnClickListener, OnWheelChangedListener {
	MiniApplyInfo applyinfo;
	// 姓名
	private EditText et_user_name;
	// 性别
	private TextView et_sex;
	// 英文名
	private EditText et_yingwenming;
	// 身份证号
	private EditText et_identify_cardNum;
	// 下一页
	private Button mini_loan_identity_next_button;
	// 出生日期
	private TextView bt_birthday;
	// 证件日期前半部分
	private Button bt_expiryDate_start_button;
	// 证件日期后半部分
	private Button bt_expiryDate_end_button;
	// 户籍地址
	private EditText et_birthPlace;
	// 户籍地址详细地址
	private EditText et_com_address_more;
	// 户籍邮编
	private EditText household_email;
	private Integer age = null;
	private String st_sex;
	private Result result;

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
		MiniLoanActivity miniloan = (MiniLoanActivity) this.getActivity();
		applyinfo = miniloan.getCurrentUser();

		View view = inflater.inflate(R.layout.minifragment2, container, false);
		ImageView backView = (ImageView) view.findViewById(R.id.back2fragment);
		backView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().finish();
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

		mini_loan_identity_next_button = (Button) view
				.findViewById(R.id.mini_loan_identity_next_button);
		mini_loan_identity_next_button.setOnClickListener(this);
		et_user_name = (EditText) view.findViewById(R.id.et_user_name);
		et_sex = (TextView) view.findViewById(R.id.et_sex);
		et_yingwenming = (EditText) view.findViewById(R.id.et_yingwenming);

		et_identify_cardNum = (EditText) view
				.findViewById(R.id.et_identify_cardNum);
		bt_birthday = (TextView) view.findViewById(R.id.bt_birthday);
		bt_expiryDate_start_button = (Button) view
				.findViewById(R.id.bt_expiryDate_start_button);
		bt_expiryDate_end_button = (Button) view
				.findViewById(R.id.bt_expiryDate_end_button);
		et_birthPlace = (EditText) view.findViewById(R.id.et_birthPlace);
		household_email = (EditText) view.findViewById(R.id.et_household_email);
		et_com_address_more = (EditText) view
				.findViewById(R.id.et_com_address_more);
		// 姓名
		et_user_name.setText(Contents.custInfoResponseResult.getResult()
				.getCustName());
		et_user_name.setEnabled(true);
		et_user_name.setFocusable(false);
		// if(!et_user_name.getText().toString().equals("")){
		// et_user_name.setEnabled(false);
		// et_user_name.setFocusable(true);
		// }
		// 身份证号
		et_identify_cardNum.setText(Contents.custInfoResponseResult.getResult()
				.getIdNo());
		// et_identify_cardNum.setEnabled(true);
		// et_identify_cardNum.setFocusable(false);
		String cardNum = et_identify_cardNum.getText().toString().trim();
		updateGenderAndBirthday(cardNum);
		try {
			String s = et_user_name.getText().toString();
			HanyuPinyinOutputFormat output = new HanyuPinyinOutputFormat();
			output.setCaseType(HanyuPinyinCaseType.LOWERCASE);
			output.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
			output.setVCharType(HanyuPinyinVCharType.WITH_V);
			@SuppressWarnings("deprecation")
			String pinyinArray = PinyinHelper
					.toHanyuPinyinString(s, output, "");
			// 英文名
			et_yingwenming.setText(pinyinArray);
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			// TODO Auto-generated catch block
			et_yingwenming.setText("");
			e.printStackTrace();

		}

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
		et_birthPlace.setOnClickListener(this);
		bt_expiryDate_start_button.setOnClickListener(this);
		bt_expiryDate_end_button.setOnClickListener(this);
		bt_birthday.setOnClickListener(this);
		initinfo();
		return view;
	}

	private void initinfo() {
		// TODO Auto-generated method stub
		SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
				getActivity(), "MiniOne");
		MiniApplyInfo miniApplyInfo = preferencesUtils.readObj();
		if (miniApplyInfo != null) {
			if (miniApplyInfo.getIdCard().equals(
					Contents.response.getResult().getIdNo())) {
				bt_expiryDate_start_button
						.setText(miniApplyInfo.getIdTermBgn());
				bt_expiryDate_end_button.setText(miniApplyInfo.getIdTermEnd());
				household_email.setText(miniApplyInfo.getIndivRegAddrZip());
				et_birthPlace.setText(miniApplyInfo.getIndivRegAddr());
				et_com_address_more.setText(miniApplyInfo.getHujiXiangQing());
			}
		} else {
			preferencesUtils.clearData();
		}
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
		case R.id.mini_loan_identity_next_button:
			Result temp = null;
			// 姓名
			String name = et_user_name.getText().toString().trim();
			if (name.trim().equals("")) {
				showEditDialog(v, "请输入中文姓名！");
				return;
			}
			temp = RegexUtils2.rangelength("姓名", name, 1, 30);
			if (temp.match) {
				// wz-22
				temp = RegexUtils2.chinese("姓名", name);
				if (!temp.match) {
					result = temp;

					showEditDialog(v, result.msg);
					et_user_name.requestFocus();
					return;
				}
			} else {
				result = temp;

				showEditDialog(v, result.msg);
				et_user_name.requestFocus();
				return;
			}

			String Ename = et_yingwenming.getText().toString().trim();
			if (name.equals("")) {
				showEditDialog(v, "请输入中文姓名！");
				return;
			}
			// 证件号码
			String cardNum = et_identify_cardNum.getText().toString().trim();
			IdcardValidator idCardValidator = new IdcardValidator();
			if (cardNum.equals("")) {
				showEditDialog(v, "请输入身份证号！");
				return;
			}
			if (!idCardValidator.isValidatedAllIdcard(cardNum)) {
				result = new Result(false, "证件号码有误!");
				showEditDialog(v, result.msg);
				et_identify_cardNum.requestFocus();
				return;
			}
			// 证件有限期 开始
			String ex_str = bt_expiryDate_start_button.getText().toString();
			if (ex_str == null || "".equals(ex_str)) {
				showEditDialog(v, "请选择证件有效期开始日期");
				return;
			}

			// 证件有限期 结束
			String ex_end = bt_expiryDate_end_button.getText().toString();
			if (ex_end == null || "".equals(ex_end)) {
				showEditDialog(v, "请选择证件有效期结束日期");
				return;
			}

			int msg = compare_date(ex_str, ex_end);
			if (msg == 1) {
				showEditDialog(v, "起始日期不得大于结束日期！");
				return;
			} else if (msg == 3) {
				showEditDialog(v, "起始日期不得大于当前日期！");
				return;
			} else if (msg == 4) {
				showEditDialog(v, "起始日期不得等于结束日期！");
				return;
			} else if (msg == 5) {
				showEditDialog(v, "结束日期不得小于当前日期！");
				return;
			}
			// 户籍地址
			String permanentAddress = et_birthPlace.getText().toString().trim();
			if (permanentAddress.equals("")) {
				result = new Result(false, "请选择户籍地址省市区信息！");
				showEditDialog(v, result.msg);
				et_birthPlace.requestFocus();
				return;
			}

			// 户籍地址详情
			if (et_com_address_more.getText().toString().equals("")) {
				showEditDialog(v, "请输入户籍地址详情");
				return;
			}
			// 邮编
			String et_household_email = household_email.getText().toString();
			temp = RegexUtils2.zip("户籍邮编", et_household_email);
			if (!temp.match) {
				result = temp;
				showEditDialog(v, result.msg);
				return;
			}

			MiniApplyInfo applyInfo = new MiniApplyInfo();
			String custName = et_user_name.getText().toString().trim();
			String custSpell = et_yingwenming.getText().toString().trim();
			String idNo = et_identify_cardNum.getText().toString().trim();
			String sex = et_sex.getText().toString().trim();
			String apptStartDate = bt_birthday.getText().toString().trim();
			String idTermBgn = bt_expiryDate_start_button.getText().toString()
					.trim();
			String idTermEnd = bt_expiryDate_end_button.getText().toString()
					.trim();
			String indivRegAddrZip = household_email.getText().toString()
					.trim();
			String birthplace = et_birthPlace.getText().toString().trim();
			applyInfo.setIdCard(Contents.response.getResult().getIdNo());
			applyInfo.setCustName(custName);
			applyInfo.setCustSpell(custSpell);
			applyInfo.setIdNo(idNo);
			applyInfo.setSex(sex);
			applyInfo.setApptStartDate(apptStartDate);
			applyInfo.setIdTermBgn(idTermBgn);
			applyInfo.setIdTermEnd(idTermEnd);
			applyInfo.setIndivRegAddr(birthplace);
			applyInfo.setIndivRegAddrZip(indivRegAddrZip);
			applyInfo.setHujiXiangQing(et_com_address_more.getText().toString());
			SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
					getActivity(), "MiniOne");
			preferencesUtils.saveObj(applyInfo);

			MiniBasicInfoFragment fThr = new MiniBasicInfoFragment();
			FragmentManager fm = getFragmentManager();
			FragmentTransaction tx = fm.beginTransaction();
			tx.replace(R.id.id_content, fThr, "Three");
			// tx.remove(fm.findFragmentByTag("Two"));
			tx.addToBackStack("Two");
			tx.commit();
			break;
		// 证件日期前半部分
		case R.id.bt_expiryDate_start_button:
			System.out.println("证件前部部分");
			showDatePickerFragemnt();
			break;
		// 证件日期后半部分
		case R.id.bt_expiryDate_end_button:

			System.out.println("证件后部部分");
			showDatePickerFragemnt2();
			break;
		case R.id.back2:
			// 返回
			getActivity().finish();
			break;
		case R.id.et_birthPlace:
			// 选择省市
			city_layout.setVisibility(View.VISIBLE);
			break;
		case R.id.choose_button:
			// 选择省市
			city_layout.setVisibility(View.GONE);
			et_birthPlace.setText(mCurrentProviceName + mCurrentCityName
					+ mCurrentAreaName);
			break;

		}

	}

	private void updateGenderAndBirthday(String cardNum) {
		if (cardNum.length() == 18) {
			String birthday = cardNum.substring(6, 10) + "-"
					+ cardNum.substring(10, 12) + "-"
					+ cardNum.substring(12, 14);
			bt_birthday.setText(birthday);
			int sex = Integer.valueOf(cardNum.substring(16, 17));
			et_sex.setText((sex + 2) % 2 == 0 ? "女" : "男");
		} else if (cardNum.length() == 15) {
			String birthday = "19" + cardNum.substring(6, 8) + "-"
					+ cardNum.substring(8, 10) + "-"
					+ cardNum.substring(10, 12);
			bt_birthday.setText(birthday);
			int sex = Integer.valueOf(cardNum.substring(14, 15));
			et_sex.setText((sex + 2) % 2 == 0 ? "女" : "男");

		}
		if ("男".equals(et_sex.getText().toString())) {
		} else if ("女".equals(et_sex.getText().toString())) {
		}

	}

	public void showEditDialog(View view, String str) {
		DialogFragment myFragment = NameDialogFragment.newInstance(str);
		myFragment.show(getFragmentManager(), "EditNameDialog");
	}

	public class DatePickerFragment extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {

		int _year = 1970;
		int _month = 0;
		int _day = 0;

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);
			return new DatePickerDialog(getActivity(), this, year, month, 0);
		}

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// 日期选择完成事件
			_year = year;
			_month = monthOfYear + 1;
			_day = dayOfMonth;

			bt_expiryDate_start_button.setText(getValue());
		}

		private String getValue() {
			String date = "";
			if (_month < 10) {
				date = "" + _year + "-0" + _month + "-" + _day;
			} else if (_month > 10) {
				date = "" + _year + "-" + _month + "-" + _day;
			}
			return date;
		}

	}

	private void showBirthdayFragemnt() {
		DialogFragment fragment = new BirthdayrFragment();
		fragment.show(getFragmentManager(), "datePicker");

	}

	// 2

	public class BirthdayrFragment extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {

		int _year = 1970;
		int _month = 0;
		int _day = 0;

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);
			return new DatePickerDialog(getActivity(), this, year, month, 0);
		}

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// 日期选择完成事件，取消时不会触发
			_year = year;
			_month = monthOfYear + 1;
			_day = dayOfMonth;

			bt_birthday.setText(getValue());
		}

		private String getValue() {
			return "" + _year + "-" + _month + "-" + _day;
		}

	}

	private void showDatePickerFragemnt() {
		DialogFragment fragment = new DatePickerFragment();
		fragment.show(getFragmentManager(), "datePicker");

	}

	// 2

	public class DatePickerFragment2 extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {

		int _year = 1970;
		int _month = 0;
		int _day = 0;

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);
			return new DatePickerDialog(getActivity(), this, year, month, 0);
		}

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// 日期选择完成事件，取消时不会触发
			_year = year;
			_month = monthOfYear + 1;
			_day = dayOfMonth;

			bt_expiryDate_end_button.setText(getValue());
		}

		private String getValue() {
			String date = "";
			if (_month < 10) {
				date = "" + _year + "-0" + _month + "-" + _day;
			} else if (_month > 10) {
				date = "" + _year + "-" + _month + "-" + _day;
			}
			return date;
		}

	}

	private void showDatePickerFragemnt2() {
		DialogFragment fragment = new DatePickerFragment2();
		fragment.show(getFragmentManager(), "datePicker2");

	}

	public static int compare_date(String DATE1, String DATE2) {

		Date datenow = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String timenow = format.format(datenow);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			Date dtnow = df.parse(timenow);
			if (dtnow.getTime() < dt1.getTime()) {
				return 3;
			}
			if (dtnow.getTime() > dt2.getTime()) {
				return 5;
			}

			if (dt1.getTime() > dt2.getTime()) {
				System.out.println("dt1大");
				return 1;
				// 开始日期大
			} else if (dt1.getTime() == dt2.getTime()) {
				return 4;
			}

			else if (dt1.getTime() < dt2.getTime()) {
				System.out.println("dt2大");
				// 结束日期大
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
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