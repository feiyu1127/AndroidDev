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
 * 类名称：LcApptRelation  
 * 类描述：  联系人信息
 * 创建人：zhangmin  
 * 创建时间：2013-7-23 下午3:57:42  
 * 修改人：  
 * 修改时间：2013-7-23 下午3:57:42  
 * 修改备注：  
 * @version 1.0.0  
 *
 */
public class LcApptRelation  implements java.io.Serializable {

	private static final long serialVersionUID = 9033861242571966312L;
	//联系人姓名
 	private String indivRelName="";
	//与联系人关系
 	private String indivRelation="";
	//联系人工作单位名称
 	private String indivRelEmp="";
	//联系人住宅电话区号
 	private String indivRelZone="";
 	//联系人住宅电话电话号码
	private String indivRelPhone="";
	//联系人手机号
	private String indivRelMobile="";
	// 联系类型
	private String indivRelTyp="";
	//申请流水号
	private String applSeq="";
	//贷款相关人流水号
	private String apptSeq="";
	//新增联系人主键流水号
	private String relSeq="";
	
    public LcApptRelation() {
    }
    public LcApptRelation(String indivRelName, String indivRelation, String indivRelEmp, String indivRelZone,String indivRelPhone,String indivRelMobile,String indivRelTyp,String applSeq,String apptSeq,String relSeq) {
       this.indivRelName = indivRelName;
       this.indivRelation = indivRelation;
       this.indivRelEmp = indivRelEmp;
       this.indivRelZone = indivRelZone;
       this.indivRelPhone = indivRelPhone;
       this.indivRelMobile = indivRelMobile;
       this.indivRelTyp = indivRelTyp;
       this.applSeq = applSeq;
       this.apptSeq = apptSeq;
       this.relSeq = relSeq;
    }
    /**     
     *访问<联系人姓名>属性
     */
	public String getIndivRelName() {
		return indivRelName;
	} 
	/**     
     *设置<联系人姓名>属性
     */
	public void setIndivRelName(String indivRelName) {
		this.indivRelName = indivRelName;
	}
	 /**     
     *访问<与联系人关系>属性
     */
	public String getIndivRelation() {
		return indivRelation;
	}
	/**     
     *设置<与联系人关系>属性
     */
	public void setIndivRelation(String indivRelation) {
		this.indivRelation = indivRelation;
	}
	 /**     
     *访问<联系人工作单位名称>属性
     */
	public String getIndivRelEmp() {
		return indivRelEmp;
	}
	/**     
     *设置<联系人工作单位名称>属性
     */
	public void setIndivRelEmp(String indivRelEmp) {
		this.indivRelEmp = indivRelEmp;
	}
	/**     
     *访问<联系人住宅电话区号>属性
     */
	public String getIndivRelZone() {
		return indivRelZone;
	}
	/**     
     *设置<联系人住宅电话区号>属性
     */
	public void setIndivRelZone(String indivRelZone) {
		this.indivRelZone = indivRelZone;
	}
	/**     
     *访问<联系人住宅电话>属性
     */
	public String getIndivRelPhone() {
		return indivRelPhone;
	}
	/**     
     *设置<联系人住宅电话>属性
     */
	public void setIndivRelPhone(String indivRelPhone) {
		this.indivRelPhone = indivRelPhone;
	}
	/**     
     *访问<联系人手机号码>属性
     */
	public String getIndivRelMobile() {
		return indivRelMobile;
	}
	/**     
     *设置<联系人手机号码>属性
     */
	public void setIndivRelMobile(String indivRelMobile) {
		this.indivRelMobile = indivRelMobile;
	}
	/**     
     *访问<联系人类型>属性
     */
	public String getIndivRelTyp() {
		return indivRelTyp;
	}
	/**     
     *设置<联系人类型>属性
     */
	public void setIndivRelTyp(String indivRelTyp) {
		this.indivRelTyp = indivRelTyp;
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
     *访问<新增联系人主键流水号>属性
     */
	public String getRelSeq() {
		return relSeq;
	}
	/**     
     *设置<新增联系人主键流水号>属性
     */
	public void setRelSeq(String relSeq) {
		this.relSeq = relSeq;
	}
   
   
}


