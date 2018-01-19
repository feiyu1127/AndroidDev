package com.yucheng.byxf.mini.message;

 import java.util.ArrayList;
import java.util.List;

public class EasyLoanRequest {
	
	//业务类型
	private String txType = "";
	//交易码
	private String txCde = "";
	//客户名称
	private String  custName="";
	//本单位工作年限
	private String  indivEmpYrs="";
	//总工作年限
	private String  indivWorkYrs="";
	//联系人姓名
	private String  indivRelName="";
	//联系人工作单位名称
	private String  indivRelEmp="";
	//与联系人工作关系
	private String  indivRelation="";
	//联系人住宅电话区号
	private String  indivRelZone="";
	//联系人住宅电话号码
	private String  indivRelPhone="";
	//联系人手机号
	private String  indivRelMobile="";
	//文化程度
	private String  indivDegree="";
	//证件号码
	private String  idNo="";
	//国籍
	private String  idCtry="CHN";
	//非亲属联系人姓名
	private String  indivOthName="";
	//非亲属联系人单位名称
	private String  indivOthEmp="";
	//与联系人关系
	private String  indivOthRel="";
	//非亲属联系人单位(电话号码)
	private String  indivOthPhone="";
	//非亲属联系人电话(区号)
	private String  indivOthZone="";
	//非亲属联系人手机号
	private String  indivOthMobile="";
	//证件有效期(开始)
	private String  idTermBgn="";
	//证件有效期(结束)
	private String  idTermEnd="";
	//出生日期
	private String  apptStartDate="";
	//年龄
	private String  apptAge="";
	//性别
	private String  indivSex="";
	//供养人数
	private String  indivDepNo="";
	//婚姻状况
	private String  indivMarital="";
	//现居住住房性质
	private String  indivLive="";
	//月租金(元)
	private String  indivRentAmt="";
	//现居住地址
	private String  indivLiveAddr="";
	//户籍地址
	private String  indivRegAddr="";
	//住宅电话_区号
	private String  indivLiveZone="";
	//住宅电话_电话号码
	private String  indivLivePhone="";
	//个人工资月收入(元)
	private String  indivMthInc="";
	//手机号码
	private String  indivMobile="";
	//电子邮箱
	private String  indivEmail="";
	//现单位全称
	private String  indivEmp="";
	//任职部门
	private String  indivBranch="";
	//单位地址
	private String  indivEmpAddr="";
	//单位电话_区号
	private String  indivEmpZone="";
	//单位电话_电话号码
	private String  indivEmpTelNo="";
	//单位电话_分机号
	private String  indivEmpTelSub="";
	//申请流水号
	private String  applSeq="";
	//与贷款相关流水号
	private String  apptSeq="";
	//联络部门
	private String  pomsDepart="";
	//联络员工姓名
	private String  pomsStaff="";
	//是否开通免费短信通知服务
	private String  mssInd="Y";
	//申请日期
	private String  applyDt;
	//机构码
	private String  bchCde="999999999";
	//贷款组
	private String  loanGrp="";
	//贷款品种
	private String  loanTyp="";
	//贷款类别
	private String  loanKind="";
	//申请币种
	private String  loanCcy="RMB";
	//贷款用途
	private String  purpose="";
	//其他用途
	private String  otherPurpose="";
	//贷款申请金额
	private String  applyAmt="";
	//贷款申请期限(按月)
	private String  applyTnr="";
	//审批金额
	private String  apprvAmt="";
	//审批期限(按月)
	private String  apprvTnr="";
	//还款方式
	private String  loanMtd="";
	//购买总价
	private String  proPurAmt="";
	//首付金额
	private String  fstPay="";
	//宽限期类型
	private String  odGranceTyp="";
	//宽限期天数
	private String  odGrance="";
	//放款通知书、还款计划表寄送地址
	private String  mailAddr="";
	//经销商代码
	private String  dealer="";
	//推广员工ID/经办人工号
	private String  operator="";
	//推广员工签名/经办员工姓名
	private String  operatorName="";
	//销售员工号
	private String  salerId="";
	//销售员工姓名
	private String  salerName="";
	//专案代码
	private String  pcCode="01";
	//放款账号开户行
	private String  applDisbAcBank="";
	//放款账号
	private String  applDisbAcNo="";
	//放款账户名
	private String  applDisbAcNam="";
	//还款账户开户行
	private String  applRpymAcBank="";
	//还款账号
	private String  applRpymAcNo="";
	//还款账号户名
	private String  applRpymAcNam="";
	//申请编号
	private String  applCde="";
	//渠道码：YDKHDYB—移动客户端一般消费贷款(轻松贷)；YDKHDNY—移动客户端耐用贷款(轻松付)
	private String  trenchNo="";
	//网点号：PADSHB—PAD商户版；SJSHB—手机商户版；SJGRB—手机个人版；
	private String  branchNo="";
	//支行网点号
	private String  switchNo="";
	//团办码
	private String  groupNo="";
	//进件来源,01---商户，02----pad,03----业务受理
	private String  inputSrc="08";
	//放款信息流水号
	private String  applDisbSeq="";
	//还款信息流水号
	private String  applRpymSeq="";
	//证件类型
	private String  idType="20";
	//最后更新日期
	private String  lastChgDt;
	//创建人
	private String  crtUsr="";
	//表单类型
	private String  form="";
	//还款间隔单位
	private String  freqUnit="1";
	//商品信息
	private List<Goods> goods = new ArrayList<Goods>();
	//新增联系人信息
	private List<LcApptRelation> lcAppRelations = new ArrayList<LcApptRelation>();
	//联系信息
	private List<ApptContact> appContact = new ArrayList<ApptContact>();
	//资产信息
	private List<Asset> asset = new ArrayList<Asset>();
	
