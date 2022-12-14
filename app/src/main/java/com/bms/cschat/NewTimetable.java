package com.bms.cschat;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bms.cschat.classes.Timetable;
import com.bms.cschat.managers.TimetableSqliteManager;

import java.util.ArrayList;

public class NewTimetable extends AppCompatActivity {
    EditText day;
    EditText course;
    EditText location;
    EditText time1;
    EditText time2;

    AlertDialog alertDialog;
    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_timetable);

        day = findViewById(R.id.timetableDay);
        course = findViewById(R.id.timetableCourse);
        location = findViewById(R.id.timetableLocation);
        time1 = findViewById(R.id.timetableTime1);
        time2 = findViewById(R.id.timetableTime2);
    }

    public void cancel(View view) {

        builder = new AlertDialog.Builder(NewTimetable.this);
        builder.setMessage("Are you sure you wish to exit?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                alertDialog.cancel();
            }
        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        alertDialog.cancel();
                    }
                });

        alertDialog = builder.create();
        alertDialog.show();
    }

    public void saveTimetable(View view) {
        //Declare Variable to String
        int id = TimetableSqliteManager.timeTableArrayList.size();
        String dayTxt = day.getText().toString();
        String courseTxt = course.getText().toString();
        String locationTxt = location.getText().toString();
        String time1Txt = time1.getText().toString();
        String time2Txt = time2.getText().toString();


        ArrayList<String> days = new ArrayList<>();

        days.add("monday");
        days.add("tuesday");
        days.add("wednesday");
        days.add("thursday");
        days.add("friday");
        days.add("saturday");

        days.add("Monday ");
        days.add("Tuesday ");
        days.add("Wednesday ");
        days.add("Thursday ");
        days.add("Friday ");
        days.add("Saturday ");

        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");

        //Some Validation
        if(!days.contains(dayTxt)){
           day.setError("Not a Valid Date");
           day.requestFocus();
           return;
        }
        if(courseTxt.isEmpty()){
            course.setError("Course can't be empty");
            course.requestFocus();
            return;
        }
        if(locationTxt.isEmpty()){
            location.setError("Location cannot be Blank");
            location.requestFocus();
            return;
        }
        if(time1Txt.isEmpty()){
            time1.setError("Start Time cannot be Blank");
            time1.requestFocus();
            return;
        }
        if(!time1Txt.contains("m")){
            time1.setError("Must indicate am or pm e.g 11:00am");
            time1.requestFocus();
            return;
        }
        if(time2Txt.isEmpty()){
            time2.setError("End Time cannot be Blank");
            time2.requestFocus();
            return;
        }
        if(!time2Txt.contains("m")){
            time2.setError("Must indicate am or pm e.g 2:30am");
            time2.requestFocus();
            return;
        }

        TimetableSqliteManager timetableSqliteManager = TimetableSqliteManager.instanceOfDatabase(this);
        Timetable newTimetable = new Timetable(id,courseTxt,locationTxt,time1Txt,time2Txt,dayTxt);

        timetableSqliteManager.addTimeTableToDatabase(newTimetable);
        Timetable.timeTableArrayList.add(newTimetable);


        Toast.makeText(getApplicationContext(), "New TimeTable Added", Toast.LENGTH_SHORT).show();
        finish();

    }

}