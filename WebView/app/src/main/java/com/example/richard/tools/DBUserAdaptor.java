package com.example.richard.tools;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.richard.db.DBUser;
import com.example.richard.webview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Richard on 2018/1/5.
 */

public class DBUserAdaptor extends RecyclerView.Adapter<DBUserAdaptor.ViewHoder> {
    private List<DBUser> userlist = new ArrayList<DBUser>();
    private ClickDbUserLisioner lisioner;
    public DBUserAdaptor(List<DBUser> userlist){
        this.userlist = userlist;
    }

    public DBUserAdaptor(){

    }

    public void addUser(DBUser user){
        userlist.add(user);
    }

    public void setLisioner(ClickDbUserLisioner lisioner){
        this.lisioner = lisioner;
    }

    @Override
    public ViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.db_user_item,parent,false);
        final ViewHoder hoder = new ViewHoder(view);

        //点击每一项触发事件。
        hoder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = hoder.getAdapterPosition();
                lisioner.onClickItem(userlist.get(position));
            }
        });


        hoder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = hoder.getAdapterPosition();
                lisioner.onClickEditBtn(userlist.get(position).getId(),position);
            }
        });

        hoder.removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = hoder.getAdapterPosition();
                lisioner.onClickRemoveBtn(userlist.get(position).getId());
            }
        });
        return hoder;
    }

    @Override
    public void onBindViewHolder(ViewHoder holder, int position) {
//        holder.itemView.setBackgroundColor(Color.parseColor("#cccccc"));
//        holder.name.setBackgroundColor(Color.parseColor("#cccccc"));
//        holder.name.setTextColor(Color.parseColor("#ffffff"));
        DBUser user = userlist.get(position);
        holder.name.setText(user.getName());
        holder.age.setText(user.getAge());
        holder.sex.setText(user.getSex());
    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }

    public void clear(){
        userlist.clear();
    }

    static class ViewHoder extends ViewHolder{
        View itemView;
        TextView name;
        TextView sex;
        TextView age;
        Button editBtn;
        Button removeBtn;
        public ViewHoder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.db_item_name);
            sex = itemView.findViewById(R.id.db_item_sex);
            age = itemView.findViewById(R.id.db_item_age);
            editBtn = itemView.findViewById(R.id.edit_btn);
            removeBtn = itemView.findViewById(R.id.remove_btn);
            this.itemView = itemView;
        }
    }
}
