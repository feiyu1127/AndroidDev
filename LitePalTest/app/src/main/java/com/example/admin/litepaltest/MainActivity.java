package com.example.admin.litepaltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.tablemanager.Connector;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createDatabase = findViewById(R.id.create_database);
        Button addData = findViewById(R.id.add_data);
        Button updateData = findViewById(R.id.update_data);

        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("The Lost Symbol");
                book.setAuthor("Dan Brown");
                book.setPages(510);
                book.setPrice(16.95);
                book.setPress("UnKnow");
                book.save();

                book.setPrice(10.99);
                book.save();
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
