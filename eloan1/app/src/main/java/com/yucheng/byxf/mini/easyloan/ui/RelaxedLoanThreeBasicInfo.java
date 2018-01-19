package com.yucheng.byxf.mini.easyloan.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Toast;

import com.yucheng.apfx.utl.RegexResult;
import com.yucheng.byxf.mini.domain.MiniPersonInfo;
import com.yucheng.byxf.mini.eloan.domain.BasicInfo;
import com.yucheng.byxf.mini.ui.MiniRecordEssentialInfoEasyActivity;
import com.yucheng.byxf.mini.ui.MiniWorkInfoActivity1;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.ButtonUtil;
import com.yucheng.byxf.mini.utils.CommonUtil;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.RegexCust;

/**
 * 轻松付与轻松贷的职业信息
 * 
 * @author danny
 */
public class RelaxedLoanThreeBasicInfo extends BaseRelaxedLoanActivity {
	private Button identity_next_button;
	private Button bt_getcommon_address;
	private RadioGroup rg_marriage_info;
	private EditText et_house_hold;
	private EditText live_address;
	private EditText et_postcode;
	private Spinner sp_house_type;
	private Spinner sp_cultrue;
	private EditText et_area_code;
	private EditText et_tel_code;
	private EditText et_electron_mail;
	private EditText et_phone_code;

	private String[] house_type = { "请选择", "自购无贷款", "自购有贷款", "单位宿舍", "与亲属同住",
			"租住", "其它" };
	private String[] house_typeCode = { "", "1", "2", "3", "4", "5", "7" };
	private String[] cultrue_degree = { "请选择", "博士", "硕士", "本科", "大专", "高中或中专",
			"初中及以下" };
	private String[] cultrue_degreeCode = { "", "08", "09", "10", "20", "30",
			"40" };
	private String save_house_type;
	private String save_cultrue_degree;
	// 验证结果
	private RegexResult result;
	// 基本信息有误
	private final int INFO_ERROR = 0;

	private LinearLayout zujin_layout;
	private EditText et_house_zujin;

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		setContentView(R.layout.easy_loan_three_basicinfo);
		super.init();

		editinit();

