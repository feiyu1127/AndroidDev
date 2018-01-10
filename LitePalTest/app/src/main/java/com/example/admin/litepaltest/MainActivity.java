package com.example.admin.litepaltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createDatabase = findViewById(R.id.create_database);
        Button addData = findViewById(R.id.add_data);
        Button updateData = findViewById(R.id.update_data);
        Button deleteData = findViewById(R.id.delete_data);
        Button queryData = findViewById(R.id.query_data);

        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = DataSupport.findAll(Book.class);
                Log.d("循环外", "onClick: ");
                for(Book book:books){
                    Log.d(TAG, "书的名字是" + book.getName());
                    Log.d(TAG, "循环内");
                }
            }
        });

        
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSupport.deleteAll(Book.class,"price < ?","15");
            }
        });


        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Book book = new Book();
//                book.setName("The Lost Symbol");
//                book.setAuthor("Dan Brown");
//                book.setPages(510);
//                book.setPrice(16.95);
//                book.setPress("UnKnow");
//                book.save();
//
//                book.setPrice(10.99);
//                book.save();

                Book book = new Book();
                book.setPrice(250);
                book.setPress("DaHua");
                book.updateAll("name=? and author=?","The Lost Symbol","Dan Brown");

            }
        });

        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("UnKnow");
                book.save();
                Toast.makeText(MainActivity.this,"添加数据成功",Toast.LENGTH_LONG).show();
            }
        });

        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getDatabase();
                Toast.makeText(MainActivity.this,"创建数据库成功",Toast.LENGTH_LONG).show();
            }
        });

    }
}
