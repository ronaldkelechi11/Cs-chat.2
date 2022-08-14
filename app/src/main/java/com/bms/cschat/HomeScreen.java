package com.bms.cschat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import android.widget.LinearLayout;


public class HomeScreen extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

    }

    public void goToBrowser(View view) {
        Intent b = new Intent(getApplicationContext(),Browser.class);
        startActivity(b);
    }

    public void goToNotes(View view) {
        Intent n = new Intent(getApplicationContext(),Notes.class);
        startActivity(n);
    }


    public void lockedFeatures(View view) {
        Toast.makeText(getApplicationContext(), "Locked Feature", Toast.LENGTH_SHORT).show();
    }

    public void goToTimetable(View view) {
        Intent tt = new Intent(getApplicationContext(),TimeTable.class);
        startActivity(tt);
    }

}