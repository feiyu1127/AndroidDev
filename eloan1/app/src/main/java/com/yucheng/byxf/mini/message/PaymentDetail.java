package com.yucheng.byxf.mini.message;


/******************************************************************************
 * 
 * 北银消费金融公司移动业务终端
 *
 * Copyright © 2012 Yucheng Technologies Limited All Rights Reserved.
 * 
 * Created on 2012-7-3 17:08:58
 *
 *******************************************************************************/





/**

 * 借据列表信息

 *

 * @version 1.0

 *

 * @author mark

 */

public class PaymentDetail  implements java.io.Serializable {

	
	private static final long serialVersionUID = -8582564561928443129L;
	//放款日期
 	private String psDueDt;
	//期供金额
	private String psInstmAmt;
	//本金
	private String psPrcpAmt;
	//正常利息
	private String psNormIntAmt;
	//剩余本金

 	private String psRemPrcp;

 	//账户管理费
 	private String psFeeAmt;
	//期次

 	private String psPerdNo;
 	//已还本金
 	private String setlPrcp;
 	//已还正常利息
 	private String seltNormInt;
 	//应还逾期利息
 	private String psODIntAmt;
 	//已还逾期利息
 	private String seltOdIntAmt;
 	//应还滞纳金
 	private String lateFee;
 	//已还滞纳金
 	private String seltLateFee;
 	//已还帐户管理费
 	private String seltFeeAmt;
 	//是否已还款
 	private String seltInd;
	 

    public PaymentDetail() {

    }
    /**     
     *访问<放款日期>属性
     */
	public String getPsDueDt() {
		return psDueDt;
	}
	 /**     
     *设置<放款日期>属性
     */
	public void setPsDueDt(String psDueDt) {
		this.psDueDt = psDueDt;
	}
	/**     
     *访问<期供金额>属性
     */
	public String getPsInstmAmt() {
		return psInstmAmt;
	}
	/**     
     *设置<期供金额>属性
     */
	public void setPsInstmAmt(String psInstmAmt) {
		this.psInstmAmt = psInstmAmt;
	}
	/**     
     *访问<本金>属性
     */
	public String getPsPrcpAmt() {
		return psPrcpAmt;
	}
	/**     
     *设置<本金>属性
     */
	public void setPsPrcpAmt(String psPrcpAmt) {
		this.psPrcpAmt = psPrcpAmt;
	}
	/**     
     *访问<正常利息>属性
     */
	public String getPsNormIntAmt() {
		return psNormIntAmt;
	}
	/**     
     *设置<正常利息>属性
     */
	public void setPsNormIntAmt(String psNormIntAmt) {
		this.psNormIntAmt = psNormIntAmt;
	}
	/**     
     *访问<剩余本金>属性
     */
	public String getPsRemPrcp() {
		return psRemPrcp;
	}
	/**     
     *设置<剩余本金>属性
     */
	public void setPsRemPrcp(String psRemPrcp) {
		this.psRemPrcp = psRemPrcp;
	}
	/**     
     *访问<账户管理费>属性
     */
	public String getPsFeeAmt() {
		return psFeeAmt;
	}
	/**     
     *设置<账户管理费>属性
     */
	public void setPsFeeAmt(String psFeeAmt) {
		this.psFeeAmt = psFeeAmt;
	}
	/**     
     *访问<期次>属性
     */
	public String getPsPerdNo() {
		return psPerdNo;
	}
	/**     
     *设置<期次>属性
     */
	public void setPsPerdNo(String psPerdNo) {
		this.psPerdNo = psPerdNo;
	}
	
	/****************************************/
	public String getSetlPrcp() {
		return setlPrcp;
	}
	public void setSetlPrcp(String setlPrcp) {
		this.setlPrcp = setlPrcp;
	}
	public String getSeltNormInt() {
		return seltNormInt;
	}
	public void setSeltNormInt(String seltNormInt) {
		this.seltNormInt = seltNormInt;
	}
	public String getPsODIntAmt() {
		return psODIntAmt;
	}
	public void setPsODIntAmt(String psODIntAmt) {
		this.psODIntAmt = psODIntAmt;
	}
	public String getSeltOdIntAmt() {
		return seltOdIntAmt;
	}
	public void setSeltOdIntAmt(String seltOdIntAmt) {
		this.seltOdIntAmt = seltOdIntAmt;
	}
	public String getLateFee() {
		return lateFee;
	}
	public void setLateFee(String lateFee) {
		this.lateFee = lateFee;
	}
	public String getSeltLateFee() {
		return seltLateFee;
	}
	public void setSeltLateFee(String seltLateFee) {
		this.seltLateFee = seltLateFee;
	}
	public String getSeltFeeAmt() {
		return seltFeeAmt;
	}
	public void setSeltFeeAmt(String seltFeeAmt) {
		this.seltFeeAmt = seltFeeAmt;
	}
	public String getSeltInd() {
		return seltInd;
	}
	public void setSeltInd(String seltInd) {
		this.seltInd = seltInd;
	}
	/****************************************/
}