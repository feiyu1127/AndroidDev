package com.yucheng.byxf.mini.easyloan.ui;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.yucheng.apfx.utl.RegexResult;
import com.yucheng.byxf.mini.domain.MiniPersonInfo;
import com.yucheng.byxf.mini.eloan.domain.BasicInfo;
import com.yucheng.byxf.mini.map.DemoApplication;
import com.yucheng.byxf.mini.ui.MiniCardScanningActivity;
import com.yucheng.byxf.mini.ui.MiniRecordEssentialInfoEasyActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.RegexCust;
import com.yucheng.byxf.util.IdcardValidator;

public class RelaxedLoanTwoIdentityInfo extends BaseRelaxedLoanActivity {
	private static final String _Tag = "CardScanningActivity";
	private Button easy_loan_identity_next_button;
	private EditText et_user_name;
	private EditText et_identify_cardNum;
	private EditText et_sex;
	private EditText et_household_Address;
	private EditText et_household_email;

	private TextView bt_birthday;
	private Button bt_expiryDate_start_button;
	private Button bt_expiryDate_end_button;

	// 日期选择器
	private final int START_DATE_SELECT = 2;
	private final int END_DATE_SELECT = 3;
	private Calendar calendar = Calendar.getInstance();
	// 验证结果
	private RegexResult result;
	// 身份信息有误
	private final int INFO_ERROR = 4;
	public BasicInfo basicInfo;
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		setContentView(R.layout.easy_loan_two_identityinfo);
		super.init();
//		if (application.getAssertInfo() == null) {
//			SharedPreferencesUtils preferencesUtils =new SharedPreferencesUtils(this);
//			if(preferencesUtils.hasData("bt_expiryDate_start_button")){
//				editinit();
//			}
//		}
//		SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
//				this);
		System.out.println("==pre===>"+preferencesUtils.getData("et_identify_cardNum", ""));
//		System.out.println(	"==reppp=>"+Contents.response.getResult().getIdNo());
/*		if(preferencesUtils.hasData("et_identify_cardNum")){
			System.out.println("有et_identify_cardNum");
			if (!preferencesUtils.getData("et_identify_cardNum", "").equals(
					Contents.response.getResult().getIdNo())) {
				System.out.println("应该清空");
				preferencesUtils.clearData();
				//------------存登陆标志信息
				preferencesUtils.setData("login_sign","login_sign" );
			} 
		}*/
//		if (!preferencesUtils.getData("et_identify_cardNum", "").equals(
//				Contents.response.getResult().getIdNo())) {
//			preferencesUtils.clearData();
//		} 
//		System.out.println("1="+preferencesUtils.getData("et_identify_cardNum", "")+"2=="+Contents.response.getResult().getIdNo());
		if (Contents.isChoice) {
			preferencesUtils.clearData();
			//------------存登陆标志信息
			preferencesUtils.setData("login_sign","login_sign" );
		}else{
			if(preferencesUtils.hasData("et_identify_cardNum")
					&& (Contents.response!= null && Contents.response.getResult()!= null
									&& Contents.response.getResult().getIdNo()!=null)){
				System.out.println("有et_identify_cardNum");
				if (!(preferencesUtils.getData("et_identify_cardNum", "")).equals(
						Contents.response.getResult().getIdNo())) {
					System.out.println("应该清空");
					preferencesUtils.clearData();
					//------------存登陆标志信息
					preferencesUtils.setData("login_sign","login_sign" );
				} 
			}
		
			editinit();
		}
		
		easy_loan_head.setText("身份信息采集");
		easy_loan_identity_next_button = (Button) findViewById(R.id.easy_loan_identity_next_button);
		easy_loan_identity_next_button.setOnClickListener(this);
		// 初始化控件；
		et_user_name = (EditText) findViewById(R.id.et_user_name);
		et_identify_cardNum = (EditText) findViewById(R.id.et_identify_cardNum);
		et_sex = (EditText) findViewById(R.id.et_sex);
		bt_birthday = (TextView) findViewById(R.id.bt_birthday);
		// 证件期限
		bt_expiryDate_start_button = (Button) findViewById(R.id.bt_expiryDate_start_button);
		bt_expiryDate_end_button = (Button) findViewById(R.id.bt_expiryDate_end_button);
		bt_expiryDate_start_button.setOnClickListener(this);
		bt_expiryDate_end_button.setOnClickListener(this);

