package com.bms.cschat;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.bms.cschat.classes.Note;

public class NewNote extends AppCompatActivity {
    EditText title,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);


    }

    public void saveNote(View view) {
        String titletxt = title.getText().toString();
        String desctxt = description.getText().toString();
        int id = Note.noteArrayList.size();

        Note newNote = new Note(id,titletxt,desctxt);
        Note.noteArrayList.add(newNote);
        Intent i = new Intent(getApplicationContext(),Notes.class);
        startActivity(i);
        finish();
    }

    public void cancel(View view) {
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}