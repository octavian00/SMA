package com.example.tema1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tema1.database.User;
import com.example.tema1.database.UserDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity{
    EditText edt_username,edt_password;
    TextView txt_register;
    Button btn_register;
    private UserDatabase userDatabase;
    private String username, password;
    private List<User> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initialize();
        listener();
    }
    private void initialize(){
        userList = new ArrayList<>();
        edt_password = findViewById(R.id.edt_PasswrodRegister);
        edt_username = findViewById(R.id.edt_UsernameRegister);
        btn_register = findViewById(R.id.btn_Register);
        txt_register = findViewById(R.id.tv_login);
        userDatabase = UserDatabase.getInstance(this);
    }

    private void listener(){
        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(registerIntent);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_username.getText().toString().isEmpty()||edt_password.toString().isEmpty()){
                    return;
                }
                username = edt_username.getText().toString();
                password = edt_password.getText().toString();
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        insertToDatabase(username,password);
                    }
                });
            }
        });

    }


    private void insertToDatabase(final String username, final String password){
        class InsertValue extends AsyncTask<Void,Void,User>{

            @Override
            protected User doInBackground(Void... voids) {
               User user = new User(username,password);
               userDatabase.userDAO().insertAll(user);
               return user;
            }

        }
        InsertValue insertValue = new InsertValue();
        insertValue.execute();
        GetFromDatabase();
        nextActivity(BottomNavigationExampleActivity.class);
    }

    private void nextActivity(Class classs){
        Intent intent = new Intent(this, classs);
        startActivity(intent);
    }

    private void GetFromDatabase(){
        class GetValue extends AsyncTask<Void,Void, User>{

            @Override
            protected User doInBackground(Void... voids) {
                userList = userDatabase.userDAO().getAll();
                Log.e("sizeList",userList.size()+"");
                for (User user : userList) {
                    System.out.println(user.getUsername()+ " "+ user.getPassword());
                }
                return new User();
            }
        }
        GetValue getValue = new GetValue();
        getValue.execute();
    }
}