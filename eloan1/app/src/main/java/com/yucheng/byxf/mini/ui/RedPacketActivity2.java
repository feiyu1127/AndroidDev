package com.yucheng.byxf.mini.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.yucheng.byxf.mini.rapidly.RepidlyLoanInfoTips;

/**
 * 类名: RedPacketActivity2</br>
 * 描述:极速贷 首页</br>
 * 开发人员： weiyb</br>
 * 创建时间： 2015-7-20
 */

public class RedPacketActivity2 extends BaseActivity
{
    private String stateType = "";
    
    private Button bt_next;
    
    private Button bt_share;
    
    private ImageView back;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // 自动生成的方法存根
        super.onCreate(savedInstanceState);
        setContentView(R.layout.redpacket_activity2);
        Intent it = getIntent();
        stateType = it.getStringExtra("choiceType");
        // 接收 是不是打回操作的标志；
        // stateType=;
        back = (ImageView)findViewById(R.id.back);
        back.setOnClickListener(new OnClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                // 自动生成的方法存根
                Intent intent = new Intent();
                intent.setClass(RedPacketActivity2.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bt_next = (Button)findViewById(R.id.bt_next);
        bt_next.setOnClickListener(new OnClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                // 自动生成的方法存根
                Intent intent = new Intent();
                // intent.setClass(RedPacketActivity2.this,
                // RepidlyLoanInfoContractBook.class);
                intent.setClass(RedPacketActivity2.this, RepidlyLoanInfoTips.class);
                intent.putExtra("choiceType", stateType);
                startActivity(intent);
                
            }
        });
        
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        // 这里重写返回键
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            Intent intent = new Intent();
            intent.setClass(RedPacketActivity2.this, HomeActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return false;
    }
    
}
