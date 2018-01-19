package com.yucheng.byxf.mini.xiaojinying;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.message.XiaoChaXunResult;
import com.yucheng.byxf.mini.message.XiaoJinYingIsJihuoResponse;
import com.yucheng.byxf.mini.message.XiaoJinYingIsjihuoCardnumResponse;
import com.yucheng.byxf.mini.ui.BaseActivity;
import com.yucheng.byxf.mini.ui.HomeActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;

public class XiaoJinYingMenuActivity extends BaseActivity implements OnClickListener
{
    
    // private GridView gridview;
    private Button bt_weijihuo;
    
    private TextView text_weijihuo;
    
    private ImageView back_menu;
    
    // 激活状态 0激活 1待激活 2异常情况
    private int jihuoType = 0;
    
    private String loginErrorMessage;
    
    private Button chaxun;
    
    private Button qingkuan;
    
    private Button huankuan;
    
    private Button biangeng;
    
    private Button qita;
    
    private Button mima;
    
    private Button xiugai;
    
    private Button xiaohu;
    
    private String re;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        new XiaoMenuAsyncTask().execute(Contents.response.getResult().getIdNo() + "");
        setContentView(R.layout.xiaojinyin_menu_activity);
        chaxun = (Button)findViewById(R.id.chaxun);
        qingkuan = (Button)findViewById(R.id.qingkuan);
        huankuan = (Button)findViewById(R.id.huankuan);
        biangeng = (Button)findViewById(R.id.biangeng);
        qita = (Button)findViewById(R.id.qita);
        mima = (Button)findViewById(R.id.mima);
        xiugai = (Button)findViewById(R.id.xiugai);
        xiaohu = (Button)findViewById(R.id.xiaohu);
        chaxun.setOnClickListener(this);
        qingkuan.setOnClickListener(this);
        huankuan.setOnClickListener(this);
        biangeng.setOnClickListener(this);
        qita.setOnClickListener(this);
        mima.setOnClickListener(this);
        xiugai.setOnClickListener(this);
        xiaohu.setOnClickListener(this);
        bt_weijihuo = (Button)findViewById(R.id.button_weijihuo);
        back_menu = (ImageView)findViewById(R.id.back_menu2);
        text_weijihuo = (TextView)findViewById(R.id.text_weijihuo);
        // ItemImage = (ImageView) findViewById(R.id.ItemImage);
        back_menu.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClass(XiaoJinYingMenuActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });       
        chaxun.setOnTouchListener(new OnTouchListener()
        {
            
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (chaxun.isClickable())
                {
                    if (event.getAction() == MotionEvent.ACTION_DOWN)
                    {
                        // 更改为按下时的背景图片
                        v.setBackgroundResource(R.drawable.chaxun0);
                    }
                    else if (event.getAction() == MotionEvent.ACTION_UP)
                    {
                        // 改为抬起时的图片
                        v.setBackgroundResource(R.drawable.chaxunimg);
                    }
                }
                else
                {
                    v.setBackgroundResource(R.drawable.chaxun1);
                }
                return false;
            }
            
        });
        qingkuan.setOnTouchListener(new OnTouchListener()
        {
            
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (qingkuan.isClickable())
                {
                    if (event.getAction() == MotionEvent.ACTION_DOWN)
                    {
                        // 更改为按下时的背景图片
                        v.setBackgroundResource(R.drawable.qingkuan0);
                    }
                    else if (event.getAction() == MotionEvent.ACTION_UP)
                    {
                        // 改为抬起时的图片
                        v.setBackgroundResource(R.drawable.qingkuan);
                    }
                }
                else
                {
                    v.setBackgroundResource(R.drawable.qingkuan1);
                }
                return false;
            }
            
        });
        huankuan.setOnTouchListener(new OnTouchListener()
        {
            
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (huankuan.isClickable())
                {
                    if (event.getAction() == MotionEvent.ACTION_DOWN)
                    {
                        // 更改为按下时的背景图片
                        v.setBackgroundResource(R.drawable.huankuan0);
                    }
                    else if (event.getAction() == MotionEvent.ACTION_UP)
                    {
                        // 改为抬起时的图片
                        v.setBackgroundResource(R.drawable.huankuan);
                    }
                }
                else
                {
                    v.setBackgroundResource(R.drawable.huankuan1);
                }
                return false;
            }
            
        });
        biangeng.setOnTouchListener(new OnTouchListener()
        {
            
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (biangeng.isClickable())
                {
                    if (event.getAction() == MotionEvent.ACTION_DOWN)
                    {
                        // 更改为按下时的背景图片
                        v.setBackgroundResource(R.drawable.biangeng0);
                    }
                    else if (event.getAction() == MotionEvent.ACTION_UP)
                    {
                        // 改为抬起时的图片
                        v.setBackgroundResource(R.drawable.biangeng);
                    }
                }
                else
                {
                    v.setBackgroundResource(R.drawable.biangeng1);
                }
                return false;
            }
            
        });
        qita.setOnTouchListener(new OnTouchListener()
        {
            
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (qita.isClickable())
                {
                    if (event.getAction() == MotionEvent.ACTION_DOWN)
                    {
                        // 更改为按下时的背景图片
                        v.setBackgroundResource(R.drawable.qita0);
                    }
                    else if (event.getAction() == MotionEvent.ACTION_UP)
                    {
                        // 改为抬起时的图片
                        v.setBackgroundResource(R.drawable.qita);
                    }
                }
                else
                {
                    v.setBackgroundResource(R.drawable.qita1);
                }
                return false;
            }
            
        });
        mima.setOnTouchListener(new OnTouchListener()
        {
            
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    // 更改为按下时的背景图片
                    if (mima.isClickable())
                    {
                        // 改为抬起时的图片
                        v.setBackgroundResource(R.drawable.mima0);
                    }
                    else
                    {
                        v.setBackgroundResource(R.drawable.mima1);
                    }
                    
                }
                else if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    // 改为抬起时的图片
                    v.setBackgroundResource(R.drawable.mima);
                    
                }
                return false;
            }
            
        });
        xiugai.setOnTouchListener(new OnTouchListener()
        {
            
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    // 更改为按下时的背景图片
                    if (xiugai.isClickable())
                    {
                        // 改为抬起时的图片
                        v.setBackgroundResource(R.drawable.xiugai0);
                    }
                    else
                    {
                        v.setBackgroundResource(R.drawable.xiugai1);
                    }
                    
                }
                else if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    // 改为抬起时的图片
                    v.setBackgroundResource(R.drawable.xiugai);
                }
                return false;
            }
            
        });
        xiaohu.setOnTouchListener(new OnTouchListener()
        {
            
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (xiaohu.isClickable())
                {
                    if (event.getAction() == MotionEvent.ACTION_DOWN)
                    {
                        // 更改为按下时的背景图片
                        v.setBackgroundResource(R.drawable.xiaohu0);
                    }
                    else if (event.getAction() == MotionEvent.ACTION_UP)
                    {
                        // 改为抬起时的图片
                        v.setBackgroundResource(R.drawable.xiaohu);
                    }
                }
                else
                {
                    v.setBackgroundResource(R.drawable.xiaohu1);
                }
                return false;
            }
            
        });
        bt_weijihuo.setOnClickListener(this);
        text_weijihuo = (TextView)findViewById(R.id.text_weijihuo);
        // 生成动态数组，并且转入数据
        
        // 添加消息处理
        // 添加消息处理
    }
    
    @Override
    public void onClick(View v)
    {
        super.onClick(v);
        
        Intent it = new Intent();
        switch (v.getId())
        {
            case R.id.button_weijihuo:
                // 去激活页面
                it.setClass(XiaoJinYingMenuActivity.this, XiaoJinYingJIHuoActivity.class);
                startActivity(it);
                break;
            case R.id.chaxun:
                // 查询页面
                it.setClass(XiaoJinYingMenuActivity.this, XiaoJinYingQueryActivity.class);
                startActivity(it);
                break;
            case R.id.qingkuan:
                // 请款申请
                it.setClass(XiaoJinYingMenuActivity.this, XiaoJinYingQingKuanApplyActivity.class);
                startActivity(it);
                break;
            case R.id.huankuan:
                // 还款申请
                it.setClass(XiaoJinYingMenuActivity.this, XiaoJinYingHuanKuanApplyActivity.class);
                startActivity(it);
                break;
            case R.id.biangeng:
                // 变更绑定银行卡
                it.setClass(XiaoJinYingMenuActivity.this, XiaoJinYingChangeBankCardActivity.class);
                startActivity(it);
                break;
            case R.id.qita:
                // 变更其他信息
                it.setClass(XiaoJinYingMenuActivity.this, XiaoJinYingChangeOtherInfoActivity.class);
                startActivity(it);
                break;
            case R.id.mima:
                // 设置查询密码
                it.setClass(XiaoJinYingMenuActivity.this, XiaoJinYingSetQueryKeyActivity.class);
                startActivity(it);
                break;
            case R.id.xiugai:
                // 修改查询密码
                it.setClass(XiaoJinYingMenuActivity.this, XiaoJinYingAlterQueryKeyActivity.class);
                startActivity(it);
                break;
            case R.id.xiaohu:
                // 销户
                it.setClass(XiaoJinYingMenuActivity.this, XiaoJinYingCancelAccountActivity.class);
                startActivity(it);
                break;
            default:
                break;
        }
    }
    
    class XiaoMenuAsyncTask extends AsyncTask<String, Object, Object>
    {
        @Override
        protected Object doInBackground(String... params)
        {
            HttpHelper httpHelper = HttpHelper.getInstance(XiaoJinYingMenuActivity.this);
            List<NameValuePair> arg = new ArrayList<NameValuePair>();
            arg.add(new BasicNameValuePair("idNo", params[0]));
            
            return httpHelper.post(ContantsAddress.Xianjinyingshifuojihuo, arg, XiaoJinYingIsjihuoCardnumResponse.class);
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
            registerResponse(result);
        }
    }
    
    private void registerResponse(Object result)
    {
        XiaoJinYingIsjihuoCardnumResponse response = (XiaoJinYingIsjihuoCardnumResponse)result;
        if (response == null)
        {
            dismissProgressDialog();
            DialogUtil.showDialogOneButton2(XiaoJinYingMenuActivity.this, "服务器请求异常!");
            return;
        }
        else
        {
            if (0 == response.getCode())
            {
                if (response.getFlag().toString().equals("A"))
                {
                    // 完成激活状态
                    jihuoType = 0;
                    bt_weijihuo.setVisibility(View.VISIBLE);
                    bt_weijihuo.setBackgroundResource(R.drawable.jihuo1);
                    bt_weijihuo.setText("已激活");
                    text_weijihuo.setVisibility(View.GONE);
                    
                    // ********************************
                    // 确认已经激活后所有按钮显示
                    re = response.getResult().toString();
                    System.out.println("````````````````````````````````````````````" + re);
                    bt_weijihuo.setClickable(false);
                    // Contents.xiaoChaXunResult2.setCardNo(re.toString());
                    new XiaoMenuChaXunAsyncTask().execute(re);
                    
                }
                else if (response.getFlag().toString().equals("I"))
                {
                    dismissProgressDialog();
                    Contents.xiaoChaXunCardNo = response.getResult().toString();
                    // 待激活状态
                    jihuoType = 1;
                    // gridview.setEnabled(false);
                    bt_weijihuo.setVisibility(View.VISIBLE);
                    text_weijihuo.setVisibility(View.VISIBLE);
                    // ***********************************************
                    // 未激活
                    chaxun.setBackgroundResource(R.drawable.chaxun1);
                    qingkuan.setBackgroundResource(R.drawable.qingkuan1);
                    huankuan.setBackgroundResource(R.drawable.huankuan1);
                    biangeng.setBackgroundResource(R.drawable.biangeng1);
                    qita.setBackgroundResource(R.drawable.qita1);
                    mima.setBackgroundResource(R.drawable.mima1);
                    xiugai.setBackgroundResource(R.drawable.xiugai1);
                    xiaohu.setBackgroundResource(R.drawable.xiaohu1);
                    chaxun.setClickable(false);
                    qingkuan.setClickable(false);
                    huankuan.setClickable(false);
                    biangeng.setClickable(false);
                    qita.setClickable(false);
                    mima.setClickable(false);
                    xiugai.setClickable(false);
                    xiaohu.setClickable(false);
                    
                }
                else if (response.getFlag().toString().equals("C"))
                {
                    // 销卡
                    dismissProgressDialog(); // 异常状态
                    
                    bt_weijihuo.setVisibility(View.VISIBLE);
                    bt_weijihuo.setText("已销卡");
                    bt_weijihuo.setClickable(false);
                    // 异常状态
                    text_weijihuo.setVisibility(View.GONE);
                    jihuoType = 3;
                    chaxun.setBackgroundResource(R.drawable.chaxun1);
                    qingkuan.setBackgroundResource(R.drawable.qingkuan1);
                    huankuan.setBackgroundResource(R.drawable.huankuan1);
                    biangeng.setBackgroundResource(R.drawable.biangeng1);
                    qita.setBackgroundResource(R.drawable.qita1);
                    mima.setBackgroundResource(R.drawable.mima1);
                    xiugai.setBackgroundResource(R.drawable.xiugai1);
                    xiaohu.setBackgroundResource(R.drawable.xiaohu1);
                    chaxun.setClickable(false);
                    qingkuan.setClickable(false);
                    huankuan.setClickable(false);
                    biangeng.setClickable(false);
                    qita.setClickable(false);
                    mima.setClickable(false);
                    xiugai.setClickable(false);
                    xiaohu.setClickable(false);
                    
                }
                else if (response.getFlag().toString().equals("H"))
                {
                    // 挂失
                    
                    dismissProgressDialog();
                    // 异常状态
                    bt_weijihuo.setVisibility(View.VISIBLE);
                    bt_weijihuo.setText("挂失");
                    bt_weijihuo.setClickable(false);
                    text_weijihuo.setVisibility(View.GONE);
                    // text_weijihuo.setText("");
                    jihuoType = 4;
                    chaxun.setBackgroundResource(R.drawable.chaxun1);
                    qingkuan.setBackgroundResource(R.drawable.qingkuan1);
                    huankuan.setBackgroundResource(R.drawable.huankuan1);
                    biangeng.setBackgroundResource(R.drawable.biangeng1);
                    qita.setBackgroundResource(R.drawable.qita1);
                    mima.setBackgroundResource(R.drawable.mima1);
                    xiugai.setBackgroundResource(R.drawable.xiugai1);
                    xiaohu.setBackgroundResource(R.drawable.xiaohu1);
                    chaxun.setClickable(false);
                    qingkuan.setClickable(false);
                    huankuan.setClickable(false);
                    biangeng.setClickable(false);
                    qita.setClickable(false);
                    mima.setClickable(false);
                    xiugai.setClickable(false);
                    xiaohu.setClickable(false);
                    
                }
                
            }
            else
            {
                dismissProgressDialog();
                DialogUtil.showDialogOneButton2(XiaoJinYingMenuActivity.this, response.getMsg());
            }
        }
    }
    
    class XiaoMenuChaXunAsyncTask extends AsyncTask<String, Object, Object>
    {
        @Override
        protected Object doInBackground(String... params)
        {
            HttpHelper httpHelper = HttpHelper.getInstance(XiaoJinYingMenuActivity.this);
            List<NameValuePair> arg = new ArrayList<NameValuePair>();
            arg.add(new BasicNameValuePair("cardNo", params[0]));
            
            return httpHelper.post(ContantsAddress.XianjinyingChaXun, arg, XiaoChaXunResult.class);
        }
        
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            // showProgressDialog();
        }
        
        @Override
        protected void onPostExecute(Object result)
        {
            
            super.onPostExecute(result);
            registerResponse2(result);
            dismissProgressDialog();
        }
    }
    
    private void registerResponse2(Object result)
    {
        XiaoChaXunResult response2 = (XiaoChaXunResult)result;
        if (response2 == null)
        {
            
            DialogUtil.showDialogOneButton2(XiaoJinYingMenuActivity.this, "服务器请求异常!");
            return;
        }
        else
        {
            if (0 == response2.getCode())
            {
                
                Contents.xiaoChaXunResult2 = response2.getResult().getCardMsgBody();
                new isjihuoAsyncTask().execute(re);
            }
            else
            {
                DialogUtil.showDialogOneButton2(XiaoJinYingMenuActivity.this, response2.getMsg());
            }
        }
    }
    
    class isjihuoAsyncTask extends AsyncTask<String, Object, Object>
    {
        
        @Override
        protected Object doInBackground(String... params)
        {
            // 获取激活信息
            HttpHelper httpHelper = HttpHelper.getInstance(XiaoJinYingMenuActivity.this);
            List<NameValuePair> arg = new ArrayList<NameValuePair>();
            arg.add(new BasicNameValuePair("cardNum", params[0]));
            return httpHelper.post(ContantsAddress.Xiaojinyingischaxunmima, arg, XiaoJinYingIsJihuoResponse.class);
        }
        
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            // showProgressDialog();
        }
        
        @Override
        protected void onPostExecute(Object result)
        {
            dismissProgressDialog();
            super.onPostExecute(result);
            shifoujihuoResponse(result);
        }
    }
    
    private void shifoujihuoResponse(Object result)
    {
        XiaoJinYingIsJihuoResponse response = (XiaoJinYingIsJihuoResponse)result;
        if (response == null)
        {
            loginErrorMessage = "服务器请求异常!";
            DialogUtil.createDialog(this, 1, loginErrorMessage);
            System.out.println("--------------------------------------------------服务器异常");
            return;
        }
        else
        {
            if (0 == response.getCode())
            {
                
                if (response.getResult().getIsPwdCreate().equals("Y"))
                {
                    // ************************************
                    // 在这里设置查询密码不可点
                    mima.setBackgroundResource(R.drawable.mima1);
                    mima.setClickable(false);
                    System.out.println("********************************************" + "已经设置查询密码");
                }
                else
                {
                    // *********************************
                    // 在这里修改查询密码
                    xiugai.setBackgroundResource(R.drawable.xiugai1);
                    xiugai.setClickable(false);
                    System.out.println("----------------------------未设置查询密码--------------------------------------");
                    dismissProgressDialog();
                }
                
            }
            else
            {
                Toast.makeText(XiaoJinYingMenuActivity.this,
                    "" + response.getMsg() + "返回码" + response.getCode(),
                    Toast.LENGTH_LONG);
            }
            
        }
    }
    
}