	private String goodStr ="";
	//新增联系人信息
	private String lcAppRelationStr = "";
	//联系信息
	private String appContactStr = "";
	//资产信息
	private String assetStr = "";
	
	//账户类型
	private String applAcctInd = "";
	//审批状态
	private String state = "";
	//还款方式种类
	private String mtdTyp = "01";
	//还款期数
	private String loanInstal = "";
	//申请表情况
	private String applCircs = "";
	//申请资料
	private String applMate = "";
	//告知事项
	private String tellMatt = "";
	//申请人电话核实标志
	private String apptVeriFlag = "";
	//联系人电话核实标志
	private String relaVeriFlag = "";
	//申请人电话核实时间
	private String apptVeriDt = "";
	//联系人电话核实时间
	private String relaVeriDt = "";
	//备注
	private String remark = "";
	//手机是否通过验证
	private String indivPhoneChk = "";
	//受理日期
	private String inputDt ;
	//经办人id
//	private String jurorId = "";
	//职业
//	private String indivOccup = "";
	// 单位性质
	private String indivEmpType = "";
	// 企业规模
	private String indivEmpNum = "";
	// 行业性质
	private String indivIndtryPaper = "";
	// 现任职务
	//private String indivPosition = "";
	//记录日志中所需字段
	private String latitude="";
	private String longitude="";
	private String location="";
	//ip
	private String ip="";
	//户籍邮编 INDIV_REG_ZIP
	private String indivRegZip="";
	//居住地邮编 INDIV_LIVE_ZIP
	private String indivLiveZip="";
	
	
	
	//推广方式
	private String spreadType="";
	//进件来源
	private String applSrc="";
	//说明
	private String spreadRemark="";
	//评分
	private String scordeGrade="";
	//单位邮编
	private String indivEmpZip="";
	
	//现任职位  INDIV_POSITION
	private String  indivPosition="";
	//职业  INDIV_OCCUP
	private String indivOccup="";
	//是否有银行信用卡    INDIV_CARD_IND  Y/N
	private String indivCardInd="";
	//发卡银行名称     INDIV_CARD_BANK  
	private String indivCardBank="";
	//单张信用卡最高额度  INDIV_CARD_AMT
	private String indivCardAmt="";
	
