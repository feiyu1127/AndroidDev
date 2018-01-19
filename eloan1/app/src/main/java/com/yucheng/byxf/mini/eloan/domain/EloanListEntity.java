package com.yucheng.byxf.mini.eloan.domain;

import java.io.Serializable;

public class EloanListEntity implements Serializable {

	private String custName;
	private String idNo;
//	申请编号
	private String applCde;
//	审批金额
	private String apprvAmt;
	
	private String state;
//	申请时间
	private String applyDt;
//	申请期限
	private String applyTnr;
//	申请金额
	private String applyAtm;
//	返回值为null；
	private String contStatus;
//	放款状态
	private String actvStar;
//	放款时间
	private String actvDt;
//	贷款确认书核准状态
	private String confirmStatus;
	public String getConfirmStatus() {
		return confirmStatus;
	}
	public void setConfirmStatus(String confirmStatus) {
		this.confirmStatus = confirmStatus;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getApplCde() {
		return applCde;
	}
	public void setApplCde(String applCde) {
		this.applCde = applCde;
	}
	public String getApprvAmt() {
		return apprvAmt;
	}
	public void setApprvAmt(String apprvAmt) {
		this.apprvAmt = apprvAmt;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getApplyDt() {
		return applyDt;
	}
	public void setApplyDt(String applyDt) {
		this.applyDt = applyDt;
	}
	public String getApplyTnr() {
		return applyTnr;
	}
	public void setApplyTnr(String applyTnr) {
		this.applyTnr = applyTnr;
	}
	public String getApplyAtm() {
		return applyAtm;
	}
	public void setApplyAtm(String applyAtm) {
		this.applyAtm = applyAtm;
	}
	public String getContStatus() {
		return contStatus;
	}
	public void setContStatus(String contStatus) {
		this.contStatus = contStatus;
	}
	public String getActvStar() {
		return actvStar;
	}
	public void setActvStar(String actvStar) {
		this.actvStar = actvStar;
	}
	public String getActvDt() {
		return actvDt;
	}
	public void setActvDt(String actvDt) {
		this.actvDt = actvDt;
	}
}
