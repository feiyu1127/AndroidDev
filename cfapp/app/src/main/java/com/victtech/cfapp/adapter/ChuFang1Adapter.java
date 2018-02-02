package com.victtech.cfapp.adapter;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.sunfusheng.glideimageview.GlideImageView;
import com.sunfusheng.glideimageview.ShapeImageView;
import com.victtech.cfapp.CFApplication;
import com.victtech.cfapp.ProcessActivity;
import com.victtech.cfapp.R;
import com.victtech.cfapp.fragment.ChuFangBaseFragment;
import com.victtech.component.ImageDialog;
import com.victtech.model.entity.ChuFang;
import com.victtech.tools.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Richard on 2018/1/18.
 */

public class ChuFang1Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int ITEM_NORMAL = 1;
    private final int ITEM_FOOTER = 2;
    private Context mContext;
    private List<ChuFang> mList = new ArrayList<>();
    private ChuFangBaseFragment fragment;
    private String footerItem = null;
    private boolean finished = false;
    private FooterHolder footerHolder;
    public ChuFang1Adapter(List<ChuFang> mList, ChuFangBaseFragment fragment){
        this.mList = mList;
        this.fragment = fragment;
    }

    public void showFooter(RecyclerView recyclerView){
        LogUtil.d("**********","********");
        footerItem = "";
        mList.add(null);
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                notifyItemInserted((getItemCount()-1));
            }
        });
    }

    public void hideFooter(){
        footerItem = null;
        mList.remove(null);
        notifyDataSetChanged();
    }

    public void end(){
        finished = true;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view;
        if(viewType == ITEM_NORMAL){
            view = LayoutInflater.from(CFApplication.getContext()).inflate(R.layout.chufang1_item_layout,parent,false);
            ChuFangViewHolder vh = new ChuFangViewHolder(view);
            return vh;
        }else{
            view = LayoutInflater.from(CFApplication.getContext()).inflate(R.layout.chufang1_footer_item,parent,false);
            FooterHolder vh = new FooterHolder(view);
            footerHolder = vh;
            return vh;
        }


    }

    @Override
    public int getItemCount() {
        if(footerItem == null){
            return mList.size();
        }else{
            return mList.size();
        }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder vHolder, final int position) {
        if(finished && vHolder instanceof FooterHolder){
            FooterHolder fh = (FooterHolder)vHolder;
            fh.loadingImage.setVisibility(View.GONE);
            fh.loadingTxt.setText("没有更多了!");
            return;
        }

        if(vHolder instanceof FooterHolder){
            return;
        }


        ChuFangViewHolder holder = (ChuFangViewHolder)vHolder;
        final ChuFang chuFang = mList.get(position);
        if(chuFang == null){
            return ;
        }
        if(position==0){
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(holder.cardView.getLayoutParams());
            params.setMargins(0,0,0,0);
            holder.cardView.setLayoutParams(params);
        }
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .skipMemoryCache(true) //跳过内存缓存
                .diskCacheStrategy(DiskCacheStrategy.NONE)//不缓存到SDCard中
                .priority(Priority.HIGH);
//        Glide.with(mContext).load(mList.get(position).getAvatar()).apply(options).into(holder.avatar);
        holder.avatar.getImageLoader().requestBuilder(mList.get(position).getAvatar(),options)
                .transition(DrawableTransitionOptions.withCrossFade()).into(holder.avatar);
        holder.avatar.setShapeType(ShapeImageView.ShapeType.CIRCLE);
        holder.avatar.setBorderColor(R.color.colorPrimaryDark);
        holder.avatar.setBorderWidth(2);
        holder.avatar.setPressedAlpha(0.2f);
        holder.avatar.setPressed(true);
        holder.avatar.setPressedColor(R.color.colorPrimaryDark);
        holder.createdAt.setText("提交时间："+chuFang.getCreated_at());


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

        holder.processBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProcessActivity.class);
                intent.putExtra("id",chuFang.getId());
                fragment.startActivityForResult(intent,ChuFangBaseFragment.CHUFANG_PROCESS);
//                mContext.startActivityForResult()
            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        if(footerItem == null){
            return ITEM_NORMAL;
        }else{
            if(position == (getItemCount()-1)){
                return ITEM_FOOTER;
            }else{
                return ITEM_NORMAL;
            }
        }

    }

    class ChuFangViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
//        ImageView avatar;
        GlideImageView avatar;
        ImageView processBtn;
        TextView createdAt;
        public ChuFangViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            avatar = cardView.findViewById(R.id.cf1_avatar);
            processBtn = cardView.findViewById(R.id.cuf1_process_btn);
            createdAt = cardView.findViewById(R.id.created_at);
        }
    }

    class FooterHolder extends RecyclerView.ViewHolder{
        GlideImageView loadingImage;
        TextView loadingTxt;
        public FooterHolder(View itemView) {
            super(itemView);
            loadingImage = itemView.findViewById(R.id.loading_icon);
            loadingImage.loadLocalImage(R.drawable.loading,R.drawable.loading);
            loadingTxt = itemView.findViewById(R.id.loading_txt);
//            Glide.with(itemView).load(itemView.getResources().getDrawable(R.drawable.loading,null)).into(img);

        }
    }


}
