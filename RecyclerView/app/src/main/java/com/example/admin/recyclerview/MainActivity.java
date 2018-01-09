package com.example.admin.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<>();
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFruit();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        Log.d("this是谁", this.toString());

//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);

    }




    private void initFruit(){
        for(int i = 0; i < 100;i++){
            Fruit apple = new Fruit(getRandomLengthName("Apple"),R.drawable.pc_1);
            fruitList.add(apple); //往数组中追加数据

            Fruit banana = new Fruit(getRandomLengthName("banana"),R.drawable.pc_2);
            fruitList.add(banana);

            Fruit orange = new Fruit(getRandomLengthName("orange"),R.drawable.pc_3);
            fruitList.add(orange);

        }
    }


    private String getRandomLengthName(String name){
        Random random = new Random();
        int length = random.nextInt(20)+1;
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < length; i++){
            builder.append(name);
        }
        return builder.toString();
    }

}
