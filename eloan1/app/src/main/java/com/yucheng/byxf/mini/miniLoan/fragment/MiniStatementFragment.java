package com.yucheng.byxf.mini.miniLoan.fragment;

import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.SharedPreferencesUtils;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * 账单及自动扣款信息Fragment
 * 
 */
public class MiniStatementFragment extends Fragment implements OnClickListener {
	private Button mini_loan_Bill_info_next_button;

	private RadioGroup repay_account_group;

	private RadioButton pay_account_button1;

	private RadioButton pay_account_button2;

	private RadioButton pay_account_button3;

	private EditText edit_card_no_value;

	private RadioGroup repay_setting_group;

	private RadioButton repay_setting_button1;

	private RadioButton repay_setting_button2;

	private EditText edit_spread_no;

	private EditText edit_spread_name;
	private ImageView back10;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.minifragment6, container, false);
		mini_loan_Bill_info_next_button = (Button) view
				.findViewById(R.id.mini_loan_Bill_info_next_button);
		mini_loan_Bill_info_next_button.setOnClickListener(this);

		repay_account_group = (RadioGroup) view
				.findViewById(R.id.repay_account_group);
		pay_account_button1 = (RadioButton) view
				.findViewById(R.id.pay_account_button1);
		pay_account_button2 = (RadioButton) view
				.findViewById(R.id.pay_account_button2);
		pay_account_button3 = (RadioButton) view
				.findViewById(R.id.pay_account_button3);
		edit_card_no_value = (EditText) view
				.findViewById(R.id.edit_card_no_value);
		repay_setting_group = (RadioGroup) view
				.findViewById(R.id.repay_setting_group);
		repay_setting_button1 = (RadioButton) view
				.findViewById(R.id.repay_setting_button1);
		repay_setting_button2 = (RadioButton) view
				.findViewById(R.id.repay_setting_button2);
		edit_spread_no = (EditText) view.findViewById(R.id.edit_spread_no);
//		edit_spread_name = (EditText) view.findViewById(R.id.edit_spread_name);
		back10=(ImageView) view.findViewById(R.id.back10);
		back10.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FragmentManager fm = getFragmentManager();
				fm.popBackStack();
			}
		});
		initinfo();
		return view;
	}

	private void initinfo() {
		// TODO Auto-generated method stub
		SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
				getActivity(), "MiniFive");
		MiniApplyInfo applyinfo = preferencesUtils.readObj();
		if (applyinfo != null) {
			if (applyinfo.getIdCard().equals(
					Contents.response.getResult().getIdNo())) {
				int atpyday = applyinfo.getAtpyDay();
				if (atpyday == 06) {
					repay_account_group.check(R.id.pay_account_button1);
				} else if (atpyday == 16) {
					repay_account_group.check(R.id.pay_account_button2);
				} else if (atpyday == 26) {
					repay_account_group.check(R.id.pay_account_button3);
				}
				edit_card_no_value.setText(applyinfo.getJiejikaCard());
				String huankuanshezhi = applyinfo.getRpymOption();
				if (huankuanshezhi == "ALL") {
					repay_setting_group.check(R.id.repay_setting_button1);
				} else if (huankuanshezhi == "SUB") {
					repay_setting_group.check(R.id.repay_setting_button2);
				}
				edit_spread_no.setText(applyinfo.getTuiguangId());
//				edit_spread_name.setText(applyinfo.getTuiguangName());
			}
		} else {
			// 身份证号不匹配 删除原有的数据
			preferencesUtils.clearData();
		}
	}

	private void saveInfo() {
		MiniApplyInfo saveapplyinfo = new MiniApplyInfo();
		saveapplyinfo.setIdCard(Contents.response.getResult().getIdNo());
		switch (repay_account_group.getCheckedRadioButtonId()) {
		case R.id.pay_account_button1:
			saveapplyinfo.setAtpyDay((short) 6);
			break;
		case R.id.pay_account_button2:
			saveapplyinfo.setAtpyDay((short) 16);
			break;
		case R.id.pay_account_button3:
			saveapplyinfo.setAtpyDay((short) 26);
			break;

		default:
			break;
		}
		saveapplyinfo.setJiejikaCard(edit_card_no_value.getText().toString());
		switch (repay_setting_group.getCheckedRadioButtonId()) {
		case R.id.repay_setting_button1:
			saveapplyinfo.setRpymOption("ALL");
			break;
		case R.id.repay_setting_button2:
			saveapplyinfo.setRpymOption("SUB");
			break;
		default:
			break;
		}
		saveapplyinfo.setTuiguangId(edit_spread_no.getText().toString());
//		saveapplyinfo.setTuiguangName(edit_spread_name.getText().toString());
		SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
				getActivity(), "MiniFive");
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
		case R.id.mini_loan_Bill_info_next_button:
			
			
			
			if (edit_card_no_value.getText().toString().length()!=16) {
				showEditDialog(v, "借记卡卡号长度不正确！");
				edit_card_no_value.requestFocus();
				return;
			}
//			if (!repay_account_group.isClickable()) {
//				showEditDialog(v, "请选择还款设置！");
//				return;
//			}
			saveInfo();
			MiniIDCardInfoFragment six = new MiniIDCardInfoFragment();
			FragmentManager fm = getFragmentManager();
			FragmentTransaction tx = fm.beginTransaction();
			tx.replace(R.id.id_content, six, "Seven");
			// tx.remove(fm.findFragmentByTag("Three"));
			 tx.addToBackStack("Six");
			tx.commit();
			break;

		default:
			break;
		}
	}

	public void showEditDialog(View view, String str) {
		DialogFragment myFragment = NameDialogFragment.newInstance(str);
		myFragment.show(getFragmentManager(), "EditNameDialog");
	}
}
