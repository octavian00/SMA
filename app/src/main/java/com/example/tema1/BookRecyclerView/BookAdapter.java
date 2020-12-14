package com.example.tema1.BookRecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tema1.BookModel;
import com.example.tema1.R;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookViewHolder> {
    private List<BookModel> bookModelList;
    private Context context;

    public BookAdapter(List<BookModel> bookModelList) {
        this.bookModelList=bookModelList;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.book_layout,parent,false);
        BookViewHolder bookViewHolder = new BookViewHolder(view);
        return bookViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        BookModel bookModel = bookModelList.get(position);
        holder.setValues(bookModel.getName(),bookModel.getAuthor());
    }

    @Override
    public int getItemCount() {
        return bookModelList.size();
    }
}
