package com.yucheng.byxf.mini.miniLoan.fragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.adapter.HuoDongAdapter;
import com.yucheng.byxf.mini.domain.MiniPersonInfo;
import com.yucheng.byxf.mini.easyloan.ui.ImageDispose;
import com.yucheng.byxf.mini.map.ActActivity;
import com.yucheng.byxf.mini.map.DemoApplication;
import com.yucheng.byxf.mini.message.ActivityNoticeResponse;
import com.yucheng.byxf.mini.message.CustInfoResponseResult;
import com.yucheng.byxf.mini.message.MiniConfirmMessage;
import com.yucheng.byxf.mini.message.MiniConfirmResponse;
import com.yucheng.byxf.mini.message.PhotoInfo;
import com.yucheng.byxf.mini.message.Response;
import com.yucheng.byxf.mini.miniLoan.MiniLoanActivity;
import com.yucheng.byxf.mini.ui.HomeActivity;
import com.yucheng.byxf.mini.ui.MiniConfirmInfoActivity;
import com.yucheng.byxf.mini.ui.MyLoginActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.CustomMultiPartEntity;
import com.yucheng.byxf.mini.utils.HttpMultipartPostAddMore;
import com.yucheng.byxf.mini.utils.ImageCompress;
import com.yucheng.byxf.mini.utils.SharedPreferencesUtils;
import com.yucheng.byxf.mini.utils.ViewTools;
import com.yucheng.byxf.mini.utils.CustomMultiPartEntity.ProgressListener;
import com.yucheng.byxf.mini.views.RoundAngleImageView;
import com.yucheng.byxf.mini.xiaojinying.XiaoJinYingQingKuanApplyActivity;
import com.yucheng.byxf.util.LogManager;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 信息确认Fragment
 * 
 */
public class MiniInfoConfirmFragment extends Fragment {
	MiniLoanActivity mini = new MiniLoanActivity();
	private String IMEI = "";
	private String applCde;
	private String applSeq;
	private ImageView back8;
	// 公司规模
	private String[] scale = { "请选择", "99人及以下", "99-500人", "500-2000人",
			"2000人以上" };
	// 单位性质
	private String[] properties = { "请选择", "国家机关/事业单位", "国有企业", "集体企业",
			"中外合资/中外合作/外商独资", "股份制企业", "私营企业", "其他" };
	// 行业性质
	private String[] trade_properties = { "请选择", "个体工商户", "批发/零售", "制造业",
			"房地产", "建筑业", "酒店/旅游/餐饮", "交通运输/仓储/邮政业", "传媒/体育/娱乐", "专业事务所",
			"信息传输/计算机服务/软件业", "生物/医药", "金融", "公共事业", "石油/石化", "教育", "医疗卫生",
			"科研院所", "水利/环境/公共设施管理业", "烟草", "军事机构", "社会团体", "政府机构/公检法", "自由职业",
			"其他" };
	// 所在职位
	private String[] position = { "请选择", "高级管理人员", "一般管理人员", "一般正式员工", "非正式员工",
			"企业负责人", "中层管理人员", "其他", "厅局级以上", "处级", "科级", "一般干部", "退休", " 失业" };

	List<PhotoInfo> photo = new ArrayList<PhotoInfo>();
	long totalSize;
	Context context;
	public static List<PhotoInfo> list;
	public static List<ProgressBar> progressBars = new ArrayList<ProgressBar>();
	public static List<ImageView> imageButtons = new ArrayList<ImageView>();
	public static List<ImageView> flagViews = new ArrayList<ImageView>();
	public static boolean isableClick[];
	public static int buttonId[];
	String photoUrl;
	List<Map<String, Object>> listInfo;
	public static String infoUrl;
	Gson gson;

	int uploadNum = 0;// 正在上传的文件编号

	public static boolean isFlag[];

	public static final int PHOTO_FAIL = 11;
	public static final int PHOTO_SUCCEED = 10;
	public static final int PHOTO_SUCCEED1 = 110;
	public static final int PHOTO_SUCCEEDDATA = 111;

	// public static final int IMAGEBUTTON_ONCLICK = 20;
	// public static final int IMAGEBUTTON_ONCLICK0 = 21;
	// public static final int IMAGEBUTTON_ONCLICKDATA = 22;

	private boolean isCan = true;
	DemoApplication application = null;

