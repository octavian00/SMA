package com.example.tema1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class File extends AppCompatActivity {
    private Button btn_afisazaFile,btn_readFile;
    private TextView tv_sharedFile;
    private EditText edt_sharedFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        init();
        listener();
    }
    private void init(){
        btn_afisazaFile = findViewById(R.id.btn_afisazaFile);
        tv_sharedFile = findViewById(R.id.tv_textSharedFile);
        edt_sharedFile = findViewById(R.id.edt_textSharedFile);
        btn_readFile = findViewById(R.id.btn_readFile);
    }
    private void writeFile()  {
        try{
            OutputStreamWriter outputStreamWriter =
                    new OutputStreamWriter(this.openFileOutput(AppConstants.MY_FILE, Context.MODE_PRIVATE));
            outputStreamWriter.write(edt_sharedFile.getText().toString());
            outputStreamWriter.close();
            tv_sharedFile.setText("salvare reusita");
            Toast.makeText(this,"Salvare reusita",Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            Log.e("File Activity","File not found" + e.toString());
        } catch (IOException e) {
            Log.e("File Activity","Can't write file" + e.toString());
        }

    }
    private void readFile(){
        try{
            InputStream inputStream = this.openFileInput(AppConstants.MY_FILE);
            if(inputStream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ((receiveString = bufferedReader.readLine()) != null){
                    stringBuilder.append(receiveString);
                }
                inputStream.close();
                tv_sharedFile.setText(stringBuilder.toString());
            }
        } catch (FileNotFoundException e) {
            Log.e("File Activity","File not found" + e.toString());
        } catch (IOException e) {
            Log.e("File Activity","Can't read file" + e.toString());
        }

    }
    private void listener(){
        btn_afisazaFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeFile();
            }
        });
        btn_readFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFile();
            }
        });
    }
}