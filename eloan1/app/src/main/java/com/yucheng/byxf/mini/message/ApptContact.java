/******************************************************************************
 * 
 * 北银消费金融公司移动业务终端
 *
 * Copyright © 2012 Yucheng Technologies Limited All Rights Reserved.
 * 
 * Created on 2012-7-11 15:37:48
 *
 *******************************************************************************/
package com.yucheng.byxf.mini.message;

/**
 * 
 *    
 * 类名称：ApptConnect  
 * 类描述： 联系信息
 * 创建人：zhangmin  
 * 创建时间：2013-7-23 下午4:36:04  
 * 修改人：  
 * 修改时间：2013-7-23 下午4:36:04  
 * 修改备注：  
 * @version 1.0.0  
 *
 */
public class ApptContact  implements java.io.Serializable {

	private static final long serialVersionUID = 9033861242571966312L;
	//是否通讯地址
 	private String contInd="";
	//地址类型
 	private String contType="";
	//地址
 	private String contAddr="";
	// 邮编号
 	private String contZip="";
 	// 电话
  	private String contPhone="";
 	//电话区号
	private String contZone="";
	//分机号
	private String contTelSub="";
	//贷款相关人流水号
	private String apptSeq="";
	//申请流水号
	private String applSeq="";
	//联系信息流水号
	private String contSeq="";
	
    public ApptContact() {
    }
    public ApptContact(String contInd, String contType, String contAddr, String contZip,String contPhone,String contZone,String contTelSub,String apptSeq,String applSeq,String contSeq) {
       this.contInd = contInd;
       this.contType = contType;
       this.contAddr = contAddr;
       this.contZip = contZip;
       this.contPhone = contPhone;
       this.contZone = contZone;
       this.contTelSub = contTelSub;
       this.apptSeq = apptSeq;
       this.applSeq = applSeq;
       this.contSeq = contSeq;
    }
    /**     
     *访问<是否通信地址>属性
     */
	public String getContInd() {
		return contInd;
	}
	 /**     
     *设置<是否通信地址>属性
     */
	public void setContInd(String contInd) {
		this.contInd = contInd;
	}
	/**     
     *访问<地址类型>属性
     */
	public String getContType() {
		return contType;
	}
	/**     
     *设置<地址类型>属性
     */
	public void setContType(String contType) {
		this.contType = contType;
	}
	/**     
     *访问<地址>属性
     */
	public String getContAddr() {
		return contAddr;
	}
	/**     
     *设置<地址>属性
     */
	public void setContAddr(String contAddr) {
		this.contAddr = contAddr;
	}
	/**     
     *访问<邮编号>属性
     */
	public String getContZip() {
		return contZip;
	}
	/**     
     *设置<邮编号>属性
     */
	public void setContZip(String contZip) {
		this.contZip = contZip;
	}
	/**     
     *访问<电话>属性
     */
	public String getContPhone() {
		return contPhone;
	}
	/**     
     *设置<电话>属性
     */
	public void setContPhone(String contPhone) {
		this.contPhone = contPhone;
	}
	/**     
     *访问<电话区号>属性
     */
	public String getContZone() {
		return contZone;
	}
	/**     
     *设置<电话区号>属性
     */
	public void setContZone(String contZone) {
		this.contZone = contZone;
	}
	/**     
     *访问<分机号>属性
     */
	public String getContTelSub() {
		return contTelSub;
	}
	/**     
     *设置<分机号>属性
     */
	public void setContTelSub(String contTelSub) {
		this.contTelSub = contTelSub;
	}
	/**     
     *访问<贷款人相关流水号>属性
     */
	public String getApptSeq() {
		return apptSeq;
	}
	/**     
     *设置<贷款人相关流水号>属性
     */
	public void setApptSeq(String apptSeq) {
		this.apptSeq = apptSeq;
	}
	/**     
     *访问<申请人流水号>属性
     */
	public String getApplSeq() {
		return applSeq;
	}
	/**     
     *设置<申请人流水号>属性
     */
	public void setApplSeq(String applSeq) {
		this.applSeq = applSeq;
	}
	/**     
     *访问<联系信息流水号>属性
     */
	public String getContSeq() {
		return contSeq;
	}
	/**     
     *设置<联系信息流水号>属性
     */
	public void setContSeq(String contSeq) {
		this.contSeq = contSeq;
	}
    
   
}


