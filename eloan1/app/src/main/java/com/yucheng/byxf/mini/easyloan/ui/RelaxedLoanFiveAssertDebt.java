package com.yucheng.byxf.mini.easyloan.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.yucheng.apfx.utl.RegexResult;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.RegexCust;

/**
 * 轻松贷
 * 
 * @author Administrator
 */
public class RelaxedLoanFiveAssertDebt extends BaseRelaxedLoanActivity {
	private Button assert_dept_next_button;

	private EditText et_house_assert;
	private EditText et_car_assert;
	private EditText et_other_assert;

	private EditText et_loan_house;
	private EditText et_loan_car;
	private EditText et_loan_other;

	private EditText et_per_loan_house;
	private EditText et_per_loan_car;
	private EditText et_per_loan_other;

	private RadioGroup is_credit_card_group;

	private EditText et_card_top_money;
	private EditText et_card_bank_name;

	private LinearLayout card_top_money_text_ll;
	private LinearLayout card_bank_name_text_ll;
	// 信息有误
	private final int INFO_ERROR = 0;

	// 验证结果
	private RegexResult result;
	private boolean flag = true;

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		setContentView(R.layout.easy_loan_five_assertdebt);
		super.init();

//		if (application.getAssertInfo() == null) {
//			SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
//					this);
//			if (preferencesUtils.hasData("HouseMoney")) {
//				editinit();
//			}
//		}
		editinit();

		easy_loan_head.setText("申请人资产负债情况");
		assert_dept_next_button = (Button) findViewById(R.id.assert_dept_next_button);
		assert_dept_next_button.setOnClickListener(this);

		et_house_assert = (EditText) findViewById(R.id.et_house_assert);
		et_car_assert = (EditText) findViewById(R.id.et_car_assert);
		et_other_assert = (EditText) findViewById(R.id.et_other_assert);

		et_loan_house = (EditText) findViewById(R.id.et_loan_house);
		et_loan_car = (EditText) findViewById(R.id.et_loan_car);
		et_loan_other = (EditText) findViewById(R.id.et_loan_other);

		et_per_loan_house = (EditText) findViewById(R.id.et_per_loan_house);
		et_per_loan_car = (EditText) findViewById(R.id.et_per_loan_car);
		et_per_loan_other = (EditText) findViewById(R.id.et_per_loan_other);

