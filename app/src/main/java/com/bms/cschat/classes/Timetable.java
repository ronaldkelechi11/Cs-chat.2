package com.bms.cschat.classes;

import java.util.ArrayList;

public class Timetable {
    int id;
    String Day;





    String Location;
    String Time1;
    String Time2;

    public static ArrayList<Timetable> timetableArrayList = new ArrayList<>();

    public Timetable(int id, String day, String location, String time1, String time2) {
        this.id = id;
        this.Day = day;
        this.Location = location;
        this.Time1 = time1;
        this.Time2 = time2;
    }

    //Getters
    public int getId() {
        return id;
    }
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
    public void setId(int id) {
        this.id = id;
    }
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
