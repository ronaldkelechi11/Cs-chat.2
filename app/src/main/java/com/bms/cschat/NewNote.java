package com.bms.cschat;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NewNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
    }

    public void saveNote(View view) {

    }

    public void cancel(View view) {
        Intent c = new Intent(getApplicationContext(),Notes.class);
        startActivity(c);
        finish();
    }
}