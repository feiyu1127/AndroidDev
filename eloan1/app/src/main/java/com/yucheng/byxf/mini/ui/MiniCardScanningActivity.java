package com.yucheng.byxf.mini.ui;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.yucheng.apfx.utl.RegexResult;
import com.yucheng.byxf.comm.CardInfo;
import com.yucheng.byxf.comm.MyApplication;
import com.yucheng.byxf.mini.domain.MiniPersonInfo;
import com.yucheng.byxf.mini.message.CustInfoResponse;
import com.yucheng.byxf.mini.message.CustInfoResponseResult;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.RegexCust;
import com.yucheng.byxf.mini.utils.ViewTools;
import com.yucheng.byxf.service.LoanPayInfo;
import com.yucheng.byxf.util.IdcardValidator;
import com.yucheng.byxf.util.Utils;

public class MiniCardScanningActivity extends BaseActivity {
	private static final String _Tag = "CardScanningActivity";
	// private ImageView iv_menu;//菜单
	// 配置文件信息
	private Button gather_button;
	private EditText name_text_value;
	// private EditText name_pinyin_text_value;
	private Spinner nationality_text_value;
	private Button birthday_text_value;
	private EditText cardType_text_value;
	private EditText cardNum_text_value;
	// private EditText expiryDate_text_value;
	private Button expiryDate_start_button;
	private Button expiryDate_end_button;
	private EditText birthPlace_text_value;
	private ImageView card_image;
	private EditText sex_text_value;
	private Button next_button;
	private TextView tvError;

	// 证件扫描信息
	private final int REQUEST_ENABLE_BT = 2;
	public static final int WM_CLEARSCREEN = 1;
	public static final int WM_READCARD = 2;
	public static final int WM_ERROR = 3;
	BluetoothAdapter mbta = null;
	ReadCardThread mrcThread = null;

