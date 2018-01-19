package com.yucheng.byxf.mini.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.yucheng.apfx.utl.RegexResult;
import com.yucheng.byxf.comm.BaseInfo;
import com.yucheng.byxf.comm.CardInfo;
import com.yucheng.byxf.comm.MyApplication;
import com.yucheng.byxf.comm.ScreenManager;
import com.yucheng.byxf.mini.domain.MiniPersonInfo;
import com.yucheng.byxf.mini.message.CustInfoResponse;
import com.yucheng.byxf.mini.utils.CommonUtil;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.RegexCust;
import com.yucheng.byxf.mini.utils.ViewTools;
import com.yucheng.byxf.service.LoanPayInfo;
import com.yucheng.byxf.util.IdcardValidator;

/**
 * 基本信息
 * 
 * @author danny
 * 
 */
public class MiniRecordEssentialInfoEasyActivity extends BaseActivity {
	private static final String _Tag = "RecordEssentialInfoEasyActivity";

	private RelativeLayout rl_body;// 标题以外的部分

	// 下一步
	private Button next_button;
	// 婚姻状况
	private RadioGroup marital_info_radio;
	// 供养人数
	private EditText house_hold_text_value;
	// 文化程度
	private Spinner stand_degree_radio;
	private String save_stand_degree;
	private String[] stand_degrees = { "请选择", "博士", "硕士", "本科", "大专", "高中或中专",
			"初中及以下" };
	// 现居住地址
	private EditText province_value;
	// 同居住地址
	private Button permanent_address_img;
	// 区号
	private EditText area_card_value;
	// 电话号码
	private EditText phone_no_value;
	// 手机号码
	private EditText mobile_no_value;
	// 住宅性质
	private Spinner house_noture_radio;
	private String save_house_noture;
	private String[] house_notures = { "请选择", "自购无贷款", "自购有贷款", "单位宿舍",
			"与亲属同住", "租住", "其它" };
	// 在职状况
	private RadioGroup career_info_radio;

	// 基本信息有误
	private final int INFO_ERROR = 0;
	// 验证结果
	private RegexResult result;

	private ScreenManager screenManager;

	private boolean isSpinner = false;

	private ImageView iv_menu_common;

