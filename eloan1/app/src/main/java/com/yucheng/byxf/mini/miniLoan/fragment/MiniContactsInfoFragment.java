package com.yucheng.byxf.mini.miniLoan.fragment;

import com.yucheng.apfx.utl.RegexResult;
import com.yucheng.byxf.mini.domain.MiniPersonInfo;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.RegexCust;
import com.yucheng.byxf.mini.utils.SharedPreferencesUtils;
import com.yucheng.byxf.mini.utils.RegexUtils2.Result;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * 联系人信息Fragment
 * 
 */
public class MiniContactsInfoFragment extends Fragment implements
		OnClickListener {

	private RegexResult result;
	private ImageView back5;

	// *********后添加。。接口暂无信息 亲属和非亲属联系人工作单位 必填
	// 亲属 工作单位
	private EditText edit_contact_work_name_val;
	// 非亲属 工作单位
	private EditText edit_contact_work_name_val2;

	// 直系亲属联系人
	private EditText edit_contact_name_value;
	// 与申请人关系
	private Spinner contact_relation_group1;
	// 住宅电话-区号
	private EditText edit_phone_no_value1;
	// 住宅电话-号码
	private EditText edit_phone_no_value2;
	// 手机号
	private EditText edit_mobile_no_value1;
	// 非亲属联系人姓名
	private EditText edit_contact_name_value2;
	// 非亲属联系人与亲属联系人关系
	private Spinner contact_relation_group2;
	// 非亲属联系人住宅电话区号
	private EditText edit_phone_no_value3;
	// 非亲属联系人住宅电话
	private EditText edit_phone_no_value4;
	// 非亲属联系人手机
	private EditText edit_mobile_no_value2;
	// 第三个姓名
	private EditText edit_contact_name_value3;
	// 第三个工作单位名
	private EditText edit_contact_work_name_value3;
	// 第三个的关系
	private Spinner contact_relation_group3;
	// 第三个住宅电话区号
	private EditText edit_phone_no_value5;
	// 第三个住宅电话
	private EditText edit_phone_no_value6;
	// 第三个手机号
	private EditText edit_mobile_no_value3;
	// 第四个姓名
	private EditText edit_contact_name_value4;
	// 第四个工作单位名
	private EditText edit_contact_work_name_value4;
	// 第四个的关系
	private Spinner contact_relation_group4;
	// 第四个住宅电话区号
	private EditText edit_phone_no_value7;
	// 第四个住宅电话
	private EditText edit_phone_no_value8;
	// 第四个手机号
	private EditText edit_mobile_no_value4;
	// 下一页
	private Button mini_loan_Contacts_info_next_button;
	// 第三联系人 布局
	private RelativeLayout mobile_no3_ll;
	// 第四联系人 布局
	private RelativeLayout mobile_no4_ll;
	// 基本信息有误
	private String[] relation_group = { "请选择", "配偶", "父母", "子女", "兄弟姐妹" };
	private String[] relation_group2 = { "请选择", "配偶", "父母", "子女", "其他血亲",
			"其他姻亲", "同事", "合伙人", "其他关系", "兄弟姐妹", "同学", "朋友" };
	private String[] relation_group3 = { "请选择", "配偶", "父母", "子女", "其他血亲",
			"其他姻亲", "同事", "合伙人", "其他关系", "兄弟姐妹", "同学", "朋友" };
	private String[] relation_group4 = { "请选择", "配偶", "父母", "子女", "其他血亲",
			"其他姻亲", "同事", "合伙人", "其他关系", "兄弟姐妹", "同学", "朋友" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.minifragment5, container, false);
		back5 = (ImageView) view.findViewById(R.id.back5);
		back5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentManager fm = getFragmentManager();
				fm.popBackStack();
			}
		});
		edit_contact_name_value = (EditText) view
				.findViewById(R.id.edit_contact_name_value);
		contact_relation_group1 = (Spinner) view
				.findViewById(R.id.contact_relation_group1);
		edit_phone_no_value1 = (EditText) view
				.findViewById(R.id.edit_phone_no_value1);
		edit_phone_no_value2 = (EditText) view
				.findViewById(R.id.edit_phone_no_value2);
		edit_mobile_no_value1 = (EditText) view
				.findViewById(R.id.edit_mobile_no_value1);
		edit_contact_name_value2 = (EditText) view
				.findViewById(R.id.edit_contact_name_value2);
		contact_relation_group2 = (Spinner) view
				.findViewById(R.id.contact_relation_group2);
		edit_phone_no_value3 = (EditText) view
				.findViewById(R.id.edit_phone_no_value3);
		edit_phone_no_value4 = (EditText) view
				.findViewById(R.id.edit_phone_no_value4);
		edit_mobile_no_value2 = (EditText) view
				.findViewById(R.id.edit_mobile_no_value2);
		edit_contact_name_value3 = (EditText) view
				.findViewById(R.id.edit_contact_name_value3);
		contact_relation_group3 = (Spinner) view
				.findViewById(R.id.contact_relation_group3);
		edit_phone_no_value5 = (EditText) view
				.findViewById(R.id.edit_phone_no_value5);
		edit_phone_no_value6 = (EditText) view
				.findViewById(R.id.edit_phone_no_value6);
		edit_mobile_no_value3 = (EditText) view
				.findViewById(R.id.edit_mobile_no_value3);
		edit_contact_name_value4 = (EditText) view
				.findViewById(R.id.edit_contact_name_value4);
		contact_relation_group4 = (Spinner) view
				.findViewById(R.id.contact_relation_group4);
		edit_phone_no_value7 = (EditText) view
				.findViewById(R.id.edit_phone_no_value7);
		edit_phone_no_value8 = (EditText) view
				.findViewById(R.id.edit_phone_no_value8);
		edit_mobile_no_value4 = (EditText) view
				.findViewById(R.id.edit_mobile_no_value4);
		mini_loan_Contacts_info_next_button = (Button) view
				.findViewById(R.id.mini_loan_Contacts_info_next_button);
		mini_loan_Contacts_info_next_button.setOnClickListener(this);
		edit_contact_work_name_value3 = (EditText) view
				.findViewById(R.id.edit_contact_work_name_value3);
		edit_contact_work_name_value4 = (EditText) view
				.findViewById(R.id.edit_contact_work_name_value4);
		mobile_no3_ll = (RelativeLayout) view.findViewById(R.id.mobile_no3_ll);
		mobile_no4_ll = (RelativeLayout) view.findViewById(R.id.mobile_no4_ll);
		edit_contact_work_name_val = (EditText) view
				.findViewById(R.id.edit_contact_work_name_val);
		edit_contact_work_name_val2 = (EditText) view
				.findViewById(R.id.edit_contact_work_name_val2);
		setOnItemSelected(contact_relation_group1);
		setOnItemSelected(contact_relation_group2);
		setOnItemSelected(contact_relation_group3);
		setOnItemSelected(contact_relation_group4);
		setSpinner(getActivity(), relation_group, contact_relation_group1);
		setSpinner(getActivity(), relation_group2, contact_relation_group2);
		setSpinner(getActivity(), relation_group3, contact_relation_group3);
		setSpinner(getActivity(), relation_group4, contact_relation_group4);

		initinfo();

		return view;
	}

	private void initinfo() {
		// TODO Auto-generated method stub
		SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
				getActivity(), "MiniFour");
		MiniApplyInfo applyinfo = preferencesUtils.readObj();
		if (applyinfo != null) {
			if (applyinfo.getIdCard().equals(
					Contents.response.getResult().getIdNo())) {
				// ----------第一联系人
				edit_contact_name_value.setText(applyinfo.getIndivRelName());
				edit_contact_work_name_val.setText(applyinfo.getDanweiOne());
				edit_contact_work_name_val2.setText(applyinfo.getDanweiTwo());
				short onettype = applyinfo.getIndivRelation();
				Log.d("xxx", onettype + "==========");
				contact_relation_group1.setSelection(onettype);
				edit_phone_no_value1.setText(applyinfo.getIndivRelZone());
				edit_phone_no_value2.setText(applyinfo.getIndivRelPhone());
				edit_mobile_no_value1.setText(applyinfo.getIndivRelMobile());
				if (!edit_contact_name_value.getText().equals("")) {
					edit_mobile_no_value1
							.addTextChangedListener(new TextWatcher() {

								@Override
								public void onTextChanged(CharSequence s,
										int start, int before, int count) {
									// TODO Auto-generated method stub

								}

								@Override
								public void beforeTextChanged(CharSequence s,
										int start, int count, int after) {
									// TODO Auto-generated method stub

								}

								@Override
								public void afterTextChanged(Editable s) {
									// TODO Auto-generated method stub
									String oldName = Contents.custInfoResponseResult
											.getResult().getCustName();
									String oldPhone = Contents.custInfoResponseResult
											.getResult().getIndivMobile();
									if (edit_contact_name_value.getText()
											.toString().trim().equals(oldName)
											&& edit_mobile_no_value1.getText()
													.toString().trim()
													.equals(oldPhone)) {
										mobile_no3_ll.setVisibility(View.GONE);
										mobile_no4_ll.setVisibility(View.GONE);
									}
								}
							});
				}
				// ------------第二联系人
				edit_contact_name_value2.setText(applyinfo.getIndivOthName());
				short twottype = applyinfo.getIndivOthRel();
				contact_relation_group2.setSelection(twottype);
				edit_phone_no_value3.setText(applyinfo.getIndivOthZone());
				edit_phone_no_value4.setText(applyinfo.getIndivOthPhone());
				edit_mobile_no_value2.setText(applyinfo.getIndivOthMobile());
				// ------------第三联系人
				edit_contact_name_value3.setText(applyinfo.getIndivThiName());
				edit_contact_work_name_value3.setText(applyinfo
						.getIndivThiEmp());
				// short Threettype = applyinfo.getIndivOthRel();
				// if (Threettype == 06) {
				// contact_relation_group3.setSelection(1);
				// } else if (Threettype == 10) {
				// contact_relation_group3.setSelection(2);
				// } else if (Threettype == 11) {
				// contact_relation_group3.setSelection(3);
				// } else if (Threettype == -1) {
				// contact_relation_group3.setSelection(0);
				// } else {
				// contact_relation_group3.setSelection(4);
				// }
				short Threettype = applyinfo.getIndivOthRel();
				contact_relation_group3.setSelection(Threettype);
				// }
				Log.d("xxxxxx", Threettype + "============");
				contact_relation_group3.setSelection((int) Threettype);
				edit_phone_no_value5.setText(applyinfo.getIndivThiZone());
				edit_phone_no_value6.setText(applyinfo.getIndivThiPhone());
				edit_mobile_no_value3.setText(applyinfo.getIndivThiMobile());
				// ------------第四联系人
				edit_contact_name_value4.setText(applyinfo.getIndivFouName());
				edit_contact_work_name_value4.setText(applyinfo
						.getIndivFouEmp());
				short Fourtype = applyinfo.getIndivThiFouation();
				contact_relation_group4.setSelection(Fourtype);
				edit_phone_no_value7.setText(applyinfo.getIndivFouZone());
				edit_phone_no_value8.setText(applyinfo.getIndivFouPhone());
				edit_mobile_no_value4.setText(applyinfo.getIndivFouMobile());
			}
		} else {
			// 身份证号不匹配 删除原有的数据
			preferencesUtils.clearData();
		}

	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	private void saveInfo() {
		MiniApplyInfo saveapplyinfo = new MiniApplyInfo();
		saveapplyinfo.setIdCard(Contents.response.getResult().getIdNo());
		// ----------第一联系人
		saveapplyinfo.setIndivRelName(edit_contact_name_value.getText()
				.toString());
		saveapplyinfo.setDanweiOne(edit_contact_work_name_val.getText()
				.toString());
		saveapplyinfo.setDanweiTwo(edit_contact_work_name_val2.getText()
				.toString());
		Log.d("xxx", "第一联系人选择的ID" + contact_relation_group1.getSelectedItemId());
		switch ((int) contact_relation_group1.getSelectedItemId()) {
		case 0:
			saveapplyinfo.setIndivRelation((short) -1);
			break;
		case 1:
			saveapplyinfo.setIndivRelation((short) 1);
			break;
		case 2:
			saveapplyinfo.setIndivRelation((short) 2);
			break;
		case 3:
			saveapplyinfo.setIndivRelation((short) 3);
			break;
		case 4:
			saveapplyinfo.setIndivRelation((short) 9);
			break;
		default:
			break;
		}
		saveapplyinfo
				.setIndivRelZone(edit_phone_no_value1.getText().toString());
		saveapplyinfo.setIndivRelPhone(edit_phone_no_value2.getText()
				.toString());
		saveapplyinfo.setIndivRelMobile(edit_mobile_no_value1.getText()
				.toString());
		// ------------第二联系人
		saveapplyinfo.setIndivOthName(edit_contact_name_value2.getText()
				.toString());
		saveapplyinfo.setIndivOthRel((short) contact_relation_group2
				.getSelectedItemId());
		saveapplyinfo
				.setIndivOthZone(edit_phone_no_value3.getText().toString());
		saveapplyinfo.setIndivOthPhone(edit_phone_no_value4.getText()
				.toString());
		saveapplyinfo.setIndivOthMobile(edit_mobile_no_value2.getText()
				.toString());
		// ------------第三联系人
		saveapplyinfo.setIndivThiName(edit_contact_name_value3.getText()
				.toString());
		saveapplyinfo.setIndivThiEmp(edit_contact_work_name_value3.getText()
				.toString());
		saveapplyinfo.setIndivThiRelation((short) contact_relation_group3
				.getSelectedItemId());
		saveapplyinfo
				.setIndivThiZone(edit_phone_no_value5.getText().toString());
		saveapplyinfo.setIndivThiPhone(edit_phone_no_value6.getText()
				.toString());
		saveapplyinfo.setIndivThiMobile(edit_mobile_no_value3.getText()
				.toString());
		// ------------第四联系人
		saveapplyinfo.setIndivFouName(edit_contact_name_value4.getText()
				.toString());

		saveapplyinfo.setIndivFouEmp(edit_contact_work_name_value4.getText()
				.toString());
		saveapplyinfo.setIndivThiFouation((short) contact_relation_group4
				.getSelectedItemId());
		saveapplyinfo
				.setIndivFouZone(edit_phone_no_value7.getText().toString());
		saveapplyinfo.setIndivFouPhone(edit_phone_no_value8.getText()
				.toString());
		saveapplyinfo.setIndivFouMobile(edit_mobile_no_value4.getText()
				.toString());
		SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
				getActivity(), "MiniFour");
		preferencesUtils.saveObj(saveapplyinfo);

	}

	private void setSpinner(Context mContext, String[] array, Spinner sp) {
		// TODO Auto-generated method stub

		ArrayAdapter<String> commSpinnerAdapter = null;
		commSpinnerAdapter = new ArrayAdapter<String>(mContext,
				R.layout.simple_spinner_item, array);
		commSpinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_item);
		sp.setAdapter(commSpinnerAdapter);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.mini_loan_Contacts_info_next_button:
			RegexResult temp = null;
			// // 住宅电话-区号
			String phone_no_value1 = edit_phone_no_value1.getText().toString();
			// // 住宅电话-号码
			String phone_no_value2 = edit_phone_no_value2.getText().toString();

			// // 手机号
			String mobile_no_value1 = edit_mobile_no_value1.getText()
					.toString();
			// // 非亲属联系人姓名
			String contact_name_value2String = edit_contact_name_value2
					.getText().toString();
			// // 非亲属联系人与亲属联系人关系
			// private Spinner contact_relation_group2;
			// // 非亲属联系人住宅电话区号
			String phone_no_value3 = edit_phone_no_value3.getText().toString();
			// // 非亲属联系人住宅电话
			String phone_no_value4String = edit_phone_no_value4.getText()
					.toString();
			// // 非亲属联系人手机
			String mobile_no_value2 = edit_mobile_no_value2.getText()
					.toString();
			// // 第三个姓名
			String contact_name_value3 = edit_contact_name_value3.getText()
					.toString();
			// //第三个工作单位名
			String contact_work_name_value3 = edit_contact_work_name_value3
					.getText().toString();
			// // 第三个的关系
			// private Spinner contact_relation_group3;
			// // 第三个住宅电话区号
			String phone_no_value5 = edit_phone_no_value5.getText().toString();
			// // 第三个住宅电话
			String phone_no_value6 = edit_phone_no_value6.getText().toString();
			// // 第三个手机号
			String mobile_no_value3 = edit_mobile_no_value3.getText()
					.toString();
			// // 第四个姓名
			String contact_name_value4 = edit_contact_name_value4.getText()
					.toString();
			// //第四个工作单位名
			String contact_work_name_value4 = edit_contact_work_name_value4
					.getText().toString();
			// // 第四个的关系
			// private Spinner contact_relation_group4;
			// // 第四个住宅电话区号
			String phone_no_value7 = edit_phone_no_value7.getText().toString();
			// // 第四个住宅电话
			String phone_no_value8 = edit_phone_no_value8.getText().toString();
			// // 第四个手机号
			String mobile_no_value4 = edit_mobile_no_value4.getText()
					.toString();

			// 直系亲属联系人
			String contact_name_value = edit_contact_name_value.getText()
					.toString();
			temp = RegexCust.length("姓名", contact_name_value, 2, 30);
			if (temp.match) {
				temp = RegexCust.chinese("姓名", contact_name_value);
				if (!temp.match) {
					result = new RegexResult(false, "直系亲属姓名应为汉字!");
					showEditDialog(v, result.msg);
					return;
				}
			} else {
				if (contact_name_value.length() == 0) {
					result = new RegexResult(false, "直系亲属联系人姓名不能为空!");
				} else {
					result = new RegexResult(false, "直系亲属姓名字符长度不对!");
				}

				showEditDialog(v, result.msg);
				return;
			}

			String danweiOnee = edit_contact_work_name_val.getText().toString();
			if (danweiOnee.equals("")) {
				showEditDialog(v, "亲属联系人工作单位不能为空！");
				return;
			}

			// 直系亲属联系人与申请人关系
			if ("请选择".equals(contact_relation_group1.getSelectedItem()
					.toString())) {
				result = new RegexResult(false, "请选择直系亲属联系人与申请人关系!");
				showEditDialog(v, result.msg);
				return;
			}

			// 直系亲属住宅电话区号
			if (phone_no_value1 != null && !phone_no_value1.equals("")) {
				if (!RegexCust.numberNatural(phone_no_value1)) {
					result = new RegexResult(false, "直系亲属住宅电话区号格式不对!");
					showEditDialog(v, result.msg);
					edit_phone_no_value1.requestFocus();
					return;
				} else {
					if (!TextUtils.isEmpty(phone_no_value1) && phone_no_value1.length() < 3) {
						result = new RegexResult(false, "直系亲属住宅电话区号长度只能是3到4位!");
						showEditDialog(v, result.msg);
						edit_phone_no_value1.requestFocus();
						return;
					}
				}
			}

			// 直系亲属住宅电话号码
			if (phone_no_value2 != null && !phone_no_value2.equals("")) {
				temp = RegexCust.required("住宅电话号码", phone_no_value2);
				if (temp.match) {
					if (RegexCust.phoneFix(phone_no_value2) == false) {
						result = new RegexResult(false, "直系亲属住宅电话号码格式不对!");
						showEditDialog(v, result.msg);
						edit_phone_no_value2.requestFocus();
						return;
					}
				} else {
					result = temp;
					showEditDialog(v, result.msg);
					edit_phone_no_value2.requestFocus();
					return;
				}
			}

			// 直系亲属手机号码
			temp = RegexCust.required("手机号码", mobile_no_value1);
			if (temp.match) {
				if (RegexCust.phoneMob(mobile_no_value1) == false) {
					result = new RegexResult(false, "直系亲属手机号码格式不对!");
					showEditDialog(v, result.msg);
					edit_mobile_no_value1.requestFocus();
					return;
				}
			} else {
				result = temp;
				showEditDialog(v, result.msg);
				edit_mobile_no_value1.requestFocus();
				return;
			}

			// 非直系亲属联系人姓名
			temp = RegexCust.length("姓名", contact_name_value2String, 2, 30);
			if (temp.match) {
				temp = RegexCust.chinese("姓名", contact_name_value2String);
				if (!temp.match) {
					result = new RegexResult(false, "非直系亲属姓名应为汉字!");
					showEditDialog(v, result.msg);
					return;
				}
			} else {
				if (contact_name_value2String.length() == 0) {
					result = new RegexResult(false, "非亲属联系人姓名不能为空!");
				} else {
					result = new RegexResult(false, "非直系亲属姓名字符长度不对!");
				}
				showEditDialog(v, result.msg);
				return;
			}
			String danweiTwoo = edit_contact_work_name_val2.getText()
					.toString();
			if (danweiTwoo.equals("")) {
				showEditDialog(v, "非亲属联系人单位名称不能为空！");
				return;
			}

			// 非直系亲属联系人与申请人关系
			if ("请选择".equals(contact_relation_group2.getSelectedItem()
					.toString())) {
				result = new RegexResult(false, "非直系亲属联系人与申请人关系!");
				showEditDialog(v, result.msg);
				return;
			}

			// 非直系亲属住宅电话区号
			if (phone_no_value3 != null && !phone_no_value3.equals("")) {
				if (!RegexCust.numberNatural(phone_no_value3)) {
					result = new RegexResult(false, "非直系亲属住宅电话区号格式不对!");
					showEditDialog(v, result.msg);
					edit_phone_no_value3.requestFocus();
					return;
				} else {
					if (!TextUtils.isEmpty(phone_no_value3) && phone_no_value3.length() < 3) {
						result = new RegexResult(false, "住宅电话区号长度只能是3到4位!");
						showEditDialog(v, result.msg);
						edit_phone_no_value3.requestFocus();
						return;
					}
				}
			}
			// 非直系亲属住宅电话号码
			if (phone_no_value8 != null && !phone_no_value8.equals("")) {
				temp = RegexCust.required("住宅电话号码", phone_no_value8);
				if (temp.match) {
					if (RegexCust.phoneFix(phone_no_value8) == false) {
						result = new RegexResult(false, "非直系亲属住宅电话号码格式不对!");
						showEditDialog(v, result.msg);
						edit_phone_no_value4.requestFocus();
						return;
					}
				} else {
					result = temp;
					showEditDialog(v, result.msg);
					edit_phone_no_value4.requestFocus();
					return;
				}
			}

			// 非直系亲属手机号码
			temp = RegexCust.required("手机号码", mobile_no_value2);
			if (temp.match) {
				if (RegexCust.phoneMob(mobile_no_value2) == false) {
					result = new RegexResult(false, "非直系亲属手机号码格式不对!");
					showEditDialog(v, result.msg);
					edit_mobile_no_value2.requestFocus();
					return;
				}
			} else {
				result = temp;
				showEditDialog(v, result.msg);
				edit_mobile_no_value2.requestFocus();
				return;
			}

			if (!"".equals(contact_name_value3) || !"".equals(phone_no_value5)
					|| !"".equals(phone_no_value6)
					|| !"".equals(mobile_no_value3)) {
				// 非直系亲属联系人姓名
				temp = RegexCust.length("姓名", contact_name_value3, 2, 30);
				if (temp.match) {
					temp = RegexCust.chinese("姓名", contact_name_value3);
					if (!temp.match) {
						result = new RegexResult(false, "非直系亲属姓名应为汉字!");
						showEditDialog(v, result.msg);
						return;
					}
				} else {
					if (contact_name_value3.length() == 0) {
						result = new RegexResult(false, "非亲属联系人姓名不能为空!");
					} else {
						result = new RegexResult(false, "非直系亲属姓名字符长度不对!");
					}
					showEditDialog(v, result.msg);
					return;
				}

				// 非直系亲属联系人与申请人关系
				if ("请选择".equals(contact_relation_group3.getSelectedItem()
						.toString())) {
					result = new RegexResult(false, "非直系亲属联系人与申请人关系!");
					showEditDialog(v, result.msg);
					return;
				}

				// 非直系亲属住宅电话区号
				if (phone_no_value5 != null && !phone_no_value5.equals("")) {
					if (!RegexCust.numberNatural(phone_no_value5)) {
						result = new RegexResult(false, "非直系亲属住宅电话区号格式不对!");
						showEditDialog(v, result.msg);
						edit_phone_no_value5.requestFocus();
						return;
					} else {
						if (!TextUtils.isEmpty(phone_no_value5) && phone_no_value5.length() < 3) {
							result = new RegexResult(false, "住宅电话区号长度只能是3到4位!");
							showEditDialog(v, result.msg);
							edit_phone_no_value5.requestFocus();
							return;
						}
					}
				}
				// 非直系亲属住宅电话号码
				if (phone_no_value6 != null && !phone_no_value6.equals("")) {
					temp = RegexCust.required("住宅电话号码", phone_no_value6);
					if (temp.match) {
						if (RegexCust.phoneFix(phone_no_value6) == false) {
							result = new RegexResult(false, "非直系亲属住宅电话号码格式不对!");
							showEditDialog(v, result.msg);
							edit_phone_no_value6.requestFocus();
							return;
						}
					} else {
						result = temp;
						showEditDialog(v, result.msg);
						edit_phone_no_value6.requestFocus();
						return;
					}
				}

				// 非直系亲属手机号码
				temp = RegexCust.required("手机号码", mobile_no_value3);
				if (temp.match) {
					if (RegexCust.phoneMob(mobile_no_value3) == false) {
						result = new RegexResult(false, "非直系亲属手机号码格式不对!");
						showEditDialog(v, result.msg);
						edit_mobile_no_value3.requestFocus();
						return;
					}
				} else {
					result = temp;
					showEditDialog(v, result.msg);
					edit_mobile_no_value3.requestFocus();
					return;
				}
			}

			if (!"".equals(contact_name_value4) || !"".equals(phone_no_value7)
					|| !"".equals(phone_no_value8)
					|| !"".equals(mobile_no_value4)) {
				// 非直系亲属联系人姓名
				temp = RegexCust.length("姓名", contact_name_value4, 2, 30);
				if (temp.match) {
					temp = RegexCust.chinese("姓名", contact_name_value4);
					if (!temp.match) {
						result = new RegexResult(false, "非直系亲属姓名应为汉字!");
						showEditDialog(v, result.msg);
						return;
					}
				} else {
					if (contact_name_value4.length() == 0) {
						result = new RegexResult(false, "非亲属联系人姓名不能为空!");
					} else {
						result = new RegexResult(false, "非直系亲属姓名字符长度不对!");
					}
					showEditDialog(v, result.msg);
					return;
				}
				// 第三联系人工作单位名称
				String threetext = edit_contact_work_name_value3.getText()
						.toString().trim();
				if (threetext.equals("")) {
					showEditDialog(v, "第三联系人工作单位名称能为空！");
					edit_contact_work_name_value3.requestFocus();
					return;
				}

				// 非直系亲属联系人与申请人关系
				if ("请选择".equals(contact_relation_group4.getSelectedItem()
						.toString())) {
					result = new RegexResult(false, "非直系亲属联系人与申请人关系!");
					showEditDialog(v, result.msg);
					return;
				}

				// 非直系亲属住宅电话区号
				if (phone_no_value7 != null && !phone_no_value7.equals("")) {
					if (!RegexCust.numberNatural(phone_no_value7)) {
						result = new RegexResult(false, "非直系亲属住宅电话区号格式不对!");
						showEditDialog(v, result.msg);
						edit_phone_no_value7.requestFocus();
						return;
					} else {
						if (!TextUtils.isEmpty(phone_no_value7) && phone_no_value7.length() < 3) {
							result = new RegexResult(false, "住宅电话区号长度只能是3到4位!");
							showEditDialog(v, result.msg);
							edit_phone_no_value7.requestFocus();
							return;
						}
					}
				}
				// 非直系亲属住宅电话号码
				if (phone_no_value8 != null && !phone_no_value8.equals("")) {
					temp = RegexCust.required("住宅电话号码", phone_no_value8);
					if (temp.match) {
						if (RegexCust.phoneFix(phone_no_value8) == false) {
							result = new RegexResult(false, "非直系亲属住宅电话号码格式不对!");
							showEditDialog(v, result.msg);
							edit_phone_no_value8.requestFocus();
							return;
						}
					} else {
						result = temp;
						showEditDialog(v, result.msg);
						edit_phone_no_value8.requestFocus();
						return;
					}
				}

				// 非直系亲属手机号码
				temp = RegexCust.required("手机号码", mobile_no_value4);
				if (temp.match) {
					if (RegexCust.phoneMob(mobile_no_value4) == false) {
						result = new RegexResult(false, "非直系亲属手机号码格式不对!");
						showEditDialog(v, result.msg);
						edit_mobile_no_value4.requestFocus();
						return;
					}
				} else {
					result = temp;
					showEditDialog(v, result.msg);
					edit_mobile_no_value4.requestFocus();
					return;
				}
				// 第四联系人工作单位名称
				String fourtext = edit_contact_work_name_value4.getText()
						.toString().trim();
				if (fourtext.equals("")) {
					showEditDialog(v, "第四联系人工作单位名称不能为空！");
					edit_contact_work_name_value4.requestFocus();
					return;
				}
				String nameOne = edit_contact_name_value.getText().toString()
						.trim();
				String nameTwo = edit_contact_name_value2.getText().toString()
						.trim();
				String nameThr = edit_contact_name_value3.getText().toString()
						.trim();
				String nameFou = edit_contact_name_value4.getText().toString()
						.trim();
				if (nameOne.equals(nameTwo)) {
					showEditDialog(v, "非亲属联系人姓名不能和亲属姓名相同！");
					edit_contact_name_value2.requestFocus();
					return;
				} else if (nameOne.equals(nameThr)) {
					showEditDialog(v, "第三联系人姓名不能和亲属姓名相同！");
					edit_contact_name_value3.requestFocus();
					return;
				} else if (nameOne.equals(nameFou)) {
					showEditDialog(v, "第四联系人姓名不能和亲属姓名相同！");
					edit_contact_name_value4.requestFocus();
					return;
				} else if (nameTwo.equals(nameThr)) {
					showEditDialog(v, "第三联系人姓名不能和非亲属联系人姓名相同！");
					edit_contact_name_value3.requestFocus();
					return;
				} else if (nameThr.equals(nameFou)) {
					showEditDialog(v, "第四联系人姓名不能和第三联系人相同！");
					edit_contact_name_value4.requestFocus();
					return;
				}
				String mobileOne = edit_mobile_no_value1.getText().toString()
						.trim();
				String mobileTwo = edit_mobile_no_value2.getText().toString()
						.trim();
				String mobileThr = edit_mobile_no_value3.getText().toString()
						.trim();
				String mobileFou = edit_mobile_no_value4.getText().toString()
						.trim();
				if (mobileOne.equals(mobileTwo)) {
					showEditDialog(v, "非亲属联系人手机号不能和亲属手机号相同！");
					edit_mobile_no_value2.requestFocus();
					return;
				} else if (mobileOne.equals(mobileThr)) {
					showEditDialog(v, "第三联系人手机号不能和亲属手机号相同！");
					edit_mobile_no_value3.requestFocus();
					return;
				} else if (mobileOne.equals(mobileFou)) {
					showEditDialog(v, "第四联系人手机号不能和亲属手机号相同！");
					edit_mobile_no_value4.requestFocus();
					return;
				} else if (mobileTwo.equals(mobileThr)) {
					showEditDialog(v, "第三联系人手机号不能和非亲属联系人手机号相同！");
					edit_mobile_no_value3.requestFocus();
					return;
				} else if (mobileThr.equals(mobileFou)) {
					showEditDialog(v, "第四联系人手机号不能和第三联系人手机号相同！");
					edit_mobile_no_value4.requestFocus();
					return;
				}
				// MiniStatementFragment fSix = new MiniStatementFragment();
				// FragmentManager fm = getFragmentManager();
				// FragmentTransaction tx = fm.beginTransaction();
				// tx.replace(R.id.id_content, fSix, "Six");
				// tx.remove(fm.findFragmentByTag("Five"));
				// tx.addToBackStack("Five");
				// tx.commit();
				saveInfo();
				MiniStatementFragment fSix = new MiniStatementFragment();
				FragmentManager fm = getFragmentManager();
				FragmentTransaction tx = fm.beginTransaction();
				tx.replace(R.id.id_content, fSix, "Six");
				// tx.remove(fm.findFragmentByTag("Five"));
				tx.addToBackStack("Five");
				tx.commit();
				break;
			}
		default:
			break;
		}
	}

	public void showEditDialog(View view, String str) {
		DialogFragment myFragment = NameDialogFragment.newInstance(str);
		myFragment.show(getFragmentManager(), "EditNameDialog");
	}

	private void setOnItemSelected(Spinner sp) {
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Spinner spin = (Spinner) parent;
				if (position != 0) {
					spin.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
				} else {
					spin.setBackgroundResource(R.drawable.comm_spinner_selecter);
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
	}
}
