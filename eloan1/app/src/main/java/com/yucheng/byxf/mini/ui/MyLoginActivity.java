package com.yucheng.byxf.mini.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.apfx.utl.RegexResult;
import com.yucheng.byxf.mini.domain.MiniPersonInfo;
import com.yucheng.byxf.mini.easyloan.ui.RelaxedLoanAdActivity;
import com.yucheng.byxf.mini.easyloan.ui.SharedPreferencesUtils;
import com.yucheng.byxf.mini.map.DemoApplication;
import com.yucheng.byxf.mini.message.LoginResponse;
import com.yucheng.byxf.mini.message.PersonalApkResponse;
import com.yucheng.byxf.mini.rapidly.RepidlyLoanInfoContractBook;
import com.yucheng.byxf.mini.utils.ApkUpdate;
import com.yucheng.byxf.mini.utils.ChooseDialog;
import com.yucheng.byxf.mini.utils.ChooseDialog.OnDialogClickListener;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DESUtils;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.ExampleUtil;
import com.yucheng.byxf.mini.utils.RegexCust;
import com.yucheng.byxf.mini.utils.StringUtils;
import com.yucheng.byxf.util.IdcardValidator;

public class MyLoginActivity extends BaseActivity implements OnClickListener {
	private Button register_bt;
	private Button login_bt;
	private EditText name;// 账号
	private EditText name_zhezhao;// 账号遮罩
	private EditText password;// 密码
	private static final Integer RET_CODE = 0;
	private static final Integer RET_CODE2 = 1004;
	private TextView forget_password;
	private String fugai_String;
	private String loginErrorMessage;

	private String type = null;
	private String cardNumDES;
	private boolean isRegisterFlag = false;
 
	private String openHome;
	 
	protected DemoApplication application = null;
	// wz11.2
	private String isMemory = ""; // 用于 记住密码
	private String FILE = "saveUserNamePwd";// 用于保存SharedPreferences的文件
	private SharedPreferences sp = null;// 声明一个SharedPreferences
	static String YES = "yes";
	static String NO = "no";
	public static String sh_name;
	public static String zhongzhuan_name;

	private CheckBox ch_box;
	// // 定位相关
	// LocationClient mLocClient;
	// LocationData locData = null;
	// public MyLocationListenner myListener = new MyLocationListenner();
	//
	// // UI相关
	// boolean isFirstLoc = true;// 是否首次定位
	// // 搜索相关
	// MKSearch mSearch = null; // 搜索模块，也可去掉地图模块独立使用
	private String strInfo;
	private ChooseDialog chooseDialog;
	// private String city;
	// private String province;
	// private String area;

	ApkUpdate mApkUpdate;
	private PersonalApkResponse apk;

	private int isFlag;

	private int loadTime = 15;
	LoginAsyncTask loginAsyncTask;
	//receiverS re;
	private boolean isUpdata = false;
	private SharedPreferences sp0 = null;// 声明一个SharedPreferences
	private String FILE2 = "saveGesturePwd";// 用于保存SharedPreferences的文件

