package com.example.admin.chufang;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.example.admin.chufang.adapter.HandleRecipeAdapter;

public class HandleRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_recipe);

        final PhotoView imgRecipe = findViewById(R.id.img_recipe_handle);
        imgRecipe.enable(); //禁用图片缩放功能
        imgRecipe.setMaxScale(3);

        //动画监听事件
        Info imgRecipeinfo = imgRecipe.getInfo();
        imgRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgRecipe.setMaxScale(3);

            }
        });


        initToolbar();

        RecyclerView recyclerView = findViewById(R.id.handle_recipe_recycle_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        HandleRecipeAdapter adapter = new HandleRecipeAdapter();
        recyclerView.setAdapter(adapter);



    }


    private void initToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("处方处理");
    }

}
