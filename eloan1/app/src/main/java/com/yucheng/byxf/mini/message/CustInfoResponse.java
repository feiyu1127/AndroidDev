package com.yucheng.byxf.mini.message;

/******************************************************************************
 * 
 * 北银消费金融公司-移动业务终端三期-轻松e贷
 *
 * Copyright © 2014 YuchengTech Technologies Limited All Rights Reserved.
 * 
 * Created on 2014-2-22 16:20:58
 *
 *******************************************************************************/
import java.util.ArrayList;
import java.util.List;

/**
 * 客户信息查询实体类
 * 
 * @version 1.0
 * 
 * @author neo
 */
public class CustInfoResponse implements java.io.Serializable {

	/**
	 * cbd 返回码
	 */
	private String retCode;
	private String contZip;
	private String contZone;
	private String contPhone;

	public String getContZone() {
		return contZone;
	}

	public void setContZone(String contZone) {
		this.contZone = contZone;
	}

	public String getContPhone() {
		return contPhone;
	}

	public void setContPhone(String contPhone) {
		this.contPhone = contPhone;
	}

	public String getContZip() {
		return contZip;
	}

	public void setContZip(String contZip) {
		this.contZip = contZip;
	}

	/**
	 * 返回信息
	 */
	private String retInfo;
	/**
	 * 客户名称
	 */
	private String custName;
	/**
	 * 出生日期
	 */
	private String indivBirth;
	/**
	 * 本单位工作年限
	 */
	private String indivEmpYrs;
	/**
	 * 总工作年限
	 */
	private String indivWorkYrs;
	/**
	 * 联系人姓名<
	 */
	private String indivRelName;
	/**
	 * 联系人工作单位名称
	 */
	private String indivRelEmp;
	/**
	 * 与联系人关系
	 */
	private String indivRelation;
	/**
	 * 联系人_住宅电话_区号
	 */
	private String indivRelZone;
	/**
	 * 联系人_住宅电话_电话号码
	 */
	private String indivRelPhone;
	/**
	 * 联系人_手机号
	 */
	private String indivRelMobile;
	/**
	 * 证件号码
	 */
	private String idNo;
	/**
	 * 非亲属联系人姓名
	 */
	private String indivOthName;
	/**
	 * 非亲属联系人单位名称
	 */
	private String indivOthEmp;
	/**
	 * 非亲属联系人(与联系人关系)
	 */
	private String indivOthRel;
	/**
	 * 非亲属联系人单位(电话号码)
	 */
	private String indivOthPhone;
	/**
	 * 分亲属联系人单位(区号)
	 */
	private String indivOthZone;
	/**
	 * 非亲属联系人手机号
	 */
	private String indivOthMobile;
	/**
	 * 证件有效期(起始)
	 */
	private String idTermBgn;
	/**
	 * 证件有效期(结束)
	 */
	private String idTermEnd;
	/**
	 * 证件发证国家
	 */
	private String idCtry;
	/**
	 * 证件类型
	 */
	private String idType;
	/**
	 * 出生日期
	 */
	private String apptStartDate;
	/**
	 * 性别
	 */
	private String indivSex;
	/**
	 * 供养人数
	 */
	private String indivDepNo;
	/**
	 * 婚姻状况
	 */
	private String indivMarital;
	/**
	 * 现居住住房性质
	 */
	private String indivLive;
	/**
	 * 现居住地址
	 */
	private String indivLiveAddr;
	/**
	 * 户籍邮编
	 */
	private String indivRegZip;
	/**
	 * 房租金额
	 */
	private String indivRentAmt;
	/**
	 * 任职部门
	 */
	private String indivBranch;
	/**
	 * 单位性质
	 */
	private String indivEmpType;
	/**
	 * 单位规模
	 */
	private String indivEmpNum;
	/**
	 * 申请单行业
	 */
	private String indivIndtryPaper;
	/**
	 * 职务
	 */
	private String indivPosition;
	/**
	 * 个人工资月收入(元)
	 */
	private String indivMthInc;
	/**
	 * 是否有银行信用卡
	 */
	private String indivCardInd;
	/**
	 * 单张信用卡最高额度
	 */
	private String indivCardAmt;
	/**
	 * 发卡银行名称
	 */
	private String indivCardBank;
	/**
	 * 户籍地址
	 */
	private String indivRegAddr;
	/**
	 * 手机号
	 */
	private String indivMobile;
	/**
	 * 电子邮箱
	 */
	private String indivEmail;
	/**
	 * 现单位地址
	 */
	private String indivEmp;
	/**
	 * 职业
	 */
	private String indivOccup;
	/**
	 * 文化程度
	 */
	private String indivDegree;
	/**
	 * 客户号
	 */
	private String custId;
	/**
	 * 贷款相关人流水号
	 */
	private String apptSeq;
	/**
	 * 联系人信息
	 */
	private List<ApptContactInfo> apptContacts = new ArrayList<ApptContactInfo>();

