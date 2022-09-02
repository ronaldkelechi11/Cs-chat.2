package com.bms.cschat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class Notes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);


    }
    public void goToNewNote(View view) {
        Intent nt = new Intent(getApplicationContext(),NewNote.class);
        startActivity(nt);
    }
}