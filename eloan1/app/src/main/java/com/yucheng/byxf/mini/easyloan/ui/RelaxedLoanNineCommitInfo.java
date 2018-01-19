package com.yucheng.byxf.mini.easyloan.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.domain.MiniPersonInfo;
import com.yucheng.byxf.mini.eloan.domain.AssertInfo;
import com.yucheng.byxf.mini.eloan.domain.BasicInfo;
import com.yucheng.byxf.mini.eloan.domain.LinkManInfo;
import com.yucheng.byxf.mini.eloan.domain.LoanApplyPersonalInfo;
import com.yucheng.byxf.mini.eloan.domain.ProfessionalInfo;
import com.yucheng.byxf.mini.message.LoginMessage;
import com.yucheng.byxf.mini.message.LoginResponse;
import com.yucheng.byxf.mini.message.MiniConfirmMessage;
import com.yucheng.byxf.mini.message.MiniConfirmResponse;
import com.yucheng.byxf.mini.message.PhotoInfo;
import com.yucheng.byxf.mini.message.RapidlyLoanInfo;
import com.yucheng.byxf.mini.message.Response;
import com.yucheng.byxf.mini.message.VerifyCodeResponse;
import com.yucheng.byxf.mini.rapidly.RapidlyLoanInfoConfrim;
import com.yucheng.byxf.mini.rapidly.RapidlyLoanInfoContents;
import com.yucheng.byxf.mini.ui.MyLoginActivity;
import com.yucheng.byxf.mini.ui.PreviewActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.HttpMultipartPostAddMore;
import com.yucheng.byxf.mini.utils.StringUtils;
import com.yucheng.byxf.mini.utils.ViewTools;
import com.yucheng.byxf.util.LogManager;

/**
 * @author danny
 */
public class RelaxedLoanNineCommitInfo extends BaseRelaxedLoanActivity {
	private Button confirm_button;
	private TextView confirm_text1, confirm_text2, confirm_text3,
			confirm_text4, confirm_text5, confirm_text6, imagetext;
	private ImageView imageView1;
	private ImageView imageView2;
	private ImageView imageView5;
	private ImageView imageView3;
	private ImageView imageView4;

	private ImageView imageView51;
	private ImageView imageView52;
	private ImageView imageView55;
	private ImageView imageView53;
	private ImageView imageView54;

	private static final Integer RET_CODE = 0;
	private String applCde;
	private String applSeq;

	private String applDisbSeq;
	private String applRpymSeq;
	private String apptSeq;

	private LinearLayout layout;

	private TextView house_assert;
	private TextView car_assert;
	private TextView other_assert;

	private TextView house_loan;
	private TextView car_loan;
	private TextView other_loan;

	private TextView repay_loan_house;
	private TextView repay_loan_car;
	private TextView repay_loan_other;
	private String IMEI = "";//设备imei
	// private List<Bitmap> listBitmaps = new ArrayList<Bitmap>();

	private RelaxedLoanNineCommitInfo activity;
	//验证码
	private TextView tishi_str;
	private RelativeLayout rel_msg_key;
	private LinearLayout lin_msg;
	private boolean  Type_key=false;
	private TextView bt_subkey;
	private EditText ed_key;
	//判断是否
	private boolean isFlagOk = false;
	private boolean isFlag=true;
	
	private int time = 100;
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		setContentView(R.layout.easy_loan_nine_commit_info);
		super.init();
		activity = new RelaxedLoanNineCommitInfo();
		easy_loan_head.setText("信息确认");
		confirm_button = (Button) findViewById(R.id.confirm_button);
		confirm_button.setOnClickListener(this);
		confirm_text1 = (TextView) findViewById(R.id.confirm_text1);
		confirm_text2 = (TextView) findViewById(R.id.confirm_text2);
		confirm_text3 = (TextView) findViewById(R.id.confirm_text3);
		confirm_text4 = (TextView) findViewById(R.id.confirm_text4);
		confirm_text5 = (TextView) findViewById(R.id.confirm_text5);
		confirm_text6 = (TextView) findViewById(R.id.confirm_text6);
		imagetext = (TextView) findViewById(R.id.imagetext);

		house_assert = (TextView) findViewById(R.id.house_assert);
		car_assert = (TextView) findViewById(R.id.car_assert);
		other_assert = (TextView) findViewById(R.id.other_assert);

		house_loan = (TextView) findViewById(R.id.house_loan);
		car_loan = (TextView) findViewById(R.id.car_loan);
		other_loan = (TextView) findViewById(R.id.other_loan);

