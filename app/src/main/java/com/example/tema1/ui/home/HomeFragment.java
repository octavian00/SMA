package com.example.tema1.ui.home;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tema1.BottomNavigationExampleActivity;
import com.example.tema1.R;
import com.example.tema1.database.User;
import com.example.tema1.database.UserDatabase;

import java.util.List;

public class HomeFragment extends Fragment {
    private EditText edt_username,edt_password,edt_age,edt__email;
    private Button btn_update;
    private UserDatabase userDatabase;
    private List<User> userList;
    private User userToEdit;
    private String username, password,emailAdress;
    private int age;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        initialize(root);
        GetFromDatabase();
        listener();
        return root;
    }
    private void initialize(View root){

        userDatabase = UserDatabase.getInstance(getContext());
       edt_username = root.findViewById(R.id.edt_usernameEdit);
       edt_password = root.findViewById(R.id.edt_passwordEdit);
       edt__email = root.findViewById(R.id.edt_emailAdressEdit);
       edt_age = root.findViewById(R.id.edt_ageEdit);
       btn_update = root.findViewById(R.id.btn_update);
    }
    private void setText(User u){
        edt_username.setText(u.getUsername());
        edt_password.setText(u.getPassword());
        edt_age.setText(u.getAge()+"");
        edt__email.setText(u.getEmailadress());
    }

    private User getUserFromDatabase(){
        for(User user:userList){
            if(user.getUsername().equals(BottomNavigationExampleActivity.username)){
                return user;
            }
        }
        return null;
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
                userToEdit = getUserFromDatabase();
                if(userToEdit!=null){
                    setText(userToEdit);
                }
            }
        }
        GetValue getValue = new GetValue();
        getValue.execute();
    }

    private void UpdateDatabase(){
        class UpdateValue extends AsyncTask<Void,Void, User>{

            @Override
            protected User doInBackground(Void... voids) {
                userDatabase.userDAO().update(username,password,emailAdress,age);
                return new User();
            }
            @Override
            protected void onPostExecute(User testEntity) {
                super.onPostExecute(testEntity);
            }
        }
        UpdateValue updateValue = new UpdateValue();
        updateValue.execute();
    }

    private void listener(){
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData();
                UpdateDatabase();
            }
        });
    }
    private void setData(){
        username = edt_username.getText().toString();
        password = edt_password.getText().toString();
        emailAdress = edt__email.getText().toString();
        age= Integer.parseInt(edt_age.getText().toString());
    }
}