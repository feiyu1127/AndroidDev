package com.yucheng.byxf.mini.xiaojinying;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.apfx.utl.RegexResult;
import com.yucheng.byxf.mini.message.Response;
import com.yucheng.byxf.mini.message.XiaojinYingBasicResult;
import com.yucheng.byxf.mini.ui.BaseActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.RegexCust;

/** 
 * 类名: XiaoJinYingChangeOtherInfoActivity_1</br> 
 * 描述:变更其他信息---基本信息 </br>
 * 开发人员： weiyb</br>
 * 创建时间： 2015-7-24 
 */ 

public class XiaoJinYingChangeOtherInfoActivity_1 extends BaseActivity {

	// 验证结果
	@SuppressWarnings("unused")
	private RegexResult result;
	private ImageView back;
	private EditText et_homephone_quhao;// 区号
	private EditText et_homephone_num; // 电话
	private EditText et_email;// 邮箱
	private EditText et_danweimingcheng;// 单位名称
	private EditText et_danweidianhua_quhao; // 单位电话区号
	private EditText et_danweidianhua_num;// 单位电话 号码
	private EditText et_danweidianhua_fenjihao;// 分机号

	private String S_et_homephone_quhao = "";// 区号
	private String S_et_homephone_num = ""; // 电话
	private String S_et_email = "";// 邮箱
	private String S_et_danweimingcheng;// 单位名称
	private String S_et_danweidianhua_quhao = ""; // 单位电话区号
	private String S_et_danweidianhua_num = "";// 单位电话 号码
	private String S_et_danweidianhua_fenjihao = "";// 分机号

	private String S_indivSex = "";
	private String S_indivCtry = "";
	private String S_indivMarital = "";
	private String S_indivEdu = "";
	private String S_indivMobile = "";
	private String S_fixNo = "";
	private String S_indivBranch = "";
	private String S_indivPosition = "";
	private RelativeLayout rl_phonenum;

	// ---不能修改
	private EditText edit_phone_no_vadlue2;// 手机号码 ======就展示

