package com.yucheng.byxf.mini.ui;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.apfx.utl.RegexResult;
import com.yucheng.byxf.mini.message.AdvanceRepaymentApplyResultSubmitYe;
import com.yucheng.byxf.mini.message.MessageInfoSubmitYe;
import com.yucheng.byxf.mini.ui.AdvanceRepayment4.GetAdvanceSubmit;
import com.yucheng.byxf.mini.utils.CommonUtil;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.RegexCust;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;


import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MessageActivity extends BaseActivity implements
OnItemSelectedListener, OnClickListener, OnWheelChangedListener{
	private String me_type="";
	// 验证结果
	private RegexResult result;
	private String save_cultrue_degree;
	private ImageView back;
	private Button  next_button;
	// 信息有误
	private final int INFO_ERROR = 0;
	private Spinner sp_cultrue;
	private String[] cultrue_degree = { "请选择", "博士", "硕士", "本科", "大专", "高中或中专",
		"初中及以下" };
	private String[] cultrue_degreeCode = { "", "08", "09", "10", "20", "30",
		"40" };
	private RadioGroup rg_marriage_info;
	private EditText et_username,et_idNo, et_cipMobile,
		et_liveAddr ,et_liveAddr2, et_regAddr  ,et_empName ,et_empZone, et_empTelNo, et_empTelSub
		,et_empAddr, et_mthInc;
	
	private String tx_username,tx_idNo, tx_cipMobile,
	tx_liveAddr ,tx_liveAddr2, tx_regAddr  ,tx_empName ,tx_empZone, tx_empTelNo, tx_empTelSub
	,tx_empAddr, tx_mthInc;
	private String tx_marital="";
	private String tx_cultrue="";

	/**
	 * 把全国的省市区的信息以json的格式保存，解析完成后赋值为null
	 */
	private JSONObject mJsonObj;

	private WheelView mProvince;

	private WheelView mCity;

	private WheelView mArea;

	private String[] mProvinceDatas;

	private Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();

	private Map<String, String[]> mAreaDatasMap = new HashMap<String, String[]>();

	private String mCurrentProviceName;

	private String mCurrentCityName;

	private String mCurrentAreaName = "";

	private Button choose_button;
	private LinearLayout city_layout;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_activity);
		initJsonData();
		initView();
		initContents();
	}

	private void initView() {
		// TODO Auto-generated method stub
		back = (ImageView) findViewById(R.id.back);
		next_button=(Button) findViewById(R.id.next_button);
		et_username=(EditText) findViewById(R.id.et_username);//申请人姓名
		et_idNo=(EditText) findViewById(R.id.et_idNo);//身份证号么
		et_cipMobile=(EditText) findViewById(R.id.et_cipMobile);//手机号码
		et_liveAddr=(EditText) findViewById(R.id.et_liveAddr);//现在居住地址
		et_liveAddr2=(EditText) findViewById(R.id.et_liveAddr2);//居住地址2
		et_regAddr=(EditText) findViewById(R.id.et_regAddr); //户籍地址
		et_empName =(EditText) findViewById(R.id.et_empName);//单位名称
		et_empZone=(EditText) findViewById(R.id.et_empZone);//区号
		et_empTelNo=(EditText) findViewById(R.id.et_empTelNo);//电话号码
		et_empTelSub=(EditText) findViewById(R.id.et_empTelSub);//分机号
		et_empAddr=(EditText) findViewById(R.id.et_empAddr);//单位地址
		et_mthInc=(EditText) findViewById(R.id.et_mthInc);//个人工资收入
		//地址选择器
		choose_button = (Button) findViewById(R.id.choose_button);
		city_layout = (LinearLayout) findViewById(R.id.city_layout);
		mProvince = (WheelView) findViewById(R.id.id_province);
		mCity = (WheelView) findViewById(R.id.id_city);
		mArea = (WheelView) findViewById(R.id.id_area);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
			finish();	
			}
		});

		next_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				init();	
