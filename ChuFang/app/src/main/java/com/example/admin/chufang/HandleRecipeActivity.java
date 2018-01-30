package com.example.admin.chufang;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin.chufang.adapter.HandleRecipeAdapter;
import com.example.admin.chufang.components.ImageDialog;


public class HandleRecipeActivity extends AppCompatActivity {

    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_recipe);
        initToolbar();

        ImageView imgRecipe = findViewById(R.id.img_recipe_handle);

        imgRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext,"123",Toast.LENGTH_SHORT).show();
                ImageDialog.Builder builder = new ImageDialog.Builder(mContext);
                ImageDialog imageDialog = builder.create();
                imageDialog.show();
//                imageDialog.setImageSrc(mList.get(position).getAvatar());
            }
        });


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
