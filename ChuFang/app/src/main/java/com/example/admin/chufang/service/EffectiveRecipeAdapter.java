package com.example.admin.chufang.service;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.chufang.R;

import java.util.List;

/**
 * Created by admin on 2018/1/19.
 */

public class EffectiveRecipeAdapter extends RecyclerView.Adapter<EffectiveRecipeAdapter.ViewHolder> {
    private List<Recipe> mEffectiveRecipeList;

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

    public EffectiveRecipeAdapter(List<Recipe> effectiveRecipeList){
        mEffectiveRecipeList = effectiveRecipeList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.valid_recipe_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe effectiveRecipe = mEffectiveRecipeList.get(position);
        holder.imgReceipt.setImageResource(effectiveRecipe.getImgReceipt());
        holder.hostipal.setText(effectiveRecipe.getHostipal());
        holder.doctor.setText(effectiveRecipe.getDoctor());
        holder.effectiveDate.setText(effectiveRecipe.getEffectiveDate());
        holder.isEffective.setText(effectiveRecipe.getIsEffective());
    }

    @Override
    public int getItemCount() {
        return mEffectiveRecipeList.size();
    }
}
