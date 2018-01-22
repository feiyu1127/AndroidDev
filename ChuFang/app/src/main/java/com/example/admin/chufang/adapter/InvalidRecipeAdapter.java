package com.example.admin.chufang.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.chufang.R;
import com.example.admin.chufang.service.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/1/22.
 */

public class InvalidRecipeAdapter extends RecyclerView.Adapter<InvalidRecipeAdapter.ViewHolder> {

    private List<Recipe> mList = new ArrayList<>();

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgReceipt;
        TextView hostipal;
        TextView doctor;
        TextView effectiveDate;
        TextView isEffective;

        public ViewHolder(View view) {
            super(view);
            imgReceipt = view.findViewById(R.id.img_recipe);
            hostipal = view.findViewById(R.id.hospital);
            doctor = view.findViewById(R.id.doctor);
            effectiveDate = view.findViewById(R.id.effective_date);
            isEffective = view.findViewById(R.id.is_effective);
        }
    }


    public InvalidRecipeAdapter(){
        initData();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.invalid_recipe_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe validRecipe = mList.get(position);
        holder.imgReceipt.setImageResource(validRecipe.getImgReceipt());
        holder.hostipal.setText(validRecipe.getHostipal());
        holder.doctor.setText(validRecipe.getDoctor());
        holder.effectiveDate.setText(validRecipe.getEffectiveDate());
        holder.isEffective.setText(validRecipe.getIsEffective());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private void initData(){
        for(int i=0;i<10;i++){
            Recipe rc = new Recipe();
            mList.add(rc);
            rc.setImgReceipt(R.drawable.ic_delete);
            rc.setHostipal("无效"+i+"医院");
            rc.setDoctor("周"+ i+ "医生");
            rc.setEffectiveDate("20178-01-22");
            rc.setIsEffective("无效");
        }
    }


}
