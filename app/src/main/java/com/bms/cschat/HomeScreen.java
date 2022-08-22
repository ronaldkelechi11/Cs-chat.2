package com.bms.cschat;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
    RecyclerView newsRecylerView;
    ArrayList<News> newsArrayList;
    DatabaseReference databaseReference;
    NewsAdapter newsAdapter;

    ProgressBar progressBar;
    TextView progressBarTextView;

    int Counter = 1;
    TimetableSqliteManager timetableSqliteManager = TimetableSqliteManager.instanceOfDatabase(this);
    NotesSqliteManager notesSqliteManager = NotesSqliteManager.instanceOfDatabase(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        timetableSqliteManager.populateTimetableArrayList();
        notesSqliteManager.populateNoteArrayList();

        progressBar = findViewById(R.id.progressBarHomeScreen);
        progressBarTextView = findViewById(R.id.progressBarHomeScreenText);

//        //For News Display Still a lot of work to be done for it to work well
//        newsRecylerView = findViewById(R.id.newsRecyclerView);
//        databaseReference = FirebaseDatabase.getInstance().getReference("News");
//        newsRecylerView.setLayoutManager(new LinearLayoutManager(HomeScreen.this));
//
//        newsArrayList = new ArrayList<>();
//        newsAdapter = new NewsAdapter(getApplicationContext(),newsArrayList);
//        newsRecylerView.setAdapter(newsAdapter);
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    News news = dataSnapshot.getValue(News.class);
//                    newsArrayList.add(news);
//                }
//                newsAdapter.notifyDataSetChanged();
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getApplicationContext(), "Error Fetching News Updates", Toast.LENGTH_SHORT).show();
//            }
//        });


    }//End of Initial Class

    public void goToBrowser(View view) {
        Intent b = new Intent(getApplicationContext(),Browser.class);
        startActivity(b);
    }
    public void goToNotes(View view) {
        Intent n = new Intent(getApplicationContext(),Notes.class);
        startActivity(n);
    }
    public void lockedFeatures(View view) {
        Toast.makeText(getApplicationContext(), "Locked Feature wait till further notice", Toast.LENGTH_SHORT).show();
    }
    public void goToTimetable(View view) {
        Intent tt = new Intent(getApplicationContext(),TimeTable.class);
        startActivity(tt);
    }

}