//				initNext();
//				 new GetMessageInfo().execute(
//						 tx_username,
//						 tx_idNo,
//						 tx_cipMobile,
//						 tx_liveAddr,
//						 tx_liveAddr2,
//						 tx_regAddr,
//						 tx_empName,
//						 tx_empZone,
//						 tx_empTelNo,
//						 tx_empTelSub,
//						 tx_empAddr,
//						 tx_mthInc,
//						 tx_marital,
//						 tx_cultrue
//						 );
				
			
			}
		});
		//婚姻选择
		rg_marriage_info = (RadioGroup) findViewById(R.id.rg_marriage_info);
		rg_marriage_info
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup arg0, int arg1) {
						switch (arg0.getCheckedRadioButtonId()) {
						case R.id.marital_status_dis_no:
							tx_marital="10";
						//	Contents.response.getResult().setMarital("10");
							System.out.println("-->未婚");
							break;
						case R.id.marital_status_mar_no:
							tx_marital="20";
						//Contents.response.getResult().setMarital("20");
//							application.getBasicInfo().setMarriageStatueCode(
//									"20");
							System.out.println("-->已婚");
							break;
						case R.id.marital_status_other_no:
							System.out.println("-->其它");
							tx_marital="90";
						//	Contents.response.getResult().setMarital("90");
//							application.getBasicInfo().setMarriageStatue("其它");
//							application.getBasicInfo().setMarriageStatueCode(
//									"90");
							break;
						}
					}
				});
		//文化程度
		sp_cultrue = (Spinner) findViewById(R.id.sp_stand_degree);
		CommonUtil.setSpinner(this, cultrue_degree, sp_cultrue);

		sp_cultrue
		.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent,
					View view, int position, long id) {
				save_cultrue_degree = (String) sp_cultrue
						.getItemAtPosition(position);
				// save_stand_degre_code =
				// stand_degrees_codes[position];
				if (!"请选择".equals(save_cultrue_degree)) {
					sp_cultrue
							.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
				} else {
					sp_cultrue
							.setBackgroundResource(R.drawable.comm_spinner_selecter);
				}
				sp_cultrue.setPadding(15, 0, 0,
						0);
				tx_cultrue=cultrue_degreeCode[position];
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		et_liveAddr.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				
				city_layout.setVisibility(View.VISIBLE);
			}
		});
		
		choose_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				city_layout.setVisibility(View.GONE);
				et_liveAddr.setText(mCurrentProviceName + mCurrentCityName
						+ mCurrentAreaName);
			}
		});
		
		
	/*	 if (v == et_com_address) {
				city_layout.setVisibility(View.VISIBLE);
			} else if (v == choose_button) {
				city_layout.setVisibility(View.GONE);
				et_com_address.setText(mCurrentProviceName + mCurrentCityName
						+ mCurrentAreaName);
			}*/
		
	
		initDatas();
		mProvince.setViewAdapter(new ArrayWheelAdapter<String>(this,
				mProvinceDatas));
		// 添加change事件
		mProvince.addChangingListener(this);
		// 添加change事件
		mCity.addChangingListener(this);
		// 添加change事件
		mArea.addChangingListener(this);

		mProvince.setVisibleItems(5);
		mCity.setVisibleItems(5);
		mArea.setVisibleItems(5);
		updateCities();
		updateAreas();

	}

	private void initContents(){
//		private EditText et_username,et_idNo, et_cipMobile,
//		et_liveAddr ,et_liveAddr2, et_regAddr  ,et_empName ,et_empZone, et_empTelNo, et_empTelSub
//		,et_empAddr, et_mthInc;

		if (Contents.response != null && Contents.response.getResult() != null) {
			//	et_username
			if (null != Contents.response.getResult().getCipNamecn()
					&&!"".equals(Contents.response.getResult().getCipNamecn()) 
					) {
				et_username.setText(Contents.response.getResult().getCipNamecn());
			}
			//et_idNo
			if (null != Contents.response.getResult().getIdNo()
					&&!"".equals(Contents.response.getResult().getIdNo()) 
					) {
				et_idNo.setText(Contents.response.getResult().getIdNo());
			}
			//et_cipMobile
			if (null != Contents.response.getResult().getCipMobile()
					&&!"".equals(Contents.response.getResult().getCipMobile()) 
					) {
				et_cipMobile.setText(Contents.response.getResult().getCipMobile());
			}
			//婚姻!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			
			if (!"".equals(Contents.response.getResult().getMarital())&&null != Contents.response.getResult().getMarital()) {
				for (int i = 0; i < rg_marriage_info.getChildCount(); i++) {
					RadioButton radioButton = (RadioButton) rg_marriage_info
							.getChildAt(i);
						String ma="";
					if(Contents.response.getResult().getMarital().equals("10")){
						ma="未婚";
					}else if(Contents.response.getResult().getMarital().equals("20")){
						ma="已婚";
					}else if(Contents.response.getResult().getMarital().equals("90")){
						ma="其它";
					}
					if (radioButton.getText().equals(ma)) {
						radioButton.setChecked(true);
					}
				}
			}
			//et_liveAddr
			if (null != Contents.response.getResult().getLiveAddr()
					&&!"".equals(Contents.response.getResult().getLiveAddr()) 
					) {
				et_liveAddr.setText(Contents.response.getResult().getLiveAddr());
			}
			//et_liveAddr2
			if (null != Contents.response.getResult().getCurentResidentialAddr()
					&&!"".equals(Contents.response.getResult().getCurentResidentialAddr()) 
					) {
				et_liveAddr2.setText(Contents.response.getResult().getCurentResidentialAddr());
			}
			//et_regAddr
			if (null != Contents.response.getResult().getRegAddr()
					&&!"".equals(Contents.response.getResult().getRegAddr()) 
					) {
				et_regAddr.setText(Contents.response.getResult().getRegAddr());
			}
			//文化程度！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
			if(null != Contents.response.getResult().getDegree()
					&&!"".equals(Contents.response.getResult().getDegree()) 
					){
//				"博士", "硕士", "本科", "大专", "高中或中专",
//			"初中及以下" };
				for (int i = 0; i < sp_cultrue.getCount(); i++) {
					String item = cultrue_degree[i];
			
					String str_Degree="";
					
					if (Contents.response.getResult().getDegree().equals("08")){
						str_Degree="博士";
					}else if(Contents.response.getResult().getDegree().equals("09")){
						str_Degree="硕士";
					}else if(Contents.response.getResult().getDegree().equals("10")){
						str_Degree="本科";
					}else if(Contents.response.getResult().getDegree().equals("20")){
						str_Degree="大专";
					}else if(Contents.response.getResult().getDegree().equals("30")){
						str_Degree="高中或中专";
					}else if(Contents.response.getResult().getDegree().equals("40")){
						str_Degree="初中及以下";
					}
					if (item.equals(str_Degree)) {
						sp_cultrue.setSelection(i);
					}
				}
				
				
			}
			
			//et_empName
			if (null != Contents.response.getResult().getEmpName()
					&&!"".equals(Contents.response.getResult().getEmpName()) 
					) {
				et_empName.setText(Contents.response.getResult().getEmpName());
			}
			//et_empZone
			if (null != Contents.response.getResult().getEmpZone()
					&&!"".equals(Contents.response.getResult().getEmpZone()) 
					) {
				et_empZone.setText(Contents.response.getResult().getEmpZone());
			}
			//et_empTelNo
			if (null != Contents.response.getResult().getEmpTelNo()
					&&!"".equals(Contents.response.getResult().getEmpTelNo()) 
					) {
				et_empTelNo.setText(Contents.response.getResult().getEmpTelNo());
			}
			//et_empTelSub
			if (null != Contents.response.getResult().getEmpTelSub()
					&&!"".equals(Contents.response.getResult().getEmpTelSub()) 
					) {
				et_empTelSub.setText(Contents.response.getResult().getEmpTelSub());
			}
			//et_empAddr
			if (null != Contents.response.getResult().getEmpAddr()
					&&!"".equals(Contents.response.getResult().getEmpAddr()) 
					) {
				et_empAddr.setText(Contents.response.getResult().getEmpAddr());
			}
			// et_mthInc
			if (null != Contents.response.getResult().getMthInc()
					&&!"".equals(Contents.response.getResult().getMthInc()) 
					) {
				et_mthInc.setText(Contents.response.getResult().getMthInc());
			}
			//==================================end
		}
	}
	/**
	 * 根据当前的市，更新区WheelView的信息
	 */
	private void updateAreas() {
		int pCurrent = mCity.getCurrentItem();
		mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
		String[] areas = mAreaDatasMap.get(mCurrentCityName);

		if (areas == null) {
			areas = new String[] { "" };
			mCurrentAreaName = "";
		} else {
			mCurrentAreaName = mAreaDatasMap.get(mCurrentCityName)[0];
		}
		mArea.setViewAdapter(new ArrayWheelAdapter<String>(this, areas));
		mArea.setCurrentItem(0);
	}

	/**
	 * 根据当前的省，更新市WheelView的信息
	 */
	private void updateCities() {
		int pCurrent = mProvince.getCurrentItem();
		mCurrentProviceName = mProvinceDatas[pCurrent];
		String[] cities = mCitisDatasMap.get(mCurrentProviceName);
		if (cities == null) {
			cities = new String[] { "" };
		}
		mCity.setViewAdapter(new ArrayWheelAdapter<String>(this, cities));
		mCity.setCurrentItem(0);
		updateAreas();
	}
