package com.bms.cschat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bms.cschat.R;
import com.bms.cschat.classes.Note;
import com.bms.cschat.classes.Timetable;

import java.util.List;

public class TimetableAdapter extends ArrayAdapter<Timetable> {

    public TimetableAdapter(@NonNull Context context,@NonNull List<Timetable> timetable) {
        super(context, 0, timetable);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Timetable timetable = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_timetable_item,parent,false);

        TextView day = convertView.findViewById(R.id.date);
        TextView location = convertView.findViewById(R.id.location);
        TextView time1 = convertView.findViewById(R.id.time1);
        TextView time2 = convertView.findViewById(R.id.time2);

        day.setText(timetable.getDay());
        location.setText(timetable.getLocation());
        time1.setText(timetable.getTime1());
        time2.setText(timetable.getTime2());

        return convertView;
    }
}
