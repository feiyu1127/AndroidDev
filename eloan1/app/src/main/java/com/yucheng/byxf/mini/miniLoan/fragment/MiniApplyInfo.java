package com.yucheng.byxf.mini.miniLoan.fragment;

import java.io.Serializable;

public class MiniApplyInfo implements Serializable {
	public static String MinicomPhoto1Path = null;
	public static String MinicomPhoto2Path = null;
	public static String MinicomPhoto4Path = null;
	public static String MninvideoPath = null;
	
	
	private int flag;
	
	//---------------------添加亲属和非亲属 工作单位信息
	
	/**
	 * 亲属 工作单位
	 */
	private String danweiOne;
	/**
	 *非亲属工作单位 
	 */
	private String danweiTwo;
	/**
	 * 户籍地址详细地址
	 */
	private String hujiXiangQing;
	
	

	/**
	 * 当前登录用户的身份证号码
	 */
	private String idCard;
	/**
	 * 申请人中文名称
	 */
	private String custName;
	/**
	 * 英文或拼音姓名
	 */
	private String custSpell;
	/**
	 * 性别 1男 2女 3未知 4未说明
	 */
	private String sex;
	/**
	 * 身份证类型
	 */
	private int idType;
	/**
	 * 出生日期
	 */
	private String apptStartDate;
	/**
	 * 证件号码
	 */
	private String idNo;
	/**
	 * 证件有效期（启始）
	 */
	private String idTermBgn;
	/**
	 * 证件有效期（结束）
	 */
	private String idTermEnd;
	/**
	 * 户籍地址
	 */
	private String indivRegAddr;
	/**
	 * 户籍地址邮编
	 */
	private String indivRegAddrZip;
	/**
	 * 婚姻状况 字典：10未婚 20已婚 90其他
	 */
	private int indivMarital = -1;
	/**
	 * 供养人数
	 */
	private int dependentPersons;
	/**
	 * 文化程度 1"初中及以下",2 "高中或中专", 3"大专", 4"本科", 5"硕士",6"博士"
	 */
	private short indivDegree = -1;
	/**
	 * 手机号码
	 */
	private String indivMobile;
	/**
	 * 手机号码2
	 */
	private String indivMobileAno;
	/**
	 * 家庭电话区号
	 */
	private String indivFmyZone;
	/**
	 * 家庭电话
	 */
	private String indivFmyTel;
	/**
	 * 现居住地址
	 */
	private String indivLiveAddr;
	/**
	 * 现居住地址邮编
	 */
	private int indivLiveZip;
	/**
	 * 现居住住房性质 字典，1 自购无贷款 2自购有贷款 3单位宿舍 4与亲属同住 5租住 7其他
	 */
	private short indivLive = 0;
	/**
	 * 月租金/月还款额(元)
	 */
	private String indivRentAmt;
	/**
	 * 现单位名称
	 */
	private String indivEmp;
	/**
	 * 任职部门
	 */
	private String indivBranch;
	/**
	 * 单位电话_区号
	 */
	private String indivEmpZone;
	/**
	 * 单位电话_电话号码
	 */
	private String indivEmpTelNo;
	/**
	 * 单位电话_分机号
	 */
	private String indivEmpTelSub;
	/**
	 * 单位地址
	 */
	private String indivEmpAddr;
	/**
	 * 单位地址邮编
	 */
	private String indivEmpZip;
	/**
	 * 本单位工作年限
	 */
	private short indivEmpYrs;
	/**
	 * 总工作年限
	 */
	private short indivWorkYrs;
	/**
	 * 个人税后工资月收入
	 */
	private String indivMthInc;
	/**
	 * 现任职位 字典：1 高级管理人员 2一般管理人员 3一般正式员工 4非正式员工 5无 6企业负责人 7中层管理人员 9其他11厅局级以上
	 * 12处级13科级 14一般干部 15退休 16失业
	 */
	private short indivPosition = -1;
	/**
	 * 还款账户开户行
	 */
	private String applRpymAcBank;
	/**
	 * 还款账号
	 */
	private String applRpymAcNo;
	/**
	 * 还款账号户名
	 */
	private String applRpymAcNam;
	/**
	 * 还款设置 字典：ALL全部应还款 SUB最低还款额
	 */
	private String rpymOption;
	/**
	 * 信件寄送地址类型 字典：01单位 00住宅
	 */
	private short sendTyp = -1;
	/**
	 * 账单日 字典：06（默认） 16 26
	 */
	private short atpyDay;
	/**
	 * 直系亲属联系人名称
	 */
	private String indivRelName;
	/**
	 * 直系亲属与申请人关系 字典：'01':'配偶', '02':'父母','03':'子女','09':'兄弟姐妹
	 */
	private short indivRelation = -1;
	/**
	 * 直系亲属电话区号
	 */
	private String indivRelZone;
	/**
	 * 直系联系电话
	 */
	private String indivRelPhone;
	/**
	 * 直系联系人手机
	 */
	private String indivRelMobile;
	/**
	 * 非亲属联系人姓名
	 */
	private String indivOthName;
	/**
	 * 非亲属与联系人关系 字典：'06':'同事' '10':'同学 '11':'朋友 '08':'其他关系
	 */
	private short indivOthRel = -1;
	/**
	 * 非亲属联系人单位(电话号码)
	 */
	private String indivOthPhone;
	/**
	 * 非亲属联系人电话(区号)
	 */
	private String indivOthZone;
	/**
	 * 非亲属联系人手机号
	 */
	private String indivOthMobile;
	/**
	 * 第三联系人姓名 lcAppRelations[0].indivRelName
	 */
	private String indivThiName;
	/**
	 * 第三联系人工作单位名称 lcAppRelations[0].indivRelEmp
	 */
	private String indivThiEmp;
	/**
	 * 第三联系人与联系人工作关系 lcAppRelations[0].indivRelation
	 */
	private short indivThiRelation;
	/**
	 * 第三联系人联系人区号 lcAppRelations[0].indivRelZone
	 */
	private String indivThiZone;
	/**
	 * 第三联系人联系人住宅电话号码 lcAppRelations[0].indivRelPhone
	 */
	private String indivThiPhone;
	/**
	 * 第三联系人联系人手机号 lcAppRelations[0].indivRelMobile
	 */
	private String indivThiMobile;
	/**
	 * 第三联系人联系类型 lcAppRelations[0].indivRelTyp
	 */
	private String indivThiTyp;

