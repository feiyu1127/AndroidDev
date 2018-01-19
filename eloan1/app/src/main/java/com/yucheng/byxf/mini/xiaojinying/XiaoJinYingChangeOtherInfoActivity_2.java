package com.yucheng.byxf.mini.xiaojinying;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.message.Response;
import com.yucheng.byxf.mini.message.XIaoaddressHuoQuResponse;
import com.yucheng.byxf.mini.message.XIaoaddressHuoQuResponseRes;
import com.yucheng.byxf.mini.message.XiaoChaXunResult;
import com.yucheng.byxf.mini.ui.BaseActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;

/**
 * 类名: XiaoJinYingChangeOtherInfoActivity_2</br> 描述:变更其他信息--地址信息 </br> 开发人员：
 * weiyb</br> 创建时间： 2015-7-24
 */

public class XiaoJinYingChangeOtherInfoActivity_2 extends BaseActivity {

	private EditText et_homeaddress;// 家厅地址
	private EditText et_homeyoubian;// 邮编
	private EditText et_danweidizhi;// 单位地址
	private EditText et_danweiyoubian;// 单位地址邮编
	private Button caocun_button; // 保存
	private String contAddrJt = "";
	private String addrJtZip = "";

	private String contAddrGs = "";
	private String addrGsZip = "";

	private String sendTyp = "";
	private String contAddrHj = "";
	private String addrHjZip = "";
	private String contAddrFc = "";
	private String addrFcZip = "";