	private String applyType;   //申请类型  0轻松贷   1极速贷
	
	
	private List<MiniDocInfo> docList; 

	
	//新加打回字段    ,号分割 拆分用
	private String curentResidentialAddr;
	
	
	public String getCurentResidentialAddr() {
		return curentResidentialAddr;
	}
	public void setCurentResidentialAddr(String curentResidentialAddr) {
		this.curentResidentialAddr = curentResidentialAddr;
	}
	/**     
     *访问<客户名称>属性
     */
	public String getCustName() {
		return custName;
	}
	/**     
     *访问<本单位工作年限>属性
     */
	public String getIndivEmpYrs() {
		return indivEmpYrs;
	}
	/**     
     *访问<总工作年限>属性
     */
	public String getIndivWorkYrs() {
		return indivWorkYrs;
	}
	/**     
     *访问<联系人姓名>属性
     */
	public String getIndivRelName() {
		return indivRelName;
	}
	/**     
     *访问<联系人工作单位名称>属性
     */
	public String getIndivRelEmp() {
		return indivRelEmp;
	}
	/**     
     *访问<与联系人工作关系>属性
     */
	public String getIndivRelation() {
		return indivRelation;
	}
	/**     
     *访问<联系人住宅电话区号>属性
     */
	public String getIndivRelZone() {
		return indivRelZone;
	}
	/**     
     *访问<联系人住宅电话号码>属性
     */
	public String getIndivRelPhone() {
		return indivRelPhone;
	}
	/**     
     *访问<联系人手机号>属性
     */
	public String getIndivRelMobile() {
		return indivRelMobile;
	}
	/**     
     *访问<文化程度>属性
     */
	public String getIndivDegree() {
		return indivDegree;
	}
	/**     
     *访问<证件号码>属性
     */
	public String getIdNo() {
		return idNo;
	}
	/**     
     *访问<国籍>属性
     */
	public String getIdCtry() {
		return idCtry;
	}
	/**     
     *访问<非亲属联系人姓名>属性
     */
	public String getIndivOthName() {
		return indivOthName;
	}
	/**     
     *访问<非亲属联系人单位名称>属性
     */
	public String getIndivOthEmp() {
		return indivOthEmp;
	}
	/**     
     *访问<与联系人关系>属性
     */
	public String getIndivOthRel() {
		return indivOthRel;
	}
	/**     
     *访问<非亲属联系人单位电话号码>属性
     */
	public String getIndivOthPhone() {
		return indivOthPhone;
	}
	/**     
     *访问<非亲属联系人单位电话区号>属性
     */
	public String getIndivOthZone() {
		return indivOthZone;
	}
	/**     
     *访问<非亲属联系人手机号>属性
     */
	public String getIndivOthMobile() {
		return indivOthMobile;
	}
	/**     
     *访问<证件有效期(开始)>属性
     */
	public String getIdTermBgn() {
		return idTermBgn;
	}
	/**     
     *访问<证件有效期(结束)>属性
     */
	public String getIdTermEnd() {
		return idTermEnd;
	}
	/**     
     *访问<出生日期>属性
     */
	public String getApptStartDate() {
		return apptStartDate;
	}
	/**     
     *访问<年龄>属性
     */
	public String getApptAge(){
		return apptAge;
	}
	/**     
     *访问<性别>属性
     */
	public String getIndivSex() {
		return indivSex;
	}
	/**     
     *访问<供养人数>属性
     */
	public String getIndivDepNo() {
		return indivDepNo;
	}
	/**     
     *访问<婚姻状况>属性
     */
	public String getIndivMarital() {
		return indivMarital;
	}
	/**     
     *访问<新居住住房性质>属性
     */
	public String getIndivLive() {
		return indivLive;
	}
	/**     
     *访问<现租金()元>属性
     */
	public String getIndivRentAmt() {
		return indivRentAmt;
	}
	/**     
     *访问<现居住地址>属性
     */
	public String getIndivLiveAddr() {
		return indivLiveAddr;
	}
	/**     
     *访问<户籍地址>属性
     */
	public String getIndivRegAddr() {
		return indivRegAddr;
	}
	/**     
     *访问<住宅电话区号>属性
     */
	public String getIndivLiveZone() {
		return indivLiveZone;
	}
	/**     
     *访问<住宅电话号码>属性
     */
	public String getIndivLivePhone() {
		return indivLivePhone;
	}
	/**     
     *访问<个人工资月收入(元)>属性
     */
	public String getIndivMthInc() {
		return indivMthInc;
	}
	/**     
     *访问<手机号码>属性
     */
	public String getIndivMobile() {
		return indivMobile;
	}
	/**     
     *访问<电子邮箱>属性
     */
	public String getIndivEmail() {
		return indivEmail;
	}
	/**     
     *访问<现单位地址>属性
     */
	public String getIndivEmp() {
		return indivEmp;
	}
	/**     
     *访问<任职部门>属性
     */
	public String getIndivBranch() {
		return indivBranch;
	}
	/**     
     *访问<单位地址>属性
     */
	public String getIndivEmpAddr() {
		return indivEmpAddr;
	}
	/**     
     *访问<单位电话区号>属性
     */
	public String getIndivEmpZone() {
		return indivEmpZone;
	}
	/**     
     *访问<单位电话号码>属性
     */
	public String getIndivEmpTelNo() {
		return indivEmpTelNo;
	}
	/**     
     *访问<单位电话分机号>属性
     */
	public String getIndivEmpTelSub() {
		return indivEmpTelSub;
	}
	/**     
     *访问<申请流水号>属性
     */
	public String getApplSeq() {
		return applSeq;
	}
	/**     
     *访问<与贷款相关流水号>属性
     */
	public String getApptSeq() {
		return apptSeq;
	}
	/**     
     *访问<联络部门>属性
     */
	public String getPomsDepart() {
		return pomsDepart;
	}
	/**     
     *访问<联络员工姓名>属性
     */
	public String getPomsStaff() {
		return pomsStaff;
	}
	/**     
     *访问<是否开通免费短信通知服务>属性
     */
	public String getMssInd() {
		return mssInd;
	}
	/**     
     *访问<申请日期>属性
     */
	public String getApplyDt() {
		return applyDt;
	}
	/**     
     *访问<机构码>属性
     */
	public String getBchCde() {
		return bchCde;
	}
	/**     
     *访问<贷款组>属性
     */
	public String getLoanGrp() {
		return loanGrp;
	}
	/**     
     *访问<贷款品种>属性
     */
	public String getLoanTyp() {
		return loanTyp;
	}
	/**     
     *访问<贷款类别>属性
     */
	public String getLoanKind() {
		return loanKind;
	}
	/**     
     *访问<申请币种>属性
     */
	public String getLoanCcy() {
		return loanCcy;
	}
	/**     
     *访问<贷款用途>属性
     */
	public String getPurpose() {
		return purpose;
	}
	/**     
     *访问<其他用途>属性
     */
	public String getOtherPurpose() {
		return otherPurpose;
	}
	/**     
     *访问<贷款申请金额>属性
     */
	public String getApplyAmt() {
		return applyAmt;
	}
	/**     
     *访问<贷款申请期限>属性
     */
	public String getApplyTnr() {
		return applyTnr;
	}
	/**     
     *访问<审批金额>属性
     */
	public String getApprvAmt() {
		return apprvAmt;
	}
	/**     
     *访问<审批期限>属性
     */
	public String getApprvTnr() {
		return apprvTnr;
	}
	/**     
     *访问<还款方式>属性
     */
	public String getLoanMtd() {
		return loanMtd;
	}
	/**     
     *访问<购买总价>属性
     */
	public String getProPurAmt() {
		return proPurAmt;
	}
	/**     
     *访问<首付金额>属性
     */
	public String getFstPay() {
		return fstPay;
	}
	/**     
     *访问<宽限期类型>属性
     */
	public String getOdGranceTyp() {
		return odGranceTyp;
	}
	/**     
     *访问<宽限期天数>属性
     */
	public String getOdGrance() {
		return odGrance;
	}
	/**     
     *访问<放款通知书邮寄地址>属性
     */
	public String getMailAddr() {
		return mailAddr;
	}
	/**     
     *访问<经销商代码>属性
     */
	public String getDealer() {
		return dealer;
	}
	/**     
     *访问<推广员工id/代办员工工号>属性
     */
	public String getOperator() {
		return operator;
	}
	/**     
     *访问<推广员工名称/代办员工名称>属性
     */
	public String getOperatorName() {
		return operatorName;
	}
	/**     
     *访问<销售员工id>属性
     */
	public String getSalerId() {
		return salerId;
	}
	/**     
     *访问<销售员工姓名>属性
     */
	public String getSalerName() {
		return salerName;
	}
	/**     
     *访问<专案代码>属性
     */
	public String getPcCode() {
		return pcCode;
	}
	/**     
     *访问<放款账号开户行>属性
     */
	public String getApplDisbAcBank() {
		return applDisbAcBank;
	}
	/**     
     *访问<放款账号>属性
     */
	public String getApplDisbAcNo() {
		return applDisbAcNo;
	}
	/**     
     *访问<放款账号名>属性
     */
	public String getApplDisbAcNam() {
		return applDisbAcNam;
	}
	/**     
     *访问<还款账号开户行>属性
     */
	public String getApplRpymAcBank() {
		return applRpymAcBank;
	}
	/**     
     *访问<还款账号>属性
     */
	public String getApplRpymAcNo() {
		return applRpymAcNo;
	}
	/**     
     *访问<还款账号名>属性
     */
	public String getApplRpymAcNam() {
		return applRpymAcNam;
	}
	/**     
     *访问<申请编号>属性
     */
	public String getApplCde() {
		return applCde;
	}
	/**     
     *访问<渠道码>属性
     */
	public String getTrenchNo() {
		return trenchNo;
	}
	/**     
     *访问<网点号>属性
     */
	public String getBranchNo() {
		return branchNo;
	}
	/**     
     *访问<支行网点号>属性
     */
	public String getSwitchNo() {
		return switchNo;
	}
	/**     
     *访问<团办码>属性
     */
	public String getGroupNo() {
		return groupNo;
	}
	/**     
     *访问<进件来源>属性
     */
	public String getInputSrc() {
		return inputSrc;
	}
	/**     
     *访问<放款信息流水号>属性
     */
	public String getApplDisbSeq() {
		return applDisbSeq;
	}
	/**     
     *访问<还款信息流水号>属性
     */
	public String getApplRpymSeq() {
		return applRpymSeq;
	}
	/**     
     *访问<证件类型>属性
     */
	public String getIdType() {
		return idType;
	}
	/**     
     *访问<最后更新日期>属性
     */
	public String getLastChgDt() {
		return lastChgDt;
	}
	/**     
     *访问<创建人>属性
     */
	public String getCrtUsr() {
		return crtUsr;
	}
	/**     
     *访问<表单类型>属性
     */
	public String getForm() {
		return form;
	}
	/**     
     *访问<还款间隔单位>属性
     */
	public String getFreqUnit() {
		return freqUnit;
	}
	/**     
     *访问<商品列表>属性
     */
	public List<Goods> getGoods() {
		return goods;
	}
	/**     
     *访问<新增联系人>属性
     */
	public List<LcApptRelation> getLcAppRelations() {
		return lcAppRelations;
	}
	/**     
     *访问<新增联系人>属性
     */
	public List<ApptContact> getAppContact() {
		return appContact;
	}
	/**     
     *访问<资产>属性
     */
	public List<Asset> getAsset() {
		return asset;
	}
	/**     
     *访问<业务类型>属性
     */
	public String getTxType() {
		return txType;
	}
	/**     
     *访问<交易码>属性
     */
	public String getTxCde() {
		return txCde;
	}
 	/**     
     *访问<账户类型>属性
     */
	public String getApplAcctInd() {
		return applAcctInd;
	}
 	/**     
     *访问<审批状态>属性
     */
	public String getState() {
		return state;
	}
 	/**     
     *访问<还款方式种类>属性
     */
	public String getMtdTyp() {
		return mtdTyp;
	}
 	/**     
     *访问<还款期数>属性
     */
	public String getLoanInstal() {
		return loanInstal;
	}
 	/**     
     *访问<申请表情况>属性
     */
 	public String getApplCircs() {
		return applCircs;
	}
 	/**     
     *访问<申请资料>属性
     */
	public String getApplMate() {
		return applMate;
	}
 	/**     
     *访问<告知事项>属性
     */
	public String getTellMatt() {
		return tellMatt;
	}
 	/**     
     *访问<申请人电话核实标志>属性
     */
	public String getApptVeriFlag() {
		return apptVeriFlag;
	}
 	/**     
     *访问<联系人电话核实标志>属性
     */
	public String getRelaVeriFlag() {
		return relaVeriFlag;
	}
 	/**     
     *访问<申请人电话核实时间>属性
     */
	public String getApptVeriDt() {
		return apptVeriDt;
	}
 	/**     
     *访问<联系人电话核实时间>属性
     */
	public String getRelaVeriDt() {
		return relaVeriDt;
	}
 	/**     
     *访问<备注>属性
     */
	public String getRemark() {
		return remark;
	}
 	/**     
     *访问<手机是否通过验证>属性
     */
	public String getIndivPhoneChk() {
		return indivPhoneChk;
	}
 	/**     
     *访问<受理日期>属性
     */
	public String getInputDt() {
		return inputDt;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public void setIndivEmpYrs(String indivEmpYrs) {
		this.indivEmpYrs = indivEmpYrs;
	}
	public void setIndivWorkYrs(String indivWorkYrs) {
		this.indivWorkYrs = indivWorkYrs;
	}
	public void setIndivRelName(String indivRelName) {
		this.indivRelName = indivRelName;
	}
	public void setIndivRelEmp(String indivRelEmp) {
		this.indivRelEmp = indivRelEmp;
	}
	public void setIndivRelation(String indivRelation) {
		this.indivRelation = indivRelation;
	}
	public void setIndivRelZone(String indivRelZone) {
		this.indivRelZone = indivRelZone;
	}
	public void setIndivRelPhone(String indivRelPhone) {
		this.indivRelPhone = indivRelPhone;
	}
	public void setIndivRelMobile(String indivRelMobile) {
		this.indivRelMobile = indivRelMobile;
	}
	public void setIndivDegree(String indivDegree) {
		this.indivDegree = indivDegree;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public void setIdCtry(String idCtry) {
		this.idCtry = idCtry;
	}
	public void setIndivOthName(String indivOthName) {
		this.indivOthName = indivOthName;
	}
	public void setIndivOthEmp(String indivOthEmp) {
		this.indivOthEmp = indivOthEmp;
	}
	public void setIndivOthRel(String indivOthRel) {
		this.indivOthRel = indivOthRel;
	}
	public void setIndivOthPhone(String indivOthPhone) {
		this.indivOthPhone = indivOthPhone;
	}
	public void setIndivOthZone(String indivOthZone) {
		this.indivOthZone = indivOthZone;
	}
	public void setIndivOthMobile(String indivOthMobile) {
		this.indivOthMobile = indivOthMobile;
	}
	public void setIdTermBgn(String idTermBgn) {
		this.idTermBgn = idTermBgn;
	}
	public void setIdTermEnd(String idTermEnd) {
		this.idTermEnd = idTermEnd;
	}
	public void setApptStartDate(String apptStartDate) {
		this.apptStartDate = apptStartDate;
	}
	public void setApptAge(String apptAge) {
		this.apptAge = apptAge;
	}
	public void setIndivSex(String indivSex) {
		this.indivSex = indivSex;
	}
	public void setIndivDepNo(String indivDepNo) {
		this.indivDepNo = indivDepNo;
	}
	public void setIndivMarital(String indivMarital) {
		this.indivMarital = indivMarital;
	}
	public void setIndivLive(String indivLive) {
		this.indivLive = indivLive;
	}
	public void setIndivRentAmt(String indivRentAmt) {
		this.indivRentAmt = indivRentAmt;
	}
	public void setIndivLiveAddr(String indivLiveAddr) {
		this.indivLiveAddr = indivLiveAddr;
	}
	public void setIndivRegAddr(String indivRegAddr) {
		this.indivRegAddr = indivRegAddr;
	}
	public void setIndivLiveZone(String indivLiveZone) {
		this.indivLiveZone = indivLiveZone;
	}
	public void setIndivLivePhone(String indivLivePhone) {
		this.indivLivePhone = indivLivePhone;
	}
	public void setIndivMthInc(String indivMthInc) {
		this.indivMthInc = indivMthInc;
	}
	public void setIndivMobile(String indivMobile) {
		this.indivMobile = indivMobile;
	}
	public void setIndivEmail(String indivEmail) {
		this.indivEmail = indivEmail;
	}
	public void setIndivEmp(String indivEmp) {
		this.indivEmp = indivEmp;
	}
	public void setIndivBranch(String indivBranch) {
		this.indivBranch = indivBranch;
	}
	public void setIndivEmpAddr(String indivEmpAddr) {
		this.indivEmpAddr = indivEmpAddr;
	}
	public void setIndivEmpZone(String indivEmpZone) {
		this.indivEmpZone = indivEmpZone;
	}
	public void setIndivEmpTelNo(String indivEmpTelNo) {
		this.indivEmpTelNo = indivEmpTelNo;
	}
	public void setIndivEmpTelSub(String indivEmpTelSub) {
		this.indivEmpTelSub = indivEmpTelSub;
	}
	public void setApplSeq(String applSeq) {
		this.applSeq = applSeq;
	}
	public void setApptSeq(String apptSeq) {
		this.apptSeq = apptSeq;
	}
	public void setPomsDepart(String pomsDepart) {
		this.pomsDepart = pomsDepart;
	}
	public void setPomsStaff(String pomsStaff) {
		this.pomsStaff = pomsStaff;
	}
	public void setMssInd(String mssInd) {
		this.mssInd = mssInd;
	}
	public void setApplyDt(String applyDt) {
		this.applyDt = applyDt;
	}
	public void setBchCde(String bchCde) {
		this.bchCde = bchCde;
	}
	public void setLoanGrp(String loanGrp) {
		this.loanGrp = loanGrp;
	}
	public void setLoanTyp(String loanTyp) {
		this.loanTyp = loanTyp;
	}
	public void setLoanKind(String loanKind) {
		this.loanKind = loanKind;
	}
	public void setLoanCcy(String loanCcy) {
		this.loanCcy = loanCcy;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public void setOtherPurpose(String otherPurpose) {
		this.otherPurpose = otherPurpose;
	}
	public void setApplyAmt(String applyAmt) {
		this.applyAmt = applyAmt;
	}
	public void setApplyTnr(String applyTnr) {
		this.applyTnr = applyTnr;
	}
	public void setApprvAmt(String apprvAmt) {
		this.apprvAmt = apprvAmt;
	}
	public void setApprvTnr(String apprvTnr) {
		this.apprvTnr = apprvTnr;
	}
	public void setLoanMtd(String loanMtd) {
		this.loanMtd = loanMtd;
	}
	public void setProPurAmt(String proPurAmt) {
		this.proPurAmt = proPurAmt;
	}
	public void setFstPay(String fstPay) {
		this.fstPay = fstPay;
	}
	public void setOdGranceTyp(String odGranceTyp) {
		this.odGranceTyp = odGranceTyp;
	}
	public void setOdGrance(String odGrance) {
		this.odGrance = odGrance;
	}
	public void setMailAddr(String mailAddr) {
		this.mailAddr = mailAddr;
	}
	public void setDealer(String dealer) {
		this.dealer = dealer;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public void setSalerId(String salerId) {
		this.salerId = salerId;
	}
	public void setSalerName(String salerName) {
		this.salerName = salerName;
	}
	public void setPcCode(String pcCode) {
		this.pcCode = pcCode;
	}
	public void setApplDisbAcBank(String applDisbAcBank) {
		this.applDisbAcBank = applDisbAcBank;
	}
	public void setApplDisbAcNo(String applDisbAcNo) {
		this.applDisbAcNo = applDisbAcNo;
	}
	public void setApplDisbAcNam(String applDisbAcNam) {
		this.applDisbAcNam = applDisbAcNam;
	}
	public void setApplRpymAcBank(String applRpymAcBank) {
		this.applRpymAcBank = applRpymAcBank;
	}
	public void setApplRpymAcNo(String applRpymAcNo) {
		this.applRpymAcNo = applRpymAcNo;
	}
	public void setApplRpymAcNam(String applRpymAcNam) {
		this.applRpymAcNam = applRpymAcNam;
	}
	public void setApplCde(String applCde) {
		this.applCde = applCde;
	}
	public void setTrenchNo(String trenchNo) {
		this.trenchNo = trenchNo;
	}
	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}
	public void setSwitchNo(String switchNo) {
		this.switchNo = switchNo;
	}
	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}
	public void setInputSrc(String inputSrc) {
		this.inputSrc = inputSrc;
	}
	public void setApplDisbSeq(String applDisbSeq) {
		this.applDisbSeq = applDisbSeq;
	}
	public void setApplRpymSeq(String applRpymSeq) {
		this.applRpymSeq = applRpymSeq;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public void setLastChgDt(String lastChgDt) {
		this.lastChgDt = lastChgDt;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	public void setForm(String form) {
		this.form = form;
	}
	public void setFreqUnit(String freqUnit) {
		this.freqUnit = freqUnit;
	}
	public void setGoods(List<Goods> goods) {
		this.goods = goods;
	}
	public void setLcAppRelations(List<LcApptRelation> lcAppRelations) {
		this.lcAppRelations = lcAppRelations;
	}
	public void setAppContact(List<ApptContact> appContact) {
		this.appContact = appContact;
	}
	public void setAsset(List<Asset> asset) {
		this.asset = asset;
	}
	public void setTxType(String txType) {
		this.txType = txType;
	}
	public void setTxCde(String txCde) {
		this.txCde = txCde;
	}
	public void setApplAcctInd(String applAcctInd) {
		this.applAcctInd = applAcctInd;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setMtdTyp(String mtdTyp) {
		this.mtdTyp = mtdTyp;
	}
	public void setLoanInstal(String loanInstal) {
		this.loanInstal = loanInstal;
	}
	public void setApplCircs(String applCircs) {
		this.applCircs = applCircs;
	}
	public void setApplMate(String applMate) {
		this.applMate = applMate;
	}
	public void setTellMatt(String tellMatt) {
		this.tellMatt = tellMatt;
	}
	public void setApptVeriFlag(String apptVeriFlag) {
		this.apptVeriFlag = apptVeriFlag;
	}
	public void setRelaVeriFlag(String relaVeriFlag) {
		this.relaVeriFlag = relaVeriFlag;
	}
	public void setApptVeriDt(String apptVeriDt) {
		this.apptVeriDt = apptVeriDt;
	}
	public void setRelaVeriDt(String relaVeriDt) {
		this.relaVeriDt = relaVeriDt;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setIndivPhoneChk(String indivPhoneChk) {
		this.indivPhoneChk = indivPhoneChk;
	}
	public void setInputDt(String inputDt) {
		this.inputDt = inputDt;
	}
//	/**     
//     *访问<联系人电话核实标志>属性
//     */
// 	@XmlElement(name="JUROR_ID")
//	public String getJurorId() {
//		return jurorId;
//	}
//	public void setJurorId(String jurorId) {
//		this.jurorId = jurorId;
//	}
//	/**     
//     *访问<联系人电话核实标志>属性
//     */
// 	@XmlElement(name="INDIV_OCCUP")
//	public String getIndivOccup() {
//		return indivOccup;
//	}
//	public void setIndivOccup(String indivOccup) {
//		this.indivOccup = indivOccup;
//	}
	
	public String getGoodStr() {
		return goodStr;
	}
	public void setGoodStr(String goodStr) {
		this.goodStr = goodStr;
	}
	
	public String getLcAppRelationStr() {
		return lcAppRelationStr;
	}
	public void setLcAppRelationStr(String lcAppRelationStr) {
		this.lcAppRelationStr = lcAppRelationStr;
	}
	
	public String getAppContactStr() {
		return appContactStr;
	}
	public void setAppContactStr(String appContactStr) {
		this.appContactStr = appContactStr;
	}
	
	public String getAssetStr() {
		return assetStr;
	}
	public void setAssetStr(String assetStr) {
		this.assetStr = assetStr;
	}
	
	public String getIndivEmpType() {
		return indivEmpType;
	}
	public void setIndivEmpType(String indivEmpType) {
		this.indivEmpType = indivEmpType;
	}
	
	public String getIndivEmpNum() {
		return indivEmpNum;
	}
	public void setIndivEmpNum(String indivEmpNum) {
		this.indivEmpNum = indivEmpNum;
	}
	
	public String getIndivIndtryPaper() {
		return indivIndtryPaper;
	}
	public void setIndivIndtryPaper(String indivIndtryPaper) {
		this.indivIndtryPaper = indivIndtryPaper;
	}
	
//	public String getIndivPosition() {
//		return indivPosition;
//	}
//	public void setIndivPosition(String indivPosition) {
//		this.indivPosition = indivPosition;
//	}
	
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * 户籍地址
	 * @return
	 */
	public String getIndivRegZip() {
		return indivRegZip;
	}
	public void setIndivRegZip(String indivRegZip) {
		this.indivRegZip = indivRegZip;
	}
	/**
	 * 现居住地址
	 * @return
	 */
	public String getIndivLiveZip() {
		return indivLiveZip;
	}
	public void setIndivLiveZip(String indivLiveZip) {
		this.indivLiveZip = indivLiveZip;
	}
	
	/**
	 * 推广方式
	 * @return
	 */
	public String getSpreadType() {
		return spreadType;
	}
	public void setSpreadType(String spreadType) {
		this.spreadType = spreadType;
	}
	/**
	 * 进件来源
	 * @return
	 */
	public String getApplSrc() {
		return applSrc;
	}
	public void setApplSrc(String applSrc) {
		this.applSrc = applSrc;
	}
	/**
	 * 说明
	 * @return
	 */
	public String getSpreadRemark() {
		return spreadRemark;
	}
	public void setSpreadRemark(String spreadRemark) {
		this.spreadRemark = spreadRemark;
	}
	/**
	 * 评分
	 * @return
	 */
	public String getScordeGrade() {
		return scordeGrade;
	}
	public void setScordeGrade(String scordeGrade) {
		this.scordeGrade = scordeGrade;
	}
	/**
	 * 单位邮编
	 * @return
	 */
	public String getIndivEmpZip() {
		return indivEmpZip;
	}
	public void setIndivEmpZip(String indivEmpZip) {
		this.indivEmpZip = indivEmpZip;
	}
	
	/**
	 * 现任职位
	 * @return
	 */
	public String getIndivPosition() {
		return indivPosition;
	}
	public void setIndivPosition(String indivPosition) {
		this.indivPosition = indivPosition;
	}
	/**
	 * 职业
	 * @return
	 */
	public String getIndivOccup() {
		return indivOccup;
	}
	public void setIndivOccup(String indivOccup) {
		this.indivOccup = indivOccup;
	}
	/**
	 * 是否有银行信用卡
	 * @return
	 */
	public String getIndivCardInd() {
		return indivCardInd;
	}
	public void setIndivCardInd(String indivCardInd) {
		this.indivCardInd = indivCardInd;
	}
	/**
	 * 发卡银行名称
	 * @return
	 */
	public String getIndivCardBank() {
		return indivCardBank;
	}
	public void setIndivCardBank(String indivCardBank) {
		this.indivCardBank = indivCardBank;
	}
	/**
	 * 单张信用卡最高额度
	 * @return
	 */
	public String getIndivCardAmt() {
		return indivCardAmt;
	}
	public void setIndivCardAmt(String indivCardAmt) {
		this.indivCardAmt = indivCardAmt;
	}
	public String getApplyType() {
		return applyType;
	}
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	public List<MiniDocInfo> getDocList() {
		return docList;
	}
	public void setDocList(List<MiniDocInfo> docList) {
		this.docList = docList;
	}
	 
	
	
}
