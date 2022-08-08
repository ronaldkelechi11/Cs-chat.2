package com.bms.cschat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

public class HomeScreen extends AppCompatActivity{
    LinearLayout browserButton;
    LinearLayout notesButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        browserButton = findViewById(R.id.browserButton);
        notesButton = findViewById(R.id.notesButton);


        notesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n = new Intent(getApplicationContext(),Notes.class);
                startActivity(n);
            }
        });
        browserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(getApplicationContext(),Browser.class);
                startActivity(b);
            }
        });



    }
}