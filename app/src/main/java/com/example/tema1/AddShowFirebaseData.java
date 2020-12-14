package com.example.tema1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tema1.BookRecyclerView.BookAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class AddShowFirebaseData extends AppCompatActivity {
    FirebaseHelper firebaseHelper;
    private EditText edt_author,edt_name;
    private Button btn_add;
    private List<BookModel> bookModelList;
    private BookAdapter bookAdapter;
    private RecyclerView bookListRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_show_firebase_data);
        initializeViews();
        listener();
        getBooks();
    }
    private void initializeViews(){
        firebaseHelper = FirebaseHelper.getInstance();
        edt_name=findViewById(R.id.edt_Book);
        edt_author = findViewById(R.id.edt_bookAuthor);
        btn_add = findViewById(R.id.btn_addBook);
        bookListRv = findViewById(R.id.rv_books);
    }
    private void listener(){
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToDatabase();
                bookAdapter.notifyDataSetChanged();
            }
        });
    }
    private void initializeList(){
        bookModelList = new ArrayList<>();
    }
    private void addToDatabase(){
        String name = edt_name.getText().toString();
        String author = edt_author.getText().toString();
        if(name.isEmpty() || author.isEmpty()){
            return;
        }
        UUID uuid=UUID.randomUUID();
        BookModel bookModel = new BookModel(name,author);
        Log.e("NULL",bookModel.getName()+" - "+author);
        firebaseHelper.bookDatabase.child(uuid.toString()).setValue(bookModel);
        Toast.makeText(this,"Success Add",Toast.LENGTH_LONG).show();
    }
    private void getBooks(){
        FirebaseHelper.bookDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                initializeList();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    BookModel bookModel=dataSnapshot.getValue(BookModel.class);
                    bookModelList.add(bookModel);
                }
                setRecycleView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AddShowFirebaseData.this,"GetBooks Error",Toast.LENGTH_SHORT).show();
                Log.d("FIREBASE","Failed getBooks");
            }
        });
    }
    private void setRecycleView(){
        bookAdapter = new BookAdapter(bookModelList);
        bookListRv.setLayoutManager(new LinearLayoutManager(this));
        bookListRv.setAdapter(bookAdapter);
    }
}