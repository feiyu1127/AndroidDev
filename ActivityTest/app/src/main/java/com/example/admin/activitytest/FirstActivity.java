package com.example.admin.activitytest;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {
    private static final String TAG = "FirstActivity";
    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);

        Button button1 = findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d(TAG, this.toString());
//                Intent intent = new Intent(mContext,SecondActivity.class);
//                startActivityForResult(intent,1);
//                dialog1();
//                dialog1_1();
//                dialog2();
//                  dialog3();
                  dialog4();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    String name = data.getStringExtra("name");
                    Toast.makeText(mContext,name,Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }


    private void dialog1(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this); //先得到构造器
        builder.setTitle("提示"); //设置标题
        builder.setMessage("是否确认退出?"); //设置内容
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); //关闭dialog
                Toast.makeText(mContext, "确认" + which, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(mContext, "取消" + which, Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNeutralButton("忽略", new DialogInterface.OnClickListener() {//设置忽略按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(mContext, "忽略" + which, Toast.LENGTH_SHORT).show();
            }
        });
        //参数都设置完成了，创建并显示出来
        builder.create().show();
    }


    private void dialog1_1(){
        //先new出一个监听器，设置好监听 
        DialogInterface.OnClickListener dialogOnclicListener=new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(which){
                    case Dialog.BUTTON_POSITIVE:
                        Toast.makeText(mContext, "确认" + which, Toast.LENGTH_SHORT).show();
                        break;
                    case Dialog.BUTTON_NEGATIVE:
                        Toast.makeText(mContext, "取消" + which, Toast.LENGTH_SHORT).show();
                        break;
                    case Dialog.BUTTON_NEUTRAL:
                        Toast.makeText(mContext, "忽略" + which, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        //dialog参数设置 
        AlertDialog.Builder builder=new AlertDialog.Builder(this); //先得到构造器 
        builder.setTitle("提示"); //设置标题 
        builder.setMessage("是否确认退出?"); //设置内容 
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可 
        builder.setPositiveButton("确认",dialogOnclicListener);
        builder.setNegativeButton("取消", dialogOnclicListener);
        builder.setNeutralButton("忽略", dialogOnclicListener);
        builder.create().show();
    }

    /**
     * 列表对话框
     */
    private void dialog2() {
        final String items[]={"张三","李四","王五","王五","马六","李七","赵八"};
        //dialog参数设置 
        AlertDialog.Builder builder=new AlertDialog.Builder(this); //先得到构造器 
        builder.setTitle("提示"); //设置标题 
        //builder.setMessage("是否确认退出?"); //设置内容 
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可 
        //设置列表显示，注意设置了列表显示就不要设置builder.setMessage()了，否则列表不起作用。 
        builder.setItems(items,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(mContext, items[which], Toast.LENGTH_SHORT).show();

            }
        });
        builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(mContext, "确定", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                  dialog.dismiss();
                  Toast.makeText(mContext, "取消", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }


    /**
     * 单选对话框
     */
    private void dialog3(){
        final String items[]={"男","女"};
        AlertDialog.Builder builder=new AlertDialog.Builder(this); //先得到构造器 
        builder.setTitle("提示"); //设置标题 
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可 
        builder.setSingleChoiceItems(items,0,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //dialog.dismiss(); 
                Toast.makeText(mContext, items[which], Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(mContext, "确定", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }


    /**
     * 多选对话框
     */
    private void dialog4(){
        final String items[]={"篮球","足球","排球"};
        final boolean selected[]={true,false,true};
        AlertDialog.Builder builder=new AlertDialog.Builder(this); //先得到构造器 
        builder.setTitle("提示"); //设置标题 
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可 
        builder.setMultiChoiceItems(items,selected,new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                // dialog.dismiss(); 
                Toast.makeText(mContext, items[which]+isChecked, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(mContext, "确定", Toast.LENGTH_SHORT).show();
                //android会自动根据你选择的改变selected数组的值。 
                for (int i=0;i<selected.length;i++){
                    Log.e("hongliang",""+selected[i]);
                }
            }
        });
        builder.create().show();
    }


}
