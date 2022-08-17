package com.bms.cschat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bms.cschat.classes.Note;
import com.bms.cschat.managers.NotesSqliteManager;

public class NewNote extends AppCompatActivity {
    EditText title,description;
    Note selectedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        title = findViewById(R.id.noteTitleEditText);
        description = findViewById(R.id.noteDescriptionEdittext);
        checkForEditNote();


    }

    private void checkForEditNote() {
        Intent previousIntent = getIntent();
        int passedNoteID = previousIntent.getIntExtra(Note.NOTE_EDIT_EXTRA, 0);
        selectedNote = Note.getPassedNoteID(passedNoteID);
        if(selectedNote != null){
            title.setText(selectedNote.getTitle());
            description.setText(selectedNote.getDescription());
        }
    }

    public void saveNote(View view) {
        NotesSqliteManager notesSqliteManager = NotesSqliteManager.instanceOfDatabase(this);
        String titletxt = title.getText().toString();
        String desctxt = description.getText().toString();
        int id = Note.noteArrayList.size();

        if(selectedNote == null){
            Note newNote = new Note(id,titletxt,desctxt);
            Note.noteArrayList.add(newNote);
            notesSqliteManager.addNoteToDatabase(newNote);
        }
        else{
            selectedNote.setTitle(titletxt);
            selectedNote.setDescription(desctxt);
            notesSqliteManager.updateNoteInDB(selectedNote);
        }
        Intent i = new Intent(getApplicationContext(),Notes.class);
        startActivity(i);
        finish();
    }

    public void cancel(View view) {
        finish();
    }

}