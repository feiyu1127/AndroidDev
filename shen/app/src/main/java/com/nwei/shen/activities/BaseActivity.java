package com.nwei.shen.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by admin on 2018/2/26.
 */

public class BaseActivity extends AppCompatActivity {

    protected void customUIToastThread(final Context context, final String msg){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }


    protected void customUIToast(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

}
