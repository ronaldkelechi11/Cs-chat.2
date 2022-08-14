package com.bms.cschat.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.bms.cschat.classes.Note;

public class NotesSqliteManager extends SQLiteOpenHelper {

    private static NotesSqliteManager notesSqliteManager;

    private static final String DATABASE_NAME = "CSDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "note";
    private static final String COUNTER = "counter";

    private static final String ID_FIELD = "id";
    private static final String TITLE_FIELD = "id";
    private static final String DESCRIPTION_FIELD = "id";

    public NotesSqliteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static NotesSqliteManager instanceOfDatabase(Context context){
        if(notesSqliteManager == null)
            notesSqliteManager = new NotesSqliteManager(context);

        return notesSqliteManager;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
         StringBuilder sql;
         sql = new StringBuilder()
                 .append("CREATE TABLE")
                 .append(TABLE_NAME)
                 .append("(")
                 .append(COUNTER)
                 .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                 .append(ID_FIELD)
                 .append(" INT, ")
                 .append(TITLE_FIELD)
                 .append(" TEXT, ")
                 .append(DESCRIPTION_FIELD)
                 .append(" TEXT, ");
         sqLiteDatabase.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public void addNoteToDatabase(Note note){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, note.getId());
        contentValues.put(TITLE_FIELD, note.getTitle());
        contentValues.put(DESCRIPTION_FIELD, note.getDescription());

        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    }

    public void populateNoteArrayList(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null))
        {
            if (result.getCount() != 0){
                while (result.moveToNext()){
                    int id = result.getInt(1);
                    String title = result.getString(2);
                    String description = result.getString(3);
                    Note note = new Note(id,title,description);
                    Note.noteArrayList.add(note);
                }
            }
        }

    }

    public void updateNoteInDB(Note note){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, note.getId());
        contentValues.put(TITLE_FIELD, note.getTitle());
        contentValues.put(DESCRIPTION_FIELD, note.getDescription());

        sqLiteDatabase.update(TABLE_NAME, contentValues, ID_FIELD + " =? ", new String[]{String.valueOf(note.getId())});
    }

}