	private ImageView back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xiaojinyin_address_activity);
		et_homeaddress = (EditText) findViewById(R.id.et_homeaddress);
		et_homeyoubian = (EditText) findViewById(R.id.et_homeyoubian);
		et_danweidizhi = (EditText) findViewById(R.id.et_danweidizhi);
		et_danweiyoubian = (EditText) findViewById(R.id.et_danweiyoubian);
		caocun_button = (Button) findViewById(R.id.caocun_button);
		caocun_button.setOnClickListener(this);
		back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(this);
		new Info2HuoQuAsyncTask().execute(
				Contents.xiaoChaXunResult2.getCardNo(),
				Contents.xiaoChaXunResult2.getIdNo());
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.caocun_button:
			String St_et_homeaddress = et_homeaddress.getText().toString();
			// 家厅地址
			String St_et_homeyoubian = et_homeyoubian.getText().toString();
			// 邮编
			String St_et_danweidizhi = et_danweidizhi.getText().toString();
			// 单位地址
			String St_et_danweiyoubian = et_danweiyoubian.getText().toString();
			// 单位地址邮编

			if (St_et_homeaddress == null || St_et_homeaddress.equals("")) {
				DialogUtil.showDialogOneButton2(
						XiaoJinYingChangeOtherInfoActivity_2.this, "请输入家庭地址");
				return;
			}
			if (St_et_homeyoubian == null || St_et_homeyoubian.equals("")) {
				// DialogUtil.showDialogOneButton2(
				// XiaoJinYingChangeOtherInfoActivity_2.this, "请输入邮编");
				// return;
				St_et_homeyoubian = "";
			}
			if (St_et_danweidizhi == null || St_et_danweidizhi.equals("")) {
				DialogUtil.showDialogOneButton2(
						XiaoJinYingChangeOtherInfoActivity_2.this, "请输入单位地址");
				return;
			}
			if (St_et_danweiyoubian == null || St_et_danweiyoubian.equals("")) {
				St_et_danweiyoubian = "";
				// DialogUtil.showDialogOneButton2(
				// XiaoJinYingChangeOtherInfoActivity_2.this, "请输入单位地址邮编");
				// return;
			}
			new Info2TiJiaoAsyncTask().execute(
					Contents.xiaoChaXunResult2.getCardNo(),
					Contents.xiaoChaXunResult2.getIdNo(), St_et_homeaddress,
					St_et_homeyoubian, St_et_danweidizhi, St_et_danweiyoubian,
					sendTyp, contAddrHj, addrHjZip, contAddrFc, addrFcZip

			);
			break;
		case R.id.back:
			back.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					finish();
				}
			});
			break;
		default:
			break;
		}

	}

	/**
	 * 获取信息
	 */

	class Info2HuoQuAsyncTask extends AsyncTask<String, Object, Object> {
		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(XiaoJinYingChangeOtherInfoActivity_2.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("cardNo", params[0]));
			arg.add(new BasicNameValuePair("idNo", params[1]));

			// private EditText et_danweiyoubian;// 单位地址邮编

			return httpHelper.post(ContantsAddress.XiaoJingYingdizhiHuoQu, arg,
					XIaoaddressHuoQuResponse.class);
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
			registerResponse2(result);
		}
	}

	private void registerResponse2(Object result) {
		XIaoaddressHuoQuResponse response2 = (XIaoaddressHuoQuResponse) result;
		if (response2 == null) {
			DialogUtil.showDialogOneButton2(
					XiaoJinYingChangeOtherInfoActivity_2.this, "服务器请求异常!");
			return;
		} else {
			if (0 == response2.getCode()) {

				contAddrJt = response2.getResult().getGetAddressBody()
						.getContAddrJt();
				addrJtZip = response2.getResult().getGetAddressBody()
						.getAddrJtZip();
				contAddrGs = response2.getResult().getGetAddressBody()
						.getContAddrGs();
				addrGsZip = response2.getResult().getGetAddressBody()
						.getAddrGsZip();

				sendTyp = response2.getResult().getGetAddressBody()
						.getSendTyp();
				contAddrHj = response2.getResult().getGetAddressBody()
						.getContAddrHj();
				addrHjZip = response2.getResult().getGetAddressBody()
						.getAddrHjZip();
				contAddrFc = response2.getResult().getGetAddressBody()
						.getContAddrFc();
				addrFcZip = response2.getResult().getGetAddressBody()
						.getAddrFcZip();

				et_homeaddress.setText(contAddrJt);
				// 家厅地址
				et_homeyoubian.setText(addrJtZip);
				// 邮编
				et_danweidizhi.setText(contAddrGs);
				// 单位地址
				et_danweiyoubian.setText(addrGsZip);
				// 单位地址邮编

			} else {
				DialogUtil.showDialogOneButton2(
						XiaoJinYingChangeOtherInfoActivity_2.this,
						response2.getMsg());
			}
		}
	}

	// cardNo
	// 证件号码 idNo 必填
	// 账单地址类型 sendTyp 00 居住地址01 公司地址02 房产地址03 户籍地址
	// 家庭地址 contAddrJt
	// 家庭地址邮编 addrJtZip
	// 户籍地址 contAddrHj
	// 户籍地址邮编 addrHjZip
	// 房产地址 contAddrFc
	// 房产地址邮编 addrFcZip
	// 公司地址 contAddrGs
	// 公司地址邮编 addrGsZip
	/**
	 * 提交信息
	 */
	class Info2TiJiaoAsyncTask extends AsyncTask<String, Object, Object> {
		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(XiaoJinYingChangeOtherInfoActivity_2.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("cardNo", params[0]));
			arg.add(new BasicNameValuePair("idNo", params[1]));
			//
			arg.add(new BasicNameValuePair("contAddrJt", params[2]));
			arg.add(new BasicNameValuePair("addrJtZip", params[3]));
			arg.add(new BasicNameValuePair("contAddrGs", params[4]));
			arg.add(new BasicNameValuePair("addrGsZip", params[5]));
			//
			arg.add(new BasicNameValuePair("sendTyp", params[6]));
			arg.add(new BasicNameValuePair("contAddrHj", params[7]));
			arg.add(new BasicNameValuePair("addrHjZip", params[8]));
			arg.add(new BasicNameValuePair("contAddrFc", params[9]));
			arg.add(new BasicNameValuePair("addrFcZip", params[10]));
			// private EditText et_homeaddress;// 家厅地址
			// private EditText et_homeyoubian;// 邮编
			// private EditText et_danweidizhi;// 单位地址

			return httpHelper.post(ContantsAddress.XiaoJingYingdizhiTiJiao,
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
			registerResponse3(result);
		}
	}

	private void registerResponse3(Object result) {
		Response response3 = (Response) result;
		if (response3 == null) {
			DialogUtil.showDialogOneButton2(
					XiaoJinYingChangeOtherInfoActivity_2.this, "服务器请求异常!");
			return;
		} else {
			if (0 == response3.getCode()) {
				DialogUtil.showDialogOneButton(
						XiaoJinYingChangeOtherInfoActivity_2.this,

						"提交成功！", new OnClickListener() {

							@Override
							public void onClick(View v) {

								finish();

							}

						});
			} else {
				DialogUtil.showDialogOneButton2(
						XiaoJinYingChangeOtherInfoActivity_2.this,
						response3.getMsg());
			}
		}
	}
}
