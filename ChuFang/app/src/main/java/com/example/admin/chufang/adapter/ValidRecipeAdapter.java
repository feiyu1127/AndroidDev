package com.example.admin.chufang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.chufang.R;
import com.example.admin.chufang.entity.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/1/22.
 */

public class ValidRecipeAdapter extends RecyclerView.Adapter<ValidRecipeAdapter.ViewHolder> {

    private Context mContext;

    private List<Recipe> mList = new ArrayList<>();

    public ValidRecipeAdapter(){
        initData();
    }

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


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.valid_recipe_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe validRecipe = mList.get(position);
        Glide.with(mContext).load(mList.get(position).getImgReceipt()).into(holder.imgReceipt);
        holder.hostipal.setText(validRecipe.getHostipal());
        holder.doctor.setText(validRecipe.getDoctor());
        holder.effectiveDate.setText(validRecipe.getEffectiveDate());
        holder.isEffective.setText(validRecipe.getIsEffective());
    }

    /**
     * 初始化数据
     */
    private void initData(){
        for(int i = 0;i < 10;i++){
            Recipe rc = new Recipe();
            mList.add(rc);
            rc.setImgReceipt("http://img1.3lian.com/2015/a1/91/d/58.jpg");
            rc.setHostipal("有效"+i+"医院");
            rc.setDoctor("周"+ i+ "医生");
            rc.setEffectiveDate("20178-01-22");
            rc.setIsEffective("有效");
        }
    }

}
