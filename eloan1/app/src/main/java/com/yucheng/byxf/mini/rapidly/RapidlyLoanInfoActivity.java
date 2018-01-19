package com.yucheng.byxf.mini.rapidly;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.apfx.utl.RegexResult;
import com.yucheng.byxf.mini.map.DemoApplication;
import com.yucheng.byxf.mini.message.LoginMessage;
import com.yucheng.byxf.mini.message.MiniConfirmMessage;
import com.yucheng.byxf.mini.message.MiniConfirmResponse;
import com.yucheng.byxf.mini.message.MsgKeyCodeResponse;
import com.yucheng.byxf.mini.message.PoetryResult;
import com.yucheng.byxf.mini.message.RapidlyLoanInfo;
import com.yucheng.byxf.mini.ui.BaseActivity;
import com.yucheng.byxf.mini.ui.HomeActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.ButtonUtil;
import com.yucheng.byxf.mini.utils.CommonUtil;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.ContentsMoney;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.LogUtil;
import com.yucheng.byxf.mini.utils.RegexCust;
import com.yucheng.byxf.mini.utils.StringUtils;

/**
 * 类名: RapidlyLoanInfoActivity</br>
 * 描述: 极速贷申请信息-采集页</br>
 * 开发人员： weiyb</br>
 * 创建时间： 2015-7-20
 */

