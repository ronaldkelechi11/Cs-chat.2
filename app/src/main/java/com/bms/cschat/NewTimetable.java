package com.bms.cschat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bms.cschat.classes.Timetable;

import java.util.ArrayList;

public class NewTimetable extends AppCompatActivity {
    EditText day,location,time1,time2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_timetable);

        day = findViewById(R.id.timetableDay);
        location = findViewById(R.id.timetableLocation);
        time1 = findViewById(R.id.timetableTime1);
        time2 = findViewById(R.id.timetableTime2);
    }

    public void cancel(View view) {
        //TODO: Add an Alert so that it doesn't happen by mistake
        finish();
    }

    public void saveTimetable(View view) {
        //Convert to String
        String dayTxt = day.getText().toString();
        String locationTxt = location.getText().toString();
        String time1Txt = time1.getText().toString();
        String time2Txt = time2.getText().toString();
        int id = Timetable.timetableArrayList.size();

        ArrayList<String> days = new ArrayList<>();
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


        Timetable newTimetable = new Timetable(id,dayTxt,locationTxt,time1Txt,time2Txt);
        Timetable.timetableArrayList.add(newTimetable);
        Toast.makeText(getApplicationContext(), "New TimeTable Added", Toast.LENGTH_SHORT).show();
        finish();
    }

}