    private static final String TAG = "JPush";
    private static final int MSG_SET_ALIAS = 1001;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		init();
	//	initBroadcastReceiver();
		//re=new receiverS();
//		sp = getSharedPreferences(FILE, MODE_PRIVATE);
//		isMemory = sp.getString("isMemory", NO);
//
//		if (isMemory.equals(YES)) {
//			sh_name = sp.getString("sh_name", "");
//			zhongzhuan_name = sh_name;
//			fugai_String = StringUtils.coverLoginName(zhongzhuan_name);
//			name.setText(fugai_String);
//		}

		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			type = bundle.getString("type");
			openHome = bundle.getString("openHome");
			 
		}

		getLocationInfo();

		// //////////////更新APK/////////////

		new Thread() {
			public void run() {
				Looper.prepare();
				// if (!NetworkUtil.check(MyLoginActivity.this)) {
				// Toast.makeText(MyLoginActivity.this, "网络未连接，请重试",
				// Toast.LENGTH_SHORT).show();
				// isUpdata = false;
				// } else {
				ApkUpdate mApkUpdate = new ApkUpdate(MyLoginActivity.this);
				isUpdata = true;
				// 检测版本
				isFlag = mApkUpdate.checkNewVersionAndTap(MyLoginActivity.this);
				if (isFlag == 2) {
					handler.sendEmptyMessage(0);
				}
				// }
				Looper.loop();
			};
		}.start();

		loginAsyncTask = new LoginAsyncTask();
	}

	private void not_remember() {
		if (sp == null) {
			sp = getSharedPreferences(FILE, MODE_PRIVATE);
		}
		Editor edit = sp.edit();
		// edit.putString("sh_name", name.getText().toString());
		edit.putString("isMemory", NO);
		edit.commit();
	}

	private void remember() {
		if (sp == null) {
			sp = getSharedPreferences(FILE, MODE_PRIVATE);
		}
		Editor edit = sp.edit();
		if( name.getText().toString().contains("*")){
			edit.putString("sh_name", zhongzhuan_name);
		}else{
			
			edit.putString("sh_name", name.getText().toString());
		}
		edit.putString("isMemory", YES);
		edit.commit();
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 0) {
				mApkUpdate = new ApkUpdate(MyLoginActivity.this);
				new GetAPKAsyncTask().execute();
			} else if (msg.what == 12) {
				dismissProgressDialog();
			}

			else if (msg.what == 11) {
				String loginName = "";
				if (name.getText().toString().contains("*")) {
					loginName = zhongzhuan_name;
				} else {
					loginName = name.getText().toString();
				}
				// String loginName = name.getText().toString();
				String loginPassword = password.getText().toString();
				if (null == loginName || "".equals(loginName)) {
					DialogUtil.showDialogOneButton2(MyLoginActivity.this,
							"请输入用户名");
					return;
				}

				// Result temp_user = RegexCust.required("用户名", loginName);
				// if (temp_user.match) {
				// if (loginName.length() < 4 || loginName.length() > 20) {
				// DialogUtil.showDialogOneButton2(MyLoginActivity.this,
				// "用户名格式错误");
				// return;
				// }
				// if (!RegexCust.user_name(loginName)) {
				// DialogUtil.showDialogOneButton2(MyLoginActivity.this,
				// "用户名格式错误");
				// }
				// } else {
				// DialogUtil.showDialogOneButton2(MyLoginActivity.this,
				// "用户名格式错误");
				// return;
				// }

				if (null == loginName || "".equals(loginPassword)) {
					DialogUtil.showDialogOneButton2(MyLoginActivity.this,
							"请输入密码");
					return;
				}
				// 是否匹配username为空值；
				RegexResult temp_password = RegexCust.required("密码",
						loginPassword);
				if (temp_password.match) {
					if (RegexCust.userPass(loginPassword) == false) {
						DialogUtil.showDialogOneButton2(MyLoginActivity.this,
								"密码格式错误");
						dismissProgressDialog();
						return;
					}
				} else {
					DialogUtil.showDialogOneButton2(MyLoginActivity.this,
							"密码格式错误");
					dismissProgressDialog();
					return;
				}

				if (hasDigit(loginPassword)) {
					DialogUtil.showDialogOneButton2(MyLoginActivity.this,
							"密码格式错误");
					dismissProgressDialog();
					return;
				}
				String macAddress = "";
				macAddress = getLocalMacAddress(MyLoginActivity.this);

				IdcardValidator idCardValidator = new IdcardValidator();

				try {
					loginPassword = DESUtils.encryptMode(loginPassword,
							MyLoginActivity.this);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (!idCardValidator.isValidatedAllIdcard(loginName)) {
					// handler.sendEmptyMessage(0);
					loginAsyncTask = new LoginAsyncTask();
					loginAsyncTask
							.execute(loginName, loginPassword, macAddress);
				} else {
					try {
						cardNumDES = DESUtils.encryptMode(loginName,
								MyLoginActivity.this);
						loginAsyncTask = new LoginAsyncTask();
						loginAsyncTask.execute(cardNumDES, loginPassword,
								macAddress);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		};
	};

	private boolean hasDigit(String contant) {
		boolean flag = false;
		Pattern pattern = Pattern.compile("[a-zA-Z]+");
		Matcher matcher = pattern.matcher(contant);
		if (matcher.matches()) {
			flag = true;
		}
		return flag;
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		setIntent(intent);
		intent = getIntent();
		if (intent != null) {
			String user_name = intent.getStringExtra("user_name");
			name.setText(user_name);
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		forget_password.setText("忘记密码？");
		
		sp = getSharedPreferences(FILE, MODE_PRIVATE);
		isMemory = sp.getString("isMemory", NO);

		if (isMemory.equals(YES)) {
			sh_name = sp.getString("sh_name", "");
			zhongzhuan_name = sh_name;
			fugai_String = StringUtils.coverLoginName(zhongzhuan_name);
			name.setText(fugai_String);
		}
		super.onResume();
	}

	private void init() {
		// TODO Auto-generated method stub
		forget_password = (TextView) findViewById(R.id.forget_password);
		name = (EditText) findViewById(R.id.bg_username);
		password = (EditText) findViewById(R.id.bg_password);
		register_bt = (Button) findViewById(R.id.bt_register);
		login_bt = (Button) findViewById(R.id.bt_login);
		register_bt.setOnClickListener(this);
		login_bt.setOnClickListener(this);
		forget_password.setOnClickListener(this);
		ch_box = (CheckBox) findViewById(R.id.checkbox_jizhu);
		application = ((DemoApplication) getApplicationContext());

		name.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					if ((name.getText().toString()).contains("*")) {
						name.setText("");
						System.out.println("改变额！！！！！！！！！");
					}
				}

			}
		});
	}
 
	public void registerBoradcastReceiver() {
		IntentFilter myIntentFilter = new IntentFilter();
		myIntentFilter.addAction("automatic_login");
		
		registerReceiver(mBroadcastReceiver, myIntentFilter);
	}

	private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals("automatic_login")) {
				String user_name = intent.getStringExtra("user_name");
				String user_password = intent.getStringExtra("user_password");
				isRegisterFlag = intent
						.getBooleanExtra("isRegisterFlag", false);
				login(user_name, user_password);
			}
			
			 
			// if (action.equals(Contents.ISEXCEPTION)) {
			// dismissProgressDialog();
			// }
		}

	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_login:
			System.out.println("==111111111111111=>"+zhongzhuan_name);
			login_bt.setClickable(false);
			login();
			
			break;
		case R.id.bt_register:
			registerBoradcastReceiver();
			register();
			break;
		case R.id.forget_password:
			Intent intent = new Intent();
			intent.setClass(this, ForgetPassWordActivity.class);
			startActivity(intent);
			// forget_password.setText("请到轻松e贷网站找回");
			// Intent intent = new Intent();
			// intent.setClass(this, ForgetPassWordActivity.class);
			// startActivity(intent);
			break;
		default:
			break;
		}
	}

	class GetAPKAsyncTask extends AsyncTask<Object, Object, Object> {
		@Override
		protected Object doInBackground(Object... params) {

			apk = mApkUpdate.getVersionOnServer();
			return apk;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Object result) {
			dismissProgressDialog();
			super.onPostExecute(result);
			getResponse(result);
		}
	}

	public void getResponse(Object result) {
		PersonalApkResponse apk = (PersonalApkResponse) result;
		if (apk == null) {
			isUpdata = false;
			Toast.makeText(MyLoginActivity.this,
					getResources().getString(R.string.no_network),
					Toast.LENGTH_SHORT).show();
		} else {
			if (0 == apk.getCode()) {
				mApkUpdate.checkNewVersion(MyLoginActivity.this, apk);
				isUpdata = true;
			} else {
				Toast.makeText(MyLoginActivity.this, apk.getMsg(),
						Toast.LENGTH_SHORT).show();
			}
		}

	}

	private void register() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(MyLoginActivity.this, RegisterActivity.class);
		startActivity(intent);
		password.setText("");
	}

	private void login() {
		String loginName = "";
		if (name.getText().toString().contains("*")) {
			loginName = zhongzhuan_name;
		} else {
			loginName = name.getText().toString();
		}
		if (ch_box.isChecked()) {
			remember();
			System.out.println("记住用户名");
			} else {
			not_remember();
			System.out.println("不记住用户名");
			}
		// String loginName =name.getText().toString();
		String loginPassword = password.getText().toString();
		if (null == loginName || "".equals(loginName)) {
			login_bt.setClickable(true);
			DialogUtil.showDialogOneButton2(MyLoginActivity.this, "请输入用户名");

			return;
		}
		if (null == loginName || "".equals(loginPassword)) {
			login_bt.setClickable(true);
			DialogUtil.showDialogOneButton2(MyLoginActivity.this, "请输入密码");
			return;
		}
		if (isUpdata) {
			showProgressDialog();
			new Thread() {
				public void run() {
					Looper.prepare();
					// if (!NetworkUtil.check(MyLoginActivity.this)) {
					// Toast.makeText(MyLoginActivity.this, "网络未连接，请重试",
					// Toast.LENGTH_SHORT).show();
					// login_bt.setClickable(true);
					// handler.sendEmptyMessage(12);
					// } else {
					login_bt.setClickable(true);
					handler.sendEmptyMessage(11);
					// }
					Looper.loop();
				};
			}.start();
		} else {
			showProgressDialog();
			new Thread() {
				public void run() {
					Looper.prepare();
					// if (!NetworkUtil.check(MyLoginActivity.this)) {
					// handler.sendEmptyMessage(12);
					// Toast.makeText(MyLoginActivity.this, "网络未连接，请重试",
					// Toast.LENGTH_SHORT).show();
					// isUpdata = false;
					// login_bt.setClickable(true);
					// } else {
					login_bt.setClickable(true);
					ApkUpdate mApkUpdate = new ApkUpdate(MyLoginActivity.this);

					// 检测版本
					isFlag = mApkUpdate
							.checkNewVersionAndTap(MyLoginActivity.this);
					if (isFlag == 2) {
						handler.sendEmptyMessage(0);
					} else {
						isUpdata = true;
						handler.sendEmptyMessage(11);
					}
					// }
					Looper.loop();
				};
			}.start();
		}

		// String macAddress = "";
		// macAddress = getLocalMacAddress(MyLoginActivity.this);
		//
		// IdcardValidator idCardValidator = new IdcardValidator();
		//
		// try {
		// loginPassword = DESUtils.encryptMode(loginPassword,
		// MyLoginActivity.this);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// if (!idCardValidator.isValidatedAllIdcard(loginName)) {
		// // handler.sendEmptyMessage(0);
		// new LoginAsyncTask().execute(loginName, loginPassword,
		// macAddress);
		// } else {
		// try {
		// cardNumDES = DESUtils.encryptMode(loginName,
		// MyLoginActivity.this);
		// new LoginAsyncTask().execute(cardNumDES, loginPassword,
		// macAddress);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// }

	}

	private void login(String loginName, String loginPassword) {
		// TODO Auto-generated method stub
		// if (NetworkUtil.isConnect()) {

		String macAddress = "";
		macAddress = getLocalMacAddress(this);

		IdcardValidator idCardValidator = new IdcardValidator();

		try {
			loginPassword = DESUtils.encryptMode(loginPassword,
					MyLoginActivity.this);
			System.out.println("登录加密之后的数字" + loginPassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!idCardValidator.isValidatedAllIdcard(loginName)) {
			new LoginAsyncTask().execute(loginName, loginPassword, macAddress);
		} else {
			try {
				cardNumDES = DESUtils.encryptMode(loginName,
						MyLoginActivity.this);
				new LoginAsyncTask().execute(cardNumDES, loginPassword,
						macAddress);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		unregisterReceiver(mBroadcastReceiver);
		// onDestroy();
		// } else {
		// DialogUtil.showDialogOneButton2(this,
		// getResources().getString(R.string.no_network));
		// }
	}

	@Override
	public void cancleAsyncTask() {
		// TODO Auto-generated method stub
		super.cancleAsyncTask();

		if (null != loginAsyncTask && !loginAsyncTask.isCancelled()) {
			loginAsyncTask.cancel(true);
			if (null != loginAsyncTask && !loginAsyncTask.isCancelled()) {

			}
		}
		login_bt.setClickable(true);
	}

	private String getLocalMacAddress(Context context) {
		WifiManager wifi = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		return info.getMacAddress();
	}

	class LoginAsyncTask extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper
					.getInstance(MyLoginActivity.this);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("username", params[0]));
			arg.add(new BasicNameValuePair("password", params[1]));
			arg.add(new BasicNameValuePair("longitude", Contents.longitude + ""));
			arg.add(new BasicNameValuePair("latitude", Contents.latitude + ""));
			arg.add(new BasicNameValuePair("location", Contents.locationInfo));
			System.out.println("位置信息+++》"+Contents.longitude+"--->"+Contents.latitude+"--->"+ Contents.locationInfo);
			TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
			String imei = telephonyManager.getDeviceId();
			Contents.iMei = imei;
			System.out.println("设备IMEI==" + imei);
			arg.add(new BasicNameValuePair("udid", imei));
			arg.add(new BasicNameValuePair("city", Contents.city));
			arg.add(new BasicNameValuePair("area", Contents.area));
			arg.add(new BasicNameValuePair("province", Contents.province));
			return httpHelper.post(ContantsAddress.LOGIN, arg,
					LoginResponse.class);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
		}

		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);
			dismissProgressDialog();
			loginResponse(result);
		}
	}

	private void loginResponse(Object result) {
		LoginResponse response = (LoginResponse) result;
		if (response == null) {
			loginErrorMessage = getResources().getString(R.string.no_network);
			// DialogUtil.createDialog(this, 1, loginErrorMessage);
			DialogUtil.showDialogOneButton2(this, loginErrorMessage);
			login_bt.setClickable(true);
			try {
				String s = null;
				System.out.println(s);
			} catch (NullPointerException e) {
				// TODO: handle exception
				System.out.println(e);
			}

			return;
		} else {
			if (RET_CODE.equals(response.getCode())) {
			//	initBroadcastReceiver();
//				ScreenActionReceiver s=new ScreenActionReceiver();
//				s.registerScreenActionReceiver(MyLoginActivity.this);
				password.setText("");
				// Toast.makeText(MyLoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
				login_bt.setClickable(true);
				// 初始化MiniPersonInfo
				MiniPersonInfo.clearAll();
				Contents.IS_LOGIN = true;
				Contents.response = response;

				application.setResponse(response);
				
				SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
						MyLoginActivity.this);
				if (preferencesUtils.hasData("loanFlag")) {
					if (Contents.response != null && Contents.response.getResult() != null
							&& Contents.response.getResult().getIdNo() != null
							&& !Contents.response.getResult().getIdNo()
							.equals(preferencesUtils.getData("loanFlag", ""))) {
						preferencesUtils.removeData("loanFlag");
					}
				}
				//根据手势密码存的姓名  ===》
				sp0 = getSharedPreferences(FILE2, MODE_PRIVATE);
				String name="";
				name = sp0.getString("Name", "");
				if(Contents.response != null && Contents.response.getResult() != null
						&& Contents.response.getResult().getCipNamecn() != null
						&& !name.equals(Contents.response
						.getResult().getCipNamecn())){
					Editor edit = sp0.edit();
					 
					edit.putString("isTurnOn", "No");
					edit.putString("Pwd","");
					edit.commit();
					
				}
				
				
				
				chooseDialog = new ChooseDialog();
				chooseDialog.createFengxianDialog(MyLoginActivity.this);
				chooseDialog.showDialog();
				chooseDialog
						.setOnDialogClickListener(new OnDialogClickListener() {

							@Override
							public void onPos() {
								// TODO Auto-generated method stub
								chooseDialog.dismissDialog();
								Intent intent = new Intent();

//								if (ch_box.isChecked()) {
//								remember();
//								System.out.println("记住用户名");
//								} else {
//
//								not_remember();
//								System.out.println("不记住用户名");
//								}
								if (isRegisterFlag) {
									intent.setClass(MyLoginActivity.this,
											HomeActivity.class);
									startActivity(intent);
									if(Contents.response != null && Contents.response.getResult() != null ) {
										Contents.username = Contents.response
												.getResult().getCipNamecn();
									}
									isRegisterFlag = false;
									finish();
									return;
								}
								if ("menu".equals(type)) {
									intent.setClass(MyLoginActivity.this,
											HomeActivity.class);
									startActivity(intent);
									overridePendingTransition(
											R.anim.transaction_item_righttoleftss,
											R.anim.transaction_item_lefttorights);
									finish();
								} else if ("bill".equals(type)) {
									intent.setClass(MyLoginActivity.this,
											BillNewActivity.class);
									// intent.putExtra("flag", "bill");
									startActivity(intent);
									finish();
								} else if ("search".equals(type)) {
									intent.setClass(
											MyLoginActivity.this,
											ApplicationScheduleQueryHomeActivity.class);
									// intent.putExtra("flag", "scheduleQuery");
									startActivity(intent);
									finish();
								} else if ("common".equals(type)) {
//									intent.setClass(MyLoginActivity.this,
//											RelaxedLoanOneContractBook.class);
							//加轻松贷内页
									intent.setClass(MyLoginActivity.this,
											RelaxedLoanAdActivity.class);
									intent.putExtra("choiceType", "choicenull");
									startActivity(intent);
									finish();
								} else if ("rapidly".equals(type)) {
									intent.setClass(MyLoginActivity.this,
											RepidlyLoanInfoContractBook.class);
									intent.putExtra("choiceType", "choicenull");
									startActivity(intent);
									finish();
								} else if ("message".equals(type)) {
									intent.setClass(MyLoginActivity.this,
											MessageActivity.class);
									startActivity(intent);
									finish();
								} else if ("consumeloan".equals(type)) {
									intent.setClass(MyLoginActivity.this,
											GeneralConsumeLoanActivity.class);
									startActivity(intent);
									finish();
								} else if ("openHome".equals(openHome)) {
									intent.setClass(MyLoginActivity.this,
											HomeActivity.class);
									startActivity(intent);
									finish();
								} else if("myeloan".equals(type)){
									intent.setClass(MyLoginActivity.this,
											MyEloanActivity.class);
									startActivity(intent);
									finish();
								} else if("mymessage".equals(type)){
									intent.setClass(MyLoginActivity.this,
											MyMessageActivity.class);
									startActivity(intent);
									finish();
								} else if("mymessageJpush".equals(type)){
									intent.setClass(MyLoginActivity.this,
											MyMessageActivity.class);
									intent.putExtra("isJPush", true);
									startActivity(intent);
									finish();
								}

								if(Contents.response != null && Contents.response.getResult() != null) {
									Contents.username = Contents.response
											.getResult().getCipNamecn();
								}
								System.out.println(Contents.username);
							}

							@Override
							public void onNeg() {

							}
						});
				//***************************************************
				String idNo = Contents.response.getResult().getIdNo();
				TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
				String imei = telephonyManager.getDeviceId();
				JPushInterface.setAlias(getApplicationContext(), idNo, mAliasCallback);
				JPushInterface.setAlias(getApplicationContext(), imei, mAliasCallback);

			} else if (RET_CODE2.equals(response.getCode())) {
				loginErrorMessage = "用户不存在";
				DialogUtil.showDialogOneButton2(this, loginErrorMessage);
				login_bt.setClickable(true);
			} else if (response.getCode() == 90019) {
				DialogUtil.showDialogOneButton(this, response.getMsg(),
						new OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								finish();
							}
						});
			} else {
				loginErrorMessage = response.getMsg();
				DialogUtil.showDialogOneButton2(this, loginErrorMessage);
				login_bt.setClickable(true);
			}

		}
	}

	@Override
	protected void onDestroy() {
//		unregisterReceiver(mBroadcastReceiver);
		// 退出时销毁定位
		if (mLocClient != null)
			mLocClient.stop();
		// mSearch.destory();

		super.onDestroy();
	}

