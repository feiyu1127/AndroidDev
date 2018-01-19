package com.yucheng.byxf.mini.rapidly;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore.Images.Thumbnails;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.Spanned;
import android.text.format.Time;
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
import com.yucheng.byxf.mini.message.MiniConfirmMessage;
import com.yucheng.byxf.mini.message.MiniConfirmResponse;
import com.yucheng.byxf.mini.message.PhotoInfo;
import com.yucheng.byxf.mini.message.RapidlyLoanInfo;
import com.yucheng.byxf.mini.message.Response;
import com.yucheng.byxf.mini.message.VerifyCodeResponse;
import com.yucheng.byxf.mini.ui.BaseActivity;
import com.yucheng.byxf.mini.ui.ForgetPassWord1Activity;
import com.yucheng.byxf.mini.ui.ForgetPassWord2Activity;
import com.yucheng.byxf.mini.ui.MyLoginActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.StringUtils;
import com.yucheng.byxf.mini.utils.ViewTools;
import com.yucheng.byxf.mini.views.RoundAngleImageView;
import com.yucheng.byxf.util.LogManager;

public class RapidlyLoanInfoConfrim extends BaseActivity {

	private TextView infoTextView;
	private LinearLayout incLayout;
	private TextView easy_loan_head;
	private Button bt_back_common;

	private Button next_button;
	private RoundAngleImageView imageView1;
	private RoundAngleImageView imageView2;
	private RoundAngleImageView imageView3;

	private Button modification;
	private TextView tishi_str;
	RapidlyLoanInfo loanInfo = null;
	private RelativeLayout rel_msg_key;
	private LinearLayout lin_msg;
//	private String applCde;
//	private String applSeq;
//
//	private String applDisbSeq;
//	private String applRpymSeq;
//	private String apptSeq;

	@SuppressWarnings("unused")
	private Dialog dialog;
	private String IMEI = "";//设备imei
	private RapidlyLoanInfoConfrim activity;
	
	private Bitmap mCutBitmap ;//视频截图
	private int time = 100;
	private boolean  Type_key=false;
	private TextView bt_subkey;
	private EditText ed_key;
	private boolean isFlag = true;
	//判断是否
	private boolean isFlagOk = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rapidlyloan_confrim);
		getLocationInfo();
		loanInfo = RapidlyLoanInfoContents.rapidlyLoanInfo;
		activity = new RapidlyLoanInfoConfrim();
		//先不上线获取本人手机号
