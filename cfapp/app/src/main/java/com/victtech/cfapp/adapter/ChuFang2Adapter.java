package com.victtech.cfapp.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.victtech.cfapp.CFApplication;
import com.victtech.cfapp.R;
import com.victtech.model.entity.ChuFang;
import com.victtech.tools.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Richard on 2018/1/18.
 */

public class ChuFang2Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public final int TYPE_FOOTER = 1;
    public final int TYPE_NOMAL = 2;
    private List<ChuFang> mList = new ArrayList<>();
//    private View headerView;
    private String footerView = null;
    public boolean finished = false;

//    public View getFooterView() {
//        return footerView;
//    }

    public void showFooterView(RecyclerView recyclerView) {
        this.footerView = "";
        mList.add(null);
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                notifyItemInserted(getItemCount()-1);
            }
        });
//        notifyItemInserted(getItemCount()-1);
//        notifyItemRangeInserted(0,getItemCount()-1);
//        notifyDataSetChanged();
    }

    public void hideFooterView(){
        this.footerView = null;
        mList.remove(null);
        LogUtil.d("^^^^^^^^^^^^^^^", "hideFooterView: "+mList);
        notifyDataSetChanged();
    }

    public ChuFang2Adapter(List<ChuFang> mList){
        this.mList = mList;
    }

    public void setFinished(){
        finished = true;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(viewType == TYPE_FOOTER){
            view = LayoutInflater.from(CFApplication.getContext()).inflate(R.layout.chufang_item_footer,parent,false);
            FooterViewHolder fvh = new FooterViewHolder(view);
            return fvh;
        }else{
            view = LayoutInflater.from(CFApplication.getContext()).inflate(R.layout.chufang2_item_layout,parent,false);
            ChuFangViewHolder vh = new ChuFangViewHolder(view);
            return vh;
        }


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(finished && holder instanceof FooterViewHolder ){
            FooterViewHolder fvh = (FooterViewHolder)holder;
            LogUtil.d("*********", "ChuFangViewHolder: "+ fvh.textView);
            fvh.textView.setText("没有更多数据了");
            return;
        }

        if( holder instanceof FooterViewHolder){
            return;
        }else{
            ChuFangViewHolder cfv = (ChuFangViewHolder) holder;
            if(mList.get(position)!=null){
                cfv.nameView.setText(mList.get(position).getName());
            }

        }
    }

    @Override
    public int getItemCount() {
        if(footerView == null){
            return mList.size();
        }else {
            return mList.size() + 1;
        }

    }



    @Override
    public int getItemViewType(int position) {
        if(footerView == null){
            return TYPE_NOMAL;
        }
        if(position == getItemCount() -1){
            return TYPE_FOOTER;
        }else{
            return TYPE_NOMAL;
        }

    }

    class ChuFangViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView nameView;
        public ChuFangViewHolder(View itemView) {
            super(itemView);
            LogUtil.d("+++++", "ChuFangViewHolder: ");
            cardView = (CardView) itemView;
            nameView = itemView.findViewById(R.id.cf2_name);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public FooterViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.footer_text);
        }
    }
}
