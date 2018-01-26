package com.example.admin.chufang.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.chufang.HandleRecipeActivity;
import com.example.admin.chufang.MyApplication;
import com.example.admin.chufang.R;
import com.example.admin.chufang.service.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/1/22.
 */

public class UntreatedRecipeAdapter extends RecyclerView.Adapter<UntreatedRecipeAdapter.ViewHolder> {

    private Context mContext;
    private static final String TAG = "UntreatedRecipeAdapter";
    private List<Recipe> mList = new ArrayList<>();

    public UntreatedRecipeAdapter(){
        initData();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        mContext = parent.getContext();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.untreated_recipe_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Recipe validRecipe = mList.get(position);
        holder.imgReceipt.setImageResource(validRecipe.getImgReceipt());
        holder.hostipal.setText(validRecipe.getHostipal());
        holder.doctor.setText(validRecipe.getDoctor());
        holder.effectiveDate.setText(validRecipe.getEffectiveDate());
        holder.isEffective.setText(validRecipe.getIsEffective());

        holder.isEffective.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getLayoutPosition();
                Intent intent = new Intent(MyApplication.getContext(),HandleRecipeActivity.class);
                mContext.startActivity(intent);
            }
        });

    }

    private void initData(){
        for(int i=0;i<10;i++){
            Recipe rc = new Recipe();
            mList.add(rc);
            rc.setImgReceipt(R.drawable.ic_backup);
            rc.setHostipal("未处理"+i+"医院");
            rc.setDoctor("周"+ i+ "医生");
            rc.setEffectiveDate("2018-01-2"+i);
            rc.setIsEffective("未处理");
        }
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


     class replaceFragmentClass extends FragmentActivity{
        public void  replaceFragment(Fragment fragment){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.main_content,fragment);
            transaction.commit();
        }
    }

}
