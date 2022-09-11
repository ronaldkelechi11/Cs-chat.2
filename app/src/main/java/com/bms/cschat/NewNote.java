package com.bms.cschat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bms.cschat.classes.Note;
import com.bms.cschat.managers.NotesSqliteManager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewNote extends AppCompatActivity {
    AlertDialog dialog;
    AlertDialog.Builder builder;

    EditText titleEditText,descriptionEditText;

    NotesSqliteManager notesSqliteManager = NotesSqliteManager.instanceOfDatabase(this);

    public Note selectedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        titleEditText = findViewById(R.id.titleNewNote);
        descriptionEditText = findViewById(R.id.descriptionNewNote);

        notesSqliteManager.populateNoteArrayList();

        //Once it is created it should check if the note was sent
        checkForEditNote();

    }//End Of Initial Class

    public void goToNotes(View view) {
        builder = new AlertDialog.Builder(NewNote.this);
        builder.setMessage("Are you sure you want to leave? Note might not be saved.");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                dialog.cancel();
            }
        })
        .setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.cancel();
            }
        });
        dialog = builder.create();
        dialog.show();
    }

    public void saveNote(View view) {
        // To get Date
        Date date = new Date();
        String myDateFormat = "MMMM dd, yyyy hh:mm a";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myDateFormat);

        String titletxt = titleEditText.getText().toString();
        String descriptiontxt = descriptionEditText.getText().toString();
        String theDate = dateFormat.format(date);

        //Minor Validator just for title
        if(titletxt.isEmpty()){
            titleEditText.setError("Title Is required");
            titleEditText.requestFocus();
            return;
        }

        try{
            if(selectedNote == null){
                int id = Note.noteArrayList.size();
                Note note = new Note(id,titletxt,descriptiontxt,theDate);

                //Adding the newNoteItem to the ArrayList and DB
                Note.noteArrayList.add(note);
                notesSqliteManager.addNoteToDatabase(note);
                Toast.makeText(getApplicationContext(), "Note Added successfully", Toast.LENGTH_SHORT).show();
            }
            else{
                //Updates the title, description and date
                selectedNote.setTitle(titletxt);
                selectedNote.setDescription(descriptiontxt);
                selectedNote.setDate(theDate);
                Toast.makeText(getApplicationContext(), "Note Updated Successfully", Toast.LENGTH_SHORT).show();
            }
            finish();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Note was not saved", Toast.LENGTH_SHORT).show();
        }
    }

    //Check onCreate to see if the note is to be edited
    private void checkForEditNote() {
        Intent getEditNote = getIntent();
        int passedNoteId = getEditNote.getIntExtra(Note.NOTE_EDIT_EXTRA,-1);
        selectedNote = Note.getNoteForId(passedNoteId);
        if(selectedNote != null){
            titleEditText.setText(selectedNote.getTitle());
            descriptionEditText.setText(selectedNote.getDescription());
        }
    }



}