package com.bms.cschat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),NewNote.class);
                startActivity(intent);
                TextView t = findViewById(R.id.noteTitle);
                String savedTitle = t.getText().toString();
                intent.putExtra("savedTitle",savedTitle);

            }
        });



    }
    public void toNewNote(View view) {
        Intent i = new Intent(getApplicationContext(),NewNote.class);
        startActivity(i);
    }
    public void toSavedNote(View view) {
        TextView savedTitle = findViewById(R.id.noteTitle);
        Intent i = new Intent(getApplicationContext(),NewNote.class);
        i.putExtra("savedTitle",savedTitle.getText().toString());
        startActivity(i);
    }
}