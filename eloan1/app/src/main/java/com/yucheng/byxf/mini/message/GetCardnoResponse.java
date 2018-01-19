/******************************************************************************
 * 
 * 北银消费金融公司-移动业务终端三期-轻松e贷
 *
 * Copyright © 2014 YuchengTech Technologies Limited All Rights Reserved.
 * 
 * Created on 2014-2-22 18:10:03
 *
 *******************************************************************************/
package com.yucheng.byxf.mini.message;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取卡号实体类
 *
 * @version 1.0
 *
 * @author neo
 */

public class GetCardnoResponse  implements java.io.Serializable {

	/**
 	  *  返回码
      */
 	private String retCode;
	/**
 	  *  返回信息
      */
 	private String retInfo;
	/**
 	  *  卡号
      */
 	private String cardNo;

    public GetCardnoResponse() {
    }
	
    public GetCardnoResponse(String retCode) {
        this.retCode = retCode;
    }
    public GetCardnoResponse(String retCode, String retInfo, String cardNo) {
       this.retCode = retCode;
       this.retInfo = retInfo;
       this.cardNo = cardNo;
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
     *访问<卡号>属性
     */
    public String getCardNo() {
        return this.cardNo;
    }	    
    /**     
     *设置<卡号>属性
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
    
}