	private Button tijiao_button;// 保存
	private Button btn_changmobile;// 改手机

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xiaoxinyin_baiscinfomation_activity);

		back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(this);
		et_homephone_quhao = (EditText) findViewById(R.id.et_homephone_quhao);
		et_homephone_num = (EditText) findViewById(R.id.et_homephone_num);
		et_email = (EditText) findViewById(R.id.et_email);
		et_danweimingcheng = (EditText) findViewById(R.id.et_danweimingcheng);
		et_danweidianhua_quhao = (EditText) findViewById(R.id.et_danweidianhua_quhao);
		et_danweidianhua_num = (EditText) findViewById(R.id.et_danweidianhua_num);
		et_danweidianhua_fenjihao = (EditText) findViewById(R.id.et_danweidianhua_fenjihao);
		edit_phone_no_vadlue2 = (EditText) findViewById(R.id.edit_phone_no_vadlue2);
		tijiao_button = (Button) findViewById(R.id.tijiao_button);
		tijiao_button.setOnClickListener(this);
		btn_changmobile = (Button) findViewById(R.id.btn_changmobile);

		rl_phonenum = (RelativeLayout) findViewById(R.id.rl_phonenum);
		rl_phonenum.setOnClickListener(this);
		// new GetInfoAsyncTask().execute("210101198506190073");
		btn_changmobile.setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		new GetInfoAsyncTask().execute(Contents.xiaoChaXunResult2.getIdNo());
	}

	/**
	 * 获取基本信息
	 */
	class GetInfoAsyncTask extends AsyncTask<String, Object, Object> {
		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(XiaoJinYingChangeOtherInfoActivity_1.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("idNo", params[0]));

			return httpHelper.post(ContantsAddress.XiaoBasicInfo, arg,
					XiaojinYingBasicResult.class);
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
			registerResponse3(result);
		}
	}

	private void registerResponse3(Object result) {
		XiaojinYingBasicResult response3 = (XiaojinYingBasicResult) result;
		if (response3 == null) {
			DialogUtil.showDialogOneButton2(
					XiaoJinYingChangeOtherInfoActivity_1.this, "服务器请求异常!");
			return;
		} else {
			if (0 == response3.getCode()) {
				et_homephone_quhao.setText(response3.getResult()
						.getBasicMsgBody().getIndivFmyZome());// 区号
				et_homephone_num.setText(response3.getResult()
						.getBasicMsgBody().getIndivFmyTel()); // 电话
				et_email.setText(response3.getResult().getBasicMsgBody()
						.getIndivEmail());// 邮箱
				et_danweimingcheng.setText(response3.getResult()
						.getBasicMsgBody().getIndivEmp());// 单位名称
				et_danweidianhua_quhao.setText(response3.getResult()
						.getBasicMsgBody().getIndivEmpZone()); // 单位电话区号
				et_danweidianhua_num.setText(response3.getResult()
						.getBasicMsgBody().getIndivEmpTelNo());// 单位电话 号码
				et_danweidianhua_fenjihao.setText(response3.getResult()
						.getBasicMsgBody().getIndivEmpTelSub());// 分机号
				edit_phone_no_vadlue2.setText(response3.getResult()
						.getBasicMsgBody().getIndivMobile());// 手机号码 ======就展示

				S_indivSex = response3.getResult().getBasicMsgBody()
						.getIndivSex();
				S_indivCtry = response3.getResult().getBasicMsgBody()
						.getIndivCtry();
				S_indivMarital = response3.getResult().getBasicMsgBody()
						.getIndivMarital();
				S_indivEdu = response3.getResult().getBasicMsgBody()
						.getIndivEdu();
				S_indivMobile = response3.getResult().getBasicMsgBody()
						.getIndivMobile();
				S_fixNo = response3.getResult().getBasicMsgBody().getFixNo();
				S_indivBranch = response3.getResult().getBasicMsgBody()
						.getIndivBranch();
				S_indivPosition = response3.getResult().getBasicMsgBody()
						.getIndivPosition();
			} else {
				DialogUtil.showDialogOneButton2(
						XiaoJinYingChangeOtherInfoActivity_1.this,
						response3.getMsg());
			}
		}
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;

		case R.id.btn_changmobile:
			// 修改手机
			Intent it = new Intent();
			it.setClass(XiaoJinYingChangeOtherInfoActivity_1.this,
					XiaoJinYingChangeOtherInfoActivity_1_phoneNumInfo.class);
			startActivity(it);
			break;
		case R.id.tijiao_button:
			// 保存按钮
			S_et_homephone_quhao = et_homephone_quhao.getText().toString();// 区号
			if (S_et_homephone_quhao == null || S_et_homephone_quhao.equals("")) {
				// DialogUtil.showDialogOneButton2(
				// XiaoJinYingChangeOtherInfoActivity_1.this, "请输入区号");
				// return;
				S_et_homephone_quhao = "";
			}

			S_et_homephone_num = et_homephone_num.getText().toString(); // 电话
			if (S_et_homephone_num == null || S_et_homephone_num.equals("")) {
				// DialogUtil.showDialogOneButton2(
				// XiaoJinYingChangeOtherInfoActivity_1.this, "请输入电话号码");
				// return;
				S_et_homephone_num = "";
			}

			S_et_email = et_email.getText().toString();// 邮箱

			if (S_et_email == null || S_et_email.equals("")) {
				DialogUtil.showDialogOneButton2(
						XiaoJinYingChangeOtherInfoActivity_1.this, "请输入邮箱地址");
				return;
			}
			// // 电子邮箱
			RegexResult temp = null;
			temp = RegexCust.required("电子邮箱", S_et_email);

			if (RegexCust.email(S_et_email) == false) {
				// result = new RegexResult(false, "电子邮箱格式不对!");
				DialogUtil.showDialogOneButton2(
						XiaoJinYingChangeOtherInfoActivity_1.this, "电子邮箱格式不对!");

				return;
			}

			S_et_danweimingcheng = et_danweimingcheng.getText().toString();// 单位名称
			if (S_et_danweimingcheng == null || S_et_danweimingcheng.equals("")) {
				DialogUtil.showDialogOneButton2(
						XiaoJinYingChangeOtherInfoActivity_1.this, "请输入单位名称");
				return;
			}

			S_et_danweidianhua_quhao = et_danweidianhua_quhao.getText()
					.toString(); // 单位电话区号
			if (S_et_danweidianhua_quhao == null
					|| S_et_danweidianhua_quhao.equals("")) {
				DialogUtil.showDialogOneButton2(
						XiaoJinYingChangeOtherInfoActivity_1.this, "请输入单位电话区号");
				return;
				// S_et_danweidianhua_quhao="";
			}
			S_et_danweidianhua_num = et_danweidianhua_num.getText().toString();// 单位电话
			if (S_et_danweidianhua_num == null
					|| S_et_danweidianhua_num.equals("")) {
				DialogUtil.showDialogOneButton2(
						XiaoJinYingChangeOtherInfoActivity_1.this, "请输入单位电话");
				return;
			}
			// 分级号码
			S_et_danweidianhua_fenjihao = et_danweidianhua_fenjihao.getText()
					.toString();// 分机号

			// 修改成功 上传！
			new UpInfoAsyncTask().execute(Contents.xiaoChaXunResult2.getIdNo());
			break;
		default:
			break;
		}
	}

	/**
	 * 
	 * 上传基本信息
	 *
	 */
	class UpInfoAsyncTask extends AsyncTask<String, Object, Object> {
		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(XiaoJinYingChangeOtherInfoActivity_1.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("idNo", params[0]));
			// 0 证件号码 idNo 必填
			// 性别 indivSex 1 男2 女3 未知的性别4 未说明的性别
			arg.add(new BasicNameValuePair("indivSex", S_indivSex));
			// 国籍 indivCtry
			arg.add(new BasicNameValuePair("indivCtry", S_indivCtry));
			// 婚姻状况 indivMarital 10 未婚20 已婚90 其他
			arg.add(new BasicNameValuePair("indivMarital", S_indivMarital));
			// 教育程度 indivEdu 08 博士及以上09 硕士10 本科20 大专30 高中或中专40 初中及以下50 无
			arg.add(new BasicNameValuePair("indivEdu", S_indivEdu));
			// 家庭电话区号 indivFmyZome
			arg.add(new BasicNameValuePair("indivFmyZome", S_et_homephone_quhao));
			// 家庭电话 indivFmyTel
			arg.add(new BasicNameValuePair("indivFmyTel", S_et_homephone_num));
			// 手机号码 indivMobile
			arg.add(new BasicNameValuePair("indivMobile", S_indivMobile));
			// 传真机号码 fixNo
			arg.add(new BasicNameValuePair("fixNo", S_fixNo));
			// E-mail indivEmail
			arg.add(new BasicNameValuePair("indivEmail", S_et_email));
			// 公司电话区号 indivEmpZone
			arg.add(new BasicNameValuePair("indivEmpZone",
					S_et_danweidianhua_quhao));
			// 公司电话 indivEmpTelNo
			arg.add(new BasicNameValuePair("indivEmpTelNo",
					S_et_danweidianhua_num));
			// 公司电话分机号 indivEmpTelSub
			arg.add(new BasicNameValuePair("indivEmpTelSub",
					S_et_danweidianhua_fenjihao));
			// 单位名称 indivEmp
			arg.add(new BasicNameValuePair("indivEmp", S_et_danweimingcheng));
			// 部门 indivBranch
			arg.add(new BasicNameValuePair("indivBranch", S_indivBranch));
			// 职务分类 indivPosition
			arg.add(new BasicNameValuePair("indivPosition", S_indivPosition));

			return httpHelper.post(ContantsAddress.XiaoUpBasicInfo, arg,
					Response.class);
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
			registerResponse4(result);
		}
	}

	private void registerResponse4(Object result) {
		Response response3 = (Response) result;
		if (response3 == null) {
			DialogUtil.showDialogOneButton2(
					XiaoJinYingChangeOtherInfoActivity_1.this, "服务器请求异常!");
			return;
		} else {
			if (0 == response3.getCode()) {
				DialogUtil.showDialogOneButton(
						XiaoJinYingChangeOtherInfoActivity_1.this, "提交成功",
						new OnClickListener() {
							@Override
							public void onClick(View v) {

								finish();
							}

						});
			} else {
				DialogUtil.showDialogOneButton(
						XiaoJinYingChangeOtherInfoActivity_1.this, "提交失败"
								+ response3.getMsg(), new OnClickListener() {
							@Override
							public void onClick(View v) {
								finish();
							}

						});
			}
		}
	}
}
