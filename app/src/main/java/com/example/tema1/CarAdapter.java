package com.example.tema1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarViewHolder> {
    private List<CarModel> carModels;
    private Context context;
    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.car_layout,parent,false);
        CarViewHolder carViewHolder = new CarViewHolder(contactView);
        return carViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        CarModel carModel = carModels.get(position);
        holder.setValues(carModel.getType(),carModel.getYear());

    }

    @Override
    public int getItemCount() {
        return carModels.size();
    }

    public CarAdapter(List<CarModel> carModels) {
        this.carModels = carModels;
    }
}
