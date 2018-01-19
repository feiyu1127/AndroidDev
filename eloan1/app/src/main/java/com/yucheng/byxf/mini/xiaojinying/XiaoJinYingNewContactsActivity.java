package com.yucheng.byxf.mini.xiaojinying;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.message.ApplicationScheduleQueryResponse;
import com.yucheng.byxf.mini.message.Response;
import com.yucheng.byxf.mini.message.XiaojinYingRelationInfoResult;
import com.yucheng.byxf.mini.message.XiaojinYingRelationInfoResultBodyList;
import com.yucheng.byxf.mini.ui.BaseActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.CommonUtil;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;

/**
 * 新增联系人
 */
public class XiaoJinYingNewContactsActivity extends BaseActivity {

	private String[] apply_relationship = { "配偶", "父母", "子女", "其他血亲", "其他姻亲",
			"同事", "合伙人", "其他关系", "兄弟姐妹", "同学", "朋友" };
	private String[] apply_relationshipCode = { "01", "02", "03", "04", "05",
			"06", "07", "08", "09", "10", "11" };

	// 01 配偶02 父母03 子女04 其他血亲05 其他姻亲06
	// 同事07 合伙人08 其他关系09 兄弟姐妹10 同学11 朋友
	XiaojinYingRelationInfoResultBodyList resp;
	private EditText et_new_contactsName;// 姓名
	private EditText et_new_contactsMobile;// 手机
	private EditText et_new_contactsPhone1;// 区号
	private EditText et_new_contactsPhone2;// 电话
	private TextView sp_new_contactsType;// 类型
	private Spinner sp_new_contactsRelationship;// 关系

	private String St_new_contactsName = "";// 姓名
	private String St_new_contactsMobile = "";// 手机
	private String St_new_contactsPhone1 = "";// 区号
	private String St_new_contactsPhone2 = "";// 电话
	private String Sp_new_contactsType = "";// 类型
	private String Sp_new_contactsRelationship = "";
	private String zhong = "";
	private Button caocun_button;
	private int pos = -1;
	private List<XiaojinYingRelationInfoResultBodyList> RealitionInfo2 = new ArrayList<XiaojinYingRelationInfoResultBodyList>();
	private ArrayList<XiaojinYingRelationInfoResultBodyList> listObj = new ArrayList<XiaojinYingRelationInfoResultBodyList>();

