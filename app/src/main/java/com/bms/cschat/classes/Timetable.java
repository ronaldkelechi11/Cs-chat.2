package com.bms.cschat.classes;

import java.util.ArrayList;

public class Timetable {
    String Day,Location,Time;

    public Timetable(String day, String location, String time) {
        this.Day = day;
        this.Location = location;
        this.Time = time;
    }
    //TODO: Change time to a time format to work better or leave as it is

    public static ArrayList<Note> timetableArrayList = new ArrayList<>();

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }



}
