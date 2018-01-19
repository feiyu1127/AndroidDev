package com.yucheng.byxf.mini.easyloan.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Toast;

import com.yucheng.apfx.utl.RegexResult;
import com.yucheng.byxf.mini.message.LoginMessage;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.CommonUtil;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.RegexCust;

public class RelaxedLoanSixApplyLoanInfo extends BaseRelaxedLoanActivity {
	private Button apply_info_next_button;

	private EditText et_apply_amount;

	private Spinner sp_tick_loan_pursose;
	private Spinner sp_tick_loan_method;
	private Spinner sp_applyLoanTime;
	private Spinner sp_send_method;

	private RadioGroup rg_repay_acount_type;

	private EditText et_username_value;
	private EditText et_account_value;
	private EditText et_adentify_num;

	private EditText et_employee_id;
	private EditText et_employee_name;

	private String str_tick_loan_pursose;
	private String str_tick_loan_method;
	private String str_applyLoanTime;
	private String str_send_method;
	private String str_adentify_type;

	private String[] tick_loans = { "请选择", "装修", "教育", "旅游", "婚庆", "其它" };
	private String[] tick_loan_codes = { "", "DEC", "EDU", "TRA", "MAR", "OTH" };

	private String[] repay_method = { "等额本息" };
	private String[] repay_method_code = { "M001" };

	private String[] apply_loanTime = { "请选择", "12", "24", "36" };
	private String[] apply_loanTime_code = { "", "3", "5", "6" };

	private String[] send_address = { "请选择", "居住地址", "单位地址" };
	private String[] send_address_code = { "", "00", "01" };

	// 信息有误
	private final int INFO_ERROR = 0;
	// 验证结果
	private RegexResult result;

	private LinearLayout otherpurpose_layout;
	private EditText et_otherpurpose;

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		setContentView(R.layout.easy_loan_six_apply_info);
		super.init();

		// if (application.getApplyPersonalInfo() == null) {
		// SharedPreferencesUtils preferencesUtils =new
		// SharedPreferencesUtils(this);
		// if(preferencesUtils.hasData("Apply_amount_value")){
		// editinit();
		// }
		// }
		editinit();

		easy_loan_head.setText("申请人贷款申请信息");

		otherpurpose_layout = (LinearLayout) findViewById(R.id.otherpurpose_layout);
		et_otherpurpose = (EditText) findViewById(R.id.et_otherpurpose);

		apply_info_next_button = (Button) findViewById(R.id.apply_info_next_button);
		apply_info_next_button.setOnClickListener(this);

		et_apply_amount = (EditText) findViewById(R.id.et_apply_amount);

		sp_tick_loan_pursose = (Spinner) findViewById(R.id.sp_tick_loan_pursose);
		CommonUtil.setSpinner(this, tick_loans, sp_tick_loan_pursose);
		sp_tick_loan_pursose.setOnItemSelectedListener(this);

		sp_tick_loan_method = (Spinner) findViewById(R.id.sp_tick_loan_method);
		CommonUtil.setSpinner(this, repay_method, sp_tick_loan_method);
		sp_tick_loan_method.setOnItemSelectedListener(this);

		sp_applyLoanTime = (Spinner) findViewById(R.id.sp_applyLoanTime);
		CommonUtil.setSpinner(this, apply_loanTime, sp_applyLoanTime);
		sp_applyLoanTime.setOnItemSelectedListener(this);

		sp_send_method = (Spinner) findViewById(R.id.sp_send_method);
		CommonUtil.setSpinner(this, send_address, sp_send_method);
		sp_send_method.setOnItemSelectedListener(this);

		rg_repay_acount_type = (RadioGroup) findViewById(R.id.rg_repay_acount_type);
		if (null == application.getApplyPersonalInfo().getRepay_acount_type()
				|| "".equals(application.getApplyPersonalInfo()
						.getRepay_acount_type())) {
			application.getApplyPersonalInfo().setRepay_acount_type("北京银行");
		}

