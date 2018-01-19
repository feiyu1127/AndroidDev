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
 * 商品信息
 *
 * @version 1.0
 *
 * @author mark
 */
public class Goods  implements java.io.Serializable {

	private static final long serialVersionUID = 5975899111973241953L;

	/** 
 	  *  商品名称及型号
      */
 	private String name="";
	 
	/** 
 	  *  货号
      */
 	private String model="";
	 
	/** 
 	  *  价格(元)
      */
 	private String price="";
	 
	/** 
 	  *  拟首付金额(元)
      */
 	private String fstAmt="";
 	
 	/** 
	  * 商品类别
     */
	private String kind="";
	
	/** 
	  * 是否购买
	  */
	private String buyInd="";
	
	/** 
	  * 商品流水号
	  */
	private String goodsSeq="";
	
	/** 
	  * 申请流水号
	  */
	private String applSeq="";

    public Goods() {
    }
	
    public Goods(String name) {
        this.name = name;
    }
    public Goods(String name, String model, String price, String fstAmt,String kind,String buyInd,String goodsSeq,String applSeq) {
       this.name = name;
       this.model = model;
       this.price = price;
       this.fstAmt = fstAmt;
       this.kind = kind;
       this.buyInd = buyInd;
       this.goodsSeq=goodsSeq;
       this.applSeq = applSeq;
    }
   
   /**     
     *访问<商品名称及型号>属性
     */
    public String getName() {
        return this.name;
    }	    
    /**     
     *设置<商品名称及型号>属性
     */
    public void setName(String name) {
        this.name = name;
    }
   /**     
     *访问<货号>属性
     */
    public String getModel() {
        return this.model;
    }	    
    /**     
     *设置<货号>属性
     */
    public void setModel(String model) {
        this.model = model;
    }
   /**     
     *访问<价格(元)>属性
     */
    public String getPrice() {
        return this.price;
    }	    
    /**     
     *设置<价格(元)>属性
     */
    public void setPrice(String price) {
        this.price = price;
    }
   /**     
     *访问<拟首付金额(元)>属性
     */
    public String getFstAmt() {
        return this.fstAmt;
    }	    
    /**     
     *设置<拟首付金额(元)>属性
     */
    public void setFstAmt(String fstAmt) {
        this.fstAmt = fstAmt;
    }
    /**     
     *访问<商品类别>属性
     */
	public String getKind() {
		return kind;
	}
	 /**     
     *设置<商品类别>属性
     */
	public void setKind(String kind) {
		this.kind = kind;
	}
	/**     
     *访问<是否购买>属性
     */
	public String getBuyInd() {
		return buyInd;
	}
	/**     
     *设置<是否购买>属性
     */
	public void setBuyInd(String buyInd) {
		this.buyInd = buyInd;
	}
	/**     
     *访问<商品流水号>属性
     */
	public String getGoodsSeq() {
		return goodsSeq;
	}
	/**     
     *设置<商品流水号>属性
     */
	public void setGoodsSeq(String goodsSeq) {
		this.goodsSeq = goodsSeq;
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
    
    

}
