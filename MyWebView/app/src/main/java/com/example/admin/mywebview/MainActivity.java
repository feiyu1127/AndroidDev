package com.example.admin.mywebview;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button customBtn = findViewById(R.id.custom_dial);
        Button robotChatBtn = findViewById(R.id.robot_chat_btn);

        customBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DialActivity.class);
                startActivity(intent);
            }
        });


        robotChatBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.robot_chat_btn: //机器人聊天按钮
                Intent intent = new Intent(this,RobotChatActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //1:为哪一个资源文件创建菜单,2:菜单将添加到哪一个 Menu 对象中
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override //点击菜单按钮方法
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.access_baidu:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.baidu.com")); //将字符串解析成 Uri 对象
                startActivity(intent);
                break;
            case R.id.tel_10086:
                //Intent.ACTION_DIAL 是系统内置的动作
                Intent intentDial = new Intent(Intent.ACTION_DIAL);
                intentDial.setData(Uri.parse("tel:10086"));
                startActivity(intentDial);
                break;
            default:
                break;
        }

        return  true;
    }
}
