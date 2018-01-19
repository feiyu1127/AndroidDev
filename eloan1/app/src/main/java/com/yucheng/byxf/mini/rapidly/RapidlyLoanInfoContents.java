package com.yucheng.byxf.mini.rapidly;

import com.yucheng.byxf.mini.message.MiniConfirmMessage;
import com.yucheng.byxf.mini.message.RapidlyLoanInfo;

public class RapidlyLoanInfoContents {
	public static RapidlyLoanInfo rapidlyLoanInfo = null;
	//
	public static MiniConfirmMessage MiniConfirmMessage=null;
	
	public static String comPhoto1Path = null;
	public static String comPhoto2Path = null;
	
	public static Integer age ;
	
	public static String sex = null;
	
	public static String videoPath = null;
	
	public static String apptStartDate = null;
	
	//
	


	public static RapidlyLoanInfo getRapidlyLoanInfo() {
		return rapidlyLoanInfo;
	}

	public static void setRapidlyLoanInfo(RapidlyLoanInfo rapidlyLoanInfo) {
		RapidlyLoanInfoContents.rapidlyLoanInfo = rapidlyLoanInfo;
	}

	public static MiniConfirmMessage getMiniConfirmMessage() {
		return MiniConfirmMessage;
	}

	public static void setMiniConfirmMessage(MiniConfirmMessage miniConfirmMessage) {
		MiniConfirmMessage = miniConfirmMessage;
	}
	
}
