package com.example.tema1;

import com.example.tema1.database.User;

public class StorageHelper {
    private static StorageHelper instance;
    private User userModel;
    public static StorageHelper getInstance(){
        if(instance == null){
            instance = new StorageHelper();
        }
        return instance;
    }

    public User getUserModel() {
        return userModel;
    }

    public void setUserModel(User userModel) {
        this.userModel = userModel;
    }
}