	/**
	 * 身份信息采集页面
	 */
	// 姓名
	private TextView et_user_name;
	// 性别
	private TextView et_sex;
	// 英文名
	private TextView et_yingwenming;
	// 身份证号
	private TextView et_identify_cardNum;
	// 出生日期
	private TextView bt_birthday;
	// 证件有效期
	private TextView zjyxq;
	// 户籍地址
	private TextView et_birthPlace;
	// 户籍邮编
	private TextView household_email;
	/**
	 * 基本资料
	 */
	// 婚姻状况
	private TextView rg_marriage_info;
	// 供养人数
	private TextView et_house_hold;
	// 现居住地址
	private TextView live_address;
	// 邮编
	private TextView et_postcode;
	// 住宅性质
	private TextView sp_house_type;
	// 文化性质
	private TextView sp_cultrue;
	// 月供金额
	private TextView ygjinetext;
	private TextView ygjine;
	// 住宅电话
	private TextView et_tel_code;
	// 手机号
	private TextView et_electron_mail;
	// 第二手机号码
	private TextView diershoujihaoma;
	/**
	 * 职业信息
	 */
	// 现单位全称
	private TextView et_com_name;
	// 任职部门
	private TextView et_dpment_name;
	// 本单位工作年限
	private TextView et_worktimes;
	// 总工作年限
	private TextView et_total_worktimes;
	// 单位地址
	private TextView et_com_address;
	// 邮编
	private TextView et_mail_code;
	// 单位电话号码
	private TextView et_com_tel_code;
	// 邮寄地址
	private TextView email_address;
	// 公司规模
	private TextView et_com_scale;
	// 单位性质
	private TextView et_com_properties;
	// 行业性质
	private TextView et_trade_properties;
	// 所在职位
	private TextView et_com_position;
	// 申请人月收入
	private TextView et_salary;
	/**
	 * 联系人信息
	 */
	// 直系亲属联系人
	private TextView contact_name_value;
	// 直系亲属 单位
	private TextView zxqsgzdw;
	// 与申请人关系
	private TextView contact_relation;
	// 住宅电话-号码
	private TextView phone_no_value1;
	// 手机号
	private TextView mobile_no_value1;
	// 非亲属联系人姓名
	private TextView contact_name_value2;
	// 非亲属联系人工作单位
	private TextView zxqsgzdw2;
	// 非亲属联系人与亲属联系人关系
	private TextView contact_relation2;
	// 非亲属联系人住宅电话
	private TextView phone_no_value2;
	// 非亲属联系人手机
	private TextView mobile_no_value2;
	// 第三个姓名
	private TextView contact_name_value3;
	// 第三个工作单位名
	private TextView contact_work_name_value3;
	// 第三个的关系
	private TextView contact_relation3;
	// 第三个住宅电话
	private TextView phone_no_value3;
	// 第三个手机号
	private TextView mobile_no_value3;
	// 第四个姓名
	private TextView contact_name_value4;
	// 第四个工作单位名
	private TextView contact_work_name_value4;
	// 第四个的关系
	private TextView contact_relation4;
	// 第四个住宅电话
	private TextView phone_no_value4;
	// 第四个手机号
	private TextView mobile_no_value4;
	/**
	 * 账单及自动扣款信息
	 */
	// 账单日
	private TextView repay_account;
	// 北京银行借记卡卡号
	private TextView card_no_value;
	// 还款设置
	private TextView repay_setting;
	// 推广员工ID
	private TextView spread_no;
	// 推广员工姓名
	private TextView spread_name;

	private Button mini_loan_Confirm_info_next_button;
	private Dialog dialog;

	private static final Integer RET_CODE = 0;

