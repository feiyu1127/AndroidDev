package com.nwei.httprequest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.nwei.model.TestModel1;
import com.nwei.model.entity.Test1;
import com.nwei.utils.HttpCallBackLisioner;
import com.nwei.utils.HttpUtil;
import com.nwei.utils.ParseJson;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button sendRequest;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendRequest = findViewById(R.id.send_request);

        sendRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_request:
                HttpUtil.getRequest("jsontest1", new HttpCallBackLisioner() {
                    @Override
                    public void onFinish(String requestString) {
                        try {
                            TestModel1 testModel1 = ParseJson.parseJson(requestString,TestModel1.class);
                            Test1 test1 = testModel1.getTest1Data();

//                            ApiVal apiVal = test1.getApiVal();
//                            Log.d(TAG, apiVal.getNote());

                            Log.d(TAG, test1.getName());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                },null);
                break;
            default:
                break;
        }
    }
}
