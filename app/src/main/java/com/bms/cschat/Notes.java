package com.bms.cschat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;


public class Notes extends AppCompatActivity {
    FloatingActionButton fab;
    EditText searchNotes;
    ListView notesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        //Initialization of Views
        fab = findViewById(R.id.fabNotes);
        searchNotes = findViewById(R.id.searchNoteEditText);
        notesListView = findViewById(R.id.notesListView);


        //To hide FAB if Keyboard is Visible
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


    }
    public void goToNewNote(View view) {
        Intent nt = new Intent(getApplicationContext(),NewNote.class);
        startActivity(nt);
    }

    public void backToHome(View view) {
        finish();
    }
}