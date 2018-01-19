package com.yucheng.byxf.mini.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.domain.MiniPersonInfo;
import com.yucheng.byxf.mini.easyloan.ui.RelaxedLoanNineCommitInfo;
import com.yucheng.byxf.mini.message.MiniConfirmMessage;
import com.yucheng.byxf.mini.message.MiniConfirmResponse;
import com.yucheng.byxf.mini.message.PhotoInfo;
import com.yucheng.byxf.mini.utils.CnToSpell;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.HttpMultipartPostAddMore;
import com.yucheng.byxf.mini.utils.ViewTools;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MiniConfirmInfoActivity extends BaseActivity implements
		OnClickListener {

	private Button next_button;
	private TextView confirm_text1, confirm_text2, confirm_text3,
			confirm_text4, confirm_text5;
	private String card_num;
	private ImageView imageView1;
	private ImageView imageView2;
	private ImageView imageView5;
	private ImageView imageView3;
	private ImageView imageView4;
	private ImageView iv_menu_common;

	private static final Integer RET_CODE = 0;
	private String applCde;
	private String applSeq;
	private LinearLayout layout;

	private ImageView card;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mini_confirm_info);
		initView();
	}

	private void initView() {
		next_button = (Button) findViewById(R.id.next_button);
		next_button.setOnClickListener(this);
		iv_menu_common = (ImageView) findViewById(R.id.iv_menu_common);
		iv_menu_common.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		confirm_text1 = (TextView) findViewById(R.id.confirm_text1);
		confirm_text2 = (TextView) findViewById(R.id.confirm_text2);
		confirm_text3 = (TextView) findViewById(R.id.confirm_text3);
		confirm_text4 = (TextView) findViewById(R.id.confirm_text4);
		confirm_text5 = (TextView) findViewById(R.id.confirm_text5);
		/* 身份信息1 */
		Spanned text1 = Html.fromHtml("<p>姓名：" + MiniPersonInfo.name + "</p>"
				+ "<p>民族：" + MiniPersonInfo.nationality + "</p>" + "<p>性别："
				+ MiniPersonInfo.sex + "</p>" + "<p>出生年月："
				+ MiniPersonInfo.birthday + "</p>" + "<p>身份证号码："
				+ MiniPersonInfo.cardNum + "</p>" + "<p>户籍地址："
				+ MiniPersonInfo.permanentAddress + "</p>" + "<p>证件有效期："
				+ MiniPersonInfo.expiryStartDate + "至"
				+ MiniPersonInfo.expiryEndDate + "</p>");
		confirm_text1.setText(text1.toString());

		/* 身份信息2 */
		Spanned text2 = Html.fromHtml("<p>婚姻状况：" + MiniPersonInfo.maritalInfo
				+ "</p>" + "<p>手机号码：" + MiniPersonInfo.mobile + "</p>"
				+ "<p>文化程度：" + MiniPersonInfo.standDegree + "</p>" + "<p>住宅电话："
				+ MiniPersonInfo.areaCard + "-" + MiniPersonInfo.phoneNo
				+ "</p>" + "<p>现居住房屋性质：" + MiniPersonInfo.houseNoture + "</p>"
				+ "<p>在职状况：" + MiniPersonInfo.careerInfo + "</p>" + "<p>现居住地址："
				+ MiniPersonInfo.province + "</p>");
		confirm_text2.setText(text2.toString());

		/* 身份信息3 */
		Spanned text3 = Html.fromHtml("<p>现单位名称：" + MiniPersonInfo.nowDpName
				+ "</p>" + "<p>任职部门：" + MiniPersonInfo.rzDp + "</p>"
				+ "<p>本单位工作年限：" + MiniPersonInfo.professionYear + "年</p>"
				+ "<p>单位性质：" + MiniPersonInfo.dpNature + "</p>" + "<p>现任职位："
				+ MiniPersonInfo.nowRole + "</p>" + "<p>行业性质："
				+ MiniPersonInfo.tradeNature + "</p>" + "<p>申请人年收入："
				+ MiniPersonInfo.money_Year + "万元</p>" + "<p>邮寄地址："
				+ MiniPersonInfo.email_address + "</p>" + "<p>单位电话："
				+ MiniPersonInfo.areaNum + "-" + MiniPersonInfo.telNum + "-"
				+ MiniPersonInfo.extensionNum + "</p>" + "<p>单位地址："
				+ MiniPersonInfo.dpPlace + "</p>");
		confirm_text3.setText(text3.toString());

		/* 身份信息4 */
		Spanned text4 = Html.fromHtml("<p>直系亲属姓名：" + MiniPersonInfo.qinshu_name
				+ "</p>" + "<p>直系亲属手机：" + MiniPersonInfo.qinshu_mobilephone
				+ "</p>" + "<p>与申请人关系：" + MiniPersonInfo.qinshu_relationship
				+ "</p>" + "<p>直系亲属电话：" + MiniPersonInfo.qinshu_phone_quhao
				+ "-" + MiniPersonInfo.qinshu_phone_dianhua + "</p>"
				+ "<p>联系人姓名：" + MiniPersonInfo.contact_name + "</p>"
				+ "<p>联系人手机：" + MiniPersonInfo.contact_mobilephone + "</p>"
				+ "<p>联系人关系：" + MiniPersonInfo.contact_relationship + "</p>"
				+ "<p>联系人住宅电话：" + MiniPersonInfo.contact_phone_quhao + "-"
				+ MiniPersonInfo.contact_phone_dianhua + "</p>");
		confirm_text4.setText(text4.toString());

		/* 账单 */
		card_num = MiniPersonInfo.card_num;
		if (!card_num.equals("")) {
			String cardno1 = card_num.substring(0, 4);
			String cardno2 = card_num.substring(4, 8);
			String cardno3 = card_num.substring(8, 12);
			String cardno4 = card_num.substring(12, 16);
			Spanned text5 = Html.fromHtml("<p>账单日：" + MiniPersonInfo.zhangdan
					+ "</p>" + "<p>北京银行借记卡号</p>" + "<p>" + cardno1 + "&nbsp;"
					+ cardno2 + "&nbsp;" + cardno3 + "&nbsp;" + cardno4
					+ "&nbsp;" + "</p>" + "<p>还款设置："
					+ MiniPersonInfo.huankuan_setting + "</p>");
			confirm_text5.setText(text5.toString());
		} else {
			Spanned text5 = Html.fromHtml("<p>账单日：" + MiniPersonInfo.zhangdan
					+ "</p>" + "<p>北京银行借记卡号</p>" + "<p>无</p>" + "<p>还款设置："
					+ MiniPersonInfo.huankuan_setting + "</p>");
			confirm_text5.setText(text5.toString());
		}

		imageView1 = (ImageView) findViewById(R.id.imageView1);
		imageView1.setImageBitmap(ViewTools.getBitMap(
				MiniPersonInfo.compress_photoPath1, 10));
		imageView2 = (ImageView) findViewById(R.id.imageView2);
		imageView2.setImageBitmap(ViewTools.getBitMap(
				MiniPersonInfo.compress_photoPath2, 10));
		imageView3 = (ImageView) findViewById(R.id.imageView3);
		imageView3.setImageBitmap(ViewTools.getBitMap(
				MiniPersonInfo.compress_photoPath3, 10));
		imageView4 = (ImageView) findViewById(R.id.imageView4);
		imageView4.setImageBitmap(ViewTools.getBitMap(
				MiniPersonInfo.compress_photoPath4, 10));
		imageView5 = (ImageView) findViewById(R.id.imageView5);
		imageView5.setImageBitmap(ViewTools.getBitMap(
				MiniPersonInfo.compress_photoPath5, 10));

		imageView1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MiniConfirmInfoActivity.this,
						PreviewActivity.class);
				intent.putExtra("image_view", "1");
				startActivity(intent);
				overridePendingTransition(R.anim.fading_in, R.anim.fading_out);
			}
		});

		imageView2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MiniConfirmInfoActivity.this,
						PreviewActivity.class);
				intent.putExtra("image_view", "2");
				startActivity(intent);
				overridePendingTransition(R.anim.fading_in, R.anim.fading_out);
			}
		});

		imageView3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MiniConfirmInfoActivity.this,
						PreviewActivity.class);
				intent.putExtra("image_view", "3");
				startActivity(intent);
				overridePendingTransition(R.anim.fading_in, R.anim.fading_out);
			}
		});

		imageView4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MiniConfirmInfoActivity.this,
						PreviewActivity.class);
				intent.putExtra("image_view", "4");
				startActivity(intent);
				overridePendingTransition(R.anim.fading_in, R.anim.fading_out);
			}
		});

		imageView5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (MiniPersonInfo.compress_photoPath5 != null) {
					Intent intent = new Intent(MiniConfirmInfoActivity.this,
							PreviewActivity.class);
					intent.putExtra("image_view", "5");
					startActivity(intent);
					overridePendingTransition(R.anim.fading_in,
							R.anim.fading_out);
				}
			}
		});
		layout = (LinearLayout) findViewById(R.id.layout);
		if (MiniPersonInfo.compress_photoPath5 == null) {
			layout.setVisibility(View.GONE);
		}

		card = (ImageView) findViewById(R.id.card_mian);

		if (MiniPersonInfo.card.equals("A")) {
			card.setBackgroundResource(R.drawable.card1_select);
		} else {
			card.setBackgroundResource(R.drawable.card2_select);
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.next_button:
			new GetSeqAsyncTask().execute();
			break;
		}

	}

	// Mini卡申请最终提交页面
	private void replyMiniCard() {
//		 new ApplyAsyncTask().execute();
//		 new HttpMultipartPost(MiniConfirmInfoActivity.this);
		addList();

	}

	class GetSeqAsyncTask extends AsyncTask<String, Object, Object> {

    @Override
    protected Object doInBackground(String... params) {
      HttpHelper httpHelper = HttpHelper.getInstance(MiniConfirmInfoActivity.this);
      List<NameValuePair> arg = new ArrayList<NameValuePair>();
      return httpHelper.post(ContantsAddress.GET_SEQ, arg, MiniConfirmResponse.class);
    }

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressDialog();
		}

		@Override
		protected void onPostExecute(Object result) {
			dismissProgressDialog();
			super.onPostExecute(result);
			MiniConfirmResponse response = (MiniConfirmResponse) result;
			if (response != null) {
				if (RET_CODE == response.getCode()) {
					MiniConfirmMessage message = response.getResult();
					applCde = message.getApplCde();// 申请编号
				//	applSeq = message.getApplSeq();// 申请流水号
					MiniPersonInfo.applCde = applCde;
					MiniPersonInfo.applSeq = applSeq;
					// TODO:解析获取申请编码，申请流水号传入下一次联网。
					replyMiniCard();
				} else {
					System.out.println(response.getMsg());
					Toast.makeText(getApplicationContext(), "获取申请流水号失败！", Toast.LENGTH_LONG)
							.show();
				}
			} else {
				Toast.makeText(getApplicationContext(), getResources().getString(R.string.no_network), Toast.LENGTH_LONG).show();
			}
		}
	}

	private void addList() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<PhotoInfo> photo = new ArrayList<PhotoInfo>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("txCde", "ZZSL008");
		map.put("custName", MiniPersonInfo.name);
		map.put("indivSex", MiniPersonInfo.sex);
		String printnam = CnToSpell.getPinyin(MiniPersonInfo.name);
		map.put("custSpell", printnam);
		map.put("idType", "20");
		map.put("idNo", MiniPersonInfo.cardNum);
		map.put("idTermBgn", MiniPersonInfo.expiryStartDate);
		map.put("idTermEnd", MiniPersonInfo.expiryEndDate);
		map.put("apptStartDate", MiniPersonInfo.birthday);
		String indivMarital = null;
		if (MiniPersonInfo.maritalInfo.equals("未婚")) {
			indivMarital = "10";
		} else if (MiniPersonInfo.maritalInfo.equals("已婚")) {
			indivMarital = "20";
		} else if (MiniPersonInfo.maritalInfo.equals("其它")) {
			indivMarital = "90";
		}
		map.put("indivMarital", indivMarital);
		map.put("indivDepNo", MiniPersonInfo.house_hold);
		String indivDegree = null;
		if (MiniPersonInfo.standDegree.equals("博士")) {
			indivDegree = "08";
		} else if (MiniPersonInfo.standDegree.equals("硕士")) {
			indivDegree = "09";
		} else if (MiniPersonInfo.standDegree.equals("本科")) {
			indivDegree = "10";
		} else if (MiniPersonInfo.standDegree.equals("大专")) {
			indivDegree = "20";
		} else if (MiniPersonInfo.standDegree.equals("高中或中专")) {
			indivDegree = "30";
		} else if (MiniPersonInfo.standDegree.equals("初中及以下")) {
			indivDegree = "40";
		}
		map.put("indivDegree", indivDegree);
		map.put("indivFmyZone", MiniPersonInfo.areaCard);
		map.put("indivFmyTel", MiniPersonInfo.phoneNo);
		map.put("indivMobile", MiniPersonInfo.mobile);
		String indivLive = null;
		if (MiniPersonInfo.houseNoture.equals("自购无贷款")) {
			indivLive = "1";
		} else if (MiniPersonInfo.houseNoture.equals("自购有贷款")) {
			indivLive = "2";
		} else if (MiniPersonInfo.houseNoture.equals("单位宿舍")) {
			indivLive = "3";
		} else if (MiniPersonInfo.houseNoture.equals("与亲属同住")) {
			indivLive = "4";
		} else if (MiniPersonInfo.houseNoture.equals("租住")) {
			indivLive = "5";
		} else if (MiniPersonInfo.houseNoture.equals("其他")) {
			indivLive = "7";
		}
		map.put("indivLive", indivLive);
		map.put("indivLiveAddr", MiniPersonInfo.province);
		map.put("indivRegAddr", MiniPersonInfo.permanentAddress);
		map.put("indivEmp", MiniPersonInfo.nowDpName);
		map.put("indivBranch", MiniPersonInfo.rzDp);
		String indivPosition = null;
		if (MiniPersonInfo.nowRole.equals("高级管理人员")) {
			indivPosition = "1";
		} else if (MiniPersonInfo.nowRole.equals("一般管理人员")) {
			indivPosition = "2";
		} else if (MiniPersonInfo.nowRole.equals("一般正式员工")) {
			indivPosition = "3";
		} else if (MiniPersonInfo.nowRole.equals("非正式员工")) {
			indivPosition = "4";
		} else if (MiniPersonInfo.nowRole.equals("无")) {
			indivPosition = "5";
		} else if (MiniPersonInfo.nowRole.equals("企业负责人")) {
			indivPosition = "6";
		} else if (MiniPersonInfo.nowRole.equals("中层管理人员")) {
			indivPosition = "7";
		} else if (MiniPersonInfo.nowRole.equals("其他")) {
			indivPosition = "9";
		} else if (MiniPersonInfo.nowRole.equals("厅局级以上")) {
			indivPosition = "11";
		} else if (MiniPersonInfo.nowRole.equals("处级")) {
			indivPosition = "12";
		} else if (MiniPersonInfo.nowRole.equals("科级")) {
			indivPosition = "13";
		} else if (MiniPersonInfo.nowRole.equals("一般干部")) {
			indivPosition = "14";
		} else if (MiniPersonInfo.nowRole.equals("退休")) {
			indivPosition = "15";
		} else if (MiniPersonInfo.nowRole.equals("失业")) {
			indivPosition = "16";
		}
		map.put("indivPosition", indivPosition);
		map.put("indivEmpAddr", MiniPersonInfo.dpPlace);
		map.put("indivEmpZone", MiniPersonInfo.areaNum);
		map.put("indivEmpTelNo", MiniPersonInfo.telNum);
		map.put("indivEmpTelSub", MiniPersonInfo.extensionNum);
		String indivIndtryPaper = null;
		if (MiniPersonInfo.tradeNature.equals("个体工商户")) {
			indivIndtryPaper = "01";
		} else if (MiniPersonInfo.tradeNature.equals("批发/零售")) {
			indivIndtryPaper = "02";
		} else if (MiniPersonInfo.tradeNature.equals("制造业")) {
			indivIndtryPaper = "03";
		} else if (MiniPersonInfo.tradeNature.equals("房地产")) {
			indivIndtryPaper = "04";
		} else if (MiniPersonInfo.tradeNature.equals("建筑业")) {
			indivIndtryPaper = "05";
		} else if (MiniPersonInfo.tradeNature.equals("酒店/旅游/餐饮")) {
			indivIndtryPaper = "06";
		} else if (MiniPersonInfo.tradeNature.equals("交通运输/仓储/邮政业")) {
			indivIndtryPaper = "07";
		} else if (MiniPersonInfo.tradeNature.equals("传媒/体育/娱乐")) {
			indivIndtryPaper = "08";
		} else if (MiniPersonInfo.tradeNature.equals("专业事务所")) {
			indivIndtryPaper = "09";
		} else if (MiniPersonInfo.tradeNature.equals("信息传输/计算机服务/软件业")) {
			indivIndtryPaper = "10";
		} else if (MiniPersonInfo.tradeNature.equals("生物/医药")) {
			indivIndtryPaper = "11";
		} else if (MiniPersonInfo.tradeNature.equals("金融")) {
			indivIndtryPaper = "12";
		} else if (MiniPersonInfo.tradeNature.equals("公共事业")) {
			indivIndtryPaper = "13";
		} else if (MiniPersonInfo.tradeNature.equals("石油/石化")) {
			indivIndtryPaper = "14";
		} else if (MiniPersonInfo.tradeNature.equals("教育")) {
			indivIndtryPaper = "15";
		} else if (MiniPersonInfo.tradeNature.equals("医疗卫生")) {
			indivIndtryPaper = "16";
		} else if (MiniPersonInfo.tradeNature.equals("科研院所")) {
			indivIndtryPaper = "17";
		} else if (MiniPersonInfo.tradeNature.equals("水利/环境/公共设施管理业")) {
			indivIndtryPaper = "18";
		} else if (MiniPersonInfo.tradeNature.equals("烟草")) {
			indivIndtryPaper = "19";
		} else if (MiniPersonInfo.tradeNature.equals("军事机构")) {
			indivIndtryPaper = "20";
		} else if (MiniPersonInfo.tradeNature.equals("社会团体")) {
			indivIndtryPaper = "21";
		} else if (MiniPersonInfo.tradeNature.equals("政府机构/公检法")) {
			indivIndtryPaper = "22";
		} else if (MiniPersonInfo.tradeNature.equals("自由职业")) {
			indivIndtryPaper = "23";
		} else if (MiniPersonInfo.tradeNature.equals("租赁/服务业")) {
			indivIndtryPaper = "24";
		} else if (MiniPersonInfo.tradeNature.equals("无")) {
			indivIndtryPaper = "25";
		} else if (MiniPersonInfo.tradeNature.equals("农、林、渔、畜牧")) {
			indivIndtryPaper = "26";
		} else if (MiniPersonInfo.tradeNature.equals("其他")) {
			indivIndtryPaper = "99";
		}
		map.put("indivIndtryPaper", indivIndtryPaper);
		String indivEmpType = null;
		if (MiniPersonInfo.dpNature.equals("国家机关、事业单位")) {
			indivEmpType = "A";
		} else if (MiniPersonInfo.dpNature.equals("国有企业")) {
			indivEmpType = "B";
		} else if (MiniPersonInfo.dpNature.equals("集体企业")) {
			indivEmpType = "C";
		} else if (MiniPersonInfo.dpNature.equals("中外合资/中外合作/外商独资")) {
			indivEmpType = "D";
		} else if (MiniPersonInfo.dpNature.equals("股份制企业")) {
			indivEmpType = "E";
		} else if (MiniPersonInfo.dpNature.equals("私营企业")) {
			indivEmpType = "F";
		} else if (MiniPersonInfo.dpNature.equals("其他")) {
			indivEmpType = "G";
		} else if (MiniPersonInfo.dpNature.equals("无")) {
			indivEmpType = "H";
		}
		map.put("indivEmpType", indivEmpType);
		map.put("indivEmpYrs", MiniPersonInfo.professionYear);
		map.put("indivYrInc", MiniPersonInfo.money_Year);
		map.put("indivRelName", MiniPersonInfo.qinshu_name);
		String indivRelation = null;
		if (MiniPersonInfo.qinshu_relationship.equals("配偶")) {
			indivRelation = "01";
		} else if (MiniPersonInfo.qinshu_relationship.equals("父母")) {
			indivRelation = "02";
		} else if (MiniPersonInfo.qinshu_relationship.equals("子女")) {
			indivRelation = "03";
		} else if (MiniPersonInfo.qinshu_relationship.equals("兄弟姐妹")) {
			indivRelation = "09";
		}
		map.put("indivRelation", indivRelation);
		map.put("indivRelZone", MiniPersonInfo.qinshu_phone_quhao);
		map.put("indivRelPhone", MiniPersonInfo.qinshu_phone_dianhua);
		map.put("indivRelMobile", MiniPersonInfo.qinshu_mobilephone);
		map.put("indivOthName", MiniPersonInfo.contact_name);
		String indivOthRel = null;
		if (MiniPersonInfo.contact_relationship.equals("同事")) {
			indivOthRel = "06";
		} else if (MiniPersonInfo.contact_relationship.equals("同学")) {
			indivOthRel = "10";
		} else if (MiniPersonInfo.contact_relationship.equals("朋友")) {
			indivOthRel = "11";
		} else if (MiniPersonInfo.contact_relationship.equals("其他关系")) {
			indivOthRel = "08";
		}
		map.put("indivOthRel", indivOthRel);
		map.put("indivOthZone", MiniPersonInfo.contact_phone_quhao);
		map.put("indivOthPhone", MiniPersonInfo.contact_phone_dianhua);
		map.put("indivOthMobile", MiniPersonInfo.contact_mobilephone);
		String sendTyp = null;
		if (MiniPersonInfo.email_address.equals("现居住地址")) {
			sendTyp = "00";
		} else if (MiniPersonInfo.email_address.equals("单位地址")) {
			sendTyp = "01";
		}
		map.put("sendTyp", sendTyp);
		map.put("mssInd", "Y");
		String atpyDay = null;
		if (MiniPersonInfo.zhangdan.equals("6日")) {
			atpyDay = "06";
		} else if (MiniPersonInfo.zhangdan.equals("16日")) {
			atpyDay = "16";
		} else if (MiniPersonInfo.zhangdan.equals("26日")) {
			atpyDay = "26";
		}
		map.put("atpyDay", atpyDay);
		map.put("applRpymAcBank", "BOB");
		map.put("applRpymAcNo", MiniPersonInfo.card_num);
		String rpymOption = null;
		if (MiniPersonInfo.huankuan_setting.equals("全部还款额")) {
			rpymOption = "ALL";
		} else if (MiniPersonInfo.huankuan_setting.equals("最低还款额")) {
			rpymOption = "SUB";
		}
		map.put("rpymOption", rpymOption);
		map.put("switchNo", "");
		map.put("operator", MiniPersonInfo.spread_no);
		map.put("operatorName", MiniPersonInfo.spread_name);
		map.put("groupNo", "");
		map.put("pcCode", "");
		map.put("trenchNo", "");
		map.put("pomsStaff", "");
		map.put("applSeq", MiniPersonInfo.applSeq);
		map.put("applCde", MiniPersonInfo.applCde);
		map.put("loanTyp", "0001");
		map.put("idCtry", "CHN");
		map.put("atpySend", "LT");
		map.put("inputSrc", "08");
		map.put("layout", MiniPersonInfo.card);
		map.put("machineId", "eloan_phone");
		map.put("agentId", "eloan_phone");
	
		list.add(map);
		photo.add(new PhotoInfo(MiniPersonInfo.applCde + "01",
				MiniPersonInfo.applCde, "SFZZM", "LCDOC",
				MiniPersonInfo.applCde + "01" + ".jpg", "999",
				MiniPersonInfo.compress_photoPath1, "身份证正面"));
		photo.add(new PhotoInfo(MiniPersonInfo.applCde + "02",
				MiniPersonInfo.applCde, "SFZFM", "LCDOC",
				MiniPersonInfo.applCde + "02" + ".jpg", "999",
				MiniPersonInfo.compress_photoPath2, "身份证反面"));
		photo.add(new PhotoInfo(MiniPersonInfo.applCde + "03",
				MiniPersonInfo.applCde, "KHZP", "LCDOC", MiniPersonInfo.applCde
						+ "03" + ".jpg", "999",
				MiniPersonInfo.compress_photoPath3, "身份证及本人合照"));
		photo.add(new PhotoInfo(MiniPersonInfo.applCde + "04",
				MiniPersonInfo.applCde, "XYK", "LCDOC", MiniPersonInfo.applCde
						+ "04" + ".jpg", "999",
				MiniPersonInfo.compress_photoPath4, "信用卡"));
		if (MiniPersonInfo.compress_photoPath5 != null) {
			photo.add(new PhotoInfo(MiniPersonInfo.applCde + "05",
					MiniPersonInfo.applCde, "QT", "LCDOC",
					MiniPersonInfo.applCde + "05" + ".jpg", "999",
					MiniPersonInfo.compress_photoPath5, "其他"));
		}
		new HttpMultipartPostAddMore(MiniConfirmInfoActivity.this, photo,
				ContantsAddress.DOC_UPLOAD, list, ContantsAddress.APPLAY_MINI);

	}
}
