package com.yucheng.byxf.mini.utils;

public class ContantsAddress {

	/**
	 * 通过修改此参数来进行生产和测试环境的切换 true:生产 false:测试
	 */
	public static boolean isProductionEnvironment = true;

	// ture-->v1 false--->v3
	public static boolean isv1_v3 = false;

	public static String ip;

	// 后台管理系统

	// public static String ip = "https://etp.bobcfc.com:7788/eloan-services/";

	// public static String ip = "http://10.0.248.108:8080/eloan-services/";

	public static String APPLAY_RALEX = "";

	public static String GET_SEQ = "";

	public static String LOAD_APPLY_LOAN = "";

	public static String APPLY_QUICK_LOAN = "";

	static {
		if (isProductionEnvironment) {
			ip = "https://etp.bobcfc.com:7788/eloan-services/";
		} else {
			// ip = "http://192.168.1.124:8080/eloan-services/";
//			ip = "http://10.160.7.220:9080/eloan-services/";
			ip = "http://10.160.7.230:9080/eloan-services/";
			// ip = "http://192.168.1.129:8080/eloan-services/";
		}
	}

	public static String anon = "anon/";

	public static String v1 = "v1/";

	public static String v2 = "v2/";

	public static String v3 = "v3/";

	public static String v4 = "v4/";

	static {
		if (isv1_v3) {
			System.out.println("进入v1=======》》》》》》》");
			// v1
			APPLAY_RALEX = ip + v1 + "apply-easy-loan";
			GET_SEQ = ip + v1 + "get-seq";
			/** 获取贷款打回数据 **/
			LOAD_APPLY_LOAN = ip + v1 + "load_apply-loan";
			APPLY_QUICK_LOAN = ip + v1 + "apply-quick-loan";
		} else {
			// v3
			System.out.println("进入v3=======》》》》》》》");
			APPLAY_RALEX = ip + v3 + "apply-easy-loan";
			GET_SEQ = ip + v3 + "get-sequence";
			APPLY_QUICK_LOAN = ip + v3 + "apply-quick-loan";
			LOAD_APPLY_LOAN = ip + v3 + "load_apply-loan";

		}
	}

	// 用户登录
	public static String LOGIN = ip + v1 + anon + "login";

	// 用户注销
	public static String LOGOUT = ip + v1 + anon + "logout";

	// 检查用户名合法性
	public static String CHECK_USERID = ip + v1 + anon + "check-userid";

	// 用户注册
	public static String CUST_REG = ip + v1 + anon + "cust-reg";

	// 发送验证码
	public static String SEND_VERIFY_CODE = ip + v1 + anon + "send-verify-code";

	// 确认注册信息
	public static String CONFIRM_REG_INFO = ip + v1 + anon + "confirm-reg-info";

	// Mini卡申请
	public static String APPLAY_MINI = ip + v1 + "apply-mini";

	// 我的账单
	public static String MY_BILL = ip + v1 + "my-bill";

	// 客户信息查询
	public static String CUST_INFO = ip + v1 + "cust-info";

	// 附件上传
	public static String DOC_UPLOAD = ip + v1 + "doc-upload";

	// 审批状态查询
	public static String MIMI_PROGRESS = ip + v1 + "mini-progress";

	// 审批状态查询
	public static String ELOAN_PROGRESS = ip + v1 + "easy-loan-progress";

	// 查看贷款核准确认书
	public static String ELOAN_CONFIRMATION = ip + v1
			+ "query-loan-confirmation";

	// 确认贷款核准确认书
	public static String ELOAN_CONFIRM_CONFIRMATION = ip + v1
			+ "confirm-loan-confirmation";

	// 查询卡号
	public static String GET_CARDNO = ip + v1 + "get-cardno";

	// 网点信息
	public static String GET_NetInfo = ip + v1 + "anon/branch-location";

	// 网点信息 省
	public static String GET_Net_Province = ip + v1
			+ "anon/branch-location-province";

