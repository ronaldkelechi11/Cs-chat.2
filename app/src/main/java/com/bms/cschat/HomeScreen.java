package com.bms.cschat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

public class HomeScreen extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
//  Code here is to get snapshot from the database


    }

//    I used switch case to get click on a massive scale instead of initializing all and
//    having a massive code base
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.boardButton:
                Toast.makeText(getApplicationContext(), "Board is Locked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.timetableButton:
                Toast.makeText(getApplicationContext(), "Timetable is Locked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.notesButton:
                Toast.makeText(getApplicationContext(), "Notes is Locked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tradeButton:
                Toast.makeText(getApplicationContext(), "Trade is Locked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ebookButton:
                Toast.makeText(getApplicationContext(), "E-books is Locked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.browserButton:
                Intent i = new Intent(getApplicationContext(),Browser.class);
                startActivity(i);
                break;
            case R.id.reminderButton:
                Toast.makeText(getApplicationContext(), "Reminders is Locked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.moreButton:
                Toast.makeText(getApplicationContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
                break;
        }
    }



}