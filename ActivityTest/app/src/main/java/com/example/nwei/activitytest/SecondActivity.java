package com.example.nwei.activitytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends  BaseActivity {

    private static final String TAG = "SecondActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

//        Intent intent = getIntent();
//        String data = intent.getStringExtra("extra_data");
//        Log.d("lala", data);

        Log.d("SecondActivity", "Task id is " + getTaskId());

        Button button2 = findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.putExtra("data_return","我是从二回来的");
//                setResult(RESULT_OK,intent);
//                finish();

//                    Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
                    Intent intent = new Intent(SecondActivity.this,FirstActivity.class);
                    startActivity(intent);

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("data_return","我是从二222222回来的");
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