	// 网点信息 市
	public static String GET_Net_City = ip + v1 + "anon/branch-location-city";

	// 一般消费贷款提交意向
	public static String GENERAL_CUSTOME_LOAN = ip + v1 + "loan-intent";

	// 一般消费贷款查询意向
	public static String GENERAL_CUSTOME_LOAN_QUERY = ip + v1
			+ "query-loan-intent";

	// 老客户
	public static String OldCust = ip + v1 + "eloan-old-customer";

	/********************************************************/
	// MINI老客户
	public static String MiniOldCust = ip + v1 + "mini-old-customer";
	/********************************************************/

	// 检查APK
	public static String PERSONAL_APK = ip + v1 + anon + "personal-apk";

	// 检查网络状态
	public static String CONNECT_TEST = ip + v1 + anon + "connect-test";

	// Mini卡激活
	public static String MINI_CARD_ACTIVATE = ip + v1 + "activate-card";

	// 测试密钥
	public static String CARD_ACTIVATE_KEY = "A01E2BA349A867025D404F5D17F0E3DB";

	// 轻松贷还款计划列表
	public static String RELAX_LOAN_BILL_LIST = ip + v1 + "payment-list";

	// 轻松贷还款计划明细
	public static String RELAX_LONE_BILL_DETAILS = ip + v1 + "payment-detail";

	// 贷款申请条件验证
	public static String RELAX_APPLY_VALIDATOR = ip + v2
			+ "apply-easy-loan-validator";

	/** 贷款信息进度查询 **/
	public static String EASY_LOAN_PROGRESS = ip + v2 + "easy-loan-progress";

	/** 还款计划借据信息列表 **/
	public static String PAYMENT_LIST = ip + v2 + "payment-list";

	public static String VIDEO_UPLOAD = ip + v1 + "video-upload";

	public static String APPLY_QUICK_LOAN_VALIDATOR = ip + v1
			+ "apply-quick-loan-validator";

	/** 获取图片验证码 **/
	public static String VERIFY_CODE = ip + "verifyCode.jpg";

	public static String GET_PHONE = ip + v1 + anon + "get-phone";

	public static String MODIFY_PWD = ip + v1 + anon + "modify-pwd";

	public static String SEND_MODIFY_PWD_CODE = ip + v1 + anon
			+ "send-modify-pwd-verify-code";

	// 极速贷短信确认
	public static String SEND_MODIFY_PWD_CODE_KEY = ip + v1 + anon
			+ "send-loan-apply-verify-code";

	// 极速贷短信获取欧
	public static String SEND_KEY_MSG = ip + v1 + "get-config";

	public static String VALID_PHONE_VERIFY_CODE = ip + v1 + anon
			+ "valid-phone-verify-code";

	// 确认验证码
	public static String VALID_PHONE_VERIFYKEY_CODE = ip + v1 + anon
			+ "valid-phone-verify-code";

	public static String BANNER = ip + v2 + anon + "banner";

	// 还款查询
	public static String ADVANCEREPAYMENT = ip + v3 + "active-payment-list";

	// 还款申请查询
	public static String ADVANCEREPAYMENTAPPLY = ip + v3
			+ "active-payment-query";

	// 还款提交页面
	public static String ADVANCEREPAYMENTSUBMIT = ip + v3
			+ "active-payment-apply";

	// 个人信息 提交
	public static String MESSAGEINFOSUBMIT = ip + v1 + "anon/modify-reg";

	// 意见反馈query
	public static String MSGSUGGESTIONQUERY = ip + v1 + "idea/query";
	// 意见反馈add
	public static String MSGSUGGESTIONINFO = ip + v1 + "idea/add";

	// 轻松贷贷款条款
	public static String EASYBOOK = ip + "upload/article/easyloan.html";

	// 极速贷贷款条款
	public static String QUICKBOOK = ip + "upload/article/quickloan.html";

	/***********************************************************/
	// Mini贷款条款
	public static String MINIBOOK = ip + "upload/article/mini.html";
	/***********************************************************/

