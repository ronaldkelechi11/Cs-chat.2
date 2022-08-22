package com.bms.cschat.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;
import android.util.Log;

import com.bms.cschat.classes.Timetable;

import java.util.ArrayList;

public class TimetableSqliteManager extends SQLiteOpenHelper {

    public static ArrayList<Timetable> timeTableArrayList = new ArrayList<>();

    private static TimetableSqliteManager timeTableSqliteManger;

    private static final String DATABASE_NAME = "CSD.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "timetable";
    private static final String COUNTER = "Counter";


    private static final String ID_FIELD = "id";
    private static final String DAY_FIELD = "title";
    private static final String LOCATION_FIELD = "location";
    private static final String TIME1_FIELD = "time1";
    private static final String TIME2_FILED = "time2";


    public TimetableSqliteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static TimetableSqliteManager instanceOfDatabase(Context context){
        if(timeTableSqliteManger == null)
            timeTableSqliteManger = new TimetableSqliteManager(context);

        return timeTableSqliteManger;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String customQuery =
                "CREATE TABLE " + TABLE_NAME
                        + "(" + COUNTER + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + ID_FIELD + " INT, "
                        + DAY_FIELD + " TEXT, "
                        + LOCATION_FIELD +  " TEXT, "
                        + TIME1_FIELD + " TEXT, "
                        + TIME2_FILED + " TEXT);" ;
        sqLiteDatabase.execSQL(customQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public void addTimeTableToDatabase(Timetable timetable){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID_FIELD, timetable.getId());
        contentValues.put(DAY_FIELD, timetable.getDay());
        contentValues.put(LOCATION_FIELD, timetable.getLocation());
        contentValues.put(TIME1_FIELD, timetable.getTime1());
        contentValues.put(TIME2_FILED, timetable.getTime2());

        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    }

    public void populateTimetableArrayList(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try (Cursor results = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME,null))
        {
            if(results.getCount() != 0){
                while (results.moveToNext()){
                    int id = results.getInt(1);
                    String day = results.getString(2);
                    String location = results.getString(3);
                    String time1 = results.getString(4);
                    String time2 = results.getString(5);
                    Timetable timetable = new Timetable(id,day,location,time1,time2);
                    Timetable.timeTableArrayList.add(timetable);
                    break;
                }
            }
        }
    }

}
