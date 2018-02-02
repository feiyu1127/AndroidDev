package com.victtech.cfapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.victtech.cfapp.R;
import com.victtech.model.entity.Medicine;

import java.util.List;

/**
 * Created by Richard on 2018/1/23.
 */

public class MedicineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Medicine> mList;

    public MedicineAdapter(List<Medicine> mList){
        this.mList = mList;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.medicine_layout,parent,false);
        MedicineAdapter.MedicineHolder holder = new MedicineHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Medicine medicine = mList.get(position);
        MedicineHolder mHoder = (MedicineHolder)holder;
        mHoder.serNum.setText(medicine.getCode());
        mHoder.medicineName.setText(medicine.getName());
        mHoder.medicineVendor.setText(medicine.getManu_name());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MedicineHolder extends RecyclerView.ViewHolder {
        TextView serNum;
        TextView medicineName;
        TextView medicineVendor;
        public MedicineHolder(View itemView) {
            super(itemView);
            serNum = itemView.findViewById(R.id.ser_num);
            medicineName = itemView.findViewById(R.id.medicine_name);
            medicineVendor = itemView.findViewById(R.id.medicine_vendor);
        }
    }
}
