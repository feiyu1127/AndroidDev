package com.yucheng.byxf.mini.message;

import java.io.Serializable;

public class RelaxBillResponse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String talCount;
	String custName;
	String idNo;
	String loanNo;
	String loanContNo;
	String origPrcp;
	String loanActvDt;
	String tnr;
	String loanCyy;
	public String getTalCount() {
		return talCount;
	}
	public void setTalCount(String talCount) {
		this.talCount = talCount;
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
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getLoanContNo() {
		return loanContNo;
	}
	public void setLoanContNo(String loanContNo) {
		this.loanContNo = loanContNo;
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
	
}
