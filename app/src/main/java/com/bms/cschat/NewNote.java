package com.bms.cschat;

import android.content.DialogInterface;
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
    NotesSqliteManager notesSqliteManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        titleEditText = findViewById(R.id.titleNewNote);
        descriptionEditText = findViewById(R.id.descriptionNewNote);

        notesSqliteManager = new NotesSqliteManager(this);
        notesSqliteManager.populateNoteArrayList();

    }//End Of Initial Class

    public void goToNotes(View view) {
        builder = new AlertDialog.Builder(NewNote.this);
        builder.setMessage("Are you sure you want to leave ? Note might not be saved.");
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


        int id = Note.noteArrayList.size();
        String titletxt = titleEditText.getText().toString();
        String descriptiontxt = descriptionEditText.getText().toString();
        String theDate = dateFormat.format(date);

        //Some Validation only for the title
        if(titletxt.isEmpty()){
            titleEditText.setError("Field cannot be empty");
            titleEditText.requestFocus();
            return;
        }
        try{
            NotesSqliteManager notesSqliteManager1 = new NotesSqliteManager(this);
            Note note = new Note(id,titletxt,descriptiontxt,theDate);
            Note.noteArrayList.add(note);
            notesSqliteManager1.addNoteToDatabase(note);
            Toast.makeText(getApplicationContext(), "Note Added successfully", Toast.LENGTH_SHORT).show();
            finish();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Note could not add successfully", Toast.LENGTH_SHORT).show();
        }
    }
}