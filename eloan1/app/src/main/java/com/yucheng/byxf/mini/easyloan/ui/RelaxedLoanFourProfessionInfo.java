package com.yucheng.byxf.mini.easyloan.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.yucheng.apfx.utl.RegexResult;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.CommonUtil;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.RegexCust;

/**
 * @author
 */
public class RelaxedLoanFourProfessionInfo extends BaseRelaxedLoanActivity {
	// 信息有误
	private final int INFO_ERROR = 0;
	private Button easy_loan_profession_info_next_button;
	private EditText et_com_name;
	private EditText et_dpment_name;
	private EditText et_worktimes;
	private EditText et_total_worktimes;
	private EditText et_com_address;
	private EditText et_mail_code;

	private EditText et_com_zone_code;
	private EditText et_com_tel_code;
	private EditText et_com_extension_code;
	private EditText et_salary;

	private Spinner et_com_scale;
	private Spinner et_com_properties;
	private Spinner et_trade_properties;
	private Spinner et_com_position;

	private String str_com_scale;
	private String str_com_properties;
	private String str_trade_properties;
	private String str_com_position;
	// 企业规模；
	private String[] dp_scales = { "请选择", "20人以下", "50人（含）以下", "50-100人", "100-500人"
			,"500人以上" };
	private String[] dp_scalesCode = { "", "0", "1", "2", "3","4" };
	// 单位性质
	private String[] dp_natures = { "请选择", "国家机关/事业单位", "国有企业", "集体企业",
			"中外合资/中外合作/外商独资", "股份制企业", "私营企业", "其它" };
	private String[] dp_naturesCode = { "", "A", "B", "C", "D", "E", "F", "G" };
	// 行业性质
	private String[] dp_trade_natures = { "请选择", "个体工商户", "批发/零售", "租赁/服务业",
			"制造业", "房地产", "建筑业", "酒店/旅游/餐饮", "交通运输/仓储/邮政业", "传媒/体育/娱乐",
			"专业事务所", "信息传输/计算机服务/软件业", "生物/医药", "金融", "公共事业", "石油/石化", "教育",
			"医疗卫生", "科研院所", "水利/环境/公共设施管理", "烟草", "军事机构", "社会团体", "政府机构/公检法",
			"自由职业", "其它" };
	private String[] dp_trade_naturesCode = { "", "01", "02", "03", "04", "05",
			"06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16",
			"17", "18", "19", "20", "21", "22", "23", "24", "99" };
	// 所在职位；
	private String[] now_roles = { "请选择", "高级管理人员", "一般管理人员", "一般正式员工",
			"非正式员工", "其它" };
	private String[] now_rolesCode = { "", "1", "2", "3", "4", "9" };
	// 验证结果
	private RegexResult result;

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		setContentView(R.layout.easy_loan_four_profession_info);
		super.init();
		
		editinit();
		
		easy_loan_head.setText("职业信息");
		easy_loan_profession_info_next_button = (Button) findViewById(R.id.easy_loan_profession_info_next_button);
		easy_loan_profession_info_next_button.setOnClickListener(this);
		// 初始化控件；
		et_com_name = (EditText) findViewById(R.id.et_com_name);
		et_dpment_name = (EditText) findViewById(R.id.et_dpment_name);
		et_worktimes = (EditText) findViewById(R.id.et_worktimes);
		et_total_worktimes = (EditText) findViewById(R.id.et_total_worktimes);
		et_com_address = (EditText) findViewById(R.id.et_com_address);
		et_mail_code = (EditText) findViewById(R.id.et_mail_code);

		et_com_zone_code = (EditText) findViewById(R.id.et_com_zone_code);
		et_com_tel_code = (EditText) findViewById(R.id.et_com_tel_code);
		et_com_extension_code = (EditText) findViewById(R.id.et_com_extension_code);
		et_salary = (EditText) findViewById(R.id.et_salary);

		et_com_scale = (Spinner) findViewById(R.id.et_com_scale);
		et_com_properties = (Spinner) findViewById(R.id.et_com_properties);
		et_trade_properties = (Spinner) findViewById(R.id.et_trade_properties);
		et_com_position = (Spinner) findViewById(R.id.et_com_position);

		et_com_scale.setOnItemSelectedListener(this);
		CommonUtil.setSpinner(this, dp_scales, et_com_scale);
		et_com_properties.setOnItemSelectedListener(this);
		CommonUtil.setSpinner(this, dp_natures, et_com_properties);

