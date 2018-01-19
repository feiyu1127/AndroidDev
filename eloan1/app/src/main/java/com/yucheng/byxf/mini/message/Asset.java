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
 * 资产信息
 *
 * @version 1.0
 *
 * @author mark
 */
public class Asset  implements java.io.Serializable {

	private static final long serialVersionUID = 9033861242571966312L;

	/** 
 	  *  资产性质
      */
 	private String assetKind="";
	 
	/** 
 	  *  资产类型
      */
 	private String assetTyp="";
	 
	/** 
 	  *  金额
      */
 	private String assetAmt="";
	 
	/** 
 	  *  月还款额
      */
 	private String assetMthAmt="";
 	
 	/** 
	  *  贷款余额
     */
	private String assetReAmt="";
	
	/** 
	  *  申请流水号
    */
	private String applSeq="";

	/** 
	  *  贷款相关人流水号
	  */
	private String apptSeq="";
	/** 
	  *  资产表流水号
	  */
	private String assetSeq="";
	
    public Asset() {
    }
    public Asset(String assetKind, String assetTyp, String assetAmt, String assetMthAmt,String assetReAmt,String applSeq,String apptSeq,String assetSeq) {
       this.assetKind = assetKind;
       this.assetTyp = assetTyp;
       this.assetAmt = assetAmt;
       this.assetMthAmt = assetMthAmt;
       this.assetReAmt = assetReAmt;
       this.applSeq = applSeq;
       this.apptSeq = apptSeq;
       this.assetSeq = assetSeq;
    }
   
   /**     
     *访问<资产性质>属性
     */
    public String getAssetKind() {
        return this.assetKind;
    }	    
    /**     
     *设置<资产性质>属性
     */
    public void setAssetKind(String assetKind) {
        this.assetKind = assetKind;
    }
   /**     
     *访问<资产类型>属性
     */
    public String getAssetTyp() {
        return this.assetTyp;
    }	    
    /**     
     *设置<资产类型>属性
     */
    public void setAssetTyp(String assetTyp) {
        this.assetTyp = assetTyp;
    }
   /**     
     *访问<金额>属性
     */
    public String getAssetAmt() {
        return this.assetAmt;
    }	    
    /**     
     *设置<金额>属性
     */
    public void setAssetAmt(String assetAmt) {
        this.assetAmt = assetAmt;
    }
   /**     
     *访问<月还款额>属性
     */
    public String getAssetMthAmt() {
        return this.assetMthAmt;
    }	    
    /**     
     *设置<月还款额>属性
     */
    public void setAssetMthAmt(String assetMthAmt) {
        this.assetMthAmt = assetMthAmt;
    }
    /**     
     *访问<贷款余额>属性
     */
	public String getAssetReAmt() {
		return assetReAmt;
	}
	/**     
     *设置<贷款余额>属性
     */
	public void setAssetReAmt(String assetReAmt) {
		this.assetReAmt = assetReAmt;
	}
	/**     
     *访问<申请流水号>属性
     */
	public String getApplSeq() {
		return applSeq;
	}
	/**     
     *设置<申请流水号>属性
     */
	public void setApplSeq(String applSeq) {
		this.applSeq = applSeq;
	}
	/**     
     *访问<贷款相关人流水号>属性
     */
	public String getApptSeq() {
		return apptSeq;
	}
	/**     
     *设置<贷款相关人流水号>属性
     */
	public void setApptSeq(String apptSeq) {
		this.apptSeq = apptSeq;
	}
	/**     
     *访问<资产表流水号>属性
     */
	public String getAssetSeq() {
		return assetSeq;
	}
	/**     
     *设置<资产表流水号>属性
     */
	public void setAssetSeq(String assetSeq) {
		this.assetSeq = assetSeq;
	}

    

}


