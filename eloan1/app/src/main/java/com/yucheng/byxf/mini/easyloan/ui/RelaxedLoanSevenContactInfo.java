package com.yucheng.byxf.mini.easyloan.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.yucheng.apfx.utl.RegexResult;
import com.yucheng.byxf.mini.domain.MiniPersonInfo;
import com.yucheng.byxf.mini.message.LoginMessage;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.CommonUtil;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.RegexCust;

public class RelaxedLoanSevenContactInfo extends BaseRelaxedLoanActivity {
	private Button photogragh_info_next_button;

	private EditText et_contactName1;
	private Spinner sp_contactRelation1;
	private EditText et_area_telphone1;
	private EditText et_area_telphone1code;
	private EditText et_mobileNo1;

	private EditText et_contactName2;
	private Spinner sp_contactRelation2;
	private EditText et_area_telphone2;
	private EditText et_area_telphone2code;
	private EditText et_mobileNo2;

	private EditText et_contactName3;
	private Spinner sp_contactRelation3;
	private EditText et_area_telphone3;
	private EditText et_area_telphone3code;
	private EditText et_mobileNo3;

	private EditText et_contactName4;
	private Spinner sp_contactRelation4;
	private EditText et_area_telphone4;
	private EditText et_area_telphone4code;
	private EditText et_mobileNo4;

	private String[] apply_relationship = { "请选择", "配偶", "父母", "子女", "兄弟姐妹" };
	private String[] apply_relationshipCode = { "", "01", "02", "03", "09" };
	private String[] apply_relationship2 = { "请选择", "配偶", "父母", "子女", "其他血亲",
			"其他姻亲", "同事", "合伙人", "其他关系", "同学", "朋友", "兄弟姐妹" };
	private String[] apply_relationship2Code = { "", "01", "02", "03", "04",
			"05", "06", "07", "08", "10", "11", "09" };
	private String str_contactRelation1;
	private String str_contactRelation2;
	private String str_contactRelation3;
	private String str_contactRelation4;

	// 验证结果
	private RegexResult result;
	// 信息有误
	private final int INFO_ERROR = 0;

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		setContentView(R.layout.easy_loan_seven_contactinfo);
		super.init();
		// if (application.getLinkManInfo() == null) {
		// SharedPreferencesUtils preferencesUtils =new
		// SharedPreferencesUtils(this);
		// if(preferencesUtils.hasData("et_contactName1")){
		// editinit();
		// }
		// }
		editinit();

		easy_loan_head.setText("联系人信息");
		photogragh_info_next_button = (Button) findViewById(R.id.photogragh_info_next_button);
		photogragh_info_next_button.setOnClickListener(this);

		et_contactName1 = (EditText) findViewById(R.id.et_contactName1);
		sp_contactRelation1 = (Spinner) findViewById(R.id.sp_contactRelation1);
		CommonUtil.setSpinner(this, apply_relationship, sp_contactRelation1);
		sp_contactRelation1.setOnItemSelectedListener(this);
		et_area_telphone1 = (EditText) findViewById(R.id.et_area_telphone1);
		et_area_telphone1code = (EditText) findViewById(R.id.et_area_telphone1code);
		et_mobileNo1 = (EditText) findViewById(R.id.et_mobileNo1);

		et_contactName2 = (EditText) findViewById(R.id.et_contactName2);
		sp_contactRelation2 = (Spinner) findViewById(R.id.sp_contactRelation2);
		CommonUtil.setSpinner(this, apply_relationship2, sp_contactRelation2);
		sp_contactRelation2.setOnItemSelectedListener(this);
		et_area_telphone2code = (EditText) findViewById(R.id.et_area_telphone2code);
		et_area_telphone2 = (EditText) findViewById(R.id.et_area_telphone2);
		et_mobileNo2 = (EditText) findViewById(R.id.et_mobileNo2);

		et_contactName3 = (EditText) findViewById(R.id.et_contactName3);
		sp_contactRelation3 = (Spinner) findViewById(R.id.sp_contactRelation3);
		CommonUtil.setSpinner(this, apply_relationship2, sp_contactRelation3);
		sp_contactRelation3.setOnItemSelectedListener(this);
		et_area_telphone3 = (EditText) findViewById(R.id.et_area_telphone3);
		et_area_telphone3code = (EditText) findViewById(R.id.et_area_telphone3code);
		et_mobileNo3 = (EditText) findViewById(R.id.et_mobileNo3);

