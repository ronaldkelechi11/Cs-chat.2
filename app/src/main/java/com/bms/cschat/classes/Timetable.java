package com.bms.cschat.classes;

import java.util.AbstractCollection;
import java.util.ArrayList;

public class Timetable {

    public static ArrayList<Timetable> timeTableArrayList = new ArrayList<>();

    int id;
   String course;
   String location;
   String time1;
   String time2;
   String date;

    public Timetable(int id, String course, String location, String time1, String time2, String date) {
        this.id = id;
        this.course = course;
        this.location = location;
        this.time1 = time1;
        this.time2 = time2;
        this.date = date;
    }

    //Getters
    public int getId() {
        return id;
    }
    public String getDate() {
        return date;
    }
    public String getCourse() {
        return course;
    }
    public String getLocation() {
        return location;
    }
    public String getTime1() {
        return time1;
    }
    public String getTime2() {
        return time2;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setTime2(String time2) {
        this.time2 = time2;
    }
    public void setTime1(String time1) {
        this.time1 = time1;
    }
}
