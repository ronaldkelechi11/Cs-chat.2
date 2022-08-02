package com.bms.cschat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

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

        // Button Functionality for To login click
        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t = new Intent(getApplicationContext(),Login.class);
                startActivity(t);
            }
        });

        // Button Functionality to Register User
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Progressbar visibility
                progressBar.setVisibility(View.VISIBLE);

                //EditText to String
                String nicknameST = nickname.getText().toString();
                String emailST = email.getText().toString();
                String passwordST = password.getText().toString();

                // Front-End Validation
                if(nicknameST.isEmpty()){
                    nickname.setError("Nickname cannot be Empty");
                    nickname.requestFocus();
                    return;
                }
                if(emailST.isEmpty()){
                    email.setError("Email cannot be Empty");
                    email.requestFocus();
                    return;
                }
                if(passwordST.isEmpty()){
                    password.setError("Password cannot be Empty");
                    password.requestFocus();
                    return;
                }
                if(passwordST.length() < 6){
                    password.setError("Password must be greater than 6");
                    password.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(emailST).matches()){
                    email.setError("Not a valid email address");
                    email.requestFocus();
                    return;
                }

                Toast.makeText(getApplicationContext(),"Button click",Toast.LENGTH_SHORT).show();



            }
        });


    }

}