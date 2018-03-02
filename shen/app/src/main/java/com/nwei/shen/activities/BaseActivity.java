package com.nwei.shen.activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by admin on 2018/2/26.
 */

public class BaseActivity extends AppCompatActivity {

    private static SharedPreferences mSharedPreferences;

    protected void customUIToast(final String msg){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MyApplication.getContext(),msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 持久化数据
     * @param name
     * @param msg
     */
    protected void saveMsgToSharedPreferences(String name,String msg){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(name,msg);
        editor.apply();
    }

}
