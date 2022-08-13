package com.bms.cschat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bms.cschat.adapters.NoteAdapter;
import com.bms.cschat.classes.Note;


public class Notes extends AppCompatActivity {
    ListView noteListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);



        noteListView = findViewById(R.id.notesListView);
        NoteAdapter noteAdapter = new NoteAdapter(getApplicationContext(), Note.noteArrayList);
        noteListView.setAdapter(noteAdapter);
        

    }

    public void toNewNote(View view) {
        Intent i = new Intent(getApplicationContext(),NewNote.class);
        startActivity(i);
    }

}