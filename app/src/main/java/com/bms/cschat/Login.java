package com.bms.cschat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;

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


        // Button Functionality for To Register click
        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t = new Intent(getApplicationContext(),Register.class);
                startActivity(t);
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailSt = email.getText().toString();
                String passwordSt = password.getText().toString();

                //Front-End Validation
                if(emailSt.isEmpty()){
                    email.setError("Email cannot be empty");
                    email.requestFocus();
                    return;
                }
                if(passwordSt.isEmpty()){
                    password.setError("Password cannot be empty");
                    password.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(emailSt).matches()){
                    email.setError("Not a valid Email Address");
                    email.requestFocus();
                    return;
                }
                if(passwordSt.length() < 6){
                    password.setError("Invalid Password");
                    password.requestFocus();
                    return;
                }




            }
        });

    }
}