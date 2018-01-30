package com.example.admin.chufang.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.chufang.R;
import com.example.admin.chufang.entity.HandleRecipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/1/26.
 */

public class HandleRecipeAdapter extends RecyclerView.Adapter<HandleRecipeAdapter.ViewHolder> {

    private List<HandleRecipe> handleRecipeList = new ArrayList<>();

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView medicine_code;
        TextView medicine_name;
        TextView manufacturer_name;
        TextView trademark;
        TextView number;

        public ViewHolder(View view) {
            super(view);
            medicine_code = view.findViewById(R.id.medicine_code);
            medicine_name = view.findViewById(R.id.medicine_name);
            manufacturer_name = view.findViewById(R.id.manufacturer_name);
            trademark = view.findViewById(R.id.trademark);
            number = view.findViewById(R.id.number);
        }
    }


    public HandleRecipeAdapter(){
        initData();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.handle_recipe_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return handleRecipeList.size();
    }


    private void initData(){
        for(int i=0;i<10;i++){
            HandleRecipe hr = new HandleRecipe();
            handleRecipeList.add(hr);
            hr.setMedicine_code("12541254122");
            hr.setMedicine_name("阿莫西林");
            hr.setManufacturer_name("国药集团汕头金石制药有限公司");
            hr.setTrademark("西安维客");
            hr.setNumber(2);

        }
    }


}
