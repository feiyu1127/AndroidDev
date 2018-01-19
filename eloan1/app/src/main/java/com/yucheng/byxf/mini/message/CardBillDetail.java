package com.yucheng.byxf.mini.message;
import java.util.List;

/**
 * Mini已出账单明细
 *
 * @version 1.0
 *
 * @author neo
 */
public class CardBillDetail extends Response implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
 	  *  返回码
      */
 	private String errorCode;
	/**
 	  *  返回信息
      */
 	private String errorMsg;
	/**
 	  *  账单日期
      */
 	private String psBillDt;
	/**
 	  *  到期还款日
      */
 	private String psDueDt;
	/**
	  *  消费笔数
     */
	private String cashNum;
	/**
	 *   逾期金额
	 */
	private String odAmt;
	/**
 	  *  本期应还金额
      */
 	private String curPsInstmAmt;
	/**
 	  *  本期账单金额
      */
 	private String curBillAmt;
	/**
 	  *  上期账单金额
      */
 	private String lastBillAmt;
	/**
 	  *  本期新增金额
      */
 	private String curAddAmt;
	/**
 	  *  本期循环利息
      */
 	private String allOdInt;
	/**
 	  *  本期利息金额
      */
 	private String curNormInt;
	/**
 	  *  最低还款额
      */
 	private String minAmt;
	/**
 	  *  已还款金额
      */
 	private String setlAmt;
	/**
 	  *  本期取款总金额
      */
 	private String curCashAmt;
	/**
 	  *  本期手续费总金额
      */
 	private String curFeeAmt;
 
 	
	public CardBillDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CardBillDetail(String errorCode, String errorMsg, String psBillDt,
			String psDueDt, String cashNum, String odAmt, String curPsInstmAmt,
			String curBillAmt, String lastBillAmt, String curAddAmt,
			String allOdInt, String curNormInt, String minAmt, String setlAmt,
			String curCashAmt, String curFeeAmt) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.psBillDt = psBillDt;
		this.psDueDt = psDueDt;
		this.cashNum = cashNum;
		this.odAmt = odAmt;
		this.curPsInstmAmt = curPsInstmAmt;
		this.curBillAmt = curBillAmt;
		this.lastBillAmt = lastBillAmt;
		this.curAddAmt = curAddAmt;
		this.allOdInt = allOdInt;
		this.curNormInt = curNormInt;
		this.minAmt = minAmt;
		this.setlAmt = setlAmt;
		this.curCashAmt = curCashAmt;
		this.curFeeAmt = curFeeAmt;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getPsBillDt() {
		return psBillDt;
	}
	public void setPsBillDt(String psBillDt) {
		this.psBillDt = psBillDt;
	}
	public String getPsDueDt() {
		return psDueDt;
	}
	public void setPsDueDt(String psDueDt) {
		this.psDueDt = psDueDt;
	}
	public String getCashNum() {
		return cashNum;
	}
	public void setCashNum(String cashNum) {
		this.cashNum = cashNum;
	}
	public String getOdAmt() {
		return odAmt;
	}
	public void setOdAmt(String odAmt) {
		this.odAmt = odAmt;
	}
	public String getCurPsInstmAmt() {
		return curPsInstmAmt;
	}
	public void setCurPsInstmAmt(String curPsInstmAmt) {
		this.curPsInstmAmt = curPsInstmAmt;
	}
	public String getCurBillAmt() {
		return curBillAmt;
	}
	public void setCurBillAmt(String curBillAmt) {
		this.curBillAmt = curBillAmt;
	}
	public String getLastBillAmt() {
		return lastBillAmt;
	}
	public void setLastBillAmt(String lastBillAmt) {
		this.lastBillAmt = lastBillAmt;
	}
	public String getCurAddAmt() {
		return curAddAmt;
	}
	public void setCurAddAmt(String curAddAmt) {
		this.curAddAmt = curAddAmt;
	}
	public String getAllOdInt() {
		return allOdInt;
	}
	public void setAllOdInt(String allOdInt) {
		this.allOdInt = allOdInt;
	}
	public String getCurNormInt() {
		return curNormInt;
	}
	public void setCurNormInt(String curNormInt) {
		this.curNormInt = curNormInt;
	}
	public String getMinAmt() {
		return minAmt;
	}
	public void setMinAmt(String minAmt) {
		this.minAmt = minAmt;
	}
	public String getSetlAmt() {
		return setlAmt;
	}
	public void setSetlAmt(String setlAmt) {
		this.setlAmt = setlAmt;
	}
	public String getCurCashAmt() {
		return curCashAmt;
	}
	public void setCurCashAmt(String curCashAmt) {
		this.curCashAmt = curCashAmt;
	}
	public String getCurFeeAmt() {
		return curFeeAmt;
	}
	public void setCurFeeAmt(String curFeeAmt) {
		this.curFeeAmt = curFeeAmt;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
