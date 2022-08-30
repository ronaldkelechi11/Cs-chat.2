package com.bms.cschat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private static final String SHARED_PREFERNCE_NAME = "mySharedPreferences";
    SharedPreferences myPref;
    private  static final String KEY = "default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void goToLogin(View view) {
        Intent l = new Intent(getApplicationContext(), Login.class);
        startActivity(l);
        finish();
    }

    public void gotoRegister(View view) {
        Intent i = new Intent(getApplicationContext(), Register.class);
        startActivity(i);
        finish();
    }
}