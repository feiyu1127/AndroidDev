package com.yucheng.byxf.mini.message;

public class LoginMessage {
//	username_account;
	private String cipAlias;
//	identify_card身份证号
	private String idNo;
//	nickname_china_name
	private String cipNamecn;
//	Sex
	private String cipSex;
//	phone
	private String cipMobile;
	
	private String txCde;
	private String cipCstno;
	private String cipCtfno;
	private String cipCtftype;
	private String cipEmail;
	private String cipStatus;
	private String cipLevel;
	private String cipRegdate;
	private String cipStt;
	//白名单
	private String pcCode;
	private String isWhiteCust;
	//个人详细信息 字段
	private String liveAddr; //现居住地址 
	private String curentResidentialAddr; //详细地址 
	private String regAddr ;//户籍地址 
	private String degree ;//文化程度 
	private String empName ;//现单位名称 
	private String empZone ;//单位区号 
	private String empTelNo; //单位电话号码 
	private String empTelSub ;//单位分机号 
	private String empAddr;// 单位地址 
	private String mthInc ;//个人工资收入 
	private String marital ;//婚姻状况 
	private boolean existFavourableActv;//活动状态
	
	public boolean isExistFavourableActv() {
		return existFavourableActv;
	}
	public void setExistFavourableActv(boolean existFavourableActv) {
		this.existFavourableActv = existFavourableActv;
	}
	
	
	public String getMarital() {
		return marital;
	}
	public void setMarital(String marital) {
		this.marital = marital;
	}
	public String getLiveAddr() {
		return liveAddr;
	}
	public void setLiveAddr(String liveAddr) {
		this.liveAddr = liveAddr;
	}
	public String getCurentResidentialAddr() {
		return curentResidentialAddr;
	}
	public void setCurentResidentialAddr(String curentResidentialAddr) {
		this.curentResidentialAddr = curentResidentialAddr;
	}
	public String getRegAddr() {
		return regAddr;
	}
	public void setRegAddr(String regAddr) {
		this.regAddr = regAddr;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpZone() {
		return empZone;
	}
	public void setEmpZone(String empZone) {
		this.empZone = empZone;
	}
	public String getEmpTelNo() {
		return empTelNo;
	}
	public void setEmpTelNo(String empTelNo) {
		this.empTelNo = empTelNo;
	}
	public String getEmpTelSub() {
		return empTelSub;
	}
	public void setEmpTelSub(String empTelSub) {
		this.empTelSub = empTelSub;
	}
	public String getEmpAddr() {
		return empAddr;
	}
	public void setEmpAddr(String empAddr) {
		this.empAddr = empAddr;
	}
	public String getMthInc() {
		return mthInc;
	}
	public void setMthInc(String mthInc) {
		this.mthInc = mthInc;
	}

	
	public String getPcCode() {
		return pcCode;
	}
	public void setPcCode(String pcCode) {
		this.pcCode = pcCode;
	}
	public String getIsWhiteCust() {
		return isWhiteCust;
	}
	public void setIsWhiteCust(String isWhiteCust) {
		this.isWhiteCust = isWhiteCust;
	}
	public String getTxCde() {
		return txCde;
	}
	public void setTxCde(String txCde) {
		this.txCde = txCde;
	}
	public String getCipCstno() {
		return cipCstno;
	}
	public void setCipCstno(String cipCstno) {
		this.cipCstno = cipCstno;
	}
	public String getCipAlias() {
		return cipAlias;
	}
	public void setCipAlias(String cipAlias) {
		this.cipAlias = cipAlias;
	}
	public String getCipCtfno() {
		return cipCtfno;
	}
	public void setCipCtfno(String cipCtfno) {
		this.cipCtfno = cipCtfno;
	}
	public String getCipMobile() {
		return cipMobile;
	}
	public void setCipMobile(String cipMobile) {
		this.cipMobile = cipMobile;
	}
	public String getCipSex() {
		return cipSex;
	}
	public void setCipSex(String cipSex) {
		this.cipSex = cipSex;
	}
	public String getCipNamecn() {
		return cipNamecn;
	}
	public void setCipNamecn(String cipNamecn) {
		this.cipNamecn = cipNamecn;
	}
	public String getCipCtftype() {
		return cipCtftype;
	}
	public void setCipCtftype(String cipCtftype) {
		this.cipCtftype = cipCtftype;
	}
	public String getCipEmail() {
		return cipEmail;
	}
	public void setCipEmail(String cipEmail) {
		this.cipEmail = cipEmail;
	}
	public String getCipStatus() {
		return cipStatus;
	}
	public void setCipStatus(String cipStatus) {
		this.cipStatus = cipStatus;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getCipLevel() {
		return cipLevel;
	}
	public void setCipLevel(String cipLevel) {
		this.cipLevel = cipLevel;
	}
	public String getCipRegdate() {
		return cipRegdate;
	}
	public void setCipRegdate(String cipRegdate) {
		this.cipRegdate = cipRegdate;
	}
	public String getCipStt() {
		return cipStt;
	}
	public void setCipStt(String cipStt) {
		this.cipStt = cipStt;
	}
	@Override
	public String toString() {
		return "LoginMessage [cipAlias=" + cipAlias + ", idNo=" + idNo
				+ ", cipNamecn=" + cipNamecn + ", cipSex=" + cipSex
				+ ", cipMobile=" + cipMobile + ", txCde=" + txCde
				+ ", cipCstno=" + cipCstno + ", cipCtfno=" + cipCtfno
				+ ", cipCtftype=" + cipCtftype + ", cipEmail=" + cipEmail
				+ ", cipStatus=" + cipStatus + ", cipLevel=" + cipLevel
				+ ", cipRegdate=" + cipRegdate + ", cipStt=" + cipStt
				+ ", pcCode=" + pcCode + ", isWhiteCust=" + isWhiteCust
				+ ", liveAddr=" + liveAddr + ", curentResidentialAddr="
				+ curentResidentialAddr + ", regAddr=" + regAddr + ", degree="
				+ degree + ", empName=" + empName + ", empZone=" + empZone
				+ ", empTelNo=" + empTelNo + ", empTelSub=" + empTelSub
				+ ", empAddr=" + empAddr + ", mthInc=" + mthInc + ", marital="
				+ marital + "]";
	}
	
	
}