//		if (Contents.response.getResult() != null) {
//			if (null != Contents.response.getResult().getCipAlias()
//					&&!"".equals(Contents.response.getResult().getCipAlias()) 
//					) {
//				username.setText("用户名      " +Contents.response.getResult().getCipAlias());
//				
//			}else {
//				username.setText("用户名      ");
//			}
//			
//			if (!"".equals(Contents.response.getResult().getCipNamecn()) &&
//					null != Contents.response.getResult().getCipNamecn()) {
//				name.setText("姓    名      " +Contents.response.getResult().getCipNamecn());
//				
//			}else {
//				name.setText("姓    名      ");
//			}
//			
//			if ("".equals(Contents.response.getResult().getCipSex()) ||
//					null == Contents.response.getResult().getCipSex()) {
//				sex.setText("性    别      " +"");
//			}else if ("1".equals(Contents.response.getResult().getCipSex())) {
//				sex.setText("性    别      " +"男");
//			}else if ("2".equals(Contents.response.getResult().getCipSex())) {
//				sex.setText("性    别      " +"女");
//			}
//			
//			if (!"".equals(Contents.response.getResult().getCipMobile()) &&
//					null != Contents.response.getResult().getCipMobile()) {
//				phoneNumber.setText("手机号      "+Contents.response.getResult().getCipMobile());
//				
//			}else {
//				phoneNumber.setText("手机号      ");
//			}
//			
//			if (!"".equals(Contents.response.getResult().getCipEmail()) &&
//					null != Contents.response.getResult().getCipEmail()) {
//				email.setText("邮箱地址      "+Contents.response.getResult().getCipEmail());
//				
//			}else {
//				email.setText("邮箱地址      ");
//			}
//			
//			if (!"".equals(Contents.response.getResult().getCipRegdate()) &&
//					null != Contents.response.getResult().getCipRegdate()) {
//				addtime.setText("加入时间      "+Contents.response.getResult().getCipRegdate());
//				
//			}else {
//				addtime.setText("加入时间      ");
//			}
//		}
//
//		back.setOnClickListener(this);
	

	private void init(){
		RegexResult temp = null;
		//申请姓名
		tx_username=et_username.getText().toString().trim();
		//身份证号码
		tx_idNo=et_idNo.getText().toString().trim();
		//手机号码
		tx_cipMobile=et_cipMobile.getText().toString().trim();
		//居住地址1
		tx_liveAddr=et_liveAddr.getText().toString().trim();
		//居住地址2
		tx_liveAddr2=et_liveAddr2.getText().toString().trim();
		temp = RegexCust.required("现居住地址", tx_liveAddr2);
		if (!tx_liveAddr2.equals("")&& null != tx_liveAddr2) {
		if (temp.match) {
			boolean flag = RegexCust.lengthMax(tx_liveAddr2, 60);
			if (flag == false) {
				result = new RegexResult(false, "居住地址字符长度过长!");
				DialogUtil.showDialogOneButton2(this, result.msg);
				et_liveAddr2.requestFocus();
				return;
			}
		} }
		
		//户籍地址
		tx_regAddr =et_regAddr.getText().toString().trim();
		if (!tx_regAddr.equals("") && null != tx_regAddr) {
		temp = RegexCust.required("户籍地址", tx_regAddr);
		if (temp.match) {
			boolean flag = RegexCust.lengthMax(tx_regAddr, 60);
			if (flag == false) {
				result = new RegexResult(false, "户籍地址字符长度过长!");
				DialogUtil.showDialogOneButton2(this, result.msg);
				et_regAddr.requestFocus();
				return;
			}
		}}
		
		//单位名称
		tx_empName =et_empName.getText().toString().trim();
		
		if ((!"".equals(tx_empName)) && null != tx_empName) {
		temp = RegexCust.required("单位名称", tx_empName);
		if (temp.match) {
			if (!RegexCust.lengthMax(tx_empName, 60)) {
				result = new RegexResult(false, "单位名称字符长度过长!");
				DialogUtil.showDialogOneButton2(this, result.msg);
				et_empName.requestFocus();
				return;
			}
		} 
		}
		//单位区号
		tx_empZone =et_empZone.getText().toString().trim();
		
		if (!tx_empZone.equals("")  &&null != tx_empZone) {
			if (!RegexCust.numberNatural(tx_empZone)) {
				result = new RegexResult(false, "单位电话区号格式不对!");
				showDialog(INFO_ERROR);
				et_empZone.requestFocus();
				return;
			}
		}
		//单位号码
		tx_empTelNo  =et_empTelNo.getText().toString().trim();
		if (!tx_empTelNo.equals("") && null != tx_empTelNo) {
			if (!RegexCust.phoneFix(tx_empTelNo)) {
				result = new RegexResult(false, "单位电话格式不对!");
				showDialog(INFO_ERROR);
				et_empTelNo.requestFocus();
				return;
			}}

		//分机号
		tx_empTelSub =et_empTelSub.getText().toString().trim();
		if (!tx_empTelSub.equals("")&&null != tx_empTelSub) {
			if (!RegexCust.numberNatural(tx_empTelSub)) {
				result = new RegexResult(false, "单位电话分机号格式不对!");
				showDialog(INFO_ERROR);
				et_empTelSub.requestFocus();
				return;
			}
		}
		
		//单位地址
		tx_empAddr  =et_empAddr.getText().toString().trim();
		if (!tx_empAddr.equals("")&& null != tx_empAddr) {
		temp = RegexCust.required("单位地址", tx_empAddr);
		if (temp.match) {
			boolean flag = RegexCust.lengthMax(tx_empAddr, 60);
			if (flag == false) {
				result = new RegexResult(false, "单位地址字符长度过长!");
				DialogUtil.showDialogOneButton2(this, result.msg);
				et_empAddr.requestFocus();
				return;
			}
		}
		}
		
		//月收入
		tx_mthInc =et_mthInc.getText().toString().trim();
		if (!tx_mthInc.equals("")  && null != tx_mthInc) {
		temp = RegexCust.required("月工资收入", tx_mthInc);
		if (temp.match) {
			if (!RegexCust.lengthMax(tx_mthInc, 8)) {
				result = new RegexResult(false, "月工资收入最高为1000万");
				DialogUtil.showDialogOneButton2(this, result.msg);
				et_mthInc.requestFocus();
				return;
			}
		} }
		
		
		 new GetMessageInfo().execute(
				 tx_username,
				 tx_idNo,
				 tx_cipMobile,
				 tx_liveAddr,
				 tx_liveAddr2,
				 tx_regAddr,
				 tx_empName,
				 tx_empZone,
				 tx_empTelNo,
				 tx_empTelSub,
				 tx_empAddr,
				 tx_mthInc,
				 tx_marital,
				 tx_cultrue
				 );
	}
