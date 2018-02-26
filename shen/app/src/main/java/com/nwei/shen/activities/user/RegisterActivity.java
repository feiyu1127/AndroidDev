package com.nwei.shen.activities.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.nwei.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    Context mContext = this;

    EditText registerRequestPhone;
    EditText registerRequestPassword;
    EditText registerVerifyCode;
    Button sendVerifyCodeBtn;
    ImageView registerBackupIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerRequestPhone = findViewById(R.id.register_request_phone);
        registerRequestPassword = findViewById(R.id.register_request_password);
        registerVerifyCode = findViewById(R.id.register_verify_code);
        sendVerifyCodeBtn = findViewById(R.id.register_send_verify_code_btn);
        registerBackupIcon = findViewById(R.id.register_backup_icon);

        registerBackupIcon.setOnClickListener(this);


        //发送验证码
//        sendVerifyCodeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("点击按钮", "onClick: ");
//                String registerRequestPhoneStr = registerRequestPhone.getText().toString();
//                if(registerRequestPhoneStr == null || registerRequestPhoneStr.length() <= 0){
//                       customToast(mContext,"手机号不能为空");
//                }
//
//                Log.d("判断号码是否为空", registerRequestPhoneStr);
//
//                HttpUtil.getRequest("verifyCode/"+registerRequestPhoneStr, new HttpCallBackLisioner() {
//                    @Override
//                    public void onFinish(String requestString) {
//                        Log.d("发送验证码", "code");
//                        try {
//                            MsgEntity msgEntity = ParseJson.parseJson(requestString,MsgEntity.class);
//                            if("0".equals(msgEntity.getCode())){
//                                customToast(mContext,msgEntity.getMessage());
//                            }else{
//                                customToast(mContext,msgEntity.getMessage());
//                            }
//                        } catch (JSONException e) {
//                            Log.d("发送验证码失败", "onFinish: ");
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onError(Exception e) {
//                        e.printStackTrace();
//                    }
//                },null);
//
//            }
//        });
//
//        //设置光标的位置
//        registerRequestPhone.setSelection(registerRequestPhone.getText().length());
//        registerRequestPassword.setSelection(registerRequestPassword.getText().length());
//        registerVerifyCode.setSelection(registerVerifyCode.getText().length());
//
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_backup_icon:
                Intent intent = new Intent(mContext,LoginActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }




    /**
     * 自定义 Toast
     * @param context
     * @param msg
     */
    private void customToast(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

}