		et_trade_properties.setOnItemSelectedListener(this);
		CommonUtil.setSpinner(this, dp_trade_natures, et_trade_properties);

		et_com_position.setOnItemSelectedListener(this);
		CommonUtil.setSpinner(this, now_roles, et_com_position);

		initOldClientInfo();
		
		setData();
	}
	
	private void shareclear(){
//		SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
//				this);
		if (preferencesUtils.hasData("et_com_name")) {
			preferencesUtils.removeData("et_com_name");
			preferencesUtils.removeData("et_dpment_name");
			preferencesUtils.removeData("et_worktimes");
			preferencesUtils.removeData("et_total_worktimes");
			preferencesUtils.removeData("et_com_address");
			preferencesUtils.removeData("et_mail_code");
			preferencesUtils.removeData("et_com_zone_code");
			preferencesUtils.removeData("et_com_tel_code");
			preferencesUtils.removeData("et_com_extension_code");
			preferencesUtils.removeData("et_salary");
			preferencesUtils.removeData("et_com_scale");
			preferencesUtils.removeData("et_com_properties");
			preferencesUtils.removeData("et_trade_properties");
			preferencesUtils.removeData("et_com_position");
			}
		}
	
	
	private void shareinit() {
//		SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
//				this);
		preferencesUtils.setData("et_com_name", application.getProfessionInfo().getCompanyName());
		preferencesUtils.setData("et_dpment_name", application.getProfessionInfo()
				.getRzDepartment());
		preferencesUtils.setData("et_worktimes", application.getProfessionInfo().getWorkYear());
		preferencesUtils.setData("et_total_worktimes", application.getProfessionInfo()
				.getTotalWorkYear());
		preferencesUtils.setData("et_com_address", application.getProfessionInfo()
				.getCompanyAddress());
		preferencesUtils.setData("et_mail_code",application.getProfessionInfo().getCompanyMail());
		preferencesUtils.setData("et_com_zone_code", application.getProfessionInfo()
				.getCompanyAreaNum());
		preferencesUtils.setData("et_com_tel_code", application.getProfessionInfo()
				.getCompanytelNum());
		preferencesUtils.setData("et_com_extension_code", application.getProfessionInfo()
				.getCompanyExtensionNum());
		preferencesUtils.setData("et_salary", application.getProfessionInfo().getMoneyMonth());
		preferencesUtils.setData("et_com_scale",application.getProfessionInfo().getDpScale());
		preferencesUtils.setData("et_com_properties", application.getProfessionInfo().getDpNature());
		preferencesUtils.setData("et_trade_properties", application.getProfessionInfo().getTradeNature());
		preferencesUtils.setData("et_com_position", application.getProfessionInfo().getNowRole());

	}
	
	
	private void editinit(){
//		SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
//				this);
		if (preferencesUtils.hasData("et_com_name")) {
			application.getProfessionInfo().setCompanyName(preferencesUtils.getData("et_com_name", ""));
			application.getProfessionInfo().setRzDepartment(preferencesUtils.getData("et_dpment_name", ""));
			application.getProfessionInfo().setWorkYear(preferencesUtils.getData("et_worktimes", ""));
			application.getProfessionInfo().setTotalWorkYear(preferencesUtils.getData("et_total_worktimes", ""));
			application.getProfessionInfo().setCompanyAddress(preferencesUtils.getData("et_com_address", ""));
			application.getProfessionInfo().setCompanyMail(preferencesUtils.getData("et_mail_code", ""));
			application.getProfessionInfo().setCompanyAreaNum(preferencesUtils.getData("et_com_zone_code", ""));
			application.getProfessionInfo().setCompanytelNum(preferencesUtils.getData("et_com_tel_code", ""));
			application.getProfessionInfo().setCompanyExtensionNum(preferencesUtils.getData("et_com_extension_code", ""));
			application.getProfessionInfo().setMoneyMonth(preferencesUtils.getData("et_salary", ""));
			application.getProfessionInfo().setDpScale(preferencesUtils.getData("et_com_scale", ""));
			application.getProfessionInfo().setDpNature(preferencesUtils.getData("et_com_properties", ""));
			application.getProfessionInfo().setTradeNature(preferencesUtils.getData("et_trade_properties", ""));
			application.getProfessionInfo().setNowRole(preferencesUtils.getData("et_com_position", ""));
		}else{
			if (Contents.isChoice==false) {
				if(Contents.response != null && Contents.response.getResult()!=null) {
					//填充个人信息的数据
					if ((null != Contents.response.getResult().getEmpName()) && !Contents.response.getResult().getEmpName().equals("")) {
						//单位全称
						application.getProfessionInfo().setCompanyName(Contents.response.getResult().getEmpName());
					}
					if ((null != Contents.response.getResult().getEmpAddr()) && !Contents.response.getResult().getEmpAddr().equals("")) {
						//单位地址
						application.getProfessionInfo().setCompanyAddress(Contents.response.getResult().getEmpAddr());
					}
					if ((null != Contents.response.getResult().getEmpZone()) && !Contents.response.getResult().getEmpZone().equals("")) {
						//单位区号
						application.getProfessionInfo().setCompanyAreaNum(Contents.response.getResult().getEmpZone());
					}
					if ((null != Contents.response.getResult().getEmpTelNo()) && !Contents.response.getResult().getEmpTelNo().equals("")) {
						//单位电话
						application.getProfessionInfo().setCompanytelNum(Contents.response.getResult().getEmpTelNo());
					}
					if ((null != Contents.response.getResult().getEmpTelSub()) && !Contents.response.getResult().getEmpTelSub().equals("")) {
						//单位分机号
						application.getProfessionInfo().setCompanyExtensionNum(Contents.response.getResult().getEmpTelSub());
					}
					if ((null != Contents.response.getResult().getMthInc()) && !Contents.response.getResult().getMthInc().equals("")) {
						//工资
						application.getProfessionInfo().setMoneyMonth(Contents.response.getResult().getMthInc());
					}
				}
			}
		}
	}
	
	private void setData(){
		et_com_name.setText(application.getProfessionInfo().getCompanyName());
		et_dpment_name.setText(application.getProfessionInfo()
				.getRzDepartment());
		et_worktimes.setText(application.getProfessionInfo().getWorkYear());
		et_total_worktimes.setText(application.getProfessionInfo()
				.getTotalWorkYear());
		et_com_address.setText(application.getProfessionInfo()
				.getCompanyAddress());
		et_mail_code.setText(application.getProfessionInfo().getCompanyMail());

		et_com_zone_code.setText(application.getProfessionInfo()
				.getCompanyAreaNum());
		et_com_tel_code.setText(application.getProfessionInfo()
				.getCompanytelNum());
		et_com_extension_code.setText(application.getProfessionInfo()
				.getCompanyExtensionNum());
		et_salary.setText(application.getProfessionInfo().getMoneyMonth());

		if (!"".equals(application.getProfessionInfo().getDpScaleCode())) {
			for (int i = 0; i < et_com_scale.getCount(); i++) {
				if (dp_scalesCode[i].equals(application.getProfessionInfo().getDpScaleCode())) {
					application.getProfessionInfo().setDpScale(dp_scales[i]);
				}
			}
		}

		
		if (!"".equals(application.getProfessionInfo().getDpScale())) {
			for (int i = 0; i < et_com_scale.getCount(); i++) {
				String item = dp_scales[i];
				if (item.equals(application.getProfessionInfo().getDpScale())) {
					et_com_scale.setSelection(i);
				}
			}
		}

		if (!"".equals(application.getProfessionInfo().getDpNatureCode())) {// 行业性质
			for (int i = 0; i < et_com_properties.getCount(); i++) {
				if (dp_naturesCode[i].equals(application.getProfessionInfo().getDpNatureCode())) {
					application.getProfessionInfo().setDpNature(dp_natures[i]);
				}
			}
		}
		
		if (!"".equals(application.getProfessionInfo().getDpNature())) {// 行业性质
			for (int i = 0; i < et_com_properties.getCount(); i++) {
				String item = dp_natures[i];
				if (item.equals(application.getProfessionInfo().getDpNature())) {
					et_com_properties.setSelection(i);
				}
			}
		}
		
		if (!"".equals(application.getProfessionInfo().getTradeNatureCode())) {// 行业性质
			for (int i = 0; i < et_trade_properties.getCount(); i++) {
				if (dp_trade_naturesCode[i].equals(application.getProfessionInfo()
						.getTradeNatureCode())) {
					application.getProfessionInfo()
					.setTradeNature(dp_trade_natures[i]);
				}
			}
		}
		
		if (!"".equals(application.getProfessionInfo().getTradeNature())) {// 行业性质
			for (int i = 0; i < et_trade_properties.getCount(); i++) {
				String item = dp_trade_natures[i];
				if (item.equals(application.getProfessionInfo()
						.getTradeNature())) {
					et_trade_properties.setSelection(i);
				}
			}
		}
		
		if (!"".equals(application.getProfessionInfo().getNowRoleCode())) {
			for (int i = 0; i < et_com_position.getCount(); i++) {
				if (now_rolesCode[i].equals(application.getProfessionInfo().getNowRoleCode())) {
					application.getProfessionInfo().setNowRole(now_roles[i]);
				}
			}
		}
		
		if (!"".equals(application.getProfessionInfo().getNowRole())) {
			for (int i = 0; i < et_com_position.getCount(); i++) {
				String item = now_roles[i];
				if (item.equals(application.getProfessionInfo().getNowRole())) {
					et_com_position.setSelection(i);
				}
			}
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
		if (v.getId() == R.id.easy_loan_profession_info_next_button) {
		  RegexResult temp = null; // 现单位全称
			String nowDpName = et_com_name.getText().toString().trim();
			
			temp = RegexCust.required("现单位全称", nowDpName);
			if (temp.match) {
				if (!RegexCust.lengthMax(nowDpName, 60)) {
					result = new RegexResult(false, "现单位全称字符长度过长!");
					showDialog(INFO_ERROR);
					et_com_name.requestFocus();
					return;
				}
			} else {
				result = temp;
				showDialog(INFO_ERROR);
				et_com_name.requestFocus();
				return;
			}
			if (RegexCust.nosymbol(nowDpName) == false) {
				result = new RegexResult(false, "单位名称禁止输入符号等字符");
				showDialog(INFO_ERROR);
				et_com_name.requestFocus();
				return;
			}
			// 任职部门
			String rzDp = et_dpment_name.getText().toString().trim();
			temp = RegexCust.required("任职部门", rzDp);
			
			if (temp.match) {
				if (!RegexCust.lengthMax(nowDpName, 60)) {
					result = new RegexResult(false, "任职部门字符长度过长!");
					showDialog(INFO_ERROR);
					et_dpment_name.requestFocus();
					return;
				}
			} else {
				result = temp;
				showDialog(INFO_ERROR);
				et_dpment_name.requestFocus();
				return;
			}
			if (RegexCust.nosymbol(rzDp) == false) {
				result = new RegexResult(false, "任职部门名称禁止输入符号等字符");
				showDialog(INFO_ERROR);
				et_dpment_name.requestFocus();
				return;
			}
			// 本单位工作年限
			String year = et_worktimes.getText().toString().trim();
			temp = RegexCust.required("本单位工作年限", year);
			if (temp.match) {
				if (!RegexCust.lengthMax(year, 10)) {
					result = new RegexResult(false, "本单位工作年限字符长度过长!");
					showDialog(INFO_ERROR);
					et_worktimes.requestFocus();
					return;
				}
			} else {
				result = temp;
				showDialog(INFO_ERROR);
				et_worktimes.requestFocus();
				return;
			}
			// 总工作年限
			String totalYear = et_total_worktimes.getText().toString().trim();
			temp = RegexCust.required("总工作年限", totalYear);
			if (temp.match) {
				if (!RegexCust.lengthMax(totalYear, 10)) {
					result = new RegexResult(false, "总工作年限字符长度过长!");
					showDialog(INFO_ERROR);
					et_total_worktimes.requestFocus();
					return;
				}
			} else {
				result = temp;
				showDialog(INFO_ERROR);
				et_total_worktimes.requestFocus();
				return;
			} 
			// 工作年限判断
			if (!"".equals(totalYear)) {
				int totalYear_int = Integer.parseInt(totalYear.trim());
				int year_int = Integer.parseInt(year.trim());
				if (year_int > totalYear_int) {
					result = new RegexResult(false, "总工作年限不能小于本单位工作年限");
					showDialog(INFO_ERROR);
					return;
				}
			}
			
			// 单位地址
			String dpPlace = et_com_address.getText().toString().trim();
			temp = RegexCust.required("单位地址", dpPlace);
			if (temp.match) {
				if (!RegexCust.lengthMax(dpPlace, 200)) {
					result = new RegexResult(false, "单位地址字符长度过长!");
					showDialog(INFO_ERROR);
					et_com_address.requestFocus();
					return;
				}
			} else {
				result = temp;
				showDialog(INFO_ERROR);
				et_com_address.requestFocus();
				return;
			}
			if (RegexCust.nosymbol(dpPlace) == false) {
				result = new RegexResult(false, "单位名称禁止输入符号等字符");
				showDialog(INFO_ERROR);
				et_com_address.requestFocus();
				return;
			}
			
			// 单位邮编
			String mail_code = et_mail_code.getText().toString().trim();
//			temp = RegexCust.required("单位邮编", mail_code);
//			if (temp.match) {
//				if (!RegexCust.maxlength(dpPlace, 200)) {
//					result = new Result(false, "单位邮编字符长度过长!");
//					showDialog(INFO_ERROR);
//					et_mail_code.requestFocus();
//					return;
//				}
//			} else {
//				result = temp;
//				showDialog(INFO_ERROR);
//				et_mail_code.requestFocus();
//				return;
//			}
			if ("".equals(mail_code)) {
				result = new RegexResult(false, "请填写户籍邮编!");
				showDialog(INFO_ERROR);
				et_mail_code.requestFocus();
				return;
			}
			if (mail_code.length() != 6) {
				result = new RegexResult(false, "户籍邮编位数有误，应为六位。");
				showDialog(INFO_ERROR);
				et_mail_code.requestFocus();
				return;
			}
			// 单位电话区号
			String areaNum = et_com_zone_code.getText().toString().trim();
			temp = RegexCust.required("单位电话区号", areaNum);
			if (temp.match) {
				if (!RegexCust.lengthMax(areaNum, 5)) {
					result = new RegexResult(false, "单位电话区号格式有误!");
					showDialog(INFO_ERROR);
					et_com_zone_code.requestFocus();
					return;
				}
			} else {
				result = temp;
				showDialog(INFO_ERROR);
				et_com_zone_code.requestFocus();
				return;
			}

			// 单位电话号码
			String telNum = et_com_tel_code.getText().toString().trim();
			temp = RegexCust.required("单位电话号码", telNum);
			if (temp.match) {
				if (!RegexCust.lengthMax(telNum,9)) {
					result = new RegexResult(false, "单位电话号码格式有误,或者位数有误，分机号最多4位!");
					showDialog(INFO_ERROR);
					et_com_tel_code.requestFocus();
					return;
				}
			} else {
				result = temp;
				showDialog(INFO_ERROR);
				et_com_tel_code.requestFocus();
				return;
			}
			// 单位电话分机号
			String extensionNum = et_com_extension_code.getText().toString()
					.trim();
			if (!RegexCust.lengthMax(extensionNum, 5)) {
				result = new RegexResult(false, "单位电话分机号格式有误!");
				showDialog(INFO_ERROR);
				et_com_extension_code.requestFocus();
				return;
			} // 单位规模
			if (str_com_scale == null || "请选择".equals(str_com_scale)) {
				result = new RegexResult(false, "请选择单位规模!");
				showDialog(INFO_ERROR);
				et_com_properties.requestFocus();
				return;
			} // 单位性质
			if (str_com_properties == null || "请选择".equals(str_com_properties)) {
				result = new RegexResult(false, "请选择单位性质!");
				showDialog(INFO_ERROR);
				et_com_properties.requestFocus();
				return;
			} // 行业性质
			if (str_trade_properties == null
					|| "请选择".equals(str_trade_properties)) {
				result = new RegexResult(false, "请选择行业性质!");
				showDialog(INFO_ERROR);
				et_trade_properties.requestFocus();
				return;
			} // 现任职位
			if (str_com_position == null || "请选择".equals(str_com_position)) {
				result = new RegexResult(false, "请选择现任职位!");
				showDialog(INFO_ERROR);
				et_com_position.requestFocus();
				return;
			} // 年工资收入
			String moneyYear = et_salary.getText().toString().trim();
			temp = RegexCust.required("月工资收入", moneyYear);
			if (temp.match) {
				if (!RegexCust.lengthMax(moneyYear, 7)) {
					result = new RegexResult(false, "月工资收入格式有误!");
					showDialog(INFO_ERROR);
					et_salary.requestFocus();
					return;
				}
			} else {
				result = temp;
				showDialog(INFO_ERROR);
				et_salary.requestFocus();
				return;
			}
			// 数据写入内存；
			application.getProfessionInfo().setCompanyName(
					et_com_name.getText().toString());
			application.getProfessionInfo().setRzDepartment(
					et_dpment_name.getText().toString());
			application.getProfessionInfo().setWorkYear(
					et_worktimes.getText().toString());
			application.getProfessionInfo().setTotalWorkYear(
					et_total_worktimes.getText().toString());
			application.getProfessionInfo().setCompanyAddress(
					et_com_address.getText().toString());
			application.getProfessionInfo().setCompanyMail(
					et_mail_code.getText().toString());

			application.getProfessionInfo().setCompanyAreaNum(
					et_com_zone_code.getText().toString());
			application.getProfessionInfo().setCompanytelNum(
					et_com_tel_code.getText().toString());
			application.getProfessionInfo().setCompanyExtensionNum(
					et_com_extension_code.getText().toString());
			application.getProfessionInfo().setMoneyMonth(
					et_salary.getText().toString());
			
			
			
			shareclear();
			shareinit();
			
		}
		super.onClick(v);
	}
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		if (parent == et_com_scale) {
			// 公司规模
			str_com_scale = (String) et_com_scale.getItemAtPosition(position);
			if ("请选择".equals(str_com_scale)) {
				et_com_scale
						.setBackgroundResource(R.drawable.comm_spinner_selecter);
			} else {
				et_com_scale
						.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
			}
			et_com_scale.setPadding(15, 0, 0, 0);
			application.getProfessionInfo().setDpScale(str_com_scale);
			application.getProfessionInfo().setDpScaleCode(
					dp_scalesCode[position]);

		}
		if (parent == et_com_properties) {
			// 单位性质
			str_com_properties = (String) et_com_properties
					.getItemAtPosition(position);
			if ("请选择".equals(str_com_properties)) {
				et_com_properties
						.setBackgroundResource(R.drawable.comm_spinner_selecter);
			} else {
				et_com_properties
						.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
			}
			et_com_properties.setPadding(15, 0, 0, 0);
			application.getProfessionInfo().setDpNature(str_com_properties);
			application.getProfessionInfo().setDpNatureCode(
					dp_naturesCode[position]);

		}
		if (parent == et_trade_properties) {
			// 行业性质
			str_trade_properties = (String) et_trade_properties
					.getItemAtPosition(position);
			if ("请选择".equals(str_trade_properties)) {
				et_trade_properties
						.setBackgroundResource(R.drawable.comm_spinner_selecter);
			} else {
				et_trade_properties
						.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
			}
			et_trade_properties.setPadding(15, 0, 0, 0);
			application.getProfessionInfo()
					.setTradeNature(str_trade_properties);
			application.getProfessionInfo().setTradeNatureCode(
					dp_trade_naturesCode[position]);

		}
		if (parent == et_com_position) {
			str_com_position = (String) et_com_position
					.getItemAtPosition(position);
			if ("请选择".equals(str_com_position)) {
				et_com_position
						.setBackgroundResource(R.drawable.comm_spinner_selecter);
			} else {
				et_com_position
						.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
			}
			et_com_position.setPadding(15, 0, 0, 0);
			application.getProfessionInfo().setNowRole(str_com_position);
			application.getProfessionInfo().setNowRoleCode(
					now_rolesCode[position]);
		}
		super.onItemSelected(parent, view, position, id);
	}

	@Override
	protected Dialog onCreateDialog(int id, Bundle args) {
		Dialog dialog = null;
		StringBuffer sb = new StringBuffer();
		switch (id) {
		case INFO_ERROR:
			sb.append(result.msg);
			dialog = DialogUtil.showDialogOneButton2(
					RelaxedLoanFourProfessionInfo.this, sb.toString());
			break;
		default:
			break;
		}
		return super.onCreateDialog(id, args);
	}

	private void initOldClientInfo() {
		// TODO Auto-generated method stub
		// if (application.getClientInfo() != null && application.getOldCilent3)
		// {
		// // 把数据写入内存；
		// application.getOldCilent3 = false;
		// }
	}
}