//	private String tx_username,tx_idNo, tx_cipMobile,
//	tx_liveAddr ,tx_liveAddr2, tx_regAddr  ,tx_empName ,tx_empZone, tx_empTelNo, tx_empTelSub
//	,tx_empAddr, tx_mthInc;
//	private String tx_marital="";
//	private String tx_cultrue="";
	private void initNext(){
		//存到对象中
		//姓名 
		System.out.println("tx_username==>"+tx_username);
		System.out.println("tx_idNo==>"+tx_idNo);
		System.out.println("tx_cipMobile==>"+tx_cipMobile);
		System.out.println("tx_liveAddr==>"+tx_liveAddr);
		System.out.println("tx_liveAddr2==>"+tx_liveAddr2);
		System.out.println("tx_regAddr==>"+tx_regAddr);
		System.out.println("tx_empName==>"+tx_empName);
		System.out.println("tx_empZone==>"+tx_empZone);
		System.out.println("tx_empTelNo==>"+tx_empTelNo);
		System.out.println("tx_empTelSub==>"+tx_empTelSub);
		System.out.println("tx_empAddr==>"+tx_empAddr);
		System.out.println("tx_mthInc==>"+tx_mthInc);
		System.out.println("tx_marital==>"+tx_marital);
		System.out.println("tx_cultrue==>"+tx_cultrue);
		if(Contents.response != null && Contents.response.getResult() != null) {
			Contents.response.getResult().setCipNamecn(tx_username);
			Contents.response.getResult().setIdNo(tx_idNo);
			Contents.response.getResult().setCipMobile(tx_cipMobile);
			Contents.response.getResult().setMarital(tx_marital);
			Contents.response.getResult().setLiveAddr(tx_liveAddr);
			Contents.response.getResult().setCurentResidentialAddr(tx_liveAddr2);
			Contents.response.getResult().setRegAddr(tx_regAddr);
			Contents.response.getResult().setDegree(tx_cultrue);
			Contents.response.getResult().setEmpName(tx_empName);
			Contents.response.getResult().setEmpZone(tx_empZone);
			Contents.response.getResult().setEmpTelNo(tx_empTelNo);
			Contents.response.getResult().setEmpTelSub(tx_empTelSub);
			Contents.response.getResult().setEmpAddr(tx_empAddr);
			Contents.response.getResult().setMthInc(tx_mthInc);
		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.back) {
			finish();
			Time t=new Time(); 
			t.setToNow(); // 取得系统时间。   
			System.out.println("===>>"+t);
		}
	}
	
	/**
	 * 解析整个Json对象，完成后释放Json对象的内存
	 */
	private void initDatas() {
		try {
			JSONArray jsonArray = mJsonObj.getJSONArray("citylist");
			mProvinceDatas = new String[jsonArray.length()];
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonP = jsonArray.getJSONObject(i);// 每个省的json对象
				String province = jsonP.getString("p");// 省名字

				mProvinceDatas[i] = province;

				JSONArray jsonCs = null;
				try {
					/**
					 * Throws JSONException if the mapping doesn't exist or is
					 * not a JSONArray.
					 */
					jsonCs = jsonP.getJSONArray("c");
				} catch (Exception e1) {
					continue;
				}
				String[] mCitiesDatas = new String[jsonCs.length()];
				for (int j = 0; j < jsonCs.length(); j++) {
					JSONObject jsonCity = jsonCs.getJSONObject(j);
					String city = jsonCity.getString("n");// 市名字
					mCitiesDatas[j] = city;
					JSONArray jsonAreas = null;
					try {
						/**
						 * Throws JSONException if the mapping doesn't exist or
						 * is not a JSONArray.
						 */
						jsonAreas = jsonCity.getJSONArray("a");
					} catch (Exception e) {
						continue;
					}

					String[] mAreasDatas = new String[jsonAreas.length()];// 当前市的所有区
					for (int k = 0; k < jsonAreas.length(); k++) {
						String area = jsonAreas.getJSONObject(k).getString("s");// 区域的名称
						mAreasDatas[k] = area;
					}
					mAreaDatasMap.put(city, mAreasDatas);
				}
				mCitisDatasMap.put(province, mCitiesDatas);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		mJsonObj = null;
	}

	/**
	 * 从assert文件夹中读取省市区的json文件，然后转化为json对象
	 */
	private void initJsonData() {
		try {
			StringBuffer sb = new StringBuffer();
			InputStream is = getAssets().open("city.json");
			int len = -1;
			byte[] buf = new byte[1024 * 2];
			while ((len = is.read(buf)) != -1) {
				sb.append(new String(buf, 0, len, "GB2312"));
			}
			is.close();
			mJsonObj = new JSONObject(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * change事件的处理
	 */
	//wz10.11
	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) throws NullPointerException{
		if (wheel == mProvince) {
			updateCities();
		} else if (wheel == mCity) {
			updateAreas();
		} else if (wheel == mArea) {
			mCurrentAreaName = "";
			mCurrentAreaName = mAreaDatasMap.get(mCurrentCityName)[newValue];
		}
	}

	public void showChoose(View view) {
		Toast.makeText(this,
				mCurrentProviceName + mCurrentCityName + mCurrentAreaName, Toast.LENGTH_LONG)
				.show();
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO 自动生成的方法存根
		
	}
	class GetMessageInfo extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {

			HttpHelper mBobcfcHttpClient = HttpHelper
					.getInstance(MessageActivity.this);
			String url = ContantsAddress.MESSAGEINFOSUBMIT;
			List<NameValuePair> param = new ArrayList<NameValuePair>();
		
			param.add(new BasicNameValuePair("cipNamecn", params[0]));
			param.add(new BasicNameValuePair("idNo", params[1]));
			param.add(new BasicNameValuePair("cipMobile", params[2]));
			param.add(new BasicNameValuePair("liveAddr", params[3]));
			param.add(new BasicNameValuePair("curentResidentialAddr", params[4]));
			param.add(new BasicNameValuePair("regAddr", params[5]));
			param.add(new BasicNameValuePair("empName", params[6]));
			param.add(new BasicNameValuePair("empZone", params[7]));
			param.add(new BasicNameValuePair("empTelNo", params[8]));
			param.add(new BasicNameValuePair("empTelSub", params[9]));
			param.add(new BasicNameValuePair("empAddr", params[10]));
			param.add(new BasicNameValuePair("mthInc", params[11]));
			param.add(new BasicNameValuePair("marital", params[12]));
			param.add(new BasicNameValuePair("degree", params[13]));

			Class<MessageInfoSubmitYe> clazz = MessageInfoSubmitYe.class;
			MessageInfoSubmitYe response = mBobcfcHttpClient.post(url,
					param, clazz);
			return response;

		}

		@Override
		protected void onPostExecute(Object result) {
			// TODO 自动生成的方法存根
			super.onPostExecute(result);
			dismissProgressDialog();
			MessageInfoSubmitYe response = (MessageInfoSubmitYe) result;
			me_type="yes";
			if (response != null) {
				System.out.println("不为空");
				if (response.getCode() == 0) {
					initNext();
					System.out.println("code=>" + response.getCode());
					DialogUtil.showDialogOneButton(MessageActivity.this, "提交成功！",new OnClickListener() {
						@Override
						public void onClick(View v) {
							// TODO 自动生成的方法存根
							Intent it =new Intent(MessageActivity.this,HomeActivity.class);
							startActivity(it);
						}
					});
					//提交成功！！！！！！！！！！！！！！！！！！！！
			}else{
				DialogUtil.showDialogOneButton(MessageActivity.this,  response.getMsg() ,new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO 自动生成的方法存根
						Intent it =new Intent(MessageActivity.this,HomeActivity.class);
						startActivity(it);
					}
				});
			}
			} else {
				Toast.makeText(
						MessageActivity.this,
						getResources().getString(R.string.no_network),
						Toast.LENGTH_LONG).show();
			
			}

		}
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
				if ((me_type.equals("yes"))) {
					
				} else {
					showProgressDialog();
				}
	}
	}
	@Override
	protected void onResume() {
		// TODO 自动生成的方法存根
		super.onResume();
		me_type.equals("no");
	}
	
	
}