//	//加广播
// 
//	private void initBroadcastReceiver() {
//		IntentFilter filter = new IntentFilter();
//		filter.addAction(Intent.ACTION_SCREEN_OFF);
//		filter.addAction(Intent.ACTION_SCREEN_ON);
//		registerReceiver(receiverS, filter);
//	}
//
//	private BroadcastReceiver receiverS = new BroadcastReceiver() {
//		 @Override
//		public void onReceive(Context context, Intent intent) {
//			System.out.println(intent.getAction());
//			 
//			if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction()))
//				System.out.println("********** The Screen is locked");
//			if (Intent.ACTION_SCREEN_ON.equals(intent.getAction()))
//				System.out.println("********** The screen is unlocked");
//			Intent it = new Intent();
//
//			it.putExtra("type", 9);
//
//			it.setClass(MyLoginActivity.this, GestureVerifyActivity.class);
//
//			startActivity(it);
//		}
//	};
	  public boolean onKeyDown(int keyCode, KeyEvent event) {  
	        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {  
	           finish();
	            return true;  
	        } else  
	            return super.onKeyDown(keyCode, event);  
	    }  
	  
	    @Override
	    protected void onPause() {
	    	super.onPause();
	    }
	    
	    private final Handler mHandler = new Handler() {
	        @Override
	        public void handleMessage(android.os.Message msg) {
	            super.handleMessage(msg);
                JPushInterface.setAliasAndTags(getApplicationContext(), (String) msg.obj, null, mAliasCallback);
	        }
	    };
	    
	    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {

	        @Override
	        public void gotResult(int code, String alias, Set<String> tags) {
	            String logs ;
	            switch (code) {
	            case 0:
	                logs = "Set tag and alias success";
	                Log.i(TAG, logs);
	                break;
	                
	            case 6002:
	                logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
	                Log.i(TAG, logs);
	                if (ExampleUtil.isConnected(getApplicationContext())) {
	                	mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
	                } else {
	                	Log.i(TAG, "No network");
	                }
	                break;
	            
	            default:
	                logs = "Failed with errorCode = " + code;
	                Log.e(TAG, logs);
	            }
	            
	            //ExampleUtil.showToast(logs, getApplicationContext());
	        }
		    
		};
	    /**
	     * 推送TEST
	     */
}