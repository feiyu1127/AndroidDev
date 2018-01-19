/******************************************************************************
 * 
 * 北银消费金融公司-移动业务终端三期-轻松e贷
 *
 * Copyright © 2014 YuchengTech Technologies Limited All Rights Reserved.
 * 
 * Created on 2014-2-26 22:09:25
 *
 *******************************************************************************/
package com.yucheng.byxf.mini.message;

/**
 * 提交贷款意向实体类
 *
 * @version 1.0
 *
 * @author neo
 */
public class LoanIntent  implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5690812376887050735L;
	/**
 	  *  ID
      */
 	private Long id;
	/**
 	  *  证件号码
      */
 	private String idNo;
	/**
 	  *  姓名
      */
 	private String custName;
	/**
 	 *  联系电话
 	 */
 	private String indivMobile;
 	/**
 	 *  联系电话
 	 */
 	private String indivMobile2;
	public String getIndivMobile2() {
		return indivMobile2;
	}

	public void setIndivMobile2(String indivMobile2) {
		this.indivMobile2 = indivMobile2;
	}

	/**
 	  *  贷款金额
      */
 	private String applyAmt;
	/**
 	  *  借款用途 DEC装修 EDU教育 MAR婚庆 TRA旅游 OTH其他
      */
 	private String purpose;
	/**
 	  *  借款用途(其他)
      */
 	private String budUse;
	/**
 	  *  是否有银行信用卡
      */
 	private String indivCardInd;
	/**
 	  *  信用卡最高额度
      */
 	private String indivCardAmt;
	/**
 	  *  最高学历08博士 09硕士 10本科 20大专 30高中或中专 40初中及以下
      */
 	private String indivDegree;
	/**
 	  *  婚姻状况10未婚 20已婚 90其他
      */
 	private String indivMarital;
	/**
 	  *  婚姻状况(其他)
      */
 	private String marrSts;
	/**
 	  *  房产状况1自购无贷款 2自购有贷款 3单位宿舍 4与亲属同住 5租住 7其他
      */
 	private String indivLive;
	/**
 	  *  房产状况(其他)
      */
 	private String livSts;
	/**
 	  *  居住地址
      */
 	private String indivLiveAddr;
	/**
 	  *  工作单位
      */
 	private String indivEmp;
	/**
 	  *  单位性质
      */
 	private String indivEmpType;
	/**
 	  *  月收入
      */
 	private String indivMthInc;
 	
 	private String submitTime;

    public LoanIntent() {
    }
	
    public LoanIntent(Long id) {
        this.id = id;
    }
    public LoanIntent(Long id, String idNo, String custName, String indivMobile, String applyAmt, String purpose, String budUse, String indivCardInd, String indivCardAmt, String indivDegree, String indivMarital, String marrSts, String indivLive, String livSts, String indivLiveAddr, String indivEmp, String indivEmpType, String indivMthInc) {
       this.id = id;
       this.idNo = idNo;
       this.custName = custName;
       this.indivMobile = indivMobile;
       this.applyAmt = applyAmt;
       this.purpose = purpose;
       this.budUse = budUse;
       this.indivCardInd = indivCardInd;
       this.indivCardAmt = indivCardAmt;
       this.indivDegree = indivDegree;
       this.indivMarital = indivMarital;
       this.marrSts = marrSts;
       this.indivLive = indivLive;
       this.livSts = livSts;
       this.indivLiveAddr = indivLiveAddr;
       this.indivEmp = indivEmp;
       this.indivEmpType = indivEmpType;
       this.indivMthInc = indivMthInc;
    }
   
   /**     
     *访问<ID>属性
     */
    public Long getId() {
        return this.id;
    }	    
    /**     
     *设置<ID>属性
     */
    public void setId(Long id) {
        this.id = id;
    }
   /**     
     *访问<证件号码>属性
     */
    public String getIdNo() {
        return this.idNo;
    }	    
    /**     
     *设置<证件号码>属性
     */
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }
   /**     
     *访问<姓名>属性
     */
    public String getCustName() {
        return this.custName;
    }	    
    /**     
     *设置<姓名>属性
     */
    public void setCustName(String custName) {
        this.custName = custName;
    }
   /**     
     *访问<联系电话>属性
     */
    public String getIndivMobile() {
        return this.indivMobile;
    }	    
    /**     
     *设置<联系电话>属性
     */
    public void setIndivMobile(String indivMobile) {
        this.indivMobile = indivMobile;
    }
   /**     
     *访问<贷款金额>属性
     */
    public String getApplyAmt() {
        return this.applyAmt;
    }	    
    /**     
     *设置<贷款金额>属性
     */
    public void setApplyAmt(String applyAmt) {
        this.applyAmt = applyAmt;
    }
   /**     
     *访问<借款用途 DEC装修 EDU教育 MAR婚庆 TRA旅游 OTH其他>属性
     */
    public String getPurpose() {
        return this.purpose;
    }	    
    /**     
     *设置<借款用途 DEC装修 EDU教育 MAR婚庆 TRA旅游 OTH其他>属性
     */
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
   /**     
     *访问<借款用途(其他)>属性
     */
    public String getBudUse() {
        return this.budUse;
    }	    
    /**     
     *设置<借款用途(其他)>属性
     */
    public void setBudUse(String budUse) {
        this.budUse = budUse;
    }
   /**     
     *访问<是否有银行信用卡>属性
     */
    public String getIndivCardInd() {
        return this.indivCardInd;
    }	    
    /**     
     *设置<是否有银行信用卡>属性
     */
    public void setIndivCardInd(String indivCardInd) {
        this.indivCardInd = indivCardInd;
    }
   /**     
     *访问<信用卡最高额度>属性
     */
    public String getIndivCardAmt() {
        return this.indivCardAmt;
    }	    
    /**     
     *设置<信用卡最高额度>属性
     */
    public void setIndivCardAmt(String indivCardAmt) {
        this.indivCardAmt = indivCardAmt;
    }
   /**     
     *访问<最高学历08博士 09硕士 10本科 20大专 30高中或中专 40初中及以下>属性
     */
    public String getIndivDegree() {
        return this.indivDegree;
    }	    
    /**     
     *设置<最高学历08博士 09硕士 10本科 20大专 30高中或中专 40初中及以下>属性
     */
    public void setIndivDegree(String indivDegree) {
        this.indivDegree = indivDegree;
    }
   /**     
     *访问<婚姻状况10未婚 20已婚 90其他>属性
     */
    public String getIndivMarital() {
        return this.indivMarital;
    }	    
    /**     
     *设置<婚姻状况10未婚 20已婚 90其他>属性
     */
    public void setIndivMarital(String indivMarital) {
        this.indivMarital = indivMarital;
    }
   /**     
     *访问<婚姻状况(其他)>属性
     */
    public String getMarrSts() {
        return this.marrSts;
    }	    
    /**     
     *设置<婚姻状况(其他)>属性
     */
    public void setMarrSts(String marrSts) {
        this.marrSts = marrSts;
    }
   /**     
     *访问<房产状况1自购无贷款 2自购有贷款 3单位宿舍 4与亲属同住 5租住 7其他>属性
     */
    public String getIndivLive() {
        return this.indivLive;
    }	    
    /**     
     *设置<房产状况1自购无贷款 2自购有贷款 3单位宿舍 4与亲属同住 5租住 7其他>属性
     */
    public void setIndivLive(String indivLive) {
        this.indivLive = indivLive;
    }
   /**     
     *访问<房产状况(其他)>属性
     */
    public String getLivSts() {
        return this.livSts;
    }	    
    /**     
     *设置<房产状况(其他)>属性
     */
    public void setLivSts(String livSts) {
        this.livSts = livSts;
    }
   /**     
     *访问<居住地址>属性
     */
    public String getIndivLiveAddr() {
        return this.indivLiveAddr;
    }	    
    /**     
     *设置<居住地址>属性
     */
    public void setIndivLiveAddr(String indivLiveAddr) {
        this.indivLiveAddr = indivLiveAddr;
    }
   /**     
     *访问<工作单位>属性
     */
    public String getIndivEmp() {
        return this.indivEmp;
    }	    
    /**     
     *设置<工作单位>属性
     */
    public void setIndivEmp(String indivEmp) {
        this.indivEmp = indivEmp;
    }
   /**     
     *访问<单位性质>属性
     */
    public String getIndivEmpType() {
        return this.indivEmpType;
    }	    
    /**     
     *设置<单位性质>属性
     */
    public void setIndivEmpType(String indivEmpType) {
        this.indivEmpType = indivEmpType;
    }
   /**     
     *访问<月收入>属性
     */
    public String getIndivMthInc() {
        return this.indivMthInc;
    }	    
    /**     
     *设置<月收入>属性
     */
    public void setIndivMthInc(String indivMthInc) {
        this.indivMthInc = indivMthInc;
    }
    
	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}


}