	/**
	 * 证件拍照
	 */
	// 身份证正面
	private RoundAngleImageView imageView1;
	// 身份证反面
	private RoundAngleImageView imageView2;
	// 手持身份证拍照
	private RoundAngleImageView imageView4;
	private ImageView card_mian;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.minifragment9, container, false);
		ImageView backcustom = (ImageView) view.findViewById(R.id.back8);
		backcustom.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentManager fm = getFragmentManager();
				fm.popBackStack();
			}
		});

		// 身份信息采集
		et_user_name = (TextView) view.findViewById(R.id.zwname);
		et_yingwenming = (TextView) view.findViewById(R.id.engname);
		et_sex = (TextView) view.findViewById(R.id.sex);
		et_identify_cardNum = (TextView) view.findViewById(R.id.idcardno);
		bt_birthday = (TextView) view.findViewById(R.id.birthday);
		zjyxq = (TextView) view.findViewById(R.id.zjyxq);
		et_birthPlace = (TextView) view.findViewById(R.id.address);
		household_email = (TextView) view.findViewById(R.id.hjpost);

		// 基本资料
		rg_marriage_info = (TextView) view.findViewById(R.id.fystate);
		et_house_hold = (TextView) view.findViewById(R.id.gyrs);
		live_address = (TextView) view.findViewById(R.id.liveadd);
		et_postcode = (TextView) view.findViewById(R.id.postno);
		sp_house_type = (TextView) view.findViewById(R.id.zzpro);
		sp_cultrue = (TextView) view.findViewById(R.id.whstate);
		et_tel_code = (TextView) view.findViewById(R.id.zzphone);
		et_electron_mail = (TextView) view.findViewById(R.id.sjphone);
		diershoujihaoma = (TextView) view.findViewById(R.id.desjphone);
		ygjinetext = (TextView) view.findViewById(R.id.ygjinetext);
		ygjine = (TextView) view.findViewById(R.id.ygjine);

		// 职业信息
		et_com_name = (TextView) view.findViewById(R.id.xdwname);
		et_dpment_name = (TextView) view.findViewById(R.id.xdept);
		et_worktimes = (TextView) view.findViewById(R.id.bworkyear);
		et_total_worktimes = (TextView) view.findViewById(R.id.zworkyear);
		et_com_address = (TextView) view.findViewById(R.id.dwadd);
		et_mail_code = (TextView) view.findViewById(R.id.dwpostno);
		et_com_tel_code = (TextView) view.findViewById(R.id.comtel);
		email_address = (TextView) view.findViewById(R.id.yjadd);
		et_com_scale = (TextView) view.findViewById(R.id.dwgm);
		et_com_properties = (TextView) view.findViewById(R.id.dwxz);
		et_trade_properties = (TextView) view.findViewById(R.id.hyxz);
		et_com_position = (TextView) view.findViewById(R.id.szzw);
		et_salary = (TextView) view.findViewById(R.id.sqrysr);

		// 联系人信息
		contact_name_value = (TextView) view.findViewById(R.id.zxqsname);
		zxqsgzdw2 = (TextView) view.findViewById(R.id.zxqsgzdw2);
		zxqsgzdw = (TextView) view.findViewById(R.id.zxqsgzdw);
		contact_relation = (TextView) view.findViewById(R.id.ysqrrel);
		phone_no_value1 = (TextView) view.findViewById(R.id.zzphoneno);
		mobile_no_value1 = (TextView) view.findViewById(R.id.zxlxrphone);
		contact_name_value2 = (TextView) view.findViewById(R.id.fqslxrname);
		contact_relation2 = (TextView) view.findViewById(R.id.feqsrel);
		phone_no_value2 = (TextView) view.findViewById(R.id.fqszzphone);
		mobile_no_value2 = (TextView) view.findViewById(R.id.fqsphone);
		contact_name_value3 = (TextView) view.findViewById(R.id.dslxrname);
		contact_work_name_value3 = (TextView) view.findViewById(R.id.dslxrdept);
		contact_relation3 = (TextView) view.findViewById(R.id.ysqrgzrel);
		phone_no_value3 = (TextView) view.findViewById(R.id.dslxrzzphone);
		mobile_no_value3 = (TextView) view.findViewById(R.id.dslxrphone);
		contact_name_value4 = (TextView) view.findViewById(R.id.dsfqslxrname);
		contact_work_name_value4 = (TextView) view
				.findViewById(R.id.dsilxrdept);
		contact_relation4 = (TextView) view.findViewById(R.id.dsysqrgzrel);
		phone_no_value4 = (TextView) view.findViewById(R.id.dsilxrzzphone);
		mobile_no_value4 = (TextView) view.findViewById(R.id.dsilxrphone);

		// 账单及自动扣款信息
		repay_account = (TextView) view.findViewById(R.id.zdday);
		card_no_value = (TextView) view.findViewById(R.id.bjyhkno);
		repay_setting = (TextView) view.findViewById(R.id.hksetting);
		spread_no = (TextView) view.findViewById(R.id.tgygID);
		// spread_name = (TextView) view.findViewById(R.id.tgygname);

		// 证件拍照
		imageView1 = (RoundAngleImageView) view.findViewById(R.id.imageView1);
		imageView2 = (RoundAngleImageView) view.findViewById(R.id.imageView2);
		imageView4 = (RoundAngleImageView) view.findViewById(R.id.imageView4);

		mini_loan_Confirm_info_next_button = (Button) view
				.findViewById(R.id.mini_loan_Confirm_info_next_button);
		card_mian = (ImageView) view.findViewById(R.id.card_mian);
		bindData();
		mini_loan_Confirm_info_next_button
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new getContactInfo().execute();
					}
				});
		return view;
	}

	private void bindData() {
		// TODO Auto-generated method stub
		SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
				getActivity(), "MiniOne");
		MiniApplyInfo applyinfoOne = preferencesUtils.readObj();
		if (applyinfoOne != null) {
			et_user_name.setText(applyinfoOne.getCustName());
			et_yingwenming.setText(applyinfoOne.getCustSpell());
			et_sex.setText(applyinfoOne.getSex());
			et_identify_cardNum.setText(applyinfoOne.getIdNo());
			bt_birthday.setText(applyinfoOne.getApptStartDate());
			zjyxq.setText(applyinfoOne.getIdTermBgn() + "至"
					+ applyinfoOne.getIdTermEnd());
			et_birthPlace.setText(applyinfoOne.getIndivRegAddr()+applyinfoOne.getHujiXiangQing());
			household_email.setText(applyinfoOne.getIndivRegAddrZip());
		}

		// 基本资料
		preferencesUtils = new SharedPreferencesUtils(getActivity(), "MiniTwo");
		MiniApplyInfo applyinfoTwo = preferencesUtils.readObj();
		if (applyinfoTwo != null) {
			switch (applyinfoTwo.getIndivMarital()) {
			case 10:
				rg_marriage_info.setText("未婚");
				break;

			case 20:
				rg_marriage_info.setText("已婚");
				break;

			case 90:
				rg_marriage_info.setText("其他");
				break;
			}

			et_house_hold.setText(applyinfoTwo.getDependentPersons() + "");
			et_house_hold.setText(applyinfoTwo.getDependentPersons() + "");
			live_address.setText(applyinfoTwo.getIndivLiveAddr()+applyinfoTwo.getHujiXiangQing());
			et_postcode.setText(applyinfoTwo.getIndivLiveZip() + "");
			et_postcode.setText(applyinfoTwo.getIndivLiveZip() + "");
			switch (applyinfoTwo.getIndivLive()) {
			case 0:
				sp_house_type.setText("");
				break;
			case 1:
				sp_house_type.setText("自购无贷款");
				break;
			case 2:
				sp_house_type.setText("自购有贷款");
				break;
			case 3:
				sp_house_type.setText("单位宿舍");
				break;
			case 4:
				sp_house_type.setText("与亲属同住");
				break;
			case 5:
				sp_house_type.setText("租住");
				break;
			case 7:
				sp_house_type.setText("其他");
				break;
			}
			if (applyinfoTwo.getIndivRentAmt().equals("")) {
				ygjinetext.setVisibility(View.GONE);
				ygjine.setVisibility(View.GONE);
			} else {
				ygjinetext.setVisibility(View.VISIBLE);
				ygjine.setVisibility(View.VISIBLE);
				ygjine.setText(applyinfoTwo.getIndivRentAmt());
			}
			switch (applyinfoTwo.getIndivDegree()) {
			case -1:
				sp_cultrue.setText("");
				break;
			case 1:
				sp_cultrue.setText("初中及以下");
				break;
			case 2:
				sp_cultrue.setText("高中或中专");
				break;
			case 3:
				sp_cultrue.setText("大专");
				break;
			case 4:
				sp_cultrue.setText("本科");
				break;
			case 5:
				sp_cultrue.setText("硕士");
				break;
			case 6:
				sp_cultrue.setText("博士");
				break;
			}
			et_tel_code.setText(applyinfoTwo.getIndivFmyZone() + "-"
					+ applyinfoTwo.getIndivFmyTel());
			et_electron_mail.setText(applyinfoTwo.getIndivMobile());
			diershoujihaoma.setText(applyinfoTwo.getIndivMobileAno());
		}

		preferencesUtils = new SharedPreferencesUtils(getActivity(),
				"MiniThree");
		MiniApplyInfo applyinfoThree = preferencesUtils.readObj();
		if (applyinfoThree != null) {

			et_com_name.setText(applyinfoThree.getIndivEmp());
			et_dpment_name.setText(applyinfoThree.getIndivBranch());
			et_worktimes.setText(applyinfoThree.getIndivEmpYrs() + "");
			et_total_worktimes.setText(applyinfoThree.getIndivWorkYrs() + "");
			et_com_address.setText(applyinfoThree.getIndivEmpAddr()+applyinfoThree.getHujiXiangQing());
			et_mail_code.setText(applyinfoThree.getIndivEmpZip());
			et_com_tel_code.setText(applyinfoThree.getIndivEmpZone()
					+ applyinfoThree.getIndivEmpTelNo()
					+ applyinfoThree.getIndivEmpTelSub());
			// 现居地址
			SharedPreferencesUtils pUtils = new SharedPreferencesUtils(
					getActivity(), "MiniTwo");
			final MiniApplyInfo info = pUtils.readObj();
			Log.d("SendTypxxxxxxxxx", applyinfoThree.getSendTyp() + "");
			Log.d("xxx", applyinfoThree.getSendTyp() + ""); // 如果不选择 默认为01 单位地址
															// **00家庭地址
			Log.d("xxxxx", applyinfoThree.getIndivEmpAddr() + "=====");
			switch ((int) applyinfoThree.getSendTyp()) {
			case 0:
				email_address.setText(info.getIndivLiveAddr());
				break;

			case 1:
				email_address.setText(applyinfoThree.getIndivEmpAddr());
				break;
			}
			et_com_scale.setText(scale[Integer.parseInt(applyinfoThree
					.getGsguimo())]);
			et_com_properties.setText(properties[Integer
					.parseInt(applyinfoThree.getDwxingzhi())]);
			et_trade_properties.setText(trade_properties[Integer
					.parseInt(applyinfoThree.getHyxingzhi())]);
			et_com_position.setText(position[Integer.parseInt(applyinfoThree
					.getSzzhiwei())]);
			et_salary.setText(applyinfoThree.getIndivMthInc());
		}

		preferencesUtils = new SharedPreferencesUtils(getActivity(), "MiniFour");
		MiniApplyInfo applyinfoFour = preferencesUtils.readObj();
		if (applyinfoFour != null) {
			contact_name_value.setText(applyinfoFour.getIndivRelName());
			zxqsgzdw.setText(applyinfoFour.getDanweiOne());
			zxqsgzdw2.setText(applyinfoFour.getDanweiTwo());
			Log.d("xxx", "直系亲属" + applyinfoFour.getIndivRelation());
			switch ((int) applyinfoFour.getIndivRelation()) {
			case 1:
				contact_relation.setText("配偶");
				break;

			case 2:
				contact_relation.setText("父母");
				break;

			case 3:
				contact_relation.setText("子女");
				break;

			case -1:
				contact_relation.setText("");
				break;

			case 9:
				contact_relation.setText("兄弟姐妹");
				break;
			}
			phone_no_value1.setText(applyinfoFour.getIndivRelZone()
					+ applyinfoFour.getIndivRelPhone());
			mobile_no_value1.setText(applyinfoFour.getIndivRelMobile());
			contact_name_value2.setText(applyinfoFour.getIndivOthName());
			Log.d("xxxx", applyinfoFour.getIndivOthRel() + "========");
			switch ((int) applyinfoFour.getIndivOthRel()) {
			case 1:
				contact_relation2.setText("配偶");
				break;

			case 2:
				contact_relation2.setText("父母");
				break;

			case 3:
				contact_relation2.setText("子女");
				break;

			case 4:
				contact_relation2.setText("其他血亲");
				break;

			case 5:
				contact_relation2.setText("其他姻亲");
				break;
			case 6:
				contact_relation2.setText("同事");
				break;
			case 7:
				contact_relation2.setText("合伙人");
				break;
			case 8:
				contact_relation2.setText("其他关系");
				break;
			case 9:
				contact_relation2.setText("兄弟姐妹");
				break;
			case 10:
				contact_relation2.setText("同学");
				break;
			case 11:
				contact_relation2.setText("朋友");
				break;
			default:
				contact_relation2.setText("");
				break;
			}
			phone_no_value2.setText(applyinfoFour.getIndivOthZone()
					+ applyinfoFour.getIndivOthPhone());
			mobile_no_value2.setText(applyinfoFour.getIndivOthMobile());
			contact_name_value3.setText(applyinfoFour.getIndivThiName());
			contact_work_name_value3.setText(applyinfoFour.getIndivThiEmp());
			switch ((int) applyinfoFour.getIndivThiRelation()) {
			case 1:
				contact_relation3.setText("配偶");
				break;

			case 2:
				contact_relation3.setText("父母");
				break;

			case 3:
				contact_relation3.setText("子女");
				break;

			case 4:
				contact_relation3.setText("其他血亲");
				break;

			case 5:
				contact_relation3.setText("其他姻亲");
				break;
			case 6:
				contact_relation3.setText("同事");
				break;
			case 7:
				contact_relation3.setText("合伙人");
				break;
			case 8:
				contact_relation3.setText("其他关系");
				break;
			case 9:
				contact_relation3.setText("兄弟姐妹");
				break;
			case 10:
				contact_relation3.setText("同学");
				break;
			case 11:
				contact_relation3.setText("朋友");
				break;
			default:
				contact_relation3.setText("");
				break;
			}
			phone_no_value3.setText(applyinfoFour.getIndivThiZone()
					+ applyinfoFour.getIndivThiPhone());
			mobile_no_value3.setText(applyinfoFour.getIndivThiMobile());
			contact_name_value4.setText(applyinfoFour.getIndivFouName());
			contact_work_name_value4.setText(applyinfoFour.getIndivFouEmp());
			// contact_relation4.setText(applyinfoFour.getIndivThiFouation() +
			// "");
			switch ((int) applyinfoFour.getIndivThiFouation()) {
			case 1:
				contact_relation4.setText("配偶");
				break;

			case 2:
				contact_relation4.setText("父母");
				break;

			case 3:
				contact_relation4.setText("子女");
				break;

			case 4:
				contact_relation4.setText("其他血亲");
				break;

			case 5:
				contact_relation4.setText("其他姻亲");
				break;
			case 6:
				contact_relation4.setText("同事");
				break;
			case 7:
				contact_relation4.setText("合伙人");
				break;
			case 8:
				contact_relation4.setText("其他关系");
				break;
			case 9:
				contact_relation4.setText("兄弟姐妹");
				break;
			case 10:
				contact_relation4.setText("同学");
				break;
			case 11:
				contact_relation4.setText("朋友");
				break;
			default:
				contact_relation4.setText("");
				break;
			}
			phone_no_value4.setText(applyinfoFour.getIndivFouZone()
					+ applyinfoFour.getIndivFouPhone());
			mobile_no_value4.setText(applyinfoFour.getIndivFouMobile());
		}

		preferencesUtils = new SharedPreferencesUtils(getActivity(), "MiniFive");
		MiniApplyInfo applyinfoFive = preferencesUtils.readObj();
		if (applyinfoFive != null) {
			repay_account.setText(applyinfoFive.getAtpyDay() + "");
			card_no_value.setText(applyinfoFive.getJiejikaCard());
			if (applyinfoFive.getRpymOption().equals("SUB")) {
				repay_setting.setText("最低还款");
			} else if (applyinfoFive.getRpymOption().equals("ALL")) {
				repay_setting.setText("全额还款");
			}

			spread_no.setText(applyinfoFive.getTuiguangId());
			// spread_name.setText(applyinfoFive.getTuiguangName());
		}
		preferencesUtils = new SharedPreferencesUtils(getActivity(), "MiniSix");
		MiniApplyInfo applyinfoSix = preferencesUtils.readObj();
		if (applyinfoSix != null) {
			if (applyinfoSix.getCardxuanze().equals("A")) {
				card_mian.setBackgroundResource(R.drawable.os);
			} else {
				card_mian.setBackgroundResource(R.drawable.ts);
			}
		}

		// 证件拍照
		// String path1 = ImageDispose.getPath("byxfmini", "001");
		// String path2 = ImageDispose.getPath("byxfmini", "002");
		// ImageCompress compress = new ImageCompress();
		// getBitmap1(compress, path1, path1, imageView1);
		// getBitmap1(compress, path2, path2, imageView2);
		String path1 = ImageDispose.getPath("byxfmini", "001");
		String path2 = ImageDispose.getPath("byxfmini", "002");
		String path4 = ImageDispose.getPath("byxfmini", "004");
		ImageCompress compress = new ImageCompress();

		setImageViewBitmap(path1, imageView1);
		setImageViewBitmap(path2, imageView2);
		setImageViewBitmap(path4, imageView4);
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	private void addPhoto() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		list.add(map);
		List<PhotoInfo> photo = new ArrayList<PhotoInfo>();
		photo.add(new PhotoInfo(applCde + "01", applCde, "SFZZM", "LCDOC",
				applCde + "01" + ".jpg", "999", MiniApplyInfo.MinicomPhoto1Path));
		photo.add(new PhotoInfo(applCde + "02", applCde, "SFZFM", "LCDOC",
				applCde + "02" + ".jpg", "999", MiniApplyInfo.MinicomPhoto2Path));
		photo.add(new PhotoInfo(applCde + "03", applCde, "KHZP", "LCDOC",
				applCde + "03" + ".jpg", "999", MiniApplyInfo.MinicomPhoto4Path));
		new HttpMultipartPostAddMore(getActivity(), photo,
				ContantsAddress.DOC_UPLOAD, applCde);
	}

	// 申请接口
	class getContactInfo extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper.getInstance(getActivity());
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
					getActivity(), "MiniOne");
			MiniApplyInfo applyinfoOne = preferencesUtils.readObj();

			preferencesUtils = new SharedPreferencesUtils(getActivity(),
					"MiniTwo");
			MiniApplyInfo applyinfoTwo = preferencesUtils.readObj();

			// ************第一页信息**************
			arg.add(new BasicNameValuePair("custName", et_user_name.getText()
					.toString()));
			arg.add(new BasicNameValuePair("indivSex", et_sex.getText()
					.toString()));
			arg.add(new BasicNameValuePair("custSpell", et_yingwenming
					.getText().toString()));
			arg.add(new BasicNameValuePair("idType", "20"));
			arg.add(new BasicNameValuePair("apptStartDate", bt_birthday
					.getText().toString()));
			arg.add(new BasicNameValuePair("idNo", et_identify_cardNum
					.getText().toString()));
			arg.add(new BasicNameValuePair("idTermBgn", applyinfoOne
					.getIdTermBgn()));
			arg.add(new BasicNameValuePair("idTermEnd", applyinfoOne
					.getIdTermEnd()));
			arg.add(new BasicNameValuePair("indivRegAddr", et_birthPlace
					.getText().toString()));

			// ****************第二页信息**************
			arg.add(new BasicNameValuePair("indivMarital", applyinfoTwo
					.getIndivMarital() + ""));
			arg.add(new BasicNameValuePair("indivMobile", et_electron_mail
					.getText().toString()));
			if (!diershoujihaoma.getText().equals("")) {
				arg.add(new BasicNameValuePair("indivMobileAno",
						diershoujihaoma.getText().toString()));
			}
			arg.add(new BasicNameValuePair("indivFmyZone", applyinfoTwo
					.getIndivFmyZone()));
			arg.add(new BasicNameValuePair("indivFmyTel", applyinfoTwo
					.getIndivFmyTel()));
			arg.add(new BasicNameValuePair("indivLiveAddr", live_address
					.getText().toString()));
			arg.add(new BasicNameValuePair("indivLiveZip", et_postcode
					.getText().toString()));
			arg.add(new BasicNameValuePair("indivLive", applyinfoTwo
					.getIndivLive() + ""));
			String zujin = applyinfoTwo.getIndivLive() + "";
			if (!zujin.equals("")) {
				arg.add(new BasicNameValuePair("indivRentAmt", ygjine.getText()
						.toString()));
			}
			// ********************第三页*******************
			preferencesUtils = new SharedPreferencesUtils(getActivity(),
					"MiniThree");
			MiniApplyInfo applyinfoThree = preferencesUtils.readObj();
			arg.add(new BasicNameValuePair("indivEmp", applyinfoThree
					.getIndivEmp()));
			arg.add(new BasicNameValuePair("indivBranch", applyinfoThree
					.getIndivBranch()));
			arg.add(new BasicNameValuePair("indivEmpZone", applyinfoThree
					.getIndivEmpZone()));
			arg.add(new BasicNameValuePair("indivEmpTelNo", applyinfoThree
					.getIndivEmpTelNo()));
			arg.add(new BasicNameValuePair("indivEmpTelSub", applyinfoThree
					.getIndivEmpTelSub()));
			arg.add(new BasicNameValuePair("indivEmpAddr", applyinfoThree
					.getIndivEmpAddr()+applyinfoThree.getHujiXiangQing()));
			arg.add(new BasicNameValuePair("indivEmpZip", applyinfoThree
					.getIndivEmpZip()));
			arg.add(new BasicNameValuePair("indivEmpYrs", applyinfoThree
					.getIndivEmpYrs() + ""));
			arg.add(new BasicNameValuePair("indivWorkYrs", applyinfoThree
					.getIndivWorkYrs() + ""));
			arg.add(new BasicNameValuePair("indivMthInc", applyinfoThree
					.getIndivMthInc()));

			// 字典：1 高级管理人员 11厅局级以上 12处级
			// 13科级 14一般干部 2一般管理人员
			// 3一般正式员工 4非正式员工 5无 6企业负责人
			// 7中层管理人员 9 15退休 16失业

			String PositionPost = "";
			String positionValue = "";
			for (int i = 0; i < position.length; i++) {
				positionValue = position[i];
				if("高级管理人员".endsWith(positionValue)){
					PositionPost = "1";
				}else if("厅局级以上".equals(positionValue)){
					PositionPost = "11";
				}else if("处级".equals(positionValue)){
					PositionPost = "12";
				}else if("科级".equals(positionValue)){
					PositionPost = "13";
				}else if("一般干部".equals(positionValue)){
					PositionPost = "14";
				}else if("一般管理人员".equals(positionValue)){
					PositionPost = "2";
				}else if("一般正式员工".equals(positionValue)){
					PositionPost = "3";
				}else if("企业负责人".equals(positionValue)){
					PositionPost = "6";
				}else if("其他".equals(positionValue)){
					PositionPost = "9";
				}else if("退休".equals(positionValue)){
					PositionPost = "15";
				}else if("失业".equals(positionValue)){
					PositionPost = "16";
				}else if("中层管理人员".equals(positionValue)){
					PositionPost = "7";
				}else if("非正式员工".equals(positionValue)){
					PositionPost = "4";
				}
			}

			arg.add(new BasicNameValuePair("indivPosition", PositionPost));

			// ****************第五页***********************
			preferencesUtils = new SharedPreferencesUtils(getActivity(),
					"MiniFive");
			MiniApplyInfo applyinfoFive = preferencesUtils.readObj();
			if (applyinfoFive != null) {
				arg.add(new BasicNameValuePair("applRpymAcBank", "BOB"));
				arg.add(new BasicNameValuePair("applRpymAcNo", card_no_value
						.getText().toString()));
				// System.out.println(applyinfoThree
				// .getApplRpymAcNo());
				// arg.add(new BasicNameValuePair("applRpymAcNam", "20"));
				arg.add(new BasicNameValuePair("rpymOption", repay_setting
						.getText().toString()));
				// *******************************账单寄送地址有问题 取不到值
				// 暂时写死**************************
				arg.add(new BasicNameValuePair("sendTyp", "00"));
			}
			// ************************第四页 联系人*******************
			preferencesUtils = new SharedPreferencesUtils(getActivity(),
					"MiniFour");
			MiniApplyInfo applyinfoFour = preferencesUtils.readObj();
			if (applyinfoFour != null) {
				arg.add(new BasicNameValuePair("indivRelName", applyinfoFour
						.getIndivRelName()));
				arg.add(new BasicNameValuePair("indivRelation", applyinfoFour
						.getIndivRelation() + ""));
				// arg.add(new BasicNameValuePair("indivRelZone",
				// applyinfoFour.getIndivRelZone()));
				// arg.add(new
				// BasicNameValuePair("indivRelPhone",applyinfoFour.getIndivRelPhone()));
				arg.add(new BasicNameValuePair("indivRelMobile", applyinfoFour
						.getIndivRelMobile()));
				arg.add(new BasicNameValuePair("indivOthName", applyinfoFour
						.getIndivOthName()));
				arg.add(new BasicNameValuePair("indivOthRel", applyinfoFour
						.getIndivOthRel() + ""));
				// arg.add(new BasicNameValuePair("indivOthPhone",
				// applyinfoFour.getIndivOthPhone()));
				arg.add(new BasicNameValuePair("indivOthZone", applyinfoFour
						.getIndivOthZone()));
				arg.add(new BasicNameValuePair("indivOthMobile", applyinfoFour
						.getIndivOthMobile()));
				arg.add(new BasicNameValuePair(
						"lcAppRelations[0].indivRelName", "20"));
				arg.add(new BasicNameValuePair("lcAppRelations[0].indivRelEmp",
						applyinfoFour.getIndivThiName()));
				arg.add(new BasicNameValuePair(
						"lcAppRelations[0].indivRelation", "03"));
				arg.add(new BasicNameValuePair(
						"lcAppRelations[0].indivRelZone", applyinfoFour
								.getIndivThiZone()));
				arg.add(new BasicNameValuePair(
						"lcAppRelations[0].indivRelPhone", applyinfoFour
								.getIndivThiPhone()));
				arg.add(new BasicNameValuePair(
						"lcAppRelations[0].indivRelMobile", applyinfoFour
								.getIndivThiMobile()));
				arg.add(new BasicNameValuePair("lcAppRelations[0].indivRelTyp",
						applyinfoFour.getIndivThiTyp()));
				arg.add(new BasicNameValuePair(
						"lcAppRelations[1].indivRelName", applyinfoFour
								.getIndivFouName()));
				arg.add(new BasicNameValuePair("lcAppRelations[1].indivRelEmp",
						applyinfoFour.getIndivFouEmp() + ""));
				arg.add(new BasicNameValuePair(
						"lcAppRelations[1].indivRelation", "04"));
				arg.add(new BasicNameValuePair(
						" lcAppRelations[1].indivRelZone ", applyinfoFour
								.getIndivFmyZone()));
				// System.out.println(applyinfoFour
				// .getIndivFmyZone());
				arg.add(new BasicNameValuePair(
						"lcAppRelations[1].indivRelPhone", applyinfoFour
								.getIndivFouPhone()));
				arg.add(new BasicNameValuePair(
						"lcAppRelations[1].indivRelMobile", applyinfoFour
								.getIndivFouMobile()));
				arg.add(new BasicNameValuePair(
						" lcAppRelations[1].indivRelTyp ", applyinfoFour
								.getIndivFouTyp()));
				// System.out.println(applyinfoFour
				// .getIndivFouTyp());

				arg.add(new BasicNameValuePair("operator", spread_no.getText()
						.toString()));
				// arg.add(new BasicNameValuePair("layout", "BOB"));// 卡面类型
				// arg.add(new BasicNameValuePair("location", "BOB")); // 交易地址
				// arg.add(new BasicNameValuePair("longitude", "BOB"));// 经度
				// arg.add(new BasicNameValuePair("latitude", "BOB"));// 纬度

				// ****************
				// 获取不到IMEI*****************暂时写死********************
				// 设备ID deviceId
				arg.add(new BasicNameValuePair("deviceId", "867569022607101"));
				// System.out.println("IMEI++++"+telephonyManager.getDeviceId(););
				// 设备型号 SCH-I959
				arg.add(new BasicNameValuePair("deviceType",
						android.os.Build.MODEL));
				// 设备名称 deviceName
				arg.add(new BasicNameValuePair("deviceName",
						android.os.Build.PRODUCT));
				// 设备制造商 mfrs sansung
				arg.add(new BasicNameValuePair("mfrs",
						android.os.Build.MANUFACTURER));
				// 操作系统名称 osName iphone/android/miui
				arg.add(new BasicNameValuePair("osName",
						android.os.Build.VERSION.INCREMENTAL));
				// 操作系统版本 osVersion 4.2.2
				arg.add(new BasicNameValuePair("osVersion",
						android.os.Build.VERSION.RELEASE));
				// 应用类型 appName iphone/android
				arg.add(new BasicNameValuePair("appName", "android"));
				// 应用版本 appVersion 1.01
				// arg.add(new BasicNameValuePair("appVersion",
				// getVerName(getActivity())));

			}
			return httpHelper.post(ContantsAddress.APPLAY_MINI, arg,
					MiniConfirmResponse.class);
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			showProgressDialog();
		}

		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			MiniConfirmResponse response = (MiniConfirmResponse) result;
			if (response != null) {
				if (RET_CODE == response.getCode()) {
					// 提交成功 上传身份证图片
					System.out.println("这事申请编号"
							+ response.getResult().getApplCde());
					MiniConfirmMessage message = response.getResult();
					applCde = message.getApplCde();// 申请编号
					// *********************在这里提交图片*******************
					addPhoto();
					// new uploadVideoFile().execute();
				} else {
					System.out.println(response.getMsg());
					// 获取申请编码失败！
					Toast.makeText(getActivity(),
							"获取申请编码失败！" + response.getMsg(), Toast.LENGTH_LONG)
							.show();
				}
			} else {
				Toast.makeText(getActivity(),
						getResources().getString(R.string.no_network),
						Toast.LENGTH_LONG).show();
			}
		}
	}

	public void showProgressDialog() {
		// TODO Auto-generated method stub
		if (null != dialog && dialog.isShowing()) {
			dialog.dismiss();
		}
	}

	private void getProInfo() {
		TelephonyManager telephonyManager = (TelephonyManager) this
				.getSystemService(Context.TELEPHONY_SERVICE);
		IMEI = telephonyManager.getDeviceId();
		PackageManager packageManager = mini.getPackageManager();
		PackageInfo packInfo;
	}

	private TelephonyManager getSystemService(String telephonyService) {
		return null;
	}

	public void showEditDialog(View view, String str) {
		DialogFragment myFragment = NameDialogFragment.newInstance(str);
		myFragment.show(getFragmentManager(), "EditNameDialog");
	}

	/**
	 * 保存图片为JPEG
	 * 
	 * @param bitmap
	 * @param path
	 */
	public String saveJPGE_After(Bitmap bitmap, String path) {
		File file = new File(path);
		try {
			FileOutputStream out = new FileOutputStream(file);
			if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)) {
				out.flush();
				out.close();
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	// 读取照片
	private void getBitmap1(ImageCompress compress, String path1,
			String comPath, ImageView imageView) {
		imageView.setImageBitmap(null);
		Bitmap bitmap = compress.bitmapComp(path1);
		saveJPGE_After(bitmap, comPath);
		Bitmap newBitmap = ViewTools.cutCreateBitMap(bitmap);
		imageView.setImageBitmap(newBitmap);
		if (bitmap != null && !bitmap.isRecycled()) {
			bitmap.recycle();
			bitmap = null;
			System.gc();
		}
	}

	private void setImageViewBitmap(String path, ImageView imageView) {
		Bitmap bitmap = ViewTools.getBitMap(path, 0);
		imageView.setImageBitmap(bitmap);
	}

}
