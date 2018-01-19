package com.yucheng.byxf.mini.message;

import java.io.Serializable;

public class MiniCardActivateList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 申请编号
	 */
	private String applSeq;
	/**
	 * 录入日期
	 */
	private String crtDt;
	/**
	 * zf卡标识
	 */
	private String baiscCardInd;
	/**
	 * 审批状态
	 */
	private String applSts;
	/**
	 * 产品描述
	 */
	private String cardTypDesc;
	/**
	 * 审核日期
	 */
	private String applDt;
	/**
	 * 制卡日期
	 */
	private String cardSts;
	public String getApplSeq() {
		return applSeq;
	}
	public void setApplSeq(String applSeq) {
		this.applSeq = applSeq;
	}
	public String getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(String crtDt) {
		this.crtDt = crtDt;
	}
	public String getBaiscCardInd() {
		return baiscCardInd;
	}
	public void setBaiscCardInd(String baiscCardInd) {
		this.baiscCardInd = baiscCardInd;
	}
	public String getApplSts() {
		return applSts;
	}
	public void setApplSts(String applSts) {
		this.applSts = applSts;
	}
	public String getCardTypDesc() {
		return cardTypDesc;
	}
	public void setCardTypDesc(String cardTypDesc) {
		this.cardTypDesc = cardTypDesc;
	}
	public String getApplDt() {
		return applDt;
	}
	public void setApplDt(String applyDt) {
		this.applDt = applyDt;
	}
	public String getCardSts() {
		return cardSts;
	}
	public void setCardSts(String cardSts) {
		this.cardSts = cardSts;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}