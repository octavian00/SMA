package com.example.tema1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tema1.database.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class RegisterFirebase extends AppCompatActivity {
    private TextInputEditText emailEt;
    private TextInputEditText passowrdEt;
    private TextInputEditText nameEt;
    private FirebaseHelper firebaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_firebase);
        initializeViews();
    }
    private void initializeViews(){
        emailEt = findViewById(R.id.et_register_email);
        passowrdEt = findViewById(R.id.et_register_password);
        nameEt = findViewById(R.id.et_register_name);
        firebaseHelper=FirebaseHelper.getInstance();
    }


    public void onRegister(View view) {
        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if(emailEt.getText().toString().isEmpty() || passowrdEt.getText().toString().isEmpty()){
            Toast.makeText(this,"Please fill email and password", Toast.LENGTH_SHORT).show();
            return;
        }
        final String email = emailEt.getText().toString();
        final String password = passowrdEt.getText().toString();
        final String name = nameEt.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if(user ==null){
                        return;
                    }
                    User userModel =new User(name,"zuzu",email,22);
                    firebaseHelper.usersDatabase.child(user.getUid()).setValue(userModel);
                    startActivity(new Intent(RegisterFirebase.this,LoginFirebase.class));
                    Toast.makeText(getBaseContext(), "Register succesfuly",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getBaseContext(),"Failed registery",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}