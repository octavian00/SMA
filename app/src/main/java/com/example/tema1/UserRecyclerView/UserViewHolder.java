package com.example.tema1.UserRecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tema1.R;

public class UserViewHolder extends RecyclerView.ViewHolder {
    private TextView tv_username,tv_age,tv_email;
    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        initialize();
    }
    private void initialize(){
        tv_username = itemView.findViewById(R.id.tv_username);
        tv_age = itemView.findViewById(R.id.tv_age);
        tv_email = itemView.findViewById(R.id.tv_emailadress);
    }
    public void setValues(String username, String email, int age){
        Log.e("ajung aiciHOlder",username);
        tv_username.setText(username);
        tv_email.setText(email);
        tv_age.setText(age+"");
    }
}