	// 日期选择器
	private final int DATE_SELECT = 0;
	private final int BIRTHDAY_DATE_SELECT = 1;
	private final int START_DATE_SELECT = 2;
	private final int END_DATE_SELECT = 3;
	// 身份信息有误
	private final int INFO_ERROR = 4;
	private ArrayAdapter<String> adapter;
	private String nation;
	public static String nationFlag;
	public static String[] nationId = { "01", "02", "03", "04", "05", "06",
			"07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17",
			"18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28",
			"29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39",
			"40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50",
			"51", "52", "53", "54", "55", "56", "98", "99", "9A", "9B", "9C",
			"9D", "9E", "9F" };
	public static String[] nationValue = { "汉族", "蒙族", "回族", "藏族", "维吾尔族",
			"苗族", "彝族", "壮族", "布依族", "朝鲜族", "满族", "侗族", "瑶族", "白族", "土家族",
			"哈尼族", "哈萨克族", "傣族", "黎族", "僳僳族", "佤族", "畲族", "高山族", "拉祜族", "水族",
			"东乡族", "纳西族", "景颇族", "柯尔克孜族", "土族", "达斡尔族", "仫佬族", "羌族", "布朗族",
			"撒拉族", "毛难族", "仡佬族", "锡伯族", "阿昌族", "普米族", "塔吉克族", "怒族", "乌孜别克族",
			"俄罗斯族", "鄂温克族", "崩龙族", "保安族", "裕固族", "京族", "塔塔尔族", "独龙族", "鄂伦春族",
			"赫哲族", "门巴族", "珞巴族", "基诺族", "外国血统", "其他合作", "外籍人员", "无国籍人员", "香港人",
			"澳门人", "台湾人", "华侨" };
	// 证件扫描的信息
	private PersonInfo personInfo;
	// 验证结果
	private RegexResult result;
	Calendar calendar = Calendar.getInstance();
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == WM_CLEARSCREEN) {

			}
			// 获取成功
			if (msg.what == WM_READCARD) {
				personInfo = (PersonInfo) msg.obj;
				name_text_value.setText(personInfo.name);
				// name_pinyin_text_value.setText(CnToSpell.getPinyin(personInfo.name));
				sex_text_value.setText(personInfo.sex);
				// nationality_text_value.set
				cardType_text_value.setText("身份证");
				birthday_text_value.setText(personInfo.birthday2);
				cardNum_text_value.setText(personInfo.idNum);
				// expiryDate_text_value.setText(personInfo.validDate2);
				expiryDate_start_button.setText(Utils
						.getCardValidDate(personInfo.validDate2)[0]);
				expiryDate_end_button.setText(Utils
						.getCardValidDate(personInfo.validDate2)[1]);
				birthPlace_text_value.setText(personInfo.address);
				card_image.setImageBitmap(personInfo.photo);
				// tvError.setText("");
				onDisconnect();
			}
			// 有错误
			if (msg.what == WM_ERROR) {
				// tvError.setText(Error.GetErrorText(msg.arg2));
			}
			super.handleMessage(msg);
		}
	};
	private ImageView iv_menu_common;

	@Override
	protected void onStart() {
		super.onStart();
		if (MyApplication.isExit()) {
			finish();
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.card_scanning);

		MiniPersonInfo.name = Contents.username;
		String idNo = "";
		if(Contents.response != null && Contents.response.getResult() != null)
			idNo = Contents.response.getResult().getIdNo();
		MiniPersonInfo.cardNum = idNo;

		/* 返回 */
		iv_menu_common = (ImageView) findViewById(R.id.iv_menu_common);
		iv_menu_common.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		gather_button = (Button) this.findViewById(R.id.gather_button);
		name_text_value = (EditText) this.findViewById(R.id.name_text_value);
		name_text_value.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
			}

			@Override
			public void afterTextChanged(Editable arg0) {
				String name = name_text_value.getText().toString();
				MiniPersonInfo.name = name;
			}
		});
		// name_pinyin_text_value = (EditText)
		// this.findViewById(R.id.name_pinyin_text_value);

		nationality_text_value = (Spinner) this
				.findViewById(R.id.nationality_text_value);
		setNations();
		nationality_text_value
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						// nation = nationality_text_value.getSelectedItem()
						// .toString();
						// LogManager.i(CardScanningActivity.class,
						// "position+id==" + position + "," + id);
						// LogManager.i(CardScanningActivity.class,
						// nationality_text_value.getSelectedItem()
						// .toString());
						nation = nationValue[position];
						nationFlag = nationId[position];
						MiniPersonInfo.nationalityID = position;
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						// TODO Auto-generated method stub

					}
				});
		birthday_text_value = (Button) this
				.findViewById(R.id.birthday_text_value);
		// birthday_text_value.setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// showDialog(BIRTHDAY_DATE_SELECT);
		// }
		// });
		expiryDate_start_button = (Button) this
				.findViewById(R.id.expiryDate_start_button);
		expiryDate_start_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(START_DATE_SELECT);
			}
		});
		expiryDate_end_button = (Button) this
				.findViewById(R.id.expiryDate_end_button);
		expiryDate_end_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(END_DATE_SELECT);
			}
		});
		cardType_text_value = (EditText) this
				.findViewById(R.id.cardType_text_value);
		cardNum_text_value = (EditText) this
				.findViewById(R.id.cardNum_text_value);
		/**
		 * 自动获取性别和出生日期
		 */
		cardNum_text_value.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				String cardNum = cardNum_text_value.getText().toString().trim();
				MiniPersonInfo.cardNum = cardNum;
				updateGenderAndBirthday(cardNum);
			}
		});
		// expiryDate_text_value = (EditText)
		// this.findViewById(R.id.expiryDate_text_value);
		birthPlace_text_value = (EditText) this
				.findViewById(R.id.birthPlace_text_value);
		birthPlace_text_value.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				String birthPlace = birthPlace_text_value.getText().toString()
						.trim();
				MiniPersonInfo.birthPlace = birthPlace;
			}
		});
		card_image = (ImageView) this.findViewById(R.id.card_image);
		sex_text_value = (EditText) this.findViewById(R.id.sex_text_value);
		sex_text_value.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				String sex = sex_text_value.getText().toString().trim();
				MiniPersonInfo.sex = sex;
			}
		});
		tvError = (TextView) this.findViewById(R.id.tvError);

		gather_button.setOnClickListener(new ButtonOnClickListener());
		next_button = (Button) findViewById(R.id.next_button);
		next_button.setOnClickListener(new ButtonOnClickListener());

		mbta = BluetoothAdapter.getDefaultAdapter();
		if (mbta == null) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("警告").setMessage("此设备不支持蓝牙")
					.setPositiveButton("确定", null).show();
			return;
		}

		if ("".equals(MiniPersonInfo.name) || MiniPersonInfo.name == null) {
			if (Contents.custInfoResponseResult != null
					&& Contents.custInfoResponseResult.getCode() == 0) {
				CustInfoResponse response = Contents.custInfoResponseResult
						.getResult();
				MiniPersonInfo.name = response.getCustName();
				MiniPersonInfo.cardNum = response.getIdNo();
				MiniPersonInfo.sex = response.getIndivSex();
				MiniPersonInfo.birthday = response.getIndivBirth();
				MiniPersonInfo.expiryStartDate = response.getIdTermBgn();
				MiniPersonInfo.expiryEndDate = response.getIdTermEnd();
				MiniPersonInfo.birthPlace = response.getIndivRegAddr();
				MiniPersonInfo.cardType = "20";
				// 民族
			}
		}
	}

	private void updateGenderAndBirthday(String cardNum) {
		if (cardNum.length() == 18) {
			String birthday = cardNum.substring(6, 10) + "-"
					+ cardNum.substring(10, 12) + "-"
					+ cardNum.substring(12, 14);
			birthday_text_value.setText(birthday);
			int sex = Integer.valueOf(cardNum.substring(16, 17));
			sex_text_value.setText((sex + 2) % 2 == 0 ? "女" : "男");
		} else if (cardNum.length() == 15) {
			String birthday = "19" + cardNum.substring(6, 8) + "-"
					+ cardNum.substring(8, 10) + "-"
					+ cardNum.substring(10, 12);
			birthday_text_value.setText(birthday);
			int sex = Integer.valueOf(cardNum.substring(14, 15));
			sex_text_value.setText((sex + 2) % 2 == 0 ? "女" : "男");

		}
		MiniPersonInfo.birthday = birthday_text_value.getText().toString();
		if ("男".equals(sex_text_value.getText().toString())) {
			MiniPersonInfo.sex = "1";
		} else if ("女".equals(sex_text_value.getText().toString())) {
			MiniPersonInfo.sex = "2";
		}

	}

	@Override
	protected void onResume() {
		name_text_value.setText(MiniPersonInfo.name);
		// name_pinyin_text_value.setText(LoanPayInfo.cardInfo.getNamePinyin());
		nationality_text_value.setSelection(MiniPersonInfo.nationalityID);

		birthday_text_value.setText(MiniPersonInfo.birthday);
		cardNum_text_value.setText(MiniPersonInfo.cardNum);
		// expiryDate_text_value.setText(LoanPayInfo.cardInfo.getExpiryDate());
		expiryDate_start_button.setText(MiniPersonInfo.expiryStartDate);
		expiryDate_end_button.setText(MiniPersonInfo.expiryEndDate);
		birthPlace_text_value.setText(MiniPersonInfo.birthPlace);
		String st_sext = null;
		if (MiniPersonInfo.sex.equals("1")) {
			st_sext = "男";
		} else if (MiniPersonInfo.sex.equals("2")) {
			st_sext = "女";
		} else {
			st_sext = "未知";
		}
		sex_text_value.setText(st_sext);
		super.onResume();
	}

	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		if (requestCode == REQUEST_ENABLE_BT) {
			if (resultCode == RESULT_OK) {
				// Toast.makeText(this, "启用蓝牙成功", Toast.LENGTH_SHORT);
				selectDevice();
			}
		}
	}

	private class ButtonOnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			if (v == next_button) {
				RegexResult temp = null;
				// 姓名
				String name = name_text_value.getText().toString().trim();
				temp = RegexCust.length("姓名", name, 2, 30);
				if (temp.match) {
					temp = RegexCust.chinese("姓名", name);
					if (!temp.match) {
						result = new RegexResult(false, "姓名应为汉字!");
						showDialog(INFO_ERROR);
						return;
					}
				} else {
					if (name.length() == 0) {
						result = new RegexResult(false, "姓名不能为空!");
					} else {
						result = new RegexResult(false, "姓名字符长度不对!");
					}
					showDialog(INFO_ERROR);
					return;
				}
				// 民族
				String nationality = nation;
				// 证件号码
				final String cardNum = cardNum_text_value.getText().toString()
						.trim();
				IdcardValidator idCardValidator = new IdcardValidator();
				if (cardNum.length() == 0) {
					result = new RegexResult(false, "证件号码不能为空!");
					showDialog(INFO_ERROR);
					return;
				} else if (!idCardValidator.isValidatedAllIdcard(cardNum)) {
					result = new RegexResult(false, "证件号码有误!");
					showDialog(INFO_ERROR);
					return;
				}

				// 性别
				String sex = sex_text_value.getText().toString().trim();
				if ("".equals(sex)) {
					result = new RegexResult(false, "请选择性别!");
					showDialog(INFO_ERROR);
					return;
				}
				// 出生日期
				String birthday = birthday_text_value.getText().toString()
						.trim();

				// 证件期限
				String expiryDateStart = expiryDate_start_button.getText()
						.toString().trim();
				String expiryDateEnd = expiryDate_end_button.getText()
						.toString().trim();
				if ("".equals(expiryDateStart) || "".equals(expiryDateEnd)) {
					result = new RegexResult(false, "请填写完证件期限！");
					showDialog(INFO_ERROR);
					return;
				} else {
					String currdt = getTheDayOneMonthAgo();
					String msg = checkDate(expiryDateStart, expiryDateEnd,
							currdt);
					if (!"".equals(msg)) {
						result = new RegexResult(false, msg);
						showDialog(INFO_ERROR);
						return;
					}
				}

				// 籍贯
				String birthPlace = birthPlace_text_value.getText().toString()
						.trim();
				if ("".equals(birthPlace)) {
					result = new RegexResult(false, "请填写户籍地址!");
					showDialog(INFO_ERROR);
					birthPlace_text_value.requestFocus();
					return;
				}

				/**
				 * 将信息存入内存
				 */
				MiniPersonInfo.name = name;
				// 姓名拼音
				// LoanPayInfo.cardInfo.setNamePinyin(name_pinyin_text_value
				// .getText().toString().trim());
				// 民族
				MiniPersonInfo.nationality = nationality;
				// 出生日期
				MiniPersonInfo.birthday = birthday;
				// 证件类型
				MiniPersonInfo.cardType = cardType_text_value.getText()
						.toString().trim();
				// 证件号码
				MiniPersonInfo.cardNum = cardNum;
				// 证件到期时间
				// LoanPayInfo.cardInfo.setExpiryDate(expiryDate_text_value.getText().toString().trim());
				MiniPersonInfo.expiryStartDate = expiryDate_start_button
						.getText().toString().trim();
				MiniPersonInfo.expiryEndDate = expiryDate_end_button.getText()
						.toString().trim();
				// 籍贯
				MiniPersonInfo.birthPlace = birthPlace;
				// 证件照片
				// if (personInfo != null && personInfo.photo != null) {
				// LoanPayInfo.cardInfo.setCardImage(personInfo.photo);
				// }
				// 性别
				if (sex.equals("男")) {
					MiniPersonInfo.sex = "1";
				} else if (sex.equals("女")) {
					MiniPersonInfo.sex = "2";
				}
				System.out.println("MiniPersonInfo.sex" + MiniPersonInfo.sex);
				Intent intent = getIntent();
				intent.setClass(MiniCardScanningActivity.this,
						MiniRecordEssentialInfoEasyActivity.class);
				startActivity(intent);
			}

			if (v == gather_button) {
				if (mbta == null)
					return;
				if (mbta.isEnabled()) {
					selectDevice();
				} else {
					Intent enableBtIntent = new Intent(
							BluetoothAdapter.ACTION_REQUEST_ENABLE);
					startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
				}
			}
		}
	}

	@Override
	protected Dialog onCreateDialog(int id, Bundle args) {
		Dialog dialog = null;
		StringBuffer sb = new StringBuffer();

		switch (id) {
		case INFO_ERROR:
			sb.append(result.msg);
			dialog = DialogUtil.showDialogOneButton2(
					MiniCardScanningActivity.this, sb.toString());
			break;

		default:
			break;
		}
		return super.onCreateDialog(id, args);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		// 用来获取日期和时间的

		Dialog dialog = null;
		switch (id) {
		case BIRTHDAY_DATE_SELECT:
			DatePickerDialog.OnDateSetListener dateListener1 = new DatePickerDialog.OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker datePicker, int year,
						int month, int dayOfMonth) {
					// Calendar月份是从0开始,所以month要加1
					birthday_text_value.setText(year
							+ "-"
							+ (String.valueOf(month + 1).length() == 1 ? "0"
									+ (month + 1) : (month + 1))
							+ "-"
							+ (String.valueOf(dayOfMonth).length() == 1 ? "0"
									+ (dayOfMonth) : (dayOfMonth)));
				}
			};
			dialog = new DatePickerDialog(this, dateListener1,
					calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DAY_OF_MONTH));
			break;
		case START_DATE_SELECT:
			DatePickerDialog.OnDateSetListener dateListener2 = new DatePickerDialog.OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker datePicker, int year,
						int month, int dayOfMonth) {
					// Calendar月份是从0开始,所以month要加1
					expiryDate_start_button.setText(year
							+ "-"
							+ (String.valueOf(month + 1).length() == 1 ? "0"
									+ (month + 1) : (month + 1))
							+ "-"
							+ (String.valueOf(dayOfMonth).length() == 1 ? "0"
									+ (dayOfMonth) : (dayOfMonth)));
					MiniPersonInfo.expiryStartDate = expiryDate_start_button
							.getText().toString();
				}
			};
			dialog = new DatePickerDialog(this, dateListener2,
					calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DAY_OF_MONTH));
			break;
		case END_DATE_SELECT:
			DatePickerDialog.OnDateSetListener dateListener3 = new DatePickerDialog.OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker datePicker, int year,
						int month, int dayOfMonth) {
					// Calendar月份是从0开始,所以month要加1
					expiryDate_end_button.setText(year
							+ "-"
							+ (String.valueOf(month + 1).length() == 1 ? "0"
									+ (month + 1) : (month + 1))
							+ "-"
							+ (String.valueOf(dayOfMonth).length() == 1 ? "0"
									+ (dayOfMonth) : (dayOfMonth)));
					MiniPersonInfo.expiryEndDate = expiryDate_end_button
							.getText().toString();
				}
			};
			dialog = new DatePickerDialog(this, dateListener3,
					calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DAY_OF_MONTH));
			break;
		default:
			break;
		}
		return dialog;
	}

	private void selectDevice() {
		Set<BluetoothDevice> pairedDevices = mbta.getBondedDevices();
		if (pairedDevices.size() > 0) {
			CharSequence[] deviceArray = new CharSequence[pairedDevices.size()];
			// ArrayAdapter<String>deviceArray=new
			// ArrayAdapter<String>(this,R.layout.device_list);
			int i = 0;
			for (BluetoothDevice device : pairedDevices) {
				// deviceArray.add(device.getName() + "\n" +
				// device.getAddress());
				deviceArray[i++] = device.getName() + "\n"
						+ device.getAddress();
			}
			// int count=deviceArray.getCount();
			// Log.i("CardDemo", "Find "+Integer.toString(count) +"device");
			DeviceListClicked clicked = new DeviceListClicked();
			LayoutInflater factory = LayoutInflater.from(this);
			View view = factory.inflate(R.layout.device_list, null);
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("选择一个设备")
					.setSingleChoiceItems(deviceArray, 0, clicked)
					.setPositiveButton("确定", clicked)
					.setNegativeButton("取消", clicked).setView(view).show();
		} else {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("警告").setMessage("没有找到蓝牙设备")
					.setPositiveButton("确定", null).show();
			// Toast.makeText(this, "没有找到蓝牙设备", Toast.LENGTH_SHORT);
		}
	}

	private class DeviceListClicked implements DialogInterface.OnClickListener {
		int selectedIndex = 0;

		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			if (DialogInterface.BUTTON_POSITIVE == which) {
				String str = (String) ((AlertDialog) dialog).getListView()
						.getAdapter().getItem(selectedIndex);
				// Toast.makeText(((AlertDialog)dialog).getContext(),
				// deviceName, Toast.LENGTH_SHORT);
				int index = str.indexOf('\n');
				if (index != -1) {
					String macAddress = str.substring(index + 1);
					// mbta.cancelDiscovery();
					BluetoothDevice device = mbta.getRemoteDevice(macAddress);
					mrcThread = new ReadCardThread(
							MiniCardScanningActivity.this, handler, device);
					mrcThread.start();
					// mbtnConnect.setEnabled(false);
					// mbtnDisconnect.setEnabled(true);
				}
			} else if (DialogInterface.BUTTON_NEGATIVE == which) {

			} else {
				selectedIndex = which;
			}
		}
	}

	public void setNations() {
		adapter = new ArrayAdapter<String>(MiniCardScanningActivity.this,
				R.layout.simple_spinner_item, nationValue);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		nationality_text_value.setAdapter(adapter);
	}

	public void onDisconnect() {
		if (mrcThread != null) {
			mrcThread.StopReadCard();
			mrcThread = null;
		}
	}

	public void onClose(View button) {
		if (mrcThread != null) {
			mrcThread.StopReadCard();
			mrcThread = null;
		}
		this.finish();
	}

	/**
	 * 检查证件的有效性
	 * 
	 * @param beginDt
	 * @param endDt
	 * @param currdt
	 * @return
	 */
	private String checkDate(String beginDt, String endDt, String currdt) {
		String msg = "";
		// if ("00000000".equals(beginDt)) {
		// return "";
		// }
		String currentDt = currdt.substring(0, 4) + currdt.substring(5, 7)
				+ currdt.substring(8, 10);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		if (!"".equals(beginDt) && beginDt != null) {
			beginDt = beginDt.replaceAll("-", "");
			if (beginDt.trim().length() != 8) {
				msg += "证件起始日期 " + beginDt + " 有误;";
			} else {
				try {
					Date ds = df.parse(beginDt);
					String ss = df.format(ds);
					if (!beginDt.equals(ss) && beginDt != ss) {
						msg += "证件起始日期 " + beginDt + " 有误;";
					} else {
						if (beginDt.compareTo(currentDt) > 0) {
							msg += "证件起始日期 " + beginDt + " 大于当前营业日期;";
						} else {
							msg = "";
						}
					}
				} catch (Exception e) {
					msg += "证件起始日期 " + beginDt + " 有误;";
				}
			}
		}
		return msg;
	}

	@SuppressWarnings("deprecation")
	private String getTheDayOneMonthAgo() {
		Date date = new Date();
		int month = date.getMonth();
		if (month == 0) {
			date.setYear(date.getYear() - 1);
			date.setMonth(11);
		} else {
			date.setMonth(month - 1);
		}
		return format(date, "yyyy-MM-dd");
	}

	/**
	 * 将指定的时间格式格式化为指定的时间字符串
	 * 
	 * @param date
	 *            时间对象
	 * 
	 * @param format
	 *            格式
	 * 
	 * @return 时间字符串
	 */
	private String format(Date date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		String s = df.format(date);
		return s;
	}

}