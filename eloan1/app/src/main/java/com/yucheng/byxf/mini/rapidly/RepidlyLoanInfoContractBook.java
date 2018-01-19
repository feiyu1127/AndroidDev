package com.yucheng.byxf.mini.rapidly;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.message.ResponseforProving;
import com.yucheng.byxf.mini.ui.BaseActivity;
import com.yucheng.byxf.mini.ui.HomeActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.ContentsMoney;
import com.yucheng.byxf.mini.utils.DialogUtil;

/**
 * 类名: RepidlyLoanInfoContractBook</br>
 * 描述:极速贷贷款条款 页</br>
 * 开发人员： weiyb</br>
 * 创建时间： 2015-7-20
 */

public class RepidlyLoanInfoContractBook extends BaseActivity
{
    private static final String _Tag = "RiskPromptLoanActivity";
    
    private static final Integer RET_CODE = 0;
    
    private WebView webview;
    
    private Button disagree_button, agreeButton;
    
    private TextView repidy_loan_head, repidy_loan_title;
    
    Dialog dialog;
    
    /**
     * 年龄
     */
    private Integer age = null;
    
    private String st_sex;
    
    private LinearLayout include;
    
    private Button back;
    
    private TextView easy_loan_head;
    
    private RepidlyLoanInfoContractBook activity;
    
