package com.example.admin.chufang.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.chufang.R;
import com.example.admin.chufang.entity.Medicine;

import java.util.List;

/**
 * Created by admin on 2018/1/24.
 */

public class MedicineAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Medicine> mRegulars;
    private DisplayMetrics dm ;

    public MedicineAdapter(Context context, List<Medicine> regulars) {
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dm = context.getResources().getDisplayMetrics();
        mRegulars = regulars;
    }

    @Override
    public int getCount() {
        if(mRegulars == null) return 0;
        return mRegulars.size();
    }

    @Override
    public Object getItem(int position) {
        return mRegulars.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        if (convertView == null) {
            v = mInflater.inflate(R.layout.check_recipe_item, parent,false);
        } else {
            v = convertView;
        }
        //Log.v("Regular", position + " " + v.hashCode() + "  " + checkedPosition);

        Medicine item = (Medicine) getItem(position);
        TextView tv1=(TextView) v.findViewById(R.id.regularCph);
        TextView tv2 = (TextView) v.findViewById(R.id.regularFcsj);
        TextView tv3  = (TextView) v.findViewById(R.id.regularLc);
        TextView tv4 = (TextView) v.findViewById(R.id.regularZDZ);

        tv1.setWidth(dm.widthPixels/4);
        tv2.setWidth(dm.widthPixels/4);
        tv3.setWidth(dm.widthPixels/4);
        tv4.setWidth(dm.widthPixels/4);

        tv1.setText(item.getMedicineCode());
        tv2.setText(item.getMedicineName());
        tv3.setText(""+item.getMedicineTrademark() + "");
        tv4.setText(""+item.getMedicineNum() + "");

        return v;
    }


}
