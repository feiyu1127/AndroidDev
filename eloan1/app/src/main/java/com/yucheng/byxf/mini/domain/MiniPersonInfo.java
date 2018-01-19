package com.yucheng.byxf.mini.domain;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class MiniPersonInfo {
	/* 身份信息1 */
	public static String name = "";// 姓名
	public static String nationality;// 民族
	public static int nationalityID;// 民族Spinner的序号
	public static String birthday;// 出生日期
	public static String cardType;// 证件类型
	public static String cardNum;// 证件号码
	public static String expiryStartDate;// 证件有效期起始日期
	public static String expiryEndDate;// 证件有效期终止日期
	public static String birthPlace;// 籍贯
	public static String sex;// 性别
	/* 身份信息2 */
	public static String maritalInfo = "";// 婚姻状况
	public static String house_hold;// 供养人数
	public static String standDegree;// 文化程度
	public static String province;// 现居住地址
	public static String permanentAddress;// 户籍地址
	public static String areaCard;// 住宅电话区号
	public static String phoneNo;// 住宅电话
	public static String mobile;// 手机号
	public static String houseNoture;// 住宅性质
	public static String careerInfo;// 在职状况
	/* 身份信息3 */
	public static String nowDpName = "";// 现单位全称
	public static String rzDp;// 任职部门
	public static String professionYear;// 本单位工作年限
	public static String totalProfessionYear;// 总工作年限
	public static String dpPlace;// 单位地址
	public static String areaNum;// 单位电话区号
	public static String telNum;// 单位电话号码
	public static String extensionNum;// 单位电话号码分机号
	public static String email_address;// 邮寄地址
	public static String dpNature;// 单位性质
	public static String tradeNature;// 行业性质
	public static String nowRole;// 现任职位
	public static String money_Year;// 申请人年收入
	/* 身份信息4 */
	public static String qinshu_name = "";// 直系亲属联系人姓名

	public static String qinshu_mobilephone;// 直系亲属手机号码
	public static int qinshu_relationshipID;// 与申请人关系id
	public static String qinshu_relationship;// 与申请人关系
	public static String qinshu_phone_quhao;// 直系亲属电话区号
	public static String qinshu_phone_dianhua;// 直系亲属电话
	// 第一个非亲属
	public static String contact_name;// 非亲属联系人姓名
	public static String contact_mobilephone;// 非亲属联系人手机号码
	public static String contact_relationship;// 非亲属联系人与申请人关系
	public static int contact_relationshipID;// 非亲属联系人与申请人关系id
	public static String contact_phone_quhao;// 非亲属联系人电话区号
	public static String contact_phone_dianhua;// 非亲属联系人电话
	// 第二个非亲属
	public static String contact_name1;// 非亲属联系人姓名
	public static String contact_mobilephone1;// 非亲属联系人手机号码
	public static String contact_relationship1;// 非亲属联系人与申请人关系
	public static int contact_relationshipID1;// 非亲属联系人与申请人关系id
	public static String contact_phone_quhao1;// 非亲属联系人电话区号
	public static String contact_phone_dianhua1;// 非亲属联系人电话
	// 第三个非亲属
	public static String contact_name2;// 非亲属联系人姓名
	public static String contact_mobilephone2;// 非亲属联系人手机号码
	public static String contact_relationship2;// 非亲属联系人与申请人关系
	public static int contact_relationshipID2;// 非亲属联系人与申请人关系id
	public static String contact_phone_quhao2;// 非亲属联系人电话区号
	public static String contact_phone_dianhua2;// 非亲属联系人电话
	/* 身份信息5 */
	public static String zhangdan = "";// 账单日
	public static String card_num;// 借记卡号
	public static String huankuan_setting;// 还款设置
	public static String spread_no;// 推广员工ID
	public static String spread_name;// 推广员工姓名
	/* 证件拍照 */
//	public static Drawable image0 = null;
//	public static Drawable image1 = null;
//	public static Drawable image2 = null;
//	public static Drawable image3 = null;
//	public static Drawable image4 = null;
//	
//	public static Drawable image50 = null;
//	public static Drawable image51 = null;
//	public static Drawable image52 = null;
//	public static Drawable image53 = null;
//	public static Drawable image54 = null;

	public static String photoPath1;// 照片存储路径1
	public static String photoPath2;// 照片存储路径2
	public static String photoPath3;// 照片存储路径3
	public static String photoPath4;// 照片存储路径4
	public static String photoPath5;// 照片存储路径5
	
	public static String photoPath51;// 照片存储路径1
	public static String photoPath52;// 照片存储路径2
	public static String photoPath53;// 照片存储路径3
	public static String photoPath54;// 照片存储路径4
	public static String photoPath55;// 照片存储路径5
	
	// 压缩后的图片
	public static String compress_photoPath1;// 照片存储路径1
	public static String compress_photoPath2;// 照片存储路径2
	public static String compress_photoPath3;// 照片存储路径3
	public static String compress_photoPath4;// 照片存储路径4
	public static String compress_photoPath5;// 照片存储路径5
	
	public static String compress_photoPath51;// 照片存储路径1
	public static String compress_photoPath52;// 照片存储路径2
	public static String compress_photoPath53;// 照片存储路径3
	public static String compress_photoPath54;// 照片存储路径4
	public static String compress_photoPath55;// 照片存储路径5

	public static String applCde;// 申请编号
	public static String applSeq;// 申请流水号
	public static String applDisbSeq;// 房款信息流水号；
	public static String applRpymSeq;// 还款信息流水号
	public static String apptSeq;// 与贷款相关流水号
	
	
	public static String card = "";// 卡面

	/**
	 * 清除所有全局数据
	 * */
	public static void clearAll() {
		name = "";// 姓名
		nationality = "";// 民族
		nationalityID = -1;// 民族Spinner的序号
		birthday = "";// 出生日期
		cardType = "";// 证件类型
		cardNum = "";// 证件号码
		expiryStartDate = "";// 证件有效期起始日期
		expiryEndDate = "";// 证件有效期终止日期
		birthPlace = "";// 籍贯
		sex = "";// 性别
		maritalInfo = "";// 婚姻状况
		house_hold = "";// 供养人数
		standDegree = "";// 文化程度
		province = "";// 现居住地址
		permanentAddress = "";// 户籍地址
		areaCard = "";// 住宅电话区号
		phoneNo = "";// 住宅电话
		mobile = "";// 手机号
		houseNoture = "";// 住宅性质
		careerInfo = "";// 在职状况
		nowDpName = "";// 现单位全称
		rzDp = "";// 任职部门
		professionYear = "";// 本单位工作年限
		totalProfessionYear = "";// 总工作年限
		dpPlace = "";// 单位地址
		areaNum = "";// 单位电话区号
		telNum = "";// 单位电话号码
		extensionNum = "";// 单位电话号码分机号
		email_address = "";// 邮寄地址
		dpNature = "";// 单位性质
		tradeNature = "";// 行业性质
		nowRole = "";// 现任职位
		money_Year = "";// 申请人年收入
		qinshu_name = "";// 直系亲属联系人姓名
		qinshu_mobilephone = "";// 直系亲属手机号码
		qinshu_relationshipID = -1;// 与申请人关系id
		qinshu_relationship = "";// 与申请人关系
		qinshu_phone_quhao = "";// 直系亲属电话区号
		qinshu_phone_dianhua = "";// 直系亲属电话
		contact_name = "";// 非亲属联系人姓名
		contact_mobilephone = "";// 非亲属联系人手机号码
		contact_relationship = "";// 非亲属联系人与申请人关系
		contact_relationshipID = -1;// 非亲属联系人与申请人关系id
		contact_phone_quhao = "";// 非亲属联系人电话区号
		contact_phone_dianhua = "";// 非亲属联系人电话
		contact_name1 = "";// 非亲属联系人姓名
		contact_mobilephone1 = "";// 非亲属联系人手机号码
		contact_relationship1 = "";// 非亲属联系人与申请人关系
		contact_relationshipID1 = -1;// 非亲属联系人与申请人关系id
		contact_phone_quhao1 = "";// 非亲属联系人电话区号
		contact_phone_dianhua1 = "";// 非亲属联系人电话
		contact_name2 = "";// 非亲属联系人姓名
		contact_mobilephone2 = "";// 非亲属联系人手机号码
		contact_relationship2 = "";// 非亲属联系人与申请人关系
		contact_relationshipID2 = -1;// 非亲属联系人与申请人关系id
		contact_phone_quhao2 = "";// 非亲属联系人电话区号
		contact_phone_dianhua2 = "";// 非亲属联系人电话
		zhangdan = "";// 账单日
		card_num = "";// 借记卡号
		huankuan_setting = "";// 还款设置
		spread_no = "";// 推广员工ID
		spread_name = "";// 推广员工姓名
		/* 证件拍照 */
//		image0 = null;
//		image1 = null;
//		image2 = null;
//		image3 = null;
//		image4 = null;

		photoPath1 = null;
		photoPath2 = null;
		photoPath3 = null;
		photoPath4 = null;
		photoPath5 = null;

		compress_photoPath1 = null;
		compress_photoPath2 = null;
		compress_photoPath3 = null;
		compress_photoPath4 = null;
		compress_photoPath5 = null;
		
		card = null;
	}
}
