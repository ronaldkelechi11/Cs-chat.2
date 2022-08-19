package com.bms.cschat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ListView;

import com.bms.cschat.adapters.NoteAdapter;
import com.bms.cschat.classes.Note;
import com.bms.cschat.managers.NotesSqliteManager;


public class Notes extends AppCompatActivity {
    ListView noteListView;
    NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        //TODO: Try to create a function that is called to check if it has
        // been populated before and not do it again

        loadFromDB();
        noteListView = findViewById(R.id.notesListView);
        noteAdapter = new NoteAdapter(getApplicationContext(), Note.noteArrayList);
        noteListView.setAdapter(noteAdapter);

        onClickListener();


    }

    private void loadFromDB() {
        NotesSqliteManager notesSqliteManager = NotesSqliteManager.instanceOfDatabase(this);
        notesSqliteManager.populateNoteArrayList();
    }


    private void onClickListener() {
        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Note selectedNote = (Note) noteListView.getItemAtPosition(position);
                Intent editNoteIntent = new Intent(getApplicationContext(), NewNote.class);
                editNoteIntent.putExtra(Note.NOTE_EDIT_EXTRA,selectedNote.getId());
                startActivity(editNoteIntent);
            }
        });
    }


    public void toNewNote(View view) {
        Intent i = new Intent(Notes.this,NewNote.class);
        startActivity(i);
    }

}