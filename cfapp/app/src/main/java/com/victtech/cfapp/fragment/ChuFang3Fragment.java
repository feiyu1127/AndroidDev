package com.victtech.cfapp.fragment;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.victtech.cfapp.CFApplication;
import com.victtech.cfapp.R;
import com.victtech.cfapp.adapter.ChuFang2Adapter;
import com.victtech.cfapp.adapter.ChuFang3Adapter;

/**
 * Created by Richard on 2018/1/18.
 */

@SuppressLint("ValidFragment")
public class ChuFang3Fragment extends ChuFangBaseFragment {
    private View view;
    public ChuFang3Fragment(String type){
        this.setType(type);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.chufang3_layout,container,false);

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        GridLayoutManager layoutManager =  new GridLayoutManager(CFApplication.getContext(),2);

        RecyclerView recyclerView =  view.findViewById(R.id.cf3_card_list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new ChuFang3Adapter());
        return view;
    }
}
