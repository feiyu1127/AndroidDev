package com.victtech.cfapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.victtech.cfapp.R;
import com.victtech.cfapp.adapter.ChuFang2Adapter;

/**
 * Created by Richard on 2018/1/18.
 */

@SuppressLint("ValidFragment")
public class ChuFang2Fragment extends ChuFangBaseFragment {
    private View view;
    public ChuFang2Fragment(String type){
        this.setType(type);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.chufang2_layout,container,false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());

        RecyclerView recyclerView =  view.findViewById(R.id.cf2_card_list);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new ChuFang2Adapter());
        return view;
    }
}
