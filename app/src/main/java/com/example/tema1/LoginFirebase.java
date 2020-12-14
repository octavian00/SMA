package com.example.tema1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tema1.database.User;
import com.example.tema1.pictures.PhotoGalery;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class LoginFirebase extends AppCompatActivity {
    private TextInputEditText emailEt;
    private TextInputEditText passwordEt;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_firebase);
        initializeViews();
    }
    private void initializeViews(){
        emailEt = findViewById(R.id.et_login_email);
        passwordEt = findViewById(R.id.et_login_password);
    }

    public void onLogin(View view) {
        firebaseAuth = FirebaseAuth.getInstance();
        if(emailEt.getText().toString().isEmpty() || passwordEt.getText().toString().isEmpty()){
            Toast.makeText(this,"Please fill email and password",Toast.LENGTH_SHORT).show();
            return;
        }
        String email = emailEt.getText().toString();
        String password = passwordEt.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    FirebaseHelper.usersDatabase.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            User userModel = snapshot.getValue(User.class);
                            StorageHelper.getInstance().setUserModel(userModel);
                            startActivity(new Intent(LoginFirebase.this, PhotoGalery.class));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }else{
                    Toast.makeText(LoginFirebase.this,"Login failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void goToRegister(View view) {
        startActivity(new Intent(LoginFirebase.this,RegisterFirebase.class));
    }
}