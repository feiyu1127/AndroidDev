package com.yucheng.byxf.mini.map;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.widget.Toast;

import cn.jpush.android.api.JPushInterface;

import com.baidu.mapapi.SDKInitializer;
import com.wujay.fund.ui.GestureVerifyActivity;
import com.yucheng.byxf.mini.eloan.domain.AssertInfo;
import com.yucheng.byxf.mini.eloan.domain.BasicInfo;
import com.yucheng.byxf.mini.eloan.domain.LinkManInfo;
import com.yucheng.byxf.mini.eloan.domain.LoanApplyPersonalInfo;
import com.yucheng.byxf.mini.eloan.domain.ProfessionalInfo;
import com.yucheng.byxf.mini.message.CustInfoResponseResult;
import com.yucheng.byxf.mini.message.EasyLoanRequest;
import com.yucheng.byxf.mini.message.EasyLoanResult;
import com.yucheng.byxf.mini.message.LoginResponse;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.msg.eloan.EasyLoanResponse;

public class DemoApplication extends Application {
	private SharedPreferences sp0 = null;// 声明一个SharedPreferences
	private String FILE = "saveGesturePwd";// 用于保存SharedPreferences的文件
	private static DemoApplication context;

	public static DemoApplication getApplication() {
		return context;
	}

	/** 存储本客户信息 **/
	public LoginResponse response = null;
	/** 存储老用户信息 **/
	public CustInfoResponseResult clientInfo = null;

	public LoginResponse getResponse() {
		return response;
	}

	public void setResponse(LoginResponse response) {
		this.response = response;
	}

	public CustInfoResponseResult getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(CustInfoResponseResult clientInfo) {
		this.clientInfo = clientInfo;
	}

	// 基本资料
	public BasicInfo basicInfo;
	// 职业信息
	public ProfessionalInfo professionInfo;
	// 资产信息
	public AssertInfo assertInfo;
	// 贷款申请信息
	public LoanApplyPersonalInfo applyPersonalInfo;
	// 联系人信息
	public LinkManInfo linkManInfo;

	public EasyLoanRequest easyLoanRequest;

	public EasyLoanRequest getEasyLoanRequest() {
		return easyLoanRequest;
	}

	public void setEasyLoanRequest(EasyLoanRequest easyLoanRequest) {
		this.easyLoanRequest = easyLoanRequest;
	}

	public ProfessionalInfo getProfessionInfo() {
		if (professionInfo == null) {
			professionInfo = new ProfessionalInfo();
		}
		return professionInfo;
	}

	public void setProfessionInfo(ProfessionalInfo professionInfo) {
		this.professionInfo = professionInfo;
	}

	public BasicInfo getBasicInfo() {
		if (basicInfo == null) {
			basicInfo = new BasicInfo();
		}
		return basicInfo;
	}

	public void setBasicInfo(BasicInfo basicInfo) {
		this.basicInfo = basicInfo;
	}

	public LinkManInfo getLinkManInfo() {
		if (linkManInfo == null) {
			linkManInfo = new LinkManInfo();
		}
		return linkManInfo;
	}

	public void setLinkManInfo(LinkManInfo linkManInfo) {
		this.linkManInfo = linkManInfo;
	}

	public AssertInfo getAssertInfo() {
		assertInfo = AssertInfo.getInstance();
		return assertInfo;
	}

	public void setAssertInfo(AssertInfo assertInfo) {
		this.assertInfo = assertInfo;
	}

	public LoanApplyPersonalInfo getApplyPersonalInfo() {
		if (applyPersonalInfo == null) {
			applyPersonalInfo = new LoanApplyPersonalInfo();
		}
		return applyPersonalInfo;
	}

	public void setApplyPersonalInfo(LoanApplyPersonalInfo applyPersonalInfo) {
		this.applyPersonalInfo = applyPersonalInfo;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		// 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
		SDKInitializer.initialize(this);
		initBroadcastReceiver();
		//推送
		JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(DemoApplication.this);     		// 初始化 JPush
	}
	// 加广播

	private void initBroadcastReceiver() {
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		filter.addAction(Intent.ACTION_SCREEN_ON);
		registerReceiver(receiverS, filter);
	}

	private BroadcastReceiver receiverS = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			System.out.println(intent.getAction());
			if (Contents.IS_LOGIN) {
				
				sp0 = getSharedPreferences(FILE, MODE_PRIVATE);
				String type = "";
				type = sp0.getString("isTurnOn", "");
				if (type.equals("YES")) {
					if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction()))
						System.out.println("********** The Screen is locked");
					if(Contents.IS_LocStart!=1){
						Contents.IS_Loc=2;
					}
						System.out.println("DD**锁屏");
					if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())){
						System.out.println("DD**开屏");
				}
					}
				}
			 
		}
	};

    public DemoApplication()
    {
        super();
        context=this;
    }

 
 
}