		repay_loan_house = (TextView) findViewById(R.id.repay_loan_house);
		repay_loan_car = (TextView) findViewById(R.id.repay_loan_car);
		repay_loan_other = (TextView) findViewById(R.id.repay_loan_other);

		
		//验证码
		tishi_str=(TextView) findViewById(R.id.lltishi_str);
		rel_msg_key=(RelativeLayout) findViewById(R.id.llrel_msg_key);
		lin_msg=(LinearLayout) findViewById(R.id.lllin_msg);
		bt_subkey=(TextView) findViewById(R.id.llsubmit);
		ed_key=(EditText) findViewById(R.id.llet_codekey);
		bt_subkey.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				if (isFlag) {
					//获取验证码
					new VertifyCodellKeyAsyncTask().execute(application.getBasicInfo().getMobilePhone(),"CODE_LOAN_APPLY");
				}
			}
		});
		
		if(!Contents.isChoiceMsgKey){
			rel_msg_key.setVisibility(View.GONE);
			lin_msg.setVisibility(View.GONE);
		}

		if(application != null) {
			LoginResponse response = application.getResponse();
			LoginMessage result = null;
			if(response != null){
				result = response.getResult();
			}
			BasicInfo basicInfo = application.getBasicInfo();
		/* 基本信息1 */
			Spanned text1 = Html.fromHtml("<p>姓名："
					+ ((result == null)? "" : result.getCipNamecn())
					+ "<p>性别：" + ((basicInfo == null)? "" : basicInfo.getSex()) + "</p>"
					+ "<p>身份证号码：" + ((result == null)? "" :result.getIdNo())
					+ "</p>" + "<p>出生年月："
					+ ((basicInfo == null)? "" : basicInfo.getBirthday()) + "</p>"
					+ "<p>证件有效期：" + ((basicInfo == null)? "" : basicInfo.getExpiryStartDate())
					+ "至" + ((basicInfo == null)? "" : basicInfo.getExpiryEndDate()) + "</p>"
					+ "<p>户籍地址：" + ((basicInfo == null)? "" : basicInfo.getBirthPlace())
					+ "</p>" + "<p>户籍邮编："
					+ ((basicInfo == null)? "" : basicInfo.getHousehold_email()) + "</p>");
			confirm_text1.setText(text1.toString());
		/* 基本信息2 */
			Spanned text2 = Html
					.fromHtml("<p>婚姻状况："
							+ ((basicInfo == null)? "" : basicInfo.getMarriageStatue())
							+ "</p>" + "<p>供养人数："
							+ ((basicInfo == null)? "" : basicInfo.getProvidePerson())
							+ "</p>" + "<p>现居住地址："
							+ ((basicInfo == null)? "" : basicInfo.getLiveAddress()) + "</p>"
							+ "<p>邮编："
							+ ((basicInfo == null)? "" : basicInfo.getLivePostcode()) + "</p>"
							+ "<p>住宅性质："
							+ ((basicInfo == null)? "" : basicInfo.getLiveProperties())
							+ "</p>" + "<p>文化程度："
							+ ((basicInfo == null)? "" : basicInfo.getCultureDegree())
							+ "</p>" + "<p>住宅电话："
							+ ((basicInfo == null)? "" : basicInfo.getZoneCode()) + "-"
							+ ((basicInfo == null)? "" : basicInfo.getTelPhone()) + "</p>"
							+ "<p>电子邮箱："
							+ ((basicInfo == null)? "" : basicInfo.getElecTronEmail())
							+ "</p>" + "<p>手机号码："
							+ ((basicInfo == null)? "" : basicInfo.getMobilePhone())

							+ "</p>");
			confirm_text2.setText(text2.toString());
			System.out.println("shoujihao" + ((basicInfo == null)? "" : basicInfo.getMobilePhone()));
		/* 职业信息3 */
			ProfessionalInfo profess = application.getProfessionInfo();
			Spanned text3 = Html.fromHtml("<p>现单位名称："
					+ ((profess == null)? "" : profess.getCompanyName()) + "</p>"
					+ "<p>任职部门："
					+ ((profess == null)? "" : profess.getRzDepartment()) + "</p>"
					+ "<p>本单位工作年限：" + ((profess == null)? "" : profess.getWorkYear())
					+ "年</p>" + "<p>总工作年限："
					+ ((profess == null)? "" : profess.getTotalWorkYear()) + "年</p>"
					+ "<p>单位地址："
					+ ((profess == null)? "" : profess.getCompanyAddress()) + "</p>"
					+ "<p>邮编：" + ((profess == null)? "" : profess.getCompanyMail())
					+ "</p>" + "<p>单位电话(区号)："
					+ ((profess == null)? "" : profess.getCompanyAreaNum()) + "</p>"
					+ "<p>电话号码："
					+ ((profess == null)? "" : profess.getCompanytelNum()) + "</p>"
					+ "<p>分机号："
					+ ((profess == null)? "" : profess.getCompanyExtensionNum())
					+ "</p>" + "<p>单位规模："
					+ ((profess == null)? "" : profess.getDpScale()) + "</p>"
					+ "<p>单位性质：" + ((profess == null)? "" : profess.getDpNature())
					+ "</p>" + "<p>行业性质："
					+ ((profess == null)? "" : profess.getTradeNature()) + "</p>"
					+ "<p>所在职位：" + ((profess == null)? "" : profess.getNowRole())
					+ "</p>" + "<p>申请人月收入："
					+ ((profess == null)? "" : profess.getMoneyMonth()) + "元</p>");
			confirm_text3.setText(text3.toString());

		/* 资产信息4 */
			AssertInfo assertInfo = application.getAssertInfo();
			house_assert.setText(((assertInfo == null)? "" : assertInfo.getHouseMoney()));
			car_assert.setText(((assertInfo == null)? "" : assertInfo.getCarMoney()));
			other_assert.setText(((assertInfo == null)? "" : assertInfo.getOtherMoney()));

			house_loan.setText(((assertInfo == null)? "" : assertInfo.getDaikuanHouse()));
			car_loan.setText(((assertInfo == null)? "" : assertInfo.getDaikuanCar()));
			other_loan.setText(((assertInfo == null)? "" : assertInfo.getDaikuanOher()));

			repay_loan_house.setText(((assertInfo == null)? "" : assertInfo.getPayHouse()));
			repay_loan_car.setText(((assertInfo == null)? "" : assertInfo.getPayCar()));
			repay_loan_other.setText(((assertInfo == null)? "" : assertInfo.getPayOher()));

			Spanned text4 = Html.fromHtml("<p>是否有银行信用卡："
					+ ((assertInfo == null)? "" : assertInfo.getIsHavingCreditCard()) + "</p>"
					+ "<p>单张信用卡的最高额度："
					+ ((assertInfo == null)? "" : assertInfo.getCardTopMoney()) + "</p>"
					+ "<p>发卡银行名称：" + ((assertInfo == null)? "" : assertInfo.getCardBankName())
					+ "</p>");
			confirm_text4.setText(text4.toString());
		/* 贷款申请5 */
			LoanApplyPersonalInfo applyPersonalInfo = application.getApplyPersonalInfo();
			Spanned text5 = Html.fromHtml("<p>申请人贷款金额："
					+ ((applyPersonalInfo == null)? "" : applyPersonalInfo.getApply_amount_value())
					+ "元" + "</p>" + "<p>贷款用途："
					+ ((applyPersonalInfo == null)? "" : applyPersonalInfo.getTick_loan_pursose())
					+ "</p>" + "<p>还款方式："
					+ ((applyPersonalInfo == null)? "" : applyPersonalInfo.getTick_loan_method())
					+ "</p>" + "<p>申请贷款期限："
					+ ((applyPersonalInfo == null)? "" : applyPersonalInfo.getApplyLoanTime())
					+ "</p>" + "<p>信件邮件地址："
					+ ((applyPersonalInfo == null)? "" : applyPersonalInfo.getSend_method()) + "</p>"
					+ "<p>还款账号开户行："
					+ ((applyPersonalInfo == null)? "" : applyPersonalInfo.getRepay_acount_type())
//				+ "</p>" + "<p>户名："
//				+ application.getApplyPersonalInfo().getUsername_value()
					+ "</p>" + "<p>账号："
					+ ((applyPersonalInfo == null)? "" : applyPersonalInfo.getAccount_value())
//				+ "</p>" + "<p>证件号码："
//				+ application.getApplyPersonalInfo().getAdentify_num() + "</p>"
					+ "<p>推广员工ID："
					+ ((applyPersonalInfo == null)? "" : applyPersonalInfo.getEmployee_id()) + "</p>"
					+ "<p>推广员工姓名："
					+ ((applyPersonalInfo == null)? "" : applyPersonalInfo.getEmployee_name())
					+ "</p>");
			confirm_text5.setText(text5.toString());

		/* 联系人信息6 */
			LinkManInfo linkMan = application.getLinkManInfo();
			Spanned text6 = Html.fromHtml("<p>直系亲属联系人："
					+ ((linkMan == null)? "" : linkMan.getContactName1()) + "</p>"
					+ "<p>于申请人关系："
					+ ((linkMan == null)? "" : linkMan.getContactRelation1()) + "</p>"
					+ "<p>住宅电话："
					+ ((linkMan == null)? "" : linkMan.getArea_telphone1zone()) + "-"
					+ ((linkMan == null)? "" : linkMan.getArea_telphone1()) + "</p>"
					+ "<p>手机号码：" + ((linkMan == null)? "" : linkMan.getMobileNo1())
					+ "</p>" +

					"<p>第二联系人：" + ((linkMan == null)? "" : linkMan.getContactName2())
					+ "</p>" + "<p>于申请人关系："
					+ ((linkMan == null)? "" : linkMan.getContactRelation2()) + "</p>"
					+ "<p>住宅电话："
					+ ((linkMan == null)? "" : linkMan.getArea_telphone2zone()) + "-"
					+ ((linkMan == null)? "" : linkMan.getArea_telphone2()) + "</p>"
					+ "<p>手机号码：" + ((linkMan == null)? "" : linkMan.getMobileNo2())
					+ "</p>" +

					"<p>第三联系人：" + ((linkMan == null)? "" : linkMan.getContactName3())
					+ "</p>" + "<p>于申请人关系："
					+ ((linkMan == null)? "" : linkMan.getContactRelation3()) + "</p>"
					+ "<p>住宅电话："
					+ ((linkMan == null)? "" : linkMan.getArea_telphone3zone()) + "-"
					+ ((linkMan == null)? "" : linkMan.getArea_telphone3()) + "</p>"
					+ "<p>手机号码：" + ((linkMan == null)? "" : linkMan.getMobileNo3())
					+ "</p>" +

					"<p>第四联系人：" + ((linkMan == null)? "" : linkMan.getContactName4())
					+ "</p>" + "<p>于申请人关系："
					+ ((linkMan == null)? "" : linkMan.getContactRelation4()) + "</p>"
					+ "<p>住宅电话："
					+ ((linkMan == null)? "" : linkMan.getArea_telphone4zone()) + "-"
					+ ((linkMan == null)? "" : linkMan.getArea_telphone4()) + "</p>"
					+ "<p>手机号码：" + ((linkMan == null)? "" : linkMan.getMobileNo4())
					+ "</p>");
			confirm_text6.setText(text6.toString());
		}
		imageView1 = (ImageView) findViewById(R.id.imageView1);
		setImageViewBitmap(MiniPersonInfo.compress_photoPath1, imageView1);
		imageView2 = (ImageView) findViewById(R.id.imageView2);
		setImageViewBitmap(MiniPersonInfo.compress_photoPath2, imageView2);
		imageView3 = (ImageView) findViewById(R.id.imageView3);
		setImageViewBitmap(MiniPersonInfo.compress_photoPath3, imageView3);
		imageView4 = (ImageView) findViewById(R.id.imageView4);
		View tv_qita = findViewById(R.id.tv_qita);
		if (MiniPersonInfo.compress_photoPath4 != null
				&& !"".equals(MiniPersonInfo.compress_photoPath4)) {
			setImageViewBitmap(MiniPersonInfo.compress_photoPath4, imageView4);
		} else {
			imageView4.setVisibility(View.GONE);
			tv_qita.setVisibility(View.GONE);
		}
		imageView5 = (ImageView) findViewById(R.id.imageView5);
		if (MiniPersonInfo.compress_photoPath5 != null
				&& !"".equals(MiniPersonInfo.compress_photoPath5)) {
			setImageViewBitmap(MiniPersonInfo.compress_photoPath5, imageView5);
		} else {
			imageView5.setVisibility(View.GONE);
			imagetext.setVisibility(View.GONE);
		}
		View imagetext1 = findViewById(R.id.imagetext1);
		imageView51 = (ImageView) findViewById(R.id.imageView51);
		if (MiniPersonInfo.compress_photoPath51 != null

		&& !"".equals(MiniPersonInfo.compress_photoPath51)) {
			setImageViewBitmap(MiniPersonInfo.compress_photoPath51, imageView51);
			imageView51.setVisibility(View.VISIBLE);
			imagetext1.setVisibility(View.VISIBLE);
		} else {
			imageView51.setVisibility(View.GONE);
			imagetext1.setVisibility(View.GONE);
		}
		imageView52 = (ImageView) findViewById(R.id.imageView52);
		View imagetext2 = findViewById(R.id.imagetext2);
		if (MiniPersonInfo.compress_photoPath52 != null

		&& !"".equals(MiniPersonInfo.compress_photoPath52)) {
			setImageViewBitmap(MiniPersonInfo.compress_photoPath52, imageView52);
			imageView52.setVisibility(View.VISIBLE);
			imagetext2.setVisibility(View.VISIBLE);
		} else {
			imageView52.setVisibility(View.GONE);
			imagetext2.setVisibility(View.GONE);
		}
		imageView53 = (ImageView) findViewById(R.id.imageView53);
		View imagetext3 = findViewById(R.id.imagetext3);
		if (MiniPersonInfo.compress_photoPath53 != null
				&& !"".equals(MiniPersonInfo.compress_photoPath53)) {
			setImageViewBitmap(MiniPersonInfo.compress_photoPath53, imageView53);
			imageView53.setVisibility(View.VISIBLE);
			imagetext3.setVisibility(View.VISIBLE);
		} else {
			imageView53.setVisibility(View.GONE);
			imagetext3.setVisibility(View.GONE);
		}
		imageView54 = (ImageView) findViewById(R.id.imageView54);
		View imagetext4 = findViewById(R.id.imagetext4);
		if (MiniPersonInfo.compress_photoPath54 != null
				&& !"".equals(MiniPersonInfo.compress_photoPath54)) {
			setImageViewBitmap(MiniPersonInfo.compress_photoPath54, imageView54);
			imageView54.setVisibility(View.VISIBLE);
			imagetext4.setVisibility(View.VISIBLE);
		} else {
			imageView54.setVisibility(View.GONE);
			imagetext4.setVisibility(View.GONE);
		}
		imageView55 = (ImageView) findViewById(R.id.imageView55);
		View imagetext5 = findViewById(R.id.imagetext5);
		if (MiniPersonInfo.compress_photoPath55 != null
				&& !"".equals(MiniPersonInfo.compress_photoPath55)) {
			setImageViewBitmap(MiniPersonInfo.compress_photoPath55, imageView55);
			imageView55.setVisibility(View.VISIBLE);
			imagetext5.setVisibility(View.VISIBLE);
		} else {
			imageView55.setVisibility(View.GONE);
			imagetext5.setVisibility(View.GONE);
		}
		imageView1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(RelaxedLoanNineCommitInfo.this,
						PreviewActivity.class);
				intent.putExtra("image_view", "1");
				startActivity(intent);
				overridePendingTransition(R.anim.fading_in, R.anim.fading_out);
			}
		});

		imageView2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(RelaxedLoanNineCommitInfo.this,
						PreviewActivity.class);
				intent.putExtra("image_view", "2");
				startActivity(intent);
				overridePendingTransition(R.anim.fading_in, R.anim.fading_out);
			}
		});

		imageView3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(RelaxedLoanNineCommitInfo.this,
						PreviewActivity.class);
				intent.putExtra("image_view", "3");
				startActivity(intent);
				overridePendingTransition(R.anim.fading_in, R.anim.fading_out);
			}
		});

		imageView4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(RelaxedLoanNineCommitInfo.this,
						PreviewActivity.class);
				intent.putExtra("image_view", "4");
				startActivity(intent);
				overridePendingTransition(R.anim.fading_in, R.anim.fading_out);
			}
		});
		imageView5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (MiniPersonInfo.compress_photoPath5 != null) {
					Intent intent = new Intent(RelaxedLoanNineCommitInfo.this,
							PreviewActivity.class);
					intent.putExtra("image_view", "5");
					startActivity(intent);
					overridePendingTransition(R.anim.fading_in,
							R.anim.fading_out);
				}
			}
		});
	}
	private void getProInfo(){
		TelephonyManager telephonyManager= (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		IMEI = telephonyManager.getDeviceId();
//		BobLog.print("IMEI ------------->" + IMEI);
//		BobLog.print("操作系统名称 ------------->" + android.os.Build.VERSION.INCREMENTAL);
//		BobLog.print("操作系统版本 ------------->" + android.os.Build.VERSION.RELEASE);
		PackageManager packageManager = getPackageManager();
          // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo;
//		try {
//			packInfo = packageManager.getPackageInfo(getPackageName(),0);
//			appVersion = packInfo.versionName;
////			BobLog.print("应用版本 ------------->" + appVersion);
//		} catch (NameNotFoundException e) {
//			e.printStackTrace();
//		}
//		BobLog.print("设备制造商 ------------->" + android.os.Build.MANUFACTURER);
//		BobLog.print("设备型号 ------------->" + android.os.Build.MODEL);
//		BobLog.print("设备名称 ------------->" + android.os.Build.PRODUCT);
	}
	private void setImageViewBitmap(String path, ImageView imageView) {
		Bitmap bitmap = ViewTools.getBitMap(path, 10);
		imageView.setImageBitmap(bitmap);
		// listBitmaps.add(bitmap);
	}

	@Override
	protected void onStop() {
		super.onStop();
		// for (Bitmap bitmap : listBitmaps) {
		// bitmap.recycle();
		// bitmap = null;
		// }
		// listBitmaps.clear();
		// listBitmaps = null;
		// System.gc();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		activity = null;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.confirm_button:
			// Toast.makeText(RelaxedLoanNineCommitInfo.this, "点击确认按钮",
			// Toast.LENGTH_LONG).show();
			confirm_button.setClickable(false);
			//验证码===========》判断
			//需要验证
			if(Contents.isChoiceMsgKey){
			String st_ed_key = ed_key.getText().toString();
			if(st_ed_key==null||"".equals(st_ed_key)){
				DialogUtil.showDialogOneButton2(RelaxedLoanNineCommitInfo.this,
						"请输入验证码！");
				confirm_button.setClickable(true);
				return;
			}
			new CommitKeysyncllTask().execute(application.getBasicInfo().getMobilePhone(),st_ed_key);

			}else{
//不需要验证
				
				 getProInfo();
				if (Contents.isChoice) {
//					RapidlyLoanInfoContents rapidlyLoanInfoContents = new RapidlyLoanInfoContents();
//					applCde = rapidlyLoanInfoContents.MiniConfirmMessage
//							.getApplCde();// 申请编号
//					applSeq = rapidlyLoanInfoContents.MiniConfirmMessage
//							.getApptSeq();// 申请流水号
	//
//					applDisbSeq = rapidlyLoanInfoContents.MiniConfirmMessage
//							.getApplDisbSeq();
//					applRpymSeq = rapidlyLoanInfoContents.MiniConfirmMessage
//							.getApplRpymSeq();
//					apptSeq = rapidlyLoanInfoContents.MiniConfirmMessage
//							.getApptSeq();
	//
//					MiniPersonInfo.applCde = applCde;
//					MiniPersonInfo.applSeq = applSeq;
	//
//					MiniPersonInfo.applDisbSeq = applDisbSeq;
//					MiniPersonInfo.applRpymSeq = applRpymSeq;
//					MiniPersonInfo.apptSeq = apptSeq;
					replyMiniCard();
				} else {
					new GetSeqAsyncTask().execute();
				}
				
				
				
			}
			
			
			


			break;
		default:
			break;
		}
		super.onClick(v);
	}

	// Mini卡申请最终提交页面
	private void replyMiniCard() {
		// new ApplyAsyncTask().execute();
		// new HttpMultipartPost(RelaxedLoanNineCommitInfo.this);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<PhotoInfo> photo = new ArrayList<PhotoInfo>();
		Map<String, Object> map = new HashMap<String, Object>();
		BasicInfo basicInfo = application.getBasicInfo();
		LoginResponse response = application.getResponse();
		LoginMessage result = null;
		if(response != null){
			result = response.getResult();
		}
		LinkManInfo linkMan = application.getLinkManInfo();
		ProfessionalInfo profess = application.getProfessionInfo();
		LoanApplyPersonalInfo applyPersonalInfo = application.getApplyPersonalInfo();
		AssertInfo assertInfo = application.getAssertInfo();

		map.put("indivLiveZip", ((basicInfo == null)? "" : basicInfo.getLivePostcode()));
		// 客户名称 custName 必填
		// 本单位工作年限 indivEmpYrs 必填
		// 总工作年限 indivWorkYrs 必填
		// 亲属联系人姓名 indivRelName 必填
		// 亲属联系人工作单位名称 indivRelEmp 必填
		map.put("custName", ((result == null)? "" : result.getCipNamecn()));
		map.put("indivEmpYrs", ((profess == null)? "" : profess.getWorkYear()));
		map.put("indivWorkYrs", ((profess == null)? "" : profess.getTotalWorkYear()));
		map.put("indivRelName", ((linkMan == null)? "" : linkMan.getContactName1()));
		map.put("indivRelEmp", "");

		// 与联系人工作关系 indivRelation 必填,参见字典项
		// 联系人区号 indivRelZone 必填
		// 联系人住宅电话号码 indivRelPhone 必填
		// 联系人手机号 indivRelMobile 必填
		// 文化程度 indivDegree 必填，参见字典项
		map.put("indivRelation", ((linkMan == null)? "" : linkMan
				.getContactRelation1Code()));
		map.put("indivRelZone", ((linkMan == null)? "" : linkMan
				.getArea_telphone1zone()));
		map.put("indivRelPhone", ((linkMan == null)? "" : linkMan
				.getArea_telphone1()));
		map.put("indivRelMobile", ((linkMan == null)? "" : linkMan.getMobileNo1()));
		map.put("indivDegree", ((basicInfo == null)? "" : basicInfo
				.getCultureDegreeCode()));

		// 第三联系人信息
		map.put("lcAppRelations[0].indivRelName", ((linkMan == null)? "" : linkMan
				.getContactName3()));
		map.put("lcAppRelations[0].indivRelEmp", "");
		map.put("lcAppRelations[0].indivRelation", ((linkMan == null)? "" : linkMan
				.getContactRelation3Code()));
		map.put("lcAppRelations[0].indivRelZone", ((linkMan == null)? "" : linkMan
				.getArea_telphone3zone()));
		map.put("lcAppRelations[0].indivRelPhone", ((linkMan == null)? "" : linkMan
				.getArea_telphone3()));
		map.put("lcAppRelations[0].indivRelMobile", ((linkMan == null)? "" : linkMan
				.getMobileNo3()));
		map.put("lcAppRelations[0].indivRelTyp", "03");
		// 第四联系人信息
		map.put("lcAppRelations[1].indivRelName", ((linkMan == null)? "" : linkMan
				.getContactName4()));
		map.put("lcAppRelations[1].indivRelEmp", "");
		map.put("lcAppRelations[1].indivRelation", ((linkMan == null)? "" : linkMan
				.getContactRelation4Code()));
		map.put("lcAppRelations[1].indivRelZone", ((linkMan == null)? "" : linkMan
				.getArea_telphone4zone()));
		map.put("lcAppRelations[1].indivRelPhone", ((linkMan == null)? "" : linkMan
				.getArea_telphone4()));
		map.put("lcAppRelations[1].indivRelMobile", ((linkMan == null)? "" : linkMan.getMobileNo4()));
		map.put("lcAppRelations[1].indivRelTyp", "04");
		// 证件号码 idNo 必填，参见字典项
		// 国籍 idCity 必填，参见字典项
		// 非亲属联系人姓名 indivOthName 必填
		// 非亲属联系人单位名称 indivOthEmp 必填
		// 非亲属联系人关系 indivOthRel 必填
		map.put("idNo", ((result == null)? "" : result.getIdNo()));
		map.put("idCity", "CHN");
		map.put("indivOthName", ((linkMan == null)? "" : linkMan.getContactName2()));
		map.put("indivOthEmp", "");
		map.put("indivOthRel", ((linkMan == null)? "" : linkMan
				.getContactRelation2Code()));
		// 非亲属联系人单位(电话号码) indivOthPhone 必填
		// 非亲属联系人电话(区号) indivOthZone 必填
		// 非亲属联系人手机号 indivOthMobile 必填
		// 证件有效期(开始) idTermBgn 非必填
		// 证件有效期(结束) idTermEnd 非必填
		map.put("indivOthPhone", ((linkMan == null)? "" : linkMan
				.getArea_telphone2()));
		map.put("indivOthZone", ((linkMan == null)? "" : linkMan
				.getArea_telphone2zone()));
		map.put("indivOthMobile", ((linkMan == null)? "" : linkMan.getMobileNo2()));
		map.put("idTermBgn", ((basicInfo == null)? "" : basicInfo.getExpiryStartDate()));
		map.put("idTermEnd", ((basicInfo == null)? "" : basicInfo.getExpiryEndDate()));
		// 出生日期 apptStartDate 必填
		// 年龄 apptAge 非必填
		// 性别 indivSex 必填，参见字典项
		// 供养人数 indivDepNo 必填
		// 婚姻情况 indivMarital 必填，参见字典项
		map.put("apptStartDate", ((basicInfo == null)? "" : basicInfo.getBirthday()));
		map.put("apptAge", "");
		if (basicInfo!= null && basicInfo.getSex()!= null && basicInfo.getSex().equals("男")){
			map.put("indivSex", "1");
		} else {
			map.put("indivSex", "2");
		}
		map.put("indivDepNo", ((basicInfo == null)? "" : basicInfo.getProvidePerson()));
		map.put("indivMarital", ((basicInfo == null)? "" : basicInfo.getMarriageStatueCode()));
		// 现居住住房性质 indivMarital 必填，参见字典项
		// 月租金(元) indivRentAmt 非必填， 居住性质选择为租住时，填写该项
		// 现居住地址 indivLiveAddr 必填
		// 户籍地址 indivRegAddr 必填
		// 住宅电话_区号 indivLiveZone 必填
		map.put("indivLive", ((basicInfo == null)? "" : basicInfo.getLivePropertiesCode()));
		map.put("indivRentAmt", ((basicInfo == null)? "" : basicInfo.getIndivRentAmt()));
		map.put("indivLiveAddr", ((basicInfo == null)? "" : basicInfo.getLiveAddress()));
		map.put("indivRegAddr", ((basicInfo == null)? "" : basicInfo.getBirthPlace()));

		map.put("indivRegZip", ((basicInfo == null)? "" : basicInfo.getHousehold_email()));
		map.put("indivLiveZone", ((basicInfo == null)? "" : basicInfo.getZoneCode()));
		// 住宅电话_电话号码 indivLivePhone 必填
		// 个人工资月收入(元) indivMthInc 必填
		// 手机号码 indivMobile 必填
		// 手机号码 indivEmail 非必填
		// 现单位名称 indivEmp 必填
		map.put("indivLivePhone", ((basicInfo == null)? "" : basicInfo.getTelPhone()));
		map.put("indivMthInc", ((profess == null)? "" : profess.getMoneyMonth()));
		System.out.println("shoujihao"+((basicInfo == null)? "" : basicInfo.getMobilePhone()));
		map.put("indivMobile", ((basicInfo == null)? "" : basicInfo.getMobilePhone()));
		map.put("indivEmail", ((basicInfo == null)? "" : basicInfo.getElecTronEmail()));
		map.put("indivEmp", ((profess == null)? "" : profess.getCompanyName()));
		// 任职部门 indivBranch 非必填
		// 单位地址 indivEmpAddr 非必填
		// 单位电话_区号 indivEmpZone 非必填
		// 单位电话_电话号码 indivEmpTelNo 必填
		// 单位电话_分机号 indivEmpTelSub 非必填
		map.put("indivBranch", ((profess == null)? "" : profess.getRzDepartment()));
		map.put("indivEmpAddr", ((profess == null)? "" : profess.getCompanyAddress()));
		map.put("indivEmpZone", ((profess == null)? "" : profess.getCompanyAreaNum()));
		map.put("indivEmpTelNo", ((profess == null)? "" : profess.getCompanytelNum()));
		map.put("indivEmpTelSub", ((profess == null)? "" : profess.getCompanyExtensionNum()));
		// 联络员工姓名 pomsStaff 必填，获取登录用户id传入server端
		// 是否开通免费短信通知服务 mssInd 必填，Y---是，N---否
		// 机构码 bchCde 必填，获取登录用户组织机构传入server端
		// 贷款用途 purpose 必填，参见字典项
		// 其他用途 otherPurpose 非必填
		map.put("pomsStaff", "");
		map.put("mssInd", "Y");
		map.put("bchCde", "999999999");
		map.put("purpose", ((applyPersonalInfo == null)? "" : applyPersonalInfo
				.getTick_loan_pursoseCode()));
		map.put("otherPurpose", ((applyPersonalInfo == null)? "" : applyPersonalInfo
				.getTick_loan_other_purpose()));
		// 贷款申请金额 applyAmt 必填
		// 贷款申请期限(按月) applyTnr 必填，输入数值参见还款期限及方式接口
		// 还款方式 loanMtd 必填，输入数值参见还款期限及方式接口
		// 经销商代码 dealer 必填，前端传入登录用户组织机构
		// 推广员工ID/经办人工号 operator 必填，前端传入登录用户id
		map.put("applyAmt", ((applyPersonalInfo == null)? "" : applyPersonalInfo
				.getApply_amount_value()));
		map.put("applyTnr", ((applyPersonalInfo == null)? "" : applyPersonalInfo
				.getApplyLoanTimeCode()));
//===========================================================================
/*		map.put("loanMtd", application.getApplyPersonalInfo()
				.getTick_loan_methodCode());*/
		//		10.14
		//通过 还款期限 改方式  7<13  M049
		//>12  M050
		int appltTnr_int=Integer.parseInt(((applyPersonalInfo == null)? "" : applyPersonalInfo
				.getApplyLoanTimeCode()));
		if(7<appltTnr_int&&appltTnr_int<13){
			map.put("loanMtd", "M049");
		}
		if(appltTnr_int>12){
			map.put("loanMtd", "M050");	
		}
//==============================================================================		
		
		map.put("dealer", "999999999");
		map.put("operator", ((applyPersonalInfo == null)? "" : applyPersonalInfo.getEmployee_id()));
		// 推广员工签名/经办员工姓名 operatorName 必填，前端传入登录用户名称
		// 销售员工号 salerId 必填，前端传入登录用户id
		// 销售员工姓名 salerName 必填，前端传入登录用户名称
		// 专案代码 pcCode 必填，根据用户选择传入server
		// 放款账号开户行 applDisbAcBank 必填，参见字典项
		map.put("operatorName", ((applyPersonalInfo == null)? "" : applyPersonalInfo
				.getEmployee_name()));
		map.put("salerId", "");
		map.put("salerName", "");
		int money = Integer.parseInt(((applyPersonalInfo == null)? "" : applyPersonalInfo
				.getApply_amount_value()));
		if (Contents.response.getResult().getIsWhiteCust().equals("Y")) {
			map.put("pcCode", Contents.response.getResult().getPcCode());
			System.out.println("pcCode="
					+ ((result == null)? "" : result.getPcCode()
					+ "---bai="
					+ ((result == null)? "" : result.getIsWhiteCust())));
		} else {

			if (money >= 600 && money <= 50000) {
				map.put("pcCode", "Z100092");
			} else if (money > 50000 && money <= 200000) {
				map.put("pcCode", "Z100093");
			}
		}
		// map.put("pcCode", "Z100198");
		// 放款账号 applDisbAcNo 必填，参见字典项
		// 放款账户名 applDisbAcNam 必填，参见字典项
		// 还款账户开户行 applRpymAcBank 非必填，缺省的情况下，默认为还款账号与放款账号一致
		// 还款账号 applRpymAcNo 非必填，缺省的情况下，默认为还款账号与放款账号一致
		// 还款账号户名 applRpymAcNam 非必填，缺省的情况下，默认为还款账号与放款账号一致
		if(applyPersonalInfo!= null && applyPersonalInfo.getRepay_acount_type() != null) {
			if (applyPersonalInfo.getRepay_acount_type().equals("北京银行")) {
				//北京银行
				map.put("applDisbAcBank", "BOB");
				map.put("applRpymAcBank", "BOB");
			} else if (applyPersonalInfo.getRepay_acount_type().equals("农业银行")) {
				//农业银行
				map.put("applDisbAcBank", "ABC");
				map.put("applRpymAcBank", "ABC");
			}
		}
		if(applyPersonalInfo != null && applyPersonalInfo.getAccount_value()!=null){
			map.put("applDisbAcNo", ((applyPersonalInfo.getAccount_value()!=null)? applyPersonalInfo
					.getAccount_value().replace(" ", "") : ""));
			map.put("applRpymAcNo", ((applyPersonalInfo.getAccount_value()!=null)? applyPersonalInfo
					.getAccount_value().replace(" ", "") : ""));
		}
		map.put("applDisbAcNam", ((applyPersonalInfo == null)? "" : applyPersonalInfo
				.getUsername_value()));
		map.put("applRpymAcNam", ((applyPersonalInfo == null)? "" : applyPersonalInfo
				.getUsername_value()));
		// 渠道码 trenchNo 必填，前端获取登录用户组织机构号传入server
		// 网点号 branchNo 必填，前端获取登录用户组织机构号传入server
		// 放款信息流水号 applDisbSeq 必填，通过接口获取，参见获取流水号
		// 申请编号 applCde 必填，通过接口获取，参见获取流水号
		// 申请流水号 applSeq 必填，通过接口获取，参见获取流水号
		map.put("trenchNo", "YDKHDYB");
		map.put("branchNo", "SJGRB");
		map.put("applDisbSeq", MiniPersonInfo.applDisbSeq);
		map.put("applCde", MiniPersonInfo.applCde);
		map.put("applSeq", MiniPersonInfo.applSeq);
		// 还款信息流水号 applRpymSeq 必填，通过接口获取，参见获取流水号
		// 与贷款相关流水号 apptSeq 必填，通过接口获取，参见获取流水号
		// 职业 indivOccup 必填，参见字典项
		// 单位性质 indivEmpType 必填，参见字典项
		// 企业规模 indivEmpNum 必填，参见字典项
		map.put("applRpymSeq", MiniPersonInfo.applRpymSeq);
		map.put("apptSeq", MiniPersonInfo.apptSeq);
		map.put("indivOccup", ((profess == null)? "" : profess.getNowRoleCode()));
		map.put("indivEmpType", ((profess == null)? "" : profess.getDpNatureCode()));
		map.put("indivEmpNum", ((profess == null)? "" : profess.getDpScaleCode()));
		// 行业性质 indivIndtryPaper 必填，参见字典项
		// 现任职务 indivPosition 必填，参见字典项
		// 资产类型 asset[0].assetKind 必填，参见字典项
		// 资产性质 asset[0].assetTyp 必填，参见字典项
		// 金额 asset[0].assetAmtd 必填
		map.put("indivIndtryPaper", ((profess == null)? "" : profess.getTradeNatureCode()));
		map.put("indivPosition", ((profess == null)? "" : profess.getNowRoleCode()));
		map.put("asset[0].assetKind", "PPTY");
		map.put("asset[0].assetTyp", "ASSET");
		map.put("asset[0].assetAmt", ((assertInfo == null)? "" : assertInfo
				.getHouseMoney()));

		map.put("asset[1].assetKind", "VEC");
		map.put("asset[1].assetTyp", "ASSET");
		map.put("asset[1].assetAmt", ((assertInfo == null)? "" : assertInfo.getCarMoney()));

		map.put("asset[2].assetKind", "OTH");
		map.put("asset[2].assetTyp", "ASSET");
		map.put("asset[2].assetAmt", ((assertInfo == null)? "" : assertInfo
				.getOtherMoney()));
		// 月还款额 asset[0].assetMthAmt 必填
		// 贷款余额 asset[0].assetReAmt 必填
		map.put("asset[0].assetMthAmt", ((assertInfo == null)? "" : assertInfo
				.getPayHouse()));
		map.put("asset[1].assetMthAmt", ((assertInfo == null)? "" : assertInfo.getPayCar()));
		map.put("asset[2].assetMthAmt", ((assertInfo == null)? "" : assertInfo
				.getPayOher()));

		map.put("asset[0].assetReAmt", ((assertInfo == null)? "" : assertInfo
				.getDaikuanHouse()));
		map.put("asset[1].assetReAmt", ((assertInfo == null)? "" : assertInfo
				.getDaikuanCar()));
		map.put("asset[2].assetReAmt", ((assertInfo == null)? "" : assertInfo
				.getDaikuanOher()));

		map.put("crtUsr", ((result == null)? "" : result.getCipNamecn()));
		map.put("mailAddr", ((applyPersonalInfo == null)? "" : applyPersonalInfo
				.getSend_methodCode()));
		map.put("loanInstal", ((applyPersonalInfo == null)? "" : applyPersonalInfo
				.getApplyLoanTimeCode()));
		map.put("indivCardInd", ((assertInfo == null)? "" : assertInfo
				.getIsHavingCreditCard()));
		map.put("indivCardBank", ((assertInfo == null)? "" : assertInfo.getCardBankName()));
		map.put("indivCardAmt", ((assertInfo == null)? "" : assertInfo.getCardTopMoney()));

		// 版本号
		map.put("appVersion", getVerName(RelaxedLoanNineCommitInfo.this));
		map.put("indivEmpZip", ((profess == null)? "" : profess.getCompanyMail()));
		
		map.put("crtUsr", "MOBILE");
		//applyAddr-----
		map.put("applyAddr", Contents.locationInfo);
		System.out.println("dizhi--->addr>>"+Contents.locationInfo);
		
		//加设备信息等-----11.12---------.
		//设备ID
		map.put("deviceId", IMEI);
		//设备型号
		map.put("deviceType", android.os.Build.MODEL);
		//设备名称
		map.put("deviceName", android.os.Build.PRODUCT);
		//设备制造商
		map.put("mfrs", android.os.Build.MANUFACTURER);
		//操作系统名称
		map.put("osName",android.os.Build.VERSION.INCREMENTAL);
		//操作系统版本
		map.put("osVersion", android.os.Build.VERSION.RELEASE);
		//应用类型 
		map.put("appName", "AndroidPhone");
		
		// -------------------------数据待定------------------------------------
		if (Contents.isChoice) {
//			map.put("scordeGrade", "");
//			map.put("spreadRemark", "");
//			map.put("applSrc", "");
//			map.put("spreadType", "");
//
//			map.put("ip", "");
//			map.put("assetStr", "");
//			map.put("appContactStr", "");
//			map.put("lcAppRelationStr", "");
//			map.put("goodStr", "");
//			map.put("inputDt", "");
//			map.put("indivPhoneChk", "");
//			map.put("remark", "");
//			map.put("relaVeriDt", "");
//			map.put("apptVeriDt", "");
//			map.put("relaVeriFlag", "");
//			map.put("tellMatt", "");
//			map.put("applMate", "");
//			map.put("applCircs", "");
//			map.put("mtdTyp", "01");
//			map.put("state", "000");
//			map.put("applAcctInd", "PERSON");
//			map.put("txCde", "02");
			map.put("txType", "02");
//			map.put("freqUnit", "1");
//			map.put("form", "LcApplNor");
//			// 最后更改时间
//			map.put("lastChgDt", "");
//			map.put("groupNo", "");
//			map.put("switchNo", "");

			// 代表轻松贷
			map.put("applyType", "0");
			
			map.put("apprvAmt", ((applyPersonalInfo == null)? "" : applyPersonalInfo
					.getApply_amount_value()));
			map.put("apprvTnr", ((applyPersonalInfo == null)? "" : applyPersonalInfo
					.getApplyLoanTimeCode()));
			
		}
		list.add(map);
		
		//获取时间
		SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMddHHmmss");       
		Date  curDate= new Date(System.currentTimeMillis());//获取当前时间       
		String t= formatter.format(curDate); 
		
		photo.add(new PhotoInfo(MiniPersonInfo.applCde + "01",
				MiniPersonInfo.applCde, "SFZZM", "LCDOC",
				MiniPersonInfo.applCde +t +"01" + ".jpg", "999",
				MiniPersonInfo.compress_photoPath1, "身份证正面"));
		photo.add(new PhotoInfo(MiniPersonInfo.applCde + "02",
				MiniPersonInfo.applCde, "SFZFM", "LCDOC",
				MiniPersonInfo.applCde + t +"02" + ".jpg", "999",
				MiniPersonInfo.compress_photoPath2, "身份证反面"));
		photo.add(new PhotoInfo(MiniPersonInfo.applCde + "03",
				MiniPersonInfo.applCde, "KHZP", "LCDOC", MiniPersonInfo.applCde
						+ t +"03" + ".jpg", "999",
				MiniPersonInfo.compress_photoPath3, "客户照片"));
		if (MiniPersonInfo.compress_photoPath4 != null
				&& !"".equals(MiniPersonInfo.compress_photoPath4)) {
			photo.add(new PhotoInfo(MiniPersonInfo.applCde + "04",
					MiniPersonInfo.applCde, "RHZXBG", "LCDOC",
					MiniPersonInfo.applCde + t +"04" + ".jpg", "999",
					MiniPersonInfo.compress_photoPath4, "人行征信报告"));
		}
		if (MiniPersonInfo.compress_photoPath5 != null
				&& !"".equals(MiniPersonInfo.compress_photoPath5)) {
			photo.add(new PhotoInfo(MiniPersonInfo.applCde + "05",
					MiniPersonInfo.applCde, "QT", "LCDOC",
					MiniPersonInfo.applCde + t +"05" + ".jpg", "999",
					MiniPersonInfo.compress_photoPath5, "其它"));
		}

		if (MiniPersonInfo.compress_photoPath51 != null

		&& !"".equals(MiniPersonInfo.compress_photoPath51)) {
			photo.add(new PhotoInfo(MiniPersonInfo.applCde + "06",
					MiniPersonInfo.applCde, "RHZXBG", "LCDOC",
					MiniPersonInfo.applCde + t +"06" + ".jpg", "999",
					MiniPersonInfo.compress_photoPath51, "人行征信报告1"));
		}

		if (MiniPersonInfo.compress_photoPath52 != null
				&& !"".equals(MiniPersonInfo.compress_photoPath52)) {
			photo.add(new PhotoInfo(MiniPersonInfo.applCde + "07",
					MiniPersonInfo.applCde, "RHZXBG", "LCDOC",
					MiniPersonInfo.applCde + t +"07" + ".jpg", "999",
					MiniPersonInfo.compress_photoPath52, "人行征信报告2"));
		}
		if (MiniPersonInfo.compress_photoPath53 != null
				&& !"".equals(MiniPersonInfo.compress_photoPath53)) {
			photo.add(new PhotoInfo(MiniPersonInfo.applCde + "08",
					MiniPersonInfo.applCde, "RHZXBG", "LCDOC",
					MiniPersonInfo.applCde + t +"08" + ".jpg", "999",
					MiniPersonInfo.compress_photoPath53, "人行征信报告3"));
		}
		if (MiniPersonInfo.compress_photoPath54 != null
				&& !"".equals(MiniPersonInfo.compress_photoPath54)) {
			photo.add(new PhotoInfo(MiniPersonInfo.applCde + "09",
					MiniPersonInfo.applCde, "RHZXBG", "LCDOC",
					MiniPersonInfo.applCde + t +"09" + ".jpg", "999",
					MiniPersonInfo.compress_photoPath54, "人行征信报告4"));
		}
		if (MiniPersonInfo.compress_photoPath55 != null
				&& !"".equals(MiniPersonInfo.compress_photoPath55)) {
			photo.add(new PhotoInfo(MiniPersonInfo.applCde + "10",
					MiniPersonInfo.applCde, "RHZXBG", "LCDOC",
					MiniPersonInfo.applCde +t + "10" + ".jpg", "999",
					MiniPersonInfo.compress_photoPath55, "人行征信报告5"));
		}
		new HttpMultipartPostAddMore(RelaxedLoanNineCommitInfo.this, photo,
				ContantsAddress.DOC_UPLOAD, list, ContantsAddress.APPLAY_RALEX);

	}

	// 获取版本名
	public String getVerName(Context context) {
		String verName = "";
		try {
			verName = context.getPackageManager().getPackageInfo(
					"com.yucheng.byxf.mini.ui", 0).versionName;
		} catch (NameNotFoundException e) {
			LogManager.i(MyLoginActivity.class, e.getMessage());
		}
		return verName;
	}

	class GetSeqAsyncTask extends AsyncTask<String, Object, Object> {

    @Override
    protected Object doInBackground(String... params) {
      HttpHelper httpHelper = HttpHelper.getInstance(RelaxedLoanNineCommitInfo.this);
      List<NameValuePair> arg = new ArrayList<NameValuePair>();
      return httpHelper.post(ContantsAddress.GET_SEQ, arg, MiniConfirmResponse.class);
    }

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog();
		}

		@Override
		protected void onPostExecute(Object result) {
			// dismissProgressDialog();
			super.onPostExecute(result);
			if (activity != null) {
				dismissProgressDialog();
				MiniConfirmResponse response = (MiniConfirmResponse) result;
				if (response != null) {
					if (RET_CODE == response.getCode()) {
						MiniConfirmMessage message = response.getResult();
						applCde = message.getApplCde();// 申请编号
						applSeq = message.getApplSeq();// 申请流水号

						applDisbSeq = message.getApplDisbSeq();
						applRpymSeq = message.getApplRpymSeq();
						apptSeq = message.getApptSeq();

						MiniPersonInfo.applCde = applCde;
						MiniPersonInfo.applSeq = applSeq;

						MiniPersonInfo.applDisbSeq = applDisbSeq;
						MiniPersonInfo.applRpymSeq = applRpymSeq;
						MiniPersonInfo.apptSeq = apptSeq;
						// TODO:解析获取申请编码，申请流水号传入下一次联网。
						replyMiniCard();
					} else {
						System.out.println(response.getMsg());
						Toast.makeText(getApplicationContext(), "获取申请流水号失败！", Toast.LENGTH_LONG)
								.show();
						confirm_button.setClickable(true);
					}
				} else {
					Toast.makeText(getApplicationContext(), getResources().getString(R.string.no_network), Toast.LENGTH_LONG).show();
					confirm_button.setClickable(true);
					try {
						String s = null;
						System.out.println(s);
					} catch (NullPointerException e) {
						// TODO: handle exception
						System.out.println(e);
					}
				}
			}

		}
	}
	
	
	//验证码 相关操作
	