    private String dahuiType = "";
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        activity = new RepidlyLoanInfoContractBook();
        init();
        try
        {
            age_count();
        }
        catch (Exception e)
        {
            
            e.printStackTrace();
        }
    }
    
    private void init()
    {
        setContentView(R.layout.repidly_loan__one_contractbook);
        
        Intent it = getIntent();
        dahuiType = it.getStringExtra("choiceType");
        
        repidy_loan_head = (TextView)findViewById(R.id.easy_loan_head);
        agreeButton = (Button)this.findViewById(R.id.easy_loan_btn_agree_button);
        disagree_button = (Button)findViewById(R.id.easy_loan_btn_disagree_button);
        repidy_loan_head.setText("贷款条款");
        agreeButton.setOnClickListener(new OnClickListener_agreeButton());
        disagree_button.setOnClickListener(new OnClickListener_disagree_button());
        
        include = (LinearLayout)findViewById(R.id.easy_loan_title);
        back = (Button)include.findViewById(R.id.bt2_back_common);
        back.setOnClickListener(new OnClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                // 回退
                Intent it = new Intent(RepidlyLoanInfoContractBook.this, HomeActivity.class);
                startActivity(it);
            }
        });
        initWebView();
    }
    
    @Override
    protected void onDestroy()
    {
        
        super.onDestroy();
        activity = null;
    }
    
    public void age_count()
        throws Exception
    {
        if (Contents.response == null || Contents.response.getResult() == null
            || Contents.response.getResult().getIdNo() == null)
            return;
        String s = Contents.response.getResult().getIdNo();
        System.out.println("身份证号：" + s);
        int leh = s.length();
        String dates = "";
        if (leh != 18 && leh != 15)
        {
            System.out.println("NO");
        }
        else
        {
            if (leh == 18)
            {
                // //18位身份证号
                Calendar c = Calendar.getInstance();
                Date d = c.getTime();
                String s1 = d.toString();
                String s2 = (String)s1.subSequence(s1.length() - 4, s1.length());
                String ss = s.substring(6, s.length() - 8);
                RapidlyLoanInfoContents.apptStartDate = s.substring(6, 14);
                System.out.println("=============>" + RapidlyLoanInfoContents.apptStartDate);
                Integer int1 = Integer.parseInt(ss);
                Integer int2 = Integer.parseInt(s2);
                age = int2 - int1;
                System.out.println("age : " + age);
                RapidlyLoanInfoContents.age = age;
                
                int sex = Integer.valueOf(s.substring(16, 17));
                st_sex = (sex + 2) % 2 == 0 ? "女" : "男";
                RapidlyLoanInfoContents.sex = st_sex;
                System.out.println(RapidlyLoanInfoContents.sex);
            }
            else
            {
                // 15位身份证号
                Calendar c = Calendar.getInstance();
                Date d = c.getTime();
                String s1 = d.toString();
                String s2 = (String)s1.subSequence(s1.length() - 4, s1.length());
                String ss = "19" + s.substring(6, 8);
                
                RapidlyLoanInfoContents.apptStartDate = "19" + s.substring(6, 12);
                
                Integer int1 = Integer.parseInt(ss);
                Integer int2 = Integer.parseInt(s2);
                age = int2 - int1;
                System.out.println("age : " + age);
                RapidlyLoanInfoContents.age = age;
                int sex = Integer.valueOf(s.substring(14, 15));
                st_sex = (sex + 2) % 2 == 0 ? "女" : "男";
                RapidlyLoanInfoContents.sex = st_sex;
            }
        }
        
    }
    
    // agreeButton 同意按钮监听
    private class OnClickListener_agreeButton implements OnClickListener
    {
        @Override
        public void onClick(View arg0)
        {
            if ((age < 50 || age == 50) && (age > 20 || age == 20))
            {
                Intent it = new Intent(RepidlyLoanInfoContractBook.this, RapidlyLoanInfoActivity.class);
                // 跳转到极速贷申请信息页
                startActivity(it);
            }
            else
            {
                dialog =
                    DialogUtil.showDialogOneButton(RepidlyLoanInfoContractBook.this,
                        "年龄不符合20-50周岁",
                        new OnClickListener()
                        {
                            
                            @Override
                            public void onClick(View v)
                            {
                                
                                finish();
                            }
                        });
            }
        }
    }
    
    private class OnClickListener_disagree_button implements OnClickListener
    {
        @Override
        public void onClick(View arg0)
        {
            Intent it1 = new Intent(RepidlyLoanInfoContractBook.this, HomeActivity.class);
            startActivity(it1);
            finish();
        }
    }
    
    private void initWebView()
    {
        // String url = "file:///android_asset/html/rapidlyloan.html";
        // 后台控制url
        String url = ContantsAddress.QUICKBOOK;
        webview = (WebView)findViewById(R.id.risk_prompt_loan_webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDefaultTextEncodingName("GBK");
        webview.loadUrl(url);
        // 2次验证 测试打回
        // 轻松贷 30306 允许 向下执行
        // 急速贷 7070301 允许向下执行
        String idNo = "";
        if (Contents.response != null && Contents.response.getResult() != null)
            idNo = Contents.response.getResult().getIdNo();
        if (Contents.isChoice)
        {
            // 打回
            
            new ValidatorAsyncTask().execute(idNo, getProInfo(), "02");
        }
        else
        {
            // 非打回
            new ValidatorAsyncTask().execute(idNo, getProInfo(), "01");
        }
        
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent it1 = new Intent(RepidlyLoanInfoContractBook.this, HomeActivity.class);
            startActivity(it1);
            finish();
        }
        return true;
    }
    
    class ValidatorAsyncTask extends AsyncTask<String, Object, Object>
    {
        @Override
        protected Object doInBackground(String... params)
        {
            HttpHelper httpHelper = HttpHelper.getInstance(RepidlyLoanInfoContractBook.this);
            List<NameValuePair> arg = new ArrayList<NameValuePair>();
            arg.add(new BasicNameValuePair("idNo", params[0]));
            arg.add(new BasicNameValuePair("deviceId", params[1]));
            arg.add(new BasicNameValuePair("txType", params[2]));
            return httpHelper.post(ContantsAddress.APPLY_QUICK_LOAN_VALIDATOR, arg, ResponseforProving.class);
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
            if (activity != null)
            {
                dismissProgressDialog();
                ResponseforProving response = (ResponseforProving)result;
                if (response != null)
                {
                    System.out.println("dahuiType===>" + dahuiType);
                    // if (response.getCode() ==0 ||((response.getCode()== 7070301)&&(dahuiType.equals("choiceType"))))
                    // {
                    // response.getResult().get();
                    if (response.getCode() == 0)
                    {
                        ContentsMoney.MapMoney = response.getResult();
                    }
                    else if (response.getCode() == 7070301 && dahuiType.equals("choiceType"))
                    {
                        ContentsMoney.MapMoney = response.getResult();
                    }
                    else
                    {
                        dialog =
                            DialogUtil.showDialogOneButton(RepidlyLoanInfoContractBook.this,
                                response.getMsg(),
                                new OnClickListener()
                                {
                                    
                                    @Override
                                    public void onClick(View v)
                                    {
                                        Intent it1 = new Intent(RepidlyLoanInfoContractBook.this, HomeActivity.class);
                                        startActivity(it1);
                                        finish();
                                    }
                                });
                    }
                }
                else
                {
                    dialog =
                        DialogUtil.showDialogOneButton(RepidlyLoanInfoContractBook.this,
                            getResources().getString(R.string.no_network),
                            new OnClickListener()
                            {
                                
                                @Override
                                public void onClick(View v)
                                {
                                    Intent it1 = new Intent(RepidlyLoanInfoContractBook.this, HomeActivity.class);
                                    startActivity(it1);
                                    finish();
                                }
                            });
                }
            }
            
        }
    }
    
    private String getProInfo()
    {
        TelephonyManager telephonyManager = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
        PackageManager packageManager = getPackageManager();
        PackageInfo packInfo;
        return telephonyManager.getDeviceId();
    }
    
}
