package com.example.tema1.UserRecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tema1.R;
import com.example.tema1.database.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private List<User> userList;
    private Context context;

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        Log.e("adapter","ajung aici");
        View view = layoutInflater.inflate(R.layout.user_layout,parent,false);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return  userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.setValues(user.getUsername(),user.getEmailadress(),user.getAge());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }
}
