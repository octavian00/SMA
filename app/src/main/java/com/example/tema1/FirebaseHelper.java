package com.example.tema1;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHelper {
    private static FirebaseHelper firebaseHelper;
    public static  DatabaseReference usersDatabase;
    public static  DatabaseReference bookDatabase;
    private FirebaseHelper(){}
    public static FirebaseHelper getInstance(){
        if(firebaseHelper == null){
            firebaseHelper = new FirebaseHelper();
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            bookDatabase = FirebaseDatabase.getInstance().getReference().child("books");
            usersDatabase = FirebaseDatabase.getInstance().getReference().child("users");
        }
        return firebaseHelper;
    }

}
