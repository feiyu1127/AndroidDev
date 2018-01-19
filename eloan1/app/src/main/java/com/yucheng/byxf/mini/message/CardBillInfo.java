/******************************************************************************
 * 
 * 北银消费金融公司-移动业务终端三期-轻松e贷
 *
 * Copyright © 2014 YuchengTech Technologies Limited All Rights Reserved.
 * 
 * Created on 2014-2-22 18:06:07
 *
 *******************************************************************************/
package com.yucheng.byxf.mini.message;


/**
 * 卡账单明细实体类
 *
 * @version 1.0
 *
 * @author neo
 */
public class CardBillInfo  implements java.io.Serializable {

	/**
 	  *  明细序号
      */
 	private String seqNo;
	/**
 	  *  账期
      */
 	private String billDt;
	/**
 	  *  交易类型
      */
 	private String txTyp;
	/**
 	  *  交易日期
      */
 	private String hostTxDt;
	/**
 	  *  记账日期
      */
 	private String txValDt;
	/**
 	  *  卡号末四位
      */
 	private String lastNo;
	/**
 	  *  交易金额
      */
 	private String txAmt;
	/**
 	  *  交易摘要
      */
 	private String reMark;

    public CardBillInfo() {
    }
	
    public CardBillInfo(String seqNo) {
        this.seqNo = seqNo;
    }
    public CardBillInfo(String seqNo, String billDt, String txTyp, String hostTxDt, String txValDt, String lastNo, String txAmt, String reMark) {
       this.seqNo = seqNo;
       this.billDt = billDt;
       this.txTyp = txTyp;
       this.hostTxDt = hostTxDt;
       this.txValDt = txValDt;
       this.lastNo = lastNo;
       this.txAmt = txAmt;
       this.reMark = reMark;
    }
   
   /**     
     *访问<明细序号>属性
     */
    public String getSeqNo() {
        return this.seqNo;
    }	    
    /**     
     *设置<明细序号>属性
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
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
     *访问<交易类型>属性
     */
    public String getTxTyp() {
        return this.txTyp;
    }	    
    /**     
     *设置<交易类型>属性
     */
    public void setTxTyp(String txTyp) {
        this.txTyp = txTyp;
    }
   /**     
     *访问<交易日期>属性
     */
    public String getHostTxDt() {
        return this.hostTxDt;
    }	    
    /**     
     *设置<交易日期>属性
     */
    public void setHostTxDt(String hostTxDt) {
        this.hostTxDt = hostTxDt;
    }
   /**     
     *访问<记账日期>属性
     */
    public String getTxValDt() {
        return this.txValDt;
    }	    
    /**     
     *设置<记账日期>属性
     */
    public void setTxValDt(String txValDt) {
        this.txValDt = txValDt;
    }
   /**     
     *访问<卡号末四位>属性
     */
    public String getLastNo() {
        return this.lastNo;
    }	    
    /**     
     *设置<卡号末四位>属性
     */
    public void setLastNo(String lastNo) {
        this.lastNo = lastNo;
    }
   /**     
     *访问<交易金额>属性
     */
    public String getTxAmt() {
        return this.txAmt;
    }	    
    /**     
     *设置<交易金额>属性
     */
    public void setTxAmt(String txAmt) {
        this.txAmt = txAmt;
    }
   /**     
     *访问<交易摘要>属性
     */
    public String getReMark() {
        return this.reMark;
    }	    
    /**     
     *设置<交易摘要>属性
     */
    public void setReMark(String reMark) {
        this.reMark = reMark;
    }


}
