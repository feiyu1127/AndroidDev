/******************************************************************************
 * 
 * 北银消费金融公司-移动业务终端三期-轻松e贷
 *
 * Copyright © 2014 YuchengTech Technologies Limited All Rights Reserved.
 * 
 * Created on 2014-2-27 18:06:37
 *
 *******************************************************************************/
package com.yucheng.byxf.mini.message;
import java.util.List;

/**
 * 获取申请编号实体类
 *
 * @version 1.0
 *
 * @author neo
 */
public class MiniConfirmMessage  implements java.io.Serializable {

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
 	  *  申请编号
      */
 	private String applCde;
	/**
 	  *  交易流水号
      */
 	private String txLogSeq;
	/**
 	  *  申请流水号
      */
 	private String applSeq;
	/**
 	  *  放款信息流水号
      */
 	private String applDisbSeq;
	/**
 	  *  还款信息流水号
      */
 	private String applRpymSeq;
	/**
 	  * 与贷款相关流水号
      */
 	private String apptSeq;
 	
 	
	public MiniConfirmMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MiniConfirmMessage(String retCode, String retInfo, String applCde,
			String txLogSeq, String applSeq, String applDisbSeq,
			String applRpymSeq, String apptSeq) {
		super();
		this.retCode = retCode;
		this.retInfo = retInfo;
		this.applCde = applCde;
		this.txLogSeq = txLogSeq;
		this.applSeq = applSeq;
		this.applDisbSeq = applDisbSeq;
		this.applRpymSeq = applRpymSeq;
		this.apptSeq = apptSeq;
	}
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getRetInfo() {
		return retInfo;
	}
	public void setRetInfo(String retInfo) {
		this.retInfo = retInfo;
	}
	public String getApplCde() {
		return applCde;
	}
	public void setApplCde(String applCde) {
		this.applCde = applCde;
	}
	public String getTxLogSeq() {
		return txLogSeq;
	}
	public void setTxLogSeq(String txLogSeq) {
		this.txLogSeq = txLogSeq;
	}
	public String getApplSeq() {
		return applSeq;
	}
	public void setApplSeq(String applSeq) {
		this.applSeq = applSeq;
	}
	public String getApplDisbSeq() {
		return applDisbSeq;
	}
	public void setApplDisbSeq(String applDisbSeq) {
		this.applDisbSeq = applDisbSeq;
	}
	public String getApplRpymSeq() {
		return applRpymSeq;
	}
	public void setApplRpymSeq(String applRpymSeq) {
		this.applRpymSeq = applRpymSeq;
	}
	public String getApptSeq() {
		return apptSeq;
	}
	public void setApptSeq(String apptSeq) {
		this.apptSeq = apptSeq;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
