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

import java.util.List;

public class NoteAdapter extends ArrayAdapter<Note> {

    public NoteAdapter(@NonNull Context context, List<Note> notes) {
        super(context,0,notes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Note note = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_notes_item,parent,false);


        return convertView;
    }
}
