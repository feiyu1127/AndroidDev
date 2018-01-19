package com.victtech.cfapp.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.victtech.cfapp.CFApplication;
import com.victtech.cfapp.R;
import com.victtech.model.entity.ChuFang;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Richard on 2018/1/18.
 */

public class ChuFang2Adapter extends RecyclerView.Adapter<ChuFang2Adapter.ChuFangViewHolder> {
    private List<ChuFang> mList = new ArrayList<>();

    public ChuFang2Adapter(){
        initData();
    }

    @Override
    public ChuFangViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(CFApplication.getContext()).inflate(R.layout.chufang2_item_layout,parent,false);
        ChuFangViewHolder vh = new ChuFangViewHolder(view);
        return vh;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(ChuFangViewHolder holder, int position) {
    }

    private void initData(){
        for(int i=0;i<10;i++){
            mList.add(new ChuFang());
        }
    }


    class ChuFangViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        public ChuFangViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
        }
    }
}
