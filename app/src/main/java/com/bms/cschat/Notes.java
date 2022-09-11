package com.bms.cschat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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
    NotesSqliteManager notesSqliteManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        //Initialization of Views
        fab = findViewById(R.id.fabNotes);
        searchNotes = findViewById(R.id.searchNoteEditText);
        notesListView = findViewById(R.id.notesListView);

        //Initialization of Database object
        notesSqliteManager = NotesSqliteManager.instanceOfDatabase(this);
        // Hahaha forgot to populate arraylist onStart
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
    }//End of Initial Class

    public void goToNewNote(View view) {
        Intent nt = new Intent(getApplicationContext(),NewNote.class);
        startActivity(nt);
    }

    public void backToHome(View view) {
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        noteAdapter.notifyDataSetChanged();
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(noteAdapter!=null){
            noteAdapter.notifyDataSetChanged();
        }
    }
}