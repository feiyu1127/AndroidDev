package com.yucheng.byxf.mini.rapidly;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.yucheng.byxf.mini.ui.BaseActivity;
import com.yucheng.byxf.mini.ui.HomeActivity;
import com.yucheng.byxf.mini.ui.R;

/**
 * 类名: RepidlyLoanInfoTips</br>
 * 描述: 极速贷小贴士</br>
 * 开发人员： weiyb</br>
 * 创建时间： 2015-7-20
 */

public class RepidlyLoanInfoTips extends BaseActivity
{
    
    private String stateType = "";
    
    private Button bt_next;
    
    private ImageView bt_back;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repidly_tips_activity);
        Intent it = getIntent();
        stateType = it.getStringExtra("choiceType");
        // 接收 是不是打回操作的标志；
        bt_next = (Button)findViewById(R.id.bt_next);
        bt_next.setOnClickListener(new OnClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                // 自动生成的方法存根
                Intent intent = new Intent();
                intent.setClass(RepidlyLoanInfoTips.this, RepidlyLoanInfoContractBook.class);
                intent.putExtra("choiceType", stateType);
                // 跳转到极速贷代款条款页
                startActivity(intent);
                
            }
        });
        bt_back = (ImageView)findViewById(R.id.back);
        // 回退
        bt_back.setOnClickListener(new OnClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                finish();
                Intent intent = new Intent();
                intent.setClass(RepidlyLoanInfoTips.this, HomeActivity.class);
                startActivity(intent);
                
            }
        });
    }
    
    //
    // Intent intent =new Intent();
    // intent.setClass(RedPacketActivity2.this,
    // RepidlyLoanInfoContractBook.class);
    // intent.putExtra("choiceType", stateType);
    // startActivity(intent);
    
    @Override
    protected void onDestroy()
    {
        
        super.onDestroy();
        
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            // Intent it1 = new Intent(RepidlyLoanInfoTips.this,
            // HomeActivity.class);
            // startActivity(it1);
            // finish();
        }
        return true;
    }
    
}
