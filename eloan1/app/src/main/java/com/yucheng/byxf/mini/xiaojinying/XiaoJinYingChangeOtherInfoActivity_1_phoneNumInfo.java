package com.yucheng.byxf.mini.xiaojinying;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.map.DemoApplication;
import com.yucheng.byxf.mini.message.MessageInfoSubmitYe;
import com.yucheng.byxf.mini.message.Response;
import com.yucheng.byxf.mini.message.VerifyCodeResponse;
import com.yucheng.byxf.mini.message.XiaojinYingBasicResult;
import com.yucheng.byxf.mini.ui.BaseActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DES3Utils;
import com.yucheng.byxf.mini.utils.DESUtils;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.StringUtils;

/**
 * 类名: XiaoJinYingChangeOtherInfoActivity_1_phoneNumInfo</br> 描述:
 * 变更其他信息---基本信息---2---手机修改页面</br> 开发人员： weiyb</br> 创建时间： 2015-7-24
 */

public class XiaoJinYingChangeOtherInfoActivity_1_phoneNumInfo extends
		BaseActivity {
	// 信息有误
	private int time;

	private static final Integer RET_CODE = 0;

	private String loginErrorMessage;

	private boolean isFlag = true;

	private ImageView back;

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

	private EditText edit_chaxunmima;// 查询密码

	private EditText et_xinshoujihaoma;// 新手机号码

	private EditText et_yanzhengma;// 验证码

	private Button tijiao_button;// 提交页面bt

	private Button bt_yanzhengma;

	private String S_edit_chaxunmima = "";// 查询密码

	private String S_et_xinshoujihaoma = "";// 新手机号码

	private String S_et_yanzhengma = "";// 验证码

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xiaoxinyin_baiscinfomation_activity_phone);
		back = (ImageView) findViewById(R.id.back);
		edit_chaxunmima = (EditText) findViewById(R.id.edit_chaxunmima);// 查询密码
		et_xinshoujihaoma = (EditText) findViewById(R.id.et_xinshoujihaoma);// 新手机号码
		et_yanzhengma = (EditText) findViewById(R.id.et_yanzhengma);// 新手机号码
		tijiao_button = (Button) findViewById(R.id.tijiao_button);// 提交页面bt
		bt_yanzhengma = (Button) findViewById(R.id.bt_yanzhengma);// 提交验证码
		bt_yanzhengma.setOnClickListener(this);
		tijiao_button.setOnClickListener(this);
		back.setOnClickListener(this);
		new GetInfoAsyncTask().execute(Contents.response.getResult().getIdNo());
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0) {
				bt_yanzhengma.setText(time + "秒后可获取");

			} else if (msg.what == 1) {
				isFlag = !isFlag;
				bt_yanzhengma.setText("获取验证码");
			} else if (msg.what == 2) {

				// 去 验证 查询密码
				new ChaXunKeyInfoAsyncTask().execute(
						Contents.xiaoChaXunResult2.getCardNo(),
						S_edit_chaxunmima);
			} else if (msg.what == 3) {
				// 上传 改的资料----- 身份证 号码 + 手机号码

				new UpInfoAsyncTask().execute(Contents.response.getResult()
						.getIdNo(), S_et_xinshoujihaoma);
			} else if (msg.what == 4) {
				// 姓名 证件号 手机号
				new GetMessageInfo().execute(Contents.response.getResult()
						.getCipNamecn(), Contents.response.getResult()
						.getIdNo(), S_et_xinshoujihaoma);
			}
		};
	};

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {

		case R.id.tijiao_button:
			S_edit_chaxunmima = edit_chaxunmima.getText().toString();
			S_et_xinshoujihaoma = et_xinshoujihaoma.getText().toString();
			S_et_yanzhengma = et_yanzhengma.getText().toString();

			if (S_edit_chaxunmima == null || S_edit_chaxunmima.equals("")) {
				DialogUtil.showDialogOneButton2(
						XiaoJinYingChangeOtherInfoActivity_1_phoneNumInfo.this,
						"请输入查询密码");
				return;
			}
			if (S_et_xinshoujihaoma == null || S_et_xinshoujihaoma.equals("")) {
				DialogUtil.showDialogOneButton2(
						XiaoJinYingChangeOtherInfoActivity_1_phoneNumInfo.this,
						"请输入新电话号码");
				return;
			}
			if (S_et_yanzhengma == null || S_et_yanzhengma.equals("")) {
				DialogUtil.showDialogOneButton2(
						XiaoJinYingChangeOtherInfoActivity_1_phoneNumInfo.this,
						"请输入验证码");
				return;
			}
			new CommitRegisterAsyncTask().execute(S_et_xinshoujihaoma,
					S_et_yanzhengma);
			break;

		case R.id.back:
			// 返回
			finish();
			break;
		case R.id.bt_yanzhengma:
			// 获取验证码
			S_et_xinshoujihaoma = et_xinshoujihaoma.getText().toString();
			if (S_et_xinshoujihaoma == null || S_et_xinshoujihaoma.equals("")) {
				DialogUtil.showDialogOneButton2(
						XiaoJinYingChangeOtherInfoActivity_1_phoneNumInfo.this,
						"请输入新电话号码");
				return;
			}
			if (isFlag) {
				new YanZhengMaAsyncTask().execute(S_et_xinshoujihaoma);
			}
			break;

		default:
			break;
		}

	}

	/**
	 * 获取基本信息
	 */
	class GetInfoAsyncTask extends AsyncTask<String, Object, Object> {
		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(XiaoJinYingChangeOtherInfoActivity_1_phoneNumInfo.this);
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
					XiaoJinYingChangeOtherInfoActivity_1_phoneNumInfo.this,
					"服务器请求异常!");
			return;
		} else {
			if (0 == response3.getCode()) {
				S_et_homephone_quhao = response3.getResult().getBasicMsgBody()
						.getIndivFmyZome();
				S_et_homephone_num = response3.getResult().getBasicMsgBody()
						.getIndivFmyTel();// 电话
				S_et_email = response3.getResult().getBasicMsgBody()
						.getIndivEmail();
				S_et_danweimingcheng = response3.getResult().getBasicMsgBody()
						.getIndivEmp();// 单位名称
				S_et_danweidianhua_quhao = response3.getResult()
						.getBasicMsgBody().getIndivEmpZone(); // 单位电话区号
				S_et_danweidianhua_num = response3.getResult()
						.getBasicMsgBody().getIndivEmpTelNo();// 单位电话 号码
				S_et_danweidianhua_fenjihao = response3.getResult()
						.getBasicMsgBody().getIndivEmpTelSub();// 分机号

				S_indivSex = response3.getResult().getBasicMsgBody()
						.getIndivSex();
				S_indivCtry = response3.getResult().getBasicMsgBody()
						.getIndivCtry();
				S_indivMarital = response3.getResult().getBasicMsgBody()
						.getIndivMarital();
				S_indivEdu = response3.getResult().getBasicMsgBody()
						.getIndivEdu();
				// S_indivMobile = response3.getResult().getBasicMsgBody()
				// .getIndivMobile();
				S_fixNo = response3.getResult().getBasicMsgBody().getFixNo();
				S_indivBranch = response3.getResult().getBasicMsgBody()
						.getIndivBranch();
				S_indivPosition = response3.getResult().getBasicMsgBody()
						.getIndivPosition();
			} else {
				DialogUtil.showDialogOneButton2(
						XiaoJinYingChangeOtherInfoActivity_1_phoneNumInfo.this,
						response3.getMsg());
			}
		}
	}

	/**
	 * 校验密码！
	 */
	class ChaXunKeyInfoAsyncTask extends AsyncTask<String, Object, Object> {
		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(XiaoJinYingChangeOtherInfoActivity_1_phoneNumInfo.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("cardNum", params[0]));

			// arg.add(new BasicNameValuePair("txnPsw", params[1]));

			try {
				arg.add(new BasicNameValuePair("txnPsw", DESUtils.encryptMode(
						params[1], DemoApplication.getApplication())));
			} catch (Exception e) {

				e.printStackTrace();
			}

			return httpHelper.post(ContantsAddress.XiaoYaZhengKeyInfo, arg,
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
			registerResponse5(result);
		}
	}

	private void registerResponse5(Object result) {
		Response response5 = (Response) result;
		if (response5 == null) {
			DialogUtil.showDialogOneButton2(
					XiaoJinYingChangeOtherInfoActivity_1_phoneNumInfo.this,
					"服务器请求异常!");
			return;
		} else {
			if (0 == response5.getCode()) {
				// 密码正确！
				handler.sendEmptyMessage(3);

			} else {
				DialogUtil.showDialogOneButton2(
						XiaoJinYingChangeOtherInfoActivity_1_phoneNumInfo.this,
						response5.getMsg());
			}
		}
	}

	/**
	 * 上传基本信息
	 */
	class UpInfoAsyncTask extends AsyncTask<String, Object, Object> {
		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(XiaoJinYingChangeOtherInfoActivity_1_phoneNumInfo.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("idNo", params[0]));
			// 手机号码 indivMobile
			arg.add(new BasicNameValuePair("indivMobile", params[1]));
			// ===================
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
			registerResponse7(result);
		}
	}

	private void registerResponse7(Object result) {
		Response response7 = (Response) result;
		if (response7 == null) {
			DialogUtil.showDialogOneButton2(
					XiaoJinYingChangeOtherInfoActivity_1_phoneNumInfo.this,
					"服务器请求异常!");
			return;
		} else {
			if (0 == response7.getCode()) {
				handler.sendEmptyMessage(4);
			} else {
				DialogUtil.showDialogOneButton2(
						XiaoJinYingChangeOtherInfoActivity_1_phoneNumInfo.this,
						response7.getMsg());
			}
		}
	}

	/**
	 * 获取验证码！
	 * 
	 * @author wangze
	 */
	class YanZhengMaAsyncTask extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(XiaoJinYingChangeOtherInfoActivity_1_phoneNumInfo.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("phoneNum", params[0]));
			arg.add(new BasicNameValuePair("msgTemplate", "XJY_MODIFY_PHONE"));
			String s = Contents.response.getResult().getIdNo();
			s = StringUtils.replacesFour(s);
			arg.add(new BasicNameValuePair("param", "****" + s));
			return httpHelper.post(ContantsAddress.Qingkuan_xiaojinying, arg,
					VerifyCodeResponse.class);
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
			loginResponse(result);
		}
	}

	private void timer() {
		new Thread() {
			public void run() {

				while (time > 0) {
					time--;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					handler.sendEmptyMessage(0);

				}
				handler.sendEmptyMessage(1);
			};
		}.start();
	}

	private void loginResponse(Object result) {
		VerifyCodeResponse response = (VerifyCodeResponse) result;
		if (response == null) {
			loginErrorMessage = "服务器请求异常!";
			DialogUtil.createDialog(this, 1, loginErrorMessage);
			isFlag = true;
			bt_yanzhengma.setText("重新获取验证码");
			return;
		} else {
			if (RET_CODE.equals(response.getCode())) {
				String s = response.getMsg();

				Toast.makeText(
						XiaoJinYingChangeOtherInfoActivity_1_phoneNumInfo.this,
						"获取验证码成功" + response.getMsg(), Toast.LENGTH_LONG)
						.show();
				time = 120;// response.getResult();
				isFlag = false;
				timer();
			} else {
				loginErrorMessage = response.getMsg();
				DialogUtil.createDialog(this, 4, loginErrorMessage);
				isFlag = true;
				bt_yanzhengma.setText("重新获取验证码");
			}
		}
	}

	/**
	 * 验证 验证码
	 */

	class CommitRegisterAsyncTask extends AsyncTask<String, Object, Object> {
		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(XiaoJinYingChangeOtherInfoActivity_1_phoneNumInfo.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("phoneNum", params[0]));
			arg.add(new BasicNameValuePair("verifyCode", params[1]));
			return httpHelper.post(ContantsAddress.VALID_PHONE_VERIFY_CODE,
					arg, Response.class);
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
			CommitResponse(result);
		}
	}

	private void CommitResponse(Object result) {
		Response response = (Response) result;
		if (response == null) {
			DialogUtil.showDialogOneButton2(
					XiaoJinYingChangeOtherInfoActivity_1_phoneNumInfo.this,
					"服务器请求异常!");
			return;
		} else {
			if (response.getCode() == 0) {

				handler.sendEmptyMessage(2);
			} else {
				DialogUtil.showDialogOneButton2(
						XiaoJinYingChangeOtherInfoActivity_1_phoneNumInfo.this,
						response.getMsg());
			}
		}
	}

	/**
	 * 个人资料
	 */
	class GetMessageInfo extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {

			HttpHelper mBobcfcHttpClient = HttpHelper
					.getInstance(XiaoJinYingChangeOtherInfoActivity_1_phoneNumInfo.this);
			String url = ContantsAddress.MESSAGEINFOSUBMIT;
			List<NameValuePair> param = new ArrayList<NameValuePair>();

			param.add(new BasicNameValuePair("cipNamecn", params[0]));
			param.add(new BasicNameValuePair("idNo", params[1]));
			param.add(new BasicNameValuePair("cipMobile", params[2]));

			Class<MessageInfoSubmitYe> clazz = MessageInfoSubmitYe.class;
			MessageInfoSubmitYe response = mBobcfcHttpClient.post(url, param,
					clazz);
			return response;

		}

		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);
			dismissProgressDialog();
			MessageInfoSubmitYe response = (MessageInfoSubmitYe) result;
			if (response != null) {
				System.out.println("不为空");
				if (response.getCode() == 0) {

					// 成功后把编辑框内的号码赋值给常量
					if (et_xinshoujihaoma.getText() != null) {
						Contents.response.getResult().setCipMobile(
								et_xinshoujihaoma.getText().toString());
					}
					;

					DialogUtil
							.showDialogOneButton(
									XiaoJinYingChangeOtherInfoActivity_1_phoneNumInfo.this,

									"提交成功", new OnClickListener() {
										@Override
										public void onClick(View v) {
											finish();
										}
									});
				} else {
					DialogUtil
							.showDialogOneButton(
									XiaoJinYingChangeOtherInfoActivity_1_phoneNumInfo.this,

									"提交失败：" + response.getMsg(),
									new OnClickListener() {
										@Override
										public void onClick(View v) {
											finish();
										}
									});
				}
			} else {
				Toast.makeText(
						XiaoJinYingChangeOtherInfoActivity_1_phoneNumInfo.this,
						getResources().getString(R.string.no_network),
						Toast.LENGTH_LONG).show();

			}

		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			showProgressDialog();

		}
	}

}