//		if(Contents.isChoiceMsgKey){
//			String str_numinfo="";
//			str_numinfo=getPhoneNumber();
//			if(str_numinfo!=null||"".equals(str_numinfo)){
//				str_numinfo.trim();
//				int i=str_numinfo.length();
//				if(i==11){
//					loanInfo.setPhone(str_numinfo);
//					RapidlyLoanInfoContents.rapidlyLoanInfo.setPhone(str_numinfo);
//				}
//			}
//		}
		init();
		//System.out.println("流水号相关信息=》"+Contents.applCde+",---"+Contents.applSeq +",--"+	Contents.applDisbSeq+",=="+Contents.applRpymSeq+",=="+Contents.apptSeq+".");
		System.out.println("诗句信息："+Contents.poetry_str);
		if(!Contents.isChoiceMsgKey){
			rel_msg_key.setVisibility(View.GONE);
			lin_msg.setVisibility(View.GONE);
		}
	}

	private void init() {
		tishi_str=(TextView) findViewById(R.id.tishi_str);
		rel_msg_key=(RelativeLayout) findViewById(R.id.rel_msg_key);
		lin_msg=(LinearLayout) findViewById(R.id.lin_msg);
		// TODO Auto-generated method stub
		next_button = (Button) findViewById(R.id.next_button);
		infoTextView = (TextView) findViewById(R.id.confirm_text1);
		incLayout = (LinearLayout) findViewById(R.id.head);
		easy_loan_head = (TextView) incLayout.findViewById(R.id.easy_loan_head);
		easy_loan_head.setText("信息确认");
		bt_back_common = (Button) incLayout.findViewById(R.id.bt_back_common);
		bt_back_common.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		//验证码
		bt_subkey=(TextView) findViewById(R.id.submit);
		ed_key=(EditText) findViewById(R.id.et_codekey);
		bt_subkey.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				if (isFlag) {
					new VertifyCodeKeyAsyncTask().execute(loanInfo.getPhone(),"2");
				}
			}
		});
		
		Spanned info = Html.fromHtml("<p>姓名:\t\t" + loanInfo.getName() + "</p>"
				+ "<p>身份证号:\t\t" + loanInfo.getIdNo() + "</p>" + "<p>手机号码:\t\t"
				+ loanInfo.getPhone() + "</p>" + "<p>现居住地址:\t\t"
				+ loanInfo.getAddress() + loanInfo.getAddress_more() + "</p>"
				+ "<p>单位名称:\t\t" + loanInfo.getCompanyName() + "</p>"
				+ "<p>单位电话:\t\t" + loanInfo.getCompany_phone_zone_code() + "-"
				+ loanInfo.getCompany_phone_tel_code() + "</p>"
				+ "<p>联系人1姓名:\t\t" + loanInfo.getContactName1() + "</p>"
				+ "<p>与申请人关系:\t\t" + loanInfo.getContactRelation1() + "</p>"
				+ "<p>联系人1手机:\t\t" + loanInfo.getMobileNo1() + "</p>"
				+ "<p>联系人2姓名:\t\t" + loanInfo.getContactName2() + "</p>"
				+ "<p>与申请人关系:\t\t" + loanInfo.getContactRelation2() + "</p>"
				+ "<p>联系人2手机:\t\t" + loanInfo.getMobileNo2() + "</p>"
				+ "<p>贷款金额:\t\t" + loanInfo.getMoney() + "元" + "</p>"
				+ "<p>贷款期限:\t\t" + "一个月" + "</p>" + "<p>手续费:\t\t"
				+ loanInfo.getPoundage() + "</p>" + "<p>还款方式:\t\t" + "一次还本"
				+ "</p>" + "<p>放还款账户银行名称:\t\t" + loanInfo.getBankName()
				+ "</p>" + "<p>卡号:\t" + loanInfo.getCardNo() + "</p>");

		infoTextView.setText(info);

		imageView1 = (RoundAngleImageView) findViewById(R.id.imageView1);
		imageView2 = (RoundAngleImageView) findViewById(R.id.imageView2);
		imageView3 = (RoundAngleImageView) findViewById(R.id.imageView3);

		setImageViewBitmap(RapidlyLoanInfoContents.comPhoto1Path, imageView1);
		setImageViewBitmap(RapidlyLoanInfoContents.comPhoto2Path, imageView2);
