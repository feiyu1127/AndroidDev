package com.example.admin.chufang.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.chufang.R;
import com.example.admin.chufang.adapter.InvalidRecipeAdapter;

/**
 * Created by admin on 2018/1/22.
 */

@SuppressLint("ValidFragment")
public class InvalidRecipeFragment extends BaseRecipeFragment {
    private View view;

    public InvalidRecipeFragment(String type){
        this.setType(type);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.invalid_recipe,container,false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());

        RecyclerView recyclerView = view.findViewById(R.id.invalid_recipe);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new InvalidRecipeAdapter());
        return view;
    }
}