public class RapidlyLoanInfoActivity extends BaseActivity implements OnItemSelectedListener, OnClickListener,
    OnWheelChangedListener
{
    
    // private String applCde;
    // private String applSeq;
    //
    // private String applDisbSeq;
    // private String applRpymSeq;
    // private String apptSeq;
    private String LocPhoneNum = "";
    
    private RapidlyLoanInfoActivity activity;
    
    /** 姓名 **/
    private EditText et_name;
    
    /** 身份证号 **/
    private EditText et_com_number;
    
    /** 手机号 **/
    private EditText et_com_phone;
    
    /** 现居住地址-省/市、区 **/
    private EditText et_com_address;
    
    /** 现居住地址-详细地址 **/
    private EditText et_com_address_more;
    
    /** 单位名称 **/
    private EditText et_com_name;
    
    /** 单位电话-区号 **/
    private EditText et_com_zone_code;
    
    /** 单位电话-电话 **/
    private EditText et_com_tel_code;
    
    /** 单位电话-分机号 **/
    private EditText et_com_extension_code;
    
    /** 联系人1姓名 **/
    private EditText et_contactName1;
    
    /** 联系人1与申请人关系 **/
    private Spinner sp_contactRelation1;
    
    /** 联系人1手机号码 **/
    private EditText et_mobileNo1;
    
    /** 联系人2姓名 **/
    private EditText et_contactName2;
    
    /** 联系人2与申请人关系 **/
    private Spinner sp_contactRelation2;
    
    /** 联系人2手机号码 **/
    private EditText et_mobileNo2;
    
    /** 贷款金额 **/
    private RadioGroup money_group;
    
    /** 贷款金额-500 **/
    private RadioButton money_500_button;
    
    /** 贷款金额-1000 **/
    private RadioButton money_1000_button;
    
    /** 贷款期限 **/
    private EditText et_loan_deadline;
    
    /** 手续费 **/
    private EditText et_loan_poundage;
    
    /** 还款方式 **/
    private EditText et_repayment_way;
    
    /** 银行姓名 **/
    private RadioGroup bank_name_group;
    
    /** 银行姓名-北京银行 **/
    private RadioButton beijing_button;
    
    /** 银行姓名-农业银行 **/
    private RadioButton nongye_button;
    
    /** 放/还款账号 **/
    private EditText et_account_number;
    
    private String[] apply_relationship = {"请选择", "配偶", "父母", "子女", "兄弟姐妹"};
    
    private String[] apply_relationshipCode = {"", "01", "02", "03", "09"};
    
    private String[] apply_relationship2 = {"请选择", "配偶", "父母", "子女", "其他血亲", "其他姻亲", "同事", "合伙人", "其他关系", "同学", "朋友",
        "兄弟姐妹"};
    
    private String[] apply_relationshipCode2 = new String[] {"", "01", "02", "03", "04", "05", "06", "07", "08", "10",
        "11", "09"};
    
    private String str_contactRelation1;
    
    private String str_contactRelation2;
    
    private LinearLayout include;
    
    private Button back;
    
    private TextView easy_loan_head;
    
    private Button next_Button;
    
    // 验证结果
    private RegexResult result;
    
    // 信息有误
    private final int INFO_ERROR = 0;
    
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
    
    private RapidlyLoanInfo rapidlyLoanInfo;
    
    private String bankName = "北京银行";
    
    private String money = "500";
    
    private String type = null;
    
    private String cardNumDES;
    
    private boolean isRegisterFlag = false;
    
    private String openHome;
    
    protected DemoApplication application = null;
    
    Dialog dialog = null;
    
    private Button bt_addRe1;
    
    private Button bt_addRe2;
    
    String username, usernumber;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rapidly_loan_info_activity);
        
        if (null == Contents.province || "".equals(Contents.province))
        {
            getLocationInfo();
        }
        rapidlyLoanInfo = new RapidlyLoanInfo();
        // SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
        // this);
        
        initJsonData();
        init();
        
        if (Contents.isChoice)
        {
            rapidlyLoanInfo = RapidlyLoanInfoContents.getRapidlyLoanInfo();
            
            System.out.println("===打回>" + rapidlyLoanInfo.getMoney());
        }
        else
        {
            if (preferencesUtils.hasData("repidly_et_com_number"))
            {
                if ((Contents.response != null && Contents.response.getResult() != null && Contents.response.getResult()
                    .getIdNo() != null)
                    && !preferencesUtils.getData("repidly_et_com_number", "").equals(Contents.response.getResult()
                        .getIdNo()))
                {
                    preferencesUtils.clearData();
                    // ------------存登陆标志信息
                    preferencesUtils.setData("login_sign", "login_sign");
                }
            }
            
            if (preferencesUtils.hasData("repidly_et_com_address"))
            {
                editinit();
            }
            else
            {
                // 填充数据
                MessageInit();
                
            }
            
        }
        
        // initJsonData();
        // init();
        
        setData();
        // 获取流水号！！！
        Contents.applCde = null;
        Contents.applSeq = null;
        Contents.applDisbSeq = null;
        Contents.applRpymSeq = null;
        Contents.apptSeq = null;
        if (Contents.isChoice)
        {
            // 是打回操作！！！！！
            Contents.applCde = RapidlyLoanInfoContents.MiniConfirmMessage.getApplCde();
            Contents.applSeq = RapidlyLoanInfoContents.MiniConfirmMessage.getApplSeq();// 申请流水号
            Contents.applDisbSeq = RapidlyLoanInfoContents.MiniConfirmMessage.getApplDisbSeq();
            Contents.applRpymSeq = RapidlyLoanInfoContents.MiniConfirmMessage.getApplRpymSeq();
            Contents.apptSeq = RapidlyLoanInfoContents.MiniConfirmMessage.getApptSeq();
            Contents.poetry_str = null;
        }
        else
        {
            // 不是打回操作！！！！！
            // 获取 流水号
            new GetSeqAsyncTask().execute();
        }
        new IsChoiseCodeKeyAsyncTask().execute("quickApplyMsgValid");
        
        LocPhoneNum = initPhone();
        // ceshi
        // LocPhoneNum="138888";
        if ("".equals(LocPhoneNum))
        {
            
        }
        else
        {
            et_com_phone.setText(LocPhoneNum);
        }
    }
    
    private void MessageInit()
    {
        if (Contents.response != null && Contents.response.getResult() != null)
        {
            LoginMessage loginResult = Contents.response.getResult();
            
            if ((null != loginResult.getLiveAddr()) && !loginResult.getLiveAddr().equals(""))
            {
                // 居住地址
                rapidlyLoanInfo.setAddress(loginResult.getLiveAddr());
            }
            if (null != loginResult.getCurentResidentialAddr() && !loginResult.getCurentResidentialAddr().equals(""))
            {
                // 居住地址2
                rapidlyLoanInfo.setAddress_more(loginResult.getCurentResidentialAddr());
            }
            if (null != loginResult.getEmpName() && !loginResult.getEmpName().equals(""))
            {
                // 单位名称
                rapidlyLoanInfo.setCompanyName(loginResult.getEmpName());
            }
            if (null != loginResult.getEmpZone() && !loginResult.getEmpZone().equals(""))
            {
                // 单位区号
                rapidlyLoanInfo.setCompany_phone_zone_code(loginResult.getEmpZone());
            }
            if (null != loginResult.getEmpTelNo() && !loginResult.getEmpTelNo().equals(""))
            {
                // 单位电话
                rapidlyLoanInfo.setCompany_phone_tel_code(loginResult.getEmpTelNo());
            }
            if (null != loginResult.getEmpTelSub() && !loginResult.getEmpTelSub().equals(""))
            {
                // 单位分机号
                rapidlyLoanInfo.setCompany_phone_extension_code(loginResult.getEmpTelSub());
            }
        }
    };
    
    private void setData()
    {
        if (rapidlyLoanInfo != null)
        {
            if (!"".equals(rapidlyLoanInfo.getContactRelationCode1()))
            {
                for (int i = 0; i < apply_relationshipCode.length; i++)
                {
                    if (apply_relationshipCode[i].equals(rapidlyLoanInfo.getContactRelationCode1()))
                    {
                        rapidlyLoanInfo.setContactRelation1(apply_relationship[i]);
                    }
                }
            }
            else
            {
                rapidlyLoanInfo.setContactRelation1("请选择");
            }
            
            if (!"".equals(rapidlyLoanInfo.getContactRelation1()))
            {
                for (int i = 0; i < apply_relationship.length; i++)
                {
                    if (apply_relationship[i].equals(rapidlyLoanInfo.getContactRelation1()))
                    {
                        sp_contactRelation1.setSelection(i);
                        sp_contactRelation1.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
                    }
                }
            }
            
            if (!"".equals(rapidlyLoanInfo.getContactRelationCode2()))
            {
                for (int i = 0; i < apply_relationshipCode2.length; i++)
                {
                    if (apply_relationshipCode2[i].equals(rapidlyLoanInfo.getContactRelationCode2()))
                    {
                        rapidlyLoanInfo.setContactRelation2(apply_relationship2[i]);
                    }
                }
            }
            else
            {
                rapidlyLoanInfo.setContactRelation2("请选择");
            }
            
            if (!"".equals(rapidlyLoanInfo.getContactRelation2()))
            {
                for (int i = 0; i < apply_relationship2.length; i++)
                {
                    if (apply_relationship2[i].equals(rapidlyLoanInfo.getContactRelation2()))
                    {
                        sp_contactRelation2.setSelection(i);
                        sp_contactRelation2.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
                    }
                }
            }
            
            et_com_address.setText(rapidlyLoanInfo.getAddress());
            et_com_address_more.setText(rapidlyLoanInfo.getAddress_more());
            et_com_phone.setText(rapidlyLoanInfo.getPhone());
            et_com_name.setText(rapidlyLoanInfo.getCompanyName());
            et_com_zone_code.setText(rapidlyLoanInfo.getCompany_phone_zone_code());
            et_com_tel_code.setText(rapidlyLoanInfo.getCompany_phone_tel_code());
            et_com_extension_code.setText(rapidlyLoanInfo.getCompany_phone_extension_code());
            et_contactName1.setText(rapidlyLoanInfo.getContactName1());
            et_contactName2.setText(rapidlyLoanInfo.getContactName2());
            et_mobileNo1.setText(rapidlyLoanInfo.getMobileNo1());
            et_mobileNo2.setText(rapidlyLoanInfo.getMobileNo2());
            
            if ("500".equals(rapidlyLoanInfo.getMoney()))
            {
                money_500_button.setChecked(true);
                
                et_loan_poundage.setText(ContentsMoney.MapMoney.get("500") + "元");
                
                // et_loan_poundage.setText("10元");
            }
            else if ("1000".equals(rapidlyLoanInfo.getMoney()))
            {
                money_1000_button.setChecked(true);
                // et_loan_poundage.setText("20元");
                
                et_loan_poundage.setText(ContentsMoney.MapMoney.get("1000") + "元");
                
            }
            
            if ("ABC".equals(rapidlyLoanInfo.getBankName()))
            {
                nongye_button.setChecked(true);
                rapidlyLoanInfo.setBankName("农业银行");
            }
            else if ("BOB".equals(rapidlyLoanInfo.getBankName()))
            {
                beijing_button.setChecked(true);
                rapidlyLoanInfo.setBankName("北京银行");
            }
            if ("农业银行".equals(rapidlyLoanInfo.getBankName()))
            {
                nongye_button.setChecked(true);
                rapidlyLoanInfo.setBankName("农业银行");
                
            }
            else if ("北京银行".equals(rapidlyLoanInfo.getBankName()))
            {
                beijing_button.setChecked(true);
                rapidlyLoanInfo.setBankName("北京银行");
            }
            
            et_account_number.setText(rapidlyLoanInfo.getCardNo());
            
            et_com_name.setText(rapidlyLoanInfo.getCompanyName());
        }
    }
    
    private void editinit()
    {
        // SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
        // this);
        rapidlyLoanInfo.setPhone(preferencesUtils.getData("repidly_et_com_phone", ""));
        rapidlyLoanInfo.setAddress(preferencesUtils.getData("repidly_et_com_address", ""));
        /** 现居住地址-省/市、区 **/
        // et_com_address.setText();
        
        /** 现居住地址-详细地址 **/
        rapidlyLoanInfo.setAddress_more(preferencesUtils.getData("repidly_et_com_address_more", ""));
        /** 单位名称 **/
        rapidlyLoanInfo.setCompanyName(preferencesUtils.getData("repidly_et_com_name", ""));
        /** 单位电话-区号 **/
        rapidlyLoanInfo.setCompany_phone_zone_code(preferencesUtils.getData("repidly_et_com_zone_code", ""));
        /** 单位电话-电话 **/
        rapidlyLoanInfo.setCompany_phone_tel_code(preferencesUtils.getData("repidly_et_com_tel_code", ""));
        /** 单位电话-分机号 **/
        rapidlyLoanInfo.setCompany_phone_extension_code(preferencesUtils.getData("repidly_et_com_extension_code", ""));
        /** 联系人1姓名 **/
        rapidlyLoanInfo.setContactName1(preferencesUtils.getData("repidly_et_contactName1", ""));
        /** 联系人1与申请人关系 **/
        rapidlyLoanInfo.setContactRelation1(preferencesUtils.getData("repidly_sp_contactRelation1", ""));
        rapidlyLoanInfo.setMobileNo1(preferencesUtils.getData("repidly_et_mobileNo1", ""));
        /** 联系人2姓名 **/
        rapidlyLoanInfo.setContactName2(preferencesUtils.getData("repidly_et_contactName2", ""));
        /** 联系人2与申请人关系 **/
        rapidlyLoanInfo.setContactRelation2(preferencesUtils.getData("repidly_sp_contactRelation2", ""));
        /** 联系人2手机号码 **/
        rapidlyLoanInfo.setMobileNo2(preferencesUtils.getData("repidly_et_mobileNo2", ""));
        /** 贷款金额 **/
        rapidlyLoanInfo.setMoney(preferencesUtils.getData("repidly_money_group", ""));
        System.out.println("=贷款金额==>>>" + preferencesUtils.getData("repidly_money_group", ""));
        /** 银行姓名-北京银行 **/
        rapidlyLoanInfo.setBankName(preferencesUtils.getData("repidly_bank_name_group", ""));
        System.out.println("=银行姓名-==>>>" + preferencesUtils.getData("repidly_bank_name_group", ""));
        /** 放/还款账号 **/
        rapidlyLoanInfo.setCardNo(preferencesUtils.getData("repidly_et_account_number", ""));
        
        // cade2
        rapidlyLoanInfo.setContactRelationCode1(preferencesUtils.getData("apply_relationshipCode", ""));
        rapidlyLoanInfo.setContactRelationCode2(preferencesUtils.getData("apply_relationshipCode2", ""));
        
    }
    
    private void shareclear()
    {
        // SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
        // this);
        // preferencesUtils.setData(", value)
        // 姓名
        if (preferencesUtils.hasData("repidly_et_name"))
        {
            preferencesUtils.removeData("repidly_et_name");
            // 身份证号
            preferencesUtils.removeData("repidly_et_com_number");
            // 手机号
            preferencesUtils.removeData("repidly_et_com_phone");
            // 住址市区
            preferencesUtils.removeData("repidly_et_com_address");
            // 详细地址
            preferencesUtils.removeData("repidly_et_com_address_more");
            // 单位名称
            preferencesUtils.removeData("repidly_et_com_name");
            // 单位电话区号
            preferencesUtils.removeData("repidly_et_com_zone_code");
            // 单位电话电话
            preferencesUtils.removeData("repidly_et_com_tel_code");
            // 单位电话-分机号
            preferencesUtils.removeData("repidly_et_com_extension_code");
            // 联系人1姓名
            preferencesUtils.removeData("repidly_et_contactName1");
            // 联系人1与申请人关系
            preferencesUtils.removeData("repidly_sp_contactRelation1");
            // 联系人1手机号码
            preferencesUtils.removeData("repidly_et_mobileNo1");
            // 练习人2姓名
            preferencesUtils.removeData("repidly_et_contactName2");
            // 联系人2与申请人关系
            preferencesUtils.removeData("repidly_sp_contactRelation2");
            // 联系人2手机号
            preferencesUtils.removeData("repidly_et_mobileNo2");
            // 贷款金额
            preferencesUtils.removeData("repidly_money_group");
            // 贷款期限
            preferencesUtils.removeData("repidly_et_loan_deadline");
            // 手续费
            preferencesUtils.removeData("repidly_et_loan_poundage");
            // 还款方式
            // preferencesUtils.removeData("et_repayment_way");
            // 银行名称
            preferencesUtils.removeData("repidly_bank_name_group");
            // 放还款账号
            preferencesUtils.removeData("repidly_et_account_number");
            
            // code
            preferencesUtils.removeData("apply_relationshipCode");
            preferencesUtils.removeData("apply_relationshipCode2");
            
        }
        
    }
    
    private void shareinit()
    {
        // SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
        // this);
        // 姓名
        preferencesUtils.setData("repidly_et_name", rapidlyLoanInfo.getName());
        // 身份证号
        preferencesUtils.setData("repidly_et_com_number", rapidlyLoanInfo.getIdNo());
        // 手机号
        preferencesUtils.setData("repidly_et_com_phone", rapidlyLoanInfo.getPhone());
        // 住址市区
        preferencesUtils.setData("repidly_et_com_address", rapidlyLoanInfo.getAddress());
        // 详细地址
        preferencesUtils.setData("repidly_et_com_address_more", rapidlyLoanInfo.getAddress_more());
        // 单位名称
        preferencesUtils.setData("repidly_et_com_name", rapidlyLoanInfo.getCompanyName());
        // 单位电话区号
        preferencesUtils.setData("repidly_et_com_zone_code", rapidlyLoanInfo.getCompany_phone_zone_code());
        // 单位电话电话
        preferencesUtils.setData("repidly_et_com_tel_code", rapidlyLoanInfo.getCompany_phone_tel_code());
        // 单位电话-分机号
        preferencesUtils.setData("repidly_et_com_extension_code", rapidlyLoanInfo.getCompany_phone_extension_code());
        // 联系人1姓名
        preferencesUtils.setData("repidly_et_contactName1", rapidlyLoanInfo.getContactName1());
        // 联系人1与申请人关系
        preferencesUtils.setData("repidly_sp_contactRelation1", rapidlyLoanInfo.getContactRelation1());
        // 联系人1手机号码
        preferencesUtils.setData("repidly_et_mobileNo1", rapidlyLoanInfo.getMobileNo1());
        // 练习人2姓名
        preferencesUtils.setData("repidly_et_contactName2", rapidlyLoanInfo.getContactName2());
        // 联系人2与申请人关系
        preferencesUtils.setData("repidly_sp_contactRelation2", rapidlyLoanInfo.getContactRelation2());
        // 联系人2手机号
        preferencesUtils.setData("repidly_et_mobileNo2", rapidlyLoanInfo.getMobileNo2());
        // 贷款金额
        preferencesUtils.setData("repidly_money_group", rapidlyLoanInfo.getMoney());
        // 贷款期限
        // preferencesUtils.setData("et_loan_deadline", rapidlyLoanInfo.);
        // 手续费
        preferencesUtils.setData("repidly_et_loan_poundage", rapidlyLoanInfo.getPoundage());
        // 还款方式
        // preferencesUtils.setData("et_repayment_way",
        // rapidlyLoanInfo.getRepayment_way());
        // 银行名称
        preferencesUtils.setData("repidly_bank_name_group", rapidlyLoanInfo.getBankName());
        // 放还款账号
        preferencesUtils.setData("repidly_et_account_number", rapidlyLoanInfo.getCardNo());
        
        preferencesUtils.setData("apply_relationshipCode", rapidlyLoanInfo.getContactRelationCode1());
        preferencesUtils.setData("apply_relationshipCode2", rapidlyLoanInfo.getContactRelationCode2());
        
    }
    
    private void init()
    {
        
        choose_button = (Button)findViewById(R.id.choose_button);
        
        city_layout = (LinearLayout)findViewById(R.id.city_layout);
        
        mProvince = (WheelView)findViewById(R.id.id_province);
        mCity = (WheelView)findViewById(R.id.id_city);
        mArea = (WheelView)findViewById(R.id.id_area);
        
        choose_button.setOnClickListener(this);
        
        next_Button = (Button)findViewById(R.id.sure_button);
        include = (LinearLayout)findViewById(R.id.head);
        back = (Button)include.findViewById(R.id.bt_back_common);
        easy_loan_head = (TextView)include.findViewById(R.id.easy_loan_head);
        easy_loan_head.setText("填写申请信息");
        bt_addRe1 = (Button)findViewById(R.id.relation1);
        bt_addRe2 = (Button)findViewById(R.id.relation2);
        bt_addRe1.setOnClickListener(this);
        bt_addRe2.setOnClickListener(this);
        back.setOnClickListener(new OnClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                // 返回主页
                Intent it = new Intent(RapidlyLoanInfoActivity.this, HomeActivity.class);
                startActivity(it);
                finish();
            }
        });
        
        et_name = (EditText)findViewById(R.id.et_name);
        et_com_number = (EditText)findViewById(R.id.et_com_number);
        et_com_phone = (EditText)findViewById(R.id.et_com_phone);
        if (null != Contents.response && null != Contents.response.getResult())
        {
            et_name.setText(Contents.response.getResult().getCipNamecn());
            et_com_number.setText(Contents.response.getResult().getIdNo());
            // et_com_phone.setText(Contents.response.getResult().getCipMobile());
        }
        et_com_address = (EditText)findViewById(R.id.et_com_address);
        et_com_address_more = (EditText)findViewById(R.id.et_com_address_more);
        et_com_name = (EditText)findViewById(R.id.et_com_name);
        et_com_zone_code = (EditText)findViewById(R.id.et_com_zone_code);
        et_com_tel_code = (EditText)findViewById(R.id.et_com_tel_code);
        et_com_extension_code = (EditText)findViewById(R.id.et_com_extension_code);
        et_contactName1 = (EditText)findViewById(R.id.et_contactName1);
        sp_contactRelation1 = (Spinner)findViewById(R.id.sp_contactRelation1);
        CommonUtil.setSpinner(this, apply_relationship, sp_contactRelation1);
        sp_contactRelation1.setOnItemSelectedListener(this);
        
        et_mobileNo1 = (EditText)findViewById(R.id.et_mobileNo1);
        et_contactName2 = (EditText)findViewById(R.id.et_contactName2);
        sp_contactRelation2 = (Spinner)findViewById(R.id.sp_contactRelation2);
        CommonUtil.setSpinner(this, apply_relationship2, sp_contactRelation2);
        sp_contactRelation2.setOnItemSelectedListener(this);
        et_mobileNo2 = (EditText)findViewById(R.id.et_mobileNo2);
        money_group = (RadioGroup)findViewById(R.id.money_group);
        money_500_button = (RadioButton)findViewById(R.id.money_500_button);
        money_1000_button = (RadioButton)findViewById(R.id.money_1000_button);
        et_loan_deadline = (EditText)findViewById(R.id.et_loan_deadline);
        et_loan_poundage = (EditText)findViewById(R.id.et_loan_poundage);
        et_repayment_way = (EditText)findViewById(R.id.et_repayment_way);
        bank_name_group = (RadioGroup)findViewById(R.id.bank_name_group);
        beijing_button = (RadioButton)findViewById(R.id.beijing_button);
        nongye_button = (RadioButton)findViewById(R.id.nongye_button);
        et_account_number = (EditText)findViewById(R.id.et_account_number);
        
        et_loan_deadline.setText("一个月");
        et_loan_poundage.setText(ContentsMoney.MapMoney.get("500") + "元");
        beijing_button.setChecked(true);
        
        /** 还款方式 **/
        et_repayment_way.setText("一次还本");
        
        money_group.setOnCheckedChangeListener(new OnCheckedChangeListener()
        {
            
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                // TODO Auto-generated method stub
                switch (group.getCheckedRadioButtonId())
                {
                    case R.id.money_500_button:
                        et_loan_poundage.setText(ContentsMoney.MapMoney.get("500") + "元");
                        // et_loan_poundage.setText("10元");
                        money = "500";
                        break;
                    
                    case R.id.money_1000_button:
                        et_loan_poundage.setText(ContentsMoney.MapMoney.get("1000") + "元");
                        // et_loan_poundage.setText("20元");
                        money = "1000";
                        break;
                }
            }
        });
        
        et_account_number.setFilters(new InputFilter[] {new InputFilter.LengthFilter(19)});
        
        bank_name_group.setOnCheckedChangeListener(new OnCheckedChangeListener()
        {
            
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                // TODO Auto-generated method stub
                switch (group.getCheckedRadioButtonId())
                {
                    case R.id.beijing_button:
                        et_account_number.setFilters(new InputFilter[] {new InputFilter.LengthFilter(19)});
                        bankName = "北京银行";
                        et_account_number.setText("");
                        break;
                    
                    case R.id.nongye_button:
                        et_account_number.setFilters(new InputFilter[] {new InputFilter.LengthFilter(23)});
                        bankName = "农业银行";
                        et_account_number.setText("");
                        break;
                }
            }
        });
        
        et_account_number.addTextChangedListener(new TextWatcher()
        {
            int beforeTextLength = 0;
            
            int onTextLength = 0;
            
            boolean isChanged = false;
            
            int location = 0;// 记录光标的位置
            
            private char[] tempChar;
            
            private StringBuffer buffer = new StringBuffer();
            
            int konggeNumberB = 0;
            
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {// 第二步骤(改变中)
             // TODO Auto-generated method stub
                onTextLength = s.length();
                buffer.append(s.toString());
                if (onTextLength == beforeTextLength || onTextLength <= 3 || isChanged)
                {
                    isChanged = false;
                    return;
                }
                isChanged = true;
            }
            
            // 先调这个(1);
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                // TODO Auto-generated method stub
                beforeTextLength = s.length();
                if (buffer.length() > 0)
                {
                    buffer.delete(0, buffer.length());
                }
                konggeNumberB = 0;
                for (int i = 0; i < s.length(); i++)
                {
                    if (s.charAt(i) == ' ')
                    {
                        konggeNumberB++;
                    }
                }
            }
            
            @Override
            public void afterTextChanged(Editable s)
            {
                // TODO Auto-generated method stub
                if (isChanged)
                {
                    location = et_account_number.getSelectionEnd();
                    int index = 0;
                    while (index < buffer.length())
                    {
                        if (buffer.charAt(index) == ' ')
                        {
                            buffer.deleteCharAt(index);
                        }
                        else
                        {
                            index++;
                        }
                    }
                    index = 0;
                    int konggeNumberC = 0;
                    while (index < buffer.length())
                    {
                        if ((index == 4 || index == 9 || index == 14 || index == 19))
                        {
                            buffer.insert(index, ' ');
                            konggeNumberC++;
                        }
                        index++;
                    }
                    
                    if (konggeNumberC > konggeNumberB)
                    {
                        location += (konggeNumberC - konggeNumberB);
                    }
                    
                    tempChar = new char[buffer.length()];
                    buffer.getChars(0, buffer.length(), tempChar, 0);
                    String str = buffer.toString();
                    if (location > str.length())
                    {
                        location = str.length();
                    }
                    else if (location < 0)
                    {
                        location = 0;
                    }
                    et_account_number.setText(str);
                    // System.out.println(str.replace(" ", ""));
                    Editable etable = et_account_number.getText();
                    Selection.setSelection(etable, location);
                    isChanged = false;
                }
            }
        });
        next_Button.setOnClickListener(this);
        et_com_address.setOnClickListener(this);
        
        initDatas();
        
        mProvince.setViewAdapter(new ArrayWheelAdapter<String>(this, mProvinceDatas));
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
    
    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateAreas()
    {
        int pCurrent = mCity.getCurrentItem();
        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
        String[] areas = mAreaDatasMap.get(mCurrentCityName);
        
        if (areas == null)
        {
            areas = new String[] {""};
            mCurrentAreaName = "";
        }
        else
        {
            mCurrentAreaName = mAreaDatasMap.get(mCurrentCityName)[0];
        }
        mArea.setViewAdapter(new ArrayWheelAdapter<String>(this, areas));
        mArea.setCurrentItem(0);
    }
    
    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities()
    {
        int pCurrent = mProvince.getCurrentItem();
        mCurrentProviceName = mProvinceDatas[pCurrent];
        String[] cities = mCitisDatasMap.get(mCurrentProviceName);
        if (cities == null)
        {
            cities = new String[] {""};
        }
        mCity.setViewAdapter(new ArrayWheelAdapter<String>(this, cities));
        mCity.setCurrentItem(0);
        updateAreas();
    }
    
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        // TODO Auto-generated method stub
        if (parent == sp_contactRelation1)
        {
            str_contactRelation1 = (String)sp_contactRelation1.getItemAtPosition(position);
            rapidlyLoanInfo.setContactRelation1(str_contactRelation1);
            if ("请选择".equals(str_contactRelation1))
            {
                sp_contactRelation1.setBackgroundResource(R.drawable.comm_spinner_selecter);
            }
            else
            {
                sp_contactRelation1.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
                // if ("配偶".equals(str_contactRelation1)) {
                // apply_relationship2 = new String[] { "请选择", "父母", "子女",
                // "其他血亲", "其他姻亲", "同事", "合伙人", "其他关系", "同学", "朋友",
                // "兄弟姐妹" };
                // apply_relationshipCode2 = new String[] { "", "02", "03",
                // "04", "05", "06", "07", "08", "09", "10", "11" };
                // } else {
                // apply_relationship2 = new String[] { "请选择", "配偶", "父母",
                // "子女", "其他血亲", "其他姻亲", "同事", "合伙人", "其他关系", "同学",
                // "朋友", "兄弟姐妹" };
                // apply_relationshipCode2 = new String[] { "", "01", "02",
                // "03", "04", "05", "06", "07", "08", "09", "10",
                // "11" };
                // }
                // CommonUtil.setSpinner(this, apply_relationship2,
                // sp_contactRelation2);
                // if ("请选择".equals(rapidlyLoanInfo.getContactRelation2())) {
                // sp_contactRelation2
                // .setBackgroundResource(R.drawable.comm_spinner_selecter);
                // } else {
                // sp_contactRelation2
                // .setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
                // for (int i = 0; i < apply_relationship2.length; i++) {
                // if
                // (apply_relationship2[i].equals(rapidlyLoanInfo.getContactRelation2()))
                // {
                // sp_contactRelation2.setSelection(i);
                //
                // }
                // }
                // }
            }
            sp_contactRelation1.setPadding(15, 0, 0, 0);
        }
        if (parent == sp_contactRelation2)
        {
            str_contactRelation2 = (String)sp_contactRelation2.getItemAtPosition(position);
            rapidlyLoanInfo.setContactRelation2(str_contactRelation2);
            if ("请选择".equals(str_contactRelation2))
            {
                sp_contactRelation2.setBackgroundResource(R.drawable.comm_spinner_selecter);
            }
            else
            {
                sp_contactRelation2.setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
                // if ("配偶".equals(str_contactRelation2)) {
                // apply_relationship = new String[] { "请选择", "父母", "子女"
                // ,"兄弟姐妹"};
                // apply_relationshipCode = new String[] { "", "02", "03"
                // ,"11"};
                // } else {
                // apply_relationship = new String[] { "请选择", "配偶", "父母", "子女"
                // ,"兄弟姐妹"};
                // apply_relationshipCode = new String[] { "", "01", "02",
                // "03","11" };
                // }
                // CommonUtil.setSpinner(this, apply_relationship,
                // sp_contactRelation1);
                //
                // if ("请选择".equals(rapidlyLoanInfo.getContactRelation1())) {
                // sp_contactRelation1
                // .setBackgroundResource(R.drawable.comm_spinner_selecter);
                // } else {
                // sp_contactRelation1
                // .setBackgroundResource(R.drawable.comm_spinner_xuanzhong);
                //
                // for (int i = 0; i < apply_relationship.length; i++) {
                // if (apply_relationship[i].equals(str_contactRelation1)) {
                // sp_contactRelation1.setSelection(i);
                // }
                // }
                // }
                
            }
            sp_contactRelation2.setPadding(15, 0, 0, 0);
        }
    }
    
    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void onClick(View v)
    {
        // TODO Auto-generated method stub
        super.onClick(v);
        if (v == next_Button)
        {
            if (!"".equals(LocPhoneNum))
            {
                if (!(LocPhoneNum).equals(et_com_phone.getText().toString()))
                {
                    
                    Toast.makeText(getApplicationContext(), "请填写正确的本机手机号！", Toast.LENGTH_LONG).show();
                    return;
                }
            }
            
            // wz8.5
            if (ButtonUtil.isFastDoubleClick())
            {
                return;
            }
            
            RegexResult temp = null;
            String name = et_name.getText().toString();
            rapidlyLoanInfo.setName(name);
            String idNo = et_com_number.getText().toString();
            rapidlyLoanInfo.setIdNo(idNo);
            String phone = et_com_phone.getText().toString();
            
            // 0326手机号码
            temp = RegexCust.required("手机号码", phone);
            if (temp.match)
            {
                if (RegexCust.phoneMob(phone) == false)
                {
                    result = new RegexResult(false, "手机号码格式不对!");
                    showDialog(INFO_ERROR);
                    et_com_phone.requestFocus();
                    return;
                }
            }
            else
            {
                result = temp;
                showDialog(INFO_ERROR);
                et_com_phone.requestFocus();
                return;
            }
            rapidlyLoanInfo.setPhone(phone);
            
            String address = et_com_address.getText().toString();
            String addressMore = et_com_address_more.getText().toString();
            if ("".equals(address) || null == address)
            {
                result = new RegexResult(false, "请选择居住地址的省市区");
                showDialog(INFO_ERROR);
                return;
            }
            if ("".equals(addressMore) || null == addressMore)
            {
                result = new RegexResult(false, "居住地址详细地址不能为空");
                showDialog(INFO_ERROR);
                et_com_address_more.requestFocus();
                return;
            }
            if (RegexCust.nosymbol(addressMore) == false)
            {
                result = new RegexResult(false, "详细居住地址禁止输入符号等字符");
                showDialog(INFO_ERROR);
                et_com_address_more.requestFocus();
                return;
            }
            
            // if (RegexCust.phoneMob(st_mobileNo2) == false) {
            // result = new RegexResult(false, "联系人2手机号码格式不对!");
            // showDialog(INFO_ERROR);
            // et_mobileNo2.requestFocus();
            // return;
            // }
            String companyName = et_com_name.getText().toString();
            if ("".equals(companyName) || null == companyName)
            {
                result = new RegexResult(false, "单位名称不能为空");
                showDialog(INFO_ERROR);
                et_com_name.requestFocus();
                return;
            }
            if (RegexCust.nosymbol(companyName) == false)
            {
                result = new RegexResult(false, "单位名称禁止输入符号等字符");
                showDialog(INFO_ERROR);
                et_com_name.requestFocus();
                return;
            }
            
            String st_com_zone_code = et_com_zone_code.getText().toString();
            if (!"".equals(st_com_zone_code) || null != st_com_zone_code)
            {
                if (!RegexCust.numberNatural(st_com_zone_code))
                {
                    result = new RegexResult(false, "单位电话区号格式不对!");
                    showDialog(INFO_ERROR);
                    et_com_zone_code.requestFocus();
                    return;
                }
                //单位电话区号
                if (!TextUtils.isEmpty(st_com_zone_code) && st_com_zone_code.length()<3)
                {
                    result = new RegexResult(false, "单位电话区号长度只能是3到4位!");
                    showDialog(INFO_ERROR);
                    et_com_zone_code.requestFocus();
                    return;
                }
            }
            String st_com_tel_code = et_com_tel_code.getText().toString();
            if (!"".equals(st_com_tel_code) || null != st_com_tel_code)
            {
                if (!RegexCust.phoneFix(st_com_tel_code))
                {
                    result = new RegexResult(false, "单位电话格式不对!");
                    showDialog(INFO_ERROR);
                    et_com_tel_code.requestFocus();
                    return;
                }
            }
            else
            {
                result = new RegexResult(false, "单位电话号码不能为空!");
                showDialog(INFO_ERROR);
                et_com_tel_code.requestFocus();
                return;
            }
            String st_com_extension_code = et_com_extension_code.getText().toString();
            if (!"".equals(st_com_extension_code) && null != st_com_extension_code)
            {
                if (!RegexCust.numberNatural(st_com_extension_code))
                {
                    result = new RegexResult(false, "单位电话分机号格式不对!");
                    showDialog(INFO_ERROR);
                    et_com_extension_code.requestFocus();
                    return;
                }
                if (!TextUtils.isEmpty(st_com_extension_code) && st_com_extension_code.length()<3)
                {
                    result = new RegexResult(false, "单位电话分机号长度只能是3到4位!");
                    showDialog(INFO_ERROR);
                    et_com_extension_code.requestFocus();
                    return;
                }
            }
            String st_contactName1 = et_contactName1.getText().toString();
            RegexResult temp_uname = null;
            temp_uname = RegexCust.length("联系人1姓名", st_contactName1, 2, 8);
            if (temp_uname.match)
            {
                temp_uname = RegexCust.chinese("联系人1姓名", st_contactName1);
                if (!temp_uname.match)
                {
                    result = new RegexResult(false, "联系人1姓名应为汉字!");
                    showDialog(INFO_ERROR);
                    return;
                }
            }
            else
            {
                if (st_contactName1.length() == 0)
                {
                    result = new RegexResult(false, "联系人1姓名不能为空!");
                }
                else
                {
                    result = new RegexResult(false, "联系人1姓名字符长度不对!");
                }
                showDialog(INFO_ERROR);
                return;
            }
            // 直系亲属联系人与申请人关系
            if (str_contactRelation1 == null || "请选择".equals(str_contactRelation1))
            {
                result = new RegexResult(false, "请选择联系人1与申请人关系!");
                showDialog(INFO_ERROR);
                return;
            }
            String st_mobileNo1 = et_mobileNo1.getText().toString();
            // 直系亲属手机号码
            temp = RegexCust.required("手机号码", st_mobileNo1);
            if (temp.match)
            {
                if (RegexCust.phoneMob(st_mobileNo1) == false)
                {
                    result = new RegexResult(false, "联系人1手机号码格式不对!");
                    showDialog(INFO_ERROR);
                    et_mobileNo1.requestFocus();
                    return;
                }
            }
            else
            {
                result = temp;
                showDialog(INFO_ERROR);
                et_mobileNo1.requestFocus();
                return;
            }
            
            String st_contactName2 = et_contactName2.getText().toString();
            RegexResult temp_uname2 = null;
            temp_uname2 = RegexCust.length("联系人2姓名", st_contactName2, 2, 8);
            if (temp_uname2.match)
            {
                temp_uname2 = RegexCust.chinese("联系人2姓名", st_contactName2);
                if (!temp_uname2.match)
                {
                    result = new RegexResult(false, "联系人2姓名应为汉字!");
                    showDialog(INFO_ERROR);
                    return;
                }
            }
            else
            {
                if (st_contactName2.length() == 0)
                {
                    result = new RegexResult(false, "联系人2姓名不能为空!");
                }
                else
                {
                    result = new RegexResult(false, "联系人2姓名字符长度不对!");
                }
                showDialog(INFO_ERROR);
                return;
            }
            if (str_contactRelation2 == null || "请选择".equals(str_contactRelation2))
            {
                result = new RegexResult(false, "请选择联系人2与申请人关系!");
                showDialog(INFO_ERROR);
                return;
            }
            
            if ("配偶".equals(str_contactRelation1) && "配偶".equals(str_contactRelation2))
            {
                result = new RegexResult(false, "配偶只能选择1个!");
                showDialog(INFO_ERROR);
                return;
            }
            
            String st_mobileNo2 = et_mobileNo2.getText().toString();
            temp = RegexCust.required("手机号码", st_mobileNo2);
            if (temp.match)
            {
                if (RegexCust.phoneMob(st_mobileNo2) == false)
                {
                    result = new RegexResult(false, "联系人2手机号码格式不对!");
                    showDialog(INFO_ERROR);
                    et_mobileNo2.requestFocus();
                    return;
                }
            }
            else
            {
                result = temp;
                showDialog(INFO_ERROR);
                et_mobileNo2.requestFocus();
                return;
            }
            if (st_contactName1.equals(st_contactName2))
            {
                result = new RegexResult(false, "联系人1姓名与联系人2姓名不能相同!");
                showDialog(INFO_ERROR);
                return;
            }
            if (st_mobileNo1.equals(st_mobileNo2))
            {
                result = new RegexResult(false, "联系人1手机号码与联系人2手机号码不能相同!");
                showDialog(INFO_ERROR);
                return;
            }
            String st_account_number = et_account_number.getText().toString();
            if ("北京银行".equals(bankName))
            {
                if (st_account_number.length() != 19)
                {
                    result = new RegexResult(false, "放/还款账号格式错误!");
                    showDialog(INFO_ERROR);
                    et_account_number.requestFocus();
                    return;
                }
            }
            else
            {
                if (st_account_number.length() != 23)
                {
                    result = new RegexResult(false, "放/还款账号格式错误!");
                    showDialog(INFO_ERROR);
                    et_account_number.requestFocus();
                    return;
                }
            }
            
            if (!mCurrentProviceName.equals("北京") && !mCurrentProviceName.equals("上海")
                && !mCurrentCityName.equals("广州") && !mCurrentCityName.equals("深圳"))
            {
                if (money.equals("1000"))
                {
                    result = new RegexResult(false, "只有北京、上海、广州、深圳才能贷款1000元");
                    showDialog(INFO_ERROR);
                    return;
                }
            }
            
            if (!address.contains("北京") && !address.contains("上海") && !address.contains("广州")
                && !address.contains("深圳"))
            {
                if (money.equals("1000"))
                {
                    result = new RegexResult(false, "只有北京、上海、广州、深圳才能贷款1000元");
                    showDialog(INFO_ERROR);
                    return;
                }
            }
            
            rapidlyLoanInfo.setAddress(address);
            rapidlyLoanInfo.setAddress_more(addressMore);
            rapidlyLoanInfo.setCompanyName(companyName);
            rapidlyLoanInfo.setCompany_phone_zone_code(st_com_zone_code);
            rapidlyLoanInfo.setCompany_phone_tel_code(st_com_tel_code);
            rapidlyLoanInfo.setCompany_phone_extension_code(st_com_extension_code);
            rapidlyLoanInfo.setContactName1(st_contactName1);
            rapidlyLoanInfo.setContactRelation1(str_contactRelation1);
            for (int i = 0; i < apply_relationship.length; i++)
            {
                if (apply_relationship[i].equals(str_contactRelation1))
                {
                    rapidlyLoanInfo.setContactRelationCode1(apply_relationshipCode[i]);
                }
            }
            rapidlyLoanInfo.setContactRelation2(str_contactRelation2);
            for (int i = 0; i < apply_relationship2.length; i++)
            {
                if (apply_relationship2[i].equals(str_contactRelation2))
                {
                    rapidlyLoanInfo.setContactRelationCode2(apply_relationshipCode2[i]);
                }
            }
            
            if (st_mobileNo1.equals(phone))
            {
                
                Toast.makeText(getApplicationContext(), "联系人手机号不能与贷款人手机号相同", Toast.LENGTH_LONG).show();
                return;
            }
            else if (st_mobileNo2.equals(phone))
            {
                
                Toast.makeText(getApplicationContext(), "联系人手机号不能与贷款人手机号相同", Toast.LENGTH_LONG).show();
                return;
            }
            else if (Contents.response.getResult().getCipNamecn().equals(st_contactName1))
            {
                
                Toast.makeText(getApplicationContext(), "联系人姓名不能与注册人相同", Toast.LENGTH_LONG).show();
                return;
            }
            else if (Contents.response.getResult().getCipNamecn().equals(st_contactName2))
            {
                
                Toast.makeText(getApplicationContext(), "联系人姓名不能与注册人相同", Toast.LENGTH_LONG).show();
                return;
            }
            
            rapidlyLoanInfo.setMobileNo1(st_mobileNo1);
            rapidlyLoanInfo.setContactName2(st_contactName2);
            rapidlyLoanInfo.setContactRelation2(str_contactRelation2);
            rapidlyLoanInfo.setMobileNo2(st_mobileNo2);
            rapidlyLoanInfo.setMoney(money);
            rapidlyLoanInfo.setBankName(bankName);
            rapidlyLoanInfo.setCardNo(st_account_number);
            rapidlyLoanInfo.setPoundage(et_loan_poundage.getText().toString());
            // rapidlyLoanInfo.setRepayment_way("");
            // wz8.3
            
            RapidlyLoanInfoContents.setRapidlyLoanInfo(rapidlyLoanInfo);
            shareclear();
            shareinit();
            // 测试---生产
            if (ContantsAddress.isProductionEnvironment)
            {
                if (StringUtils.isNotBlank(Contents.province))
                {
                    if (!Contents.city.contains("北京") && !Contents.city.contains("上海") && !Contents.city.contains("广州")
                        && !Contents.city.contains("深圳"))
                    {
                        if (money.equals("1000"))
                        {
                            result = new RegexResult(false, "只有北京、上海、广州、深圳才能贷款1000元");
                            showDialog(INFO_ERROR);
                            return;
                        }
                        else
                        {
                            // Intent intent = new Intent();
                            // intent.setClass(this, RapidlyLoanInfo1Activity.class);
                            // startActivity(intent);
                            // finish();
                        }
                    }
                    else
                    {
                        // Intent intent = new Intent();
                        // intent.setClass(this, RapidlyLoanInfo1Activity.class);
                        // startActivity(intent);
                        // finish();
                    }
                }
                else
                {
                    dialog =
                        DialogUtil.showDialogTwoButton(RapidlyLoanInfoActivity.this,
                            "定位信息获取失败，请确认是否被其他安全软件拦截，点击确定按钮后重新获取！",
                            new OnClickListener()
                            {
                                
                                @Override
                                public void onClick(View v)
                                {
                                    // TODO Auto-generated method stub
                                    dialog.dismiss();
                                }
                            },
                            new OnClickListener()
                            {
                                
                                @Override
                                public void onClick(View v)
                                {
                                    // TODO Auto-generated method stub
                                    dialog.dismiss();
                                    isFirstLoc = true;
                                    locationAgain();
                                }
                            });
                }
                
            }
            else
            { 
                Intent intent = new Intent();
                intent.setClass(this, RapidlyLoanInfo1Activity.class);
                LogUtil.i("RapidlyLoanInfoActivity","启动 RapidlyLoanInfo1Activity" );
                //跳转到极速贷影象资料采集页
                startActivity(intent);
                finish();
            }
            Contents.poetry_str = null;
            new GetPoetryAsyncTask().execute(Contents.applCde);
            
        }
        else if (v == et_com_address)
        {
            city_layout.setVisibility(View.VISIBLE);
        }
        else if (v == choose_button)
        {
            city_layout.setVisibility(View.GONE);
            et_com_address.setText(mCurrentProviceName + mCurrentCityName + mCurrentAreaName);
        }
        else if (v == bt_addRe1)
        {
            // 添加联系人1
            startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), 1);
        }
        else if (v == bt_addRe2)
        {
            // 添加联系人2
            startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), 2);
        }
    }
    
    // 添加获取验证诗句的联网操作。GetPoetryAsyncTask
    class GetPoetryAsyncTask extends AsyncTask<String, Object, Object>
    {
        
        @Override
        protected Object doInBackground(String... params)
        {
            // TODO 自动生成的方法存根
            HttpHelper mBobcfcHttpClient = HttpHelper.getInstance(RapidlyLoanInfoActivity.this);
            String url = ContantsAddress.GETPOETRY;
            List<NameValuePair> param = new ArrayList<NameValuePair>();
            // 请求参数
            param.add(new BasicNameValuePair("applyCde", params[0]));
            Class<PoetryResult> clazz = PoetryResult.class;
            PoetryResult response = mBobcfcHttpClient.post(url, param, clazz);
            return response;
            
        }
        
        @Override
        protected void onPostExecute(Object result)
        {
            // TODO 自动生成的方法存根
            super.onPostExecute(result);
            dismissProgressDialog();
            PoetryResult response = (PoetryResult)result;
            if (response != null)
            {
                if (0 == response.getCode())
                {
                    response.getResult().getContent();
                    Contents.poetry_str = response.getResult().getContent();
                    System.out.println("诗句信息：" + Contents.poetry_str);
                    Intent it = new Intent(RapidlyLoanInfoActivity.this, RapidlyLoanInfo1Activity.class);
                    startActivity(it);
                    LogUtil.i("RapidlyLoanInfoActivity",response.getMsg()+"启动新RapidlyLoanInfo1Activity" );
                    // finish();
                }
                else
                {
                    System.out.println(response.getMsg());
                    LogUtil.i("RapidlyLoanInfoActivity",response.getMsg()+"失败" );
                    Toast.makeText(getApplicationContext(), response.getMsg(), Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.no_network),
                    Toast.LENGTH_LONG).show();
            }
        }
        
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            showProgressDialog();
        }
        
    }
    
    /**
     * 解析整个Json对象，完成后释放Json对象的内存
     */
    private void initDatas()
    {
        try
        {
            JSONArray jsonArray = mJsonObj.getJSONArray("citylist");
            mProvinceDatas = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonP = jsonArray.getJSONObject(i);// 每个省的json对象
                String province = jsonP.getString("p");// 省名字
                
                mProvinceDatas[i] = province;
                
                JSONArray jsonCs = null;
                try
                {
                    /**
                     * Throws JSONException if the mapping doesn't exist or is
                     * not a JSONArray.
                     */
                    jsonCs = jsonP.getJSONArray("c");
                }
                catch (Exception e1)
                {
                    continue;
                }
                String[] mCitiesDatas = new String[jsonCs.length()];
                for (int j = 0; j < jsonCs.length(); j++)
                {
                    JSONObject jsonCity = jsonCs.getJSONObject(j);
                    String city = jsonCity.getString("n");// 市名字
                    mCitiesDatas[j] = city;
                    JSONArray jsonAreas = null;
                    try
                    {
                        /**
                         * Throws JSONException if the mapping doesn't exist or
                         * is not a JSONArray.
                         */
                        jsonAreas = jsonCity.getJSONArray("a");
                    }
                    catch (Exception e)
                    {
                        continue;
                    }
                    
                    String[] mAreasDatas = new String[jsonAreas.length()];// 当前市的所有区
                    for (int k = 0; k < jsonAreas.length(); k++)
                    {
                        String area = jsonAreas.getJSONObject(k).getString("s");// 区域的名称
                        mAreasDatas[k] = area;
                    }
                    mAreaDatasMap.put(city, mAreasDatas);
                }
                mCitisDatasMap.put(province, mCitiesDatas);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        mJsonObj = null;
    }
    
    /**
     * 从assert文件夹中读取省市区的json文件，然后转化为json对象
     */
    private void initJsonData()
    {
        try
        {
            StringBuffer sb = new StringBuffer();
            InputStream is = getAssets().open("city.json");
            int len = -1;
            byte[] buf = new byte[1024 * 2];
            while ((len = is.read(buf)) != -1)
            {
                sb.append(new String(buf, 0, len, "GB2312"));
            }
            is.close();
            mJsonObj = new JSONObject(sb.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * change事件的处理
     */
    // wz10.11
    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue)
        throws NullPointerException
    {
        if (wheel == mProvince)
        {
            updateCities();
        }
        else if (wheel == mCity)
        {
            updateAreas();
        }
        else if (wheel == mArea)
        {
            mCurrentAreaName = "";
            mCurrentAreaName = mAreaDatasMap.get(mCurrentCityName)[newValue];
        }
    }
    
    public void showChoose(View view)
    {
        Toast.makeText(this, mCurrentProviceName + mCurrentCityName + mCurrentAreaName, Toast.LENGTH_LONG).show();
    }
    
    @Override
    protected Dialog onCreateDialog(int id, Bundle args)
    {
        Dialog dialog = null;
        StringBuffer sb = new StringBuffer();
        switch (id)
        {
            case INFO_ERROR:
                sb.append(result.msg);
                dialog = DialogUtil.showDialogOneButton2(RapidlyLoanInfoActivity.this, sb.toString());
                break;
            default:
                break;
        }
        return super.onCreateDialog(id, args);
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            // 这里重写返回键
            Intent it = new Intent(RapidlyLoanInfoActivity.this, HomeActivity.class);
            startActivity(it);
            return true;
        }
        return false;
        
    }
    
    // 获取流水号
    class GetSeqAsyncTask extends AsyncTask<String, Object, Object>
    {
        
        @Override
        protected Object doInBackground(String... params)
        {
            HttpHelper httpHelper = HttpHelper.getInstance(RapidlyLoanInfoActivity.this);
            List<NameValuePair> arg = new ArrayList<NameValuePair>();
            return httpHelper.post(ContantsAddress.GET_SEQ, arg, MiniConfirmResponse.class);
        }
        
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            showProgressDialog();
        }
        
        @Override
        protected void onPostExecute(Object result)
        {
            super.onPostExecute(result);
            dismissProgressDialog();
            MiniConfirmResponse response = (MiniConfirmResponse)result;
            if (response != null)
            {
                if (0 == response.getCode())
                {
                    MiniConfirmMessage message = response.getResult();
                    Contents.applCde = message.getApplCde();// 申请编号
                    Contents.applSeq = message.getApplSeq();// 申请流水号
                    Contents.applDisbSeq = message.getApplDisbSeq();
                    Contents.applRpymSeq = message.getApplRpymSeq();
                    Contents.apptSeq = message.getApptSeq();
                    System.out.println("流水号相关信息=》" + Contents.applCde + ",---" + Contents.applSeq + ",--"
                        + Contents.applDisbSeq + ",==" + Contents.applRpymSeq + ",==" + Contents.apptSeq + ".");
                    
                }
                else
                {
                    System.out.println(response.getMsg());
                    Toast.makeText(getApplicationContext(), response.getMsg(), Toast.LENGTH_LONG).show();
                    // next_button.setClickable(true);
                }
            }
            else
            {
                Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.no_network),
                    Toast.LENGTH_LONG).show();
                // next_button.setClickable(true);
                try
                {
                    String s = null;
                    System.out.println(s);
                }
                catch (NullPointerException e)
                {
                    // TODO: handle exception
                    System.out.println(e);
                }
            }
            
        }
        
        @Override
        protected void onCancelled(Object result)
        {
            // TODO Auto-generated method stub
            super.onCancelled(result);
            System.out.println("执行结束1");
        }
        
        @Override
        protected void onCancelled()
        {
            // TODO Auto-generated method stub
            super.onCancelled();
            System.out.println("执行结束");
        }
        
    }
    
    // 添加联系人 回调intent
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
        {
            try
            {
                ContentResolver reContentResolverol = getContentResolver();
                Uri contactData = data.getData();
                @SuppressWarnings("deprecation")
                Cursor cursor = managedQuery(contactData, null, null, null, null);
                cursor.moveToFirst();
                username = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                Cursor phone =
                    reContentResolverol.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                        null,
                        null);
                while (phone.moveToNext())
                {
                    usernumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    // usernumber.replaceAll("\\s*", "");
                    // username.replace(" ", "");
                    if (usernumber != null)
                    {
                        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                        Matcher m = p.matcher(usernumber);
                        usernumber = m.replaceAll("");
                    }
                    if (username != null)
                    {
                        Pattern p2 = Pattern.compile("\\s*|\t|\r|\n");
                        Matcher m2 = p2.matcher(username);
                        username = m2.replaceAll("");
                    }
                    switch (requestCode)
                    {
                        case 1:
                            et_mobileNo1.setText(usernumber);
                            // et_contactName1.setText(username);
                            break;
                        case 2:
                            et_mobileNo2.setText(usernumber);
                            // et_contactName2.setText(username);
                            break;
                        case 3:
                            break;
                    }
                }
            }
            catch (Exception e)
            {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
                return;
            }
            
        }
    }
    
    // 获取是否可以------
    class IsChoiseCodeKeyAsyncTask extends AsyncTask<String, Object, Object>
    {
        @Override
        protected Object doInBackground(String... params)
        {
            HttpHelper httpHelper = HttpHelper.getInstance(RapidlyLoanInfoActivity.this);
            List<NameValuePair> arg = new ArrayList<NameValuePair>();
            arg.add(new BasicNameValuePair("paramName", params[0]));
            return httpHelper.post(ContantsAddress.SEND_KEY_MSG, arg, MsgKeyCodeResponse.class);
        }
        
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }
        
        @Override
        protected void onPostExecute(Object result)
        {
            super.onPostExecute(result);
            KeyResponse(result);
        }
    }
    
    private void KeyResponse(Object result)
    {
        MsgKeyCodeResponse response = (MsgKeyCodeResponse)result;
        if (response == null)
        {
        }
        else
        {
            if (response.getCode() == 0)
            {
                String strkey = "";
                strkey = response.getResult().getSysConfigValue().toString();
                if (strkey.equals("true"))
                {
                    Contents.isChoiceMsgKey = true;
                }
                else if (strkey.equals("false"))
                {
                    Contents.isChoiceMsgKey = false;
                }
            }
        }
    }
    
    // 获取的手机号是11位则传11位 否则传“”；
    private String initPhone()
    {
        String str_numinfo = "";
        str_numinfo = getPhoneNumber();
        if (str_numinfo != null || "".equals(str_numinfo))
        {
            str_numinfo.trim();
            int i = str_numinfo.length();
            if (i == 11)
            {
                return str_numinfo;
                // loanInfo.setPhone(str_numinfo);
                // RapidlyLoanInfoContents.rapidlyLoanInfo.setPhone(str_numinfo);
            }
            else
            {
                return "";
            }
        }
        return "";
        
    }
    
    // 获取本机手机号
    private String getPhoneNumber()
    {
        String stnuml;
        TelephonyManager mTelephonyMgr;
        mTelephonyMgr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        stnuml = mTelephonyMgr.getLine1Number() + "";
        return stnuml;
        
    }
}
