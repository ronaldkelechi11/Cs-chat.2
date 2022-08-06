package com.bms.cschat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    int splashscreentime = 2000;

    private static final String SHARED_PREFERNCE_NAME = "mySharedPreferences";
    SharedPreferences myPref;
    private  static final String KEY = "default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Go to FullScreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


myPref = getSharedPreferences(SHARED_PREFERNCE_NAME,MODE_PRIVATE);
String IS_LOGGED_IN = myPref.getString(KEY,"default");

        // Increase Splashscreen delay time by increasing the variable above
        if(IS_LOGGED_IN.equals("true")){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent t = new Intent(getApplicationContext(),HomeScreen.class);
                    startActivity(t);
                    finish();
                }
            },splashscreentime);
        }
        else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(getApplicationContext(),Register.class);
                    startActivity(i);
                    finish();
                }
            },splashscreentime);
        }




    }
}