	public CustInfoResponse() {
	}

	public CustInfoResponse(String retCode) {
		this.retCode = retCode;
	}

	public CustInfoResponse(String retCode, String retInfo, String custName,
			String indivBirth, String indivEmpYrs, String indivWorkYrs,
			String indivRelName, String indivRelEmp, String indivRelation,
			String indivRelZone, String indivRelPhone, String indivRelMobile,
			String idNo, String indivOthName, String indivOthEmp,
			String indivOthRel, String indivOthPhone, String indivOthZone,
			String indivOthMobile, String idTermBgn, String idTermEnd,
			String idCtry, String idType, String apptStartDate,
			String indivSex, String indivDepNo, String indivMarital,
			String indivLive, String indivLiveAddr, String indivRegZip,
			String indivRentAmt, String indivBranch, String indivEmpType,
			String indivEmpNum, String indivIndtryPaper, String indivPosition,
			String indivMthInc, String indivCardInd, String indivCardAmt,
			String indivCardBank, String indivRegAddr, String indivMobile,
			String indivEmail, String indivEmp, String indivOccup,
			String indivDegree, String custId, String apptSeq) {
		this.retCode = retCode;
		this.retInfo = retInfo;
		this.custName = custName;
		this.indivBirth = indivBirth;
		this.indivEmpYrs = indivEmpYrs;
		this.indivWorkYrs = indivWorkYrs;
		this.indivRelName = indivRelName;
		this.indivRelEmp = indivRelEmp;
		this.indivRelation = indivRelation;
		this.indivRelZone = indivRelZone;
		this.indivRelPhone = indivRelPhone;
		this.indivRelMobile = indivRelMobile;
		this.idNo = idNo;
		this.indivOthName = indivOthName;
		this.indivOthEmp = indivOthEmp;
		this.indivOthRel = indivOthRel;
		this.indivOthPhone = indivOthPhone;
		this.indivOthZone = indivOthZone;
		this.indivOthMobile = indivOthMobile;
		this.idTermBgn = idTermBgn;
		this.idTermEnd = idTermEnd;
		this.idCtry = idCtry;
		this.idType = idType;
		this.apptStartDate = apptStartDate;
		this.indivSex = indivSex;
		this.indivDepNo = indivDepNo;
		this.indivMarital = indivMarital;
		this.indivLive = indivLive;
		this.indivLiveAddr = indivLiveAddr;
		this.indivRegZip = indivRegZip;
		this.indivRentAmt = indivRentAmt;
		this.indivBranch = indivBranch;
		this.indivEmpType = indivEmpType;
		this.indivEmpNum = indivEmpNum;
		this.indivIndtryPaper = indivIndtryPaper;
		this.indivPosition = indivPosition;
		this.indivMthInc = indivMthInc;
		this.indivCardInd = indivCardInd;
		this.indivCardAmt = indivCardAmt;
		this.indivCardBank = indivCardBank;
		this.indivRegAddr = indivRegAddr;
		this.indivMobile = indivMobile;
		this.indivEmail = indivEmail;
		this.indivEmp = indivEmp;
		this.indivOccup = indivOccup;
		this.indivDegree = indivDegree;
		this.custId = custId;
		this.apptSeq = apptSeq;
	}

	/**
	 * 访问<返回码>属性
	 */
	public String getRetCode() {
		return this.retCode;
	}

	/**
	 * 设置<返回码>属性
	 */
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	/**
	 * 访问<返回信息>属性
	 */
	public String getRetInfo() {
		return this.retInfo;
	}

	/**
	 * 设置<返回信息>属性
	 */
	public void setRetInfo(String retInfo) {
		this.retInfo = retInfo;
	}

	/**
	 * 访问<客户名称>属性
	 */
	public String getCustName() {
		return this.custName;
	}

	/**
	 * 设置<客户名称>属性
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	/**
	 * 访问<出生日期>属性
	 */
	public String getIndivBirth() {
		return this.indivBirth;
	}

	/**
	 * 设置<出生日期>属性
	 */
	public void setIndivBirth(String indivBirth) {
		this.indivBirth = indivBirth;
	}

