package com.bms.cschat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import android.widget.LinearLayout;

import com.bms.cschat.adapters.NewsAdapter;
import com.bms.cschat.classes.News;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HomeScreen extends AppCompatActivity{
        RecyclerView newsRecyclerView;
        DatabaseReference databaseReference;
        ArrayList<News> news;
        NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        newsRecyclerView = findViewById(R.id.newsRecyclerView);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        news = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("News");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                        News n = snapshot.getValue(News.class);
                        news.add(n);
                    }
                    newsAdapter = new NewsAdapter(getApplicationContext(),news);
                    newsRecyclerView.setAdapter(newsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Error Fetching News Feed", Toast.LENGTH_SHORT).show();
            }
        });

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