package com.yucheng.byxf.mini.ui;

import com.yucheng.apfx.utl.RegexResult;
import com.yucheng.byxf.mini.domain.MiniPersonInfo;
import com.yucheng.byxf.mini.message.CustInfoResponse;
import com.yucheng.byxf.mini.utils.CommonUtil;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.RegexCust;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class MiniWorkInfoActivity2 extends BaseActivity implements
		OnClickListener {

	private Button next_button;
	/* 直系亲属 */
	private Spinner contact_relation_group1;
	private String[] contact_relations1 = { "请选择", "配偶", "父母", "子女", "兄弟姐妹" };
	/* 非直系亲属 */
	private Spinner contact_relation_group2;
	private String[] contact_relations2 = { "请选择", "同事", "同学", "朋友", "其它" };
	private EditText edit_contact_name_value;
	private EditText edit_phone_no_value1;
	private EditText edit_phone_no_value2;
	private EditText edit_mobile_no_value1;
	private EditText edit_contact_name_value2;
	private EditText edit_phone_no_value3;
	private EditText edit_phone_no_value4;
	private EditText edit_mobile_no_value2;
	private EditText edit_contact_name_value3;
	private EditText edit_phone_no_value5;
	private EditText edit_phone_no_value6;
	private EditText edit_mobile_no_value3;
	private EditText edit_contact_name_value4;
	private EditText edit_phone_no_value7;
	private EditText edit_phone_no_value8;
	private EditText edit_mobile_no_value4;
	private ImageView iv_menu_common;
	private Button addperson_Button;
	private RelativeLayout contact_layout2;
	private RelativeLayout contact_layout3;
	private Spinner contact_relation_group3;
	private Spinner contact_relation_group4;
	// 基本信息有误
	private final int INFO_ERROR = 0;
	// 验证结果
	private RegexResult result;

	private int flag = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mini_work_info2);
		initView();
	}

	private void initView() {
		contact_layout2 = (RelativeLayout) findViewById(R.id.mobile_no3_ll);
		contact_layout3 = (RelativeLayout) findViewById(R.id.mobile_no4_ll);
		addperson_Button = (Button) findViewById(R.id.addperson_Button);

		addperson_Button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (flag == 0) {
					contact_layout2.setVisibility(View.VISIBLE);
					flag++;
				} else if (flag == 1) {
					flag++;
					contact_layout3.setVisibility(View.VISIBLE);
					addperson_Button.setVisibility(View.GONE);
				}
			}
		});

		next_button = (Button) findViewById(R.id.next_button);
		next_button.setOnClickListener(this);
		iv_menu_common = (ImageView) findViewById(R.id.iv_menu_common);
		iv_menu_common.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		// 直系亲属姓名
		edit_contact_name_value = (EditText) findViewById(R.id.edit_contact_name_value);
		edit_contact_name_value.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String qinshu_name = edit_contact_name_value.getText()
						.toString().trim();
				MiniPersonInfo.qinshu_name = qinshu_name;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		// 直系亲属电话区号
		edit_phone_no_value1 = (EditText) findViewById(R.id.edit_phone_no_value1);
		edit_phone_no_value1.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String qinshu_phone_quhao = edit_phone_no_value1.getText()
						.toString().trim();
				MiniPersonInfo.qinshu_phone_quhao = qinshu_phone_quhao;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		// 直系亲属电话
		edit_phone_no_value2 = (EditText) findViewById(R.id.edit_phone_no_value2);
		edit_phone_no_value2.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String qinshu_phone_dianhua = edit_phone_no_value2.getText()
						.toString().trim();
				MiniPersonInfo.qinshu_phone_dianhua = qinshu_phone_dianhua;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		// 直系亲属手机号
		edit_mobile_no_value1 = (EditText) findViewById(R.id.edit_mobile_no_value1);
		edit_mobile_no_value1.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String qinshu_mobilephone = edit_mobile_no_value1.getText()
						.toString().trim();
				MiniPersonInfo.qinshu_mobilephone = qinshu_mobilephone;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		// 非直系亲属姓名
		edit_contact_name_value2 = (EditText) findViewById(R.id.edit_contact_name_value2);
		edit_contact_name_value2.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String contact_name = edit_contact_name_value2.getText()
						.toString().trim();
				MiniPersonInfo.contact_name = contact_name;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		// 非直系亲属电话区号
		edit_phone_no_value3 = (EditText) findViewById(R.id.edit_phone_no_value3);
		edit_phone_no_value3.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String contact_phone_quhao = edit_phone_no_value3.getText()
						.toString().trim();
				MiniPersonInfo.contact_phone_quhao = contact_phone_quhao;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		// 非直系亲属电话
		edit_phone_no_value4 = (EditText) findViewById(R.id.edit_phone_no_value4);
		edit_phone_no_value4.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String contact_phone_dianhua = edit_phone_no_value4.getText()
						.toString().trim();
				MiniPersonInfo.contact_phone_dianhua = contact_phone_dianhua;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		// 非直系亲属手机号
		edit_mobile_no_value2 = (EditText) findViewById(R.id.edit_mobile_no_value2);
		edit_mobile_no_value2.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String contact_mobilephone = edit_mobile_no_value2.getText()
						.toString().trim();
				MiniPersonInfo.contact_mobilephone = contact_mobilephone;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		contact_relation_group1 = (Spinner) findViewById(R.id.contact_relation_group1);
		CommonUtil
				.setSpinner(this, contact_relations1, contact_relation_group1);
		contact_relation_group1
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						String select_contact = (String) contact_relation_group1
								.getItemAtPosition(position);
						if ("请选择".equals(select_contact)) {
							contact_relation_group1
									.setBackgroundResource(R.drawable.comm_spinner_selecter);
						} else {
							System.out.println("select_contact:"
									+ select_contact);
							contact_relation_group1
									.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
							MiniPersonInfo.qinshu_relationship = select_contact;
							MiniPersonInfo.qinshu_relationshipID = position;
						}
						contact_relation_group1.setPadding(15, 0, 0, 0);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});

		contact_relation_group2 = (Spinner) findViewById(R.id.contact_relation_group2);
		CommonUtil
				.setSpinner(this, contact_relations2, contact_relation_group2);
		contact_relation_group2
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						String select_contact1 = (String) contact_relation_group2
								.getItemAtPosition(position);
						if ("请选择".equals(select_contact1)) {
							contact_relation_group2
									.setBackgroundResource(R.drawable.comm_spinner_selecter);
						} else {
							contact_relation_group2
									.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
							MiniPersonInfo.contact_relationship = select_contact1;
							MiniPersonInfo.contact_relationshipID = position;
						}
						contact_relation_group2.setPadding(15, 0, 0, 0);

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});

		contact_relation_group3 = (Spinner) findViewById(R.id.contact_relation_group3);
		CommonUtil
				.setSpinner(this, contact_relations2, contact_relation_group3);
		contact_relation_group3
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						String select_contact1 = (String) contact_relation_group3
								.getItemAtPosition(position);
						if ("请选择".equals(select_contact1)) {
							contact_relation_group3
									.setBackgroundResource(R.drawable.comm_spinner_selecter);
						} else {
							contact_relation_group3
									.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
							MiniPersonInfo.contact_relationship1 = select_contact1;
							MiniPersonInfo.contact_relationshipID1 = position;
						}
						contact_relation_group3.setPadding(15, 0, 0, 0);

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});

		contact_relation_group4 = (Spinner) findViewById(R.id.contact_relation_group4);
		CommonUtil
				.setSpinner(this, contact_relations2, contact_relation_group4);
		contact_relation_group4
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						String select_contact1 = (String) contact_relation_group4
								.getItemAtPosition(position);
						if ("请选择".equals(select_contact1)) {
							contact_relation_group4
									.setBackgroundResource(R.drawable.comm_spinner_selecter);
						} else {
							contact_relation_group4
									.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
							MiniPersonInfo.contact_relationship2 = select_contact1;
							MiniPersonInfo.contact_relationshipID2 = position;
						}
						contact_relation_group4.setPadding(15, 0, 0, 0);

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});

		// ///////////////////////第二个非直系亲属信息//////////////////////////////////////

		// 非直系亲属姓名
		edit_contact_name_value3 = (EditText) findViewById(R.id.edit_contact_name_value3);
		edit_contact_name_value3.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String contact_name = edit_contact_name_value3.getText()
						.toString().trim();
				MiniPersonInfo.contact_name1 = contact_name;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		// 非直系亲属电话区号
		edit_phone_no_value5 = (EditText) findViewById(R.id.edit_phone_no_value5);
		edit_phone_no_value5.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String contact_phone_quhao = edit_phone_no_value5.getText()
						.toString().trim();
				MiniPersonInfo.contact_phone_quhao1 = contact_phone_quhao;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		// 非直系亲属电话
		edit_phone_no_value6 = (EditText) findViewById(R.id.edit_phone_no_value6);
		edit_phone_no_value6.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String contact_phone_dianhua = edit_phone_no_value6.getText()
						.toString().trim();
				MiniPersonInfo.contact_phone_dianhua1 = contact_phone_dianhua;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		// 非直系亲属手机号
		edit_mobile_no_value3 = (EditText) findViewById(R.id.edit_mobile_no_value3);
		edit_mobile_no_value3.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String contact_mobilephone = edit_mobile_no_value3.getText()
						.toString().trim();
				MiniPersonInfo.contact_mobilephone1 = contact_mobilephone;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});

		// ///////////////////////第三个非直系亲属信息//////////////////////////////////////

		// 非直系亲属姓名
		edit_contact_name_value4 = (EditText) findViewById(R.id.edit_contact_name_value4);
		edit_contact_name_value4.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String contact_name = edit_contact_name_value4.getText()
						.toString().trim();
				MiniPersonInfo.contact_name1 = contact_name;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		// 非直系亲属电话区号
		edit_phone_no_value7 = (EditText) findViewById(R.id.edit_phone_no_value7);
		edit_phone_no_value7.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String contact_phone_quhao = edit_phone_no_value7.getText()
						.toString().trim();
				MiniPersonInfo.contact_phone_quhao2 = contact_phone_quhao;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		// 非直系亲属电话
		edit_phone_no_value8 = (EditText) findViewById(R.id.edit_phone_no_value8);
		edit_phone_no_value8.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String contact_phone_dianhua = edit_phone_no_value8.getText()
						.toString().trim();
				MiniPersonInfo.contact_phone_dianhua2 = contact_phone_dianhua;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		// 非直系亲属手机号
		edit_mobile_no_value4 = (EditText) findViewById(R.id.edit_mobile_no_value4);
		edit_mobile_no_value4.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String contact_mobilephone = edit_mobile_no_value4.getText()
						.toString().trim();
				MiniPersonInfo.contact_mobilephone2 = contact_mobilephone;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		
		if ("".equals(MiniPersonInfo.qinshu_name) ||
				null == MiniPersonInfo.qinshu_name) {
			if (Contents.custInfoResponseResult != null
					&& Contents.custInfoResponseResult.getCode() == 0) {
				CustInfoResponse response = Contents.custInfoResponseResult
						.getResult();
				// 直系亲属联系人
				MiniPersonInfo.qinshu_name = response.getIndivRelName();
				MiniPersonInfo.qinshu_phone_quhao = response.getIndivRelZone();
				MiniPersonInfo.qinshu_phone_dianhua = response.getIndivRelPhone();
				MiniPersonInfo.qinshu_mobilephone = response.getIndivRelMobile();
				if ("01".equals(response.getIndivRelation())) {
					MiniPersonInfo.qinshu_relationshipID = 1;
				}else if ("02".equals(response.getIndivRelation())) {
					MiniPersonInfo.qinshu_relationshipID = 2;
				}else if ("03".equals(response.getIndivRelation())) {
					MiniPersonInfo.qinshu_relationshipID = 3;
				}else if ("09".equals(response.getIndivRelation())) {
					MiniPersonInfo.qinshu_relationshipID = 4;
				}
				// 非直系亲属联系人
				MiniPersonInfo.contact_name = response.getIndivOthName();
				MiniPersonInfo.contact_phone_quhao = response.getIndivOthZone();
				MiniPersonInfo.contact_phone_dianhua = response.getIndivOthPhone();
				MiniPersonInfo.contact_mobilephone = response.getIndivOthMobile();
				if ("06".equals(response.getIndivOthRel())) {
					MiniPersonInfo.contact_relationshipID = 1;
				}else if ("10".equals(response.getIndivOthRel())) {
					MiniPersonInfo.contact_relationshipID = 2;
				}else if ("11".equals(response.getIndivOthRel())) {
					MiniPersonInfo.contact_relationshipID = 3;
				}else if ("08".equals(response.getIndivOthRel())) {
					MiniPersonInfo.contact_relationshipID = 4;
				}
			}
		}
		
	}

	@Override
	protected void onResume() {
//		if (!MiniPersonInfo.qinshu_name.equals("")) {
			edit_contact_name_value.setText(MiniPersonInfo.qinshu_name);
			edit_phone_no_value1.setText(MiniPersonInfo.qinshu_phone_quhao);
			edit_phone_no_value2.setText(MiniPersonInfo.qinshu_phone_dianhua);
			edit_mobile_no_value1.setText(MiniPersonInfo.qinshu_mobilephone);
			contact_relation_group1
					.setSelection(MiniPersonInfo.qinshu_relationshipID);

			edit_contact_name_value2.setText(MiniPersonInfo.contact_name);
			edit_phone_no_value3.setText(MiniPersonInfo.contact_phone_quhao);
			edit_phone_no_value4.setText(MiniPersonInfo.contact_phone_dianhua);
			edit_mobile_no_value2.setText(MiniPersonInfo.contact_mobilephone);
			contact_relation_group2
					.setSelection(MiniPersonInfo.contact_relationshipID);
			
			if (!"".equals(MiniPersonInfo.contact_name1)) {
				contact_layout2.setVisibility(View.VISIBLE);
				edit_contact_name_value3.setText(MiniPersonInfo.contact_name1);
				edit_phone_no_value5.setText(MiniPersonInfo.contact_phone_quhao1);
				edit_phone_no_value6.setText(MiniPersonInfo.contact_phone_dianhua1);
				edit_mobile_no_value3.setText(MiniPersonInfo.contact_mobilephone1);
				contact_relation_group3
						.setSelection(MiniPersonInfo.contact_relationshipID1);
			}
			
			if (!"".equals(MiniPersonInfo.contact_name2)) {
				contact_layout3.setVisibility(View.VISIBLE);
				edit_contact_name_value4.setText(MiniPersonInfo.contact_name2);
				edit_phone_no_value7.setText(MiniPersonInfo.contact_phone_quhao2);
				edit_phone_no_value8.setText(MiniPersonInfo.contact_phone_dianhua2);
				edit_mobile_no_value4.setText(MiniPersonInfo.contact_mobilephone2);
				contact_relation_group4
						.setSelection(MiniPersonInfo.contact_relationshipID2);
			}
//		}
		super.onResume();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.next_button:
			if (Contents.VERIFT) {
				String contact_name_value = edit_contact_name_value.getText()
						.toString();
				String phone_no_value1 = edit_phone_no_value1.getText()
						.toString();
				String phone_no_value2 = edit_phone_no_value2.getText()
						.toString();
				String mobile_no_value1 = edit_mobile_no_value1.getText()
						.toString();
				String contact_name_value2 = edit_contact_name_value2.getText()
						.toString();
				String phone_no_value3 = edit_phone_no_value3.getText()
						.toString();
				String phone_no_value4 = edit_phone_no_value4.getText()
						.toString();
				String mobile_no_value2 = edit_mobile_no_value2.getText()
						.toString();

				String contact_name_value3 = edit_contact_name_value3.getText()
						.toString();
				String phone_no_value5 = edit_phone_no_value5.getText()
						.toString();
				String phone_no_value6 = edit_phone_no_value6.getText()
						.toString();
				String mobile_no_value3 = edit_mobile_no_value3.getText()
						.toString();
				
				String contact_name_value4 = edit_contact_name_value4.getText()
						.toString();
				String phone_no_value7 = edit_phone_no_value7.getText()
						.toString();
				String phone_no_value8 = edit_phone_no_value8.getText()
						.toString();
				String mobile_no_value4 = edit_mobile_no_value4.getText()
						.toString();
				
				RegexResult temp = null;
				// 直系亲属联系人姓名
				temp = RegexCust.length("姓名", contact_name_value, 2, 30);
				if (temp.match) {
					temp = RegexCust.chinese("姓名", contact_name_value);
					if (!temp.match) {
						result = new RegexResult(false, "直系亲属姓名应为汉字!");
						showDialog(INFO_ERROR);
						return;
					}
				} else {
					if (contact_name_value.length() == 0) {
						result = new RegexResult(false, "直系亲属联系人姓名不能为空!");
					} else {
						result = new RegexResult(false, "直系亲属姓名字符长度不对!");
					}
					showDialog(INFO_ERROR);
					return;
				}

				// 直系亲属联系人与申请人关系
				if (contact_relation_group1 == null
						|| "请选择".equals(MiniPersonInfo.qinshu_relationship)) {
					result = new RegexResult(false, "请选择直系亲属联系人与申请人关系!");
					showDialog(INFO_ERROR);
					return;
				}

				// 直系亲属住宅电话区号
				if (phone_no_value1 != null && !phone_no_value1.equals("")) {
					if (!RegexCust.numberNatural(phone_no_value1)) {
						result = new RegexResult(false, "直系亲属住宅电话区号格式不对!");
						showDialog(INFO_ERROR);
						edit_phone_no_value1.requestFocus();
						return;
					} else {
						if (phone_no_value1.length() > 4) {
							result = new RegexResult(false, "直系亲属住宅电话区号长度不应大于4!");
							showDialog(INFO_ERROR);
							edit_phone_no_value1.requestFocus();
							return;
						}
					}
				}

				// 直系亲属住宅电话号码
				if (phone_no_value2 != null && !phone_no_value2.equals("")) {
					temp = RegexCust.required("住宅电话号码", phone_no_value2);
					if (temp.match) {
						if (RegexCust.phoneFix(phone_no_value2) == false) {
							result = new RegexResult(false, "直系亲属住宅电话号码格式不对!");
							showDialog(INFO_ERROR);
							edit_phone_no_value2.requestFocus();
							return;
						}
					} else {
						result = temp;
						showDialog(INFO_ERROR);
						edit_phone_no_value2.requestFocus();
						return;
					}
				}

				// 直系亲属手机号码
				temp = RegexCust.required("手机号码", mobile_no_value1);
				if (temp.match) {
					if (RegexCust.phoneMob(mobile_no_value1) == false) {
						result = new RegexResult(false, "直系亲属手机号码格式不对!");
						showDialog(INFO_ERROR);
						edit_mobile_no_value1.requestFocus();
						return;
					}
				} else {
					result = temp;
					showDialog(INFO_ERROR);
					edit_mobile_no_value1.requestFocus();
					return;
				}

				// 非直系亲属联系人姓名
				temp = RegexCust.length("姓名", contact_name_value2, 2, 30);
				if (temp.match) {
					temp = RegexCust.chinese("姓名", contact_name_value2);
					if (!temp.match) {
						result = new RegexResult(false, "非直系亲属姓名应为汉字!");
						showDialog(INFO_ERROR);
						return;
					}
				} else {
					if (contact_name_value2.length() == 0) {
						result = new RegexResult(false, "非亲属联系人姓名不能为空!");
					} else {
						result = new RegexResult(false, "非直系亲属姓名字符长度不对!");
					}
					showDialog(INFO_ERROR);
					return;
				}

				// 非直系亲属联系人与申请人关系
				if (contact_relation_group2 == null
						|| "请选择".equals(MiniPersonInfo.contact_relationship)) {
					result = new RegexResult(false, "非直系亲属联系人与申请人关系!");
					showDialog(INFO_ERROR);
					return;
				}

				// 非直系亲属住宅电话区号
				if (phone_no_value3 != null && !phone_no_value3.equals("")) {
					if (!RegexCust.numberNatural(phone_no_value3)) {
						result = new RegexResult(false, "非直系亲属住宅电话区号格式不对!");
						showDialog(INFO_ERROR);
						edit_phone_no_value3.requestFocus();
						return;
					} else {
						if (phone_no_value3.length() > 4) {
							result = new RegexResult(false, "住宅电话区号长度不应大于4!");
							showDialog(INFO_ERROR);
							edit_phone_no_value3.requestFocus();
							return;
						}
					}
				}
				// 非直系亲属住宅电话号码
				if (phone_no_value4 != null && !phone_no_value4.equals("")) {
					temp = RegexCust.required("住宅电话号码", phone_no_value4);
					if (temp.match) {
						if (RegexCust.phoneFix(phone_no_value4) == false) {
							result = new RegexResult(false, "非直系亲属住宅电话号码格式不对!");
							showDialog(INFO_ERROR);
							edit_phone_no_value4.requestFocus();
							return;
						}
					} else {
						result = temp;
						showDialog(INFO_ERROR);
						edit_phone_no_value4.requestFocus();
						return;
					}
				}

				// 非直系亲属手机号码
				temp = RegexCust.required("手机号码", mobile_no_value2);
				if (temp.match) {
					if (RegexCust.phoneMob(mobile_no_value2) == false) {
						result = new RegexResult(false, "非直系亲属手机号码格式不对!");
						showDialog(INFO_ERROR);
						edit_mobile_no_value2.requestFocus();
						return;
					}
				} else {
					result = temp;
					showDialog(INFO_ERROR);
					edit_mobile_no_value2.requestFocus();
					return;
				}
				
				if (!"".equals(contact_name_value3) || !"".equals(phone_no_value5) || !"".equals(phone_no_value6) || !"".equals(mobile_no_value3)) {
					// 非直系亲属联系人姓名
					temp = RegexCust.length("姓名", contact_name_value3, 2, 30);
					if (temp.match) {
						temp = RegexCust.chinese("姓名", contact_name_value3);
						if (!temp.match) {
							result = new RegexResult(false, "非直系亲属姓名应为汉字!");
							showDialog(INFO_ERROR);
							return;
						}
					} else {
						if (contact_name_value3.length() == 0) {
							result = new RegexResult(false, "非亲属联系人姓名不能为空!");
						} else {
							result = new RegexResult(false, "非直系亲属姓名字符长度不对!");
						}
						showDialog(INFO_ERROR);
						return;
					}

					// 非直系亲属联系人与申请人关系
					if (contact_relation_group3 == null
							|| "请选择".equals(MiniPersonInfo.contact_relationship1)) {
						result = new RegexResult(false, "非直系亲属联系人与申请人关系!");
						showDialog(INFO_ERROR);
						return;
					}

					// 非直系亲属住宅电话区号
					if (phone_no_value5 != null && !phone_no_value5.equals("")) {
						if (!RegexCust.numberNatural(phone_no_value5)) {
							result = new RegexResult(false, "非直系亲属住宅电话区号格式不对!");
							showDialog(INFO_ERROR);
							edit_phone_no_value5.requestFocus();
							return;
						} else {
							if (phone_no_value5.length() > 4) {
								result = new RegexResult(false, "住宅电话区号长度不应大于4!");
								showDialog(INFO_ERROR);
								edit_phone_no_value5.requestFocus();
								return;
							}
						}
					}
					// 非直系亲属住宅电话号码
					if (phone_no_value6 != null && !phone_no_value6.equals("")) {
						temp = RegexCust.required("住宅电话号码", phone_no_value6);
						if (temp.match) {
							if (RegexCust.phoneFix(phone_no_value6) == false) {
								result = new RegexResult(false, "非直系亲属住宅电话号码格式不对!");
								showDialog(INFO_ERROR);
								edit_phone_no_value6.requestFocus();
								return;
							}
						} else {
							result = temp;
							showDialog(INFO_ERROR);
							edit_phone_no_value6.requestFocus();
							return;
						}
					}

					// 非直系亲属手机号码
					temp = RegexCust.required("手机号码", mobile_no_value3);
					if (temp.match) {
						if (RegexCust.phoneMob(mobile_no_value3) == false) {
							result = new RegexResult(false, "非直系亲属手机号码格式不对!");
							showDialog(INFO_ERROR);
							edit_mobile_no_value3.requestFocus();
							return;
						}
					} else {
						result = temp;
						showDialog(INFO_ERROR);
						edit_mobile_no_value3.requestFocus();
						return;
					}
				}
				
				if (!"".equals(contact_name_value4) || !"".equals(phone_no_value7) || !"".equals(phone_no_value8) || !"".equals(mobile_no_value4)) {
					// 非直系亲属联系人姓名
					temp = RegexCust.length("姓名", contact_name_value4, 2, 30);
					if (temp.match) {
						temp = RegexCust.chinese("姓名", contact_name_value4);
						if (!temp.match) {
							result = new RegexResult(false, "非直系亲属姓名应为汉字!");
							showDialog(INFO_ERROR);
							return;
						}
					} else {
						if (contact_name_value4.length() == 0) {
							result = new RegexResult(false, "非亲属联系人姓名不能为空!");
						} else {
							result = new RegexResult(false, "非直系亲属姓名字符长度不对!");
						}
						showDialog(INFO_ERROR);
						return;
					}

					// 非直系亲属联系人与申请人关系
					if (contact_relation_group4 == null
							|| "请选择".equals(MiniPersonInfo.contact_relationship2)) {
						result = new RegexResult(false, "非直系亲属联系人与申请人关系!");
						showDialog(INFO_ERROR);
						return;
					}

					// 非直系亲属住宅电话区号
					if (phone_no_value7 != null && !phone_no_value7.equals("")) {
						if (!RegexCust.numberNatural(phone_no_value7)) {
							result = new RegexResult(false, "非直系亲属住宅电话区号格式不对!");
							showDialog(INFO_ERROR);
							edit_phone_no_value7.requestFocus();
							return;
						} else {
							if (phone_no_value7.length() > 4) {
								result = new RegexResult(false, "住宅电话区号长度不应大于4!");
								showDialog(INFO_ERROR);
								edit_phone_no_value7.requestFocus();
								return;
							}
						}
					}
					// 非直系亲属住宅电话号码
					if (phone_no_value8 != null && !phone_no_value8.equals("")) {
						temp = RegexCust.required("住宅电话号码", phone_no_value8);
						if (temp.match) {
							if (RegexCust.phoneFix(phone_no_value8) == false) {
								result = new RegexResult(false, "非直系亲属住宅电话号码格式不对!");
								showDialog(INFO_ERROR);
								edit_phone_no_value8.requestFocus();
								return;
							}
						} else {
							result = temp;
							showDialog(INFO_ERROR);
							edit_phone_no_value8.requestFocus();
							return;
						}
					}

					// 非直系亲属手机号码
					temp = RegexCust.required("手机号码", mobile_no_value4);
					if (temp.match) {
						if (RegexCust.phoneMob(mobile_no_value4) == false) {
							result = new RegexResult(false, "非直系亲属手机号码格式不对!");
							showDialog(INFO_ERROR);
							edit_mobile_no_value4.requestFocus();
							return;
						}
					} else {
						result = temp;
						showDialog(INFO_ERROR);
						edit_mobile_no_value4.requestFocus();
						return;
					}
				}

				MiniPersonInfo.qinshu_name = contact_name_value;
				MiniPersonInfo.qinshu_phone_quhao = phone_no_value1;
				MiniPersonInfo.qinshu_phone_dianhua = phone_no_value2;
				MiniPersonInfo.qinshu_mobilephone = mobile_no_value1;
				MiniPersonInfo.contact_name = contact_name_value2;
				MiniPersonInfo.contact_phone_quhao = phone_no_value3;
				MiniPersonInfo.contact_phone_dianhua = phone_no_value4;
				MiniPersonInfo.contact_mobilephone = mobile_no_value2;
				MiniPersonInfo.contact_name1 = contact_name_value3;
				MiniPersonInfo.contact_phone_quhao1 = phone_no_value5;
				MiniPersonInfo.contact_phone_dianhua1 = phone_no_value6;
				MiniPersonInfo.contact_mobilephone1 = mobile_no_value3;
				MiniPersonInfo.contact_name2 = contact_name_value4;
				MiniPersonInfo.contact_phone_quhao2 = phone_no_value7;
				MiniPersonInfo.contact_phone_dianhua2 = phone_no_value8;
				MiniPersonInfo.contact_mobilephone2 = mobile_no_value4;
				Intent intent = new Intent(MiniWorkInfoActivity2.this,
						MiniAutoPayActivity.class);
				startActivity(intent);

				/*
				 * if(contact_name_value.equals("") ||phone_no_value1.equals("")
				 * ||phone_no_value2.equals("") ||mobile_no_value1.equals("")
				 * ||contact_name_value2.equals("") ||phone_no_value3.equals("")
				 * ||phone_no_value4.equals("") ||mobile_no_value2.equals("")){
				 * Toast.makeText(getApplicationContext(), "请将信息填写完整！",
				 * Toast.LENGTH_LONG).show(); }else{ ======= }
				 * 
				 * if (contact_name_value.equals("") ||
				 * phone_no_value1.equals("") || phone_no_value2.equals("") ||
				 * mobile_no_value1.equals("") || contact_name_value2.equals("")
				 * || phone_no_value3.equals("") || phone_no_value4.equals("")
				 * || mobile_no_value2.equals("")) {
				 * Toast.makeText(getApplicationContext(), "请将信息填写完整！", 0)
				 * .show(); } else { MiniPersonInfo.qinshu_name =
				 * contact_name_value; MiniPersonInfo.qinshu_phone_quhao =
				 * phone_no_value1; MiniPersonInfo.qinshu_phone_dianhua =
				 * phone_no_value2; MiniPersonInfo.qinshu_mobilephone =
				 * mobile_no_value1; MiniPersonInfo.contact_name =
				 * contact_name_value2; MiniPersonInfo.contact_phone_quhao =
				 * phone_no_value3; MiniPersonInfo.contact_phone_dianhua =
				 * phone_no_value4; MiniPersonInfo.contact_mobilephone =
				 * mobile_no_value2; Intent intent = new
				 * Intent(MiniWorkInfoActivity2.this,
				 * MiniAutoPayActivity.class); startActivity(intent); }
				 */

			}
			break;
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
					MiniWorkInfoActivity2.this, sb.toString());
			break;

		default:
			break;
		}

		return super.onCreateDialog(id, args);
	}
}