/*	
 * 获取 短信验证码的 相关操作
*/
	//定时器
	private void timer() {
		new Thread() {
			public void run() {
				while (time > 0) {
					time--;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					handler.sendEmptyMessage(0);

				}
				handler.sendEmptyMessage(1);
			};
		}.start();
	}
	
	//消息操作
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0) {
				bt_subkey.setText(time + "秒后可获取");
			} else if (msg.what == 1) {
				isFlag = !isFlag;
				bt_subkey.setBackgroundResource(R.drawable.timer);
				bt_subkey.setText("获取验证码");
			}
		};
	};
 //获取验证码------
	class VertifyCodellKeyAsyncTask extends AsyncTask<String, Object, Object> {
    @Override
    protected Object doInBackground(String... params) {
      HttpHelper httpHelper = HttpHelper.getInstance(RelaxedLoanNineCommitInfo.this);
      List<NameValuePair> arg = new ArrayList<NameValuePair>();
      arg.add(new BasicNameValuePair("phoneNum", params[0]));
      
      arg.add(new BasicNameValuePair("msgTemplate", params[1]));
      return httpHelper.post(ContantsAddress.VertifyCodellKey, arg, VerifyCodeResponse.class);
    }

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog();
		}

		@Override
		protected void onPostExecute(Object result) {
			dismissProgressDialog();
			super.onPostExecute(result);
			verifyKeyResponse(result);
		}
	}

	private void verifyKeyResponse(Object result) {
		VerifyCodeResponse response = (VerifyCodeResponse) result;
		if (response == null) {
			DialogUtil.createDialog(this, 1, "服务器请求异常!");
			return;
		} else {
			if (response.getCode() == 0) {
				time = response.getResult();
				//String str_num=loanInfo.getPhone().toString();
				String str_num=application.getBasicInfo().getMobilePhone();
				str_num=StringUtils.coverPhoneNumber(str_num);
				Spanned info2 = Html.fromHtml("动态验证码已发送至您本人手机号"+str_num+", "+time+"秒后可重新获取,  如有疑问请致电客服。");
			if(ContantsAddress.isProductionEnvironment){
				tishi_str.setText(info2);
				lin_msg.setVisibility(View.VISIBLE);
//				Toast.makeText(RapidlyLoanInfoConfrim.this,
//						"动态验证码已发至您注册时的手机号，60秒后可重新获取。如您的手机号码已经变更，请联系客服人员。", Toast.LENGTH_LONG).show();
			}else{
				tishi_str.setText(info2);
				lin_msg.setVisibility(View.VISIBLE);
				Toast.makeText(RelaxedLoanNineCommitInfo.this,
						"验证码："+response.getMsg(), Toast.LENGTH_LONG).show();
			}
				timer();
				bt_subkey.setBackgroundResource(R.drawable.timer_dao);
				isFlag = !isFlag;
			} else {
				DialogUtil.createDialog(this, 4, response.getMsg());
			}
		}
	}
	
	//提交验证码 ！ 验证。
	class CommitKeysyncllTask extends AsyncTask<String, Object, Object> {
	    @Override
	    protected Object doInBackground(String... params) {
	      HttpHelper httpHelper = HttpHelper.getInstance(RelaxedLoanNineCommitInfo.this);
	      List<NameValuePair> arg = new ArrayList<NameValuePair>();
	      arg.add(new BasicNameValuePair("phoneNum", params[0]));
	      arg.add(new BasicNameValuePair("verifyCode", params[1]));
	      return httpHelper.post(ContantsAddress.VALID_PHONE_VERIFYKEY_CODE, arg, Response.class);
	    }

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				showProgressDialog();
			}

			@Override
			protected void onPostExecute(Object result) {
				dismissProgressDialog();
				super.onPostExecute(result);
				CommitKeyResponse(result);
			}
		}
	
	private void CommitKeyResponse(Object result) {
		Response response = (Response) result;
		if (response == null) {
			DialogUtil.showDialogOneButton2(RelaxedLoanNineCommitInfo.this,
					"服务器请求异常!");
			confirm_button.setClickable(true);
			return;
		} else {
			if (response.getCode() == 0) {
			//code=0
				isFlagOk=true;
//判断是否通过了 短信验证码--------------------------------------------------------------
				 getProInfo();
				if (Contents.isChoice) {
//					RapidlyLoanInfoContents rapidlyLoanInfoContents = new RapidlyLoanInfoContents();
//					applCde = rapidlyLoanInfoContents.MiniConfirmMessage
//							.getApplCde();// 申请编号
//					applSeq = rapidlyLoanInfoContents.MiniConfirmMessage
//							.getApptSeq();// 申请流水号
	//
//					applDisbSeq = rapidlyLoanInfoContents.MiniConfirmMessage
//							.getApplDisbSeq();
//					applRpymSeq = rapidlyLoanInfoContents.MiniConfirmMessage
//							.getApplRpymSeq();
//					apptSeq = rapidlyLoanInfoContents.MiniConfirmMessage
//							.getApptSeq();
	//
//					MiniPersonInfo.applCde = applCde;
//					MiniPersonInfo.applSeq = applSeq;
	//
//					MiniPersonInfo.applDisbSeq = applDisbSeq;
//					MiniPersonInfo.applRpymSeq = applRpymSeq;
//					MiniPersonInfo.apptSeq = apptSeq;
					replyMiniCard();
				} else {
					new GetSeqAsyncTask().execute();
				}
			//-------------------------------------end
			} else {
				DialogUtil.showDialogOneButton2(RelaxedLoanNineCommitInfo.this,
						response.getMsg());
				confirm_button.setClickable(true);
				return;
			}
		}
	}

}