		et_household_Address = (EditText) findViewById(R.id.et_birthPlace);
		et_household_email = (EditText) findViewById(R.id.et_household_email);

		// initOldClientInfo();
//		String code=Contents.response.getResult().getPcCode();
//		System.out.println("白名单："+code);
		setData();
	}

	private void setData(){
		et_user_name.setText(application.getResponse().getResult()
				.getCipNamecn());
		et_identify_cardNum.setText(application.getResponse().getResult()
				.getIdNo());
		// 自动获取性别和生日；
		updateGenderAndBirthday(application.getResponse().getResult().getIdNo());

		bt_expiryDate_start_button.setText(application.getBasicInfo()
				.getExpiryStartDate());
		bt_expiryDate_end_button.setText(application.getBasicInfo()
				.getExpiryEndDate());
		et_household_Address
				.setText(application.getBasicInfo().getBirthPlace());
		et_household_email.setText(application.getBasicInfo()
				.getHousehold_email());
	}
	
	private void shareclear() {
//		SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
//				this);
		if (preferencesUtils.hasData("bt_expiryDate_start_button")) {
//			preferencesUtils.removeData("et_user_name");
			preferencesUtils.removeData("et_identify_cardNum");
			preferencesUtils.removeData("bt_expiryDate_start_button");
			preferencesUtils.removeData("bt_expiryDate_end_button");
			preferencesUtils.removeData("et_household_Address");
			preferencesUtils.removeData("et_household_email");
		}
	}
	
	
	private void shareinit() {
//		SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
//				this);
//		preferencesUtils.setData("et_user_name", application.getResponse().getResult()
//				.getCipNamecn());
		preferencesUtils.setData("et_identify_cardNum", application.getResponse().getResult()
				.getIdNo());
		preferencesUtils.setData("bt_expiryDate_start_button", application.getBasicInfo()
				.getExpiryStartDate());
		preferencesUtils.setData("bt_expiryDate_end_button", application.getBasicInfo()
				.getExpiryEndDate());
		preferencesUtils.setData("et_household_Address", application.getBasicInfo().getBirthPlace());
		preferencesUtils.setData("et_household_email", application.getBasicInfo()
				.getHousehold_email());
	}
	
	
	private void editinit(){
		
//		SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
//				this);
		if (preferencesUtils.hasData("bt_expiryDate_start_button")) {
			application.getBasicInfo().setExpiryStartDate(preferencesUtils.getData("bt_expiryDate_start_button", ""));
			application.getBasicInfo().setExpiryEndDate(preferencesUtils.getData("bt_expiryDate_end_button", ""));
			application.getBasicInfo().setBirthPlace(preferencesUtils.getData("et_household_Address", ""));
			application.getBasicInfo().setHousehold_email(preferencesUtils.getData("et_household_email", ""));
		}else{
			//填充个人信息的数据
			if(null != Contents.response
					&& null != Contents.response.getResult()
					&& (null != Contents.response.getResult().getRegAddr())
							&&!Contents.response.getResult().getRegAddr().equals("")){
				//居住地址
				application.getBasicInfo().setBirthPlace(Contents.response.getResult().getRegAddr());
			}
			
			
		}
		
//		et_user_name.setText(preferencesUtils.getData("et_user_name", ""));
//		et_identify_cardNum.setText(preferencesUtils.getData("et_identify_cardNum", ""));
//		bt_expiryDate_start_button.setText(preferencesUtils.getData("bt_expiryDate_start_button", ""));
//		bt_expiryDate_end_button.setText(preferencesUtils.getData("bt_expiryDate_end_button", ""));
//		et_household_Address.setText(preferencesUtils.getData("et_household_Address", ""));
//		et_household_email.setText(preferencesUtils.getData("et_household_email", ""));
		
	}
		
	@Override
	protected void onResume() {
		
		super.onResume();
	}

	private void updateGenderAndBirthday(String cardNum) {
		if (cardNum.length() == 18) {
			String birthday = cardNum.substring(6, 10) + "-"
					+ cardNum.substring(10, 12) + "-"
					+ cardNum.substring(12, 14);
			bt_birthday.setText(birthday);
			application.getBasicInfo().setBirthday(birthday);

			int sex = Integer.valueOf(cardNum.substring(16, 17));
			et_sex.setText((sex + 2) % 2 == 0 ? "女" : "男");
			application.getBasicInfo().setSex((sex + 2) % 2 == 0 ? "女" : "男");

		} else if (cardNum.length() == 15) {
			String birthday = "19" + cardNum.substring(6, 8) + "-"
					+ cardNum.substring(8, 10) + "-"
					+ cardNum.substring(10, 12);
			bt_birthday.setText(birthday);
			application.getBasicInfo().setBirthday(birthday);

			int sex = Integer.valueOf(cardNum.substring(14, 15));
			et_sex.setText((sex + 2) % 2 == 0 ? "女" : "男");
			application.getBasicInfo().setSex((sex + 2) % 2 == 0 ? "女" : "男");
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		// 用来获取日期和时间的
		Dialog dialog = null;
		switch (id) {
		case START_DATE_SELECT:
			DatePickerDialog.OnDateSetListener dateListener2 = new DatePickerDialog.OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker datePicker, int year,
						int month, int dayOfMonth) {
					// Calendar月份是从0开始,所以month要加1
					bt_expiryDate_start_button.setText(year
							+ "-"
							+ (String.valueOf(month + 1).length() == 1 ? "0"
									+ (month + 1) : (month + 1))
							+ "-"
							+ (String.valueOf(dayOfMonth).length() == 1 ? "0"
									+ (dayOfMonth) : (dayOfMonth)));
					application.getBasicInfo().setExpiryStartDate(
							bt_expiryDate_start_button.getText().toString());
				}
			};
			dialog = new DatePickerDialog(this, dateListener2,
					calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DAY_OF_MONTH));
			break;
		case END_DATE_SELECT:
			DatePickerDialog.OnDateSetListener dateListener3 = new DatePickerDialog.OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker datePicker, int year,
						int month, int dayOfMonth) {
					// Calendar月份是从0开始,所以month要加1
					bt_expiryDate_end_button.setText(year
							+ "-"
							+ (String.valueOf(month + 1).length() == 1 ? "0"
									+ (month + 1) : (month + 1))
							+ "-"
							+ (String.valueOf(dayOfMonth).length() == 1 ? "0"
									+ (dayOfMonth) : (dayOfMonth)));
					application.getBasicInfo().setExpiryEndDate(
							bt_expiryDate_end_button.getText().toString());
				}
			};
			dialog = new DatePickerDialog(this, dateListener3,
					calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DAY_OF_MONTH));
			break;
		default:
			break;
		}
		return dialog;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.easy_loan_identity_next_button:
		  RegexResult temp = null;
		
			// 证件期限
			String expiryDateStart = bt_expiryDate_start_button.getText()
					.toString().trim();
			String expiryDateEnd = bt_expiryDate_end_button.getText()
					.toString().trim();
			if ("".equals(expiryDateStart) || "".equals(expiryDateEnd)) {
				result = new RegexResult(false, "请填写完证件期限！");
				showDialog(INFO_ERROR);
				return;
			} else {
				String currdt = getTheDayOneMonthAgo();
				String msg = checkDate(expiryDateStart, expiryDateEnd, currdt);
				if (!"".equals(msg)) {
					result = new RegexResult(false, msg);
					showDialog(INFO_ERROR);
					return;
				}
			} // 籍贯
			String birthPlace = et_household_Address.getText().toString()
					.trim();
			if ("".equals(birthPlace)) {
				result = new RegexResult(false, "请填写户籍地址!");
				showDialog(INFO_ERROR);
				et_household_Address.requestFocus();
				return;
			}
	
			
			// 籍贯
			String household_email = et_household_email.getText().toString()
					.trim();
			if ("".equals(household_email)) {
				result = new RegexResult(false, "请填写户籍邮编!");
				showDialog(INFO_ERROR);
				et_household_email.requestFocus();
				return;
			}
			if (RegexCust.nosymbol(birthPlace) == false) {
				result = new RegexResult(false, "户籍地址禁止输入符号等字符");
				showDialog(INFO_ERROR);
				et_household_Address.requestFocus();
				return;
			}
			if (household_email.length() != 6) {
				result = new RegexResult(false, "户籍邮编位数有误，应为六位。");
				showDialog(INFO_ERROR);
				et_household_email.requestFocus();
				return;
			}
			// 把修改过的数据写入内存；
			application.getBasicInfo().setBirthPlace(
					et_household_Address.getText().toString().trim());
			application.getBasicInfo().setHousehold_email(
					et_household_email.getText().toString().trim());
			
			shareclear();
			shareinit();
			break;
		case R.id.bt_expiryDate_start_button:
			showDialog(START_DATE_SELECT);
			break;
		case R.id.bt_expiryDate_end_button:
			showDialog(END_DATE_SELECT);
			break;
		default:
			break;
		}
		super.onClick(v);
	}

	@Override
	protected Dialog onCreateDialog(int id, Bundle args) {
		Dialog dialog = null;
		StringBuffer sb = new StringBuffer();

		switch (id) {
		case INFO_ERROR:
			sb.append(result.msg);
			dialog = DialogUtil.showDialogOneButton2(
					RelaxedLoanTwoIdentityInfo.this, sb.toString());
			break;
		default:
			break;
		}
		return super.onCreateDialog(id, args);
	}

	/**
	 * 检查证件的有效性
	 * 
	 * @param beginDt
	 * @param endDt
	 * @param currdt
	 * @return
	 */
	private String checkDate(String beginDt, String endDt, String currdt) {
		String msg = "";
		String currentDt = currdt.substring(0, 4) + currdt.substring(5, 7)
				+ currdt.substring(8, 10);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		if (!"".equals(beginDt) && beginDt != null) {
			beginDt = beginDt.replaceAll("-", "");
			if (beginDt.trim().length() != 8) {
				msg += "证件起始日期有误;";
			} else {
				try {
					Date ds = df.parse(beginDt);
					String ss = df.format(ds);
					if (!beginDt.equals(ss) && beginDt != ss) {
						msg += "证件起始日期 有误;";
					} else {
						if (beginDt.compareTo(currentDt) > 0) {
							msg += "证件起始日期 大于证件结束日期;";
						} else {
							msg = "";
						}
					}
				} catch (Exception e) {
					msg += "证件起始日期有误;";
				}
			}
		}
		return msg;
	}

	@SuppressWarnings("deprecation")
	private String getTheDayOneMonthAgo() {
		Date date = new Date();
		int month = date.getMonth();
		if (month == 0) {
			date.setYear(date.getYear() - 1);
			date.setMonth(11);
		} else {
			date.setMonth(month - 1);
		}
		return format(date, "yyyy-MM-dd");
	}

	/**
	 * 将指定的时间格式格式化为指定的时间字符串
	 * 
	 * @param date
	 *            时间对象
	 * 
	 * @param format
	 *            格式
	 * 
	 * @return 时间字符串
	 */
	private String format(Date date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		String s = df.format(date);
		return s;
	}

	private void initOldClientInfo() {
		// TODO Auto-generated method stub
		// if (application.getClientInfo() != null && application.getOldCilent1)
		// {
		// // 把数据写入内存；
		// application.setNick_name(application.getClientInfo().getResult()
		// .getCustName());
		// application.setExpiryStartDate(application.getClientInfo()
		// .getResult().getIdTermBgn());
		// application.setExpiryEndDate(application.getClientInfo()
		// .getResult().getIdTermEnd());
		// application.setBirthPlace(application.getClientInfo().getResult()
		// .getIndivRegAddr());
		// application.setHousehold_email(application.getClientInfo()
		// .getResult().getIndivRegZip());
		// application.getOldCilent1 = false;
		// }
	}
}