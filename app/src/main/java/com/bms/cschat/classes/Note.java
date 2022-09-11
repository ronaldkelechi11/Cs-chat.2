package com.bms.cschat.classes;

import com.bms.cschat.managers.NotesSqliteManager;

import java.util.ArrayList;

public class Note {
    public static String NOTE_EDIT_EXTRA = "noteEdit";
    public static ArrayList<Note> noteArrayList = new ArrayList<>();

    int noteCounter = 1;

    int id;
    String title;
    String description;
    String date;

    public Note(int id, String title, String description, String date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
