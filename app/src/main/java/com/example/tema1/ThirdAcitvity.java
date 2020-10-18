package com.example.tema1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ThirdAcitvity extends AppCompatActivity {
    private RecyclerView carRv;
    private List<CarModel> carModels;
    private CarAdapter carAdapter;
    private TextView typeTv,yearTv;
    private Button addBtn,deleteBtn;
    private Integer yearCar;
    private String typeCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_acitvity);
        initializeViews();
        initializeList();
        setRecycleView();
        setListener();
    }
    private void initializeList(){
        carModels = new ArrayList<>();
        carModels.add(new CarModel("Dacia",2005));
        carModels.add(new CarModel("Renault",2010));

    }
    private void setRecycleView(){
        carAdapter = new CarAdapter(carModels);
        carRv.setLayoutManager(new LinearLayoutManager(this));
        carRv.setAdapter(carAdapter);
    }

    private void initializeViews(){
        carRv = findViewById(R.id.rv);
        typeTv = findViewById(R.id.edt_Type);
        yearTv = findViewById(R.id.edt_Year);
        addBtn = findViewById(R.id.btn_add);
        deleteBtn = findViewById(R.id.btn_delete);
    }

    private void setListener(){
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarModel carModel=new CarModel(typeTv.getText().toString(),Integer.parseInt(yearTv.getText().toString()));
                carModels.add(carModel);
                carAdapter.notifyDataSetChanged();
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringStergere=typeTv.getText().toString();
                if(!stringStergere.isEmpty()){
                    for(CarModel carModel:carModels){
                        if(carModel.getType().equals(stringStergere)){
                            carModels.remove(carModel);
                            carAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }
}