	@Override
	protected void onStart() {
		super.onStart();
		if (MyApplication.isExit()) {
			finish();
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.record_essential_info_easy);
		screenManager = ScreenManager.getScreenManager();
		screenManager.pushActivity(this);
		rl_body = (RelativeLayout) findViewById(R.id.record_essential_info_easy_rl_body);
		rl_body.setOnClickListener(new ButtonOnClickListener());
		iv_menu_common = (ImageView) findViewById(R.id.iv_menu_common);
		iv_menu_common.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		// 基本信息
		marital_info_radio = (RadioGroup) this
				.findViewById(R.id.marital_info_radio);
		marital_info_radio
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup arg0, int arg1) {
						switch (arg0.getCheckedRadioButtonId()) {
						case R.id.marital_status_dis_no:
							MiniPersonInfo.maritalInfo = "未婚";
							break;
						case R.id.marital_status_mar_no:
							MiniPersonInfo.maritalInfo = "已婚";
							break;
						case R.id.marital_status_other_no:
							MiniPersonInfo.maritalInfo = "其它";
							break;
						}

					}
				});
		house_hold_text_value = (EditText) this
				.findViewById(R.id.house_hold_text_value);
		house_hold_text_value.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				String house_hold = house_hold_text_value.getText().toString()
						.trim();
				MiniPersonInfo.house_hold = house_hold;
			}
		});
		stand_degree_radio = (Spinner) this
				.findViewById(R.id.record_essential_info_easy_sp_stand_degree);
		CommonUtil.setSpinner(this, stand_degrees, stand_degree_radio);
		stand_degree_radio
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						if (isSpinner) {
							save_stand_degree = (String) stand_degree_radio
									.getItemAtPosition(position);
							if (!"请选择".equals(save_stand_degree)) {
								stand_degree_radio
										.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
							} else {
								stand_degree_radio
										.setBackgroundResource(R.drawable.comm_spinner_selecter);
							}
							stand_degree_radio.setPadding(15, 0, 0, 0);
							MiniPersonInfo.standDegree = save_stand_degree;
						}
						isSpinner = true;
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						// TODO Auto-generated method stub

					}
				});
		province_value = (EditText) this
				.findViewById(R.id.permanent_address_province_value);
		province_value.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				String province = province_value.getText().toString().trim();
				MiniPersonInfo.province = province;
			}
		});
		// permanent_address_province_value = (EditText) this
		// .findViewById(R.id.permanent_address_province_value);
		permanent_address_img = (Button) this
				.findViewById(R.id.permanent_address_img);
		permanent_address_img.setOnClickListener(new ButtonOnClickListener());
		area_card_value = (EditText) this.findViewById(R.id.area_card_value);
		area_card_value.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				String areaCard = area_card_value.getText().toString().trim();
				MiniPersonInfo.areaCard = areaCard;
			}
		});
		phone_no_value = (EditText) this.findViewById(R.id.phone_no_value);
		phone_no_value.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				String phoneNo = phone_no_value.getText().toString().trim();
				MiniPersonInfo.phoneNo = phoneNo;
			}
		});
		mobile_no_value = (EditText) this.findViewById(R.id.mobile_no_value);
		mobile_no_value.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				String mobile = mobile_no_value.getText().toString().trim();
				MiniPersonInfo.mobile = mobile;
			}
		});
		house_noture_radio = (Spinner) this
				.findViewById(R.id.record_essential_info_easy_sp_house_noture);
		CommonUtil.setSpinner(this, house_notures, house_noture_radio);
		house_noture_radio
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						if (isSpinner) {
							save_house_noture = (String) house_noture_radio
									.getItemAtPosition(position);
							if ("请选择".equals(save_house_noture)) {
								house_noture_radio
										.setBackgroundResource(R.drawable.comm_spinner_selecter);
							} else {
								house_noture_radio
										.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
							}
							house_noture_radio.setPadding(15, 0, 0, 0);
							MiniPersonInfo.houseNoture = save_house_noture;
						}
						isSpinner = true;
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						// TODO Auto-generated method stub

					}
				});

		career_info_radio = (RadioGroup) this
				.findViewById(R.id.career_info_radio);
		career_info_radio
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup arg0, int arg1) {
						switch (arg0.getCheckedRadioButtonId()) {
						case R.id.career_status_mar_no:
							MiniPersonInfo.careerInfo = "在职";
							break;
						case R.id.career_status_retire_no:
							MiniPersonInfo.careerInfo = "退休";
							break;
						case R.id.career_status_dis_no:
							MiniPersonInfo.careerInfo = "失业";
							break;
						}

					}
				});
		next_button = (Button) findViewById(R.id.next_button);
		next_button.setOnClickListener(new ButtonOnClickListener());

		String confirm_info = getIntent().getStringExtra("confirm_info");
		if ("confirmActivity".equals(confirm_info)
				|| "loanConfirmActivity".equals(confirm_info)) {
			next_button.setText("确定");
		}
		if ("".equals(MiniPersonInfo.maritalInfo)
				|| null == MiniPersonInfo.maritalInfo) {
			if (Contents.custInfoResponseResult != null
					&& Contents.custInfoResponseResult.getCode() == 0) {
				CustInfoResponse response = Contents.custInfoResponseResult
						.getResult();
				if (response.getIndivMarital().equals("10")) {
					MiniPersonInfo.maritalInfo = "未婚";
				} else if (response.getIndivMarital().equals("20")) {
					MiniPersonInfo.maritalInfo = "已婚";
				} else if (response.getIndivMarital().equals("90")) {
					MiniPersonInfo.maritalInfo = "其他";
				}
				MiniPersonInfo.house_hold = response.getIndivDepNo();
				if (response.getIndivDegree().equals("08")) {
					MiniPersonInfo.standDegree = stand_degrees[1];
				} else if (response.getIndivDegree().equals("09")) {
					MiniPersonInfo.standDegree = stand_degrees[2];
				} else if (response.getIndivDegree().equals("10")) {
					MiniPersonInfo.standDegree = stand_degrees[3];
				} else if (response.getIndivDegree().equals("20")) {
					// MiniPersonInfo.houseNoture = stand_degrees[4];
					MiniPersonInfo.standDegree = stand_degrees[4];
				} else if (response.getIndivDegree().equals("30")) {
					MiniPersonInfo.standDegree = stand_degrees[5];
				} else if (response.getIndivDegree().equals("40")) {
					MiniPersonInfo.standDegree = stand_degrees[6];
				}

				MiniPersonInfo.province = response.getIndivLiveAddr();
				MiniPersonInfo.areaCard = response.getIndivRelZone();
				MiniPersonInfo.phoneNo = response.getIndivRelPhone();
				MiniPersonInfo.mobile = response.getIndivRelMobile();
				if (response.getIndivLive().equals("1")) {
					MiniPersonInfo.houseNoture = (String) house_noture_radio
							.getItemAtPosition(1);
				} else if (response.getIndivLive().equals("2")) {
					MiniPersonInfo.houseNoture = (String) house_noture_radio
							.getItemAtPosition(2);
				} else if (response.getIndivLive().equals("3")) {
					MiniPersonInfo.houseNoture = (String) house_noture_radio
							.getItemAtPosition(3);
				} else if (response.getIndivLive().equals("4")) {
					MiniPersonInfo.houseNoture = (String) house_noture_radio
							.getItemAtPosition(4);
				} else if (response.getIndivLive().equals("5")) {
					MiniPersonInfo.houseNoture = (String) house_noture_radio
							.getItemAtPosition(5);
				} else if (response.getIndivLive().equals("7")) {
					MiniPersonInfo.houseNoture = (String) house_noture_radio
							.getItemAtPosition(6);
				}
				// 在职状况
				// MiniPersonInfo.careerInfo
			}
		}

	}

	// 按钮事件
	private class ButtonOnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// 下一步
			if (v == next_button) {
				if (Contents.VERIFT) {

				  RegexResult temp = null;
					// 婚姻状况
					if (marital_info_radio.getCheckedRadioButtonId() == -1) {
						result = new RegexResult(false, "请选择婚姻状况!");
						showDialog(INFO_ERROR);
						return;
					}
					// 供养人数
					String house_hold = house_hold_text_value.getText()
							.toString().trim();
					temp = RegexCust.required("供养人数", house_hold);
					if (temp.match) {
						temp = RegexCust.numberNatural("供养人数", house_hold);
						if (temp.match == false) {
							result = temp;
							showDialog(INFO_ERROR);
							house_hold_text_value.requestFocus();
							return;
						}
					} else {
						result = temp;
						showDialog(INFO_ERROR);
						house_hold_text_value.requestFocus();
						return;
					}
					// 文化程度
					if (save_stand_degree == null
							|| "请选择".equals(save_stand_degree)) {
						result = new RegexResult(false, "请选择文化程度!");
						showDialog(INFO_ERROR);
						return;
					}
					// 现居住地址
					String province = province_value.getText().toString()
							.trim();
					temp = RegexCust.required("现居住地址", province);
					if (temp.match) {
						boolean flag = RegexCust.lengthMax(province, 200);
						if (flag == false) {
							result = new RegexResult(false, "现居住地址字符长度过长!");
							showDialog(INFO_ERROR);
							province_value.requestFocus();
							return;
						}
					} else {
						result = temp;
						showDialog(INFO_ERROR);
						province_value.requestFocus();
						return;
					}
					// 户籍地址
					// String permanentAddress =
					// permanent_address_province_value
					// .getText().toString().trim();
					// temp = RegexCust.required("户籍地址", permanentAddress);
					// if (temp.match) {
					// boolean flag = RegexCust.maxlength(province, 200);
					// if (flag == false) {
					// result = new Result(false, "户籍地址地址字符长度过长!");
					// showDialog(INFO_ERROR);
					// permanent_address_province_value.requestFocus();
					// return;
					// }
					// } else {
					// result = temp;
					// showDialog(INFO_ERROR);
					// permanent_address_province_value.requestFocus();
					// return;
					// }
					// 住宅电话区号
					String areaCard = area_card_value.getText().toString()
							.trim();
					if (areaCard != null && !areaCard.equals("")) {
						if (!RegexCust.numberNatural(areaCard)) {
							result = new RegexResult(false, "住宅电话区号格式不对!");
							showDialog(INFO_ERROR);
							area_card_value.requestFocus();
							return;
						} else {
							if (areaCard.length() > 4) {
								result = new RegexResult(false, "住宅电话区号长度不应大于4!");
								showDialog(INFO_ERROR);
								area_card_value.requestFocus();
								return;
							}
						}
					}
					// 住宅电话电话号码
					String phoneNo = phone_no_value.getText().toString().trim();
					temp = RegexCust.required("住宅电话号码", phoneNo);
					if (temp.match) {
						// temp = RegexCust.familyPhone("住宅电话号码", phoneNo);
						if (familyPhone(phoneNo) == false) {
							result = new RegexResult(false, "住宅电话号码格式不对!");
							showDialog(INFO_ERROR);
							phone_no_value.requestFocus();
							return;
						}
					}
					// else {
					// result = temp;
					// showDialog(INFO_ERROR);
					// phone_no_value.requestFocus();
					// return;
					// }
					// 手机号码
					String mobile = mobile_no_value.getText().toString().trim();
					temp = RegexCust.required("手机号码", mobile);
					if (temp.match) {
						if (RegexCust.phoneMob(mobile) == false) {
							result = new RegexResult(false, "手机号码格式不对!");
							showDialog(INFO_ERROR);
							mobile_no_value.requestFocus();
							return;
						}
					} else {
						result = temp;
						showDialog(INFO_ERROR);
						mobile_no_value.requestFocus();
						return;
					}
					// 居住状态
					if (save_house_noture == null
							|| "请选择".equals(save_house_noture)) {
						result = new RegexResult(false, "请选择居住状态!");
						showDialog(INFO_ERROR);
						return;
					}

					if (marital_info_radio.getCheckedRadioButtonId() != -1) {
						RadioButton radioButton = (RadioButton) findViewById(marital_info_radio
								.getCheckedRadioButtonId());
						MiniPersonInfo.maritalInfo = radioButton.getText()
								.toString();
					}
					MiniPersonInfo.house_hold = house_hold;
					if (save_stand_degree != null
							&& !"请选择".equals(save_stand_degree)) {
						MiniPersonInfo.standDegree = save_stand_degree
								.toString().trim();
					}
					MiniPersonInfo.province = province;
					MiniPersonInfo.permanentAddress = MiniPersonInfo.birthPlace;
					MiniPersonInfo.areaCard = areaCard;
					MiniPersonInfo.phoneNo = phoneNo;
					MiniPersonInfo.mobile = mobile;
					if (save_house_noture != null
							&& !"请选择".equals(save_house_noture)) {
						MiniPersonInfo.houseNoture = save_house_noture
								.toString().trim();
					}

					// 在职状况
					if (career_info_radio.getCheckedRadioButtonId() == -1) {
						result = new RegexResult(false, "请选择在职状况!");
						showDialog(INFO_ERROR);
						return;
					} else {
						RadioButton radioButton = (RadioButton) findViewById(career_info_radio
								.getCheckedRadioButtonId());
						MiniPersonInfo.careerInfo = (radioButton.getText()
								.toString());
					}
				}

				if (getIntent().getStringExtra("confirm_info") != null) {
					destroyActivity();
				} else {
					Intent intent = getIntent();
					intent.setClass(MiniRecordEssentialInfoEasyActivity.this,
							MiniWorkInfoActivity1.class);
					startActivity(intent);
				}

			}
			// 同户籍地址
			if (v == permanent_address_img) {
				if (!"".equals(MiniPersonInfo.birthPlace)) {
					province_value.setText(MiniPersonInfo.birthPlace);
					MiniPersonInfo.province = MiniPersonInfo.birthPlace;
				}
			}
		}
	}

	@Override
	protected Dialog onCreateDialog(int id, Bundle args) {

		Dialog dialog = null;
		StringBuffer sb = new StringBuffer();

		switch (id) {
		case INFO_ERROR:
			sb.append(result.msg);
			dialog = DialogUtil.showDialogOneButton2(
					MiniRecordEssentialInfoEasyActivity.this, sb.toString());
			break;

		default:
			break;
		}

		return super.onCreateDialog(id, args);
	}

	@Override
	protected void onResume() {
		if (!"".equals(MiniPersonInfo.maritalInfo)) {
			for (int i = 0; i < marital_info_radio.getChildCount(); i++) {
				RadioButton radioButton = (RadioButton) marital_info_radio
						.getChildAt(i);
				if (radioButton.getText().equals(MiniPersonInfo.maritalInfo)) {
					radioButton.setChecked(true);
				}
			}
		}
		house_hold_text_value.setText(MiniPersonInfo.house_hold);
		if (!"".equals(MiniPersonInfo.standDegree)) {
			for (int i = 0; i < stand_degree_radio.getCount(); i++) {
				String item = stand_degrees[i];
				if (item.equals(MiniPersonInfo.standDegree)) {
					stand_degree_radio.setSelection(i);
					save_stand_degree = MiniPersonInfo.standDegree;
					if (!"请选择".equals(save_stand_degree)) {
						stand_degree_radio
								.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
						stand_degree_radio.setPadding(15, 0, 0, 0);
					}
				}
			}
		}
		province_value.setText(MiniPersonInfo.province);
		// permanent_address_province_value.setText(LoanPayInfo.baseInfo
		// .getPermanentAddress());
		area_card_value.setText(MiniPersonInfo.areaCard);
		phone_no_value.setText(MiniPersonInfo.phoneNo);
		mobile_no_value.setText(MiniPersonInfo.mobile);
		if (!"".equals(MiniPersonInfo.houseNoture)) {
			for (int i = 0; i < house_noture_radio.getCount(); i++) {
				String item = house_notures[i];
				if (item.equals(MiniPersonInfo.houseNoture)) {
					house_noture_radio.setSelection(i);
					if (!"请选择".equals(save_house_noture)) {
						house_noture_radio
								.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
					}
				}
			}
		}
		// 在职状况
		for (int i = 0; i < career_info_radio.getChildCount(); i++) {
			RadioButton radioButton = (RadioButton) career_info_radio
					.getChildAt(i);
			if (radioButton.getText().equals(MiniPersonInfo.careerInfo)) {
				radioButton.setChecked(true);
			}
		}

		super.onResume();
	}

	private String familyPhone = "[1-9](\\d{6,7})";

	public boolean familyPhone(String value) {
		return value.matches(familyPhone);
	}
}