		card_top_money_text_ll = (LinearLayout) findViewById(R.id.card_top_money_text_ll);
		card_bank_name_text_ll = (LinearLayout) findViewById(R.id.card_bank_name_text_ll);
		is_credit_card_group = (RadioGroup) findViewById(R.id.is_credit_card_group);
//		application.getAssertInfo().setIsHavingCreditCard("是");
		if(null==application.getAssertInfo().getIsHavingCreditCard()||"".equals(application.getAssertInfo().getIsHavingCreditCard())){
			application.getAssertInfo().setIsHavingCreditCard("是");
		}
		is_credit_card_group
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup arg0, int arg1) {
						switch (arg0.getCheckedRadioButtonId()) {
						case R.id.yes_button:
							application.getAssertInfo().setIsHavingCreditCard(
									"是");
							card_top_money_text_ll.setVisibility(View.VISIBLE);
							card_bank_name_text_ll.setVisibility(View.VISIBLE);
							application.getAssertInfo().setCardTopMoney("");
							application.getAssertInfo().setCardBankName("");
							et_card_top_money.setText(application
									.getAssertInfo().getCardTopMoney());
							et_card_bank_name.setText(application
									.getAssertInfo().getCardBankName());
							flag = true;
							break;
						case R.id.no_button:
							application.getAssertInfo().setIsHavingCreditCard(
									"否");
							card_top_money_text_ll.setVisibility(View.GONE);
							card_bank_name_text_ll.setVisibility(View.GONE);
							flag = false;
							break;
						}
					}
				});
		et_card_top_money = (EditText) findViewById(R.id.et_card_top_money);
		et_card_bank_name = (EditText) findViewById(R.id.et_card_bank_name);
		
		
		setData();
	}

	private void editinit() {
//		SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
//				this);
		if (preferencesUtils.hasData("HouseMoney")) {
			application.getAssertInfo().setHouseMoney(
					preferencesUtils.getData("HouseMoney", ""));
			application.getAssertInfo().setCarMoney(
					preferencesUtils.getData("CarMoney", ""));
			application.getAssertInfo().setOtherMoney(
					preferencesUtils.getData("OtherMoney", ""));
			application.getAssertInfo().setDaikuanHouse(
					preferencesUtils.getData("DaikuanHouse", ""));
			application.getAssertInfo().setDaikuanCar(
					preferencesUtils.getData("DaikuanCar", ""));
			application.getAssertInfo().setDaikuanOher(
					preferencesUtils.getData("DaikuanOher", ""));
			application.getAssertInfo().setPayHouse(
					preferencesUtils.getData("PayHouse", ""));
			application.getAssertInfo().setPayCar(
					preferencesUtils.getData("PayCar", ""));
			application.getAssertInfo().setPayOher(
					preferencesUtils.getData("PayOher", ""));
			application.getAssertInfo().setIsHavingCreditCard(
					preferencesUtils.getData("IsHavingCreditCard", ""));
			application.getAssertInfo().setCardTopMoney(
					preferencesUtils.getData("CardTopMoney", ""));
			application.getAssertInfo().setCardBankName(
					preferencesUtils.getData("CardBankName", ""));
		}
		
	}

	private void shareclear() {
//		SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
//				this);
		if (preferencesUtils.hasData("HouseMoney")) {
			preferencesUtils.removeData("HouseMoney");
			preferencesUtils.removeData("CarMoney");
			preferencesUtils.removeData("OtherMoney");
			preferencesUtils.removeData("DaikuanHouse");
			preferencesUtils.removeData("DaikuanCar");
			preferencesUtils.removeData("DaikuanOher");
			preferencesUtils.removeData("PayHouse");
			preferencesUtils.removeData("PayCar");
			preferencesUtils.removeData("PayOher");
			preferencesUtils.removeData("IsHavingCreditCard");
			preferencesUtils.removeData("CardTopMoney");
			preferencesUtils.removeData("CardBankName");
		}
	}

	private void shareinit() {
//		SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
//				this);
		preferencesUtils.setData("HouseMoney", application.getAssertInfo()
				.getHouseMoney());
		preferencesUtils.setData("CarMoney", application.getAssertInfo()
				.getCarMoney());
		preferencesUtils.setData("OtherMoney", application.getAssertInfo()
				.getOtherMoney());
		preferencesUtils.setData("DaikuanHouse", application.getAssertInfo()
				.getDaikuanHouse());
		preferencesUtils.setData("DaikuanCar", application.getAssertInfo()
				.getDaikuanCar());
		preferencesUtils.setData("DaikuanOher", application.getAssertInfo()
				.getDaikuanOher());
		preferencesUtils.setData("PayHouse", application.getAssertInfo()
				.getPayHouse());
		preferencesUtils.setData("PayCar", application.getAssertInfo()
				.getPayCar());
		preferencesUtils.setData("PayOher", application.getAssertInfo()
				.getPayOher());
		preferencesUtils.setData("IsHavingCreditCard", application
				.getAssertInfo().getIsHavingCreditCard());
		preferencesUtils.setData("CardTopMoney", application.getAssertInfo()
				.getCardTopMoney());
		preferencesUtils.setData("CardBankName", application.getAssertInfo()
				.getCardBankName());
	}

	private void setData(){
		if (application.getAssertInfo() != null) {
			et_house_assert
					.setText(application.getAssertInfo().getHouseMoney());
			et_car_assert.setText(application.getAssertInfo().getCarMoney());
			et_other_assert
					.setText(application.getAssertInfo().getOtherMoney());

			et_loan_house
					.setText(application.getAssertInfo().getDaikuanHouse());
			et_loan_car.setText(application.getAssertInfo().getDaikuanCar());
			et_loan_other.setText(application.getAssertInfo().getDaikuanOher());

			et_per_loan_house
					.setText(application.getAssertInfo().getPayHouse());
			et_per_loan_car.setText(application.getAssertInfo().getPayCar());
			et_per_loan_other.setText(application.getAssertInfo().getPayOher());

			if (!"".equals(application.getAssertInfo().getIsHavingCreditCard())) {
				for (int i = 0; i < is_credit_card_group.getChildCount(); i++) {
					RadioButton radioButton = (RadioButton) is_credit_card_group
							.getChildAt(i);
					if (radioButton.getText()
							.equals(application.getAssertInfo()
									.getIsHavingCreditCard())) {
						radioButton.setChecked(true);
					}
				}
			}
			et_card_top_money.setText(application.getAssertInfo()
					.getCardTopMoney());
			et_card_bank_name.setText(application.getAssertInfo()
					.getCardBankName());
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
		// 单张信用卡的最高额度；
		if (v.getId() == R.id.assert_dept_next_button) {
			if (flag) {
			  RegexResult temp = null;
				String nowDpName = et_card_top_money.getText().toString()
						.trim();
				temp = RegexCust.required("单张信用卡的最高额度", nowDpName);
				if (temp.match) {
					if (!RegexCust.lengthMax(nowDpName, 10)) {
						result = new RegexResult(false, "单张信用卡的最高额度过长!");
						showDialog(INFO_ERROR);
						et_card_top_money.requestFocus();
						return;
					}
				} else {
					result = temp;
					showDialog(INFO_ERROR);
					et_card_top_money.requestFocus();
					return;
				}
				// 发卡银行名称
				String rzDp = et_card_bank_name.getText().toString().trim();
				temp = RegexCust.required("发卡银行名称", rzDp);
				if (temp.match) {
					if (!RegexCust.lengthMax(nowDpName, 60)) {
						result = new RegexResult(false, "发卡银行名称长度过长!");
						showDialog(INFO_ERROR);
						et_card_bank_name.requestFocus();
						return;
					}
				} else {
					result = temp;
					showDialog(INFO_ERROR);
					et_card_bank_name.requestFocus();
					return;
				}

				if (RegexCust.nosymbol(rzDp) == false) {
					result = new RegexResult(false, "发卡行禁止输入符号等字符");
					showDialog(INFO_ERROR);
					et_card_bank_name.requestFocus();
					return;
				}
				
			}
			// 写入内存；
			application.getAssertInfo().setHouseMoney(
					et_house_assert.getText().toString());
			application.getAssertInfo().setCarMoney(
					et_car_assert.getText().toString());
			application.getAssertInfo().setOtherMoney(
					et_other_assert.getText().toString());

			application.getAssertInfo().setDaikuanHouse(
					et_loan_house.getText().toString());
			application.getAssertInfo().setDaikuanCar(
					et_loan_car.getText().toString());
			application.getAssertInfo().setDaikuanOher(
					et_loan_other.getText().toString());

			application.getAssertInfo().setPayHouse(
					et_per_loan_house.getText().toString());
			application.getAssertInfo().setPayCar(
					et_per_loan_car.getText().toString());
			application.getAssertInfo().setPayOher(
					et_per_loan_other.getText().toString());

			application.getAssertInfo().setCardTopMoney(
					et_card_top_money.getText().toString());
			application.getAssertInfo().setCardBankName(
					et_card_bank_name.getText().toString());

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
					RelaxedLoanFiveAssertDebt.this, sb.toString());
			break;
		default:
			break;
		}
		return super.onCreateDialog(id, args);
	}
}