	/**
	 * 访问<本单位工作年限>属性
	 */
	public String getIndivEmpYrs() {
		return this.indivEmpYrs;
	}

	/**
	 * 设置<本单位工作年限>属性
	 */
	public void setIndivEmpYrs(String indivEmpYrs) {
		this.indivEmpYrs = indivEmpYrs;
	}

	/**
	 * 访问<总工作年限>属性
	 */
	public String getIndivWorkYrs() {
		return this.indivWorkYrs;
	}

	/**
	 * 设置<总工作年限>属性
	 */
	public void setIndivWorkYrs(String indivWorkYrs) {
		this.indivWorkYrs = indivWorkYrs;
	}

	/**
	 * 访问<联系人姓名<>属性
	 */
	public String getIndivRelName() {
		return this.indivRelName;
	}

	/**
	 * 设置<联系人姓名<>属性
	 */
	public void setIndivRelName(String indivRelName) {
		this.indivRelName = indivRelName;
	}

	/**
	 * 访问<联系人工作单位名称>属性
	 */
	public String getIndivRelEmp() {
		return this.indivRelEmp;
	}

	/**
	 * 设置<联系人工作单位名称>属性
	 */
	public void setIndivRelEmp(String indivRelEmp) {
		this.indivRelEmp = indivRelEmp;
	}

	/**
	 * 访问<与联系人关系>属性
	 */
	public String getIndivRelation() {
		return this.indivRelation;
	}

	/**
	 * 设置<与联系人关系>属性
	 */
	public void setIndivRelation(String indivRelation) {
		this.indivRelation = indivRelation;
	}

	/**
	 * 访问<联系人_住宅电话_区号>属性
	 */
	public String getIndivRelZone() {
		return this.indivRelZone;
	}

	/**
	 * 设置<联系人_住宅电话_区号>属性
	 */
	public void setIndivRelZone(String indivRelZone) {
		this.indivRelZone = indivRelZone;
	}

	/**
	 * 访问<联系人_住宅电话_电话号码>属性
	 */
	public String getIndivRelPhone() {
		return this.indivRelPhone;
	}

	/**
	 * 设置<联系人_住宅电话_电话号码>属性
	 */
	public void setIndivRelPhone(String indivRelPhone) {
		this.indivRelPhone = indivRelPhone;
	}

	/**
	 * 访问<联系人_手机号>属性
	 */
	public String getIndivRelMobile() {
		return this.indivRelMobile;
	}

	/**
	 * 设置<联系人_手机号>属性
	 */
	public void setIndivRelMobile(String indivRelMobile) {
		this.indivRelMobile = indivRelMobile;
	}

	/**
	 * 访问<证件号码>属性
	 */
	public String getIdNo() {
		return this.idNo;
	}

	/**
	 * 设置<证件号码>属性
	 */
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	/**
	 * 访问<非亲属联系人姓名>属性
	 */
	public String getIndivOthName() {
		return this.indivOthName;
	}

	/**
	 * 设置<非亲属联系人姓名>属性
	 */
	public void setIndivOthName(String indivOthName) {
		this.indivOthName = indivOthName;
	}

	/**
	 * 访问<非亲属联系人单位名称>属性
	 */
	public String getIndivOthEmp() {
		return this.indivOthEmp;
	}

	/**
	 * 设置<非亲属联系人单位名称>属性
	 */
	public void setIndivOthEmp(String indivOthEmp) {
		this.indivOthEmp = indivOthEmp;
	}

	/**
	 * 访问<非亲属联系人(与联系人关系)>属性
	 */
	public String getIndivOthRel() {
		return this.indivOthRel;
	}

	/**
	 * 设置<非亲属联系人(与联系人关系)>属性
	 */
	public void setIndivOthRel(String indivOthRel) {
		this.indivOthRel = indivOthRel;
	}

	/**
	 * 访问<非亲属联系人单位(电话号码)>属性
	 */
	public String getIndivOthPhone() {
		return this.indivOthPhone;
	}

	/**
	 * 设置<非亲属联系人单位(电话号码)>属性
	 */
	public void setIndivOthPhone(String indivOthPhone) {
		this.indivOthPhone = indivOthPhone;
	}

	/**
	 * 访问<分亲属联系人单位(区号)>属性
	 */
	public String getIndivOthZone() {
		return this.indivOthZone;
	}

	/**
	 * 设置<分亲属联系人单位(区号)>属性
	 */
	public void setIndivOthZone(String indivOthZone) {
		this.indivOthZone = indivOthZone;
	}

