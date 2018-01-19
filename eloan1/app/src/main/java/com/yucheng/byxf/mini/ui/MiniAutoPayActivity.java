package com.yucheng.byxf.mini.ui;

import com.yucheng.byxf.mini.domain.MiniPersonInfo;
import com.yucheng.byxf.mini.utils.CommonUtil;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
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
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MiniAutoPayActivity extends BaseActivity implements OnClickListener{

	private Button next_button;
	private RadioGroup repay_account_group;
	private EditText edit_card_no_value;
	private RadioGroup repay_setting_group;
	private String zhangdan;
	private String huankuan;
	private TextView tv_tag;
	private EditText edit_spread_name;
	private EditText edit_spread_no;
	private ImageView iv_menu_common;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mini_autopay_info);
		initView();
	}

	private void initView() {
		next_button = (Button)findViewById(R.id.next_button);
		next_button.setOnClickListener(this);
		iv_menu_common = (ImageView)findViewById(R.id.iv_menu_common);
		iv_menu_common.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View arg0) {
				finish();		
			}
		});
		//借记卡卡号
		edit_card_no_value = (EditText)findViewById(R.id.edit_card_no_value);
		edit_card_no_value.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				String card_num = edit_card_no_value.getText().toString().trim();
				MiniPersonInfo.card_num = card_num;
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,int after) {}
			@Override
			public void afterTextChanged(Editable s) {}
		});
		//账单日
		repay_account_group = (RadioGroup)findViewById(R.id.repay_account_group);
		if(MiniPersonInfo.zhangdan==null || "".equals(MiniPersonInfo.zhangdan)){
			MiniPersonInfo.zhangdan = "6日";
		}
		repay_account_group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup arg0, int pos) {
				switch(arg0.getCheckedRadioButtonId()){
				case R.id.pay_account_button1:
					zhangdan = "6日";
					break;
				case R.id.pay_account_button2:
					zhangdan = "16日";
					break;
				case R.id.pay_account_button3:
					zhangdan = "26日";
					break;			
				}
				MiniPersonInfo.zhangdan = zhangdan;
			}
		});
		
		//还款设置
		repay_setting_group = (RadioGroup)findViewById(R.id.repay_setting_group);
		if(MiniPersonInfo.huankuan_setting==null || "".equals(MiniPersonInfo.huankuan_setting)){
			MiniPersonInfo.huankuan_setting = "最低还款额";
		}
		repay_setting_group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup arg0, int pos) {
				switch(arg0.getCheckedRadioButtonId()){
				case R.id.repay_setting_button1:
					huankuan = "最低还款额";
					break;
				case R.id.repay_setting_button2:
					huankuan = "全部还款额";
					break;	
				}
				MiniPersonInfo.huankuan_setting = huankuan;
			}
		});
		
		//推广员工信息
		edit_spread_no = (EditText)findViewById(R.id.edit_spread_no);
		edit_spread_no.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				String spread_no = edit_spread_no.getText().toString().trim();
				MiniPersonInfo.spread_no = spread_no;
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,int after) {}
			@Override
			public void afterTextChanged(Editable s) {}
		});
		edit_spread_name = (EditText)findViewById(R.id.edit_spread_name);
		edit_spread_name.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				String spread_name = edit_spread_name.getText().toString().trim();
				MiniPersonInfo.spread_name = spread_name;
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,int after) {}
			@Override
			public void afterTextChanged(Editable s) {}
		});
		tv_tag = (TextView)findViewById(R.id.tv_tag);
		Spanned tag = Html.fromHtml(
				"<p>须知：<br>"
				+"1.申请人须保证上述自扣关联卡号在开户时所使用的证件类型及号码与申请本贷款时填写的证件类型及号码一致。<br>"
				+"2.申请人填写自动还款资料后，即表示同意授权北京银行代为从以上银行卡的人民币活期账户中扣缴账款。<br>"
				+"3.北京银行将于每月账单日次日扣款，请于当日保持足够余额。若余额不足，则会将账户余额扣到“0”元。其余部分不再另行补扣，申请人另行通过其他方式还款。申请人本人愿意承担因账户内资金不足导致还款失败所导致的一切费用和责任。<br>"
				+"4.北京银行暂不支持与存折关联还款，请勿写存折号，以免关联失败延误还款。</p>");
		tv_tag.setText(tag.toString());
	}

	@Override
	protected void onResume() {
		if(!MiniPersonInfo.zhangdan.equals("")){
			//账单日
			for (int i = 0; i < repay_account_group.getChildCount(); i++) {
				RadioButton radioButton = (RadioButton) repay_account_group
						.getChildAt(i);
				if (radioButton.getText().equals(
						MiniPersonInfo.zhangdan)) {
					radioButton.setChecked(true);
				}
			}
			
			//卡号
			edit_card_no_value.setText(MiniPersonInfo.card_num);
			//还款设置
			for (int i = 0; i < repay_setting_group.getChildCount(); i++) {
				RadioButton radioButton = (RadioButton) repay_setting_group
						.getChildAt(i);
				if (radioButton.getText().equals(
						MiniPersonInfo.huankuan_setting)) {
					radioButton.setChecked(true);
				}
			}
			//推广员工ID
			edit_spread_no.setText(MiniPersonInfo.spread_no);
			//推广员工姓名
			edit_spread_name.setText(MiniPersonInfo.spread_name);
		}
		super.onResume();
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.next_button:
			String card_no_value = edit_card_no_value.getText().toString();
			String edit_spread_no_value = edit_spread_no.getText().toString();
			String edit_spread_name_value = edit_spread_name.getText().toString();
				if(!edit_spread_no_value.equals("")){
					MiniPersonInfo.spread_no = edit_spread_no_value;
				}
				if(!edit_spread_name_value.equals("")){
					MiniPersonInfo.spread_name = edit_spread_name_value;
				}
				if(card_no_value.equals("")||card_no_value.length()==16 || card_no_value.length() == 19){
					MiniPersonInfo.card_num = card_no_value;
					Intent intent = new Intent(MiniAutoPayActivity.this,MiniPaperPhotoActivity.class);
					startActivity(intent);
				}else{
//					CommonUtil.commonToast(getApplicationContext(), "请输入16位或19位卡号！");
					AlertDialog.Builder builder = new AlertDialog.Builder(
							new ContextThemeWrapper(this, R.style.AlertDialogCustom));
					builder.setTitle("提示信息");
					builder.setMessage("请输入16位或19位卡号！");
					builder.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
								}
							});
					builder.create();
					builder.show();
				}


			break;

		}
		
	}

}
