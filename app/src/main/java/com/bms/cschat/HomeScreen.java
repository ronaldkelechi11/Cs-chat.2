package com.bms.cschat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

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



        }
    }



}