package com.yucheng.byxf.mini.message;


/******************************************************************************
 * 
 * 北银消费金融公司-移动业务终端三期-轻松e贷
 *
 * Copyright © 2014 YuchengTech Technologies Limited All Rights Reserved.
 * 
 * Created on 2014-2-22 18:03:14
 *
 *******************************************************************************/

/**
 * 通讯地址实体类
 *
 * @version 1.0
 *
 * @author neo
 */
public class ApptContactInfo  implements java.io.Serializable {

	/**
 	  *  联系信息流水号
      */
 	private String contSeq;
	/**
 	  *  是否为通讯地址
      */
 	private String contInd;
	/**
 	  *  地址类型
      */
 	private String contType;
	/**
 	  *  地址
      */
 	private String contAddr;
	/**
 	  *  邮编号
      */
 	private String contZip;
	/**
 	  *  电话
      */
 	private String contPhone;
	/**
 	  *  电话区号
      */
 	private String contZone;
	/**
 	  *  分机号
      */
 	private String contTelSub;
	/**
 	  *  贷款相关人流水号
      */
 	private String apptSeq;

    public ApptContactInfo() {
    }
	
    public ApptContactInfo(String contSeq) {
        this.contSeq = contSeq;
    }
    public ApptContactInfo(String contSeq, String contInd, String contType, String contAddr, String contZip, String contPhone, String contZone, String contTelSub, String apptSeq) {
       this.contSeq = contSeq;
       this.contInd = contInd;
       this.contType = contType;
       this.contAddr = contAddr;
       this.contZip = contZip;
       this.contPhone = contPhone;
       this.contZone = contZone;
       this.contTelSub = contTelSub;
       this.apptSeq = apptSeq;
    }
   
   /**     
     *访问<联系信息流水号>属性
     */
    public String getContSeq() {
        return this.contSeq;
    }	    
    /**     
     *设置<联系信息流水号>属性
     */
    public void setContSeq(String contSeq) {
        this.contSeq = contSeq;
    }
   /**     
     *访问<是否为通讯地址>属性
     */
    public String getContInd() {
        return this.contInd;
    }	    
    /**     
     *设置<是否为通讯地址>属性
     */
    public void setContInd(String contInd) {
        this.contInd = contInd;
    }
   /**     
     *访问<地址类型>属性
     */
    public String getContType() {
        return this.contType;
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
        return this.contAddr;
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
        return this.contZip;
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
        return this.contPhone;
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
        return this.contZone;
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
        return this.contTelSub;
    }	    
    /**     
     *设置<分机号>属性
     */
    public void setContTelSub(String contTelSub) {
        this.contTelSub = contTelSub;
    }
   /**     
     *访问<贷款相关人流水号>属性
     */
    public String getApptSeq() {
        return this.apptSeq;
    }	    
    /**     
     *设置<贷款相关人流水号>属性
     */
    public void setApptSeq(String apptSeq) {
        this.apptSeq = apptSeq;
    }


}
