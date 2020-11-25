package com.example.tema1;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHelper {
    public static final DatabaseReference usersDatabase = FirebaseDatabase.getInstance().getReference("users");
}
