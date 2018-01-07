package com.example.nwei.activitytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ThirdActivity extends BaseActivity {

    private static final String TAG = "ThirdActivity";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_layout);

        Log.d(TAG, "Task id is " + getTaskId());
        
    }
}