//		imageView3.setImageResource(R.drawable.video_ok);
		
		if (RapidlyLoanInfoContents.videoPath!= null && !"".equals(RapidlyLoanInfoContents.videoPath) && mCutBitmap == null) {
			mCutBitmap = ThumbnailUtils.createVideoThumbnail(Environment.getExternalStorageDirectory()+"/"+RapidlyLoanInfoContents.videoPath, Thumbnails.FULL_SCREEN_KIND);
			if (mCutBitmap != null) {
				imageView3.setImageBitmap(mCutBitmap);
			}
		}else {
			imageView3.setImageBitmap(mCutBitmap);
		}

		next_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				next_button.setClickable(false);
				//需要验证
				if(Contents.isChoiceMsgKey){
				String st_ed_key = ed_key.getText().toString();
				if(st_ed_key==null||"".equals(st_ed_key)){
					DialogUtil.showDialogOneButton2(RapidlyLoanInfoConfrim.this,
							"请输入验证码！");
					next_button.setClickable(true);
					return;
				}
				new CommitKeysyncTask().execute(loanInfo.getPhone(),st_ed_key,"2");
	
				}else{
				//不要验证	
					//判断是否通过了 短信验证码--------------------------------------------------------------
					getProInfo();
					if (Contents.isChoice) {
						//是打回操作！！！！！
						setPostInfo();
					} else {
						//不是打回操作！！！！！
						setPostInfo();
					}
				//-------------------------------------end
				}
			}
		});

		modification = (Button) findViewById(R.id.modification);

		modification.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(RapidlyLoanInfoConfrim.this,
						RapidlyLoanInfoActivity.class);
				startActivity(intent);
				finish();
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
	}



	private void setPostInfo() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		List<PhotoInfo> photo = new ArrayList<PhotoInfo>();
		// 客戶姓名
		map.put("custName", RapidlyLoanInfoContents.rapidlyLoanInfo.getName());
		// 单位名称
		map.put("indivEmp",
				RapidlyLoanInfoContents.rapidlyLoanInfo.getCompanyName());
		// 亲属联系人姓名
		map.put("indivRelName",
				RapidlyLoanInfoContents.rapidlyLoanInfo.getContactName1());
		// 与联系人关系
		map.put("indivRelation", RapidlyLoanInfoContents.rapidlyLoanInfo
				.getContactRelationCode1());
		// 联系人手机号
		map.put("indivRelMobile",
				RapidlyLoanInfoContents.rapidlyLoanInfo.getMobileNo1());
		// 证件号码
		map.put("idNo", RapidlyLoanInfoContents.rapidlyLoanInfo.getIdNo());
		// 非亲属联系人姓名
		map.put("indivOthName",
				RapidlyLoanInfoContents.rapidlyLoanInfo.getContactName2());
		// 非亲属联系人关系
		map.put("indivOthRel", RapidlyLoanInfoContents.rapidlyLoanInfo
				.getContactRelationCode2());
		// 非亲属联系人手机号
		map.put("indivOthMobile",
				RapidlyLoanInfoContents.rapidlyLoanInfo.getMobileNo2());
		// 性别
		if (RapidlyLoanInfoContents.sex.equals("男")) {
			map.put("indivSex", "1");
		} else {
			map.put("indivSex", "2");
		}
		map.put("apptStartDate", RapidlyLoanInfoContents.apptStartDate);
		// 年龄
		map.put("apptAge", RapidlyLoanInfoContents.age + "");
		// 现居住地址
		map.put("indivLiveAddr",
				RapidlyLoanInfoContents.rapidlyLoanInfo.getAddress()
						+ RapidlyLoanInfoContents.rapidlyLoanInfo
								.getAddress_more());
		// 手机号码
		map.put("indivMobile",
				RapidlyLoanInfoContents.rapidlyLoanInfo.getPhone());
		// 单位电话_区号
		map.put("indivEmpZone", RapidlyLoanInfoContents.rapidlyLoanInfo
				.getCompany_phone_zone_code());
		// 单位电话_电话号码
		map.put("indivEmpTelNo", RapidlyLoanInfoContents.rapidlyLoanInfo
				.getCompany_phone_tel_code());
		// 单位电话_分机号
		map.put("indivEmpTelSub", RapidlyLoanInfoContents.rapidlyLoanInfo
				.getCompany_phone_extension_code());
		// 贷款申请金额
		map.put("applyAmt", RapidlyLoanInfoContents.rapidlyLoanInfo.getMoney());
		// 贷款申请期限(按月)
		map.put("applyTnr", "1");
		// 还款方式
		// map.put("loanMtd", "J00001");
		map.put("loanMtd", "M052");
		// 专案代码
		map.put("pcCode", "");
		// 放款账号开户行
		if (RapidlyLoanInfoContents.rapidlyLoanInfo.getBankName()
				.equals("北京银行")) {
			map.put("applDisbAcBank", "BOB");
		} else {
			map.put("applDisbAcBank", "ABC");
		}
		// 放款账号
		map.put("applDisbAcNo", RapidlyLoanInfoContents.rapidlyLoanInfo
				.getCardNo().replace(" ", ""));
		System.out.println(RapidlyLoanInfoContents.rapidlyLoanInfo.getCardNo()
				.replace(" ", ""));
		// 还款账户开户行
		if (RapidlyLoanInfoContents.rapidlyLoanInfo.getBankName()
				.equals("北京银行")) {
			map.put("applRpymAcBank", "BOB");
		} else {
			map.put("applRpymAcBank", "ABC");
		}
		// 放款账号
		map.put("applRpymAcNo", RapidlyLoanInfoContents.rapidlyLoanInfo
				.getCardNo().replace(" ", ""));
		// 放款信息流水号
		 map.put("applDisbSeq", Contents.applDisbSeq);
		// 申请编号
		map.put("applCde", Contents.applCde);
		// 申请流水号
		 map.put("applSeq", Contents.applSeq);
		// 还款信息流水号
		 map.put("applRpymSeq", Contents.applRpymSeq);
		// 与贷款相关流水号
		 map.put("apptSeq", Contents.apptSeq);
		// 机具编号
		map.put("machineId", Contents.iMei);
		// 机具类型
		map.put("machineType", "08");

		// 坐席ID
		if(Contents.response != null && Contents.response.getResult() != null) {
			map.put("agentId", Contents.response.getResult().getIdNo());

			map.put("agentName", Contents.response.getResult().getCipNamecn());
		}
		if (ContantsAddress.isProductionEnvironment) {
			// 省
			map.put("contProvince", Contents.province);
			// 市
			map.put("contCity", Contents.city);
			// 区
			map.put("contArea", Contents.area);
			// 经度
			map.put("longitude", Contents.longitude);
			// 纬度
			map.put("latitude", Contents.latitude);
			// jsp定位详细地址
			map.put("ipAdd", Contents.locationInfo);
			//传详细地址
			map.put("applyAddr", Contents.locationInfo);
		} else {
			// 测试---生产
			// 省
			map.put("contProvince", "北京市");
			// 市
			map.put("contCity", "北京市");
			// 区
			map.put("contArea", "海淀区");
			// 经度
			map.put("longitude", "106");
			// 纬度
			map.put("latitude", "36");
			// jsp定位详细地址
			map.put("ipAdd", "北京市海淀区清河");
			//传详细地址
			map.put("applyAddr", "北京市海淀区清河");
		}
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

		// 版本号
		map.put("appVersion", getVerName(RapidlyLoanInfoConfrim.this));
		// 现居住地址省市区,以”,“分隔
		System.out.println(RapidlyLoanInfoContents.rapidlyLoanInfo.getAddress()
						+ ","
						+ RapidlyLoanInfoContents.rapidlyLoanInfo
								.getAddress_more());
		
		map.put("curentResidentialAddr",
				RapidlyLoanInfoContents.rapidlyLoanInfo.getAddress()
						+ ","
						+ RapidlyLoanInfoContents.rapidlyLoanInfo
								.getAddress_more());
		// map.put("crtUsr", Contents.response.getResult().getCipAlias());
		// 测试打回 crtUsr 改为 "MOBILE"
		map.put("crtUsr", "MOBILE");
	
		map.put("videoInfo", Contents.poetry_str);
		if (Contents.isChoice) {
			map.put("txType", "02");
			// 代表极速贷
			map.put("applyType", "1");

			map.put("apprvAmt",
					RapidlyLoanInfoContents.rapidlyLoanInfo.getMoney());
			map.put("apprvTnr", "1");
		}

		list.add(map);
		//获取时间
		SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMddHHmmss");       
		Date  curDate= new Date(System.currentTimeMillis());//获取当前时间       
		String t= formatter.format(curDate); 
		
		// 身份证正面
		photo.add(new PhotoInfo(Contents.applCde + "01", Contents.applCde, "11", "LCDOC", Contents.applCde
				+ t+"01" + ".jpg", "999", RapidlyLoanInfoContents.comPhoto1Path));
		
		System.out.println(RapidlyLoanInfoContents.comPhoto1Path);
		photo.add(new PhotoInfo(Contents.applCde + "02", Contents.applCde, "12", "LCDOC", Contents.applCde
				+t +"02" + ".jpg", "999", RapidlyLoanInfoContents.comPhoto2Path));
		
		System.out.println(RapidlyLoanInfoContents.comPhoto2Path);
	
		String path = Environment.getExternalStorageDirectory().toString();
		photo.add(new PhotoInfo(Contents.applCde + "03", Contents.applCde, "26", "LCVIDEO",
				Contents.applCde +t+ "03" + ".3gp", "999", path + "/"
						+ RapidlyLoanInfoContents.videoPath,Contents.poetry_str,"1"));
	
		new RapidlyPost(RapidlyLoanInfoConfrim.this, list, photo);
	}

	// private void setPostInfo1() {
	// List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	// Map<String, Object> map = new HashMap<String, Object>();
	// List<PhotoInfo> photo = new ArrayList<PhotoInfo>();
	// // 客戶姓名
	// map.put("custName", RapidlyLoanInfoContents.rapidlyLoanInfo.getName());
	// // 单位名称
	// map.put("indivEmp",
	// RapidlyLoanInfoContents.rapidlyLoanInfo.getCompanyName());
	// // 亲属联系人姓名
	// map.put("indivRelName",
	// RapidlyLoanInfoContents.rapidlyLoanInfo.getContactName1());
	// // 与联系人关系
	// map.put("indivRelation", RapidlyLoanInfoContents.rapidlyLoanInfo
	// .getContactRelationCode1());
	// // 联系人手机号
	// map.put("indivRelMobile",
	// RapidlyLoanInfoContents.rapidlyLoanInfo.getMobileNo1());
	// // 证件号码
	// map.put("idNo", RapidlyLoanInfoContents.rapidlyLoanInfo.getIdNo());
	// // 非亲属联系人姓名
	// map.put("indivOthName",
	// RapidlyLoanInfoContents.rapidlyLoanInfo.getContactName2());
	// // 非亲属联系人关系
	// map.put("indivOthRel", RapidlyLoanInfoContents.rapidlyLoanInfo
	// .getContactRelationCode2());
	// // 非亲属联系人手机号
	// map.put("indivOthMobile",
	// RapidlyLoanInfoContents.rapidlyLoanInfo.getMobileNo2());
	// // 性别
	// if (RapidlyLoanInfoContents.sex.equals("男")) {
	// map.put("indivSex", "1");
	// } else {
	// map.put("indivSex", "2");
	// }
	// map.put("apptStartDate", RapidlyLoanInfoContents.apptStartDate);
	// // 年龄
	// map.put("apptAge", RapidlyLoanInfoContents.age + "");
	// // 现居住地址
	// map.put("indivLiveAddr",
	// RapidlyLoanInfoContents.rapidlyLoanInfo.getAddress()
	// + RapidlyLoanInfoContents.rapidlyLoanInfo
	// .getAddress_more());
	// // 手机号码
	// map.put("indivMobile",
	// RapidlyLoanInfoContents.rapidlyLoanInfo.getPhone());
	// // 单位电话_区号
	// map.put("indivEmpZone", RapidlyLoanInfoContents.rapidlyLoanInfo
	// .getCompany_phone_zone_code());
	// // 单位电话_电话号码
	// map.put("indivEmpTelNo", RapidlyLoanInfoContents.rapidlyLoanInfo
	// .getCompany_phone_tel_code());
	// // 单位电话_分机号
	// map.put("indivEmpTelSub", RapidlyLoanInfoContents.rapidlyLoanInfo
	// .getCompany_phone_extension_code());
	// // 贷款申请金额
	// map.put("applyAmt", RapidlyLoanInfoContents.rapidlyLoanInfo.getMoney());
	// // 贷款申请期限(按月)
	// map.put("applyTnr", "1");
	// // 还款方式
	// // map.put("loanMtd", "J00001");
	// map.put("loanMtd", "M052");
	// // 专案代码
	// map.put("pcCode", "");
	// // 放款账号开户行
	// if (RapidlyLoanInfoContents.rapidlyLoanInfo.getBankName()
	// .equals("北京银行")) {
	// map.put("applDisbAcBank", "BOB");
	// } else {
	// map.put("applDisbAcBank", "ABC");
	// }
	// // 放款账号
	// map.put("applDisbAcNo", RapidlyLoanInfoContents.rapidlyLoanInfo
	// .getCardNo().replace(" ", ""));
	// System.out.println(RapidlyLoanInfoContents.rapidlyLoanInfo.getCardNo()
	// .replace(" ", ""));
	// // 还款账户开户行
	// if (RapidlyLoanInfoContents.rapidlyLoanInfo.getBankName()
	// .equals("北京银行")) {
	// map.put("applRpymAcBank", "BOB");
	// } else {
	// map.put("applRpymAcBank", "ABC");
	// }
	// // 放款账号
	// map.put("applRpymAcNo", RapidlyLoanInfoContents.rapidlyLoanInfo
	// .getCardNo().replace(" ", ""));
	// // 放款信息流水号
	// map.put("applDisbSeq", applDisbSeq);
	// // 申请编号
	// map.put("applCde", applCde);
	// // 申请流水号
	// map.put("applSeq", applSeq);
	// // 还款信息流水号
	// map.put("applRpymSeq", applRpymSeq);
	// // 与贷款相关流水号
	// map.put("apptSeq", apptSeq);
	// // 机具编号
	// map.put("machineId", Contents.iMei);
	// // 机具类型
	// map.put("machineType", "08");
	// // jsp定位详细地址
	// map.put("ipAdd", "北京市海淀区清河");
	// // map.put("ipAdd", Contents.locationInfo);
	// // 坐席ID
	// map.put("agentId", Contents.response.getResult().getIdNo());
	//
	// map.put("agentName", Contents.response.getResult().getCipNamecn());
	//
	// // 测试---生产
	// // 省
	// map.put("contProvince", "北京市");
	// // 市
	// map.put("contCity", "北京市");
	// // 区
	// map.put("contArea", "海淀区");
	// // 经度
	// map.put("longitude", "106");
	// // 纬度
	// map.put("latitude", "36");
	// // // 省
	// // map.put("contProvince", Contents.province);
	// // // 市
	// // map.put("contCity", Contents.city);
	// // // 区
	// // map.put("contArea", Contents.area);
	// // // 经度
	// // map.put("longitude", Contents.longitude);
	// // // 纬度
	// // map.put("latitude", Contents.latitude);
	//
	// // 版本号
	// map.put("appVersion", getVerName(RapidlyLoanInfoConfrim.this));
	// // 现居住地址省市区,以”,“分隔
	// map.put("curentResidentialAddr",
	// RapidlyLoanInfoContents.rapidlyLoanInfo.getAddress()
	// + ","
	// + RapidlyLoanInfoContents.rapidlyLoanInfo
	// .getAddress_more());
	//
	// // =======================轻松贷数据===================================
	// // 数据可能为NULL，暂填 空
	// // 本单位工作年限
	// map.put("indivEmpYrs", application.getProfessionInfo().getWorkYear());
	// // 本单位总工作年限
	// map.put("indivWorkYrs", application.getProfessionInfo()
	// .getTotalWorkYear());
	// // 情书联系人工作单位名称
	// map.put("indivRelEmp", "");
	// // 情书联系人住宅电话区号
	// map.put("indivRelZone", "");
	// // 亲属联系人住宅电话号码
	// map.put("indivRelPhone", "");
	// // 文化程度
	// map.put("indivDegree", "");
	// // 国籍
	// map.put("idCtry", "");
	// // 非亲属联系人单位名称
	// map.put("indivOthEmp", "");
	// // 非亲属联系人单位(电话号码)
	// map.put("indivOthPhone", "");
	// // 非亲属联系人手机号
	// map.put("indivOthMobile", "");
	// // 证件有效期(开始)
	// map.put("idTermBgn", "");
	// // 证件有效期(结束)
	// map.put("idTermEnd", "");
	// // 出生日期
	// map.put("apptStartDate", "");
	// // 供养人数
	// map.put("indivDepNo", application.getBasicInfo().getProvidePerson());
	// // 婚姻状况
	// map.put("indivMarital", application.getBasicInfo()
	// .getMarriageStatueCode());
	// // 现居住住房性质
	// map.put("indivLive", application.getBasicInfo().getLiveAddress());
	// // 月租金(元)
	// map.put("indivRentAmt", "");
	// // 户籍地址
	// map.put("indivRegAddr", application.getBasicInfo().getBirthPlace());
	// // 住宅电话_区号
	// map.put("indivLiveZone", application.getBasicInfo().getZoneCode());
	// // 住宅电话_电话号码
	// map.put("indivLivePhone", application.getBasicInfo().getTelPhone());
	// // 个人工资月收入(元)
	// map.put("indivMthInc", application.getProfessionInfo().getMoneyMonth());
	// // 电子邮箱
	// map.put("indivEmail", application.getBasicInfo().getElecTronEmail());
	// // 任职部门
	// map.put("indivBranch", application.getProfessionInfo()
	// .getRzDepartment());
	// // 单位地址
	// map.put("indivEmpAddr", application.getProfessionInfo()
	// .getCompanyAddress());
	// // 联络员工姓名 pomsStaff 必填，获取登录用户id传入server端
	// // 是否开通免费短信通知服务 mssInd 必填，Y---是，N---否
	// // 机构码 bchCde 必填，获取登录用户组织机构传入server端
	// // 贷款用途 purpose 必填，参见字典项
	// // 其他用途 otherPurpose 非必填
	// map.put("pomsStaff", "");
	// map.put("mssInd", "Y");
	// map.put("bchCde", "999999999");
	// map.put("purpose", application.getApplyPersonalInfo()
	// .getTick_loan_pursoseCode());
	// map.put("otherPurpose", "");
	// // 经销商代码 dealer 必填，前端传入登录用户组织机构
	// // 推广员工ID/经办人工号 operator 必填，前端传入登录用户id
	// map.put("dealer", "999999999");
	// map.put("operator", application.getApplyPersonalInfo().getEmployee_id());
	//
	// // 推广员工签名/经办员工姓名 operatorName 必填，前端传入登录用户名称
	// // 销售员工号 salerId 必填，前端传入登录用户id
	// // 销售员工姓名 salerName 必填，前端传入登录用户名称
	// map.put("operatorName", application.getApplyPersonalInfo()
	// .getEmployee_name());
	// map.put("salerId", "");
	// map.put("salerName", "");
	// // 放款账户名 applDisbAcNam 必填，参见字典项
	// // 还款账号户名 applRpymAcNam 非必填，缺省的情况下，默认为还款账号与放款账号一致
	// // map.put("applDisbAcNam", application.getApplyPersonalInfo()
	// // .getUsername_value());
	// map.put("applDisbAcNam", "");
	// map.put("applRpymAcNam", "");
	// // map.put("applRpymAcNam", application.getApplyPersonalInfo()
	// // .getUsername_value());
	// // 渠道码 trenchNo 必填，前端获取登录用户组织机构号传入server
	// // 网点号 branchNo 必填，前端获取登录用户组织机构号传入server
	// map.put("trenchNo", "YDKHDYB");
	// map.put("branchNo", "SJGRB");
	// // 职业 indivOccup 必填，参见字典项
	// // 单位性质 indivEmpType 必填，参见字典项
	// // 企业规模 indivEmpNum 必填，参见字典项
	// map.put("indivOccup", "");
	// map.put("indivEmpType", application.getProfessionInfo()
	// .getDpNatureCode());
	// map.put("indivEmpNum", application.getProfessionInfo().getDpScaleCode());
	// // 行业性质 indivIndtryPaper 必填，参见字典项
	// // 现任职务 indivPosition 必填，参见字典项
	// // 资产类型 asset[0].assetKind 必填，参见字典项
	// // 资产性质 asset[0].assetTyp 必填，参见字典项
	// // 金额 asset[0].assetAmtd 必填
	// map.put("indivIndtryPaper", "");
	// map.put("indivPosition", "");
	//
	// map.put("asset[0].assetKind", "");
	// map.put("asset[0].assetTyp", "");
	// map.put("asset[0].assetAmtd", "");
	//
	// map.put("asset[1].assetKind", "");
	// map.put("asset[1].assetTyp", "");
	// map.put("asset[1].assetAmtd", "");
	//
	// map.put("asset[2].assetKind", "");
	// map.put("asset[2].assetTyp", "");
	// map.put("asset[2].assetAmtd", "");
	// // 月还款额 asset[0].assetMthAmt 必填
	// // 贷款余额 asset[0].assetReAmt 必填
	// map.put("asset[0].assetMthAmt", "");
	// map.put("asset[1].assetMthAmt", "");
	// map.put("asset[2].assetMthAmt", "");
	//
	// map.put("asset[0].assetReAmt", "");
	// map.put("asset[1].assetReAmt", "");
	// map.put("asset[2].assetReAmt", "");
	//
	// map.put("crtUsr", Contents.response.getResult().getCipAlias());
	// map.put("mailAddr", application.getApplyPersonalInfo()
	// .getSend_methodCode());
	// map.put("loanInstal", "");
	// map.put("indivCardInd", application.getAssertInfo()
	// .getIsHavingCreditCard());
	// map.put("indivCardBank", application.getAssertInfo().getCardBankName());
	// map.put("indivCardAmt", application.getAssertInfo().getCardTopMoney());
	//
	// // 第三联系人信息
	// map.put("lcAppRelations[0].indivRelName", "");
	// map.put("lcAppRelations[0].indivRelEmp", "");
	// map.put("lcAppRelations[0].indivRelation", "");
	// map.put("lcAppRelations[0].indivRelZone", "");
	// map.put("lcAppRelations[0].indivRelPhone", "");
	// map.put("lcAppRelations[0].indivRelMobile", "");
	// // 第四联系人信息
	// map.put("lcAppRelations[1].indivRelName", "");
	// map.put("lcAppRelations[1].indivRelEmp", "");
	// map.put("lcAppRelations[1].indivRelation", "");
	// map.put("lcAppRelations[1].indivRelZone", "");
	// map.put("lcAppRelations[1].indivRelPhone", "");
	// map.put("lcAppRelations[1].indivRelMobile", "");
	// // wz核对数据添加
	// map.put("pomsDepart", "10");
	// map.put("applyDt", "2014-09-24");// 传的 当前日期
	// map.put("loanGrp", "G002");
	// map.put("loanTyp", "C002");
	// map.put("loanKind", "NOB");
	// map.put("loanCcy", "RMB");
	// map.put("apprvAmt", "");
	// map.put("apprvTnr", "");
	// map.put("proPurAmt", "");
	// map.put("fstPay", "");
	// map.put("odGranceTyp", "");
	// map.put("odGrance", "");
	// map.put("inputSrc", "08");
	// map.put("idType", "20");
	// map.put("apptVeriFlag", "");
	// map.put("location", "");
	// map.put("indivRegZip", "");
	// map.put("applyAddr", "");
	//
	// // -------------------------数据待定------------------------------------
	// map.put("indivEmpZip", application.getProfessionInfo().getCompanyMail());
	// map.put("scordeGrade", "");
	// map.put("spreadRemark", "");
	// map.put("applSrc", "");
	// map.put("spreadType", "");
	// map.put("indivLiveZip", application.getBasicInfo().getLivePostcode());
	// map.put("ip", "");
	// map.put("assetStr", "");
	// map.put("appContactStr", "");
	// map.put("lcAppRelationStr", "");
	// map.put("goodStr", "");
	// map.put("inputDt", "");
	// map.put("indivPhoneChk", "");
	// map.put("remark", "");
	// map.put("relaVeriDt", "");
	// map.put("apptVeriDt", "");
	// map.put("relaVeriFlag", "");
	// map.put("tellMatt", "");
	// map.put("applMate", "");
	// map.put("applCircs", "");
	// map.put("mtdTyp", "01");
	// map.put("state", "000");
	// map.put("applAcctInd", "PERSON");
	// // map.put("txCde", "YWPS007");
	// map.put("txType", "02");
	// map.put("freqUnit", "1");
	// map.put("form", "LcApplNor");
	// // 最后更改时间
	// map.put("lastChgDt", "");
	// map.put("groupNo", "");
	// map.put("switchNo", "");
	//
	// // 代表极速贷
	// map.put("applyType", "1");
	// list.add(map);
	//
	// // 身份证正面
	//
	// photo.add(new PhotoInfo(applCde + "01", applCde, "11", "LCDOC", applCde
	// + "01" + ".jpg", "999", RapidlyLoanInfoContents.comPhoto1Path));
	// System.out.println(RapidlyLoanInfoContents.comPhoto1Path);
	// photo.add(new PhotoInfo(applCde + "02", applCde, "12", "LCDOC", applCde
	// + "02" + ".jpg", "999", RapidlyLoanInfoContents.comPhoto2Path));
	// System.out.println(RapidlyLoanInfoContents.comPhoto2Path);
	// String path = Environment.getExternalStorageDirectory().toString();
	// photo.add(new PhotoInfo(applCde + "03", applCde, "26", "LCVIDEO",
	// applCde + "03" + ".3gp", "999", path + "/"
	// + RapidlyLoanInfoContents.videoPath));
	// new RapidlyPost(RapidlyLoanInfoConfrim.this, list, photo);
	// }

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

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		activity = null;
	}

	@Override
	public void cancleAsyncTask() {
		// TODO Auto-generated method stub
		super.cancleAsyncTask();

	}
	
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
	class VertifyCodeKeyAsyncTask extends AsyncTask<String, Object, Object> {
    @Override
    protected Object doInBackground(String... params) {
      HttpHelper httpHelper = HttpHelper.getInstance(RapidlyLoanInfoConfrim.this);
      List<NameValuePair> arg = new ArrayList<NameValuePair>();
      arg.add(new BasicNameValuePair("phoneNum", params[0]));
      arg.add(new BasicNameValuePair("msgTemplate", params[1]));
      return httpHelper.post(ContantsAddress.SEND_MODIFY_PWD_CODE_KEY, arg, VerifyCodeResponse.class);
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
				String str_num=loanInfo.getPhone().toString();
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
				Toast.makeText(RapidlyLoanInfoConfrim.this,
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
	class CommitKeysyncTask extends AsyncTask<String, Object, Object> {
	    @Override
	    protected Object doInBackground(String... params) {
	      HttpHelper httpHelper = HttpHelper.getInstance(RapidlyLoanInfoConfrim.this);
	      List<NameValuePair> arg = new ArrayList<NameValuePair>();
	      arg.add(new BasicNameValuePair("phoneNum", params[0]));
	      arg.add(new BasicNameValuePair("verifyCode", params[1]));
	      arg.add(new BasicNameValuePair("msgTemplate", params[2]));
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
			DialogUtil.showDialogOneButton2(RapidlyLoanInfoConfrim.this,
					"服务器请求异常!");
			next_button.setClickable(true);
			return;
		} else {
			if (response.getCode() == 0) {
			//code=0
				isFlagOk=true;
//判断是否通过了 短信验证码--------------------------------------------------------------
				getProInfo();
				if (Contents.isChoice) {
					//是打回操作！！！！！
//					applCde = RapidlyLoanInfoContents.MiniConfirmMessage
//							.getApplCde();
//					applSeq = RapidlyLoanInfoContents.MiniConfirmMessage
//							.getApplSeq();// 申请流水号
//					applDisbSeq = RapidlyLoanInfoContents.MiniConfirmMessage
//							.getApplDisbSeq();
//					applRpymSeq = RapidlyLoanInfoContents.MiniConfirmMessage
//							.getApplRpymSeq();
//					apptSeq = RapidlyLoanInfoContents.MiniConfirmMessage
//							.getApptSeq();
					setPostInfo();
				} else {
//不是打回操作！！！！！
					setPostInfo();
					//new GetSeqAsyncTask().execute();
				}
			//-------------------------------------end
			} else {
				DialogUtil.showDialogOneButton2(RapidlyLoanInfoConfrim.this,
						response.getMsg());
				next_button.setClickable(true);
				return;
			}
		}
	}

}
