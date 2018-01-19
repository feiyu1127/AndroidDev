package com.yucheng.byxf.mini.xiaojinying;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.yucheng.byxf.mini.ui.BaseActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.NameDialogFragment;
import com.yucheng.byxf.mini.utils.RegexUtils2;
import com.yucheng.byxf.mini.utils.RegexUtils2.Result;

public class XiaoJinYingQuery2Activity extends BaseActivity
{
    private TextView et_startdate;
    
    private TextView et_enddate;
    
    private Button btn_chaxun;
    
    private Result result;
    
  //  private final int INFO_ERROR = 0;
    
   // private int time;
    
   // private static final Integer RET_CODE = 0;
    
   // private String loginErrorMessage;
    
    private String typequery = "1";
    
   // private boolean isFlag = true;
    
    private ImageView back;
    
    /**
     * 查询页面2 明细查询
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xiaojinyin_mingxichaxun_activity);
        et_startdate = (TextView)findViewById(R.id.et_startdate);
        et_enddate = (TextView)findViewById(R.id.et_enddate);
        btn_chaxun = (Button)findViewById(R.id.btn_chaxun);
        // 起始日期
        back = (ImageView)findViewById(R.id.back);
        back.setOnClickListener(new OnClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                finish();
                
            }
        });
        
        
        
        et_startdate.setOnClickListener(new OnClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                // 显示日期选择器
                showDatePickerFragemnt();
            }
        });
        // 结束日期
        et_enddate.setOnClickListener(new OnClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                // 选择日期选择器
                showDatePickerFragemnt2();
            }
        });
        // 查询
        btn_chaxun.setOnClickListener(new OnClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                
                Result temp = null;
                String moneydate = et_startdate.getText().toString().trim();
                temp = RegexUtils2.required("起始日期", moneydate);
                if (temp.match == false)
                {
                    result = temp;
                    showEditDialog(v, result.msg);
                    return;
                }
                String moneydate1 = et_enddate.getText().toString().trim();
                temp = RegexUtils2.required("结束日期", moneydate1);
                if (temp.match == false)
                {
                    result = temp;
                    showEditDialog(v, result.msg);
                    return;
                }
                int msg = compare_date(moneydate, moneydate1);
                if (msg == 1)
                {
                    showEditDialog(v, "起始日期不得大于结束日期！");
                    return;
                }
                else if (msg == 3)
                {
                    showEditDialog(v, "起始日期不得大于当前日期！");
                    return;
                }
              /*  else if (msg == 4)
                {
                    showEditDialog(v, "起始日期不得等于结束日期！");
                    return;
                }*/
                Intent intent = new Intent();
                // 跳转到帐单展示页面2
                intent.setClass(XiaoJinYingQuery2Activity.this, XiaoJinYingZhangDanActivity2.class);
                intent.putExtra("kaishiriqi", moneydate);
                intent.putExtra("jieshuriqi", moneydate1);
                intent.putExtra("zhuangtai", typequery);
                startActivity(intent);
                
            }
        });
        
    }
    
    private void showDatePickerFragemnt()
    {
        DialogFragment fragment = new DatePickerFragment();
        fragment.show(getFragmentManager(), "datePicker");
        
    }
    
    public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener
    {
        
        int _year = 1970;
        
        int _month = 0;
        
        int _day = 0;
        
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState)
        {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            
            Date datenow = new Date();
            
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            // 当前时间
            String timenow = format.format(datenow);
            
            String[] array = timenow.split("-");
            
            year = Integer.parseInt(array[0]);
            month = Integer.parseInt(array[1]) - 1;
            day = Integer.parseInt(array[2]);
            
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }
        
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
        {
            // 日期选择完成事件
            _year = year;
            _month = monthOfYear + 1;
            _day = dayOfMonth;
            
            et_startdate.setText(getValue());
        }
        
        private String getValue()
        {
            
            String month;
            String day;
            
            if (_month < 10)
            {
                month = "0" + String.valueOf(_month);
            }
            else
            {
                month = String.valueOf(_month);
            }
            if (_day < 10)
            {
                day = "0" + String.valueOf(_day);
            }
            else
            {
                day = String.valueOf(_day);
            }
            
            return "" + _year + "-" + month + "-" + day;
            
            // return "" + _year + "-" + _month + "-" + _day;
        }
        
    }
    
    private void showDatePickerFragemnt2()
    {
        DialogFragment fragment = new DatePickerFragment2();
        fragment.show(getFragmentManager(), "datePicker");
        
    }
    
    public class DatePickerFragment2 extends DialogFragment implements DatePickerDialog.OnDateSetListener
    {
        
        int _year = 1970;
        
        int _month = 0;
        
        int _day = 0;
        
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState)
        {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            
            Date datenow = new Date();
            
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            // 当前时间
            String timenow = format.format(datenow);
            
            String[] array = timenow.split("-");
            
            year = Integer.parseInt(array[0]);
            month = Integer.parseInt(array[1]) - 1;
            day = Integer.parseInt(array[2]);
            
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }
        
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
        {
            // 日期选择完成事件
            _year = year;
            _month = monthOfYear + 1;
            _day = dayOfMonth;
            
            et_enddate.setText(getValue());
        }
        
        private String getValue()
        {
            
            String month;
            String day;
            
            if (_month < 10)
            {
                month = "0" + String.valueOf(_month);
            }
            else
            {
                month = String.valueOf(_month);
            }
            if (_day < 10)
            {
                day = "0" + String.valueOf(_day);
            }
            else
            {
                day = String.valueOf(_day);
            }
            
            return "" + _year + "-" + month + "-" + day;
            // return "" + _year + "-" + _month + "-" + _day;
        }
        
    }
    
    public void showEditDialog(View view, String str)
    {
        DialogFragment myFragment = NameDialogFragment.newInstance(str);
        myFragment.show(getFragmentManager(), "EditNameDialog");
    }
    
    public static int compare_date(String DATE1, String DATE2)
    {
        
        Date datenow = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String timenow = format.format(datenow);
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            Date dtnow = df.parse(timenow);
            if (dtnow.getTime() < dt1.getTime())
            {
                return 3;
            }
            
            if (dt2.getTime()<dt1.getTime())
            {
                return 3;
            }
            
            if (dtnow.getTime() > dt2.getTime())
            {
                return 5;
            }
            
            if (dt1.getTime() > dt2.getTime())
            {
                System.out.println("dt1大");
                return 1;
                // 开始日期大
            }
            else if (dt1.getTime() == dt2.getTime())
            {
                return 4;
            }
            // if((dt1.getTime()-dt2.getTime())>)
            
            else if (dt1.getTime() < dt2.getTime())
            {
                System.out.println("dt2大");
                // 结束日期大
                return -1;
            }
            else
            {
                return 0;
            }
            
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return 0;
    }
    
}
