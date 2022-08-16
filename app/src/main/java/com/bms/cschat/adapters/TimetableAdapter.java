package com.bms.cschat.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.bms.cschat.classes.Timetable;

import java.util.List;

public class TimetableAdapter extends ArrayAdapter<Timetable> {

    public TimetableAdapter(@NonNull Context context, List<Timetable> timetable) {
        super(context, 0, timetable);
    }
        //TODO: Complete the Adapter for Timetable
}