	private ImageView back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xiaojinyin_newcontacts_activity);

		Intent intent = getIntent();
		pos = intent.getIntExtra("pos", -1);
		pos = intent.getIntExtra("pos", -1);
		switch (pos) {
		case 0:
			apply_relationship = new String[] { "配偶", "父母", "子女", "兄弟姐妹" };
			break;

		default:
			break;
		}

		back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});
		et_new_contactsName = (EditText) findViewById(R.id.et_new_contactsName);
		et_new_contactsMobile = (EditText) findViewById(R.id.et_new_contactsMobile);
		et_new_contactsPhone1 = (EditText) findViewById(R.id.et_new_contactsPhone1);
		et_new_contactsPhone2 = (EditText) findViewById(R.id.et_new_contactsPhone2);
		sp_new_contactsType = (TextView) findViewById(R.id.sp_new_contactsType);
		sp_new_contactsRelationship = (Spinner) findViewById(R.id.sp_new_contactsRelationship);
		CommonUtil.setSpinner(this, apply_relationship,
				sp_new_contactsRelationship);
		caocun_button = (Button) findViewById(R.id.caocun_button);
		Bundle bundle = getIntent().getExtras();
		resp = (XiaojinYingRelationInfoResultBodyList) bundle
				.getSerializable("list");
		listObj = (ArrayList<XiaojinYingRelationInfoResultBodyList>) getIntent()
				.getSerializableExtra("listAll");

		St_new_contactsName = resp.getContName();

		St_new_contactsMobile = resp.getContPhone();
		St_new_contactsPhone1 = resp.getContZone();
		St_new_contactsPhone2 = resp.getContTel();
		Sp_new_contactsRelationship = resp.getContRel();
		Sp_new_contactsType = resp.getContTyp();

		et_new_contactsName.setText(St_new_contactsName);
		et_new_contactsMobile.setText(St_new_contactsMobile);
		et_new_contactsPhone1.setText(St_new_contactsPhone1);
		et_new_contactsPhone2.setText(St_new_contactsPhone2);
		// 01 直系联系人02 其他联系人03 第三联系人04 第四联系人
		if (Sp_new_contactsType.equals("01")) {
			sp_new_contactsType.setText("直系联系人");
		}
		if (Sp_new_contactsType.equals("02")) {
			sp_new_contactsType.setText("其他联系人");
		}
		if (Sp_new_contactsType.equals("03")) {
			sp_new_contactsType.setText("第三联系人");
		}
		if (Sp_new_contactsType.equals("04")) {
			sp_new_contactsType.setText("第四联系人");
		}
		if (Sp_new_contactsRelationship.equals("01")) {
			zhong = ("配偶");
		}
		if (Sp_new_contactsRelationship.equals("02")) {
			zhong = ("父母");
		}
		if (Sp_new_contactsRelationship.equals("03")) {
			zhong = ("子女");
		}
		if (Sp_new_contactsRelationship.equals("04")) {
			zhong = ("其他血亲");
		}
		if (Sp_new_contactsRelationship.equals("05")) {
			zhong = ("其他姻亲");
		}

		if (Sp_new_contactsRelationship.equals("06")) {
			zhong = ("同事");
		}
		if (Sp_new_contactsRelationship.equals("07")) {
			zhong = ("合伙人");
		}

		if (Sp_new_contactsRelationship.equals("08")) {
			zhong = ("其他关系");
		}

		if (Sp_new_contactsRelationship.equals("09")) {
			zhong = ("兄弟姐妹");
		}

		if (Sp_new_contactsRelationship.equals("10")) {
			zhong = ("同学");
		}
		if (Sp_new_contactsRelationship.equals("11")) {
			zhong = ("朋友");
		}

		if (!"".equals(Sp_new_contactsRelationship)) {
			for (int i = 0; i < apply_relationship.length; i++) {
				if (apply_relationship[i].equals(zhong)) {
					sp_new_contactsRelationship.setSelection(i);
					// sp_new_contactsRelationship
					// .setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
				}
			}
		}

		caocun_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 提交！
				St_new_contactsName = et_new_contactsName.getText().toString();
				St_new_contactsMobile = et_new_contactsMobile.getText()
						.toString();
				St_new_contactsPhone1 = et_new_contactsPhone1.getText()
						.toString();
				St_new_contactsPhone2 = et_new_contactsPhone2.getText()
						.toString();

				if (St_new_contactsName == null
						|| St_new_contactsName.equals("")) {
					DialogUtil.showDialogOneButton2(
							XiaoJinYingNewContactsActivity.this, "请输入姓名");
					return;
				}
				if (St_new_contactsMobile == null
						|| St_new_contactsMobile.equals("")) {
					DialogUtil.showDialogOneButton2(
							XiaoJinYingNewContactsActivity.this, "请输入手机号");
					return;
				}
				if (St_new_contactsPhone1 == null
						|| St_new_contactsPhone1.equals("")) {
					// DialogUtil.showDialogOneButton2(
					// XiaoJinYingNewContactsActivity.this, "请输入区号");
					// return;
					St_new_contactsPhone1 = "";
				}
				if (St_new_contactsPhone2 == null
						|| St_new_contactsPhone2.equals("")) {
					// DialogUtil.showDialogOneButton2(
					// XiaoJinYingNewContactsActivity.this, "请输入电话号码");
					// return;
					St_new_contactsPhone2 = "";
				}
				// private String[] apply_relationship = { "配偶", "父母", "子女",
				// "其他血亲", "其他姻亲",
				// "同事", "合伙人", "其他关系", "兄弟姐妹", "同学", "朋友" };
				String guanxi = "";
				int i = sp_new_contactsRelationship.getSelectedItemPosition();
				i = i + 1;
				if (i > 9) {
					guanxi = String.valueOf(i);
				} else {
					guanxi = "0" + String.valueOf(i);
				}
				// 提交！！！！！！！！！！！！！！！！！
				listObj.get(pos).setContName(St_new_contactsName);
				listObj.get(pos).setContTel(St_new_contactsPhone2);
				listObj.get(pos).setContZone(St_new_contactsPhone1);
				listObj.get(pos).setContPhone(St_new_contactsMobile);
				if (guanxi.equals("01")) {
					int count = 0;
					for (int j = 0; j < listObj.size(); j++) {
						if (((String) listObj.get(j).getContRel()).equals("01")
								&& j != pos) {
							count++;
						}
					}
					if (count < 1) {
						listObj.get(pos).setContRel(guanxi);
					} else {
						DialogUtil.showDialogOneButton2(
								XiaoJinYingNewContactsActivity.this,
								"联系人列表中配偶只能选择一次!");
						return;
					}
				} else if (guanxi.equals("02")) {
					int count = 0;
					for (int j = 0; j < listObj.size(); j++) {
						if (((String) listObj.get(j).getContRel()).equals("02")
								&& j != pos) {
							count++;
						}
					}
					if (count < 2) {
						listObj.get(pos).setContRel(guanxi);
					} else {
						DialogUtil.showDialogOneButton2(
								XiaoJinYingNewContactsActivity.this,
								"联系人列表中父母只能选择两次!");
						return;
					}
				} else {
					listObj.get(pos).setContRel(guanxi);
				}

				String s = listObj.get(pos).getContName();
				new ContenInfoUpAsyncTask().execute(Contents.xiaoChaXunResult2
						.getCardNo());
			}
		});
	}

	/**
	 * 上传联系人。。。
	 */
	class ContenInfoUpAsyncTask extends AsyncTask<String, Object, Object> {
		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(XiaoJinYingNewContactsActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("cardNo", params[0]));
			// arg.add(new BasicNameValuePair("idNo", params[1]));
			// 第一联系人
			arg.add(new BasicNameValuePair("details[0].contTyp",
					(String) listObj.get(0).getContTyp()));
			arg.add(new BasicNameValuePair("details[0].contName",
					(String) listObj.get(0).getContName()));
			arg.add(new BasicNameValuePair("details[0].contRel",
					(String) listObj.get(0).getContRel()));
			arg.add(new BasicNameValuePair("details[0].contPhone",
					(String) listObj.get(0).getContPhone()));
			arg.add(new BasicNameValuePair("details[0].contZone",
					(String) listObj.get(0).getContZone()));
			arg.add(new BasicNameValuePair("details[0].contTel",
					(String) listObj.get(0).getContTel()));
			// 第2联系人
			arg.add(new BasicNameValuePair("details[1].contTyp",
					(String) listObj.get(1).getContTyp()));
			arg.add(new BasicNameValuePair("details[1].contName",
					(String) listObj.get(1).getContName()));
			arg.add(new BasicNameValuePair("details[1].contRel",
					(String) listObj.get(1).getContRel()));
			arg.add(new BasicNameValuePair("details[1].contPhone",
					(String) listObj.get(1).getContPhone()));
			arg.add(new BasicNameValuePair("details[1].contZone",
					(String) listObj.get(1).getContZone()));
			arg.add(new BasicNameValuePair("details[1].contTel",
					(String) listObj.get(1).getContTel()));
			// 第3联系人
			arg.add(new BasicNameValuePair("details[2].contTyp",
					(String) listObj.get(2).getContTyp()));
			arg.add(new BasicNameValuePair("details[2].contName",
					(String) listObj.get(2).getContName()));
			arg.add(new BasicNameValuePair("details[2].contRel",
					(String) listObj.get(2).getContRel()));
			arg.add(new BasicNameValuePair("details[2].contPhone",
					(String) listObj.get(2).getContPhone()));
			arg.add(new BasicNameValuePair("details[2].contZone",
					(String) listObj.get(2).getContZone()));
			arg.add(new BasicNameValuePair("details[2].contTel",
					(String) listObj.get(2).getContTel()));
			// 第4联系人
			arg.add(new BasicNameValuePair("details[3].contTyp",
					(String) listObj.get(3).getContTyp()));
			arg.add(new BasicNameValuePair("details[3].contName",
					(String) listObj.get(3).getContName()));
			arg.add(new BasicNameValuePair("details[3].contRel",
					(String) listObj.get(3).getContRel()));
			arg.add(new BasicNameValuePair("details[3].contPhone",
					(String) listObj.get(3).getContPhone()));
			arg.add(new BasicNameValuePair("details[3].contZone",
					(String) listObj.get(3).getContZone()));
			arg.add(new BasicNameValuePair("details[3].contTel",
					(String) listObj.get(3).getContTel()));

			return httpHelper.post(ContantsAddress.XiaoLianXiRenUp, arg,
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
					XiaoJinYingNewContactsActivity.this, "服务器请求异常!");
			return;
		} else {
			if (0 == response3.getCode()) {
				DialogUtil.showDialogOneButton(
						XiaoJinYingNewContactsActivity.this,
						"提交成功", new OnClickListener() {
							@Override
							public void onClick(View v) {
								finish();
							}

						});
			} else {
				DialogUtil
						.showDialogOneButton2(
								XiaoJinYingNewContactsActivity.this,
								response3.getMsg());
			}
		}
	}
}
