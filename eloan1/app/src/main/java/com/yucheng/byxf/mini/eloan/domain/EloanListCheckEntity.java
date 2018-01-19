package com.yucheng.byxf.mini.eloan.domain;

import java.io.Serializable;

public class EloanListCheckEntity implements Serializable {
	// 参数名称 说明
	private String code; // 返回码
	private String msg;// 返回信息
	private String fkDate; // 确认日期
	private String contNo; // 贷款合同号
	private String applCode; // 流水号
	private String custName; // 借款人姓名
	private String idType;// 证件类型
	private String idNo; // 证件号码
	private String applyAmt; // 贷款金额
	private String tnr; // 贷款期限
	private String minthRat; // 贷款月利率
	private String loanMtd;// 还款方式
	private String psFeeAmt; // 账户管理费
	private String purpose;// 贷款用途
	private String otherPurpose; // 其他用途
	private String applDisbAcNam; // 放款/还款帐户户名
	private String applDisbAcNo;// 放款/还款帐户账号
	private String psSignInd; // 签订标志
	private String msgTime; // 短信验证码时间
	private String instalAmt; // 期供金额
	private String contStatus;// 合同状态
	public String getContStatus() {
		return contStatus;
	}
	public void setContStatus(String contStatus) {
		this.contStatus = contStatus;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getFkDate() {
		return fkDate;
	}
	public void setFkDate(String fkDate) {
		this.fkDate = fkDate;
	}
	public String getContNo() {
		return contNo;
	}
	public void setContNo(String contNo) {
		this.contNo = contNo;
	}
	public String getApplCode() {
		return applCode;
	}
	public void setApplCode(String applCode) {
		this.applCode = applCode;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getApplyAmt() {
		return applyAmt;
	}
	public void setApplyAmt(String applyAmt) {
		this.applyAmt = applyAmt;
	}
	public String getTnr() {
		return tnr;
	}
	public void setTnr(String tnr) {
		this.tnr = tnr;
	}
	public String getMinthRat() {
		return minthRat;
	}
	public void setMinthRat(String minthRat) {
		this.minthRat = minthRat;
	}
	public String getLoanMtd() {
		return loanMtd;
	}
	public void setLoanMtd(String loanMtd) {
		this.loanMtd = loanMtd;
	}
	public String getPsFeeAmt() {
		return psFeeAmt;
	}
	public void setPsFeeAmt(String psFeeAmt) {
		this.psFeeAmt = psFeeAmt;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getOtherPurpose() {
		return otherPurpose;
	}
	public void setOtherPurpose(String otherPurpose) {
		this.otherPurpose = otherPurpose;
	}
	public String getApplDisbAcNam() {
		return applDisbAcNam;
	}
	public void setApplDisbAcNam(String applDisbAcNam) {
		this.applDisbAcNam = applDisbAcNam;
	}
	public String getApplDisbAcNo() {
		return applDisbAcNo;
	}
	public void setApplDisbAcNo(String applDisbAcNo) {
		this.applDisbAcNo = applDisbAcNo;
	}
	public String getPsSignInd() {
		return psSignInd;
	}
	public void setPsSignInd(String psSignInd) {
		this.psSignInd = psSignInd;
	}
	public String getMsgTime() {
		return msgTime;
	}
	public void setMsgTime(String msgTime) {
		this.msgTime = msgTime;
	}
	public String getInstalAmt() {
		return instalAmt;
	}
	public void setInstalAmt(String instalAmt) {
		this.instalAmt = instalAmt;
	}
}