	/**
	 * 访问<非亲属联系人手机号>属性
	 */
	public String getIndivOthMobile() {
		return this.indivOthMobile;
	}

	/**
	 * 设置<非亲属联系人手机号>属性
	 */
	public void setIndivOthMobile(String indivOthMobile) {
		this.indivOthMobile = indivOthMobile;
	}

	/**
	 * 访问<证件有效期(起始)>属性
	 */
	public String getIdTermBgn() {
		return this.idTermBgn;
	}

	/**
	 * 设置<证件有效期(起始)>属性
	 */
	public void setIdTermBgn(String idTermBgn) {
		this.idTermBgn = idTermBgn;
	}

	/**
	 * 访问<证件有效期(结束)>属性
	 */
	public String getIdTermEnd() {
		return this.idTermEnd;
	}

	/**
	 * 设置<证件有效期(结束)>属性
	 */
	public void setIdTermEnd(String idTermEnd) {
		this.idTermEnd = idTermEnd;
	}

	/**
	 * 访问<证件发证国家>属性
	 */
	public String getIdCtry() {
		return this.idCtry;
	}

	/**
	 * 设置<证件发证国家>属性
	 */
	public void setIdCtry(String idCtry) {
		this.idCtry = idCtry;
	}

	/**
	 * 访问<证件类型>属性
	 */
	public String getIdType() {
		return this.idType;
	}

	/**
	 * 设置<证件类型>属性
	 */
	public void setIdType(String idType) {
		this.idType = idType;
	}

	/**
	 * 访问<出生日期>属性
	 */
	public String getApptStartDate() {
		return this.apptStartDate;
	}

	/**
	 * 设置<出生日期>属性
	 */
	public void setApptStartDate(String apptStartDate) {
		this.apptStartDate = apptStartDate;
	}

	/**
	 * 访问<性别>属性
	 */
	public String getIndivSex() {
		return this.indivSex;
	}

	/**
	 * 设置<性别>属性
	 */
	public void setIndivSex(String indivSex) {
		this.indivSex = indivSex;
	}

	/**
	 * 访问<供养人数>属性
	 */
	public String getIndivDepNo() {
		return this.indivDepNo;
	}

	/**
	 * 设置<供养人数>属性
	 */
	public void setIndivDepNo(String indivDepNo) {
		this.indivDepNo = indivDepNo;
	}

	/**
	 * 访问<婚姻状况>属性
	 */
	public String getIndivMarital() {
		return this.indivMarital;
	}

	/**
	 * 设置<婚姻状况>属性
	 */
	public void setIndivMarital(String indivMarital) {
		this.indivMarital = indivMarital;
	}

	/**
	 * 访问<现居住住房性质>属性
	 */
	public String getIndivLive() {
		return this.indivLive;
	}

	/**
	 * 设置<现居住住房性质>属性
	 */
	public void setIndivLive(String indivLive) {
		this.indivLive = indivLive;
	}

	/**
	 * 访问<现居住地址>属性
	 */
	public String getIndivLiveAddr() {
		return this.indivLiveAddr;
	}

	/**
	 * 设置<现居住地址>属性
	 */
	public void setIndivLiveAddr(String indivLiveAddr) {
		this.indivLiveAddr = indivLiveAddr;
	}

	/**
	 * 访问<户籍邮编>属性
	 */
	public String getIndivRegZip() {
		return this.indivRegZip;
	}

	/**
	 * 设置<户籍邮编>属性
	 */
	public void setIndivRegZip(String indivRegZip) {
		this.indivRegZip = indivRegZip;
	}

	/**
	 * 访问<房租金额>属性
	 */
	public String getIndivRentAmt() {
		return this.indivRentAmt;
	}

	/**
	 * 设置<房租金额>属性
	 */
	public void setIndivRentAmt(String indivRentAmt) {
		this.indivRentAmt = indivRentAmt;
	}

	/**
	 * 访问<任职部门>属性
	 */
	public String getIndivBranch() {
		return this.indivBranch;
	}

	/**
	 * 设置<任职部门>属性
	 */
	public void setIndivBranch(String indivBranch) {
		this.indivBranch = indivBranch;
	}

	/**
	 * 访问<单位性质>属性
	 */
	public String getIndivEmpType() {
		return this.indivEmpType;
	}

	/**
	 * 设置<单位性质>属性
	 */
	public void setIndivEmpType(String indivEmpType) {
		this.indivEmpType = indivEmpType;
	}

	/**
	 * 访问<单位规模>属性
	 */
	public String getIndivEmpNum() {
		return this.indivEmpNum;
	}