	/**
	 * 第四联系人姓名 lcAppRelations[0].indivRelName
	 */
	private String indivFouName;
	/**
	 * 第四联系人工作单位名称 lcAppRelations[0].indivRelEmp
	 */
	private String indivFouEmp;
	/**
	 * 第四联系人与联系人工作关系 lcAppRelations[0].indivRelation
	 */
	private short indivThiFouation;
	/**
	 * 第四联系人联系人区号 lcAppRelations[0].indivRelZone
	 */
	private String indivFouZone;
	/**
	 * 第四联系人联系人住宅电话号码 lcAppRelations[0].indivRelPhone
	 */
	private String indivFouPhone;
	/**
	 * 第四联系人联系人手机号 lcAppRelations[0].indivRelMobile
	 */
	private String indivFouMobile;
	/**
	 * 第四联系人联系类型 lcAppRelations[0].indivRelTyp
	 */
	private String indivFouTyp;
	/**
	 * 推广员工ID/经办人工号
	 */
	private String operator;
	/**
	 * 卡版面类型 A竖版 B横版
	 */
	private String layout;
	/**
	 * 交易地址
	 */
	private String location;
	/**
	 * 经度
	 */
	private String longitude;
	/**
	 * 纬度
	 */
	private String latitude;
	/**
	 * 设备ID
	 */
	private String deviceId;
	/**
	 * 设备型号
	 */
	private String deviceType;
	/**
	 * 设备名称
	 */
	private String deviceName;
	/**
	 * 设备制造商
	 */
	private String mfrs;
	/**
	 * 操作系统名称
	 */
	private String osName;
	/**
	 * 操作系统版本
	 */
	private String osVersion;
	/**
	 * 应用类型
	 */
	private String appName;
	/**
	 * 应用版本
	 */
	private String appVersion;
	/**
	 * 申请编号
	 */
	private String applCde;
	/**
	 * 返回码
	 */
	private String retCde;
	/**
	 * 返回信息
	 */
	private String errDesc;

	/*------------------------------------------------------------------------------------------------------*/
	/**
	 * 公司规模
	 */
	private String gsguimo;
	/**
	 * 单位性质
	 */
	private String dwxingzhi;
	/**
	 * 行业性质
	 */
	private String hyxingzhi;
	/**
	 * 所在职位
	 */
	private String szzhiwei;
	/**
	 * 借记卡卡号
	 */
	private String jiejikaCard;
	/**
	 * 推广员工id
	 */
	private String tuiguangId="";
	/**
	 * 推广员工姓名
	 */
	private String tuiguangName="";
	/**
	 * 卡面选择
	 */
	private String Cardxuanze;
	
	
	public String getHujiXiangQing() {
		return hujiXiangQing;
	}