	// 极速贷诗句
	public static String GETPOETRY = ip + v1 + "get-poetry";

	// 已出账单
	public static String MINI_IN = ip + v1 + "mini-in-print-bill";

	// mini申请
	public static String MINIAPPLY = ip + v1 + "mini-progress";

	// 未出账单
	public static String MINI_OUT = ip + v1 + "mini-out-print-bill";

	// mini已出账单明细
	public static String MINI_BILL = ip + v1 + "mini-bill-deal-detail";

	// 通用验证码 获取
	public static String VertifyCodellKey = ip + v1 + "anon/send-verify-cde";

	/********************************************************************/
	// 还款计划查询
	public static String PAYMENT_PLAN = ip + v3 + "payment-plan-list";
	// 查询用户其他消息
	public static String USER_OTHER_MSG = ip + v1 + "user-other-msg";
	/********************************************************************/

	// 消金银 查询
	public static String XianjinyingChaXun = ip + "v4/card-msg-query";

	// 消金银 获取卡号
	public static String XianjinyinghuoquKaHao = ip + "v4/valid-virtual-card";

	// 消金银 是否激活
	public static String Xianjinyingshifuojihuo = ip + "v4/activate-valid";

	// 消金银 是否设置查询密码
	public static String Xiaojinyingischaxunmima = ip + "v4/pwd-status-query";

	// 循环时贷 请款申请验证码
	public static String Qingkuan_xiaojinying = ip + v1 + anon
			+ "send-verify-cde";

	// 循环时贷 请款
	public static String qingkuan = ip + v4 + "use-apply";

	// 循环时贷 还款
	public static String Huankuan_xiaojinying = ip + v4 + "active-payment";

	// 循环时贷 变更银行卡
	public static String changebank = ip + v4 + "modify-bank-card";

	// 循环时贷 Setkey
	public static String XiaoJingYingsetkey = ip + v4 + "set-pwd";

	// 地址获取v4/get-address
	public static String XiaoJingYingdizhiHuoQu = ip + v4 + "get-address";

	// 地址tijaov4
	public static String XiaoJingYingdizhiTiJiao = ip + v4 + "modify-address";

	// 联系人
	public static String XiaoLianXiRen = ip + v4 + "get-relation";

	// 联系人上传
	public static String XiaoLianXiRenUp = ip + v4 + "modify-relation";

	// 获取信息
	public static String XiaoBasicInfo = ip + v4 + "get-basic-msg";

	// 上传获取信息
	public static String XiaoUpBasicInfo = ip + v4 + "modify-basic-msg";

	// 验密码
	public static String XiaoYaZhengKeyInfo = ip + v4 + "pwd-valid";

	// 循环时贷短信验证码验证
	public static String messageYanzheng = ip + v1 + anon
			+ "valid-phone-verify-code";

	// 循环时贷修改查询密码
	public static String xiugaimima = ip + v4 + "modify-pwd";

	// 循环时贷销户
	public static String xiaohu = ip + v4 + "destroy-user";

	// 循环时贷-mini 按时间查询账单明细
	public static String shijianzhangdan = ip + v1 + "mini-in-print-bill";

	// 消金盈-mini 未出账单明细
	public static String shijianzhangdan_weichu = ip + v1
			+ "mini-out-print-bill";

	// 循环时贷-消金盈 日间交易查询
	public static String shijianzhangdan_rijianjiaoyi = ip + v4 + "deal-detail";

	// 消金盈激活
	public static String jihuo = ip + v4 + "card-active";

	// 急速贷轻松贷打回信息
	public static String ChoiceGetInfo = ip + "v3/exam-and-appr-schedule-list";

	// 活动公告
	public static String ACTIVITY_MENU = ip + v1 + anon + "activity-notice";

	// 上传文件到ftp
	public static String UPLOAD_VIDEO_FILE = ip + v1 + "upload-video-file";

}