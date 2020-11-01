package com.example.tema1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tema1.database.User;
import com.example.tema1.database.UserDatabase;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity  {
    private EditText edt_username,edt_password;
    private TextView txt_register;
    private Button btn_login;
    private UserDatabase userDatabase;
    private String username, password;
    private List<User> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
        listener();
    }

    private void initialize(){
        userList = new ArrayList<>();
        userDatabase = UserDatabase.getInstance(this);
        edt_password = findViewById(R.id.edt_Passwrod);
        edt_username = findViewById(R.id.edt_Username);
        btn_login = findViewById(R.id.btn_Login);
        txt_register = findViewById(R.id.tv_register);
    }

    private void listener(){
        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeParams();
                GetFromDatabase();

            }
        });
    }

    private void initializeParams(){
        username = edt_username.getText().toString();
        password = edt_password.getText().toString();
    }

    private boolean checkIfUsernameAndPasswordisCorrect(){
        for(User user:userList){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    private void GetFromDatabase(){
        class GetValue extends AsyncTask<Void,Void, User>{

            @Override
            protected User doInBackground(Void... voids) {
                userList = userDatabase.userDAO().getAll();
                return new User();
            }
            @Override
            protected void onPostExecute(User testEntity) {
                super.onPostExecute(testEntity);
                if(checkIfUsernameAndPasswordisCorrect()) {
                    nextActivity(BottomNavigationExampleActivity.class,username);
                }
            }
        }
        GetValue getValue = new GetValue();
        getValue.execute();
    }
    private void nextActivity(Class classs,String username){
        Intent intent = new Intent(this, classs);
        intent.putExtra("username", username);
        startActivity(intent);
    }

}