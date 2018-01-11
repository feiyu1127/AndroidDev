package com.example.admin.mywebview;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DialActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText dialPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dial_layout);

        Button dialBtn = findViewById(R.id.dial_button);
        dialPhone = findViewById(R.id.dial_phone);

        dialBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dial_button: //拨号
                String inputDialPhone = dialPhone.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + inputDialPhone));
                startActivity(intent);
                break;

            default:
                break;
        }
    }
}