	/**
	 * 设置<单位规模>属性
	 */
	public void setIndivEmpNum(String indivEmpNum) {
		this.indivEmpNum = indivEmpNum;
	}

	/**
	 * 访问<申请单行业>属性
	 */
	public String getIndivIndtryPaper() {
		return this.indivIndtryPaper;
	}

	/**
	 * 设置<申请单行业>属性
	 */
	public void setIndivIndtryPaper(String indivIndtryPaper) {
		this.indivIndtryPaper = indivIndtryPaper;
	}

	/**
	 * 访问<职务>属性
	 */
	public String getIndivPosition() {
		return this.indivPosition;
	}

	/**
	 * 设置<职务>属性
	 */
	public void setIndivPosition(String indivPosition) {
		this.indivPosition = indivPosition;
	}

	/**
	 * 访问<个人工资月收入(元)>属性
	 */
	public String getIndivMthInc() {
		return this.indivMthInc;
	}

	/**
	 * 设置<个人工资月收入(元)>属性
	 */
	public void setIndivMthInc(String indivMthInc) {
		this.indivMthInc = indivMthInc;
	}

	/**
	 * 访问<是否有银行信用卡>属性
	 */
	public String getIndivCardInd() {
		return this.indivCardInd;
	}

	/**
	 * 设置<是否有银行信用卡>属性
	 */
	public void setIndivCardInd(String indivCardInd) {
		this.indivCardInd = indivCardInd;
	}

	/**
	 * 访问<单张信用卡最高额度>属性
	 */
	public String getIndivCardAmt() {
		return this.indivCardAmt;
	}

	/**
	 * 设置<单张信用卡最高额度>属性
	 */
	public void setIndivCardAmt(String indivCardAmt) {
		this.indivCardAmt = indivCardAmt;
	}

	/**
	 * 访问<发卡银行名称>属性
	 */
	public String getIndivCardBank() {
		return this.indivCardBank;
	}

	/**
	 * 设置<发卡银行名称>属性
	 */
	public void setIndivCardBank(String indivCardBank) {
		this.indivCardBank = indivCardBank;
	}

	/**
	 * 访问<户籍地址>属性
	 */
	public String getIndivRegAddr() {
		return this.indivRegAddr;
	}

	/**
	 * 设置<户籍地址>属性
	 */
	public void setIndivRegAddr(String indivRegAddr) {
		this.indivRegAddr = indivRegAddr;
	}

	/**
	 * 访问<手机号>属性
	 */
	public String getIndivMobile() {
		return this.indivMobile;
	}

	/**
	 * 设置<手机号>属性
	 */
	public void setIndivMobile(String indivMobile) {
		this.indivMobile = indivMobile;
	}

	/**
	 * 访问<电子邮箱>属性
	 */
	public String getIndivEmail() {
		return this.indivEmail;
	}

	/**
	 * 设置<电子邮箱>属性
	 */
	public void setIndivEmail(String indivEmail) {
		this.indivEmail = indivEmail;
	}

	/**
	 * 访问<现单位地址>属性
	 */
	public String getIndivEmp() {
		return this.indivEmp;
	}

	/**
	 * 设置<现单位地址>属性
	 */
	public void setIndivEmp(String indivEmp) {
		this.indivEmp = indivEmp;
	}

	/**
	 * 访问<职业>属性
	 */
	public String getIndivOccup() {
		return this.indivOccup;
	}

	/**
	 * 设置<职业>属性
	 */
	public void setIndivOccup(String indivOccup) {
		this.indivOccup = indivOccup;
	}

	/**
	 * 访问<文化程度>属性
	 */
	public String getIndivDegree() {
		return this.indivDegree;
	}

	/**
	 * 设置<文化程度>属性
	 */
	public void setIndivDegree(String indivDegree) {
		this.indivDegree = indivDegree;
	}

	/**
	 * 访问<客户号>属性
	 */
	public String getCustId() {
		return this.custId;
	}

	/**
	 * 设置<客户号>属性
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}

	/**
	 * 访问<贷款相关人流水号>属性
	 */
	public String getApptSeq() {
		return this.apptSeq;
	}

	/**
	 * 设置<贷款相关人流水号>属性
	 */
	public void setApptSeq(String apptSeq) {
		this.apptSeq = apptSeq;
	}

	public List<ApptContactInfo> getApptContacts() {
		return apptContacts;
	}

	public void setApptContacts(List<ApptContactInfo> apptContacts) {
		this.apptContacts = apptContacts;
	}

}
