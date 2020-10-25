package com.example.tema1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPreference extends AppCompatActivity {
    private Button btn_afisaza;
    private TextView tv_shared;
    private EditText edt_shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        init();
        listener();
    }
    private void init(){
        btn_afisaza = findViewById(R.id.btn_afisaza);
        tv_shared = findViewById(R.id.tv_textShared);
        edt_shared = findViewById(R.id.edt_textShared);
    }
    private void writeSharedPref(){
       SharedPreferences.Editor editor = getSharedPreferences(AppConstants.MY_PREFS,MODE_PRIVATE).edit();
       editor.putString(AppConstants.MY_KEY,edt_shared.getText().toString());
       editor.apply();
    }

    private void readSharedPref(){
        SharedPreferences prefs = getSharedPreferences(AppConstants.MY_PREFS, MODE_PRIVATE);
        String name = prefs.getString(AppConstants.MY_KEY,"no text");
        tv_shared.setText(name);
    }
    private void listener(){
        btn_afisaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeSharedPref();
                readSharedPref();
            }
        });
    }
}