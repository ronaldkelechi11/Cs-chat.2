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

        title = findViewById(R.id.noteTitleEditText);
        description = findViewById(R.id.noteDescriptionEdittext);
    }

    public void saveNote(View view) {
        String titletxt = title.getText().toString();
        String desctxt = description.getText().toString();
        int id = Note.noteArrayList.size();

        Note newNote = new Note(id,titletxt,desctxt);
        Note.noteArrayList.add(newNote);
        finish();
    }

    public void cancel(View view) {
        finish();
    }
}