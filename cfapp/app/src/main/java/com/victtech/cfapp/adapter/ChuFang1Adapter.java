package com.victtech.cfapp.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.victtech.cfapp.CFApplication;
import com.victtech.cfapp.R;
import com.victtech.component.ImageDialog;
import com.victtech.model.entity.ChuFang;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Richard on 2018/1/18.
 */

public class ChuFang1Adapter extends RecyclerView.Adapter<ChuFang1Adapter.ChuFangViewHolder> {
    private Context mContext;
    private List<ChuFang> mList = new ArrayList<>();

    public ChuFang1Adapter(){
        initData();
    }

    @Override
    public ChuFangViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(CFApplication.getContext()).inflate(R.layout.chufang1_item_layout,parent,false);
        ChuFangViewHolder vh = new ChuFangViewHolder(view);
        return vh;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(final ChuFangViewHolder holder, final int position) {
        if(position==0){
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(holder.cardView.getLayoutParams());
            params.setMargins(0,0,0,0);
            holder.cardView.setLayoutParams(params);
        }

        Glide.with(mContext).load(mList.get(position).getAvatar()).into(holder.avatar);

        //定义点击头像事件
        holder.avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageDialog.Builder builder = new ImageDialog.Builder(mContext);
                ImageDialog imageDialog = builder.create();
                imageDialog.show();
                imageDialog.setImageSrc(mList.get(position).getAvatar());
            }
        });

    }

    private void initData(){
        for(int i=0;i<10;i++){
            ChuFang cf = new ChuFang();
            mList.add(cf);
            if(i%2==0){
                cf.setAvatar("http://pic15.nipic.com/20110616/7177713_105928197391_2.jpg");
            }else{
                cf.setAvatar("http://pic.nipic.com/2007-06-14/200761413321323_2.jpg");
            }
        }
    }


    class ChuFangViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView avatar;
        public ChuFangViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            avatar = cardView.findViewById(R.id.cf1_avatar);
        }
    }
}
