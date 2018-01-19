package com.yucheng.byxf.mini.message;

import java.io.Serializable;

public class BillNewResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String custName;
	private String idNo;
	private String origPrcp;
	private String loanActvDt;
	private String tnr;
	private String loanCyy;
	private String loanContNo;
	private String loanNo;
	private String cardNo;
	private String idType;
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
	public String getOrigPrcp() {
		return origPrcp;
	}
	public void setOrigPrcp(String origPrcp) {
		this.origPrcp = origPrcp;
	}
	public String getLoanActvDt() {
		return loanActvDt;
	}
	public void setLoanActvDt(String loanActvDt) {
		this.loanActvDt = loanActvDt;
	}
	public String getTnr() {
		return tnr;
	}
	public void setTnr(String tnr) {
		this.tnr = tnr;
	}
	public String getLoanCyy() {
		return loanCyy;
	}
	public void setLoanCyy(String loanCyy) {
		this.loanCyy = loanCyy;
	}
	public String getLoanContNo() {
		return loanContNo;
	}
	public void setLoanContNo(String loanContNo) {
		this.loanContNo = loanContNo;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	

}