	public void setHujiXiangQing(String hujiXiangQing) {
		this.hujiXiangQing = hujiXiangQing;
	}

	public String getDanweiOne() {
		return danweiOne;
	}

	public void setDanweiOne(String danweiOne) {
		this.danweiOne = danweiOne;
	}

	public String getDanweiTwo() {
		return danweiTwo;
	}

	public void setDanweiTwo(String danweiTwo) {
		this.danweiTwo = danweiTwo;
	}

	public String getCardxuanze() {
		return Cardxuanze;
	}

	public void setCardxuanze(String cardxuanze) {
		Cardxuanze = cardxuanze;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustSpell() {
		return custSpell;
	}

	public void setCustSpell(String custSpell) {
		this.custSpell = custSpell;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getIdType() {
		return idType;
	}

	public void setIdType(int idType) {
		this.idType = idType;
	}

	public String getApptStartDate() {
		return apptStartDate;
	}

	public void setApptStartDate(String apptStartDate) {
		this.apptStartDate = apptStartDate;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getIdTermBgn() {
		return idTermBgn;
	}

	public void setIdTermBgn(String idTermBgn) {
		this.idTermBgn = idTermBgn;
	}

	public String getIdTermEnd() {
		return idTermEnd;
	}

	public void setIdTermEnd(String idTermEnd) {
		this.idTermEnd = idTermEnd;
	}

	public String getIndivRegAddr() {
		return indivRegAddr;
	}

	public void setIndivRegAddr(String indivRegAddr) {
		this.indivRegAddr = indivRegAddr;
	}

	public String getIndivRegAddrZip() {
		return indivRegAddrZip;
	}

	public void setIndivRegAddrZip(String indivRegAddrZip) {
		this.indivRegAddrZip = indivRegAddrZip;
	}

	public int getIndivMarital() {
		return indivMarital;
	}

	public void setIndivMarital(int indivMarital) {
		this.indivMarital = indivMarital;
	}

	public int getDependentPersons() {
		return dependentPersons;
	}

	public void setDependentPersons(int dependentPersons) {
		this.dependentPersons = dependentPersons;
	}

	public short getIndivDegree() {
		return indivDegree;
	}

	public void setIndivDegree(short indivDegree) {
		this.indivDegree = indivDegree;
	}

	public String getIndivMobile() {
		return indivMobile;
	}

	public void setIndivMobile(String indivMobile) {
		this.indivMobile = indivMobile;
	}

	public String getIndivMobileAno() {
		return indivMobileAno;
	}

	public void setIndivMobileAno(String indivMobileAno) {
		this.indivMobileAno = indivMobileAno;
	}

	public String getIndivFmyZone() {
		return indivFmyZone;
	}

	public void setIndivFmyZone(String indivFmyZone) {
		this.indivFmyZone = indivFmyZone;
	}

	public String getIndivFmyTel() {
		return indivFmyTel;
	}

	public void setIndivFmyTel(String indivFmyTel) {
		this.indivFmyTel = indivFmyTel;
	}

	public String getIndivLiveAddr() {
		return indivLiveAddr;
	}

	public void setIndivLiveAddr(String indivLiveAddr) {
		this.indivLiveAddr = indivLiveAddr;
	}

	public int getIndivLiveZip() {
		return indivLiveZip;
	}

	public void setIndivLiveZip(int indivLiveZip) {
		this.indivLiveZip = indivLiveZip;
	}

	public short getIndivLive() {
		return indivLive;
	}

	public void setIndivLive(short indivLive) {
		this.indivLive = indivLive;
	}

	public String getIndivRentAmt() {
		return indivRentAmt;
	}

	public void setIndivRentAmt(String indivRentAmt) {
		this.indivRentAmt = indivRentAmt;
	}

	public String getIndivEmp() {
		return indivEmp;
	}

	public void setIndivEmp(String indivEmp) {
		this.indivEmp = indivEmp;
	}

	public String getIndivBranch() {
		return indivBranch;
	}

	public void setIndivBranch(String indivBranch) {
		this.indivBranch = indivBranch;
	}

	public String getIndivEmpZone() {
		return indivEmpZone;
	}

	public void setIndivEmpZone(String indivEmpZone) {
		this.indivEmpZone = indivEmpZone;
	}

	public String getIndivEmpTelNo() {
		return indivEmpTelNo;
	}

	public void setIndivEmpTelNo(String indivEmpTelNo) {
		this.indivEmpTelNo = indivEmpTelNo;
	}

	public String getIndivEmpTelSub() {
		return indivEmpTelSub;
	}

	public void setIndivEmpTelSub(String indivEmpTelSub) {
		this.indivEmpTelSub = indivEmpTelSub;
	}

	public String getIndivEmpAddr() {
		return indivEmpAddr;
	}

	public void setIndivEmpAddr(String indivEmpAddr) {
		this.indivEmpAddr = indivEmpAddr;
	}

	public String getIndivEmpZip() {
		return indivEmpZip;
	}

	public void setIndivEmpZip(String indivEmpZip) {
		this.indivEmpZip = indivEmpZip;
	}

	public short getIndivEmpYrs() {
		return indivEmpYrs;
	}

	public void setIndivEmpYrs(short indivEmpYrs) {
		this.indivEmpYrs = indivEmpYrs;
	}

	public short getIndivWorkYrs() {
		return indivWorkYrs;
	}

	public void setIndivWorkYrs(short indivWorkYrs) {
		this.indivWorkYrs = indivWorkYrs;
	}

	public String getIndivMthInc() {
		return indivMthInc;
	}

	public void setIndivMthInc(String indivMthInc) {
		this.indivMthInc = indivMthInc;
	}

	public short getIndivPosition() {
		return indivPosition;
	}

	public void setIndivPosition(short indivPosition) {
		this.indivPosition = indivPosition;
	}

	public String getApplRpymAcBank() {
		return applRpymAcBank;
	}

	public void setApplRpymAcBank(String applRpymAcBank) {
		this.applRpymAcBank = applRpymAcBank;
	}

	public String getApplRpymAcNo() {
		return applRpymAcNo;
	}

	public void setApplRpymAcNo(String applRpymAcNo) {
		this.applRpymAcNo = applRpymAcNo;
	}

	public String getApplRpymAcNam() {
		return applRpymAcNam;
	}

	public void setApplRpymAcNam(String applRpymAcNam) {
		this.applRpymAcNam = applRpymAcNam;
	}

	public String getRpymOption() {
		return rpymOption;
	}

	public void setRpymOption(String rpymOption) {
		this.rpymOption = rpymOption;
	}

	public short getSendTyp() {
		return sendTyp;
	}

	public void setSendTyp(short sendTyp) {
		this.sendTyp = sendTyp;
	}

	public short getAtpyDay() {
		return atpyDay;
	}

	public void setAtpyDay(short atpyDay) {
		this.atpyDay = atpyDay;
	}

	public String getIndivRelName() {
		return indivRelName;
	}

	public void setIndivRelName(String indivRelName) {
		this.indivRelName = indivRelName;
	}

	public short getIndivRelation() {
		return indivRelation;
	}

	public void setIndivRelation(short indivRelation) {
		this.indivRelation = indivRelation;
	}

	public String getIndivRelZone() {
		return indivRelZone;
	}

	public void setIndivRelZone(String indivRelZone) {
		this.indivRelZone = indivRelZone;
	}

	public String getIndivRelPhone() {
		return indivRelPhone;
	}

	public void setIndivRelPhone(String indivRelPhone) {
		this.indivRelPhone = indivRelPhone;
	}

	public String getIndivRelMobile() {
		return indivRelMobile;
	}

	public void setIndivRelMobile(String indivRelMobile) {
		this.indivRelMobile = indivRelMobile;
	}

	public String getIndivOthName() {
		return indivOthName;
	}

	public void setIndivOthName(String indivOthName) {
		this.indivOthName = indivOthName;
	}

	public short getIndivOthRel() {
		return indivOthRel;
	}

	public void setIndivOthRel(short indivOthRel) {
		this.indivOthRel = indivOthRel;
	}

	public String getIndivOthPhone() {
		return indivOthPhone;
	}

	public void setIndivOthPhone(String indivOthPhone) {
		this.indivOthPhone = indivOthPhone;
	}

	public String getIndivOthZone() {
		return indivOthZone;
	}

	public void setIndivOthZone(String indivOthZone) {
		this.indivOthZone = indivOthZone;
	}

	public String getIndivOthMobile() {
		return indivOthMobile;
	}

	public void setIndivOthMobile(String indivOthMobile) {
		this.indivOthMobile = indivOthMobile;
	}

	public String getIndivThiName() {
		return indivThiName;
	}

	public void setIndivThiName(String indivThiName) {
		this.indivThiName = indivThiName;
	}

	public String getIndivThiEmp() {
		return indivThiEmp;
	}

	public void setIndivThiEmp(String indivThiEmp) {
		this.indivThiEmp = indivThiEmp;
	}

	public short getIndivThiRelation() {
		return indivThiRelation;
	}

	public void setIndivThiRelation(short indivThiRelation) {
		this.indivThiRelation = indivThiRelation;
	}

	public String getIndivThiZone() {
		return indivThiZone;
	}

	public void setIndivThiZone(String indivThiZone) {
		this.indivThiZone = indivThiZone;
	}

	public String getIndivThiPhone() {
		return indivThiPhone;
	}

	public void setIndivThiPhone(String indivThiPhone) {
		this.indivThiPhone = indivThiPhone;
	}

	public String getIndivThiMobile() {
		return indivThiMobile;
	}

	public void setIndivThiMobile(String indivThiMobile) {
		this.indivThiMobile = indivThiMobile;
	}

	public String getIndivThiTyp() {
		return indivThiTyp;
	}

	public void setIndivThiTyp(String indivThiTyp) {
		this.indivThiTyp = indivThiTyp;
	}

	public String getIndivFouName() {
		return indivFouName;
	}

	public void setIndivFouName(String indivFouName) {
		this.indivFouName = indivFouName;
	}

	public String getIndivFouEmp() {
		return indivFouEmp;
	}

	public void setIndivFouEmp(String indivFouEmp) {
		this.indivFouEmp = indivFouEmp;
	}

	public short getIndivThiFouation() {
		return indivThiFouation;
	}

	public void setIndivThiFouation(short indivThiFouation) {
		this.indivThiFouation = indivThiFouation;
	}

	public String getIndivFouZone() {
		return indivFouZone;
	}

	public void setIndivFouZone(String indivFouZone) {
		this.indivFouZone = indivFouZone;
	}

	public String getIndivFouPhone() {
		return indivFouPhone;
	}

	public void setIndivFouPhone(String indivFouPhone) {
		this.indivFouPhone = indivFouPhone;
	}

	public String getIndivFouMobile() {
		return indivFouMobile;
	}

	public void setIndivFouMobile(String indivFouMobile) {
		this.indivFouMobile = indivFouMobile;
	}

	public String getIndivFouTyp() {
		return indivFouTyp;
	}

	public void setIndivFouTyp(String indivFouTyp) {
		this.indivFouTyp = indivFouTyp;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getMfrs() {
		return mfrs;
	}

	public void setMfrs(String mfrs) {
		this.mfrs = mfrs;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getApplCde() {
		return applCde;
	}

	public void setApplCde(String applCde) {
		this.applCde = applCde;
	}

	public String getRetCde() {
		return retCde;
	}

	public void setRetCde(String retCde) {
		this.retCde = retCde;
	}

	public String getErrDesc() {
		return errDesc;
	}

	public void setErrDesc(String errDesc) {
		this.errDesc = errDesc;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getGsguimo() {
		return gsguimo;
	}

	public void setGsguimo(String gsguimo) {
		this.gsguimo = gsguimo;
	}

	public String getDwxingzhi() {
		return dwxingzhi;
	}

	public void setDwxingzhi(String dwxingzhi) {
		this.dwxingzhi = dwxingzhi;
	}

	public String getHyxingzhi() {
		return hyxingzhi;
	}

	public void setHyxingzhi(String hyxingzhi) {
		this.hyxingzhi = hyxingzhi;
	}

	public String getSzzhiwei() {
		return szzhiwei;
	}

	public void setSzzhiwei(String szzhiwei) {
		this.szzhiwei = szzhiwei;
	}

	public String getJiejikaCard() {
		return jiejikaCard;
	}

	public void setJiejikaCard(String jiejikaCard) {
		this.jiejikaCard = jiejikaCard;
	}

	public String getTuiguangId() {
		return tuiguangId;
	}

	public void setTuiguangId(String tuiguangId) {
		this.tuiguangId = tuiguangId;
	}

	public String getTuiguangName() {
		return tuiguangName;
	}

	public void setTuiguangName(String tuiguangName) {
		this.tuiguangName = tuiguangName;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	

}
