package com.example.tema1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private TextView textView;
    private Button button;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setText();
        button=findViewById(R.id.btn_NextActivity);
        onClick();

    }

    private void onClick() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity();
            }
        });
    }

    private void setText(){
        textView=findViewById(R.id.text_Text);
        Intent intent = getIntent();
        text = intent.getStringExtra("text");
        textView.setText(text);
    }
    private void nextActivity(){
        startActivity(new Intent(this,ThirdAcitvity.class));
    }
}