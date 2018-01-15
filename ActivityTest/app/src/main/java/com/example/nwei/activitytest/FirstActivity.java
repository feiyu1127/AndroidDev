package com.example.nwei.activitytest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends BaseActivity {

    private static final String TAG = "FirstActivity";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);

        Log.d(TAG, this.toString());
//        Log.d("FirstActivity", "Task id is " + getTaskId());
        
        Button button1 = findViewById(R.id.button_1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(FirstActivity.this,"亲,请点击按钮",Toast.LENGTH_LONG).show();
//                Intent intent = new Intent("com.example.nwei.activitytest.ACTION_START");
//                intent.addCategory("com.example.nwei.activitytest.MY_CATEGORY");

                //打开一个网页
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("tel:10086"));
//                startActivity(intent);

//                  String data = "你好,第二个活动";
//                  Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
//                  intent.putExtra("extra_data",data);
//                  startActivity(intent);
//                Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
//                startActivityForResult(intent,1);

//                    Intent intent = new Intent(FirstActivity.this,FirstActivity.class);
                    Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
                    startActivity(intent);


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return  true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            switch (requestCode){
                case 1:
                    if(resultCode == RESULT_OK){
                        String returnedData = data.getStringExtra("data_return");
                        Log.d("销毁时返回的结果", returnedData);
                    }
                    break;
                default:
                    break;
            }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(this,"添加按钮",Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this,"删除按钮",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }
}
