package com.bms.cschat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bms.cschat.adapters.NoteAdapter;
import com.bms.cschat.classes.Note;
import com.bms.cschat.managers.NotesSqliteManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;


public class Notes extends AppCompatActivity {
    FloatingActionButton fab;
    EditText searchNotes;
    ListView notesListView;
    NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        //Initialization of Views
        fab = findViewById(R.id.fabNotes);
        searchNotes = findViewById(R.id.searchNoteEditText);
        notesListView = findViewById(R.id.notesListView);

        //Initialization of Database object
        NotesSqliteManager notesSqliteManager;
        notesSqliteManager = NotesSqliteManager.instanceOfDatabase(this);
        notesSqliteManager.populateNoteArrayList();



        noteAdapter = new NoteAdapter(getApplicationContext(), Note.noteArrayList);
        notesListView.setAdapter(noteAdapter);

        //To hide FAB if Keyboard is Visible working perfectly
        KeyboardVisibilityEvent.setEventListener(this, new KeyboardVisibilityEventListener() {
            @Override
            public void onVisibilityChanged(boolean isOpen) {
                if(isOpen){
                    fab.setVisibility(View.GONE);
                }
                else{
                    fab.setVisibility(View.VISIBLE);
                }
            }
        });


        //Single Click to edit
        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Note selectedNote = (Note) notesListView.getItemAtPosition(position);
                Intent editNoteIntent = new Intent(getApplicationContext(), NewNote.class);
                editNoteIntent.putExtra(Note.NOTE_EDIT_EXTRA,selectedNote.getId());

                startActivity(editNoteIntent);
            }
        });


        //Long click to delete
        notesListView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(getApplicationContext(), "Note Deleted Successfully", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


    }//End of Initial Class

    public void goToNewNote(View view) {
        Intent nt = new Intent(getApplicationContext(),NewNote.class);
        startActivity(nt);
    }
    public void backToHome(View view) {
        finish();
    }
}