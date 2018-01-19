package com.yucheng.byxf.mini.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.comm.ScreenManager;
import com.yucheng.byxf.mini.domain.MiniPersonInfo;
import com.yucheng.byxf.mini.easyloan.ui.RelaxedLoanAdActivity;
import com.yucheng.byxf.mini.easyloan.ui.RelaxedLoanOneContractBook;
import com.yucheng.byxf.mini.eloan.domain.EloanListEntity;
import com.yucheng.byxf.mini.map.DemoApplication;
import com.yucheng.byxf.mini.message.ApplicationScheduleQueryResponse;
import com.yucheng.byxf.mini.message.EasyLoanChoiceInfoResult;
import com.yucheng.byxf.mini.message.EasyLoanRequest;
import com.yucheng.byxf.mini.message.EasyLoanResult;
import com.yucheng.byxf.mini.message.MiniCardActivateResult;
import com.yucheng.byxf.mini.message.MiniConfirmMessage;
import com.yucheng.byxf.mini.message.MiniProgress;
import com.yucheng.byxf.mini.message.MiniProgressResponse;
import com.yucheng.byxf.mini.message.RapidlyLoanInfo;
import com.yucheng.byxf.mini.message.Response;
import com.yucheng.byxf.mini.rapidly.RapidlyLoanInfoContents;
import com.yucheng.byxf.mini.rapidly.RepidlyLoanInfoContractBook;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.FileUtils2;

