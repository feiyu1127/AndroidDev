package com.victtech.cfapp.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.victtech.cfapp.CFApplication;
import com.victtech.cfapp.R;
import com.victtech.model.entity.ChuFang;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Richard on 2018/1/18.
 */

public class ChuFang3Adapter extends RecyclerView.Adapter<ChuFang3Adapter.ChuFangViewHolder> {
    private List<ChuFang> mList = new ArrayList<>();

    public ChuFang3Adapter(){
        initData();
    }

    @Override
    public ChuFangViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(CFApplication.getContext()).inflate(R.layout.chufang3_item_layout,parent,false);
        ChuFangViewHolder vh = new ChuFangViewHolder(view);
        return vh;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(ChuFangViewHolder holder, int position) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(holder.cardView.getLayoutParams());
        if(position%2 ==0){
            params.setMargins(0,0,1,1);
        }else{
            params.setMargins(0,0,0,1);
        }
        holder.cardView.setLayoutParams(params);
    }

    private void initData(){
        for(int i=0;i<10;i++){
            mList.add(new ChuFang());
        }
    }


    class ChuFangViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView hospitalName;
        public ChuFangViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            hospitalName = cardView.findViewById(R.id.cf3_hospital);
        }
    }
}
