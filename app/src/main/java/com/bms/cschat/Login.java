package com.bms.cschat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity{
    ConstraintLayout toRegister;
    EditText email,password;
    ProgressBar progressBar;
    Button loginButton;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Buttons
        toRegister = findViewById(R.id.toRegister);
        loginButton = findViewById(R.id.LoginButton);

        //EditText
        email = findViewById(R.id.EditTextLoginEmail);
        password = findViewById(R.id.EditTextLoginPassword);

        //Progress Bar
        progressBar = findViewById(R.id.progressBarLogin);

        //Firebase
        mAuth = FirebaseAuth.getInstance();

    }
}