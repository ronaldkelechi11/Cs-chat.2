package com.bms.cschat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class Register extends AppCompatActivity {
    ConstraintLayout toLogin;
    EditText nickname,email,password;
    Button registerButton;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        //Progress Bar
        progressBar  = findViewById(R.id.progressBarRegister);

        // Buttons
        toLogin = findViewById(R.id.toLogin);
        registerButton = findViewById(R.id.RegisterButton);

        //EditTexts
        nickname = findViewById(R.id.EditTextRegisterNickname);
        email = findViewById(R.id.EditTextRegisterEmail);
        password = findViewById(R.id.EditTextRegisterPassword);

    }
}