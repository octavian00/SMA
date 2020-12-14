package com.example.tema1.BookRecyclerView;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tema1.R;

public class BookViewHolder extends RecyclerView.ViewHolder {
    private TextView tv_name,tv_author;
    public BookViewHolder(@NonNull View itemView) {
        super(itemView);
        initialize();
    }

    private void initialize() {
        tv_name = itemView.findViewById(R.id.tv_bookName);
        tv_author = itemView.findViewById(R.id.tv_bookAuthor);
    }

    public void setValues(String name, String author){
        tv_name.setText(name);
        tv_author.setText(author);
    }
}
