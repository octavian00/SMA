package com.example.tema1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LinearLayout ll_dialog;
    Button btn_ok,btn_cancel,btn_arataDialogul,
            btn_sharedPreferences,btn_file,btn_room,btn_login,btn_fireaseLogin, btn_addBook;
    EditText edt_Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
        onClickButtons();
    }
    private void setViews(){
        ll_dialog=findViewById(R.id.linearLayout_Dialog);
        btn_arataDialogul = findViewById(R.id.btn_showDialog);
        btn_ok = findViewById(R.id.btn_OK);
        btn_cancel = findViewById(R.id.btn_Cancel);
        edt_Text = findViewById(R.id.edt_Text);
        btn_sharedPreferences = findViewById(R.id.btn_sharedPreferences);
        btn_file = findViewById(R.id.btn_file);
        btn_room = findViewById(R.id.btn_room);
        btn_login = findViewById(R.id.btn_login);
        btn_fireaseLogin = findViewById(R.id.btn_loginFirebase);
        btn_addBook = findViewById(R.id.btn_addBookFirebase);
    }

    private void onClickButtons(){
        btn_arataDialogul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_dialog.setVisibility(LinearLayout.VISIBLE);
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Operatie anulata",Toast.LENGTH_SHORT).show();
                ll_dialog.setVisibility(LinearLayout.INVISIBLE);
            }
        });
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text= edt_Text.getText().toString();
                if(text.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Introuceti un text",Toast.LENGTH_SHORT).show();
                }else{
                    newActivity(text);
                    ll_dialog.setVisibility(LinearLayout.INVISIBLE);
                }

            }
        });
        btn_sharedPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedActivity(SharedPreference.class);
            }
        });
        btn_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedActivity(File.class);
            }
        });
        btn_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedActivity(RoomPlusSQL.class);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedActivity(LoginActivity.class);
            }
        });
        btn_fireaseLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedActivity(LoginFirebase.class);
            }
        });
        btn_addBook.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedActivity(AddShowFirebaseData.class);
            }
        }));
    }
    private void newActivity(String text){
        Intent intent = new Intent(this,SecondActivity.class);
        intent.putExtra("text",text);
        startActivity(intent);
    }
    private void sharedActivity(Class classs){
        Intent intent = new Intent(this, classs);
        startActivity(intent);
    }
}