		easy_loan_head.setText("基本资料");
		identity_next_button = (Button) findViewById(R.id.easy_loan_basicinfo_next_button);
		identity_next_button.setOnClickListener(this);
		bt_getcommon_address = (Button) findViewById(R.id.bt_getcommon_address);
		bt_getcommon_address.setOnClickListener(this);
		// 控件
		et_house_hold = (EditText) findViewById(R.id.et_house_hold);
		rg_marriage_info = (RadioGroup) findViewById(R.id.rg_marriage_info);
		rg_marriage_info
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup arg0, int arg1) {
						switch (arg0.getCheckedRadioButtonId()) {
						case R.id.marital_status_dis_no:
							application.getBasicInfo().setMarriageStatue("未婚");
							application.getBasicInfo().setMarriageStatueCode(
									"10");
							break;
						case R.id.marital_status_mar_no:
							application.getBasicInfo().setMarriageStatue("已婚");
							application.getBasicInfo().setMarriageStatueCode(
									"20");
							break;
						case R.id.marital_status_other_no:
							application.getBasicInfo().setMarriageStatue("其它");
							application.getBasicInfo().setMarriageStatueCode(
									"90");
							break;
						}
					}
				});
		et_house_zujin = (EditText) findViewById(R.id.et_house_zujin);
		zujin_layout = (LinearLayout) findViewById(R.id.zujin_layout);
		live_address = (EditText) findViewById(R.id.live_address);
		et_postcode = (EditText) findViewById(R.id.et_postcode);
		sp_house_type = (Spinner) findViewById(R.id.sp_house_type);
		CommonUtil.setSpinner(this, house_type, sp_house_type);
		sp_house_type.setOnItemSelectedListener(this);
		sp_cultrue = (Spinner) findViewById(R.id.sp_cultrue);
		CommonUtil.setSpinner(this, cultrue_degree, sp_cultrue);
		sp_cultrue.setOnItemSelectedListener(this);
		et_area_code = (EditText) findViewById(R.id.et_area_code);
		et_tel_code = (EditText) findViewById(R.id.et_tel_code);
		et_electron_mail = (EditText) findViewById(R.id.et_electron_mail);
		et_phone_code = (EditText) findViewById(R.id.et_phone_code);

		initOldClientInfo();

		// if (application.getAssertInfo() == null) {
		// SharedPreferencesUtils preferencesUtils =new
		// SharedPreferencesUtils(this);
		// if(preferencesUtils.hasData("et_house_hold")){
		// editinit();
		// }
		// }

		setData();
	}

	private void shareclear() {
		// SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
		// this);
		if (preferencesUtils.hasData("et_house_hold")) {

			preferencesUtils.removeData("rg_marriage_info");
			preferencesUtils.removeData("et_house_hold");
			preferencesUtils.removeData("live_address");
			preferencesUtils.removeData("et_postcode");
			preferencesUtils.removeData("et_area_code");
			preferencesUtils.removeData("et_tel_code");
			preferencesUtils.removeData("et_electron_mail");
			preferencesUtils.removeData("sp_house_type");
			preferencesUtils.removeData("sp_cultrue");
			preferencesUtils.removeData("et_phone_code");
			// preferencesUtils.removeData("et_house_zujin");
		}

		if (preferencesUtils.hasData("et_house_zujin")) {
			preferencesUtils.removeData("et_house_zujin");
		}

	}

	private void shareinit() {
		// SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
		// this);
		preferencesUtils.setData("rg_marriage_info", application.getBasicInfo()
				.getMarriageStatue());
		preferencesUtils.setData("et_house_hold", application.getBasicInfo()
				.getProvidePerson());
		preferencesUtils.setData("live_address", application.getBasicInfo()
				.getLiveAddress());
		preferencesUtils.setData("et_postcode", application.getBasicInfo()
				.getLivePostcode());
		preferencesUtils.setData("et_area_code", application.getBasicInfo()
				.getZoneCode());
		preferencesUtils.setData("et_tel_code", application.getBasicInfo()
				.getTelPhone());

		preferencesUtils.setData("et_electron_mail", application.getBasicInfo()
				.getElecTronEmail());
		preferencesUtils.setData("sp_house_type", application.getBasicInfo()
				.getLiveProperties());
		preferencesUtils.setData("sp_cultrue", application.getBasicInfo()
				.getCultureDegree());
		preferencesUtils.setData("et_house_zujin", application.getBasicInfo()
				.getIndivRentAmt());
//wz0409
		 preferencesUtils.setData("et_phone_code",application.getBasicInfo()
					.getMobilePhone());

	}

	private void editinit() {
		// SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
		// this);
		if (preferencesUtils.hasData("rg_marriage_info")) {
			application.getBasicInfo().setMarriageStatue(
					preferencesUtils.getData("rg_marriage_info", ""));
			System.out.println("==>hunyin"+preferencesUtils.getData("rg_marriage_info", ""));
			// ======lizi
			// application.getProfessionInfo().setCompanyName(preferencesUtils.getData("et_com_name",
			// ""));
			application.getBasicInfo().setProvidePerson(
					preferencesUtils.getData("et_house_hold", ""));
			application.getBasicInfo().setLiveAddress(
					preferencesUtils.getData("live_address", ""));
			application.getBasicInfo().setLivePostcode(
					preferencesUtils.getData("et_postcode", ""));
			application.getBasicInfo().setZoneCode(
					preferencesUtils.getData("et_area_code", ""));
			application.getBasicInfo().setTelPhone(
					preferencesUtils.getData("et_tel_code", ""));
			application.getBasicInfo().setElecTronEmail(
					preferencesUtils.getData("et_electron_mail", ""));
			application.getBasicInfo().setLiveProperties(
					preferencesUtils.getData("sp_house_type", ""));
			application.getBasicInfo().setCultureDegree(
					preferencesUtils.getData("sp_cultrue", ""));
			System.out.println("xueli===>"+preferencesUtils.getData("sp_cultrue", ""));
			application.getBasicInfo().setIndivRentAmt(
					preferencesUtils.getData("et_house_zujin", ""));
	//wz0409
			application.getBasicInfo().setMobilePhone(
					preferencesUtils.getData("et_phone_code", ""));
			
		}else{
			if (Contents.isChoice==false) {
				if(Contents.response != null && Contents.response.getResult() != null) {
					if ((null != Contents.response.getResult().getMarital()) && !Contents.response.getResult().getMarital().equals("")) {
						//婚姻

						if (Contents.response.getResult().getMarital().equals("10")) {
							application.getBasicInfo().setMarriageStatue("未婚");
							application.getBasicInfo().setMarriageStatueCode("10");
						} else if (Contents.response.getResult().getMarital().equals("20")) {
							application.getBasicInfo().setMarriageStatue("已婚");
							application.getBasicInfo().setMarriageStatueCode("20");
						} else if (Contents.response.getResult().getMarital().equals("90")) {
							application.getBasicInfo().setMarriageStatue("其它");
							application.getBasicInfo().setMarriageStatueCode("90");
						}
					}
					if ((null != Contents.response.getResult().getLiveAddr()) && (null != Contents.response.getResult().getCurentResidentialAddr()) && !Contents.response.getResult().getLiveAddr().equals("") && !Contents.response.getResult().getCurentResidentialAddr().equals("")) {
						//居住地址
						application.getBasicInfo().setLiveAddress(Contents.response.getResult().getLiveAddr() + Contents.response.getResult().getCurentResidentialAddr());
					}
					if ((null != Contents.response.getResult().getDegree()) && !Contents.response.getResult().getDegree().equals("")) {
						//学历
						if (Contents.response.getResult().getDegree().equals("08")) {
							application.getBasicInfo().setCultureDegree("博士");
						} else if (Contents.response.getResult().getDegree().equals("09")) {
							application.getBasicInfo().setCultureDegree("硕士");
						} else if (Contents.response.getResult().getDegree().equals("10")) {
							application.getBasicInfo().setCultureDegree("本科");
						} else if (Contents.response.getResult().getDegree().equals("20")) {
							application.getBasicInfo().setCultureDegree("大专");
						} else if (Contents.response.getResult().getDegree().equals("30")) {
							application.getBasicInfo().setCultureDegree("高中或中专");
						} else if (Contents.response.getResult().getDegree().equals("40")) {
							application.getBasicInfo().setCultureDegree("初中及以下");
						}

						//	application.getBasicInfo().setCultureDegree(Contents.response.getResult().getDegree());
					}
				}
			}
		}

		// /** 现居住地址-省/市、区 **/
		// 1 et_house_hold.setText(preferencesUtils.getData("et_house_hold",
		// ""));
		// 1 live_address.setText(preferencesUtils.getData("live_address", ""));
		// 1 et_postcode.setText(preferencesUtils.getData("et_postcode", ""));
		// 1 et_tel_code.setText(preferencesUtils.getData("et_tel_code", ""));
		// 1
		// et_electron_mail.setText(preferencesUtils.getData("et_electron_mail",
		// ""));
		//
	

	}

	private void setData() {
		if(application != null) {
			BasicInfo basicInfo = application.getBasicInfo();
			if(basicInfo != null) {
				if (basicInfo.getMarriageStatueCode() != null && !"".equals(basicInfo.getMarriageStatueCode())) {
					if ("10".equals(basicInfo.getMarriageStatueCode())) {
						basicInfo.setMarriageStatue("未婚");
					} else if ("20".equals(basicInfo
							.getMarriageStatueCode())) {
						basicInfo.setMarriageStatue("已婚");
					} else {
						basicInfo.setMarriageStatue("其它");
					}
				}
				if (basicInfo.getMarriageStatue() != null && !"".equals(basicInfo.getMarriageStatue())) {
					for (int i = 0; i < rg_marriage_info.getChildCount(); i++) {
						RadioButton radioButton = (RadioButton) rg_marriage_info
								.getChildAt(i);
						if (radioButton.getText().equals(
								basicInfo.getMarriageStatue())) {
							radioButton.setChecked(true);
						}
					}
				}
				et_house_hold.setText(basicInfo.getProvidePerson());
				live_address.setText(basicInfo.getLiveAddress());
				et_postcode.setText(basicInfo.getLivePostcode());

				et_area_code.setText(basicInfo.getZoneCode());
				et_tel_code.setText(basicInfo.getTelPhone());
				et_electron_mail.setText(basicInfo.getElecTronEmail());
				et_house_zujin.setText(basicInfo.getIndivRentAmt());
				// 住宅性质；
				// if (application.getIndivLive().equals("1")) {
				// application.setIndivLive(house_type[1]);
				// } else if (application.getIndivLive().equals("2")) {
				// application.setIndivLive(house_type[2]);
				// } else if (application.getIndivLive().equals("3")) {
				// application.setIndivLive(house_type[3]);
				// } else if (application.getIndivLive().equals("4")) {
				// // MiniPersonInfo.houseNoture = stand_degrees[4];
				// application.setIndivLive(house_type[4]);
				// } else if (application.getIndivLive().equals("5")) {
				// application.setIndivLive(house_type[5]);
				// } else if (application.getIndivLive().equals("7")) {
				// application.setIndivLive(house_type[6]);
				// }

				if (basicInfo.getLivePropertiesCode()!= null && !"".equals(basicInfo.getLivePropertiesCode())) {
					for (int i = 0; i < house_typeCode.length; i++) {
						if (house_typeCode[i].equals(basicInfo
								.getLivePropertiesCode())) {
							basicInfo.setLiveProperties(house_type[i]);
						}
					}
				}

				if (basicInfo.getLiveProperties() != null && !"".equals(basicInfo.getLiveProperties())) {
					for (int i = 0; i < sp_house_type.getCount(); i++) {
						String item = house_type[i];
						if (item.equals(basicInfo.getLiveProperties())) {
							sp_house_type.setSelection(i);
						}
					}
				}
				// 文化程度
				// if (application.getIndivDegree().equals("08")) {
				// application.setIndivDegree(cultrue_degree[1]);
				// } else if (application.getIndivDegree().equals("09")) {
				// application.setIndivDegree(cultrue_degree[2]);
				// } else if (application.getIndivDegree().equals("10")) {
				// application.setIndivDegree(cultrue_degree[3]);
				// } else if (application.getIndivDegree().equals("20")) {
				// // MiniPersonInfo.houseNoture = stand_degrees[4];
				// application.setIndivDegree(cultrue_degree[4]);
				// } else if (application.getIndivDegree().equals("30")) {
				// application.setIndivDegree(cultrue_degree[5]);
				// } else if (application.getIndivDegree().equals("40")) {
				// application.setIndivDegree(cultrue_degree[6]);
				// }

				if (basicInfo.getCultureDegree()!= null && !"".equals(basicInfo.getCultureDegree())) {
					for (int i = 0; i < cultrue_degreeCode.length; i++) {
						if (cultrue_degreeCode[i].equals(basicInfo
								.getCultureDegree())) {
							basicInfo.setCultureDegree(
									cultrue_degree[i]);
						}
					}
				}

				if (basicInfo.getCultureDegree() != null && !"".equals(basicInfo.getCultureDegree())) {
					for (int i = 0; i < sp_cultrue.getCount(); i++) {
						String item = cultrue_degree[i];
						if (item.equals(basicInfo.getCultureDegree())) {
							sp_cultrue.setSelection(i);
						}
					}
				}
			}
			// 手机、
			if(application.getResponse() != null && application.getResponse().getResult()!= null)
				et_phone_code.setText(application.getResponse().getResult()
						.getCipMobile());
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub

		super.onResume();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// 下一步
		switch (v.getId()) {
		case R.id.easy_loan_basicinfo_next_button:
		  RegexResult temp = null; // 婚姻状况
			if (rg_marriage_info.getCheckedRadioButtonId() == -1) {
				result = new RegexResult(false, "请选择婚姻状况!");
				showDialog(INFO_ERROR);
				return;
			}
			// 供养人数
			String house_hold = et_house_hold.getText().toString().trim();
			temp = RegexCust.required("供养人数", house_hold);
			if (temp.match) {
				temp = RegexCust.numberNatural("供养人数", house_hold);
				if (temp.match == false) {
					result = temp;
					showDialog(INFO_ERROR);
					et_house_hold.requestFocus();
					return;
				}
			} else {
				result = temp;
				showDialog(INFO_ERROR);
				et_house_hold.requestFocus();
				return;
			}
			// 现居住地址
			String province = live_address.getText().toString().trim();
	
			
			temp = RegexCust.required("现居住地址", province);
			if (temp.match) {
				boolean flag = RegexCust.lengthMax(province, 200);
				if (flag == false) {
					result = new RegexResult(false, "现居住地址字符长度过长!");
					showDialog(INFO_ERROR);
					live_address.requestFocus();
					return;
				}
			} else {
				result = temp;
				showDialog(INFO_ERROR);
				live_address.requestFocus();
				return;
			}
			if (RegexCust.nosymbol(province) == false) {
				result = new RegexResult(false, "地址禁止输入符号等字符");
				showDialog(INFO_ERROR);
				live_address.requestFocus();
				return;
			}
			// 现居住邮编
			String postcode = et_postcode.getText().toString().trim();
			if ("".equals(postcode)) {
				result = new RegexResult(false, "请填写户籍邮编!");
				showDialog(INFO_ERROR);
				et_postcode.requestFocus();
				return;
			}
			if (postcode.length() != 6) {
				result = new RegexResult(false, "户籍邮编位数有误，应为六位。");
				showDialog(INFO_ERROR);
				et_postcode.requestFocus();
				return;
			}
			// 住宅性质
			if (save_house_type == null || "请选择".equals(save_house_type)) {
				result = new RegexResult(false, "请选择住宅性质!");
				showDialog(INFO_ERROR);
				return;
			}
			String indivRentAmt = et_house_zujin.getText().toString().trim();
			// 月租金
			if (zujin_layout.getVisibility() == View.VISIBLE) {
				indivRentAmt = et_house_zujin.getText().toString().trim();
				temp = RegexCust.required("月租金", indivRentAmt);
				if (temp.match) {
					temp = RegexCust.numberNatural("月租金", indivRentAmt);
					if (temp.match == false) {
						result = temp;
						showDialog(INFO_ERROR);
						et_house_hold.requestFocus();
						return;
					}
				} else {
					result = temp;
					showDialog(INFO_ERROR);
					et_house_hold.requestFocus();
					return;
				}
			}

			// 文化程度
			if (save_cultrue_degree == null
					|| "请选择".equals(save_cultrue_degree)) {
				result = new RegexResult(false, "请选择文化程度!");
				showDialog(INFO_ERROR);
				return;
			} // 住宅电话区号
			String areaCode = et_area_code.getText().toString().trim();
			if (areaCode != null && !areaCode.equals("")) {
				if (!RegexCust.numberNatural(areaCode)) {
					result = new RegexResult(false, "住宅电话区号格式不对!");
					showDialog(INFO_ERROR);
					et_area_code.requestFocus();
					return;
				} else {
					if (areaCode.length() > 4) {
						result = new RegexResult(false, "住宅电话区号长度不应大于4!");
						showDialog(INFO_ERROR);
						et_area_code.requestFocus();
						return;
					}
				}
			} // 住宅电话电话号码
			String phoneNo = et_tel_code.getText().toString().trim();
			temp = RegexCust.required("住宅电话号码", phoneNo);
			if (temp.match) { //
				temp = RegexCust.phoneFix("住宅电话号码", phoneNo);
				if (familyPhone(phoneNo) == false) {
					result = new RegexResult(false, "住宅电话号码格式不对!");
					showDialog(INFO_ERROR);
					et_tel_code.requestFocus();
					return;
				}
			} // 电子邮箱
			String mobile = et_electron_mail.getText().toString().trim();
			temp = RegexCust.required("电子邮箱", mobile);
			if (temp.match) {
				if (RegexCust.email(mobile) == false) {
					result = new RegexResult(false, "电子邮箱格式不对!");
					showDialog(INFO_ERROR);
					et_electron_mail.requestFocus();
					return;
				}
			} else {
				result = temp;
				showDialog(INFO_ERROR);
				et_electron_mail.requestFocus();
				return;
			}
			String phone = et_phone_code.getText().toString();
			temp = RegexCust.required("手机号码", phone);
			if (temp.match) {
				if (RegexCust.phoneMob(phone) == false) {
					result = new RegexResult(false, "手机号码格式不对!");
					showDialog(INFO_ERROR);
					et_phone_code.requestFocus();
					return;
				}
			} else {
				result = temp;
				showDialog(INFO_ERROR);
				et_phone_code.requestFocus();
				return;
			}
			
			// 数据写入内存；
			// 供养人数
			application.getBasicInfo().setProvidePerson(
					et_house_hold.getText().toString());
			// 现居住地址；
			application.getBasicInfo().setLiveAddress(
					live_address.getText().toString());
			// 邮编
			application.getBasicInfo().setLivePostcode(
					et_postcode.getText().toString());
			// 区号
			application.getBasicInfo().setZoneCode(
					et_area_code.getText().toString());
			application.getBasicInfo().setTelPhone(
					et_tel_code.getText().toString());
			application.getBasicInfo().setElecTronEmail(
					et_electron_mail.getText().toString());
			application.getBasicInfo().setIndivRentAmt(
					et_house_zujin.getText().toString());
			application.getBasicInfo().setMobilePhone(
					et_phone_code.getText().toString()); 
			shareclear();
			shareinit();

			break;
		case R.id.bt_getcommon_address:
			live_address.setText(application.getBasicInfo().getBirthPlace());
			et_postcode
					.setText(application.getBasicInfo().getHousehold_email());
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
					RelaxedLoanThreeBasicInfo.this, sb.toString());
			break;
		default:
			break;
		}
		return super.onCreateDialog(id, args);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		if (parent == sp_house_type) {
			save_house_type = (String) sp_house_type
					.getItemAtPosition(position);
			if (!"请选择".equals(save_house_type)) {
				sp_house_type
						.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
				if ("租住".equals(save_house_type)) {
					zujin_layout.setVisibility(View.VISIBLE);
				} else {
					zujin_layout.setVisibility(View.GONE);
				}
			} else {
				sp_house_type
						.setBackgroundResource(R.drawable.comm_spinner_selecter);
				zujin_layout.setVisibility(View.GONE);
			}
			sp_house_type.setPadding(15, 0, 0, 0);
			application.getBasicInfo().setLiveProperties(save_house_type);
			application.getBasicInfo().setLivePropertiesCode(
					house_typeCode[position]);
		}

		if (parent == sp_cultrue) {
			save_cultrue_degree = (String) sp_cultrue
					.getItemAtPosition(position);
			if (!"请选择".equals(save_cultrue_degree)) {
				sp_cultrue
						.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
			} else {
				sp_cultrue
						.setBackgroundResource(R.drawable.comm_spinner_selecter);
			}
			sp_cultrue.setPadding(15, 0, 0, 0);
			application.getBasicInfo().setCultureDegree(save_cultrue_degree);
			application.getBasicInfo().setCultureDegreeCode(
					cultrue_degreeCode[position]);
		}
		super.onItemSelected(parent, view, position, id);
	}

	// 初始化老客户信息；
	private void initOldClientInfo() {
		// TODO Auto-generated method stub
		// if (application.getClientInfo() != null && application.getOldCilent2)
		// {
		// // 把数据写入内存；
		// // 婚姻
		// application.setIndivMarital((application.getClientInfo()
		// .getResult().getIndivMarital()));
		// // 供养
		// application.setIndivDepNo((application.getClientInfo().getResult()
		// .getIndivDepNo()));
		// // 现居住地址；
		// application.setIndivLiveAddr((application.getClientInfo()
		// .getResult().getIndivLiveAddr()));
		// // 邮编
		// application.setContZip((application.getClientInfo().getResult()
		// .getContZip()));
		// // 住在性质
		// application.setIndivLive((application.getClientInfo().getResult()
		// .getIndivLive()));
		// // 文化程度；
		// application.setIndivDegree(((application.getClientInfo()
		// .getResult().getIndivDegree())));
		// // 区号
		// application.setContZone((((application.getClientInfo().getResult()
		// .getContZone()))));
		// // 电话
		// application.setContPhone((((application.getClientInfo().getResult()
		// .getContPhone()))));
		// // 电子邮箱
		// application.setIndivEmail(((((application.getClientInfo()
		// .getResult().getIndivEmail())))));
		// // 手机号
		// application.setIndivMobile(((((application.getClientInfo()
		// .getResult().getIndivMobile())))));
		// application.getOldCilent2 = false;
		// }
	}

	private String familyPhone = "[1-9](\\d{6,7})";

	public boolean familyPhone(String value) {
		return value.matches(familyPhone);
	}
}