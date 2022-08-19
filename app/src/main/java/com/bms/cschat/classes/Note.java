package com.bms.cschat.classes;

import com.bms.cschat.managers.NotesSqliteManager;

import java.util.ArrayList;

public class Note {

    public static String NOTE_EDIT_EXTRA = "noteEdit";


    int id;
    String title;
    String description;

    // Constructor
    public Note(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public static Note getPassedNoteID(int passedNoteID) {
        for(Note note: NotesSqliteManager.noteArrayList){
            if(note.getId() == passedNoteID)
                return note;
        }
        return null;
    }

    //Getters
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }

    //Setters
    public void setDescription(String description) {
        this.description = description;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setId(int id) {
        this.id = id;
    }
}
