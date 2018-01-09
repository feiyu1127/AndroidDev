package com.example.admin.listviewtest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by admin on 2018/1/8.
 */

public class FruitAdapter extends ArrayAdapter<Fruit> {
    private int resourceId;

    public FruitAdapter(Context context, int textViewResourceId, List<Fruit> objects){
        super(context,textViewResourceId,objects); //重写父类的构造方法
        resourceId = textViewResourceId;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
         Fruit fruit = getItem(position);
         View view = LayoutInflater.from(this.getContext()).inflate(resourceId,parent,false);
         ImageView fruitImage = view.findViewById(R.id.fruit_image);
         TextView fruitName = view.findViewById(R.id.fruit_name);
         fruitImage.setImageResource(fruit.getImageId());
         fruitName.setText(fruit.getName());
         return  view;
    }
}
