package com.example.richard.webview;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.richard.db.DBUser;
import com.example.richard.db.InitDB;
import com.example.richard.tools.ClickDbUserLisioner;
import com.example.richard.tools.DBUserAdaptor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class SaveDataActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "SaveDataActivity";
    EditText savetxt ;
    EditText nametxt;
    EditText agetxt;
    RecyclerView recyclerView;
    DBUserAdaptor dbUserAdaptor;
    InitDB initDB;
    EditText et_name;
    EditText et_sex;
    EditText et_age;
    Context context;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                   initData();
                }
                break;
            default:
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);
        savetxt = findViewById(R.id.save_text);
        nametxt = findViewById(R.id.name_txt);
        agetxt = findViewById(R.id.age_txt);
        et_name = findViewById(R.id.name_db_txt);
        et_sex = findViewById(R.id.sex_db_txt);
        et_age = findViewById(R.id.age_db_txt);
        context = this;

        Button saveBtn = findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(this);

        Button loadBtn = findViewById(R.id.load_btn);
        loadBtn.setOnClickListener(this);

        Button sharedBtn = findViewById(R.id.shared_btn);
        sharedBtn.setOnClickListener(this);

        Button loadSharedBtn = findViewById(R.id.load_shared_btn);
        loadSharedBtn.setOnClickListener(this);

        Button dbSaveBtn = findViewById(R.id.db_save_btn);
        dbSaveBtn.setOnClickListener(this);

        initDB =  InitDB.getInitDb(this,null,1);

        recyclerView = findViewById(R.id.db_user_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        dbUserAdaptor = new DBUserAdaptor();
        dbUserAdaptor.setLisioner(new ClickDbUserLisioner() {
            @Override
            public void onClickItem(DBUser user) {
                et_name.setText(user.getName());
                et_age.setText(user.getAge());
                et_sex.setText(user.getSex());
            }

            @Override
            public void onClickEditBtn(int id,int position) {
                Intent intent = new Intent(SaveDataActivity.this,EditDBUserActivity.class);
                intent.putExtra("user_id",String.valueOf(id));
//                startActivity(intent);
                startActivityForResult(intent,1);
            }

            @Override
            public void onClickRemoveBtn(int id) {
                final String user_id = String.valueOf(id);
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("警告");
                alert.setMessage("确认删除该记录吗？");
                alert.setCancelable(true);
                alert.setNegativeButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteDatabase db = initDB.getWritableDatabase();
                        String sql = "delete from user where id =?";
                        db.execSQL(sql,new String[]{user_id});
                        initData();
                    }
                });
                alert.setPositiveButton("取消", new Dialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alert.show();
            }
        });


        initData();

    }

    private void initData(){
        dbUserAdaptor.clear();
        //get data from db
        SQLiteDatabase db = initDB.getWritableDatabase();
        Cursor cursor  = db.query("user",null,null,null,null,null,"id desc");
        if(cursor.moveToFirst()){
            do{
                DBUser dbUser = new DBUser();
                dbUser.setId(cursor.getInt(cursor.getColumnIndex("id")));
                dbUser.setName(cursor.getString(cursor.getColumnIndex("name")));
                dbUser.setSex(cursor.getString(cursor.getColumnIndex("sex")));
                dbUser.setAge(cursor.getString(cursor.getColumnIndex("age")));
                dbUserAdaptor.addUser(dbUser);
            }while(cursor.moveToNext());
        }
        recyclerView.setAdapter(dbUserAdaptor);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save_btn:
                BufferedWriter writer = null;
                try {
                    FileOutputStream out = openFileOutput("savedata", Context.MODE_PRIVATE);
                    writer = new BufferedWriter(new OutputStreamWriter(out) );
                    writer.write(savetxt.getText().toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(writer != null){
                        try {
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case R.id.load_btn:
                FileInputStream in = null;
                BufferedReader reader = null;
                StringBuilder content = new StringBuilder();
                try {
                    in = openFileInput("savedata");
                    reader = new BufferedReader(new InputStreamReader(in));
                    String line = "";
                    while((line = reader.readLine()) != null){
                        content.append(line);
                    }
                    Toast.makeText(SaveDataActivity.this,content.toString(),Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.shared_btn:
                SharedPreferences.Editor editor = getSharedPreferences("userinfo",MODE_PRIVATE).edit();
                editor.putString("name",nametxt.getText().toString());
                editor.putInt("age",Integer.parseInt(agetxt.getText().toString()) );
                editor.apply();
                break;
            case R.id.load_shared_btn:
                SharedPreferences sharedPreferences = getSharedPreferences("userinfo",MODE_PRIVATE);
                String msg = "姓名："+sharedPreferences.getString("name","")+"\n"+"年龄："+sharedPreferences.getInt("age",0);
                Toast.makeText(SaveDataActivity.this,msg,Toast.LENGTH_SHORT).show();
                break;
            case R.id.db_save_btn:
                String db_user_name = et_name.getText().toString();
                String db_user_sex = et_sex.getText().toString();
                String db_user_age = et_age.getText().toString();
                if(db_user_age.equals("")||db_user_name.equals("")||db_user_sex.equals("")){
                    Toast.makeText(SaveDataActivity.this,"姓名、性别、年龄不能为空",Toast.LENGTH_SHORT).show();
                }else{
                    SQLiteDatabase db = initDB.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("name",db_user_name);
                    values.put("sex",db_user_sex);
                    values.put("age",db_user_age);
                    long id = db.insert("user",null,values);
                    int user_id = (int)id;
                    dbUserAdaptor.addUser(new DBUser(user_id,db_user_name,db_user_sex,db_user_age));
                    dbUserAdaptor.notifyItemInserted(dbUserAdaptor.getItemCount()-1);
                    recyclerView.scrollToPosition(dbUserAdaptor.getItemCount()-1);
                    et_age.setText("");
                    et_sex.setText("");
                    et_name.setText("");
                    et_name.setFocusable(true);
                }

                break;
            default:
        }
    }

}
