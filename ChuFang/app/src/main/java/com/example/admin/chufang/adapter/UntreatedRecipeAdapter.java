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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.admin.chufang.HandleRecipeActivity;
import com.example.admin.chufang.MyApplication;
import com.example.admin.chufang.R;
import com.example.admin.chufang.components.ImageDialog;
import com.example.admin.chufang.entity.ErrorModel;
import com.example.admin.chufang.entity.Recipe;
import com.example.admin.chufang.gson.Data;
import com.example.admin.chufang.gson.InnerData;
import com.example.admin.chufang.gson.InnerDataList;
import com.example.admin.chufang.gson.Medicine;
import com.example.admin.chufang.utils.HttpRequest;
import com.example.admin.chufang.utils.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by admin on 2018/1/22.
 */

public class UntreatedRecipeAdapter extends RecyclerView.Adapter<UntreatedRecipeAdapter.ViewHolder> {

    private Context mContext;
    private static final String TAG = "UntreatedRecipeAdapter";
    private List<Recipe> mList = new ArrayList<>();

    public UntreatedRecipeAdapter(){ //构造方法
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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Recipe validRecipe = mList.get(position);
        Glide.with(mContext).load(mList.get(position).getImgReceipt()).into(holder.imgReceipt);
        holder.hospital.setText(validRecipe.getHostipal());
        holder.doctor.setText(validRecipe.getDoctor());
        holder.effectiveDate.setText(validRecipe.getEffectiveDate());
        holder.isEffective.setText(validRecipe.getIsEffective());

        //点击处理处方
        holder.isEffective.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyApplication.getContext(),HandleRecipeActivity.class);
                mContext.startActivity(intent);
            }
        });

        //点击图片
        holder.imgReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageDialog.Builder builder = new ImageDialog.Builder(mContext);
                ImageDialog imageDialog = builder.create();
                imageDialog.show();
                imageDialog.setImageSrc(mList.get(position).getImgReceipt());
            }
        });

    }

    private void initData(){

        for(int i=0;i<10;i++){
            Recipe rc = new Recipe();
            mList.add(rc);
            rc.setImgReceipt("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3446672618,225431101&fm=27&gp=0.jpg");
            rc.setHostipal(i+"西京医院");
            rc.setDoctor(i+"李医生");
            rc.setEffectiveDate("2018-01-30");
            rc.setIsEffective("待处理");
        }


        //---------------------- 请求服务器数据
//        String url = "https://chufang-api.victtech.com/api/v1/recipes?status=5";
//        HttpRequest.sendOkHttpRequest(url, new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                    Toast.makeText(mContext,"获取数据失败!",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                    final String responseText = response.body().string();
//                    final Data data = HttpUtil.JsonToData(responseText);
//                    if(data != null && "0".equals(data.code)) {
//                            showDataInfo(data.innerDataLists);
//                    }else{
//                            Toast.makeText(mContext,data.message,Toast.LENGTH_SHORT).show();
//                    }
//            }
//        });

    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgReceipt;
        TextView hospital;
        TextView doctor;
        TextView effectiveDate;
        TextView isEffective;

        public ViewHolder(View view) {
            super(view);
            imgReceipt = view.findViewById(R.id.img_recipe);
            hospital = view.findViewById(R.id.hospital);
            doctor = view.findViewById(R.id.doctor);
            effectiveDate = view.findViewById(R.id.effective_date);
            isEffective = view.findViewById(R.id.is_effective);
        }
    }


    /**
     * 显示数据
     * @param innerDataList
     */

    private void showDataInfo(InnerDataList innerDataList){
            for(InnerData innerData : innerDataList.innerDataList){
                Recipe rc = new Recipe();
                mList.add(rc);
//                Glide.with(mContext).load(mList.get(position).getImgReceipt()).into(holder.imgReceipt);

//                rc.setImgReceipt(innerData.recipe_img);
                rc.setHostipal(innerData.hospital);
                rc.setDoctor(innerData.doctor);
                rc.setEffectiveDate(innerData.created_at);

                switch (innerData.status){
                    case 0:
                        rc.setIsEffective("无效");
                        break;
                    case 1:
                        rc.setIsEffective("有效");
                        break;
                    case -1:
                        rc.setIsEffective("未处理");
                        break;
                    default:
                        break;
                }
            }
    }


}