public class ApplicationScheduleQueryEloanActivity extends BaseActivity
		implements OnClickListener {
	/**
	 * 核准确认书
	 */
	// 客户姓名；
	TextView app_schedule_query_name;
	// 证件号
	TextView app_schedule_query_zhengjian_num;
	// 核准金额
	TextView app_schedule_query_shenqing_money;
	// 申请时间；
	TextView app_schedule_query_shenpi_time;
	// 审批状态
	TextView app_schedule_query_shenpi_state;
	// 合同状态
	TextView app_schedule_query_hetong_state;
	// 核准确认状态
	TextView app_schedule_query_hezhun_confirmstate;
	// 放款状态
	TextView app_schedule_query_fangkuan_state;

	ImageView app_schedule_query_menu;
	private ImageView iv_line;
	ApplicationScheduleQueryResponse response;
	Button bt_check;

	protected DemoApplication easyApplication = null;

	private boolean flag = false;
	private String status;
	private RapidlyLoanInfo rapidlyLoanInfo;
	private MiniConfirmMessage miniConfirmMessage;
	private Button bt_choice;
	private Button hezhuntishi;

	private ApplicationScheduleQueryEloanActivity activity;

	private int aHeight;
	private int aWidth;
	LinearLayout linearLayout_img;
	private TextView tvtitle;
	private Button bt_showChoiseInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activity = new ApplicationScheduleQueryEloanActivity();
		setContentView(R.layout.application_schedule_query_eloan);
		Bundle bundle = getIntent().getExtras();
		response = (ApplicationScheduleQueryResponse) bundle
				.getSerializable("list");
		initView();

	}

	private void initView() {
		tvtitle = (TextView) findViewById(R.id.daikuantitle);
		// TODO Auto-generated method stub
		app_schedule_query_name = (TextView) findViewById(R.id.app_schedule_query_name);
		app_schedule_query_zhengjian_num = (TextView) findViewById(R.id.app_schedule_query_zhengjian_num);
		app_schedule_query_shenqing_money = (TextView) findViewById(R.id.app_schedule_query_shenqing_money);
		app_schedule_query_shenpi_time = (TextView) findViewById(R.id.app_schedule_query_shenpi_time);

		app_schedule_query_shenpi_state = (TextView) findViewById(R.id.app_schedule_query_shenpi_state);
		app_schedule_query_hetong_state = (TextView) findViewById(R.id.app_schedule_query_hetong_state);
		app_schedule_query_hezhun_confirmstate = (TextView) findViewById(R.id.app_schedule_query_hezhun_confirmstate);
		app_schedule_query_fangkuan_state = (TextView) findViewById(R.id.app_schedule_query_fangkuan_state);

		hezhuntishi = (Button) findViewById(R.id.iv_hezhun);
		// 新加 查看按钮
		bt_showChoiseInfo = (Button) findViewById(R.id.bt_showChoiseInfo);
		bt_showChoiseInfo.setOnClickListener(this);
		bt_choice = (Button) findViewById(R.id.bt_choice);
		bt_choice.setOnClickListener(this);
		// 返回按钮；
		app_schedule_query_menu = (ImageView) findViewById(R.id.app_schedule_query_menu);
		app_schedule_query_menu.setOnClickListener(this);

		iv_line = (ImageView) findViewById(R.id.iv_line);
		bt_check = (Button) findViewById(R.id.bt_check);
		bt_check.setOnClickListener(this);
		status = response.getState();
		setTextData();
		if (flag) {
			app_schedule_query_hezhun_confirmstate.setText("已核准");
			iv_line.setBackgroundResource(R.drawable.line_hetongqianding2);
			hezhuntishi.setVisibility(View.VISIBLE);
		}

		linearLayout_img = (LinearLayout) findViewById(R.id.lin1);

	}

	private void setTextData() {
		bt_check.setClickable(false);
		bt_check.setBackgroundResource(R.drawable.check_unuse);
		app_schedule_query_name.setText(response.getCustName());
		app_schedule_query_zhengjian_num.setText(response.getIdNo());
		// 核准金额
		app_schedule_query_shenqing_money.setText(response.getApplyAmt() + "元");
		app_schedule_query_shenpi_time.setText(response.getApplyDt());
		if (response.getState().equals("997")) {
			iv_line.setBackgroundResource(R.drawable.line_sheni2);
			hezhuntishi.setVisibility(View.VISIBLE);
		}
		if (response.getState().equals("992")) {
			bt_choice.setVisibility(View.VISIBLE);
			//先不上0716
			bt_showChoiseInfo.setVisibility(View.VISIBLE);
		}
		app_schedule_query_shenpi_state.setText(response.getStateShow());
		String status = response.getContStatus();
		if (status.equals("200")) {
			app_schedule_query_hetong_state.setText("生效");
			iv_line.setBackgroundResource(R.drawable.line_hetongqianding2);
			hezhuntishi.setVisibility(View.VISIBLE);
			bt_check.setClickable(true);
			bt_check.setBackgroundResource(R.drawable.check_nomal);
		} else if (status.equals("100")) {
			app_schedule_query_hetong_state.setText("未生效");
		} else if (status.equals("300")) {
			app_schedule_query_hetong_state.setText("注销");
		} else if (status.equals("800")) {
			app_schedule_query_hetong_state.setText("已出帐");
		} else {
			app_schedule_query_hetong_state.setText("核销");
		}

		if (response.getPsSignInd().equals("Y")) {
			app_schedule_query_hezhun_confirmstate.setText("已核准");
			iv_line.setBackgroundResource(R.drawable.line_hetongqianding2);
			hezhuntishi.setVisibility(View.VISIBLE);
		} else {
			app_schedule_query_hezhun_confirmstate.setText("未核准");
			flag = false;
		}
		if (response.getActvStr().equals("Y")) {
			app_schedule_query_fangkuan_state.setText("已放款");
			iv_line.setBackgroundResource(R.drawable.line_fangkuan2);
			hezhuntishi.setVisibility(View.VISIBLE);
			bt_check.setBackgroundResource(R.drawable.check_unuse);
		} else if (response.getActvStr().equals("NACTV")) {
			app_schedule_query_fangkuan_state.setText("未放款");
		} else {
			app_schedule_query_fangkuan_state.setText("放款异常");
		}

		// wz
		System.out.println("response.getApplyTnr()=======>"
				+ response.getApplyTnr());
		if (response.getApplyTnr().equals("1")) {
			tvtitle.setText("极速贷申请进度");
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.app_schedule_query_menu:
			if ("Y".equals(status)) {
				Intent data = new Intent();
				data.putExtra("confirmStatus", "Y");
				setResult(21, data);
			}
			finish();
			break;
		case R.id.bt_check:
			Intent intent = new Intent(this, ConfirmContractBook.class);
			intent.putExtra("applCde", response.getApplCde());
			intent.putExtra("confirmStatus", status);
			startActivityForResult(intent, 100);
			break;
		case R.id.bt_choice:
			// new GetInfoAsyncTask().execute("0060011001577780");
			// new GetInfoAsyncTask().execute("0060011001825540");
			// 急速 打回测试
			// new GetInfoAsyncTask().execute("0060011001579599");
			// 轻松打回测试
			new GetInfoAsyncTask().execute(response.getApplCde());

			break;
		case R.id.bt_showChoiseInfo:
			// 打回详情查看
			new ChoiceGetInfoAsyncTask().execute(response.getApplCde(), "08",
					"1");

			break;
		default:
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ("Y".equals(status)) {
				Intent data = new Intent();
				data.putExtra("confirmStatus", "Y");
				setResult(21, data);
			}
			finish();
		}
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		System.out.println("===========>" + resultCode);
		if (20 == resultCode) {
			status = data.getExtras().getString("confirmStatus");
			if ("Y".equals(status)) {
				app_schedule_query_hezhun_confirmstate.setText("已核准");
				iv_line.setBackgroundResource(R.drawable.line_hetongqianding2);
				hezhuntishi.setVisibility(View.VISIBLE);
				flag = true;
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	class GetInfoAsyncTask extends AsyncTask<String, Object, Object> {
		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(ApplicationScheduleQueryEloanActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("applyCode", params[0]));
			return httpHelper.post(ContantsAddress.LOAD_APPLY_LOAN, arg,
					EasyLoanResult.class);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog();
		}

		@Override
		protected void onPostExecute(Object result) {

			super.onPostExecute(result);
			if (activity != null) {
				dismissProgressDialog();
				EasyLoanResult response = (EasyLoanResult) result;
				if (response != null) {
					if (response.getCode() == 0) {

						if (response.getResult().getApplyType().equals("0")) {// 轻松贷
							setDataEasyLoan(response.getResult());
							// setDataRapidly(response.getResult());
							ScreenManager screenManager = ScreenManager
									.getScreenManager();
							screenManager.popAllActivityExceptOne();
							Contents.isChoice = true;
							// Intent intent = new Intent(
							// ApplicationScheduleQueryEloanActivity.this,
							// RelaxedLoanOneContractBook.class);
							// 加轻松贷内页
							Intent intent = new Intent(
									ApplicationScheduleQueryEloanActivity.this,
									RelaxedLoanAdActivity.class);
							intent.putExtra("choiceType", "choiceType");
							startActivity(intent);
						} else if (response.getResult().getApplyType()
								.equals("1")) {// 极速贷
							setDataRapidly(response.getResult());
							// setDataEasyLoan(response.getResult());
							ScreenManager screenManager = ScreenManager
									.getScreenManager();
							screenManager.popAllActivityExceptOne();
							Contents.isChoice = true;
							// Intent intent = new Intent(
							// ApplicationScheduleQueryEloanActivity.this,
							// RepidlyLoanInfoContractBook.class);
							// intent.putExtra("choiceType", "choiceType");
							// startActivity(intent);
							Intent intent = new Intent();
							// if(1==2){
							if (Contents.response != null
									&& Contents.response.getResult() != null
									&& Contents.response.getResult()
											.isExistFavourableActv()) {
								intent.setClass(
										ApplicationScheduleQueryEloanActivity.this,
										RedPacketActivity.class);
								intent.putExtra("choiceType", "choiceType");
								startActivity(intent);
							} else {
								intent.setClass(
										ApplicationScheduleQueryEloanActivity.this,
										RedPacketActivity2.class);
								intent.putExtra("choiceType", "choiceType");
								startActivity(intent);
							}

						}
					} else {

					}
				} else {

				}
			}

		}

	}

	/**
	 * 打回 信息的查看
	 * 
	 */
	class ChoiceGetInfoAsyncTask extends AsyncTask<String, Object, Object> {
		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(ApplicationScheduleQueryEloanActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("applCde", params[0]));
			arg.add(new BasicNameValuePair("inputSrc", params[1]));
			arg.add(new BasicNameValuePair("loanType", params[2]));
			return httpHelper.post(ContantsAddress.ChoiceGetInfo, arg,
					EasyLoanChoiceInfoResult.class);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog();
		}

		@Override
		protected void onPostExecute(Object result) {

			super.onPostExecute(result);
			dismissProgressDialog();
			EasyLoanChoiceInfoResult response = (EasyLoanChoiceInfoResult) result;
			if (response != null) {
				if (response.getCode() == 0) {
					String getInfo = "";
					getInfo = response.getResult().get(0).getYj().toString();
					DialogUtil.showDialogOneButton2_big(
							ApplicationScheduleQueryEloanActivity.this,
							getInfo);
				} else {
					DialogUtil.showDialogOneButton(
							ApplicationScheduleQueryEloanActivity.this,
							response.getMsg(), new OnClickListener() {
								@Override
								public void onClick(View v) {
									finish();
								}

							});
			}
			} else {
				DialogUtil.showDialogOneButton(
						ApplicationScheduleQueryEloanActivity.this,
						"服务器异常", new OnClickListener() {
							@Override
							public void onClick(View v) {

								finish();
							}

						});
			}

		}
	}

	/**
	 * 极速贷对象赋值
	 */
	private void setDataRapidly(EasyLoanRequest request) {
		rapidlyLoanInfo = new RapidlyLoanInfo();

		rapidlyLoanInfo.setName(request.getCustName());
		rapidlyLoanInfo.setIdNo(request.getIdNo());
		rapidlyLoanInfo.setPhone(request.getIndivMobile());
		// 地址 现居住地址or户籍地址

		String addStr = request.getCurentResidentialAddr();
		String add1 = addStr.substring(0, addStr.indexOf(","));
		String add2 = addStr.substring(addStr.indexOf(",") + 1);
		System.out.println(addStr + "<=====addStr");
		System.out.println(add1 + "<=====add1");
		System.out.println(add2 + "<=====add2");
		rapidlyLoanInfo.setAddress(add1);
		rapidlyLoanInfo.setAddress_more(add2);
		// 单电话 区号
		rapidlyLoanInfo.setCompany_phone_zone_code(request.getIndivEmpZone());
		// 单电话 电号
		rapidlyLoanInfo.setCompany_phone_tel_code(request.getIndivEmpTelNo());
		// 单电话 分机号 indivEmpTelNo
		rapidlyLoanInfo.setCompany_phone_extension_code(request
				.getIndivEmpTelSub());

		rapidlyLoanInfo.setCompanyName(request.getIndivEmp());
		rapidlyLoanInfo.setContactName1(request.getIndivRelName());
		rapidlyLoanInfo.setContactRelationCode1(request.getIndivRelation());
		rapidlyLoanInfo.setMobileNo1(request.getIndivRelMobile());
		rapidlyLoanInfo.setContactName2(request.getIndivOthName());
		rapidlyLoanInfo.setContactRelationCode2(request.getIndivOthRel());
		rapidlyLoanInfo.setMobileNo2(request.getIndivOthMobile());
		rapidlyLoanInfo.setMoney(request.getApplyAmt());
		// rapidlyLoanInfo.setPoundage(request.getindi)
		rapidlyLoanInfo.setRepayment_way(request.getLoanMtd());
		rapidlyLoanInfo.setBankName(request.getApplDisbAcBank());
		System.out.println("===负值 =>+" + request.getApplDisbAcBank());
		rapidlyLoanInfo.setCardNo(request.getApplDisbAcNo());
		miniConfirmMessage = new MiniConfirmMessage();
		// 申请流水号
		miniConfirmMessage.setApplSeq(request.getApplSeq());
		// // 申请编号
		miniConfirmMessage.setApplCde(request.getApplCde());
		// // 放款流水号
		miniConfirmMessage.setApplDisbSeq(request.getApplDisbSeq());
		// // 相关流水号
		miniConfirmMessage.setApptSeq(request.getApptSeq());
		// // 还款流水号
		miniConfirmMessage.setApplRpymSeq(request.getApplRpymSeq());

		RapidlyLoanInfoContents.setRapidlyLoanInfo(rapidlyLoanInfo);
		RapidlyLoanInfoContents.setMiniConfirmMessage(miniConfirmMessage);
	}

	/**
	 * 轻松带对象赋值
	 */
	private void setDataEasyLoan(EasyLoanRequest request) {
		easyApplication = ((DemoApplication) getApplicationContext());
		// 生日
		easyApplication.getBasicInfo().setBirthday(request.getApptStartDate());
		// 性别
		easyApplication.getBasicInfo().setSex(request.getIndivSex());
		// 证件开始日期
		easyApplication.getBasicInfo().setExpiryStartDate(
				request.getIdTermBgn());
		// 证件结束日期
		easyApplication.getBasicInfo().setExpiryEndDate(request.getIdTermEnd());
		// 籍贯
		easyApplication.getBasicInfo().setBirthPlace(request.getIndivRegAddr());
		// 户籍邮编
		easyApplication.getBasicInfo().setHousehold_email(
				request.getIndivRegZip());
		// 婚姻状况
		easyApplication.getBasicInfo().setMarriageStatueCode(
				request.getIndivMarital());
		// 供养人数
		easyApplication.getBasicInfo()
				.setProvidePerson(request.getIndivDepNo());
		// 现居住地地址
		easyApplication.getBasicInfo().setLiveAddress(
				request.getIndivLiveAddr());
		// 邮编
		easyApplication.getBasicInfo().setLivePostcode(
				request.getIndivLiveZip());
		// 住宅性质
		easyApplication.getBasicInfo().setLivePropertiesCode(
				request.getIndivLive());
		// 文化程度
		easyApplication.getBasicInfo().setCultureDegree(
				request.getIndivDegree());
		// 住宅电话区号
		easyApplication.getBasicInfo().setZoneCode(request.getIndivLiveZone());
		// 住宅电话
		easyApplication.getBasicInfo().setTelPhone(request.getIndivLivePhone());
		// 电子邮箱
		easyApplication.getBasicInfo()
				.setElecTronEmail(request.getIndivEmail());
		// 租金-----
		easyApplication.getBasicInfo().setIndivRentAmt(
				request.getIndivRentAmt());

		// 手机号码
		easyApplication.getBasicInfo().setMobilePhone(request.getIndivMobile());
		// 现在单位全称
		easyApplication.getProfessionInfo().setCompanyName(
				request.getIndivEmp());
		// 现任部门
		easyApplication.getProfessionInfo().setRzDepartment(
				request.getIndivBranch());
		// 本单位工作年限
		easyApplication.getProfessionInfo().setWorkYear(
				request.getIndivEmpYrs());
		// 总工作年限
		easyApplication.getProfessionInfo().setTotalWorkYear(
				request.getIndivWorkYrs());
		// 单位地址
		easyApplication.getProfessionInfo().setCompanyAddress(
				request.getIndivEmpAddr());
		// 邮编
		easyApplication.getProfessionInfo().setCompanyMail(
				request.getIndivEmpZip());
		// 单电话 区号
		easyApplication.getProfessionInfo().setCompanyAreaNum(
				request.getIndivEmpZone());
		// 单电话 电号
		easyApplication.getProfessionInfo().setCompanytelNum(
				request.getIndivEmpTelNo());
		// 单电话 分机号
		easyApplication.getProfessionInfo().setCompanyExtensionNum(
				request.getIndivEmpTelSub());
		// 单位规模 -
		easyApplication.getProfessionInfo().setDpScaleCode(
				request.getIndivEmpNum());
		// 单位性质 -
		easyApplication.getProfessionInfo().setDpNatureCode(
				request.getIndivEmpType());
		// 行业性质 -
		easyApplication.getProfessionInfo().setTradeNatureCode(
				request.getIndivIndtryPaper());
		// 所在职位 -
		easyApplication.getProfessionInfo().setNowRoleCode(
				request.getIndivPosition());
		// 申请人月 收入
		easyApplication.getProfessionInfo().setMoneyMonth(
				request.getIndivMthInc());

		if (request.getAsset() != null) {
			if (request.getAsset().size() == 1) {
				// 个人资产 房
				easyApplication.getAssertInfo().setHouseMoney(
						request.getAsset().get(0).getAssetAmt());
				// 个人负债 房屋贷款
				easyApplication.getAssertInfo().setDaikuanHouse(
						request.getAsset().get(0).getAssetReAmt());
				// 月还房贷
				easyApplication.getAssertInfo().setPayHouse(
						request.getAsset().get(0).getAssetMthAmt());
			}
			if (request.getAsset().size() == 2) {
				// 个人资产 房
				easyApplication.getAssertInfo().setHouseMoney(
						request.getAsset().get(0).getAssetAmt());
				// 个人负债 房屋贷款
				easyApplication.getAssertInfo().setDaikuanHouse(
						request.getAsset().get(0).getAssetReAmt());
				// 月还房贷
				easyApplication.getAssertInfo().setPayHouse(
						request.getAsset().get(0).getAssetMthAmt());
				// 个人资产 车
				easyApplication.getAssertInfo().setCarMoney(
						request.getAsset().get(1).getAssetAmt());
				// 个人负债 车贷款、
				easyApplication.getAssertInfo().setDaikuanCar(
						request.getAsset().get(1).getAssetReAmt());
				// 月还其他
				easyApplication.getAssertInfo().setPayOher(
						request.getAsset().get(1).getAssetMthAmt());
			}
			if (request.getAsset().size() == 3) {
				// 个人资产 房
				easyApplication.getAssertInfo().setHouseMoney(
						request.getAsset().get(0).getAssetAmt());
				// 个人资产 车
				easyApplication.getAssertInfo().setCarMoney(
						request.getAsset().get(1).getAssetAmt());
				// 个人资产 其他
				easyApplication.getAssertInfo().setOtherMoney(
						request.getAsset().get(2).getAssetAmt());

				// 个人负债 房屋贷款
				easyApplication.getAssertInfo().setDaikuanHouse(
						request.getAsset().get(0).getAssetReAmt());
				// 个人负债 车贷款、
				easyApplication.getAssertInfo().setDaikuanCar(
						request.getAsset().get(1).getAssetReAmt());
				// 个人负债其他贷款
				easyApplication.getAssertInfo().setDaikuanOher(
						request.getAsset().get(2).getAssetReAmt());

				// 月还房贷
				easyApplication.getAssertInfo().setPayHouse(
						request.getAsset().get(0).getAssetMthAmt());
				// 月还车贷
				easyApplication.getAssertInfo().setPayCar(
						request.getAsset().get(1).getAssetMthAmt());
				// 月还其他
				easyApplication.getAssertInfo().setPayOher(
						request.getAsset().get(2).getAssetMthAmt());
			}
		}

		// 是否有银行信用卡
		easyApplication.getAssertInfo().setIsHavingCreditCard(
				request.getIndivCardInd());
		// 单张额度
		easyApplication.getAssertInfo().setCardTopMoney(
				request.getIndivCardAmt());
		// 发卡行名称
		easyApplication.getAssertInfo().setCardBankName(
				request.getIndivCardBank());
		// 申请贷款金额
		easyApplication.getApplyPersonalInfo().setApply_amount_value(
				request.getApplyAmt());
		// 贷款用途
		easyApplication.getApplyPersonalInfo().setTick_loan_pursoseCode(
				request.getPurpose());
		// 还款方式
		easyApplication.getApplyPersonalInfo().setTick_loan_methodCode(
				request.getLoanMtd());
		// 申请贷款期限
		easyApplication.getApplyPersonalInfo().setApplyLoanTime(
				request.getApplyTnr());
		// 信件邮件地址
		easyApplication.getApplyPersonalInfo().setSend_methodCode(
				request.getMailAddr());
		// 还款账户开户行
		easyApplication.getApplyPersonalInfo().setRepay_acount_type(
				request.getApplRpymAcBank());
		// 户名
		easyApplication.getApplyPersonalInfo().setUsername_value(
				request.getCustName());
		// 账号
		easyApplication.getApplyPersonalInfo().setAccount_value(
				request.getApplDisbAcNo());
		// 员工推广id
		easyApplication.getApplyPersonalInfo().setEmployee_id(
				request.getOperator());
		// 推广员姓名
		easyApplication.getApplyPersonalInfo().setEmployee_name(
				request.getOperatorName());

		// 第一联系人姓名
		easyApplication.getLinkManInfo().setContactName1(
				request.getIndivRelName());
		// 第一联系人 与申请人关系
		easyApplication.getLinkManInfo().setContactRelation1Code(
				request.getIndivRelation());
		// 第一联系人住宅电话区号
		easyApplication.getLinkManInfo().setArea_telphone1zone(
				request.getIndivRelZone());
		// 第一联系人住宅电话
		easyApplication.getLinkManInfo().setArea_telphone1(
				request.getIndivRelPhone());
		easyApplication.getLinkManInfo().setMobileNo1(
				request.getIndivRelMobile());

		// 第2联系人姓名
		easyApplication.getLinkManInfo().setContactName2(
				request.getIndivOthName());
		// 第2联系人 与申请人关系
		easyApplication.getLinkManInfo().setContactRelation2Code(
				request.getIndivOthRel());
		// 第2联系人住宅电话区号
		easyApplication.getLinkManInfo().setArea_telphone2zone(
				request.getIndivOthZone());
		// 第2联系人住宅电话
		easyApplication.getLinkManInfo().setArea_telphone2(
				request.getIndivOthPhone());
		easyApplication.getLinkManInfo().setMobileNo2(
				request.getIndivOthMobile());
		if (request.getLcAppRelations().size() > 0) {
			// 第3联系人姓名
			easyApplication.getLinkManInfo().setContactName3(
					request.getLcAppRelations().get(0).getIndivRelName());
			// 第3联系人 与申请人关系
			easyApplication.getLinkManInfo().setContactRelation3Code(
					request.getLcAppRelations().get(0).getIndivRelation());
			// 第3联系人住宅电话区号
			easyApplication.getLinkManInfo().setArea_telphone3zone(
					request.getLcAppRelations().get(0).getIndivRelZone());
			// 第3联系人住宅电话
			easyApplication.getLinkManInfo().setArea_telphone3(
					request.getLcAppRelations().get(0).getIndivRelPhone());
			easyApplication.getLinkManInfo().setMobileNo3(
					request.getLcAppRelations().get(0).getIndivRelMobile());

			// 第4联系人姓名
			easyApplication.getLinkManInfo().setContactName4(
					request.getLcAppRelations().get(1).getIndivRelName());
			// 第4联系人 与申请人关系
			easyApplication.getLinkManInfo().setContactRelation4Code(
					request.getLcAppRelations().get(1).getIndivRelation());
			// 第4联系人住宅电话区号
			easyApplication.getLinkManInfo().setArea_telphone4zone(
					request.getLcAppRelations().get(1).getIndivRelZone());
			// 第4联系人住宅电话
			easyApplication.getLinkManInfo().setArea_telphone4(
					request.getLcAppRelations().get(1).getIndivRelPhone());
			easyApplication.getLinkManInfo().setMobileNo4(
					request.getLcAppRelations().get(1).getIndivRelMobile());
		}
		// miniConfirmMessage = new MiniConfirmMessage();
		// // 申请流水号
		// miniConfirmMessage.setApplSeq(request.getApplSeq());
		// // 申请编号
		// miniConfirmMessage.setApplCde(request.getApplCde());
		// // 放款流水号
		// miniConfirmMessage.setApplDisbSeq(request.getApplDisbSeq());
		// // 相关流水号
		// miniConfirmMessage.setApptSeq(request.getApptSeq());
		// // 还款流水号
		// miniConfirmMessage.setApplRpymSeq(request.getApplRpymSeq());

		MiniPersonInfo.applCde = request.getApplCde();
		MiniPersonInfo.applSeq = request.getApplSeq();

		MiniPersonInfo.applDisbSeq = request.getApplDisbSeq();
		MiniPersonInfo.applRpymSeq = request.getApplRpymSeq();
		MiniPersonInfo.apptSeq = request.getApptSeq();

		// RapidlyLoanInfoContents.setMiniConfirmMessage(miniConfirmMessage);

		// // 新增缺失字段
		// easyApplication.getEasyLoanRequest().setIdCtry(request.getIdCtry());
		// easyApplication.getEasyLoanRequest().setIndivOthEmp(request.getIndivOthEmp());
		// easyApplication.getEasyLoanRequest().setApptAge(request.getApptAge());
		// easyApplication.getEasyLoanRequest().setIndivRentAmt(request.getIndivRentAmt());
		// easyApplication.getEasyLoanRequest().setPomsDepart(request.getPomsDepart());
		// easyApplication.getEasyLoanRequest().setPomsStaff(request.getPomsStaff());
		// easyApplication.getEasyLoanRequest().setMssInd(request.getMssInd());
		// easyApplication.getEasyLoanRequest().setBchCde(request.getBchCde());
		// easyApplication.getEasyLoanRequest().setLoanGrp(request.getLoanGrp());
		// easyApplication.getEasyLoanRequest().setLoanTyp(request.getLoanTyp());
		// easyApplication.getEasyLoanRequest().setLoanKind(request.getLoanKind());
		// easyApplication.getEasyLoanRequest().setLoanCcy(request.getLoanCcy());
		// easyApplication.getEasyLoanRequest().setPurpose(request.getPurpose());
		// easyApplication.getEasyLoanRequest().setOtherPurpose(request.getOtherPurpose());
		// easyApplication.getEasyLoanRequest().setApprvAmt(request.getApprvAmt());
		// easyApplication.getEasyLoanRequest().setApprvTnr(request.getApprvTnr());
		// easyApplication.getEasyLoanRequest().setProPurAmt(request.getProPurAmt());
		// easyApplication.getEasyLoanRequest().setFstPay(request.getFstPay());
		// easyApplication.getEasyLoanRequest().setOdGranceTyp(request.getOdGranceTyp());
		// easyApplication.getEasyLoanRequest().setOdGrance(request.getOdGrance());
		// easyApplication.getEasyLoanRequest().setDealer(request.getDealer());
		// easyApplication.getEasyLoanRequest().setSalerId(request.getSalerId());
		// easyApplication.getEasyLoanRequest().setSalerName(request.getSalerName());
		// easyApplication.getEasyLoanRequest().setPcCode(request.getPcCode());
		// easyApplication.getEasyLoanRequest().setApplDisbAcNam(request.getApplDisbAcNam());
		// easyApplication.getEasyLoanRequest().setApplRpymAcNam(request.getApplRpymAcNam());
	}

}