		et_contactName4 = (EditText) findViewById(R.id.et_contactName4);
		sp_contactRelation4 = (Spinner) findViewById(R.id.sp_contactRelation4);
		CommonUtil.setSpinner(this, apply_relationship2, sp_contactRelation4);
		sp_contactRelation4.setOnItemSelectedListener(this);
		et_area_telphone4 = (EditText) findViewById(R.id.et_area_telphone4);
		et_area_telphone4code = (EditText) findViewById(R.id.et_area_telphone4code);
		et_mobileNo4 = (EditText) findViewById(R.id.et_mobileNo4);
		setData();
	}

	private void shareclear() {
		// SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
		// this);
		if (preferencesUtils.hasData("ContactName1")) {
			preferencesUtils.removeData("ContactName1");
			preferencesUtils.removeData("ContactRelation1");
			preferencesUtils.removeData("Area_telphone1");
			preferencesUtils.removeData("Area_telphone1zone");
			preferencesUtils.removeData("MobileNo1");

			preferencesUtils.removeData("ContactName2");
			preferencesUtils.removeData("ContactRelation2");
			preferencesUtils.removeData("Area_telphone2");
			preferencesUtils.removeData("Area_telphone2zone");
			preferencesUtils.removeData("MobileNo2");

			preferencesUtils.removeData("ContactName3");
			preferencesUtils.removeData("ContactRelation3");
			preferencesUtils.removeData("Area_telphone3");
			preferencesUtils.removeData("Area_telphone3zone");
			preferencesUtils.removeData("MobileNo3");

			preferencesUtils.removeData("ContactName4");
			preferencesUtils.removeData("ContactRelation4");
			preferencesUtils.removeData("Area_telphone4");
			preferencesUtils.removeData("Area_telphone4zone");
			preferencesUtils.removeData("MobileNo4");
		}
	}

	private void shareinit() {
		// SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
		// this);

		preferencesUtils.setData("ContactName1", application.getLinkManInfo()
				.getContactName1());
		preferencesUtils.setData("ContactRelation1", application
				.getLinkManInfo().getContactRelation1());
		preferencesUtils.setData("Area_telphone1", application.getLinkManInfo()
				.getArea_telphone1());
		preferencesUtils.setData("Area_telphone1zone", application
				.getLinkManInfo().getArea_telphone1zone());
		preferencesUtils.setData("MobileNo1", application.getLinkManInfo()
				.getMobileNo1());

		preferencesUtils.setData("ContactName2", application.getLinkManInfo()
				.getContactName2());
		preferencesUtils.setData("ContactRelation2", application
				.getLinkManInfo().getContactRelation2());
		preferencesUtils.setData("Area_telphone2", application.getLinkManInfo()
				.getArea_telphone2());
		preferencesUtils.setData("Area_telphone2zone", application
				.getLinkManInfo().getArea_telphone2zone());
		preferencesUtils.setData("MobileNo2", application.getLinkManInfo()
				.getMobileNo2());

		preferencesUtils.setData("ContactName3", application.getLinkManInfo()
				.getContactName3());
		preferencesUtils.setData("ContactRelation3", application
				.getLinkManInfo().getContactRelation3());
		preferencesUtils.setData("Area_telphone3", application.getLinkManInfo()
				.getArea_telphone3());
		preferencesUtils.setData("Area_telphone3zone", application
				.getLinkManInfo().getArea_telphone3zone());
		preferencesUtils.setData("MobileNo3", application.getLinkManInfo()
				.getMobileNo3());

		preferencesUtils.setData("ContactName4", application.getLinkManInfo()
				.getContactName4());
		preferencesUtils.setData("ContactRelation4", application
				.getLinkManInfo().getContactRelation4());
		preferencesUtils.setData("Area_telphone4", application.getLinkManInfo()
				.getArea_telphone4());
		preferencesUtils.setData("Area_telphone4zone", application
				.getLinkManInfo().getArea_telphone4zone());
		preferencesUtils.setData("MobileNo4", application.getLinkManInfo()
				.getMobileNo4());
	}

	private void editinit() {
		// SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
		// this);
		if (preferencesUtils.hasData("ContactName1")) {
			application.getLinkManInfo().setContactName1(
					preferencesUtils.getData("ContactName1", ""));
			application.getLinkManInfo().setContactRelation1(
					preferencesUtils.getData("ContactRelation1", ""));
			application.getLinkManInfo().setArea_telphone1(
					preferencesUtils.getData("Area_telphone1", ""));
			application.getLinkManInfo().setArea_telphone1zone(
					preferencesUtils.getData("Area_telphone1zone", ""));
			application.getLinkManInfo().setMobileNo1(
					preferencesUtils.getData("MobileNo1", ""));

			application.getLinkManInfo().setContactName2(
					preferencesUtils.getData("ContactName2", ""));
			application.getLinkManInfo().setContactRelation2(
					preferencesUtils.getData("ContactRelation2", ""));
			application.getLinkManInfo().setArea_telphone2(
					preferencesUtils.getData("Area_telphone2", ""));
			application.getLinkManInfo().setArea_telphone2zone(
					preferencesUtils.getData("Area_telphone2zone", ""));
			application.getLinkManInfo().setMobileNo2(
					preferencesUtils.getData("MobileNo2", ""));

			application.getLinkManInfo().setContactName3(
					preferencesUtils.getData("ContactName3", ""));
			application.getLinkManInfo().setContactRelation3(
					preferencesUtils.getData("ContactRelation3", ""));
			application.getLinkManInfo().setArea_telphone3(
					preferencesUtils.getData("Area_telphone3", ""));
			application.getLinkManInfo().setArea_telphone3zone(
					preferencesUtils.getData("Area_telphone3zone", ""));
			application.getLinkManInfo().setMobileNo3(
					preferencesUtils.getData("MobileNo3", ""));

			application.getLinkManInfo().setContactName4(
					preferencesUtils.getData("ContactName4", ""));
			application.getLinkManInfo().setContactRelation4(
					preferencesUtils.getData("ContactRelation4", ""));
			application.getLinkManInfo().setArea_telphone4(
					preferencesUtils.getData("Area_telphone4", ""));
			application.getLinkManInfo().setArea_telphone4zone(
					preferencesUtils.getData("Area_telphone4zone", ""));
			application.getLinkManInfo().setMobileNo4(
					preferencesUtils.getData("MobileNo4", ""));

		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub

		super.onResume();
	}

	private void setData() {
		if (application.getLinkManInfo() != null) {
			et_contactName1.setText(application.getLinkManInfo()
					.getContactName1());
			if (!"".equals(application.getLinkManInfo()
					.getContactRelation1Code())) {
				for (int i = 0; i < sp_contactRelation1.getCount(); i++) {
					if (apply_relationshipCode[i].equals(application
							.getLinkManInfo().getContactRelation1Code())) {
						application.getLinkManInfo().setContactRelation1(
								apply_relationship[i]);
					}
				}
			}

			if (!"".equals(application.getLinkManInfo().getContactRelation1())) {
				for (int i = 0; i < sp_contactRelation1.getCount(); i++) {
					String item = apply_relationship[i];
					if (item.equals(application.getLinkManInfo()
							.getContactRelation1())) {
						sp_contactRelation1.setSelection(i);
					}
				}
			}
			et_area_telphone1.setText(application.getLinkManInfo()
					.getArea_telphone1());
			et_area_telphone1code.setText(application.getLinkManInfo()
					.getArea_telphone1zone());
			et_mobileNo1.setText(application.getLinkManInfo().getMobileNo1());
			// 第二个联系人
			et_contactName2.setText(application.getLinkManInfo()
					.getContactName2());
			if (!"".equals(application.getLinkManInfo()
					.getContactRelation2Code())) {
				for (int i = 0; i < sp_contactRelation2.getCount(); i++) {
					if (apply_relationship2Code[i].equals(application
							.getLinkManInfo().getContactRelation2Code())) {
						application.getLinkManInfo().setContactRelation2(
								apply_relationship2[i]);
					}
				}
			}

			if (!"".equals(application.getLinkManInfo().getContactRelation2())) {
				for (int i = 0; i < sp_contactRelation2.getCount(); i++) {
					String item = apply_relationship2[i];
					if (item.equals(application.getLinkManInfo()
							.getContactRelation2())) {
						sp_contactRelation2.setSelection(i);
					}
				}
			}
			et_area_telphone2.setText(application.getLinkManInfo()
					.getArea_telphone2());
			et_area_telphone2code.setText(application.getLinkManInfo()
					.getArea_telphone2zone());
			et_mobileNo2.setText(application.getLinkManInfo().getMobileNo2());
			// 第三个联系人
			et_contactName3.setText(application.getLinkManInfo()
					.getContactName3());

			if (!"".equals(application.getLinkManInfo()
					.getContactRelation3Code())) {
				for (int i = 0; i < sp_contactRelation3.getCount(); i++) {
					if (apply_relationship2Code[i].equals(application
							.getLinkManInfo().getContactRelation3Code())) {
						application.getLinkManInfo().setContactRelation3(
								apply_relationship2[i]);
					}
				}
			}

			if (!"".equals(application.getLinkManInfo().getContactRelation3())) {
				for (int i = 0; i < sp_contactRelation3.getCount(); i++) {
					String item = apply_relationship2[i];
					if (item.equals(application.getLinkManInfo()
							.getContactRelation3())) {
						sp_contactRelation3.setSelection(i);
					}
				}
			}
			et_area_telphone3.setText(application.getLinkManInfo()
					.getArea_telphone3());
			et_area_telphone3code.setText(application.getLinkManInfo()
					.getArea_telphone3zone());
			et_mobileNo3.setText(application.getLinkManInfo().getMobileNo3());
			// 第四个联系人
			et_contactName4.setText(application.getLinkManInfo()
					.getContactName4());

			if (!"".equals(application.getLinkManInfo()
					.getContactRelation4Code())) {
				for (int i = 0; i < sp_contactRelation4.getCount(); i++) {
					if (apply_relationship2Code[i].equals(application
							.getLinkManInfo().getContactRelation4Code())) {
						application.getLinkManInfo().setContactRelation4(
								apply_relationship2[i]);
					}
				}
			}

			if (!"".equals(application.getLinkManInfo().getContactRelation4())) {
				for (int i = 0; i < sp_contactRelation4.getCount(); i++) {
					String item = apply_relationship2[i];
					if (item.equals(application.getLinkManInfo()
							.getContactRelation4())) {
						sp_contactRelation4.setSelection(i);
					}
				}
			}
			et_area_telphone4.setText(application.getLinkManInfo()
					.getArea_telphone4());
			et_area_telphone4code.setText(application.getLinkManInfo()
					.getArea_telphone4zone());
			et_mobileNo4.setText(application.getLinkManInfo().getMobileNo4());
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
					RelaxedLoanSevenContactInfo.this, sb.toString());
			break;
		default:
			break;
		}
		return super.onCreateDialog(id, args);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.photogragh_info_next_button) {
			RegexResult temp = null;
			// 直系亲属联系人姓名
			temp = RegexCust.length("姓名", et_contactName1.getText().toString(),
					2, 30);
			if (temp.match) {
				temp = RegexCust.chinese("姓名", et_contactName1.getText()
						.toString());
				if (!temp.match) {
					result = new RegexResult(false, "直系亲属姓名应为汉字!");
					showDialog(INFO_ERROR);
					return;
				}
			} else {
				if (et_contactName1.getText().toString().length() == 0) {
					result = new RegexResult(false, "直系亲属联系人姓名不能为空!");
				} else {
					result = new RegexResult(false, "直系亲属姓名字符长度不对!");
				}
				showDialog(INFO_ERROR);
				return;
			}
			// 直系亲属联系人与申请人关系
			if (str_contactRelation1 == null
					|| "请选择".equals(str_contactRelation1)) {
				result = new RegexResult(false, "请选择直系亲属联系人与申请人关系!");
				showDialog(INFO_ERROR);
				return;
			}
			// 直系亲属住宅电话区号
			if (et_area_telphone1code.getText().toString() != null
					&& !et_area_telphone1code.getText().toString().equals("")) {
				if (!RegexCust.numberNatural(et_area_telphone1code.getText()
						.toString())) {
					result = new RegexResult(false, "直系亲属住宅电话区号格式不对!");
					showDialog(INFO_ERROR);
					et_area_telphone1code.requestFocus();
					return;
				} else {
					if (et_area_telphone1code.length() > 4) {
						result = new RegexResult(false, "直系亲属住宅电话区号长度不应大于4!");
						showDialog(INFO_ERROR);
						et_area_telphone1code.requestFocus();
						return;
					}
				}
			}

			// 直系亲属住宅电话号码
			if (et_area_telphone1.getText().toString() != null
					&& !et_area_telphone1.getText().toString().equals("")) {
				temp = RegexCust.required("住宅电话号码", et_area_telphone1.getText()
						.toString());
				if (temp.match) {
					if (RegexCust.phoneFix(et_area_telphone1.getText()
							.toString()) == false) {
						result = new RegexResult(false, "直系亲属住宅电话号码格式不对!");
						showDialog(INFO_ERROR);
						et_area_telphone1.requestFocus();
						return;
					}
				} else {
					result = temp;
					showDialog(INFO_ERROR);
					et_area_telphone1.requestFocus();
					return;
				}
			}
			// 直系亲属手机号码
			temp = RegexCust
					.required("手机号码", et_mobileNo1.getText().toString());
			if (temp.match) {
				if (RegexCust.phoneMob(et_mobileNo1.getText().toString()) == false) {
					result = new RegexResult(false, "直系亲属手机号码格式不对!");
					showDialog(INFO_ERROR);
					et_mobileNo1.requestFocus();
					return;
				}
			} else {
				result = temp;
				showDialog(INFO_ERROR);
				et_mobileNo1.requestFocus();
				return;
			}

			// 第二联系人
			temp = RegexCust.length("姓名", et_contactName2.getText().toString(),
					2, 30);
			if (temp.match) {
				temp = RegexCust.chinese("姓名", et_contactName2.getText()
						.toString());
				if (!temp.match) {
					result = new RegexResult(false, "联系人姓名应为汉字!");
					showDialog(INFO_ERROR);
					return;
				}
			} else {
				if (et_contactName2.getText().toString().length() == 0) {
					result = new RegexResult(false, "联系人姓名不能为空!");
				} else {
					result = new RegexResult(false, "联系人姓名字符长度不对!");
				}
				showDialog(INFO_ERROR);
				return;
			}
			// 直系亲属联系人与申请人关系
			if (str_contactRelation2 == null
					|| "请选择".equals(str_contactRelation2)) {
				result = new RegexResult(false, "请选择联系人与申请人关系!");
				showDialog(INFO_ERROR);
				return;
			}
			// 直系亲属住宅电话区号
			if (et_area_telphone2code.getText().toString() != null
					&& !et_area_telphone2code.getText().toString().equals("")) {
				if (!RegexCust.numberNatural(et_area_telphone2code.getText()
						.toString())) {
					result = new RegexResult(false, "联系人住宅电话区号格式不对!");
					showDialog(INFO_ERROR);
					et_area_telphone2code.requestFocus();
					return;
				} else {
					if (et_area_telphone2code.length() > 4) {
						result = new RegexResult(false, "联系人住宅电话区号长度不应大于4!");
						showDialog(INFO_ERROR);
						et_area_telphone2code.requestFocus();
						return;
					}
				}
			}

			// 联系人住宅电话号码
			if (et_area_telphone2.getText().toString() != null
					&& !et_area_telphone2.getText().toString().equals("")) {
				temp = RegexCust.required("住宅电话号码", et_area_telphone2.getText()
						.toString());
				if (temp.match) {
					if (RegexCust.phoneFix(et_area_telphone2.getText()
							.toString()) == false) {
						result = new RegexResult(false, "联系人住宅电话号码格式不对!");
						showDialog(INFO_ERROR);
						et_area_telphone2.requestFocus();
						return;
					}
				} else {
					result = temp;
					showDialog(INFO_ERROR);
					et_area_telphone2.requestFocus();
					return;
				}
			}
			// 联系人手机号码
			temp = RegexCust
					.required("手机号码", et_mobileNo2.getText().toString());
			if (temp.match) {
				if (RegexCust.phoneMob(et_mobileNo2.getText().toString()) == false) {
					result = new RegexResult(false, "联系人手机号码格式不对!");
					showDialog(INFO_ERROR);
					et_mobileNo2.requestFocus();
					return;
				}
			} else {
				result = temp;
				showDialog(INFO_ERROR);
				et_mobileNo2.requestFocus();
				return;
			}

			// 第三联系人
			temp = RegexCust.length("姓名", et_contactName3.getText().toString(),
					2, 30);
			if (temp.match) {
				temp = RegexCust.chinese("姓名", et_contactName3.getText()
						.toString());
				if (!temp.match) {
					result = new RegexResult(false, "联系人姓名应为汉字!");
					showDialog(INFO_ERROR);
					return;
				}
			} else {
				if (et_contactName3.getText().toString().length() == 0) {
					result = new RegexResult(false, "联系人姓名不能为空!");
				} else {
					result = new RegexResult(false, "联系人姓名字符长度不对!");
				}
				showDialog(INFO_ERROR);
				return;
			}
			// 直系亲属联系人与申请人关系
			if (str_contactRelation3 == null
					|| "请选择".equals(str_contactRelation3)) {
				result = new RegexResult(false, "请选择联系人与申请人关系!");
				showDialog(INFO_ERROR);
				return;
			}
			// 直系亲属住宅电话区号
			if (et_area_telphone3code.getText().toString() != null
					&& !et_area_telphone3code.getText().toString().equals("")) {
				if (!RegexCust.numberNatural(et_area_telphone3code.getText()
						.toString())) {
					result = new RegexResult(false, "联系人住宅电话区号格式不对!");
					showDialog(INFO_ERROR);
					et_area_telphone3code.requestFocus();
					return;
				} else {
					if (et_area_telphone3code.length() > 4) {
						result = new RegexResult(false, "联系人住宅电话区号长度不应大于4!");
						showDialog(INFO_ERROR);
						et_area_telphone3code.requestFocus();
						return;
					}
				}
			}

			// 联系人住宅电话号码
			if (et_area_telphone3.getText().toString() != null
					&& !et_area_telphone3.getText().toString().equals("")) {
				temp = RegexCust.required("住宅电话号码", et_area_telphone3.getText()
						.toString());
				if (temp.match) {
					if (RegexCust.phoneFix(et_area_telphone3.getText()
							.toString()) == false) {
						result = new RegexResult(false, "联系人住宅电话号码格式不对!");
						showDialog(INFO_ERROR);
						et_area_telphone3.requestFocus();
						return;
					}
				} else {
					result = temp;
					showDialog(INFO_ERROR);
					et_area_telphone3.requestFocus();
					return;
				}
			}
			// 联系人手机号码
			temp = RegexCust
					.required("手机号码", et_mobileNo3.getText().toString());
			if (temp.match) {
				if (RegexCust.phoneMob(et_mobileNo3.getText().toString()) == false) {
					result = new RegexResult(false, "联系人手机号码格式不对!");
					showDialog(INFO_ERROR);
					et_mobileNo3.requestFocus();
					return;
				}
			} else {
				result = temp;
				showDialog(INFO_ERROR);
				et_mobileNo3.requestFocus();
				return;
			}

			// 第四联系人
			temp = RegexCust.length("姓名", et_contactName4.getText().toString(),
					2, 30);
			if (temp.match) {
				temp = RegexCust.chinese("姓名", et_contactName4.getText()
						.toString());
				if (!temp.match) {
					result = new RegexResult(false, "联系人姓名应为汉字!");
					showDialog(INFO_ERROR);
					return;
				}
			} else {
				if (et_contactName4.getText().toString().length() == 0) {
					result = new RegexResult(false, "联系人姓名不能为空!");
				} else {
					result = new RegexResult(false, "联系人姓名字符长度不对!");
				}
				showDialog(INFO_ERROR);
				return;
			}
			// 直系亲属联系人与申请人关系
			if (str_contactRelation4 == null
					|| "请选择".equals(str_contactRelation4)) {
				result = new RegexResult(false, "请选择联系人与申请人关系!");
				showDialog(INFO_ERROR);
				return;
			}
			// 直系亲属住宅电话区号
			if (et_area_telphone4code.getText().toString() != null
					&& !et_area_telphone4code.getText().toString().equals("")) {
				if (!RegexCust.numberNatural(et_area_telphone4code.getText()
						.toString())) {
					result = new RegexResult(false, "联系人住宅电话区号格式不对!");
					showDialog(INFO_ERROR);
					et_area_telphone4code.requestFocus();
					return;
				} else {
					if (et_area_telphone4code.length() > 4) {
						result = new RegexResult(false, "联系人住宅电话区号长度不应大于4!");
						showDialog(INFO_ERROR);
						et_area_telphone4code.requestFocus();
						return;
					}
				}
			}

			// 联系人住宅电话号码
			if (et_area_telphone4.getText().toString() != null
					&& !et_area_telphone4.getText().toString().equals("")) {
				temp = RegexCust.required("住宅电话号码", et_area_telphone4.getText()
						.toString());
				if (temp.match) {
					if (RegexCust.phoneFix(et_area_telphone4.getText()
							.toString()) == false) {
						result = new RegexResult(false, "联系人住宅电话号码格式不对!");
						showDialog(INFO_ERROR);
						et_area_telphone4.requestFocus();
						return;
					}
				} else {
					result = temp;
					showDialog(INFO_ERROR);
					et_area_telphone4.requestFocus();
					return;
				}
			}
			// 联系人手机号码
			temp = RegexCust
					.required("手机号码", et_mobileNo4.getText().toString());
			if (temp.match) {
				if (RegexCust.phoneMob(et_mobileNo4.getText().toString()) == false) {
					result = new RegexResult(false, "联系人手机号码格式不对!");
					showDialog(INFO_ERROR);
					et_mobileNo4.requestFocus();
					return;
				}
			} else {
				result = temp;
				showDialog(INFO_ERROR);
				et_mobileNo4.requestFocus();
				return;
			}
			String st_mobile1 = et_mobileNo1.getText().toString();
			String st_mobile2 = et_mobileNo2.getText().toString();
			String st_mobile3 = et_mobileNo3.getText().toString();
			String st_mobile4 = et_mobileNo4.getText().toString();

			String st_contactName1 = et_contactName1.getText().toString();
			String st_contactName2 = et_contactName2.getText().toString();
			String st_contactName3 = et_contactName3.getText().toString();
			String st_contactName4 = et_contactName4.getText().toString();

			if (st_mobile1.equals(st_mobile2)) {
				result = new RegexResult(false, "第一联系人和第二联系人手机号不能相同!");
				showDialog(INFO_ERROR);
				return;
			}
			if (st_contactName1.equals(st_contactName2)) {
				result = new RegexResult(false, "第一联系人和第二联系人姓名不能相同!");
				showDialog(INFO_ERROR);
				return;
			}

			if (st_mobile1.equals(st_mobile3)) {
				result = new RegexResult(false, "第一联系人和第三联系人手机号不能相同!");
				showDialog(INFO_ERROR);
				return;
			}
			if (st_contactName1.equals(st_contactName3)) {
				result = new RegexResult(false, "第一联系人和第三联系人姓名不能相同!");
				showDialog(INFO_ERROR);
				return;
			}
			if (st_mobile1.equals(st_mobile4)) {
				result = new RegexResult(false, "第一联系人和第四联系人手机号不能相同!");
				showDialog(INFO_ERROR);
				return;
			}
			if (st_contactName1.equals(st_contactName4)) {
				result = new RegexResult(false, "第一联系人和第四联系人姓名不能相同!");
				showDialog(INFO_ERROR);
				return;
			}
			if (st_mobile2.equals(st_mobile3)) {
				result = new RegexResult(false, "第二联系人和第三联系人手机号不能相同!");
				showDialog(INFO_ERROR);
				return;
			}
			if (st_contactName2.equals(st_contactName3)) {
				result = new RegexResult(false, "第二联系人和第三联系人姓名不能相同!");
				showDialog(INFO_ERROR);
				return;
			}

			if (st_mobile2.equals(st_mobile4)) {
				result = new RegexResult(false, "第二联系人和第四联系人手机号不能相同!");
				showDialog(INFO_ERROR);
				return;
			}
			if (st_contactName2.equals(st_contactName4)) {
				result = new RegexResult(false, "第二联系人和第四联系人姓名不能相同!");
				showDialog(INFO_ERROR);
				return;
			}
			if (st_mobile3.equals(st_mobile4)) {
				result = new RegexResult(false, "第三联系人和第四联系人手机号不能相同!");
				showDialog(INFO_ERROR);
				return;
			}
			if (st_contactName3.equals(st_contactName4)) {
				result = new RegexResult(false, "第三联系人和第四联系人姓名不能相同!");
				showDialog(INFO_ERROR);
				return;
			}

			//提取response值，做非空判断
			LoginMessage loginResult = null;
			if(Contents.response != null)
				loginResult = Contents.response.getResult();
			// 加姓名电话 冲突 判断
			if (st_contactName1.equals(((loginResult == null)? "" : loginResult
					.getCipNamecn()))) {

				Toast.makeText(getApplicationContext(), "联系人不能与贷款人相同",
						Toast.LENGTH_LONG).show();
				return;
			}
			if (st_contactName2.equals(((loginResult == null) ? "" : loginResult
					.getCipNamecn()))) {

				Toast.makeText(getApplicationContext(), "联系人不能与贷款人相同",
						Toast.LENGTH_LONG).show();
				return;
			}
			if (st_contactName3.equals(((loginResult == null) ? "" : loginResult
					.getCipNamecn()))) {

				Toast.makeText(getApplicationContext(), "联系人不能与贷款人相同",
						Toast.LENGTH_LONG).show();
				return;
			}
			if (st_contactName4.equals(((loginResult == null) ? "" : loginResult
					.getCipNamecn()))) {

				Toast.makeText(getApplicationContext(), "联系人不能与贷款人相同",
						Toast.LENGTH_LONG).show();
				return;
			}
			//tel
			if (st_mobile1.equals(application.getBasicInfo().getMobilePhone())) {

				Toast.makeText(getApplicationContext(), "联系人手机号不能与贷款人手机号相同",
						Toast.LENGTH_LONG).show();
				return;
			}
			if (st_mobile2.equals(application.getBasicInfo().getMobilePhone())) {

				Toast.makeText(getApplicationContext(), "联系人手机号不能与贷款人手机号相同",
						Toast.LENGTH_LONG).show();
				return;
			}
			if (st_mobile3.equals(application.getBasicInfo().getMobilePhone())) {

				Toast.makeText(getApplicationContext(), "联系人手机号不能与贷款人手机号相同",
						Toast.LENGTH_LONG).show();
				return;
			}
			if (st_mobile4.equals(application.getBasicInfo().getMobilePhone())) {

				Toast.makeText(getApplicationContext(), "联系人手机号不能与贷款人手机号相同",
						Toast.LENGTH_LONG).show();
				return;
			}
			if ("配偶".equals(str_contactRelation1)
					&& "配偶".equals(str_contactRelation2)
					|| "配偶".equals(str_contactRelation1)
					&& "配偶".equals(str_contactRelation3)
					|| "配偶".equals(str_contactRelation1)
					&& "配偶".equals(str_contactRelation4)
					|| "配偶".equals(str_contactRelation2)
					&& "配偶".equals(str_contactRelation3)
					|| "配偶".equals(str_contactRelation2)
					&& "配偶".equals(str_contactRelation4)
					|| "配偶".equals(str_contactRelation3)
					&& "配偶".equals(str_contactRelation4)) {
				result = new RegexResult(false, "只能选择一个配偶！");
				showDialog(INFO_ERROR);
				return;
			}

			// 写入内存
			application.getLinkManInfo().setContactName1(
					et_contactName1.getText().toString());
			application.getLinkManInfo().setArea_telphone1(
					et_area_telphone1.getText().toString());
			application.getLinkManInfo().setArea_telphone1zone(
					et_area_telphone1code.getText().toString());
			application.getLinkManInfo().setMobileNo1(
					et_mobileNo1.getText().toString());

			application.getLinkManInfo().setContactName2(
					et_contactName2.getText().toString());
			application.getLinkManInfo().setArea_telphone2(
					et_area_telphone2.getText().toString());
			application.getLinkManInfo().setArea_telphone2zone(
					et_area_telphone2code.getText().toString());
			application.getLinkManInfo().setMobileNo2(
					et_mobileNo2.getText().toString());

			application.getLinkManInfo().setContactName3(
					et_contactName3.getText().toString());
			application.getLinkManInfo().setArea_telphone3(
					et_area_telphone3.getText().toString());
			application.getLinkManInfo().setArea_telphone3zone(
					et_area_telphone3code.getText().toString());
			application.getLinkManInfo().setMobileNo3(
					et_mobileNo3.getText().toString());

			application.getLinkManInfo().setContactName4(
					et_contactName4.getText().toString());
			application.getLinkManInfo().setArea_telphone4(
					et_area_telphone4.getText().toString());
			application.getLinkManInfo().setArea_telphone4zone(
					et_area_telphone4code.getText().toString());
			application.getLinkManInfo().setMobileNo4(
					et_mobileNo4.getText().toString());

			shareclear();
			shareinit();
		}
		super.onClick(v);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		if (parent == sp_contactRelation1) {
			str_contactRelation1 = (String) sp_contactRelation1
					.getItemAtPosition(position);
			if ("请选择".equals(str_contactRelation1)) {
				sp_contactRelation1
						.setBackgroundResource(R.drawable.comm_spinner_selecter);
			} else {
				sp_contactRelation1
						.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
			}
			sp_contactRelation1.setPadding(15, 0, 0, 0);
			application.getLinkManInfo().setContactRelation1(
					str_contactRelation1);
			application.getLinkManInfo().setContactRelation1Code(
					apply_relationshipCode[position]);

		}
		if (parent == sp_contactRelation2) {
			str_contactRelation2 = (String) sp_contactRelation2
					.getItemAtPosition(position);
			if ("请选择".equals(str_contactRelation2)) {
				sp_contactRelation2
						.setBackgroundResource(R.drawable.comm_spinner_selecter);
			} else {
				sp_contactRelation2
						.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
			}
			sp_contactRelation2.setPadding(15, 0, 0, 0);
			application.getLinkManInfo().setContactRelation2(
					str_contactRelation2);
			application.getLinkManInfo().setContactRelation2Code(
					apply_relationship2Code[position]);

		}
		if (parent == sp_contactRelation3) {
			str_contactRelation3 = (String) sp_contactRelation3
					.getItemAtPosition(position);
			if ("请选择".equals(str_contactRelation2)) {
				sp_contactRelation3
						.setBackgroundResource(R.drawable.comm_spinner_selecter);
			} else {
				sp_contactRelation3
						.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
			}
			sp_contactRelation3.setPadding(15, 0, 0, 0);
			application.getLinkManInfo().setContactRelation3(
					str_contactRelation3);
			application.getLinkManInfo().setContactRelation3Code(
					apply_relationship2Code[position]);

		}
		if (parent == sp_contactRelation4) {
			str_contactRelation4 = (String) sp_contactRelation4
					.getItemAtPosition(position);
			if ("请选择".equals(str_contactRelation4)) {
				sp_contactRelation4
						.setBackgroundResource(R.drawable.comm_spinner_selecter);
			} else {
				sp_contactRelation4
						.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
			}
			sp_contactRelation4.setPadding(15, 0, 0, 0);
			application.getLinkManInfo().setContactRelation4(
					str_contactRelation4);
			application.getLinkManInfo().setContactRelation4Code(
					apply_relationship2Code[position]);
		}
		super.onItemSelected(parent, view, position, id);
	}
}