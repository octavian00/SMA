package com.example.tema1;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CarViewHolder  extends RecyclerView.ViewHolder{
    private TextView typeTv,yearTv;
    public CarViewHolder(@NonNull View itemView) {
        super(itemView);
        initializeViews();
    }

    private void initializeViews() {
        typeTv = itemView.findViewById(R.id.tv_name);
        yearTv = itemView.findViewById(R.id.tv_year);
    }

    public void setValues(String type,Integer year){
        typeTv.setText(type);
        yearTv.setText(year.toString());
    }
}
