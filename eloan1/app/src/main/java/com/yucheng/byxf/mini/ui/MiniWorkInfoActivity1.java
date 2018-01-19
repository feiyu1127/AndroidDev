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
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.yucheng.apfx.utl.RegexResult;
import com.yucheng.byxf.comm.MyApplication;
import com.yucheng.byxf.comm.ProfessionInfo;
import com.yucheng.byxf.comm.ScreenManager;
import com.yucheng.byxf.mini.domain.MiniPersonInfo;
import com.yucheng.byxf.mini.message.CustInfoResponse;
import com.yucheng.byxf.mini.utils.CommonUtil;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.RegexCust;
import com.yucheng.byxf.mini.utils.ViewTools;
import com.yucheng.byxf.service.LoanPayInfo;

/**
 * 轻松付与轻松贷的职业信息
 * 
 * @author danny
 * 
 */
public class MiniWorkInfoActivity1 extends BaseActivity implements
		OnItemSelectedListener {
	private static final String _Tag = "ProfessionInfoEasyActivity";
	private FrameLayout fl_title;// 标题
	private FrameLayout fl_title2;// 标题2
	private RelativeLayout rl_body;// 标题以外的部分
	// 下一步
	private Button next_button;

	// 现单位全称
	private EditText now_dp_name_value;
	// 任职部门
	private EditText rz_dp_value;
	// 本单位工作年数
	private EditText year_value;
	// 总工作年限
	private EditText total_year_value;
	// 单位地址
	private EditText dp_Place;
	// 区号
	private EditText area_num_value;
	// 电话号码
	private EditText tel_num_value;
	// 分机号
	private EditText extension_num_value;
	// 单位性质
	private Spinner dp_nature_group;
	private String save_dp_nature;
	private String[] dp_natures = { "请选择", "国家机关/事业单位", "国有企业", "集体企业",
			"中外合资/中外合作/外商独资", "股份制企业", "私营企业", "其它" };
	// 企业规模
	private Spinner dp_scale_group;
	private String save_dp_scale;
	private String[] dp_scales = { "请选择", "20人以下", "50人（含）以下", "50-100人",
			"500人以上" };
	// 行业性质
	// private Boolean changeedGroup = false;
	private Spinner dp_trade_nature_group;
	private String save_dp_trade_nature;
	private String[] dp_trade_natures = { "请选择", "个体工商户", "批发/零售", "租赁/服务业",
			"制造业", "房地产", "建筑业", "酒店/旅游/餐饮", "交通运输/仓储/邮政业", "传媒/体育/娱乐",
			"专业事务所", "信息传输/计算机服务/软件业", "生物/医药", "金融", "公共事业", "石油/石化", "教育",
			"医疗卫生", "科研院所", "水利/环境/公共设施管理", "烟草", "军事机构", "社会团体", "政府机构/公检法",
			"自由职业", "其它" };

	// 职业
	private Spinner now_progression_group;
	private String save_now_progression;
	private String[] now_progressions = { "请选择", "国有大中型企业或业务合作单位",
			"政府公务员及事业单位人员/教师/注册会计师/医师/律师等专业人员", "金融/电力/邮电/通讯/烟草/能源等资源垄断行业员",
			"中小型企业/有固定职业", "个体工商户", "无固定职业", "无" };
	// 现任职位
	private Spinner now_role_group;
	private String save_now_role;
	private String[] now_roles = { "请选择", "高级管理人员", "一般管理人员", "一般正式员工",
			"非正式员工", "其它" };
	// 个人工资月收入
	// private EditText money_month_value;
	// 申请人年收入
	private EditText money_year_value;
	// 邮寄地址
	private RadioGroup email_address_radio;
	// 信息有误
	private final int INFO_ERROR = 0;
	// 验证结果
	private RegexResult result;
	private Intent intent;
	private String confirm_info;// 存储确认页过来的intent

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
		setContentView(R.layout.profession_info_easy);
		ScreenManager.getScreenManager().pushActivity(this);
		intent = getIntent();
		iv_menu_common = (ImageView) findViewById(R.id.iv_menu_common);
		iv_menu_common.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		confirm_info = intent.getStringExtra("confirm_info");
		rl_body = (RelativeLayout) findViewById(R.id.profession_info_easy_rl_body);
		rl_body.setOnClickListener(new NextButtonOnClickListener());
		now_dp_name_value = (EditText) this
				.findViewById(R.id.now_dp_name_value);
		now_dp_name_value.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String nowDpName = now_dp_name_value.getText().toString()
						.trim();
				MiniPersonInfo.nowDpName = nowDpName;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		rz_dp_value = (EditText) this.findViewById(R.id.rz_dp_value);
		rz_dp_value.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String rzDp = rz_dp_value.getText().toString().trim();
				MiniPersonInfo.rzDp = rzDp;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		year_value = (EditText) this.findViewById(R.id.year_value);
		year_value.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String professionYear = year_value.getText().toString().trim();
				MiniPersonInfo.professionYear = professionYear;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		total_year_value = (EditText) this.findViewById(R.id.total_year_value);
		total_year_value.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String totalProfessionYear = total_year_value.getText()
						.toString().trim();
				MiniPersonInfo.totalProfessionYear = totalProfessionYear;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		dp_Place = (EditText) this.findViewById(R.id.province_value);
		dp_Place.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String dpPlace = dp_Place.getText().toString().trim();
				MiniPersonInfo.dpPlace = dpPlace;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		area_num_value = (EditText) this.findViewById(R.id.area_num_value);
		area_num_value.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String areaNum = area_num_value.getText().toString().trim();
				MiniPersonInfo.areaNum = areaNum;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		tel_num_value = (EditText) this.findViewById(R.id.tel_num_value);
		tel_num_value.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String telNum = tel_num_value.getText().toString().trim();
				MiniPersonInfo.telNum = telNum;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		extension_num_value = (EditText) this
				.findViewById(R.id.extension_num_value);
		extension_num_value.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String extensionNum = extension_num_value.getText().toString()
						.trim();
				MiniPersonInfo.extensionNum = extensionNum;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		dp_nature_group = (Spinner) this.findViewById(R.id.dp_nature_group);
		dp_nature_group.setOnItemSelectedListener(this);
		CommonUtil.setSpinner(this, dp_natures, dp_nature_group);
		dp_trade_nature_group = (Spinner) this
				.findViewById(R.id.dp_trade_nature_group);
		dp_trade_nature_group.setOnItemSelectedListener(this);
		CommonUtil.setSpinner(this, dp_trade_natures, dp_trade_nature_group);

		// zhiye
		// now_profession_text = (TextView)
		// findViewById(R.id.now_profession_text);

		next_button = (Button) findViewById(R.id.next_button);
		next_button.setOnClickListener(new NextButtonOnClickListener());

		confirm_info = getIntent().getStringExtra("confirm_info");
		if ("confirmActivity".equals(confirm_info)
				|| "loanConfirmActivity".equals(confirm_info)) {
			next_button.setText("确认");
		}

		now_role_group = (Spinner) this.findViewById(R.id.now_role_group);
		now_role_group.setOnItemSelectedListener(this);
		CommonUtil.setSpinner(this, now_roles, now_role_group);
		money_year_value = (EditText) this.findViewById(R.id.money_year_value);
		money_year_value.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String money_Year = money_year_value.getText().toString()
						.trim();
				MiniPersonInfo.money_Year = money_Year;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		email_address_radio = (RadioGroup) this
				.findViewById(R.id.email_address_radio);
		email_address_radio
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup arg0, int arg1) {
						switch (arg0.getCheckedRadioButtonId()) {
						case R.id.email_address_dwell:// 邮寄地址为现居住地址
							MiniPersonInfo.email_address = "现居住地址";
							break;
						case R.id.email_address_unit:// 邮寄地址为单位地址
							MiniPersonInfo.email_address = "单位地址";
							break;
						}

					}
				});

		if ("".equals(MiniPersonInfo.nowDpName)
				|| null == MiniPersonInfo.nowDpName) {
			if (Contents.custInfoResponseResult != null
					&& Contents.custInfoResponseResult.getCode() == 0) {
				CustInfoResponse response = Contents.custInfoResponseResult
						.getResult();
				MiniPersonInfo.nowDpName = response.getIndivRelEmp();
				MiniPersonInfo.rzDp = response.getIndivBranch();
				MiniPersonInfo.professionYear = response.getIndivEmpYrs();
				MiniPersonInfo.totalProfessionYear = response.getIndivWorkYrs();
				MiniPersonInfo.dpPlace = response.getIndivEmp();
				if (!"".equals(response.getIndivEmpType())
						&& response.getIndivEmpType() != null) {
					if (response.getIndivEmpType().equals("A")) {
						MiniPersonInfo.dpNature = dp_natures[1];
					} else if (response.getIndivEmpType().equals("B")) {
						MiniPersonInfo.dpNature = dp_natures[2];
					} else if (response.getIndivEmpType().equals("C")) {
						MiniPersonInfo.dpNature = dp_natures[3];
					} else if (response.getIndivEmpType().equals("D")) {
						MiniPersonInfo.dpNature = dp_natures[4];
					} else if (response.getIndivEmpType().equals("E")) {
						MiniPersonInfo.dpNature = dp_natures[5];
					} else if (response.getIndivEmpType().equals("F")) {
						MiniPersonInfo.dpNature = dp_natures[6];
					} else if (response.getIndivEmpType().equals("G")) {
						MiniPersonInfo.dpNature = dp_natures[7];
					} else if (response.getIndivEmpType().equals("H")) {
						MiniPersonInfo.dpNature = dp_natures[8];
					}
				}
				if (!"".equals(response.getIndivIndtryPaper())
						&& null != response.getIndivIndtryPaper()) {
					if (response.getIndivIndtryPaper().equals("01")) {
						MiniPersonInfo.tradeNature = dp_trade_natures[1];
					} else if (response.getIndivIndtryPaper().equals("02")) {
						MiniPersonInfo.tradeNature = dp_trade_natures[2];
					} else if (response.getIndivIndtryPaper().equals("03")) {
						MiniPersonInfo.tradeNature = dp_trade_natures[3];
					} else if (response.getIndivIndtryPaper().equals("04")) {
						MiniPersonInfo.tradeNature = dp_trade_natures[4];
					} else if (response.getIndivIndtryPaper().equals("05")) {
						MiniPersonInfo.tradeNature = dp_trade_natures[5];
					} else if (response.getIndivIndtryPaper().equals("06")) {
						MiniPersonInfo.tradeNature = dp_trade_natures[6];
					} else if (response.getIndivIndtryPaper().equals("07")) {
						MiniPersonInfo.tradeNature = dp_trade_natures[7];
					} else if (response.getIndivIndtryPaper().equals("08")) {
						MiniPersonInfo.tradeNature = dp_trade_natures[8];
					} else if (response.getIndivIndtryPaper().equals("09")) {
						MiniPersonInfo.tradeNature = dp_trade_natures[9];
					} else if (response.getIndivIndtryPaper().equals("10")) {
						MiniPersonInfo.tradeNature = dp_trade_natures[10];
					} else if (response.getIndivIndtryPaper().equals("11")) {
						MiniPersonInfo.tradeNature = dp_trade_natures[11];
					} else if (response.getIndivIndtryPaper().equals("12")) {
						MiniPersonInfo.tradeNature = dp_trade_natures[12];
					} else if (response.getIndivIndtryPaper().equals("13")) {
						MiniPersonInfo.tradeNature = dp_trade_natures[13];
					} else if (response.getIndivIndtryPaper().equals("14")) {
						MiniPersonInfo.tradeNature = dp_trade_natures[14];
					} else if (response.getIndivIndtryPaper().equals("15")) {
						MiniPersonInfo.tradeNature = dp_trade_natures[15];
					} else if (response.getIndivIndtryPaper().equals("16")) {
						MiniPersonInfo.tradeNature = dp_trade_natures[16];
					} else if (response.getIndivIndtryPaper().equals("17")) {
						MiniPersonInfo.tradeNature = dp_trade_natures[17];
					} else if (response.getIndivIndtryPaper().equals("18")) {
						MiniPersonInfo.tradeNature = dp_trade_natures[18];
					} else if (response.getIndivIndtryPaper().equals("19")) {
						MiniPersonInfo.tradeNature = dp_trade_natures[19];
					} else if (response.getIndivIndtryPaper().equals("20")) {
						MiniPersonInfo.tradeNature = dp_trade_natures[20];
					} else if (response.getIndivIndtryPaper().equals("21")) {
						MiniPersonInfo.tradeNature = dp_trade_natures[21];
					} else if (response.getIndivIndtryPaper().equals("22")) {
						MiniPersonInfo.tradeNature = dp_trade_natures[22];
					} else if (response.getIndivIndtryPaper().equals("23")) {
						MiniPersonInfo.tradeNature = dp_trade_natures[23];
					} else if (response.getIndivIndtryPaper().equals("24")) {
						MiniPersonInfo.tradeNature = dp_trade_natures[24];
					} else if (response.getIndivIndtryPaper().equals("25")) {
						MiniPersonInfo.tradeNature = dp_trade_natures[25];
					} else if (response.getIndivIndtryPaper().equals("26")) {
						MiniPersonInfo.tradeNature = dp_trade_natures[26];
					} else if (response.getIndivIndtryPaper().equals("99")) {
						MiniPersonInfo.tradeNature = dp_trade_natures[27];
					}
				}
				if (!"".equals(response.getIndivPosition())
						&& null != response.getIndivPosition()) {
					if (response.getIndivPosition().equals("1")) {
						MiniPersonInfo.nowRole = now_roles[1];
					} else if (response.getIndivPosition().equals("2")) {
						MiniPersonInfo.nowRole = now_roles[2];
					} else if (response.getIndivPosition().equals("3")) {
						MiniPersonInfo.nowRole = now_roles[3];
					} else if (response.getIndivPosition().equals("4")) {
						MiniPersonInfo.nowRole = now_roles[4];
					} else if (response.getIndivPosition().equals("9")) {
						MiniPersonInfo.nowRole = now_roles[5];
					}
				}

				// 个人工资年收入
			}
		}

	}

	@Override
	protected void onResume() {
//		if (!MiniPersonInfo.nowDpName.equals("")) {
			now_dp_name_value.setText(MiniPersonInfo.nowDpName);
			rz_dp_value.setText(MiniPersonInfo.rzDp);
			year_value.setText(MiniPersonInfo.professionYear);
			total_year_value.setText(MiniPersonInfo.totalProfessionYear);
			dp_Place.setText(MiniPersonInfo.dpPlace);
			area_num_value.setText(MiniPersonInfo.areaNum);
			tel_num_value.setText(MiniPersonInfo.telNum);
			extension_num_value.setText(MiniPersonInfo.extensionNum);

			// 邮寄地址
			RadioButton radioButton0 = (RadioButton) email_address_radio
					.getChildAt(0);
			RadioButton radioButton1 = (RadioButton) email_address_radio
					.getChildAt(1);
			for (int i = 0; i < email_address_radio.getChildCount(); i++) {
				RadioButton radioButton = (RadioButton) email_address_radio
						.getChildAt(0);
				if (radioButton.getText().equals("现居住地址")) {
					radioButton0.setChecked(true);
				} else if (radioButton.getText().equals("单位地址")) {
					radioButton1.setChecked(true);
				}
			}
			// 单位性质
			if (!"".equals(MiniPersonInfo.dpNature)) {
				for (int i = 0; i < dp_nature_group.getCount(); i++) {
					String item = dp_natures[i];
					if (item.equals(MiniPersonInfo.dpNature)) {
						dp_nature_group.setSelection(i);
						save_dp_nature = MiniPersonInfo.dpNature;
						if (!"请选择".equals(save_dp_nature)) {
							dp_nature_group
									.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
							dp_nature_group.setPadding(15, 0, 0, 0);
						}
					}
				}
			}
			// 行业性质
			if (!"".equals(MiniPersonInfo.tradeNature)) {// 行业性质
				for (int i = 0; i < dp_trade_nature_group.getCount(); i++) {
					String item = dp_trade_natures[i];
					if (item.equals(MiniPersonInfo.tradeNature)) {
						dp_trade_nature_group.setSelection(i);
					}
				}
			}
			// 现任职位
			if (!"".equals(MiniPersonInfo.nowRole)) {
				for (int i = 0; i < now_role_group.getCount(); i++) {
					String item = now_roles[i];
					if (item.equals(MiniPersonInfo.nowRole)) {
						now_role_group.setSelection(i);
					}
				}
			}
			money_year_value.setText(MiniPersonInfo.money_Year);
//		}
		super.onResume();
	}

	// 下一步
	private class NextButtonOnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {

			if (v == next_button) {

				if (Contents.VERIFT) {
				  RegexResult temp = null;
					// 现单位全称
					String nowDpName = now_dp_name_value.getText().toString()
							.trim();
					temp = RegexCust.required("现单位全称", nowDpName);
					if (temp.match) {
						if (!RegexCust.lengthMax(nowDpName, 60)) {
							result = new RegexResult(false, "现单位全称字符长度过长!");
							showDialog(INFO_ERROR);
							now_dp_name_value.requestFocus();
							return;
						}
					} else {
						result = temp;
						showDialog(INFO_ERROR);
						now_dp_name_value.requestFocus();
						return;
					}
					// 任职部门
					String rzDp = rz_dp_value.getText().toString().trim();
					if (!RegexCust.lengthMax(nowDpName, 60)) {
						result = new RegexResult(false, "任职部门字符长度过长!");
						showDialog(INFO_ERROR);
						rz_dp_value.requestFocus();
						return;
					}
					// 本单位工作年限
					String year = year_value.getText().toString().trim();
					temp = RegexCust.required("本单位工作年限", year);
					if (temp.match) {
						if (!RegexCust.lengthMax(year, 10)) {
							result = new RegexResult(false, "本单位工作年限字符长度过长!");
							showDialog(INFO_ERROR);
							year_value.requestFocus();
							return;
						}
					} else {
						result = temp;
						showDialog(INFO_ERROR);
						year_value.requestFocus();
						return;
					}
					// 总工作年限
					String totalYear = total_year_value.getText().toString()
							.trim();
					temp = RegexCust.required("总工作年限", year);
					if (temp.match) {
						if (!RegexCust.lengthMax(totalYear, 10)) {
							result = new RegexResult(false, "总工作年限字符长度过长!");
							showDialog(INFO_ERROR);
							total_year_value.requestFocus();
							return;
						}
					} else {
						result = temp;
						showDialog(INFO_ERROR);
						total_year_value.requestFocus();
						return;
					}
					// 单位地址
					String dpPlace = dp_Place.getText().toString().trim();
					temp = RegexCust.required("单位地址", dpPlace);
					if (temp.match) {
						if (!RegexCust.lengthMax(dpPlace, 200)) {
							result = new RegexResult(false, "单位地址字符长度过长!");
							showDialog(INFO_ERROR);
							dp_Place.requestFocus();
							return;
						}
					} else {
						result = temp;
						showDialog(INFO_ERROR);
						dp_Place.requestFocus();
						return;
					}
					// 单位电话区号
					String areaNum = area_num_value.getText().toString().trim();
					temp = RegexCust.required("单位电话区号", areaNum);
					if (temp.match) {
						if (!RegexCust.lengthMax(areaNum, 5)) {
							result = new RegexResult(false, "单位电话区号格式有误!");
							showDialog(INFO_ERROR);
							area_num_value.requestFocus();
							return;
						}
					} else {
						result = temp;
						showDialog(INFO_ERROR);
						dp_Place.requestFocus();
						return;
					}
					
					// 单位电话号码
					String telNum = tel_num_value.getText().toString().trim();
					temp = RegexCust.required("单位电话号码", telNum);
					if (temp.match) {
						if (!RegexCust.lengthMax(telNum, 20)) {
							result = new RegexResult(false, "单位电话号码格式有误!");
							showDialog(INFO_ERROR);
							tel_num_value.requestFocus();
							return;
						}
					} else {
						result = temp;
						showDialog(INFO_ERROR);
						tel_num_value.requestFocus();
						return;
					}
					// 单位电话分机号
					String extensionNum = extension_num_value.getText()
							.toString().trim();
					if (!RegexCust.lengthMax(extensionNum, 10)) {
						result = new RegexResult(false, "单位电话分机号格式有误!");
						showDialog(INFO_ERROR);
						extension_num_value.requestFocus();
						return;
					}
					// 单位性质
					if (save_dp_nature == null || "请选择".equals(save_dp_nature)) {
						result = new RegexResult(false, "请选择单位性质!");
						showDialog(INFO_ERROR);
						dp_nature_group.requestFocus();
						return;
					}
					// 行业性质
					if (save_dp_trade_nature == null
							|| "请选择".equals(save_dp_trade_nature)) {
						result = new RegexResult(false, "请选择行业性质!");
						showDialog(INFO_ERROR);
						dp_trade_nature_group.requestFocus();
						return;
					}

					// 现任职位
					if (save_now_role == null || "请选择".equals(save_now_role)) {
						result = new RegexResult(false, "请选择现任职位!");
						showDialog(INFO_ERROR);
						now_role_group.requestFocus();
						return;
					}
					 // 月工资收入
//					 String moneyMonth =
//					 money_month_value.getText().toString()
//					 .trim();
//					 temp = RegexCust.required("月工资收入", moneyMonth);
//					 if (temp.match) {
//					 if (!RegexCust.maxlength(moneyMonth, 16)) {
//					 result = new Result(false, "月工资收入格式有误!");
//					 showDialog(INFO_ERROR);
//					 money_month_value.requestFocus();
//					 return;
//					 }
//					 } else {
//					 result = temp;
//					 showDialog(INFO_ERROR);
//					 money_month_value.requestFocus();
//					 return;
//					 }

					// 年工资收入
					String moneyYear = money_year_value.getText().toString()
							.trim();
					temp = RegexCust.required("年工资收入", moneyYear);
					if (temp.match) {
						if (!RegexCust.lengthMax(moneyYear, 16)) {
							result = new RegexResult(false, "年工资收入格式有误!");
							showDialog(INFO_ERROR);
							money_year_value.requestFocus();
							return;
						}
					} else {
						result = temp;
						showDialog(INFO_ERROR);
						money_year_value.requestFocus();
						return;
					}
					MiniPersonInfo.nowDpName = nowDpName;
					MiniPersonInfo.rzDp = rzDp;
					MiniPersonInfo.professionYear = year;
					MiniPersonInfo.totalProfessionYear = totalYear;
					MiniPersonInfo.dpPlace = dpPlace;
					MiniPersonInfo.areaNum = areaNum;
					MiniPersonInfo.telNum = telNum;
					MiniPersonInfo.extensionNum = extensionNum;
					if (save_dp_nature != null && !"请选择".equals(save_dp_nature)) {
						MiniPersonInfo.dpNature = save_dp_nature.toString();
					}
					if (save_dp_trade_nature != null
							&& !"请选择".equals(save_dp_trade_nature)) {// 行业性质
						MiniPersonInfo.tradeNature = save_dp_trade_nature
								.toString();
					}

					// LoanPayInfo.professionInfo.setTradeNature(tradeNatureValue);
					if (save_now_role != null && !"请选择".equals(save_now_role)) {
						MiniPersonInfo.nowRole = save_now_role.toString();
					}
					// LoanPayInfo.professionInfo.setMoneyMonth(moneyMonth);
					MiniPersonInfo.money_Year = moneyYear;
				}
				Intent intent = getIntent();
				intent.setClass(MiniWorkInfoActivity1.this,
						MiniWorkInfoActivity2.class);
				startActivity(intent);
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
					MiniWorkInfoActivity1.this, sb.toString());
			break;

		default:
			break;
		}
		return super.onCreateDialog(id, args);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		if (isSpinner) {
			if (parent.equals(dp_nature_group)) {// 单位性质
				save_dp_nature = (String) dp_nature_group
						.getItemAtPosition(position);
				if ("请选择".equals(save_dp_nature)) {
					dp_nature_group
							.setBackgroundResource(R.drawable.comm_spinner_selecter);
				} else {
					dp_nature_group
							.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
				}
				dp_nature_group.setPadding(15, 0, 0, 0);
				MiniPersonInfo.dpNature = save_dp_nature;
			}
			if (parent.equals(dp_trade_nature_group)) {// 行业性质
				save_dp_trade_nature = (String) dp_trade_nature_group
						.getItemAtPosition(position);
				if ("请选择".equals(save_dp_trade_nature)) {
					dp_trade_nature_group
							.setBackgroundResource(R.drawable.comm_spinner_selecter);
				} else {
					dp_trade_nature_group
							.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
				}
				dp_trade_nature_group.setPadding(15, 0, 0, 0);
				MiniPersonInfo.tradeNature = save_dp_trade_nature;
			}
			if (parent.equals(now_role_group)) {// 现任职位
				save_now_role = (String) now_role_group
						.getItemAtPosition(position);
				if ("请选择".equals(save_now_role)) {
					now_role_group
							.setBackgroundResource(R.drawable.comm_spinner_selecter);
				} else {
					now_role_group
							.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
				}
				now_role_group.setPadding(15, 0, 0, 0);
				MiniPersonInfo.nowRole = save_now_role;
			}
		}
		isSpinner = true;
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

}