package com.example.admin.chufang.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.chufang.R;
import com.example.admin.chufang.service.Recipe;
import com.example.admin.chufang.service.Shop;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by admin on 2018/1/23.
 */

public class ShopManageAdapter extends RecyclerView.Adapter<ShopManageAdapter.ViewHolder>{


    private List<Shop> mList = new ArrayList<>();

    public ShopManageAdapter(){
        initData();
    }

     static class ViewHolder extends RecyclerView.ViewHolder{
         TextView name;
         TextView address;

         public ViewHolder(View view) {
             super(view);
             name = view.findViewById(R.id.shop_name);
             address = view.findViewById(R.id.shop_address);
         }
     }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_layout_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            Shop shop = mList.get(position);
            holder.name.setText(shop.getName());
            holder.address.setText(shop.getAddress());
    }


    /**
     * 初始化数据
     */
    private void initData(){
        for(int i = 0;i < 10;i++){
            Shop shop = new Shop();
            mList.add(shop);
            shop.setName("高科尚都"+i+"号店");
            shop.setAddress("西安雁塔区"+ i+ "23号");
        }
    }


}
