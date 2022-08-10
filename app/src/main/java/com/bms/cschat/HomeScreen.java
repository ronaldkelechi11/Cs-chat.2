package com.bms.cschat;

import static com.bms.cschat.R.color.red;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class HomeScreen extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

    }

    //Click On Linear Layouts
    public void goToBrowser(View view) {
        Intent b = new Intent(getApplicationContext(),Browser.class);
        startActivity(b);
    }

    public void goToNotes(View view) {
        TextView t = (TextView) findViewById(R.id.notesText);
        t.setTextColor(getResources().getColor(red));
        Intent n = new Intent(getApplicationContext(),Notes.class);
        startActivity(n);
    }

    public void moreButton(View view) {
        Toast.makeText(getApplicationContext(), "Locked", Toast.LENGTH_SHORT).show();
    }
}