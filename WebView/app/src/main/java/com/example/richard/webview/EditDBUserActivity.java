package com.example.richard.webview;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.richard.db.InitDB;

public class EditDBUserActivity extends BaseActivity {
    private InitDB initDB;
    private EditText nametxt;
    private EditText sextxt;
    private EditText agetxt;
    private String user_id;
    private String position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dbuser);
        initDB = InitDB.getInitDb(this,null,1);
        user_id = getIntent().getStringExtra("user_id");
        position = getIntent().getStringExtra("position");
        nametxt = findViewById(R.id.edit_name_txt);
        sextxt = findViewById(R.id.edit_sex_txt);
        agetxt = findViewById(R.id.edit_age_txt);

        if(user_id!=null && !user_id.equals("")){
            SQLiteDatabase db = initDB.getWritableDatabase();
            String sql = "select * from user where id=?";
            Cursor cursor = db.rawQuery(sql,new String[] {user_id});
            if(cursor.moveToFirst()){
                do{
                    nametxt.setText(cursor.getString(cursor.getColumnIndex("name")));
                    sextxt.setText(cursor.getString(cursor.getColumnIndex("sex")));
                    agetxt.setText(cursor.getString(cursor.getColumnIndex("age")));
                }while(cursor.moveToNext());
            }else{
                Toast.makeText(this,"数据异常",Toast.LENGTH_SHORT).show();
                finish();
            }
        }else{
            Toast.makeText(this,"数据异常",Toast.LENGTH_SHORT).show();
            finish();
        }

        Button saveBtn = findViewById(R.id.update_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = initDB.getWritableDatabase();
                String sql = "update user set name=?,age=?,sex=? where id=?";
                db.execSQL(sql,new String[]{nametxt.getText().toString(),agetxt.getText().toString(),sextxt.getText().toString(),user_id});
                Toast.makeText(EditDBUserActivity.this,"更新成功",Toast.LENGTH_SHORT).show();

                //返回信息
                Intent intent = new Intent();
                setResult(RESULT_OK,intent);

                finish();
            }
        });
    }
}
