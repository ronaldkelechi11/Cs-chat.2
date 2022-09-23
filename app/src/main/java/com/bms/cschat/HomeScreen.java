package com.bms.cschat;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bms.cschat.adapters.NewsAdapter;
import com.bms.cschat.classes.News;

import com.bms.cschat.managers.NotesSqliteManager;
import com.bms.cschat.managers.TimetableSqliteManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HomeScreen extends AppCompatActivity{
    TextView seeMore;
    TableRow thirdrow;
    CardView crgButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        //Field Initialization
        seeMore = findViewById(R.id.seeMore);
        thirdrow = findViewById(R.id.thirdRow);
        crgButton = findViewById(R.id.readingGroupCreate);


    }//End of Initial Class

    public void goToNotes(View view) {
        Intent i = new Intent(getApplicationContext(),Notes.class);
        startActivity(i);
    }

    public void changeVisi(View view) {
        if(seeMore.getText().toString() == "See more"){
            seeMore.setText("See less");
            thirdrow.setVisibility(View.VISIBLE);
            crgButton.setVisibility(View.GONE);
        }
        else{
            seeMore.setText("See more");
            thirdrow.setVisibility(View.GONE);
            crgButton.setVisibility(View.VISIBLE);
        }
    }
}