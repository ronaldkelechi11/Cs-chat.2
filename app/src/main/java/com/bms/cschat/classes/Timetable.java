package com.bms.cschat.classes;

import java.util.ArrayList;

public class Timetable {
    String Day;
    String Location;
    String Time1;
    String Time2;

    public Timetable(String day, String location, String time1, String time2) {
        this.Day = day;
        this.Location = location;
        this.Time1 = time1;
        this.Time2 = time2;
    }

    public static ArrayList<Note> timetableArrayList = new ArrayList<>();

    //Getters
    public String getDay() {
        return Day;
    }
    public String getLocation() {
        return Location;
    }
    public String getTime2() {
        return Time2;
    }
    public String getTime1() {
        return Time1;
    }

    //Setters
    public void setLocation(String location) {
        Location = location;
    }
    public void setDay(String day) {
        Day = day;
    }
    public void setTime1(String time) {
        Time1 = time;
    }
    public void setTime2(String time2) {
        Time2 = time2;
    }


}
