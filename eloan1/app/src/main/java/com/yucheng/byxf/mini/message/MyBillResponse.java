/******************************************************************************
 * 
 * 北银消费金融公司-移动业务终端三期-轻松e贷
 *
 * Copyright © 2014 YuchengTech Technologies Limited All Rights Reserved.
 * 
 * Created on 2014-2-22 18:06:37
 *
 *******************************************************************************/
package com.yucheng.byxf.mini.message;
import java.util.List;

/**
 * 账单查询实体类
 *
 * @version 1.0
 *
 * @author neo
 */
public class MyBillResponse  implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
 	  *  返回码
      */
 	private String retCode;
	/**
 	  *  返回信息
      */
 	private String retInfo;
	/**
 	  *  账期
      */
 	private String billDt;
	/**
 	  *  账单状态
      */
 	private String billInd;
	/**
 	  *  到期还款日
      */
 	private String psDueDt;
	/**
 	  *  币种
      */
 	private String txCcyCde;
	/**
 	  *  本期账单金额
      */
 	private String curAllPsAmt;
	/**
 	  *  本期最低还款额
      */
 	private String curMinPay;
	/**
 	  *  上期账单金额
      */
 	private String lastBillAmt;
	/**
 	  *  本期已还金额
      */
 	private String curPayedAmt;
	/**
 	  *  本期新增金额
      */
 	private String curAppendAmt;
	/**
 	  *  本期调整金额
      */
 	private String curAdjAmt;
	/**
 	  *  循环利息
      */
 	private String cycleInt;
	/**
 	  *  本期利息金额
      */
 	private String curNormInt;
	/**
 	  *  本期取款总金额
      */
 	private String curCashAmt;
	/**
 	  *  本期手续费总金额
      */
 	private String curFeeAmt;
	/**
 	  *  本期逾期总额
      */
 	private String curAllOdAmt;
	/**
 	  *  消费笔数
      */
 	private String cashNum;
 	
	/**
	  *  资产负债信息
   */
	private List<CardBillInfo> cardBillList;

    public MyBillResponse() {
    }
	
    public MyBillResponse(String retCode) {
        this.retCode = retCode;
    }
    public MyBillResponse(String retCode, String retInfo, String billDt, String billInd, String psDueDt, String txCcyCde, String curAllPsAmt, String curMinPay, String lastBillAmt, String curPayedAmt, String curAppendAmt, String curAdjAmt, String cycleInt, String curNormInt, String curCashAmt, String curFeeAmt, String curAllOdAmt, String cashNum) {
       this.retCode = retCode;
       this.retInfo = retInfo;
       this.billDt = billDt;
       this.billInd = billInd;
       this.psDueDt = psDueDt;
       this.txCcyCde = txCcyCde;
       this.curAllPsAmt = curAllPsAmt;
       this.curMinPay = curMinPay;
       this.lastBillAmt = lastBillAmt;
       this.curPayedAmt = curPayedAmt;
       this.curAppendAmt = curAppendAmt;
       this.curAdjAmt = curAdjAmt;
       this.cycleInt = cycleInt;
       this.curNormInt = curNormInt;
       this.curCashAmt = curCashAmt;
       this.curFeeAmt = curFeeAmt;
       this.curAllOdAmt = curAllOdAmt;
       this.cashNum = cashNum;
    }
   
   /**     
     *访问<返回码>属性
     */
    public String getRetCode() {
        return this.retCode;
    }	    
    /**     
     *设置<返回码>属性
     */
    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }
   /**     
     *访问<返回信息>属性
     */
    public String getRetInfo() {
        return this.retInfo;
    }	    
    /**     
     *设置<返回信息>属性
     */
    public void setRetInfo(String retInfo) {
        this.retInfo = retInfo;
    }
   /**     
     *访问<账期>属性
     */
    public String getBillDt() {
        return this.billDt;
    }	    
    /**     
     *设置<账期>属性
     */
    public void setBillDt(String billDt) {
        this.billDt = billDt;
    }
   /**     
     *访问<账单状态>属性
     */
    public String getBillInd() {
        return this.billInd;
    }	    
    /**     
     *设置<账单状态>属性
     */
    public void setBillInd(String billInd) {
        this.billInd = billInd;
    }
   /**     
     *访问<到期还款日>属性
     */
    public String getPsDueDt() {
        return this.psDueDt;
    }	    
    /**     
     *设置<到期还款日>属性
     */
    public void setPsDueDt(String psDueDt) {
        this.psDueDt = psDueDt;
    }
   /**     
     *访问<币种>属性
     */
    public String getTxCcyCde() {
        return this.txCcyCde;
    }	    
    /**     
     *设置<币种>属性
     */
    public void setTxCcyCde(String txCcyCde) {
        this.txCcyCde = txCcyCde;
    }
   /**     
     *访问<本期账单金额>属性
     */
    public String getCurAllPsAmt() {
        return this.curAllPsAmt;
    }	    
    /**     
     *设置<本期账单金额>属性
     */
    public void setCurAllPsAmt(String curAllPsAmt) {
        this.curAllPsAmt = curAllPsAmt;
    }
   /**     
     *访问<本期最低还款额>属性
     */
    public String getCurMinPay() {
        return this.curMinPay;
    }	    
    /**     
     *设置<本期最低还款额>属性
     */
    public void setCurMinPay(String curMinPay) {
        this.curMinPay = curMinPay;
    }
   /**     
     *访问<上期账单金额>属性
     */
    public String getLastBillAmt() {
        return this.lastBillAmt;
    }	    
    /**     
     *设置<上期账单金额>属性
     */
    public void setLastBillAmt(String lastBillAmt) {
        this.lastBillAmt = lastBillAmt;
    }
   /**     
     *访问<本期已还金额>属性
     */
    public String getCurPayedAmt() {
        return this.curPayedAmt;
    }	    
    /**     
     *设置<本期已还金额>属性
     */
    public void setCurPayedAmt(String curPayedAmt) {
        this.curPayedAmt = curPayedAmt;
    }
   /**     
     *访问<本期新增金额>属性
     */
    public String getCurAppendAmt() {
        return this.curAppendAmt;
    }	    
    /**     
     *设置<本期新增金额>属性
     */
    public void setCurAppendAmt(String curAppendAmt) {
        this.curAppendAmt = curAppendAmt;
    }
   /**     
     *访问<本期调整金额>属性
     */
    public String getCurAdjAmt() {
        return this.curAdjAmt;
    }	    
    /**     
     *设置<本期调整金额>属性
     */
    public void setCurAdjAmt(String curAdjAmt) {
        this.curAdjAmt = curAdjAmt;
    }
   /**     
     *访问<循环利息>属性
     */
    public String getCycleInt() {
        return this.cycleInt;
    }	    
    /**     
     *设置<循环利息>属性
     */
    public void setCycleInt(String cycleInt) {
        this.cycleInt = cycleInt;
    }
   /**     
     *访问<本期利息金额>属性
     */
    public String getCurNormInt() {
        return this.curNormInt;
    }	    
    /**     
     *设置<本期利息金额>属性
     */
    public void setCurNormInt(String curNormInt) {
        this.curNormInt = curNormInt;
    }
   /**     
     *访问<本期取款总金额>属性
     */
    public String getCurCashAmt() {
        return this.curCashAmt;
    }	    
    /**     
     *设置<本期取款总金额>属性
     */
    public void setCurCashAmt(String curCashAmt) {
        this.curCashAmt = curCashAmt;
    }
   /**     
     *访问<本期手续费总金额>属性
     */
    public String getCurFeeAmt() {
        return this.curFeeAmt;
    }	    
    /**     
     *设置<本期手续费总金额>属性
     */
    public void setCurFeeAmt(String curFeeAmt) {
        this.curFeeAmt = curFeeAmt;
    }
   /**     
     *访问<本期逾期总额>属性
     */
    public String getCurAllOdAmt() {
        return this.curAllOdAmt;
    }	    
    /**     
     *设置<本期逾期总额>属性
     */
    public void setCurAllOdAmt(String curAllOdAmt) {
        this.curAllOdAmt = curAllOdAmt;
    }
   /**     
     *访问<消费笔数>属性
     */
    public String getCashNum() {
        return this.cashNum;
    }	    
    /**     
     *设置<消费笔数>属性
     */
    public void setCashNum(String cashNum) {
        this.cashNum = cashNum;
    }

	public List<CardBillInfo> getCardBillList() {
		return cardBillList;
	}
	
	public void setCardBillList(List<CardBillInfo> cardBillList) {
		this.cardBillList = cardBillList;
	}
	


}
