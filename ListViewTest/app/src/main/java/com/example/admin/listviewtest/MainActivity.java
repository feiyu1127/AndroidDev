package com.example.admin.listviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String[] data = {
            "Apple",
            "banana",
            "orange",
//            "Apple4",
//            "Apple5",
//            "Apple6",
//            "Apple7",
//            "Apple8",
//            "Apple9",
//            "Apple10",
//            "Apple11",
//            "Apple12",
//            "Apple13",
//            "Apple14",
//            "Apple15",
//            "Apple16",
//            "Apple17",
//            "Apple18",
//            "Apple19",
//            "Apple20",
    };


    private List<Fruit> fruitList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFruit(); //初始化水果数据
        FruitAdapter adapter = new FruitAdapter(MainActivity.this,R.layout.fruit_item,fruitList);

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,data);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);

    }


    private void initFruit(){
        for(int i = 0; i < 20;i++){
             Fruit apple = new Fruit("Apple",R.drawable.pc_1);
             fruitList.add(apple); //往数组中追加数据

             Fruit banana = new Fruit("banana",R.drawable.pc_2);
             fruitList.add(banana);

             Fruit orange = new Fruit("orange",R.drawable.pc_3);
             fruitList.add(orange);

        }
    }


}
