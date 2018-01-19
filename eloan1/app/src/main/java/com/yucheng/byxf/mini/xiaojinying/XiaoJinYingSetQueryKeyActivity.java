package com.yucheng.byxf.mini.xiaojinying;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.map.DemoApplication;
import com.yucheng.byxf.mini.message.Response;
import com.yucheng.byxf.mini.ui.BaseActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DESUtils;
import com.yucheng.byxf.mini.utils.DialogUtil;

public class XiaoJinYingSetQueryKeyActivity extends BaseActivity
{
    /**
     * 设置查询密码
     */
    private EditText et_findpw;
    
    private EditText et_findpw2;
    
    private Button queding_button;
    
    private ImageView back_setquertkey;
    
    String key1 = "";
    
    String key2 = "";
    
    private ImageView back1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xiaojinyin_findpassword_activity);
        
        et_findpw = (EditText)findViewById(R.id.et_findpw);
        et_findpw2 = (EditText)findViewById(R.id.et_findpw2);
        queding_button = (Button)findViewById(R.id.queding_button);
        back_setquertkey = (ImageView)findViewById(R.id.back_setquertkey);
        back_setquertkey.setOnClickListener(new OnClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClass(XiaoJinYingSetQueryKeyActivity.this, XiaoJinYingMenuActivity.class);
                startActivity(intent);
            }
        });
        queding_button.setOnClickListener(this);
        back_setquertkey = (ImageView)findViewById(R.id.back_setquertkey);
        back_setquertkey.setOnClickListener(new OnClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                finish();
                
            }
        });
    }
    
    @Override
    public void onClick(View v)
    {
        super.onClick(v);
        switch (v.getId())
        {
            case R.id.queding_button:
                key1 = et_findpw.getText().toString();
                key2 = et_findpw2.getText().toString();
                if (key1 == null || key1.equals(""))
                {
                    DialogUtil.showDialogOneButton2(XiaoJinYingSetQueryKeyActivity.this, "请输入密码");
                    return;
                }
                if (key2 == null || key2.equals(""))
                {
                    DialogUtil.showDialogOneButton2(XiaoJinYingSetQueryKeyActivity.this, "请输入确认密码");
                    return;
                }
                if (!key1.equals(key2))
                {
                    DialogUtil.showDialogOneButton2(XiaoJinYingSetQueryKeyActivity.this, "请输入相同密码");
                    return;
                }
                // 测试
                new XiaoSetKetAsyncTask().execute(Contents.xiaoChaXunResult2.getCardNo(),
                    Contents.xiaoChaXunResult2.getIdNo(),
                    "2",
                    key1);
                
                break;
            
            default:
                break;
        }
        
    }
    
    class XiaoSetKetAsyncTask extends AsyncTask<String, Object, Object>
    {
        @Override
        protected Object doInBackground(String... params)
        {
            HttpHelper httpHelper = HttpHelper.getInstance(XiaoJinYingSetQueryKeyActivity.this);
            List<NameValuePair> arg = new ArrayList<NameValuePair>();
            arg.add(new BasicNameValuePair("cardNum", params[0]));
            arg.add(new BasicNameValuePair("iDNum", params[1]));
            arg.add(new BasicNameValuePair("txn_Flag", params[2]));
            // arg.add(new BasicNameValuePair("newPsw", params[3]));
            
            try
            {
                arg.add(new BasicNameValuePair("newPsw", DESUtils.encryptMode(params[3],
                    DemoApplication.getApplication())));
            }
            catch (Exception e)
            {
                
                e.printStackTrace();
            }
            
            return httpHelper.post(ContantsAddress.XiaoJingYingsetkey, arg, Response.class);
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
            dismissProgressDialog();
            super.onPostExecute(result);
            registerResponse2(result);
        }
    }
    
    private void registerResponse2(Object result)
    {
        Response response2 = (Response)result;
        if (response2 == null)
        {
            
            DialogUtil.showDialogOneButton2(XiaoJinYingSetQueryKeyActivity.this, getString(R.string.xiaojinying_fwqyc));
            return;
        }
        else
        {
            if (0 == response2.getCode())
            {
                
                DialogUtil.showDialogOneButton(XiaoJinYingSetQueryKeyActivity.this,
                
                "提交成功！", new OnClickListener()
                {
                    
                    @Override
                    public void onClick(View v)
                    {
                        Intent it = new Intent();
                        it.setClass(XiaoJinYingSetQueryKeyActivity.this, XiaoJinYingMenuActivity.class);
                        startActivity(it);
                        finish();
                        
                    }
                    
                });
                
            }
            else
            {
                DialogUtil.showDialogOneButton2(XiaoJinYingSetQueryKeyActivity.this, response2.getMsg());
            }
        }
    }
}