		rg_repay_acount_type
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						// TODO Auto-generated method stub
						switch (group.getCheckedRadioButtonId()) {
						case R.id.repay_account_bank_bjyh:
							application.getApplyPersonalInfo()
									.setRepay_acount_type("北京银行");
					//	et_account_value.setText("");
							et_account_value
									.setFilters(new InputFilter[] { new InputFilter.LengthFilter(
											19) });
							break;
						case R.id.repay_account_bank_nyyh:
							application.getApplyPersonalInfo()
									.setRepay_acount_type("农业银行");
						//	et_account_value.setText("");
							et_account_value
									.setFilters(new InputFilter[] { new InputFilter.LengthFilter(
											23) });
							break;
						default:
							break;
						}

					}
				});

		et_username_value = (EditText) findViewById(R.id.et_username_value);
		et_account_value = (EditText) findViewById(R.id.et_account_value);
		et_account_value
				.setFilters(new InputFilter[] { new InputFilter.LengthFilter(23) });
//		et_account_value
//		.setFilters(new InputFilter[] { new InputFilter.LengthFilter(19) });
		et_account_value.addTextChangedListener(new TextWatcher() {
			int beforeTextLength = 0;
			int onTextLength = 0;
			boolean isChanged = false;
			int location = 0;// 记录光标的位置
			private char[] tempChar;
			private StringBuffer buffer = new StringBuffer();
			int konggeNumberB = 0;

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {// 第二步骤(改变中)
				// TODO Auto-generated method stub
				onTextLength = s.length();
				buffer.append(s.toString());
				if (onTextLength == beforeTextLength || onTextLength <= 3
						|| isChanged) {
					isChanged = false;
					return;
				}
				isChanged = true;
			}

			// 先调这个(1);
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				beforeTextLength = s.length();
				if (buffer.length() > 0) {
					buffer.delete(0, buffer.length());
				}
				konggeNumberB = 0;
				for (int i = 0; i < s.length(); i++) {
					if (s.charAt(i) == ' ') {
						konggeNumberB++;
					}
				}
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (isChanged) {
					location = et_account_value.getSelectionEnd();
					int index = 0;
					while (index < buffer.length()) {
						if (buffer.charAt(index) == ' ') {
							buffer.deleteCharAt(index);
						} else {
							index++;
						}
					}
					index = 0;
					int konggeNumberC = 0;
					while (index < buffer.length()) {
						if ((index == 4 || index == 9 || index == 14 || index == 19)) {
							buffer.insert(index, ' ');
							konggeNumberC++;
						}
						index++;
					}

					if (konggeNumberC > konggeNumberB) {
						location += (konggeNumberC - konggeNumberB);
					}

					tempChar = new char[buffer.length()];
					buffer.getChars(0, buffer.length(), tempChar, 0);
					String str = buffer.toString();
					if (location > str.length()) {
						location = str.length();
					} else if (location < 0) {
						location = 0;
					}
					et_account_value.setText(str);
					// System.out.println(str.replace(" ", ""));
					Editable etable = et_account_value.getText();
					Selection.setSelection(etable, location);
					isChanged = false;
				}
			}
		});

		et_adentify_num = (EditText) findViewById(R.id.et_adentify_num);

		et_employee_id = (EditText) findViewById(R.id.et_employee_id);
		et_employee_name = (EditText) findViewById(R.id.et_employee_name);

		setData();
		
		if(application.getApplyPersonalInfo()
				.getRepay_acount_type().equals("北京银行")){
			et_account_value
			.setFilters(new InputFilter[] { new InputFilter.LengthFilter(19) });
		}
	}

	private void shareclear() {
		// SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
		// this);
		if (preferencesUtils.hasData("Apply_amount_value")) {
			preferencesUtils.removeData("Apply_amount_value");
			preferencesUtils.removeData("Tick_loan_pursose");
			preferencesUtils.removeData("Tick_loan_method");
			preferencesUtils.removeData("ApplyLoanTime");
			preferencesUtils.removeData("Send_method");
			preferencesUtils.removeData("Account_value");
			preferencesUtils.removeData("Repay_acount_type");
			preferencesUtils.removeData("Employee_id");
			preferencesUtils.removeData("Employee_name");
		}
	}

	private void editinit() {
		// SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
		// this);
		if (preferencesUtils.hasData("Apply_amount_value")) {
			application.getApplyPersonalInfo().setApply_amount_value(
					preferencesUtils.getData("Apply_amount_value", ""));
			application.getApplyPersonalInfo().setTick_loan_pursose(
					preferencesUtils.getData("Tick_loan_pursose", ""));
			application.getApplyPersonalInfo().setTick_loan_method(
					preferencesUtils.getData("Tick_loan_method", ""));
			application.getApplyPersonalInfo().setApplyLoanTime(
					preferencesUtils.getData("ApplyLoanTime", ""));
			application.getApplyPersonalInfo().setSend_method(
					preferencesUtils.getData("Send_method", ""));
			application.getApplyPersonalInfo().setAccount_value(
					preferencesUtils.getData("Account_value", ""));
			application.getApplyPersonalInfo().setRepay_acount_type(
					preferencesUtils.getData("Repay_acount_type", ""));
			application.getApplyPersonalInfo().setEmployee_id(
					preferencesUtils.getData("Employee_id", ""));
			application.getApplyPersonalInfo().setEmployee_name(
					preferencesUtils.getData("Employee_name", ""));
		}
	}

	private void shareinit() {
		// SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
		// this);
		// 姓名
		preferencesUtils.setData("Apply_amount_value", application
				.getApplyPersonalInfo().getApply_amount_value());
		preferencesUtils.setData("Tick_loan_pursose", application
				.getApplyPersonalInfo().getTick_loan_pursose());
		preferencesUtils.setData("Tick_loan_method", application
				.getApplyPersonalInfo().getTick_loan_method());
		preferencesUtils.setData("ApplyLoanTime", application
				.getApplyPersonalInfo().getApplyLoanTime());
		preferencesUtils.setData("Send_method", application
				.getApplyPersonalInfo().getSend_method());
		preferencesUtils.setData("Account_value", application
				.getApplyPersonalInfo().getAccount_value());
		preferencesUtils.setData("Repay_acount_type", application
				.getApplyPersonalInfo().getRepay_acount_type());
		preferencesUtils.setData("Employee_id", application
				.getApplyPersonalInfo().getEmployee_id());
		preferencesUtils.setData("Employee_name", application
				.getApplyPersonalInfo().getEmployee_name());
	}

	private void setData() {
		if (application != null && application.getApplyPersonalInfo() != null) {
			et_apply_amount.setText(application.getApplyPersonalInfo()
					.getApply_amount_value());

			if (!"".equals(application.applyPersonalInfo
					.getTick_loan_pursoseCode())) {
				for (int i = 0; i < sp_tick_loan_pursose.getCount(); i++) {
					if (tick_loan_codes[i].equals(application.applyPersonalInfo
							.getTick_loan_pursoseCode())) {
						application.applyPersonalInfo
								.setTick_loan_pursose(tick_loans[i]);
					}
				}
			}

			if (!"".equals(application.applyPersonalInfo.getTick_loan_pursose())) {
				for (int i = 0; i < sp_tick_loan_pursose.getCount(); i++) {
					String item = tick_loans[i];
					if (item.equals(application.applyPersonalInfo
							.getTick_loan_pursose())) {
						sp_tick_loan_pursose.setSelection(i);
						if (i == sp_tick_loan_pursose.getCount() - 1) {
							otherpurpose_layout.setVisibility(View.VISIBLE);
							et_otherpurpose
									.setText(application.applyPersonalInfo
											.getTick_loan_other_purpose());
						}
					}
				}
			}

			if (!"".equals(application.applyPersonalInfo
					.getTick_loan_methodCode())) {
				for (int i = 0; i < sp_tick_loan_method.getCount(); i++) {
					if (repay_method_code[i]
							.equals(application.applyPersonalInfo
									.getTick_loan_methodCode())) {
						application.applyPersonalInfo
								.setTick_loan_method(repay_method[i]);
					}
				}
			}

			if (!"".equals(application.applyPersonalInfo.getTick_loan_method())) {
				for (int i = 0; i < sp_tick_loan_method.getCount(); i++) {
					String item = repay_method[i];
					if (item.equals(application.applyPersonalInfo
							.getTick_loan_method())) {
						sp_tick_loan_method.setSelection(i);
					}
				}
			}

			// if
			// (!"".equals(application.applyPersonalInfo.getApplyLoanTimeCode()))
			// {
			// for (int i = 0; i < sp_applyLoanTime.getCount(); i++) {
			// if (apply_loanTime_code[i]
			// .equals(application.applyPersonalInfo
			// .getApplyLoanTimeCode())) {
			// application.applyPersonalInfo
			// .setApplyLoanTime(apply_loanTime[i]);
			// }
			// }
			// }

			if (!"".equals(application.applyPersonalInfo.getApplyLoanTime())) {
				for (int i = 0; i < sp_applyLoanTime.getCount(); i++) {
					String item = apply_loanTime[i];
					if (item.equals(application.applyPersonalInfo
							.getApplyLoanTime())) {
						sp_applyLoanTime.setSelection(i);
					}
				}
			}

			if (!"".equals(application.applyPersonalInfo.getSend_methodCode())) {
				for (int i = 0; i < sp_send_method.getCount(); i++) {
					if (send_address_code[i]
							.equals(application.applyPersonalInfo
									.getSend_methodCode())) {
						application.applyPersonalInfo
								.setSend_method(send_address[i]);
					}
				}
			}

			if (!"".equals(application.applyPersonalInfo.getSend_method())) {
				for (int i = 0; i < sp_send_method.getCount(); i++) {
					String item = send_address[i];
					if (item.equals(application.applyPersonalInfo
							.getSend_method())) {
						sp_send_method.setSelection(i);
					}
				}
			}
	
			if (!"".equals(application.getApplyPersonalInfo()
					.getRepay_acount_type())) {
				if ("BOB".equals(application.getApplyPersonalInfo()
						.getRepay_acount_type())) {
					application.getApplyPersonalInfo().setRepay_acount_type(
							"北京银行");
				} else if ("ABC".equals(application.getApplyPersonalInfo()
						.getRepay_acount_type())) {
					application.getApplyPersonalInfo().setRepay_acount_type(
							"农业银行");
				}
			}
			LoginMessage loginResult = null;
			if(application.getResponse() != null)
				loginResult = application.getResponse().getResult();
			et_username_value.setText(((loginResult == null) ? "" : loginResult
					.getCipNamecn()));
			et_account_value.setText(application.getApplyPersonalInfo()
					.getAccount_value());

			if (!"".equals(application.getApplyPersonalInfo()
					.getRepay_acount_type())) {
				for (int i = 0; i < rg_repay_acount_type.getChildCount(); i++) {
					RadioButton radioButton = (RadioButton) rg_repay_acount_type
							.getChildAt(i);
					if (radioButton.getText().equals(
							application.getApplyPersonalInfo()
									.getRepay_acount_type())) {
						radioButton.setChecked(true);
					}
				}
			}
			et_adentify_num.setText(((loginResult == null)? "" : loginResult
					.getIdNo()));
			et_employee_id.setText(application.getApplyPersonalInfo()
					.getEmployee_id());
			et_employee_name.setText(application.getApplyPersonalInfo()
					.getEmployee_name());
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		rg_repay_acount_type
		.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (group.getCheckedRadioButtonId()) {
				case R.id.repay_account_bank_bjyh:
					application.getApplyPersonalInfo()
							.setRepay_acount_type("北京银行");
				et_account_value.setText("");
					et_account_value
							.setFilters(new InputFilter[] { new InputFilter.LengthFilter(
									19) });
					break;
				case R.id.repay_account_bank_nyyh:
					application.getApplyPersonalInfo()
							.setRepay_acount_type("农业银行");
				et_account_value.setText("");
					et_account_value
							.setFilters(new InputFilter[] { new InputFilter.LengthFilter(
									23) });
					break;
				default:
					break;
				}

			}
		});
		super.onResume();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.apply_info_next_button) {
		  RegexResult temp = null;
			// 贷款申请金额
      String aplAmount = et_apply_amount.getText().toString().trim();
      int apply_amount = 0;
      if (!aplAmount.equals("")) {
        try {
          apply_amount = Integer.parseInt(aplAmount);
        }
        catch (NumberFormatException e) {
          ;/* apply_amount = 0; */
        }
      }
      else {
        result = new RegexResult(false, "贷款金额不能为空");
        showDialog(INFO_ERROR);
        et_apply_amount.requestFocus();
        return;
      }
      if (apply_amount < 600 || 50000 <= apply_amount) {
        result = new RegexResult(false, "申请贷款金额必须在600到5万之间");
        showDialog(INFO_ERROR);
        et_apply_amount.requestFocus();
        return;
      }
			// temp = RegexCust.required("申请贷款金额", apply_amount);
			// if (temp.match) {
			// if (!RegexCust.maxlength(apply_amount, 10)) {
			// result = new Result(false, "申请贷款金额字符长度过长");
			// showDialog(INFO_ERROR);
			// et_apply_amount.requestFocus();
			// return;
			// }
			// } else {
			// result = temp;
			// showDialog(INFO_ERROR);
			// et_apply_amount.requestFocus();
			// return;
			// }
			// 贷款用途
			if (str_tick_loan_pursose == null
					|| "请选择".equals(str_tick_loan_pursose)) {
				result = new RegexResult(false, "请选择贷款用途!");
				showDialog(INFO_ERROR);
				return;
			}
			String st_otherpurpose = et_otherpurpose.getText().toString();
			if ("其它".equals(str_tick_loan_pursose)) {
				if ("".equals(st_otherpurpose)) {
					result = new RegexResult(false, "其它用途不能为空！");
					showDialog(INFO_ERROR);
					return;
				}
			}

			// 还款方式
			if (str_tick_loan_method == null
					|| "请选择".equals(str_tick_loan_method)) {
				result = new RegexResult(false, "请选择还款方式!");
				showDialog(INFO_ERROR);
				return;
			}
			// 申请贷款期限
			if (str_applyLoanTime == null || "请选择".equals(str_applyLoanTime)) {
				result = new RegexResult(false, "请输入申请贷款期限!");
				showDialog(INFO_ERROR);
				return;
			}
			// 信件邮件地址
			if (str_send_method == null || "请选择".equals(str_send_method)) {
				result = new RegexResult(false, "请输入信件邮件地址!");
				showDialog(INFO_ERROR);
				return;
			}
			// 户名
			String username_value = et_username_value.getText().toString()
					.trim();
			temp = RegexCust.required("户名", username_value);
			if (temp.match) {
				if (!RegexCust.lengthMax(username_value, 10)) {
					result = new RegexResult(false, "户名过长");
					showDialog(INFO_ERROR);
					et_username_value.requestFocus();
					return;
				}
			} else {
				result = temp;
				showDialog(INFO_ERROR);
				et_username_value.requestFocus();
				return;
			}
			// 账号
			String account_value = et_account_value.getText().toString().trim();
			temp = RegexCust.required("账号", account_value);
			if (account_value.equals("")) {
				result = new RegexResult(false, "账号不能为空");
				showDialog(INFO_ERROR);
				return;
			}
			if (account_value.length() == 19 || account_value.length() == 23) {
				if(application.getApplyPersonalInfo()
				.getRepay_acount_type().equals("北京银行")){
					if(account_value.length() != 19){
						Toast.makeText(this, "北京银行卡号为16位！", 3000).show();
						return;
					}
				}
				
				if(application.getApplyPersonalInfo()
						.getRepay_acount_type().equals("农业银行")){
							if(account_value.length() != 23){
								Toast.makeText(this, "农业卡号为19位！", 3000).show();
								return;
							}
						}
				
			} else {
				result = new RegexResult(false, "账号必须是16位，或者19位");
				showDialog(INFO_ERROR);
				return;

			}
			// // 证件号码
			// String identify = et_adentify_num.getText().toString().trim();
			// temp = RegexCust.required("证件号码", identify);
			// if (temp.match) {
			// if (!RegexCust.maxlength(identify, 18)) {
			// result = new Result(false, "证件号码长度过长");
			// showDialog(INFO_ERROR);
			// et_adentify_num.requestFocus();
			// return;
			// }
			// } else {
			// result = temp;
			// showDialog(INFO_ERROR);
			// et_adentify_num.requestFocus();
			// return;
			// }
			application.getApplyPersonalInfo().setApply_amount_value(
					et_apply_amount.getText().toString());
			application.getApplyPersonalInfo().setUsername_value(
					et_username_value.getText().toString());
			application.getApplyPersonalInfo().setAccount_value(
					et_account_value.getText().toString());
			application.getApplyPersonalInfo().setAdentify_num(
					et_adentify_num.getText().toString());

			application.getApplyPersonalInfo().setEmployee_id(
					et_employee_id.getText().toString());
			application.getApplyPersonalInfo().setEmployee_name(
					et_employee_name.getText().toString());

			application.getApplyPersonalInfo().setTick_loan_other_purpose(
					et_otherpurpose.getText().toString());

			shareclear();
			shareinit();
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
					RelaxedLoanSixApplyLoanInfo.this, sb.toString());
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
		if (parent == sp_tick_loan_pursose) {
			str_tick_loan_pursose = (String) sp_tick_loan_pursose
					.getItemAtPosition(position);
			if ("请选择".equals(str_tick_loan_pursose)) {
				sp_tick_loan_pursose
						.setBackgroundResource(R.drawable.comm_spinner_selecter);
			} else {
				sp_tick_loan_pursose
						.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
				if ("其它".equals(str_tick_loan_pursose)) {
					otherpurpose_layout.setVisibility(View.VISIBLE);
				} else {
					otherpurpose_layout.setVisibility(View.GONE);
					et_otherpurpose.setText("");
				}
			}
			sp_tick_loan_pursose.setPadding(15, 0, 0, 0);
			application.getApplyPersonalInfo().setTick_loan_pursose(
					str_tick_loan_pursose);
			application.getApplyPersonalInfo().setTick_loan_pursoseCode(
					tick_loan_codes[position]);
		}
		if (parent == sp_tick_loan_method) {
			str_tick_loan_method = (String) sp_tick_loan_method
					.getItemAtPosition(position);
			if ("请选择".equals(str_tick_loan_method)) {
				sp_tick_loan_method
						.setBackgroundResource(R.drawable.comm_spinner_selecter);
			} else {
				sp_tick_loan_method
						.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
			}
			sp_tick_loan_method.setPadding(15, 0, 0, 0);
			application.getApplyPersonalInfo().setTick_loan_method(
					str_tick_loan_method);
			application.getApplyPersonalInfo().setTick_loan_methodCode(
					repay_method_code[position]);

		}
		if (parent == sp_applyLoanTime) {
			str_applyLoanTime = (String) sp_applyLoanTime
					.getItemAtPosition(position);
			if ("请选择".equals(str_applyLoanTime)) {
				sp_applyLoanTime
						.setBackgroundResource(R.drawable.comm_spinner_selecter);
			} else {
				sp_applyLoanTime
						.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
			}
			sp_applyLoanTime.setPadding(15, 0, 0, 0);
			application.getApplyPersonalInfo().setApplyLoanTime(
					str_applyLoanTime);
			application.getApplyPersonalInfo().setApplyLoanTimeCode(
					apply_loanTime[position]);
		}
		if (parent == sp_send_method) {
			str_send_method = (String) sp_send_method
					.getItemAtPosition(position);
			if ("请选择".equals(str_send_method)) {
				sp_send_method
						.setBackgroundResource(R.drawable.comm_spinner_selecter);
			} else {
				sp_send_method
						.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
			}
			sp_send_method.setPadding(15, 0, 0, 0);
			application.getApplyPersonalInfo().setSend_method(str_send_method);
			application.getApplyPersonalInfo().setSend_methodCode(
					send_address_code[position]);
		}
		super.onItemSelected(parent, view, position, id);
	}
}