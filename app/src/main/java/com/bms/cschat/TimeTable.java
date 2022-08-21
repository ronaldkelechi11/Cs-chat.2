package com.bms.cschat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toolbar;

import com.bms.cschat.adapters.TimetableAdapter;
import com.bms.cschat.classes.Timetable;
import com.bms.cschat.managers.TimetableSqliteManager;

public class TimeTable extends AppCompatActivity {
    TimetableAdapter timetableAdapter;
    ListView timeTabelListView;

    TimetableSqliteManager timetableSqliteManager = TimetableSqliteManager.instanceOfDatabase(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        timetableSqliteManager.populateTimetableArrayList();

        timeTabelListView = findViewById(R.id.timetableListView);
        timetableAdapter = new TimetableAdapter(this, Timetable.timeTableArrayList);
        timeTabelListView.setAdapter(timetableAdapter);

    }

    public void newTimetable(View view) {
        Intent i = new Intent(getApplicationContext(),NewTimetable.class);
        startActivity(i);
        finish();
    }

}